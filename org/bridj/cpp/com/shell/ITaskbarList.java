/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com.shell;

import org.bridj.Pointer;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.IID;
import org.bridj.cpp.com.IUnknown;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@IID(value="56FDF342-FD6D-11D0-958A-006097C9A090")
public class ITaskbarList
extends IUnknown {
    @Virtual(value=0)
    public native void HrInit();

    @Virtual(value=1)
    public native void AddTab(Pointer<Integer> var1);

    @Virtual(value=2)
    public native void DeleteTab(Pointer<Integer> var1);

    @Virtual(value=3)
    public native void ActivateTab(Pointer<Integer> var1);

    @Virtual(value=4)
    public native void SetActiveAlt(Pointer<Integer> var1);
}

