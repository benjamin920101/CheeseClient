/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.list;

import java.util.List;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.list.AbstractSerializableListDecorator;

public class LazyList<E>
extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -1708388017160694542L;
    private final Factory<? extends E> factory;

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory) {
        return new LazyList<E>(list, factory);
    }

    protected LazyList(List<E> list, Factory<? extends E> factory) {
        super(list);
        if (factory == null) {
            throw new IllegalArgumentException("Factory must not be null");
        }
        this.factory = factory;
    }

    @Override
    public E get(int index) {
        int size = this.decorated().size();
        if (index < size) {
            Object object = this.decorated().get(index);
            if (object == null) {
                object = this.factory.create();
                this.decorated().set(index, object);
                return object;
            }
            return object;
        }
        for (int i2 = size; i2 < index; ++i2) {
            this.decorated().add(null);
        }
        E object = this.factory.create();
        this.decorated().add(object);
        return object;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List sub = this.decorated().subList(fromIndex, toIndex);
        return new LazyList<E>(sub, this.factory);
    }
}

