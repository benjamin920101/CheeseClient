/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TLongCollection;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.procedure.TLongProcedure;
import java.io.Serializable;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TUnmodifiableLongCollection
implements TLongCollection,
Serializable {
    private static final long serialVersionUID = 1820017752578914078L;
    final TLongCollection c;

    public TUnmodifiableLongCollection(TLongCollection c2) {
        if (c2 == null) {
            throw new NullPointerException();
        }
        this.c = c2;
    }

    @Override
    public int size() {
        return this.c.size();
    }

    @Override
    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override
    public boolean contains(long o2) {
        return this.c.contains(o2);
    }

    @Override
    public long[] toArray() {
        return this.c.toArray();
    }

    @Override
    public long[] toArray(long[] a2) {
        return this.c.toArray(a2);
    }

    public String toString() {
        return this.c.toString();
    }

    @Override
    public long getNoEntryValue() {
        return this.c.getNoEntryValue();
    }

    @Override
    public boolean forEach(TLongProcedure procedure) {
        return this.c.forEach(procedure);
    }

    @Override
    public TLongIterator iterator() {
        return new TLongIterator(){
            TLongIterator i;
            {
                this.i = TUnmodifiableLongCollection.this.c.iterator();
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public long next() {
                return this.i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(long e2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(long o2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(TLongCollection coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(long[] array) {
        return this.c.containsAll(array);
    }

    @Override
    public boolean addAll(TLongCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Long> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(long[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(TLongCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(long[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(TLongCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(long[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

