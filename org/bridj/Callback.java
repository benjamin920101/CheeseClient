/*
 * Decompiled with CFR 0.152.
 */
package org.bridj;

import org.bridj.CRuntime;
import org.bridj.CallbackInterface;
import org.bridj.NativeObject;
import org.bridj.Pointer;
import org.bridj.ann.Runtime;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Runtime(value=CRuntime.class)
public abstract class Callback<C extends Callback<C>>
extends NativeObject
implements CallbackInterface {
    public Pointer<C> toPointer() {
        return Pointer.pointerTo(this);
    }
}

