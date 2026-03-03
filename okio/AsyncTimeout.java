/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.Segment;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.Util;

public class AsyncTimeout
extends Timeout {
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60L);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    @Nullable
    static AsyncTimeout head;
    private boolean inQueue;
    @Nullable
    private AsyncTimeout next;
    private long timeoutAt;

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = this.timeoutNanos();
        boolean hasDeadline = this.hasDeadline();
        if (timeoutNanos == 0L && !hasDeadline) {
            return;
        }
        this.inQueue = true;
        AsyncTimeout.scheduleTimeout(this, timeoutNanos, hasDeadline);
    }

    private static synchronized void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
        if (head == null) {
            head = new AsyncTimeout();
            new Watchdog().start();
        }
        long now = System.nanoTime();
        if (timeoutNanos != 0L && hasDeadline) {
            node.timeoutAt = now + Math.min(timeoutNanos, node.deadlineNanoTime() - now);
        } else if (timeoutNanos != 0L) {
            node.timeoutAt = now + timeoutNanos;
        } else if (hasDeadline) {
            node.timeoutAt = node.deadlineNanoTime();
        } else {
            throw new AssertionError();
        }
        long remainingNanos = node.remainingNanos(now);
        AsyncTimeout prev = head;
        while (true) {
            if (prev.next == null || remainingNanos < prev.next.remainingNanos(now)) {
                node.next = prev.next;
                prev.next = node;
                if (prev != head) break;
                AsyncTimeout.class.notify();
                break;
            }
            prev = prev.next;
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return AsyncTimeout.cancelScheduledTimeout(this);
    }

    private static synchronized boolean cancelScheduledTimeout(AsyncTimeout node) {
        AsyncTimeout prev = head;
        while (prev != null) {
            if (prev.next == node) {
                prev.next = node.next;
                node.next = null;
                return false;
            }
            prev = prev.next;
        }
        return true;
    }

    private long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    public final Sink sink(final Sink sink) {
        return new Sink(){

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                Util.checkOffsetAndCount(source.size, 0L, byteCount);
                while (byteCount > 0L) {
                    long toWrite = 0L;
                    Segment s2 = source.head;
                    while (toWrite < 65536L) {
                        int segmentSize = s2.limit - s2.pos;
                        if ((toWrite += (long)segmentSize) >= byteCount) {
                            toWrite = byteCount;
                            break;
                        }
                        s2 = s2.next;
                    }
                    boolean throwOnTimeout = false;
                    AsyncTimeout.this.enter();
                    try {
                        sink.write(source, toWrite);
                        byteCount -= toWrite;
                        throwOnTimeout = true;
                    }
                    catch (IOException e2) {
                        throw AsyncTimeout.this.exit(e2);
                    }
                    finally {
                        AsyncTimeout.this.exit(throwOnTimeout);
                    }
                }
            }

            @Override
            public void flush() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    sink.flush();
                    throwOnTimeout = true;
                }
                catch (IOException e2) {
                    throw AsyncTimeout.this.exit(e2);
                }
                finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override
            public void close() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    sink.close();
                    throwOnTimeout = true;
                }
                catch (IOException e2) {
                    throw AsyncTimeout.this.exit(e2);
                }
                finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
        };
    }

    public final Source source(final Source source) {
        return new Source(){

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    long result = source.read(sink, byteCount);
                    throwOnTimeout = true;
                    long l2 = result;
                    return l2;
                }
                catch (IOException e2) {
                    throw AsyncTimeout.this.exit(e2);
                }
                finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override
            public void close() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    source.close();
                    throwOnTimeout = true;
                }
                catch (IOException e2) {
                    throw AsyncTimeout.this.exit(e2);
                }
                finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }

    final void exit(boolean throwOnTimeout) throws IOException {
        boolean timedOut = this.exit();
        if (timedOut && throwOnTimeout) {
            throw this.newTimeoutException(null);
        }
    }

    final IOException exit(IOException cause) throws IOException {
        if (!this.exit()) {
            return cause;
        }
        return this.newTimeoutException(cause);
    }

    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e2 = new InterruptedIOException("timeout");
        if (cause != null) {
            e2.initCause(cause);
        }
        return e2;
    }

    @Nullable
    static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout node = AsyncTimeout.head.next;
        if (node == null) {
            long startNanos = System.nanoTime();
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            return AsyncTimeout.head.next == null && System.nanoTime() - startNanos >= IDLE_TIMEOUT_NANOS ? head : null;
        }
        long waitNanos = node.remainingNanos(System.nanoTime());
        if (waitNanos > 0L) {
            long waitMillis = waitNanos / 1000000L;
            AsyncTimeout.class.wait(waitMillis, (int)(waitNanos -= waitMillis * 1000000L));
            return null;
        }
        AsyncTimeout.head.next = node.next;
        node.next = null;
        return node;
    }

    private static final class Watchdog
    extends Thread {
        Watchdog() {
            super("Okio Watchdog");
            this.setDaemon(true);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        Class<AsyncTimeout> clazz = AsyncTimeout.class;
                        // MONITORENTER : okio.AsyncTimeout.class
                        AsyncTimeout timedOut = AsyncTimeout.awaitTimeout();
                        if (timedOut == null) {
                            // MONITOREXIT : clazz
                            continue;
                        }
                        if (timedOut == head) {
                            head = null;
                            // MONITOREXIT : clazz
                            return;
                        }
                        // MONITOREXIT : clazz
                        timedOut.timedOut();
                    }
                }
                catch (InterruptedException interruptedException) {
                    continue;
                }
                break;
            }
        }
    }
}

