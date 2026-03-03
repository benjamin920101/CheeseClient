/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TIntIntIterator
extends TAdvancingIterator {
    public int key();

    public int value();

    public int setValue(int var1);
}

