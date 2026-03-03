/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.TIntHashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.utils.ClosableIterator;
import net.dv8tion.jda.api.utils.LockIterator;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.api.utils.cache.ShardCacheView;
import net.dv8tion.jda.internal.utils.ChainedClosableIterator;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.ReadWriteLockCache;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;

public class ShardCacheViewImpl
extends ReadWriteLockCache<JDA>
implements ShardCacheView {
    protected static final JDA[] EMPTY_ARRAY = new JDA[0];
    protected final TIntObjectMap<JDA> elements;

    public ShardCacheViewImpl() {
        this.elements = new TIntObjectHashMap<JDA>();
    }

    public ShardCacheViewImpl(int initialCapacity) {
        this.elements = new TIntObjectHashMap<JDA>(initialCapacity);
    }

    public void clear() {
        try (UnlockHook hook = this.writeLock();){
            this.elements.clear();
        }
    }

    public JDA remove(int shardId) {
        try (UnlockHook hook = this.writeLock();){
            JDA jDA = this.elements.remove(shardId);
            return jDA;
        }
    }

    public TIntObjectMap<JDA> getMap() {
        if (!this.lock.writeLock().isHeldByCurrentThread()) {
            throw new IllegalStateException("Cannot access map without holding write lock!");
        }
        return this.elements;
    }

    public TIntSet keySet() {
        try (UnlockHook hook = this.readLock();){
            TIntHashSet tIntHashSet = new TIntHashSet(this.elements.keySet());
            return tIntHashSet;
        }
    }

    @Override
    public void forEach(Consumer<? super JDA> action) {
        Objects.requireNonNull(action);
        try (UnlockHook hook = this.readLock();){
            for (JDA shard : this.elements.valueCollection()) {
                action.accept(shard);
            }
        }
    }

    @Override
    @Nonnull
    public List<JDA> asList() {
        if (this.isEmpty()) {
            return Collections.emptyList();
        }
        try (UnlockHook hook = this.readLock();){
            List<JDA> list = this.getCachedList();
            if (list != null) {
                List<JDA> list2 = list;
                return list2;
            }
            List<JDA> list3 = this.cache(new ArrayList<JDA>(this.elements.valueCollection()));
            return list3;
        }
    }

    @Override
    @Nonnull
    public Set<JDA> asSet() {
        if (this.isEmpty()) {
            return Collections.emptySet();
        }
        try (UnlockHook hook = this.readLock();){
            Set<JDA> set = this.getCachedSet();
            if (set != null) {
                Set<JDA> set2 = set;
                return set2;
            }
            Set<JDA> set3 = this.cache(new HashSet<JDA>(this.elements.valueCollection()));
            return set3;
        }
    }

    @Override
    @Nonnull
    public LockIterator<JDA> lockedIterator() {
        ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
        MiscUtil.tryLock(readLock);
        try {
            Iterator<JDA> directIterator = this.elements.valueCollection().iterator();
            return new LockIterator<JDA>(directIterator, readLock);
        }
        catch (Throwable t2) {
            readLock.unlock();
            throw t2;
        }
    }

    @Override
    public long size() {
        return this.elements.size();
    }

    @Override
    public boolean isEmpty() {
        return this.elements.isEmpty();
    }

    @Override
    @Nonnull
    public List<JDA> getElementsByName(@Nonnull String name, boolean ignoreCase) {
        Checks.notEmpty(name, "Name");
        if (this.elements.isEmpty()) {
            return Collections.emptyList();
        }
        try (UnlockHook hook = this.readLock();){
            LinkedList<JDA> list = new LinkedList<JDA>();
            for (JDA elem : this.elements.valueCollection()) {
                String elementName = elem.getShardInfo().getShardString();
                if (elementName == null) continue;
                if (ignoreCase) {
                    if (!elementName.equalsIgnoreCase(name)) continue;
                    list.add(elem);
                    continue;
                }
                if (!elementName.equals(name)) continue;
                list.add(elem);
            }
            LinkedList<JDA> linkedList = list;
            return linkedList;
        }
    }

    @Override
    public Spliterator<JDA> spliterator() {
        try (UnlockHook hook = this.readLock();){
            Spliterator<JDA> spliterator = Spliterators.spliterator(this.iterator(), this.size(), 1280);
            return spliterator;
        }
    }

    @Override
    @Nonnull
    public Stream<JDA> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    @Nonnull
    public Stream<JDA> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    @Override
    @Nonnull
    public Iterator<JDA> iterator() {
        try (UnlockHook hook = this.readLock();){
            JDA[] arr2 = this.elements.values((JDA[])EMPTY_ARRAY);
            ObjectArrayIterator<JDA> objectArrayIterator = new ObjectArrayIterator<JDA>(arr2);
            return objectArrayIterator;
        }
    }

    @Override
    public JDA getElementById(int id2) {
        try (UnlockHook hook = this.readLock();){
            JDA jDA = this.elements.get(id2);
            return jDA;
        }
    }

    public int hashCode() {
        try (UnlockHook hook = this.readLock();){
            int n2 = this.elements.hashCode();
            return n2;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ShardCacheViewImpl)) {
            return false;
        }
        ShardCacheViewImpl view = (ShardCacheViewImpl)obj;
        try (UnlockHook hook = this.readLock();){
            UnlockHook otherHook = view.readLock();
            try {
                boolean bl2 = this.elements.equals(view.elements);
                if (otherHook != null) {
                    otherHook.close();
                }
                return bl2;
            }
            catch (Throwable throwable) {
                if (otherHook != null) {
                    try {
                        otherHook.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                }
                throw throwable;
            }
        }
    }

    public String toString() {
        return this.asList().toString();
    }

    public static class UnifiedShardCacheViewImpl
    implements ShardCacheView {
        protected final Supplier<? extends Stream<? extends ShardCacheView>> generator;

        public UnifiedShardCacheViewImpl(Supplier<? extends Stream<? extends ShardCacheView>> generator) {
            this.generator = generator;
        }

        @Override
        public long size() {
            return this.distinctStream().mapToLong(CacheView::size).sum();
        }

        @Override
        public boolean isEmpty() {
            return this.generator.get().allMatch(CacheView::isEmpty);
        }

        @Override
        @Nonnull
        public List<JDA> asList() {
            ArrayList list = new ArrayList();
            this.stream().forEach(list::add);
            return Collections.unmodifiableList(list);
        }

        @Override
        @Nonnull
        public Set<JDA> asSet() {
            HashSet set = new HashSet();
            this.generator.get().flatMap(CacheView::stream).forEach(set::add);
            return Collections.unmodifiableSet(set);
        }

        @Override
        @Nonnull
        public ClosableIterator<JDA> lockedIterator() {
            Iterator gen = this.generator.get().iterator();
            return new ChainedClosableIterator<JDA>(gen);
        }

        @Override
        @Nonnull
        public List<JDA> getElementsByName(@Nonnull String name, boolean ignoreCase) {
            return Collections.unmodifiableList(this.distinctStream().flatMap(view -> view.getElementsByName(name, ignoreCase).stream()).collect(Collectors.toList()));
        }

        @Override
        public JDA getElementById(int id2) {
            return this.generator.get().map(view -> view.getElementById(id2)).filter(Objects::nonNull).findFirst().orElse(null);
        }

        @Override
        @Nonnull
        public Stream<JDA> stream() {
            return this.generator.get().flatMap(CacheView::stream).distinct();
        }

        @Override
        @Nonnull
        public Stream<JDA> parallelStream() {
            return this.generator.get().flatMap(CacheView::parallelStream).distinct();
        }

        @Override
        @Nonnull
        public Iterator<JDA> iterator() {
            return this.stream().iterator();
        }

        protected Stream<? extends ShardCacheView> distinctStream() {
            return this.generator.get().distinct();
        }
    }
}

