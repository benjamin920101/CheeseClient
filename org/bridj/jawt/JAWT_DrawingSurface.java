/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.jawt;

import org.bridj.Callback;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Convention;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.jawt.JAWT_DrawingSurfaceInfo;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="jawt")
@Convention(value=Convention.Style.StdCall)
public class JAWT_DrawingSurface
extends StructObject {
    @Field(value=0)
    public Pointer env() {
        return this.io.getPointerField(this, 0);
    }

    @Field(value=1)
    public Pointer target() {
        return this.io.getPointerField(this, 1);
    }

    @Field(value=2)
    public Pointer<Lock_callback> Lock() {
        return this.io.getPointerField(this, 2);
    }

    @Field(value=3)
    public Pointer<GetDrawingSurfaceInfo_callback> GetDrawingSurfaceInfo() {
        return this.io.getPointerField(this, 3);
    }

    @Field(value=4)
    public Pointer<FreeDrawingSurfaceInfo_callback> FreeDrawingSurfaceInfo() {
        return this.io.getPointerField(this, 4);
    }

    @Field(value=5)
    public Pointer<Unlock_callback> Unlock() {
        return this.io.getPointerField(this, 5);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Unlock_callback
    extends Callback {
        public abstract void invoke(Pointer<JAWT_DrawingSurface> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class FreeDrawingSurfaceInfo_callback
    extends Callback {
        public abstract void invoke(Pointer<JAWT_DrawingSurfaceInfo> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class GetDrawingSurfaceInfo_callback
    extends Callback {
        public abstract Pointer<JAWT_DrawingSurfaceInfo> invoke(Pointer<JAWT_DrawingSurface> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Lock_callback
    extends Callback {
        public abstract int invoke(Pointer<JAWT_DrawingSurface> var1);
    }
}

