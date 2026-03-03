/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheRequest;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.cache.InternalCache;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class CacheInterceptor
implements Interceptor {
    @Nullable
    final InternalCache cache;

    public CacheInterceptor(@Nullable InternalCache cache) {
        this.cache = cache;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response cacheCandidate = this.cache != null ? this.cache.get(chain.request()) : null;
        long now = System.currentTimeMillis();
        CacheStrategy strategy = new CacheStrategy.Factory(now, chain.request(), cacheCandidate).get();
        Request networkRequest = strategy.networkRequest;
        Response cacheResponse = strategy.cacheResponse;
        if (this.cache != null) {
            this.cache.trackResponse(strategy);
        }
        if (cacheCandidate != null && cacheResponse == null) {
            Util.closeQuietly(cacheCandidate.body());
        }
        if (networkRequest == null && cacheResponse == null) {
            return new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
        }
        if (networkRequest == null) {
            return cacheResponse.newBuilder().cacheResponse(CacheInterceptor.stripBody(cacheResponse)).build();
        }
        Response networkResponse = null;
        try {
            networkResponse = chain.proceed(networkRequest);
        }
        finally {
            if (networkResponse == null && cacheCandidate != null) {
                Util.closeQuietly(cacheCandidate.body());
            }
        }
        if (cacheResponse != null) {
            if (networkResponse.code() == 304) {
                Response response = cacheResponse.newBuilder().headers(CacheInterceptor.combine(cacheResponse.headers(), networkResponse.headers())).sentRequestAtMillis(networkResponse.sentRequestAtMillis()).receivedResponseAtMillis(networkResponse.receivedResponseAtMillis()).cacheResponse(CacheInterceptor.stripBody(cacheResponse)).networkResponse(CacheInterceptor.stripBody(networkResponse)).build();
                networkResponse.body().close();
                this.cache.trackConditionalCacheHit();
                this.cache.update(cacheResponse, response);
                return response;
            }
            Util.closeQuietly(cacheResponse.body());
        }
        Response response = networkResponse.newBuilder().cacheResponse(CacheInterceptor.stripBody(cacheResponse)).networkResponse(CacheInterceptor.stripBody(networkResponse)).build();
        if (this.cache != null) {
            if (HttpHeaders.hasBody(response) && CacheStrategy.isCacheable(response, networkRequest)) {
                CacheRequest cacheRequest = this.cache.put(response);
                return this.cacheWritingResponse(cacheRequest, response);
            }
            if (HttpMethod.invalidatesCache(networkRequest.method())) {
                try {
                    this.cache.remove(networkRequest);
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
        return response;
    }

    private static Response stripBody(Response response) {
        return response != null && response.body() != null ? response.newBuilder().body(null).build() : response;
    }

    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink cacheBodyUnbuffered = cacheRequest.body();
        if (cacheBodyUnbuffered == null) {
            return response;
        }
        final BufferedSource source = response.body().source();
        final BufferedSink cacheBody = Okio.buffer(cacheBodyUnbuffered);
        Source cacheWritingSource = new Source(){
            boolean cacheRequestClosed;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead;
                try {
                    bytesRead = source.read(sink, byteCount);
                }
                catch (IOException e2) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e2;
                }
                if (bytesRead == -1L) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheBody.close();
                    }
                    return -1L;
                }
                sink.copyTo(cacheBody.buffer(), sink.size() - bytesRead, bytesRead);
                cacheBody.emitCompleteSegments();
                return bytesRead;
            }

            @Override
            public Timeout timeout() {
                return source.timeout();
            }

            @Override
            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                source.close();
            }
        };
        String contentType = response.header("Content-Type");
        long contentLength = response.body().contentLength();
        return response.newBuilder().body(new RealResponseBody(contentType, contentLength, Okio.buffer(cacheWritingSource))).build();
    }

    private static Headers combine(Headers cachedHeaders, Headers networkHeaders) {
        String fieldName;
        int i2;
        Headers.Builder result = new Headers.Builder();
        int size = cachedHeaders.size();
        for (i2 = 0; i2 < size; ++i2) {
            fieldName = cachedHeaders.name(i2);
            String value = cachedHeaders.value(i2);
            if ("Warning".equalsIgnoreCase(fieldName) && value.startsWith("1") || !CacheInterceptor.isContentSpecificHeader(fieldName) && CacheInterceptor.isEndToEnd(fieldName) && networkHeaders.get(fieldName) != null) continue;
            Internal.instance.addLenient(result, fieldName, value);
        }
        size = networkHeaders.size();
        for (i2 = 0; i2 < size; ++i2) {
            fieldName = networkHeaders.name(i2);
            if (CacheInterceptor.isContentSpecificHeader(fieldName) || !CacheInterceptor.isEndToEnd(fieldName)) continue;
            Internal.instance.addLenient(result, fieldName, networkHeaders.value(i2));
        }
        return result.build();
    }

    static boolean isEndToEnd(String fieldName) {
        return !"Connection".equalsIgnoreCase(fieldName) && !"Keep-Alive".equalsIgnoreCase(fieldName) && !"Proxy-Authenticate".equalsIgnoreCase(fieldName) && !"Proxy-Authorization".equalsIgnoreCase(fieldName) && !"TE".equalsIgnoreCase(fieldName) && !"Trailers".equalsIgnoreCase(fieldName) && !"Transfer-Encoding".equalsIgnoreCase(fieldName) && !"Upgrade".equalsIgnoreCase(fieldName);
    }

    static boolean isContentSpecificHeader(String fieldName) {
        return "Content-Length".equalsIgnoreCase(fieldName) || "Content-Encoding".equalsIgnoreCase(fieldName) || "Content-Type".equalsIgnoreCase(fieldName);
    }
}

