/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.ByteArray;
import com.neovisionaries.ws.client.FormatException;
import com.neovisionaries.ws.client.Huffman;

class DeflateUtil {
    private static int[] INDICES_FROM_CODE_LENGTH_ORDER = new int[]{16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    DeflateUtil() {
    }

    public static void readDynamicTables(ByteArray input, int[] bitIndex, Huffman[] tables) throws FormatException {
        int hlit = input.readBits(bitIndex, 5) + 257;
        int hdist = input.readBits(bitIndex, 5) + 1;
        int hclen = input.readBits(bitIndex, 4) + 4;
        int[] codeLengthsFromCodeLengthValue = new int[19];
        for (int i2 = 0; i2 < hclen; ++i2) {
            byte codeLengthOfCodeLengthValue = (byte)input.readBits(bitIndex, 3);
            int index = DeflateUtil.codeLengthOrderToIndex(i2);
            codeLengthsFromCodeLengthValue[index] = codeLengthOfCodeLengthValue;
        }
        Huffman codeLengthHuffman = new Huffman(codeLengthsFromCodeLengthValue);
        int[] codeLengthsFromLiteralLengthCode = new int[hlit];
        DeflateUtil.readCodeLengths(input, bitIndex, codeLengthsFromLiteralLengthCode, codeLengthHuffman);
        Huffman literalLengthHuffman = new Huffman(codeLengthsFromLiteralLengthCode);
        int[] codeLengthsFromDistanceCode = new int[hdist];
        DeflateUtil.readCodeLengths(input, bitIndex, codeLengthsFromDistanceCode, codeLengthHuffman);
        Huffman distanceHuffman = new Huffman(codeLengthsFromDistanceCode);
        tables[0] = literalLengthHuffman;
        tables[1] = distanceHuffman;
    }

    private static void readCodeLengths(ByteArray input, int[] bitIndex, int[] codeLengths, Huffman codeLengthHuffman) throws FormatException {
        for (int i2 = 0; i2 < codeLengths.length; ++i2) {
            int repeatCount;
            int codeLength = codeLengthHuffman.readSym(input, bitIndex);
            if (0 <= codeLength && codeLength <= 15) {
                codeLengths[i2] = codeLength;
                continue;
            }
            switch (codeLength) {
                case 16: {
                    codeLength = codeLengths[i2 - 1];
                    repeatCount = input.readBits(bitIndex, 2) + 3;
                    break;
                }
                case 17: {
                    codeLength = 0;
                    repeatCount = input.readBits(bitIndex, 3) + 3;
                    break;
                }
                case 18: {
                    codeLength = 0;
                    repeatCount = input.readBits(bitIndex, 7) + 11;
                    break;
                }
                default: {
                    String message = String.format("[%s] Bad code length '%d' at the bit index '%d'.", DeflateUtil.class.getSimpleName(), codeLength, bitIndex);
                    throw new FormatException(message);
                }
            }
            for (int j2 = 0; j2 < repeatCount; ++j2) {
                codeLengths[i2 + j2] = codeLength;
            }
            i2 += repeatCount - 1;
        }
    }

    private static int codeLengthOrderToIndex(int order) {
        return INDICES_FROM_CODE_LENGTH_ORDER[order];
    }

    public static int readLength(ByteArray input, int[] bitIndex, int literalLength) throws FormatException {
        int nBits;
        int baseValue;
        switch (literalLength) {
            case 257: 
            case 258: 
            case 259: 
            case 260: 
            case 261: 
            case 262: 
            case 263: 
            case 264: {
                return literalLength - 254;
            }
            case 265: {
                baseValue = 11;
                nBits = 1;
                break;
            }
            case 266: {
                baseValue = 13;
                nBits = 1;
                break;
            }
            case 267: {
                baseValue = 15;
                nBits = 1;
                break;
            }
            case 268: {
                baseValue = 17;
                nBits = 1;
                break;
            }
            case 269: {
                baseValue = 19;
                nBits = 2;
                break;
            }
            case 270: {
                baseValue = 23;
                nBits = 2;
                break;
            }
            case 271: {
                baseValue = 27;
                nBits = 2;
                break;
            }
            case 272: {
                baseValue = 31;
                nBits = 2;
                break;
            }
            case 273: {
                baseValue = 35;
                nBits = 3;
                break;
            }
            case 274: {
                baseValue = 43;
                nBits = 3;
                break;
            }
            case 275: {
                baseValue = 51;
                nBits = 3;
                break;
            }
            case 276: {
                baseValue = 59;
                nBits = 3;
                break;
            }
            case 277: {
                baseValue = 67;
                nBits = 4;
                break;
            }
            case 278: {
                baseValue = 83;
                nBits = 4;
                break;
            }
            case 279: {
                baseValue = 99;
                nBits = 4;
                break;
            }
            case 280: {
                baseValue = 115;
                nBits = 4;
                break;
            }
            case 281: {
                baseValue = 131;
                nBits = 5;
                break;
            }
            case 282: {
                baseValue = 163;
                nBits = 5;
                break;
            }
            case 283: {
                baseValue = 195;
                nBits = 5;
                break;
            }
            case 284: {
                baseValue = 227;
                nBits = 5;
                break;
            }
            case 285: {
                return 258;
            }
            default: {
                String message = String.format("[%s] Bad literal/length code '%d' at the bit index '%d'.", DeflateUtil.class.getSimpleName(), literalLength, bitIndex[0]);
                throw new FormatException(message);
            }
        }
        int n2 = input.readBits(bitIndex, nBits);
        return baseValue + n2;
    }

    public static int readDistance(ByteArray input, int[] bitIndex, Huffman distanceHuffman) throws FormatException {
        int nBits;
        int baseValue;
        int code = distanceHuffman.readSym(input, bitIndex);
        switch (code) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return code + 1;
            }
            case 4: {
                baseValue = 5;
                nBits = 1;
                break;
            }
            case 5: {
                baseValue = 7;
                nBits = 1;
                break;
            }
            case 6: {
                baseValue = 9;
                nBits = 2;
                break;
            }
            case 7: {
                baseValue = 13;
                nBits = 2;
                break;
            }
            case 8: {
                baseValue = 17;
                nBits = 3;
                break;
            }
            case 9: {
                baseValue = 25;
                nBits = 3;
                break;
            }
            case 10: {
                baseValue = 33;
                nBits = 4;
                break;
            }
            case 11: {
                baseValue = 49;
                nBits = 4;
                break;
            }
            case 12: {
                baseValue = 65;
                nBits = 5;
                break;
            }
            case 13: {
                baseValue = 97;
                nBits = 5;
                break;
            }
            case 14: {
                baseValue = 129;
                nBits = 6;
                break;
            }
            case 15: {
                baseValue = 193;
                nBits = 6;
                break;
            }
            case 16: {
                baseValue = 257;
                nBits = 7;
                break;
            }
            case 17: {
                baseValue = 385;
                nBits = 7;
                break;
            }
            case 18: {
                baseValue = 513;
                nBits = 8;
                break;
            }
            case 19: {
                baseValue = 769;
                nBits = 8;
                break;
            }
            case 20: {
                baseValue = 1025;
                nBits = 9;
                break;
            }
            case 21: {
                baseValue = 1537;
                nBits = 9;
                break;
            }
            case 22: {
                baseValue = 2049;
                nBits = 10;
                break;
            }
            case 23: {
                baseValue = 3073;
                nBits = 10;
                break;
            }
            case 24: {
                baseValue = 4097;
                nBits = 11;
                break;
            }
            case 25: {
                baseValue = 6145;
                nBits = 11;
                break;
            }
            case 26: {
                baseValue = 8193;
                nBits = 12;
                break;
            }
            case 27: {
                baseValue = 12289;
                nBits = 12;
                break;
            }
            case 28: {
                baseValue = 16385;
                nBits = 13;
                break;
            }
            case 29: {
                baseValue = 24577;
                nBits = 13;
                break;
            }
            default: {
                String message = String.format("[%s] Bad distance code '%d' at the bit index '%d'.", DeflateUtil.class.getSimpleName(), code, bitIndex[0]);
                throw new FormatException(message);
            }
        }
        int n2 = input.readBits(bitIndex, nBits);
        return baseValue + n2;
    }
}

