/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TLongDoubleIterator;
import gnu.trove.map.TLongDoubleMap;
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
public class TLongDoubleMapDecorator
extends AbstractMap<Long, Double>
implements Map<Long, Double>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TLongDoubleMap _map;

    public TLongDoubleMapDecorator() {
    }

    public TLongDoubleMapDecorator(TLongDoubleMap map) {
        this._map = map;
    }

    public TLongDoubleMap getMap() {
        return this._map;
    }

    @Override
    public Double put(Long key, Double value) {
        double v2;
        long k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        double retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Double get(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        double v2 = this._map.get(k2);
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
    public Double remove(Object key) {
        long k2;
        if (key != null) {
            if (!(key instanceof Long)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        double v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Long, Double>> entrySet() {
        return new AbstractSet<Map.Entry<Long, Double>>(){

            @Override
            public int size() {
                return TLongDoubleMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TLongDoubleMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TLongDoubleMapDecorator.this.containsKey(k2) && TLongDoubleMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Long, Double>> iterator() {
                return new Iterator<Map.Entry<Long, Double>>(){
                    private final TLongDoubleIterator it;
                    {
                        this.it = TLongDoubleMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Long, Double> next() {
                        this.it.advance();
                        long ik2 = this.it.key();
                        final Long key = ik2 == TLongDoubleMapDecorator.this._map.getNoEntryKey() ? null : TLongDoubleMapDecorator.this.wrapKey(ik2);
                        double iv2 = this.it.value();
                        final Double v2 = iv2 == TLongDoubleMapDecorator.this._map.getNoEntryValue() ? null : TLongDoubleMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Long, Double>(){
                            private Double val;
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
                                return TLongDoubleMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Long, Double> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Long key = (Long)((Map.Entry)o2).getKey();
                    TLongDoubleMapDecorator.this._map.remove(TLongDoubleMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Long, Double>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TLongDoubleMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Double && this._map.containsValue(this.unwrapValue(val));
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
    public void putAll(Map<? extends Long, ? extends Double> map) {
        Iterator<Map.Entry<? extends Long, ? extends Double>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Long, ? extends Double> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Long wrapKey(long k2) {
        return k2;
    }

    protected long unwrapKey(Object key) {
        return (Long)key;
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
        this._map = (TLongDoubleMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

