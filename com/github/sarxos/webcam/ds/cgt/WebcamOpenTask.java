/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.cgt;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamOpenTask
extends WebcamTask {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamOpenTask.class);

    public WebcamOpenTask(WebcamDriver driver, WebcamDevice device) {
        super(driver, device);
    }

    public void open() throws InterruptedException {
        this.process();
    }

    @Override
    protected void handle() {
        WebcamDevice device = this.getDevice();
        if (device.isOpen()) {
            return;
        }
        if (device.getResolution() == null) {
            device.setResolution(device.getResolutions()[0]);
        }
        LOG.info("Opening webcam {}", (Object)device.getName());
        device.open();
    }
}

