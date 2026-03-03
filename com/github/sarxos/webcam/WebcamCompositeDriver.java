/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import java.util.ArrayList;
import java.util.List;

public class WebcamCompositeDriver
implements WebcamDriver {
    private List<WebcamDriver> drivers = new ArrayList<WebcamDriver>();

    public WebcamCompositeDriver(WebcamDriver ... drivers) {
        for (WebcamDriver driver : drivers) {
            this.drivers.add(driver);
        }
    }

    public void add(WebcamDriver driver) {
        this.drivers.add(driver);
    }

    public List<WebcamDriver> getDrivers() {
        return this.drivers;
    }

    @Override
    public List<WebcamDevice> getDevices() {
        ArrayList<WebcamDevice> all2 = new ArrayList<WebcamDevice>();
        for (WebcamDriver driver : this.drivers) {
            all2.addAll(driver.getDevices());
        }
        return all2;
    }

    @Override
    public boolean isThreadSafe() {
        boolean safe = true;
        for (WebcamDriver driver : this.drivers) {
            if (!(safe &= driver.isThreadSafe())) break;
        }
        return safe;
    }
}

