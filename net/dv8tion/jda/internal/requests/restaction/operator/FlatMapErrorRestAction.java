/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.requests.restaction.operator;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.RestActionOperator;
import net.dv8tion.jda.internal.utils.Helpers;
import org.jetbrains.annotations.Contract;

public class FlatMapErrorRestAction<T>
extends RestActionOperator<T, T> {
    private final Predicate<? super Throwable> check;
    private final Function<? super Throwable, ? extends RestAction<? extends T>> map;

    public FlatMapErrorRestAction(RestAction<T> action, Predicate<? super Throwable> check, Function<? super Throwable, ? extends RestAction<? extends T>> map) {
        super(action);
        this.check = check;
        this.map = map;
    }

    @Override
    public void queue(@Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure) {
        Consumer<Throwable> contextFailure = this.contextWrap(failure);
        this.action.queue(success, this.contextWrap(error -> {
            try {
                if (this.check.test((Throwable)error)) {
                    RestAction<T> then = this.map.apply((Throwable)error);
                    if (then == null) {
                        FlatMapErrorRestAction.doFailure(failure, new IllegalStateException("FlatMapError operand is null", (Throwable)error));
                    } else {
                        then.queue(success, contextFailure);
                    }
                } else {
                    FlatMapErrorRestAction.doFailure(failure, error);
                }
            }
            catch (Throwable e2) {
                FlatMapErrorRestAction.doFailure(failure, Helpers.appendCause(e2, error));
            }
        }));
    }

    @Override
    public T complete(boolean shouldQueue) throws RateLimitedException {
        try {
            return this.action.complete(shouldQueue);
        }
        catch (Throwable error) {
            try {
                if (this.check.test(error)) {
                    RestAction<T> then = this.map.apply(error);
                    if (then == null) {
                        throw new IllegalStateException("FlatMapError operand is null", error);
                    }
                    return then.complete(shouldQueue);
                }
            }
            catch (Throwable e2) {
                if (e2 instanceof IllegalStateException && e2.getCause() == error) {
                    throw (IllegalStateException)e2;
                }
                if (e2 instanceof RateLimitedException) {
                    throw (RateLimitedException)Helpers.appendCause(e2, error);
                }
                this.fail(Helpers.appendCause(e2, error));
            }
            this.fail(error);
            throw new AssertionError((Object)"Unreachable");
        }
    }

    @Override
    @Nonnull
    public CompletableFuture<T> submit(boolean shouldQueue) {
        return ((CompletableFuture)this.action.submit(shouldQueue).handle((result, error) -> {
            if (this.check.test((Throwable)error)) {
                return this.map.apply((Throwable)error).submit(shouldQueue).thenApply(x2 -> x2);
            }
            return CompletableFuture.completedFuture(result);
        })).thenCompose(Function.identity());
    }

    @Contract(value="_ -> fail")
    private void fail(Throwable error) {
        if (error instanceof RuntimeException) {
            throw (RuntimeException)error;
        }
        if (error instanceof Error) {
            throw (Error)error;
        }
        throw new RuntimeException(error);
    }
}

