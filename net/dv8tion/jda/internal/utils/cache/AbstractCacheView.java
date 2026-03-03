/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import gnu.trove.set.TLongSet;
import gnu.trove.set.hash.TLongHashSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.utils.LockIterator;
import net.dv8tion.jda.api.utils.MiscUtil;
import net.dv8tion.jda.api.utils.cache.CacheView;
import net.dv8tion.jda.internal.utils.Checks;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.ReadWriteLockCache;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;

public abstract class AbstractCacheView<T>
extends ReadWriteLockCache<T>
implements CacheView<T> {
    protected final TLongObjectMap<T> elements = new TLongObjectHashMap<T>();
    protected final T[] emptyArray;
    protected final Function<T, String> nameMapper;
    protected final Class<T> type;

    protected AbstractCacheView(Class<T> type, Function<T, String> nameMapper) {
        this.nameMapper = nameMapper;
        this.type = type;
        this.emptyArray = (Object[])Array.newInstance(type, 0);
    }

    public void clear() {
        try (UnlockHook hook = this.writeLock();){
            this.elements.clear();
        }
    }

    public TLongObjectMap<T> getMap() {
        if (!this.lock.writeLock().isHeldByCurrentThread()) {
            throw new IllegalStateException("Cannot access map directly without holding write lock!");
        }
        return this.elements;
    }

    public T get(long id2) {
        try (UnlockHook hook = this.readLock();){
            T t2 = this.elements.get(id2);
            return t2;
        }
    }

    public T remove(long id2) {
        try (UnlockHook hook = this.writeLock();){
            T t2 = this.elements.remove(id2);
            return t2;
        }
    }

    public TLongSet keySet() {
        try (UnlockHook hook = this.readLock();){
            TLongHashSet tLongHashSet = new TLongHashSet(this.elements.keySet());
            return tLongHashSet;
        }
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        try (UnlockHook hook = this.readLock();){
            for (T elem : this.elements.valueCollection()) {
                action.accept(elem);
            }
        }
    }

    @Override
    @Nonnull
    public LockIterator<T> lockedIterator() {
        ReentrantReadWriteLock.ReadLock readLock = this.lock.readLock();
        MiscUtil.tryLock(readLock);
        try {
            Iterator<T> directIterator = this.elements.valueCollection().iterator();
            return new LockIterator<T>(directIterator, readLock);
        }
        catch (Throwable t2) {
            readLock.unlock();
            throw t2;
        }
    }

    @Override
    @Nonnull
    public List<T> asList() {
        if (this.isEmpty()) {
            return Collections.emptyList();
        }
        try (UnlockHook hook = this.readLock();){
            List list = this.getCachedList();
            if (list != null) {
                List list2 = list;
                return list2;
            }
            list = new ArrayList(this.elements.size());
            this.elements.forEachValue(list::add);
            List list3 = this.cache(list);
            return list3;
        }
    }

    @Override
    @Nonnull
    public Set<T> asSet() {
        if (this.isEmpty()) {
            return Collections.emptySet();
        }
        try (UnlockHook hook = this.readLock();){
            Set set = this.getCachedSet();
            if (set != null) {
                Set set2 = set;
                return set2;
            }
            set = new HashSet(this.elements.size());
            this.elements.forEachValue(set::add);
            Set set3 = this.cache(set);
            return set3;
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
    public List<T> getElementsByName(@Nonnull String name, boolean ignoreCase) {
        Checks.notEmpty(name, "Name");
        if (this.elements.isEmpty()) {
            return Collections.emptyList();
        }
        if (this.nameMapper == null) {
            throw new UnsupportedOperationException("The contained elements are not assigned with names.");
        }
        if (this.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList list = new ArrayList();
        this.forEach(elem -> {
            String elementName = this.nameMapper.apply(elem);
            if (elementName != null && this.equals(ignoreCase, elementName, name)) {
                list.add(elem);
            }
        });
        return list;
    }

    @Override
    public Spliterator<T> spliterator() {
        try (UnlockHook hook = this.readLock();){
            Spliterator spliterator = Spliterators.spliterator(this.elements.values(), 1024);
            return spliterator;
        }
    }

    @Override
    @Nonnull
    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    @Nonnull
    public Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        try (UnlockHook hook = this.readLock();){
            ObjectArrayIterator<T> objectArrayIterator = new ObjectArrayIterator<T>(this.elements.values(this.emptyArray));
            return objectArrayIterator;
        }
    }

    public String toString() {
        return this.asList().toString();
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
        if (!(obj instanceof AbstractCacheView)) {
            return false;
        }
        AbstractCacheView view = (AbstractCacheView)obj;
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

    protected boolean equals(boolean ignoreCase, String first, String second) {
        return ignoreCase ? first.equalsIgnoreCase(second) : first.equals(second);
    }
}

