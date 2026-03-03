/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TCharByteIterator;
import gnu.trove.map.TCharByteMap;
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
public class TCharByteMapDecorator
extends AbstractMap<Character, Byte>
implements Map<Character, Byte>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TCharByteMap _map;

    public TCharByteMapDecorator() {
    }

    public TCharByteMapDecorator(TCharByteMap map) {
        this._map = map;
    }

    public TCharByteMap getMap() {
        return this._map;
    }

    @Override
    public Byte put(Character key, Byte value) {
        byte v2;
        char k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        char k2;
        if (key != null) {
            if (!(key instanceof Character)) return null;
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
        char k2;
        if (key != null) {
            if (!(key instanceof Character)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        byte v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Character, Byte>> entrySet() {
        return new AbstractSet<Map.Entry<Character, Byte>>(){

            @Override
            public int size() {
                return TCharByteMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TCharByteMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TCharByteMapDecorator.this.containsKey(k2) && TCharByteMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Character, Byte>> iterator() {
                return new Iterator<Map.Entry<Character, Byte>>(){
                    private final TCharByteIterator it;
                    {
                        this.it = TCharByteMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Character, Byte> next() {
                        this.it.advance();
                        char ik2 = this.it.key();
                        final Character key = ik2 == TCharByteMapDecorator.this._map.getNoEntryKey() ? null : TCharByteMapDecorator.this.wrapKey(ik2);
                        byte iv2 = this.it.value();
                        final Byte v2 = iv2 == TCharByteMapDecorator.this._map.getNoEntryValue() ? null : TCharByteMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Character, Byte>(){
                            private Byte val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Character getKey() {
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
                                return TCharByteMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Character, Byte> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Character key = (Character)((Map.Entry)o2).getKey();
                    TCharByteMapDecorator.this._map.remove(TCharByteMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Character, Byte>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TCharByteMapDecorator.this.clear();
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
        return key instanceof Character && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Character, ? extends Byte> map) {
        Iterator<Map.Entry<? extends Character, ? extends Byte>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Character, ? extends Byte> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Character wrapKey(char k2) {
        return Character.valueOf(k2);
    }

    protected char unwrapKey(Object key) {
        return ((Character)key).charValue();
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
        this._map = (TCharByteMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

