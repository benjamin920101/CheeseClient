/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com.shell;

import org.bridj.Pointer;
import org.bridj.ann.Virtual;
import org.bridj.cpp.com.shell.ITaskbarList;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ITaskbarList2
extends ITaskbarList {
    @Virtual(value=0)
    public native int MarkFullscreenWindow(Pointer<Integer> var1, boolean var2);
}

