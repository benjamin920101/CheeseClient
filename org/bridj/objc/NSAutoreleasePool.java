/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.objc.NSObject;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSAutoreleasePool
extends NSObject {
    public static native Pointer<NSAutoreleasePool> new_();

    public native void drain();

    static {
        BridJ.register();
    }
}

