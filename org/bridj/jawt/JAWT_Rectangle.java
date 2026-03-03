/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.jawt;

import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;

@Library(value="jawt")
public class JAWT_Rectangle
extends StructObject {
    @Field(value=0)
    public int x() {
        return this.io.getIntField(this, 0);
    }

    @Field(value=1)
    public int y() {
        return this.io.getIntField(this, 1);
    }

    @Field(value=2)
    public int width() {
        return this.io.getIntField(this, 2);
    }

    @Field(value=3)
    public int height() {
        return this.io.getIntField(this, 3);
    }
}

