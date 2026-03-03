/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.Pointer;
import org.bridj.PointerIO;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class TypedPointer
extends Pointer.OrderedPointer {
    Pointer<?> copy;

    private TypedPointer(PointerIO<?> io2, long peer) {
        super(io2, peer, UNKNOWN_VALIDITY, UNKNOWN_VALIDITY, null, 0L, null);
    }

    public TypedPointer(long address) {
        this(PointerIO.getPointerInstance(), address);
    }

    public TypedPointer(Pointer<?> ptr) {
        this(PointerIO.getPointerInstance(), ptr.getPeer());
        this.copy = ptr;
    }
}

