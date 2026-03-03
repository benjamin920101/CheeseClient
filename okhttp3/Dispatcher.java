/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.RealCall;
import okhttp3.internal.Util;

public final class Dispatcher {
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    @Nullable
    private Runnable idleCallback;
    @Nullable
    private ExecutorService executorService;
    private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
    private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<RealCall.AsyncCall>();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque<RealCall>();

    public Dispatcher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Dispatcher() {
    }

    public synchronized ExecutorService executorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
        }
        return this.executorService;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setMaxRequests(int maxRequests) {
        if (maxRequests < 1) {
            throw new IllegalArgumentException("max < 1: " + maxRequests);
        }
        Dispatcher dispatcher = this;
        synchronized (dispatcher) {
            this.maxRequests = maxRequests;
        }
        this.promoteAndExecute();
    }

    public synchronized int getMaxRequests() {
        return this.maxRequests;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setMaxRequestsPerHost(int maxRequestsPerHost) {
        if (maxRequestsPerHost < 1) {
            throw new IllegalArgumentException("max < 1: " + maxRequestsPerHost);
        }
        Dispatcher dispatcher = this;
        synchronized (dispatcher) {
            this.maxRequestsPerHost = maxRequestsPerHost;
        }
        this.promoteAndExecute();
    }

    public synchronized int getMaxRequestsPerHost() {
        return this.maxRequestsPerHost;
    }

    public synchronized void setIdleCallback(@Nullable Runnable idleCallback) {
        this.idleCallback = idleCallback;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void enqueue(RealCall.AsyncCall call) {
        Dispatcher dispatcher = this;
        synchronized (dispatcher) {
            RealCall.AsyncCall existingCall;
            this.readyAsyncCalls.add(call);
            if (!call.get().forWebSocket && (existingCall = this.findExistingCallWithHost(call.host())) != null) {
                call.reuseCallsPerHostFrom(existingCall);
            }
        }
        this.promoteAndExecute();
    }

    @Nullable
    private RealCall.AsyncCall findExistingCallWithHost(String host) {
        for (RealCall.AsyncCall existingCall : this.runningAsyncCalls) {
            if (!existingCall.host().equals(host)) continue;
            return existingCall;
        }
        for (RealCall.AsyncCall existingCall : this.readyAsyncCalls) {
            if (!existingCall.host().equals(host)) continue;
            return existingCall;
        }
        return null;
    }

    public synchronized void cancelAll() {
        for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
            asyncCall.get().cancel();
        }
        for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
            asyncCall.get().cancel();
        }
        for (RealCall realCall : this.runningSyncCalls) {
            realCall.cancel();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean promoteAndExecute() {
        boolean isRunning;
        RealCall.AsyncCall asyncCall;
        assert (!Thread.holdsLock(this));
        ArrayList<RealCall.AsyncCall> executableCalls = new ArrayList<RealCall.AsyncCall>();
        Dispatcher dispatcher = this;
        synchronized (dispatcher) {
            Iterator<RealCall.AsyncCall> i2 = this.readyAsyncCalls.iterator();
            while (i2.hasNext()) {
                asyncCall = i2.next();
                if (this.runningAsyncCalls.size() >= this.maxRequests) break;
                if (asyncCall.callsPerHost().get() >= this.maxRequestsPerHost) continue;
                i2.remove();
                asyncCall.callsPerHost().incrementAndGet();
                executableCalls.add(asyncCall);
                this.runningAsyncCalls.add(asyncCall);
            }
            isRunning = this.runningCallsCount() > 0;
        }
        int size = executableCalls.size();
        for (int i3 = 0; i3 < size; ++i3) {
            asyncCall = (RealCall.AsyncCall)executableCalls.get(i3);
            asyncCall.executeOn(this.executorService());
        }
        return isRunning;
    }

    synchronized void executed(RealCall call) {
        this.runningSyncCalls.add(call);
    }

    void finished(RealCall.AsyncCall call) {
        call.callsPerHost().decrementAndGet();
        this.finished(this.runningAsyncCalls, call);
    }

    void finished(RealCall call) {
        this.finished(this.runningSyncCalls, call);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private <T> void finished(Deque<T> calls, T call) {
        Runnable idleCallback;
        Dispatcher dispatcher = this;
        synchronized (dispatcher) {
            if (!calls.remove(call)) {
                throw new AssertionError((Object)"Call wasn't in-flight!");
            }
            idleCallback = this.idleCallback;
        }
        boolean isRunning = this.promoteAndExecute();
        if (!isRunning && idleCallback != null) {
            idleCallback.run();
        }
    }

    public synchronized List<Call> queuedCalls() {
        ArrayList<RealCall> result = new ArrayList<RealCall>();
        for (RealCall.AsyncCall asyncCall : this.readyAsyncCalls) {
            result.add(asyncCall.get());
        }
        return Collections.unmodifiableList(result);
    }

    public synchronized List<Call> runningCalls() {
        ArrayList<RealCall> result = new ArrayList<RealCall>();
        result.addAll(this.runningSyncCalls);
        for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
            result.add(asyncCall.get());
        }
        return Collections.unmodifiableList(result);
    }

    public synchronized int queuedCallsCount() {
        return this.readyAsyncCalls.size();
    }

    public synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }
}

