/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import java.util.EventObject;

public class WebcamDiscoveryEvent
extends EventObject {
    private static final long serialVersionUID = 1L;
    public static final int ADDED = 1;
    public static final int REMOVED = 2;
    private int type = -1;

    public WebcamDiscoveryEvent(Webcam webcam, int type) {
        super(webcam);
        this.type = type;
    }

    public Webcam getWebcam() {
        return (Webcam)this.getSource();
    }

    public int getType() {
        return this.type;
    }
}

