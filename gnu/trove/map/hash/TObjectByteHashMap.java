/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.THash;
import gnu.trove.impl.hash.TObjectHash;
import gnu.trove.iterator.TByteIterator;
import gnu.trove.iterator.TObjectByteIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TObjectByteProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TObjectByteHashMap<K>
extends TObjectHash<K>
implements TObjectByteMap<K>,
Externalizable {
    static final long serialVersionUID = 1L;
    private final TObjectByteProcedure<K> PUT_ALL_PROC = new TObjectByteProcedure<K>(){

        @Override
        public boolean execute(K key, byte value) {
            TObjectByteHashMap.this.put(key, value);
            return true;
        }
    };
    protected transient byte[] _values;
    protected byte no_entry_value;

    public TObjectByteHashMap() {
        this.no_entry_value = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
    }

    public TObjectByteHashMap(int initialCapacity) {
        super(initialCapacity);
        this.no_entry_value = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
    }

    public TObjectByteHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.no_entry_value = Constants.DEFAULT_BYTE_NO_ENTRY_VALUE;
    }

    public TObjectByteHashMap(int initialCapacity, float loadFactor, byte noEntryValue) {
        super(initialCapacity, loadFactor);
        this.no_entry_value = noEntryValue;
        if (this.no_entry_value != 0) {
            Arrays.fill(this._values, this.no_entry_value);
        }
    }

    public TObjectByteHashMap(TObjectByteMap<? extends K> map) {
        this(map.size(), 0.5f, map.getNoEntryValue());
        if (map instanceof TObjectByteHashMap) {
            TObjectByteHashMap hashmap = (TObjectByteHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_value = hashmap.no_entry_value;
            if (this.no_entry_value != 0) {
                Arrays.fill(this._values, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.putAll(map);
    }

    @Override
    public int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new byte[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        Object[] oldKeys = this._set;
        byte[] oldVals = this._values;
        this._set = new Object[newCapacity];
        Arrays.fill(this._set, FREE);
        this._values = new byte[newCapacity];
        Arrays.fill(this._values, this.no_entry_value);
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldKeys[i2] == FREE || oldKeys[i2] == REMOVED) continue;
            Object o2 = oldKeys[i2];
            int index = this.insertKey(o2);
            if (index < 0) {
                this.throwObjectContractViolation(this._set[-index - 1], o2);
            }
            this._set[index] = o2;
            this._values[index] = oldVals[i2];
        }
    }

    @Override
    public byte getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.contains(key);
    }

    @Override
    public boolean containsValue(byte val) {
        Object[] keys = this._set;
        byte[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public byte get(Object key) {
        int index = this.index(key);
        return index < 0 ? this.no_entry_value : this._values[index];
    }

    @Override
    public byte put(K key, byte value) {
        int index = this.insertKey(key);
        return this.doPut(value, index);
    }

    @Override
    public byte putIfAbsent(K key, byte value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(value, index);
    }

    private byte doPut(byte value, int index) {
        byte previous = this.no_entry_value;
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
    public byte remove(Object key) {
        byte prev = this.no_entry_value;
        int index = this.index(key);
        if (index >= 0) {
            prev = this._values[index];
            this.removeAt(index);
        }
        return prev;
    }

    @Override
    protected void removeAt(int index) {
        this._values[index] = this.no_entry_value;
        super.removeAt(index);
    }

    @Override
    public void putAll(Map<? extends K, ? extends Byte> map) {
        Set<Map.Entry<K, Byte>> set = map.entrySet();
        for (Map.Entry<K, Byte> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TObjectByteMap<? extends K> map) {
        map.forEachEntry(this.PUT_ALL_PROC);
    }

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(this._set, 0, this._set.length, FREE);
        Arrays.fill(this._values, 0, this._values.length, this.no_entry_value);
    }

    @Override
    public Set<K> keySet() {
        return new KeyView();
    }

    @Override
    public Object[] keys() {
        Object[] keys = new Object[this.size()];
        Object[] k2 = this._set;
        int i2 = k2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (k2[i2] == FREE || k2[i2] == REMOVED) continue;
            keys[j2++] = k2[i2];
        }
        return keys;
    }

    @Override
    public K[] keys(K[] a2) {
        int size = this.size();
        if (a2.length < size) {
            a2 = (Object[])Array.newInstance(a2.getClass().getComponentType(), size);
        }
        Object[] k2 = this._set;
        int i2 = k2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (k2[i2] == FREE || k2[i2] == REMOVED) continue;
            a2[j2++] = k2[i2];
        }
        return a2;
    }

    @Override
    public TByteCollection valueCollection() {
        return new TByteValueCollection();
    }

    @Override
    public byte[] values() {
        byte[] vals = new byte[this.size()];
        byte[] v2 = this._values;
        Object[] keys = this._set;
        int i2 = v2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED) continue;
            vals[j2++] = v2[i2];
        }
        return vals;
    }

    @Override
    public byte[] values(byte[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new byte[size];
        }
        byte[] v2 = this._values;
        Object[] keys = this._set;
        int i2 = v2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED) continue;
            array[j2++] = v2[i2];
        }
        if (array.length > size) {
            array[size] = this.no_entry_value;
        }
        return array;
    }

    @Override
    public TObjectByteIterator<K> iterator() {
        return new TObjectByteHashIterator(this);
    }

    @Override
    public boolean increment(K key) {
        return this.adjustValue(key, (byte)1);
    }

    @Override
    public boolean adjustValue(K key, byte amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = (byte)(this._values[n2] + amount);
        return true;
    }

    @Override
    public byte adjustOrPutValue(K key, byte adjust_amount, byte put_amount) {
        boolean isNewMapping;
        byte newValue;
        int index = this.insertKey(key);
        if (index < 0) {
            int n2 = index = -index - 1;
            byte by = (byte)(this._values[n2] + adjust_amount);
            this._values[n2] = by;
            newValue = by;
            isNewMapping = false;
        } else {
            newValue = this._values[index] = put_amount;
            isNewMapping = true;
        }
        if (isNewMapping) {
            this.postInsertHook(this.consumeFreeSlot);
        }
        return newValue;
    }

    @Override
    public boolean forEachKey(TObjectProcedure<? super K> procedure) {
        return this.forEach(procedure);
    }

    @Override
    public boolean forEachValue(TByteProcedure procedure) {
        Object[] keys = this._set;
        byte[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TObjectByteProcedure<? super K> procedure) {
        Object[] keys = this._set;
        byte[] values = this._values;
        int i2 = keys.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(keys[i2], values[i2])) continue;
            return false;
        }
        return true;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainEntries(TObjectByteProcedure<? super K> procedure) {
        boolean modified = false;
        Object[] keys = this._set;
        byte[] values = this._values;
        this.tempDisableAutoCompaction();
        try {
            int i2 = keys.length;
            while (i2-- > 0) {
                if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(keys[i2], values[i2])) continue;
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
    public void transformValues(TByteFunction function) {
        Object[] keys = this._set;
        byte[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == null || keys[i2] == REMOVED) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TObjectByteMap)) {
            return false;
        }
        TObjectByteMap that = (TObjectByteMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        try {
            TObjectByteIterator<K> iter = this.iterator();
            while (iter.hasNext()) {
                iter.advance();
                K key = iter.key();
                byte value = iter.value();
                if (!(value == this.no_entry_value ? that.get(key) != that.getNoEntryValue() || !that.containsKey(key) : value != that.get(key))) continue;
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
        Object[] keys = this._set;
        byte[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED) continue;
            hashcode += HashFunctions.hash(values[i2]) ^ (keys[i2] == null ? 0 : keys[i2].hashCode());
        }
        return hashcode;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        super.writeExternal(out);
        out.writeByte(this.no_entry_value);
        out.writeInt(this._size);
        int i2 = this._set.length;
        while (i2-- > 0) {
            if (this._set[i2] == REMOVED || this._set[i2] == FREE) continue;
            out.writeObject(this._set[i2]);
            out.writeByte(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        this.no_entry_value = in2.readByte();
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            Object key = in2.readObject();
            byte val = in2.readByte();
            this.put(key, val);
        }
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TObjectByteProcedure<K>(){
            private boolean first = true;

            @Override
            public boolean execute(K key, byte value) {
                if (this.first) {
                    this.first = false;
                } else {
                    buf.append(",");
                }
                buf.append(key).append("=").append(value);
                return true;
            }
        });
        buf.append("}");
        return buf.toString();
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TObjectByteHashIterator<K>
    extends TObjectHashIterator<K>
    implements TObjectByteIterator<K> {
        private final TObjectByteHashMap<K> _map;

        public TObjectByteHashIterator(TObjectByteHashMap<K> map) {
            super(map);
            this._map = map;
        }

        @Override
        public void advance() {
            this.moveToNextIndex();
        }

        @Override
        public K key() {
            return (K)this._map._set[this._index];
        }

        @Override
        public byte value() {
            return this._map._values[this._index];
        }

        @Override
        public byte setValue(byte val) {
            byte old = this.value();
            this._map._values[this._index] = val;
            return old;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TByteValueCollection
    implements TByteCollection {
        TByteValueCollection() {
        }

        @Override
        public TByteIterator iterator() {
            return new TObjectByteValueHashIterator();
        }

        @Override
        public byte getNoEntryValue() {
            return TObjectByteHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TObjectByteHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TObjectByteHashMap.this._size;
        }

        @Override
        public boolean contains(byte entry) {
            return TObjectByteHashMap.this.containsValue(entry);
        }

        @Override
        public byte[] toArray() {
            return TObjectByteHashMap.this.values();
        }

        @Override
        public byte[] toArray(byte[] dest) {
            return TObjectByteHashMap.this.values(dest);
        }

        @Override
        public boolean add(byte entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(byte entry) {
            byte[] values = TObjectByteHashMap.this._values;
            Object[] set = TObjectByteHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || entry != values[i2]) continue;
                TObjectByteHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Byte) {
                    byte ele = (Byte)element;
                    if (TObjectByteHashMap.this.containsValue(ele)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TByteCollection collection) {
            TByteIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TObjectByteHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(byte[] array) {
            for (byte element : array) {
                if (TObjectByteHashMap.this.containsValue(element)) continue;
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
            byte[] values = TObjectByteHashMap.this._values;
            Object[] set = TObjectByteHashMap.this._set;
            int i2 = set.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TObjectByteHashMap.this.removeAt(i2);
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
            if (this == collection) {
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
            TObjectByteHashMap.this.clear();
        }

        @Override
        public boolean forEach(TByteProcedure procedure) {
            return TObjectByteHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TObjectByteHashMap.this.forEachValue(new TByteProcedure(){
                private boolean first = true;

                public boolean execute(byte value) {
                    if (this.first) {
                        this.first = false;
                    } else {
                        buf.append(", ");
                    }
                    buf.append(value);
                    return true;
                }
            });
            buf.append("}");
            return buf.toString();
        }

        class TObjectByteValueHashIterator
        implements TByteIterator {
            protected THash _hash;
            protected int _expectedSize;
            protected int _index;

            TObjectByteValueHashIterator() {
                this._hash = TObjectByteHashMap.this;
                this._expectedSize = this._hash.size();
                this._index = this._hash.capacity();
            }

            public boolean hasNext() {
                return this.nextIndex() >= 0;
            }

            public byte next() {
                this.moveToNextIndex();
                return TObjectByteHashMap.this._values[this._index];
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void remove() {
                if (this._expectedSize != this._hash.size()) {
                    throw new ConcurrentModificationException();
                }
                try {
                    this._hash.tempDisableAutoCompaction();
                    TObjectByteHashMap.this.removeAt(this._index);
                }
                finally {
                    this._hash.reenableAutoCompaction(false);
                }
                --this._expectedSize;
            }

            protected final void moveToNextIndex() {
                this._index = this.nextIndex();
                if (this._index < 0) {
                    throw new NoSuchElementException();
                }
            }

            protected final int nextIndex() {
                if (this._expectedSize != this._hash.size()) {
                    throw new ConcurrentModificationException();
                }
                Object[] set = TObjectByteHashMap.this._set;
                int i2 = this._index;
                while (i2-- > 0 && (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED)) {
                }
                return i2;
            }
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
            TObjectByteHashMap.this.clear();
        }

        @Override
        public boolean add(E obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TObjectByteHashMap.this.size();
        }

        @Override
        public Object[] toArray() {
            Object[] result = new Object[this.size()];
            Iterator e2 = this.iterator();
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
            Iterator it2 = this.iterator();
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
            return TObjectByteHashMap.this.isEmpty();
        }

        @Override
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean changed = false;
            Iterator i2 = this.iterator();
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
    protected class KeyView
    extends MapBackedView<K> {
        protected KeyView() {
        }

        @Override
        public Iterator<K> iterator() {
            return new TObjectHashIterator(TObjectByteHashMap.this);
        }

        @Override
        public boolean removeElement(K key) {
            return TObjectByteHashMap.this.no_entry_value != TObjectByteHashMap.this.remove(key);
        }

        @Override
        public boolean containsElement(K key) {
            return TObjectByteHashMap.this.contains(key);
        }
    }
}

