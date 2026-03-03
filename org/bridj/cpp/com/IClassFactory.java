/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.Pointer;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@IID(value="00000001-0000-0000-C000-000000000046")
public class IClassFactory
extends IUnknown {
    @Virtual(value=0)
    @Deprecated
    public native int CreateInstance(Pointer<IUnknown> var1, Pointer<Byte> var2, Pointer<Pointer<IUnknown>> var3);

    public <I extends IUnknown> I CreateInstance(Class<I> type) {
        Pointer<Pointer<IUnknown>> p2 = Pointer.allocatePointer(IUnknown.class);
        int ret = this.CreateInstance(null, COMRuntime.getIID(type), p2);
        if (ret != 0) {
            return null;
        }
        return (I)((IUnknown)p2.get().getNativeObject(type));
    }

    @Virtual(value=1)
    public native int LockServer(boolean var1);
}

