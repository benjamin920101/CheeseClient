/*
 * Decompiled with CFR 0.152.
 */
package org.apache.commons.collections4.trie.analyzer;

import org.apache.commons.collections4.trie.KeyAnalyzer;

public class StringKeyAnalyzer
extends KeyAnalyzer<String> {
    private static final long serialVersionUID = -7032449491269434877L;
    public static final StringKeyAnalyzer INSTANCE = new StringKeyAnalyzer();
    public static final int LENGTH = 16;
    private static final int MSB = 32768;

    private static int mask(int bit2) {
        return 32768 >>> bit2;
    }

    @Override
    public int bitsPerElement() {
        return 16;
    }

    @Override
    public int lengthInBits(String key) {
        return key != null ? key.length() * 16 : 0;
    }

    @Override
    public int bitIndex(String key, int offsetInBits, int lengthInBits, String other, int otherOffsetInBits, int otherLengthInBits) {
        boolean allNull = true;
        if (offsetInBits % 16 != 0 || otherOffsetInBits % 16 != 0 || lengthInBits % 16 != 0 || otherLengthInBits % 16 != 0) {
            throw new IllegalArgumentException("The offsets and lengths must be at Character boundaries");
        }
        int beginIndex1 = offsetInBits / 16;
        int beginIndex2 = otherOffsetInBits / 16;
        int endIndex1 = beginIndex1 + lengthInBits / 16;
        int endIndex2 = beginIndex2 + otherLengthInBits / 16;
        int length = Math.max(endIndex1, endIndex2);
        char k2 = '\u0000';
        char f2 = '\u0000';
        for (int i2 = 0; i2 < length; ++i2) {
            int index1 = beginIndex1 + i2;
            int index2 = beginIndex2 + i2;
            k2 = index1 >= endIndex1 ? (char)'\u0000' : key.charAt(index1);
            if (k2 != (f2 = other == null || index2 >= endIndex2 ? (char)'\u0000' : other.charAt(index2))) {
                int x2 = k2 ^ f2;
                return i2 * 16 + Integer.numberOfLeadingZeros(x2) - 16;
            }
            if (k2 == '\u0000') continue;
            allNull = false;
        }
        if (allNull) {
            return -1;
        }
        return -2;
    }

    @Override
    public boolean isBitSet(String key, int bitIndex, int lengthInBits) {
        if (key == null || bitIndex >= lengthInBits) {
            return false;
        }
        int index = bitIndex / 16;
        int bit2 = bitIndex % 16;
        return (key.charAt(index) & StringKeyAnalyzer.mask(bit2)) != 0;
    }

    @Override
    public boolean isPrefix(String prefix, int offsetInBits, int lengthInBits, String key) {
        if (offsetInBits % 16 != 0 || lengthInBits % 16 != 0) {
            throw new IllegalArgumentException("Cannot determine prefix outside of Character boundaries");
        }
        String s1 = prefix.substring(offsetInBits / 16, lengthInBits / 16);
        return key.startsWith(s1);
    }
}

