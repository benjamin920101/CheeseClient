/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.list.AbstractListDecorator;

public abstract class AbstractSerializableListDecorator<E>
extends AbstractListDecorator<E> {
    private static final long serialVersionUID = 2684959196747496299L;

    protected AbstractSerializableListDecorator(List<E> list) {
        super(list);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(this.decorated());
    }

    private void readObject(ObjectInputStream in2) throws IOException, ClassNotFoundException {
        in2.defaultReadObject();
        this.setCollection((Collection)in2.readObject());
    }
}

