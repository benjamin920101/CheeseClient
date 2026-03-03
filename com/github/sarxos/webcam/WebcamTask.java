/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamProcessor;

public abstract class WebcamTask {
    private boolean doSync = true;
    private WebcamProcessor processor = null;
    private WebcamDevice device = null;
    private Throwable throwable = null;

    public WebcamTask(boolean threadSafe, WebcamDevice device) {
        this.doSync = !threadSafe;
        this.device = device;
        this.processor = WebcamProcessor.getInstance();
    }

    public WebcamTask(WebcamDriver driver, WebcamDevice device) {
        this(driver.isThreadSafe(), device);
    }

    public WebcamTask(WebcamDevice device) {
        this(false, device);
    }

    public WebcamDevice getDevice() {
        return this.device;
    }

    public void process() throws InterruptedException {
        boolean alreadyInSync = Thread.currentThread() instanceof WebcamProcessor.ProcessorThread;
        if (alreadyInSync) {
            this.handle();
        } else if (this.doSync) {
            if (this.processor == null) {
                throw new RuntimeException("Driver should be synchronized, but processor is null");
            }
            this.processor.process(this);
        } else {
            this.handle();
        }
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable t2) {
        this.throwable = t2;
    }

    protected abstract void handle();
}

