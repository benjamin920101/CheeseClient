/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TIntObjectIterator<V>
extends TAdvancingIterator {
    public int key();

    public V value();

    public V setValue(V var1);
}

