/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.ds.cgt.WebcamGetImageTask;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamUpdater
implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamUpdater.class);
    private static final int TARGET_FPS = 50;
    private static final UpdaterThreadFactory THREAD_FACTORY = new UpdaterThreadFactory();
    private ScheduledExecutorService executor = null;
    private final AtomicReference<BufferedImage> image = new AtomicReference();
    private Webcam webcam = null;
    private volatile double fps = 0.0;
    private AtomicBoolean running = new AtomicBoolean(false);
    private volatile boolean imageNew = false;

    protected WebcamUpdater(Webcam webcam) {
        this.webcam = webcam;
    }

    public void start() {
        if (this.running.compareAndSet(false, true)) {
            this.image.set(new WebcamGetImageTask(Webcam.getDriver(), this.webcam.getDevice()).getImage());
            this.executor = Executors.newSingleThreadScheduledExecutor(THREAD_FACTORY);
            this.executor.execute(this);
            LOG.debug("Webcam updater has been started");
        } else {
            LOG.debug("Webcam updater is already started");
        }
    }

    public void stop() {
        if (this.running.compareAndSet(true, false)) {
            this.executor.shutdown();
            while (!this.executor.isTerminated()) {
                try {
                    this.executor.awaitTermination(100L, TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException e2) {
                    return;
                }
            }
            LOG.debug("Webcam updater has been stopped");
        } else {
            LOG.debug("Webcam updater is already stopped");
        }
    }

    @Override
    public void run() {
        if (!this.running.get()) {
            return;
        }
        try {
            this.tick();
        }
        catch (Throwable t2) {
            WebcamExceptionHandler.handle(t2);
        }
    }

    private void tick() {
        if (!this.webcam.isOpen()) {
            return;
        }
        long t1 = 0L;
        long t2 = 0L;
        WebcamDriver driver = Webcam.getDriver();
        WebcamDevice device = this.webcam.getDevice();
        assert (driver != null);
        assert (device != null);
        BufferedImage img = null;
        t1 = System.currentTimeMillis();
        img = this.webcam.transform(new WebcamGetImageTask(driver, device).getImage());
        t2 = System.currentTimeMillis();
        this.image.set(img);
        this.imageNew = true;
        long delta = t2 - t1 + 1L;
        long delay = Math.max(20L - delta, 0L);
        this.fps = device instanceof WebcamDevice.FPSSource ? ((WebcamDevice.FPSSource)((Object)device)).getFPS() : (4.0 * this.fps + (double)(1000L / delta)) / 5.0;
        if (this.webcam.isOpen()) {
            try {
                this.executor.schedule(this, delay, TimeUnit.MILLISECONDS);
            }
            catch (RejectedExecutionException e2) {
                LOG.trace("Webcam update has been rejected", e2);
            }
        }
        this.webcam.notifyWebcamImageAcquired(this.image.get());
    }

    public BufferedImage getImage() {
        int i2 = 0;
        while (this.image.get() == null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e2) {
                throw new RuntimeException(e2);
            }
            if (i2++ <= 100) continue;
            LOG.error("Image has not been found for more than 10 seconds");
            return null;
        }
        this.imageNew = false;
        return this.image.get();
    }

    protected boolean isImageNew() {
        return this.imageNew;
    }

    public double getFPS() {
        return this.fps;
    }

    private static final class UpdaterThreadFactory
    implements ThreadFactory {
        private static final AtomicInteger number = new AtomicInteger(0);

        private UpdaterThreadFactory() {
        }

        @Override
        public Thread newThread(Runnable r2) {
            Thread t2 = new Thread(r2, String.format("webcam-updater-thread-%d", number.incrementAndGet()));
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            t2.setDaemon(true);
            return t2;
        }
    }
}

