/*
 * Decompiled with CFR 0.152.
 */
package okio;

import java.nio.charset.Charset;

final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Util() {
    }

    public static void checkOffsetAndCount(long size, long offset, long byteCount) {
        if ((offset | byteCount) < 0L || offset > size || size - offset < byteCount) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", size, offset, byteCount));
        }
    }

    public static short reverseBytesShort(short s2) {
        int i2 = s2 & 0xFFFF;
        int reversed = (i2 & 0xFF00) >>> 8 | (i2 & 0xFF) << 8;
        return (short)reversed;
    }

    public static int reverseBytesInt(int i2) {
        return (i2 & 0xFF000000) >>> 24 | (i2 & 0xFF0000) >>> 8 | (i2 & 0xFF00) << 8 | (i2 & 0xFF) << 24;
    }

    public static long reverseBytesLong(long v2) {
        return (v2 & 0xFF00000000000000L) >>> 56 | (v2 & 0xFF000000000000L) >>> 40 | (v2 & 0xFF0000000000L) >>> 24 | (v2 & 0xFF00000000L) >>> 8 | (v2 & 0xFF000000L) << 8 | (v2 & 0xFF0000L) << 24 | (v2 & 0xFF00L) << 40 | (v2 & 0xFFL) << 56;
    }

    public static void sneakyRethrow(Throwable t2) {
        Util.sneakyThrow2(t2);
    }

    private static <T extends Throwable> void sneakyThrow2(Throwable t2) throws T {
        throw t2;
    }

    public static boolean arrayRangeEquals(byte[] a2, int aOffset, byte[] b2, int bOffset, int byteCount) {
        for (int i2 = 0; i2 < byteCount; ++i2) {
            if (a2[i2 + aOffset] == b2[i2 + bOffset]) continue;
            return false;
        }
        return true;
    }
}

