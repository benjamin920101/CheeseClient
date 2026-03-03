/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import com.github.sarxos.webcam.util.jh.JHBlurFilter;
import com.github.sarxos.webcam.util.jh.JHGrayFilter;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamMotionDetector {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamMotionDetector.class);
    private static final AtomicInteger NT = new AtomicInteger(0);
    private static final ThreadFactory THREAD_FACTORY = new DetectorThreadFactory();
    public static final int DEFAULT_PIXEL_THREASHOLD = 25;
    public static final int DEFAULT_INTERVAL = 500;
    public static final double DEFAULT_AREA_THREASHOLD = 0.2;
    private final ExecutorService executor = Executors.newFixedThreadPool(2, THREAD_FACTORY);
    private final List<WebcamMotionListener> listeners = new ArrayList<WebcamMotionListener>();
    private final AtomicBoolean running = new AtomicBoolean(false);
    private volatile boolean motion = false;
    private BufferedImage previous = null;
    private Webcam webcam = null;
    private volatile int interval = 500;
    private volatile int pixelThreshold = 25;
    private volatile double areaThreshold = 0.2;
    private volatile int inertia = -1;
    private double area = 0.0;
    private Point cog = null;
    private volatile long lastMotionTimestamp = 0L;
    private final JHBlurFilter blur = new JHBlurFilter(6.0f, 6.0f, 1);
    private final JHGrayFilter gray = new JHGrayFilter();

    public WebcamMotionDetector(Webcam webcam, int pixelThreshold, double areaThreshold, int interval) {
        this.webcam = webcam;
        this.setPixelThreshold(pixelThreshold);
        this.setAreaThreshold(areaThreshold);
        this.setInterval(interval);
        int w2 = webcam.getViewSize().width;
        int h2 = webcam.getViewSize().height;
        this.cog = new Point(w2 / 2, h2 / 2);
    }

    public WebcamMotionDetector(Webcam webcam, int pixelThreshold, double areaThreshold) {
        this(webcam, pixelThreshold, areaThreshold, 500);
    }

    public WebcamMotionDetector(Webcam webcam, int pixelThreshold) {
        this(webcam, pixelThreshold, 0.2);
    }

    public WebcamMotionDetector(Webcam webcam) {
        this(webcam, 25);
    }

    public void start() {
        if (this.running.compareAndSet(false, true)) {
            this.webcam.open();
            this.executor.submit(new Runner());
            this.executor.submit(new Inverter());
        }
    }

    public void stop() {
        if (this.running.compareAndSet(true, false)) {
            this.webcam.close();
            this.executor.shutdownNow();
        }
    }

    protected void detect() {
        if (!this.webcam.isOpen()) {
            this.motion = false;
            return;
        }
        BufferedImage current = this.webcam.getImage();
        if (current == null) {
            this.motion = false;
            return;
        }
        current = this.blur.filter(current, null);
        current = this.gray.filter(current, null);
        int p2 = 0;
        int cogX = 0;
        int cogY = 0;
        int w2 = current.getWidth();
        int h2 = current.getHeight();
        if (this.previous != null) {
            for (int x2 = 0; x2 < w2; ++x2) {
                for (int y2 = 0; y2 < h2; ++y2) {
                    int ppx;
                    int cpx = current.getRGB(x2, y2);
                    int pid = WebcamMotionDetector.combinePixels(cpx, ppx = this.previous.getRGB(x2, y2)) & 0xFF;
                    if (pid < this.pixelThreshold) continue;
                    cogX += x2;
                    cogY += y2;
                    ++p2;
                }
            }
        }
        this.area = (double)p2 * 100.0 / (double)(w2 * h2);
        if (this.area >= this.areaThreshold) {
            this.cog = new Point(cogX / p2, cogY / p2);
            this.motion = true;
            this.lastMotionTimestamp = System.currentTimeMillis();
            this.notifyMotionListeners();
        } else {
            this.cog = new Point(w2 / 2, h2 / 2);
        }
        this.previous = current;
    }

    private void notifyMotionListeners() {
        WebcamMotionEvent wme = new WebcamMotionEvent(this, this.area, this.cog);
        for (WebcamMotionListener l2 : this.listeners) {
            try {
                l2.motionDetected(wme);
            }
            catch (Exception e2) {
                WebcamExceptionHandler.handle(e2);
            }
        }
    }

    public boolean addMotionListener(WebcamMotionListener l2) {
        return this.listeners.add(l2);
    }

    public WebcamMotionListener[] getMotionListeners() {
        return this.listeners.toArray(new WebcamMotionListener[this.listeners.size()]);
    }

    public boolean removeMotionListener(WebcamMotionListener l2) {
        return this.listeners.remove(l2);
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        if (interval < 100) {
            throw new IllegalArgumentException("Motion check interval cannot be less than 100 ms");
        }
        this.interval = interval;
    }

    public void setPixelThreshold(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Pixel intensity threshold cannot be negative!");
        }
        if (threshold > 255) {
            throw new IllegalArgumentException("Pixel intensity threshold cannot be higher than 255!");
        }
        this.pixelThreshold = threshold;
    }

    public void setAreaThreshold(double threshold) {
        if (threshold < 0.0) {
            throw new IllegalArgumentException("Area fraction threshold cannot be negative!");
        }
        if (threshold > 100.0) {
            throw new IllegalArgumentException("Area fraction threshold cannot be higher than 100!");
        }
        this.areaThreshold = threshold;
    }

    public void setInertia(int inertia) {
        if (inertia < 0) {
            throw new IllegalArgumentException("Inertia time must not be negative!");
        }
        this.inertia = inertia;
    }

    public void clearInertia() {
        this.inertia = -1;
    }

    public Webcam getWebcam() {
        return this.webcam;
    }

    public boolean isMotion() {
        if (!this.running.get()) {
            LOG.warn("Motion cannot be detected when detector is not running!");
        }
        return this.motion;
    }

    public double getMotionArea() {
        return this.area;
    }

    public Point getMotionCog() {
        return this.cog;
    }

    private static int combinePixels(int rgb1, int rgb2) {
        int a1 = rgb1 >> 24 & 0xFF;
        int r1 = rgb1 >> 16 & 0xFF;
        int g1 = rgb1 >> 8 & 0xFF;
        int b1 = rgb1 & 0xFF;
        int a2 = rgb2 >> 24 & 0xFF;
        int r2 = rgb2 >> 16 & 0xFF;
        int g2 = rgb2 >> 8 & 0xFF;
        int b2 = rgb2 & 0xFF;
        r1 = WebcamMotionDetector.clamp(Math.abs(r1 - r2));
        g1 = WebcamMotionDetector.clamp(Math.abs(g1 - g2));
        b1 = WebcamMotionDetector.clamp(Math.abs(b1 - b2));
        if (a1 != 255) {
            a1 = a1 * 255 / 255;
            int a3 = (255 - a1) * a2 / 255;
            r1 = WebcamMotionDetector.clamp((r1 * a1 + r2 * a3) / 255);
            g1 = WebcamMotionDetector.clamp((g1 * a1 + g2 * a3) / 255);
            b1 = WebcamMotionDetector.clamp((b1 * a1 + b2 * a3) / 255);
            a1 = WebcamMotionDetector.clamp(a1 + a3);
        }
        return a1 << 24 | r1 << 16 | g1 << 8 | b1;
    }

    private static int clamp(int c2) {
        if (c2 < 0) {
            return 0;
        }
        if (c2 > 255) {
            return 255;
        }
        return c2;
    }

    private class Inverter
    implements Runnable {
        private Inverter() {
        }

        @Override
        public void run() {
            int delay = 0;
            while (WebcamMotionDetector.this.running.get()) {
                try {
                    Thread.sleep(10L);
                }
                catch (InterruptedException e2) {
                    break;
                }
                int n2 = delay = WebcamMotionDetector.this.inertia != -1 ? WebcamMotionDetector.this.inertia : 2 * WebcamMotionDetector.this.interval;
                if (WebcamMotionDetector.this.lastMotionTimestamp + (long)delay >= System.currentTimeMillis()) continue;
                WebcamMotionDetector.this.motion = false;
            }
        }
    }

    private class Runner
    implements Runnable {
        private Runner() {
        }

        @Override
        public void run() {
            WebcamMotionDetector.this.running.set(true);
            while (WebcamMotionDetector.this.running.get() && WebcamMotionDetector.this.webcam.isOpen()) {
                try {
                    WebcamMotionDetector.this.detect();
                    Thread.sleep(WebcamMotionDetector.this.interval);
                }
                catch (InterruptedException e2) {
                    break;
                }
                catch (Exception e3) {
                    WebcamExceptionHandler.handle(e3);
                }
            }
            WebcamMotionDetector.this.running.set(false);
        }
    }

    private static final class DetectorThreadFactory
    implements ThreadFactory {
        private DetectorThreadFactory() {
        }

        @Override
        public Thread newThread(Runnable runnable) {
            Thread t2 = new Thread(runnable, String.format("motion-detector-%d", NT.incrementAndGet()));
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            t2.setDaemon(true);
            return t2;
        }
    }
}

