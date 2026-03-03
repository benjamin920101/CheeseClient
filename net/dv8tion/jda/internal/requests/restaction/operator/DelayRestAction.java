/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.operator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.RestActionOperator;

public class DelayRestAction<T>
extends RestActionOperator<T, T> {
    private final TimeUnit unit;
    private final long delay;
    private final ScheduledExecutorService scheduler;

    public DelayRestAction(RestAction<T> action, TimeUnit unit, long delay, ScheduledExecutorService scheduler) {
        super(action);
        this.unit = unit;
        this.delay = delay;
        this.scheduler = scheduler == null ? action.getJDA().getRateLimitPool() : scheduler;
    }

    @Override
    public void queue(@Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure) {
        this.action.queue(result -> this.scheduler.schedule(() -> DelayRestAction.doSuccess(success, result), this.delay, this.unit), this.contextWrap(failure));
    }

    @Override
    public T complete(boolean shouldQueue) throws RateLimitedException {
        Object result = this.action.complete(shouldQueue);
        try {
            this.unit.sleep(this.delay);
            return result;
        }
        catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override
    @Nonnull
    public CompletableFuture<T> submit(boolean shouldQueue) {
        CompletableFuture future = new CompletableFuture();
        this.queue(future::complete, future::completeExceptionally);
        return future;
    }
}

