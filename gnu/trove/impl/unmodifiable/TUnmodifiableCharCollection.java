/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCharCollection;
import gnu.trove.iterator.TCharIterator;
import gnu.trove.procedure.TCharProcedure;
import java.io.Serializable;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TUnmodifiableCharCollection
implements TCharCollection,
Serializable {
    private static final long serialVersionUID = 1820017752578914078L;
    final TCharCollection c;

    public TUnmodifiableCharCollection(TCharCollection c2) {
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
    public boolean contains(char o2) {
        return this.c.contains(o2);
    }

    @Override
    public char[] toArray() {
        return this.c.toArray();
    }

    @Override
    public char[] toArray(char[] a2) {
        return this.c.toArray(a2);
    }

    public String toString() {
        return this.c.toString();
    }

    @Override
    public char getNoEntryValue() {
        return this.c.getNoEntryValue();
    }

    @Override
    public boolean forEach(TCharProcedure procedure) {
        return this.c.forEach(procedure);
    }

    @Override
    public TCharIterator iterator() {
        return new TCharIterator(){
            TCharIterator i;
            {
                this.i = TUnmodifiableCharCollection.this.c.iterator();
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public char next() {
                return this.i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(char e2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(char o2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(TCharCollection coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(char[] array) {
        return this.c.containsAll(array);
    }

    @Override
    public boolean addAll(TCharCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Character> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(char[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(TCharCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(char[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(TCharCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(char[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

