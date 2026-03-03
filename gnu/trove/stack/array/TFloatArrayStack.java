/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.stack.array;

import gnu.trove.list.array.TFloatArrayList;
import gnu.trove.stack.TFloatStack;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TFloatArrayStack
implements TFloatStack,
Externalizable {
    static final long serialVersionUID = 1L;
    protected TFloatArrayList _list;
    public static final int DEFAULT_CAPACITY = 10;

    public TFloatArrayStack() {
        this(10);
    }

    public TFloatArrayStack(int capacity) {
        this._list = new TFloatArrayList(capacity);
    }

    public TFloatArrayStack(int capacity, float no_entry_value) {
        this._list = new TFloatArrayList(capacity, no_entry_value);
    }

    public TFloatArrayStack(TFloatStack stack) {
        if (!(stack instanceof TFloatArrayStack)) {
            throw new UnsupportedOperationException("Only support TFloatArrayStack");
        }
        TFloatArrayStack array_stack = (TFloatArrayStack)stack;
        this._list = new TFloatArrayList(array_stack._list);
    }

    public float getNoEntryValue() {
        return this._list.getNoEntryValue();
    }

    public void push(float val) {
        this._list.add(val);
    }

    public float pop() {
        return this._list.removeAt(this._list.size() - 1);
    }

    public float peek() {
        return this._list.get(this._list.size() - 1);
    }

    public int size() {
        return this._list.size();
    }

    public void clear() {
        this._list.clear();
    }

    public float[] toArray() {
        float[] retval = this._list.toArray();
        this.reverse(retval, 0, this.size());
        return retval;
    }

    public void toArray(float[] dest) {
        int size = this.size();
        int start = size - dest.length;
        if (start < 0) {
            start = 0;
        }
        int length = Math.min(size, dest.length);
        this._list.toArray(dest, start, length);
        this.reverse(dest, 0, length);
        if (dest.length > size) {
            dest[size] = this._list.getNoEntryValue();
        }
    }

    private void reverse(float[] dest, int from, int to2) {
        if (from == to2) {
            return;
        }
        if (from > to2) {
            throw new IllegalArgumentException("from cannot be greater than to");
        }
        int i2 = from;
        for (int j2 = to2 - 1; i2 < j2; ++i2, --j2) {
            this.swap(dest, i2, j2);
        }
    }

    private void swap(float[] dest, int i2, int j2) {
        float tmp = dest[i2];
        dest[i2] = dest[j2];
        dest[j2] = tmp;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder("{");
        for (int i2 = this._list.size() - 1; i2 > 0; --i2) {
            buf.append(this._list.get(i2));
            buf.append(", ");
        }
        if (this.size() > 0) {
            buf.append(this._list.get(0));
        }
        buf.append("}");
        return buf.toString();
    }

    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (o2 == null || this.getClass() != o2.getClass()) {
            return false;
        }
        TFloatArrayStack that = (TFloatArrayStack)o2;
        return this._list.equals(that._list);
    }

    public int hashCode() {
        return this._list.hashCode();
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._list);
    }

    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._list = (TFloatArrayList)in2.readObject();
    }
}

