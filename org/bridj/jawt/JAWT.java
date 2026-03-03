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
import org.bridj.jawt.JAWT_DrawingSurface;
import org.bridj.jawt.JawtLibrary;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
@Library(value="jawt")
@Convention(value=Convention.Style.StdCall)
public class JAWT
extends StructObject {
    @Field(value=0)
    public int version() {
        return this.io.getIntField(this, 0);
    }

    @Field(value=0)
    public JAWT version(int version) {
        this.io.setIntField(this, 0, version);
        return this;
    }

    @Field(value=1)
    public Pointer<GetDrawingSurface_callback> GetDrawingSurface() {
        return this.io.getPointerField(this, 1);
    }

    @Field(value=2)
    public Pointer<FreeDrawingSurface_callback> FreeDrawingSurface() {
        return this.io.getPointerField(this, 2);
    }

    @Field(value=3)
    public Pointer<Lock_callback> Lock() {
        return this.io.getPointerField(this, 3);
    }

    @Field(value=4)
    public Pointer<Unlock_callback> Unlock() {
        return this.io.getPointerField(this, 4);
    }

    @Field(value=5)
    public Pointer<GetComponent_callback> GetComponent() {
        return this.io.getPointerField(this, 5);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class GetComponent_callback
    extends Callback {
        public abstract Pointer<?> invoke(Pointer<JawtLibrary.JNIEnv> var1, Pointer<?> var2);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Unlock_callback
    extends Callback {
        public abstract void invoke(Pointer<JawtLibrary.JNIEnv> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class Lock_callback
    extends Callback {
        public abstract void invoke(Pointer<JawtLibrary.JNIEnv> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class FreeDrawingSurface_callback
    extends Callback {
        public abstract void invoke(Pointer<JAWT_DrawingSurface> var1);
    }

    /*
     * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
     */
    public static abstract class GetDrawingSurface_callback
    extends Callback {
        public abstract Pointer<JAWT_DrawingSurface> invoke(Pointer<JawtLibrary.JNIEnv> var1, Pointer<?> var2);
    }
}

