/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.buildin.natives;

import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;

@Library(value="OpenIMAJGrabber")
@Runtime(value=CPPRuntime.class)
public final class Device
extends CPPObject {
    public Device() {
    }

    public Device(Pointer pointer) {
        super(pointer);
    }

    @Field(value=0)
    protected Pointer<Byte> name() {
        return this.io.getPointerField(this, 0);
    }

    @Field(value=0)
    protected Device name(Pointer<Byte> name) {
        this.io.setPointerField(this, 0, name);
        return this;
    }

    @Field(value=1)
    protected Pointer<Byte> identifier() {
        return this.io.getPointerField(this, 1);
    }

    @Field(value=1)
    protected Device identifier(Pointer<Byte> identifier) {
        this.io.setPointerField(this, 1, identifier);
        return this;
    }

    protected native Pointer<Byte> getName();

    public String getNameStr() {
        return this.getName().getCString();
    }

    protected native Pointer<Byte> getIdentifier();

    public String getIdentifierStr() {
        return this.getIdentifier().getCString();
    }

    @Override
    public boolean equals(Object o2) {
        return o2.toString().equals(this.toString());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Device[%s]=\"%s\"", this.getIdentifierStr(), this.getNameStr());
    }
}

