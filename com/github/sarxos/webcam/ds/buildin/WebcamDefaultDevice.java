/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam.ds.buildin;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamTask;
import com.github.sarxos.webcam.ds.buildin.natives.Device;
import com.github.sarxos.webcam.ds.buildin.natives.DeviceList;
import com.github.sarxos.webcam.ds.buildin.natives.OpenIMAJGrabber;
import java.awt.Dimension;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.ComponentSampleModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.bridj.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamDefaultDevice
implements WebcamDevice,
WebcamDevice.BufferAccess,
Runnable,
WebcamDevice.FPSSource {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamDefaultDevice.class);
    private static final Dimension[] DIMENSIONS = new Dimension[]{WebcamResolution.QQVGA.getSize(), WebcamResolution.QVGA.getSize(), WebcamResolution.VGA.getSize()};
    private static final int[] BAND_OFFSETS = new int[]{0, 1, 2};
    private static final int[] BITS = new int[]{8, 8, 8};
    private static final int[] OFFSET = new int[]{0};
    private static final int DATA_TYPE = 0;
    private static final ColorSpace COLOR_SPACE = ColorSpace.getInstance(1000);
    private int timeout = 5000;
    private OpenIMAJGrabber grabber = null;
    private Device device = null;
    private Dimension size = null;
    private ComponentSampleModel smodel = null;
    private ColorModel cmodel = null;
    private boolean failOnSizeMismatch = false;
    private final AtomicBoolean disposed = new AtomicBoolean(false);
    private final AtomicBoolean open = new AtomicBoolean(false);
    private final AtomicBoolean fresh = new AtomicBoolean(false);
    private Thread refresher = null;
    private String name = null;
    private String id = null;
    private String fullname = null;
    private long t1 = -1L;
    private long t2 = -1L;
    private volatile double fps = 0.0;

    protected WebcamDefaultDevice(Device device) {
        this.device = device;
        this.name = device.getNameStr();
        this.id = device.getIdentifierStr();
        this.fullname = String.format("%s %s", this.name, this.id);
    }

    @Override
    public String getName() {
        return this.fullname;
    }

    public String getDeviceName() {
        return this.name;
    }

    public String getDeviceId() {
        return this.id;
    }

    public Device getDeviceRef() {
        return this.device;
    }

    @Override
    public Dimension[] getResolutions() {
        return DIMENSIONS;
    }

    @Override
    public Dimension getResolution() {
        if (this.size == null) {
            this.size = this.getResolutions()[0];
        }
        return this.size;
    }

    @Override
    public void setResolution(Dimension size) {
        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }
        if (this.open.get()) {
            throw new IllegalStateException("Cannot change resolution when webcam is open, please close it first");
        }
        this.size = size;
    }

    @Override
    public ByteBuffer getImageBytes() {
        if (this.disposed.get()) {
            LOG.debug("Webcam is disposed, image will be null");
            return null;
        }
        if (!this.open.get()) {
            LOG.debug("Webcam is closed, image will be null");
            return null;
        }
        if (this.fresh.compareAndSet(false, true)) {
            this.updateFrameBuffer();
        }
        LOG.trace("Webcam grabber get image pointer");
        Pointer<Byte> image = this.grabber.getImage();
        this.fresh.set(false);
        if (image == null) {
            LOG.warn("Null array pointer found instead of image");
            return null;
        }
        int length = this.size.width * this.size.height * 3;
        LOG.trace("Webcam device get buffer, read {} bytes", (Object)length);
        return image.getByteBuffer(length);
    }

    @Override
    public void getImageBytes(ByteBuffer target) {
        if (this.disposed.get()) {
            LOG.debug("Webcam is disposed, image will be null");
            return;
        }
        if (!this.open.get()) {
            LOG.debug("Webcam is closed, image will be null");
            return;
        }
        int minSize = this.size.width * this.size.height * 3;
        int curSize = target.remaining();
        if (minSize < curSize) {
            throw new IllegalArgumentException(String.format("Not enough remaining space in target buffer (%d necessary vs %d remaining)", minSize, curSize));
        }
        if (this.fresh.compareAndSet(false, true)) {
            this.updateFrameBuffer();
        }
        LOG.trace("Webcam grabber get image pointer");
        Pointer<Byte> image = this.grabber.getImage();
        this.fresh.set(false);
        if (image == null) {
            LOG.warn("Null array pointer found instead of image");
            return;
        }
        LOG.trace("Webcam device read buffer {} bytes", (Object)minSize);
        image = image.validBytes(minSize);
        image.getBytes(target);
    }

    @Override
    public BufferedImage getImage() {
        ByteBuffer buffer = this.getImageBytes();
        if (buffer == null) {
            LOG.error("Images bytes buffer is null!");
            return null;
        }
        byte[] bytes = new byte[this.size.width * this.size.height * 3];
        byte[][] data = new byte[][]{bytes};
        buffer.get(bytes);
        DataBufferByte dbuf = new DataBufferByte(data, bytes.length, OFFSET);
        WritableRaster raster = Raster.createWritableRaster(this.smodel, dbuf, null);
        BufferedImage bi2 = new BufferedImage(this.cmodel, raster, false, null);
        bi2.flush();
        return bi2;
    }

    @Override
    public void open() {
        if (this.disposed.get()) {
            return;
        }
        LOG.debug("Opening webcam device {}", (Object)this.getName());
        if (this.size == null) {
            this.size = this.getResolutions()[0];
        }
        if (this.size == null) {
            throw new RuntimeException("The resolution size cannot be null");
        }
        LOG.debug("Webcam device {} starting session, size {}", (Object)this.device.getIdentifierStr(), (Object)this.size);
        this.grabber = new OpenIMAJGrabber();
        DeviceList list = this.grabber.getVideoDevices().get();
        for (Device d2 : list.asArrayList()) {
            d2.getNameStr();
            d2.getIdentifierStr();
        }
        boolean started = this.grabber.startSession(this.size.width, this.size.height, 50.0, Pointer.pointerTo(this.device));
        if (!started) {
            throw new WebcamException("Cannot start native grabber!");
        }
        LOG.debug("Webcam device session started");
        Dimension size2 = new Dimension(this.grabber.getWidth(), this.grabber.getHeight());
        int w1 = this.size.width;
        int w2 = size2.width;
        int h1 = this.size.height;
        int h2 = size2.height;
        if (w1 != w2 || h1 != h2) {
            if (this.failOnSizeMismatch) {
                throw new WebcamException(String.format("Different size obtained vs requested - [%dx%d] vs [%dx%d]", w1, h1, w2, h2));
            }
            Object[] args = new Object[]{w1, h1, w2, h2, w2, h2};
            LOG.warn("Different size obtained vs requested - [{}x{}] vs [{}x{}]. Setting correct one. New size is [{}x{}]", args);
            this.size = new Dimension(w2, h2);
        }
        this.smodel = new ComponentSampleModel(0, this.size.width, this.size.height, 3, this.size.width * 3, BAND_OFFSETS);
        this.cmodel = new ComponentColorModel(COLOR_SPACE, BITS, false, false, 1, 0);
        LOG.debug("Initialize buffer");
        int i2 = 0;
        do {
            this.grabber.nextFrame();
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException e2) {
                LOG.error("Nasty interrupted exception", e2);
            }
        } while (++i2 < 3);
        LOG.debug("Webcam device {} is now open", (Object)this);
        this.open.set(true);
        this.refresher = new Thread((Runnable)this, String.format("frames-refresher-[%s]", this.id));
        this.refresher.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
        this.refresher.setDaemon(true);
        this.refresher.start();
    }

    @Override
    public void close() {
        if (!this.open.compareAndSet(true, false)) {
            return;
        }
        LOG.debug("Closing webcam device");
        this.grabber.stopSession();
    }

    @Override
    public void dispose() {
        if (!this.disposed.compareAndSet(false, true)) {
            return;
        }
        LOG.debug("Disposing webcam device {}", (Object)this.getName());
        this.close();
    }

    public void setFailOnSizeMismatch(boolean fail) {
        this.failOnSizeMismatch = fail;
    }

    @Override
    public boolean isOpen() {
        return this.open.get();
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private void updateFrameBuffer() {
        LOG.trace("Next frame");
        if (this.t1 == -1L || this.t2 == -1L) {
            this.t1 = System.currentTimeMillis();
            this.t2 = System.currentTimeMillis();
        }
        int result = new NextFrameTask((WebcamDevice)this).nextFrame();
        this.t1 = this.t2;
        this.t2 = System.currentTimeMillis();
        this.fps = (4.0 * this.fps + (double)(1000L / (this.t2 - this.t1 + 1L))) / 5.0;
        if (result == -1) {
            LOG.error("Timeout when requesting image!");
        } else if (result < -1) {
            LOG.error("Error requesting new frame!");
        }
    }

    @Override
    public void run() {
        do {
            if (Thread.interrupted()) {
                LOG.debug("Refresher has been interrupted");
                return;
            }
            if (!this.open.get()) {
                LOG.debug("Cancelling refresher");
                return;
            }
            this.updateFrameBuffer();
        } while (this.open.get());
    }

    @Override
    public double getFPS() {
        return this.fps;
    }

    private class NextFrameTask
    extends WebcamTask {
        private final AtomicInteger result;

        public NextFrameTask(WebcamDevice device) {
            super(device);
            this.result = new AtomicInteger(0);
        }

        public int nextFrame() {
            try {
                this.process();
            }
            catch (InterruptedException e2) {
                LOG.debug("Image buffer request interrupted", e2);
            }
            return this.result.get();
        }

        @Override
        protected void handle() {
            WebcamDefaultDevice device = (WebcamDefaultDevice)this.getDevice();
            if (!device.isOpen()) {
                return;
            }
            WebcamDefaultDevice.this.grabber.setTimeout(WebcamDefaultDevice.this.timeout);
            this.result.set(WebcamDefaultDevice.this.grabber.nextFrame());
            WebcamDefaultDevice.this.fresh.set(true);
        }
    }
}

