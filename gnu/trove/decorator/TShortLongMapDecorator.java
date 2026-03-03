/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TShortLongIterator;
import gnu.trove.map.TShortLongMap;
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
public class TShortLongMapDecorator
extends AbstractMap<Short, Long>
implements Map<Short, Long>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TShortLongMap _map;

    public TShortLongMapDecorator() {
    }

    public TShortLongMapDecorator(TShortLongMap map) {
        this._map = map;
    }

    public TShortLongMap getMap() {
        return this._map;
    }

    @Override
    public Long put(Short key, Long value) {
        long v2;
        short k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        short k2;
        if (key != null) {
            if (!(key instanceof Short)) return null;
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
        short k2;
        if (key != null) {
            if (!(key instanceof Short)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        long v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Short, Long>> entrySet() {
        return new AbstractSet<Map.Entry<Short, Long>>(){

            @Override
            public int size() {
                return TShortLongMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TShortLongMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TShortLongMapDecorator.this.containsKey(k2) && TShortLongMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Short, Long>> iterator() {
                return new Iterator<Map.Entry<Short, Long>>(){
                    private final TShortLongIterator it;
                    {
                        this.it = TShortLongMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Short, Long> next() {
                        this.it.advance();
                        short ik2 = this.it.key();
                        final Short key = ik2 == TShortLongMapDecorator.this._map.getNoEntryKey() ? null : TShortLongMapDecorator.this.wrapKey(ik2);
                        long iv2 = this.it.value();
                        final Long v2 = iv2 == TShortLongMapDecorator.this._map.getNoEntryValue() ? null : TShortLongMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Short, Long>(){
                            private Long val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Short getKey() {
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
                                return TShortLongMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Short, Long> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Short key = (Short)((Map.Entry)o2).getKey();
                    TShortLongMapDecorator.this._map.remove(TShortLongMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Short, Long>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TShortLongMapDecorator.this.clear();
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
        return key instanceof Short && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Short, ? extends Long> map) {
        Iterator<Map.Entry<? extends Short, ? extends Long>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Short, ? extends Long> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Short wrapKey(short k2) {
        return k2;
    }

    protected short unwrapKey(Object key) {
        return (Short)key;
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
        this._map = (TShortLongMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

