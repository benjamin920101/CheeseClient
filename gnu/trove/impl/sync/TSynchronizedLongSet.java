/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.sync;

import gnu.trove.impl.sync.TSynchronizedLongCollection;
import gnu.trove.set.TLongSet;

public class TSynchronizedLongSet
extends TSynchronizedLongCollection
implements TLongSet {
    private static final long serialVersionUID = 487447009682186044L;

    public TSynchronizedLongSet(TLongSet s2) {
        super(s2);
    }

    public TSynchronizedLongSet(TLongSet s2, Object mutex) {
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

