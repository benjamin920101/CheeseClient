/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamEvent;

public interface WebcamListener {
    public void webcamOpen(WebcamEvent var1);

    public void webcamClosed(WebcamEvent var1);

    public void webcamDisposed(WebcamEvent var1);

    public void webcamImageObtained(WebcamEvent var1);
}

