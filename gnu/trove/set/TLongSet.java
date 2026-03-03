/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.set;

import gnu.trove.TLongCollection;
import gnu.trove.iterator.TLongIterator;
import gnu.trove.procedure.TLongProcedure;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TLongSet
extends TLongCollection {
    @Override
    public long getNoEntryValue();

    @Override
    public int size();

    @Override
    public boolean isEmpty();

    @Override
    public boolean contains(long var1);

    @Override
    public TLongIterator iterator();

    @Override
    public long[] toArray();

    @Override
    public long[] toArray(long[] var1);

    @Override
    public boolean add(long var1);

    @Override
    public boolean remove(long var1);

    @Override
    public boolean containsAll(Collection<?> var1);

    @Override
    public boolean containsAll(TLongCollection var1);

    @Override
    public boolean containsAll(long[] var1);

    @Override
    public boolean addAll(Collection<? extends Long> var1);

    @Override
    public boolean addAll(TLongCollection var1);

    @Override
    public boolean addAll(long[] var1);

    @Override
    public boolean retainAll(Collection<?> var1);

    @Override
    public boolean retainAll(TLongCollection var1);

    @Override
    public boolean retainAll(long[] var1);

    @Override
    public boolean removeAll(Collection<?> var1);

    @Override
    public boolean removeAll(TLongCollection var1);

    @Override
    public boolean removeAll(long[] var1);

    @Override
    public void clear();

    @Override
    public boolean forEach(TLongProcedure var1);

    @Override
    public boolean equals(Object var1);

    @Override
    public int hashCode();
}

