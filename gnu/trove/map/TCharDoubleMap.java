/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.map;

import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.iterator.TCharDoubleIterator;
import gnu.trove.procedure.TCharDoubleProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.set.TCharSet;
import java.util.Map;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface TCharDoubleMap {
    public char getNoEntryKey();

    public double getNoEntryValue();

    public double put(char var1, double var2);

    public double putIfAbsent(char var1, double var2);

    public void putAll(Map<? extends Character, ? extends Double> var1);

    public void putAll(TCharDoubleMap var1);

    public double get(char var1);

    public void clear();

    public boolean isEmpty();

    public double remove(char var1);

    public int size();

    public TCharSet keySet();

    public char[] keys();

    public char[] keys(char[] var1);

    public TDoubleCollection valueCollection();

    public double[] values();

    public double[] values(double[] var1);

    public boolean containsValue(double var1);

    public boolean containsKey(char var1);

    public TCharDoubleIterator iterator();

    public boolean forEachKey(TCharProcedure var1);

    public boolean forEachValue(TDoubleProcedure var1);

    public boolean forEachEntry(TCharDoubleProcedure var1);

    public void transformValues(TDoubleFunction var1);

    public boolean retainEntries(TCharDoubleProcedure var1);

    public boolean increment(char var1);

    public boolean adjustValue(char var1, double var2);

    public double adjustOrPutValue(char var1, double var2, double var4);
}

