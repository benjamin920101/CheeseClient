/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import java.util.Set;
import org.apache.commons.collections4.IterableMap;

public interface BidiMap<K, V>
extends IterableMap<K, V> {
    @Override
    public V put(K var1, V var2);

    public K getKey(Object var1);

    public K removeValue(Object var1);

    public BidiMap<V, K> inverseBidiMap();

    @Override
    public Set<V> values();
}

