/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.PeekSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import okio.Util;

final class RealBufferedSource
implements BufferedSource {
    public final Buffer buffer = new Buffer();
    public final Source source;
    boolean closed;

    RealBufferedSource(Source source) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        this.source = source;
    }

    @Override
    public Buffer buffer() {
        return this.buffer;
    }

    @Override
    public Buffer getBuffer() {
        return this.buffer;
    }

    @Override
    public long read(Buffer sink, long byteCount) throws IOException {
        long read;
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size == 0L && (read = this.source.read(this.buffer, 8192L)) == -1L) {
            return -1L;
        }
        long toRead = Math.min(byteCount, this.buffer.size);
        return this.buffer.read(sink, toRead);
    }

    @Override
    public boolean exhausted() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, 8192L) == -1L;
    }

    @Override
    public void require(long byteCount) throws IOException {
        if (!this.request(byteCount)) {
            throw new EOFException();
        }
    }

    @Override
    public boolean request(long byteCount) throws IOException {
        if (byteCount < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (this.buffer.size < byteCount) {
            if (this.source.read(this.buffer, 8192L) != -1L) continue;
            return false;
        }
        return true;
    }

    @Override
    public byte readByte() throws IOException {
        this.require(1L);
        return this.buffer.readByte();
    }

    @Override
    public ByteString readByteString() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override
    public ByteString readByteString(long byteCount) throws IOException {
        this.require(byteCount);
        return this.buffer.readByteString(byteCount);
    }

    @Override
    public int select(Options options) throws IOException {
        int index;
        block3: {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            do {
                if ((index = this.buffer.selectPrefix(options, true)) == -1) {
                    return -1;
                }
                if (index != -2) break block3;
            } while (this.source.read(this.buffer, 8192L) != -1L);
            return -1;
        }
        int selectedSize = options.byteStrings[index].size();
        this.buffer.skip(selectedSize);
        return index;
    }

    @Override
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override
    public byte[] readByteArray(long byteCount) throws IOException {
        this.require(byteCount);
        return this.buffer.readByteArray(byteCount);
    }

    @Override
    public int read(byte[] sink) throws IOException {
        return this.read(sink, 0, sink.length);
    }

    @Override
    public void readFully(byte[] sink) throws IOException {
        try {
            this.require(sink.length);
        }
        catch (EOFException e2) {
            int offset = 0;
            while (this.buffer.size > 0L) {
                int read = this.buffer.read(sink, offset, (int)this.buffer.size);
                if (read == -1) {
                    throw new AssertionError();
                }
                offset += read;
            }
            throw e2;
        }
        this.buffer.readFully(sink);
    }

    @Override
    public int read(byte[] sink, int offset, int byteCount) throws IOException {
        long read;
        Util.checkOffsetAndCount(sink.length, offset, byteCount);
        if (this.buffer.size == 0L && (read = this.source.read(this.buffer, 8192L)) == -1L) {
            return -1;
        }
        int toRead = (int)Math.min((long)byteCount, this.buffer.size);
        return this.buffer.read(sink, offset, toRead);
    }

    @Override
    public int read(ByteBuffer sink) throws IOException {
        long read;
        if (this.buffer.size == 0L && (read = this.source.read(this.buffer, 8192L)) == -1L) {
            return -1;
        }
        return this.buffer.read(sink);
    }

    @Override
    public void readFully(Buffer sink, long byteCount) throws IOException {
        try {
            this.require(byteCount);
        }
        catch (EOFException e2) {
            sink.writeAll(this.buffer);
            throw e2;
        }
        this.buffer.readFully(sink, byteCount);
    }

    @Override
    public long readAll(Sink sink) throws IOException {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long totalBytesWritten = 0L;
        while (this.source.read(this.buffer, 8192L) != -1L) {
            long emitByteCount = this.buffer.completeSegmentByteCount();
            if (emitByteCount <= 0L) continue;
            totalBytesWritten += emitByteCount;
            sink.write(this.buffer, emitByteCount);
        }
        if (this.buffer.size() > 0L) {
            totalBytesWritten += this.buffer.size();
            sink.write(this.buffer, this.buffer.size());
        }
        return totalBytesWritten;
    }

    @Override
    public String readUtf8() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override
    public String readUtf8(long byteCount) throws IOException {
        this.require(byteCount);
        return this.buffer.readUtf8(byteCount);
    }

    @Override
    public String readString(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.buffer.writeAll(this.source);
        return this.buffer.readString(charset);
    }

    @Override
    public String readString(long byteCount, Charset charset) throws IOException {
        this.require(byteCount);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return this.buffer.readString(byteCount, charset);
    }

    @Override
    @Nullable
    public String readUtf8Line() throws IOException {
        long newline = this.indexOf((byte)10);
        if (newline == -1L) {
            return this.buffer.size != 0L ? this.readUtf8(this.buffer.size) : null;
        }
        return this.buffer.readUtf8Line(newline);
    }

    @Override
    public String readUtf8LineStrict() throws IOException {
        return this.readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override
    public String readUtf8LineStrict(long limit) throws IOException {
        if (limit < 0L) {
            throw new IllegalArgumentException("limit < 0: " + limit);
        }
        long scanLength = limit == Long.MAX_VALUE ? Long.MAX_VALUE : limit + 1L;
        long newline = this.indexOf((byte)10, 0L, scanLength);
        if (newline != -1L) {
            return this.buffer.readUtf8Line(newline);
        }
        if (scanLength < Long.MAX_VALUE && this.request(scanLength) && this.buffer.getByte(scanLength - 1L) == 13 && this.request(scanLength + 1L) && this.buffer.getByte(scanLength) == 10) {
            return this.buffer.readUtf8Line(scanLength);
        }
        Buffer data = new Buffer();
        this.buffer.copyTo(data, 0L, Math.min(32L, this.buffer.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.buffer.size(), limit) + " content=" + data.readByteString().hex() + '\u2026');
    }

    @Override
    public int readUtf8CodePoint() throws IOException {
        this.require(1L);
        byte b0 = this.buffer.getByte(0L);
        if ((b0 & 0xE0) == 192) {
            this.require(2L);
        } else if ((b0 & 0xF0) == 224) {
            this.require(3L);
        } else if ((b0 & 0xF8) == 240) {
            this.require(4L);
        }
        return this.buffer.readUtf8CodePoint();
    }

    @Override
    public short readShort() throws IOException {
        this.require(2L);
        return this.buffer.readShort();
    }

    @Override
    public short readShortLe() throws IOException {
        this.require(2L);
        return this.buffer.readShortLe();
    }

    @Override
    public int readInt() throws IOException {
        this.require(4L);
        return this.buffer.readInt();
    }

    @Override
    public int readIntLe() throws IOException {
        this.require(4L);
        return this.buffer.readIntLe();
    }

    @Override
    public long readLong() throws IOException {
        this.require(8L);
        return this.buffer.readLong();
    }

    @Override
    public long readLongLe() throws IOException {
        this.require(8L);
        return this.buffer.readLongLe();
    }

    @Override
    public long readDecimalLong() throws IOException {
        this.require(1L);
        int pos = 0;
        while (this.request(pos + 1)) {
            byte b2 = this.buffer.getByte(pos);
            if (!(b2 >= 48 && b2 <= 57 || pos == 0 && b2 == 45)) {
                if (pos != 0) break;
                throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", b2));
            }
            ++pos;
        }
        return this.buffer.readDecimalLong();
    }

    @Override
    public long readHexadecimalUnsignedLong() throws IOException {
        this.require(1L);
        int pos = 0;
        while (this.request(pos + 1)) {
            byte b2 = this.buffer.getByte(pos);
            if (!(b2 >= 48 && b2 <= 57 || b2 >= 97 && b2 <= 102 || b2 >= 65 && b2 <= 70)) {
                if (pos != 0) break;
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", b2));
            }
            ++pos;
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override
    public void skip(long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (byteCount > 0L) {
            if (this.buffer.size == 0L && this.source.read(this.buffer, 8192L) == -1L) {
                throw new EOFException();
            }
            long toSkip = Math.min(byteCount, this.buffer.size());
            this.buffer.skip(toSkip);
            byteCount -= toSkip;
        }
    }

    @Override
    public long indexOf(byte b2) throws IOException {
        return this.indexOf(b2, 0L, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b2, long fromIndex) throws IOException {
        return this.indexOf(b2, fromIndex, Long.MAX_VALUE);
    }

    @Override
    public long indexOf(byte b2, long fromIndex, long toIndex) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (fromIndex < 0L || toIndex < fromIndex) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", fromIndex, toIndex));
        }
        while (fromIndex < toIndex) {
            long result = this.buffer.indexOf(b2, fromIndex, toIndex);
            if (result != -1L) {
                return result;
            }
            long lastBufferSize = this.buffer.size;
            if (lastBufferSize >= toIndex || this.source.read(this.buffer, 8192L) == -1L) {
                return -1L;
            }
            fromIndex = Math.max(fromIndex, lastBufferSize);
        }
        return -1L;
    }

    @Override
    public long indexOf(ByteString bytes) throws IOException {
        return this.indexOf(bytes, 0L);
    }

    @Override
    public long indexOf(ByteString bytes, long fromIndex) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long result;
        while ((result = this.buffer.indexOf(bytes, fromIndex)) == -1L) {
            long lastBufferSize = this.buffer.size;
            if (this.source.read(this.buffer, 8192L) == -1L) {
                return -1L;
            }
            fromIndex = Math.max(fromIndex, lastBufferSize - (long)bytes.size() + 1L);
        }
        return result;
    }

    @Override
    public long indexOfElement(ByteString targetBytes) throws IOException {
        return this.indexOfElement(targetBytes, 0L);
    }

    @Override
    public long indexOfElement(ByteString targetBytes, long fromIndex) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long result;
        while ((result = this.buffer.indexOfElement(targetBytes, fromIndex)) == -1L) {
            long lastBufferSize = this.buffer.size;
            if (this.source.read(this.buffer, 8192L) == -1L) {
                return -1L;
            }
            fromIndex = Math.max(fromIndex, lastBufferSize);
        }
        return result;
    }

    @Override
    public boolean rangeEquals(long offset, ByteString bytes) throws IOException {
        return this.rangeEquals(offset, bytes, 0, bytes.size());
    }

    @Override
    public boolean rangeEquals(long offset, ByteString bytes, int bytesOffset, int byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (offset < 0L || bytesOffset < 0 || byteCount < 0 || bytes.size() - bytesOffset < byteCount) {
            return false;
        }
        for (int i2 = 0; i2 < byteCount; ++i2) {
            long bufferOffset = offset + (long)i2;
            if (!this.request(bufferOffset + 1L)) {
                return false;
            }
            if (this.buffer.getByte(bufferOffset) == bytes.getByte(bytesOffset + i2)) continue;
            return false;
        }
        return true;
    }

    @Override
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override
    public InputStream inputStream() {
        return new InputStream(){

            @Override
            public int read() throws IOException {
                long count;
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                if (RealBufferedSource.this.buffer.size == 0L && (count = RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L)) == -1L) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.readByte() & 0xFF;
            }

            @Override
            public int read(byte[] data, int offset, int byteCount) throws IOException {
                long count;
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                Util.checkOffsetAndCount(data.length, offset, byteCount);
                if (RealBufferedSource.this.buffer.size == 0L && (count = RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L)) == -1L) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.read(data, offset, byteCount);
            }

            @Override
            public int available() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                return (int)Math.min(RealBufferedSource.this.buffer.size, Integer.MAX_VALUE);
            }

            @Override
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override
    public boolean isOpen() {
        return !this.closed;
    }

    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }

    @Override
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ")";
    }
}

