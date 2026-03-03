/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes {
    private static final char[] HC;
    private static final byte[] HB;
    private static final int[] sInputCodes;
    private static final int[] sInputCodesUTF8;
    private static final int[] sInputCodesJsNames;
    private static final int[] sInputCodesUtf8JsNames;
    private static final int[] sInputCodesComment;
    private static final int[] sInputCodesWS;
    private static final int[] sOutputEscapes128;
    private static final int[] sHexValues;

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeUtf8() {
        return sInputCodesUTF8;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] getInputCodeUtf8JsNames() {
        return sInputCodesUtf8JsNames;
    }

    public static int[] getInputCodeComment() {
        return sInputCodesComment;
    }

    public static int[] getInputCodeWS() {
        return sInputCodesWS;
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int[] get7BitOutputEscapes(int quoteChar) {
        if (quoteChar == 34) {
            return sOutputEscapes128;
        }
        return AltEscapes.instance.escapesFor(quoteChar);
    }

    public static int charToHex(int ch) {
        return sHexValues[ch & 0xFF];
    }

    public static void appendQuoted(StringBuilder sb2, String content) {
        int[] escCodes = sOutputEscapes128;
        int escLen = escCodes.length;
        int len = content.length();
        for (int i2 = 0; i2 < len; ++i2) {
            char c2 = content.charAt(i2);
            if (c2 >= escLen || escCodes[c2] == 0) {
                sb2.append(c2);
                continue;
            }
            sb2.append('\\');
            int escCode = escCodes[c2];
            if (escCode < 0) {
                sb2.append('u');
                sb2.append('0');
                sb2.append('0');
                char value = c2;
                sb2.append(HC[value >> 4]);
                sb2.append(HC[value & 0xF]);
                continue;
            }
            sb2.append((char)escCode);
        }
    }

    public static char[] copyHexChars() {
        return (char[])HC.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[])HB.clone();
    }

    static {
        int i2;
        int i3;
        HC = "0123456789ABCDEF".toCharArray();
        int len = HC.length;
        HB = new byte[len];
        for (i3 = 0; i3 < len; ++i3) {
            CharTypes.HB[i3] = (byte)HC[i3];
        }
        int[] table = new int[256];
        for (i3 = 0; i3 < 32; ++i3) {
            table[i3] = -1;
        }
        table[34] = 1;
        table[92] = 1;
        sInputCodes = table;
        table = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, table, 0, table.length);
        for (int c2 = 128; c2 < 256; ++c2) {
            int code = (c2 & 0xE0) == 192 ? 2 : ((c2 & 0xF0) == 224 ? 3 : ((c2 & 0xF8) == 240 ? 4 : -1));
            table[c2] = code;
        }
        sInputCodesUTF8 = table;
        table = new int[256];
        Arrays.fill(table, -1);
        for (i3 = 33; i3 < 256; ++i3) {
            if (!Character.isJavaIdentifierPart((char)i3)) continue;
            table[i3] = 0;
        }
        table[64] = 0;
        table[35] = 0;
        table[42] = 0;
        table[45] = 0;
        table[43] = 0;
        sInputCodesJsNames = table;
        table = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, table, 0, table.length);
        Arrays.fill(table, 128, 128, 0);
        sInputCodesUtf8JsNames = table;
        int[] buf = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
        Arrays.fill(buf, 0, 32, -1);
        buf[9] = 0;
        buf[10] = 10;
        buf[13] = 13;
        buf[42] = 42;
        sInputCodesComment = buf;
        buf = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
        Arrays.fill(buf, 0, 32, -1);
        buf[32] = 1;
        buf[9] = 1;
        buf[10] = 10;
        buf[13] = 13;
        buf[47] = 47;
        buf[35] = 35;
        sInputCodesWS = buf;
        table = new int[128];
        for (i3 = 0; i3 < 32; ++i3) {
            table[i3] = -1;
        }
        table[34] = 34;
        table[92] = 92;
        table[8] = 98;
        table[9] = 116;
        table[12] = 102;
        table[10] = 110;
        table[13] = 114;
        sOutputEscapes128 = table;
        sHexValues = new int[256];
        Arrays.fill(sHexValues, -1);
        for (i2 = 0; i2 < 10; ++i2) {
            CharTypes.sHexValues[48 + i2] = i2;
        }
        for (i2 = 0; i2 < 6; ++i2) {
            CharTypes.sHexValues[97 + i2] = 10 + i2;
            CharTypes.sHexValues[65 + i2] = 10 + i2;
        }
    }

    private static class AltEscapes {
        public static final AltEscapes instance = new AltEscapes();
        private int[][] _altEscapes = new int[128][];

        private AltEscapes() {
        }

        public int[] escapesFor(int quoteChar) {
            int[] esc = this._altEscapes[quoteChar];
            if (esc == null) {
                esc = Arrays.copyOf(sOutputEscapes128, 128);
                if (esc[quoteChar] == 0) {
                    esc[quoteChar] = -1;
                }
                this._altEscapes[quoteChar] = esc;
            }
            return esc;
        }
    }
}

