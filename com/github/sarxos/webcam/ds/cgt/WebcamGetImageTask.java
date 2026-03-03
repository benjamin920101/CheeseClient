/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.cgt;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;
import java.awt.image.BufferedImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamGetImageTask
extends WebcamTask {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamGetImageTask.class);
    private volatile BufferedImage image = null;

    public WebcamGetImageTask(WebcamDriver driver, WebcamDevice device) {
        super(driver, device);
    }

    public BufferedImage getImage() {
        try {
            this.process();
        }
        catch (InterruptedException e2) {
            LOG.debug("Interrupted exception", e2);
            return null;
        }
        return this.image;
    }

    @Override
    protected void handle() {
        WebcamDevice device = this.getDevice();
        if (!device.isOpen()) {
            return;
        }
        this.image = device.getImage();
    }
}

