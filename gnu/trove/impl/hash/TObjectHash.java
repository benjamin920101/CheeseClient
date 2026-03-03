/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.hash;

import gnu.trove.impl.hash.THash;
import gnu.trove.procedure.TObjectProcedure;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class TObjectHash<T>
extends THash {
    static final long serialVersionUID = -3461112548087185871L;
    public transient Object[] _set;
    public static final Object REMOVED = new Object();
    public static final Object FREE = new Object();
    protected boolean consumeFreeSlot;

    public TObjectHash() {
    }

    public TObjectHash(int initialCapacity) {
        super(initialCapacity);
    }

    public TObjectHash(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public int capacity() {
        return this._set.length;
    }

    @Override
    protected void removeAt(int index) {
        this._set[index] = REMOVED;
        super.removeAt(index);
    }

    @Override
    public int setUp(int initialCapacity) {
        int capacity = super.setUp(initialCapacity);
        this._set = new Object[capacity];
        Arrays.fill(this._set, FREE);
        return capacity;
    }

    public boolean forEach(TObjectProcedure<? super T> procedure) {
        Object[] set = this._set;
        int i2 = set.length;
        while (i2-- > 0) {
            if (set[i2] == FREE || set[i2] == REMOVED || procedure.execute(set[i2])) continue;
            return false;
        }
        return true;
    }

    public boolean contains(Object obj) {
        return this.index(obj) >= 0;
    }

    protected int index(Object obj) {
        if (obj == null) {
            return this.indexForNull();
        }
        int hash = this.hash(obj) & Integer.MAX_VALUE;
        int index = hash % this._set.length;
        Object cur = this._set[index];
        if (cur == FREE) {
            return -1;
        }
        if (cur == obj || this.equals(obj, cur)) {
            return index;
        }
        return this.indexRehashed(obj, index, hash, cur);
    }

    private int indexRehashed(Object obj, int index, int hash, Object cur) {
        Object[] set = this._set;
        int length = set.length;
        int probe = 1 + hash % (length - 2);
        int loopIndex = index;
        do {
            if ((index -= probe) < 0) {
                index += length;
            }
            if ((cur = set[index]) == FREE) {
                return -1;
            }
            if (cur != obj && !this.equals(obj, cur)) continue;
            return index;
        } while (index != loopIndex);
        return -1;
    }

    private int indexForNull() {
        int index = 0;
        for (Object o2 : this._set) {
            if (o2 == null) {
                return index;
            }
            if (o2 == FREE) {
                return -1;
            }
            ++index;
        }
        return -1;
    }

    @Deprecated
    protected int insertionIndex(T obj) {
        return this.insertKey(obj);
    }

    protected int insertKey(T key) {
        this.consumeFreeSlot = false;
        if (key == null) {
            return this.insertKeyForNull();
        }
        int hash = this.hash(key) & Integer.MAX_VALUE;
        int index = hash % this._set.length;
        Object cur = this._set[index];
        if (cur == FREE) {
            this.consumeFreeSlot = true;
            this._set[index] = key;
            return index;
        }
        if (cur == key || this.equals(key, cur)) {
            return -index - 1;
        }
        return this.insertKeyRehash(key, index, hash, cur);
    }

    private int insertKeyRehash(T key, int index, int hash, Object cur) {
        Object[] set = this._set;
        int length = set.length;
        int probe = 1 + hash % (length - 2);
        int loopIndex = index;
        int firstRemoved = -1;
        do {
            if (cur == REMOVED && firstRemoved == -1) {
                firstRemoved = index;
            }
            if ((index -= probe) < 0) {
                index += length;
            }
            if ((cur = set[index]) == FREE) {
                if (firstRemoved != -1) {
                    this._set[firstRemoved] = key;
                    return firstRemoved;
                }
                this.consumeFreeSlot = true;
                this._set[index] = key;
                return index;
            }
            if (cur != key && !this.equals(key, cur)) continue;
            return -index - 1;
        } while (index != loopIndex);
        if (firstRemoved != -1) {
            this._set[firstRemoved] = key;
            return firstRemoved;
        }
        throw new IllegalStateException("No free or removed slots available. Key set full?!!");
    }

    private int insertKeyForNull() {
        int index = 0;
        int firstRemoved = -1;
        for (Object o2 : this._set) {
            if (o2 == REMOVED && firstRemoved == -1) {
                firstRemoved = index;
            }
            if (o2 == FREE) {
                if (firstRemoved != -1) {
                    this._set[firstRemoved] = null;
                    return firstRemoved;
                }
                this.consumeFreeSlot = true;
                this._set[index] = null;
                return index;
            }
            if (o2 == null) {
                return -index - 1;
            }
            ++index;
        }
        if (firstRemoved != -1) {
            this._set[firstRemoved] = null;
            return firstRemoved;
        }
        throw new IllegalStateException("Could not find insertion index for null key. Key set full!?!!");
    }

    protected final void throwObjectContractViolation(Object o1, Object o2) throws IllegalArgumentException {
        throw this.buildObjectContractViolation(o1, o2, "");
    }

    protected final void throwObjectContractViolation(Object o1, Object o2, int size, int oldSize, Object[] oldKeys) throws IllegalArgumentException {
        String extra = this.dumpExtraInfo(o1, o2, this.size(), oldSize, oldKeys);
        throw this.buildObjectContractViolation(o1, o2, extra);
    }

    protected final IllegalArgumentException buildObjectContractViolation(Object o1, Object o2, String extra) {
        return new IllegalArgumentException("Equal objects must have equal hashcodes. During rehashing, Trove discovered that the following two objects claim to be equal (as in java.lang.Object.equals()) but their hashCodes (or those calculated by your TObjectHashingStrategy) are not equal.This violates the general contract of java.lang.Object.hashCode().  See bullet point two in that method's documentation. object #1 =" + TObjectHash.objectInfo(o1) + "; object #2 =" + TObjectHash.objectInfo(o2) + "\n" + extra);
    }

    protected boolean equals(Object notnull, Object two) {
        if (two == null || two == REMOVED) {
            return false;
        }
        return notnull.equals(two);
    }

    protected int hash(Object notnull) {
        return notnull.hashCode();
    }

    protected static String reportPotentialConcurrentMod(int newSize, int oldSize) {
        if (newSize != oldSize) {
            return "[Warning] apparent concurrent modification of the key set. Size before and after rehash() do not match " + oldSize + " vs " + newSize;
        }
        return "";
    }

    protected String dumpExtraInfo(Object newVal, Object oldVal, int currentSize, int oldSize, Object[] oldKeys) {
        StringBuilder b2 = new StringBuilder();
        b2.append(this.dumpKeyTypes(newVal, oldVal));
        b2.append(TObjectHash.reportPotentialConcurrentMod(currentSize, oldSize));
        b2.append(TObjectHash.detectKeyLoss(oldKeys, oldSize));
        if (newVal == oldVal) {
            b2.append("Inserting same object twice, rehashing bug. Object= ").append(oldVal);
        }
        return b2.toString();
    }

    private static String detectKeyLoss(Object[] keys, int oldSize) {
        StringBuilder buf = new StringBuilder();
        Set<Object> k2 = TObjectHash.makeKeySet(keys);
        if (k2.size() != oldSize) {
            buf.append("\nhashCode() and/or equals() have inconsistent implementation");
            buf.append("\nKey set lost entries, now got ").append(k2.size()).append(" instead of ").append(oldSize);
            buf.append(". This can manifest itself as an apparent duplicate key.");
        }
        return buf.toString();
    }

    private static Set<Object> makeKeySet(Object[] keys) {
        HashSet<Object> types = new HashSet<Object>();
        for (Object o2 : keys) {
            if (o2 == FREE || o2 == REMOVED) continue;
            types.add(o2);
        }
        return types;
    }

    private static String equalsSymmetryInfo(Object a2, Object b2) {
        StringBuilder buf = new StringBuilder();
        if (a2 == b2) {
            return "a == b";
        }
        if (a2.getClass() != b2.getClass()) {
            buf.append("Class of objects differ a=").append(a2.getClass()).append(" vs b=").append(b2.getClass());
            boolean aEb = a2.equals(b2);
            boolean bEa = b2.equals(a2);
            if (aEb != bEa) {
                buf.append("\nequals() of a or b object are asymmetric");
                buf.append("\na.equals(b) =").append(aEb);
                buf.append("\nb.equals(a) =").append(bEa);
            }
        }
        return buf.toString();
    }

    protected static String objectInfo(Object o2) {
        return (o2 == null ? "class null" : o2.getClass()) + " id= " + System.identityHashCode(o2) + " hashCode= " + (o2 == null ? 0 : o2.hashCode()) + " toString= " + String.valueOf(o2);
    }

    private String dumpKeyTypes(Object newVal, Object oldVal) {
        StringBuilder buf = new StringBuilder();
        HashSet types = new HashSet();
        for (Object o2 : this._set) {
            if (o2 == FREE || o2 == REMOVED) continue;
            if (o2 != null) {
                types.add(o2.getClass());
                continue;
            }
            types.add(null);
        }
        if (types.size() > 1) {
            buf.append("\nMore than one type used for keys. Watch out for asymmetric equals(). Read about the 'Liskov substitution principle' and the implications for equals() in java.");
            buf.append("\nKey types: ").append(types);
            buf.append(TObjectHash.equalsSymmetryInfo(newVal, oldVal));
        }
        return buf.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        super.readExternal(in2);
    }
}

