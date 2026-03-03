/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TShortIterator;
import gnu.trove.list.TShortList;
import gnu.trove.procedure.TShortProcedure;
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
public class TShortLinkedList
implements TShortList,
Externalizable {
    short no_entry_value;
    int size;
    TShortLink head;
    TShortLink tail;

    public TShortLinkedList() {
        this.tail = this.head = null;
    }

    public TShortLinkedList(short no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TShortLinkedList(TShortList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TShortIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            short next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public short getNoEntryValue() {
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
    public boolean add(short val) {
        TShortLink l2 = new TShortLink(val);
        if (TShortLinkedList.no(this.head)) {
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
    public void add(short[] vals) {
        for (short val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(short[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            short val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, short value) {
        TShortLinkedList tmp = new TShortLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, short[] values) {
        this.insert(offset, TShortLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, short[] values, int valOffset, int len) {
        this.insert(offset, TShortLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TShortLinkedList tmp) {
        TShortLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TShortLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TShortLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TShortLinkedList link(short[] values, int valOffset, int len) {
        TShortLinkedList ret = new TShortLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public short get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TShortLink l2 = this.getLinkAt(offset);
        if (TShortLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TShortLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TShortLinkedList.getLink(this.head, 0, offset, true);
        }
        return TShortLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TShortLink getLink(TShortLink l2, int idx, int offset) {
        return TShortLinkedList.getLink(l2, idx, offset, true);
    }

    private static TShortLink getLink(TShortLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TShortLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public short set(int offset, short val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TShortLink l2 = this.getLinkAt(offset);
        if (TShortLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        short prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, short[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, short[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            short value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public short replace(int offset, short val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(short value) {
        boolean changed = false;
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TShortLink l2) {
        if (TShortLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TShortLink prev = l2.getPrevious();
        TShortLink next = l2.getNext();
        if (TShortLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TShortLinkedList.got(next)) {
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
            if (o2 instanceof Short) {
                Short i2 = (Short)o2;
                if (this.contains(i2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TShortCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TShortIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            short i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(short[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (short i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Short> collection) {
        boolean ret = false;
        for (Short s2 : collection) {
            if (!this.add(s2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TShortCollection collection) {
        boolean ret = false;
        TShortIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            short i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(short[] array) {
        boolean ret = false;
        for (short i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TShortIterator iter = this.iterator();
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
        TShortIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TShortCollection collection) {
        boolean modified = false;
        TShortIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(short[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TShortIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public short removeAt(int offset) {
        TShortLink l2 = this.getLinkAt(offset);
        if (TShortLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        short prev = l2.getValue();
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
    public void transformValues(TShortFunction function) {
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TShortLink h2 = this.head;
        TShortLink t2 = this.tail;
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            TShortLink next = l2.getNext();
            TShortLink prev = l2.getPrevious();
            TShortLink tmp = l2;
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
        TShortLink start = this.getLinkAt(from);
        TShortLink stop = this.getLinkAt(to2);
        TShortLink tmp = null;
        TShortLink tmpHead = start.getPrevious();
        for (TShortLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TShortLink next = l2.getNext();
            TShortLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TShortLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TShortLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TShortList subList(int begin, int end) {
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
        TShortLinkedList ret = new TShortLinkedList();
        TShortLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public short[] toArray() {
        return this.toArray(new short[this.size], 0, this.size);
    }

    @Override
    public short[] toArray(int offset, int len) {
        return this.toArray(new short[len], offset, 0, len);
    }

    @Override
    public short[] toArray(short[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public short[] toArray(short[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public short[] toArray(short[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TShortLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TShortProcedure procedure) {
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TShortProcedure procedure) {
        TShortLink l2 = this.tail;
        while (TShortLinkedList.got(l2)) {
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
        TShortList tmp = this.subList(fromIndex, toIndex);
        short[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(short val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, short val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TShortLink l2 = this.getLinkAt(fromIndex);
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
    public int binarySearch(short value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(short value, int fromIndex, int toIndex) {
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
        TShortLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TShortLink middle = TShortLinkedList.getLink(fromLink, from, mid);
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
    public int indexOf(short value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, short value) {
        int count = offset;
        TShortLink l2 = this.getLinkAt(offset);
        while (TShortLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(short value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, short value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TShortLink l2 = this.getLinkAt(offset);
        while (TShortLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(short value) {
        if (this.isEmpty()) {
            return false;
        }
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TShortIterator iterator() {
        return new TShortIterator(){
            TShortLink l;
            TShortLink current;
            {
                this.l = TShortLinkedList.this.head;
            }

            public short next() {
                if (TShortLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                short ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TShortLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TShortLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TShortList grep(TShortProcedure condition) {
        TShortLinkedList ret = new TShortLinkedList();
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TShortList inverseGrep(TShortProcedure condition) {
        TShortLinkedList ret = new TShortLinkedList();
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public short max() {
        short ret = Short.MIN_VALUE;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public short min() {
        short ret = Short.MAX_VALUE;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public short sum() {
        short sum = 0;
        TShortLink l2 = this.head;
        while (TShortLinkedList.got(l2)) {
            sum = (short)(sum + l2.getValue());
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeShort(this.no_entry_value);
        out.writeInt(this.size);
        TShortIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            short next = iterator.next();
            out.writeShort(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readShort();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readShort());
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
        TShortLinkedList that = (TShortLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TShortIterator iterator = this.iterator();
        TShortIterator thatIterator = that.iterator();
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
        TShortIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TShortIterator it2 = this.iterator();
        while (it2.hasNext()) {
            short next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TShortProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(short value) {
            if (TShortLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TShortLink {
        short value;
        TShortLink previous;
        TShortLink next;

        TShortLink(short value) {
            this.value = value;
        }

        public short getValue() {
            return this.value;
        }

        public void setValue(short value) {
            this.value = value;
        }

        public TShortLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TShortLink previous) {
            this.previous = previous;
        }

        public TShortLink getNext() {
            return this.next;
        }

        public void setNext(TShortLink next) {
            this.next = next;
        }
    }
}

