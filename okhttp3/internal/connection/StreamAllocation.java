/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.List;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

public final class StreamAllocation {
    public final Address address;
    private RouteSelector.Selection routeSelection;
    private Route route;
    private final ConnectionPool connectionPool;
    public final Call call;
    public final EventListener eventListener;
    private final Object callStackTrace;
    private final RouteSelector routeSelector;
    private int refusedStreamCount;
    private RealConnection connection;
    private boolean reportedAcquired;
    private boolean released;
    private boolean canceled;
    private HttpCodec codec;

    public StreamAllocation(ConnectionPool connectionPool, Address address, Call call, EventListener eventListener, Object callStackTrace) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
        this.routeSelector = new RouteSelector(address, this.routeDatabase(), call, eventListener);
        this.callStackTrace = callStackTrace;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HttpCodec newStream(OkHttpClient client, Interceptor.Chain chain, boolean doExtensiveHealthChecks) {
        int connectTimeout = chain.connectTimeoutMillis();
        int readTimeout = chain.readTimeoutMillis();
        int writeTimeout = chain.writeTimeoutMillis();
        int pingIntervalMillis = client.pingIntervalMillis();
        boolean connectionRetryEnabled = client.retryOnConnectionFailure();
        try {
            RealConnection resultConnection = this.findHealthyConnection(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled, doExtensiveHealthChecks);
            HttpCodec resultCodec = resultConnection.newCodec(client, chain, this);
            ConnectionPool connectionPool = this.connectionPool;
            synchronized (connectionPool) {
                this.codec = resultCodec;
                return resultCodec;
            }
        }
        catch (IOException e2) {
            throw new RouteException(e2);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private RealConnection findHealthyConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled, boolean doExtensiveHealthChecks) throws IOException {
        RealConnection candidate;
        while (true) {
            candidate = this.findConnection(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled);
            ConnectionPool connectionPool = this.connectionPool;
            synchronized (connectionPool) {
                if (candidate.successCount == 0) {
                    return candidate;
                }
            }
            if (candidate.isHealthy(doExtensiveHealthChecks)) break;
            this.noNewStreams();
        }
        return candidate;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private RealConnection findConnection(int connectTimeout, int readTimeout, int writeTimeout, int pingIntervalMillis, boolean connectionRetryEnabled) throws IOException {
        Socket toClose;
        RealConnection releasedConnection;
        boolean foundPooledConnection = false;
        RealConnection result = null;
        Route selectedRoute = null;
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
            if (this.codec != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            releasedConnection = this.connection;
            toClose = this.releaseIfNoNewStreams();
            if (this.connection != null) {
                result = this.connection;
                releasedConnection = null;
            }
            if (!this.reportedAcquired) {
                releasedConnection = null;
            }
            if (result == null) {
                Internal.instance.acquire(this.connectionPool, this.address, this, null);
                if (this.connection != null) {
                    foundPooledConnection = true;
                    result = this.connection;
                } else {
                    selectedRoute = this.route;
                }
            }
        }
        Util.closeQuietly(toClose);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
        if (foundPooledConnection) {
            this.eventListener.connectionAcquired(this.call, result);
        }
        if (result != null) {
            return result;
        }
        boolean newRouteSelection = false;
        if (!(selectedRoute != null || this.routeSelection != null && this.routeSelection.hasNext())) {
            newRouteSelection = true;
            this.routeSelection = this.routeSelector.next();
        }
        ConnectionPool connectionPool2 = this.connectionPool;
        synchronized (connectionPool2) {
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            if (newRouteSelection) {
                List<Route> routes = this.routeSelection.getAll();
                int size = routes.size();
                for (int i2 = 0; i2 < size; ++i2) {
                    Route route = routes.get(i2);
                    Internal.instance.acquire(this.connectionPool, this.address, this, route);
                    if (this.connection == null) continue;
                    foundPooledConnection = true;
                    result = this.connection;
                    this.route = route;
                    break;
                }
            }
            if (!foundPooledConnection) {
                if (selectedRoute == null) {
                    selectedRoute = this.routeSelection.next();
                }
                this.route = selectedRoute;
                this.refusedStreamCount = 0;
                result = new RealConnection(this.connectionPool, selectedRoute);
                this.acquire(result, false);
            }
        }
        if (foundPooledConnection) {
            this.eventListener.connectionAcquired(this.call, result);
            return result;
        }
        result.connect(connectTimeout, readTimeout, writeTimeout, pingIntervalMillis, connectionRetryEnabled, this.call, this.eventListener);
        this.routeDatabase().connected(result.route());
        Socket socket = null;
        ConnectionPool connectionPool3 = this.connectionPool;
        synchronized (connectionPool3) {
            this.reportedAcquired = true;
            Internal.instance.put(this.connectionPool, result);
            if (result.isMultiplexed()) {
                socket = Internal.instance.deduplicate(this.connectionPool, this.address, this);
                result = this.connection;
            }
        }
        Util.closeQuietly(socket);
        this.eventListener.connectionAcquired(this.call, result);
        return result;
    }

    private Socket releaseIfNoNewStreams() {
        assert (Thread.holdsLock(this.connectionPool));
        RealConnection allocatedConnection = this.connection;
        if (allocatedConnection != null && allocatedConnection.noNewStreams) {
            return this.deallocate(false, false, true);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void streamFinished(boolean noNewStreams, HttpCodec codec, long bytesRead, IOException e2) {
        boolean callEnd;
        Socket socket;
        RealConnection releasedConnection;
        this.eventListener.responseBodyEnd(this.call, bytesRead);
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            if (codec == null || codec != this.codec) {
                throw new IllegalStateException("expected " + this.codec + " but was " + codec);
            }
            if (!noNewStreams) {
                ++this.connection.successCount;
            }
            releasedConnection = this.connection;
            socket = this.deallocate(noNewStreams, false, true);
            if (this.connection != null) {
                releasedConnection = null;
            }
            callEnd = this.released;
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
        if (e2 != null) {
            e2 = Internal.instance.timeoutExit(this.call, e2);
            this.eventListener.callFailed(this.call, e2);
        } else if (callEnd) {
            Internal.instance.timeoutExit(this.call, null);
            this.eventListener.callEnd(this.call);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public HttpCodec codec() {
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            return this.codec;
        }
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public Route route() {
        return this.route;
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void release(boolean callEnd) {
        Socket socket;
        RealConnection releasedConnection;
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            releasedConnection = this.connection;
            socket = this.deallocate(false, true, false);
            if (this.connection != null) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            if (callEnd) {
                Internal.instance.timeoutExit(this.call, null);
            }
            this.eventListener.connectionReleased(this.call, releasedConnection);
            if (callEnd) {
                this.eventListener.callEnd(this.call);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void noNewStreams() {
        Socket socket;
        RealConnection releasedConnection;
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            releasedConnection = this.connection;
            socket = this.deallocate(true, false, false);
            if (this.connection != null) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
    }

    private Socket deallocate(boolean noNewStreams, boolean released, boolean streamFinished) {
        assert (Thread.holdsLock(this.connectionPool));
        if (streamFinished) {
            this.codec = null;
        }
        if (released) {
            this.released = true;
        }
        Socket socket = null;
        if (this.connection != null) {
            if (noNewStreams) {
                this.connection.noNewStreams = true;
            }
            if (this.codec == null && (this.released || this.connection.noNewStreams)) {
                this.release(this.connection);
                if (this.connection.allocations.isEmpty()) {
                    this.connection.idleAtNanos = System.nanoTime();
                    if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                        socket = this.connection.socket();
                    }
                }
                this.connection = null;
            }
        }
        return socket;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void cancel() {
        RealConnection connectionToCancel;
        HttpCodec codecToCancel;
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            this.canceled = true;
            codecToCancel = this.codec;
            connectionToCancel = this.connection;
        }
        if (codecToCancel != null) {
            codecToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void streamFailed(IOException e2) {
        Socket socket;
        RealConnection releasedConnection;
        boolean noNewStreams = false;
        ConnectionPool connectionPool = this.connectionPool;
        synchronized (connectionPool) {
            if (e2 instanceof StreamResetException) {
                ErrorCode errorCode = ((StreamResetException)e2).errorCode;
                if (errorCode == ErrorCode.REFUSED_STREAM) {
                    ++this.refusedStreamCount;
                    if (this.refusedStreamCount > 1) {
                        noNewStreams = true;
                        this.route = null;
                    }
                } else if (errorCode != ErrorCode.CANCEL) {
                    noNewStreams = true;
                    this.route = null;
                }
            } else if (this.connection != null && (!this.connection.isMultiplexed() || e2 instanceof ConnectionShutdownException)) {
                noNewStreams = true;
                if (this.connection.successCount == 0) {
                    if (this.route != null && e2 != null) {
                        this.routeSelector.connectFailed(this.route, e2);
                    }
                    this.route = null;
                }
            }
            releasedConnection = this.connection;
            socket = this.deallocate(noNewStreams, false, true);
            if (this.connection != null || !this.reportedAcquired) {
                releasedConnection = null;
            }
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
    }

    public void acquire(RealConnection connection, boolean reportedAcquired) {
        assert (Thread.holdsLock(this.connectionPool));
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        this.connection = connection;
        this.reportedAcquired = reportedAcquired;
        connection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
    }

    private void release(RealConnection connection) {
        int size = connection.allocations.size();
        for (int i2 = 0; i2 < size; ++i2) {
            Reference<StreamAllocation> reference = connection.allocations.get(i2);
            if (reference.get() != this) continue;
            connection.allocations.remove(i2);
            return;
        }
        throw new IllegalStateException();
    }

    public Socket releaseAndAcquire(RealConnection newConnection) {
        assert (Thread.holdsLock(this.connectionPool));
        if (this.codec != null || this.connection.allocations.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<StreamAllocation> onlyAllocation = this.connection.allocations.get(0);
        Socket socket = this.deallocate(true, false, false);
        this.connection = newConnection;
        newConnection.allocations.add(onlyAllocation);
        return socket;
    }

    public boolean hasMoreRoutes() {
        return this.route != null || this.routeSelection != null && this.routeSelection.hasNext() || this.routeSelector.hasNext();
    }

    public String toString() {
        RealConnection connection = this.connection();
        return connection != null ? connection.toString() : this.address.toString();
    }

    public static final class StreamAllocationReference
    extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation referent, Object callStackTrace) {
            super(referent);
            this.callStackTrace = callStackTrace;
        }
    }
}

