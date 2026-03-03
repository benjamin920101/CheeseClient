/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.array;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TDoubleIterator;
import gnu.trove.list.TDoubleList;
import gnu.trove.procedure.TDoubleProcedure;
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
public class TDoubleArrayList
implements TDoubleList,
Externalizable {
    static final long serialVersionUID = 1L;
    protected double[] _data;
    protected int _pos;
    protected static final int DEFAULT_CAPACITY = 10;
    protected double no_entry_value;

    public TDoubleArrayList() {
        this(10, 0.0);
    }

    public TDoubleArrayList(int capacity) {
        this(capacity, 0.0);
    }

    public TDoubleArrayList(int capacity, double no_entry_value) {
        this._data = new double[capacity];
        this._pos = 0;
        this.no_entry_value = no_entry_value;
    }

    public TDoubleArrayList(TDoubleCollection collection) {
        this(collection.size());
        this.addAll(collection);
    }

    public TDoubleArrayList(double[] values) {
        this(values.length);
        this.add(values);
    }

    protected TDoubleArrayList(double[] values, double no_entry_value, boolean wrap) {
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

    public static TDoubleArrayList wrap(double[] values) {
        return TDoubleArrayList.wrap(values, 0.0);
    }

    public static TDoubleArrayList wrap(double[] values, double no_entry_value) {
        return new TDoubleArrayList(values, no_entry_value, true){

            public void ensureCapacity(int capacity) {
                if (capacity > this._data.length) {
                    throw new IllegalStateException("Can not grow ArrayList wrapped external array");
                }
            }
        };
    }

    @Override
    public double getNoEntryValue() {
        return this.no_entry_value;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > this._data.length) {
            int newCap = Math.max(this._data.length << 1, capacity);
            double[] tmp = new double[newCap];
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
            double[] tmp = new double[this.size()];
            this.toArray(tmp, 0, tmp.length);
            this._data = tmp;
        }
    }

    @Override
    public boolean add(double val) {
        this.ensureCapacity(this._pos + 1);
        this._data[this._pos++] = val;
        return true;
    }

    @Override
    public void add(double[] vals) {
        this.add(vals, 0, vals.length);
    }

    @Override
    public void add(double[] vals, int offset, int length) {
        this.ensureCapacity(this._pos + length);
        System.arraycopy(vals, offset, this._data, this._pos, length);
        this._pos += length;
    }

    @Override
    public void insert(int offset, double value) {
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
    public void insert(int offset, double[] values) {
        this.insert(offset, values, 0, values.length);
    }

    @Override
    public void insert(int offset, double[] values, int valOffset, int len) {
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
    public double get(int offset) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        return this._data[offset];
    }

    public double getQuick(int offset) {
        return this._data[offset];
    }

    @Override
    public double set(int offset, double val) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        double prev_val = this._data[offset];
        this._data[offset] = val;
        return prev_val;
    }

    @Override
    public double replace(int offset, double val) {
        if (offset >= this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        double old = this._data[offset];
        this._data[offset] = val;
        return old;
    }

    @Override
    public void set(int offset, double[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, double[] values, int valOffset, int length) {
        if (offset < 0 || offset + length > this._pos) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        System.arraycopy(values, valOffset, this._data, offset, length);
    }

    public void setQuick(int offset, double val) {
        this._data[offset] = val;
    }

    @Override
    public void clear() {
        this.clear(10);
    }

    public void clear(int capacity) {
        this._data = new double[capacity];
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
    public boolean remove(double value) {
        for (int index = 0; index < this._pos; ++index) {
            if (value != this._data[index]) continue;
            this.remove(index, 1);
            return true;
        }
        return false;
    }

    @Override
    public double removeAt(int offset) {
        double old = this.get(offset);
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
    public TDoubleIterator iterator() {
        return new TDoubleArrayIterator(0);
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
        if (this == collection) {
            return true;
        }
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
        for (double element : array) {
            if (!this.add(element)) continue;
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
        double[] data = this._data;
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
            double c2;
            if (!(element instanceof Double) || !this.remove(c2 = ((Double)element).doubleValue())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(TDoubleCollection collection) {
        if (collection == this) {
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
    public void transformValues(TDoubleFunction function) {
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
        double tmp = this._data[i2];
        this._data[i2] = this._data[j2];
        this._data[j2] = tmp;
    }

    @Override
    public TDoubleList subList(int begin, int end) {
        if (end < begin) {
            throw new IllegalArgumentException("end index " + end + " greater than begin index " + begin);
        }
        if (begin < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        if (end > this._data.length) {
            throw new IndexOutOfBoundsException("end index < " + this._data.length);
        }
        TDoubleArrayList list = new TDoubleArrayList(end - begin);
        for (int i2 = begin; i2 < end; ++i2) {
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public double[] toArray() {
        return this.toArray(0, this._pos);
    }

    @Override
    public double[] toArray(int offset, int len) {
        double[] rv2 = new double[len];
        this.toArray(rv2, offset, len);
        return rv2;
    }

    @Override
    public double[] toArray(double[] dest) {
        int len = dest.length;
        if (dest.length > this._pos) {
            len = this._pos;
            dest[len] = this.no_entry_value;
        }
        this.toArray(dest, 0, len);
        return dest;
    }

    @Override
    public double[] toArray(double[] dest, int offset, int len) {
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
    public double[] toArray(double[] dest, int source_pos, int dest_pos, int len) {
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
        if (other instanceof TDoubleArrayList) {
            TDoubleArrayList that = (TDoubleArrayList)other;
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
    public boolean forEach(TDoubleProcedure procedure) {
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (procedure.execute(this._data[i2])) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TDoubleProcedure procedure) {
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
    public void fill(double val) {
        Arrays.fill(this._data, 0, this._pos, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, double val) {
        if (toIndex > this._pos) {
            this.ensureCapacity(toIndex);
            this._pos = toIndex;
        }
        Arrays.fill(this._data, fromIndex, toIndex, val);
    }

    @Override
    public int binarySearch(double value) {
        return this.binarySearch(value, 0, this._pos);
    }

    @Override
    public int binarySearch(double value, int fromIndex, int toIndex) {
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
            double midVal = this._data[mid];
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
    public int indexOf(double value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, double value) {
        for (int i2 = offset; i2 < this._pos; ++i2) {
            if (this._data[i2] != value) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(double value) {
        return this.lastIndexOf(this._pos, value);
    }

    @Override
    public int lastIndexOf(int offset, double value) {
        int i2 = offset;
        while (i2-- > 0) {
            if (this._data[i2] != value) continue;
            return i2;
        }
        return -1;
    }

    @Override
    public boolean contains(double value) {
        return this.lastIndexOf(value) >= 0;
    }

    @Override
    public TDoubleList grep(TDoubleProcedure condition) {
        TDoubleArrayList list = new TDoubleArrayList();
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (!condition.execute(this._data[i2])) continue;
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public TDoubleList inverseGrep(TDoubleProcedure condition) {
        TDoubleArrayList list = new TDoubleArrayList();
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (condition.execute(this._data[i2])) continue;
            list.add(this._data[i2]);
        }
        return list;
    }

    @Override
    public double max() {
        if (this.size() == 0) {
            throw new IllegalStateException("cannot find maximum of an empty list");
        }
        double max = Double.NEGATIVE_INFINITY;
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (!(this._data[i2] > max)) continue;
            max = this._data[i2];
        }
        return max;
    }

    @Override
    public double min() {
        if (this.size() == 0) {
            throw new IllegalStateException("cannot find minimum of an empty list");
        }
        double min = Double.POSITIVE_INFINITY;
        for (int i2 = 0; i2 < this._pos; ++i2) {
            if (!(this._data[i2] < min)) continue;
            min = this._data[i2];
        }
        return min;
    }

    @Override
    public double sum() {
        double sum = 0.0;
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
        out.writeDouble(this.no_entry_value);
        int len = this._data.length;
        out.writeInt(len);
        for (int i2 = 0; i2 < len; ++i2) {
            out.writeDouble(this._data[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._pos = in2.readInt();
        this.no_entry_value = in2.readDouble();
        int len = in2.readInt();
        this._data = new double[len];
        for (int i2 = 0; i2 < len; ++i2) {
            this._data[i2] = in2.readDouble();
        }
    }

    class TDoubleArrayIterator
    implements TDoubleIterator {
        private int cursor = 0;
        int lastRet = -1;

        TDoubleArrayIterator(int index) {
            this.cursor = index;
        }

        public boolean hasNext() {
            return this.cursor < TDoubleArrayList.this.size();
        }

        public double next() {
            try {
                double next = TDoubleArrayList.this.get(this.cursor);
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
                TDoubleArrayList.this.remove(this.lastRet, 1);
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

