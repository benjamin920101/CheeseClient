/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.utils.Result;
import net.dv8tion.jda.api.utils.concurrent.DelayedCompletableFuture;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.requests.restaction.operator.CombineRestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.DelayRestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.FlatMapErrorRestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.FlatMapRestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.MapErrorRestAction;
import net.dv8tion.jda.internal.requests.restaction.operator.MapRestAction;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.ContextRunnable;

public interface RestAction<T> {
    public static void setPassContext(boolean enable) {
        RestActionImpl.setPassContext(enable);
    }

    public static boolean isPassContext() {
        return RestActionImpl.isPassContext();
    }

    public static void setDefaultFailure(@Nullable Consumer<? super Throwable> callback) {
        RestActionImpl.setDefaultFailure(callback);
    }

    public static void setDefaultSuccess(@Nullable Consumer<Object> callback) {
        RestActionImpl.setDefaultSuccess(callback);
    }

    public static void setDefaultTimeout(long timeout, @Nonnull TimeUnit unit) {
        RestActionImpl.setDefaultTimeout(timeout, unit);
    }

    public static long getDefaultTimeout() {
        return RestActionImpl.getDefaultTimeout();
    }

    @Nonnull
    public static Consumer<? super Throwable> getDefaultFailure() {
        return RestActionImpl.getDefaultFailure();
    }

    @Nonnull
    public static Consumer<Object> getDefaultSuccess() {
        return RestActionImpl.getDefaultSuccess();
    }

    @Nonnull
    @SafeVarargs
    @CheckReturnValue
    public static <E> RestAction<List<E>> allOf(@Nonnull RestAction<? extends E> first, RestAction<? extends E> ... others) {
        Checks.notNull(first, "RestAction");
        Checks.noneNull(others, "RestAction");
        ArrayList<RestAction<? extends E>> list = new ArrayList<RestAction<? extends E>>(others.length + 1);
        list.add(first);
        Collections.addAll(list, others);
        return RestAction.allOf(list);
    }

    @Nonnull
    @CheckReturnValue
    public static <E> RestAction<List<E>> allOf(@Nonnull Collection<? extends RestAction<? extends E>> actions) {
        return RestAction.accumulate(actions, Collectors.toList());
    }

    @Nonnull
    @CheckReturnValue
    public static <E, A, O> RestAction<O> accumulate(@Nonnull Collection<? extends RestAction<? extends E>> actions, @Nonnull Collector<? super E, A, ? extends O> collector) {
        Checks.noneNull(actions, "RestAction");
        Checks.notEmpty(actions, "RestActions");
        Checks.notNull(collector, "Collector");
        Supplier accumulator = collector.supplier();
        BiConsumer add2 = collector.accumulator();
        Function<A, ? extends O> output = collector.finisher();
        actions = new LinkedHashSet<RestAction<? extends RestAction<? extends E>>>(actions);
        Iterator<RestAction<E>> iterator = actions.iterator();
        RestAction<Object> result = iterator.next().map(it2 -> {
            Object list = accumulator.get();
            add2.accept((Object)list, (Object)it2);
            return list;
        });
        while (iterator.hasNext()) {
            RestAction<? extends E> next = iterator.next();
            result = result.and(next, (list, b2) -> {
                add2.accept((Object)list, (Object)b2);
                return list;
            });
        }
        return result.map(output);
    }

    @Nonnull
    public JDA getJDA();

    @Nonnull
    public RestAction<T> setCheck(@Nullable BooleanSupplier var1);

