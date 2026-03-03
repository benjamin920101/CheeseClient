/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.StructIO;
import org.bridj.ann.Runtime;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Runtime(value=CRuntime.class)
public abstract class StructObject
extends NativeObject {
    protected StructIO io;

    protected StructObject() {
    }

    protected StructObject(Void voidArg, int constructorId, Object ... args) {
        super(constructorId, args);
    }

    protected StructObject(Pointer<? extends StructObject> peer) {
        super(peer);
    }

    public String toString() {
        return BridJ.describe(this);
    }
}

