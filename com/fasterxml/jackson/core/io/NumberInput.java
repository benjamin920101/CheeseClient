/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;

public final class NumberInput {
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final long L_BILLION = 1000000000L;
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    public static int parseInt(char[] ch, int off, int len) {
        int num = ch[off + len - 1] - 48;
        switch (len) {
            case 9: {
                num += (ch[off++] - 48) * 100000000;
            }
            case 8: {
                num += (ch[off++] - 48) * 10000000;
            }
            case 7: {
                num += (ch[off++] - 48) * 1000000;
            }
            case 6: {
                num += (ch[off++] - 48) * 100000;
            }
            case 5: {
                num += (ch[off++] - 48) * 10000;
            }
            case 4: {
                num += (ch[off++] - 48) * 1000;
            }
            case 3: {
                num += (ch[off++] - 48) * 100;
            }
            case 2: {
                num += (ch[off] - 48) * 10;
            }
        }
        return num;
    }

    public static int parseInt(String s2) {
        char c2 = s2.charAt(0);
        int len = s2.length();
        boolean neg = c2 == '-';
        int offset = 1;
        if (neg) {
            if (len == 1 || len > 10) {
                return Integer.parseInt(s2);
            }
            c2 = s2.charAt(offset++);
        } else if (len > 9) {
            return Integer.parseInt(s2);
        }
        if (c2 > '9' || c2 < '0') {
            return Integer.parseInt(s2);
        }
        int num = c2 - 48;
        if (offset < len) {
            if ((c2 = s2.charAt(offset++)) > '9' || c2 < '0') {
                return Integer.parseInt(s2);
            }
            num = num * 10 + (c2 - 48);
            if (offset < len) {
                if ((c2 = s2.charAt(offset++)) > '9' || c2 < '0') {
                    return Integer.parseInt(s2);
                }
                num = num * 10 + (c2 - 48);
                if (offset < len) {
                    do {
                        if ((c2 = s2.charAt(offset++)) > '9' || c2 < '0') {
                            return Integer.parseInt(s2);
                        }
                        num = num * 10 + (c2 - 48);
                    } while (offset < len);
                }
            }
        }
        return neg ? -num : num;
    }

    public static long parseLong(char[] ch, int off, int len) {
        int len1 = len - 9;
        long val = (long)NumberInput.parseInt(ch, off, len1) * 1000000000L;
        return val + (long)NumberInput.parseInt(ch, off + len1, 9);
    }

    public static long parseLong(String s2) {
        int length = s2.length();
        if (length <= 9) {
            return NumberInput.parseInt(s2);
        }
        return Long.parseLong(s2);
    }

    public static boolean inLongRange(char[] ch, int off, int len, boolean negative) {
        String cmpStr = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmpStr.length();
        if (len < cmpLen) {
            return true;
        }
        if (len > cmpLen) {
            return false;
        }
        for (int i2 = 0; i2 < cmpLen; ++i2) {
            int diff = ch[off + i2] - cmpStr.charAt(i2);
            if (diff == 0) continue;
            return diff < 0;
        }
        return true;
    }

    public static boolean inLongRange(String s2, boolean negative) {
        String cmp = negative ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int cmpLen = cmp.length();
        int alen = s2.length();
        if (alen < cmpLen) {
            return true;
        }
        if (alen > cmpLen) {
            return false;
        }
        for (int i2 = 0; i2 < cmpLen; ++i2) {
            int diff = s2.charAt(i2) - cmp.charAt(i2);
            if (diff == 0) continue;
            return diff < 0;
        }
        return true;
    }

    public static int parseAsInt(String s2, int def) {
        char c2;
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        int i2 = 0;
        if (i2 < len) {
            c2 = s2.charAt(0);
            if (c2 == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c2 == '-') {
                ++i2;
            }
        }
        while (i2 < len) {
            c2 = s2.charAt(i2);
            if (c2 > '9' || c2 < '0') {
                try {
                    return (int)NumberInput.parseDouble(s2);
                }
                catch (NumberFormatException e2) {
                    return def;
                }
            }
            ++i2;
        }
        try {
            return Integer.parseInt(s2);
        }
        catch (NumberFormatException numberFormatException) {
            return def;
        }
    }

    public static long parseAsLong(String s2, long def) {
        char c2;
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        int i2 = 0;
        if (i2 < len) {
            c2 = s2.charAt(0);
            if (c2 == '+') {
                s2 = s2.substring(1);
                len = s2.length();
            } else if (c2 == '-') {
                ++i2;
            }
        }
        while (i2 < len) {
            c2 = s2.charAt(i2);
            if (c2 > '9' || c2 < '0') {
                try {
                    return (long)NumberInput.parseDouble(s2);
                }
                catch (NumberFormatException e2) {
                    return def;
                }
            }
            ++i2;
        }
        try {
            return Long.parseLong(s2);
        }
        catch (NumberFormatException numberFormatException) {
            return def;
        }
    }

    public static double parseAsDouble(String s2, double def) {
        if (s2 == null) {
            return def;
        }
        int len = (s2 = s2.trim()).length();
        if (len == 0) {
            return def;
        }
        try {
            return NumberInput.parseDouble(s2);
        }
        catch (NumberFormatException numberFormatException) {
            return def;
        }
    }

    public static double parseDouble(String s2) throws NumberFormatException {
        if (NASTY_SMALL_DOUBLE.equals(s2)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(s2);
    }

    public static BigDecimal parseBigDecimal(String s2) throws NumberFormatException {
        try {
            return new BigDecimal(s2);
        }
        catch (NumberFormatException e2) {
            throw NumberInput._badBD(s2);
        }
    }

    public static BigDecimal parseBigDecimal(char[] b2) throws NumberFormatException {
        return NumberInput.parseBigDecimal(b2, 0, b2.length);
    }

    public static BigDecimal parseBigDecimal(char[] b2, int off, int len) throws NumberFormatException {
        try {
            return new BigDecimal(b2, off, len);
        }
        catch (NumberFormatException e2) {
            throw NumberInput._badBD(new String(b2, off, len));
        }
    }

    private static NumberFormatException _badBD(String s2) {
        return new NumberFormatException("Value \"" + s2 + "\" can not be represented as BigDecimal");
    }
}

