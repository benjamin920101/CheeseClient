/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.set.hash;

import gnu.trove.TDoubleCollection;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TDoubleHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.set.TDoubleSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TDoubleHashSet
extends TDoubleHash
implements TDoubleSet,
Externalizable {
    static final long serialVersionUID = 1L;

    public TDoubleHashSet() {
    }

    public TDoubleHashSet(int initialCapacity) {
        super(initialCapacity);
    }

    public TDoubleHashSet(int initialCapacity, float load_factor) {
        super(initialCapacity, load_factor);
    }

    public TDoubleHashSet(int initial_capacity, float load_factor, double no_entry_value) {
        super(initial_capacity, load_factor, no_entry_value);
        if (no_entry_value != 0.0) {
            Arrays.fill(this._set, no_entry_value);
        }
    }

    public TDoubleHashSet(Collection<? extends Double> collection) {
        this(Math.max(collection.size(), 10));
        this.addAll(collection);
    }

    public TDoubleHashSet(TDoubleCollection collection) {
        this(Math.max(collection.size(), 10));
        if (collection instanceof TDoubleHashSet) {
            TDoubleHashSet hashset = (TDoubleHashSet)collection;
            this._loadFactor = hashset._loadFactor;
            this.no_entry_value = hashset.no_entry_value;
            if (this.no_entry_value != 0.0) {
                Arrays.fill(this._set, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.addAll(collection);
    }

    public TDoubleHashSet(double[] array) {
        this(Math.max(array.length, 10));
        this.addAll(array);
    }

    @Override
    public TDoubleIterator iterator() {
        return new TDoubleHashIterator(this);
    }

    @Override
    public double[] toArray() {
        double[] result = new double[this.size()];
        double[] set = this._set;
        byte[] states = this._states;
        int i2 = states.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            result[j2++] = set[i2];
        }
        return result;
    }

    @Override
    public double[] toArray(double[] dest) {
        double[] set = this._set;
        byte[] states = this._states;
        int i2 = states.length;
        int j2 = 0;
        while (i2-- > 0) {
            if (states[i2] != 1) continue;
            dest[j2++] = set[i2];
        }
        if (dest.length > this._size) {
            dest[this._size] = this.no_entry_value;
        }
        return dest;
    }

    @Override
    public boolean add(double val) {
        int index = this.insertKey(val);
        if (index < 0) {
            return false;
        }
        this.postInsertHook(this.consumeFreeSlot);
        return true;
    }

    @Override
    public boolean remove(double val) {
        int index = this.index(val);
        if (index >= 0) {
            this.removeAt(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (element instanceof Double) {
                double c2 = (Double)element;
                if (this.contains(c2)) continue;
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
            double element = iter.next();
            if (this.contains(element)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(double[] array) {
        int i2 = array.length;
        while (i2-- > 0) {
            if (this.contains(array[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Double> collection) {
        boolean changed = false;
        for (Double d2 : collection) {
            double e2 = d2;
            if (!this.add(e2)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(TDoubleCollection collection) {
        boolean changed = false;
        TDoubleIterator iter = collection.iterator();
        while (iter.hasNext()) {
            double element = iter.next();
            if (!this.add(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(double[] array) {
        boolean changed = false;
        int i2 = array.length;
        while (i2-- > 0) {
            if (!this.add(array[i2])) continue;
            changed = true;
        }
        return changed;
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
        double[] set = this._set;
        byte[] states = this._states;
        this._autoCompactTemporaryDisable = true;
        int i2 = set.length;
        while (i2-- > 0) {
            if (states[i2] != 1 || Arrays.binarySearch(array, set[i2]) >= 0) continue;
            this.removeAt(i2);
            changed = true;
        }
        this._autoCompactTemporaryDisable = false;
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
        super.clear();
        double[] set = this._set;
        byte[] states = this._states;
        int i2 = set.length;
        while (i2-- > 0) {
            set[i2] = this.no_entry_value;
            states[i2] = 0;
        }
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        double[] oldSet = this._set;
        byte[] oldStates = this._states;
        this._set = new double[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            double o2 = oldSet[i2];
            int index = this.insertKey(o2);
        }
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
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1 || that.contains(this._set[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            hashcode += HashFunctions.hash(this._set[i2]);
        }
        return hashcode;
    }

    public String toString() {
        StringBuilder buffy = new StringBuilder(this._size * 2 + 2);
        buffy.append("{");
        int i2 = this._states.length;
        int j2 = 1;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            buffy.append(this._set[i2]);
            if (j2++ >= this._size) continue;
            buffy.append(",");
        }
        buffy.append("}");
        return buffy.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(1);
        super.writeExternal(out);
        out.writeInt(this._size);
        out.writeFloat(this._loadFactor);
        out.writeDouble(this.no_entry_value);
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            out.writeDouble(this._set[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        byte version = in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        if (version >= 1) {
            this._loadFactor = in2.readFloat();
            this.no_entry_value = in2.readDouble();
            if (this.no_entry_value != 0.0) {
                Arrays.fill(this._set, this.no_entry_value);
            }
        }
        this.setUp(size);
        while (size-- > 0) {
            double val = in2.readDouble();
            this.add(val);
        }
    }

    class TDoubleHashIterator
    extends THashPrimitiveIterator
    implements TDoubleIterator {
        private final TDoubleHash _hash;

        public TDoubleHashIterator(TDoubleHash hash) {
            super(hash);
            this._hash = hash;
        }

        public double next() {
            this.moveToNextIndex();
            return this._hash._set[this._index];
        }
    }
}

