/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.BridJ;
import org.bridj.Pointer;
import org.bridj.objc.IMP;
import org.bridj.objc.ObjCObject;
import org.bridj.objc.SEL;
import org.bridj.objc.Selector;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjCClass
extends ObjCObject {
    public native <T extends ObjCObject> Pointer<T> alloc();

    @Selector(value="new")
    public native <T extends ObjCObject> Pointer<T> new$();

    public native boolean instancesRespondTo(SEL var1);

    public native IMP instanceMethodFor(SEL var1);

    static {
        BridJ.register();
    }
}

