/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.buildin.natives;

import com.github.sarxos.webcam.ds.buildin.natives.Device;
import java.util.ArrayList;
import java.util.List;
import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.cpp.CPPObject;

@Library(value="OpenIMAJGrabber")
public class DeviceList
extends CPPObject {
    public DeviceList() {
    }

    public DeviceList(Pointer pointer) {
        super(pointer);
    }

    @Field(value=0)
    protected int nDevices() {
        return this.io.getIntField(this, 0);
    }

    @Field(value=0)
    protected DeviceList nDevices(int nDevices) {
        this.io.setIntField(this, 0, nDevices);
        return this;
    }

    @Field(value=1)
    protected Pointer<Pointer<Device>> devices() {
        return this.io.getPointerField(this, 1);
    }

    @Field(value=1)
    protected DeviceList devices(Pointer<Pointer<Device>> devices) {
        this.io.setPointerField(this, 1, devices);
        return this;
    }

    public native int getNumDevices();

    public native Pointer<Device> getDevice(int var1);

    public List<Device> asArrayList() {
        ArrayList<Device> devices = new ArrayList<Device>();
        for (int i2 = 0; i2 < this.getNumDevices(); ++i2) {
            devices.add(this.getDevice(i2).get());
        }
        return devices;
    }
}

