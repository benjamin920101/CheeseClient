/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

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
import java.util.NoSuchElementException;
import java.util.Random;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TDoubleLinkedList
implements TDoubleList,
Externalizable {
    double no_entry_value;
    int size;
    TDoubleLink head;
    TDoubleLink tail;

    public TDoubleLinkedList() {
        this.tail = this.head = null;
    }

    public TDoubleLinkedList(double no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TDoubleLinkedList(TDoubleList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TDoubleIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            double next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public double getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean add(double val) {
        TDoubleLink l2 = new TDoubleLink(val);
        if (TDoubleLinkedList.no(this.head)) {
            this.head = l2;
            this.tail = l2;
        } else {
            l2.setPrevious(this.tail);
            this.tail.setNext(l2);
            this.tail = l2;
        }
        ++this.size;
        return true;
    }

    @Override
    public void add(double[] vals) {
        for (double val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(double[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            double val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, double value) {
        TDoubleLinkedList tmp = new TDoubleLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, double[] values) {
        this.insert(offset, TDoubleLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, double[] values, int valOffset, int len) {
        this.insert(offset, TDoubleLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TDoubleLinkedList tmp) {
        TDoubleLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TDoubleLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TDoubleLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TDoubleLinkedList link(double[] values, int valOffset, int len) {
        TDoubleLinkedList ret = new TDoubleLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public double get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TDoubleLink l2 = this.getLinkAt(offset);
        if (TDoubleLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TDoubleLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TDoubleLinkedList.getLink(this.head, 0, offset, true);
        }
        return TDoubleLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TDoubleLink getLink(TDoubleLink l2, int idx, int offset) {
        return TDoubleLinkedList.getLink(l2, idx, offset, true);
    }

    private static TDoubleLink getLink(TDoubleLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TDoubleLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public double set(int offset, double val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TDoubleLink l2 = this.getLinkAt(offset);
        if (TDoubleLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        double prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, double[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, double[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            double value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public double replace(int offset, double val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(double value) {
        boolean changed = false;
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TDoubleLink l2) {
        if (TDoubleLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TDoubleLink prev = l2.getPrevious();
        TDoubleLink next = l2.getNext();
        if (TDoubleLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TDoubleLinkedList.got(next)) {
            next.setPrevious(prev);
        } else {
            this.tail = prev;
        }
        l2.setNext(null);
        l2.setPrevious(null);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (this.isEmpty()) {
            return false;
        }
        for (Object o2 : collection) {
            if (o2 instanceof Double) {
                Double i2 = (Double)o2;
                if (this.contains(i2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TDoubleCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TDoubleIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            double i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(double[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (double i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Double> collection) {
        boolean ret = false;
        for (Double d2 : collection) {
            if (!this.add(d2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TDoubleCollection collection) {
        boolean ret = false;
        TDoubleIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            double i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(double[] array) {
        boolean ret = false;
        for (double i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TDoubleIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) >= 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;
        TDoubleIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TDoubleCollection collection) {
        boolean modified = false;
        TDoubleIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(double[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TDoubleIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public double removeAt(int offset) {
        TDoubleLink l2 = this.getLinkAt(offset);
        if (TDoubleLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        double prev = l2.getValue();
        this.removeLink(l2);
        return prev;
    }

    @Override
    public void remove(int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            this.removeAt(offset);
        }
    }

    @Override
    public void transformValues(TDoubleFunction function) {
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TDoubleLink h2 = this.head;
        TDoubleLink t2 = this.tail;
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            TDoubleLink next = l2.getNext();
            TDoubleLink prev = l2.getPrevious();
            TDoubleLink tmp = l2;
            l2 = l2.getNext();
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        this.head = t2;
        this.tail = h2;
    }

    @Override
    public void reverse(int from, int to2) {
        if (from > to2) {
            throw new IllegalArgumentException("from > to : " + from + ">" + to2);
        }
        TDoubleLink start = this.getLinkAt(from);
        TDoubleLink stop = this.getLinkAt(to2);
        TDoubleLink tmp = null;
        TDoubleLink tmpHead = start.getPrevious();
        for (TDoubleLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TDoubleLink next = l2.getNext();
            TDoubleLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TDoubleLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TDoubleLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TDoubleList subList(int begin, int end) {
        if (end < begin) {
            throw new IllegalArgumentException("begin index " + begin + " greater than end index " + end);
        }
        if (this.size < begin) {
            throw new IllegalArgumentException("begin index " + begin + " greater than last index " + this.size);
        }
        if (begin < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        if (end > this.size) {
            throw new IndexOutOfBoundsException("end index < " + this.size);
        }
        TDoubleLinkedList ret = new TDoubleLinkedList();
        TDoubleLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public double[] toArray() {
        return this.toArray(new double[this.size], 0, this.size);
    }

    @Override
    public double[] toArray(int offset, int len) {
        return this.toArray(new double[len], offset, 0, len);
    }

    @Override
    public double[] toArray(double[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public double[] toArray(double[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public double[] toArray(double[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TDoubleLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TDoubleProcedure procedure) {
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TDoubleProcedure procedure) {
        TDoubleLink l2 = this.tail;
        while (TDoubleLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getPrevious();
        }
        return true;
    }

    @Override
    public void sort() {
        this.sort(0, this.size);
    }

    @Override
    public void sort(int fromIndex, int toIndex) {
        TDoubleList tmp = this.subList(fromIndex, toIndex);
        double[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(double val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, double val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TDoubleLink l2 = this.getLinkAt(fromIndex);
        if (toIndex > this.size) {
            int i2;
            for (i2 = fromIndex; i2 < this.size; ++i2) {
                l2.setValue(val);
                l2 = l2.getNext();
            }
            for (i2 = this.size; i2 < toIndex; ++i2) {
                this.add(val);
            }
        } else {
            for (int i3 = fromIndex; i3 < toIndex; ++i3) {
                l2.setValue(val);
                l2 = l2.getNext();
            }
        }
    }

    @Override
    public int binarySearch(double value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(double value, int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        if (toIndex > this.size) {
            throw new IndexOutOfBoundsException("end index > size: " + toIndex + " > " + this.size);
        }
        if (toIndex < fromIndex) {
            return -(fromIndex + 1);
        }
        int from = fromIndex;
        TDoubleLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TDoubleLink middle = TDoubleLinkedList.getLink(fromLink, from, mid);
            if (middle.getValue() == value) {
                return mid;
            }
            if (middle.getValue() < value) {
                from = mid + 1;
                fromLink = middle.next;
                continue;
            }
            to2 = mid - 1;
        }
        return -(from + 1);
    }

    @Override
    public int indexOf(double value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, double value) {
        int count = offset;
        TDoubleLink l2 = this.getLinkAt(offset);
        while (TDoubleLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(double value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, double value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TDoubleLink l2 = this.getLinkAt(offset);
        while (TDoubleLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(double value) {
        if (this.isEmpty()) {
            return false;
        }
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TDoubleIterator iterator() {
        return new TDoubleIterator(){
            TDoubleLink l;
            TDoubleLink current;
            {
                this.l = TDoubleLinkedList.this.head;
            }

            public double next() {
                if (TDoubleLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                double ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TDoubleLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TDoubleLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TDoubleList grep(TDoubleProcedure condition) {
        TDoubleLinkedList ret = new TDoubleLinkedList();
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TDoubleList inverseGrep(TDoubleProcedure condition) {
        TDoubleLinkedList ret = new TDoubleLinkedList();
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public double max() {
        double ret = Double.NEGATIVE_INFINITY;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public double min() {
        double ret = Double.POSITIVE_INFINITY;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public double sum() {
        double sum = 0.0;
        TDoubleLink l2 = this.head;
        while (TDoubleLinkedList.got(l2)) {
            sum += l2.getValue();
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeDouble(this.no_entry_value);
        out.writeInt(this.size);
        TDoubleIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            double next = iterator.next();
            out.writeDouble(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readDouble();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readDouble());
        }
    }

    static boolean got(Object ref) {
        return ref != null;
    }

    static boolean no(Object ref) {
        return ref == null;
    }

    @Override
    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (o2 == null || this.getClass() != o2.getClass()) {
            return false;
        }
        TDoubleLinkedList that = (TDoubleLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TDoubleIterator iterator = this.iterator();
        TDoubleIterator thatIterator = that.iterator();
        while (iterator.hasNext()) {
            if (!thatIterator.hasNext()) {
                return false;
            }
            if (iterator.next() == thatIterator.next()) continue;
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = HashFunctions.hash(this.no_entry_value);
        result = 31 * result + this.size;
        TDoubleIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TDoubleIterator it2 = this.iterator();
        while (it2.hasNext()) {
            double next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TDoubleProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(double value) {
            if (TDoubleLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TDoubleLink {
        double value;
        TDoubleLink previous;
        TDoubleLink next;

        TDoubleLink(double value) {
            this.value = value;
        }

        public double getValue() {
            return this.value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public TDoubleLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TDoubleLink previous) {
            this.previous = previous;
        }

        public TDoubleLink getNext() {
            return this.next;
        }

        public void setNext(TDoubleLink next) {
            this.next = next;
        }
    }
}

