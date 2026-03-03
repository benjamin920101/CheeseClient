/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.io.UnsupportedEncodingException;

final class Base64 {
    private static final byte[] MAP = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] URL_MAP = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    private Base64() {
    }

    public static byte[] decode(String in2) {
        char c2;
        int limit;
        for (limit = in2.length(); limit > 0 && ((c2 = in2.charAt(limit - 1)) == '=' || c2 == '\n' || c2 == '\r' || c2 == ' ' || c2 == '\t'); --limit) {
        }
        byte[] out = new byte[(int)((long)limit * 6L / 8L)];
        int outCount = 0;
        int inCount = 0;
        int word = 0;
        for (int pos = 0; pos < limit; ++pos) {
            int bits;
            char c3 = in2.charAt(pos);
            if (c3 >= 'A' && c3 <= 'Z') {
                bits = c3 - 65;
            } else if (c3 >= 'a' && c3 <= 'z') {
                bits = c3 - 71;
            } else if (c3 >= '0' && c3 <= '9') {
                bits = c3 + 4;
            } else if (c3 == '+' || c3 == '-') {
                bits = 62;
            } else if (c3 == '/' || c3 == '_') {
                bits = 63;
            } else {
                if (c3 == '\n' || c3 == '\r' || c3 == ' ' || c3 == '\t') continue;
                return null;
            }
            word = word << 6 | (byte)bits;
            if (++inCount % 4 != 0) continue;
            out[outCount++] = (byte)(word >> 16);
            out[outCount++] = (byte)(word >> 8);
            out[outCount++] = (byte)word;
        }
        int lastWordChars = inCount % 4;
        if (lastWordChars == 1) {
            return null;
        }
        if (lastWordChars == 2) {
            out[outCount++] = (byte)((word <<= 12) >> 16);
        } else if (lastWordChars == 3) {
            out[outCount++] = (byte)((word <<= 6) >> 16);
            out[outCount++] = (byte)(word >> 8);
        }
        if (outCount == out.length) {
            return out;
        }
        byte[] prefix = new byte[outCount];
        System.arraycopy(out, 0, prefix, 0, outCount);
        return prefix;
    }

    public static String encode(byte[] in2) {
        return Base64.encode(in2, MAP);
    }

    public static String encodeUrl(byte[] in2) {
        return Base64.encode(in2, URL_MAP);
    }

    private static String encode(byte[] in2, byte[] map) {
        int length = (in2.length + 2) / 3 * 4;
        byte[] out = new byte[length];
        int index = 0;
        int end = in2.length - in2.length % 3;
        for (int i2 = 0; i2 < end; i2 += 3) {
            out[index++] = map[(in2[i2] & 0xFF) >> 2];
            out[index++] = map[(in2[i2] & 3) << 4 | (in2[i2 + 1] & 0xFF) >> 4];
            out[index++] = map[(in2[i2 + 1] & 0xF) << 2 | (in2[i2 + 2] & 0xFF) >> 6];
            out[index++] = map[in2[i2 + 2] & 0x3F];
        }
        switch (in2.length % 3) {
            case 1: {
                out[index++] = map[(in2[end] & 0xFF) >> 2];
                out[index++] = map[(in2[end] & 3) << 4];
                out[index++] = 61;
                out[index++] = 61;
                break;
            }
            case 2: {
                out[index++] = map[(in2[end] & 0xFF) >> 2];
                out[index++] = map[(in2[end] & 3) << 4 | (in2[end + 1] & 0xFF) >> 4];
                out[index++] = map[(in2[end + 1] & 0xF) << 2];
                out[index++] = 61;
            }
        }
        try {
            return new String(out, "US-ASCII");
        }
        catch (UnsupportedEncodingException e2) {
            throw new AssertionError((Object)e2);
        }
    }
}

