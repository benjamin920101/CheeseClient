/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TLongLongIterator;
import gnu.trove.map.TLongLongMap;
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
public class TLongLongMapDecorator
extends AbstractMap<Long, Long>
implements Map<Long, Long>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TLongLongMap _map;

    public TLongLongMapDecorator() {
    }

    public TLongLongMapDecorator(TLongLongMap map) {
        this._map = map;
    }

    public TLongLongMap getMap() {
        return this._map;
    }

    @Override
    public Long put(Long key, Long value) {
        long v2;
        long k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        long retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
        if (retval == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(retval);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Long get(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        long v2 = this._map.get(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public void clear() {
        this._map.clear();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Long remove(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        long v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Long, Long>> entrySet() {
        return new AbstractSet<Map.Entry<Long, Long>>(){

            @Override
            public int size() {
                return TLongLongMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TLongLongMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TLongLongMapDecorator.this.containsKey(k2) && TLongLongMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Long, Long>> iterator() {
                return new Iterator<Map.Entry<Long, Long>>(){
                    private final TLongLongIterator it;
                    {
                        this.it = TLongLongMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Long, Long> next() {
                        this.it.advance();
                        long ik2 = this.it.key();
                        final Long key = ik2 == TLongLongMapDecorator.this._map.getNoEntryKey() ? null : TLongLongMapDecorator.this.wrapKey(ik2);
                        long iv2 = this.it.value();
                        final Long v2 = iv2 == TLongLongMapDecorator.this._map.getNoEntryValue() ? null : TLongLongMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Long, Long>(){
                            private Long val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Long getKey() {
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
                                return TLongLongMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Long, Long> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Long key = (Long)((Map.Entry)o2).getKey();
                    TLongLongMapDecorator.this._map.remove(TLongLongMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Long, Long>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TLongLongMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Long && this._map.containsValue(this.unwrapValue(val));
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return this._map.containsKey(this._map.getNoEntryKey());
        }
        return key instanceof Long && this._map.containsKey(this.unwrapKey(key));
    }

    @Override
    public int size() {
        return this._map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void putAll(Map<? extends Long, ? extends Long> map) {
        Iterator<Map.Entry<? extends Long, ? extends Long>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Long, ? extends Long> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Long wrapKey(long k2) {
        return k2;
    }

    protected long unwrapKey(Object key) {
        return (Long)key;
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
        this._map = (TLongLongMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

