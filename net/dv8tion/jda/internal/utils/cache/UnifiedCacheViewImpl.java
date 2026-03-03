/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.utils.ClosableIterator;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.MemberCacheView;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;
import net.dv8tion.jda.api.utils.cache.UnifiedMemberCacheView;
import net.dv8tion.jda.internal.utils.ChainedClosableIterator;

public class UnifiedCacheViewImpl<T, E extends CacheView<T>>
implements CacheView<T> {
    protected final Supplier<? extends Stream<? extends E>> generator;

    public UnifiedCacheViewImpl(Supplier<? extends Stream<? extends E>> generator) {
        this.generator = generator;
    }

    @Override
    public long size() {
        return this.distinctStream().mapToLong(CacheView::size).sum();
    }

    @Override
    public boolean isEmpty() {
        return this.distinctStream().allMatch(CacheView::isEmpty);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        try (ClosableIterator it2 = this.lockedIterator();){
            while (it2.hasNext()) {
                action.accept(it2.next());
            }
        }
    }

    @Override
    @Nonnull
    public List<T> asList() {
        LinkedList list = new LinkedList();
        this.forEach(list::add);
        return Collections.unmodifiableList(list);
    }

    @Override
    @Nonnull
    public Set<T> asSet() {
        try (ClosableIterator it2 = this.lockedIterator();){
            while (((ChainedClosableIterator)it2).hasNext()) {
                ((ChainedClosableIterator)it2).next();
            }
            Set set = Collections.unmodifiableSet(((ChainedClosableIterator)it2).getItems());
            return set;
        }
    }

    @Override
    @Nonnull
    public ChainedClosableIterator<T> lockedIterator() {
        Iterator gen = this.generator.get().iterator();
        return new ChainedClosableIterator(gen);
    }

    @Override
    @Nonnull
    public List<T> getElementsByName(@Nonnull String name, boolean ignoreCase) {
        return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsByName(name, ignoreCase).stream()).distinct().collect(Collectors.toList()));
    }

    @Override
    @Nonnull
    public Stream<T> stream() {
        return this.distinctStream().flatMap(CacheView::stream).distinct();
    }

    @Override
    @Nonnull
    public Stream<T> parallelStream() {
        return this.distinctStream().flatMap(CacheView::parallelStream).distinct();
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        return this.stream().iterator();
    }

    protected Stream<? extends E> distinctStream() {
        return this.generator.get().distinct();
    }

    public static class UnifiedMemberCacheViewImpl
    extends UnifiedCacheViewImpl<Member, MemberCacheView>
    implements UnifiedMemberCacheView {
        public UnifiedMemberCacheViewImpl(Supplier<? extends Stream<? extends MemberCacheView>> generator) {
            super(generator);
        }

        @Override
        @Nonnull
        public List<Member> getElementsById(long id2) {
            return Collections.unmodifiableList(this.distinctStream().map(view -> view.getElementById(id2)).filter(Objects::nonNull).collect(Collectors.toList()));
        }

        @Override
        @Nonnull
        public List<Member> getElementsByUsername(@Nonnull String name, boolean ignoreCase) {
            return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsByUsername(name, ignoreCase).stream()).collect(Collectors.toList()));
        }

        @Override
        @Nonnull
        public List<Member> getElementsByNickname(@Nullable String name, boolean ignoreCase) {
            return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsByNickname(name, ignoreCase).stream()).collect(Collectors.toList()));
        }

        @Override
        @Nonnull
        public List<Member> getElementsWithRoles(Role ... roles) {
            return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsWithRoles(roles).stream()).collect(Collectors.toList()));
        }

        @Override
        @Nonnull
        public List<Member> getElementsWithRoles(@Nonnull Collection<Role> roles) {
            return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsWithRoles(roles).stream()).collect(Collectors.toList()));
        }
    }

    public static class UnifiedSnowflakeCacheView<T extends ISnowflake>
    extends UnifiedCacheViewImpl<T, SnowflakeCacheView<T>>
    implements SnowflakeCacheView<T> {
        public UnifiedSnowflakeCacheView(Supplier<? extends Stream<? extends SnowflakeCacheView<T>>> generator) {
            super(generator);
        }

        @Override
        public T getElementById(long id2) {
            return (T)((ISnowflake)((Stream)this.generator.get()).map(view -> view.getElementById(id2)).filter(Objects::nonNull).findFirst().orElse(null));
        }
    }
}

