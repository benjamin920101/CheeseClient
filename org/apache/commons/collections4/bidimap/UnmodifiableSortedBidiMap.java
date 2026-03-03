/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.bidimap.AbstractSortedBidiMapDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.apache.commons.collections4.set.UnmodifiableSet;

public final class UnmodifiableSortedBidiMap<K, V>
extends AbstractSortedBidiMapDecorator<K, V>
implements Unmodifiable {
    private UnmodifiableSortedBidiMap<V, K> inverse;

    public static <K, V> SortedBidiMap<K, V> unmodifiableSortedBidiMap(SortedBidiMap<K, ? extends V> map) {
        if (map instanceof Unmodifiable) {
            SortedBidiMap<K, ? extends V> tmpMap = map;
            return tmpMap;
        }
        return new UnmodifiableSortedBidiMap<K, V>(map);
    }

    private UnmodifiableSortedBidiMap(SortedBidiMap<K, ? extends V> map) {
        super(map);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> mapToCopy) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set set = super.entrySet();
        return UnmodifiableEntrySet.unmodifiableEntrySet(set);
    }

    @Override
    public Set<K> keySet() {
        Set set = super.keySet();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public Set<V> values() {
        Collection set = super.values();
        return UnmodifiableSet.unmodifiableSet(set);
    }

    @Override
    public K removeValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderedMapIterator<K, V> mapIterator() {
        OrderedMapIterator it2 = this.decorated().mapIterator();
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(it2);
    }

    @Override
    public SortedBidiMap<V, K> inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableSortedBidiMap<K, V>(this.decorated().inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        SortedMap sm2 = this.decorated().subMap(fromKey, toKey);
        return UnmodifiableSortedMap.unmodifiableSortedMap(sm2);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        SortedMap sm2 = this.decorated().headMap(toKey);
        return UnmodifiableSortedMap.unmodifiableSortedMap(sm2);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        SortedMap sm2 = this.decorated().tailMap(fromKey);
        return UnmodifiableSortedMap.unmodifiableSortedMap(sm2);
    }
}

