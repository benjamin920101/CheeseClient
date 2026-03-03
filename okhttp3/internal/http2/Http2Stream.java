/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.StreamResetException;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http2Stream {
    long unacknowledgedBytesRead = 0L;
    long bytesLeftInWriteWindow;
    final int id;
    final Http2Connection connection;
    private final Deque<Headers> headersQueue = new ArrayDeque<Headers>();
    private boolean hasResponseHeaders;
    private final FramingSource source;
    final FramingSink sink;
    final StreamTimeout readTimeout = new StreamTimeout();
    final StreamTimeout writeTimeout = new StreamTimeout();
    ErrorCode errorCode = null;

    Http2Stream(int id2, Http2Connection connection, boolean outFinished, boolean inFinished, @Nullable Headers headers) {
        if (connection == null) {
            throw new NullPointerException("connection == null");
        }
        this.id = id2;
        this.connection = connection;
        this.bytesLeftInWriteWindow = connection.peerSettings.getInitialWindowSize();
        this.source = new FramingSource(connection.okHttpSettings.getInitialWindowSize());
        this.sink = new FramingSink();
        this.source.finished = inFinished;
        this.sink.finished = outFinished;
        if (headers != null) {
            this.headersQueue.add(headers);
        }
        if (this.isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        }
        if (!this.isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        return !this.source.finished && !this.source.closed || !this.sink.finished && !this.sink.closed || !this.hasResponseHeaders;
    }

    public boolean isLocallyInitiated() {
        boolean streamIsClient = (this.id & 1) == 1;
        return this.connection.client == streamIsClient;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized Headers takeHeaders() throws IOException {
        this.readTimeout.enter();
        try {
            while (this.headersQueue.isEmpty() && this.errorCode == null) {
                this.waitForIo();
            }
        }
        finally {
            this.readTimeout.exitAndThrowIfTimedOut();
        }
        if (!this.headersQueue.isEmpty()) {
            return this.headersQueue.removeFirst();
        }
        throw new StreamResetException(this.errorCode);
    }

    public synchronized Headers trailers() throws IOException {
        if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
        if (!(this.source.finished && this.source.receiveBuffer.exhausted() && this.source.readBuffer.exhausted())) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        return this.source.trailers != null ? this.source.trailers : Util.EMPTY_HEADERS;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void writeHeaders(List<Header> responseHeaders, boolean outFinished, boolean flushHeaders) throws IOException {
        assert (!Thread.holdsLock(this));
        if (responseHeaders == null) {
            throw new NullPointerException("headers == null");
        }
        Object object = this;
        synchronized (object) {
            this.hasResponseHeaders = true;
            if (outFinished) {
                this.sink.finished = true;
            }
        }
        if (!flushHeaders) {
            object = this.connection;
            synchronized (object) {
                flushHeaders = this.connection.bytesLeftInWriteWindow == 0L;
            }
        }
        this.connection.writeHeaders(this.id, outFinished, responseHeaders);
        if (flushHeaders) {
            this.connection.flush();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void enqueueTrailers(Headers trailers) {
        Http2Stream http2Stream = this;
        synchronized (http2Stream) {
            if (this.sink.finished) {
                throw new IllegalStateException("already finished");
            }
            if (trailers.size() == 0) {
                throw new IllegalArgumentException("trailers.size() == 0");
            }
            this.sink.trailers = trailers;
        }
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }

    public Source getSource() {
        return this.source;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Sink getSink() {
        Http2Stream http2Stream = this;
        synchronized (http2Stream) {
            if (!this.hasResponseHeaders && !this.isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public void close(ErrorCode rstStatusCode) throws IOException {
        if (!this.closeInternal(rstStatusCode)) {
            return;
        }
        this.connection.writeSynReset(this.id, rstStatusCode);
    }

    public void closeLater(ErrorCode errorCode) {
        if (!this.closeInternal(errorCode)) {
            return;
        }
        this.connection.writeSynResetLater(this.id, errorCode);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private boolean closeInternal(ErrorCode errorCode) {
        assert (!Thread.holdsLock(this));
        Http2Stream http2Stream = this;
        synchronized (http2Stream) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            this.notifyAll();
        }
        this.connection.removeStream(this.id);
        return true;
    }

    void receiveData(BufferedSource in2, int length) throws IOException {
        assert (!Thread.holdsLock(this));
        this.source.receive(in2, length);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void receiveHeaders(Headers headers, boolean inFinished) {
        boolean open;
        assert (!Thread.holdsLock(this));
        Http2Stream http2Stream = this;
        synchronized (http2Stream) {
            if (!this.hasResponseHeaders || !inFinished) {
                this.hasResponseHeaders = true;
                this.headersQueue.add(headers);
            } else {
                this.source.trailers = headers;
            }
            if (inFinished) {
                this.source.finished = true;
            }
            open = this.isOpen();
            this.notifyAll();
        }
        if (!open) {
            this.connection.removeStream(this.id);
        }
    }

    synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            this.notifyAll();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void cancelStreamIfNecessary() throws IOException {
        boolean open;
        boolean cancel;
        assert (!Thread.holdsLock(this));
        Http2Stream http2Stream = this;
        synchronized (http2Stream) {
            cancel = !this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed);
            open = this.isOpen();
        }
        if (cancel) {
            this.close(ErrorCode.CANCEL);
        } else if (!open) {
            this.connection.removeStream(this.id);
        }
    }

    void addBytesToWriteWindow(long delta) {
        this.bytesLeftInWriteWindow += delta;
        if (delta > 0L) {
            this.notifyAll();
        }
    }

    void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        }
        if (this.sink.finished) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    void waitForIo() throws InterruptedIOException {
        try {
            this.wait();
        }
        catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    class StreamTimeout
    extends AsyncTimeout {
        StreamTimeout() {
        }

        @Override
        protected void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        @Override
        protected IOException newTimeoutException(IOException cause) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (cause != null) {
                socketTimeoutException.initCause(cause);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (this.exit()) {
                throw this.newTimeoutException(null);
            }
        }
    }

    final class FramingSink
    implements Sink {
        private static final long EMIT_BUFFER_SIZE = 16384L;
        private final Buffer sendBuffer = new Buffer();
        private Headers trailers;
        boolean closed;
        boolean finished;

        FramingSink() {
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            assert (!Thread.holdsLock(Http2Stream.this));
            this.sendBuffer.write(source, byteCount);
            while (this.sendBuffer.size() >= 16384L) {
                this.emitFrame(false);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private void emitFrame(boolean outFinishedOnLastFrame) throws IOException {
            long toWrite;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                Http2Stream.this.writeTimeout.enter();
                try {
                    while (Http2Stream.this.bytesLeftInWriteWindow <= 0L && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                        Http2Stream.this.waitForIo();
                    }
                }
                finally {
                    Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                }
                Http2Stream.this.checkOutNotClosed();
                toWrite = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
                Http2Stream.this.bytesLeftInWriteWindow -= toWrite;
            }
            Http2Stream.this.writeTimeout.enter();
            try {
                boolean outFinished = outFinishedOnLastFrame && toWrite == this.sendBuffer.size();
                Http2Stream.this.connection.writeData(Http2Stream.this.id, outFinished, this.sendBuffer, toWrite);
            }
            finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void flush() throws IOException {
            assert (!Thread.holdsLock(Http2Stream.this));
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size() > 0L) {
                this.emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override
        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            assert (!Thread.holdsLock(Http2Stream.this));
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                if (this.closed) {
                    return;
                }
            }
            if (!Http2Stream.this.sink.finished) {
                boolean hasTrailers;
                boolean hasData = this.sendBuffer.size() > 0L;
                boolean bl2 = hasTrailers = this.trailers != null;
                if (hasTrailers) {
                    while (this.sendBuffer.size() > 0L) {
                        this.emitFrame(false);
                    }
                    Http2Stream.this.connection.writeHeaders(Http2Stream.this.id, true, Util.toHeaderBlock(this.trailers));
                } else if (hasData) {
                    while (this.sendBuffer.size() > 0L) {
                        this.emitFrame(true);
                    }
                } else {
                    Http2Stream.this.connection.writeData(Http2Stream.this.id, true, null, 0L);
                }
            }
            Http2Stream http2Stream2 = Http2Stream.this;
            synchronized (http2Stream2) {
                this.closed = true;
            }
            Http2Stream.this.connection.flush();
            Http2Stream.this.cancelStreamIfNecessary();
        }
    }

    private final class FramingSource
    implements Source {
        private final Buffer receiveBuffer = new Buffer();
        private final Buffer readBuffer = new Buffer();
        private final long maxByteCount;
        private Headers trailers;
        boolean closed;
        boolean finished;

        FramingSource(long maxByteCount) {
            this.maxByteCount = maxByteCount;
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
        public long read(Buffer sink, long byteCount) throws IOException {
            ErrorCode errorCodeToDeliver;
            long readBytesDelivered;
            if (byteCount < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + byteCount);
            }
            while (true) {
                readBytesDelivered = -1L;
                errorCodeToDeliver = null;
                Http2Stream http2Stream = Http2Stream.this;
                // MONITORENTER : http2Stream
                Http2Stream.this.readTimeout.enter();
                try {
                    if (Http2Stream.this.errorCode != null) {
                        errorCodeToDeliver = Http2Stream.this.errorCode;
                    }
                    if (this.closed) {
                        throw new IOException("stream closed");
                    }
                    if (this.readBuffer.size() > 0L) {
                        readBytesDelivered = this.readBuffer.read(sink, Math.min(byteCount, this.readBuffer.size()));
                        Http2Stream.this.unacknowledgedBytesRead += readBytesDelivered;
                        if (errorCodeToDeliver != null || Http2Stream.this.unacknowledgedBytesRead < (long)(Http2Stream.this.connection.okHttpSettings.getInitialWindowSize() / 2)) break;
                        Http2Stream.this.connection.writeWindowUpdateLater(Http2Stream.this.id, Http2Stream.this.unacknowledgedBytesRead);
                        Http2Stream.this.unacknowledgedBytesRead = 0L;
                        break;
                    }
                    if (this.finished || errorCodeToDeliver != null) break;
                    Http2Stream.this.waitForIo();
                    continue;
                }
                finally {
                    Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                    continue;
                }
                break;
            }
            // MONITOREXIT : http2Stream
            if (readBytesDelivered != -1L) {
                this.updateConnectionFlowControl(readBytesDelivered);
                return readBytesDelivered;
            }
            if (errorCodeToDeliver == null) return -1L;
            throw new StreamResetException(errorCodeToDeliver);
        }

        private void updateConnectionFlowControl(long read) {
            assert (!Thread.holdsLock(Http2Stream.this));
            Http2Stream.this.connection.updateConnectionFlowControl(read);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        void receive(BufferedSource in2, long byteCount) throws IOException {
            assert (!Thread.holdsLock(Http2Stream.this));
            while (byteCount > 0L) {
                boolean flowControlError;
                boolean finished;
                Http2Stream http2Stream = Http2Stream.this;
                synchronized (http2Stream) {
                    finished = this.finished;
                    flowControlError = byteCount + this.readBuffer.size() > this.maxByteCount;
                }
                if (flowControlError) {
                    in2.skip(byteCount);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if (finished) {
                    in2.skip(byteCount);
                    return;
                }
                long read = in2.read(this.receiveBuffer, byteCount);
                if (read == -1L) {
                    throw new EOFException();
                }
                byteCount -= read;
                Http2Stream http2Stream2 = Http2Stream.this;
                synchronized (http2Stream2) {
                    boolean wasEmpty = this.readBuffer.size() == 0L;
                    this.readBuffer.writeAll(this.receiveBuffer);
                    if (wasEmpty) {
                        Http2Stream.this.notifyAll();
                    }
                }
            }
        }

        @Override
        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            long bytesDiscarded;
            Http2Stream http2Stream = Http2Stream.this;
            synchronized (http2Stream) {
                this.closed = true;
                bytesDiscarded = this.readBuffer.size();
                this.readBuffer.clear();
                Http2Stream.this.notifyAll();
            }
            if (bytesDiscarded > 0L) {
                this.updateConnectionFlowControl(bytesDiscarded);
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }
    }
}

