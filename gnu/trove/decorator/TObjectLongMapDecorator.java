/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TObjectLongIterator;
import gnu.trove.map.TObjectLongMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TObjectLongMapDecorator<K>
extends AbstractMap<K, Long>
implements Map<K, Long>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TObjectLongMap<K> _map;

    public TObjectLongMapDecorator() {
    }

    public TObjectLongMapDecorator(TObjectLongMap<K> map) {
        this._map = map;
    }

    public TObjectLongMap<K> getMap() {
        return this._map;
    }

    @Override
    public Long put(K key, Long value) {
        if (value == null) {
            return this.wrapValue(this._map.put(key, this._map.getNoEntryValue()));
        }
        return this.wrapValue(this._map.put(key, this.unwrapValue(value)));
    }

    @Override
    public Long get(Object key) {
        long v2 = this._map.get(key);
        if (v2 == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(v2);
    }

    @Override
    public void clear() {
        this._map.clear();
    }

    @Override
    public Long remove(Object key) {
        long v2 = this._map.remove(key);
        if (v2 == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(v2);
    }

    @Override
    public Set<Map.Entry<K, Long>> entrySet() {
        return new AbstractSet<Map.Entry<K, Long>>(){

            @Override
            public int size() {
                return TObjectLongMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TObjectLongMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TObjectLongMapDecorator.this.containsKey(k2) && TObjectLongMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<K, Long>> iterator() {
                return new Iterator<Map.Entry<K, Long>>(){
                    private final TObjectLongIterator<K> it;
                    {
                        this.it = TObjectLongMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<K, Long> next() {
                        this.it.advance();
                        final Object key = this.it.key();
                        final Long v2 = TObjectLongMapDecorator.this.wrapValue(this.it.value());
                        return new Map.Entry<K, Long>(){
                            private Long val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public K getKey() {
                                return key;
                            }

                            @Override
                            public Long getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Long setValue(Long value) {
                                this.val = value;
                                return TObjectLongMapDecorator.this.put(key, value);
                            }
                        };
                    }

                    @Override
                    public boolean hasNext() {
                        return this.it.hasNext();
                    }

                    @Override
                    public void remove() {
                        this.it.remove();
                    }
                };
            }

            @Override
            public boolean add(Map.Entry<K, Long> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Object key = ((Map.Entry)o2).getKey();
                    TObjectLongMapDecorator.this._map.remove(key);
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<K, Long>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TObjectLongMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Long && this._map.containsValue(this.unwrapValue(val));
    }

    @Override
    public boolean containsKey(Object key) {
        return this._map.containsKey(key);
    }

    @Override
    public int size() {
        return this._map.size();
    }

    @Override
    public boolean isEmpty() {
        return this._map.size() == 0;
    }

    @Override
    public void putAll(Map<? extends K, ? extends Long> map) {
        Iterator<Map.Entry<K, Long>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<K, Long> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Long wrapValue(long k2) {
        return k2;
    }

    protected long unwrapValue(Object value) {
        return (Long)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TObjectLongMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

