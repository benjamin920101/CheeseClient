/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEventType;
import java.awt.image.BufferedImage;
import java.util.EventObject;

public class WebcamEvent
extends EventObject {
    private static final long serialVersionUID = 1L;
    private BufferedImage image = null;
    private WebcamEventType type = null;

    public WebcamEvent(WebcamEventType type, Webcam w2) {
        this(type, w2, null);
    }

    public WebcamEvent(WebcamEventType type, Webcam w2, BufferedImage image) {
        super(w2);
        this.type = type;
        this.image = image;
    }

    @Override
    public Webcam getSource() {
        return (Webcam)super.getSource();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public WebcamEventType getType() {
        return this.type;
    }
}

