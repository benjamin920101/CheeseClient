/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.Pointer;
import org.bridj.ann.Convention;
import org.bridj.ann.Runtime;
import org.bridj.ann.Virtual;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.com.COMCallableWrapper;
import org.bridj.cpp.com.COMRuntime;
import org.bridj.cpp.com.IID;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Convention(value=Convention.Style.StdCall)
@IID(value="00000000-0000-0000-C000-000000000046")
@Runtime(value=COMRuntime.class)
public class IUnknown
extends CPPObject {
    protected boolean autoRelease;

    public static IUnknown wrap(Object object) {
        if (object instanceof IUnknown) {
            return (IUnknown)object;
        }
        return new COMCallableWrapper(object);
    }

    protected void finalize() throws Throwable {
        if (this.autoRelease) {
            this.Release();
        }
        super.finalize();
    }

    @Virtual(value=0)
    @Deprecated
    public native int QueryInterface(Pointer<Byte> var1, Pointer<Pointer<IUnknown>> var2);

    public <I extends IUnknown> I QueryInterface(Class<I> type) {
        Pointer<Pointer<IUnknown>> p2 = Pointer.allocatePointer(IUnknown.class);
        int ret = this.QueryInterface(COMRuntime.getIID(type), p2);
        if (ret != 0) {
            return null;
        }
        return (I)((IUnknown)p2.get().getNativeObject(type));
    }

    @Virtual(value=1)
    public native int AddRef();

    @Virtual(value=2)
    public native int Release();
}

