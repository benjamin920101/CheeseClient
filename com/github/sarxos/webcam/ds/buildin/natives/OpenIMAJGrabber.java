/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.buildin.natives;

import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import org.bridj.BridJ;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;

@Library(value="OpenIMAJGrabber")
@Runtime(value=CPPRuntime.class)
public class OpenIMAJGrabber
extends CPPObject {
    public OpenIMAJGrabber() {
    }

    public OpenIMAJGrabber(Pointer pointer) {
        super(pointer);
    }

    public native Pointer<DeviceList> getVideoDevices();

    public native Pointer<Byte> getImage();

    public native int nextFrame();

    public native void setTimeout(int var1);

    public native boolean startSession(int var1, int var2, double var3);

    public native boolean startSession(int var1, int var2, double var3, Pointer<Device> var5);

    public native void stopSession();

    public native int getWidth();

    public native int getHeight();

    @Field(value=0)
    protected Pointer<?> data() {
        return this.io.getPointerField(this, 0);
    }

    @Field(value=0)
    protected OpenIMAJGrabber data(Pointer<?> data) {
        this.io.setPointerField(this, 0, data);
        return this;
    }

    static {
        Platform.addEmbeddedLibraryResourceRoot("com/github/sarxos/webcam/ds/buildin/lib/");
        BridJ.register();
    }
}

