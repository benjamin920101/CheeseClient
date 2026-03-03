/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TFloatCharIterator;
import gnu.trove.map.TFloatCharMap;
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
public class TFloatCharMapDecorator
extends AbstractMap<Float, Character>
implements Map<Float, Character>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TFloatCharMap _map;

    public TFloatCharMapDecorator() {
    }

    public TFloatCharMapDecorator(TFloatCharMap map) {
        this._map = map;
    }

    public TFloatCharMap getMap() {
        return this._map;
    }

    @Override
    public Character put(Float key, Character value) {
        char v2;
        float k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        float k2;
        if (key != null) {
            if (!(key instanceof Float)) return null;
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
        float k2;
        if (key != null) {
            if (!(key instanceof Float)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        char v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Float, Character>> entrySet() {
        return new AbstractSet<Map.Entry<Float, Character>>(){

            @Override
            public int size() {
                return TFloatCharMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TFloatCharMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TFloatCharMapDecorator.this.containsKey(k2) && TFloatCharMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Float, Character>> iterator() {
                return new Iterator<Map.Entry<Float, Character>>(){
                    private final TFloatCharIterator it;
                    {
                        this.it = TFloatCharMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Float, Character> next() {
                        this.it.advance();
                        float ik2 = this.it.key();
                        final Float key = ik2 == TFloatCharMapDecorator.this._map.getNoEntryKey() ? null : TFloatCharMapDecorator.this.wrapKey(ik2);
                        char iv2 = this.it.value();
                        final Character v2 = iv2 == TFloatCharMapDecorator.this._map.getNoEntryValue() ? null : TFloatCharMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Float, Character>(){
                            private Character val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Float getKey() {
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
                                return TFloatCharMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Float, Character> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Float key = (Float)((Map.Entry)o2).getKey();
                    TFloatCharMapDecorator.this._map.remove(TFloatCharMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Float, Character>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TFloatCharMapDecorator.this.clear();
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
        return key instanceof Float && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Float, ? extends Character> map) {
        Iterator<Map.Entry<? extends Float, ? extends Character>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Float, ? extends Character> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Float wrapKey(float k2) {
        return Float.valueOf(k2);
    }

    protected float unwrapKey(Object key) {
        return ((Float)key).floatValue();
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
        this._map = (TFloatCharMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

