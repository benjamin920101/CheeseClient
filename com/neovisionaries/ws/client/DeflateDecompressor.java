/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ByteArray;
import com.neovisionaries.ws.client.DeflateUtil;
import com.neovisionaries.ws.client.FixedDistanceHuffman;
import com.neovisionaries.ws.client.FixedLiteralLengthHuffman;
import com.neovisionaries.ws.client.FormatException;
import com.neovisionaries.ws.client.Huffman;

class DeflateDecompressor {
    DeflateDecompressor() {
    }

    public static void decompress(ByteArray input, ByteArray output) throws FormatException {
        DeflateDecompressor.decompress(input, 0, output);
    }

    private static void decompress(ByteArray input, int index, ByteArray output) throws FormatException {
        int[] bitIndex = new int[]{index * 8};
        while (DeflateDecompressor.inflateBlock(input, bitIndex, output)) {
        }
    }

    private static boolean inflateBlock(ByteArray input, int[] bitIndex, ByteArray output) throws FormatException {
        boolean last = input.readBit(bitIndex);
        int type = input.readBits(bitIndex, 2);
        switch (type) {
            case 0: {
                DeflateDecompressor.inflatePlainBlock(input, bitIndex, output);
                break;
            }
            case 1: {
                DeflateDecompressor.inflateFixedBlock(input, bitIndex, output);
                break;
            }
            case 2: {
                DeflateDecompressor.inflateDynamicBlock(input, bitIndex, output);
                break;
            }
            default: {
                String message = String.format("[%s] Bad compression type '11' at the bit index '%d'.", DeflateDecompressor.class.getSimpleName(), bitIndex[0]);
                throw new FormatException(message);
            }
        }
        if (input.length() <= bitIndex[0] / 8) {
            last = true;
        }
        return !last;
    }

    private static void inflatePlainBlock(ByteArray input, int[] bitIndex, ByteArray output) {
        int bi2 = bitIndex[0] + 7 & 0xFFFFFFF8;
        int index = bi2 / 8;
        int len = (input.get(index) & 0xFF) + (input.get(index + 1) & 0xFF) * 256;
        output.put(input, index += 4, len);
        bitIndex[0] = (index + len) * 8;
    }

    private static void inflateFixedBlock(ByteArray input, int[] bitIndex, ByteArray output) throws FormatException {
        DeflateDecompressor.inflateData(input, bitIndex, output, FixedLiteralLengthHuffman.getInstance(), FixedDistanceHuffman.getInstance());
    }

    private static void inflateDynamicBlock(ByteArray input, int[] bitIndex, ByteArray output) throws FormatException {
        Huffman[] tables = new Huffman[2];
        DeflateUtil.readDynamicTables(input, bitIndex, tables);
        DeflateDecompressor.inflateData(input, bitIndex, output, tables[0], tables[1]);
    }

    private static void inflateData(ByteArray input, int[] bitIndex, ByteArray output, Huffman literalLengthHuffman, Huffman distanceHuffman) throws FormatException {
        int literalLength;
        while ((literalLength = literalLengthHuffman.readSym(input, bitIndex)) != 256) {
            if (0 <= literalLength && literalLength <= 255) {
                output.put(literalLength);
                continue;
            }
            int length = DeflateUtil.readLength(input, bitIndex, literalLength);
            int distance = DeflateUtil.readDistance(input, bitIndex, distanceHuffman);
            DeflateDecompressor.duplicate(length, distance, output);
        }
    }

    private static void duplicate(int length, int distance, ByteArray output) {
        int initialPosition;
        int sourceLength = output.length();
        byte[] target = new byte[length];
        int sourceIndex = initialPosition = sourceLength - distance;
        int targetIndex = 0;
        while (targetIndex < length) {
            if (sourceLength <= sourceIndex) {
                sourceIndex = initialPosition;
            }
            target[targetIndex] = output.get(sourceIndex);
            ++targetIndex;
            ++sourceIndex;
        }
        output.put(target);
    }
}

