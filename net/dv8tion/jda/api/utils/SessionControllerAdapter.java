/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import com.neovisionaries.ws.client.OpeningHandshakeException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.AccountTypeException;
import net.dv8tion.jda.api.requests.Request;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.data.DataObject;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.Route;
import net.dv8tion.jda.internal.utils.JDALogger;
import net.dv8tion.jda.internal.utils.tuple.Pair;
import org.slf4j.Logger;

public class SessionControllerAdapter
implements SessionController {
    protected static final Logger log = JDALogger.getLog(SessionControllerAdapter.class);
    protected final Object lock = new Object();
    protected Queue<SessionController.SessionConnectNode> connectQueue = new ConcurrentLinkedQueue<SessionController.SessionConnectNode>();
    protected AtomicLong globalRatelimit = new AtomicLong(Long.MIN_VALUE);
    protected Thread workerHandle;
    protected long lastConnect = 0L;

    @Override
    public void appendSession(@Nonnull SessionController.SessionConnectNode node) {
        this.removeSession(node);
        this.connectQueue.add(node);
        this.runWorker();
    }

    @Override
    public void removeSession(@Nonnull SessionController.SessionConnectNode node) {
        this.connectQueue.remove(node);
    }

    @Override
    public long getGlobalRatelimit() {
        return this.globalRatelimit.get();
    }

    @Override
    public void setGlobalRatelimit(long ratelimit) {
        this.globalRatelimit.set(ratelimit);
    }

    @Override
    @Nonnull
    public String getGateway(@Nonnull JDA api2) {
        Route.CompiledRoute route = Route.Misc.GATEWAY.compile(new String[0]);
        return (String)new RestActionImpl<String>(api2, route, (response, request) -> response.getObject().getString("url")).priority().complete();
    }

    @Override
    @Nonnull
    public SessionController.ShardedGateway getShardedGateway(@Nonnull JDA api2) {
        AccountTypeException.check(api2.getAccountType(), AccountType.BOT);
        return (SessionController.ShardedGateway)new RestActionImpl<SessionController.ShardedGateway>(api2, Route.Misc.GATEWAY_BOT.compile(new String[0])){

            @Override
            public void handleResponse(Response response, Request<SessionController.ShardedGateway> request) {
                if (response.isOk()) {
                    DataObject object = response.getObject();
                    String url = object.getString("url");
                    int shards = object.getInt("shards");
                    int concurrency = object.getObject("session_start_limit").getInt("max_concurrency", 1);
                    request.onSuccess(new SessionController.ShardedGateway(url, shards, concurrency));
                } else if (response.code == 401) {
                    this.api.shutdownNow();
                    request.onFailure(new LoginException("The provided token is invalid!"));
                } else {
                    request.onFailure(response);
                }
            }
        }.priority().complete();
    }

    @Override
    @Nonnull
    public Pair<String, Integer> getGatewayBot(@Nonnull JDA api2) {
        SessionController.ShardedGateway bot2 = this.getShardedGateway(api2);
        return Pair.of(bot2.getUrl(), bot2.getShardTotal());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void runWorker() {
        Object object = this.lock;
        synchronized (object) {
            if (this.workerHandle == null) {
                this.workerHandle = new QueueWorker();
                this.workerHandle.start();
            }
        }
    }

    protected class QueueWorker
    extends Thread {
        protected final long delay;

        public QueueWorker() {
            this(5);
        }

        public QueueWorker(int delay) {
            this(TimeUnit.SECONDS.toMillis(delay));
        }

        public QueueWorker(long delay) {
            super("SessionControllerAdapter-Worker");
            this.delay = delay;
            super.setUncaughtExceptionHandler(this::handleFailure);
        }

        protected void handleFailure(Thread thread, Throwable exception) {
            log.error("Worker has failed with throwable!", exception);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            try {
                long interval;
                if (this.delay > 0L && (interval = System.currentTimeMillis() - SessionControllerAdapter.this.lastConnect) < this.delay) {
                    Thread.sleep(this.delay - interval);
                }
            }
            catch (InterruptedException ex2) {
                log.error("Unable to backoff", ex2);
            }
            this.processQueue();
            Object object = SessionControllerAdapter.this.lock;
            synchronized (object) {
                SessionControllerAdapter.this.workerHandle = null;
                if (!SessionControllerAdapter.this.connectQueue.isEmpty()) {
                    SessionControllerAdapter.this.runWorker();
                }
            }
        }

        protected void processQueue() {
            boolean isMultiple;
            boolean bl2 = isMultiple = SessionControllerAdapter.this.connectQueue.size() > 1;
            while (!SessionControllerAdapter.this.connectQueue.isEmpty()) {
                SessionController.SessionConnectNode node = SessionControllerAdapter.this.connectQueue.poll();
                try {
                    node.run(isMultiple && SessionControllerAdapter.this.connectQueue.isEmpty());
                    isMultiple = true;
                    SessionControllerAdapter.this.lastConnect = System.currentTimeMillis();
                    if (SessionControllerAdapter.this.connectQueue.isEmpty()) break;
                    if (this.delay <= 0L) continue;
                    Thread.sleep(this.delay);
                }
                catch (IllegalStateException e2) {
                    Throwable t2 = e2.getCause();
                    if (t2 instanceof OpeningHandshakeException) {
                        log.error("Failed opening handshake, appending to queue. Message: {}", (Object)e2.getMessage());
                    } else if (t2 != null && !JDA.Status.RECONNECT_QUEUED.name().equals(t2.getMessage())) {
                        log.error("Failed to establish connection for a node, appending to queue", e2);
                    } else {
                        log.error("Unexpected exception when running connect node", e2);
                    }
                    SessionControllerAdapter.this.appendSession(node);
                }
                catch (InterruptedException e3) {
                    log.error("Failed to run node", e3);
                    SessionControllerAdapter.this.appendSession(node);
                    return;
                }
            }
        }
    }
}

