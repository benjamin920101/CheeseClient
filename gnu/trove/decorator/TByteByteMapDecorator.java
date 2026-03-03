/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TByteByteIterator;
import gnu.trove.map.TByteByteMap;
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
public class TByteByteMapDecorator
extends AbstractMap<Byte, Byte>
implements Map<Byte, Byte>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TByteByteMap _map;

    public TByteByteMapDecorator() {
    }

    public TByteByteMapDecorator(TByteByteMap map) {
        this._map = map;
    }

    public TByteByteMap getMap() {
        return this._map;
    }

    @Override
    public Byte put(Byte key, Byte value) {
        byte v2;
        byte k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
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
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        byte v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Byte, Byte>> entrySet() {
        return new AbstractSet<Map.Entry<Byte, Byte>>(){

            @Override
            public int size() {
                return TByteByteMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TByteByteMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TByteByteMapDecorator.this.containsKey(k2) && TByteByteMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Byte, Byte>> iterator() {
                return new Iterator<Map.Entry<Byte, Byte>>(){
                    private final TByteByteIterator it;
                    {
                        this.it = TByteByteMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Byte, Byte> next() {
                        this.it.advance();
                        byte ik2 = this.it.key();
                        final Byte key = ik2 == TByteByteMapDecorator.this._map.getNoEntryKey() ? null : TByteByteMapDecorator.this.wrapKey(ik2);
                        byte iv2 = this.it.value();
                        final Byte v2 = iv2 == TByteByteMapDecorator.this._map.getNoEntryValue() ? null : TByteByteMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Byte, Byte>(){
                            private Byte val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Byte getKey() {
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
                                return TByteByteMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Byte, Byte> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Byte key = (Byte)((Map.Entry)o2).getKey();
                    TByteByteMapDecorator.this._map.remove(TByteByteMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Byte, Byte>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TByteByteMapDecorator.this.clear();
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
        return key instanceof Byte && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Byte, ? extends Byte> map) {
        Iterator<Map.Entry<? extends Byte, ? extends Byte>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Byte, ? extends Byte> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Byte wrapKey(byte k2) {
        return k2;
    }

    protected byte unwrapKey(Object key) {
        return (Byte)key;
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
        this._map = (TByteByteMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

