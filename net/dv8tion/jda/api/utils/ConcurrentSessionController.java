/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.api.utils;

import com.neovisionaries.ws.client.OpeningHandshakeException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.utils.SessionController;
import net.dv8tion.jda.api.utils.SessionControllerAdapter;
import net.dv8tion.jda.internal.utils.Helpers;

public class ConcurrentSessionController
extends SessionControllerAdapter
implements SessionController {
    private Worker[] workers = new Worker[1];

    @Override
    public void setConcurrency(int level) {
        assert (level > 0 && level < Integer.MAX_VALUE);
        this.workers = new Worker[level];
    }

    @Override
    public void appendSession(@Nonnull SessionController.SessionConnectNode node) {
        this.getWorker(node).enqueue(node);
    }

    @Override
    public void removeSession(@Nonnull SessionController.SessionConnectNode node) {
        this.getWorker(node).dequeue(node);
    }

    private synchronized Worker getWorker(SessionController.SessionConnectNode node) {
        int i2 = node.getShardInfo().getShardId() % this.workers.length;
        Worker worker = this.workers[i2];
        if (worker == null) {
            log.debug("Creating new worker handle for shard pool {}", (Object)i2);
            this.workers[i2] = worker = new Worker(i2);
        }
        return worker;
    }

    private static class Worker
    implements Runnable {
        private final Queue<SessionController.SessionConnectNode> queue = new ConcurrentLinkedQueue<SessionController.SessionConnectNode>();
        private final int id;
        private Thread thread;

        public Worker(int id2) {
            this.id = id2;
        }

        public synchronized void start() {
            if (this.thread == null) {
                this.thread = new Thread((Runnable)this, "ConcurrentSessionController-Worker-" + this.id);
                SessionControllerAdapter.log.debug("Running worker");
                this.thread.start();
            }
        }

        public synchronized void stop() {
            this.thread = null;
            if (!this.queue.isEmpty()) {
                this.start();
            }
        }

        public void enqueue(SessionController.SessionConnectNode node) {
            SessionControllerAdapter.log.trace("Appending node to queue {}", (Object)node.getShardInfo());
            this.queue.add(node);
            this.start();
        }

        public void dequeue(SessionController.SessionConnectNode node) {
            SessionControllerAdapter.log.trace("Removing node from queue {}", (Object)node.getShardInfo());
            this.queue.remove(node);
        }

        @Override
        public void run() {
            try {
                while (!this.queue.isEmpty()) {
                    this.processQueue();
                    TimeUnit.SECONDS.sleep(5L);
                }
            }
            catch (InterruptedException ex2) {
                SessionControllerAdapter.log.error("Worker failed to process queue", ex2);
            }
            finally {
                this.stop();
            }
        }

        private void processQueue() throws InterruptedException {
            block11: {
                SessionController.SessionConnectNode node = null;
                try {
                    node = this.queue.remove();
                    SessionControllerAdapter.log.debug("Running connect node for shard {}", (Object)node.getShardInfo());
                    node.run(false);
                }
                catch (NoSuchElementException noSuchElementException) {
                }
                catch (InterruptedException e2) {
                    this.queue.add(node);
                    throw e2;
                }
                catch (IllegalStateException | ErrorResponseException e3) {
                    if (Helpers.hasCause(e3, OpeningHandshakeException.class)) {
                        SessionControllerAdapter.log.error("Failed opening handshake, appending to queue. Message: {}", (Object)e3.getMessage());
                    } else if (!(e3 instanceof ErrorResponseException) || !(e3.getCause() instanceof IOException)) {
                        if (Helpers.hasCause(e3, UnknownHostException.class)) {
                            SessionControllerAdapter.log.error("DNS resolution failed: {}", (Object)e3.getMessage());
                        } else if (e3.getCause() != null && !JDA.Status.RECONNECT_QUEUED.name().equals(e3.getCause().getMessage())) {
                            SessionControllerAdapter.log.error("Failed to establish connection for a node, appending to queue", e3);
                        } else {
                            SessionControllerAdapter.log.error("Unexpected exception when running connect node", e3);
                        }
                    }
                    if (node == null) break block11;
                    this.queue.add(node);
                }
            }
        }
    }
}

