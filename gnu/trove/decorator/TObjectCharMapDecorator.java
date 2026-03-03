/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TObjectCharIterator;
import gnu.trove.map.TObjectCharMap;
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
public class TObjectCharMapDecorator<K>
extends AbstractMap<K, Character>
implements Map<K, Character>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TObjectCharMap<K> _map;

    public TObjectCharMapDecorator() {
    }

    public TObjectCharMapDecorator(TObjectCharMap<K> map) {
        this._map = map;
    }

    public TObjectCharMap<K> getMap() {
        return this._map;
    }

    @Override
    public Character put(K key, Character value) {
        if (value == null) {
            return this.wrapValue(this._map.put(key, this._map.getNoEntryValue()));
        }
        return this.wrapValue(this._map.put(key, this.unwrapValue(value)));
    }

    @Override
    public Character get(Object key) {
        char v2 = this._map.get(key);
        if (v2 == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(v2);
    }

    @Override
    public void clear() {
        this._map.clear();
    }

    @Override
    public Character remove(Object key) {
        char v2 = this._map.remove(key);
        if (v2 == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(v2);
    }

    @Override
    public Set<Map.Entry<K, Character>> entrySet() {
        return new AbstractSet<Map.Entry<K, Character>>(){

            @Override
            public int size() {
                return TObjectCharMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TObjectCharMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TObjectCharMapDecorator.this.containsKey(k2) && TObjectCharMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<K, Character>> iterator() {
                return new Iterator<Map.Entry<K, Character>>(){
                    private final TObjectCharIterator<K> it;
                    {
                        this.it = TObjectCharMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<K, Character> next() {
                        this.it.advance();
                        final Object key = this.it.key();
                        final Character v2 = TObjectCharMapDecorator.this.wrapValue(this.it.value());
                        return new Map.Entry<K, Character>(){
                            private Character val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public K getKey() {
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
                                return TObjectCharMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<K, Character> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Object key = ((Map.Entry)o2).getKey();
                    TObjectCharMapDecorator.this._map.remove(key);
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<K, Character>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TObjectCharMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Character && this._map.containsValue(this.unwrapValue(val));
    }

    @Override
    public boolean containsKey(Object key) {
        return this._map.containsKey(key);
    }

    @Override
    public int size() {
        return this._map.size();
    }

    @Override
    public boolean isEmpty() {
        return this._map.size() == 0;
    }

    @Override
    public void putAll(Map<? extends K, ? extends Character> map) {
        Iterator<Map.Entry<K, Character>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<K, Character> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
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
        this._map = (TObjectCharMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

