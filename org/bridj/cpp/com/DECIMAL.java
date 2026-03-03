/*
 * Decompiled with CFR 0.152.
 */
package org.bridj.cpp.com;

import org.bridj.CRuntime;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Runtime;

@Runtime(value=CRuntime.class)
public class DECIMAL
extends StructObject {
    @Field(value=0)
    public short wReserved() {
        return this.io.getShortField(this, 0);
    }

    @Field(value=0)
    public DECIMAL wReserved(short wReserved) {
        this.io.setShortField(this, 0, wReserved);
        return this;
    }

    public final short wReserved_$eq(short wReserved) {
        this.wReserved(wReserved);
        return wReserved;
    }

    @Field(value=1)
    public byte scale() {
        return this.io.getByteField(this, 1);
    }

    @Field(value=1)
    public DECIMAL scale(byte scale) {
        this.io.setByteField(this, 1, scale);
        return this;
    }

    public final byte scale_$eq(byte scale) {
        this.scale(scale);
        return scale;
    }

    @Field(value=2)
    public byte sign() {
        return this.io.getByteField(this, 2);
    }

    @Field(value=2)
    public DECIMAL sign(byte sign) {
        this.io.setByteField(this, 2, sign);
        return this;
    }

    public final byte sign_$eq(byte sign) {
        this.sign(sign);
        return sign;
    }

    @Field(value=3)
    public int Hi32() {
        return this.io.getIntField(this, 3);
    }

    @Field(value=3)
    public DECIMAL Hi32(int Hi32) {
        this.io.setIntField(this, 3, Hi32);
        return this;
    }

    public final int Hi32_$eq(int Hi32) {
        this.Hi32(Hi32);
        return Hi32;
    }

    @Field(value=4)
    public long Lo64() {
        return this.io.getLongField(this, 4);
    }

    @Field(value=4)
    public DECIMAL Lo64(long Lo64) {
        this.io.setLongField(this, 4, Lo64);
        return this;
    }

    public final long Lo64_$eq(long Lo64) {
        this.Lo64(Lo64);
        return Lo64;
    }
}

