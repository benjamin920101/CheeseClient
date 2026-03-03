/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.internal.utils.concurrent.CountingThreadFactory;

public class ThreadingConfig {
    private final Object audioLock = new Object();
    private ScheduledExecutorService rateLimitPool;
    private ScheduledExecutorService gatewayPool;
    private ExecutorService callbackPool = ForkJoinPool.commonPool();
    private ExecutorService eventPool;
    private ScheduledExecutorService audioPool;
    private boolean shutdownRateLimitPool = true;
    private boolean shutdownGatewayPool = true;
    private boolean shutdownCallbackPool = false;
    private boolean shutdownEventPool;
    private boolean shutdownAudioPool = true;

    public void setRateLimitPool(@Nullable ScheduledExecutorService executor, boolean shutdown) {
        this.rateLimitPool = executor;
        this.shutdownRateLimitPool = shutdown;
    }

    public void setGatewayPool(@Nullable ScheduledExecutorService executor, boolean shutdown) {
        this.gatewayPool = executor;
        this.shutdownGatewayPool = shutdown;
    }

    public void setCallbackPool(@Nullable ExecutorService executor, boolean shutdown) {
        this.callbackPool = executor == null ? ForkJoinPool.commonPool() : executor;
        this.shutdownCallbackPool = shutdown;
    }

    public void setEventPool(@Nullable ExecutorService executor, boolean shutdown) {
        this.eventPool = executor;
        this.shutdownEventPool = shutdown;
    }

    public void setAudioPool(@Nullable ScheduledExecutorService executor, boolean shutdown) {
        this.audioPool = executor;
        this.shutdownAudioPool = shutdown;
    }

    public void init(@Nonnull Supplier<String> identifier) {
        if (this.rateLimitPool == null) {
            this.rateLimitPool = ThreadingConfig.newScheduler(5, identifier, "RateLimit", false);
        }
        if (this.gatewayPool == null) {
            this.gatewayPool = ThreadingConfig.newScheduler(1, identifier, "Gateway");
        }
    }

    public void shutdown() {
        if (this.shutdownCallbackPool) {
            this.callbackPool.shutdown();
        }
        if (this.shutdownGatewayPool) {
            this.gatewayPool.shutdown();
        }
        if (this.shutdownEventPool && this.eventPool != null) {
            this.eventPool.shutdown();
        }
        if (this.shutdownAudioPool && this.audioPool != null) {
            this.audioPool.shutdown();
        }
        if (this.shutdownRateLimitPool) {
            if (this.rateLimitPool instanceof ScheduledThreadPoolExecutor) {
                ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)this.rateLimitPool;
                executor.setKeepAliveTime(5L, TimeUnit.SECONDS);
                executor.allowCoreThreadTimeOut(true);
            } else {
                this.rateLimitPool.shutdown();
            }
        }
    }

    public void shutdownRequester() {
        if (this.shutdownRateLimitPool) {
            this.rateLimitPool.shutdown();
        }
    }

    public void shutdownNow() {
        if (this.shutdownCallbackPool) {
            this.callbackPool.shutdownNow();
        }
        if (this.shutdownGatewayPool) {
            this.gatewayPool.shutdownNow();
        }
        if (this.shutdownRateLimitPool) {
            this.rateLimitPool.shutdownNow();
        }
        if (this.shutdownEventPool && this.eventPool != null) {
            this.eventPool.shutdownNow();
        }
        if (this.shutdownAudioPool && this.audioPool != null) {
            this.audioPool.shutdownNow();
        }
    }

    @Nonnull
    public ScheduledExecutorService getRateLimitPool() {
        return this.rateLimitPool;
    }

    @Nonnull
    public ScheduledExecutorService getGatewayPool() {
        return this.gatewayPool;
    }

    @Nonnull
    public ExecutorService getCallbackPool() {
        return this.callbackPool;
    }

    @Nullable
    public ExecutorService getEventPool() {
        return this.eventPool;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    public ScheduledExecutorService getAudioPool(@Nonnull Supplier<String> identifier) {
        ScheduledExecutorService pool = this.audioPool;
        if (pool == null) {
            Object object = this.audioLock;
            synchronized (object) {
                pool = this.audioPool;
                if (pool == null) {
                    pool = this.audioPool = ThreadingConfig.newScheduler(1, identifier, "AudioLifeCycle");
                }
            }
        }
        return pool;
    }

    public boolean isShutdownRateLimitPool() {
        return this.shutdownRateLimitPool;
    }

    public boolean isShutdownGatewayPool() {
        return this.shutdownGatewayPool;
    }

    public boolean isShutdownCallbackPool() {
        return this.shutdownCallbackPool;
    }

    public boolean isShutdownEventPool() {
        return this.shutdownEventPool;
    }

    public boolean isShutdownAudioPool() {
        return this.shutdownAudioPool;
    }

    @Nonnull
    public static ScheduledThreadPoolExecutor newScheduler(int coreSize, Supplier<String> identifier, String baseName) {
        return ThreadingConfig.newScheduler(coreSize, identifier, baseName, true);
    }

    @Nonnull
    public static ScheduledThreadPoolExecutor newScheduler(int coreSize, Supplier<String> identifier, String baseName, boolean daemon) {
        return new ScheduledThreadPoolExecutor(coreSize, new CountingThreadFactory(identifier, baseName, daemon));
    }

    @Nonnull
    public static ThreadingConfig getDefault() {
        return new ThreadingConfig();
    }
}

