/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.iterator.TObjectShortIterator;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.procedure.TObjectShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import java.util.Map;
import java.util.Set;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TObjectShortMap<K> {
    public short getNoEntryValue();

    public int size();

    public boolean isEmpty();

    public boolean containsKey(Object var1);

    public boolean containsValue(short var1);

    public short get(Object var1);

    public short put(K var1, short var2);

    public short putIfAbsent(K var1, short var2);

    public short remove(Object var1);

    public void putAll(Map<? extends K, ? extends Short> var1);

    public void putAll(TObjectShortMap<? extends K> var1);

    public void clear();

    public Set<K> keySet();

    public Object[] keys();

    public K[] keys(K[] var1);

    public TShortCollection valueCollection();

    public short[] values();

    public short[] values(short[] var1);

    public TObjectShortIterator<K> iterator();

    public boolean increment(K var1);

    public boolean adjustValue(K var1, short var2);

    public short adjustOrPutValue(K var1, short var2, short var3);

    public boolean forEachKey(TObjectProcedure<? super K> var1);

    public boolean forEachValue(TShortProcedure var1);

    public boolean forEachEntry(TObjectShortProcedure<? super K> var1);

    public void transformValues(TShortFunction var1);

    public boolean retainEntries(TObjectShortProcedure<? super K> var1);

    public boolean equals(Object var1);

    public int hashCode();
}

