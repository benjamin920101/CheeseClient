/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.sync;

import gnu.trove.impl.sync.TSynchronizedIntCollection;
import gnu.trove.set.TIntSet;

public class TSynchronizedIntSet
extends TSynchronizedIntCollection
implements TIntSet {
    private static final long serialVersionUID = 487447009682186044L;

    public TSynchronizedIntSet(TIntSet s2) {
        super(s2);
    }

    public TSynchronizedIntSet(TIntSet s2, Object mutex) {
        super(s2, mutex);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean equals(Object o2) {
        Object object = this.mutex;
        synchronized (object) {
            return ((Object)this.c).equals(o2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int hashCode() {
        Object object = this.mutex;
        synchronized (object) {
            return ((Object)this.c).hashCode();
        }
    }
}

