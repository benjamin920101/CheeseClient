/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.bag.SynchronizedBag;

public class SynchronizedSortedBag<E>
extends SynchronizedBag<E>
implements SortedBag<E> {
    private static final long serialVersionUID = 722374056718497858L;

    public static <E> SynchronizedSortedBag<E> synchronizedSortedBag(SortedBag<E> bag2) {
        return new SynchronizedSortedBag<E>(bag2);
    }

    protected SynchronizedSortedBag(SortedBag<E> bag2) {
        super(bag2);
    }

    protected SynchronizedSortedBag(Bag<E> bag2, Object lock) {
        super(bag2, lock);
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag)this.decorated();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized E first() {
        Object object = this.lock;
        synchronized (object) {
            return this.getSortedBag().first();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized E last() {
        Object object = this.lock;
        synchronized (object) {
            return this.getSortedBag().last();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public synchronized Comparator<? super E> comparator() {
        Object object = this.lock;
        synchronized (object) {
            return this.getSortedBag().comparator();
        }
    }
}

