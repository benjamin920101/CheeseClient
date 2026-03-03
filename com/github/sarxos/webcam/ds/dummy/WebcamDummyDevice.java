/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.dummy;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicBoolean;

public class WebcamDummyDevice
implements WebcamDevice {
    private static final Dimension[] DIMENSIONS = new Dimension[]{WebcamResolution.QQVGA.getSize(), WebcamResolution.QVGA.getSize(), WebcamResolution.VGA.getSize()};
    private AtomicBoolean open = new AtomicBoolean(false);
    private Dimension resolution = DIMENSIONS[0];
    private final String name;
    byte r = (byte)(Math.random() * 127.0);
    byte g = (byte)(Math.random() * 127.0);
    byte b = (byte)(Math.random() * 127.0);

    public WebcamDummyDevice(int number) {
        this.name = "Dummy Webcam " + number;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Dimension[] getResolutions() {
        return DIMENSIONS;
    }

    @Override
    public Dimension getResolution() {
        return this.resolution;
    }

    @Override
    public void setResolution(Dimension size) {
        this.resolution = size;
    }

    private void drawRect(Graphics2D g2, int w2, int h2) {
        int rx = (int)((double)w2 * Math.random() / 1.5);
        int ry2 = (int)((double)h2 * Math.random() / 1.5);
        int rw2 = (int)((double)w2 * Math.random() / 1.5);
        int rh2 = (int)((double)w2 * Math.random() / 1.5);
        g2.setColor(new Color((int)(2.147483647E9 * Math.random())));
        g2.fillRect(rx, ry2, rw2, rh2);
    }

    @Override
    public BufferedImage getImage() {
        if (!this.isOpen()) {
            throw new WebcamException("Webcam is not open");
        }
        try {
            Thread.sleep(33L);
        }
        catch (InterruptedException e2) {
            return null;
        }
        Dimension resolution = this.getResolution();
        int w2 = resolution.width;
        int h2 = resolution.height;
        String s2 = this.getName();
        GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc2 = ge2.getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage bi2 = gc2.createCompatibleImage(w2, h2);
        Graphics2D g2 = ge2.createGraphics(bi2);
        byte by = this.r;
        this.r = (byte)(by + 1);
        byte by2 = this.g;
        this.g = (byte)(by2 + 1);
        byte by3 = this.b;
        this.b = (byte)(by3 + 1);
        g2.setBackground(new Color(Math.abs(by), Math.abs(by2), Math.abs(by3)));
        g2.clearRect(0, 0, w2, h2);
        this.drawRect(g2, w2, h2);
        this.drawRect(g2, w2, h2);
        this.drawRect(g2, w2, h2);
        this.drawRect(g2, w2, h2);
        this.drawRect(g2, w2, h2);
        Font font = new Font("sans-serif", 1, 16);
        g2.setFont(font);
        FontMetrics metrics = g2.getFontMetrics(font);
        int sw2 = (w2 - metrics.stringWidth(s2)) / 2;
        int sh2 = (h2 - metrics.getHeight()) / 2 + metrics.getHeight() / 2;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.drawString(s2, sw2 + 1, sh2 + 1);
        g2.setColor(Color.WHITE);
        g2.drawString(s2, sw2, sh2);
        g2.dispose();
        bi2.flush();
        return bi2;
    }

    @Override
    public void open() {
        if (this.open.compareAndSet(false, true)) {
            // empty if block
        }
    }

    @Override
    public void close() {
        if (this.open.compareAndSet(true, false)) {
            // empty if block
        }
    }

    @Override
    public void dispose() {
        this.close();
    }

    @Override
    public boolean isOpen() {
        return this.open.get();
    }
}

