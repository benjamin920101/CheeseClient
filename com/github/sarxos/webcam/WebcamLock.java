/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamLockException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamLock {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamLock.class);
    public static final long INTERVAL = 2000L;
    private final Webcam webcam;
    private Thread updater = null;
    private AtomicBoolean locked = new AtomicBoolean(false);
    private AtomicBoolean disabled = new AtomicBoolean(false);
    private File lock = null;

    protected WebcamLock(Webcam webcam) {
        this.webcam = webcam;
        this.lock = new File(System.getProperty("java.io.tmpdir"), this.getLockName());
        this.lock.deleteOnExit();
    }

    private String getLockName() {
        return String.format(".webcam-lock-%d", Math.abs(this.webcam.getName().hashCode()));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void write(long value) {
        if (this.disabled.get()) {
            return;
        }
        String name = this.getLockName();
        File tmp = null;
        FilterOutputStream dos = null;
        try {
            tmp = File.createTempFile(String.format("%s-tmp", name), "");
            tmp.deleteOnExit();
            dos = new DataOutputStream(new FileOutputStream(tmp));
            ((DataOutputStream)dos).writeLong(value);
            ((DataOutputStream)dos).flush();
        }
        catch (IOException e2) {
            throw new RuntimeException(e2);
        }
        finally {
            if (dos != null) {
                try {
                    dos.close();
                }
                catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
        if (!this.locked.get()) {
            return;
        }
        if (tmp.renameTo(this.lock)) {
            return;
        }
        if (!this.lock.exists()) {
            try {
                if (!this.lock.createNewFile()) {
                    throw new RuntimeException("Not able to create file " + this.lock);
                }
                LOG.info("Lock file {} for {} has been created", (Object)this.lock, (Object)this.webcam);
            }
            catch (IOException e4) {
                throw new RuntimeException(e4);
            }
        }
        FileOutputStream fos = null;
        FileInputStream fis = null;
        int k2 = 0;
        int n2 = -1;
        byte[] buffer = new byte[8];
        boolean rewritten = false;
        Webcam webcam = this.webcam;
        synchronized (webcam) {
            do {
                try {
                    fos = new FileOutputStream(this.lock);
                    fis = new FileInputStream(tmp);
                    while ((n2 = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, n2);
                    }
                    rewritten = true;
                }
                catch (IOException e5) {
                    LOG.debug("Not able to rewrite lock file", e5);
                }
                finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        }
                        catch (IOException e6) {
                            throw new RuntimeException(e6);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        }
                        catch (IOException e7) {
                            throw new RuntimeException(e7);
                        }
                    }
                }
            } while (!rewritten && k2++ < 5);
        }
        if (!rewritten) {
            throw new WebcamException("Not able to write lock file");
        }
        if (!tmp.delete()) {
            tmp.deleteOnExit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private long read() {
        if (this.disabled.get()) {
            return -1L;
        }
        FilterInputStream dis = null;
        long value = -1L;
        boolean broken = false;
        Webcam webcam = this.webcam;
        synchronized (webcam) {
            try {
                dis = new DataInputStream(new FileInputStream(this.lock));
                value = ((DataInputStream)dis).readLong();
            }
            catch (EOFException e2) {
                LOG.debug("Webcam lock is broken - EOF when reading long variable from stream", e2);
                broken = true;
            }
            catch (IOException e3) {
                throw new RuntimeException(e3);
            }
            finally {
                if (dis != null) {
                    try {
                        dis.close();
                    }
                    catch (IOException e4) {
                        throw new RuntimeException(e4);
                    }
                }
            }
            if (broken) {
                LOG.warn("Lock file {} for {} is broken - recreating it", (Object)this.lock, (Object)this.webcam);
                this.write(-1L);
            }
        }
        return value;
    }

    private void update() {
        if (this.disabled.get()) {
            return;
        }
        this.write(System.currentTimeMillis());
    }

    public void lock() {
        if (this.disabled.get()) {
            return;
        }
        if (this.isLocked()) {
            throw new WebcamLockException(String.format("Webcam %s has already been locked", this.webcam.getName()));
        }
        if (!this.locked.compareAndSet(false, true)) {
            return;
        }
        LOG.debug("Lock {}", (Object)this.webcam);
        this.update();
        this.updater = new LockUpdater();
        this.updater.start();
    }

    public void disable() {
        if (this.disabled.compareAndSet(false, true)) {
            LOG.info("Locking mechanism has been disabled in {}", (Object)this.webcam);
            if (this.updater != null) {
                this.updater.interrupt();
            }
        }
    }

    public void unlock() {
        if (this.disabled.get()) {
            return;
        }
        if (!this.locked.compareAndSet(true, false)) {
            return;
        }
        LOG.debug("Unlock {}", (Object)this.webcam);
        this.updater.interrupt();
        this.write(-1L);
        if (!this.lock.delete()) {
            this.lock.deleteOnExit();
        }
    }

    public boolean isLocked() {
        if (this.disabled.get()) {
            return false;
        }
        if (this.locked.get()) {
            return true;
        }
        if (!this.lock.exists()) {
            return false;
        }
        long now = System.currentTimeMillis();
        long tsp = this.read();
        LOG.trace("Lock timestamp {} now {} for {}", tsp, now, this.webcam);
        return tsp > now - 4000L;
    }

    private class LockUpdater
    extends Thread {
        public LockUpdater() {
            this.setName(String.format("webcam-lock-[%s]", WebcamLock.this.webcam.getName()));
            this.setDaemon(true);
            this.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
        }

        @Override
        public void run() {
            do {
                if (WebcamLock.this.disabled.get()) {
                    return;
                }
                WebcamLock.this.update();
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException e2) {
                    LOG.debug("Lock updater has been interrupted");
                    return;
                }
            } while (WebcamLock.this.locked.get());
        }
    }
}

