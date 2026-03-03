/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.list.TLongList;
import gnu.trove.procedure.TLongProcedure;
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
public class TLongLinkedList
implements TLongList,
Externalizable {
    long no_entry_value;
    int size;
    TLongLink head;
    TLongLink tail;

    public TLongLinkedList() {
        this.tail = this.head = null;
    }

    public TLongLinkedList(long no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TLongLinkedList(TLongList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TLongIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            long next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public long getNoEntryValue() {
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
    public boolean add(long val) {
        TLongLink l2 = new TLongLink(val);
        if (TLongLinkedList.no(this.head)) {
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
    public void add(long[] vals) {
        for (long val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(long[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            long val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, long value) {
        TLongLinkedList tmp = new TLongLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, long[] values) {
        this.insert(offset, TLongLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, long[] values, int valOffset, int len) {
        this.insert(offset, TLongLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TLongLinkedList tmp) {
        TLongLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TLongLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TLongLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TLongLinkedList link(long[] values, int valOffset, int len) {
        TLongLinkedList ret = new TLongLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public long get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TLongLink l2 = this.getLinkAt(offset);
        if (TLongLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TLongLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TLongLinkedList.getLink(this.head, 0, offset, true);
        }
        return TLongLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TLongLink getLink(TLongLink l2, int idx, int offset) {
        return TLongLinkedList.getLink(l2, idx, offset, true);
    }

    private static TLongLink getLink(TLongLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TLongLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public long set(int offset, long val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TLongLink l2 = this.getLinkAt(offset);
        if (TLongLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        long prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, long[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, long[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            long value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public long replace(int offset, long val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(long value) {
        boolean changed = false;
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TLongLink l2) {
        if (TLongLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TLongLink prev = l2.getPrevious();
        TLongLink next = l2.getNext();
        if (TLongLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TLongLinkedList.got(next)) {
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
            if (o2 instanceof Long) {
                Long i2 = (Long)o2;
                if (this.contains(i2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TLongCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TLongIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            long i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(long[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (long i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Long> collection) {
        boolean ret = false;
        for (Long l2 : collection) {
            if (!this.add(l2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TLongCollection collection) {
        boolean ret = false;
        TLongIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            long i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(long[] array) {
        boolean ret = false;
        for (long i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TLongIterator iter = this.iterator();
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
        TLongIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TLongCollection collection) {
        boolean modified = false;
        TLongIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(long[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TLongIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public long removeAt(int offset) {
        TLongLink l2 = this.getLinkAt(offset);
        if (TLongLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        long prev = l2.getValue();
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
    public void transformValues(TLongFunction function) {
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TLongLink h2 = this.head;
        TLongLink t2 = this.tail;
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            TLongLink next = l2.getNext();
            TLongLink prev = l2.getPrevious();
            TLongLink tmp = l2;
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
        TLongLink start = this.getLinkAt(from);
        TLongLink stop = this.getLinkAt(to2);
        TLongLink tmp = null;
        TLongLink tmpHead = start.getPrevious();
        for (TLongLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TLongLink next = l2.getNext();
            TLongLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TLongLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TLongLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TLongList subList(int begin, int end) {
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
        TLongLinkedList ret = new TLongLinkedList();
        TLongLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public long[] toArray() {
        return this.toArray(new long[this.size], 0, this.size);
    }

    @Override
    public long[] toArray(int offset, int len) {
        return this.toArray(new long[len], offset, 0, len);
    }

    @Override
    public long[] toArray(long[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public long[] toArray(long[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public long[] toArray(long[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TLongLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TLongProcedure procedure) {
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TLongProcedure procedure) {
        TLongLink l2 = this.tail;
        while (TLongLinkedList.got(l2)) {
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
        TLongList tmp = this.subList(fromIndex, toIndex);
        long[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(long val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, long val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TLongLink l2 = this.getLinkAt(fromIndex);
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
    public int binarySearch(long value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(long value, int fromIndex, int toIndex) {
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
        TLongLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TLongLink middle = TLongLinkedList.getLink(fromLink, from, mid);
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
    public int indexOf(long value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, long value) {
        int count = offset;
        TLongLink l2 = this.getLinkAt(offset);
        while (TLongLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(long value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, long value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TLongLink l2 = this.getLinkAt(offset);
        while (TLongLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(long value) {
        if (this.isEmpty()) {
            return false;
        }
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TLongIterator iterator() {
        return new TLongIterator(){
            TLongLink l;
            TLongLink current;
            {
                this.l = TLongLinkedList.this.head;
            }

            public long next() {
                if (TLongLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                long ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TLongLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TLongLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TLongList grep(TLongProcedure condition) {
        TLongLinkedList ret = new TLongLinkedList();
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TLongList inverseGrep(TLongProcedure condition) {
        TLongLinkedList ret = new TLongLinkedList();
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public long max() {
        long ret = Long.MIN_VALUE;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public long min() {
        long ret = Long.MAX_VALUE;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public long sum() {
        long sum = 0L;
        TLongLink l2 = this.head;
        while (TLongLinkedList.got(l2)) {
            sum += l2.getValue();
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeLong(this.no_entry_value);
        out.writeInt(this.size);
        TLongIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            long next = iterator.next();
            out.writeLong(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readLong();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readLong());
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
        TLongLinkedList that = (TLongLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TLongIterator iterator = this.iterator();
        TLongIterator thatIterator = that.iterator();
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
        TLongIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TLongIterator it2 = this.iterator();
        while (it2.hasNext()) {
            long next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TLongProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(long value) {
            if (TLongLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TLongLink {
        long value;
        TLongLink previous;
        TLongLink next;

        TLongLink(long value) {
            this.value = value;
        }

        public long getValue() {
            return this.value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        public TLongLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TLongLink previous) {
            this.previous = previous;
        }

        public TLongLink getNext() {
            return this.next;
        }

        public void setNext(TLongLink next) {
            this.next = next;
        }
    }
}

