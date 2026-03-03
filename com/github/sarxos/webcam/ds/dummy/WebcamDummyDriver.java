/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.dummy;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDiscoverySupport;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.ds.dummy.WebcamDummyDevice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebcamDummyDriver
implements WebcamDriver,
WebcamDiscoverySupport {
    private int count;

    public WebcamDummyDriver(int count) {
        this.count = count;
    }

    @Override
    public long getScanInterval() {
        return 10000L;
    }

    @Override
    public boolean isScanPossible() {
        return true;
    }

    @Override
    public List<WebcamDevice> getDevices() {
        ArrayList<WebcamDummyDevice> devices = new ArrayList<WebcamDummyDevice>();
        for (int i2 = 0; i2 < this.count; ++i2) {
            devices.add(new WebcamDummyDevice(i2));
        }
        return Collections.unmodifiableList(devices);
    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }
}