    @Nullable
    default public BooleanSupplier getCheck() {
        return null;
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> addCheck(@Nonnull BooleanSupplier checks) {
        Checks.notNull(checks, "Checks");
        BooleanSupplier check = this.getCheck();
        return this.setCheck(() -> (check == null || check.getAsBoolean()) && checks.getAsBoolean());
    }

    @Nonnull
    default public RestAction<T> timeout(long timeout, @Nonnull TimeUnit unit) {
        Checks.notNull((Object)unit, "TimeUnit");
        return this.deadline(timeout <= 0L ? 0L : System.currentTimeMillis() + unit.toMillis(timeout));
    }

    @Nonnull
    default public RestAction<T> deadline(long timestamp) {
        throw new UnsupportedOperationException();
    }

    default public void queue() {
        this.queue(null);
    }

    default public void queue(@Nullable Consumer<? super T> success) {
        this.queue(success, null);
    }

    public void queue(@Nullable Consumer<? super T> var1, @Nullable Consumer<? super Throwable> var2);

    default public T complete() {
        try {
            return this.complete(true);
        }
        catch (RateLimitedException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    public T complete(boolean var1) throws RateLimitedException;

    @Nonnull
    default public CompletableFuture<T> submit() {
        return this.submit(true);
    }

    @Nonnull
    public CompletableFuture<T> submit(boolean var1);

    @Nonnull
    @CheckReturnValue
    default public RestAction<Result<T>> mapToResult() {
        return this.map(Result::success).onErrorMap(Result::failure);
    }

    @Nonnull
    @CheckReturnValue
    default public <O> RestAction<O> map(@Nonnull Function<? super T, ? extends O> map) {
        Checks.notNull(map, "Function");
        return new MapRestAction<T, O>(this, map);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> onErrorMap(@Nonnull Function<? super Throwable, ? extends T> map) {
        return this.onErrorMap(null, map);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> onErrorMap(@Nullable Predicate<? super Throwable> condition, @Nonnull Function<? super Throwable, ? extends T> map) {
        Checks.notNull(map, "Function");
        return new MapErrorRestAction<T>(this, condition == null ? x2 -> true : condition, map);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> onErrorFlatMap(@Nonnull Function<? super Throwable, ? extends RestAction<? extends T>> map) {
        return this.onErrorFlatMap(null, map);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> onErrorFlatMap(@Nullable Predicate<? super Throwable> condition, @Nonnull Function<? super Throwable, ? extends RestAction<? extends T>> map) {
        Checks.notNull(map, "Function");
        return new FlatMapErrorRestAction(this, condition == null ? x2 -> true : condition, map);
    }

    @Nonnull
    @CheckReturnValue
    default public <O> RestAction<O> flatMap(@Nonnull Function<? super T, ? extends RestAction<O>> flatMap) {
        return this.flatMap(null, flatMap);
    }

    @Nonnull
    @CheckReturnValue
    default public <O> RestAction<O> flatMap(@Nullable Predicate<? super T> condition, @Nonnull Function<? super T, ? extends RestAction<O>> flatMap) {
        Checks.notNull(flatMap, "Function");
        return new FlatMapRestAction(this, condition, flatMap);
    }

    @Nonnull
    @CheckReturnValue
    default public <U, O> RestAction<O> and(@Nonnull RestAction<U> other, @Nonnull BiFunction<? super T, ? super U, ? extends O> accumulator) {
        Checks.notNull(other, "RestAction");
        Checks.notNull(accumulator, "Accumulator");
        return new CombineRestAction<T, U, O>(this, other, accumulator);
    }

    @Nonnull
    @CheckReturnValue
    default public <U> RestAction<Void> and(@Nonnull RestAction<U> other) {
        return this.and(other, (a2, b2) -> null);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<List<T>> zip(@Nonnull RestAction<? extends T> first, RestAction<? extends T> ... other) {
        Checks.notNull(first, "RestAction");
        Checks.noneNull(other, "RestAction");
        ArrayList<RestAction<? extends T>> list = new ArrayList<RestAction<? extends T>>();
        list.add(this);
        list.add(first);
        Collections.addAll(list, other);
        return RestAction.allOf(list);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> delay(@Nonnull Duration duration) {
        return this.delay(duration, null);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> delay(@Nonnull Duration duration, @Nullable ScheduledExecutorService scheduler) {
        Checks.notNull(duration, "Duration");
        return new DelayRestAction(this, TimeUnit.MILLISECONDS, duration.toMillis(), scheduler);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> delay(long delay, @Nonnull TimeUnit unit) {
        return this.delay(delay, unit, null);
    }

    @Nonnull
    @CheckReturnValue
    default public RestAction<T> delay(long delay, @Nonnull TimeUnit unit, @Nullable ScheduledExecutorService scheduler) {
        Checks.notNull((Object)unit, "TimeUnit");
        return new DelayRestAction(this, unit, delay, scheduler);
    }

    @Nonnull
    default public DelayedCompletableFuture<T> submitAfter(long delay, @Nonnull TimeUnit unit) {
        return this.submitAfter(delay, unit, null);
    }

    @Nonnull
    default public DelayedCompletableFuture<T> submitAfter(long delay, @Nonnull TimeUnit unit, @Nullable ScheduledExecutorService executor) {
        Checks.notNull((Object)unit, "TimeUnit");
        if (executor == null) {
            executor = this.getJDA().getRateLimitPool();
        }
        return DelayedCompletableFuture.make(executor, delay, unit, task -> {
            Consumer<Throwable> onFailure = RestAction.isPassContext() ? ContextException.here(task::completeExceptionally) : task::completeExceptionally;
            return new ContextRunnable(() -> this.queue(task::complete, onFailure));
        });
    }

    default public T completeAfter(long delay, @Nonnull TimeUnit unit) {
        Checks.notNull((Object)unit, "TimeUnit");
        try {
            unit.sleep(delay);
            return this.complete();
        }
        catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit) {
        return this.queueAfter(delay, unit, null, null, null);
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit, @Nullable Consumer<? super T> success) {
        return this.queueAfter(delay, unit, success, null, null);
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit, @Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure) {
        return this.queueAfter(delay, unit, success, failure, null);
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit, @Nullable ScheduledExecutorService executor) {
        return this.queueAfter(delay, unit, null, null, executor);
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit, @Nullable Consumer<? super T> success, @Nullable ScheduledExecutorService executor) {
        return this.queueAfter(delay, unit, success, null, executor);
    }

    @Nonnull
    default public ScheduledFuture<?> queueAfter(long delay, @Nonnull TimeUnit unit, @Nullable Consumer<? super T> success, @Nullable Consumer<? super Throwable> failure, @Nullable ScheduledExecutorService executor) {
        Checks.notNull((Object)unit, "TimeUnit");
        if (executor == null) {
            executor = this.getJDA().getRateLimitPool();
        }
        Consumer<? super Throwable> onFailure = RestAction.isPassContext() ? ContextException.here(failure == null ? RestAction.getDefaultFailure() : failure) : failure;
        ContextRunnable task = new ContextRunnable(() -> this.queue(success, onFailure));
        return executor.schedule(task, delay, unit);
    }
}

