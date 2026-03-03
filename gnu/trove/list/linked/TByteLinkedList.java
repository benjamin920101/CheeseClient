/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.TByteCollection;
import gnu.trove.function.TByteFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TByteIterator;
import gnu.trove.list.TByteList;
import gnu.trove.procedure.TByteProcedure;
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
public class TByteLinkedList
implements TByteList,
Externalizable {
    byte no_entry_value;
    int size;
    TByteLink head;
    TByteLink tail;

    public TByteLinkedList() {
        this.tail = this.head = null;
    }

    public TByteLinkedList(byte no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TByteLinkedList(TByteList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TByteIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            byte next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public byte getNoEntryValue() {
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
    public boolean add(byte val) {
        TByteLink l2 = new TByteLink(val);
        if (TByteLinkedList.no(this.head)) {
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
    public void add(byte[] vals) {
        for (byte val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(byte[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            byte val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, byte value) {
        TByteLinkedList tmp = new TByteLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, byte[] values) {
        this.insert(offset, TByteLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, byte[] values, int valOffset, int len) {
        this.insert(offset, TByteLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TByteLinkedList tmp) {
        TByteLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TByteLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TByteLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TByteLinkedList link(byte[] values, int valOffset, int len) {
        TByteLinkedList ret = new TByteLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public byte get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TByteLink l2 = this.getLinkAt(offset);
        if (TByteLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TByteLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TByteLinkedList.getLink(this.head, 0, offset, true);
        }
        return TByteLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TByteLink getLink(TByteLink l2, int idx, int offset) {
        return TByteLinkedList.getLink(l2, idx, offset, true);
    }

    private static TByteLink getLink(TByteLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TByteLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public byte set(int offset, byte val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TByteLink l2 = this.getLinkAt(offset);
        if (TByteLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        byte prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, byte[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, byte[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            byte value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public byte replace(int offset, byte val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(byte value) {
        boolean changed = false;
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TByteLink l2) {
        if (TByteLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TByteLink prev = l2.getPrevious();
        TByteLink next = l2.getNext();
        if (TByteLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TByteLinkedList.got(next)) {
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
            if (o2 instanceof Byte) {
                Byte i2 = (Byte)o2;
                if (this.contains(i2)) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TByteCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TByteIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            byte i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(byte[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (byte i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Byte> collection) {
        boolean ret = false;
        for (Byte by : collection) {
            if (!this.add(by)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TByteCollection collection) {
        boolean ret = false;
        TByteIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            byte i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(byte[] array) {
        boolean ret = false;
        for (byte i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TByteIterator iter = this.iterator();
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
        TByteIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TByteCollection collection) {
        boolean modified = false;
        TByteIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(byte[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TByteIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public byte removeAt(int offset) {
        TByteLink l2 = this.getLinkAt(offset);
        if (TByteLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        byte prev = l2.getValue();
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
    public void transformValues(TByteFunction function) {
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TByteLink h2 = this.head;
        TByteLink t2 = this.tail;
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            TByteLink next = l2.getNext();
            TByteLink prev = l2.getPrevious();
            TByteLink tmp = l2;
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
        TByteLink start = this.getLinkAt(from);
        TByteLink stop = this.getLinkAt(to2);
        TByteLink tmp = null;
        TByteLink tmpHead = start.getPrevious();
        for (TByteLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TByteLink next = l2.getNext();
            TByteLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TByteLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TByteLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TByteList subList(int begin, int end) {
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
        TByteLinkedList ret = new TByteLinkedList();
        TByteLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public byte[] toArray() {
        return this.toArray(new byte[this.size], 0, this.size);
    }

    @Override
    public byte[] toArray(int offset, int len) {
        return this.toArray(new byte[len], offset, 0, len);
    }

    @Override
    public byte[] toArray(byte[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public byte[] toArray(byte[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public byte[] toArray(byte[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TByteLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TByteProcedure procedure) {
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TByteProcedure procedure) {
        TByteLink l2 = this.tail;
        while (TByteLinkedList.got(l2)) {
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
        TByteList tmp = this.subList(fromIndex, toIndex);
        byte[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(byte val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, byte val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TByteLink l2 = this.getLinkAt(fromIndex);
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
    public int binarySearch(byte value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(byte value, int fromIndex, int toIndex) {
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
        TByteLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TByteLink middle = TByteLinkedList.getLink(fromLink, from, mid);
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
    public int indexOf(byte value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, byte value) {
        int count = offset;
        TByteLink l2 = this.getLinkAt(offset);
        while (TByteLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(byte value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, byte value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TByteLink l2 = this.getLinkAt(offset);
        while (TByteLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(byte value) {
        if (this.isEmpty()) {
            return false;
        }
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TByteIterator iterator() {
        return new TByteIterator(){
            TByteLink l;
            TByteLink current;
            {
                this.l = TByteLinkedList.this.head;
            }

            public byte next() {
                if (TByteLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                byte ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TByteLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TByteLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TByteList grep(TByteProcedure condition) {
        TByteLinkedList ret = new TByteLinkedList();
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TByteList inverseGrep(TByteProcedure condition) {
        TByteLinkedList ret = new TByteLinkedList();
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public byte max() {
        byte ret = -128;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public byte min() {
        byte ret = 127;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public byte sum() {
        byte sum = 0;
        TByteLink l2 = this.head;
        while (TByteLinkedList.got(l2)) {
            sum = (byte)(sum + l2.getValue());
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeByte(this.no_entry_value);
        out.writeInt(this.size);
        TByteIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            byte next = iterator.next();
            out.writeByte(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readByte();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readByte());
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
        TByteLinkedList that = (TByteLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TByteIterator iterator = this.iterator();
        TByteIterator thatIterator = that.iterator();
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
        TByteIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TByteIterator it2 = this.iterator();
        while (it2.hasNext()) {
            byte next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TByteProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(byte value) {
            if (TByteLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TByteLink {
        byte value;
        TByteLink previous;
        TByteLink next;

        TByteLink(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return this.value;
        }

        public void setValue(byte value) {
            this.value = value;
        }

        public TByteLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TByteLink previous) {
            this.previous = previous;
        }

        public TByteLink getNext() {
            return this.next;
        }

        public void setNext(TByteLink next) {
            this.next = next;
        }
    }
}

