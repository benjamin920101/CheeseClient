/*
 * Decompiled with CFR 0.152.
 */
package com.github.sarxos.webcam;

import com.github.sarxos.webcam.WebcamException;
import com.github.sarxos.webcam.WebcamExceptionHandler;
import com.github.sarxos.webcam.WebcamTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebcamProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(WebcamProcessor.class);
    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static ExecutorService runner = null;
    private static final AtomicProcessor processor = new AtomicProcessor();
    private static final WebcamProcessor INSTANCE = new WebcamProcessor();

    private WebcamProcessor() {
    }

    public void process(WebcamTask task) throws InterruptedException {
        if (started.compareAndSet(false, true)) {
            runner = Executors.newSingleThreadExecutor(new ProcessorThreadFactory());
            runner.execute(processor);
        }
        if (runner.isShutdown()) {
            throw new RejectedExecutionException("Cannot process because processor runner has been already shut down");
        }
        processor.process(task);
    }

    public void shutdown() {
        if (started.compareAndSet(true, false)) {
            LOG.debug("Shutting down webcam processor");
            runner.shutdown();
            LOG.debug("Awaiting tasks termination");
            while (runner.isTerminated()) {
                try {
                    runner.awaitTermination(100L, TimeUnit.MILLISECONDS);
                }
                catch (InterruptedException e2) {
                    return;
                }
                runner.shutdownNow();
            }
            LOG.debug("All tasks has been terminated");
        }
    }

    public static synchronized WebcamProcessor getInstance() {
        return INSTANCE;
    }

    private static final class AtomicProcessor
    implements Runnable {
        private SynchronousQueue<WebcamTask> inbound = new SynchronousQueue(true);
        private SynchronousQueue<WebcamTask> outbound = new SynchronousQueue(true);

        private AtomicProcessor() {
        }

        public void process(WebcamTask task) throws InterruptedException {
            this.inbound.put(task);
            Throwable t2 = this.outbound.take().getThrowable();
            if (t2 != null) {
                throw new WebcamException("Cannot execute task", t2);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            while (true) {
                WebcamTask t2 = null;
                try {
                    t2 = this.inbound.take();
                    t2.handle();
                    continue;
                }
                catch (InterruptedException e2) {
                    if (t2 == null) break;
                    try {
                        this.outbound.put(t2);
                    }
                    catch (InterruptedException e3) {
                    }
                    catch (Exception e4) {
                        throw new RuntimeException("Cannot put task into outbound queue", e4);
                    }
                }
                catch (Throwable e5) {
                    if (t2 == null) continue;
                    t2.setThrowable(e5);
                    continue;
                }
                finally {
                    if (t2 == null) continue;
                    try {
                        this.outbound.put(t2);
                    }
                    catch (InterruptedException e6) {
                        break;
                    }
                    catch (Exception e7) {
                        throw new RuntimeException("Cannot put task into outbound queue", e7);
                    }
                    continue;
                }
                break;
            }
        }
    }

    private static final class ProcessorThreadFactory
    implements ThreadFactory {
        private ProcessorThreadFactory() {
        }

        @Override
        public Thread newThread(Runnable r2) {
            ProcessorThread t2 = new ProcessorThread(r2);
            t2.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
            t2.setDaemon(true);
            return t2;
        }
    }

    public static final class ProcessorThread
    extends Thread {
        private static final AtomicInteger N = new AtomicInteger(0);

        public ProcessorThread(Runnable r2) {
            super(r2, String.format("atomic-processor-%d", N.incrementAndGet()));
        }
    }
}

