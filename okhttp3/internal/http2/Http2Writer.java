/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Header;
import okhttp3.internal.http2.Hpack;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.http2.Settings;
import okio.Buffer;
import okio.BufferedSink;

final class Http2Writer
implements Closeable {
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final BufferedSink sink;
    private final boolean client;
    private final Buffer hpackBuffer;
    private int maxFrameSize;
    private boolean closed;
    final Hpack.Writer hpackWriter;

    Http2Writer(BufferedSink sink, boolean client) {
        this.sink = sink;
        this.client = client;
        this.hpackBuffer = new Buffer();
        this.hpackWriter = new Hpack.Writer(this.hpackBuffer);
        this.maxFrameSize = 16384;
    }

    public synchronized void connectionPreface() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (!this.client) {
            return;
        }
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
        }
        this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
        this.sink.flush();
    }

    public synchronized void applyAndAckSettings(Settings peerSettings) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.maxFrameSize = peerSettings.getMaxFrameSize(this.maxFrameSize);
        if (peerSettings.getHeaderTableSize() != -1) {
            this.hpackWriter.setHeaderTableSizeSetting(peerSettings.getHeaderTableSize());
        }
        int length = 0;
        byte type = 4;
        byte flags = 1;
        int streamId = 0;
        this.frameHeader(streamId, length, type, flags);
        this.sink.flush();
    }

    public synchronized void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(requestHeaders);
        long byteCount = this.hpackBuffer.size();
        int length = (int)Math.min((long)(this.maxFrameSize - 4), byteCount);
        byte type = 5;
        byte flags = byteCount == (long)length ? (byte)4 : 0;
        this.frameHeader(streamId, length + 4, type, flags);
        this.sink.writeInt(promisedStreamId & Integer.MAX_VALUE);
        this.sink.write(this.hpackBuffer, (long)length);
        if (byteCount > (long)length) {
            this.writeContinuationFrames(streamId, byteCount - (long)length);
        }
    }

    public synchronized void flush() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.sink.flush();
    }

    public synchronized void rstStream(int streamId, ErrorCode errorCode) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        int length = 4;
        byte type = 3;
        byte flags = 0;
        this.frameHeader(streamId, length, type, flags);
        this.sink.writeInt(errorCode.httpCode);
        this.sink.flush();
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public synchronized void data(boolean outFinished, int streamId, Buffer source, int byteCount) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        byte flags = 0;
        if (outFinished) {
            flags = (byte)(flags | '\u0001');
        }
        this.dataFrame(streamId, flags, source, byteCount);
    }

    void dataFrame(int streamId, byte flags, Buffer buffer, int byteCount) throws IOException {
        byte type = 0;
        this.frameHeader(streamId, byteCount, type, flags);
        if (byteCount > 0) {
            this.sink.write(buffer, (long)byteCount);
        }
    }

    public synchronized void settings(Settings settings) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        int length = settings.size() * 6;
        byte type = 4;
        byte flags = 0;
        int streamId = 0;
        this.frameHeader(streamId, length, type, flags);
        for (int i2 = 0; i2 < 10; ++i2) {
            if (!settings.isSet(i2)) continue;
            int id2 = i2;
            if (id2 == 4) {
                id2 = 3;
            } else if (id2 == 7) {
                id2 = 4;
            }
            this.sink.writeShort(id2);
            this.sink.writeInt(settings.get(i2));
        }
        this.sink.flush();
    }

    public synchronized void ping(boolean ack2, int payload1, int payload2) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        int length = 8;
        byte type = 6;
        byte flags = ack2 ? (byte)1 : 0;
        int streamId = 0;
        this.frameHeader(streamId, length, type, flags);
        this.sink.writeInt(payload1);
        this.sink.writeInt(payload2);
        this.sink.flush();
    }

    public synchronized void goAway(int lastGoodStreamId, ErrorCode errorCode, byte[] debugData) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (errorCode.httpCode == -1) {
            throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
        }
        int length = 8 + debugData.length;
        byte type = 7;
        byte flags = 0;
        int streamId = 0;
        this.frameHeader(streamId, length, type, flags);
        this.sink.writeInt(lastGoodStreamId);
        this.sink.writeInt(errorCode.httpCode);
        if (debugData.length > 0) {
            this.sink.write(debugData);
        }
        this.sink.flush();
    }

    public synchronized void windowUpdate(int streamId, long windowSizeIncrement) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (windowSizeIncrement == 0L || windowSizeIncrement > Integer.MAX_VALUE) {
            throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", windowSizeIncrement);
        }
        int length = 4;
        byte type = 8;
        byte flags = 0;
        this.frameHeader(streamId, length, type, flags);
        this.sink.writeInt((int)windowSizeIncrement);
        this.sink.flush();
    }

    public void frameHeader(int streamId, int length, byte type, byte flags) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, streamId, length, type, flags));
        }
        if (length > this.maxFrameSize) {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", this.maxFrameSize, length);
        }
        if ((streamId & Integer.MIN_VALUE) != 0) {
            throw Http2.illegalArgument("reserved bit set: %s", streamId);
        }
        Http2Writer.writeMedium(this.sink, length);
        this.sink.writeByte(type & 0xFF);
        this.sink.writeByte(flags & 0xFF);
        this.sink.writeInt(streamId & Integer.MAX_VALUE);
    }

    @Override
    public synchronized void close() throws IOException {
        this.closed = true;
        this.sink.close();
    }

    private static void writeMedium(BufferedSink sink, int i2) throws IOException {
        sink.writeByte(i2 >>> 16 & 0xFF);
        sink.writeByte(i2 >>> 8 & 0xFF);
        sink.writeByte(i2 & 0xFF);
    }

    private void writeContinuationFrames(int streamId, long byteCount) throws IOException {
        while (byteCount > 0L) {
            int length;
            this.frameHeader(streamId, length, (byte)9, (byteCount -= (long)(length = (int)Math.min((long)this.maxFrameSize, byteCount))) == 0L ? (byte)4 : 0);
            this.sink.write(this.hpackBuffer, (long)length);
        }
    }

    public synchronized void headers(boolean outFinished, int streamId, List<Header> headerBlock) throws IOException {
        byte flags;
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(headerBlock);
        long byteCount = this.hpackBuffer.size();
        int length = (int)Math.min((long)this.maxFrameSize, byteCount);
        byte type = 1;
        byte by = flags = byteCount == (long)length ? (byte)4 : 0;
        if (outFinished) {
            flags = (byte)(flags | 1);
        }
        this.frameHeader(streamId, length, type, flags);
        this.sink.write(this.hpackBuffer, (long)length);
        if (byteCount > (long)length) {
            this.writeContinuationFrames(streamId, byteCount - (long)length);
        }
    }
}

