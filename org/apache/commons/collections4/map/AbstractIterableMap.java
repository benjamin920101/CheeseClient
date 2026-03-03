/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter;

public abstract class AbstractIterableMap<K, V>
implements IterableMap<K, V> {
    @Override
    public MapIterator<K, V> mapIterator() {
        return new EntrySetToMapIteratorAdapter(this.entrySet());
    }
}

