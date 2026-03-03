/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamUtils;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamPanel
extends JPanel
implements WebcamListener,
PropertyChangeListener {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(WebcamPanel.class);
    public static final double MIN_FREQUENCY = 0.016;
    private static final double MAX_FREQUENCY = 50.0;
    private static final ThreadFactory THREAD_FACTORY = new PanelThreadFactory();
    public static final Map<RenderingHints.Key, Object> DEFAULT_IMAGE_RENDERING_HINTS = new HashMap<RenderingHints.Key, Object>();
    private final Runnable repaint = new SwingRepainter(this);
    private Map<RenderingHints.Key, Object> imageRenderingHints = new HashMap<RenderingHints.Key, Object>(DEFAULT_IMAGE_RENDERING_HINTS);
    private ScheduledExecutorService executor = null;
    private ResourceBundle rb = null;
    private DrawMode drawMode = DrawMode.FIT;
    private double frequency = 5.0;
    private boolean frequencyLimit = false;
    private boolean frequencyDisplayed = false;
    private boolean imageSizeDisplayed = false;
    private boolean antialiasingEnabled = true;
    private final Webcam webcam;
    private final ImageUpdater updater;
    private BufferedImage image = null;
    private volatile boolean starting = false;
    private volatile boolean paused = false;
    private volatile boolean errored = false;
    private final AtomicBoolean started = new AtomicBoolean(false);
    private final Painter defaultPainter;
    private Painter painter = this.defaultPainter = new DefaultPainter();
    private Dimension defaultSize = null;
    private boolean displayDebugInfo = false;
    private boolean mirrored = false;

    public WebcamPanel(Webcam webcam) {
        this(webcam, true);
    }

    public WebcamPanel(Webcam webcam, boolean start) {
        this(webcam, null, start);
    }

    public WebcamPanel(Webcam webcam, Dimension size, boolean start) {
        if (webcam == null) {
            throw new IllegalArgumentException(String.format("Webcam argument in %s constructor cannot be null!", this.getClass().getSimpleName()));
        }
        this.defaultSize = size;
        this.webcam = webcam;
        this.updater = new ImageUpdater();
        this.rb = WebcamUtils.loadRB(WebcamPanel.class, this.getLocale());
        this.setDoubleBuffered(true);
        this.addPropertyChangeListener("locale", this);
        if (size == null) {
            Dimension r2 = webcam.getViewSize();
            if (r2 == null) {
                r2 = webcam.getViewSizes()[0];
            }
            this.setPreferredSize(r2);
        } else {
            this.setPreferredSize(size);
        }
        if (start) {
            this.start();
        }
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public Painter getPainter() {
        return this.painter;
    }

    @Override
    protected void paintComponent(Graphics g2) {
        super.paintComponent(g2);
        if (this.image == null) {
            this.painter.paintPanel(this, (Graphics2D)g2);
        } else {
            this.painter.paintImage(this, this.image, (Graphics2D)g2);
        }
    }

    public void start() {
        if (!this.started.compareAndSet(false, true)) {
            return;
        }
        this.webcam.addWebcamListener(this);
        LOG.debug("Starting panel rendering and trying to open attached webcam");
        this.updater.start();
        this.starting = true;
        try {
            if (!this.webcam.isOpen()) {
                this.errored = !this.webcam.open();
            }
        }
        catch (WebcamException e2) {
            this.errored = true;
            throw e2;
        }
        finally {
            this.starting = false;
            this.repaintPanel();
        }
    }

    public void stop() {
        if (!this.started.compareAndSet(true, false)) {
            return;
        }
        this.webcam.removeWebcamListener(this);
        LOG.debug("Stopping panel rendering and closing attached webcam");
        try {
            this.updater.stop();
        }
        catch (InterruptedException e2) {
            throw new RuntimeException(e2);
        }
        this.image = null;
        try {
            if (this.webcam.isOpen()) {
                this.errored = !this.webcam.close();
            }
        }
        catch (WebcamException e3) {
            this.errored = true;
            throw e3;
        }
        finally {
            this.repaintPanel();
        }
    }

    private void repaintPanel() {
        SwingUtilities.invokeLater(this.repaint);
    }

    public void pause() {
        if (this.paused) {
            return;
        }
        LOG.debug("Pausing panel rendering");
        this.paused = true;
    }

    public void resume() {
        if (!this.paused) {
            return;
        }
        LOG.debug("Resuming panel rendering");
        this.paused = false;
    }

    public boolean isFPSLimited() {
        return this.frequencyLimit;
    }

    public void setFPSLimited(boolean frequencyLimit) {
        this.frequencyLimit = frequencyLimit;
    }

    public double getFPSLimit() {
        return this.frequency;
    }

    public void setFPSLimit(double fps) {
        if (fps > 50.0) {
            fps = 50.0;
        }
        if (fps < 0.016) {
            fps = 0.016;
        }
        this.frequency = fps;
    }

    public boolean isDisplayDebugInfo() {
        return this.displayDebugInfo;
    }

    public void setDisplayDebugInfo(boolean displayDebugInfo) {
        this.displayDebugInfo = displayDebugInfo;
    }

    public boolean isFPSDisplayed() {
        return this.frequencyDisplayed;
    }

    public void setFPSDisplayed(boolean displayed) {
        this.frequencyDisplayed = displayed;
    }

    public boolean isImageSizeDisplayed() {
        return this.imageSizeDisplayed;
    }

    public void setImageSizeDisplayed(boolean imageSizeDisplayed) {
        this.imageSizeDisplayed = imageSizeDisplayed;
    }

    public void setAntialiasingEnabled(boolean antialiasing) {
        this.antialiasingEnabled = antialiasing;
    }

    public boolean isAntialiasingEnabled() {
        return this.antialiasingEnabled;
    }

    public boolean isStarting() {
        return this.starting;
    }

    public boolean isStarted() {
        return this.started.get();
    }

    public boolean isFitArea() {
        return this.drawMode == DrawMode.FIT;
    }

    public void setFitArea(boolean fitArea) {
        this.drawMode = fitArea ? DrawMode.FIT : DrawMode.NONE;
    }

    public void setFillArea(boolean fillArea) {
        this.drawMode = fillArea ? DrawMode.FILL : DrawMode.NONE;
    }

    public boolean isFillArea() {
        return this.drawMode == DrawMode.FILL;
    }

    public Painter getDefaultPainter() {
        return this.defaultPainter;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Locale lc2 = (Locale)evt.getNewValue();
        if (lc2 != null) {
            this.rb = WebcamUtils.loadRB(WebcamPanel.class, lc2);
        }
    }

    @Override
    public void webcamOpen(WebcamEvent we2) {
        if (this.defaultSize == null) {
            this.setPreferredSize(this.webcam.getViewSize());
        }
    }

    @Override
    public void webcamClosed(WebcamEvent we2) {
        this.stop();
    }

    @Override
    public void webcamDisposed(WebcamEvent we2) {
        this.stop();
    }

    @Override
    public void webcamImageObtained(WebcamEvent we2) {
    }

    public boolean isMirrored() {
        return this.mirrored;
    }

    public void setMirrored(boolean mirrored) {
        this.mirrored = mirrored;
    }

    static {
        DEFAULT_IMAGE_RENDERING_HINTS.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        DEFAULT_IMAGE_RENDERING_HINTS.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        DEFAULT_IMAGE_RENDERING_HINTS.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private class ImageUpdater
    implements Runnable {
        private Thread scheduler = null;
        private AtomicBoolean running = new AtomicBoolean(false);

        private ImageUpdater() {
        }

        public void start() {
            if (this.running.compareAndSet(false, true)) {
                WebcamPanel.this.executor = Executors.newScheduledThreadPool(1, THREAD_FACTORY);
                this.scheduler = new RepaintScheduler();
                this.scheduler.start();
            }
        }

        public void stop() throws InterruptedException {
            if (this.running.compareAndSet(true, false)) {
                WebcamPanel.this.executor.shutdown();
                WebcamPanel.this.executor.awaitTermination(5000L, TimeUnit.MILLISECONDS);
                this.scheduler.join();
            }
        }

        @Override
        public void run() {
            try {
                this.update();
            }
            catch (Throwable t2) {
                WebcamPanel.this.errored = true;
                WebcamExceptionHandler.handle(t2);
            }
        }

        private void update() {
            if (!this.running.get() || !WebcamPanel.this.webcam.isOpen() || WebcamPanel.this.paused) {
                return;
            }
            BufferedImage tmp = WebcamPanel.this.webcam.getImage();
            boolean repaint = true;
            if (tmp != null) {
                if (WebcamPanel.this.image == tmp) {
                    repaint = false;
                }
                WebcamPanel.this.errored = false;
                WebcamPanel.this.image = tmp;
            }
            if (repaint) {
                WebcamPanel.this.repaintPanel();
            }
        }

        private class RepaintScheduler
        extends Thread {
            public RepaintScheduler() {
                this.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
                this.setName(String.format("repaint-scheduler-%s", WebcamPanel.this.webcam.getName()));
                this.setDaemon(true);
            }

            @Override
            public void run() {
                if (!ImageUpdater.this.running.get()) {
                    return;
                }
                WebcamPanel.this.repaintPanel();
                while (WebcamPanel.this.starting) {
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException e2) {
                        throw new RuntimeException(e2);
                    }
                }
                try {
                    if (WebcamPanel.this.webcam.isOpen()) {
                        if (WebcamPanel.this.isFPSLimited()) {
                            WebcamPanel.this.executor.scheduleAtFixedRate(WebcamPanel.this.updater, 0L, (long)(1000.0 / WebcamPanel.this.frequency), TimeUnit.MILLISECONDS);
                        } else {
                            WebcamPanel.this.executor.scheduleWithFixedDelay(WebcamPanel.this.updater, 100L, 1L, TimeUnit.MILLISECONDS);
                        }
                    } else {
                        WebcamPanel.this.executor.schedule(this, 500L, TimeUnit.MILLISECONDS);
                    }
                }
                catch (RejectedExecutionException e3) {
                    LOG.warn("Executor rejected paint update");
                    LOG.trace("Executor rejected paint update because of", e3);
                    return;
                }
            }
        }
    }

    private static final class SwingRepainter
    implements Runnable {
        private WebcamPanel panel = null;

        public SwingRepainter(WebcamPanel panel) {
            this.panel = panel;
        }

        @Override
        public void run() {
            this.panel.repaint();
        }
    }

    private static final class PanelThreadFactory
    implements ThreadFactory {
        private static final AtomicInteger number = new AtomicInteger(0);

        private PanelThreadFactory() {
        }

        @Override
        public Thread newThread(Runnable r2) {
            Thread t2 = new Thread(r2, String.format("webcam-panel-scheduled-executor-%d", number.incrementAndGet()));
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            t2.setDaemon(true);
            return t2;
        }
    }

    public class DefaultPainter
    implements Painter {
        private String name = null;
        private long lastRepaintTime = -1L;
        private BufferedImage resizedImage = null;

        @Override
        public void paintPanel(WebcamPanel owner, Graphics2D g2) {
            assert (owner != null);
            assert (g2 != null);
            Object antialiasing = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, WebcamPanel.this.isAntialiasingEnabled() ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setBackground(Color.BLACK);
            g2.fillRect(0, 0, WebcamPanel.this.getWidth(), WebcamPanel.this.getHeight());
            int cx2 = (WebcamPanel.this.getWidth() - 70) / 2;
            int cy2 = (WebcamPanel.this.getHeight() - 40) / 2;
            g2.setStroke(new BasicStroke(2.0f));
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRoundRect(cx2, cy2, 70, 40, 10, 10);
            g2.setColor(Color.WHITE);
            g2.fillOval(cx2 + 5, cy2 + 5, 30, 30);
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillOval(cx2 + 10, cy2 + 10, 20, 20);
            g2.setColor(Color.WHITE);
            g2.fillOval(cx2 + 12, cy2 + 12, 16, 16);
            g2.fillRoundRect(cx2 + 50, cy2 + 5, 15, 10, 5, 5);
            g2.fillRect(cx2 + 63, cy2 + 25, 7, 2);
            g2.fillRect(cx2 + 63, cy2 + 28, 7, 2);
            g2.fillRect(cx2 + 63, cy2 + 31, 7, 2);
            g2.setColor(Color.DARK_GRAY);
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawLine(0, 0, WebcamPanel.this.getWidth(), WebcamPanel.this.getHeight());
            g2.drawLine(0, WebcamPanel.this.getHeight(), WebcamPanel.this.getWidth(), 0);
            String str = null;
            String strInitDevice = WebcamPanel.this.rb.getString("INITIALIZING_DEVICE");
            String strNoImage = WebcamPanel.this.rb.getString("NO_IMAGE");
            String strDeviceError = WebcamPanel.this.rb.getString("DEVICE_ERROR");
            str = !WebcamPanel.this.errored ? (WebcamPanel.this.starting ? strInitDevice : strNoImage) : strDeviceError;
            FontMetrics metrics = g2.getFontMetrics(WebcamPanel.this.getFont());
            int w2 = metrics.stringWidth(str);
            int h2 = metrics.getHeight();
            int x2 = (WebcamPanel.this.getWidth() - w2) / 2;
            int y2 = cy2 - h2;
            g2.setFont(WebcamPanel.this.getFont());
            g2.setColor(Color.WHITE);
            g2.drawString(str, x2, y2);
            if (this.name == null) {
                this.name = WebcamPanel.this.webcam.getName();
            }
            str = this.name;
            w2 = metrics.stringWidth(str);
            h2 = metrics.getHeight();
            g2.drawString(str, (WebcamPanel.this.getWidth() - w2) / 2, cy2 - 2 * h2);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialiasing);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void paintImage(WebcamPanel owner, BufferedImage image, Graphics2D g2) {
            assert (owner != null);
            assert (image != null);
            assert (g2 != null);
            int pw2 = WebcamPanel.this.getWidth();
            int ph2 = WebcamPanel.this.getHeight();
            int iw2 = image.getWidth();
            int ih2 = image.getHeight();
            Object antialiasing = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
            Object rendering = g2.getRenderingHint(RenderingHints.KEY_RENDERING);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2.setBackground(Color.BLACK);
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, pw2, ph2);
            int x2 = 0;
            int y2 = 0;
            int w2 = 0;
            int h2 = 0;
            switch (WebcamPanel.this.drawMode) {
                case NONE: {
                    w2 = image.getWidth();
                    h2 = image.getHeight();
                    break;
                }
                case FILL: {
                    w2 = pw2;
                    h2 = ph2;
                    break;
                }
                case FIT: {
                    double s2 = Math.max((double)iw2 / (double)pw2, (double)ih2 / (double)ph2);
                    double niw = (double)iw2 / s2;
                    double nih = (double)ih2 / s2;
                    double dx2 = ((double)pw2 - niw) / 2.0;
                    double dy2 = ((double)ph2 - nih) / 2.0;
                    w2 = (int)niw;
                    h2 = (int)nih;
                    x2 = (int)dx2;
                    y2 = (int)dy2;
                }
            }
            if (this.resizedImage != null) {
                this.resizedImage.flush();
            }
            if (w2 == image.getWidth() && h2 == image.getHeight() && !WebcamPanel.this.mirrored) {
                this.resizedImage = image;
            } else {
                GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsConfiguration gc2 = genv.getDefaultScreenDevice().getDefaultConfiguration();
                Graphics gr2 = null;
                try {
                    int sy2;
                    int sx2;
                    int sy1;
                    int sx1;
                    this.resizedImage = gc2.createCompatibleImage(pw2, ph2);
                    gr2 = this.resizedImage.createGraphics();
                    ((Graphics2D)gr2).setComposite(AlphaComposite.Src);
                    for (Map.Entry hint : WebcamPanel.this.imageRenderingHints.entrySet()) {
                        ((Graphics2D)gr2).setRenderingHint((RenderingHints.Key)hint.getKey(), hint.getValue());
                    }
                    ((Graphics2D)gr2).setBackground(Color.BLACK);
                    gr2.setColor(Color.BLACK);
                    gr2.fillRect(0, 0, pw2, ph2);
                    int dx1 = x2;
                    int dy1 = y2;
                    int dx2 = x2 + w2;
                    int dy2 = y2 + h2;
                    if (WebcamPanel.this.mirrored) {
                        sx1 = iw2;
                        sy1 = 0;
                        sx2 = 0;
                        sy2 = ih2;
                    } else {
                        sx1 = 0;
                        sy1 = 0;
                        sx2 = iw2;
                        sy2 = ih2;
                    }
                    gr2.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
                }
                finally {
                    if (gr2 != null) {
                        gr2.dispose();
                    }
                }
            }
            g2.drawImage((Image)this.resizedImage, 0, 0, null);
            if (WebcamPanel.this.isFPSDisplayed()) {
                String str = String.format("FPS: %.1f", WebcamPanel.this.webcam.getFPS());
                int sx2 = 5;
                int sy2 = ph2 - 5;
                g2.setFont(WebcamPanel.this.getFont());
                g2.setColor(Color.BLACK);
                g2.drawString(str, sx2 + 1, sy2 + 1);
                g2.setColor(Color.WHITE);
                g2.drawString(str, sx2, sy2);
            }
            if (WebcamPanel.this.isImageSizeDisplayed()) {
                String res = String.format("%d\u2a2f%d px", iw2, ih2);
                FontMetrics metrics = g2.getFontMetrics(WebcamPanel.this.getFont());
                int sw2 = metrics.stringWidth(res);
                int sx3 = pw2 - sw2 - 5;
                int sy3 = ph2 - 5;
                g2.setFont(WebcamPanel.this.getFont());
                g2.setColor(Color.BLACK);
                g2.drawString(res, sx3 + 1, sy3 + 1);
                g2.setColor(Color.WHITE);
                g2.drawString(res, sx3, sy3);
            }
            if (WebcamPanel.this.isDisplayDebugInfo()) {
                if (this.lastRepaintTime < 0L) {
                    this.lastRepaintTime = System.currentTimeMillis();
                } else {
                    long now = System.currentTimeMillis();
                    String res = String.format("DEBUG: repaints per second: %.1f", 1000.0 / (double)(now - this.lastRepaintTime));
                    this.lastRepaintTime = now;
                    g2.setFont(WebcamPanel.this.getFont());
                    g2.setColor(Color.BLACK);
                    g2.drawString(res, 6, 16);
                    g2.setColor(Color.WHITE);
                    g2.drawString(res, 5, 15);
                }
            }
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialiasing);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, rendering);
        }
    }

    public static interface Painter {
        public void paintPanel(WebcamPanel var1, Graphics2D var2);

        public void paintImage(WebcamPanel var1, BufferedImage var2, Graphics2D var3);
    }

    public static enum DrawMode {
        NONE,
        FILL,
        FIT;

    }
}

