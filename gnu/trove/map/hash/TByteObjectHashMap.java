/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TByteCollection;
import gnu.trove.function.TObjectFunction;
import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TByteHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.iterator.TByteIterator;
import gnu.trove.iterator.TByteObjectIterator;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.procedure.TByteObjectProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TByteSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TByteObjectHashMap<V>
extends TByteHash
implements TByteObjectMap<V>,
Externalizable {
    static final long serialVersionUID = 1L;
    private final TByteObjectProcedure<V> PUT_ALL_PROC = new TByteObjectProcedure<V>(){

        @Override
        public boolean execute(byte key, V value) {
            TByteObjectHashMap.this.put(key, value);
            return true;
        }
    };
    protected transient V[] _values;
    protected byte no_entry_key;

    public TByteObjectHashMap() {
    }

    public TByteObjectHashMap(int initialCapacity) {
        super(initialCapacity);
        this.no_entry_key = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
    }

    public TByteObjectHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.no_entry_key = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
    }

    public TByteObjectHashMap(int initialCapacity, float loadFactor, byte noEntryKey) {
        super(initialCapacity, loadFactor);
        this.no_entry_key = noEntryKey;
    }

    public TByteObjectHashMap(TByteObjectMap<? extends V> map) {
        this(map.size(), 0.5f, map.getNoEntryKey());
        this.putAll(map);
    }

    @Override
    protected int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new Object[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        byte[] oldKeys = this._set;
        V[] oldVals = this._values;
        byte[] oldStates = this._states;
        this._set = new byte[newCapacity];
        this._values = new Object[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            byte o2 = oldKeys[i2];
            int index = this.insertKey(o2);
            this._values[index] = oldVals[i2];
        }
    }

    @Override
    public byte getNoEntryKey() {
        return this.no_entry_key;
    }

    @Override
    public boolean containsKey(byte key) {
        return this.contains(key);
    }

    @Override
    public boolean containsValue(Object val) {
        byte[] states = this._states;
        V[] vals = this._values;
        if (null == val) {
            int i2 = vals.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || null != vals[i2]) continue;
                return true;
            }
        } else {
            int i3 = vals.length;
            while (i3-- > 0) {
                if (states[i3] != 1 || val != vals[i3] && !val.equals(vals[i3])) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(byte key) {
        int index = this.index(key);
        return index < 0 ? null : (V)this._values[index];
    }

    @Override
    public V put(byte key, V value) {
        int index = this.insertKey(key);
        return this.doPut(value, index);
    }

    @Override
    public V putIfAbsent(byte key, V value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(value, index);
    }

    private V doPut(V value, int index) {
        V previous = null;
        boolean isNewMapping = true;
        if (index < 0) {
            index = -index - 1;
            previous = this._values[index];
            isNewMapping = false;
        }
        this._values[index] = value;
        if (isNewMapping) {
            this.postInsertHook(this.consumeFreeSlot);
        }
        return previous;
    }

    @Override
    public V remove(byte key) {
        V prev = null;
        int index = this.index(key);
        if (index >= 0) {
            prev = this._values[index];
            this.removeAt(index);
        }
        return prev;
    }

    @Override
    protected void removeAt(int index) {
        this._values[index] = null;
        super.removeAt(index);
    }

    @Override
    public void putAll(Map<? extends Byte, ? extends V> map) {
        Set<Map.Entry<Byte, V>> set = map.entrySet();
        for (Map.Entry<Byte, V> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TByteObjectMap<? extends V> map) {
        map.forEachEntry(this.PUT_ALL_PROC);
    }

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(this._set, 0, this._set.length, this.no_entry_key);
        Arrays.fill(this._states, 0, this._states.length, (byte)0);
        Arrays.fill(this._values, 0, this._values.length, null);
    }

    @Override
    public TByteSet keySet() {
        return new KeyView();
    }

    @Override
    public byte[] keys() {
        byte[] keys = new byte[this.size()];
        byte[] k2 = this._set;
        byte[] states = this._states;
        int i2 = k2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            keys[j2++] = k2[i2];
        }
        return keys;
    }

    @Override
    public byte[] keys(byte[] dest) {
        if (dest.length < this._size) {
            dest = new byte[this._size];
        }
        byte[] k2 = this._set;
        byte[] states = this._states;
        int i2 = k2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            dest[j2++] = k2[i2];
        }
        return dest;
    }

    @Override
    public Collection<V> valueCollection() {
        return new ValueView();
    }

    @Override
    public Object[] values() {
        Object[] vals = new Object[this.size()];
        V[] v2 = this._values;
        byte[] states = this._states;
        int i2 = v2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            vals[j2++] = v2[i2];
        }
        return vals;
    }

    @Override
    public V[] values(V[] dest) {
        if (dest.length < this._size) {
            dest = (Object[])Array.newInstance(dest.getClass().getComponentType(), this._size);
        }
        V[] v2 = this._values;
        byte[] states = this._states;
        int i2 = v2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            dest[j2++] = v2[i2];
        }
        return dest;
    }

    @Override
    public TByteObjectIterator<V> iterator() {
        return new TByteObjectHashIterator(this);
    }

    @Override
    public boolean forEachKey(TByteProcedure procedure) {
        return this.forEach(procedure);
    }

    @Override
    public boolean forEachValue(TObjectProcedure<? super V> procedure) {
        byte[] states = this._states;
        V[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TByteObjectProcedure<? super V> procedure) {
        byte[] states = this._states;
        byte[] keys = this._set;
        V[] values = this._values;
        int i2 = keys.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(keys[i2], values[i2])) continue;
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainEntries(TByteObjectProcedure<? super V> procedure) {
        boolean modified = false;
        byte[] states = this._states;
        byte[] keys = this._set;
        V[] values = this._values;
        this.tempDisableAutoCompaction();
        try {
            int i2 = keys.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || procedure.execute(keys[i2], values[i2])) continue;
                this.removeAt(i2);
                modified = true;
            }
        }
        finally {
            this.reenableAutoCompaction(true);
        }
        return modified;
    }

    @Override
    public void transformValues(TObjectFunction<V, V> function) {
        byte[] states = this._states;
        V[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TByteObjectMap)) {
            return false;
        }
        TByteObjectMap that = (TByteObjectMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        try {
            TByteObjectIterator<V> iter = this.iterator();
            while (iter.hasNext()) {
                iter.advance();
                byte key = iter.key();
                V value = iter.value();
                if (!(value == null ? that.get(key) != null || !that.containsKey(key) : !value.equals(that.get(key)))) continue;
                return false;
            }
        }
        catch (ClassCastException classCastException) {
            // empty catch block
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        V[] values = this._values;
        byte[] states = this._states;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            hashcode += HashFunctions.hash(this._set[i2]) ^ (values[i2] == null ? 0 : values[i2].hashCode());
        }
        return hashcode;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        super.writeExternal(out);
        out.writeByte(this.no_entry_key);
        out.writeInt(this._size);
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            out.writeByte(this._set[i2]);
            out.writeObject(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        this.no_entry_key = in2.readByte();
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            byte key = in2.readByte();
            Object val = in2.readObject();
            this.put(key, val);
        }
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TByteObjectProcedure<V>(){
            private boolean first = true;

            @Override
            public boolean execute(byte key, Object value) {
                if (this.first) {
                    this.first = false;
                } else {
                    buf.append(",");
                }
                buf.append(key);
                buf.append("=");
                buf.append(value);
                return true;
            }
        });
        buf.append("}");
        return buf.toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TByteObjectHashIterator<V>
    extends THashPrimitiveIterator
    implements TByteObjectIterator<V> {
        private final TByteObjectHashMap<V> _map;

        public TByteObjectHashIterator(TByteObjectHashMap<V> map) {
            super(map);
            this._map = map;
        }

        @Override
        public void advance() {
            this.moveToNextIndex();
        }

        @Override
        public byte key() {
            return this._map._set[this._index];
        }

        @Override
        public V value() {
            return this._map._values[this._index];
        }

        @Override
        public V setValue(V val) {
            V old = this.value();
            this._map._values[this._index] = val;
            return old;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private abstract class MapBackedView<E>
    extends AbstractSet<E>
    implements Set<E>,
    Iterable<E> {
        private MapBackedView() {
        }

        @Override
        public abstract Iterator<E> iterator();

        public abstract boolean removeElement(E var1);

        public abstract boolean containsElement(E var1);

        @Override
        public boolean contains(Object key) {
            return this.containsElement(key);
        }

        @Override
        public boolean remove(Object o2) {
            return this.removeElement(o2);
        }

        @Override
        public void clear() {
            TByteObjectHashMap.this.clear();
        }

        @Override
        public boolean add(E obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TByteObjectHashMap.this.size();
        }

        @Override
        public Object[] toArray() {
            Object[] result = new Object[this.size()];
            Iterator<E> e2 = this.iterator();
            int i2 = 0;
            while (e2.hasNext()) {
                result[i2] = e2.next();
                ++i2;
            }
            return result;
        }

        @Override
        public <T> T[] toArray(T[] a2) {
            int size = this.size();
            if (a2.length < size) {
                a2 = (Object[])Array.newInstance(a2.getClass().getComponentType(), size);
            }
            Iterator<E> it2 = this.iterator();
            T[] result = a2;
            for (int i2 = 0; i2 < size; ++i2) {
                result[i2] = it2.next();
            }
            if (a2.length > size) {
                a2[size] = null;
            }
            return a2;
        }

        @Override
        public boolean isEmpty() {
            return TByteObjectHashMap.this.isEmpty();
        }

        @Override
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean changed = false;
            Iterator<E> i2 = this.iterator();
            while (i2.hasNext()) {
                if (collection.contains(i2.next())) continue;
                i2.remove();
                changed = true;
            }
            return changed;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class ValueView
    extends MapBackedView<V> {
        protected ValueView() {
        }

        @Override
        public Iterator<V> iterator() {
            return new TByteObjectValueHashIterator(TByteObjectHashMap.this){

                @Override
                protected V objectAtIndex(int index) {
                    return TByteObjectHashMap.this._values[index];
                }
            };
        }

        @Override
        public boolean containsElement(V value) {
            return TByteObjectHashMap.this.containsValue(value);
        }

        @Override
        public boolean removeElement(V value) {
            V[] values = TByteObjectHashMap.this._values;
            byte[] states = TByteObjectHashMap.this._states;
            int i2 = values.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || value != values[i2] && (null == values[i2] || !values[i2].equals(value))) continue;
                TByteObjectHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        /*
         * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
         */
        class TByteObjectValueHashIterator
        extends THashPrimitiveIterator
        implements Iterator<V> {
            protected final TByteObjectHashMap _map;

            public TByteObjectValueHashIterator(TByteObjectHashMap map) {
                super(map);
                this._map = map;
            }

            protected V objectAtIndex(int index) {
                byte[] states = TByteObjectHashMap.this._states;
                Object value = this._map._values[index];
                if (states[index] != 1) {
                    return null;
                }
                return value;
            }

            @Override
            public V next() {
                this.moveToNextIndex();
                return this._map._values[this._index];
            }
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class KeyView
    implements TByteSet {
        KeyView() {
        }

        @Override
        public byte getNoEntryValue() {
            return TByteObjectHashMap.this.no_entry_key;
        }

        @Override
        public int size() {
            return TByteObjectHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return TByteObjectHashMap.this._size == 0;
        }

        @Override
        public boolean contains(byte entry) {
            return TByteObjectHashMap.this.containsKey(entry);
        }

        @Override
        public TByteIterator iterator() {
            return new TByteHashIterator(TByteObjectHashMap.this);
        }

        @Override
        public byte[] toArray() {
            return TByteObjectHashMap.this.keys();
        }

        @Override
        public byte[] toArray(byte[] dest) {
            return TByteObjectHashMap.this.keys(dest);
        }

        @Override
        public boolean add(byte entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(byte entry) {
            return null != TByteObjectHashMap.this.remove(entry);
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (TByteObjectHashMap.this.containsKey((Byte)element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TByteCollection collection) {
            if (collection == this) {
                return true;
            }
            TByteIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TByteObjectHashMap.this.containsKey(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(byte[] array) {
            for (byte element : array) {
                if (TByteObjectHashMap.this.containsKey(element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Byte> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(TByteCollection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(byte[] array) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean modified = false;
            TByteIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(TByteCollection collection) {
            if (this == collection) {
                return false;
            }
            boolean modified = false;
            TByteIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(byte[] array) {
            boolean changed = false;
            Arrays.sort(array);
            byte[] set = TByteObjectHashMap.this._set;
            byte[] states = TByteObjectHashMap.this._states;
            int i2 = set.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, set[i2]) >= 0) continue;
                TByteObjectHashMap.this.removeAt(i2);
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            for (Object element : collection) {
                byte c2;
                if (!(element instanceof Byte) || !this.remove(c2 = ((Byte)element).byteValue())) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(TByteCollection collection) {
            if (collection == this) {
                this.clear();
                return true;
            }
            boolean changed = false;
            TByteIterator iter = collection.iterator();
            while (iter.hasNext()) {
                byte element = iter.next();
                if (!this.remove(element)) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(byte[] array) {
            boolean changed = false;
            int i2 = array.length;
            while (i2-- > 0) {
                if (!this.remove(array[i2])) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public void clear() {
            TByteObjectHashMap.this.clear();
        }

        @Override
        public boolean forEach(TByteProcedure procedure) {
            return TByteObjectHashMap.this.forEachKey(procedure);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof TByteSet)) {
                return false;
            }
            TByteSet that = (TByteSet)other;
            if (that.size() != this.size()) {
                return false;
            }
            int i2 = TByteObjectHashMap.this._states.length;
            while (i2-- > 0) {
                if (TByteObjectHashMap.this._states[i2] != 1 || that.contains(TByteObjectHashMap.this._set[i2])) continue;
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            int i2 = TByteObjectHashMap.this._states.length;
            while (i2-- > 0) {
                if (TByteObjectHashMap.this._states[i2] != 1) continue;
                hashcode += HashFunctions.hash(TByteObjectHashMap.this._set[i2]);
            }
            return hashcode;
        }

        public String toString() {
            StringBuilder buf = new StringBuilder("{");
            boolean first = true;
            int i2 = TByteObjectHashMap.this._states.length;
            while (i2-- > 0) {
                if (TByteObjectHashMap.this._states[i2] != 1) continue;
                if (first) {
                    first = false;
                } else {
                    buf.append(",");
                }
                buf.append(TByteObjectHashMap.this._set[i2]);
            }
            return buf.toString();
        }

        class TByteHashIterator
        extends THashPrimitiveIterator
        implements TByteIterator {
            private final TByteHash _hash;

            public TByteHashIterator(TByteHash hash) {
                super(hash);
                this._hash = hash;
            }

            public byte next() {
                this.moveToNextIndex();
                return this._hash._set[this._index];
            }
        }
    }
}

