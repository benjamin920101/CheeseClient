/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TIntIntIterator;
import gnu.trove.map.TIntIntMap;
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
public class TIntIntMapDecorator
extends AbstractMap<Integer, Integer>
implements Map<Integer, Integer>,
Externalizable,
Cloneable {
    static final long serialVersionUID = 1L;
    protected TIntIntMap _map;

    public TIntIntMapDecorator() {
    }

    public TIntIntMapDecorator(TIntIntMap map) {
        this._map = map;
    }

    public TIntIntMap getMap() {
        return this._map;
    }

    @Override
    public Integer put(Integer key, Integer value) {
        int v2;
        int k2 = key == null ? this._map.getNoEntryKey() : this.unwrapKey(key);
        int retval = this._map.put(k2, v2 = value == null ? this._map.getNoEntryValue() : this.unwrapValue(value));
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
    public Integer get(Object key) {
        int k2;
        if (key != null) {
            if (!(key instanceof Integer)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        int v2 = this._map.get(k2);
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
    public Integer remove(Object key) {
        int k2;
        if (key != null) {
            if (!(key instanceof Integer)) return null;
            k2 = this.unwrapKey(key);
        } else {
            k2 = this._map.getNoEntryKey();
        }
        int v2 = this._map.remove(k2);
        if (v2 != this._map.getNoEntryValue()) return this.wrapValue(v2);
        return null;
    }

    @Override
    public Set<Map.Entry<Integer, Integer>> entrySet() {
        return new AbstractSet<Map.Entry<Integer, Integer>>(){

            @Override
            public int size() {
                return TIntIntMapDecorator.this._map.size();
            }

            @Override
            public boolean isEmpty() {
                return TIntIntMapDecorator.this.isEmpty();
            }

            @Override
            public boolean contains(Object o2) {
                if (o2 instanceof Map.Entry) {
                    Object k2 = ((Map.Entry)o2).getKey();
                    Object v2 = ((Map.Entry)o2).getValue();
                    return TIntIntMapDecorator.this.containsKey(k2) && TIntIntMapDecorator.this.get(k2).equals(v2);
                }
                return false;
            }

            @Override
            public Iterator<Map.Entry<Integer, Integer>> iterator() {
                return new Iterator<Map.Entry<Integer, Integer>>(){
                    private final TIntIntIterator it;
                    {
                        this.it = TIntIntMapDecorator.this._map.iterator();
                    }

                    @Override
                    public Map.Entry<Integer, Integer> next() {
                        this.it.advance();
                        int ik2 = this.it.key();
                        final Integer key = ik2 == TIntIntMapDecorator.this._map.getNoEntryKey() ? null : TIntIntMapDecorator.this.wrapKey(ik2);
                        int iv2 = this.it.value();
                        final Integer v2 = iv2 == TIntIntMapDecorator.this._map.getNoEntryValue() ? null : TIntIntMapDecorator.this.wrapValue(iv2);
                        return new Map.Entry<Integer, Integer>(){
                            private Integer val;
                            {
                                this.val = v2;
                            }

                            @Override
                            public boolean equals(Object o2) {
                                return o2 instanceof Map.Entry && ((Map.Entry)o2).getKey().equals(key) && ((Map.Entry)o2).getValue().equals(this.val);
                            }

                            @Override
                            public Integer getKey() {
                                return key;
                            }

                            @Override
                            public Integer getValue() {
                                return this.val;
                            }

                            @Override
                            public int hashCode() {
                                return key.hashCode() + this.val.hashCode();
                            }

                            @Override
                            public Integer setValue(Integer value) {
                                this.val = value;
                                return TIntIntMapDecorator.this.put(key, value);
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
            public boolean add(Map.Entry<Integer, Integer> o2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o2) {
                boolean modified = false;
                if (this.contains(o2)) {
                    Integer key = (Integer)((Map.Entry)o2).getKey();
                    TIntIntMapDecorator.this._map.remove(TIntIntMapDecorator.this.unwrapKey(key));
                    modified = true;
                }
                return modified;
            }

            @Override
            public boolean addAll(Collection<? extends Map.Entry<Integer, Integer>> c2) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                TIntIntMapDecorator.this.clear();
            }
        };
    }

    @Override
    public boolean containsValue(Object val) {
        return val instanceof Integer && this._map.containsValue(this.unwrapValue(val));
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null) {
            return this._map.containsKey(this._map.getNoEntryKey());
        }
        return key instanceof Integer && this._map.containsKey(this.unwrapKey(key));
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
    public void putAll(Map<? extends Integer, ? extends Integer> map) {
        Iterator<Map.Entry<? extends Integer, ? extends Integer>> it2 = map.entrySet().iterator();
        int i2 = map.size();
        while (i2-- > 0) {
            Map.Entry<? extends Integer, ? extends Integer> e2 = it2.next();
            this.put(e2.getKey(), e2.getValue());
        }
    }

    protected Integer wrapKey(int k2) {
        return k2;
    }

    protected int unwrapKey(Object key) {
        return (Integer)key;
    }

    protected Integer wrapValue(int k2) {
        return k2;
    }

    protected int unwrapValue(Object value) {
        return (Integer)value;
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._map = (TIntIntMap)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._map);
    }
}

