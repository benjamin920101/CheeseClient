/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.THash;
import gnu.trove.impl.hash.TObjectHash;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.iterator.TObjectLongIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.map.TObjectLongMap;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TObjectLongProcedure;
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
public class TObjectLongHashMap<K>
extends TObjectHash<K>
implements TObjectLongMap<K>,
Externalizable {
    static final long serialVersionUID = 1L;
    private final TObjectLongProcedure<K> PUT_ALL_PROC = new TObjectLongProcedure<K>(){

        @Override
        public boolean execute(K key, long value) {
            TObjectLongHashMap.this.put(key, value);
            return true;
        }
    };
    protected transient long[] _values;
    protected long no_entry_value;

    public TObjectLongHashMap() {
        this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
    }

    public TObjectLongHashMap(int initialCapacity) {
        super(initialCapacity);
        this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
    }

    public TObjectLongHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.no_entry_value = Constants.DEFAULT_LONG_NO_ENTRY_VALUE;
    }

    public TObjectLongHashMap(int initialCapacity, float loadFactor, long noEntryValue) {
        super(initialCapacity, loadFactor);
        this.no_entry_value = noEntryValue;
        if (this.no_entry_value != 0L) {
            Arrays.fill(this._values, this.no_entry_value);
        }
    }

    public TObjectLongHashMap(TObjectLongMap<? extends K> map) {
        this(map.size(), 0.5f, map.getNoEntryValue());
        if (map instanceof TObjectLongHashMap) {
            TObjectLongHashMap hashmap = (TObjectLongHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_value = hashmap.no_entry_value;
            if (this.no_entry_value != 0L) {
                Arrays.fill(this._values, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.putAll(map);
    }

    @Override
    public int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new long[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        Object[] oldKeys = this._set;
        long[] oldVals = this._values;
        this._set = new Object[newCapacity];
        Arrays.fill(this._set, FREE);
        this._values = new long[newCapacity];
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
    public long getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.contains(key);
    }

    @Override
    public boolean containsValue(long val) {
        Object[] keys = this._set;
        long[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public long get(Object key) {
        int index = this.index(key);
        return index < 0 ? this.no_entry_value : this._values[index];
    }

    @Override
    public long put(K key, long value) {
        int index = this.insertKey(key);
        return this.doPut(value, index);
    }

    @Override
    public long putIfAbsent(K key, long value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(value, index);
    }

    private long doPut(long value, int index) {
        long previous = this.no_entry_value;
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
    public long remove(Object key) {
        long prev = this.no_entry_value;
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
    public void putAll(Map<? extends K, ? extends Long> map) {
        Set<Map.Entry<K, Long>> set = map.entrySet();
        for (Map.Entry<K, Long> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TObjectLongMap<? extends K> map) {
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
    public TLongCollection valueCollection() {
        return new TLongValueCollection();
    }

    @Override
    public long[] values() {
        long[] vals = new long[this.size()];
        long[] v2 = this._values;
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
    public long[] values(long[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new long[size];
        }
        long[] v2 = this._values;
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
    public TObjectLongIterator<K> iterator() {
        return new TObjectLongHashIterator(this);
    }

    @Override
    public boolean increment(K key) {
        return this.adjustValue(key, 1L);
    }

    @Override
    public boolean adjustValue(K key, long amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = this._values[n2] + amount;
        return true;
    }

    @Override
    public long adjustOrPutValue(K key, long adjust_amount, long put_amount) {
        boolean isNewMapping;
        long newValue;
        int index = this.insertKey(key);
        if (index < 0) {
            int n2 = index = -index - 1;
            long l2 = this._values[n2] + adjust_amount;
            this._values[n2] = l2;
            newValue = l2;
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
    public boolean forEachValue(TLongProcedure procedure) {
        Object[] keys = this._set;
        long[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TObjectLongProcedure<? super K> procedure) {
        Object[] keys = this._set;
        long[] values = this._values;
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
    public boolean retainEntries(TObjectLongProcedure<? super K> procedure) {
        boolean modified = false;
        Object[] keys = this._set;
        long[] values = this._values;
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
    public void transformValues(TLongFunction function) {
        Object[] keys = this._set;
        long[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == null || keys[i2] == REMOVED) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TObjectLongMap)) {
            return false;
        }
        TObjectLongMap that = (TObjectLongMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        try {
            TObjectLongIterator<K> iter = this.iterator();
            while (iter.hasNext()) {
                iter.advance();
                K key = iter.key();
                long value = iter.value();
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
        long[] values = this._values;
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
        out.writeLong(this.no_entry_value);
        out.writeInt(this._size);
        int i2 = this._set.length;
        while (i2-- > 0) {
            if (this._set[i2] == REMOVED || this._set[i2] == FREE) continue;
            out.writeObject(this._set[i2]);
            out.writeLong(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        this.no_entry_value = in2.readLong();
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            Object key = in2.readObject();
            long val = in2.readLong();
            this.put(key, val);
        }
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TObjectLongProcedure<K>(){
            private boolean first = true;

            @Override
            public boolean execute(K key, long value) {
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
    class TObjectLongHashIterator<K>
    extends TObjectHashIterator<K>
    implements TObjectLongIterator<K> {
        private final TObjectLongHashMap<K> _map;

        public TObjectLongHashIterator(TObjectLongHashMap<K> map) {
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
        public long value() {
            return this._map._values[this._index];
        }

        @Override
        public long setValue(long val) {
            long old = this.value();
            this._map._values[this._index] = val;
            return old;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TLongValueCollection
    implements TLongCollection {
        TLongValueCollection() {
        }

        @Override
        public TLongIterator iterator() {
            return new TObjectLongValueHashIterator();
        }

        @Override
        public long getNoEntryValue() {
            return TObjectLongHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TObjectLongHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TObjectLongHashMap.this._size;
        }

        @Override
        public boolean contains(long entry) {
            return TObjectLongHashMap.this.containsValue(entry);
        }

        @Override
        public long[] toArray() {
            return TObjectLongHashMap.this.values();
        }

        @Override
        public long[] toArray(long[] dest) {
            return TObjectLongHashMap.this.values(dest);
        }

        @Override
        public boolean add(long entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(long entry) {
            long[] values = TObjectLongHashMap.this._values;
            Object[] set = TObjectLongHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || entry != values[i2]) continue;
                TObjectLongHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Long) {
                    long ele = (Long)element;
                    if (TObjectLongHashMap.this.containsValue(ele)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TLongCollection collection) {
            TLongIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TObjectLongHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(long[] array) {
            for (long element : array) {
                if (TObjectLongHashMap.this.containsValue(element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Long> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(TLongCollection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(long[] array) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean modified = false;
            TLongIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(TLongCollection collection) {
            if (this == collection) {
                return false;
            }
            boolean modified = false;
            TLongIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(long[] array) {
            boolean changed = false;
            Arrays.sort(array);
            long[] values = TObjectLongHashMap.this._values;
            Object[] set = TObjectLongHashMap.this._set;
            int i2 = set.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TObjectLongHashMap.this.removeAt(i2);
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            for (Object element : collection) {
                long c2;
                if (!(element instanceof Long) || !this.remove(c2 = ((Long)element).longValue())) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(TLongCollection collection) {
            if (this == collection) {
                this.clear();
                return true;
            }
            boolean changed = false;
            TLongIterator iter = collection.iterator();
            while (iter.hasNext()) {
                long element = iter.next();
                if (!this.remove(element)) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(long[] array) {
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
            TObjectLongHashMap.this.clear();
        }

        @Override
        public boolean forEach(TLongProcedure procedure) {
            return TObjectLongHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TObjectLongHashMap.this.forEachValue(new TLongProcedure(){
                private boolean first = true;

                public boolean execute(long value) {
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

        class TObjectLongValueHashIterator
        implements TLongIterator {
            protected THash _hash;
            protected int _expectedSize;
            protected int _index;

            TObjectLongValueHashIterator() {
                this._hash = TObjectLongHashMap.this;
                this._expectedSize = this._hash.size();
                this._index = this._hash.capacity();
            }

            public boolean hasNext() {
                return this.nextIndex() >= 0;
            }

            public long next() {
                this.moveToNextIndex();
                return TObjectLongHashMap.this._values[this._index];
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
                    TObjectLongHashMap.this.removeAt(this._index);
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
                Object[] set = TObjectLongHashMap.this._set;
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
            TObjectLongHashMap.this.clear();
        }

        @Override
        public boolean add(E obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TObjectLongHashMap.this.size();
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
            return TObjectLongHashMap.this.isEmpty();
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
            return new TObjectHashIterator(TObjectLongHashMap.this);
        }

        @Override
        public boolean removeElement(K key) {
            return TObjectLongHashMap.this.no_entry_value != TObjectLongHashMap.this.remove(key);
        }

        @Override
        public boolean containsElement(K key) {
            return TObjectLongHashMap.this.contains(key);
        }
    }
}

