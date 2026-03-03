/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TLongIntIterator
extends TAdvancingIterator {
    public long key();

    public int value();

    public int setValue(int var1);
}

