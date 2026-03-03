/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WebcamShutdownHook
extends Thread {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamShutdownHook.class);
    private static int number = 0;
    private Webcam webcam = null;

    protected WebcamShutdownHook(Webcam webcam) {
        super("shutdown-hook-" + ++number);
        this.webcam = webcam;
        this.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
    }

    @Override
    public void run() {
        LOG.info("Automatic {} deallocation", (Object)this.webcam.getName());
        this.webcam.dispose();
    }
}

