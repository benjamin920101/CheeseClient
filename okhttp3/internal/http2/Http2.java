/*
 * Decompiled with CFR 0.152.
 */
package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

public final class Http2 {
    static final ByteString CONNECTION_PREFACE;
    static final int INITIAL_MAX_FRAME_SIZE = 16384;
    static final byte TYPE_DATA = 0;
    static final byte TYPE_HEADERS = 1;
    static final byte TYPE_PRIORITY = 2;
    static final byte TYPE_RST_STREAM = 3;
    static final byte TYPE_SETTINGS = 4;
    static final byte TYPE_PUSH_PROMISE = 5;
    static final byte TYPE_PING = 6;
    static final byte TYPE_GOAWAY = 7;
    static final byte TYPE_WINDOW_UPDATE = 8;
    static final byte TYPE_CONTINUATION = 9;
    static final byte FLAG_NONE = 0;
    static final byte FLAG_ACK = 1;
    static final byte FLAG_END_STREAM = 1;
    static final byte FLAG_END_HEADERS = 4;
    static final byte FLAG_END_PUSH_PROMISE = 4;
    static final byte FLAG_PADDED = 8;
    static final byte FLAG_PRIORITY = 32;
    static final byte FLAG_COMPRESSED = 32;
    private static final String[] FRAME_NAMES;
    static final String[] FLAGS;
    static final String[] BINARY;

    private Http2() {
    }

    static IllegalArgumentException illegalArgument(String message, Object ... args) {
        throw new IllegalArgumentException(Util.format(message, args));
    }

    static IOException ioException(String message, Object ... args) throws IOException {
        throw new IOException(Util.format(message, args));
    }

    static String frameLog(boolean inbound, int streamId, int length, byte type, byte flags) {
        String formattedType = type < FRAME_NAMES.length ? FRAME_NAMES[type] : Util.format("0x%02x", type);
        String formattedFlags = Http2.formatFlags(type, flags);
        return Util.format("%s 0x%08x %5d %-13s %s", inbound ? "<<" : ">>", streamId, length, formattedType, formattedFlags);
    }

    static String formatFlags(byte type, byte flags) {
        String result;
        if (flags == 0) {
            return "";
        }
        switch (type) {
            case 4: 
            case 6: {
                return flags == 1 ? "ACK" : BINARY[flags];
            }
            case 2: 
            case 3: 
            case 7: 
            case 8: {
                return BINARY[flags];
            }
        }
        String string = result = flags < FLAGS.length ? FLAGS[flags] : BINARY[flags];
        if (type == 5 && (flags & 4) != 0) {
            return result.replace("HEADERS", "PUSH_PROMISE");
        }
        if (type == 0 && (flags & 0x20) != 0) {
            return result.replace("PRIORITY", "COMPRESSED");
        }
        return result;
    }

    static {
        int[] frameFlags;
        CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        FRAME_NAMES = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        FLAGS = new String[64];
        BINARY = new String[256];
        for (int i2 = 0; i2 < BINARY.length; ++i2) {
            Http2.BINARY[i2] = Util.format("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
        }
        Http2.FLAGS[0] = "";
        Http2.FLAGS[1] = "END_STREAM";
        int[] prefixFlags = new int[]{1};
        Http2.FLAGS[8] = "PADDED";
        for (int prefixFlag : prefixFlags) {
            Http2.FLAGS[prefixFlag | 8] = FLAGS[prefixFlag] + "|PADDED";
        }
        Http2.FLAGS[4] = "END_HEADERS";
        Http2.FLAGS[32] = "PRIORITY";
        Http2.FLAGS[36] = "END_HEADERS|PRIORITY";
        for (int frameFlag : frameFlags = new int[]{4, 32, 36}) {
            for (int prefixFlag : prefixFlags) {
                Http2.FLAGS[prefixFlag | frameFlag] = FLAGS[prefixFlag] + '|' + FLAGS[frameFlag];
                Http2.FLAGS[prefixFlag | frameFlag | 8] = FLAGS[prefixFlag] + '|' + FLAGS[frameFlag] + "|PADDED";
            }
        }
        for (int i3 = 0; i3 < FLAGS.length; ++i3) {
            if (FLAGS[i3] != null) continue;
            Http2.FLAGS[i3] = BINARY[i3];
        }
    }
}

