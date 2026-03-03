/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1Codec
implements HttpCodec {
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_CLOSED = 6;
    private static final int HEADER_LIMIT = 262144;
    final OkHttpClient client;
    final StreamAllocation streamAllocation;
    final BufferedSource source;
    final BufferedSink sink;
    int state = 0;
    private long headerLimit = 262144L;
    private Headers trailers;

    public Http1Codec(OkHttpClient client, StreamAllocation streamAllocation, BufferedSource source, BufferedSink sink) {
        this.client = client;
        this.streamAllocation = streamAllocation;
        this.source = source;
        this.sink = sink;
    }

    @Override
    public Sink createRequestBody(Request request, long contentLength) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return this.newChunkedSink();
        }
        if (contentLength != -1L) {
            return this.newFixedLengthSink(contentLength);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    @Override
    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    @Override
    public void writeRequestHeaders(Request request) throws IOException {
        String requestLine = RequestLine.get(request, this.streamAllocation.connection().route().proxy().type());
        this.writeRequest(request.headers(), requestLine);
    }

    @Override
    public ResponseBody openResponseBody(Response response) throws IOException {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        String contentType = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            Source source = this.newFixedLengthSource(0L);
            return new RealResponseBody(contentType, 0L, Okio.buffer(source));
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            Source source = this.newChunkedSource(response.request().url());
            return new RealResponseBody(contentType, -1L, Okio.buffer(source));
        }
        long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1L) {
            Source source = this.newFixedLengthSource(contentLength);
            return new RealResponseBody(contentType, contentLength, Okio.buffer(source));
        }
        return new RealResponseBody(contentType, -1L, Okio.buffer(this.newUnknownLengthSource()));
    }

    @Override
    public Headers trailers() throws IOException {
        if (this.state != 6) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return this.trailers != null ? this.trailers : Util.EMPTY_HEADERS;
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    @Override
    public void flushRequest() throws IOException {
        this.sink.flush();
    }

    @Override
    public void finishRequest() throws IOException {
        this.sink.flush();
    }

    public void writeRequest(Headers headers, String requestLine) throws IOException {
        if (this.state != 0) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.sink.writeUtf8(requestLine).writeUtf8("\r\n");
        int size = headers.size();
        for (int i2 = 0; i2 < size; ++i2) {
            this.sink.writeUtf8(headers.name(i2)).writeUtf8(": ").writeUtf8(headers.value(i2)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }

    @Override
    public Response.Builder readResponseHeaders(boolean expectContinue) throws IOException {
        if (this.state != 1 && this.state != 3) {
            throw new IllegalStateException("state: " + this.state);
        }
        try {
            StatusLine statusLine = StatusLine.parse(this.readHeaderLine());
            Response.Builder responseBuilder = new Response.Builder().protocol(statusLine.protocol).code(statusLine.code).message(statusLine.message).headers(this.readHeaders());
            if (expectContinue && statusLine.code == 100) {
                return null;
            }
            if (statusLine.code == 100) {
                this.state = 3;
                return responseBuilder;
            }
            this.state = 4;
            return responseBuilder;
        }
        catch (EOFException e2) {
            throw new IOException("unexpected end of stream on " + this.streamAllocation, e2);
        }
    }

    private String readHeaderLine() throws IOException {
        String line = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= (long)line.length();
        return line;
    }

    public Headers readHeaders() throws IOException {
        String line;
        Headers.Builder headers = new Headers.Builder();
        while ((line = this.readHeaderLine()).length() != 0) {
            Internal.instance.addLenient(headers, line);
        }
        return headers.build();
    }

    public Sink newChunkedSink() {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new ChunkedSink();
    }

    public Sink newFixedLengthSink(long contentLength) {
        if (this.state != 1) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 2;
        return new FixedLengthSink(contentLength);
    }

    public Source newFixedLengthSource(long length) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new FixedLengthSource(length);
    }

    public Source newChunkedSource(HttpUrl url) throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        this.state = 5;
        return new ChunkedSource(url);
    }

    public Source newUnknownLengthSource() throws IOException {
        if (this.state != 4) {
            throw new IllegalStateException("state: " + this.state);
        }
        if (this.streamAllocation == null) {
            throw new IllegalStateException("streamAllocation == null");
        }
        this.state = 5;
        this.streamAllocation.noNewStreams();
        return new UnknownLengthSource();
    }

    void detachTimeout(ForwardingTimeout timeout) {
        Timeout oldDelegate = timeout.delegate();
        timeout.setDelegate(Timeout.NONE);
        oldDelegate.clearDeadline();
        oldDelegate.clearTimeout();
    }

    private class UnknownLengthSource
    extends AbstractSource {
        private boolean inputExhausted;

        UnknownLengthSource() {
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            if (byteCount < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + byteCount);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.inputExhausted) {
                return -1L;
            }
            long read = super.read(sink, byteCount);
            if (read == -1L) {
                this.inputExhausted = true;
                this.endOfInput(true, null);
                return -1L;
            }
            return read;
        }

        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (!this.inputExhausted) {
                this.endOfInput(false, null);
            }
            this.closed = true;
        }
    }

    private class ChunkedSource
    extends AbstractSource {
        private static final long NO_CHUNK_YET = -1L;
        private final HttpUrl url;
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;

        ChunkedSource(HttpUrl url) {
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
            this.url = url;
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            long read;
            if (byteCount < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + byteCount);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (!this.hasMoreChunks) {
                return -1L;
            }
            if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
                this.readChunkSize();
                if (!this.hasMoreChunks) {
                    return -1L;
                }
            }
            if ((read = super.read(sink, Math.min(byteCount, this.bytesRemainingInChunk))) == -1L) {
                ProtocolException e2 = new ProtocolException("unexpected end of stream");
                this.endOfInput(false, e2);
                throw e2;
            }
            this.bytesRemainingInChunk -= read;
            return read;
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1L) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                String extensions = Http1Codec.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0L || !extensions.isEmpty() && !extensions.startsWith(";")) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + extensions + "\"");
                }
            }
            catch (NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
            if (this.bytesRemainingInChunk == 0L) {
                this.hasMoreChunks = false;
                Http1Codec.this.trailers = Http1Codec.this.readHeaders();
                HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.trailers);
                this.endOfInput(true, null);
            }
        }

        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.endOfInput(false, null);
            }
            this.closed = true;
        }
    }

    private class FixedLengthSource
    extends AbstractSource {
        private long bytesRemaining;

        FixedLengthSource(long length) throws IOException {
            this.bytesRemaining = length;
            if (this.bytesRemaining == 0L) {
                this.endOfInput(true, null);
            }
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            if (byteCount < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + byteCount);
            }
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (this.bytesRemaining == 0L) {
                return -1L;
            }
            long read = super.read(sink, Math.min(this.bytesRemaining, byteCount));
            if (read == -1L) {
                ProtocolException e2 = new ProtocolException("unexpected end of stream");
                this.endOfInput(false, e2);
                throw e2;
            }
            this.bytesRemaining -= read;
            if (this.bytesRemaining == 0L) {
                this.endOfInput(true, null);
            }
            return read;
        }

        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this.endOfInput(false, null);
            }
            this.closed = true;
        }
    }

    private abstract class AbstractSource
    implements Source {
        protected final ForwardingTimeout timeout;
        protected boolean closed;
        protected long bytesRead;

        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
            this.bytesRead = 0L;
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            try {
                long read = Http1Codec.this.source.read(sink, byteCount);
                if (read > 0L) {
                    this.bytesRead += read;
                }
                return read;
            }
            catch (IOException e2) {
                this.endOfInput(false, e2);
                throw e2;
            }
        }

        protected final void endOfInput(boolean reuseConnection, IOException e2) throws IOException {
            if (Http1Codec.this.state == 6) {
                return;
            }
            if (Http1Codec.this.state != 5) {
                throw new IllegalStateException("state: " + Http1Codec.this.state);
            }
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 6;
            if (Http1Codec.this.streamAllocation != null) {
                Http1Codec.this.streamAllocation.streamFinished(!reuseConnection, Http1Codec.this, this.bytesRead, e2);
            }
        }
    }

    private final class ChunkedSink
    implements Sink {
        private final ForwardingTimeout timeout;
        private boolean closed;

        ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            if (byteCount == 0L) {
                return;
            }
            Http1Codec.this.sink.writeHexadecimalUnsignedLong(byteCount);
            Http1Codec.this.sink.writeUtf8("\r\n");
            Http1Codec.this.sink.write(source, byteCount);
            Http1Codec.this.sink.writeUtf8("\r\n");
        }

        @Override
        public synchronized void flush() throws IOException {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override
        public synchronized void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }
    }

    private final class FixedLengthSink
    implements Sink {
        private final ForwardingTimeout timeout;
        private boolean closed;
        private long bytesRemaining;

        FixedLengthSink(long bytesRemaining) {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
            this.bytesRemaining = bytesRemaining;
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(source.size(), 0L, byteCount);
            if (byteCount > this.bytesRemaining) {
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + byteCount);
            }
            Http1Codec.this.sink.write(source, byteCount);
            this.bytesRemaining -= byteCount;
        }

        @Override
        public void flush() throws IOException {
            if (this.closed) {
                return;
            }
            Http1Codec.this.sink.flush();
        }

        @Override
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.bytesRemaining > 0L) {
                throw new ProtocolException("unexpected end of stream");
            }
            Http1Codec.this.detachTimeout(this.timeout);
            Http1Codec.this.state = 3;
        }
    }
}

