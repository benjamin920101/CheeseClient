/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.set.hash;

import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TObjectHash;
import gnu.trove.iterator.hash.TObjectHashIterator;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.array.ToObjectArrayProceedure;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class THashSet<E>
extends TObjectHash<E>
implements Set<E>,
Iterable<E>,
Externalizable {
    static final long serialVersionUID = 1L;

    public THashSet() {
    }

    public THashSet(int initialCapacity) {
        super(initialCapacity);
    }

    public THashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public THashSet(Collection<? extends E> collection) {
        this(collection.size());
        this.addAll(collection);
    }

    @Override
    public boolean add(E obj) {
        int index = this.insertKey(obj);
        if (index < 0) {
            return false;
        }
        this.postInsertHook(this.consumeFreeSlot);
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Set)) {
            return false;
        }
        Set that = (Set)other;
        if (that.size() != this.size()) {
            return false;
        }
        return this.containsAll(that);
    }

    @Override
    public int hashCode() {
        HashProcedure p2 = new HashProcedure();
        this.forEach(p2);
        return p2.getHashCode();
    }

    @Override
    protected void rehash(int newCapacity) {
        int oldCapacity = this._set.length;
        int oldSize = this.size();
        Object[] oldSet = this._set;
        this._set = new Object[newCapacity];
        Arrays.fill(this._set, FREE);
        int count = 0;
        int i2 = oldCapacity;
        while (i2-- > 0) {
            Object o2 = oldSet[i2];
            if (o2 == FREE || o2 == REMOVED) continue;
            int index = this.insertKey(o2);
            if (index < 0) {
                this.throwObjectContractViolation(this._set[-index - 1], o2, this.size(), oldSize, oldSet);
            }
            ++count;
        }
        THashSet.reportPotentialConcurrentMod(this.size(), oldSize);
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        this.forEach(new ToObjectArrayProceedure<Object>(result));
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a2) {
        int size = this.size();
        if (a2.length < size) {
            a2 = (Object[])Array.newInstance(a2.getClass().getComponentType(), size);
        }
        this.forEach(new ToObjectArrayProceedure<T>(a2));
        if (a2.length > size) {
            a2[size] = null;
        }
        return a2;
    }

    @Override
    public void clear() {
        super.clear();
        Arrays.fill(this._set, 0, this._set.length, FREE);
    }

    @Override
    public boolean remove(Object obj) {
        int index = this.index(obj);
        if (index >= 0) {
            this.removeAt(index);
            return true;
        }
        return false;
    }

    @Override
    public TObjectHashIterator<E> iterator() {
        return new TObjectHashIterator(this);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> i2 = collection.iterator();
        while (i2.hasNext()) {
            if (this.contains(i2.next())) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean changed = false;
        int size = collection.size();
        this.ensureCapacity(size);
        Iterator<E> it2 = collection.iterator();
        while (size-- > 0) {
            if (!this.add(it2.next())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean changed = false;
        int size = collection.size();
        Iterator<?> it2 = collection.iterator();
        while (size-- > 0) {
            if (!this.remove(it2.next())) continue;
            changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean changed = false;
        int size = this.size();
        Iterator it2 = this.iterator();
        while (size-- > 0) {
            if (collection.contains(it2.next())) continue;
            it2.remove();
            changed = true;
        }
        return changed;
    }

    public String toString() {
        final StringBuilder buf = new StringBuilder("{");
        this.forEach(new TObjectProcedure<E>(){
            private boolean first = true;

            @Override
            public boolean execute(Object value) {
                if (this.first) {
                    this.first = false;
                } else {
                    buf.append(", ");
                }
                buf.append(value);
                return true;
            }
        });
        buf.append("}");
        return buf.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(1);
        super.writeExternal(out);
        out.writeInt(this._size);
        this.writeEntries(out);
    }

    protected void writeEntries(ObjectOutput out) throws IOException {
        int i2 = this._set.length;
        while (i2-- > 0) {
            if (this._set[i2] == REMOVED || this._set[i2] == FREE) continue;
            out.writeObject(this._set[i2]);
        }
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        byte version = in2.readByte();
        if (version != 0) {
            super.readExternal(in2);
        }
        int size = in2.readInt();
        this.setUp(size);
        while (size-- > 0) {
            Object val = in2.readObject();
            this.add(val);
        }
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    private final class HashProcedure
    implements TObjectProcedure<E> {
        private int h = 0;

        private HashProcedure() {
        }

        public int getHashCode() {
            return this.h;
        }

        @Override
        public final boolean execute(E key) {
            this.h += HashFunctions.hash(key);
            return true;
        }
    }
}

