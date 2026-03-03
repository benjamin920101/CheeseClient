/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.AbstractBagDecorator;

public final class CollectionBag<E>
extends AbstractBagDecorator<E> {
    private static final long serialVersionUID = -2560033712679053143L;

    public static <E> Bag<E> collectionBag(Bag<E> bag2) {
        return new CollectionBag<E>(bag2);
    }

    public CollectionBag(Bag<E> bag2) {
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
    public boolean containsAll(Collection<?> coll) {
        Iterator<?> e2 = coll.iterator();
        while (e2.hasNext()) {
            if (this.contains(e2.next())) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean add(E object) {
        return this.add(object, 1);
    }

    @Override
    public boolean addAll(Collection<? extends E> coll) {
        boolean changed = false;
        Iterator<E> i2 = coll.iterator();
        while (i2.hasNext()) {
            boolean added = this.add(i2.next(), 1);
            changed = changed || added;
        }
        return changed;
    }

    @Override
    public boolean remove(Object object) {
        return this.remove(object, 1);
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        if (coll != null) {
            boolean result = false;
            for (Object obj : coll) {
                boolean changed = this.remove(obj, this.getCount(obj));
                result = result || changed;
            }
            return result;
        }
        return this.decorated().removeAll(null);
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        if (coll != null) {
            boolean modified = false;
            Iterator e2 = this.iterator();
            while (e2.hasNext()) {
                if (coll.contains(e2.next())) continue;
                e2.remove();
                modified = true;
            }
            return modified;
        }
        return this.decorated().retainAll(null);
    }

    @Override
    public boolean add(E object, int count) {
        this.decorated().add(object, count);
        return true;
    }
}

