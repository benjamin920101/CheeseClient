/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.sync;

import gnu.trove.impl.sync.TSynchronizedFloatCollection;
import gnu.trove.set.TFloatSet;

public class TSynchronizedFloatSet
extends TSynchronizedFloatCollection
implements TFloatSet {
    private static final long serialVersionUID = 487447009682186044L;

    public TSynchronizedFloatSet(TFloatSet s2) {
        super(s2);
    }

    public TSynchronizedFloatSet(TFloatSet s2, Object mutex) {
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

