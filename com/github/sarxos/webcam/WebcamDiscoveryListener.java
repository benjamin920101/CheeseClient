/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDiscoveryEvent;

public interface WebcamDiscoveryListener {
    public void webcamFound(WebcamDiscoveryEvent var1);

    public void webcamGone(WebcamDiscoveryEvent var1);
}

