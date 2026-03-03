/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TIntShortIterator
extends TAdvancingIterator {
    public int key();

    public short value();

    public short setValue(short var1);
}

