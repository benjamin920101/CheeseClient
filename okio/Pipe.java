/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.IOException;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.PushableTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Pipe {
    final long maxBufferSize;
    final Buffer buffer = new Buffer();
    boolean sinkClosed;
    boolean sourceClosed;
    private final Sink sink = new PipeSink();
    private final Source source = new PipeSource();
    @Nullable
    private Sink foldedSink;

    public Pipe(long maxBufferSize) {
        if (maxBufferSize < 1L) {
            throw new IllegalArgumentException("maxBufferSize < 1: " + maxBufferSize);
        }
        this.maxBufferSize = maxBufferSize;
    }

    public final Source source() {
        return this.source;
    }

    public final Sink sink() {
        return this.sink;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void fold(Sink sink) throws IOException {
        while (true) {
            Buffer sinkBuffer;
            Buffer buffer = this.buffer;
            synchronized (buffer) {
                if (this.foldedSink != null) {
                    throw new IllegalStateException("sink already folded");
                }
                if (this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink;
                    return;
                }
                sinkBuffer = new Buffer();
                sinkBuffer.write(this.buffer, this.buffer.size);
                this.buffer.notifyAll();
            }
            boolean success = false;
            try {
                sink.write(sinkBuffer, sinkBuffer.size);
                sink.flush();
                success = true;
                continue;
            }
            finally {
                if (success) continue;
                Buffer buffer2 = this.buffer;
                synchronized (buffer2) {
                    this.sourceClosed = true;
                    this.buffer.notifyAll();
                }
                continue;
            }
            break;
        }
    }

    final class PipeSource
    implements Source {
        final Timeout timeout = new Timeout();

        PipeSource() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            Buffer buffer = Pipe.this.buffer;
            synchronized (buffer) {
                if (Pipe.this.sourceClosed) {
                    throw new IllegalStateException("closed");
                }
                while (Pipe.this.buffer.size() == 0L) {
                    if (Pipe.this.sinkClosed) {
                        return -1L;
                    }
                    this.timeout.waitUntilNotified(Pipe.this.buffer);
                }
                long result = Pipe.this.buffer.read(sink, byteCount);
                Pipe.this.buffer.notifyAll();
                return result;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            Buffer buffer = Pipe.this.buffer;
            synchronized (buffer) {
                Pipe.this.sourceClosed = true;
                Pipe.this.buffer.notifyAll();
            }
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }
    }

    final class PipeSink
    implements Sink {
        final PushableTimeout timeout = new PushableTimeout();

        PipeSink() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            Sink delegate = null;
            Buffer buffer = Pipe.this.buffer;
            synchronized (buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                }
                while (byteCount > 0L) {
                    if (Pipe.this.foldedSink != null) {
                        delegate = Pipe.this.foldedSink;
                        break;
                    }
                    if (Pipe.this.sourceClosed) {
                        throw new IOException("source is closed");
                    }
                    long bufferSpaceAvailable = Pipe.this.maxBufferSize - Pipe.this.buffer.size();
                    if (bufferSpaceAvailable == 0L) {
                        this.timeout.waitUntilNotified(Pipe.this.buffer);
                        continue;
                    }
                    long bytesToWrite = Math.min(bufferSpaceAvailable, byteCount);
                    Pipe.this.buffer.write(source, bytesToWrite);
                    byteCount -= bytesToWrite;
                    Pipe.this.buffer.notifyAll();
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.write(source, byteCount);
                }
                finally {
                    this.timeout.pop();
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void flush() throws IOException {
            Sink delegate = null;
            Buffer buffer = Pipe.this.buffer;
            synchronized (buffer) {
                if (Pipe.this.sinkClosed) {
                    throw new IllegalStateException("closed");
                }
                if (Pipe.this.foldedSink != null) {
                    delegate = Pipe.this.foldedSink;
                } else if (Pipe.this.sourceClosed && Pipe.this.buffer.size() > 0L) {
                    throw new IOException("source is closed");
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.flush();
                }
                finally {
                    this.timeout.pop();
                }
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void close() throws IOException {
            Sink delegate = null;
            Buffer buffer = Pipe.this.buffer;
            synchronized (buffer) {
                if (Pipe.this.sinkClosed) {
                    return;
                }
                if (Pipe.this.foldedSink != null) {
                    delegate = Pipe.this.foldedSink;
                } else {
                    if (Pipe.this.sourceClosed && Pipe.this.buffer.size() > 0L) {
                        throw new IOException("source is closed");
                    }
                    Pipe.this.sinkClosed = true;
                    Pipe.this.buffer.notifyAll();
                }
            }
            if (delegate != null) {
                this.timeout.push(delegate.timeout());
                try {
                    delegate.close();
                }
                finally {
                    this.timeout.pop();
                }
            }
        }

        @Override
        public Timeout timeout() {
            return this.timeout;
        }
    }
}

