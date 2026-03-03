/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.jawt;

import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.jawt.JAWT_Rectangle;

@Library(value="jawt")
public class JAWT_DrawingSurfaceInfo
extends StructObject {
    @Field(value=0)
    public Pointer platformInfo() {
        return this.io.getPointerField(this, 0);
    }

    @Field(value=1)
    public Pointer ds() {
        return this.io.getPointerField(this, 1);
    }

    @Field(value=2)
    public JAWT_Rectangle bounds() {
        return (JAWT_Rectangle)this.io.getNativeObjectField(this, 2);
    }

    @Field(value=3)
    public int clipSize() {
        return this.io.getIntField(this, 3);
    }

    @Field(value=4)
    public JAWT_Rectangle clip() {
        return (JAWT_Rectangle)this.io.getNativeObjectField(this, 4);
    }
}

