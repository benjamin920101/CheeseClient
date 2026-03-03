/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.objc.NSObject;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSMethodSignature
extends NSObject {
    public static native Pointer<NSMethodSignature> signatureWithObjCTypes(Pointer<Byte> var0);

    public native Pointer<Byte> methodReturnType();

    @Ptr
    public native long numberOfArguments();

    public native boolean isOneway();

    public native Pointer<Byte> getArgumentTypeAtIndex(@Ptr long var1);

    @Ptr
    public native long frameLength();

    static {
        BridJ.register();
    }
}

