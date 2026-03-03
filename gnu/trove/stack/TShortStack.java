/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.stack;

public interface TShortStack {
    public short getNoEntryValue();

    public void push(short var1);

    public short pop();

    public short peek();

    public int size();

    public void clear();

    public short[] toArray();

    public void toArray(short[] var1);
}

