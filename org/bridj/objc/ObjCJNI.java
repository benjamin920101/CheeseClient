/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.objc;

import org.bridj.Pointer;
import org.bridj.objc.ObjCObject;
import org.bridj.objc.ObjCProxy;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ObjCJNI {
    @Deprecated
    public static synchronized native Pointer<? extends ObjCObject> createObjCProxyPeer(ObjCProxy var0);

    static synchronized native long createObjCBlockWithFunctionPointer(long var0);

    static synchronized native long getObjCBlockFunctionPointer(long var0);

    static synchronized native void releaseObjCBlock(long var0);
}

