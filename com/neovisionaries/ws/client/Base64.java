/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Misc;

class Base64 {
    private static final byte[] INDEX_TABLE = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    Base64() {
    }

    public static String encode(String data) {
        if (data == null) {
            return null;
        }
        return Base64.encode(Misc.getBytesUTF8(data));
    }

    public static String encode(byte[] data) {
        int bits;
        if (data == null) {
            return null;
        }
        int capacity = ((data.length * 8 + 5) / 6 + 3) / 4 * 4;
        StringBuilder builder = new StringBuilder(capacity);
        int bitIndex = 0;
        while ((bits = Base64.extractBits(data, bitIndex)) >= 0) {
            builder.append((char)INDEX_TABLE[bits]);
            bitIndex += 6;
        }
        for (int i2 = builder.length(); i2 < capacity; ++i2) {
            builder.append('=');
        }
        return builder.toString();
    }

    private static int extractBits(byte[] data, int bitIndex) {
        int byteIndex = bitIndex / 8;
        if (data.length <= byteIndex) {
            return -1;
        }
        byte nextByte = data.length - 1 == byteIndex ? (byte)0 : data[byteIndex + 1];
        switch (bitIndex % 24 / 6) {
            case 0: {
                return data[byteIndex] >> 2 & 0x3F;
            }
            case 1: {
                return data[byteIndex] << 4 & 0x30 | nextByte >> 4 & 0xF;
            }
            case 2: {
                return data[byteIndex] << 2 & 0x3C | nextByte >> 6 & 3;
            }
            case 3: {
                return data[byteIndex] & 0x3F;
            }
        }
        return 0;
    }
}

