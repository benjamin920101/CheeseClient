/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TIntLongIterator;
import gnu.trove.map.TIntLongMap;
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
public class TIntLongMapDecorator
extends AbstractMap<Integer, Long>
implements Map<Integer, Long>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TIntLongMap _map;

    public TIntLongMapDecorator() {
    }

    public TIntLongMapDecorator(TIntLongMap map) {
        this._map = map;
    }

    public TIntLongMap getMap() {
        return this._map;
    }

    @Override
    public Long put(Integer key, Long value) {
        long v2;
        int k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        int k2;
        if (key != null) {
            if (!(key instanceof Integer)) return null;
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
        int k2;
        if (key != null) {
            if (!(key instanceof Integer)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        long v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Integer, Long>> entrySet() {
        return new AbstractSet<Map.Entry<Integer, Long>>(){

            @Override
            public int size() {
                return TIntLongMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TIntLongMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TIntLongMapDecorator.this.containsKey(k2) && TIntLongMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Integer, Long>> iterator() {
                return new Iterator<Map.Entry<Integer, Long>>(){
                    private final TIntLongIterator it;
                    {
                        this.it = TIntLongMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Integer, Long> next() {
                        this.it.advance();
                        int ik2 = this.it.key();
                        final Integer key = ik2 == TIntLongMapDecorator.this._map.getNoEntryKey() ? null : TIntLongMapDecorator.this.wrapKey(ik2);
                        long iv2 = this.it.value();
                        final Long v2 = iv2 == TIntLongMapDecorator.this._map.getNoEntryValue() ? null : TIntLongMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Integer, Long>(){
                            private Long val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Integer getKey() {
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
                                return TIntLongMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Integer, Long> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Integer key = (Integer)((Map.Entry)o2).getKey();
                    TIntLongMapDecorator.this._map.remove(TIntLongMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Integer, Long>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TIntLongMapDecorator.this.clear();
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
        return key instanceof Integer && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Integer, ? extends Long> map) {
        Iterator<Map.Entry<? extends Integer, ? extends Long>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Integer, ? extends Long> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Integer wrapKey(int k2) {
        return k2;
    }

    protected int unwrapKey(Object key) {
        return (Integer)key;
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
        this._map = (TIntLongMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

