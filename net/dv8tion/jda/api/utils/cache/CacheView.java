/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.utils.ClosableIterator;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.ShardCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.UnifiedMemberCacheView;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.cache.AbstractCacheView;
import net.dv8tion.jda.internal.utils.cache.ShardCacheViewImpl;
import net.dv8tion.jda.internal.utils.cache.UnifiedCacheViewImpl;

public interface CacheView<T>
extends Iterable<T> {
    @Nonnull
    public List<T> asList();

    @Nonnull
    public Set<T> asSet();

    @Nonnull
    public ClosableIterator<T> lockedIterator();

    default public void forEachUnordered(@Nonnull Consumer<? super T> action) {
        this.forEach(action);
    }

    @Nullable
    default public <R> R applyStream(@Nonnull Function<? super Stream<T>, ? extends R> action) {
        Checks.notNull(action, "Action");
        try (ClosableIterator<T> it2 = this.lockedIterator();){
            Spliterator<T> spliterator = Spliterators.spliterator(it2, this.size(), 1280);
            Stream<T> stream = StreamSupport.stream(spliterator, false);
            R r2 = action.apply(stream);
            return r2;
        }
    }

    default public void acceptStream(@Nonnull Consumer<? super Stream<T>> action) {
        Checks.notNull(action, "Action");
        try (ClosableIterator<T> it2 = this.lockedIterator();){
            Spliterator<T> spliterator = Spliterators.spliterator(it2, this.size(), 1280);
            Stream<T> stream = StreamSupport.stream(spliterator, false);
            action.accept(stream);
        }
    }

    public long size();

    public boolean isEmpty();

    @Nonnull
    public List<T> getElementsByName(@Nonnull String var1, boolean var2);

    @Nonnull
    default public List<T> getElementsByName(@Nonnull String name) {
        return this.getElementsByName(name, false);
    }

    @Nonnull
    public Stream<T> stream();

    @Nonnull
    public Stream<T> parallelStream();

    @Nonnull
    default public <R, A> R collect(@Nonnull Collector<? super T, A, R> collector) {
        return this.stream().collect(collector);
    }

    @Nonnull
    public static <E> CacheView<E> all(@Nonnull Collection<? extends CacheView<E>> cacheViews) {
        Checks.noneNull(cacheViews, "Collection");
        return new UnifiedCacheViewImpl(cacheViews::stream);
    }

    @Nonnull
    public static <E> CacheView<E> all(@Nonnull Supplier<? extends Stream<? extends CacheView<E>>> generator) {
        Checks.notNull(generator, "Generator");
        return new UnifiedCacheViewImpl(generator);
    }

    @Nonnull
    public static ShardCacheView allShards(@Nonnull Collection<ShardCacheView> cacheViews) {
        Checks.noneNull(cacheViews, "Collection");
        return new ShardCacheViewImpl.UnifiedShardCacheViewImpl(cacheViews::stream);
    }

    @Nonnull
    public static ShardCacheView allShards(@Nonnull Supplier<? extends Stream<? extends ShardCacheView>> generator) {
        Checks.notNull(generator, "Generator");
        return new ShardCacheViewImpl.UnifiedShardCacheViewImpl(generator);
    }

    @Nonnull
    public static <E extends ISnowflake> SnowflakeCacheView<E> allSnowflakes(@Nonnull Collection<? extends SnowflakeCacheView<E>> cacheViews) {
        Checks.noneNull(cacheViews, "Collection");
        return new UnifiedCacheViewImpl.UnifiedSnowflakeCacheView(cacheViews::stream);
    }

    @Nonnull
    public static <E extends ISnowflake> SnowflakeCacheView<E> allSnowflakes(@Nonnull Supplier<? extends Stream<? extends SnowflakeCacheView<E>>> generator) {
        Checks.notNull(generator, "Generator");
        return new UnifiedCacheViewImpl.UnifiedSnowflakeCacheView(generator);
    }

    @Nonnull
    public static UnifiedMemberCacheView allMembers(@Nonnull Collection<? extends MemberCacheView> cacheViews) {
        Checks.noneNull(cacheViews, "Collection");
        return new UnifiedCacheViewImpl.UnifiedMemberCacheViewImpl((Supplier<? extends Stream<? extends MemberCacheView>>)((Supplier<Stream>)cacheViews::stream));
    }

    @Nonnull
    public static UnifiedMemberCacheView allMembers(@Nonnull Supplier<? extends Stream<? extends MemberCacheView>> generator) {
        Checks.notNull(generator, "Generator");
        return new UnifiedCacheViewImpl.UnifiedMemberCacheViewImpl(generator);
    }

    public static class SimpleCacheView<T>
    extends AbstractCacheView<T> {
        public SimpleCacheView(@Nonnull Class<T> type, @Nullable Function<T, String> nameMapper) {
            super(type, nameMapper);
        }
    }
}

