/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.map;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.apache.commons.collections4.map.AbstractIterableMap;
import org.apache.commons.collections4.set.CompositeSet;

public class CompositeMap<K, V>
extends AbstractIterableMap<K, V>
implements Serializable {
    private static final long serialVersionUID = -6096931280583808322L;
    private Map<K, V>[] composite;
    private MapMutator<K, V> mutator;

    public CompositeMap() {
        this((Map<K, V>[])new Map[0], (MapMutator<K, V>)null);
    }

    public CompositeMap(Map<K, V> one, Map<K, V> two) {
        this((Map<K, V>[])new Map[]{one, two}, (MapMutator<K, V>)null);
    }

    public CompositeMap(Map<K, V> one, Map<K, V> two, MapMutator<K, V> mutator) {
        this(new Map[]{one, two}, mutator);
    }

    public CompositeMap(Map<K, V> ... composite) {
        this(composite, (MapMutator<K, V>)null);
    }

    public CompositeMap(Map<K, V>[] composite, MapMutator<K, V> mutator) {
        this.mutator = mutator;
        this.composite = new Map[0];
        for (int i2 = composite.length - 1; i2 >= 0; --i2) {
            this.addComposited(composite[i2]);
        }
    }

    public void setMutator(MapMutator<K, V> mutator) {
        this.mutator = mutator;
    }

    public synchronized void addComposited(Map<K, V> map) throws IllegalArgumentException {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            Collection<K> intersect = CollectionUtils.intersection(this.composite[i2].keySet(), map.keySet());
            if (intersect.size() == 0) continue;
            if (this.mutator == null) {
                throw new IllegalArgumentException("Key collision adding Map to CompositeMap");
            }
            this.mutator.resolveCollision(this, this.composite[i2], map, intersect);
        }
        Map[] temp = new Map[this.composite.length + 1];
        System.arraycopy(this.composite, 0, temp, 0, this.composite.length);
        temp[temp.length - 1] = map;
        this.composite = temp;
    }

    public synchronized Map<K, V> removeComposited(Map<K, V> map) {
        int size = this.composite.length;
        for (int i2 = 0; i2 < size; ++i2) {
            if (!this.composite[i2].equals(map)) continue;
            Map[] temp = new Map[size - 1];
            System.arraycopy(this.composite, 0, temp, 0, i2);
            System.arraycopy(this.composite, i2 + 1, temp, i2, size - i2 - 1);
            this.composite = temp;
            return map;
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            this.composite[i2].clear();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            if (!this.composite[i2].containsKey(key)) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            if (!this.composite[i2].containsValue(value)) continue;
            return true;
        }
        return false;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        CompositeSet<Map.Entry<K, V>> entries = new CompositeSet<Map.Entry<K, V>>();
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            entries.addComposited(this.composite[i2].entrySet());
        }
        return entries;
    }

    @Override
    public V get(Object key) {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            if (!this.composite[i2].containsKey(key)) continue;
            return this.composite[i2].get(key);
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            if (this.composite[i2].isEmpty()) continue;
            return false;
        }
        return true;
    }

    @Override
    public Set<K> keySet() {
        CompositeSet<K> keys = new CompositeSet<K>();
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            keys.addComposited(this.composite[i2].keySet());
        }
        return keys;
    }

    @Override
    public V put(K key, V value) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("No mutator specified");
        }
        return this.mutator.put(this, this.composite, key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        if (this.mutator == null) {
            throw new UnsupportedOperationException("No mutator specified");
        }
        this.mutator.putAll(this, this.composite, map);
    }

    @Override
    public V remove(Object key) {
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            if (!this.composite[i2].containsKey(key)) continue;
            return this.composite[i2].remove(key);
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            size += this.composite[i2].size();
        }
        return size;
    }

    @Override
    public Collection<V> values() {
        CompositeCollection<V> values = new CompositeCollection<V>();
        for (int i2 = this.composite.length - 1; i2 >= 0; --i2) {
            values.addComposited(this.composite[i2].values());
        }
        return values;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Map) {
            Map map = (Map)obj;
            return this.entrySet().equals(map.entrySet());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int code = 0;
        for (Map.Entry<K, V> entry : this.entrySet()) {
            code += entry.hashCode();
        }
        return code;
    }

    public static interface MapMutator<K, V>
    extends Serializable {
        public void resolveCollision(CompositeMap<K, V> var1, Map<K, V> var2, Map<K, V> var3, Collection<K> var4);

        public V put(CompositeMap<K, V> var1, Map<K, V>[] var2, K var3, V var4);

        public void putAll(CompositeMap<K, V> var1, Map<K, V>[] var2, Map<? extends K, ? extends V> var3);
    }
}

