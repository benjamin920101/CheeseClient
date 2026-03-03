/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.duplex.DuplexRequestBody;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealInterceptorChain;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public final class CallServerInterceptor
implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean forWebSocket) {
        this.forWebSocket = forWebSocket;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realChain = (RealInterceptorChain)chain;
        Call call = realChain.call();
        HttpCodec httpCodec = realChain.httpStream();
        StreamAllocation streamAllocation = realChain.streamAllocation();
        RealConnection connection = (RealConnection)realChain.connection();
        Request request = realChain.request();
        long sentRequestMillis = System.currentTimeMillis();
        realChain.eventListener().requestHeadersStart(call);
        httpCodec.writeRequestHeaders(request);
        realChain.eventListener().requestHeadersEnd(call, request);
        Response.Builder responseBuilder = null;
        if (HttpMethod.permitsRequestBody(request.method()) && request.body() != null) {
            if ("100-continue".equalsIgnoreCase(request.header("Expect"))) {
                httpCodec.flushRequest();
                realChain.eventListener().responseHeadersStart(call);
                responseBuilder = httpCodec.readResponseHeaders(true);
            }
            if (responseBuilder == null) {
                if (request.body() instanceof DuplexRequestBody) {
                    httpCodec.flushRequest();
                    CountingSink requestBodyOut = new CountingSink(httpCodec.createRequestBody(request, -1L));
                    BufferedSink bufferedRequestBody = Okio.buffer(requestBodyOut);
                    request.body().writeTo(bufferedRequestBody);
                } else {
                    realChain.eventListener().requestBodyStart(call);
                    long contentLength = request.body().contentLength();
                    CountingSink requestBodyOut = new CountingSink(httpCodec.createRequestBody(request, contentLength));
                    BufferedSink bufferedRequestBody = Okio.buffer(requestBodyOut);
                    request.body().writeTo(bufferedRequestBody);
                    bufferedRequestBody.close();
                    realChain.eventListener().requestBodyEnd(call, requestBodyOut.successfulCount);
                }
            } else if (!connection.isMultiplexed()) {
                streamAllocation.noNewStreams();
            }
        }
        if (!(request.body() instanceof DuplexRequestBody)) {
            httpCodec.finishRequest();
        }
        if (responseBuilder == null) {
            realChain.eventListener().responseHeadersStart(call);
            responseBuilder = httpCodec.readResponseHeaders(false);
        }
        responseBuilder.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis());
        Internal.instance.initCodec(responseBuilder, httpCodec);
        Response response = responseBuilder.build();
        int code = response.code();
        if (code == 100) {
            responseBuilder = httpCodec.readResponseHeaders(false);
            responseBuilder.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(sentRequestMillis).receivedResponseAtMillis(System.currentTimeMillis());
            Internal.instance.initCodec(responseBuilder, httpCodec);
            response = responseBuilder.build();
            code = response.code();
        }
        realChain.eventListener().responseHeadersEnd(call, response);
        response = this.forWebSocket && code == 101 ? response.newBuilder().body(Util.EMPTY_RESPONSE).build() : response.newBuilder().body(httpCodec.openResponseBody(response)).build();
        if ("close".equalsIgnoreCase(response.request().header("Connection")) || "close".equalsIgnoreCase(response.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        if ((code == 204 || code == 205) && response.body().contentLength() > 0L) {
            throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + response.body().contentLength());
        }
        return response;
    }

    static final class CountingSink
    extends ForwardingSink {
        long successfulCount;

        CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            this.successfulCount += byteCount;
        }
    }
}

