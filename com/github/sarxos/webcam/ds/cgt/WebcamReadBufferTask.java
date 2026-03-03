/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.cgt;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;
import java.nio.ByteBuffer;

public class WebcamReadBufferTask
extends WebcamTask {
    private volatile ByteBuffer target = null;

    public WebcamReadBufferTask(WebcamDriver driver, WebcamDevice device, ByteBuffer target) {
        super(driver, device);
        this.target = target;
    }

    public ByteBuffer readBuffer() {
        try {
            this.process();
        }
        catch (InterruptedException e2) {
            return null;
        }
        return this.target;
    }

    @Override
    protected void handle() {
        WebcamDevice device = this.getDevice();
        if (!device.isOpen()) {
            return;
        }
        if (!(device instanceof WebcamDevice.BufferAccess)) {
            return;
        }
        ((WebcamDevice.BufferAccess)((Object)device)).getImageBytes(this.target);
    }
}

