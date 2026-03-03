/*
 * Decompiled with CFR 0.152.
 */
package okio;

public final class Utf8 {
    private Utf8() {
    }

    public static long size(String string) {
        return Utf8.size(string, 0, string.length());
    }

    public static long size(String string, int beginIndex, int endIndex) {
        if (string == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + beginIndex);
        }
        if (endIndex < beginIndex) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + endIndex + " < " + beginIndex);
        }
        if (endIndex > string.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + endIndex + " > " + string.length());
        }
        long result = 0L;
        int i2 = beginIndex;
        while (i2 < endIndex) {
            char low;
            char c2 = string.charAt(i2);
            if (c2 < '\u0080') {
                ++result;
                ++i2;
                continue;
            }
            if (c2 < '\u0800') {
                result += 2L;
                ++i2;
                continue;
            }
            if (c2 < '\ud800' || c2 > '\udfff') {
                result += 3L;
                ++i2;
                continue;
            }
            char c3 = low = i2 + 1 < endIndex ? string.charAt(i2 + 1) : (char)'\u0000';
            if (c2 > '\udbff' || low < '\udc00' || low > '\udfff') {
                ++result;
                ++i2;
                continue;
            }
            result += 4L;
            i2 += 2;
        }
        return result;
    }
}

