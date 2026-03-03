/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDevice;
import java.util.List;

public interface WebcamDriver {
    public List<WebcamDevice> getDevices();

    public boolean isThreadSafe();
}

