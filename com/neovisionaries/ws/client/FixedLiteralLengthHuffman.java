/*
 * Decompiled with CFR 0.152.
 */
package com.neovisionaries.ws.client;

import com.neovisionaries.ws.client.Huffman;

class FixedLiteralLengthHuffman
extends Huffman {
    private static final FixedLiteralLengthHuffman INSTANCE = new FixedLiteralLengthHuffman();

    private FixedLiteralLengthHuffman() {
        super(FixedLiteralLengthHuffman.buildCodeLensFromSym());
    }

    private static int[] buildCodeLensFromSym() {
        int symbol;
        int[] codeLengths = new int[288];
        for (symbol = 0; symbol < 144; ++symbol) {
            codeLengths[symbol] = 8;
        }
        while (symbol < 256) {
            codeLengths[symbol] = 9;
            ++symbol;
        }
        while (symbol < 280) {
            codeLengths[symbol] = 7;
            ++symbol;
        }
        while (symbol < 288) {
            codeLengths[symbol] = 8;
            ++symbol;
        }
        return codeLengths;
    }

    public static FixedLiteralLengthHuffman getInstance() {
        return INSTANCE;
    }
}

