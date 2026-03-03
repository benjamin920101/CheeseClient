/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamDiscoveryService;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamDriverUtils;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamEventType;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamImageTransformer;
import com.github.sarxos.webcam.WebcamListener;
import com.github.sarxos.webcam.WebcamLock;
import com.github.sarxos.webcam.WebcamProcessor;
import com.github.sarxos.webcam.WebcamShutdownHook;
import com.github.sarxos.webcam.WebcamUpdater;
import com.github.sarxos.webcam.ds.buildin.WebcamDefaultDriver;
import com.github.sarxos.webcam.ds.cgt.WebcamCloseTask;
import com.github.sarxos.webcam.ds.cgt.WebcamDisposeTask;
import com.github.sarxos.webcam.ds.cgt.WebcamGetBufferTask;
import com.github.sarxos.webcam.ds.cgt.WebcamGetImageTask;
import com.github.sarxos.webcam.ds.cgt.WebcamOpenTask;
import com.github.sarxos.webcam.ds.cgt.WebcamReadBufferTask;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Webcam {
    private static final Logger LOG = LoggerFactory.getLogger(Webcam.class);
    private static final List<String> DRIVERS_LIST = new ArrayList<String>();
    private static final List<Class<?>> DRIVERS_CLASS_LIST = new ArrayList();
    private static final List<WebcamDiscoveryListener> DISCOVERY_LISTENERS = Collections.synchronizedList(new ArrayList());
    private static volatile WebcamDriver driver = null;
    private static volatile WebcamDiscoveryService discovery = null;
    private static boolean deallocOnTermSignal = false;
    private static boolean autoOpen = false;
    private List<WebcamListener> listeners = new CopyOnWriteArrayList<WebcamListener>();
    private List<Dimension> customSizes = new ArrayList<Dimension>();
    private WebcamShutdownHook hook = null;
    private WebcamDevice device = null;
    private AtomicBoolean open = new AtomicBoolean(false);
    private AtomicBoolean disposed = new AtomicBoolean(false);
    private volatile boolean asynchronous = false;
    private volatile double fps = 0.0;
    private volatile WebcamUpdater updater = null;
    private volatile WebcamImageTransformer transformer = null;
    private WebcamLock lock = null;
    private ExecutorService notificator = null;

    protected Webcam(WebcamDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Webcam device cannot be null");
        }
        this.device = device;
        this.lock = new WebcamLock(this);
    }

    protected void notifyWebcamImageAcquired(BufferedImage image) {
        if (this.getWebcamListenersCount() > 0) {
            this.notificator.execute(new ImageNotification(this, image));
        }
    }

    public boolean open() {
        return this.open(false);
    }

    public boolean open(boolean async) {
        if (this.open.compareAndSet(false, true)) {
            assert (this.lock != null);
            this.notificator = Executors.newSingleThreadExecutor(new NotificationThreadFactory());
            this.lock.lock();
            WebcamOpenTask task = new WebcamOpenTask(driver, this.device);
            try {
                task.open();
            }
            catch (InterruptedException e2) {
                this.lock.unlock();
                this.open.set(false);
                LOG.debug("Thread has been interrupted in the middle of webcam opening process!", e2);
                return false;
            }
            catch (WebcamException e3) {
                this.lock.unlock();
                this.open.set(false);
                LOG.debug("Webcam exception when opening", e3);
                throw e3;
            }
            LOG.debug("Webcam is now open {}", (Object)this.getName());
            this.asynchronous = async;
            if (this.asynchronous) {
                if (this.updater == null) {
                    this.updater = new WebcamUpdater(this);
                }
                this.updater.start();
            }
            this.hook = new WebcamShutdownHook(this);
            Runtime.getRuntime().addShutdownHook(this.hook);
            WebcamEvent we2 = new WebcamEvent(WebcamEventType.OPEN, this);
            Iterator<WebcamListener> wli = this.listeners.iterator();
            WebcamListener l2 = null;
            while (wli.hasNext()) {
                l2 = wli.next();
                try {
                    l2.webcamOpen(we2);
                }
                catch (Exception e4) {
                    LOG.error(String.format("Notify webcam open, exception when calling listener %s", l2.getClass()), e4);
                }
            }
        } else {
            LOG.debug("Webcam is already open {}", (Object)this.getName());
        }
        return true;
    }

    public boolean close() {
        if (this.open.compareAndSet(true, false)) {
            LOG.debug("Closing webcam {}", (Object)this.getName());
            assert (this.lock != null);
            WebcamCloseTask task = new WebcamCloseTask(driver, this.device);
            try {
                task.close();
            }
            catch (InterruptedException e2) {
                this.open.set(true);
                LOG.debug("Thread has been interrupted before webcam was closed!", e2);
                return false;
            }
            catch (WebcamException e3) {
                this.open.set(true);
                throw e3;
            }
            if (this.asynchronous) {
                this.updater.stop();
            }
            this.removeShutdownHook();
            this.lock.unlock();
            WebcamEvent we2 = new WebcamEvent(WebcamEventType.CLOSED, this);
            Iterator<WebcamListener> wli = this.listeners.iterator();
            WebcamListener l2 = null;
            while (wli.hasNext()) {
                l2 = wli.next();
                try {
                    l2.webcamClosed(we2);
                }
                catch (Exception e4) {
                    LOG.error(String.format("Notify webcam closed, exception when calling %s listener", l2.getClass()), e4);
                }
            }
            this.notificator.shutdown();
            while (!this.notificator.isTerminated()) {
                try {
                    this.notificator.awaitTermination(100L, TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException e5) {
                    return false;
                }
            }
            LOG.debug("Webcam {} has been closed", (Object)this.getName());
        } else {
            LOG.debug("Webcam {} is already closed", (Object)this.getName());
        }
        return true;
    }

    public WebcamDevice getDevice() {
        assert (this.device != null);
        return this.device;
    }

    protected void dispose() {
        assert (this.disposed != null);
        assert (this.open != null);
        assert (driver != null);
        assert (this.device != null);
        assert (this.listeners != null);
        if (!this.disposed.compareAndSet(false, true)) {
            return;
        }
        this.open.set(false);
        LOG.info("Disposing webcam {}", (Object)this.getName());
        WebcamDisposeTask task = new WebcamDisposeTask(driver, this.device);
        try {
            task.dispose();
        }
        catch (InterruptedException e2) {
            LOG.error("Processor has been interrupted before webcam was disposed!", e2);
            return;
        }
        WebcamEvent we2 = new WebcamEvent(WebcamEventType.DISPOSED, this);
        Iterator<WebcamListener> wli = this.listeners.iterator();
        WebcamListener l2 = null;
        while (wli.hasNext()) {
            l2 = wli.next();
            try {
                l2.webcamClosed(we2);
                l2.webcamDisposed(we2);
            }
            catch (Exception e3) {
                LOG.error(String.format("Notify webcam disposed, exception when calling %s listener", l2.getClass()), e3);
            }
        }
        this.removeShutdownHook();
        LOG.debug("Webcam disposed {}", (Object)this.getName());
    }

    private void removeShutdownHook() {
        if (this.hook != null) {
            try {
                Runtime.getRuntime().removeShutdownHook(this.hook);
            }
            catch (IllegalStateException e2) {
                LOG.trace("Shutdown in progress, cannot remove hook");
            }
        }
    }

    protected BufferedImage transform(BufferedImage image) {
        WebcamImageTransformer tr2;
        if (image != null && (tr2 = this.getImageTransformer()) != null) {
            return tr2.transform(image);
        }
        return image;
    }

    public boolean isOpen() {
        return this.open.get();
    }

    public Dimension getViewSize() {
        return this.device.getResolution();
    }

    public Dimension[] getViewSizes() {
        return this.device.getResolutions();
    }

    public void setCustomViewSizes(Dimension[] sizes) {
        assert (this.customSizes != null);
        if (sizes == null) {
            this.customSizes.clear();
            return;
        }
        this.customSizes = Arrays.asList(sizes);
    }

    public Dimension[] getCustomViewSizes() {
        assert (this.customSizes != null);
        return this.customSizes.toArray(new Dimension[this.customSizes.size()]);
    }

    public void setViewSize(Dimension size) {
        if (size == null) {
            throw new IllegalArgumentException("Resolution cannot be null!");
        }
        if (this.open.get()) {
            throw new IllegalStateException("Cannot change resolution when webcam is open, please close it first");
        }
        Dimension current = this.getViewSize();
        if (current != null && current.width == size.width && current.height == size.height) {
            return;
        }
        Dimension[] predefined = this.getViewSizes();
        Dimension[] custom = this.getCustomViewSizes();
        assert (predefined != null);
        assert (custom != null);
        boolean ok2 = false;
        for (Dimension d2 : predefined) {
            if (d2.width != size.width || d2.height != size.height) continue;
            ok2 = true;
            break;
        }
        if (!ok2) {
            for (Dimension d2 : custom) {
                if (d2.width != size.width || d2.height != size.height) continue;
                ok2 = true;
                break;
            }
        }
        if (!ok2) {
            StringBuilder sb2 = new StringBuilder("Incorrect dimension [");
            sb2.append(size.width).append("x").append(size.height).append("] ");
            sb2.append("possible ones are ");
            for (Dimension d3 : predefined) {
                sb2.append("[").append(d3.width).append("x").append(d3.height).append("] ");
            }
            for (Dimension d3 : custom) {
                sb2.append("[").append(d3.width).append("x").append(d3.height).append("] ");
            }
            throw new IllegalArgumentException(sb2.toString());
        }
        LOG.debug("Setting new resolution {}x{}", (Object)size.width, (Object)size.height);
        this.device.setResolution(size);
    }

    public BufferedImage getImage() {
        if (!this.isReady()) {
            return null;
        }
        long t1 = 0L;
        long t2 = 0L;
        if (this.asynchronous) {
            return this.updater.getImage();
        }
        t1 = System.currentTimeMillis();
        BufferedImage image = this.transform(new WebcamGetImageTask(driver, this.device).getImage());
        t2 = System.currentTimeMillis();
        if (image == null) {
            return null;
        }
        this.fps = this.device instanceof WebcamDevice.FPSSource ? ((WebcamDevice.FPSSource)((Object)this.device)).getFPS() : (4.0 * this.fps + (double)(1000L / (t2 - t1 + 1L))) / 5.0;
        this.notifyWebcamImageAcquired(image);
        return image;
    }

    public boolean isImageNew() {
        if (this.asynchronous) {
            return this.updater.isImageNew();
        }
        return true;
    }

    public double getFPS() {
        if (this.asynchronous) {
            return this.updater.getFPS();
        }
        return this.fps;
    }

    public ByteBuffer getImageBytes() {
        if (!this.isReady()) {
            return null;
        }
        assert (driver != null);
        assert (this.device != null);
        if (this.device instanceof WebcamDevice.BufferAccess) {
            return new WebcamGetBufferTask(driver, this.device).getBuffer();
        }
        throw new IllegalStateException(String.format("Driver %s does not support buffer access", driver.getClass().getName()));
    }

    public void getImageBytes(ByteBuffer target) {
        if (!this.isReady()) {
            return;
        }
        assert (driver != null);
        assert (this.device != null);
        if (!(this.device instanceof WebcamDevice.BufferAccess)) {
            throw new IllegalStateException(String.format("Driver %s does not support buffer access", driver.getClass().getName()));
        }
        new WebcamReadBufferTask(driver, this.device, target).readBuffer();
    }

    private boolean isReady() {
        assert (this.disposed != null);
        assert (this.open != null);
        if (this.disposed.get()) {
            LOG.warn("Cannot get image, webcam has been already disposed");
            return false;
        }
        if (!this.open.get()) {
            if (autoOpen) {
                this.open();
            } else {
                return false;
            }
        }
        return true;
    }

    public static List<Webcam> getWebcams() throws WebcamException {
        try {
            return Webcam.getWebcams(Long.MAX_VALUE);
        }
        catch (TimeoutException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static List<Webcam> getWebcams(long timeout) throws TimeoutException, WebcamException {
        if (timeout < 0L) {
            throw new IllegalArgumentException(String.format("Timeout cannot be negative (%d)", timeout));
        }
        return Webcam.getWebcams(timeout, TimeUnit.MILLISECONDS);
    }

    public static synchronized List<Webcam> getWebcams(long timeout, TimeUnit tunit) throws TimeoutException, WebcamException {
        if (timeout < 0L) {
            throw new IllegalArgumentException(String.format("Timeout cannot be negative (%d)", timeout));
        }
        if (tunit == null) {
            throw new IllegalArgumentException("Time unit cannot be null!");
        }
        WebcamDiscoveryService discovery = Webcam.getDiscoveryService();
        assert (discovery != null);
        List<Webcam> webcams = discovery.getWebcams(timeout, tunit);
        if (!discovery.isRunning()) {
            discovery.start();
        }
        return webcams;
    }

    public static Webcam getDefault() throws WebcamException {
        try {
            return Webcam.getDefault(Long.MAX_VALUE);
        }
        catch (TimeoutException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Webcam getDefault(long timeout) throws TimeoutException, WebcamException {
        if (timeout < 0L) {
            throw new IllegalArgumentException(String.format("Timeout cannot be negative (%d)", timeout));
        }
        return Webcam.getDefault(timeout, TimeUnit.MILLISECONDS);
    }

    public static Webcam getDefault(long timeout, TimeUnit tunit) throws TimeoutException, WebcamException {
        if (timeout < 0L) {
            throw new IllegalArgumentException(String.format("Timeout cannot be negative (%d)", timeout));
        }
        if (tunit == null) {
            throw new IllegalArgumentException("Time unit cannot be null!");
        }
        List<Webcam> webcams = Webcam.getWebcams(timeout, tunit);
        assert (webcams != null);
        if (!webcams.isEmpty()) {
            return webcams.get(0);
        }
        LOG.warn("No webcam has been detected!");
        return null;
    }

    public String getName() {
        assert (this.device != null);
        return this.device.getName();
    }

    public String toString() {
        return String.format("Webcam %s", this.getName());
    }

    public boolean addWebcamListener(WebcamListener l2) {
        if (l2 == null) {
            throw new IllegalArgumentException("Webcam listener cannot be null!");
        }
        assert (this.listeners != null);
        return this.listeners.add(l2);
    }

    public WebcamListener[] getWebcamListeners() {
        assert (this.listeners != null);
        return this.listeners.toArray(new WebcamListener[this.listeners.size()]);
    }

    public int getWebcamListenersCount() {
        assert (this.listeners != null);
        return this.listeners.size();
    }

    public boolean removeWebcamListener(WebcamListener l2) {
        assert (this.listeners != null);
        return this.listeners.remove(l2);
    }

    public static synchronized WebcamDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        if (driver == null) {
            driver = WebcamDriverUtils.findDriver(DRIVERS_LIST, DRIVERS_CLASS_LIST);
        }
        if (driver == null) {
            driver = new WebcamDefaultDriver();
        }
        LOG.info("{} capture driver will be used", (Object)driver.getClass().getSimpleName());
        return driver;
    }

    public static void setDriver(WebcamDriver wd2) {
        if (wd2 == null) {
            throw new IllegalArgumentException("Webcam driver cannot be null!");
        }
        LOG.debug("Setting new capture driver {}", (Object)wd2);
        Webcam.resetDriver();
        driver = wd2;
    }

    public static void setDriver(Class<? extends WebcamDriver> driverClass) {
        if (driverClass == null) {
            throw new IllegalArgumentException("Webcam driver class cannot be null!");
        }
        Webcam.resetDriver();
        try {
            driver = driverClass.newInstance();
        }
        catch (InstantiationException e2) {
            throw new WebcamException(e2);
        }
        catch (IllegalAccessException e3) {
            throw new WebcamException(e3);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void resetDriver() {
        List<String> list = DRIVERS_LIST;
        synchronized (list) {
            DRIVERS_LIST.clear();
        }
        if (discovery != null) {
            discovery.shutdown();
            discovery = null;
        }
        driver = null;
    }

    public static void registerDriver(Class<? extends WebcamDriver> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Webcam driver class to register cannot be null!");
        }
        DRIVERS_CLASS_LIST.add(clazz);
        Webcam.registerDriver(clazz.getCanonicalName());
    }

    public static void registerDriver(String clazzName) {
        if (clazzName == null) {
            throw new IllegalArgumentException("Webcam driver class name to register cannot be null!");
        }
        DRIVERS_LIST.add(clazzName);
    }

    public static void setHandleTermSignal(boolean on2) {
        if (on2) {
            LOG.warn("Automated deallocation on TERM signal is now enabled! Make sure to not use it in production!");
        }
        deallocOnTermSignal = on2;
    }

    public static boolean isHandleTermSignal() {
        return deallocOnTermSignal;
    }

    public static void setAutoOpenMode(boolean on2) {
        autoOpen = on2;
    }

    public static boolean isAutoOpenMode() {
        return autoOpen;
    }

    public static boolean addDiscoveryListener(WebcamDiscoveryListener l2) {
        if (l2 == null) {
            throw new IllegalArgumentException("Webcam discovery listener cannot be null!");
        }
        return DISCOVERY_LISTENERS.add(l2);
    }

    public static WebcamDiscoveryListener[] getDiscoveryListeners() {
        return DISCOVERY_LISTENERS.toArray(new WebcamDiscoveryListener[DISCOVERY_LISTENERS.size()]);
    }

    public static boolean removeDiscoveryListener(WebcamDiscoveryListener l2) {
        return DISCOVERY_LISTENERS.remove(l2);
    }

    public static synchronized WebcamDiscoveryService getDiscoveryService() {
        if (discovery == null) {
            discovery = new WebcamDiscoveryService(Webcam.getDriver());
        }
        return discovery;
    }

    public static synchronized WebcamDiscoveryService getDiscoveryServiceRef() {
        return discovery;
    }

    public WebcamImageTransformer getImageTransformer() {
        return this.transformer;
    }

    public void setImageTransformer(WebcamImageTransformer transformer) {
        this.transformer = transformer;
    }

    public WebcamLock getLock() {
        return this.lock;
    }

    public static void shutdown() {
        WebcamDiscoveryService discovery = Webcam.getDiscoveryServiceRef();
        if (discovery != null) {
            discovery.stop();
        }
        WebcamProcessor.getInstance().shutdown();
    }

    private final class NotificationThreadFactory
    implements ThreadFactory {
        private NotificationThreadFactory() {
        }

        @Override
        public Thread newThread(Runnable r2) {
            Thread t2 = new Thread(r2, String.format("notificator-[%s]", Webcam.this.getName()));
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            t2.setDaemon(true);
            return t2;
        }
    }

    private static final class ImageNotification
    implements Runnable {
        private final Webcam webcam;
        private final BufferedImage image;

        public ImageNotification(Webcam webcam, BufferedImage image) {
            this.webcam = webcam;
            this.image = image;
        }

        @Override
        public void run() {
            if (this.image != null) {
                WebcamEvent we2 = new WebcamEvent(WebcamEventType.NEW_IMAGE, this.webcam, this.image);
                for (WebcamListener l2 : this.webcam.getWebcamListeners()) {
                    try {
                        l2.webcamImageObtained(we2);
                    }
                    catch (Exception e2) {
                        LOG.error(String.format("Notify image acquired, exception when calling listener %s", l2.getClass()), e2);
                    }
                }
            }
        }
    }
}

