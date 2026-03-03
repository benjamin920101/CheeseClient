/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

public interface WebcamDevice {
    public String getName();

    public Dimension[] getResolutions();

    public Dimension getResolution();

    public void setResolution(Dimension var1);

    public BufferedImage getImage();

    public void open();

    public void close();

    public void dispose();

    public boolean isOpen();

    public static interface FPSSource {
        public double getFPS();
    }

    public static interface BufferAccess {
        public ByteBuffer getImageBytes();

        public void getImageBytes(ByteBuffer var1);
    }
}

