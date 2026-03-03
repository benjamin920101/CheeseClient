/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.decorator;

import gnu.trove.iterator.TCharIterator;
import gnu.trove.set.TCharSet;
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
public class TCharSetDecorator
extends AbstractSet<Character>
implements Set<Character>,
Externalizable {
    static final long serialVersionUID = 1L;
    protected TCharSet _set;

    public TCharSetDecorator() {
    }

    public TCharSetDecorator(TCharSet set) {
        this._set = set;
    }

    public TCharSet getSet() {
        return this._set;
    }

    @Override
    public boolean add(Character value) {
        return value != null && this._set.add(value.charValue());
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
                if (val instanceof Character) {
                    char v2 = ((Character)val).charValue();
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
        return value instanceof Character && this._set.remove(((Character)value).charValue());
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>(){
            private final TCharIterator it;
            {
                this.it = TCharSetDecorator.this._set.iterator();
            }

            @Override
            public Character next() {
                return Character.valueOf(this.it.next());
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
        if (!(o2 instanceof Character)) {
            return false;
        }
        return this._set.contains(((Character)o2).charValue());
    }

    @Override
    public void readExternal(ObjectInput in2) throws IOException, ClassNotFoundException {
        in2.readByte();
        this._set = (TCharSet)in2.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(0);
        out.writeObject(this._set);
    }
}

