/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import org.apache.commons.collections4.IterableMap;

public interface BoundedMap<K, V>
extends IterableMap<K, V> {
    public boolean isFull();

    public int maxSize();
}

