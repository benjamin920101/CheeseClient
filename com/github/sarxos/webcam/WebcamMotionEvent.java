/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamMotionDetector;
import java.awt.Point;
import java.util.EventObject;

public class WebcamMotionEvent
extends EventObject {
    private static final long serialVersionUID = -7245768099221999443L;
    private final double strength;
    private final Point cog;

    public WebcamMotionEvent(WebcamMotionDetector detector, double strength, Point cog) {
        super(detector);
        this.strength = strength;
        this.cog = cog;
    }

    public double getArea() {
        return this.strength;
    }

    public Point getCog() {
        return this.cog;
    }
}

