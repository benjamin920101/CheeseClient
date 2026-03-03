/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.StructObject;
import org.bridj.ann.CLong;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;

@Runtime(value=CRuntime.class)
public class RECT
extends StructObject {
    @CLong
    @Field(value=0)
    public long left() {
        return this.io.getCLongField(this, 0);
    }

    @CLong
    @Field(value=0)
    public RECT left(long left) {
        this.io.setCLongField(this, 0, left);
        return this;
    }

    public final long left_$eq(long left) {
        this.left(left);
        return left;
    }

    @CLong
    @Field(value=1)
    public long top() {
        return this.io.getCLongField(this, 1);
    }

    @CLong
    @Field(value=1)
    public RECT top(long top) {
        this.io.setCLongField(this, 1, top);
        return this;
    }

    public final long top_$eq(long top) {
        this.top(top);
        return top;
    }

    @CLong
    @Field(value=2)
    public long right() {
        return this.io.getCLongField(this, 2);
    }

    @CLong
    @Field(value=2)
    public RECT right(long right) {
        this.io.setCLongField(this, 2, right);
        return this;
    }

    public final long right_$eq(long right) {
        this.right(right);
        return right;
    }

    @CLong
    @Field(value=3)
    public long bottom() {
        return this.io.getCLongField(this, 3);
    }

    @CLong
    @Field(value=3)
    public RECT bottom(long bottom) {
        this.io.setCLongField(this, 3, bottom);
        return this;
    }

    public final long bottom_$eq(long bottom) {
        this.bottom(bottom);
        return bottom;
    }
}

