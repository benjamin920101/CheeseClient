/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.custom_hash;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TCustomObjectHash;
import gnu.trove.impl.hash.THash;
import gnu.trove.impl.hash.TObjectHash;
import gnu.trove.iterator.TObjectShortIterator;
import gnu.trove.iterator.TShortIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.map.TObjectShortMap;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.TObjectShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.strategy.HashingStrategy;
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
public class TObjectShortCustomHashMap<K>
extends TCustomObjectHash<K>
implements TObjectShortMap<K>,
Externalizable {
    static final long serialVersionUID = 1L;
    private final TObjectShortProcedure<K> PUT_ALL_PROC = new TObjectShortProcedure<K>(){

        @Override
        public boolean execute(K key, short value) {
            TObjectShortCustomHashMap.this.put(key, value);
            return true;
        }
    };
    protected transient short[] _values;
    protected short no_entry_value;

    public TObjectShortCustomHashMap() {
    }

    public TObjectShortCustomHashMap(HashingStrategy<? super K> strategy) {
        super(strategy);
        this.no_entry_value = Constants.DEFAULT_SHORT_NO_ENTRY_VALUE;
    }

    public TObjectShortCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity) {
        super(strategy, initialCapacity);
        this.no_entry_value = Constants.DEFAULT_SHORT_NO_ENTRY_VALUE;
    }

    public TObjectShortCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity, float loadFactor) {
        super(strategy, initialCapacity, loadFactor);
        this.no_entry_value = Constants.DEFAULT_SHORT_NO_ENTRY_VALUE;
    }

    public TObjectShortCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity, float loadFactor, short noEntryValue) {
        super(strategy, initialCapacity, loadFactor);
        this.no_entry_value = noEntryValue;
        if (this.no_entry_value != 0) {
            Arrays.fill(this._values, this.no_entry_value);
        }
    }

    public TObjectShortCustomHashMap(HashingStrategy<? super K> strategy, TObjectShortMap<? extends K> map) {
        this(strategy, map.size(), 0.5f, map.getNoEntryValue());
        if (map instanceof TObjectShortCustomHashMap) {
            TObjectShortCustomHashMap hashmap = (TObjectShortCustomHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_value = hashmap.no_entry_value;
            this.strategy = hashmap.strategy;
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
        this._values = new short[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        Object[] oldKeys = this._set;
        short[] oldVals = this._values;
        this._set = new Object[newCapacity];
        Arrays.fill(this._set, FREE);
        this._values = new short[newCapacity];
        Arrays.fill(this._values, this.no_entry_value);
        int i2 = oldCapacity;
        while (i2-- > 0) {
            Object o2 = oldKeys[i2];
            if (o2 == FREE || o2 == REMOVED) continue;
            int index = this.insertKey(o2);
            if (index < 0) {
                this.throwObjectContractViolation(this._set[-index - 1], o2);
            }
            this._values[index] = oldVals[i2];
        }
    }

    @Override
    public short getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.contains(key);
    }

    @Override
    public boolean containsValue(short val) {
        Object[] keys = this._set;
        short[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public short get(Object key) {
        int index = this.index(key);
        return index < 0 ? this.no_entry_value : this._values[index];
    }

    @Override
    public short put(K key, short value) {
        int index = this.insertKey(key);
        return this.doPut(value, index);
    }

    @Override
    public short putIfAbsent(K key, short value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(value, index);
    }

    private short doPut(short value, int index) {
        short previous = this.no_entry_value;
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
    public short remove(Object key) {
        short prev = this.no_entry_value;
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
    public void putAll(Map<? extends K, ? extends Short> map) {
        Set<Map.Entry<K, Short>> set = map.entrySet();
        for (Map.Entry<K, Short> entry : set) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TObjectShortMap<? extends K> map) {
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
    public TShortCollection valueCollection() {
        return new TShortValueCollection();
    }

    @Override
    public short[] values() {
        short[] vals = new short[this.size()];
        short[] v2 = this._values;
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
    public short[] values(short[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new short[size];
        }
        short[] v2 = this._values;
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
    public TObjectShortIterator<K> iterator() {
        return new TObjectShortHashIterator(this);
    }

    @Override
    public boolean increment(K key) {
        return this.adjustValue(key, (short)1);
    }

    @Override
    public boolean adjustValue(K key, short amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = (short)(this._values[n2] + amount);
        return true;
    }

    @Override
    public short adjustOrPutValue(K key, short adjust_amount, short put_amount) {
        boolean isNewMapping;
        short newValue;
        int index = this.insertKey(key);
        if (index < 0) {
            int n2 = index = -index - 1;
            short s2 = (short)(this._values[n2] + adjust_amount);
            this._values[n2] = s2;
            newValue = s2;
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
    public boolean forEachValue(TShortProcedure procedure) {
        Object[] keys = this._set;
        short[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TObjectShortProcedure<? super K> procedure) {
        Object[] keys = this._set;
        short[] values = this._values;
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
    public boolean retainEntries(TObjectShortProcedure<? super K> procedure) {
        boolean modified = false;
        Object[] keys = this._set;
        short[] values = this._values;
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
    public void transformValues(TShortFunction function) {
        Object[] keys = this._set;
        short[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == null || keys[i2] == REMOVED) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TObjectShortMap)) {
            return false;
        }
        TObjectShortMap that = (TObjectShortMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        try {
            TObjectShortIterator<K> iter = this.iterator();
            while (iter.hasNext()) {
                iter.advance();
                K key = iter.key();
                short value = iter.value();
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
        short[] values = this._values;
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
        out.writeObject(this.strategy);
        out.writeShort(this.no_entry_value);
        out.writeInt(this._size);
        int i2 = this._set.length;
        while (i2-- > 0) {
            if (this._set[i2] == REMOVED || this._set[i2] == FREE) continue;
            out.writeObject(this._set[i2]);
            out.writeShort(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        this.strategy = (HashingStrategy)in2.readObject();
        this.no_entry_value = in2.readShort();
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            Object key = in2.readObject();
            short val = in2.readShort();
            this.put(key, val);
        }
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TObjectShortProcedure<K>(){
            private boolean first = true;

            @Override
            public boolean execute(K key, short value) {
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
    class TObjectShortHashIterator<K>
    extends TObjectHashIterator<K>
    implements TObjectShortIterator<K> {
        private final TObjectShortCustomHashMap<K> _map;

        public TObjectShortHashIterator(TObjectShortCustomHashMap<K> map) {
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
        public short value() {
            return this._map._values[this._index];
        }

        @Override
        public short setValue(short val) {
            short old = this.value();
            this._map._values[this._index] = val;
            return old;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TShortValueCollection
    implements TShortCollection {
        TShortValueCollection() {
        }

        @Override
        public TShortIterator iterator() {
            return new TObjectShortValueHashIterator();
        }

        @Override
        public short getNoEntryValue() {
            return TObjectShortCustomHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TObjectShortCustomHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TObjectShortCustomHashMap.this._size;
        }

        @Override
        public boolean contains(short entry) {
            return TObjectShortCustomHashMap.this.containsValue(entry);
        }

        @Override
        public short[] toArray() {
            return TObjectShortCustomHashMap.this.values();
        }

        @Override
        public short[] toArray(short[] dest) {
            return TObjectShortCustomHashMap.this.values(dest);
        }

        @Override
        public boolean add(short entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(short entry) {
            short[] values = TObjectShortCustomHashMap.this._values;
            Object[] set = TObjectShortCustomHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || entry != values[i2]) continue;
                TObjectShortCustomHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Short) {
                    short ele = (Short)element;
                    if (TObjectShortCustomHashMap.this.containsValue(ele)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TShortCollection collection) {
            TShortIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TObjectShortCustomHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(short[] array) {
            for (short element : array) {
                if (TObjectShortCustomHashMap.this.containsValue(element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Short> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(TShortCollection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(short[] array) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean modified = false;
            TShortIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(TShortCollection collection) {
            if (this == collection) {
                return false;
            }
            boolean modified = false;
            TShortIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(short[] array) {
            boolean changed = false;
            Arrays.sort(array);
            short[] values = TObjectShortCustomHashMap.this._values;
            Object[] set = TObjectShortCustomHashMap.this._set;
            int i2 = set.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TObjectShortCustomHashMap.this.removeAt(i2);
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            for (Object element : collection) {
                short c2;
                if (!(element instanceof Short) || !this.remove(c2 = ((Short)element).shortValue())) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(TShortCollection collection) {
            if (this == collection) {
                this.clear();
                return true;
            }
            boolean changed = false;
            TShortIterator iter = collection.iterator();
            while (iter.hasNext()) {
                short element = iter.next();
                if (!this.remove(element)) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(short[] array) {
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
            TObjectShortCustomHashMap.this.clear();
        }

        @Override
        public boolean forEach(TShortProcedure procedure) {
            return TObjectShortCustomHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TObjectShortCustomHashMap.this.forEachValue(new TShortProcedure(){
                private boolean first = true;

                public boolean execute(short value) {
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

        class TObjectShortValueHashIterator
        implements TShortIterator {
            protected THash _hash;
            protected int _expectedSize;
            protected int _index;

            TObjectShortValueHashIterator() {
                this._hash = TObjectShortCustomHashMap.this;
                this._expectedSize = this._hash.size();
                this._index = this._hash.capacity();
            }

            public boolean hasNext() {
                return this.nextIndex() >= 0;
            }

            public short next() {
                this.moveToNextIndex();
                return TObjectShortCustomHashMap.this._values[this._index];
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
                    TObjectShortCustomHashMap.this.removeAt(this._index);
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
                Object[] set = TObjectShortCustomHashMap.this._set;
                int i2 = this._index;
                while (i2-- > 0 && (set[i2] == TCustomObjectHash.FREE || set[i2] == TCustomObjectHash.REMOVED)) {
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
            TObjectShortCustomHashMap.this.clear();
        }

        @Override
        public boolean add(E obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TObjectShortCustomHashMap.this.size();
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
            return TObjectShortCustomHashMap.this.isEmpty();
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
            return new TObjectHashIterator(TObjectShortCustomHashMap.this);
        }

        @Override
        public boolean removeElement(K key) {
            return TObjectShortCustomHashMap.this.no_entry_value != TObjectShortCustomHashMap.this.remove(key);
        }

        @Override
        public boolean containsElement(K key) {
            return TObjectShortCustomHashMap.this.contains(key);
        }
    }
}

