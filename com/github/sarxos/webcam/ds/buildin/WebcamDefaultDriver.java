/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.buildin;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDiscoverySupport;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamTask;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDevice;
import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import com.github.sarxos.webcam.ds.buildin.natives.OpenIMAJGrabber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.bridj.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamDefaultDriver
implements WebcamDriver,
WebcamDiscoverySupport {
    private static final Logger LOG;
    private static OpenIMAJGrabber grabber;

    @Override
    public List<WebcamDevice> getDevices() {
        WebcamNewGrabberTask task;
        LOG.debug("Searching devices");
        if (grabber == null && (grabber = (task = new WebcamNewGrabberTask(this)).newGrabber()) == null) {
            return Collections.emptyList();
        }
        List<WebcamDevice> devices = new GetDevicesTask(this).getDevices(grabber);
        if (LOG.isDebugEnabled()) {
            for (WebcamDevice device : devices) {
                LOG.debug("Found device {}", (Object)device.getName());
            }
        }
        return devices;
    }

    @Override
    public long getScanInterval() {
        return 3000L;
    }

    @Override
    public boolean isScanPossible() {
        return true;
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    static {
        if (!"true".equals(System.getProperty("webcam.debug"))) {
            System.setProperty("bridj.quiet", "true");
        }
        LOG = LoggerFactory.getLogger(WebcamDefaultDriver.class);
        grabber = null;
    }

    private static class GetDevicesTask
    extends WebcamTask {
        private volatile List<WebcamDevice> devices = null;
        private volatile OpenIMAJGrabber grabber = null;

        public GetDevicesTask(WebcamDriver driver) {
            super(driver, null);
        }

        public List<WebcamDevice> getDevices(OpenIMAJGrabber grabber) {
            this.grabber = grabber;
            try {
                this.process();
            }
            catch (InterruptedException e2) {
                LOG.error("Processor has been interrupted");
                return Collections.emptyList();
            }
            return this.devices;
        }

        @Override
        protected void handle() {
            this.devices = new ArrayList<WebcamDevice>();
            Pointer<DeviceList> pointer = this.grabber.getVideoDevices();
            DeviceList list = pointer.get();
            for (Device device : list.asArrayList()) {
                this.devices.add(new WebcamDefaultDevice(device));
            }
        }
    }

    private static class WebcamNewGrabberTask
    extends WebcamTask {
        private AtomicReference<OpenIMAJGrabber> grabber = new AtomicReference();

        public WebcamNewGrabberTask(WebcamDriver driver) {
            super(driver, null);
        }

        public OpenIMAJGrabber newGrabber() {
            try {
                this.process();
            }
            catch (InterruptedException e2) {
                LOG.error("Processor has been interrupted");
                return null;
            }
            return this.grabber.get();
        }

        @Override
        protected void handle() {
            this.grabber.set(new OpenIMAJGrabber());
        }
    }
}

