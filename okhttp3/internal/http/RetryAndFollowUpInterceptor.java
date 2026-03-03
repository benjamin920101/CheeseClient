/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.UnrepeatableRequestBody;
import okhttp3.internal.http2.ConnectionShutdownException;

public final class RetryAndFollowUpInterceptor
implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;
    private volatile StreamAllocation streamAllocation;
    private Object callStackTrace;
    private volatile boolean canceled;

    public RetryAndFollowUpInterceptor(OkHttpClient client) {
        this.client = client;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation = this.streamAllocation;
        if (streamAllocation != null) {
            streamAllocation.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object callStackTrace) {
        this.callStackTrace = callStackTrace;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        StreamAllocation streamAllocation;
        Request request = chain.request();
        RealInterceptorChain realChain = (RealInterceptorChain)chain;
        Call call = realChain.call();
        EventListener eventListener = realChain.eventListener();
        this.streamAllocation = streamAllocation = new StreamAllocation(this.client.connectionPool(), this.createAddress(request.url()), call, eventListener, this.callStackTrace);
        int followUpCount = 0;
        Response priorResponse = null;
        while (true) {
            Request followUp;
            Response response;
            if (this.canceled) {
                streamAllocation.release(true);
                throw new IOException("Canceled");
            }
            boolean releaseConnection = true;
            try {
                response = realChain.proceed(request, streamAllocation, null, null);
                releaseConnection = false;
            }
            catch (RouteException e2) {
                if (!this.recover(e2.getLastConnectException(), streamAllocation, false, request)) {
                    throw e2.getFirstConnectException();
                }
                releaseConnection = false;
                continue;
            }
            catch (IOException e3) {
                boolean requestSendStarted;
                boolean bl2 = requestSendStarted = !(e3 instanceof ConnectionShutdownException);
                if (!this.recover(e3, streamAllocation, requestSendStarted, request)) {
                    throw e3;
                }
                releaseConnection = false;
                continue;
            }
            finally {
                if (!releaseConnection) continue;
                streamAllocation.streamFailed(null);
                streamAllocation.release(true);
                continue;
            }
            if (priorResponse != null) {
                response = response.newBuilder().priorResponse(priorResponse.newBuilder().body(null).build()).build();
            }
            try {
                followUp = this.followUpRequest(response, streamAllocation.route());
            }
            catch (IOException e4) {
                streamAllocation.release(true);
                throw e4;
            }
            if (followUp == null) {
                streamAllocation.release(true);
                return response;
            }
            Util.closeQuietly(response.body());
            if (++followUpCount > 20) {
                streamAllocation.release(true);
                throw new ProtocolException("Too many follow-up requests: " + followUpCount);
            }
            if (followUp.body() instanceof UnrepeatableRequestBody) {
                streamAllocation.release(true);
                throw new HttpRetryException("Cannot retry streamed HTTP body", response.code());
            }
            if (!this.sameConnection(response, followUp.url())) {
                streamAllocation.release(false);
                this.streamAllocation = streamAllocation = new StreamAllocation(this.client.connectionPool(), this.createAddress(followUp.url()), call, eventListener, this.callStackTrace);
            } else if (streamAllocation.codec() != null) {
                throw new IllegalStateException("Closing the body of " + response + " didn't close its backing stream. Bad interceptor?");
            }
            request = followUp;
            priorResponse = response;
        }
    }

    private Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private boolean recover(IOException e2, StreamAllocation streamAllocation, boolean requestSendStarted, Request userRequest) {
        streamAllocation.streamFailed(e2);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if (requestSendStarted && this.requestIsUnrepeatable(e2, userRequest)) {
            return false;
        }
        if (!this.isRecoverable(e2, requestSendStarted)) {
            return false;
        }
        return streamAllocation.hasMoreRoutes();
    }

    private boolean requestIsUnrepeatable(IOException e2, Request userRequest) {
        return userRequest.body() instanceof UnrepeatableRequestBody || e2 instanceof FileNotFoundException;
    }

    private boolean isRecoverable(IOException e2, boolean requestSendStarted) {
        if (e2 instanceof ProtocolException) {
            return false;
        }
        if (e2 instanceof InterruptedIOException) {
            return e2 instanceof SocketTimeoutException && !requestSendStarted;
        }
        if (e2 instanceof SSLHandshakeException && e2.getCause() instanceof CertificateException) {
            return false;
        }
        return !(e2 instanceof SSLPeerUnverifiedException);
    }

    private Request followUpRequest(Response userResponse, Route route) throws IOException {
        if (userResponse == null) {
            throw new IllegalStateException();
        }
        int responseCode = userResponse.code();
        String method = userResponse.request().method();
        switch (responseCode) {
            case 407: {
                Proxy selectedProxy;
                Proxy proxy = selectedProxy = route != null ? route.proxy() : this.client.proxy();
                if (selectedProxy.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route, userResponse);
            }
            case 401: {
                return this.client.authenticator().authenticate(route, userResponse);
            }
            case 307: 
            case 308: {
                if (!method.equals("GET") && !method.equals("HEAD")) {
                    return null;
                }
            }
            case 300: 
            case 301: 
            case 302: 
            case 303: {
                if (!this.client.followRedirects()) {
                    return null;
                }
                String location = userResponse.header("Location");
                if (location == null) {
                    return null;
                }
                HttpUrl url = userResponse.request().url().resolve(location);
                if (url == null) {
                    return null;
                }
                boolean sameScheme = url.scheme().equals(userResponse.request().url().scheme());
                if (!sameScheme && !this.client.followSslRedirects()) {
                    return null;
                }
                Request.Builder requestBuilder = userResponse.request().newBuilder();
                if (HttpMethod.permitsRequestBody(method)) {
                    boolean maintainBody = HttpMethod.redirectsWithBody(method);
                    if (HttpMethod.redirectsToGet(method)) {
                        requestBuilder.method("GET", null);
                    } else {
                        RequestBody requestBody = maintainBody ? userResponse.request().body() : null;
                        requestBuilder.method(method, requestBody);
                    }
                    if (!maintainBody) {
                        requestBuilder.removeHeader("Transfer-Encoding");
                        requestBuilder.removeHeader("Content-Length");
                        requestBuilder.removeHeader("Content-Type");
                    }
                }
                if (!this.sameConnection(userResponse, url)) {
                    requestBuilder.removeHeader("Authorization");
                }
                return requestBuilder.url(url).build();
            }
            case 408: {
                if (!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                if (userResponse.request().body() instanceof UnrepeatableRequestBody) {
                    return null;
                }
                if (userResponse.priorResponse() != null && userResponse.priorResponse().code() == 408) {
                    return null;
                }
                if (this.retryAfter(userResponse, 0) > 0) {
                    return null;
                }
                return userResponse.request();
            }
            case 503: {
                if (userResponse.priorResponse() != null && userResponse.priorResponse().code() == 503) {
                    return null;
                }
                if (this.retryAfter(userResponse, Integer.MAX_VALUE) == 0) {
                    return userResponse.request();
                }
                return null;
            }
        }
        return null;
    }

    private int retryAfter(Response userResponse, int defaultDelay) {
        String header = userResponse.header("Retry-After");
        if (header == null) {
            return defaultDelay;
        }
        if (header.matches("\\d+")) {
            return Integer.valueOf(header);
        }
        return Integer.MAX_VALUE;
    }

    private boolean sameConnection(Response response, HttpUrl followUp) {
        HttpUrl url = response.request().url();
        return url.host().equals(followUp.host()) && url.port() == followUp.port() && url.scheme().equals(followUp.scheme());
    }
}

