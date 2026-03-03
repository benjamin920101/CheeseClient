/*
 * Decompiled with CFR 0.152.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.impl.unmodifiable.TUnmodifiableFloatCollection;
import gnu.trove.set.TFloatSet;
import java.io.Serializable;

public class TUnmodifiableFloatSet
extends TUnmodifiableFloatCollection
implements TFloatSet,
Serializable {
    private static final long serialVersionUID = -9215047833775013803L;

    public TUnmodifiableFloatSet(TFloatSet s2) {
        super(s2);
    }

    public boolean equals(Object o2) {
        return o2 == this || ((Object)this.c).equals(o2);
    }

    public int hashCode() {
        return ((Object)this.c).hashCode();
    }
}

