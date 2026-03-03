/*
 * Decompiled with CFR 0.152.
 */
package com.iwebpp.crypto;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

public final class TweetNaclFast {
    private static final String TAG = "TweetNaclFast";
    private static final byte[] _0 = new byte[16];
    private static final byte[] _9 = new byte[32];
    private static final long[] gf0;
    private static final long[] gf1;
    private static final long[] _121665;
    private static final long[] D;
    private static final long[] D2;
    private static final long[] X;
    private static final long[] Y;
    private static final long[] I;
    private static final byte[] sigma;
    private static final long[] K;
    private static final long[] L;
    private static final SecureRandom jrandom;

    private static void ts64(byte[] x2, int xoff, long u2) {
        x2[7 + xoff] = (byte)(u2 & 0xFFL);
        x2[6 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[5 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[4 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[3 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[2 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[1 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
        x2[0 + xoff] = (byte)((u2 >>>= 8) & 0xFFL);
    }

    private static int vn(byte[] x2, int xoff, byte[] y2, int yoff, int n2) {
        int d2 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            d2 |= (x2[i2 + xoff] ^ y2[i2 + yoff]) & 0xFF;
        }
        return (1 & d2 - 1 >>> 8) - 1;
    }

    private static int crypto_verify_16(byte[] x2, int xoff, byte[] y2, int yoff) {
        return TweetNaclFast.vn(x2, xoff, y2, yoff, 16);
    }

    public static int crypto_verify_16(byte[] x2, byte[] y2) {
        return TweetNaclFast.crypto_verify_16(x2, 0, y2, 0);
    }

    private static int crypto_verify_32(byte[] x2, int xoff, byte[] y2, int yoff) {
        return TweetNaclFast.vn(x2, xoff, y2, yoff, 32);
    }

    public static int crypto_verify_32(byte[] x2, byte[] y2) {
        return TweetNaclFast.crypto_verify_32(x2, 0, y2, 0);
    }

    private static void core_salsa20(byte[] o2, byte[] p2, byte[] k2, byte[] c2) {
        int j0 = c2[0] & 0xFF | (c2[1] & 0xFF) << 8 | (c2[2] & 0xFF) << 16 | (c2[3] & 0xFF) << 24;
        int j1 = k2[0] & 0xFF | (k2[1] & 0xFF) << 8 | (k2[2] & 0xFF) << 16 | (k2[3] & 0xFF) << 24;
        int j2 = k2[4] & 0xFF | (k2[5] & 0xFF) << 8 | (k2[6] & 0xFF) << 16 | (k2[7] & 0xFF) << 24;
        int j3 = k2[8] & 0xFF | (k2[9] & 0xFF) << 8 | (k2[10] & 0xFF) << 16 | (k2[11] & 0xFF) << 24;
        int j4 = k2[12] & 0xFF | (k2[13] & 0xFF) << 8 | (k2[14] & 0xFF) << 16 | (k2[15] & 0xFF) << 24;
        int j5 = c2[4] & 0xFF | (c2[5] & 0xFF) << 8 | (c2[6] & 0xFF) << 16 | (c2[7] & 0xFF) << 24;
        int j6 = p2[0] & 0xFF | (p2[1] & 0xFF) << 8 | (p2[2] & 0xFF) << 16 | (p2[3] & 0xFF) << 24;
        int j7 = p2[4] & 0xFF | (p2[5] & 0xFF) << 8 | (p2[6] & 0xFF) << 16 | (p2[7] & 0xFF) << 24;
        int j8 = p2[8] & 0xFF | (p2[9] & 0xFF) << 8 | (p2[10] & 0xFF) << 16 | (p2[11] & 0xFF) << 24;
        int j9 = p2[12] & 0xFF | (p2[13] & 0xFF) << 8 | (p2[14] & 0xFF) << 16 | (p2[15] & 0xFF) << 24;
        int j10 = c2[8] & 0xFF | (c2[9] & 0xFF) << 8 | (c2[10] & 0xFF) << 16 | (c2[11] & 0xFF) << 24;
        int j11 = k2[16] & 0xFF | (k2[17] & 0xFF) << 8 | (k2[18] & 0xFF) << 16 | (k2[19] & 0xFF) << 24;
        int j12 = k2[20] & 0xFF | (k2[21] & 0xFF) << 8 | (k2[22] & 0xFF) << 16 | (k2[23] & 0xFF) << 24;
        int j13 = k2[24] & 0xFF | (k2[25] & 0xFF) << 8 | (k2[26] & 0xFF) << 16 | (k2[27] & 0xFF) << 24;
        int j14 = k2[28] & 0xFF | (k2[29] & 0xFF) << 8 | (k2[30] & 0xFF) << 16 | (k2[31] & 0xFF) << 24;
        int j15 = c2[12] & 0xFF | (c2[13] & 0xFF) << 8 | (c2[14] & 0xFF) << 16 | (c2[15] & 0xFF) << 24;
        int x0 = j0;
        int x1 = j1;
        int x2 = j2;
        int x3 = j3;
        int x4 = j4;
        int x5 = j5;
        int x6 = j6;
        int x7 = j7;
        int x8 = j8;
        int x9 = j9;
        int x10 = j10;
        int x11 = j11;
        int x12 = j12;
        int x13 = j13;
        int x14 = j14;
        int x15 = j15;
        for (int i2 = 0; i2 < 20; i2 += 2) {
            int u2 = x0 + x12 | 0;
            x4 ^= u2 << 7 | u2 >>> 25;
            u2 = x4 + x0 | 0;
            x8 ^= u2 << 9 | u2 >>> 23;
            u2 = x8 + x4 | 0;
            x12 ^= u2 << 13 | u2 >>> 19;
            u2 = x12 + x8 | 0;
            x0 ^= u2 << 18 | u2 >>> 14;
            u2 = x5 + x1 | 0;
            x9 ^= u2 << 7 | u2 >>> 25;
            u2 = x9 + x5 | 0;
            x13 ^= u2 << 9 | u2 >>> 23;
            u2 = x13 + x9 | 0;
            x1 ^= u2 << 13 | u2 >>> 19;
            u2 = x1 + x13 | 0;
            x5 ^= u2 << 18 | u2 >>> 14;
            u2 = x10 + x6 | 0;
            x14 ^= u2 << 7 | u2 >>> 25;
            u2 = x14 + x10 | 0;
            x2 ^= u2 << 9 | u2 >>> 23;
            u2 = x2 + x14 | 0;
            x6 ^= u2 << 13 | u2 >>> 19;
            u2 = x6 + x2 | 0;
            x10 ^= u2 << 18 | u2 >>> 14;
            u2 = x15 + x11 | 0;
            x3 ^= u2 << 7 | u2 >>> 25;
            u2 = x3 + x15 | 0;
            x7 ^= u2 << 9 | u2 >>> 23;
            u2 = x7 + x3 | 0;
            x11 ^= u2 << 13 | u2 >>> 19;
            u2 = x11 + x7 | 0;
            x15 ^= u2 << 18 | u2 >>> 14;
            u2 = x0 + x3 | 0;
            x1 ^= u2 << 7 | u2 >>> 25;
            u2 = x1 + x0 | 0;
            x2 ^= u2 << 9 | u2 >>> 23;
            u2 = x2 + x1 | 0;
            x3 ^= u2 << 13 | u2 >>> 19;
            u2 = x3 + x2 | 0;
            x0 ^= u2 << 18 | u2 >>> 14;
            u2 = x5 + x4 | 0;
            x6 ^= u2 << 7 | u2 >>> 25;
            u2 = x6 + x5 | 0;
            x7 ^= u2 << 9 | u2 >>> 23;
            u2 = x7 + x6 | 0;
            x4 ^= u2 << 13 | u2 >>> 19;
            u2 = x4 + x7 | 0;
            x5 ^= u2 << 18 | u2 >>> 14;
            u2 = x10 + x9 | 0;
            x11 ^= u2 << 7 | u2 >>> 25;
            u2 = x11 + x10 | 0;
            x8 ^= u2 << 9 | u2 >>> 23;
            u2 = x8 + x11 | 0;
            x9 ^= u2 << 13 | u2 >>> 19;
            u2 = x9 + x8 | 0;
            x10 ^= u2 << 18 | u2 >>> 14;
            u2 = x15 + x14 | 0;
            x12 ^= u2 << 7 | u2 >>> 25;
            u2 = x12 + x15 | 0;
            x13 ^= u2 << 9 | u2 >>> 23;
            u2 = x13 + x12 | 0;
            x14 ^= u2 << 13 | u2 >>> 19;
            u2 = x14 + x13 | 0;
            x15 ^= u2 << 18 | u2 >>> 14;
        }
        x0 = x0 + j0 | 0;
        x1 = x1 + j1 | 0;
        x2 = x2 + j2 | 0;
        x3 = x3 + j3 | 0;
        x4 = x4 + j4 | 0;
        x5 = x5 + j5 | 0;
        x6 = x6 + j6 | 0;
        x7 = x7 + j7 | 0;
        x8 = x8 + j8 | 0;
        x9 = x9 + j9 | 0;
        x10 = x10 + j10 | 0;
        x11 = x11 + j11 | 0;
        x12 = x12 + j12 | 0;
        x13 = x13 + j13 | 0;
        x14 = x14 + j14 | 0;
        x15 = x15 + j15 | 0;
        o2[0] = (byte)(x0 >>> 0 & 0xFF);
        o2[1] = (byte)(x0 >>> 8 & 0xFF);
        o2[2] = (byte)(x0 >>> 16 & 0xFF);
        o2[3] = (byte)(x0 >>> 24 & 0xFF);
        o2[4] = (byte)(x1 >>> 0 & 0xFF);
        o2[5] = (byte)(x1 >>> 8 & 0xFF);
        o2[6] = (byte)(x1 >>> 16 & 0xFF);
        o2[7] = (byte)(x1 >>> 24 & 0xFF);
        o2[8] = (byte)(x2 >>> 0 & 0xFF);
        o2[9] = (byte)(x2 >>> 8 & 0xFF);
        o2[10] = (byte)(x2 >>> 16 & 0xFF);
        o2[11] = (byte)(x2 >>> 24 & 0xFF);
        o2[12] = (byte)(x3 >>> 0 & 0xFF);
        o2[13] = (byte)(x3 >>> 8 & 0xFF);
        o2[14] = (byte)(x3 >>> 16 & 0xFF);
        o2[15] = (byte)(x3 >>> 24 & 0xFF);
        o2[16] = (byte)(x4 >>> 0 & 0xFF);
        o2[17] = (byte)(x4 >>> 8 & 0xFF);
        o2[18] = (byte)(x4 >>> 16 & 0xFF);
        o2[19] = (byte)(x4 >>> 24 & 0xFF);
        o2[20] = (byte)(x5 >>> 0 & 0xFF);
        o2[21] = (byte)(x5 >>> 8 & 0xFF);
        o2[22] = (byte)(x5 >>> 16 & 0xFF);
        o2[23] = (byte)(x5 >>> 24 & 0xFF);
        o2[24] = (byte)(x6 >>> 0 & 0xFF);
        o2[25] = (byte)(x6 >>> 8 & 0xFF);
        o2[26] = (byte)(x6 >>> 16 & 0xFF);
        o2[27] = (byte)(x6 >>> 24 & 0xFF);
        o2[28] = (byte)(x7 >>> 0 & 0xFF);
        o2[29] = (byte)(x7 >>> 8 & 0xFF);
        o2[30] = (byte)(x7 >>> 16 & 0xFF);
        o2[31] = (byte)(x7 >>> 24 & 0xFF);
        o2[32] = (byte)(x8 >>> 0 & 0xFF);
        o2[33] = (byte)(x8 >>> 8 & 0xFF);
        o2[34] = (byte)(x8 >>> 16 & 0xFF);
        o2[35] = (byte)(x8 >>> 24 & 0xFF);
        o2[36] = (byte)(x9 >>> 0 & 0xFF);
        o2[37] = (byte)(x9 >>> 8 & 0xFF);
        o2[38] = (byte)(x9 >>> 16 & 0xFF);
        o2[39] = (byte)(x9 >>> 24 & 0xFF);
        o2[40] = (byte)(x10 >>> 0 & 0xFF);
        o2[41] = (byte)(x10 >>> 8 & 0xFF);
        o2[42] = (byte)(x10 >>> 16 & 0xFF);
        o2[43] = (byte)(x10 >>> 24 & 0xFF);
        o2[44] = (byte)(x11 >>> 0 & 0xFF);
        o2[45] = (byte)(x11 >>> 8 & 0xFF);
        o2[46] = (byte)(x11 >>> 16 & 0xFF);
        o2[47] = (byte)(x11 >>> 24 & 0xFF);
        o2[48] = (byte)(x12 >>> 0 & 0xFF);
        o2[49] = (byte)(x12 >>> 8 & 0xFF);
        o2[50] = (byte)(x12 >>> 16 & 0xFF);
        o2[51] = (byte)(x12 >>> 24 & 0xFF);
        o2[52] = (byte)(x13 >>> 0 & 0xFF);
        o2[53] = (byte)(x13 >>> 8 & 0xFF);
        o2[54] = (byte)(x13 >>> 16 & 0xFF);
        o2[55] = (byte)(x13 >>> 24 & 0xFF);
        o2[56] = (byte)(x14 >>> 0 & 0xFF);
        o2[57] = (byte)(x14 >>> 8 & 0xFF);
        o2[58] = (byte)(x14 >>> 16 & 0xFF);
        o2[59] = (byte)(x14 >>> 24 & 0xFF);
        o2[60] = (byte)(x15 >>> 0 & 0xFF);
        o2[61] = (byte)(x15 >>> 8 & 0xFF);
        o2[62] = (byte)(x15 >>> 16 & 0xFF);
        o2[63] = (byte)(x15 >>> 24 & 0xFF);
    }

    private static void core_hsalsa20(byte[] o2, byte[] p2, byte[] k2, byte[] c2) {
        int j0 = c2[0] & 0xFF | (c2[1] & 0xFF) << 8 | (c2[2] & 0xFF) << 16 | (c2[3] & 0xFF) << 24;
        int j1 = k2[0] & 0xFF | (k2[1] & 0xFF) << 8 | (k2[2] & 0xFF) << 16 | (k2[3] & 0xFF) << 24;
        int j2 = k2[4] & 0xFF | (k2[5] & 0xFF) << 8 | (k2[6] & 0xFF) << 16 | (k2[7] & 0xFF) << 24;
        int j3 = k2[8] & 0xFF | (k2[9] & 0xFF) << 8 | (k2[10] & 0xFF) << 16 | (k2[11] & 0xFF) << 24;
        int j4 = k2[12] & 0xFF | (k2[13] & 0xFF) << 8 | (k2[14] & 0xFF) << 16 | (k2[15] & 0xFF) << 24;
        int j5 = c2[4] & 0xFF | (c2[5] & 0xFF) << 8 | (c2[6] & 0xFF) << 16 | (c2[7] & 0xFF) << 24;
        int j6 = p2[0] & 0xFF | (p2[1] & 0xFF) << 8 | (p2[2] & 0xFF) << 16 | (p2[3] & 0xFF) << 24;
        int j7 = p2[4] & 0xFF | (p2[5] & 0xFF) << 8 | (p2[6] & 0xFF) << 16 | (p2[7] & 0xFF) << 24;
        int j8 = p2[8] & 0xFF | (p2[9] & 0xFF) << 8 | (p2[10] & 0xFF) << 16 | (p2[11] & 0xFF) << 24;
        int j9 = p2[12] & 0xFF | (p2[13] & 0xFF) << 8 | (p2[14] & 0xFF) << 16 | (p2[15] & 0xFF) << 24;
        int j10 = c2[8] & 0xFF | (c2[9] & 0xFF) << 8 | (c2[10] & 0xFF) << 16 | (c2[11] & 0xFF) << 24;
        int j11 = k2[16] & 0xFF | (k2[17] & 0xFF) << 8 | (k2[18] & 0xFF) << 16 | (k2[19] & 0xFF) << 24;
        int j12 = k2[20] & 0xFF | (k2[21] & 0xFF) << 8 | (k2[22] & 0xFF) << 16 | (k2[23] & 0xFF) << 24;
        int j13 = k2[24] & 0xFF | (k2[25] & 0xFF) << 8 | (k2[26] & 0xFF) << 16 | (k2[27] & 0xFF) << 24;
        int j14 = k2[28] & 0xFF | (k2[29] & 0xFF) << 8 | (k2[30] & 0xFF) << 16 | (k2[31] & 0xFF) << 24;
        int j15 = c2[12] & 0xFF | (c2[13] & 0xFF) << 8 | (c2[14] & 0xFF) << 16 | (c2[15] & 0xFF) << 24;
        int x0 = j0;
        int x1 = j1;
        int x2 = j2;
        int x3 = j3;
        int x4 = j4;
        int x5 = j5;
        int x6 = j6;
        int x7 = j7;
        int x8 = j8;
        int x9 = j9;
        int x10 = j10;
        int x11 = j11;
        int x12 = j12;
        int x13 = j13;
        int x14 = j14;
        int x15 = j15;
        for (int i2 = 0; i2 < 20; i2 += 2) {
            int u2 = x0 + x12 | 0;
            x4 ^= u2 << 7 | u2 >>> 25;
            u2 = x4 + x0 | 0;
            x8 ^= u2 << 9 | u2 >>> 23;
            u2 = x8 + x4 | 0;
            x12 ^= u2 << 13 | u2 >>> 19;
            u2 = x12 + x8 | 0;
            x0 ^= u2 << 18 | u2 >>> 14;
            u2 = x5 + x1 | 0;
            x9 ^= u2 << 7 | u2 >>> 25;
            u2 = x9 + x5 | 0;
            x13 ^= u2 << 9 | u2 >>> 23;
            u2 = x13 + x9 | 0;
            x1 ^= u2 << 13 | u2 >>> 19;
            u2 = x1 + x13 | 0;
            x5 ^= u2 << 18 | u2 >>> 14;
            u2 = x10 + x6 | 0;
            x14 ^= u2 << 7 | u2 >>> 25;
            u2 = x14 + x10 | 0;
            x2 ^= u2 << 9 | u2 >>> 23;
            u2 = x2 + x14 | 0;
            x6 ^= u2 << 13 | u2 >>> 19;
            u2 = x6 + x2 | 0;
            x10 ^= u2 << 18 | u2 >>> 14;
            u2 = x15 + x11 | 0;
            x3 ^= u2 << 7 | u2 >>> 25;
            u2 = x3 + x15 | 0;
            x7 ^= u2 << 9 | u2 >>> 23;
            u2 = x7 + x3 | 0;
            x11 ^= u2 << 13 | u2 >>> 19;
            u2 = x11 + x7 | 0;
            x15 ^= u2 << 18 | u2 >>> 14;
            u2 = x0 + x3 | 0;
            x1 ^= u2 << 7 | u2 >>> 25;
            u2 = x1 + x0 | 0;
            x2 ^= u2 << 9 | u2 >>> 23;
            u2 = x2 + x1 | 0;
            x3 ^= u2 << 13 | u2 >>> 19;
            u2 = x3 + x2 | 0;
            x0 ^= u2 << 18 | u2 >>> 14;
            u2 = x5 + x4 | 0;
            x6 ^= u2 << 7 | u2 >>> 25;
            u2 = x6 + x5 | 0;
            x7 ^= u2 << 9 | u2 >>> 23;
            u2 = x7 + x6 | 0;
            x4 ^= u2 << 13 | u2 >>> 19;
            u2 = x4 + x7 | 0;
            x5 ^= u2 << 18 | u2 >>> 14;
            u2 = x10 + x9 | 0;
            x11 ^= u2 << 7 | u2 >>> 25;
            u2 = x11 + x10 | 0;
            x8 ^= u2 << 9 | u2 >>> 23;
            u2 = x8 + x11 | 0;
            x9 ^= u2 << 13 | u2 >>> 19;
            u2 = x9 + x8 | 0;
            x10 ^= u2 << 18 | u2 >>> 14;
            u2 = x15 + x14 | 0;
            x12 ^= u2 << 7 | u2 >>> 25;
            u2 = x12 + x15 | 0;
            x13 ^= u2 << 9 | u2 >>> 23;
            u2 = x13 + x12 | 0;
            x14 ^= u2 << 13 | u2 >>> 19;
            u2 = x14 + x13 | 0;
            x15 ^= u2 << 18 | u2 >>> 14;
        }
        o2[0] = (byte)(x0 >>> 0 & 0xFF);
        o2[1] = (byte)(x0 >>> 8 & 0xFF);
        o2[2] = (byte)(x0 >>> 16 & 0xFF);
        o2[3] = (byte)(x0 >>> 24 & 0xFF);
        o2[4] = (byte)(x5 >>> 0 & 0xFF);
        o2[5] = (byte)(x5 >>> 8 & 0xFF);
        o2[6] = (byte)(x5 >>> 16 & 0xFF);
        o2[7] = (byte)(x5 >>> 24 & 0xFF);
        o2[8] = (byte)(x10 >>> 0 & 0xFF);
        o2[9] = (byte)(x10 >>> 8 & 0xFF);
        o2[10] = (byte)(x10 >>> 16 & 0xFF);
        o2[11] = (byte)(x10 >>> 24 & 0xFF);
        o2[12] = (byte)(x15 >>> 0 & 0xFF);
        o2[13] = (byte)(x15 >>> 8 & 0xFF);
        o2[14] = (byte)(x15 >>> 16 & 0xFF);
        o2[15] = (byte)(x15 >>> 24 & 0xFF);
        o2[16] = (byte)(x6 >>> 0 & 0xFF);
        o2[17] = (byte)(x6 >>> 8 & 0xFF);
        o2[18] = (byte)(x6 >>> 16 & 0xFF);
        o2[19] = (byte)(x6 >>> 24 & 0xFF);
        o2[20] = (byte)(x7 >>> 0 & 0xFF);
        o2[21] = (byte)(x7 >>> 8 & 0xFF);
        o2[22] = (byte)(x7 >>> 16 & 0xFF);
        o2[23] = (byte)(x7 >>> 24 & 0xFF);
        o2[24] = (byte)(x8 >>> 0 & 0xFF);
        o2[25] = (byte)(x8 >>> 8 & 0xFF);
        o2[26] = (byte)(x8 >>> 16 & 0xFF);
        o2[27] = (byte)(x8 >>> 24 & 0xFF);
        o2[28] = (byte)(x9 >>> 0 & 0xFF);
        o2[29] = (byte)(x9 >>> 8 & 0xFF);
        o2[30] = (byte)(x9 >>> 16 & 0xFF);
        o2[31] = (byte)(x9 >>> 24 & 0xFF);
    }

    public static int crypto_core_salsa20(byte[] out, byte[] in2, byte[] k2, byte[] c2) {
        TweetNaclFast.core_salsa20(out, in2, k2, c2);
        return 0;
    }

    public static int crypto_core_hsalsa20(byte[] out, byte[] in2, byte[] k2, byte[] c2) {
        TweetNaclFast.core_hsalsa20(out, in2, k2, c2);
        return 0;
    }

    private static int crypto_stream_salsa20_xor(byte[] c2, int cpos, byte[] m2, int mpos, long b2, byte[] n2, byte[] k2) {
        int i2;
        byte[] z2 = new byte[16];
        byte[] x2 = new byte[64];
        for (i2 = 0; i2 < 16; ++i2) {
            z2[i2] = 0;
        }
        for (i2 = 0; i2 < 8; ++i2) {
            z2[i2] = n2[i2];
        }
        while (b2 >= 64L) {
            TweetNaclFast.crypto_core_salsa20(x2, z2, k2, sigma);
            for (i2 = 0; i2 < 64; ++i2) {
                c2[cpos + i2] = (byte)((m2[mpos + i2] ^ x2[i2]) & 0xFF);
            }
            int u2 = 1;
            for (i2 = 8; i2 < 16; ++i2) {
                u2 = u2 + (z2[i2] & 0xFF) | 0;
                z2[i2] = (byte)(u2 & 0xFF);
                u2 >>>= 8;
            }
            b2 -= 64L;
            cpos += 64;
            mpos += 64;
        }
        if (b2 > 0L) {
            TweetNaclFast.crypto_core_salsa20(x2, z2, k2, sigma);
            i2 = 0;
            while ((long)i2 < b2) {
                c2[cpos + i2] = (byte)((m2[mpos + i2] ^ x2[i2]) & 0xFF);
                ++i2;
            }
        }
        return 0;
    }

    public static int crypto_stream_salsa20(byte[] c2, int cpos, long b2, byte[] n2, byte[] k2) {
        int i2;
        byte[] z2 = new byte[16];
        byte[] x2 = new byte[64];
        for (i2 = 0; i2 < 16; ++i2) {
            z2[i2] = 0;
        }
        for (i2 = 0; i2 < 8; ++i2) {
            z2[i2] = n2[i2];
        }
        while (b2 >= 64L) {
            TweetNaclFast.crypto_core_salsa20(x2, z2, k2, sigma);
            for (i2 = 0; i2 < 64; ++i2) {
                c2[cpos + i2] = x2[i2];
            }
            int u2 = 1;
            for (i2 = 8; i2 < 16; ++i2) {
                u2 = u2 + (z2[i2] & 0xFF) | 0;
                z2[i2] = (byte)(u2 & 0xFF);
                u2 >>>= 8;
            }
            b2 -= 64L;
            cpos += 64;
        }
        if (b2 > 0L) {
            TweetNaclFast.crypto_core_salsa20(x2, z2, k2, sigma);
            i2 = 0;
            while ((long)i2 < b2) {
                c2[cpos + i2] = x2[i2];
                ++i2;
            }
        }
        return 0;
    }

    public static int crypto_stream(byte[] c2, int cpos, long d2, byte[] n2, byte[] k2) {
        byte[] s2 = new byte[32];
        TweetNaclFast.crypto_core_hsalsa20(s2, n2, k2, sigma);
        byte[] sn2 = new byte[8];
        for (int i2 = 0; i2 < 8; ++i2) {
            sn2[i2] = n2[i2 + 16];
        }
        return TweetNaclFast.crypto_stream_salsa20(c2, cpos, d2, sn2, s2);
    }

    public static int crypto_stream_xor(byte[] c2, int cpos, byte[] m2, int mpos, long d2, byte[] n2, byte[] k2) {
        byte[] s2 = new byte[32];
        TweetNaclFast.crypto_core_hsalsa20(s2, n2, k2, sigma);
        byte[] sn2 = new byte[8];
        for (int i2 = 0; i2 < 8; ++i2) {
            sn2[i2] = n2[i2 + 16];
        }
        return TweetNaclFast.crypto_stream_salsa20_xor(c2, cpos, m2, mpos, d2, sn2, s2);
    }

    private static int crypto_onetimeauth(byte[] out, int outpos, byte[] m2, int mpos, int n2, byte[] k2) {
        poly1305 s2 = new poly1305(k2);
        s2.update(m2, mpos, n2);
        s2.finish(out, outpos);
        return 0;
    }

    public static int crypto_onetimeauth(byte[] out, byte[] m2, int n2, byte[] k2) {
        return TweetNaclFast.crypto_onetimeauth(out, 0, m2, 0, n2, k2);
    }

    private static int crypto_onetimeauth_verify(byte[] h2, int hoff, byte[] m2, int moff, int n2, byte[] k2) {
        byte[] x2 = new byte[16];
        TweetNaclFast.crypto_onetimeauth(x2, 0, m2, moff, n2, k2);
        return TweetNaclFast.crypto_verify_16(h2, hoff, x2, 0);
    }

    public static int crypto_onetimeauth_verify(byte[] h2, byte[] m2, int n2, byte[] k2) {
        return TweetNaclFast.crypto_onetimeauth_verify(h2, 0, m2, 0, n2, k2);
    }

    public static int crypto_onetimeauth_verify(byte[] h2, byte[] m2, byte[] k2) {
        return TweetNaclFast.crypto_onetimeauth_verify(h2, m2, m2 != null ? m2.length : 0, k2);
    }

    public static int crypto_secretbox(byte[] c2, byte[] m2, int d2, byte[] n2, byte[] k2) {
        if (d2 < 32) {
            return -1;
        }
        TweetNaclFast.crypto_stream_xor(c2, 0, m2, 0, d2, n2, k2);
        TweetNaclFast.crypto_onetimeauth(c2, 16, c2, 32, d2 - 32, c2);
        return 0;
    }

    public static int crypto_secretbox_open(byte[] m2, byte[] c2, int d2, byte[] n2, byte[] k2) {
        byte[] x2 = new byte[32];
        if (d2 < 32) {
            return -1;
        }
        TweetNaclFast.crypto_stream(x2, 0, 32L, n2, k2);
        if (TweetNaclFast.crypto_onetimeauth_verify(c2, 16, c2, 32, d2 - 32, x2) != 0) {
            return -1;
        }
        TweetNaclFast.crypto_stream_xor(m2, 0, c2, 0, d2, n2, k2);
        return 0;
    }

    private static void set25519(long[] r2, long[] a2) {
        for (int i2 = 0; i2 < 16; ++i2) {
            r2[i2] = a2[i2];
        }
    }

    private static void car25519(long[] o2) {
        long c2 = 1L;
        for (int i2 = 0; i2 < 16; ++i2) {
            long v2 = o2[i2] + c2 + 65535L;
            c2 = v2 >> 16;
            o2[i2] = v2 - c2 * 65536L;
        }
        o2[0] = o2[0] + (c2 - 1L + 37L * (c2 - 1L));
    }

    private static void sel25519(long[] p2, long[] q2, int b2) {
        TweetNaclFast.sel25519(p2, 0, q2, 0, b2);
    }

    private static void sel25519(long[] p2, int poff, long[] q2, int qoff, int b2) {
        long c2 = ~(b2 - 1);
        for (int i2 = 0; i2 < 16; ++i2) {
            long t2 = c2 & (p2[i2 + poff] ^ q2[i2 + qoff]);
            int n2 = i2 + poff;
            p2[n2] = p2[n2] ^ t2;
            int n3 = i2 + qoff;
            q2[n3] = q2[n3] ^ t2;
        }
    }

    private static void pack25519(byte[] o2, long[] n2, int noff) {
        int i2;
        long[] m2 = new long[16];
        long[] t2 = new long[16];
        for (i2 = 0; i2 < 16; ++i2) {
            t2[i2] = n2[i2 + noff];
        }
        TweetNaclFast.car25519(t2);
        TweetNaclFast.car25519(t2);
        TweetNaclFast.car25519(t2);
        for (int j2 = 0; j2 < 2; ++j2) {
            m2[0] = t2[0] - 65517L;
            for (i2 = 1; i2 < 15; ++i2) {
                m2[i2] = t2[i2] - 65535L - (m2[i2 - 1] >> 16 & 1L);
                int n3 = i2 - 1;
                m2[n3] = m2[n3] & 0xFFFFL;
            }
            m2[15] = t2[15] - 32767L - (m2[14] >> 16 & 1L);
            int b2 = (int)(m2[15] >> 16 & 1L);
            m2[14] = m2[14] & 0xFFFFL;
            TweetNaclFast.sel25519(t2, 0, m2, 0, 1 - b2);
        }
        for (i2 = 0; i2 < 16; ++i2) {
            o2[2 * i2] = (byte)(t2[i2] & 0xFFL);
            o2[2 * i2 + 1] = (byte)(t2[i2] >> 8);
        }
    }

    private static int neq25519(long[] a2, long[] b2) {
        return TweetNaclFast.neq25519(a2, 0, b2, 0);
    }

    private static int neq25519(long[] a2, int aoff, long[] b2, int boff) {
        byte[] c2 = new byte[32];
        byte[] d2 = new byte[32];
        TweetNaclFast.pack25519(c2, a2, aoff);
        TweetNaclFast.pack25519(d2, b2, boff);
        return TweetNaclFast.crypto_verify_32(c2, 0, d2, 0);
    }

    private static byte par25519(long[] a2) {
        return TweetNaclFast.par25519(a2, 0);
    }

    private static byte par25519(long[] a2, int aoff) {
        byte[] d2 = new byte[32];
        TweetNaclFast.pack25519(d2, a2, aoff);
        return (byte)(d2[0] & 1);
    }

    private static void unpack25519(long[] o2, byte[] n2) {
        for (int i2 = 0; i2 < 16; ++i2) {
            o2[i2] = (long)(n2[2 * i2] & 0xFF) + (long)(n2[2 * i2 + 1] << 8 & 0xFFFF);
        }
        o2[15] = o2[15] & 0x7FFFL;
    }

    private static void A(long[] o2, long[] a2, long[] b2) {
        TweetNaclFast.A(o2, 0, a2, 0, b2, 0);
    }

    private static void A(long[] o2, int ooff, long[] a2, int aoff, long[] b2, int boff) {
        for (int i2 = 0; i2 < 16; ++i2) {
            o2[i2 + ooff] = a2[i2 + aoff] + b2[i2 + boff];
        }
    }

    private static void Z(long[] o2, long[] a2, long[] b2) {
        TweetNaclFast.Z(o2, 0, a2, 0, b2, 0);
    }

    private static void Z(long[] o2, int ooff, long[] a2, int aoff, long[] b2, int boff) {
        for (int i2 = 0; i2 < 16; ++i2) {
            o2[i2 + ooff] = a2[i2 + aoff] - b2[i2 + boff];
        }
    }

    private static void M(long[] o2, long[] a2, long[] b2) {
        TweetNaclFast.M(o2, 0, a2, 0, b2, 0);
    }

    private static void M(long[] o2, int ooff, long[] a2, int aoff, long[] b2, int boff) {
        long t0 = 0L;
        long t1 = 0L;
        long t2 = 0L;
        long t3 = 0L;
        long t4 = 0L;
        long t5 = 0L;
        long t6 = 0L;
        long t7 = 0L;
        long t8 = 0L;
        long t9 = 0L;
        long t10 = 0L;
        long t11 = 0L;
        long t12 = 0L;
        long t13 = 0L;
        long t14 = 0L;
        long t15 = 0L;
        long t16 = 0L;
        long t17 = 0L;
        long t18 = 0L;
        long t19 = 0L;
        long t20 = 0L;
        long t21 = 0L;
        long t22 = 0L;
        long t23 = 0L;
        long t24 = 0L;
        long t25 = 0L;
        long t26 = 0L;
        long t27 = 0L;
        long t28 = 0L;
        long t29 = 0L;
        long t30 = 0L;
        long b0 = b2[0 + boff];
        long b1 = b2[1 + boff];
        long b22 = b2[2 + boff];
        long b3 = b2[3 + boff];
        long b4 = b2[4 + boff];
        long b5 = b2[5 + boff];
        long b6 = b2[6 + boff];
        long b7 = b2[7 + boff];
        long b8 = b2[8 + boff];
        long b9 = b2[9 + boff];
        long b10 = b2[10 + boff];
        long b11 = b2[11 + boff];
        long b12 = b2[12 + boff];
        long b13 = b2[13 + boff];
        long b14 = b2[14 + boff];
        long b15 = b2[15 + boff];
        long v2 = a2[0 + aoff];
        t0 += v2 * b0;
        t1 += v2 * b1;
        t2 += v2 * b22;
        t3 += v2 * b3;
        t4 += v2 * b4;
        t5 += v2 * b5;
        t6 += v2 * b6;
        t7 += v2 * b7;
        t8 += v2 * b8;
        t9 += v2 * b9;
        t10 += v2 * b10;
        t11 += v2 * b11;
        t12 += v2 * b12;
        t13 += v2 * b13;
        t14 += v2 * b14;
        t15 += v2 * b15;
        v2 = a2[1 + aoff];
        t1 += v2 * b0;
        t2 += v2 * b1;
        t3 += v2 * b22;
        t4 += v2 * b3;
        t5 += v2 * b4;
        t6 += v2 * b5;
        t7 += v2 * b6;
        t8 += v2 * b7;
        t9 += v2 * b8;
        t10 += v2 * b9;
        t11 += v2 * b10;
        t12 += v2 * b11;
        t13 += v2 * b12;
        t14 += v2 * b13;
        t15 += v2 * b14;
        t16 += v2 * b15;
        v2 = a2[2 + aoff];
        t2 += v2 * b0;
        t3 += v2 * b1;
        t4 += v2 * b22;
        t5 += v2 * b3;
        t6 += v2 * b4;
        t7 += v2 * b5;
        t8 += v2 * b6;
        t9 += v2 * b7;
        t10 += v2 * b8;
        t11 += v2 * b9;
        t12 += v2 * b10;
        t13 += v2 * b11;
        t14 += v2 * b12;
        t15 += v2 * b13;
        t16 += v2 * b14;
        t17 += v2 * b15;
        v2 = a2[3 + aoff];
        t3 += v2 * b0;
        t4 += v2 * b1;
        t5 += v2 * b22;
        t6 += v2 * b3;
        t7 += v2 * b4;
        t8 += v2 * b5;
        t9 += v2 * b6;
        t10 += v2 * b7;
        t11 += v2 * b8;
        t12 += v2 * b9;
        t13 += v2 * b10;
        t14 += v2 * b11;
        t15 += v2 * b12;
        t16 += v2 * b13;
        t17 += v2 * b14;
        t18 += v2 * b15;
        v2 = a2[4 + aoff];
        t4 += v2 * b0;
        t5 += v2 * b1;
        t6 += v2 * b22;
        t7 += v2 * b3;
        t8 += v2 * b4;
        t9 += v2 * b5;
        t10 += v2 * b6;
        t11 += v2 * b7;
        t12 += v2 * b8;
        t13 += v2 * b9;
        t14 += v2 * b10;
        t15 += v2 * b11;
        t16 += v2 * b12;
        t17 += v2 * b13;
        t18 += v2 * b14;
        t19 += v2 * b15;
        v2 = a2[5 + aoff];
        t5 += v2 * b0;
        t6 += v2 * b1;
        t7 += v2 * b22;
        t8 += v2 * b3;
        t9 += v2 * b4;
        t10 += v2 * b5;
        t11 += v2 * b6;
        t12 += v2 * b7;
        t13 += v2 * b8;
        t14 += v2 * b9;
        t15 += v2 * b10;
        t16 += v2 * b11;
        t17 += v2 * b12;
        t18 += v2 * b13;
        t19 += v2 * b14;
        t20 += v2 * b15;
        v2 = a2[6 + aoff];
        t6 += v2 * b0;
        t7 += v2 * b1;
        t8 += v2 * b22;
        t9 += v2 * b3;
        t10 += v2 * b4;
        t11 += v2 * b5;
        t12 += v2 * b6;
        t13 += v2 * b7;
        t14 += v2 * b8;
        t15 += v2 * b9;
        t16 += v2 * b10;
        t17 += v2 * b11;
        t18 += v2 * b12;
        t19 += v2 * b13;
        t20 += v2 * b14;
        t21 += v2 * b15;
        v2 = a2[7 + aoff];
        t7 += v2 * b0;
        t8 += v2 * b1;
        t9 += v2 * b22;
        t10 += v2 * b3;
        t11 += v2 * b4;
        t12 += v2 * b5;
        t13 += v2 * b6;
        t14 += v2 * b7;
        t15 += v2 * b8;
        t16 += v2 * b9;
        t17 += v2 * b10;
        t18 += v2 * b11;
        t19 += v2 * b12;
        t20 += v2 * b13;
        t21 += v2 * b14;
        t22 += v2 * b15;
        v2 = a2[8 + aoff];
        t8 += v2 * b0;
        t9 += v2 * b1;
        t10 += v2 * b22;
        t11 += v2 * b3;
        t12 += v2 * b4;
        t13 += v2 * b5;
        t14 += v2 * b6;
        t15 += v2 * b7;
        t16 += v2 * b8;
        t17 += v2 * b9;
        t18 += v2 * b10;
        t19 += v2 * b11;
        t20 += v2 * b12;
        t21 += v2 * b13;
        t22 += v2 * b14;
        t23 += v2 * b15;
        v2 = a2[9 + aoff];
        t9 += v2 * b0;
        t10 += v2 * b1;
        t11 += v2 * b22;
        t12 += v2 * b3;
        t13 += v2 * b4;
        t14 += v2 * b5;
        t15 += v2 * b6;
        t16 += v2 * b7;
        t17 += v2 * b8;
        t18 += v2 * b9;
        t19 += v2 * b10;
        t20 += v2 * b11;
        t21 += v2 * b12;
        t22 += v2 * b13;
        t23 += v2 * b14;
        t24 += v2 * b15;
        v2 = a2[10 + aoff];
        t10 += v2 * b0;
        t11 += v2 * b1;
        t12 += v2 * b22;
        t13 += v2 * b3;
        t14 += v2 * b4;
        t15 += v2 * b5;
        t16 += v2 * b6;
        t17 += v2 * b7;
        t18 += v2 * b8;
        t19 += v2 * b9;
        t20 += v2 * b10;
        t21 += v2 * b11;
        t22 += v2 * b12;
        t23 += v2 * b13;
        t24 += v2 * b14;
        t25 += v2 * b15;
        v2 = a2[11 + aoff];
        t11 += v2 * b0;
        t12 += v2 * b1;
        t13 += v2 * b22;
        t14 += v2 * b3;
        t15 += v2 * b4;
        t16 += v2 * b5;
        t17 += v2 * b6;
        t18 += v2 * b7;
        t19 += v2 * b8;
        t20 += v2 * b9;
        t21 += v2 * b10;
        t22 += v2 * b11;
        t23 += v2 * b12;
        t24 += v2 * b13;
        t25 += v2 * b14;
        t26 += v2 * b15;
        v2 = a2[12 + aoff];
        t12 += v2 * b0;
        t13 += v2 * b1;
        t14 += v2 * b22;
        t15 += v2 * b3;
        t16 += v2 * b4;
        t17 += v2 * b5;
        t18 += v2 * b6;
        t19 += v2 * b7;
        t20 += v2 * b8;
        t21 += v2 * b9;
        t22 += v2 * b10;
        t23 += v2 * b11;
        t24 += v2 * b12;
        t25 += v2 * b13;
        t26 += v2 * b14;
        t27 += v2 * b15;
        v2 = a2[13 + aoff];
        t13 += v2 * b0;
        t14 += v2 * b1;
        t15 += v2 * b22;
        t16 += v2 * b3;
        t17 += v2 * b4;
        t18 += v2 * b5;
        t19 += v2 * b6;
        t20 += v2 * b7;
        t21 += v2 * b8;
        t22 += v2 * b9;
        t23 += v2 * b10;
        t24 += v2 * b11;
        t25 += v2 * b12;
        t26 += v2 * b13;
        t27 += v2 * b14;
        t28 += v2 * b15;
        v2 = a2[14 + aoff];
        t14 += v2 * b0;
        t15 += v2 * b1;
        t16 += v2 * b22;
        t17 += v2 * b3;
        t18 += v2 * b4;
        t19 += v2 * b5;
        t20 += v2 * b6;
        t21 += v2 * b7;
        t22 += v2 * b8;
        t23 += v2 * b9;
        t24 += v2 * b10;
        t25 += v2 * b11;
        t26 += v2 * b12;
        t27 += v2 * b13;
        t28 += v2 * b14;
        t29 += v2 * b15;
        v2 = a2[15 + aoff];
        t15 += v2 * b0;
        t16 += v2 * b1;
        t17 += v2 * b22;
        t18 += v2 * b3;
        t19 += v2 * b4;
        t20 += v2 * b5;
        t21 += v2 * b6;
        t22 += v2 * b7;
        t23 += v2 * b8;
        t24 += v2 * b9;
        t25 += v2 * b10;
        t26 += v2 * b11;
        t27 += v2 * b12;
        t28 += v2 * b13;
        t29 += v2 * b14;
        t30 += v2 * b15;
        t0 += 38L * t16;
        t1 += 38L * t17;
        t2 += 38L * t18;
        t3 += 38L * t19;
        t4 += 38L * t20;
        t5 += 38L * t21;
        t6 += 38L * t22;
        t7 += 38L * t23;
        t8 += 38L * t24;
        t9 += 38L * t25;
        t10 += 38L * t26;
        t11 += 38L * t27;
        t12 += 38L * t28;
        t13 += 38L * t29;
        t14 += 38L * t30;
        long c2 = 1L;
        v2 = t0 + c2 + 65535L;
        c2 = v2 >> 16;
        t0 = v2 - c2 * 65536L;
        v2 = t1 + c2 + 65535L;
        c2 = v2 >> 16;
        t1 = v2 - c2 * 65536L;
        v2 = t2 + c2 + 65535L;
        c2 = v2 >> 16;
        t2 = v2 - c2 * 65536L;
        v2 = t3 + c2 + 65535L;
        c2 = v2 >> 16;
        t3 = v2 - c2 * 65536L;
        v2 = t4 + c2 + 65535L;
        c2 = v2 >> 16;
        t4 = v2 - c2 * 65536L;
        v2 = t5 + c2 + 65535L;
        c2 = v2 >> 16;
        t5 = v2 - c2 * 65536L;
        v2 = t6 + c2 + 65535L;
        c2 = v2 >> 16;
        t6 = v2 - c2 * 65536L;
        v2 = t7 + c2 + 65535L;
        c2 = v2 >> 16;
        t7 = v2 - c2 * 65536L;
        v2 = t8 + c2 + 65535L;
        c2 = v2 >> 16;
        t8 = v2 - c2 * 65536L;
        v2 = t9 + c2 + 65535L;
        c2 = v2 >> 16;
        t9 = v2 - c2 * 65536L;
        v2 = t10 + c2 + 65535L;
        c2 = v2 >> 16;
        t10 = v2 - c2 * 65536L;
        v2 = t11 + c2 + 65535L;
        c2 = v2 >> 16;
        t11 = v2 - c2 * 65536L;
        v2 = t12 + c2 + 65535L;
        c2 = v2 >> 16;
        t12 = v2 - c2 * 65536L;
        v2 = t13 + c2 + 65535L;
        c2 = v2 >> 16;
        t13 = v2 - c2 * 65536L;
        v2 = t14 + c2 + 65535L;
        c2 = v2 >> 16;
        t14 = v2 - c2 * 65536L;
        v2 = t15 + c2 + 65535L;
        c2 = v2 >> 16;
        t15 = v2 - c2 * 65536L;
        t0 += c2 - 1L + 37L * (c2 - 1L);
        c2 = 1L;
        v2 = t0 + c2 + 65535L;
        c2 = v2 >> 16;
        t0 = v2 - c2 * 65536L;
        v2 = t1 + c2 + 65535L;
        c2 = v2 >> 16;
        t1 = v2 - c2 * 65536L;
        v2 = t2 + c2 + 65535L;
        c2 = v2 >> 16;
        t2 = v2 - c2 * 65536L;
        v2 = t3 + c2 + 65535L;
        c2 = v2 >> 16;
        t3 = v2 - c2 * 65536L;
        v2 = t4 + c2 + 65535L;
        c2 = v2 >> 16;
        t4 = v2 - c2 * 65536L;
        v2 = t5 + c2 + 65535L;
        c2 = v2 >> 16;
        t5 = v2 - c2 * 65536L;
        v2 = t6 + c2 + 65535L;
        c2 = v2 >> 16;
        t6 = v2 - c2 * 65536L;
        v2 = t7 + c2 + 65535L;
        c2 = v2 >> 16;
        t7 = v2 - c2 * 65536L;
        v2 = t8 + c2 + 65535L;
        c2 = v2 >> 16;
        t8 = v2 - c2 * 65536L;
        v2 = t9 + c2 + 65535L;
        c2 = v2 >> 16;
        t9 = v2 - c2 * 65536L;
        v2 = t10 + c2 + 65535L;
        c2 = v2 >> 16;
        t10 = v2 - c2 * 65536L;
        v2 = t11 + c2 + 65535L;
        c2 = v2 >> 16;
        t11 = v2 - c2 * 65536L;
        v2 = t12 + c2 + 65535L;
        c2 = v2 >> 16;
        t12 = v2 - c2 * 65536L;
        v2 = t13 + c2 + 65535L;
        c2 = v2 >> 16;
        t13 = v2 - c2 * 65536L;
        v2 = t14 + c2 + 65535L;
        c2 = v2 >> 16;
        t14 = v2 - c2 * 65536L;
        v2 = t15 + c2 + 65535L;
        c2 = v2 >> 16;
        t15 = v2 - c2 * 65536L;
        o2[0 + ooff] = t0 += c2 - 1L + 37L * (c2 - 1L);
        o2[1 + ooff] = t1;
        o2[2 + ooff] = t2;
        o2[3 + ooff] = t3;
        o2[4 + ooff] = t4;
        o2[5 + ooff] = t5;
        o2[6 + ooff] = t6;
        o2[7 + ooff] = t7;
        o2[8 + ooff] = t8;
        o2[9 + ooff] = t9;
        o2[10 + ooff] = t10;
        o2[11 + ooff] = t11;
        o2[12 + ooff] = t12;
        o2[13 + ooff] = t13;
        o2[14 + ooff] = t14;
        o2[15 + ooff] = t15;
    }

    private static void S(long[] o2, long[] a2) {
        TweetNaclFast.S(o2, 0, a2, 0);
    }

    private static void S(long[] o2, int ooff, long[] a2, int aoff) {
        TweetNaclFast.M(o2, ooff, a2, aoff, a2, aoff);
    }

    private static void inv25519(long[] o2, int ooff, long[] i2, int ioff) {
        int a2;
        long[] c2 = new long[16];
        for (a2 = 0; a2 < 16; ++a2) {
            c2[a2] = i2[a2 + ioff];
        }
        for (a2 = 253; a2 >= 0; --a2) {
            TweetNaclFast.S(c2, 0, c2, 0);
            if (a2 == 2 || a2 == 4) continue;
            TweetNaclFast.M(c2, 0, c2, 0, i2, ioff);
        }
        for (a2 = 0; a2 < 16; ++a2) {
            o2[a2 + ooff] = c2[a2];
        }
    }

    private static void pow2523(long[] o2, long[] i2) {
        int a2;
        long[] c2 = new long[16];
        for (a2 = 0; a2 < 16; ++a2) {
            c2[a2] = i2[a2];
        }
        for (a2 = 250; a2 >= 0; --a2) {
            TweetNaclFast.S(c2, 0, c2, 0);
            if (a2 == 1) continue;
            TweetNaclFast.M(c2, 0, c2, 0, i2, 0);
        }
        for (a2 = 0; a2 < 16; ++a2) {
            o2[a2] = c2[a2];
        }
    }

    public static int crypto_scalarmult(byte[] q2, byte[] n2, byte[] p2) {
        int i2;
        byte[] z2 = new byte[32];
        long[] x2 = new long[80];
        long[] a2 = new long[16];
        long[] b2 = new long[16];
        long[] c2 = new long[16];
        long[] d2 = new long[16];
        long[] e2 = new long[16];
        long[] f2 = new long[16];
        for (i2 = 0; i2 < 31; ++i2) {
            z2[i2] = n2[i2];
        }
        z2[31] = (byte)((n2[31] & 0x7F | 0x40) & 0xFF);
        z2[0] = (byte)(z2[0] & 0xF8);
        TweetNaclFast.unpack25519(x2, p2);
        for (i2 = 0; i2 < 16; ++i2) {
            b2[i2] = x2[i2];
            c2[i2] = 0L;
            a2[i2] = 0L;
            d2[i2] = 0L;
        }
        d2[0] = 1L;
        a2[0] = 1L;
        for (i2 = 254; i2 >= 0; --i2) {
            int r2 = z2[i2 >>> 3] >>> (i2 & 7) & 1;
            TweetNaclFast.sel25519(a2, b2, r2);
            TweetNaclFast.sel25519(c2, d2, r2);
            TweetNaclFast.A(e2, a2, c2);
            TweetNaclFast.Z(a2, a2, c2);
            TweetNaclFast.A(c2, b2, d2);
            TweetNaclFast.Z(b2, b2, d2);
            TweetNaclFast.S(d2, e2);
            TweetNaclFast.S(f2, a2);
            TweetNaclFast.M(a2, c2, a2);
            TweetNaclFast.M(c2, b2, e2);
            TweetNaclFast.A(e2, a2, c2);
            TweetNaclFast.Z(a2, a2, c2);
            TweetNaclFast.S(b2, a2);
            TweetNaclFast.Z(c2, d2, f2);
            TweetNaclFast.M(a2, c2, _121665);
            TweetNaclFast.A(a2, a2, d2);
            TweetNaclFast.M(c2, c2, a2);
            TweetNaclFast.M(a2, d2, f2);
            TweetNaclFast.M(d2, b2, x2);
            TweetNaclFast.S(b2, e2);
            TweetNaclFast.sel25519(a2, b2, r2);
            TweetNaclFast.sel25519(c2, d2, r2);
        }
        for (i2 = 0; i2 < 16; ++i2) {
            x2[i2 + 16] = a2[i2];
            x2[i2 + 32] = c2[i2];
            x2[i2 + 48] = b2[i2];
            x2[i2 + 64] = d2[i2];
        }
        TweetNaclFast.inv25519(x2, 32, x2, 32);
        TweetNaclFast.M(x2, 16, x2, 16, x2, 32);
        TweetNaclFast.pack25519(q2, x2, 16);
        return 0;
    }

    public static int crypto_scalarmult_base(byte[] q2, byte[] n2) {
        return TweetNaclFast.crypto_scalarmult(q2, n2, _9);
    }

    public static int crypto_box_keypair(byte[] y2, byte[] x2) {
        TweetNaclFast.randombytes(x2, 32);
        return TweetNaclFast.crypto_scalarmult_base(y2, x2);
    }

    public static int crypto_box_beforenm(byte[] k2, byte[] y2, byte[] x2) {
        byte[] s2 = new byte[32];
        TweetNaclFast.crypto_scalarmult(s2, x2, y2);
        return TweetNaclFast.crypto_core_hsalsa20(k2, _0, s2, sigma);
    }

    public static int crypto_box_afternm(byte[] c2, byte[] m2, int d2, byte[] n2, byte[] k2) {
        return TweetNaclFast.crypto_secretbox(c2, m2, d2, n2, k2);
    }

    public static int crypto_box_open_afternm(byte[] m2, byte[] c2, int d2, byte[] n2, byte[] k2) {
        return TweetNaclFast.crypto_secretbox_open(m2, c2, d2, n2, k2);
    }

    public static int crypto_box(byte[] c2, byte[] m2, int d2, byte[] n2, byte[] y2, byte[] x2) {
        byte[] k2 = new byte[32];
        TweetNaclFast.crypto_box_beforenm(k2, y2, x2);
        return TweetNaclFast.crypto_box_afternm(c2, m2, d2, n2, k2);
    }

    public static int crypto_box_open(byte[] m2, byte[] c2, int d2, byte[] n2, byte[] y2, byte[] x2) {
        byte[] k2 = new byte[32];
        TweetNaclFast.crypto_box_beforenm(k2, y2, x2);
        return TweetNaclFast.crypto_box_open_afternm(m2, c2, d2, n2, k2);
    }

    private static int crypto_hashblocks_hl(int[] hh2, int[] hl2, byte[] m2, int moff, int n2) {
        int[] wh2 = new int[16];
        int[] wl2 = new int[16];
        int ah0 = hh2[0];
        int ah1 = hh2[1];
        int ah2 = hh2[2];
        int ah3 = hh2[3];
        int ah4 = hh2[4];
        int ah5 = hh2[5];
        int ah6 = hh2[6];
        int ah7 = hh2[7];
        int al0 = hl2[0];
        int al1 = hl2[1];
        int al2 = hl2[2];
        int al3 = hl2[3];
        int al4 = hl2[4];
        int al5 = hl2[5];
        int al6 = hl2[6];
        int al7 = hl2[7];
        int pos = 0;
        while (n2 >= 128) {
            int d2;
            int c2;
            int b2;
            int a2;
            int l2;
            int h2;
            int j2;
            int i2;
            for (i2 = 0; i2 < 16; ++i2) {
                j2 = 8 * i2 + pos;
                wh2[i2] = (m2[j2 + 0 + moff] & 0xFF) << 24 | (m2[j2 + 1 + moff] & 0xFF) << 16 | (m2[j2 + 2 + moff] & 0xFF) << 8 | (m2[j2 + 3 + moff] & 0xFF) << 0;
                wl2[i2] = (m2[j2 + 4 + moff] & 0xFF) << 24 | (m2[j2 + 5 + moff] & 0xFF) << 16 | (m2[j2 + 6 + moff] & 0xFF) << 8 | (m2[j2 + 7 + moff] & 0xFF) << 0;
            }
            for (i2 = 0; i2 < 80; ++i2) {
                int bh0 = ah0;
                int bh1 = ah1;
                int bh2 = ah2;
                int bh3 = ah3;
                int bh4 = ah4;
                int bh5 = ah5;
                int bh6 = ah6;
                int bh7 = ah7;
                int bl0 = al0;
                int bl1 = al1;
                int bl2 = al2;
                int bl3 = al3;
                int bl4 = al4;
                int bl5 = al5;
                int bl6 = al6;
                int bl7 = al7;
                h2 = ah7;
                l2 = al7;
                a2 = l2 & 0xFFFF;
                b2 = l2 >>> 16;
                c2 = h2 & 0xFFFF;
                d2 = h2 >>> 16;
                h2 = (ah4 >>> 14 | al4 << 18) ^ (ah4 >>> 18 | al4 << 14) ^ (al4 >>> 9 | ah4 << 23);
                l2 = (al4 >>> 14 | ah4 << 18) ^ (al4 >>> 18 | ah4 << 14) ^ (ah4 >>> 9 | al4 << 23);
                a2 += l2 & 0xFFFF;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                h2 = ah4 & ah5 ^ ~ah4 & ah6;
                l2 = al4 & al5 ^ ~al4 & al6;
                a2 += l2 & 0xFFFF;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                h2 = (int)(K[i2] >>> 32 & 0xFFFFFFFFFFFFFFFFL);
                l2 = (int)(K[i2] >>> 0 & 0xFFFFFFFFFFFFFFFFL);
                a2 += l2 & 0xFFFF;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                h2 = wh2[i2 % 16];
                l2 = wl2[i2 % 16];
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                int th2 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
                int tl = a2 & 0xFFFF | b2 << 16;
                h2 = th2;
                l2 = tl;
                a2 = l2 & 0xFFFF;
                b2 = l2 >>> 16;
                c2 = h2 & 0xFFFF;
                d2 = h2 >>> 16;
                h2 = (ah0 >>> 28 | al0 << 4) ^ (al0 >>> 2 | ah0 << 30) ^ (al0 >>> 7 | ah0 << 25);
                l2 = (al0 >>> 28 | ah0 << 4) ^ (ah0 >>> 2 | al0 << 30) ^ (ah0 >>> 7 | al0 << 25);
                a2 += l2 & 0xFFFF;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                h2 = ah0 & ah1 ^ ah0 & ah2 ^ ah1 & ah2;
                l2 = al0 & al1 ^ al0 & al2 ^ al1 & al2;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                bh7 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
                bl7 = a2 & 0xFFFF | b2 << 16;
                h2 = bh3;
                l2 = bl3;
                a2 = l2 & 0xFFFF;
                b2 = l2 >>> 16;
                c2 = h2 & 0xFFFF;
                d2 = h2 >>> 16;
                h2 = th2;
                l2 = tl;
                b2 += l2 >>> 16;
                c2 += h2 & 0xFFFF;
                d2 += h2 >>> 16;
                bh3 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
                bl3 = a2 & 0xFFFF | b2 << 16;
                ah1 = bh0;
                ah2 = bh1;
                ah3 = bh2;
                ah4 = bh3;
                ah5 = bh4;
                ah6 = bh5;
                ah7 = bh6;
                ah0 = bh7;
                al1 = bl0;
                al2 = bl1;
                al3 = bl2;
                al4 = bl3;
                al5 = bl4;
                al6 = bl5;
                al7 = bl6;
                al0 = bl7;
                if (i2 % 16 != 15) continue;
                for (j2 = 0; j2 < 16; ++j2) {
                    h2 = wh2[j2];
                    l2 = wl2[j2];
                    a2 = l2 & 0xFFFF;
                    b2 = l2 >>> 16;
                    c2 = h2 & 0xFFFF;
                    d2 = h2 >>> 16;
                    h2 = wh2[(j2 + 9) % 16];
                    l2 = wl2[(j2 + 9) % 16];
                    a2 += l2 & 0xFFFF;
                    b2 += l2 >>> 16;
                    c2 += h2 & 0xFFFF;
                    d2 += h2 >>> 16;
                    th2 = wh2[(j2 + 1) % 16];
                    tl = wl2[(j2 + 1) % 16];
                    h2 = (th2 >>> 1 | tl << 31) ^ (th2 >>> 8 | tl << 24) ^ th2 >>> 7;
                    l2 = (tl >>> 1 | th2 << 31) ^ (tl >>> 8 | th2 << 24) ^ (tl >>> 7 | th2 << 25);
                    a2 += l2 & 0xFFFF;
                    b2 += l2 >>> 16;
                    c2 += h2 & 0xFFFF;
                    d2 += h2 >>> 16;
                    th2 = wh2[(j2 + 14) % 16];
                    tl = wl2[(j2 + 14) % 16];
                    h2 = (th2 >>> 19 | tl << 13) ^ (tl >>> 29 | th2 << 3) ^ th2 >>> 6;
                    l2 = (tl >>> 19 | th2 << 13) ^ (th2 >>> 29 | tl << 3) ^ (tl >>> 6 | th2 << 26);
                    b2 += l2 >>> 16;
                    c2 += h2 & 0xFFFF;
                    d2 += h2 >>> 16;
                    wh2[j2] = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
                    wl2[j2] = a2 & 0xFFFF | b2 << 16;
                }
            }
            h2 = ah0;
            l2 = al0;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[0];
            l2 = hl2[0];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[0] = ah0 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[0] = al0 = a2 & 0xFFFF | b2 << 16;
            h2 = ah1;
            l2 = al1;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[1];
            l2 = hl2[1];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[1] = ah1 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[1] = al1 = a2 & 0xFFFF | b2 << 16;
            h2 = ah2;
            l2 = al2;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[2];
            l2 = hl2[2];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[2] = ah2 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[2] = al2 = a2 & 0xFFFF | b2 << 16;
            h2 = ah3;
            l2 = al3;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[3];
            l2 = hl2[3];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[3] = ah3 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[3] = al3 = a2 & 0xFFFF | b2 << 16;
            h2 = ah4;
            l2 = al4;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[4];
            l2 = hl2[4];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[4] = ah4 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[4] = al4 = a2 & 0xFFFF | b2 << 16;
            h2 = ah5;
            l2 = al5;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[5];
            l2 = hl2[5];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[5] = ah5 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[5] = al5 = a2 & 0xFFFF | b2 << 16;
            h2 = ah6;
            l2 = al6;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[6];
            l2 = hl2[6];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[6] = ah6 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[6] = al6 = a2 & 0xFFFF | b2 << 16;
            h2 = ah7;
            l2 = al7;
            a2 = l2 & 0xFFFF;
            b2 = l2 >>> 16;
            c2 = h2 & 0xFFFF;
            d2 = h2 >>> 16;
            h2 = hh2[7];
            l2 = hl2[7];
            b2 += l2 >>> 16;
            c2 += h2 & 0xFFFF;
            d2 += h2 >>> 16;
            hh2[7] = ah7 = c2 & 0xFFFF | (d2 += (c2 += (b2 += (a2 += l2 & 0xFFFF) >>> 16) >>> 16) >>> 16) << 16;
            hl2[7] = al7 = a2 & 0xFFFF | b2 << 16;
            pos += 128;
            n2 -= 128;
        }
        return n2;
    }

    public static int crypto_hash(byte[] out, byte[] m2, int moff, int n2) {
        int i2;
        int[] hh2 = new int[8];
        int[] hl2 = new int[8];
        byte[] x2 = new byte[256];
        int b2 = n2;
        hh2[0] = 1779033703;
        hh2[1] = -1150833019;
        hh2[2] = 1013904242;
        hh2[3] = -1521486534;
        hh2[4] = 1359893119;
        hh2[5] = -1694144372;
        hh2[6] = 528734635;
        hh2[7] = 1541459225;
        hl2[0] = -205731576;
        hl2[1] = -2067093701;
        hl2[2] = -23791573;
        hl2[3] = 1595750129;
        hl2[4] = -1377402159;
        hl2[5] = 725511199;
        hl2[6] = -79577749;
        hl2[7] = 327033209;
        if (n2 >= 128) {
            TweetNaclFast.crypto_hashblocks_hl(hh2, hl2, m2, moff, n2);
            n2 %= 128;
        }
        for (i2 = 0; i2 < n2; ++i2) {
            x2[i2] = m2[b2 - n2 + i2 + moff];
        }
        x2[n2] = -128;
        n2 = 256 - 128 * (n2 < 112 ? 1 : 0);
        x2[n2 - 9] = 0;
        TweetNaclFast.ts64(x2, n2 - 8, b2 << 3);
        TweetNaclFast.crypto_hashblocks_hl(hh2, hl2, x2, 0, n2);
        for (i2 = 0; i2 < 8; ++i2) {
            long u2 = hh2[i2];
            u2 <<= 32;
            TweetNaclFast.ts64(out, 8 * i2, u2 |= (long)hl2[i2] & 0xFFFFFFFFL);
        }
        return 0;
    }

    public static int crypto_hash(byte[] out, byte[] m2) {
        return TweetNaclFast.crypto_hash(out, m2, 0, m2 != null ? m2.length : 0);
    }

    private static void add(long[][] p2, long[][] q2) {
        long[] a2 = new long[16];
        long[] b2 = new long[16];
        long[] c2 = new long[16];
        long[] d2 = new long[16];
        long[] t2 = new long[16];
        long[] e2 = new long[16];
        long[] f2 = new long[16];
        long[] g2 = new long[16];
        long[] h2 = new long[16];
        long[] p0 = p2[0];
        long[] p1 = p2[1];
        long[] p22 = p2[2];
        long[] p3 = p2[3];
        long[] q0 = q2[0];
        long[] q1 = q2[1];
        long[] q22 = q2[2];
        long[] q3 = q2[3];
        TweetNaclFast.Z(a2, 0, p1, 0, p0, 0);
        TweetNaclFast.Z(t2, 0, q1, 0, q0, 0);
        TweetNaclFast.M(a2, 0, a2, 0, t2, 0);
        TweetNaclFast.A(b2, 0, p0, 0, p1, 0);
        TweetNaclFast.A(t2, 0, q0, 0, q1, 0);
        TweetNaclFast.M(b2, 0, b2, 0, t2, 0);
        TweetNaclFast.M(c2, 0, p3, 0, q3, 0);
        TweetNaclFast.M(c2, 0, c2, 0, D2, 0);
        TweetNaclFast.M(d2, 0, p22, 0, q22, 0);
        TweetNaclFast.A(d2, 0, d2, 0, d2, 0);
        TweetNaclFast.Z(e2, 0, b2, 0, a2, 0);
        TweetNaclFast.Z(f2, 0, d2, 0, c2, 0);
        TweetNaclFast.A(g2, 0, d2, 0, c2, 0);
        TweetNaclFast.A(h2, 0, b2, 0, a2, 0);
        TweetNaclFast.M(p0, 0, e2, 0, f2, 0);
        TweetNaclFast.M(p1, 0, h2, 0, g2, 0);
        TweetNaclFast.M(p22, 0, g2, 0, f2, 0);
        TweetNaclFast.M(p3, 0, e2, 0, h2, 0);
    }

    private static void cswap(long[][] p2, long[][] q2, byte b2) {
        for (int i2 = 0; i2 < 4; ++i2) {
            TweetNaclFast.sel25519(p2[i2], 0, q2[i2], 0, b2);
        }
    }

    private static void pack(byte[] r2, long[][] p2) {
        long[] tx2 = new long[16];
        long[] ty2 = new long[16];
        long[] zi2 = new long[16];
        TweetNaclFast.inv25519(zi2, 0, p2[2], 0);
        TweetNaclFast.M(tx2, 0, p2[0], 0, zi2, 0);
        TweetNaclFast.M(ty2, 0, p2[1], 0, zi2, 0);
        TweetNaclFast.pack25519(r2, ty2, 0);
        r2[31] = (byte)(r2[31] ^ TweetNaclFast.par25519(tx2, 0) << 7);
    }

    private static void scalarmult(long[][] p2, long[][] q2, byte[] s2, int soff) {
        TweetNaclFast.set25519(p2[0], gf0);
        TweetNaclFast.set25519(p2[1], gf1);
        TweetNaclFast.set25519(p2[2], gf1);
        TweetNaclFast.set25519(p2[3], gf0);
        for (int i2 = 255; i2 >= 0; --i2) {
            byte b2 = (byte)(s2[i2 / 8 + soff] >>> (i2 & 7) & 1);
            TweetNaclFast.cswap(p2, q2, b2);
            TweetNaclFast.add(q2, p2);
            TweetNaclFast.add(p2, p2);
            TweetNaclFast.cswap(p2, q2, b2);
        }
    }

    private static void scalarbase(long[][] p2, byte[] s2, int soff) {
        long[][] q2 = new long[][]{new long[16], new long[16], new long[16], new long[16]};
        TweetNaclFast.set25519(q2[0], X);
        TweetNaclFast.set25519(q2[1], Y);
        TweetNaclFast.set25519(q2[2], gf1);
        TweetNaclFast.M(q2[3], 0, X, 0, Y, 0);
        TweetNaclFast.scalarmult(p2, q2, s2, soff);
    }

    public static int crypto_sign_keypair(byte[] pk2, byte[] sk, boolean seeded) {
        byte[] d2 = new byte[64];
        long[][] p2 = new long[][]{new long[16], new long[16], new long[16], new long[16]};
        if (!seeded) {
            TweetNaclFast.randombytes(sk, 32);
        }
        TweetNaclFast.crypto_hash(d2, sk, 0, 32);
        d2[0] = (byte)(d2[0] & 0xF8);
        d2[31] = (byte)(d2[31] & 0x7F);
        d2[31] = (byte)(d2[31] | 0x40);
        TweetNaclFast.scalarbase(p2, d2, 0);
        TweetNaclFast.pack(pk2, p2);
        for (int i2 = 0; i2 < 32; ++i2) {
            sk[i2 + 32] = pk2[i2];
        }
        return 0;
    }

    private static void modL(byte[] r2, int roff, long[] x2) {
        int j2;
        long carry;
        int i2;
        for (i2 = 63; i2 >= 32; --i2) {
            carry = 0L;
            j2 = i2 - 32;
            while (j2 < i2 - 12) {
                int n2 = j2;
                x2[n2] = x2[n2] + (carry - 16L * x2[i2] * L[j2 - (i2 - 32)]);
                carry = x2[j2] + 128L >> 8;
                int n3 = j2++;
                x2[n3] = x2[n3] - (carry << 8);
            }
            int n4 = j2;
            x2[n4] = x2[n4] + carry;
            x2[i2] = 0L;
        }
        carry = 0L;
        j2 = 0;
        while (j2 < 32) {
            int n5 = j2;
            x2[n5] = x2[n5] + (carry - (x2[31] >> 4) * L[j2]);
            carry = x2[j2] >> 8;
            int n6 = j2++;
            x2[n6] = x2[n6] & 0xFFL;
        }
        for (j2 = 0; j2 < 32; ++j2) {
            int n7 = j2;
            x2[n7] = x2[n7] - carry * L[j2];
        }
        for (i2 = 0; i2 < 32; ++i2) {
            int n8 = i2 + 1;
            x2[n8] = x2[n8] + (x2[i2] >> 8);
            r2[i2 + roff] = (byte)(x2[i2] & 0xFFL);
        }
    }

    private static void reduce(byte[] r2) {
        int i2;
        long[] x2 = new long[64];
        for (i2 = 0; i2 < 64; ++i2) {
            x2[i2] = r2[i2] & 0xFF;
        }
        for (i2 = 0; i2 < 64; ++i2) {
            r2[i2] = 0;
        }
        TweetNaclFast.modL(r2, 0, x2);
    }

    public static int crypto_sign(byte[] sm2, long dummy, byte[] m2, int moff, int n2, byte[] sk) {
        int i2;
        byte[] d2 = new byte[64];
        byte[] h2 = new byte[64];
        byte[] r2 = new byte[64];
        long[] x2 = new long[64];
        long[][] p2 = new long[][]{new long[16], new long[16], new long[16], new long[16]};
        TweetNaclFast.crypto_hash(d2, sk, 0, 32);
        d2[0] = (byte)(d2[0] & 0xF8);
        d2[31] = (byte)(d2[31] & 0x7F);
        d2[31] = (byte)(d2[31] | 0x40);
        for (i2 = 0; i2 < n2; ++i2) {
            sm2[64 + i2] = m2[i2 + moff];
        }
        for (i2 = 0; i2 < 32; ++i2) {
            sm2[32 + i2] = d2[32 + i2];
        }
        TweetNaclFast.crypto_hash(r2, sm2, 32, n2 + 32);
        TweetNaclFast.reduce(r2);
        TweetNaclFast.scalarbase(p2, r2, 0);
        TweetNaclFast.pack(sm2, p2);
        for (i2 = 0; i2 < 32; ++i2) {
            sm2[i2 + 32] = sk[i2 + 32];
        }
        TweetNaclFast.crypto_hash(h2, sm2, 0, n2 + 64);
        TweetNaclFast.reduce(h2);
        for (i2 = 0; i2 < 64; ++i2) {
            x2[i2] = 0L;
        }
        for (i2 = 0; i2 < 32; ++i2) {
            x2[i2] = r2[i2] & 0xFF;
        }
        for (i2 = 0; i2 < 32; ++i2) {
            for (int j2 = 0; j2 < 32; ++j2) {
                int n3 = i2 + j2;
                x2[n3] = x2[n3] + (long)(h2[i2] & 0xFF) * (long)(d2[j2] & 0xFF);
            }
        }
        TweetNaclFast.modL(sm2, 32, x2);
        return 0;
    }

    private static int unpackneg(long[][] r2, byte[] p2) {
        long[] t2 = new long[16];
        long[] chk = new long[16];
        long[] num = new long[16];
        long[] den = new long[16];
        long[] den2 = new long[16];
        long[] den4 = new long[16];
        long[] den6 = new long[16];
        TweetNaclFast.set25519(r2[2], gf1);
        TweetNaclFast.unpack25519(r2[1], p2);
        TweetNaclFast.S(num, r2[1]);
        TweetNaclFast.M(den, num, D);
        TweetNaclFast.Z(num, num, r2[2]);
        TweetNaclFast.A(den, r2[2], den);
        TweetNaclFast.S(den2, den);
        TweetNaclFast.S(den4, den2);
        TweetNaclFast.M(den6, den4, den2);
        TweetNaclFast.M(t2, den6, num);
        TweetNaclFast.M(t2, t2, den);
        TweetNaclFast.pow2523(t2, t2);
        TweetNaclFast.M(t2, t2, num);
        TweetNaclFast.M(t2, t2, den);
        TweetNaclFast.M(t2, t2, den);
        TweetNaclFast.M(r2[0], t2, den);
        TweetNaclFast.S(chk, r2[0]);
        TweetNaclFast.M(chk, chk, den);
        if (TweetNaclFast.neq25519(chk, num) != 0) {
            TweetNaclFast.M(r2[0], r2[0], I);
        }
        TweetNaclFast.S(chk, r2[0]);
        TweetNaclFast.M(chk, chk, den);
        if (TweetNaclFast.neq25519(chk, num) != 0) {
            return -1;
        }
        if (TweetNaclFast.par25519(r2[0]) == (p2[31] & 0xFF) >>> 7) {
            TweetNaclFast.Z(r2[0], gf0, r2[0]);
        }
        TweetNaclFast.M(r2[3], r2[0], r2[1]);
        return 0;
    }

    public static int crypto_sign_open(byte[] m2, long dummy, byte[] sm2, int smoff, int n2, byte[] pk2) {
        int i2;
        byte[] t2 = new byte[32];
        byte[] h2 = new byte[64];
        long[][] p2 = new long[][]{new long[16], new long[16], new long[16], new long[16]};
        long[][] q2 = new long[][]{new long[16], new long[16], new long[16], new long[16]};
        if (n2 < 64) {
            return -1;
        }
        if (TweetNaclFast.unpackneg(q2, pk2) != 0) {
            return -1;
        }
        for (i2 = 0; i2 < n2; ++i2) {
            m2[i2] = sm2[i2 + smoff];
        }
        for (i2 = 0; i2 < 32; ++i2) {
            m2[i2 + 32] = pk2[i2];
        }
        TweetNaclFast.crypto_hash(h2, m2, 0, n2);
        TweetNaclFast.reduce(h2);
        TweetNaclFast.scalarmult(p2, q2, h2, 0);
        TweetNaclFast.scalarbase(q2, sm2, 32 + smoff);
        TweetNaclFast.add(p2, q2);
        TweetNaclFast.pack(t2, p2);
        n2 -= 64;
        if (TweetNaclFast.crypto_verify_32(sm2, smoff, t2, 0) != 0) {
            return -1;
        }
        return 0;
    }

    public static byte[] randombytes(byte[] x2) {
        jrandom.nextBytes(x2);
        return x2;
    }

    public static byte[] randombytes(int len) {
        return TweetNaclFast.randombytes(new byte[len]);
    }

    public static byte[] randombytes(byte[] x2, int len) {
        byte[] b2 = TweetNaclFast.randombytes(len);
        System.arraycopy(b2, 0, x2, 0, len);
        return x2;
    }

    public static byte[] makeBoxNonce() {
        return TweetNaclFast.randombytes(24);
    }

    public static byte[] makeSecretBoxNonce() {
        return TweetNaclFast.randombytes(24);
    }

    public static String base64EncodeToString(byte[] b2) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b2);
    }

    public static byte[] base64Decode(String s2) {
        return Base64.getUrlDecoder().decode(s2);
    }

    public static String hexEncodeToString(byte[] raw) {
        String HEXES = "0123456789ABCDEF";
        StringBuilder hex = new StringBuilder(2 * raw.length);
        for (byte b2 : raw) {
            hex.append(HEXES.charAt((b2 & 0xF0) >> 4)).append(HEXES.charAt(b2 & 0xF));
        }
        return hex.toString();
    }

    public static byte[] hexDecode(String s2) {
        byte[] b2 = new byte[s2.length() / 2];
        for (int i2 = 0; i2 < s2.length(); i2 += 2) {
            b2[i2 / 2] = (byte)((Character.digit(s2.charAt(i2), 16) << 4) + Character.digit(s2.charAt(i2 + 1), 16));
        }
        return b2;
    }

    static {
        TweetNaclFast._9[0] = 9;
        gf0 = new long[16];
        gf1 = new long[16];
        _121665 = new long[16];
        TweetNaclFast.gf1[0] = 1L;
        TweetNaclFast._121665[0] = 56129L;
        TweetNaclFast._121665[1] = 1L;
        D = new long[]{30883L, 4953L, 19914L, 30187L, 55467L, 16705L, 2637L, 112L, 59544L, 30585L, 16505L, 36039L, 65139L, 11119L, 27886L, 20995L};
        D2 = new long[]{61785L, 9906L, 39828L, 60374L, 45398L, 33411L, 5274L, 224L, 53552L, 61171L, 33010L, 6542L, 64743L, 22239L, 55772L, 9222L};
        X = new long[]{54554L, 36645L, 11616L, 51542L, 42930L, 38181L, 51040L, 26924L, 56412L, 64982L, 57905L, 49316L, 21502L, 52590L, 14035L, 8553L};
        Y = new long[]{26200L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L, 26214L};
        I = new long[]{41136L, 18958L, 6951L, 50414L, 58488L, 44335L, 6150L, 12099L, 55207L, 15867L, 153L, 11085L, 57099L, 20417L, 9344L, 11139L};
        sigma = new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107};
        K = new long[]{4794697086780616226L, 8158064640168781261L, -5349999486874862801L, -1606136188198331460L, 4131703408338449720L, 6480981068601479193L, -7908458776815382629L, -6116909921290321640L, -2880145864133508542L, 1334009975649890238L, 2608012711638119052L, 6128411473006802146L, 8268148722764581231L, -9160688886553864527L, -7215885187991268811L, -4495734319001033068L, -1973867731355612462L, -1171420211273849373L, 1135362057144423861L, 2597628984639134821L, 3308224258029322869L, 5365058923640841347L, 6679025012923562964L, 8573033837759648693L, -7476448914759557205L, -6327057829258317296L, -5763719355590565569L, -4658551843659510044L, -4116276920077217854L, -3051310485924567259L, 489312712824947311L, 1452737877330783856L, 2861767655752347644L, 3322285676063803686L, 5560940570517711597L, 5996557281743188959L, 7280758554555802590L, 8532644243296465576L, -9096487096722542874L, -7894198246740708037L, -6719396339535248540L, -6333637450476146687L, -4446306890439682159L, -4076793802049405392L, -3345356375505022440L, -2983346525034927856L, -860691631967231958L, 1182934255886127544L, 1847814050463011016L, 2177327727835720531L, 2830643537854262169L, 3796741975233480872L, 4115178125766777443L, 5681478168544905931L, 6601373596472566643L, 7507060721942968483L, 8399075790359081724L, 8693463985226723168L, -8878714635349349518L, -8302665154208450068L, -8016688836872298968L, -6606660893046293015L, -4685533653050689259L, -4147400797238176981L, -3880063495543823972L, -3348786107499101689L, -1523767162380948706L, -757361751448694408L, 500013540394364858L, 748580250866718886L, 1242879168328830382L, 1977374033974150939L, 2944078676154940804L, 3659926193048069267L, 4368137639120453308L, 4836135668995329356L, 5532061633213252278L, 6448918945643986474L, 6902733635092675308L, 7801388544844847127L};
        L = new long[]{237L, 211L, 245L, 92L, 26L, 99L, 18L, 88L, 214L, 156L, 247L, 162L, 222L, 249L, 222L, 20L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 16L};
        jrandom = new SecureRandom();
    }

    public static final class poly1305 {
        private byte[] buffer = new byte[16];
        private int[] r = new int[10];
        private int[] h = new int[10];
        private int[] pad = new int[8];
        private int leftover = 0;
        private int fin = 0;

        public poly1305(byte[] key) {
            int t0 = key[0] & 0xFF | (key[1] & 0xFF) << 8;
            this.r[0] = t0 & 0x1FFF;
            int t1 = key[2] & 0xFF | (key[3] & 0xFF) << 8;
            this.r[1] = (t0 >>> 13 | t1 << 3) & 0x1FFF;
            int t2 = key[4] & 0xFF | (key[5] & 0xFF) << 8;
            this.r[2] = (t1 >>> 10 | t2 << 6) & 0x1F03;
            int t3 = key[6] & 0xFF | (key[7] & 0xFF) << 8;
            this.r[3] = (t2 >>> 7 | t3 << 9) & 0x1FFF;
            int t4 = key[8] & 0xFF | (key[9] & 0xFF) << 8;
            this.r[4] = (t3 >>> 4 | t4 << 12) & 0xFF;
            this.r[5] = t4 >>> 1 & 0x1FFE;
            int t5 = key[10] & 0xFF | (key[11] & 0xFF) << 8;
            this.r[6] = (t4 >>> 14 | t5 << 2) & 0x1FFF;
            int t6 = key[12] & 0xFF | (key[13] & 0xFF) << 8;
            this.r[7] = (t5 >>> 11 | t6 << 5) & 0x1F81;
            int t7 = key[14] & 0xFF | (key[15] & 0xFF) << 8;
            this.r[8] = (t6 >>> 8 | t7 << 8) & 0x1FFF;
            this.r[9] = t7 >>> 5 & 0x7F;
            this.pad[0] = key[16] & 0xFF | (key[17] & 0xFF) << 8;
            this.pad[1] = key[18] & 0xFF | (key[19] & 0xFF) << 8;
            this.pad[2] = key[20] & 0xFF | (key[21] & 0xFF) << 8;
            this.pad[3] = key[22] & 0xFF | (key[23] & 0xFF) << 8;
            this.pad[4] = key[24] & 0xFF | (key[25] & 0xFF) << 8;
            this.pad[5] = key[26] & 0xFF | (key[27] & 0xFF) << 8;
            this.pad[6] = key[28] & 0xFF | (key[29] & 0xFF) << 8;
            this.pad[7] = key[30] & 0xFF | (key[31] & 0xFF) << 8;
        }

        public poly1305 blocks(byte[] m2, int mpos, int bytes) {
            int hibit = this.fin != 0 ? 0 : 2048;
            int h0 = this.h[0];
            int h1 = this.h[1];
            int h2 = this.h[2];
            int h3 = this.h[3];
            int h4 = this.h[4];
            int h5 = this.h[5];
            int h6 = this.h[6];
            int h7 = this.h[7];
            int h8 = this.h[8];
            int h9 = this.h[9];
            int r0 = this.r[0];
            int r1 = this.r[1];
            int r2 = this.r[2];
            int r3 = this.r[3];
            int r4 = this.r[4];
            int r5 = this.r[5];
            int r6 = this.r[6];
            int r7 = this.r[7];
            int r8 = this.r[8];
            int r9 = this.r[9];
            while (bytes >= 16) {
                int c2;
                int t0 = m2[mpos + 0] & 0xFF | (m2[mpos + 1] & 0xFF) << 8;
                h0 += t0 & 0x1FFF;
                int t1 = m2[mpos + 2] & 0xFF | (m2[mpos + 3] & 0xFF) << 8;
                h1 += (t0 >>> 13 | t1 << 3) & 0x1FFF;
                int t2 = m2[mpos + 4] & 0xFF | (m2[mpos + 5] & 0xFF) << 8;
                h2 += (t1 >>> 10 | t2 << 6) & 0x1FFF;
                int t3 = m2[mpos + 6] & 0xFF | (m2[mpos + 7] & 0xFF) << 8;
                h3 += (t2 >>> 7 | t3 << 9) & 0x1FFF;
                int t4 = m2[mpos + 8] & 0xFF | (m2[mpos + 9] & 0xFF) << 8;
                h4 += (t3 >>> 4 | t4 << 12) & 0x1FFF;
                h5 += t4 >>> 1 & 0x1FFF;
                int t5 = m2[mpos + 10] & 0xFF | (m2[mpos + 11] & 0xFF) << 8;
                h6 += (t4 >>> 14 | t5 << 2) & 0x1FFF;
                int t6 = m2[mpos + 12] & 0xFF | (m2[mpos + 13] & 0xFF) << 8;
                h7 += (t5 >>> 11 | t6 << 5) & 0x1FFF;
                int t7 = m2[mpos + 14] & 0xFF | (m2[mpos + 15] & 0xFF) << 8;
                h8 += (t6 >>> 8 | t7 << 8) & 0x1FFF;
                h9 += t7 >>> 5 | hibit;
                int d0 = c2 = 0;
                d0 += h0 * r0;
                d0 += h1 * (5 * r9);
                d0 += h2 * (5 * r8);
                d0 += h3 * (5 * r7);
                c2 = (d0 += h4 * (5 * r6)) >>> 13;
                d0 &= 0x1FFF;
                d0 += h5 * (5 * r5);
                d0 += h6 * (5 * r4);
                d0 += h7 * (5 * r3);
                d0 += h8 * (5 * r2);
                d0 &= 0x1FFF;
                int d1 = c2 += (d0 += h9 * (5 * r1)) >>> 13;
                d1 += h0 * r1;
                d1 += h1 * r0;
                d1 += h2 * (5 * r9);
                d1 += h3 * (5 * r8);
                c2 = (d1 += h4 * (5 * r7)) >>> 13;
                d1 &= 0x1FFF;
                d1 += h5 * (5 * r6);
                d1 += h6 * (5 * r5);
                d1 += h7 * (5 * r4);
                d1 += h8 * (5 * r3);
                d1 &= 0x1FFF;
                int d2 = c2 += (d1 += h9 * (5 * r2)) >>> 13;
                d2 += h0 * r2;
                d2 += h1 * r1;
                d2 += h2 * r0;
                d2 += h3 * (5 * r9);
                c2 = (d2 += h4 * (5 * r8)) >>> 13;
                d2 &= 0x1FFF;
                d2 += h5 * (5 * r7);
                d2 += h6 * (5 * r6);
                d2 += h7 * (5 * r5);
                d2 += h8 * (5 * r4);
                d2 &= 0x1FFF;
                int d3 = c2 += (d2 += h9 * (5 * r3)) >>> 13;
                d3 += h0 * r3;
                d3 += h1 * r2;
                d3 += h2 * r1;
                d3 += h3 * r0;
                c2 = (d3 += h4 * (5 * r9)) >>> 13;
                d3 &= 0x1FFF;
                d3 += h5 * (5 * r8);
                d3 += h6 * (5 * r7);
                d3 += h7 * (5 * r6);
                d3 += h8 * (5 * r5);
                d3 &= 0x1FFF;
                int d4 = c2 += (d3 += h9 * (5 * r4)) >>> 13;
                d4 += h0 * r4;
                d4 += h1 * r3;
                d4 += h2 * r2;
                d4 += h3 * r1;
                c2 = (d4 += h4 * r0) >>> 13;
                d4 &= 0x1FFF;
                d4 += h5 * (5 * r9);
                d4 += h6 * (5 * r8);
                d4 += h7 * (5 * r7);
                d4 += h8 * (5 * r6);
                d4 &= 0x1FFF;
                int d5 = c2 += (d4 += h9 * (5 * r5)) >>> 13;
                d5 += h0 * r5;
                d5 += h1 * r4;
                d5 += h2 * r3;
                d5 += h3 * r2;
                c2 = (d5 += h4 * r1) >>> 13;
                d5 &= 0x1FFF;
                d5 += h5 * r0;
                d5 += h6 * (5 * r9);
                d5 += h7 * (5 * r8);
                d5 += h8 * (5 * r7);
                d5 &= 0x1FFF;
                int d6 = c2 += (d5 += h9 * (5 * r6)) >>> 13;
                d6 += h0 * r6;
                d6 += h1 * r5;
                d6 += h2 * r4;
                d6 += h3 * r3;
                c2 = (d6 += h4 * r2) >>> 13;
                d6 &= 0x1FFF;
                d6 += h5 * r1;
                d6 += h6 * r0;
                d6 += h7 * (5 * r9);
                d6 += h8 * (5 * r8);
                d6 &= 0x1FFF;
                int d7 = c2 += (d6 += h9 * (5 * r7)) >>> 13;
                d7 += h0 * r7;
                d7 += h1 * r6;
                d7 += h2 * r5;
                d7 += h3 * r4;
                c2 = (d7 += h4 * r3) >>> 13;
                d7 &= 0x1FFF;
                d7 += h5 * r2;
                d7 += h6 * r1;
                d7 += h7 * r0;
                d7 += h8 * (5 * r9);
                d7 &= 0x1FFF;
                int d8 = c2 += (d7 += h9 * (5 * r8)) >>> 13;
                d8 += h0 * r8;
                d8 += h1 * r7;
                d8 += h2 * r6;
                d8 += h3 * r5;
                c2 = (d8 += h4 * r4) >>> 13;
                d8 &= 0x1FFF;
                d8 += h5 * r3;
                d8 += h6 * r2;
                d8 += h7 * r1;
                d8 += h8 * r0;
                d8 &= 0x1FFF;
                int d9 = c2 += (d8 += h9 * (5 * r9)) >>> 13;
                d9 += h0 * r9;
                d9 += h1 * r8;
                d9 += h2 * r7;
                d9 += h3 * r6;
                c2 = (d9 += h4 * r5) >>> 13;
                d9 &= 0x1FFF;
                d9 += h5 * r4;
                d9 += h6 * r3;
                d9 += h7 * r2;
                d9 += h8 * r1;
                c2 += (d9 += h9 * r0) >>> 13;
                d9 &= 0x1FFF;
                c2 = (c2 << 2) + c2 | 0;
                c2 = c2 + d0 | 0;
                d0 = c2 & 0x1FFF;
                h0 = d0;
                h1 = d1 += (c2 >>>= 13);
                h2 = d2;
                h3 = d3;
                h4 = d4;
                h5 = d5;
                h6 = d6;
                h7 = d7;
                h8 = d8;
                h9 = d9;
                mpos += 16;
                bytes -= 16;
            }
            this.h[0] = h0;
            this.h[1] = h1;
            this.h[2] = h2;
            this.h[3] = h3;
            this.h[4] = h4;
            this.h[5] = h5;
            this.h[6] = h6;
            this.h[7] = h7;
            this.h[8] = h8;
            this.h[9] = h9;
            return this;
        }

        public poly1305 finish(byte[] mac, int macpos) {
            int i2;
            int[] g2 = new int[10];
            if (this.leftover != 0) {
                i2 = this.leftover;
                this.buffer[i2++] = 1;
                while (i2 < 16) {
                    this.buffer[i2] = 0;
                    ++i2;
                }
                this.fin = 1;
                this.blocks(this.buffer, 0, 16);
            }
            int c2 = this.h[1] >>> 13;
            this.h[1] = this.h[1] & 0x1FFF;
            i2 = 2;
            while (i2 < 10) {
                int n2 = i2;
                this.h[n2] = this.h[n2] + c2;
                c2 = this.h[i2] >>> 13;
                int n3 = i2++;
                this.h[n3] = this.h[n3] & 0x1FFF;
            }
            this.h[0] = this.h[0] + c2 * 5;
            c2 = this.h[0] >>> 13;
            this.h[0] = this.h[0] & 0x1FFF;
            this.h[1] = this.h[1] + c2;
            c2 = this.h[1] >>> 13;
            this.h[1] = this.h[1] & 0x1FFF;
            this.h[2] = this.h[2] + c2;
            g2[0] = this.h[0] + 5;
            c2 = g2[0] >>> 13;
            g2[0] = g2[0] & 0x1FFF;
            i2 = 1;
            while (i2 < 10) {
                g2[i2] = this.h[i2] + c2;
                c2 = g2[i2] >>> 13;
                int n4 = i2++;
                g2[n4] = g2[n4] & 0x1FFF;
            }
            g2[9] = g2[9] - 8192;
            g2[9] = g2[9] & 0xFFFF;
            int mask = (g2[9] >>> 15) - 1;
            mask &= 0xFFFF;
            i2 = 0;
            while (i2 < 10) {
                int n5 = i2++;
                g2[n5] = g2[n5] & mask;
            }
            mask ^= 0xFFFFFFFF;
            for (i2 = 0; i2 < 10; ++i2) {
                this.h[i2] = this.h[i2] & mask | g2[i2];
            }
            this.h[0] = (this.h[0] | this.h[1] << 13) & 0xFFFF;
            this.h[1] = (this.h[1] >>> 3 | this.h[2] << 10) & 0xFFFF;
            this.h[2] = (this.h[2] >>> 6 | this.h[3] << 7) & 0xFFFF;
            this.h[3] = (this.h[3] >>> 9 | this.h[4] << 4) & 0xFFFF;
            this.h[4] = (this.h[4] >>> 12 | this.h[5] << 1 | this.h[6] << 14) & 0xFFFF;
            this.h[5] = (this.h[6] >>> 2 | this.h[7] << 11) & 0xFFFF;
            this.h[6] = (this.h[7] >>> 5 | this.h[8] << 8) & 0xFFFF;
            this.h[7] = (this.h[8] >>> 8 | this.h[9] << 5) & 0xFFFF;
            int f2 = this.h[0] + this.pad[0];
            this.h[0] = f2 & 0xFFFF;
            for (i2 = 1; i2 < 8; ++i2) {
                f2 = (this.h[i2] + this.pad[i2] | 0) + (f2 >>> 16) | 0;
                this.h[i2] = f2 & 0xFFFF;
            }
            mac[macpos + 0] = (byte)(this.h[0] >>> 0 & 0xFF);
            mac[macpos + 1] = (byte)(this.h[0] >>> 8 & 0xFF);
            mac[macpos + 2] = (byte)(this.h[1] >>> 0 & 0xFF);
            mac[macpos + 3] = (byte)(this.h[1] >>> 8 & 0xFF);
            mac[macpos + 4] = (byte)(this.h[2] >>> 0 & 0xFF);
            mac[macpos + 5] = (byte)(this.h[2] >>> 8 & 0xFF);
            mac[macpos + 6] = (byte)(this.h[3] >>> 0 & 0xFF);
            mac[macpos + 7] = (byte)(this.h[3] >>> 8 & 0xFF);
            mac[macpos + 8] = (byte)(this.h[4] >>> 0 & 0xFF);
            mac[macpos + 9] = (byte)(this.h[4] >>> 8 & 0xFF);
            mac[macpos + 10] = (byte)(this.h[5] >>> 0 & 0xFF);
            mac[macpos + 11] = (byte)(this.h[5] >>> 8 & 0xFF);
            mac[macpos + 12] = (byte)(this.h[6] >>> 0 & 0xFF);
            mac[macpos + 13] = (byte)(this.h[6] >>> 8 & 0xFF);
            mac[macpos + 14] = (byte)(this.h[7] >>> 0 & 0xFF);
            mac[macpos + 15] = (byte)(this.h[7] >>> 8 & 0xFF);
            return this;
        }

        public poly1305 update(byte[] m2, int mpos, int bytes) {
            int i2;
            int want;
            if (this.leftover != 0) {
                want = 16 - this.leftover;
                if (want > bytes) {
                    want = bytes;
                }
                for (i2 = 0; i2 < want; ++i2) {
                    this.buffer[this.leftover + i2] = m2[mpos + i2];
                }
                bytes -= want;
                mpos += want;
                this.leftover += want;
                if (this.leftover < 16) {
                    return this;
                }
                this.blocks(this.buffer, 0, 16);
                this.leftover = 0;
            }
            if (bytes >= 16) {
                want = bytes - bytes % 16;
                this.blocks(m2, mpos, want);
                mpos += want;
                bytes -= want;
            }
            if (bytes != 0) {
                for (i2 = 0; i2 < bytes; ++i2) {
                    this.buffer[this.leftover + i2] = m2[mpos + i2];
                }
                this.leftover += bytes;
            }
            return this;
        }
    }

    public static final class Signature {
        private static final String TAG = "Signature";
        private byte[] theirPublicKey;
        private byte[] mySecretKey;
        public static final int publicKeyLength = 32;
        public static final int secretKeyLength = 64;
        public static final int seedLength = 32;
        public static final int signatureLength = 64;

        public Signature(byte[] theirPublicKey, byte[] mySecretKey) {
            this.theirPublicKey = theirPublicKey;
            this.mySecretKey = mySecretKey;
        }

        public byte[] sign(byte[] message) {
            if (message == null) {
                return null;
            }
            return this.sign(message, 0, message.length);
        }

        public byte[] sign(byte[] message, int moff) {
            if (message == null || message.length <= moff) {
                return null;
            }
            return this.sign(message, moff, message.length - moff);
        }

        public byte[] sign(byte[] message, int moff, int mlen) {
            if (message == null || message.length < moff + mlen) {
                return null;
            }
            byte[] sm2 = new byte[mlen + 64];
            TweetNaclFast.crypto_sign(sm2, -1L, message, moff, mlen, this.mySecretKey);
            return sm2;
        }

        public byte[] open(byte[] signedMessage) {
            if (signedMessage == null) {
                return null;
            }
            return this.open(signedMessage, 0, signedMessage.length);
        }

        public byte[] open(byte[] signedMessage, int smoff) {
            if (signedMessage == null || signedMessage.length <= smoff) {
                return null;
            }
            return this.open(signedMessage, smoff, signedMessage.length - smoff);
        }

        public byte[] open(byte[] signedMessage, int smoff, int smlen) {
            if (signedMessage == null || signedMessage.length < smoff + smlen || smlen < 64) {
                return null;
            }
            byte[] tmp = new byte[smlen];
            if (0 != TweetNaclFast.crypto_sign_open(tmp, -1L, signedMessage, smoff, smlen, this.theirPublicKey)) {
                return null;
            }
            byte[] msg = new byte[smlen - 64];
            for (int i2 = 0; i2 < msg.length; ++i2) {
                msg[i2] = signedMessage[smoff + i2 + 64];
            }
            return msg;
        }

        public byte[] detached(byte[] message) {
            byte[] signedMsg = this.sign(message);
            byte[] sig = new byte[64];
            for (int i2 = 0; i2 < sig.length; ++i2) {
                sig[i2] = signedMsg[i2];
            }
            return sig;
        }

        public boolean detached_verify(byte[] message, byte[] signature) {
            int i2;
            if (signature.length != 64) {
                return false;
            }
            if (this.theirPublicKey.length != 32) {
                return false;
            }
            byte[] sm2 = new byte[64 + message.length];
            byte[] m2 = new byte[64 + message.length];
            for (i2 = 0; i2 < 64; ++i2) {
                sm2[i2] = signature[i2];
            }
            for (i2 = 0; i2 < message.length; ++i2) {
                sm2[i2 + 64] = message[i2];
            }
            return TweetNaclFast.crypto_sign_open(m2, -1L, sm2, 0, sm2.length, this.theirPublicKey) >= 0;
        }

        public static KeyPair keyPair() {
            KeyPair kp = new KeyPair();
            TweetNaclFast.crypto_sign_keypair(kp.getPublicKey(), kp.getSecretKey(), false);
            return kp;
        }

        public static KeyPair keyPair_fromSecretKey(byte[] secretKey) {
            int i2;
            KeyPair kp = new KeyPair();
            byte[] pk2 = kp.getPublicKey();
            byte[] sk = kp.getSecretKey();
            for (i2 = 0; i2 < kp.getSecretKey().length; ++i2) {
                sk[i2] = secretKey[i2];
            }
            for (i2 = 0; i2 < kp.getPublicKey().length; ++i2) {
                pk2[i2] = secretKey[32 + i2];
            }
            return kp;
        }

        public static KeyPair keyPair_fromSeed(byte[] seed) {
            KeyPair kp = new KeyPair();
            byte[] pk2 = kp.getPublicKey();
            byte[] sk = kp.getSecretKey();
            for (int i2 = 0; i2 < 32; ++i2) {
                sk[i2] = seed[i2];
            }
            TweetNaclFast.crypto_sign_keypair(pk2, sk, true);
            return kp;
        }

        public static class KeyPair {
            private byte[] publicKey = new byte[32];
            private byte[] secretKey = new byte[64];

            public byte[] getPublicKey() {
                return this.publicKey;
            }

            public byte[] getSecretKey() {
                return this.secretKey;
            }
        }
    }

    public static final class Hash {
        private static final String TAG = "Hash";
        public static final int hashLength = 64;

        public static byte[] sha512(byte[] message) {
            if (message == null || message.length <= 0) {
                return null;
            }
            byte[] out = new byte[64];
            TweetNaclFast.crypto_hash(out, message);
            return out;
        }

        public static byte[] sha512(String message) throws UnsupportedEncodingException {
            return Hash.sha512(message.getBytes("utf-8"));
        }
    }

    public static final class ScalarMult {
        private static final String TAG = "ScalarMult";
        public static final int scalarLength = 32;
        public static final int groupElementLength = 32;

        public static byte[] scalseMult(byte[] n2, byte[] p2) {
            if (n2.length != 32 || p2.length != 32) {
                return null;
            }
            byte[] q2 = new byte[32];
            TweetNaclFast.crypto_scalarmult(q2, n2, p2);
            return q2;
        }

        public static byte[] scalseMult_base(byte[] n2) {
            if (n2.length != 32) {
                return null;
            }
            byte[] q2 = new byte[32];
            TweetNaclFast.crypto_scalarmult_base(q2, n2);
            return q2;
        }
    }

    public static final class SecretBox {
        private static final String TAG = "SecretBox";
        private AtomicLong nonce;
        private byte[] key;
        public static final int keyLength = 32;
        public static final int nonceLength = 24;
        public static final int overheadLength = 16;
        public static final int zerobytesLength = 32;
        public static final int boxzerobytesLength = 16;

        public SecretBox(byte[] key) {
            this(key, 68L);
        }

        public SecretBox(byte[] key, long nonce) {
            this.key = key;
            this.nonce = new AtomicLong(nonce);
        }

        public void setNonce(long nonce) {
            this.nonce.set(nonce);
        }

        public long getNonce() {
            return this.nonce.get();
        }

        public long incrNonce() {
            return this.nonce.incrementAndGet();
        }

        private byte[] generateNonce() {
            long nonce = this.nonce.get();
            byte[] n2 = new byte[24];
            for (int i2 = 0; i2 < 24; i2 += 8) {
                n2[i2 + 0] = (byte)(nonce >>> 0);
                n2[i2 + 1] = (byte)(nonce >>> 8);
                n2[i2 + 2] = (byte)(nonce >>> 16);
                n2[i2 + 3] = (byte)(nonce >>> 24);
                n2[i2 + 4] = (byte)(nonce >>> 32);
                n2[i2 + 5] = (byte)(nonce >>> 40);
                n2[i2 + 6] = (byte)(nonce >>> 48);
                n2[i2 + 7] = (byte)(nonce >>> 56);
            }
            return n2;
        }

        public byte[] box(byte[] message) {
            if (message == null) {
                return null;
            }
            return this.box(message, 0, message.length);
        }

        public byte[] box(byte[] message, int moff) {
            if (message == null || message.length <= moff) {
                return null;
            }
            return this.box(message, moff, message.length - moff);
        }

        public byte[] box(byte[] message, int moff, int mlen) {
            if (message == null || message.length < moff + mlen) {
                return null;
            }
            return this.box(message, moff, message.length - moff, this.generateNonce());
        }

        public byte[] box(byte[] message, byte[] theNonce) {
            if (message == null) {
                return null;
            }
            return this.box(message, 0, message.length, theNonce);
        }

        public byte[] box(byte[] message, int moff, byte[] theNonce) {
            if (message == null || message.length <= moff) {
                return null;
            }
            return this.box(message, moff, message.length - moff, theNonce);
        }

        public byte[] box(byte[] message, int moff, int mlen, byte[] theNonce) {
            if (message == null || message.length < moff + mlen || theNonce == null || theNonce.length != 24) {
                return null;
            }
            byte[] m2 = new byte[mlen + 32];
            byte[] c2 = new byte[m2.length];
            for (int i2 = 0; i2 < mlen; ++i2) {
                m2[i2 + 32] = message[i2 + moff];
            }
            if (0 != TweetNaclFast.crypto_secretbox(c2, m2, m2.length, theNonce, this.key)) {
                return null;
            }
            byte[] ret = new byte[c2.length - 16];
            for (int i3 = 0; i3 < ret.length; ++i3) {
                ret[i3] = c2[i3 + 16];
            }
            return ret;
        }

        public byte[] open(byte[] box2) {
            if (box2 == null) {
                return null;
            }
            return this.open(box2, 0, box2.length);
        }

        public byte[] open(byte[] box2, int boxoff) {
            if (box2 == null || box2.length <= boxoff) {
                return null;
            }
            return this.open(box2, boxoff, box2.length - boxoff);
        }

        public byte[] open(byte[] box2, int boxoff, int boxlen) {
            if (box2 == null || box2.length < boxoff + boxlen || boxlen < 16) {
                return null;
            }
            return this.open(box2, boxoff, box2.length - boxoff, this.generateNonce());
        }

        public byte[] open(byte[] box2, byte[] theNonce) {
            if (box2 == null) {
                return null;
            }
            return this.open(box2, 0, box2.length, theNonce);
        }

        public byte[] open(byte[] box2, int boxoff, byte[] theNonce) {
            if (box2 == null || box2.length <= boxoff) {
                return null;
            }
            return this.open(box2, boxoff, box2.length - boxoff, theNonce);
        }

        public byte[] open(byte[] box2, int boxoff, int boxlen, byte[] theNonce) {
            if (box2 == null || box2.length < boxoff + boxlen || boxlen < 16 || theNonce == null || theNonce.length != 24) {
                return null;
            }
            byte[] c2 = new byte[boxlen + 16];
            byte[] m2 = new byte[c2.length];
            for (int i2 = 0; i2 < boxlen; ++i2) {
                c2[i2 + 16] = box2[i2 + boxoff];
            }
            if (0 != TweetNaclFast.crypto_secretbox_open(m2, c2, c2.length, theNonce, this.key)) {
                return null;
            }
            byte[] ret = new byte[m2.length - 32];
            for (int i3 = 0; i3 < ret.length; ++i3) {
                ret[i3] = m2[i3 + 32];
            }
            return ret;
        }
    }

    public static final class Box {
        private static final String TAG = "Box";
        private AtomicLong nonce;
        private byte[] theirPublicKey;
        private byte[] mySecretKey;
        private byte[] sharedKey;
        public static final int publicKeyLength = 32;
        public static final int secretKeyLength = 32;
        public static final int sharedKeyLength = 32;
        public static final int nonceLength = 24;
        public static final int zerobytesLength = 32;
        public static final int boxzerobytesLength = 16;
        public static final int overheadLength = 16;

        public Box(byte[] theirPublicKey, byte[] mySecretKey) {
            this(theirPublicKey, mySecretKey, 68L);
        }

        public Box(byte[] theirPublicKey, byte[] mySecretKey, long nonce) {
            this.theirPublicKey = theirPublicKey;
            this.mySecretKey = mySecretKey;
            this.nonce = new AtomicLong(nonce);
            this.before();
        }

        public void setNonce(long nonce) {
            this.nonce.set(nonce);
        }

        public long getNonce() {
            return this.nonce.get();
        }

        public long incrNonce() {
            return this.nonce.incrementAndGet();
        }

        private byte[] generateNonce() {
            long nonce = this.nonce.get();
            byte[] n2 = new byte[24];
            for (int i2 = 0; i2 < 24; i2 += 8) {
                n2[i2 + 0] = (byte)(nonce >>> 0);
                n2[i2 + 1] = (byte)(nonce >>> 8);
                n2[i2 + 2] = (byte)(nonce >>> 16);
                n2[i2 + 3] = (byte)(nonce >>> 24);
                n2[i2 + 4] = (byte)(nonce >>> 32);
                n2[i2 + 5] = (byte)(nonce >>> 40);
                n2[i2 + 6] = (byte)(nonce >>> 48);
                n2[i2 + 7] = (byte)(nonce >>> 56);
            }
            return n2;
        }

        public byte[] box(byte[] message) {
            if (message == null) {
                return null;
            }
            return this.box(message, 0, message.length);
        }

        public byte[] box(byte[] message, int moff) {
            if (message == null || message.length <= moff) {
                return null;
            }
            return this.box(message, moff, message.length - moff);
        }

        public byte[] box(byte[] message, int moff, int mlen) {
            if (message == null || message.length < moff + mlen) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.after(message, moff, mlen);
        }

        public byte[] box(byte[] message, byte[] theNonce) {
            if (message == null) {
                return null;
            }
            return this.box(message, 0, message.length, theNonce);
        }

        public byte[] box(byte[] message, int moff, byte[] theNonce) {
            if (message == null || message.length <= moff) {
                return null;
            }
            return this.box(message, moff, message.length - moff, theNonce);
        }

        public byte[] box(byte[] message, int moff, int mlen, byte[] theNonce) {
            if (message == null || message.length < moff + mlen || theNonce == null || theNonce.length != 24) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.after(message, moff, mlen, theNonce);
        }

        public byte[] open(byte[] box2) {
            if (box2 == null) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, 0, box2.length);
        }

        public byte[] open(byte[] box2, int boxoff) {
            if (box2 == null || box2.length <= boxoff) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, boxoff, box2.length - boxoff);
        }

        public byte[] open(byte[] box2, int boxoff, int boxlen) {
            if (box2 == null || box2.length < boxoff + boxlen) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, boxoff, boxlen);
        }

