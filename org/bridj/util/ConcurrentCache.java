/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.util;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ConcurrentCache<K, V> {
    protected final ConcurrentHashMap<K, V> map = new ConcurrentHashMap();
    protected final Class<V> valueClass;
    private volatile Constructor<V> valueConstructor;

    public ConcurrentCache(Class<V> valueClass) {
        this.valueClass = valueClass;
    }

    private Constructor<V> getValueConstructor() {
        if (this.valueConstructor == null) {
            try {
                this.valueConstructor = this.valueClass.getConstructor(new Class[0]);
                if (this.valueConstructor != null && this.valueConstructor.isAccessible()) {
                    this.valueConstructor.setAccessible(true);
                }
            }
            catch (Exception ex2) {
                throw new RuntimeException("No accessible default constructor in class " + (this.valueClass == null ? "null" : this.valueClass.getName()), ex2);
            }
        }
        return this.valueConstructor;
    }

    protected V newInstance(K key) {
        try {
            return this.getValueConstructor().newInstance(new Object[0]);
        }
        catch (Exception ex2) {
            throw new RuntimeException("Failed to call constructor " + this.valueConstructor, ex2);
        }
    }

    public V get(K key) {
        V v2 = this.map.get(key);
        if (v2 == null) {
            V newV = this.newInstance(key);
            V oldV = this.map.putIfAbsent(key, newV);
            v2 = oldV != null ? oldV : newV;
        }
        return v2;
    }

    public void clear() {
        this.map.clear();
    }

    public Iterable<Map.Entry<K, V>> entrySet() {
        return this.map.entrySet();
    }
}

