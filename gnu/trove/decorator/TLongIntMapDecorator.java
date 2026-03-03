/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TLongIntIterator;
import gnu.trove.map.TLongIntMap;
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
public class TLongIntMapDecorator
extends AbstractMap<Long, Integer>
implements Map<Long, Integer>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TLongIntMap _map;

    public TLongIntMapDecorator() {
    }

    public TLongIntMapDecorator(TLongIntMap map) {
        this._map = map;
    }

    public TLongIntMap getMap() {
        return this._map;
    }

    @Override
    public Integer put(Long key, Integer value) {
        int v2;
        long k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        int retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Integer get(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        int v2 = this._map.get(k2);
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
    public Integer remove(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        int v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Long, Integer>> entrySet() {
        return new AbstractSet<Map.Entry<Long, Integer>>(){

            @Override
            public int size() {
                return TLongIntMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TLongIntMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TLongIntMapDecorator.this.containsKey(k2) && TLongIntMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Long, Integer>> iterator() {
                return new Iterator<Map.Entry<Long, Integer>>(){
                    private final TLongIntIterator it;
                    {
                        this.it = TLongIntMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Long, Integer> next() {
                        this.it.advance();
                        long ik2 = this.it.key();
                        final Long key = ik2 == TLongIntMapDecorator.this._map.getNoEntryKey() ? null : TLongIntMapDecorator.this.wrapKey(ik2);
                        int iv2 = this.it.value();
                        final Integer v2 = iv2 == TLongIntMapDecorator.this._map.getNoEntryValue() ? null : TLongIntMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Long, Integer>(){
                            private Integer val;
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
                            public Integer getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Integer setValue(Integer value) {
                                this.val = value;
                                return TLongIntMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Long, Integer> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Long key = (Long)((Map.Entry)o2).getKey();
                    TLongIntMapDecorator.this._map.remove(TLongIntMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Long, Integer>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TLongIntMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Integer && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends Long, ? extends Integer> map) {
        Iterator<Map.Entry<? extends Long, ? extends Integer>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Long, ? extends Integer> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Long wrapKey(long k2) {
        return k2;
    }

    protected long unwrapKey(Object key) {
        return (Long)key;
    }

    protected Integer wrapValue(int k2) {
        return k2;
    }

    protected int unwrapValue(Object value) {
        return (Integer)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TLongIntMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

