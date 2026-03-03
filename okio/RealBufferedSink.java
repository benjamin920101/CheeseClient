/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.Util;

final class RealBufferedSink
implements BufferedSink {
    public final Buffer buffer = new Buffer();
    public final Sink sink;
    boolean closed;

    RealBufferedSink(Sink sink) {
        if (sink == null) {
            throw new NullPointerException("sink == null");
        }
        this.sink = sink;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    @Override
    public void write(Buffer source, long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source, byteCount);
        this.emitCompleteSegments();
    }

    @Override
    public BufferedSink write(ByteString byteString) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(byteString);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeUtf8(String string) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(string);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeUtf8(String string, int beginIndex, int endIndex) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8(string, beginIndex, endIndex);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeUtf8CodePoint(int codePoint) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeUtf8CodePoint(codePoint);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeString(String string, Charset charset) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeString(string, charset);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeString(String string, int beginIndex, int endIndex, Charset charset) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeString(string, beginIndex, endIndex, charset);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink write(byte[] source) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink write(byte[] source, int offset, int byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(source, offset, byteCount);
        return this.emitCompleteSegments();
    }

    @Override
    public int write(ByteBuffer source) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        int result = this.buffer.write(source);
        this.emitCompleteSegments();
        return result;
    }

    @Override
    public long writeAll(Source source) throws IOException {
        long readCount;
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long totalBytesRead = 0L;
        while ((readCount = source.read(this.buffer, 8192L)) != -1L) {
            totalBytesRead += readCount;
            this.emitCompleteSegments();
        }
        return totalBytesRead;
    }

    @Override
    public BufferedSink write(Source source, long byteCount) throws IOException {
        while (byteCount > 0L) {
            long read = source.read(this.buffer, byteCount);
            if (read == -1L) {
                throw new EOFException();
            }
            byteCount -= read;
            this.emitCompleteSegments();
        }
        return this;
    }

    @Override
    public BufferedSink writeByte(int b2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeByte(b2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeShort(int s2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShort(s2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeShortLe(int s2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeShortLe(s2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeInt(int i2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeInt(i2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeIntLe(int i2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeIntLe(i2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeLong(long v2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLong(v2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeLongLe(long v2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeLongLe(v2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeDecimalLong(long v2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeDecimalLong(v2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink writeHexadecimalUnsignedLong(long v2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.writeHexadecimalUnsignedLong(v2);
        return this.emitCompleteSegments();
    }

    @Override
    public BufferedSink emitCompleteSegments() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.buffer.completeSegmentByteCount();
        if (byteCount > 0L) {
            this.sink.write(this.buffer, byteCount);
        }
        return this;
    }

    @Override
    public BufferedSink emit() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long byteCount = this.buffer.size();
        if (byteCount > 0L) {
            this.sink.write(this.buffer, byteCount);
        }
        return this;
    }

    @Override
    public OutputStream outputStream() {
        return new OutputStream(){

            @Override
            public void write(int b2) throws IOException {
                if (RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.buffer.writeByte((byte)b2);
                RealBufferedSink.this.emitCompleteSegments();
            }

            @Override
            public void write(byte[] data, int offset, int byteCount) throws IOException {
                if (RealBufferedSink.this.closed) {
                    throw new IOException("closed");
                }
                RealBufferedSink.this.buffer.write(data, offset, byteCount);
                RealBufferedSink.this.emitCompleteSegments();
            }

            @Override
            public void flush() throws IOException {
                if (!RealBufferedSink.this.closed) {
                    RealBufferedSink.this.flush();
                }
            }

            @Override
            public void close() throws IOException {
                RealBufferedSink.this.close();
            }

            public String toString() {
                return RealBufferedSink.this + ".outputStream()";
            }
        };
    }

    @Override
    public void flush() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size > 0L) {
            this.sink.write(this.buffer, this.buffer.size);
        }
        this.sink.flush();
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override
    public void close() throws IOException {
        Throwable thrown;
        block7: {
            if (this.closed) {
                return;
            }
            thrown = null;
            try {
                if (this.buffer.size > 0L) {
                    this.sink.write(this.buffer, this.buffer.size);
                }
            }
            catch (Throwable e2) {
                thrown = e2;
            }
            try {
                this.sink.close();
            }
            catch (Throwable e3) {
                if (thrown != null) break block7;
                thrown = e3;
            }
        }
        this.closed = true;
        if (thrown != null) {
            Util.sneakyRethrow(thrown);
        }
    }

    @Override
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ")";
    }
}

