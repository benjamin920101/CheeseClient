/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map;

import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.iterator.TByteLongIterator;
import gnu.trove.procedure.TByteLongProcedure;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TByteSet;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TByteLongMap {
    public byte getNoEntryKey();

    public long getNoEntryValue();

    public long put(byte var1, long var2);

    public long putIfAbsent(byte var1, long var2);

    public void putAll(Map<? extends Byte, ? extends Long> var1);

    public void putAll(TByteLongMap var1);

    public long get(byte var1);

    public void clear();

    public boolean isEmpty();

    public long remove(byte var1);

    public int size();

    public TByteSet keySet();

    public byte[] keys();

    public byte[] keys(byte[] var1);

    public TLongCollection valueCollection();

    public long[] values();

    public long[] values(long[] var1);

    public boolean containsValue(long var1);

    public boolean containsKey(byte var1);

    public TByteLongIterator iterator();

    public boolean forEachKey(TByteProcedure var1);

    public boolean forEachValue(TLongProcedure var1);

    public boolean forEachEntry(TByteLongProcedure var1);

    public void transformValues(TLongFunction var1);

    public boolean retainEntries(TByteLongProcedure var1);

    public boolean increment(byte var1);

    public boolean adjustValue(byte var1, long var2);

    public long adjustOrPutValue(byte var1, long var2, long var4);
}

