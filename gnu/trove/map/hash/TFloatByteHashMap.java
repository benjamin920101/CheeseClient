/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map.hash;

import gnu.trove.TByteCollection;
import gnu.trove.TFloatCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TFloatByteHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.impl.hash.TPrimitiveHash;
import gnu.trove.iterator.TByteIterator;
import gnu.trove.iterator.TFloatByteIterator;
import gnu.trove.iterator.TFloatIterator;
import gnu.trove.map.TFloatByteMap;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TFloatByteProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.set.TFloatSet;
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
public class TFloatByteHashMap
extends TFloatByteHash
implements TFloatByteMap,
Externalizable {
    static final long serialVersionUID = 1L;
    protected transient byte[] _values;

    public TFloatByteHashMap() {
    }

    public TFloatByteHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public TFloatByteHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public TFloatByteHashMap(int initialCapacity, float loadFactor, float noEntryKey, byte noEntryValue) {
        super(initialCapacity, loadFactor, noEntryKey, noEntryValue);
    }

    public TFloatByteHashMap(float[] keys, byte[] values) {
        super(Math.max(keys.length, values.length));
        int size = Math.min(keys.length, values.length);
        for (int i2 = 0; i2 < size; ++i2) {
            this.put(keys[i2], values[i2]);
        }
    }

    public TFloatByteHashMap(TFloatByteMap map) {
        super(map.size());
        if (map instanceof TFloatByteHashMap) {
            TFloatByteHashMap hashmap = (TFloatByteHashMap)map;
            this._loadFactor = hashmap._loadFactor;
            this.no_entry_key = hashmap.no_entry_key;
            this.no_entry_value = hashmap.no_entry_value;
            if (this.no_entry_key != 0.0f) {
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
        this._values = new byte[capacity];
        return capacity;
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        float[] oldKeys = this._set;
        byte[] oldVals = this._values;
        byte[] oldStates = this._states;
        this._set = new float[newCapacity];
        this._values = new byte[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            float o2 = oldKeys[i2];
            int index = this.insertKey(o2);
            this._values[index] = oldVals[i2];
        }
    }

    @Override
    public byte put(float key, byte value) {
        int index = this.insertKey(key);
        return this.doPut(key, value, index);
    }

    @Override
    public byte putIfAbsent(float key, byte value) {
        int index = this.insertKey(key);
        if (index < 0) {
            return this._values[-index - 1];
        }
        return this.doPut(key, value, index);
    }

    private byte doPut(float key, byte value, int index) {
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
    public void putAll(Map<? extends Float, ? extends Byte> map) {
        this.ensureCapacity(map.size());
        for (Map.Entry<? extends Float, ? extends Byte> entry : map.entrySet()) {
            this.put(entry.getKey().floatValue(), entry.getValue());
        }
    }

    @Override
    public void putAll(TFloatByteMap map) {
        this.ensureCapacity(map.size());
        TFloatByteIterator iter = map.iterator();
        while (iter.hasNext()) {
            iter.advance();
            this.put(iter.key(), iter.value());
        }
    }

    @Override
    public byte get(float key) {
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
    public byte remove(float key) {
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
    public TFloatSet keySet() {
        return new TKeyView();
    }

    @Override
    public float[] keys() {
        float[] keys = new float[this.size()];
        float[] k2 = this._set;
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
    public float[] keys(float[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new float[size];
        }
        float[] keys = this._set;
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
    public TByteCollection valueCollection() {
        return new TValueView();
    }

    @Override
    public byte[] values() {
        byte[] vals = new byte[this.size()];
        byte[] v2 = this._values;
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
    public byte[] values(byte[] array) {
        int size = this.size();
        if (array.length < size) {
            array = new byte[size];
        }
        byte[] v2 = this._values;
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
    public boolean containsValue(byte val) {
        byte[] states = this._states;
        byte[] vals = this._values;
        int i2 = vals.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || val != vals[i2]) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsKey(float key) {
        return this.contains(key);
    }

    @Override
    public TFloatByteIterator iterator() {
        return new TFloatByteHashIterator(this);
    }

    @Override
    public boolean forEachKey(TFloatProcedure procedure) {
        return this.forEach(procedure);
    }

    @Override
    public boolean forEachValue(TByteProcedure procedure) {
        byte[] states = this._states;
        byte[] values = this._values;
        int i2 = values.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachEntry(TFloatByteProcedure procedure) {
        byte[] states = this._states;
        float[] keys = this._set;
        byte[] values = this._values;
        int i2 = keys.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || procedure.execute(keys[i2], values[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void transformValues(TByteFunction function) {
        byte[] states = this._states;
        byte[] values = this._values;
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
    public boolean retainEntries(TFloatByteProcedure procedure) {
        boolean modified = false;
        byte[] states = this._states;
        float[] keys = this._set;
        byte[] values = this._values;
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
    public boolean increment(float key) {
        return this.adjustValue(key, (byte)1);
    }

    @Override
    public boolean adjustValue(float key, byte amount) {
        int index = this.index(key);
        if (index < 0) {
            return false;
        }
        int n2 = index;
        this._values[n2] = (byte)(this._values[n2] + amount);
        return true;
    }

    @Override
    public byte adjustOrPutValue(float key, byte adjust_amount, byte put_amount) {
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
        byte previousState = this._states[index];
        if (isNewMapping) {
            this.postInsertHook(this.consumeFreeSlot);
        }
        return newValue;
    }

    public boolean equals(Object other) {
        if (!(other instanceof TFloatByteMap)) {
            return false;
        }
        TFloatByteMap that = (TFloatByteMap)other;
        if (that.size() != this.size()) {
            return false;
        }
        byte[] values = this._values;
        byte[] states = this._states;
        byte this_no_entry_value = this.getNoEntryValue();
        byte that_no_entry_value = that.getNoEntryValue();
        int i2 = values.length;
        while (i2-- > 0) {
            float key;
            byte that_value;
            byte this_value;
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
        this.forEachEntry(new TFloatByteProcedure(){
            private boolean first = true;

            public boolean execute(float key, byte value) {
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
            out.writeFloat(this._set[i2]);
            out.writeByte(this._values[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            float key = in2.readFloat();
            byte val = in2.readByte();
            this.put(key, val);
        }
    }

    class TFloatByteHashIterator
    extends THashPrimitiveIterator
    implements TFloatByteIterator {
        TFloatByteHashIterator(TFloatByteHashMap map) {
            super(map);
        }

        public void advance() {
            this.moveToNextIndex();
        }

        public float key() {
            return TFloatByteHashMap.this._set[this._index];
        }

        public byte value() {
            return TFloatByteHashMap.this._values[this._index];
        }

        public byte setValue(byte val) {
            byte old = this.value();
            TFloatByteHashMap.this._values[this._index] = val;
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
                TFloatByteHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TFloatByteValueHashIterator
    extends THashPrimitiveIterator
    implements TByteIterator {
        TFloatByteValueHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public byte next() {
            this.moveToNextIndex();
            return TFloatByteHashMap.this._values[this._index];
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
                TFloatByteHashMap.this.removeAt(this._index);
            }
            finally {
                this._hash.reenableAutoCompaction(false);
            }
            --this._expectedSize;
        }
    }

    class TFloatByteKeyHashIterator
    extends THashPrimitiveIterator
    implements TFloatIterator {
        TFloatByteKeyHashIterator(TPrimitiveHash hash) {
            super(hash);
        }

        public float next() {
            this.moveToNextIndex();
            return TFloatByteHashMap.this._set[this._index];
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
                TFloatByteHashMap.this.removeAt(this._index);
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
    implements TByteCollection {
        protected TValueView() {
        }

        @Override
        public TByteIterator iterator() {
            return new TFloatByteValueHashIterator(TFloatByteHashMap.this);
        }

        @Override
        public byte getNoEntryValue() {
            return TFloatByteHashMap.this.no_entry_value;
        }

        @Override
        public int size() {
            return TFloatByteHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TFloatByteHashMap.this._size;
        }

        @Override
        public boolean contains(byte entry) {
            return TFloatByteHashMap.this.containsValue(entry);
        }

        @Override
        public byte[] toArray() {
            return TFloatByteHashMap.this.values();
        }

        @Override
        public byte[] toArray(byte[] dest) {
            return TFloatByteHashMap.this.values(dest);
        }

        @Override
        public boolean add(byte entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(byte entry) {
            byte[] values = TFloatByteHashMap.this._values;
            float[] set = TFloatByteHashMap.this._set;
            int i2 = values.length;
            while (i2-- > 0) {
                if (set[i2] == 0.0f || set[i2] == 2.0f || entry != values[i2]) continue;
                TFloatByteHashMap.this.removeAt(i2);
                return true;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Byte) {
                    byte ele = (Byte)element;
                    if (TFloatByteHashMap.this.containsValue(ele)) continue;
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
                if (TFloatByteHashMap.this.containsValue(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(byte[] array) {
            for (byte element : array) {
                if (TFloatByteHashMap.this.containsValue(element)) continue;
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
            byte[] values = TFloatByteHashMap.this._values;
            byte[] states = TFloatByteHashMap.this._states;
            int i2 = values.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, values[i2]) >= 0) continue;
                TFloatByteHashMap.this.removeAt(i2);
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
            TFloatByteHashMap.this.clear();
        }

        @Override
        public boolean forEach(TByteProcedure procedure) {
            return TFloatByteHashMap.this.forEachValue(procedure);
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TFloatByteHashMap.this.forEachValue(new TByteProcedure(){
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
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    protected class TKeyView
    implements TFloatSet {
        protected TKeyView() {
        }

        @Override
        public TFloatIterator iterator() {
            return new TFloatByteKeyHashIterator(TFloatByteHashMap.this);
        }

        @Override
        public float getNoEntryValue() {
            return TFloatByteHashMap.this.no_entry_key;
        }

        @Override
        public int size() {
            return TFloatByteHashMap.this._size;
        }

        @Override
        public boolean isEmpty() {
            return 0 == TFloatByteHashMap.this._size;
        }

        @Override
        public boolean contains(float entry) {
            return TFloatByteHashMap.this.contains(entry);
        }

        @Override
        public float[] toArray() {
            return TFloatByteHashMap.this.keys();
        }

        @Override
        public float[] toArray(float[] dest) {
            return TFloatByteHashMap.this.keys(dest);
        }

        @Override
        public boolean add(float entry) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(float entry) {
            return TFloatByteHashMap.this.no_entry_value != TFloatByteHashMap.this.remove(entry);
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            for (Object element : collection) {
                if (element instanceof Float) {
                    float ele = ((Float)element).floatValue();
                    if (TFloatByteHashMap.this.containsKey(ele)) continue;
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
                if (TFloatByteHashMap.this.containsKey(iter.next())) continue;
                return false;
            }
            return true;
        }

        @Override
        public boolean containsAll(float[] array) {
            for (float element : array) {
                if (TFloatByteHashMap.this.contains(element)) continue;
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
            float[] set = TFloatByteHashMap.this._set;
            byte[] states = TFloatByteHashMap.this._states;
            int i2 = set.length;
            while (i2-- > 0) {
                if (states[i2] != 1 || Arrays.binarySearch(array, set[i2]) >= 0) continue;
                TFloatByteHashMap.this.removeAt(i2);
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
            TFloatByteHashMap.this.clear();
        }

        @Override
        public boolean forEach(TFloatProcedure procedure) {
            return TFloatByteHashMap.this.forEachKey(procedure);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof TFloatSet)) {
                return false;
            }
            TFloatSet that = (TFloatSet)other;
            if (that.size() != this.size()) {
                return false;
            }
            int i2 = TFloatByteHashMap.this._states.length;
            while (i2-- > 0) {
                if (TFloatByteHashMap.this._states[i2] != 1 || that.contains(TFloatByteHashMap.this._set[i2])) continue;
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hashcode = 0;
            int i2 = TFloatByteHashMap.this._states.length;
            while (i2-- > 0) {
                if (TFloatByteHashMap.this._states[i2] != 1) continue;
                hashcode += HashFunctions.hash(TFloatByteHashMap.this._set[i2]);
            }
            return hashcode;
        }

        public String toString() {
            final StringBuilder buf = new StringBuilder("{");
            TFloatByteHashMap.this.forEachKey(new TFloatProcedure(){
                private boolean first = true;

                public boolean execute(float key) {
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

