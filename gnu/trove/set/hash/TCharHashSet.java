/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.set.hash;

import gnu.trove.TCharCollection;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TCharHash;
import gnu.trove.impl.hash.THashPrimitiveIterator;
import gnu.trove.iterator.TCharIterator;
import gnu.trove.set.TCharSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TCharHashSet
extends TCharHash
implements TCharSet,
Externalizable {
    static final long serialVersionUID = 1L;

    public TCharHashSet() {
    }

    public TCharHashSet(int initialCapacity) {
        super(initialCapacity);
    }

    public TCharHashSet(int initialCapacity, float load_factor) {
        super(initialCapacity, load_factor);
    }

    public TCharHashSet(int initial_capacity, float load_factor, char no_entry_value) {
        super(initial_capacity, load_factor, no_entry_value);
        if (no_entry_value != '\u0000') {
            Arrays.fill(this._set, no_entry_value);
        }
    }

    public TCharHashSet(Collection<? extends Character> collection) {
        this(Math.max(collection.size(), 10));
        this.addAll(collection);
    }

    public TCharHashSet(TCharCollection collection) {
        this(Math.max(collection.size(), 10));
        if (collection instanceof TCharHashSet) {
            TCharHashSet hashset = (TCharHashSet)collection;
            this._loadFactor = hashset._loadFactor;
            this.no_entry_value = hashset.no_entry_value;
            if (this.no_entry_value != '\u0000') {
                Arrays.fill(this._set, this.no_entry_value);
            }
            this.setUp((int)Math.ceil(10.0f / this._loadFactor));
        }
        this.addAll(collection);
    }

    public TCharHashSet(char[] array) {
        this(Math.max(array.length, 10));
        this.addAll(array);
    }

    @Override
    public TCharIterator iterator() {
        return new TCharHashIterator(this);
    }

    @Override
    public char[] toArray() {
        char[] result = new char[this.size()];
        char[] set = this._set;
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
    public char[] toArray(char[] dest) {
        char[] set = this._set;
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
    public boolean add(char val) {
        int index = this.insertKey(val);
        if (index < 0) {
            return false;
        }
        this.postInsertHook(this.consumeFreeSlot);
        return true;
    }

    @Override
    public boolean remove(char val) {
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
            if (element instanceof Character) {
                char c2 = ((Character)element).charValue();
                if (this.contains(c2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TCharCollection collection) {
        TCharIterator iter = collection.iterator();
        while (iter.hasNext()) {
            char element = iter.next();
            if (this.contains(element)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(char[] array) {
        int i2 = array.length;
        while (i2-- > 0) {
            if (this.contains(array[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Character> collection) {
        boolean changed = false;
        for (Character c2 : collection) {
            char e2 = c2.charValue();
            if (!this.add(e2)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(TCharCollection collection) {
        boolean changed = false;
        TCharIterator iter = collection.iterator();
        while (iter.hasNext()) {
            char element = iter.next();
            if (!this.add(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(char[] array) {
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
        TCharIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (collection.contains(Character.valueOf(iter.next()))) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(TCharCollection collection) {
        if (this == collection) {
            return false;
        }
        boolean modified = false;
        TCharIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(char[] array) {
        boolean changed = false;
        Arrays.sort(array);
        char[] set = this._set;
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
            char c2;
            if (!(element instanceof Character) || !this.remove(c2 = ((Character)element).charValue())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(TCharCollection collection) {
        boolean changed = false;
        TCharIterator iter = collection.iterator();
        while (iter.hasNext()) {
            char element = iter.next();
            if (!this.remove(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(char[] array) {
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
        char[] set = this._set;
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
        char[] oldSet = this._set;
        byte[] oldStates = this._states;
        this._set = new char[newCapacity];
        this._states = new byte[newCapacity];
        int i2 = oldCapacity;
        while (i2-- > 0) {
            if (oldStates[i2] != 1) continue;
            char o2 = oldSet[i2];
            int index = this.insertKey(o2);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof TCharSet)) {
            return false;
        }
        TCharSet that = (TCharSet)other;
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
        out.writeChar(this.no_entry_value);
        int i2 = this._states.length;
        while (i2-- > 0) {
            if (this._states[i2] != 1) continue;
            out.writeChar(this._set[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        byte version = in2.readByte();
        super.readExternal(in2);
        int size = in2.readInt();
        if (version >= 1) {
            this._loadFactor = in2.readFloat();
            this.no_entry_value = in2.readChar();
            if (this.no_entry_value != '\u0000') {
                Arrays.fill(this._set, this.no_entry_value);
            }
        }
        this.setUp(size);
        while (size-- > 0) {
            char val = in2.readChar();
            this.add(val);
        }
    }

    class TCharHashIterator
    extends THashPrimitiveIterator
    implements TCharIterator {
        private final TCharHash _hash;

        public TCharHashIterator(TCharHash hash) {
            super(hash);
            this._hash = hash;
        }

        public char next() {
            this.moveToNextIndex();
            return this._hash._set[this._index];
        }
    }
}

