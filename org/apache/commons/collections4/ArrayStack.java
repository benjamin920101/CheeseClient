/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import java.util.ArrayList;
import java.util.EmptyStackException;

@Deprecated
public class ArrayStack<E>
extends ArrayList<E> {
    private static final long serialVersionUID = 2130079159931574599L;

    public ArrayStack() {
    }

    public ArrayStack(int initialSize) {
        super(initialSize);
    }

    public boolean empty() {
        return this.isEmpty();
    }

    public E peek() throws EmptyStackException {
        int n2 = this.size();
        if (n2 <= 0) {
            throw new EmptyStackException();
        }
        return this.get(n2 - 1);
    }

    public E peek(int n2) throws EmptyStackException {
        int m2 = this.size() - n2 - 1;
        if (m2 < 0) {
            throw new EmptyStackException();
        }
        return this.get(m2);
    }

    public E pop() throws EmptyStackException {
        int n2 = this.size();
        if (n2 <= 0) {
            throw new EmptyStackException();
        }
        return this.remove(n2 - 1);
    }

    public E push(E item) {
        this.add(item);
        return item;
    }

    public int search(Object object) {
        int i2 = this.size() - 1;
        int n2 = 1;
        while (i2 >= 0) {
            Object current = this.get(i2);
            if (object == null && current == null || object != null && object.equals(current)) {
                return n2;
            }
            --i2;
            ++n2;
        }
        return -1;
    }
}

