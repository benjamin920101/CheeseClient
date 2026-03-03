/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.list.linked;

import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.impl.HashFunctions;
import gnu.trove.iterator.TFloatIterator;
import gnu.trove.list.TFloatList;
import gnu.trove.procedure.TFloatProcedure;
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
public class TFloatLinkedList
implements TFloatList,
Externalizable {
    float no_entry_value;
    int size;
    TFloatLink head;
    TFloatLink tail;

    public TFloatLinkedList() {
        this.tail = this.head = null;
    }

    public TFloatLinkedList(float no_entry_value) {
        this.tail = this.head = null;
        this.no_entry_value = no_entry_value;
    }

    public TFloatLinkedList(TFloatList list) {
        this.tail = this.head = null;
        this.no_entry_value = list.getNoEntryValue();
        TFloatIterator iterator = list.iterator();
        while (iterator.hasNext()) {
            float next = iterator.next();
            this.add(next);
        }
    }

    @Override
    public float getNoEntryValue() {
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
    public boolean add(float val) {
        TFloatLink l2 = new TFloatLink(val);
        if (TFloatLinkedList.no(this.head)) {
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
    public void add(float[] vals) {
        for (float val : vals) {
            this.add(val);
        }
    }

    @Override
    public void add(float[] vals, int offset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            float val = vals[offset + i2];
            this.add(val);
        }
    }

    @Override
    public void insert(int offset, float value) {
        TFloatLinkedList tmp = new TFloatLinkedList();
        tmp.add(value);
        this.insert(offset, tmp);
    }

    @Override
    public void insert(int offset, float[] values) {
        this.insert(offset, TFloatLinkedList.link(values, 0, values.length));
    }

    @Override
    public void insert(int offset, float[] values, int valOffset, int len) {
        this.insert(offset, TFloatLinkedList.link(values, valOffset, len));
    }

    void insert(int offset, TFloatLinkedList tmp) {
        TFloatLink l2 = this.getLinkAt(offset);
        this.size += tmp.size;
        if (l2 == this.head) {
            tmp.tail.setNext(this.head);
            this.head.setPrevious(tmp.tail);
            this.head = tmp.head;
            return;
        }
        if (TFloatLinkedList.no(l2)) {
            if (this.size == 0) {
                this.head = tmp.head;
                this.tail = tmp.tail;
            } else {
                this.tail.setNext(tmp.head);
                tmp.head.setPrevious(this.tail);
                this.tail = tmp.tail;
            }
        } else {
            TFloatLink prev = l2.getPrevious();
            l2.getPrevious().setNext(tmp.head);
            tmp.tail.setNext(l2);
            l2.setPrevious(tmp.tail);
            tmp.head.setPrevious(prev);
        }
    }

    static TFloatLinkedList link(float[] values, int valOffset, int len) {
        TFloatLinkedList ret = new TFloatLinkedList();
        for (int i2 = 0; i2 < len; ++i2) {
            ret.add(values[valOffset + i2]);
        }
        return ret;
    }

    @Override
    public float get(int offset) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TFloatLink l2 = this.getLinkAt(offset);
        if (TFloatLinkedList.no(l2)) {
            return this.no_entry_value;
        }
        return l2.getValue();
    }

    public TFloatLink getLinkAt(int offset) {
        if (offset >= this.size()) {
            return null;
        }
        if (offset <= this.size() >>> 1) {
            return TFloatLinkedList.getLink(this.head, 0, offset, true);
        }
        return TFloatLinkedList.getLink(this.tail, this.size() - 1, offset, false);
    }

    private static TFloatLink getLink(TFloatLink l2, int idx, int offset) {
        return TFloatLinkedList.getLink(l2, idx, offset, true);
    }

    private static TFloatLink getLink(TFloatLink l2, int idx, int offset, boolean next) {
        int i2 = idx;
        while (TFloatLinkedList.got(l2)) {
            if (i2 == offset) {
                return l2;
            }
            i2 += next ? 1 : -1;
            l2 = next ? l2.getNext() : l2.getPrevious();
        }
        return null;
    }

    @Override
    public float set(int offset, float val) {
        if (offset > this.size) {
            throw new IndexOutOfBoundsException("index " + offset + " exceeds size " + this.size);
        }
        TFloatLink l2 = this.getLinkAt(offset);
        if (TFloatLinkedList.no(l2)) {
            throw new IndexOutOfBoundsException("at offset " + offset);
        }
        float prev = l2.getValue();
        l2.setValue(val);
        return prev;
    }

    @Override
    public void set(int offset, float[] values) {
        this.set(offset, values, 0, values.length);
    }

    @Override
    public void set(int offset, float[] values, int valOffset, int length) {
        for (int i2 = 0; i2 < length; ++i2) {
            float value = values[valOffset + i2];
            this.set(offset + i2, value);
        }
    }

    @Override
    public float replace(int offset, float val) {
        return this.set(offset, val);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean remove(float value) {
        boolean changed = false;
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                changed = true;
                this.removeLink(l2);
            }
            l2 = l2.getNext();
        }
        return changed;
    }

    private void removeLink(TFloatLink l2) {
        if (TFloatLinkedList.no(l2)) {
            return;
        }
        --this.size;
        TFloatLink prev = l2.getPrevious();
        TFloatLink next = l2.getNext();
        if (TFloatLinkedList.got(prev)) {
            prev.setNext(next);
        } else {
            this.head = next;
        }
        if (TFloatLinkedList.got(next)) {
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
            if (o2 instanceof Float) {
                Float i2 = (Float)o2;
                if (this.contains(i2.floatValue())) continue;
                return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(TFloatCollection collection) {
        if (this.isEmpty()) {
            return false;
        }
        TFloatIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            float i2 = it2.next();
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean containsAll(float[] array) {
        if (this.isEmpty()) {
            return false;
        }
        for (float i2 : array) {
            if (this.contains(i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Float> collection) {
        boolean ret = false;
        for (Float f2 : collection) {
            if (!this.add(f2.floatValue())) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(TFloatCollection collection) {
        boolean ret = false;
        TFloatIterator it2 = collection.iterator();
        while (it2.hasNext()) {
            float i2 = it2.next();
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
    }

    @Override
    public boolean addAll(float[] array) {
        boolean ret = false;
        for (float i2 : array) {
            if (!this.add(i2)) continue;
            ret = true;
        }
        return ret;
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
        Arrays.sort(array);
        boolean modified = false;
        TFloatIterator iter = this.iterator();
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
        TFloatIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(Float.valueOf(iter.next()))) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(TFloatCollection collection) {
        boolean modified = false;
        TFloatIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (!collection.contains(iter.next())) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean removeAll(float[] array) {
        Arrays.sort(array);
        boolean modified = false;
        TFloatIterator iter = this.iterator();
        while (iter.hasNext()) {
            if (Arrays.binarySearch(array, iter.next()) < 0) continue;
            iter.remove();
            modified = true;
        }
        return modified;
    }

    @Override
    public float removeAt(int offset) {
        TFloatLink l2 = this.getLinkAt(offset);
        if (TFloatLinkedList.no(l2)) {
            throw new ArrayIndexOutOfBoundsException("no elemenet at " + offset);
        }
        float prev = l2.getValue();
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
    public void transformValues(TFloatFunction function) {
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            l2.setValue(function.execute(l2.getValue()));
            l2 = l2.getNext();
        }
    }

    @Override
    public void reverse() {
        TFloatLink h2 = this.head;
        TFloatLink t2 = this.tail;
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            TFloatLink next = l2.getNext();
            TFloatLink prev = l2.getPrevious();
            TFloatLink tmp = l2;
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
        TFloatLink start = this.getLinkAt(from);
        TFloatLink stop = this.getLinkAt(to2);
        TFloatLink tmp = null;
        TFloatLink tmpHead = start.getPrevious();
        for (TFloatLink l2 = start; l2 != stop; l2 = l2.getNext()) {
            TFloatLink next = l2.getNext();
            TFloatLink prev = l2.getPrevious();
            tmp = l2;
            tmp.setNext(prev);
            tmp.setPrevious(next);
        }
        if (TFloatLinkedList.got(tmp)) {
            tmpHead.setNext(tmp);
            stop.setPrevious(tmpHead);
        }
        start.setNext(stop);
        stop.setPrevious(start);
    }

    @Override
    public void shuffle(Random rand) {
        for (int i2 = 0; i2 < this.size; ++i2) {
            TFloatLink l2 = this.getLinkAt(rand.nextInt(this.size()));
            this.removeLink(l2);
            this.add(l2.getValue());
        }
    }

    @Override
    public TFloatList subList(int begin, int end) {
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
        TFloatLinkedList ret = new TFloatLinkedList();
        TFloatLink tmp = this.getLinkAt(begin);
        for (int i2 = begin; i2 < end; ++i2) {
            ret.add(tmp.getValue());
            tmp = tmp.getNext();
        }
        return ret;
    }

    @Override
    public float[] toArray() {
        return this.toArray(new float[this.size], 0, this.size);
    }

    @Override
    public float[] toArray(int offset, int len) {
        return this.toArray(new float[len], offset, 0, len);
    }

    @Override
    public float[] toArray(float[] dest) {
        return this.toArray(dest, 0, this.size);
    }

    @Override
    public float[] toArray(float[] dest, int offset, int len) {
        return this.toArray(dest, offset, 0, len);
    }

    @Override
    public float[] toArray(float[] dest, int source_pos, int dest_pos, int len) {
        if (len == 0) {
            return dest;
        }
        if (source_pos < 0 || source_pos >= this.size()) {
            throw new ArrayIndexOutOfBoundsException(source_pos);
        }
        TFloatLink tmp = this.getLinkAt(source_pos);
        for (int i2 = 0; i2 < len; ++i2) {
            dest[dest_pos + i2] = tmp.getValue();
            tmp = tmp.getNext();
        }
        return dest;
    }

    @Override
    public boolean forEach(TFloatProcedure procedure) {
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (!procedure.execute(l2.getValue())) {
                return false;
            }
            l2 = l2.getNext();
        }
        return true;
    }

    @Override
    public boolean forEachDescending(TFloatProcedure procedure) {
        TFloatLink l2 = this.tail;
        while (TFloatLinkedList.got(l2)) {
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
        TFloatList tmp = this.subList(fromIndex, toIndex);
        float[] vals = tmp.toArray();
        Arrays.sort(vals);
        this.set(fromIndex, vals);
    }

    @Override
    public void fill(float val) {
        this.fill(0, this.size, val);
    }

    @Override
    public void fill(int fromIndex, int toIndex, float val) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("begin index can not be < 0");
        }
        TFloatLink l2 = this.getLinkAt(fromIndex);
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
    public int binarySearch(float value) {
        return this.binarySearch(value, 0, this.size());
    }

    @Override
    public int binarySearch(float value, int fromIndex, int toIndex) {
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
        TFloatLink fromLink = this.getLinkAt(fromIndex);
        int to2 = toIndex;
        while (from < to2) {
            int mid = from + to2 >>> 1;
            TFloatLink middle = TFloatLinkedList.getLink(fromLink, from, mid);
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
    public int indexOf(float value) {
        return this.indexOf(0, value);
    }

    @Override
    public int indexOf(int offset, float value) {
        int count = offset;
        TFloatLink l2 = this.getLinkAt(offset);
        while (TFloatLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                return count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(float value) {
        return this.lastIndexOf(0, value);
    }

    @Override
    public int lastIndexOf(int offset, float value) {
        if (this.isEmpty()) {
            return -1;
        }
        int last = -1;
        int count = offset;
        TFloatLink l2 = this.getLinkAt(offset);
        while (TFloatLinkedList.got(l2.getNext())) {
            if (l2.getValue() == value) {
                last = count;
            }
            ++count;
            l2 = l2.getNext();
        }
        return last;
    }

    @Override
    public boolean contains(float value) {
        if (this.isEmpty()) {
            return false;
        }
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (l2.getValue() == value) {
                return true;
            }
            l2 = l2.getNext();
        }
        return false;
    }

    @Override
    public TFloatIterator iterator() {
        return new TFloatIterator(){
            TFloatLink l;
            TFloatLink current;
            {
                this.l = TFloatLinkedList.this.head;
            }

            public float next() {
                if (TFloatLinkedList.no(this.l)) {
                    throw new NoSuchElementException();
                }
                float ret = this.l.getValue();
                this.current = this.l;
                this.l = this.l.getNext();
                return ret;
            }

            public boolean hasNext() {
                return TFloatLinkedList.got(this.l);
            }

            public void remove() {
                if (this.current == null) {
                    throw new IllegalStateException();
                }
                TFloatLinkedList.this.removeLink(this.current);
                this.current = null;
            }
        };
    }

    @Override
    public TFloatList grep(TFloatProcedure condition) {
        TFloatLinkedList ret = new TFloatLinkedList();
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public TFloatList inverseGrep(TFloatProcedure condition) {
        TFloatLinkedList ret = new TFloatLinkedList();
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (!condition.execute(l2.getValue())) {
                ret.add(l2.getValue());
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public float max() {
        float ret = Float.NEGATIVE_INFINITY;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (ret < l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public float min() {
        float ret = Float.POSITIVE_INFINITY;
        if (this.isEmpty()) {
            throw new IllegalStateException();
        }
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            if (ret > l2.getValue()) {
                ret = l2.getValue();
            }
            l2 = l2.getNext();
        }
        return ret;
    }

    @Override
    public float sum() {
        float sum = 0.0f;
        TFloatLink l2 = this.head;
        while (TFloatLinkedList.got(l2)) {
            sum += l2.getValue();
            l2 = l2.getNext();
        }
        return sum;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeFloat(this.no_entry_value);
        out.writeInt(this.size);
        TFloatIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            float next = iterator.next();
            out.writeFloat(next);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this.no_entry_value = in2.readFloat();
        int len = in2.readInt();
        for (int i2 = 0; i2 < len; ++i2) {
            this.add(in2.readFloat());
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
        TFloatLinkedList that = (TFloatLinkedList)o2;
        if (this.no_entry_value != that.no_entry_value) {
            return false;
        }
        if (this.size != that.size) {
            return false;
        }
        TFloatIterator iterator = this.iterator();
        TFloatIterator thatIterator = that.iterator();
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
        TFloatIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            result = 31 * result + HashFunctions.hash(iterator.next());
        }
        return result;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        TFloatIterator it2 = this.iterator();
        while (it2.hasNext()) {
            float next = it2.next();
            buf.append(next);
            if (!it2.hasNext()) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    class RemoveProcedure
    implements TFloatProcedure {
        boolean changed = false;

        RemoveProcedure() {
        }

        public boolean execute(float value) {
            if (TFloatLinkedList.this.remove(value)) {
                this.changed = true;
            }
            return true;
        }

        public boolean isChanged() {
            return this.changed;
        }
    }

    static class TFloatLink {
        float value;
        TFloatLink previous;
        TFloatLink next;

        TFloatLink(float value) {
            this.value = value;
        }

        public float getValue() {
            return this.value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public TFloatLink getPrevious() {
            return this.previous;
        }

        public void setPrevious(TFloatLink previous) {
            this.previous = previous;
        }

        public TFloatLink getNext() {
            return this.next;
        }

        public void setNext(TFloatLink next) {
            this.next = next;
        }
    }
}

