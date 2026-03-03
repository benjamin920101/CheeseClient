/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TByteCollection;
import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TByteLongHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.impl.hash.TPrimitiveHash;
import gnu.trove.iterator.TByteIterator;
import gnu.trove.iterator.TByteLongIterator;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.map.TByteLongMap;
import gnu.trove.procedure.TByteLongProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TByteSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TByteLongHashMap
extends TByteLongHash
implements TByteLongMap,
Externalizable {
    static final long serialVersionUID = 1L;
    protected transient long[] _values;

    public TByteLongHashMap() {
    }

    public TByteLongHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public TByteLongHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public TByteLongHashMap(int initialCapacity, float loadFactor, byte noEntryKey, long noEntryValue) {
        super(initialCapacity, loadFactor, noEntryKey, noEntryValue);
    }

    public TByteLongHashMap(byte[] keys, long[] values) {
        super(Math.max(keys.length, values.length));
        int size = Math.min(keys.length, values.length);
        for (int i2 = 0; i2 < size; ++i2) {
            this.put(keys[i2], values[i2]);
        }
    }

    public TByteLongHashMap(TByteLongMap map) {
        super(map.size());
        if (map instanceof TByteLongHashMap) {
            TByteLongHashMap hashmap = (TByteLongHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_key = hashmap.no_entry_key;
            this.no_entry_value = hashmap.no_entry_value;
            if (this.no_entry_key != 0) {
                Arrays.fill(this._set, this.no_entry_key);
            }
            if (this.no_entry_value != 0L) {
                Arrays.fill(this._values, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.putAll(map);
    }

    @Override
    protected int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new long[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        byte[] oldKeys = this._set;
        long[] oldVals = this._values;
        byte[] oldStates = this._states;
        this._set = new byte[newCapacity];
        this._values = new long[newCapacity];
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
    public long put(byte key, long value) {
        int index = this.insertKey(key);
        return this.doPut(key, value, index);
    }

    @Override
    public long putIfAbsent(byte key, long value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(key, value, index);
    }

    private long doPut(byte key, long value, int index) {
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
    public void putAll(Map<? extends Byte, ? extends Long> map) {
        this.ensureCapacity(map.size());
        for (Map.Entry<? extends Byte, ? extends Long> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TByteLongMap map) {
        this.ensureCapacity(map.size());
        TByteLongIterator iter = map.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.put(iter.key(), iter.value());
        }
    }

    @Override
    public long get(byte key) {
        int index = this.index(key);
        return index < 0 ? this.no_entry_value : this._values[index];
    }

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(this._set, 0, this._set.length, this.no_entry_key);
        Arrays.fill(this._values, 0, this._values.length, this.no_entry_value);
        Arrays.fill(this._states, 0, this._states.length, (byte)0);
    }

    @Override
    public boolean isEmpty() {
        return 0 == this._size;
    }

    @Override
    public long remove(byte key) {
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
    public TByteSet keySet() {
        return new TKeyView();
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
    public byte[] keys(byte[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new byte[size];
        }
        byte[] keys = this._set;
        byte[] states = this._states;
        int i2 = keys.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            array[j2++] = keys[i2];
        }
        return array;
    }

    @Override
    public TLongCollection valueCollection() {
        return new TValueView();
    }

    @Override
    public long[] values() {
        long[] vals = new long[this.size()];
        long[] v2 = this._values;
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
    public long[] values(long[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new long[size];
        }
        long[] v2 = this._values;
        byte[] states = this._states;
        int i2 = v2.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            array[j2++] = v2[i2];
        }
        return array;
    }

    @Override
    public boolean containsValue(long val) {
        byte[] states = this._states;
        long[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsKey(byte key) {
        return this.contains(key);
    }

    @Override
    public TByteLongIterator iterator() {
        return new TByteLongHashIterator(this);
    }

    @Override
    public boolean forEachKey(TByteProcedure procedure) {
        return this.forEach(procedure);
    }

    @Override
    public boolean forEachValue(TLongProcedure procedure) {
        byte[] states = this._states;
        long[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TByteLongProcedure procedure) {
        byte[] states = this._states;
        byte[] keys = this._set;
        long[] values = this._values;
        int i2 = keys.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(keys[i2], values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void transformValues(TLongFunction function) {
        byte[] states = this._states;
        long[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            values[i2] = function.execute(values[i2]);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainEntries(TByteLongProcedure procedure) {
        boolean modified = false;
        byte[] states = this._states;
        byte[] keys = this._set;
        long[] values = this._values;
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
    public boolean increment(byte key) {
        return this.adjustValue(key, 1L);
    }

    @Override
    public boolean adjustValue(byte key, long amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = this._values[n2] + amount;
        return true;
    }

    @Override
    public long adjustOrPutValue(byte key, long adjust_amount, long put_amount) {
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
        byte previousState = this._states[index];
        if (isNewMapping) {
            this.postInsertHook(this.consumeFreeSlot);
        }
        return newValue;
    }

    public boolean equals(Object other) {
        if (!(other instanceof TByteLongMap)) {
            return false;
        }
        TByteLongMap that = (TByteLongMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        long[] values = this._values;
        byte[] states = this._states;
        long this_no_entry_value = this.getNoEntryValue();
        long that_no_entry_value = that.getNoEntryValue();
        int i2 = values.length;
        while (i2-- > 0) {
            byte key;
            long that_value;
            long this_value;
            if (states[i2] != 1 || (this_value = values[i2]) == (that_value = that.get(key = this._set[i2])) || this_value == this_no_entry_value || that_value == that_no_entry_value) continue;
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashcode = 0;
        byte[] states = this._states;
        int i2 = this._values.length;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            hashcode += HashFunctions.hash(this._set[i2]) ^ HashFunctions.hash(this._values[i2]);
        }
        return hashcode;
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEachEntry(new TByteLongProcedure(){
            private boolean first = true;

            public boolean execute(byte key, long value) {
                if (this.first) {
                    this.first = false;
                } else {
                    buf.append(", ");
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        super.writeExternal(out);
        out.writeInt(this._size);
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            out.writeByte(this._set[i2]);
            out.writeLong(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            byte key = in2.readByte();
            long val = in2.readLong();
            this.put(key, val);
        }
    }

    class TByteLongHashIterator
    extends THashPrimitiveIterator
    implements TByteLongIterator {
        TByteLongHashIterator(TByteLongHashMap map) {
            super(map);
        }

        public void advance() {
            this.moveToNextIndex();
        }

        public byte key() {
            return TByteLongHashMap.this._set[this._index];
        }

        public long value() {
            return TByteLongHashMap.this._values[this._index];
        }

        public long setValue(long val) {
            long old = this.value();
            TByteLongHashMap.this._values[this._index] = val;
            return old;
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
                TByteLongHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TByteLongValueHashIterator
    extends THashPrimitiveIterator
    implements TLongIterator {
        TByteLongValueHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public long next() {
            this.moveToNextIndex();
            return TByteLongHashMap.this._values[this._index];
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
                TByteLongHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TByteLongKeyHashIterator
    extends THashPrimitiveIterator
    implements TByteIterator {
        TByteLongKeyHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public byte next() {
            this.moveToNextIndex();
            return TByteLongHashMap.this._set[this._index];
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
                TByteLongHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class TValueView
    implements TLongCollection {
        protected TValueView() {
        }

        @Override
        public TLongIterator iterator() {
            return new TByteLongValueHashIterator(TByteLongHashMap.this);
        }

        @Override
        public long getNoEntryValue() {
            return TByteLongHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TByteLongHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TByteLongHashMap.this._size;
        }

        @Override
        public boolean contains(long entry) {
            return TByteLongHashMap.this.containsValue(entry);
        }

        @Override
        public long[] toArray() {
            return TByteLongHashMap.this.values();
        }

        @Override
        public long[] toArray(long[] dest) {
            return TByteLongHashMap.this.values(dest);
        }

        @Override
        public boolean add(long entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(long entry) {
            long[] values = TByteLongHashMap.this._values;
            byte[] set = TByteLongHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == 0 || set[i2] == 2 || entry != values[i2]) continue;
                TByteLongHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Long) {
                    long ele = (Long)element;
                    if (TByteLongHashMap.this.containsValue(ele)) continue;
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
                if (TByteLongHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(long[] array) {
            for (long element : array) {
                if (TByteLongHashMap.this.containsValue(element)) continue;
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
            long[] values = TByteLongHashMap.this._values;
            byte[] states = TByteLongHashMap.this._states;
            int i2 = values.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TByteLongHashMap.this.removeAt(i2);
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
            TByteLongHashMap.this.clear();
        }

        @Override
        public boolean forEach(TLongProcedure procedure) {
            return TByteLongHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TByteLongHashMap.this.forEachValue(new TLongProcedure(){
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
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class TKeyView
    implements TByteSet {
        protected TKeyView() {
        }

        @Override
        public TByteIterator iterator() {
            return new TByteLongKeyHashIterator(TByteLongHashMap.this);
        }

        @Override
        public byte getNoEntryValue() {
            return TByteLongHashMap.this.no_entry_key;
        }

        @Override
        public int size() {
            return TByteLongHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TByteLongHashMap.this._size;
        }

        @Override
        public boolean contains(byte entry) {
            return TByteLongHashMap.this.contains(entry);
        }

        @Override
        public byte[] toArray() {
            return TByteLongHashMap.this.keys();
        }

        @Override
        public byte[] toArray(byte[] dest) {
            return TByteLongHashMap.this.keys(dest);
        }

        @Override
        public boolean add(byte entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(byte entry) {
            return TByteLongHashMap.this.no_entry_value != TByteLongHashMap.this.remove(entry);
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Byte) {
                    byte ele = (Byte)element;
                    if (TByteLongHashMap.this.containsKey(ele)) continue;
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
                if (TByteLongHashMap.this.containsKey(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(byte[] array) {
            for (byte element : array) {
                if (TByteLongHashMap.this.contains(element)) continue;
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
            byte[] set = TByteLongHashMap.this._set;
            byte[] states = TByteLongHashMap.this._states;
            int i2 = set.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, set[i2]) >= 0) continue;
                TByteLongHashMap.this.removeAt(i2);
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
            TByteLongHashMap.this.clear();
        }

        @Override
        public boolean forEach(TByteProcedure procedure) {
            return TByteLongHashMap.this.forEachKey(procedure);
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
            int i2 = TByteLongHashMap.this._states.length;
            while (i2-- > 0) {
                if (TByteLongHashMap.this._states[i2] != 1 || that.contains(TByteLongHashMap.this._set[i2])) continue;
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            int i2 = TByteLongHashMap.this._states.length;
            while (i2-- > 0) {
                if (TByteLongHashMap.this._states[i2] != 1) continue;
                hashcode += HashFunctions.hash(TByteLongHashMap.this._set[i2]);
            }
            return hashcode;
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TByteLongHashMap.this.forEachKey(new TByteProcedure(){
                private boolean first = true;

                public boolean execute(byte key) {
                    if (this.first) {
                        this.first = false;
                    } else {
                        buf.append(", ");
                    }
                    buf.append(key);
                    return true;
                }
            });
            buf.append("}");
            return buf.toString();
        }
    }
}

