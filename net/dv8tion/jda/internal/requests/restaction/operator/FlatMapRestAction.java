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

public class FlatMapRestAction<I, O>
extends RestActionOperator<I, O> {
    private final Function<? super I, ? extends RestAction<O>> function;
    private final Predicate<? super I> condition;

    public FlatMapRestAction(RestAction<I> action, Predicate<? super I> condition, Function<? super I, ? extends RestAction<O>> function) {
        super(action);
        this.function = function;
        this.condition = condition;
    }

    private RestAction<O> supply(I input) {
        return this.applyContext(this.function.apply(input));
    }

    @Override
    public void queue(@Nullable Consumer<? super O> success, @Nullable Consumer<? super Throwable> failure) {
        Consumer<Throwable> contextFailure = this.contextWrap(failure);
        this.action.queue((? super T result) -> {
            if (this.condition != null && !this.condition.test(result)) {
                return;
            }
            RestAction<O> then = this.supply(result);
            if (then == null) {
                FlatMapRestAction.doFailure(contextFailure, new IllegalStateException("FlatMap operand is null"));
            } else {
                then.queue(success, contextFailure);
            }
        }, contextFailure);
    }

    @Override
    public O complete(boolean shouldQueue) throws RateLimitedException {
        return this.supply(this.action.complete(shouldQueue)).complete(shouldQueue);
    }

    @Override
    @Nonnull
    public CompletableFuture<O> submit(boolean shouldQueue) {
        return this.action.submit(shouldQueue).thenCompose(result -> this.supply(result).submit(shouldQueue));
    }
}

