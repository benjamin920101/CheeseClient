/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.jawt;

import org.bridj.BridJ;
import org.bridj.CRuntime;
import org.bridj.Pointer;
import org.bridj.TypedPointer;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.jawt.JAWT;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="jawt")
@Runtime(value=CRuntime.class)
public class JawtLibrary {
    public static final int JAWT_LOCK_CLIP_CHANGED = 2;
    public static final int JAWT_VERSION_1_4 = 65540;
    public static final int JAWT_VERSION_1_3 = 65539;
    public static final int JAWT_LOCK_SURFACE_CHANGED = 8;
    public static final int JAWT_LOCK_ERROR = 1;
    public static final int JAWT_LOCK_BOUNDS_CHANGED = 4;

    public static native boolean JAWT_GetAWT(Pointer<JNIEnv> var0, Pointer<JAWT> var1);

    static {
        BridJ.register();
    }

    public static class JNIEnv
    extends TypedPointer {
        public JNIEnv(long address) {
            super(address);
        }

        public JNIEnv(Pointer address) {
            super(address);
        }
    }
}

