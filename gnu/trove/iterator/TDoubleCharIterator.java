/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TDoubleCharIterator
extends TAdvancingIterator {
    public double key();

    public char value();

    public char setValue(char var1);
}

