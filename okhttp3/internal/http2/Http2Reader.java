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
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Http2Reader
implements Closeable {
    static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final BufferedSource source;
    private final ContinuationSource continuation;
    private final boolean client;
    final Hpack.Reader hpackReader;

    Http2Reader(BufferedSource source, boolean client) {
        this.source = source;
        this.client = client;
        this.continuation = new ContinuationSource(this.source);
        this.hpackReader = new Hpack.Reader(4096, this.continuation);
    }

    public void readConnectionPreface(Handler handler) throws IOException {
        if (this.client) {
            if (!this.nextFrame(true, handler)) {
                throw Http2.ioException("Required SETTINGS preface not received", new Object[0]);
            }
        } else {
            ByteString connectionPreface = this.source.readByteString(Http2.CONNECTION_PREFACE.size());
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Util.format("<< CONNECTION %s", connectionPreface.hex()));
            }
            if (!Http2.CONNECTION_PREFACE.equals(connectionPreface)) {
                throw Http2.ioException("Expected a connection header but was %s", connectionPreface.utf8());
            }
        }
    }

    public boolean nextFrame(boolean requireSettings, Handler handler) throws IOException {
        try {
            this.source.require(9L);
        }
        catch (IOException e2) {
            return false;
        }
        int length = Http2Reader.readMedium(this.source);
        if (length < 0 || length > 16384) {
            throw Http2.ioException("FRAME_SIZE_ERROR: %s", length);
        }
        byte type = (byte)(this.source.readByte() & 0xFF);
        if (requireSettings && type != 4) {
            throw Http2.ioException("Expected a SETTINGS frame but was %s", type);
        }
        byte flags = (byte)(this.source.readByte() & 0xFF);
        int streamId = this.source.readInt() & Integer.MAX_VALUE;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(true, streamId, length, type, flags));
        }
        switch (type) {
            case 0: {
                this.readData(handler, length, flags, streamId);
                break;
            }
            case 1: {
                this.readHeaders(handler, length, flags, streamId);
                break;
            }
            case 2: {
                this.readPriority(handler, length, flags, streamId);
                break;
            }
            case 3: {
                this.readRstStream(handler, length, flags, streamId);
                break;
            }
            case 4: {
                this.readSettings(handler, length, flags, streamId);
                break;
            }
            case 5: {
                this.readPushPromise(handler, length, flags, streamId);
                break;
            }
            case 6: {
                this.readPing(handler, length, flags, streamId);
                break;
            }
            case 7: {
                this.readGoAway(handler, length, flags, streamId);
                break;
            }
            case 8: {
                this.readWindowUpdate(handler, length, flags, streamId);
                break;
            }
            default: {
                this.source.skip(length);
            }
        }
        return true;
    }

    private void readHeaders(Handler handler, int length, byte flags, int streamId) throws IOException {
        short padding;
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }
        boolean endStream = (flags & 1) != 0;
        short s2 = padding = (flags & 8) != 0 ? (short)(this.source.readByte() & 0xFF) : (short)0;
        if ((flags & 0x20) != 0) {
            this.readPriority(handler, streamId);
            length -= 5;
        }
        length = Http2Reader.lengthWithoutPadding(length, flags, padding);
        List<Header> headerBlock = this.readHeaderBlock(length, padding, flags, streamId);
        handler.headers(endStream, streamId, -1, headerBlock);
    }

    private List<Header> readHeaderBlock(int length, short padding, byte flags, int streamId) throws IOException {
        this.continuation.length = this.continuation.left = length;
        this.continuation.padding = padding;
        this.continuation.flags = flags;
        this.continuation.streamId = streamId;
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private void readData(Handler handler, int length, byte flags, int streamId) throws IOException {
        boolean gzipped;
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
        }
        boolean inFinished = (flags & 1) != 0;
        boolean bl2 = gzipped = (flags & 0x20) != 0;
        if (gzipped) {
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        short padding = (flags & 8) != 0 ? (short)(this.source.readByte() & 0xFF) : (short)0;
        length = Http2Reader.lengthWithoutPadding(length, flags, padding);
        handler.data(inFinished, streamId, this.source, length);
        this.source.skip(padding);
    }

    private void readPriority(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 5) {
            throw Http2.ioException("TYPE_PRIORITY length: %d != 5", length);
        }
        if (streamId == 0) {
            throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        this.readPriority(handler, streamId);
    }

    private void readPriority(Handler handler, int streamId) throws IOException {
        int w1 = this.source.readInt();
        boolean exclusive = (w1 & Integer.MIN_VALUE) != 0;
        int streamDependency = w1 & Integer.MAX_VALUE;
        int weight = (this.source.readByte() & 0xFF) + 1;
        handler.priority(streamId, streamDependency, weight, exclusive);
    }

    private void readRstStream(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 4) {
            throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", length);
        }
        if (streamId == 0) {
            throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        int errorCodeInt = this.source.readInt();
        ErrorCode errorCode = ErrorCode.fromHttp2(errorCodeInt);
        if (errorCode == null) {
            throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", errorCodeInt);
        }
        handler.rstStream(streamId, errorCode);
    }

    private void readSettings(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (streamId != 0) {
            throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((flags & 1) != 0) {
            if (length != 0) {
                throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            handler.ackSettings();
            return;
        }
        if (length % 6 != 0) {
            throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", length);
        }
        Settings settings = new Settings();
        for (int i2 = 0; i2 < length; i2 += 6) {
            int id2 = this.source.readShort() & 0xFFFF;
            int value = this.source.readInt();
            switch (id2) {
                case 1: {
                    break;
                }
                case 2: {
                    if (value == 0 || value == 1) break;
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
                case 3: {
                    id2 = 4;
                    break;
                }
                case 4: {
                    id2 = 7;
                    if (value >= 0) break;
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                }
                case 5: {
                    if (value >= 16384 && value <= 0xFFFFFF) break;
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", value);
                }
                case 6: {
                    break;
                }
            }
            settings.set(id2, value);
        }
        handler.settings(false, settings);
    }

    private void readPushPromise(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (streamId == 0) {
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        short padding = (flags & 8) != 0 ? (short)(this.source.readByte() & 0xFF) : (short)0;
        int promisedStreamId = this.source.readInt() & Integer.MAX_VALUE;
        length -= 4;
        length = Http2Reader.lengthWithoutPadding(length, flags, padding);
        List<Header> headerBlock = this.readHeaderBlock(length, padding, flags, streamId);
        handler.pushPromise(streamId, promisedStreamId, headerBlock);
    }

    private void readPing(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 8) {
            throw Http2.ioException("TYPE_PING length != 8: %s", length);
        }
        if (streamId != 0) {
            throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
        }
        int payload1 = this.source.readInt();
        int payload2 = this.source.readInt();
        boolean ack2 = (flags & 1) != 0;
        handler.ping(ack2, payload1, payload2);
    }

    private void readGoAway(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length < 8) {
            throw Http2.ioException("TYPE_GOAWAY length < 8: %s", length);
        }
        if (streamId != 0) {
            throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        int lastStreamId = this.source.readInt();
        int errorCodeInt = this.source.readInt();
        int opaqueDataLength = length - 8;
        ErrorCode errorCode = ErrorCode.fromHttp2(errorCodeInt);
        if (errorCode == null) {
            throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", errorCodeInt);
        }
        ByteString debugData = ByteString.EMPTY;
        if (opaqueDataLength > 0) {
            debugData = this.source.readByteString(opaqueDataLength);
        }
        handler.goAway(lastStreamId, errorCode, debugData);
    }

    private void readWindowUpdate(Handler handler, int length, byte flags, int streamId) throws IOException {
        if (length != 4) {
            throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", length);
        }
        long increment = (long)this.source.readInt() & Integer.MAX_VALUE;
        if (increment == 0L) {
            throw Http2.ioException("windowSizeIncrement was 0", increment);
        }
        handler.windowUpdate(streamId, increment);
    }

    @Override
    public void close() throws IOException {
        this.source.close();
    }

    static int readMedium(BufferedSource source) throws IOException {
        return (source.readByte() & 0xFF) << 16 | (source.readByte() & 0xFF) << 8 | source.readByte() & 0xFF;
    }

    static int lengthWithoutPadding(int length, byte flags, short padding) throws IOException {
        if ((flags & 8) != 0) {
            --length;
        }
        if (padding > length) {
            throw Http2.ioException("PROTOCOL_ERROR padding %s > remaining length %s", padding, length);
        }
        return (short)(length - padding);
    }

    static interface Handler {
        public void data(boolean var1, int var2, BufferedSource var3, int var4) throws IOException;

        public void headers(boolean var1, int var2, int var3, List<Header> var4);

        public void rstStream(int var1, ErrorCode var2);

        public void settings(boolean var1, Settings var2);

        public void ackSettings();

        public void ping(boolean var1, int var2, int var3);

        public void goAway(int var1, ErrorCode var2, ByteString var3);

        public void windowUpdate(int var1, long var2);

        public void priority(int var1, int var2, int var3, boolean var4);

        public void pushPromise(int var1, int var2, List<Header> var3) throws IOException;

        public void alternateService(int var1, String var2, ByteString var3, String var4, int var5, long var6);
    }

    static final class ContinuationSource
    implements Source {
        private final BufferedSource source;
        int length;
        byte flags;
        int streamId;
        int left;
        short padding;

        ContinuationSource(BufferedSource source) {
            this.source = source;
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
            while (this.left == 0) {
                this.source.skip(this.padding);
                this.padding = 0;
                if ((this.flags & 4) != 0) {
                    return -1L;
                }
                this.readContinuationHeader();
            }
            long read = this.source.read(sink, Math.min(byteCount, (long)this.left));
            if (read == -1L) {
                return -1L;
            }
            this.left = (int)((long)this.left - read);
            return read;
        }

        @Override
        public Timeout timeout() {
            return this.source.timeout();
        }

        @Override
        public void close() throws IOException {
        }

        private void readContinuationHeader() throws IOException {
            int previousStreamId = this.streamId;
            this.length = this.left = Http2Reader.readMedium(this.source);
            byte type = (byte)(this.source.readByte() & 0xFF);
            this.flags = (byte)(this.source.readByte() & 0xFF);
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(Http2.frameLog(true, this.streamId, this.length, type, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (type != 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", type);
            }
            if (this.streamId != previousStreamId) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }
}

