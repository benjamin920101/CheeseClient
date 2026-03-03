/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.sync;

import gnu.trove.impl.sync.TSynchronizedDoubleCollection;
import gnu.trove.set.TDoubleSet;

public class TSynchronizedDoubleSet
extends TSynchronizedDoubleCollection
implements TDoubleSet {
    private static final long serialVersionUID = 487447009682186044L;

    public TSynchronizedDoubleSet(TDoubleSet s2) {
        super(s2);
    }

    public TSynchronizedDoubleSet(TDoubleSet s2, Object mutex) {
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

