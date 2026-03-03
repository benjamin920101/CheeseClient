/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.ws;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class WebSocketReader {
    final boolean isClient;
    final BufferedSource source;
    final FrameCallback frameCallback;
    boolean closed;
    int opcode;
    long frameLength;
    boolean isFinalFrame;
    boolean isControlFrame;
    private final Buffer controlFrameBuffer = new Buffer();
    private final Buffer messageFrameBuffer = new Buffer();
    private final byte[] maskKey;
    private final Buffer.UnsafeCursor maskCursor;

    WebSocketReader(boolean isClient, BufferedSource source, FrameCallback frameCallback) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = isClient;
        this.source = source;
        this.frameCallback = frameCallback;
        this.maskKey = isClient ? null : new byte[4];
        this.maskCursor = isClient ? null : new Buffer.UnsafeCursor();
    }

    void processNextFrame() throws IOException {
        this.readHeader();
        if (this.isControlFrame) {
            this.readControlFrame();
        } else {
            this.readMessageFrame();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void readHeader() throws IOException {
        boolean isMasked;
        boolean reservedFlag3;
        int b0;
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutBefore = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            b0 = this.source.readByte() & 0xFF;
        }
        finally {
            this.source.timeout().timeout(timeoutBefore, TimeUnit.NANOSECONDS);
        }
        this.opcode = b0 & 0xF;
        this.isFinalFrame = (b0 & 0x80) != 0;
        boolean bl2 = this.isControlFrame = (b0 & 8) != 0;
        if (this.isControlFrame && !this.isFinalFrame) {
            throw new ProtocolException("Control frames must be final.");
        }
        boolean reservedFlag1 = (b0 & 0x40) != 0;
        boolean reservedFlag2 = (b0 & 0x20) != 0;
        boolean bl3 = reservedFlag3 = (b0 & 0x10) != 0;
        if (reservedFlag1 || reservedFlag2 || reservedFlag3) {
            throw new ProtocolException("Reserved flags are unsupported.");
        }
        int b1 = this.source.readByte() & 0xFF;
        boolean bl4 = isMasked = (b1 & 0x80) != 0;
        if (isMasked == this.isClient) {
            throw new ProtocolException(this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
        }
        this.frameLength = b1 & 0x7F;
        if (this.frameLength == 126L) {
            this.frameLength = (long)this.source.readShort() & 0xFFFFL;
        } else if (this.frameLength == 127L) {
            this.frameLength = this.source.readLong();
            if (this.frameLength < 0L) {
                throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
            }
        }
        if (this.isControlFrame && this.frameLength > 125L) {
            throw new ProtocolException("Control frame must be less than 125B.");
        }
        if (isMasked) {
            this.source.readFully(this.maskKey);
        }
    }

    private void readControlFrame() throws IOException {
        if (this.frameLength > 0L) {
            this.source.readFully(this.controlFrameBuffer, this.frameLength);
            if (!this.isClient) {
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 9: {
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                break;
            }
            case 10: {
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                break;
            }
            case 8: {
                int code = 1005;
                String reason = "";
                long bufferSize = this.controlFrameBuffer.size();
                if (bufferSize == 1L) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (bufferSize != 0L) {
                    code = this.controlFrameBuffer.readShort();
                    reason = this.controlFrameBuffer.readUtf8();
                    String codeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(code);
                    if (codeExceptionMessage != null) {
                        throw new ProtocolException(codeExceptionMessage);
                    }
                }
                this.frameCallback.onReadClose(code, reason);
                this.closed = true;
                break;
            }
            default: {
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
            }
        }
    }

    private void readMessageFrame() throws IOException {
        int opcode = this.opcode;
        if (opcode != 1 && opcode != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(opcode));
        }
        this.readMessage();
        if (opcode == 1) {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            this.readHeader();
            if (!this.isControlFrame) break;
            this.readControlFrame();
        }
    }

    private void readMessage() throws IOException {
        block4: {
            do {
                if (this.closed) {
                    throw new IOException("closed");
                }
                if (this.frameLength > 0L) {
                    this.source.readFully(this.messageFrameBuffer, this.frameLength);
                    if (!this.isClient) {
                        this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                        this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                }
                if (this.isFinalFrame) break block4;
                this.readUntilNonControlFrame();
            } while (this.opcode == 0);
            throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
        }
    }

    public static interface FrameCallback {
        public void onReadMessage(String var1) throws IOException;

        public void onReadMessage(ByteString var1) throws IOException;

        public void onReadPing(ByteString var1);

        public void onReadPong(ByteString var1);

        public void onReadClose(int var1, String var2);
    }
}

