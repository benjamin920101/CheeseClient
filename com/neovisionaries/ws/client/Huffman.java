/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ByteArray;
import com.neovisionaries.ws.client.FormatException;
import com.neovisionaries.ws.client.Misc;

class Huffman {
    private final int mMinCodeLen;
    private final int mMaxCodeLen;
    private final int[] mMaxCodeValsFromCodeLen;
    private final int[] mSymsFromCodeVal;

    public Huffman(int[] codeLensFromSym) {
        this.mMinCodeLen = Math.max(Misc.min(codeLensFromSym), 1);
        this.mMaxCodeLen = Misc.max(codeLensFromSym);
        int[] countsFromCodeLen = Huffman.createCountsFromCodeLen(codeLensFromSym, this.mMaxCodeLen);
        Object[] out = new Object[2];
        this.mMaxCodeValsFromCodeLen = Huffman.createMaxCodeValsFromCodeLen(countsFromCodeLen, this.mMaxCodeLen, out);
        int[] codeValsFromCodeLen = (int[])out[0];
        int maxCodeVal = (Integer)out[1];
        this.mSymsFromCodeVal = Huffman.createSymsFromCodeVal(codeLensFromSym, codeValsFromCodeLen, maxCodeVal);
    }

    private static int[] createIntArray(int size, int initialValue) {
        int[] array = new int[size];
        for (int i2 = 0; i2 < size; ++i2) {
            array[i2] = initialValue;
        }
        return array;
    }

    private static int[] createCountsFromCodeLen(int[] codeLensFromSym, int maxCodeLen) {
        int[] countsFromCodeLen = new int[maxCodeLen + 1];
        for (int symbol = 0; symbol < codeLensFromSym.length; ++symbol) {
            int codeLength;
            int n2 = codeLength = codeLensFromSym[symbol];
            countsFromCodeLen[n2] = countsFromCodeLen[n2] + 1;
        }
        return countsFromCodeLen;
    }

    private static int[] createMaxCodeValsFromCodeLen(int[] countsFromCodeLen, int maxCodeLen, Object[] out) {
        int[] maxCodeValsFromCodeLen = Huffman.createIntArray(maxCodeLen + 1, -1);
        int minCodeVal = 0;
        int maxCodeVal = 0;
        countsFromCodeLen[0] = 0;
        int[] codeValsFromCodeLen = new int[maxCodeLen + 1];
        for (int codeLen = 1; codeLen < countsFromCodeLen.length; ++codeLen) {
            int prevCount = countsFromCodeLen[codeLen - 1];
            codeValsFromCodeLen[codeLen] = minCodeVal = minCodeVal + prevCount << 1;
            maxCodeValsFromCodeLen[codeLen] = maxCodeVal = minCodeVal + countsFromCodeLen[codeLen] - 1;
        }
        out[0] = codeValsFromCodeLen;
        out[1] = maxCodeVal;
        return maxCodeValsFromCodeLen;
    }

    private static int[] createSymsFromCodeVal(int[] codeLensFromSym, int[] codeValsFromCodeLen, int maxCodeVal) {
        int[] symsFromCodeVal = new int[maxCodeVal + 1];
        for (int sym = 0; sym < codeLensFromSym.length; ++sym) {
            int codeLen = codeLensFromSym[sym];
            if (codeLen == 0) continue;
            int n2 = codeLen;
            codeValsFromCodeLen[n2] = codeValsFromCodeLen[n2] + 1;
            symsFromCodeVal[codeVal] = sym;
        }
        return symsFromCodeVal;
    }

    public int readSym(ByteArray data, int[] bitIndex) throws FormatException {
        for (int codeLen = this.mMinCodeLen; codeLen <= this.mMaxCodeLen; ++codeLen) {
            int codeVal;
            int maxCodeVal = this.mMaxCodeValsFromCodeLen[codeLen];
            if (maxCodeVal < 0 || maxCodeVal < (codeVal = data.getHuffmanBits(bitIndex[0], codeLen))) continue;
            int sym = this.mSymsFromCodeVal[codeVal];
            bitIndex[0] = bitIndex[0] + codeLen;
            return sym;
        }
        String message = String.format("[%s] Bad code at the bit index '%d'.", this.getClass().getSimpleName(), bitIndex[0]);
        throw new FormatException(message);
    }
}

