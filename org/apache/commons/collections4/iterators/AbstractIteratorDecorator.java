/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator;

public abstract class AbstractIteratorDecorator<E>
extends AbstractUntypedIteratorDecorator<E, E> {
    protected AbstractIteratorDecorator(Iterator<E> iterator) {
        super(iterator);
    }

    @Override
    public E next() {
        return (E)this.getIterator().next();
    }
}

