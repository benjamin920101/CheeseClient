/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.utils.cache.SortedSnowflakeCacheView;
import net.dv8tion.jda.internal.utils.UnlockHook;
import net.dv8tion.jda.internal.utils.cache.SnowflakeCacheViewImpl;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;

public class SortedSnowflakeCacheViewImpl<T extends ISnowflake & Comparable<? super T>>
extends SnowflakeCacheViewImpl<T>
implements SortedSnowflakeCacheView<T> {
    protected static final int SPLIT_CHARACTERISTICS = 1296;
    protected final Comparator<T> comparator;

    public SortedSnowflakeCacheViewImpl(Class<T> type, Comparator<T> comparator) {
        this(type, null, comparator);
    }

    public SortedSnowflakeCacheViewImpl(Class<T> type, Function<T, String> nameMapper, Comparator<T> comparator) {
        super(type, nameMapper);
        this.comparator = comparator;
    }

    @Override
    public void forEach(@Nonnull Consumer<? super T> action) {
        try (UnlockHook hook = this.readLock();){
            this.iterator().forEachRemaining(action);
        }
    }

    @Override
    public void forEachUnordered(@Nonnull Consumer<? super T> action) {
        super.forEach(action);
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
            this.elements.forEachValue(x$0 -> list.add(x$0));
            list.sort(this.comparator);
            List list3 = this.cache(list);
            return list3;
        }
    }

    @Override
    @Nonnull
    public NavigableSet<T> asSet() {
        if (this.isEmpty()) {
            return Collections.emptyNavigableSet();
        }
        try (UnlockHook hook = this.readLock();){
            TreeSet set = (TreeSet)this.getCachedSet();
            if (set != null) {
                TreeSet treeSet = set;
                return treeSet;
            }
            set = new TreeSet(this.comparator);
            this.elements.forEachValue(x$0 -> set.add(x$0));
            NavigableSet<T> navigableSet = this.cache(set);
            return navigableSet;
        }
    }

    @Override
    @Nonnull
    public List<T> getElementsByName(@Nonnull String name, boolean ignoreCase) {
        List filtered = super.getElementsByName(name, ignoreCase);
        filtered.sort(this.comparator);
        return filtered;
    }

    @Override
    public Spliterator<T> spliterator() {
        try (UnlockHook hook = this.readLock();){
            Spliterator<T> spliterator = Spliterators.spliterator(this.iterator(), (long)this.elements.size(), 1296);
            return spliterator;
        }
    }

    @Override
    @Nonnull
    public Stream<T> streamUnordered() {
        return super.stream();
    }

    @Override
    @Nonnull
    public Stream<T> parallelStreamUnordered() {
        return super.parallelStream();
    }

    @Override
    @Nonnull
    public Stream<T> stream() {
        return super.stream().sorted(this.comparator);
    }

    @Override
    @Nonnull
    public Stream<T> parallelStream() {
        return super.parallelStream().sorted(this.comparator);
    }

    @Override
    @Nonnull
    public Iterator<T> iterator() {
        try (UnlockHook hook = this.readLock();){
            ISnowflake[] arr2 = this.elements.values((ISnowflake[])this.emptyArray);
            Arrays.sort(arr2, this.comparator);
            ObjectArrayIterator<ISnowflake> objectArrayIterator = new ObjectArrayIterator<ISnowflake>(arr2);
            return objectArrayIterator;
        }
    }
}

