/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.TransformedBag;

public class TransformedSortedBag<E>
extends TransformedBag<E>
implements SortedBag<E> {
    private static final long serialVersionUID = -251737742649401930L;

    public static <E> TransformedSortedBag<E> transformingSortedBag(SortedBag<E> bag2, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSortedBag<E>(bag2, transformer);
    }

    public static <E> TransformedSortedBag<E> transformedSortedBag(SortedBag<E> bag2, Transformer<? super E, ? extends E> transformer) {
        TransformedSortedBag<E> decorated = new TransformedSortedBag<E>(bag2, transformer);
        if (bag2.size() > 0) {
            Object[] values = bag2.toArray();
            bag2.clear();
            for (Object value : values) {
                decorated.decorated().add(transformer.transform(value));
            }
        }
        return decorated;
    }

    protected TransformedSortedBag(SortedBag<E> bag2, Transformer<? super E, ? extends E> transformer) {
        super(bag2, transformer);
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag)this.decorated();
    }

    @Override
    public E first() {
        return this.getSortedBag().first();
    }

    @Override
    public E last() {
        return this.getSortedBag().last();
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.getSortedBag().comparator();
    }
}

