/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.sharding;

import gnu.trove.set.TIntSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Queue;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.sharding.ThreadPoolProvider;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.cache.ShardCacheView;
import net.dv8tion.jda.internal.JDAImpl;
import net.dv8tion.jda.internal.entities.SelfUserImpl;
import net.dv8tion.jda.internal.managers.PresenceImpl;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.ShardCacheViewImpl;
import net.dv8tion.jda.internal.utils.config.AuthorizationConfig;
import net.dv8tion.jda.internal.utils.config.MetaConfig;
import net.dv8tion.jda.internal.utils.config.SessionConfig;
import net.dv8tion.jda.internal.utils.config.ThreadingConfig;
import net.dv8tion.jda.internal.utils.config.sharding.EventConfig;
import net.dv8tion.jda.internal.utils.config.sharding.PresenceProviderConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingMetaConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ShardingSessionConfig;
import net.dv8tion.jda.internal.utils.config.sharding.ThreadingProviderConfig;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;

public class DefaultShardManager
implements ShardManager {
    public static final Logger LOG = JDALogger.getLog(ShardManager.class);
    public static final ThreadFactory DEFAULT_THREAD_FACTORY = r2 -> {
        Thread t2 = new Thread(r2, "DefaultShardManager");
        t2.setPriority(6);
        return t2;
    };
    protected final ScheduledExecutorService executor;
    protected final Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    protected ShardCacheViewImpl shards;
    protected final AtomicBoolean shutdown = new AtomicBoolean(false);
    protected final Thread shutdownHook;
    protected final String token;
    protected Future<?> worker;
    protected String gatewayURL;
    protected final PresenceProviderConfig presenceConfig;
    protected final EventConfig eventConfig;
    protected final ShardingConfig shardingConfig;
    protected final ThreadingProviderConfig threadingConfig;
    protected final ShardingSessionConfig sessionConfig;
    protected final ShardingMetaConfig metaConfig;
    protected final ChunkingFilter chunkingFilter;

    public DefaultShardManager(@Nonnull String token) {
        this(token, null);
    }

    public DefaultShardManager(@Nonnull String token, @Nullable Collection<Integer> shardIds) {
        this(token, shardIds, null, null, null, null, null, null, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public DefaultShardManager(@Nonnull String token, @Nullable Collection<Integer> shardIds, @Nullable ShardingConfig shardingConfig, @Nullable EventConfig eventConfig, @Nullable PresenceProviderConfig presenceConfig, @Nullable ThreadingProviderConfig threadingConfig, @Nullable ShardingSessionConfig sessionConfig, @Nullable ShardingMetaConfig metaConfig, @Nullable ChunkingFilter chunkingFilter) {
        this.token = token;
        this.eventConfig = eventConfig == null ? EventConfig.getDefault() : eventConfig;
        this.shardingConfig = shardingConfig == null ? ShardingConfig.getDefault() : shardingConfig;
        this.threadingConfig = threadingConfig == null ? ThreadingProviderConfig.getDefault() : threadingConfig;
        this.sessionConfig = sessionConfig == null ? ShardingSessionConfig.getDefault() : sessionConfig;
        this.presenceConfig = presenceConfig == null ? PresenceProviderConfig.getDefault() : presenceConfig;
        this.metaConfig = metaConfig == null ? ShardingMetaConfig.getDefault() : metaConfig;
        this.chunkingFilter = chunkingFilter == null ? ChunkingFilter.ALL : chunkingFilter;
        this.executor = this.createExecutor(this.threadingConfig.getThreadFactory());
        this.shutdownHook = this.metaConfig.isUseShutdownHook() ? new Thread(this::shutdown, "JDA Shutdown Hook") : null;
        Queue<Integer> queue = this.queue;
        synchronized (queue) {
            if (this.getShardsTotal() != -1) {
                if (shardIds == null) {
                    this.shards = new ShardCacheViewImpl(this.getShardsTotal());
                    for (int i2 = 0; i2 < this.getShardsTotal(); ++i2) {
                        this.queue.add(i2);
                    }
                } else {
                    this.shards = new ShardCacheViewImpl(shardIds.size());
                    shardIds.stream().distinct().sorted().forEach(this.queue::add);
                }
            }
        }
    }

    @Override
    @Nonnull
    public EnumSet<GatewayIntent> getGatewayIntents() {
        return GatewayIntent.getIntents(this.shardingConfig.getIntents());
    }

    @Override
    public void addEventListener(Object ... listeners) {
        ShardManager.super.addEventListener(listeners);
        for (Object o2 : listeners) {
            this.eventConfig.addEventListener(o2);
        }
    }

    @Override
    public void removeEventListener(Object ... listeners) {
        ShardManager.super.removeEventListener(listeners);
        for (Object o2 : listeners) {
            this.eventConfig.removeEventListener(o2);
        }
    }

    @Override
    public void addEventListeners(@Nonnull IntFunction<Object> eventListenerProvider) {
        ShardManager.super.addEventListeners(eventListenerProvider);
        this.eventConfig.addEventListenerProvider(eventListenerProvider);
    }

    @Override
    public void removeEventListenerProvider(@Nonnull IntFunction<Object> eventListenerProvider) {
        this.eventConfig.removeEventListenerProvider(eventListenerProvider);
    }

    @Override
    public int getShardsQueued() {
        return this.queue.size();
    }

    @Override
    public int getShardsTotal() {
        return this.shardingConfig.getShardsTotal();
    }

    @Override
    public Guild getGuildById(long id2) {
        int shardId = MiscUtil.getShardForGuild(id2, this.getShardsTotal());
        JDA shard = this.getShardById(shardId);
        return shard == null ? null : shard.getGuildById(id2);
    }

    @Override
    @Nonnull
    public ShardCacheView getShardCache() {
        return this.shards;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void login() throws LoginException {
        JDAImpl jda = null;
        try {
            int shardId = this.queue.isEmpty() ? 0 : this.queue.peek();
            jda = this.buildInstance(shardId);
            try (UnlockHook hook = this.shards.writeLock();){
                this.shards.getMap().put(shardId, jda);
            }
            Queue<Integer> queue = this.queue;
            synchronized (queue) {
                this.queue.remove(shardId);
            }
        }
        catch (Exception e2) {
            if (jda != null) {
                if (this.shardingConfig.isUseShutdownNow()) {
                    jda.shutdownNow();
                } else {
                    jda.shutdown();
                }
            }
            throw e2;
        }
        this.runQueueWorker();
        if (this.shutdownHook != null) {
            Runtime.getRuntime().addShutdownHook(this.shutdownHook);
        }
    }

    @Override
    public void restart(int shardId) {
        Checks.notNegative(shardId, "shardId");
        Checks.check(shardId < this.getShardsTotal(), "shardId must be lower than shardsTotal");
        JDA jda = this.shards.remove(shardId);
        if (jda != null) {
            if (this.shardingConfig.isUseShutdownNow()) {
                jda.shutdownNow();
            } else {
                jda.shutdown();
            }
        }
        this.enqueueShard(shardId);
    }

    @Override
    public void restart() {
        TIntSet map = this.shards.keySet();
        Arrays.stream(map.toArray()).sorted().forEach(this::restart);
    }

    @Override
    public void shutdown() {
        if (this.shutdown.getAndSet(true)) {
            return;
        }
        if (this.worker != null && !this.worker.isDone()) {
            this.worker.cancel(true);
        }
        if (this.shutdownHook != null) {
            try {
                Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (this.shards != null) {
            this.executor.execute(() -> {
                Queue<Integer> queue = this.queue;
                synchronized (queue) {
                    this.shards.forEach((Consumer<? super JDA>)((Consumer<JDA>)jda -> {
                        if (this.shardingConfig.isUseShutdownNow()) {
                            jda.shutdownNow();
                        } else {
                            jda.shutdown();
                        }
                    }));
                    this.queue.clear();
                }
                this.executor.shutdown();
            });
        } else {
            this.executor.shutdown();
        }
    }

    @Override
    public void shutdown(int shardId) {
        JDA jda = this.shards.remove(shardId);
        if (jda != null) {
            if (this.shardingConfig.isUseShutdownNow()) {
                jda.shutdownNow();
            } else {
                jda.shutdown();
            }
        }
    }

    @Override
    public void start(int shardId) {
        Checks.notNegative(shardId, "shardId");
        Checks.check(shardId < this.getShardsTotal(), "shardId must be lower than shardsTotal");
        this.enqueueShard(shardId);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void enqueueShard(int shardId) {
        Queue<Integer> queue = this.queue;
        synchronized (queue) {
            this.queue.add(shardId);
            this.runQueueWorker();
        }
    }

    protected void runQueueWorker() {
        if (this.shutdown.get()) {
            throw new RejectedExecutionException("ShardManager is already shutdown!");
        }
        if (this.worker != null) {
            return;
        }
        this.worker = this.executor.submit(() -> {
            while (!this.queue.isEmpty() && !Thread.currentThread().isInterrupted()) {
                this.processQueue();
            }
            this.gatewayURL = null;
            Queue<Integer> queue = this.queue;
            synchronized (queue) {
                this.worker = null;
                if (!this.shutdown.get() && !this.queue.isEmpty()) {
                    this.runQueueWorker();
                }
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void processQueue() {
        JDAImpl api2;
        int shardId;
        if (this.shards == null) {
            shardId = 0;
        } else {
            Integer tmp = this.queue.peek();
            int n2 = shardId = tmp == null ? -1 : tmp;
        }
        if (shardId == -1) {
            return;
        }
        try {
            JDAImpl jDAImpl = api2 = this.shards == null ? null : (JDAImpl)this.shards.getElementById(shardId);
            if (api2 == null) {
                api2 = this.buildInstance(shardId);
            }
        }
        catch (CompletionException e2) {
            if (e2.getCause() instanceof InterruptedException) {
                LOG.debug("The worker thread was interrupted");
            } else {
                LOG.error("Caught an exception in queue processing thread", e2);
            }
            return;
        }
        catch (LoginException e3) {
            LOG.warn("The token has been invalidated and the ShardManager will shutdown!", e3);
            this.shutdown();
            return;
        }
        catch (Exception e4) {
            LOG.error("Caught an exception in the queue processing thread", e4);
            return;
        }
        try (UnlockHook hook = this.shards.writeLock();){
            this.shards.getMap().put(shardId, api2);
        }
        Queue<Integer> queue = this.queue;
        synchronized (queue) {
            this.queue.remove(shardId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected JDAImpl buildInstance(int shardId) throws LoginException {
        JDAImpl jda;
        block19: {
            OkHttpClient httpClient = this.sessionConfig.getHttpClient();
            if (httpClient == null) {
                httpClient = this.sessionConfig.getHttpBuilder().build();
            }
            ExecutorPair<? extends ScheduledExecutorService> rateLimitPair = DefaultShardManager.resolveExecutor(this.threadingConfig.getRateLimitPoolProvider(), shardId);
            ScheduledExecutorService rateLimitPool = (ScheduledExecutorService)rateLimitPair.executor;
            boolean shutdownRateLimitPool = rateLimitPair.automaticShutdown;
            ExecutorPair<? extends ScheduledExecutorService> gatewayPair = DefaultShardManager.resolveExecutor(this.threadingConfig.getGatewayPoolProvider(), shardId);
            ScheduledExecutorService gatewayPool = (ScheduledExecutorService)gatewayPair.executor;
            boolean shutdownGatewayPool = gatewayPair.automaticShutdown;
            ExecutorPair<? extends ExecutorService> callbackPair = DefaultShardManager.resolveExecutor(this.threadingConfig.getCallbackPoolProvider(), shardId);
            Object callbackPool = callbackPair.executor;
            boolean shutdownCallbackPool = callbackPair.automaticShutdown;
            ExecutorPair<? extends ExecutorService> eventPair = DefaultShardManager.resolveExecutor(this.threadingConfig.getEventPoolProvider(), shardId);
            Object eventPool = eventPair.executor;
            boolean shutdownEventPool = eventPair.automaticShutdown;
            ExecutorPair<? extends ScheduledExecutorService> audioPair = DefaultShardManager.resolveExecutor(this.threadingConfig.getAudioPoolProvider(), shardId);
            ScheduledExecutorService audioPool = (ScheduledExecutorService)audioPair.executor;
            boolean shutdownAudioPool = audioPair.automaticShutdown;
            AuthorizationConfig authConfig = new AuthorizationConfig(this.token);
            SessionConfig sessionConfig = this.sessionConfig.toSessionConfig(httpClient);
            ThreadingConfig threadingConfig = new ThreadingConfig();
            threadingConfig.setRateLimitPool(rateLimitPool, shutdownRateLimitPool);
            threadingConfig.setGatewayPool(gatewayPool, shutdownGatewayPool);
            threadingConfig.setCallbackPool((ExecutorService)callbackPool, shutdownCallbackPool);
            threadingConfig.setEventPool((ExecutorService)eventPool, shutdownEventPool);
            threadingConfig.setAudioPool(audioPool, shutdownAudioPool);
            MetaConfig metaConfig = new MetaConfig(this.metaConfig.getMaxBufferSize(), this.metaConfig.getContextMap(shardId), this.metaConfig.getCacheFlags(), this.sessionConfig.getFlags());
            jda = new JDAImpl(authConfig, sessionConfig, threadingConfig, metaConfig);
            jda.setMemberCachePolicy(this.shardingConfig.getMemberCachePolicy());
            threadingConfig.init(jda::getIdentifierString);
            if ((this.shardingConfig.getIntents() & GatewayIntent.GUILD_MEMBERS.getRawValue()) == 0) {
                jda.setChunkingFilter(ChunkingFilter.NONE);
            } else {
                jda.setChunkingFilter(this.chunkingFilter);
            }
            jda.setShardManager(this);
            if (this.eventConfig.getEventManagerProvider() != null) {
                jda.setEventManager(this.eventConfig.getEventManagerProvider().apply(shardId));
            }
            if (this.sessionConfig.getAudioSendFactory() != null) {
                jda.setAudioSendFactory(this.sessionConfig.getAudioSendFactory());
            }
            this.eventConfig.getListeners().forEach(xva$0 -> jda.addEventListener(xva$0));
            this.eventConfig.getListenerProviders().forEach(provider -> jda.addEventListener(provider.apply(shardId)));
            PresenceImpl presence = (PresenceImpl)jda.getPresence();
            if (this.presenceConfig.getActivityProvider() != null) {
                presence.setCacheActivity(this.presenceConfig.getActivityProvider().apply(shardId));
            }
            if (this.presenceConfig.getIdleProvider() != null) {
                presence.setCacheIdle(this.presenceConfig.getIdleProvider().apply(shardId));
            }
            if (this.presenceConfig.getStatusProvider() != null) {
                presence.setCacheStatus(this.presenceConfig.getStatusProvider().apply(shardId));
            }
            if (this.gatewayURL == null) {
                try {
                    SessionController.ShardedGateway gateway = jda.getShardedGateway();
                    this.sessionConfig.getSessionController().setConcurrency(gateway.getConcurrency());
                    this.gatewayURL = gateway.getUrl();
                    if (this.gatewayURL == null) {
                        LOG.error("Acquired null gateway url from SessionController");
                    } else {
                        LOG.info("Login Successful!");
                    }
                    if (this.getShardsTotal() != -1) break block19;
                    this.shardingConfig.setShardsTotal(gateway.getShardTotal());
                    this.shards = new ShardCacheViewImpl(this.getShardsTotal());
                    Queue<Integer> queue = this.queue;
                    synchronized (queue) {
                        for (int i2 = 0; i2 < this.getShardsTotal(); ++i2) {
                            this.queue.add(i2);
                        }
                    }
                }
                catch (CompletionException e2) {
                    if (e2.getCause() instanceof LoginException) {
                        throw (LoginException)e2.getCause();
                    }
                    throw e2;
                }
            }
        }
        JDA.ShardInfo shardInfo = new JDA.ShardInfo(shardId, this.getShardsTotal());
        SelfUser selfUser = this.getShardCache().applyStream(s2 -> s2.map(JDA::getSelfUser).findFirst().orElse(null));
        selfUser = selfUser == null ? this.retrieveSelfUser(jda) : SelfUserImpl.copyOf((SelfUserImpl)selfUser, jda);
        jda.setSelfUser(selfUser);
        jda.setStatus(JDA.Status.INITIALIZED);
        int shardTotal = jda.login(this.gatewayURL, shardInfo, this.metaConfig.getCompression(), false, this.shardingConfig.getIntents(), this.metaConfig.getEncoding());
        if (this.getShardsTotal() == -1) {
            this.shardingConfig.setShardsTotal(shardTotal);
        }
        return jda;
    }

    private SelfUser retrieveSelfUser(JDAImpl jda) {
        Route.CompiledRoute route = Route.Self.GET_SELF.compile(new String[0]);
        return (SelfUser)new RestActionImpl<SelfUser>((JDA)jda, route, (response, request) -> jda.getEntityBuilder().createSelfUser(response.getObject())).complete();
    }

    @Override
    public void setActivityProvider(IntFunction<? extends Activity> activityProvider) {
        ShardManager.super.setActivityProvider(activityProvider);
        this.presenceConfig.setActivityProvider(activityProvider);
    }

    @Override
    public void setIdleProvider(@Nonnull IntFunction<Boolean> idleProvider) {
        ShardManager.super.setIdleProvider(idleProvider);
        this.presenceConfig.setIdleProvider(idleProvider);
    }

    @Override
    public void setPresenceProvider(IntFunction<OnlineStatus> statusProvider, IntFunction<? extends Activity> activityProvider) {
        ShardManager.super.setPresenceProvider(statusProvider, activityProvider);
        this.presenceConfig.setStatusProvider(statusProvider);
        this.presenceConfig.setActivityProvider(activityProvider);
    }

    @Override
    public void setStatusProvider(IntFunction<OnlineStatus> statusProvider) {
        ShardManager.super.setStatusProvider(statusProvider);
        this.presenceConfig.setStatusProvider(statusProvider);
    }

    protected ScheduledExecutorService createExecutor(ThreadFactory threadFactory) {
        ThreadFactory factory = threadFactory == null ? DEFAULT_THREAD_FACTORY : threadFactory;
        return Executors.newSingleThreadScheduledExecutor(factory);
    }

    protected static <E extends ExecutorService> ExecutorPair<E> resolveExecutor(ThreadPoolProvider<? extends E> provider, int shardId) {
        Object executor = null;
        boolean automaticShutdown = true;
        if (provider != null) {
            executor = provider.provide(shardId);
            automaticShutdown = provider.shouldShutdownAutomatically(shardId);
        }
        return new ExecutorPair<Object>(executor, automaticShutdown);
    }

    protected static class ExecutorPair<E extends ExecutorService> {
        protected final E executor;
        protected final boolean automaticShutdown;

        protected ExecutorPair(E executor, boolean automaticShutdown) {
            this.executor = executor;
            this.automaticShutdown = automaticShutdown;
        }
    }
}

