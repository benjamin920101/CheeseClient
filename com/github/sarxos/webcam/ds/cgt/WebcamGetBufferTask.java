/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.cgt;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;
import java.nio.ByteBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamGetBufferTask
extends WebcamTask {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamGetBufferTask.class);
    private volatile ByteBuffer buffer = null;

    public WebcamGetBufferTask(WebcamDriver driver, WebcamDevice device) {
        super(driver, device);
    }

    public ByteBuffer getBuffer() {
        try {
            this.process();
        }
        catch (InterruptedException e2) {
            LOG.debug("Image buffer request interrupted", e2);
            return null;
        }
        return this.buffer;
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
        this.buffer = ((WebcamDevice.BufferAccess)((Object)device)).getImageBytes();
    }
}

