/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TObjectDoubleIterator;
import gnu.trove.map.TObjectDoubleMap;
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
public class TObjectDoubleMapDecorator<K>
extends AbstractMap<K, Double>
implements Map<K, Double>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TObjectDoubleMap<K> _map;

    public TObjectDoubleMapDecorator() {
    }

    public TObjectDoubleMapDecorator(TObjectDoubleMap<K> map) {
        this._map = map;
    }

    public TObjectDoubleMap<K> getMap() {
        return this._map;
    }

    @Override
    public Double put(K key, Double value) {
        if (value == null) {
            return this.wrapValue(this._map.put(key, this._map.getNoEntryValue()));
        }
        return this.wrapValue(this._map.put(key, this.unwrapValue(value)));
    }

    @Override
    public Double get(Object key) {
        double v2 = this._map.get(key);
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
    public Double remove(Object key) {
        double v2 = this._map.remove(key);
        if (v2 == this._map.getNoEntryValue()) {
            return null;
        }
        return this.wrapValue(v2);
    }

    @Override
    public Set<Map.Entry<K, Double>> entrySet() {
        return new AbstractSet<Map.Entry<K, Double>>(){

            @Override
            public int size() {
                return TObjectDoubleMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TObjectDoubleMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TObjectDoubleMapDecorator.this.containsKey(k2) && TObjectDoubleMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<K, Double>> iterator() {
                return new Iterator<Map.Entry<K, Double>>(){
                    private final TObjectDoubleIterator<K> it;
                    {
                        this.it = TObjectDoubleMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<K, Double> next() {
                        this.it.advance();
                        final Object key = this.it.key();
                        final Double v2 = TObjectDoubleMapDecorator.this.wrapValue(this.it.value());
                        return new Map.Entry<K, Double>(){
                            private Double val;
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
                            public Double getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Double setValue(Double value) {
                                this.val = value;
                                return TObjectDoubleMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<K, Double> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Object key = ((Map.Entry)o2).getKey();
                    TObjectDoubleMapDecorator.this._map.remove(key);
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<K, Double>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TObjectDoubleMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Double && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends K, ? extends Double> map) {
        Iterator<Map.Entry<K, Double>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<K, Double> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Double wrapValue(double k2) {
        return k2;
    }

    protected double unwrapValue(Object value) {
        return (Double)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TObjectDoubleMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

