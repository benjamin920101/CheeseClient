/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.custom_hash;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TCustomObjectHash;
import gnu.trove.impl.hash.THash;
import gnu.trove.impl.hash.TObjectHash;
import gnu.trove.iterator.TFloatIterator;
import gnu.trove.iterator.TObjectFloatIterator;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.map.TObjectFloatMap;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TObjectFloatProcedure;
import gnu.trove.procedure.TObjectProcedure;
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
public class TObjectFloatCustomHashMap<K>
extends TCustomObjectHash<K>
implements TObjectFloatMap<K>,
Externalizable {
    static final long serialVersionUID = 1L;
    private final TObjectFloatProcedure<K> PUT_ALL_PROC = new TObjectFloatProcedure<K>(){

        @Override
        public boolean execute(K key, float value) {
            TObjectFloatCustomHashMap.this.put(key, value);
            return true;
        }
    };
    protected transient float[] _values;
    protected float no_entry_value;

    public TObjectFloatCustomHashMap() {
    }

    public TObjectFloatCustomHashMap(HashingStrategy<? super K> strategy) {
        super(strategy);
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
    }

    public TObjectFloatCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity) {
        super(strategy, initialCapacity);
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
    }

    public TObjectFloatCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity, float loadFactor) {
        super(strategy, initialCapacity, loadFactor);
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
    }

    public TObjectFloatCustomHashMap(HashingStrategy<? super K> strategy, int initialCapacity, float loadFactor, float noEntryValue) {
        super(strategy, initialCapacity, loadFactor);
        this.no_entry_value = noEntryValue;
        if (this.no_entry_value != 0.0f) {
            Arrays.fill(this._values, this.no_entry_value);
        }
    }

    public TObjectFloatCustomHashMap(HashingStrategy<? super K> strategy, TObjectFloatMap<? extends K> map) {
        this(strategy, map.size(), 0.5f, map.getNoEntryValue());
        if (map instanceof TObjectFloatCustomHashMap) {
            TObjectFloatCustomHashMap hashmap = (TObjectFloatCustomHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_value = hashmap.no_entry_value;
            this.strategy = hashmap.strategy;
            if (this.no_entry_value != 0.0f) {
                Arrays.fill(this._values, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.putAll(map);
    }

    @Override
    public int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new float[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        Object[] oldKeys = this._set;
        float[] oldVals = this._values;
        this._set = new Object[newCapacity];
        Arrays.fill(this._set, FREE);
        this._values = new float[newCapacity];
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
    public float getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.contains(key);
    }

    @Override
    public boolean containsValue(float val) {
        Object[] keys = this._set;
        float[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public float get(Object key) {
        int index = this.index(key);
        return index < 0 ? this.no_entry_value : this._values[index];
    }

    @Override
    public float put(K key, float value) {
        int index = this.insertKey(key);
        return this.doPut(value, index);
    }

    @Override
    public float putIfAbsent(K key, float value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(value, index);
    }

    private float doPut(float value, int index) {
        float previous = this.no_entry_value;
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
    public float remove(Object key) {
        float prev = this.no_entry_value;
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
    public void putAll(Map<? extends K, ? extends Float> map) {
        Set<Map.Entry<K, Float>> set = map.entrySet();
        for (Map.Entry<K, Float> entry : set) {
            this.put(entry.getKey(), entry.getValue().floatValue());
        }
    }

    @Override
    public void putAll(TObjectFloatMap<? extends K> map) {
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
    public TFloatCollection valueCollection() {
        return new TFloatValueCollection();
    }

    @Override
    public float[] values() {
        float[] vals = new float[this.size()];
        float[] v2 = this._values;
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
    public float[] values(float[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new float[size];
        }
        float[] v2 = this._values;
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
    public TObjectFloatIterator<K> iterator() {
        return new TObjectFloatHashIterator(this);
    }

    @Override
    public boolean increment(K key) {
        return this.adjustValue(key, 1.0f);
    }

    @Override
    public boolean adjustValue(K key, float amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = this._values[n2] + amount;
        return true;
    }

    @Override
    public float adjustOrPutValue(K key, float adjust_amount, float put_amount) {
        boolean isNewMapping;
        float newValue;
        int index = this.insertKey(key);
        if (index < 0) {
            int n2 = index = -index - 1;
            float f2 = this._values[n2] + adjust_amount;
            this._values[n2] = f2;
            newValue = f2;
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
    public boolean forEachValue(TFloatProcedure procedure) {
        Object[] keys = this._set;
        float[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == FREE || keys[i2] == REMOVED || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TObjectFloatProcedure<? super K> procedure) {
        Object[] keys = this._set;
        float[] values = this._values;
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
    public boolean retainEntries(TObjectFloatProcedure<? super K> procedure) {
        boolean modified = false;
        Object[] keys = this._set;
        float[] values = this._values;
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
    public void transformValues(TFloatFunction function) {
        Object[] keys = this._set;
        float[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (keys[i2] == null || keys[i2] == REMOVED) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TObjectFloatMap)) {
            return false;
        }
        TObjectFloatMap that = (TObjectFloatMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        try {
            TObjectFloatIterator<K> iter = this.iterator();
            while (iter.hasNext()) {
                iter.advance();
                K key = iter.key();
                float value = iter.value();
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
        float[] values = this._values;
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
        out.writeFloat(this.no_entry_value);
        out.writeInt(this._size);
        int i2 = this._set.length;
        while (i2-- > 0) {
            if (this._set[i2] == REMOVED || this._set[i2] == FREE) continue;
            out.writeObject(this._set[i2]);
            out.writeFloat(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        this.strategy = (HashingStrategy)in2.readObject();
        this.no_entry_value = in2.readFloat();
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            Object key = in2.readObject();
            float val = in2.readFloat();
            this.put(key, val);
        }
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TObjectFloatProcedure<K>(){
            private boolean first = true;

            @Override
            public boolean execute(K key, float value) {
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
    class TObjectFloatHashIterator<K>
    extends TObjectHashIterator<K>
    implements TObjectFloatIterator<K> {
        private final TObjectFloatCustomHashMap<K> _map;

        public TObjectFloatHashIterator(TObjectFloatCustomHashMap<K> map) {
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
        public float value() {
            return this._map._values[this._index];
        }

        @Override
        public float setValue(float val) {
            float old = this.value();
            this._map._values[this._index] = val;
            return old;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    class TFloatValueCollection
    implements TFloatCollection {
        TFloatValueCollection() {
        }

        @Override
        public TFloatIterator iterator() {
            return new TObjectFloatValueHashIterator();
        }

        @Override
        public float getNoEntryValue() {
            return TObjectFloatCustomHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TObjectFloatCustomHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TObjectFloatCustomHashMap.this._size;
        }

        @Override
        public boolean contains(float entry) {
            return TObjectFloatCustomHashMap.this.containsValue(entry);
        }

        @Override
        public float[] toArray() {
            return TObjectFloatCustomHashMap.this.values();
        }

        @Override
        public float[] toArray(float[] dest) {
            return TObjectFloatCustomHashMap.this.values(dest);
        }

        @Override
        public boolean add(float entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(float entry) {
            float[] values = TObjectFloatCustomHashMap.this._values;
            Object[] set = TObjectFloatCustomHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || entry != values[i2]) continue;
                TObjectFloatCustomHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Float) {
                    float ele = ((Float)element).floatValue();
                    if (TObjectFloatCustomHashMap.this.containsValue(ele)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TFloatCollection collection) {
            TFloatIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TObjectFloatCustomHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(float[] array) {
            for (float element : array) {
                if (TObjectFloatCustomHashMap.this.containsValue(element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Float> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(TFloatCollection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(float[] array) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean modified = false;
            TFloatIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(Float.valueOf(iter.next()))) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(TFloatCollection collection) {
            if (this == collection) {
                return false;
            }
            boolean modified = false;
            TFloatIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(float[] array) {
            boolean changed = false;
            Arrays.sort(array);
            float[] values = TObjectFloatCustomHashMap.this._values;
            Object[] set = TObjectFloatCustomHashMap.this._set;
            int i2 = set.length;
            while (i2-- > 0) {
                if (set[i2] == TObjectHash.FREE || set[i2] == TObjectHash.REMOVED || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TObjectFloatCustomHashMap.this.removeAt(i2);
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            for (Object element : collection) {
                float c2;
                if (!(element instanceof Float) || !this.remove(c2 = ((Float)element).floatValue())) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(TFloatCollection collection) {
            if (this == collection) {
                this.clear();
                return true;
            }
            boolean changed = false;
            TFloatIterator iter = collection.iterator();
            while (iter.hasNext()) {
                float element = iter.next();
                if (!this.remove(element)) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(float[] array) {
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
            TObjectFloatCustomHashMap.this.clear();
        }

        @Override
        public boolean forEach(TFloatProcedure procedure) {
            return TObjectFloatCustomHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TObjectFloatCustomHashMap.this.forEachValue(new TFloatProcedure(){
                private boolean first = true;

                public boolean execute(float value) {
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

        class TObjectFloatValueHashIterator
        implements TFloatIterator {
            protected THash _hash;
            protected int _expectedSize;
            protected int _index;

            TObjectFloatValueHashIterator() {
                this._hash = TObjectFloatCustomHashMap.this;
                this._expectedSize = this._hash.size();
                this._index = this._hash.capacity();
            }

            public boolean hasNext() {
                return this.nextIndex() >= 0;
            }

            public float next() {
                this.moveToNextIndex();
                return TObjectFloatCustomHashMap.this._values[this._index];
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
                    TObjectFloatCustomHashMap.this.removeAt(this._index);
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
                Object[] set = TObjectFloatCustomHashMap.this._set;
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
            TObjectFloatCustomHashMap.this.clear();
        }

        @Override
        public boolean add(E obj) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int size() {
            return TObjectFloatCustomHashMap.this.size();
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
            return TObjectFloatCustomHashMap.this.isEmpty();
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
            return new TObjectHashIterator(TObjectFloatCustomHashMap.this);
        }

        @Override
        public boolean removeElement(K key) {
            return TObjectFloatCustomHashMap.this.no_entry_value != TObjectFloatCustomHashMap.this.remove(key);
        }

        @Override
        public boolean containsElement(K key) {
            return TObjectFloatCustomHashMap.this.contains(key);
        }
    }
}

