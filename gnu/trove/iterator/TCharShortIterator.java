/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TCharShortIterator
extends TAdvancingIterator {
    public char key();

    public short value();

    public short setValue(short var1);
}

