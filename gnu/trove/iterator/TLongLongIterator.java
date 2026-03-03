/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TLongLongIterator
extends TAdvancingIterator {
    public long key();

    public long value();

    public long setValue(long var1);
}

