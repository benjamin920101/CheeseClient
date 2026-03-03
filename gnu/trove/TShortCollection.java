/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove;

import gnu.trove.iterator.TShortIterator;
import gnu.trove.procedure.TShortProcedure;
import java.util.Collection;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TShortCollection {
    public static final long serialVersionUID = 1L;

    public short getNoEntryValue();

    public int size();

    public boolean isEmpty();

    public boolean contains(short var1);

    public TShortIterator iterator();

    public short[] toArray();

    public short[] toArray(short[] var1);

    public boolean add(short var1);

    public boolean remove(short var1);

    public boolean containsAll(Collection<?> var1);

    public boolean containsAll(TShortCollection var1);

    public boolean containsAll(short[] var1);

    public boolean addAll(Collection<? extends Short> var1);

    public boolean addAll(TShortCollection var1);

    public boolean addAll(short[] var1);

    public boolean retainAll(Collection<?> var1);

    public boolean retainAll(TShortCollection var1);

    public boolean retainAll(short[] var1);

    public boolean removeAll(Collection<?> var1);

    public boolean removeAll(TShortCollection var1);

    public boolean removeAll(short[] var1);

    public void clear();

    public boolean forEach(TShortProcedure var1);

    public boolean equals(Object var1);

    public int hashCode();
}

