/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TDoubleCharIterator;
import gnu.trove.map.TDoubleCharMap;
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
public class TDoubleCharMapDecorator
extends AbstractMap<Double, Character>
implements Map<Double, Character>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TDoubleCharMap _map;

    public TDoubleCharMapDecorator() {
    }

    public TDoubleCharMapDecorator(TDoubleCharMap map) {
        this._map = map;
    }

    public TDoubleCharMap getMap() {
        return this._map;
    }

    @Override
    public Character put(Double key, Character value) {
        char v2;
        double k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
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
        double k2;
        if (key != null) {
            if (!(key instanceof Double)) return null;
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
        double k2;
        if (key != null) {
            if (!(key instanceof Double)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        char v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Double, Character>> entrySet() {
        return new AbstractSet<Map.Entry<Double, Character>>(){

            @Override
            public int size() {
                return TDoubleCharMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TDoubleCharMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TDoubleCharMapDecorator.this.containsKey(k2) && TDoubleCharMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Double, Character>> iterator() {
                return new Iterator<Map.Entry<Double, Character>>(){
                    private final TDoubleCharIterator it;
                    {
                        this.it = TDoubleCharMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Double, Character> next() {
                        this.it.advance();
                        double ik2 = this.it.key();
                        final Double key = ik2 == TDoubleCharMapDecorator.this._map.getNoEntryKey() ? null : TDoubleCharMapDecorator.this.wrapKey(ik2);
                        char iv2 = this.it.value();
                        final Character v2 = iv2 == TDoubleCharMapDecorator.this._map.getNoEntryValue() ? null : TDoubleCharMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Double, Character>(){
                            private Character val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Double getKey() {
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
                                return TDoubleCharMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Double, Character> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Double key = (Double)((Map.Entry)o2).getKey();
                    TDoubleCharMapDecorator.this._map.remove(TDoubleCharMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Double, Character>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TDoubleCharMapDecorator.this.clear();
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
        return key instanceof Double && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Double, ? extends Character> map) {
        Iterator<Map.Entry<? extends Double, ? extends Character>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Double, ? extends Character> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Double wrapKey(double k2) {
        return k2;
    }

    protected double unwrapKey(Object key) {
        return (Double)key;
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
        this._map = (TDoubleCharMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

