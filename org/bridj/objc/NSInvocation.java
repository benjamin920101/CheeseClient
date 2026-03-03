/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.ann.Library;
import org.bridj.ann.Ptr;
import org.bridj.objc.NSObject;
import org.bridj.objc.ObjCObject;
import org.bridj.objc.SEL;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="Foundation")
public class NSInvocation
extends NSObject {
    public native SEL selector();

    public native void setSelector(SEL var1);

    public native Pointer<? extends ObjCObject> target();

    public native void setTarget(Pointer<? extends ObjCObject> var1);

    public native void setArgument_atIndex(Pointer<?> var1, @Ptr long var2);

    public native void getArgument_atIndex(Pointer<?> var1, @Ptr long var2);

    public native void setReturnValue(Pointer<?> var1);

    public native void getReturnValue(Pointer<?> var1);

    static {
        BridJ.register();
    }
}

