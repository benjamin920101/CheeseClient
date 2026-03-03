/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TByteShortIterator;
import gnu.trove.map.TByteShortMap;
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
public class TByteShortMapDecorator
extends AbstractMap<Byte, Short>
implements Map<Byte, Short>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TByteShortMap _map;

    public TByteShortMapDecorator() {
    }

    public TByteShortMapDecorator(TByteShortMap map) {
        this._map = map;
    }

    public TByteShortMap getMap() {
        return this._map;
    }

    @Override
    public Short put(Byte key, Short value) {
        short v2;
        byte k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        short retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Short get(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        short v2 = this._map.get(k2);
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
    public Short remove(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        short v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Byte, Short>> entrySet() {
        return new AbstractSet<Map.Entry<Byte, Short>>(){

            @Override
            public int size() {
                return TByteShortMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TByteShortMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TByteShortMapDecorator.this.containsKey(k2) && TByteShortMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Byte, Short>> iterator() {
                return new Iterator<Map.Entry<Byte, Short>>(){
                    private final TByteShortIterator it;
                    {
                        this.it = TByteShortMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Byte, Short> next() {
                        this.it.advance();
                        byte ik2 = this.it.key();
                        final Byte key = ik2 == TByteShortMapDecorator.this._map.getNoEntryKey() ? null : TByteShortMapDecorator.this.wrapKey(ik2);
                        short iv2 = this.it.value();
                        final Short v2 = iv2 == TByteShortMapDecorator.this._map.getNoEntryValue() ? null : TByteShortMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Byte, Short>(){
                            private Short val;
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
                            public Short getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Short setValue(Short value) {
                                this.val = value;
                                return TByteShortMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Byte, Short> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Byte key = (Byte)((Map.Entry)o2).getKey();
                    TByteShortMapDecorator.this._map.remove(TByteShortMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Byte, Short>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TByteShortMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Short && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends Byte, ? extends Short> map) {
        Iterator<Map.Entry<? extends Byte, ? extends Short>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Byte, ? extends Short> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Byte wrapKey(byte k2) {
        return k2;
    }

    protected byte unwrapKey(Object key) {
        return (Byte)key;
    }

    protected Short wrapValue(short k2) {
        return k2;
    }

    protected short unwrapValue(Object value) {
        return (Short)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TByteShortMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

