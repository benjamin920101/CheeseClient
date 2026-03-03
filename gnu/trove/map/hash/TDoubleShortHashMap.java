/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TDoubleCollection;
import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TDoubleShortHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.impl.hash.TPrimitiveHash;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.iterator.TDoubleShortIterator;
import gnu.trove.iterator.TShortIterator;
import gnu.trove.map.TDoubleShortMap;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TDoubleShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TDoubleSet;
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
public class TDoubleShortHashMap
extends TDoubleShortHash
implements TDoubleShortMap,
Externalizable {
    static final long serialVersionUID = 1L;
    protected transient short[] _values;

    public TDoubleShortHashMap() {
    }

    public TDoubleShortHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public TDoubleShortHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public TDoubleShortHashMap(int initialCapacity, float loadFactor, double noEntryKey, short noEntryValue) {
        super(initialCapacity, loadFactor, noEntryKey, noEntryValue);
    }

    public TDoubleShortHashMap(double[] keys, short[] values) {
        super(Math.max(keys.length, values.length));
        int size = Math.min(keys.length, values.length);
        for (int i2 = 0; i2 < size; ++i2) {
            this.put(keys[i2], values[i2]);
        }
    }

    public TDoubleShortHashMap(TDoubleShortMap map) {
        super(map.size());
        if (map instanceof TDoubleShortHashMap) {
            TDoubleShortHashMap hashmap = (TDoubleShortHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_key = hashmap.no_entry_key;
            this.no_entry_value = hashmap.no_entry_value;
            if (this.no_entry_key != 0.0) {
                Arrays.fill(this._set, this.no_entry_key);
            }
            if (this.no_entry_value != 0) {
                Arrays.fill(this._values, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.putAll(map);
    }

    @Override
    protected int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._values = new short[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        double[] oldKeys = this._set;
        short[] oldVals = this._values;
        byte[] oldStates = this._states;
        this._set = new double[newCapacity];
        this._values = new short[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            double o2 = oldKeys[i2];
            int index = this.insertKey(o2);
            this._values[index] = oldVals[i2];
        }
    }

    @Override
    public short put(double key, short value) {
        int index = this.insertKey(key);
        return this.doPut(key, value, index);
    }

    @Override
    public short putIfAbsent(double key, short value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(key, value, index);
    }

    private short doPut(double key, short value, int index) {
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
    public void putAll(Map<? extends Double, ? extends Short> map) {
        this.ensureCapacity(map.size());
        for (Map.Entry<? extends Double, ? extends Short> entry : map.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(TDoubleShortMap map) {
        this.ensureCapacity(map.size());
        TDoubleShortIterator iter = map.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.put(iter.key(), iter.value());
        }
    }

    @Override
    public short get(double key) {
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
    public short remove(double key) {
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
    public TDoubleSet keySet() {
        return new TKeyView();
    }

    @Override
    public double[] keys() {
        double[] keys = new double[this.size()];
        double[] k2 = this._set;
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
    public double[] keys(double[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new double[size];
        }
        double[] keys = this._set;
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
    public TShortCollection valueCollection() {
        return new TValueView();
    }

    @Override
    public short[] values() {
        short[] vals = new short[this.size()];
        short[] v2 = this._values;
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
    public short[] values(short[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new short[size];
        }
        short[] v2 = this._values;
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
    public boolean containsValue(short val) {
        byte[] states = this._states;
        short[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsKey(double key) {
        return this.contains(key);
    }

    @Override
    public TDoubleShortIterator iterator() {
        return new TDoubleShortHashIterator(this);
    }

    @Override
    public boolean forEachKey(TDoubleProcedure procedure) {
        return this.forEach(procedure);
    }

    @Override
    public boolean forEachValue(TShortProcedure procedure) {
        byte[] states = this._states;
        short[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TDoubleShortProcedure procedure) {
        byte[] states = this._states;
        double[] keys = this._set;
        short[] values = this._values;
        int i2 = keys.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(keys[i2], values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void transformValues(TShortFunction function) {
        byte[] states = this._states;
        short[] values = this._values;
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
    public boolean retainEntries(TDoubleShortProcedure procedure) {
        boolean modified = false;
        byte[] states = this._states;
        double[] keys = this._set;
        short[] values = this._values;
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
    public boolean increment(double key) {
        return this.adjustValue(key, (short)1);
    }

    @Override
    public boolean adjustValue(double key, short amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = (short)(this._values[n2] + amount);
        return true;
    }

    @Override
    public short adjustOrPutValue(double key, short adjust_amount, short put_amount) {
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
        byte previousState = this._states[index];
        if (isNewMapping) {
            this.postInsertHook(this.consumeFreeSlot);
        }
        return newValue;
    }

    public boolean equals(Object other) {
        if (!(other instanceof TDoubleShortMap)) {
            return false;
        }
        TDoubleShortMap that = (TDoubleShortMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        short[] values = this._values;
        byte[] states = this._states;
        short this_no_entry_value = this.getNoEntryValue();
        short that_no_entry_value = that.getNoEntryValue();
        int i2 = values.length;
        while (i2-- > 0) {
            double key;
            short that_value;
            short this_value;
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
        this.forEachEntry(new TDoubleShortProcedure(){
            private boolean first = true;

            public boolean execute(double key, short value) {
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
            out.writeDouble(this._set[i2]);
            out.writeShort(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            double key = in2.readDouble();
            short val = in2.readShort();
            this.put(key, val);
        }
    }

    class TDoubleShortHashIterator
    extends THashPrimitiveIterator
    implements TDoubleShortIterator {
        TDoubleShortHashIterator(TDoubleShortHashMap map) {
            super(map);
        }

        public void advance() {
            this.moveToNextIndex();
        }

        public double key() {
            return TDoubleShortHashMap.this._set[this._index];
        }

        public short value() {
            return TDoubleShortHashMap.this._values[this._index];
        }

        public short setValue(short val) {
            short old = this.value();
            TDoubleShortHashMap.this._values[this._index] = val;
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
                TDoubleShortHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TDoubleShortValueHashIterator
    extends THashPrimitiveIterator
    implements TShortIterator {
        TDoubleShortValueHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public short next() {
            this.moveToNextIndex();
            return TDoubleShortHashMap.this._values[this._index];
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
                TDoubleShortHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TDoubleShortKeyHashIterator
    extends THashPrimitiveIterator
    implements TDoubleIterator {
        TDoubleShortKeyHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public double next() {
            this.moveToNextIndex();
            return TDoubleShortHashMap.this._set[this._index];
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
                TDoubleShortHashMap.this.removeAt(this._index);
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
    implements TShortCollection {
        protected TValueView() {
        }

        @Override
        public TShortIterator iterator() {
            return new TDoubleShortValueHashIterator(TDoubleShortHashMap.this);
        }

        @Override
        public short getNoEntryValue() {
            return TDoubleShortHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TDoubleShortHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TDoubleShortHashMap.this._size;
        }

        @Override
        public boolean contains(short entry) {
            return TDoubleShortHashMap.this.containsValue(entry);
        }

        @Override
        public short[] toArray() {
            return TDoubleShortHashMap.this.values();
        }

        @Override
        public short[] toArray(short[] dest) {
            return TDoubleShortHashMap.this.values(dest);
        }

        @Override
        public boolean add(short entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(short entry) {
            short[] values = TDoubleShortHashMap.this._values;
            double[] set = TDoubleShortHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == 0.0 || set[i2] == 2.0 || entry != values[i2]) continue;
                TDoubleShortHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Short) {
                    short ele = (Short)element;
                    if (TDoubleShortHashMap.this.containsValue(ele)) continue;
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
                if (TDoubleShortHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(short[] array) {
            for (short element : array) {
                if (TDoubleShortHashMap.this.containsValue(element)) continue;
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
            short[] values = TDoubleShortHashMap.this._values;
            byte[] states = TDoubleShortHashMap.this._states;
            int i2 = values.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TDoubleShortHashMap.this.removeAt(i2);
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
            TDoubleShortHashMap.this.clear();
        }

        @Override
        public boolean forEach(TShortProcedure procedure) {
            return TDoubleShortHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TDoubleShortHashMap.this.forEachValue(new TShortProcedure(){
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
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class TKeyView
    implements TDoubleSet {
        protected TKeyView() {
        }

        @Override
        public TDoubleIterator iterator() {
            return new TDoubleShortKeyHashIterator(TDoubleShortHashMap.this);
        }

        @Override
        public double getNoEntryValue() {
            return TDoubleShortHashMap.this.no_entry_key;
        }

        @Override
        public int size() {
            return TDoubleShortHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TDoubleShortHashMap.this._size;
        }

        @Override
        public boolean contains(double entry) {
            return TDoubleShortHashMap.this.contains(entry);
        }

        @Override
        public double[] toArray() {
            return TDoubleShortHashMap.this.keys();
        }

        @Override
        public double[] toArray(double[] dest) {
            return TDoubleShortHashMap.this.keys(dest);
        }

        @Override
        public boolean add(double entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(double entry) {
            return TDoubleShortHashMap.this.no_entry_value != TDoubleShortHashMap.this.remove(entry);
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Double) {
                    double ele = (Double)element;
                    if (TDoubleShortHashMap.this.containsKey(ele)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(TDoubleCollection collection) {
            TDoubleIterator iter = collection.iterator();
            while (iter.hasNext()) {
                if (TDoubleShortHashMap.this.containsKey(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(double[] array) {
            for (double element : array) {
                if (TDoubleShortHashMap.this.contains(element)) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends Double> collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(TDoubleCollection collection) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean addAll(double[] array) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            boolean modified = false;
            TDoubleIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(TDoubleCollection collection) {
            if (this == collection) {
                return false;
            }
            boolean modified = false;
            TDoubleIterator iter = this.iterator();
            while (iter.hasNext()) {
                if (collection.contains(iter.next())) continue;
                iter.remove();
                modified = true;
            }
            return modified;
        }

        @Override
        public boolean retainAll(double[] array) {
            boolean changed = false;
            Arrays.sort(array);
            double[] set = TDoubleShortHashMap.this._set;
            byte[] states = TDoubleShortHashMap.this._states;
            int i2 = set.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, set[i2]) >= 0) continue;
                TDoubleShortHashMap.this.removeAt(i2);
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            boolean changed = false;
            for (Object element : collection) {
                double c2;
                if (!(element instanceof Double) || !this.remove(c2 = ((Double)element).doubleValue())) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(TDoubleCollection collection) {
            if (this == collection) {
                this.clear();
                return true;
            }
            boolean changed = false;
            TDoubleIterator iter = collection.iterator();
            while (iter.hasNext()) {
                double element = iter.next();
                if (!this.remove(element)) continue;
                changed = true;
            }
            return changed;
        }

        @Override
        public boolean removeAll(double[] array) {
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
            TDoubleShortHashMap.this.clear();
        }

        @Override
        public boolean forEach(TDoubleProcedure procedure) {
            return TDoubleShortHashMap.this.forEachKey(procedure);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof TDoubleSet)) {
                return false;
            }
            TDoubleSet that = (TDoubleSet)other;
            if (that.size() != this.size()) {
                return false;
            }
            int i2 = TDoubleShortHashMap.this._states.length;
            while (i2-- > 0) {
                if (TDoubleShortHashMap.this._states[i2] != 1 || that.contains(TDoubleShortHashMap.this._set[i2])) continue;
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            int i2 = TDoubleShortHashMap.this._states.length;
            while (i2-- > 0) {
                if (TDoubleShortHashMap.this._states[i2] != 1) continue;
                hashcode += HashFunctions.hash(TDoubleShortHashMap.this._set[i2]);
            }
            return hashcode;
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TDoubleShortHashMap.this.forEachKey(new TDoubleProcedure(){
                private boolean first = true;

                public boolean execute(double key) {
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

