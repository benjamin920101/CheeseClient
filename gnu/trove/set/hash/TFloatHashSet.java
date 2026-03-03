/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.set.hash;

import gnu.trove.TFloatCollection;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TFloatHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.iterator.TFloatIterator;
import gnu.trove.set.TFloatSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TFloatHashSet
extends TFloatHash
implements TFloatSet,
Externalizable {
    static final long serialVersionUID = 1L;

    public TFloatHashSet() {
    }

    public TFloatHashSet(int initialCapacity) {
        super(initialCapacity);
    }

    public TFloatHashSet(int initialCapacity, float load_factor) {
        super(initialCapacity, load_factor);
    }

    public TFloatHashSet(int initial_capacity, float load_factor, float no_entry_value) {
        super(initial_capacity, load_factor, no_entry_value);
        if (no_entry_value != 0.0f) {
            Arrays.fill(this._set, no_entry_value);
        }
    }

    public TFloatHashSet(Collection<? extends Float> collection) {
        this(Math.max(collection.size(), 10));
        this.addAll(collection);
    }

    public TFloatHashSet(TFloatCollection collection) {
        this(Math.max(collection.size(), 10));
        if (collection instanceof TFloatHashSet) {
            TFloatHashSet hashset = (TFloatHashSet)collection;
            this._loadFactor = hashset._loadFactor;
            this.no_entry_value = hashset.no_entry_value;
            if (this.no_entry_value != 0.0f) {
                Arrays.fill(this._set, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.addAll(collection);
    }

    public TFloatHashSet(float[] array) {
        this(Math.max(array.length, 10));
        this.addAll(array);
    }

    @Override
    public TFloatIterator iterator() {
        return new TFloatHashIterator(this);
    }

    @Override
    public float[] toArray() {
        float[] result = new float[this.size()];
        float[] set = this._set;
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
    public float[] toArray(float[] dest) {
        float[] set = this._set;
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
    public boolean add(float val) {
        int index = this.insertKey(val);
        if (index < 0) {
            return false;
        }
        this.postInsertHook(this.consumeFreeSlot);
        return true;
    }

    @Override
    public boolean remove(float val) {
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
            if (element instanceof Float) {
                float c2 = ((Float)element).floatValue();
                if (this.contains(c2)) continue;
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
            float element = iter.next();
            if (this.contains(element)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(float[] array) {
        int i2 = array.length;
        while (i2-- > 0) {
            if (this.contains(array[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Float> collection) {
        boolean changed = false;
        for (Float f2 : collection) {
            float e2 = f2.floatValue();
            if (!this.add(e2)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(TFloatCollection collection) {
        boolean changed = false;
        TFloatIterator iter = collection.iterator();
        while (iter.hasNext()) {
            float element = iter.next();
            if (!this.add(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(float[] array) {
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
        float[] set = this._set;
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
            float c2;
            if (!(element instanceof Float) || !this.remove(c2 = ((Float)element).floatValue())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(TFloatCollection collection) {
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
        super.clear();
        float[] set = this._set;
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
        float[] oldSet = this._set;
        byte[] oldStates = this._states;
        this._set = new float[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            float o2 = oldSet[i2];
            int index = this.insertKey(o2);
        }
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
        out.writeFloat(this.no_entry_value);
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            out.writeFloat(this._set[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        byte version = in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        if (version >= 1) {
            this._loadFactor = in2.readFloat();
            this.no_entry_value = in2.readFloat();
            if (this.no_entry_value != 0.0f) {
                Arrays.fill(this._set, this.no_entry_value);
            }
        }
        this.setUp(size);
        while (size-- > 0) {
            float val = in2.readFloat();
            this.add(val);
        }
    }

    class TFloatHashIterator
    extends THashPrimitiveIterator
    implements TFloatIterator {
        private final TFloatHash _hash;

        public TFloatHashIterator(TFloatHash hash) {
            super(hash);
            this._hash = hash;
        }

        public float next() {
            this.moveToNextIndex();
            return this._hash._set[this._index];
        }
    }
}

