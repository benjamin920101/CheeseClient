/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.array;

import gnu.trove.TIntCollection;
import gnu.trove.function.TIntFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.TIntList;
import gnu.trove.procedure.TIntProcedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TIntArrayList
implements TIntList,
Externalizable {
    static final long serialVersionUID = 1L;
    protected int[] _data;
    protected int _pos;
    protected static final int DEFAULT_CAPACITY = 10;
    protected int no_entry_value;

    public TIntArrayList() {
        this(10, 0);
    }

    public TIntArrayList(int capacity) {
        this(capacity, 0);
    }

    public TIntArrayList(int capacity, int no_entry_value) {
        this._data = new int[capacity];
        this._pos = 0;
        this.no_entry_value = no_entry_value;
    }

    public TIntArrayList(TIntCollection collection) {
        this(collection.size());
        this.addAll(collection);
    }

    public TIntArrayList(int[] values) {
        this(values.length);
        this.add(values);
    }

    protected TIntArrayList(int[] values, int no_entry_value, boolean wrap) {
        if (!wrap) {
            throw new IllegalStateException("Wrong call");
        }
        if (values == null) {
            throw new IllegalArgumentException("values can not be null");
        }
        this._data = values;
        this._pos = values.length;
        this.no_entry_value = no_entry_value;
    }

    public static TIntArrayList wrap(int[] values) {
        return TIntArrayList.wrap(values, 0);
    }

    public static TIntArrayList wrap(int[] values, int no_entry_value) {
        return new TIntArrayList(values, no_entry_value, true){

            public void ensureCapacity(int capacity) {
                if (capacity > this._data.length) {
                    throw new IllegalStateException("Can not grow ArrayList wrapped external array");
                }
            }
        };
    }

    @Override
    public int getNoEntryValue() {
        return this.no_entry_value;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > this._data.length) {
            int newCap = Math.max(this._data.length << 1, capacity);
            int[] tmp = new int[newCap];
            System.arraycopy(this._data, 0, tmp, 0, this._data.length);
            this._data = tmp;
        }
    }

    @Override
    public int size() {
        return this._pos;
    }

    @Override
    public boolean isEmpty() {
        return this._pos == 0;
    }

    public void trimToSize() {
        if (this._data.length > this.size()) {
            int[] tmp = new int[this.size()];
            this.toArray(tmp, 0, tmp.length);
            this._data = tmp;
        }
    }

    @Override
    public boolean add(int val) {
        this.ensureCapacity(this._pos + 1);
        this._data[this._pos++] = val;
        return true;
    }

    @Override
    public void add(int[] vals) {
        this.add(vals, 0, vals.length);
    }

    @Override
    public void add(int[] vals, int offset, int length) {
        this.ensureCapacity(this._pos + length);
        System.arraycopy(vals, offset, this._data, this._pos, length);
        this._pos += length;
    }

    @Override
    public void insert(int offset, int value) {
        if (offset == this._pos) {
            this.add(value);
            return;
        }
        this.ensureCapacity(this._pos + 1);
        System.arraycopy(this._data, offset, this._data, offset + 1, this._pos - offset);
        this._data[offset] = value;
        ++this._pos;
    }

    @Override
    public void insert(int offset, int[] values) {
        this.insert(offset, values, 0, values.length);
    }

    @Override
    public void insert(int offset, int[] values, int valOffset, int len) {
        if (offset == this._pos) {
            this.add(values, valOffset, len);
            return;
        }
        this.ensureCapacity(this._pos + len);
        System.arraycopy(this._data, offset, this._data, offset + len, this._pos - offset);
        System.arraycopy(values, valOffset, this._data, offset, len);
        this._pos += len;
    }

    @Override
    public int get(int offset) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        return this._data[offset];
    }

    public int getQuick(int offset) {
        return this._data[offset];
    }

    @Override
    public int set(int offset, int val) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        int prev_val = this._data[offset];
        this._data[offset] = val;
        return prev_val;
    }

    @Override
    public int replace(int offset, int val) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        int old = this._data[offset];
        this._data[offset] = val;
        return old;
    }

    @Override
    public void set(int offset, int[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, int[] values, int valOffset, int length) {
        if (offset < 0 || offset + length > this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        System.arraycopy(values, valOffset, this._data, offset, length);
    }

    public void setQuick(int offset, int val) {
        this._data[offset] = val;
    }

    @Override
    public void clear() {
        this.clear(10);
    }

    public void clear(int capacity) {
        this._data = new int[capacity];
        this._pos = 0;
    }

    public void reset() {
        this._pos = 0;
        Arrays.fill(this._data, this.no_entry_value);
    }

    public void resetQuick() {
        this._pos = 0;
    }

    @Override
    public boolean remove(int value) {
        for (int index = 0; index < this._pos; ++index) {
            if (value != this._data[index]) continue;
            this.remove(index, 1);
            return true;
        }
        return false;
    }

    @Override
    public int removeAt(int offset) {
        int old = this.get(offset);
        this.remove(offset, 1);
        return old;
    }

    @Override
    public void remove(int offset, int length) {
        if (length == 0) {
            return;
        }
        if (offset < 0 || offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        if (offset == 0) {
            System.arraycopy(this._data, length, this._data, 0, this._pos - length);
        } else if (this._pos - length != offset) {
            System.arraycopy(this._data, offset + length, this._data, offset, this._pos - (offset + length));
        }
        this._pos -= length;
    }

    @Override
    public TIntIterator iterator() {
        return new TIntArrayIterator(0);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (element instanceof Integer) {
                int c2 = (Integer)element;
                if (this.contains(c2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TIntCollection collection) {
        if (this == collection) {
            return true;
        }
        TIntIterator iter = collection.iterator();
        while (iter.hasNext()) {
            int element = iter.next();
            if (this.contains(element)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(int[] array) {
        int i2 = array.length;
        while (i2-- > 0) {
            if (this.contains(array[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        boolean changed = false;
        for (Integer n2 : collection) {
            int e2 = n2;
            if (!this.add(e2)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(TIntCollection collection) {
        boolean changed = false;
        TIntIterator iter = collection.iterator();
        while (iter.hasNext()) {
            int element = iter.next();
            if (!this.add(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean addAll(int[] array) {
        boolean changed = false;
        for (int element : array) {
            if (!this.add(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;
        TIntIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(TIntCollection collection) {
        if (this == collection) {
            return false;
        }
        boolean modified = false;
        TIntIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(int[] array) {
        boolean changed = false;
        Arrays.sort(array);
        int[] data = this._data;
        int i2 = this._pos;
        while (i2-- > 0) {
            if (Arrays.binarySearch(array, data[i2]) >= 0) continue;
            this.remove(i2, 1);
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        for (Object element : collection) {
            int c2;
            if (!(element instanceof Integer) || !this.remove(c2 = ((Integer)element).intValue())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(TIntCollection collection) {
        if (collection == this) {
            this.clear();
            return true;
        }
        boolean changed = false;
        TIntIterator iter = collection.iterator();
        while (iter.hasNext()) {
            int element = iter.next();
            if (!this.remove(element)) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(int[] array) {
        boolean changed = false;
        int i2 = array.length;
        while (i2-- > 0) {
            if (!this.remove(array[i2])) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public void transformValues(TIntFunction function) {
        int i2 = this._pos;
        while (i2-- > 0) {
            this._data[i2] = function.execute(this._data[i2]);
        }
    }

    @Override
    public void reverse() {
        this.reverse(0, this._pos);
    }

    @Override
    public void reverse(int from, int to2) {
        if (from == to2) {
            return;
        }
        if (from > to2) {
            throw new IllegalArgumentException("from cannot be greater than to");
        }
        int i2 = from;
        for (int j2 = to2 - 1; i2 < j2; ++i2, --j2) {
            this.swap(i2, j2);
        }
    }

    @Override
    public void shuffle(Random rand) {
        int i2 = this._pos;
        while (i2-- > 1) {
            this.swap(i2, rand.nextInt(i2));
        }
    }

    private void swap(int i2, int j2) {
        int tmp = this._data[i2];
        this._data[i2] = this._data[j2];
        this._data[j2] = tmp;
    }

    @Override
    public TIntList subList(int begin, int end) {
        if (end < begin) {
            throw new IllegalArgumentException("end index " + end + " greater than begin index " + begin);
        }
        if (begin < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        if (end > this._data.length) {
            throw new IndexOutOfBoundsException("end index < " + this._data.length);
        }
        TIntArrayList list = new TIntArrayList(end - begin);
        for (int i2 = begin; i2 < end; ++i2) {
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public int[] toArray() {
        return this.toArray(0, this._pos);
    }

    @Override
    public int[] toArray(int offset, int len) {
        int[] rv2 = new int[len];
        this.toArray(rv2, offset, len);
        return rv2;
    }

    @Override
    public int[] toArray(int[] dest) {
        int len = dest.length;
        if (dest.length > this._pos) {
            len = this._pos;
            dest[len] = this.no_entry_value;
        }
        this.toArray(dest, 0, len);
        return dest;
    }

    @Override
    public int[] toArray(int[] dest, int offset, int len) {
        if (len == 0) {
            return dest;
        }
        if (offset < 0 || offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        System.arraycopy(this._data, offset, dest, 0, len);
        return dest;
    }

    @Override
    public int[] toArray(int[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        System.arraycopy(this._data, source_pos, dest, dest_pos, len);
        return dest;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof TIntArrayList) {
            TIntArrayList that = (TIntArrayList)other;
            if (that.size() != this.size()) {
                return false;
            }
            int i2 = this._pos;
            while (i2-- > 0) {
                if (this._data[i2] == that._data[i2]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h2 = 0;
        int i2 = this._pos;
        while (i2-- > 0) {
            h2 += HashFunctions.hash(this._data[i2]);
        }
        return h2;
    }

    @Override
    public boolean forEach(TIntProcedure procedure) {
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (procedure.execute(this._data[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TIntProcedure procedure) {
        int i2 = this._pos;
        while (i2-- > 0) {
            if (procedure.execute(this._data[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public void sort() {
        Arrays.sort(this._data, 0, this._pos);
    }

    @Override
    public void sort(int fromIndex, int toIndex) {
        Arrays.sort(this._data, fromIndex, toIndex);
    }

    @Override
    public void fill(int val) {
        Arrays.fill(this._data, 0, this._pos, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, int val) {
        if (toIndex > this._pos) {
            this.ensureCapacity(toIndex);
            this._pos = toIndex;
        }
        Arrays.fill(this._data, fromIndex, toIndex, val);
    }

    @Override
    public int binarySearch(int value) {
        return this.binarySearch(value, 0, this._pos);
    }

    @Override
    public int binarySearch(int value, int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }
        if (toIndex > this._pos) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            int midVal = this._data[mid];
            if (midVal < value) {
                low = mid + 1;
                continue;
            }
            if (midVal > value) {
                high = mid - 1;
                continue;
            }
            return mid;
        }
        return -(low + 1);
    }

    @Override
    public int indexOf(int value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, int value) {
        for (int i2 = offset; i2 < this._pos; ++i2) {
            if (this._data[i2] != value) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(int value) {
        return this.lastIndexOf(this._pos, value);
    }

    @Override
    public int lastIndexOf(int offset, int value) {
        int i2 = offset;
        while (i2-- > 0) {
            if (this._data[i2] != value) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public boolean contains(int value) {
        return this.lastIndexOf(value) >= 0;
    }

    @Override
    public TIntList grep(TIntProcedure condition) {
        TIntArrayList list = new TIntArrayList();
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (!condition.execute(this._data[i2])) continue;
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public TIntList inverseGrep(TIntProcedure condition) {
        TIntArrayList list = new TIntArrayList();
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (condition.execute(this._data[i2])) continue;
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public int max() {
        if (this.size() == 0) {
            throw new IllegalStateException("cannot find maximum of an empty list");
        }
        int max = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (this._data[i2] <= max) continue;
            max = this._data[i2];
        }
        return max;
    }

    @Override
    public int min() {
        if (this.size() == 0) {
            throw new IllegalStateException("cannot find minimum of an empty list");
        }
        int min = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (this._data[i2] >= min) continue;
            min = this._data[i2];
        }
        return min;
    }

    @Override
    public int sum() {
        int sum = 0;
        for (int i2 = 0; i2 < this._pos; ++i2) {
            sum += this._data[i2];
        }
        return sum;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        int end = this._pos - 1;
        for (int i2 = 0; i2 < end; ++i2) {
            buf.append(this._data[i2]);
            buf.append(", ");
        }
        if (this.size() > 0) {
            buf.append(this._data[this._pos - 1]);
        }
        buf.append("}");
        return buf.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeInt(this._pos);
        out.writeInt(this.no_entry_value);
        int len = this._data.length;
        out.writeInt(len);
        for (int i2 = 0; i2 < len; ++i2) {
            out.writeInt(this._data[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._pos = in2.readInt();
        this.no_entry_value = in2.readInt();
        int len = in2.readInt();
        this._data = new int[len];
        for (int i2 = 0; i2 < len; ++i2) {
            this._data[i2] = in2.readInt();
        }
    }

    class TIntArrayIterator
    implements TIntIterator {
        private int cursor = 0;
        int lastRet = -1;

        TIntArrayIterator(int index) {
            this.cursor = index;
        }

        public boolean hasNext() {
            return this.cursor < TIntArrayList.this.size();
        }

        public int next() {
            try {
                int next = TIntArrayList.this.get(this.cursor);
                this.lastRet = this.cursor++;
                return next;
            }
            catch (IndexOutOfBoundsException e2) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (this.lastRet == -1) {
                throw new IllegalStateException();
            }
            try {
                TIntArrayList.this.remove(this.lastRet, 1);
                if (this.lastRet < this.cursor) {
                    --this.cursor;
                }
                this.lastRet = -1;
            }
            catch (IndexOutOfBoundsException e2) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

