/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.queue;

import gnu.trove.TIntCollection;

public interface TIntQueue
extends TIntCollection {
    public int element();

    public boolean offer(int var1);

    public int peek();

    public int poll();
}

