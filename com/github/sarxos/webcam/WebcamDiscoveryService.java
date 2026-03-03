/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDeallocator;
import com.github.sarxos.webcam.WebcamDevice;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamDiscoverySupport;
import com.github.sarxos.webcam.WebcamDriver;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamDiscoveryService
implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamDiscoveryService.class);
    private final WebcamDriver driver;
    private final WebcamDiscoverySupport support;
    private volatile List<Webcam> webcams = null;
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean enabled = new AtomicBoolean(true);
    private Thread runner = null;

    protected WebcamDiscoveryService(WebcamDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null!");
        }
        this.driver = driver;
        this.support = (WebcamDiscoverySupport)((Object)(driver instanceof WebcamDiscoverySupport ? driver : null));
    }

    private static List<Webcam> toWebcams(List<WebcamDevice> devices) {
        ArrayList<Webcam> webcams = new ArrayList<Webcam>();
        for (WebcamDevice device : devices) {
            webcams.add(new Webcam(device));
        }
        return webcams;
    }

    private static List<WebcamDevice> getDevices(List<Webcam> webcams) {
        ArrayList<WebcamDevice> devices = new ArrayList<WebcamDevice>();
        for (Webcam webcam : webcams) {
            devices.add(webcam.getDevice());
        }
        return devices;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<Webcam> getWebcams(long timeout, TimeUnit tunit) throws TimeoutException {
        if (timeout < 0L) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (tunit == null) {
            throw new IllegalArgumentException("Time unit cannot be null!");
        }
        ArrayList<Webcam> tmp = null;
        Class<Webcam> clazz = Webcam.class;
        synchronized (Webcam.class) {
            if (this.webcams == null) {
                WebcamsDiscovery discovery = new WebcamsDiscovery(this.driver);
                ExecutorService executor = Executors.newSingleThreadExecutor(discovery);
                Future<List<Webcam>> future = executor.submit(discovery);
                executor.shutdown();
                try {
                    executor.awaitTermination(timeout, tunit);
                    if (future.isDone()) {
                        this.webcams = future.get();
                    } else {
                        future.cancel(true);
                    }
                }
                catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
                catch (ExecutionException e3) {
                    throw new WebcamException(e3);
                }
                if (this.webcams == null) {
                    throw new TimeoutException(String.format("Webcams discovery timeout (%d ms) has been exceeded", timeout));
                }
                tmp = new ArrayList<Webcam>(this.webcams);
                if (Webcam.isHandleTermSignal()) {
                    WebcamDeallocator.store(this.webcams.toArray(new Webcam[this.webcams.size()]));
                }
            }
            // ** MonitorExit[var5_4] (shouldn't be in output)
            if (tmp != null) {
                WebcamDiscoveryListener[] listeners = Webcam.getDiscoveryListeners();
                for (Webcam webcam : tmp) {
                    WebcamDiscoveryService.notifyWebcamFound(webcam, listeners);
                }
            }
            return Collections.unmodifiableList(this.webcams);
        }
    }

    public void scan() {
        WebcamDiscoveryListener[] listeners = Webcam.getDiscoveryListeners();
        List<WebcamDevice> tmpnew = this.driver.getDevices();
        List<WebcamDevice> tmpold = null;
        try {
            tmpold = WebcamDiscoveryService.getDevices(this.getWebcams(Long.MAX_VALUE, TimeUnit.MILLISECONDS));
        }
        catch (TimeoutException e2) {
            throw new WebcamException(e2);
        }
        LinkedList<WebcamDevice> oldones = new LinkedList<WebcamDevice>(tmpold);
        LinkedList<WebcamDevice> newones = new LinkedList<WebcamDevice>(tmpnew);
        Iterator oi2 = oldones.iterator();
        Iterator ni2 = null;
        WebcamDevice od2 = null;
        WebcamDevice nd = null;
        block2: while (oi2.hasNext()) {
            od2 = (WebcamDevice)oi2.next();
            ni2 = newones.iterator();
            while (ni2.hasNext()) {
                nd = (WebcamDevice)ni2.next();
                if (!nd.getName().equals(od2.getName())) continue;
                ni2.remove();
                oi2.remove();
                continue block2;
            }
        }
        if (oldones.size() > 0) {
            ArrayList<Webcam> notified = new ArrayList<Webcam>();
            block4: for (WebcamDevice device : oldones) {
                for (Webcam webcam : this.webcams) {
                    if (!webcam.getDevice().getName().equals(device.getName())) continue;
                    notified.add(webcam);
                    continue block4;
                }
            }
            this.setCurrentWebcams(tmpnew);
            for (Webcam webcam : notified) {
                WebcamDiscoveryService.notifyWebcamGone(webcam, listeners);
                webcam.dispose();
            }
        }
        if (newones.size() > 0) {
            this.setCurrentWebcams(tmpnew);
            block7: for (WebcamDevice device : newones) {
                for (Webcam webcam : this.webcams) {
                    if (!webcam.getDevice().getName().equals(device.getName())) continue;
                    WebcamDiscoveryService.notifyWebcamFound(webcam, listeners);
                    continue block7;
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void run() {
        if (this.support == null) {
            return;
        }
        if (!this.support.isScanPossible()) {
            return;
        }
        Object monitor = new Object();
        do {
            Object object = monitor;
            synchronized (object) {
                try {
                    monitor.wait(this.support.getScanInterval());
                }
                catch (InterruptedException e2) {
                    break;
                }
                catch (Exception e3) {
                    throw new RuntimeException("Problem waiting on monitor", e3);
                }
            }
            this.scan();
        } while (this.running.get());
        LOG.debug("Webcam discovery service loop has been stopped");
    }

    private void setCurrentWebcams(List<WebcamDevice> devices) {
        this.webcams = WebcamDiscoveryService.toWebcams(devices);
        if (Webcam.isHandleTermSignal()) {
            WebcamDeallocator.unstore();
            WebcamDeallocator.store(this.webcams.toArray(new Webcam[this.webcams.size()]));
        }
    }

    private static void notifyWebcamGone(Webcam webcam, WebcamDiscoveryListener[] listeners) {
        WebcamDiscoveryEvent event = new WebcamDiscoveryEvent(webcam, 2);
        for (WebcamDiscoveryListener l2 : listeners) {
            try {
                l2.webcamGone(event);
            }
            catch (Exception e2) {
                LOG.error(String.format("Webcam gone, exception when calling listener %s", l2.getClass()), e2);
            }
        }
    }

    private static void notifyWebcamFound(Webcam webcam, WebcamDiscoveryListener[] listeners) {
        WebcamDiscoveryEvent event = new WebcamDiscoveryEvent(webcam, 1);
        for (WebcamDiscoveryListener l2 : listeners) {
            try {
                l2.webcamFound(event);
            }
            catch (Exception e2) {
                LOG.error(String.format("Webcam found, exception when calling listener %s", l2.getClass()), e2);
            }
        }
    }

    public void stop() {
        if (!this.running.compareAndSet(true, false)) {
            return;
        }
        try {
            this.runner.join();
        }
        catch (InterruptedException e2) {
            throw new WebcamException("Joint interrupted");
        }
        LOG.debug("Discovery service has been stopped");
        this.runner = null;
    }

    public void start() {
        if (!this.enabled.get()) {
            LOG.info("Discovery service has been disabled and thus it will not be started");
            return;
        }
        if (this.support == null) {
            LOG.info("Discovery will not run - driver {} does not support this feature", (Object)this.driver.getClass().getSimpleName());
            return;
        }
        if (!this.running.compareAndSet(false, true)) {
            return;
        }
        this.runner = new Thread((Runnable)this, "webcam-discovery-service");
        this.runner.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
        this.runner.setDaemon(true);
        this.runner.start();
    }

    public boolean isRunning() {
        return this.running.get();
    }

    public void setEnabled(boolean enabled) {
        this.enabled.set(enabled);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected void shutdown() {
        this.stop();
        for (Webcam webcam : this.webcams) {
            webcam.dispose();
        }
        Class<Webcam> clazz = Webcam.class;
        synchronized (Webcam.class) {
            this.webcams.clear();
            if (Webcam.isHandleTermSignal()) {
                WebcamDeallocator.unstore();
            }
            // ** MonitorExit[var2_2] (shouldn't be in output)
            return;
        }
    }

    private static final class WebcamsDiscovery
    implements Callable<List<Webcam>>,
    ThreadFactory {
        private final WebcamDriver driver;

        public WebcamsDiscovery(WebcamDriver driver) {
            this.driver = driver;
        }

        @Override
        public List<Webcam> call() throws Exception {
            return WebcamDiscoveryService.toWebcams(this.driver.getDevices());
        }

        @Override
        public Thread newThread(Runnable r2) {
            Thread t2 = new Thread(r2, "webcam-discovery-service");
            t2.setDaemon(true);
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            return t2;
        }
    }
}

