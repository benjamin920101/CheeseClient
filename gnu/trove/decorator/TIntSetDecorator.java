/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.TIntSet;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TIntSetDecorator
extends AbstractSet<Integer>
implements Set<Integer>,
Externalizable {
    static final long serialVersionUID = 1L;
    protected TIntSet _set;

    public TIntSetDecorator() {
    }

    public TIntSetDecorator(TIntSet set) {
        this._set = set;
    }

    public TIntSet getSet() {
        return this._set;
    }

    @Override
    public boolean add(Integer value) {
        return value != null && this._set.add(value);
    }

    @Override
    public boolean equals(Object other) {
        if (((Object)this._set).equals(other)) {
            return true;
        }
        if (other instanceof Set) {
            Set that = (Set)other;
            if (that.size() != this._set.size()) {
                return false;
            }
            Iterator it2 = that.iterator();
            int i2 = that.size();
            while (i2-- > 0) {
                Object val = it2.next();
                if (val instanceof Integer) {
                    int v2 = (Integer)val;
                    if (this._set.contains(v2)) continue;
                    return false;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        this._set.clear();
    }

    @Override
    public boolean remove(Object value) {
        return value instanceof Integer && this._set.remove((Integer)value);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>(){
            private final TIntIterator it;
            {
                this.it = TIntSetDecorator.this._set.iterator();
            }

            @Override
            public Integer next() {
                return this.it.next();
            }

            @Override
            public boolean hasNext() {
                return this.it.hasNext();
            }

            @Override
            public void remove() {
                this.it.remove();
            }
        };
    }

    @Override
    public int size() {
        return this._set.size();
    }

    @Override
    public boolean isEmpty() {
        return this._set.size() == 0;
    }

    @Override
    public boolean contains(Object o2) {
        if (!(o2 instanceof Integer)) {
            return false;
        }
        return this._set.contains((Integer)o2);
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._set = (TIntSet)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._set);
    }
}

