/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.iterators;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.list.UnmodifiableList;

public class CollatingIterator<E>
implements Iterator<E> {
    private Comparator<? super E> comparator = null;
    private List<Iterator<? extends E>> iterators = null;
    private List<E> values = null;
    private BitSet valueSet = null;
    private int lastReturned = -1;

    public CollatingIterator() {
        this(null, 2);
    }

    public CollatingIterator(Comparator<? super E> comp) {
        this(comp, 2);
    }

    public CollatingIterator(Comparator<? super E> comp, int initIterCapacity) {
        this.iterators = new ArrayList<Iterator<? extends E>>(initIterCapacity);
        this.setComparator(comp);
    }

    public CollatingIterator(Comparator<? super E> comp, Iterator<? extends E> a2, Iterator<? extends E> b2) {
        this(comp, 2);
        this.addIterator(a2);
        this.addIterator(b2);
    }

    public CollatingIterator(Comparator<? super E> comp, Iterator<? extends E>[] iterators) {
        this(comp, iterators.length);
        for (Iterator<? extends E> iterator : iterators) {
            this.addIterator(iterator);
        }
    }

    public CollatingIterator(Comparator<? super E> comp, Collection<Iterator<? extends E>> iterators) {
        this(comp, iterators.size());
        for (Iterator<E> iterator : iterators) {
            this.addIterator(iterator);
        }
    }

    public void addIterator(Iterator<? extends E> iterator) {
        this.checkNotStarted();
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iterators.add(iterator);
    }

    public void setIterator(int index, Iterator<? extends E> iterator) {
        this.checkNotStarted();
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iterators.set(index, iterator);
    }

    public List<Iterator<? extends E>> getIterators() {
        return UnmodifiableList.unmodifiableList(this.iterators);
    }

    public Comparator<? super E> getComparator() {
        return this.comparator;
    }

    public void setComparator(Comparator<? super E> comp) {
        this.checkNotStarted();
        this.comparator = comp;
    }

    @Override
    public boolean hasNext() {
        this.start();
        return this.anyValueSet(this.valueSet) || this.anyHasNext(this.iterators);
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int leastIndex = this.least();
        if (leastIndex == -1) {
            throw new NoSuchElementException();
        }
        E val = this.values.get(leastIndex);
        this.clear(leastIndex);
        this.lastReturned = leastIndex;
        return val;
    }

    @Override
    public void remove() {
        if (this.lastReturned == -1) {
            throw new IllegalStateException("No value can be removed at present");
        }
        this.iterators.get(this.lastReturned).remove();
    }

    public int getIteratorIndex() {
        if (this.lastReturned == -1) {
            throw new IllegalStateException("No value has been returned yet");
        }
        return this.lastReturned;
    }

    private void start() {
        if (this.values == null) {
            this.values = new ArrayList(this.iterators.size());
            this.valueSet = new BitSet(this.iterators.size());
            for (int i2 = 0; i2 < this.iterators.size(); ++i2) {
                this.values.add(null);
                this.valueSet.clear(i2);
            }
        }
    }

    private boolean set(int i2) {
        Iterator<E> it2 = this.iterators.get(i2);
        if (it2.hasNext()) {
            this.values.set(i2, it2.next());
            this.valueSet.set(i2);
            return true;
        }
        this.values.set(i2, null);
        this.valueSet.clear(i2);
        return false;
    }

    private void clear(int i2) {
        this.values.set(i2, null);
        this.valueSet.clear(i2);
    }

    private void checkNotStarted() throws IllegalStateException {
        if (this.values != null) {
            throw new IllegalStateException("Can't do that after next or hasNext has been called.");
        }
    }

    private int least() {
        int leastIndex = -1;
        Object leastObject = null;
        for (int i2 = 0; i2 < this.values.size(); ++i2) {
            if (!this.valueSet.get(i2)) {
                this.set(i2);
            }
            if (!this.valueSet.get(i2)) continue;
            if (leastIndex == -1) {
                leastIndex = i2;
                leastObject = this.values.get(i2);
                continue;
            }
            E curObject = this.values.get(i2);
            if (this.comparator == null) {
                throw new NullPointerException("You must invoke setComparator() to set a comparator first.");
            }
            if (this.comparator.compare(curObject, leastObject) >= 0) continue;
            leastObject = curObject;
            leastIndex = i2;
        }
        return leastIndex;
    }

    private boolean anyValueSet(BitSet set) {
        for (int i2 = 0; i2 < set.size(); ++i2) {
            if (!set.get(i2)) continue;
            return true;
        }
        return false;
    }

    private boolean anyHasNext(List<Iterator<? extends E>> iters) {
        for (Iterator<E> iterator : iters) {
            if (!iterator.hasNext()) continue;
            return true;
        }
        return false;
    }
}

