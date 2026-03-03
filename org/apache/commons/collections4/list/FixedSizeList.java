/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.list.AbstractSerializableListDecorator;

public class FixedSizeList<E>
extends AbstractSerializableListDecorator<E>
implements BoundedCollection<E> {
    private static final long serialVersionUID = -2218010673611160319L;

    public static <E> FixedSizeList<E> fixedSizeList(List<E> list) {
        return new FixedSizeList<E>(list);
    }

    protected FixedSizeList(List<E> list) {
        super(list);
    }

    @Override
    public boolean add(E object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public void add(int index, E object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public E get(int index) {
        return this.decorated().get(index);
    }

    @Override
    public int indexOf(Object object) {
        return this.decorated().indexOf(object);
    }

    @Override
    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(this.decorated().iterator());
    }

    @Override
    public int lastIndexOf(Object object) {
        return this.decorated().lastIndexOf(object);
    }

    @Override
    public ListIterator<E> listIterator() {
        return new FixedSizeListIterator(this.decorated().listIterator(0));
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new FixedSizeListIterator(this.decorated().listIterator(index));
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException("List is fixed size");
    }

    @Override
    public E set(int index, E object) {
        return this.decorated().set(index, object);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List sub = this.decorated().subList(fromIndex, toIndex);
        return new FixedSizeList(sub);
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public int maxSize() {
        return this.size();
    }

    private class FixedSizeListIterator
    extends AbstractListIteratorDecorator<E> {
        protected FixedSizeListIterator(ListIterator<E> iterator) {
            super(iterator);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("List is fixed size");
        }

        @Override
        public void add(Object object) {
            throw new UnsupportedOperationException("List is fixed size");
        }
    }
}

