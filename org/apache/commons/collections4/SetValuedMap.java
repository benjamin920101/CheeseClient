/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import java.util.Set;
import org.apache.commons.collections4.MultiValuedMap;

public interface SetValuedMap<K, V>
extends MultiValuedMap<K, V> {
    @Override
    public Set<V> get(K var1);

    @Override
    public Set<V> remove(Object var1);
}

