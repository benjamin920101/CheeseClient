/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.bag.AbstractBagDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableBag<E>
extends AbstractBagDecorator<E>
implements Unmodifiable {
    private static final long serialVersionUID = -1873799975157099624L;

    public static <E> Bag<E> unmodifiableBag(Bag<? extends E> bag2) {
        if (bag2 instanceof Unmodifiable) {
            Bag<? extends E> tmpBag = bag2;
            return tmpBag;
        }
        return new UnmodifiableBag<E>(bag2);
    }

    private UnmodifiableBag(Bag<? extends E> bag2) {
        super(bag2);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in2) throws IOException, ClassNotFoundException {
        in2.defaultReadObject();
        this.setCollection((Collection)in2.readObject());
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
    }

    @Override
    public boolean add(E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object, int count) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<E> uniqueSet() {
        Set set = this.decorated().uniqueSet();
        return UnmodifiableSet.unmodifiableSet(set);
    }
}

