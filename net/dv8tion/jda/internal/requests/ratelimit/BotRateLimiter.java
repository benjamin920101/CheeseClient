/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.ratelimit;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.internal.requests.RateLimiter;
import net.dv8tion.jda.internal.requests.Requester;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.requests.ratelimit.IBucket;
import okhttp3.Headers;
import okhttp3.Response;
import org.jetbrains.annotations.Contract;

public class BotRateLimiter
extends RateLimiter {
    private static final String RESET_AFTER_HEADER = "X-RateLimit-Reset-After";
    private static final String RESET_HEADER = "X-RateLimit-Reset";
    private static final String LIMIT_HEADER = "X-RateLimit-Limit";
    private static final String REMAINING_HEADER = "X-RateLimit-Remaining";
    private static final String GLOBAL_HEADER = "X-RateLimit-Global";
    private static final String HASH_HEADER = "X-RateLimit-Bucket";
    private static final String RETRY_AFTER_HEADER = "Retry-After";
    private static final String UNLIMITED_BUCKET = "unlimited";
    private final ReentrantLock bucketLock = new ReentrantLock();
    private final Set<Route> hitRatelimit = ConcurrentHashMap.newKeySet(5);
    private final Map<Route, String> hashes = new ConcurrentHashMap<Route, String>();
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<String, Bucket>();
    private final Map<Bucket, Future<?>> rateLimitQueue = new ConcurrentHashMap();
    private Future<?> cleanupWorker;

    public BotRateLimiter(Requester requester) {
        super(requester);
    }

    @Override
    public void init() {
        this.cleanupWorker = this.getScheduler().scheduleAtFixedRate(this::cleanup, 30L, 30L, TimeUnit.SECONDS);
    }

    private ScheduledExecutorService getScheduler() {
        return this.requester.getJDA().getRateLimitPool();
    }

    @Override
    public int cancelRequests() {
        return MiscUtil.locked(this.bucketLock, () -> {
            AtomicInteger count = new AtomicInteger(0);
            this.buckets.values().stream().map(Bucket::getRequests).flatMap(Collection::stream).filter(request -> !request.isPriority() && !request.isCancelled()).forEach(request -> {
                request.cancel();
                count.incrementAndGet();
            });
            int cancelled = count.get();
            if (cancelled == 1) {
                RateLimiter.log.warn("Cancelled 1 request!");
            } else if (cancelled > 1) {
                RateLimiter.log.warn("Cancelled {} requests!", (Object)cancelled);
            }
            return cancelled;
        });
    }

    private void cleanup() {
        MiscUtil.locked(this.bucketLock, () -> {
            int size = this.buckets.size();
            Iterator<Map.Entry<String, Bucket>> entries = this.buckets.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Bucket> entry = entries.next();
                String key = entry.getKey();
                Bucket bucket = entry.getValue();
                bucket.requests.removeIf(Request::isSkipped);
                if (bucket.isUnlimited() && bucket.requests.isEmpty()) {
                    entries.remove();
                    continue;
                }
                if (!bucket.requests.isEmpty() || bucket.reset > this.getNow()) continue;
                entries.remove();
            }
            if ((size -= this.buckets.size()) > 0) {
                log.debug("Removed {} expired buckets", (Object)size);
            }
        });
    }

    private String getRouteHash(Route route) {
        return this.hashes.getOrDefault(route, "unlimited+" + route);
    }

    @Override
    protected boolean stop() {
        return MiscUtil.locked(this.bucketLock, () -> {
            if (this.isStopped) {
                return false;
            }
            super.stop();
            if (this.cleanupWorker != null) {
                this.cleanupWorker.cancel(false);
            }
            this.cleanup();
            int size = this.buckets.size();
            if (!this.isShutdown && size > 0) {
                int average = (int)Math.ceil(this.buckets.values().stream().map(Bucket::getRequests).mapToInt(Collection::size).average().orElse(0.0));
                log.info("Waiting for {} bucket(s) to finish. Average queue size of {} requests", (Object)size, (Object)average);
            }
            return size < 1;
        });
    }

    @Override
    public Long getRateLimit(Route.CompiledRoute route) {
        Bucket bucket = this.getBucket(route, false);
        return bucket == null ? 0L : bucket.getRateLimit();
    }

    @Override
    protected void queueRequest(Request request) {
        MiscUtil.locked(this.bucketLock, () -> {
            Bucket bucket = this.getBucket(request.getRoute(), true);
            bucket.enqueue(request);
            this.runBucket(bucket);
        });
    }

    @Override
    protected Long handleResponse(Route.CompiledRoute route, Response response) {
        return MiscUtil.locked(this.bucketLock, () -> {
            long rateLimit = this.updateBucket(route, response).getRateLimit();
            if (response.code() == 429) {
                return rateLimit;
            }
            return null;
        });
    }

    private Bucket updateBucket(Route.CompiledRoute route, Response response) {
        return MiscUtil.locked(this.bucketLock, () -> {
            try {
                Bucket bucket = this.getBucket(route, true);
                Headers headers = response.headers();
                boolean global = headers.get(GLOBAL_HEADER) != null;
                boolean cloudflare = headers.get("via") == null;
                String hash = headers.get(HASH_HEADER);
                long now = this.getNow();
                Route baseRoute = route.getBaseRoute();
                if (hash != null) {
                    if (!this.hashes.containsKey(baseRoute)) {
                        this.hashes.put(baseRoute, hash);
                        log.debug("Caching bucket hash {} -> {}", (Object)baseRoute, (Object)hash);
                    }
                    bucket = this.getBucket(route, true);
                }
                if (response.code() == 429) {
                    String retryAfterHeader = headers.get(RETRY_AFTER_HEADER);
                    long retryAfter = this.parseLong(retryAfterHeader) * 1000L;
                    if (global) {
                        this.requester.getJDA().getSessionController().setGlobalRatelimit(now + retryAfter);
                        log.error("Encountered global rate limit! Retry-After: {} ms", (Object)retryAfter);
                    } else if (cloudflare) {
                        this.requester.getJDA().getSessionController().setGlobalRatelimit(now + retryAfter);
                        log.error("Encountered cloudflare rate limit! Retry-After: {} s", (Object)(retryAfter / 1000L));
                    } else {
                        boolean firstHit = this.hitRatelimit.add(baseRoute) && retryAfter < 60000L;
                        bucket.remaining = 0;
                        bucket.reset = this.getNow() + retryAfter;
                        if (firstHit) {
                            log.debug("Encountered 429 on route {} with bucket {} Retry-After: {} ms", baseRoute, bucket.bucketId, retryAfter);
                        } else {
                            log.warn("Encountered 429 on route {} with bucket {} Retry-After: {} ms", baseRoute, bucket.bucketId, retryAfter);
                        }
                    }
                    return bucket;
                }
                if (hash == null) {
                    return bucket;
                }
                String limitHeader = headers.get(LIMIT_HEADER);
                String remainingHeader = headers.get(REMAINING_HEADER);
                String resetAfterHeader = headers.get(RESET_AFTER_HEADER);
                String resetHeader = headers.get(RESET_HEADER);
                bucket.limit = (int)Math.max(1L, this.parseLong(limitHeader));
                bucket.remaining = (int)this.parseLong(remainingHeader);
                if (this.requester.getJDA().isRelativeRateLimit()) {
                    bucket.reset = now + this.parseDouble(resetAfterHeader);
                } else {
                    bucket.reset = this.parseDouble(resetHeader);
                }
                log.trace("Updated bucket {} to ({}/{}, {})", bucket.bucketId, bucket.remaining, bucket.limit, bucket.reset - now);
                return bucket;
            }
            catch (Exception e2) {
                Bucket bucket = this.getBucket(route, true);
                log.error("Encountered Exception while updating a bucket. Route: {} Bucket: {} Code: {} Headers:\n{}", route.getBaseRoute(), bucket, response.code(), response.headers(), e2);
                return bucket;
            }
        });
    }

    @Contract(value="_,true->!null")
    private Bucket getBucket(Route.CompiledRoute route, boolean create) {
        return MiscUtil.locked(this.bucketLock, () -> {
            String hash = this.getRouteHash(route.getBaseRoute());
            String bucketId = hash + ":" + route.getMajorParameters();
            Bucket bucket = this.buckets.get(bucketId);
            if (bucket == null && create) {
                bucket = new Bucket(bucketId);
                this.buckets.put(bucketId, bucket);
            }
            return bucket;
        });
    }

    private void runBucket(Bucket bucket) {
        if (this.isShutdown) {
            return;
        }
        MiscUtil.locked(this.bucketLock, () -> this.rateLimitQueue.computeIfAbsent(bucket, k2 -> this.getScheduler().schedule(bucket, bucket.getRateLimit(), TimeUnit.MILLISECONDS)));
    }

    private long parseLong(String input) {
        return input == null ? 0L : Long.parseLong(input);
    }

    private long parseDouble(String input) {
        return input == null ? 0L : (long)(Double.parseDouble(input) * 1000.0);
    }

    public long getNow() {
        return System.currentTimeMillis();
    }

    private class Bucket
    implements IBucket,
    Runnable {
        private final String bucketId;
        private final Deque<Request> requests = new ConcurrentLinkedDeque<Request>();
        private long reset = 0L;
        private int remaining = 1;
        private int limit = 1;

        public Bucket(String bucketId) {
            this.bucketId = bucketId;
        }

        public void enqueue(Request request) {
            this.requests.addLast(request);
        }

        public void retry(Request request) {
            this.requests.addFirst(request);
        }

        private boolean isGlobalRateLimit() {
            return BotRateLimiter.this.requester.getJDA().getSessionController().getGlobalRatelimit() > BotRateLimiter.this.getNow();
        }

        public long getRateLimit() {
            long now = BotRateLimiter.this.getNow();
            long global = BotRateLimiter.this.requester.getJDA().getSessionController().getGlobalRatelimit();
            if (global > now) {
                return global - now;
            }
            if (this.reset <= now) {
                this.remaining = this.limit;
                return 0L;
            }
            return this.remaining < 1 ? this.reset - now : 0L;
        }

        public long getReset() {
            return this.reset;
        }

        public int getRemaining() {
            return this.remaining;
        }

        public int getLimit() {
            return this.limit;
        }

        private boolean isUnlimited() {
            return this.bucketId.startsWith(BotRateLimiter.UNLIMITED_BUCKET);
        }

        private void backoff() {
            MiscUtil.locked(BotRateLimiter.this.bucketLock, () -> {
                BotRateLimiter.this.rateLimitQueue.remove(this);
                if (!this.requests.isEmpty()) {
                    BotRateLimiter.this.runBucket(this);
                } else if (BotRateLimiter.this.isStopped) {
                    BotRateLimiter.this.buckets.remove(this.bucketId);
                }
                if (BotRateLimiter.this.isStopped && BotRateLimiter.this.buckets.isEmpty()) {
                    BotRateLimiter.this.requester.getJDA().shutdownRequester();
                }
            });
        }

        @Override
        public void run() {
            log.trace("Bucket {} is running {} requests", (Object)this.bucketId, (Object)this.requests.size());
            while (!this.requests.isEmpty()) {
                boolean shouldSkip;
                Request request;
                Long rateLimit = this.getRateLimit();
                if (rateLimit > 0L) {
                    String baseRoute;
                    request = this.requests.peekFirst();
                    String string = baseRoute = request != null ? request.getRoute().getBaseRoute().toString() : "N/A";
                    if (!this.isGlobalRateLimit() && rateLimit >= 1800000L) {
                        log.warn("Encountered long {} minutes Rate-Limit on route {}", (Object)TimeUnit.MILLISECONDS.toMinutes(rateLimit), (Object)baseRoute);
                    }
                    log.debug("Backing off {} ms for bucket {} on route {}", rateLimit, this.bucketId, baseRoute);
                    break;
                }
                request = this.requests.removeFirst();
                if (request.isSkipped() || this.isUnlimited() && (shouldSkip = MiscUtil.locked(BotRateLimiter.this.bucketLock, () -> {
                    Bucket bucket = BotRateLimiter.this.getBucket(request.getRoute(), true);
                    if (bucket != this) {
                        bucket.enqueue(request);
                        BotRateLimiter.this.runBucket(bucket);
                        return true;
                    }
                    return false;
                }).booleanValue())) continue;
                try {
                    rateLimit = BotRateLimiter.this.requester.execute(request);
                    if (rateLimit == null) continue;
                    this.retry(request);
                }
                catch (Throwable ex2) {
                    log.error("Encountered exception trying to execute request", ex2);
                    if (!(ex2 instanceof Error)) break;
                    throw (Error)ex2;
                }
            }
            this.backoff();
        }

        @Override
        public Queue<Request> getRequests() {
            return this.requests;
        }

        public String toString() {
            return this.bucketId;
        }
    }
}

