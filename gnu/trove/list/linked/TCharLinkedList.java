/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.TCharCollection;
import gnu.trove.function.TCharFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TCharIterator;
import gnu.trove.list.TCharList;
import gnu.trove.procedure.TCharProcedure;
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
public class TCharLinkedList
implements TCharList,
Externalizable {
    char no_entry_value;
    int size;
    TCharLink head;
    TCharLink tail;

    public TCharLinkedList() {
        this.tail = this.head = null;
    }

    public TCharLinkedList(char no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TCharLinkedList(TCharList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TCharIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            char next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public char getNoEntryValue() {
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
    public boolean add(char val) {
        TCharLink l2 = new TCharLink(val);
        if (TCharLinkedList.no(this.head)) {
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
    public void add(char[] vals) {
        for (char val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(char[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            char val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, char value) {
        TCharLinkedList tmp = new TCharLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, char[] values) {
        this.insert(offset, TCharLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, char[] values, int valOffset, int len) {
        this.insert(offset, TCharLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TCharLinkedList tmp) {
        TCharLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TCharLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TCharLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TCharLinkedList link(char[] values, int valOffset, int len) {
        TCharLinkedList ret = new TCharLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public char get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TCharLink l2 = this.getLinkAt(offset);
        if (TCharLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TCharLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TCharLinkedList.getLink(this.head, 0, offset, true);
        }
        return TCharLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TCharLink getLink(TCharLink l2, int idx, int offset) {
        return TCharLinkedList.getLink(l2, idx, offset, true);
    }

    private static TCharLink getLink(TCharLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TCharLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public char set(int offset, char val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TCharLink l2 = this.getLinkAt(offset);
        if (TCharLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        char prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, char[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, char[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            char value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public char replace(int offset, char val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(char value) {
        boolean changed = false;
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TCharLink l2) {
        if (TCharLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TCharLink prev = l2.getPrevious();
        TCharLink next = l2.getNext();
        if (TCharLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TCharLinkedList.got(next)) {
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
            if (o2 instanceof Character) {
                Character i2 = (Character)o2;
                if (this.contains(i2.charValue())) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TCharCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TCharIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            char i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(char[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (char i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Character> collection) {
        boolean ret = false;
        for (Character c2 : collection) {
            if (!this.add(c2.charValue())) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TCharCollection collection) {
        boolean ret = false;
        TCharIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            char i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(char[] array) {
        boolean ret = false;
        for (char i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TCharIterator iter = this.iterator();
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
        TCharIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(Character.valueOf(iter.next()))) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TCharCollection collection) {
        boolean modified = false;
        TCharIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(char[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TCharIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public char removeAt(int offset) {
        TCharLink l2 = this.getLinkAt(offset);
        if (TCharLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        char prev = l2.getValue();
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
    public void transformValues(TCharFunction function) {
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TCharLink h2 = this.head;
        TCharLink t2 = this.tail;
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            TCharLink next = l2.getNext();
            TCharLink prev = l2.getPrevious();
            TCharLink tmp = l2;
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
        TCharLink start = this.getLinkAt(from);
        TCharLink stop = this.getLinkAt(to2);
        TCharLink tmp = null;
        TCharLink tmpHead = start.getPrevious();
        for (TCharLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TCharLink next = l2.getNext();
            TCharLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TCharLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TCharLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TCharList subList(int begin, int end) {
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
        TCharLinkedList ret = new TCharLinkedList();
        TCharLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public char[] toArray() {
        return this.toArray(new char[this.size], 0, this.size);
    }

    @Override
    public char[] toArray(int offset, int len) {
        return this.toArray(new char[len], offset, 0, len);
    }

    @Override
    public char[] toArray(char[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public char[] toArray(char[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public char[] toArray(char[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TCharLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TCharProcedure procedure) {
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TCharProcedure procedure) {
        TCharLink l2 = this.tail;
        while (TCharLinkedList.got(l2)) {
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
        TCharList tmp = this.subList(fromIndex, toIndex);
        char[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(char val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, char val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TCharLink l2 = this.getLinkAt(fromIndex);
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
    public int binarySearch(char value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(char value, int fromIndex, int toIndex) {
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
        TCharLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TCharLink middle = TCharLinkedList.getLink(fromLink, from, mid);
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
    public int indexOf(char value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, char value) {
        int count = offset;
        TCharLink l2 = this.getLinkAt(offset);
        while (TCharLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(char value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, char value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TCharLink l2 = this.getLinkAt(offset);
        while (TCharLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(char value) {
        if (this.isEmpty()) {
            return false;
        }
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TCharIterator iterator() {
        return new TCharIterator(){
            TCharLink l;
            TCharLink current;
            {
                this.l = TCharLinkedList.this.head;
            }

            public char next() {
                if (TCharLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                char ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TCharLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TCharLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TCharList grep(TCharProcedure condition) {
        TCharLinkedList ret = new TCharLinkedList();
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TCharList inverseGrep(TCharProcedure condition) {
        TCharLinkedList ret = new TCharLinkedList();
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public char max() {
        char ret = '\u0000';
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public char min() {
        char ret = '\uffff';
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public char sum() {
        char sum = '\u0000';
        TCharLink l2 = this.head;
        while (TCharLinkedList.got(l2)) {
            sum = (char)(sum + l2.getValue());
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeChar(this.no_entry_value);
        out.writeInt(this.size);
        TCharIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            char next = iterator.next();
            out.writeChar(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readChar();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readChar());
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
        TCharLinkedList that = (TCharLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TCharIterator iterator = this.iterator();
        TCharIterator thatIterator = that.iterator();
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
        TCharIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TCharIterator it2 = this.iterator();
        while (it2.hasNext()) {
            char next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TCharProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(char value) {
            if (TCharLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TCharLink {
        char value;
        TCharLink previous;
        TCharLink next;

        TCharLink(char value) {
            this.value = value;
        }

        public char getValue() {
            return this.value;
        }

        public void setValue(char value) {
            this.value = value;
        }

        public TCharLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TCharLink previous) {
            this.previous = previous;
        }

        public TCharLink getNext() {
            return this.next;
        }

        public void setNext(TCharLink next) {
            this.next = next;
        }
    }
}

