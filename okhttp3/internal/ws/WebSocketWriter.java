/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Random;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

final class WebSocketWriter {
    final boolean isClient;
    final Random random;
    final BufferedSink sink;
    final Buffer sinkBuffer;
    boolean writerClosed;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    boolean activeWriter;
    private final byte[] maskKey;
    private final Buffer.UnsafeCursor maskCursor;

    WebSocketWriter(boolean isClient, BufferedSink sink, Random random) {
        if (sink == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.isClient = isClient;
        this.sink = sink;
        this.sinkBuffer = sink.buffer();
        this.random = random;
        this.maskKey = isClient ? new byte[4] : null;
        this.maskCursor = isClient ? new Buffer.UnsafeCursor() : null;
    }

    void writePing(ByteString payload) throws IOException {
        this.writeControlFrame(9, payload);
    }

    void writePong(ByteString payload) throws IOException {
        this.writeControlFrame(10, payload);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void writeClose(int code, ByteString reason) throws IOException {
        ByteString payload = ByteString.EMPTY;
        if (code != 0 || reason != null) {
            if (code != 0) {
                WebSocketProtocol.validateCloseCode(code);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(code);
            if (reason != null) {
                buffer.write(reason);
            }
            payload = buffer.readByteString();
        }
        try {
            this.writeControlFrame(8, payload);
        }
        finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int opcode, ByteString payload) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int length = payload.size();
        if ((long)length > 125L) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        int b0 = 0x80 | opcode;
        this.sinkBuffer.writeByte(b0);
        int b1 = length;
        if (this.isClient) {
            this.sinkBuffer.writeByte(b1 |= 0x80);
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (length > 0) {
                long payloadStart = this.sinkBuffer.size();
                this.sinkBuffer.write(payload);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(payloadStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.writeByte(b1);
            this.sinkBuffer.write(payload);
        }
        this.sink.flush();
    }

    Sink newMessageSink(int formatOpcode, long contentLength) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = formatOpcode;
        this.frameSink.contentLength = contentLength;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    void writeMessageFrame(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        int b0;
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int n2 = b0 = isFirstFrame ? formatOpcode : 0;
        if (isFinal) {
            b0 |= 0x80;
        }
        this.sinkBuffer.writeByte(b0);
        int b1 = 0;
        if (this.isClient) {
            b1 |= 0x80;
        }
        if (byteCount <= 125L) {
            this.sinkBuffer.writeByte(b1 |= (int)byteCount);
        } else if (byteCount <= 65535L) {
            this.sinkBuffer.writeByte(b1 |= 0x7E);
            this.sinkBuffer.writeShort((int)byteCount);
        } else {
            this.sinkBuffer.writeByte(b1 |= 0x7F);
            this.sinkBuffer.writeLong(byteCount);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.write(this.maskKey);
            if (byteCount > 0L) {
                long bufferStart = this.sinkBuffer.size();
                this.sinkBuffer.write(this.buffer, byteCount);
                this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(bufferStart);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, byteCount);
        }
        this.sink.emit();
    }

    final class FrameSink
    implements Sink {
        int formatOpcode;
        long contentLength;
        boolean isFirstFrame;
        boolean closed;

        FrameSink() {
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(source, byteCount);
            boolean deferWrite = this.isFirstFrame && this.contentLength != -1L && WebSocketWriter.this.buffer.size() > this.contentLength - 8192L;
            long emitCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (emitCount > 0L && !deferWrite) {
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, emitCount, this.isFirstFrame, false);
                this.isFirstFrame = false;
            }
        }

        @Override
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            this.isFirstFrame = false;
        }

        @Override
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }
}

