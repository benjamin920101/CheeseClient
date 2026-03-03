/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4;

import java.util.SortedMap;
import org.apache.commons.collections4.IterableSortedMap;

public interface Trie<K, V>
extends IterableSortedMap<K, V> {
    public SortedMap<K, V> prefixMap(K var1);
}

