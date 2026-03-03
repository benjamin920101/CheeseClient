/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Address;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;

public final class ConnectionPool {
    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final int maxIdleConnections;
    private final long keepAliveDurationNs;
    private final Runnable cleanupRunnable = () -> {
        long waitNanos;
        while ((waitNanos = this.cleanup(System.nanoTime())) != -1L) {
            if (waitNanos <= 0L) continue;
            long waitMillis = waitNanos / 1000000L;
            waitNanos -= waitMillis * 1000000L;
            ConnectionPool connectionPool = this;
            synchronized (connectionPool) {
                try {
                    this.wait(waitMillis, (int)waitNanos);
                }
                catch (InterruptedException interruptedException) {
                    // empty catch block
                }
            }
        }
        return;
    };
    private final Deque<RealConnection> connections = new ArrayDeque<RealConnection>();
    final RouteDatabase routeDatabase = new RouteDatabase();
    boolean cleanupRunning;

    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public ConnectionPool(int maxIdleConnections, long keepAliveDuration, TimeUnit timeUnit) {
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDurationNs = timeUnit.toNanos(keepAliveDuration);
        if (keepAliveDuration <= 0L) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + keepAliveDuration);
        }
    }

    public synchronized int idleConnectionCount() {
        int total = 0;
        for (RealConnection connection : this.connections) {
            if (!connection.allocations.isEmpty()) continue;
            ++total;
        }
        return total;
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    void acquire(Address address, StreamAllocation streamAllocation, @Nullable Route route) {
        assert (Thread.holdsLock(this));
        for (RealConnection connection : this.connections) {
            if (!connection.isEligible(address, route)) continue;
            streamAllocation.acquire(connection, true);
            return;
        }
    }

    @Nullable
    Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        assert (Thread.holdsLock(this));
        for (RealConnection connection : this.connections) {
            if (!connection.isEligible(address, null) || !connection.isMultiplexed() || connection == streamAllocation.connection()) continue;
            return streamAllocation.releaseAndAcquire(connection);
        }
        return null;
    }

    void put(RealConnection connection) {
        assert (Thread.holdsLock(this));
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(connection);
    }

    boolean connectionBecameIdle(RealConnection connection) {
        assert (Thread.holdsLock(this));
        if (connection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(connection);
            return true;
        }
        this.notifyAll();
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void evictAll() {
        ArrayList<RealConnection> evictedConnections = new ArrayList<RealConnection>();
        ConnectionPool connectionPool = this;
        synchronized (connectionPool) {
            Iterator<RealConnection> i2 = this.connections.iterator();
            while (i2.hasNext()) {
                RealConnection connection = i2.next();
                if (!connection.allocations.isEmpty()) continue;
                connection.noNewStreams = true;
                evictedConnections.add(connection);
                i2.remove();
            }
        }
        for (RealConnection connection : evictedConnections) {
            Util.closeQuietly(connection.socket());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    long cleanup(long now) {
        int inUseConnectionCount = 0;
        int idleConnectionCount = 0;
        RealConnection longestIdleConnection = null;
        long longestIdleDurationNs = Long.MIN_VALUE;
        ConnectionPool connectionPool = this;
        synchronized (connectionPool) {
            for (RealConnection connection : this.connections) {
                if (this.pruneAndGetAllocationCount(connection, now) > 0) {
                    ++inUseConnectionCount;
                    continue;
                }
                ++idleConnectionCount;
                long idleDurationNs = now - connection.idleAtNanos;
                if (idleDurationNs <= longestIdleDurationNs) continue;
                longestIdleDurationNs = idleDurationNs;
                longestIdleConnection = connection;
            }
            if (longestIdleDurationNs < this.keepAliveDurationNs && idleConnectionCount <= this.maxIdleConnections) {
                if (idleConnectionCount > 0) {
                    return this.keepAliveDurationNs - longestIdleDurationNs;
                }
                if (inUseConnectionCount > 0) {
                    return this.keepAliveDurationNs;
                }
                this.cleanupRunning = false;
                return -1L;
            }
            this.connections.remove(longestIdleConnection);
        }
        Util.closeQuietly(longestIdleConnection.socket());
        return 0L;
    }

    private int pruneAndGetAllocationCount(RealConnection connection, long now) {
        List<Reference<StreamAllocation>> references = connection.allocations;
        int i2 = 0;
        while (i2 < references.size()) {
            Reference<StreamAllocation> reference = references.get(i2);
            if (reference.get() != null) {
                ++i2;
                continue;
            }
            StreamAllocation.StreamAllocationReference streamAllocRef = (StreamAllocation.StreamAllocationReference)reference;
            String message = "A connection to " + connection.route().address().url() + " was leaked. Did you forget to close a response body?";
            Platform.get().logCloseableLeak(message, streamAllocRef.callStackTrace);
            references.remove(i2);
            connection.noNewStreams = true;
            if (!references.isEmpty()) continue;
            connection.idleAtNanos = now - this.keepAliveDurationNs;
            return 0;
        }
        return references.size();
    }
}

