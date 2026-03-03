/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TByteObjectIterator;
import gnu.trove.map.TByteObjectMap;
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
public class TByteObjectMapDecorator<V>
extends AbstractMap<Byte, V>
implements Map<Byte, V>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TByteObjectMap<V> _map;

    public TByteObjectMapDecorator() {
    }

    public TByteObjectMapDecorator(TByteObjectMap<V> map) {
        this._map = map;
    }

    public TByteObjectMap<V> getMap() {
        return this._map;
    }

    @Override
    public V put(Byte key, V value) {
        byte k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        return this._map.put(k2, value);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public V get(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey((Byte)key);
            return this._map.get(k2);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        return this._map.get(k2);
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
    public V remove(Object key) {
        byte k2;
        if (key != null) {
            if (!(key instanceof Byte)) return null;
            k2 = this.unwrapKey((Byte)key);
            return this._map.remove(k2);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        return this._map.remove(k2);
    }

    @Override
    public Set<Map.Entry<Byte, V>> entrySet() {
        return new AbstractSet<Map.Entry<Byte, V>>(){

            @Override
            public int size() {
                return TByteObjectMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TByteObjectMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TByteObjectMapDecorator.this.containsKey(k2) && TByteObjectMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Byte, V>> iterator() {
                return new Iterator<Map.Entry<Byte, V>>(){
                    private final TByteObjectIterator<V> it;
                    {
                        this.it = TByteObjectMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Byte, V> next() {
                        this.it.advance();
                        byte k2 = this.it.key();
                        final Byte key = k2 == TByteObjectMapDecorator.this._map.getNoEntryKey() ? null : TByteObjectMapDecorator.this.wrapKey(k2);
                        final Object v2 = this.it.value();
                        return new Map.Entry<Byte, V>(){
                            private V val;
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
                            public V getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public V setValue(V value) {
                                this.val = value;
                                return TByteObjectMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Byte, V> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Byte key = (Byte)((Map.Entry)o2).getKey();
                    TByteObjectMapDecorator.this._map.remove(TByteObjectMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Byte, V>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TByteObjectMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return this._map.containsValue(val);
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return this._map.containsKey(this._map.getNoEntryKey());
        }
        return key instanceof Byte && this._map.containsKey((Byte)key);
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
    public void putAll(Map<? extends Byte, ? extends V> map) {
        Iterator<Map.Entry<Byte, V>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<Byte, V> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Byte wrapKey(byte k2) {
        return k2;
    }

    protected byte unwrapKey(Byte key) {
        return key;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TByteObjectMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

