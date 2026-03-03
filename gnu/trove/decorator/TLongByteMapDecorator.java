/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TLongByteIterator;
import gnu.trove.map.TLongByteMap;
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
public class TLongByteMapDecorator
extends AbstractMap<Long, Byte>
implements Map<Long, Byte>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TLongByteMap _map;

    public TLongByteMapDecorator() {
    }

    public TLongByteMapDecorator(TLongByteMap map) {
        this._map = map;
    }

    public TLongByteMap getMap() {
        return this._map;
    }

    @Override
    public Byte put(Long key, Byte value) {
        byte v2;
        long k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        byte retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Byte get(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        byte v2 = this._map.get(k2);
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
    public Byte remove(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        byte v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Long, Byte>> entrySet() {
        return new AbstractSet<Map.Entry<Long, Byte>>(){

            @Override
            public int size() {
                return TLongByteMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TLongByteMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TLongByteMapDecorator.this.containsKey(k2) && TLongByteMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Long, Byte>> iterator() {
                return new Iterator<Map.Entry<Long, Byte>>(){
                    private final TLongByteIterator it;
                    {
                        this.it = TLongByteMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Long, Byte> next() {
                        this.it.advance();
                        long ik2 = this.it.key();
                        final Long key = ik2 == TLongByteMapDecorator.this._map.getNoEntryKey() ? null : TLongByteMapDecorator.this.wrapKey(ik2);
                        byte iv2 = this.it.value();
                        final Byte v2 = iv2 == TLongByteMapDecorator.this._map.getNoEntryValue() ? null : TLongByteMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Long, Byte>(){
                            private Byte val;
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
                            public Byte getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Byte setValue(Byte value) {
                                this.val = value;
                                return TLongByteMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Long, Byte> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Long key = (Long)((Map.Entry)o2).getKey();
                    TLongByteMapDecorator.this._map.remove(TLongByteMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Long, Byte>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TLongByteMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Byte && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends Long, ? extends Byte> map) {
        Iterator<Map.Entry<? extends Long, ? extends Byte>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Long, ? extends Byte> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Long wrapKey(long k2) {
        return k2;
    }

    protected long unwrapKey(Object key) {
        return (Long)key;
    }

    protected Byte wrapValue(byte k2) {
        return k2;
    }

    protected byte unwrapValue(Object value) {
        return (Byte)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TLongByteMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

