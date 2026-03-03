/*
 * Decompiled with CFR 0.152.
 */
package okhttp3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;

final class RealCall
implements Call {
    final OkHttpClient client;
    final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;
    final AsyncTimeout timeout;
    @Nullable
    private EventListener eventListener;
    final Request originalRequest;
    final boolean forWebSocket;
    private boolean executed;

    private RealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = forWebSocket;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(client);
        this.timeout = new AsyncTimeout(){

            @Override
            protected void timedOut() {
                RealCall.this.cancel();
            }
        };
        this.timeout.timeout(client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    static RealCall newRealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        RealCall call = new RealCall(client, originalRequest, forWebSocket);
        call.eventListener = client.eventListenerFactory().create(call);
        return call;
    }

    @Override
    public Request request() {
        return this.originalRequest;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Response execute() throws IOException {
        RealCall realCall = this;
        synchronized (realCall) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.captureCallStackTrace();
        this.timeout.enter();
        this.eventListener.callStart(this);
        try {
            this.client.dispatcher().executed(this);
            Response result = this.getResponseWithInterceptorChain();
            if (result == null) {
                throw new IOException("Canceled");
            }
            Response response = result;
            return response;
        }
        catch (IOException e2) {
            e2 = this.timeoutExit(e2);
            this.eventListener.callFailed(this, e2);
            throw e2;
        }
        finally {
            this.client.dispatcher().finished(this);
        }
    }

    @Nullable
    IOException timeoutExit(@Nullable IOException cause) {
        if (!this.timeout.exit()) {
            return cause;
        }
        InterruptedIOException e2 = new InterruptedIOException("timeout");
        if (cause != null) {
            e2.initCause(cause);
        }
        return e2;
    }

    private void captureCallStackTrace() {
        Object callStackTrace = Platform.get().getStackTraceForCloseable("response.body().close()");
        this.retryAndFollowUpInterceptor.setCallStackTrace(callStackTrace);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void enqueue(Callback responseCallback) {
        RealCall realCall = this;
        synchronized (realCall) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.captureCallStackTrace();
        this.eventListener.callStart(this);
        this.client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override
    public void cancel() {
        this.retryAndFollowUpInterceptor.cancel();
    }

    @Override
    public Timeout timeout() {
        return this.timeout;
    }

    @Override
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override
    public boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    @Override
    public RealCall clone() {
        return RealCall.newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    String toLoggableString() {
        return (this.isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + this.redactedUrl();
    }

    String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    Response getResponseWithInterceptorChain() throws IOException {
        ArrayList<Interceptor> interceptors = new ArrayList<Interceptor>();
        interceptors.addAll(this.client.interceptors());
        interceptors.add(this.retryAndFollowUpInterceptor);
        interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
        interceptors.add(new CacheInterceptor(this.client.internalCache()));
        interceptors.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            interceptors.addAll(this.client.networkInterceptors());
        }
        interceptors.add(new CallServerInterceptor(this.forWebSocket));
        RealInterceptorChain chain = new RealInterceptorChain(interceptors, null, null, null, 0, this.originalRequest, this, this.eventListener, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        return chain.proceed(this.originalRequest);
    }

    final class AsyncCall
    extends NamedRunnable {
        private final Callback responseCallback;
        private volatile AtomicInteger callsPerHost;

        AsyncCall(Callback responseCallback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.callsPerHost = new AtomicInteger(0);
            this.responseCallback = responseCallback;
        }

        AtomicInteger callsPerHost() {
            return this.callsPerHost;
        }

        void reuseCallsPerHostFrom(AsyncCall other) {
            this.callsPerHost = other.callsPerHost;
        }

        String host() {
            return RealCall.this.originalRequest.url().host();
        }

        Request request() {
            return RealCall.this.originalRequest;
        }

        RealCall get() {
            return RealCall.this;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void executeOn(ExecutorService executorService) {
            assert (!Thread.holdsLock(RealCall.this.client.dispatcher()));
            boolean success = false;
            try {
                executorService.execute(this);
                success = true;
            }
            catch (RejectedExecutionException e2) {
                InterruptedIOException ioException = new InterruptedIOException("executor rejected");
                ioException.initCause(e2);
                RealCall.this.eventListener.callFailed(RealCall.this, ioException);
                this.responseCallback.onFailure(RealCall.this, ioException);
            }
            finally {
                if (!success) {
                    RealCall.this.client.dispatcher().finished(this);
                }
            }
        }

        @Override
        protected void execute() {
            boolean signalledCallback = false;
            RealCall.this.timeout.enter();
            try {
                Response response = RealCall.this.getResponseWithInterceptorChain();
                if (RealCall.this.retryAndFollowUpInterceptor.isCanceled()) {
                    signalledCallback = true;
                    this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                } else {
                    signalledCallback = true;
                    this.responseCallback.onResponse(RealCall.this, response);
                }
            }
            catch (IOException e2) {
                e2 = RealCall.this.timeoutExit(e2);
                if (signalledCallback) {
                    Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e2);
                } else {
                    RealCall.this.eventListener.callFailed(RealCall.this, e2);
                    this.responseCallback.onFailure(RealCall.this, e2);
                }
            }
            finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }
}

