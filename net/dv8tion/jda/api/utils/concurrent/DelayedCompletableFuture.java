/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import javax.annotation.Nonnull;

public class DelayedCompletableFuture<T>
extends CompletableFuture<T>
implements ScheduledFuture<T> {
    private ScheduledFuture<?> future;

    private DelayedCompletableFuture() {
    }

    @Nonnull
    public static <E> DelayedCompletableFuture<E> make(@Nonnull ScheduledExecutorService executor, long delay, @Nonnull TimeUnit unit, @Nonnull Function<? super DelayedCompletableFuture<E>, ? extends Runnable> mapping) {
        DelayedCompletableFuture handle = new DelayedCompletableFuture();
        ScheduledFuture<?> future = executor.schedule(mapping.apply(handle), delay, unit);
        super.initProxy(future);
        return handle;
    }

    private void initProxy(ScheduledFuture<?> future) {
        if (this.future != null) {
            throw new IllegalStateException("Cannot initialize twice");
        }
        this.future = future;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        if (this.future != null && !this.future.isDone()) {
            this.future.cancel(mayInterruptIfRunning);
        }
        return super.cancel(mayInterruptIfRunning);
    }

    @Override
    public long getDelay(@Nonnull TimeUnit unit) {
        return this.future.getDelay(unit);
    }

    @Override
    public int compareTo(@Nonnull Delayed o2) {
        return this.future.compareTo(o2);
    }
}

