/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.requests.restaction.pagination;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.requests.RestAction;
import net.dv8tion.jda.api.utils.Procedure;
import net.dv8tion.jda.internal.requests.RestActionImpl;
import net.dv8tion.jda.internal.utils.Checks;

public interface PaginationAction<T, M extends PaginationAction<T, M>>
extends RestAction<List<T>>,
Iterable<T> {
    @Nonnull
    public M skipTo(long var1);

    public long getLastKey();

    @Nonnull
    public M setCheck(@Nullable BooleanSupplier var1);

    @Nonnull
    public M timeout(long var1, @Nonnull TimeUnit var3);

    @Nonnull
    public M deadline(long var1);

    public int cacheSize();

    public boolean isEmpty();

    @Nonnull
    public List<T> getCached();

    @Nonnull
    public T getLast();

    @Nonnull
    public T getFirst();

    @Nonnull
    public M limit(int var1);

    @Nonnull
    public M cache(boolean var1);

    public boolean isCacheEnabled();

    public int getMaxLimit();

    public int getMinLimit();

    public int getLimit();

    @Nonnull
    default public CompletableFuture<List<T>> takeWhileAsync(@Nonnull Predicate<? super T> rule) {
        Checks.notNull(rule, "Rule");
        return this.takeUntilAsync(rule.negate());
    }

    @Nonnull
    default public CompletableFuture<List<T>> takeWhileAsync(int limit, @Nonnull Predicate<? super T> rule) {
        Checks.notNull(rule, "Rule");
        return this.takeUntilAsync(limit, rule.negate());
    }

    @Nonnull
    default public CompletableFuture<List<T>> takeUntilAsync(@Nonnull Predicate<? super T> rule) {
        return this.takeUntilAsync(0, rule);
    }

    @Nonnull
    default public CompletableFuture<List<T>> takeUntilAsync(int limit, @Nonnull Predicate<? super T> rule) {
        Checks.notNull(rule, "Rule");
        Checks.notNegative(limit, "Limit");
        ArrayList result = new ArrayList();
        CompletableFuture future = new CompletableFuture();
        CompletableFuture<?> handle = this.forEachAsync(element -> {
            if (rule.test(element)) {
                return false;
            }
            result.add(element);
            return limit == 0 || limit > result.size();
        });
        handle.whenComplete((r2, t2) -> {
            if (t2 != null) {
                future.completeExceptionally((Throwable)t2);
            } else {
                future.complete(result);
            }
        });
        return future;
    }

    @Nonnull
    public CompletableFuture<List<T>> takeAsync(int var1);

    @Nonnull
    public CompletableFuture<List<T>> takeRemainingAsync(int var1);

    @Nonnull
    default public CompletableFuture<?> forEachAsync(@Nonnull Procedure<? super T> action) {
        return this.forEachAsync(action, RestActionImpl.getDefaultFailure());
    }

    @Nonnull
    public CompletableFuture<?> forEachAsync(@Nonnull Procedure<? super T> var1, @Nonnull Consumer<? super Throwable> var2);

    @Nonnull
    default public CompletableFuture<?> forEachRemainingAsync(@Nonnull Procedure<? super T> action) {
        return this.forEachRemainingAsync(action, RestActionImpl.getDefaultFailure());
    }

    @Nonnull
    public CompletableFuture<?> forEachRemainingAsync(@Nonnull Procedure<? super T> var1, @Nonnull Consumer<? super Throwable> var2);

    public void forEachRemaining(@Nonnull Procedure<? super T> var1);

    @Override
    default public Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(this.iterator(), 1024);
    }

    @Nonnull
    default public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Nonnull
    default public Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    @Override
    @Nonnull
    public PaginationIterator<T> iterator();

    public static class PaginationIterator<E>
    implements Iterator<E> {
        protected Queue<E> items;
        protected final Supplier<List<E>> supply;

        public PaginationIterator(Collection<E> queue, Supplier<List<E>> supply) {
            this.items = new LinkedList<E>(queue);
            this.supply = supply;
        }

        @Override
        public boolean hasNext() {
            if (this.items == null) {
                return false;
            }
            if (!this.hitEnd()) {
                return true;
            }
            if (this.items.addAll(this.supply.get())) {
                return true;
            }
            this.items = null;
            return false;
        }

        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Reached End of pagination task!");
            }
            return this.items.poll();
        }

        protected boolean hitEnd() {
            return this.items.isEmpty();
        }
    }
}