        public byte[] open(byte[] box2, byte[] theNonce) {
            if (box2 == null || theNonce == null || theNonce.length != 24) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, 0, box2.length, theNonce);
        }

        public byte[] open(byte[] box2, int boxoff, byte[] theNonce) {
            if (box2 == null || box2.length <= boxoff || theNonce == null || theNonce.length != 24) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, boxoff, box2.length - boxoff, theNonce);
        }

        public byte[] open(byte[] box2, int boxoff, int boxlen, byte[] theNonce) {
            if (box2 == null || box2.length < boxoff + boxlen || theNonce == null || theNonce.length != 24) {
                return null;
            }
            if (this.sharedKey == null) {
                this.before();
            }
            return this.open_after(box2, boxoff, boxlen, theNonce);
        }

        public byte[] before() {
            if (this.sharedKey == null) {
                this.sharedKey = new byte[32];
                TweetNaclFast.crypto_box_beforenm(this.sharedKey, this.theirPublicKey, this.mySecretKey);
            }
            return this.sharedKey;
        }

        public byte[] after(byte[] message, int moff, int mlen) {
            return this.after(message, moff, mlen, this.generateNonce());
        }

        public byte[] after(byte[] message, int moff, int mlen, byte[] theNonce) {
            if (message == null || message.length < moff + mlen || theNonce == null || theNonce.length != 24) {
                return null;
            }
            byte[] m2 = new byte[mlen + 32];
            byte[] c2 = new byte[m2.length];
            for (int i2 = 0; i2 < mlen; ++i2) {
                m2[i2 + 32] = message[i2 + moff];
            }
            if (0 != TweetNaclFast.crypto_box_afternm(c2, m2, m2.length, theNonce, this.sharedKey)) {
                return null;
            }
            byte[] ret = new byte[c2.length - 16];
            for (int i3 = 0; i3 < ret.length; ++i3) {
                ret[i3] = c2[i3 + 16];
            }
            return ret;
        }

        public byte[] open_after(byte[] box2, int boxoff, int boxlen) {
            return this.open_after(box2, boxoff, boxlen, this.generateNonce());
        }

        public byte[] open_after(byte[] box2, int boxoff, int boxlen, byte[] theNonce) {
            if (box2 == null || box2.length < boxoff + boxlen || boxlen < 16) {
                return null;
            }
            byte[] c2 = new byte[boxlen + 16];
            byte[] m2 = new byte[c2.length];
            for (int i2 = 0; i2 < boxlen; ++i2) {
                c2[i2 + 16] = box2[i2 + boxoff];
            }
            if (TweetNaclFast.crypto_box_open_afternm(m2, c2, c2.length, theNonce, this.sharedKey) != 0) {
                return null;
            }
            byte[] ret = new byte[m2.length - 32];
            for (int i3 = 0; i3 < ret.length; ++i3) {
                ret[i3] = m2[i3 + 32];
            }
            return ret;
        }

        public static KeyPair keyPair() {
            KeyPair kp = new KeyPair();
            TweetNaclFast.crypto_box_keypair(kp.getPublicKey(), kp.getSecretKey());
            return kp;
        }

        public static KeyPair keyPair_fromSecretKey(byte[] secretKey) {
            KeyPair kp = new KeyPair();
            byte[] sk = kp.getSecretKey();
            byte[] pk2 = kp.getPublicKey();
            for (int i2 = 0; i2 < sk.length; ++i2) {
                sk[i2] = secretKey[i2];
            }
            TweetNaclFast.crypto_scalarmult_base(pk2, sk);
            return kp;
        }

        public static class KeyPair {
            private byte[] publicKey = new byte[32];
            private byte[] secretKey = new byte[32];

            public byte[] getPublicKey() {
                return this.publicKey;
            }

            public byte[] getSecretKey() {
                return this.secretKey;
            }
        }
    }
}

