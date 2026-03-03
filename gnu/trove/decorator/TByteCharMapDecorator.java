/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TByteCharIterator;
import gnu.trove.map.TByteCharMap;
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
public class TByteCharMapDecorator
extends AbstractMap<Byte, Character>
implements Map<Byte, Character>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TByteCharMap _map;

    public TByteCharMapDecorator() {
    }

    public TByteCharMapDecorator(TByteCharMap map) {
        this._map = map;
    }

    public TByteCharMap getMap() {
        return this._map;
    }

    @Override
    public Character put(Byte key, Character value) {
        char v2;
        byte k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        char retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Character get(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        char v2 = this._map.get(k2);
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
    public Character remove(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        char v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Byte, Character>> entrySet() {
        return new AbstractSet<Map.Entry<Byte, Character>>(){

            @Override
            public int size() {
                return TByteCharMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TByteCharMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TByteCharMapDecorator.this.containsKey(k2) && TByteCharMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Byte, Character>> iterator() {
                return new Iterator<Map.Entry<Byte, Character>>(){
                    private final TByteCharIterator it;
                    {
                        this.it = TByteCharMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Byte, Character> next() {
                        this.it.advance();
                        byte ik2 = this.it.key();
                        final Byte key = ik2 == TByteCharMapDecorator.this._map.getNoEntryKey() ? null : TByteCharMapDecorator.this.wrapKey(ik2);
                        char iv2 = this.it.value();
                        final Character v2 = iv2 == TByteCharMapDecorator.this._map.getNoEntryValue() ? null : TByteCharMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Byte, Character>(){
                            private Character val;
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
                            public Character getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Character setValue(Character value) {
                                this.val = value;
                                return TByteCharMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Byte, Character> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Byte key = (Byte)((Map.Entry)o2).getKey();
                    TByteCharMapDecorator.this._map.remove(TByteCharMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Byte, Character>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TByteCharMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Character && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends Byte, ? extends Character> map) {
        Iterator<Map.Entry<? extends Byte, ? extends Character>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Byte, ? extends Character> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Byte wrapKey(byte k2) {
        return k2;
    }

    protected byte unwrapKey(Object key) {
        return (Byte)key;
    }

    protected Character wrapValue(char k2) {
        return Character.valueOf(k2);
    }

    protected char unwrapValue(Object value) {
        return ((Character)value).charValue();
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TByteCharMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

