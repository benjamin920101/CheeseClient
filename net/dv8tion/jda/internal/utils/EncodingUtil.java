/*
 * Decompiled with CFR 0.152.
 */
package net.dv8tion.jda.internal.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class EncodingUtil {
    public static String encodeUTF8(String chars) {
        try {
            return URLEncoder.encode(chars, "UTF-8");
        }
        catch (UnsupportedEncodingException e2) {
            throw new AssertionError((Object)e2);
        }
    }

    public static String encodeCodepointsUTF8(String input) {
        if (!input.startsWith("U+")) {
            throw new IllegalArgumentException("Invalid format");
        }
        String[] codePoints = input.substring(2).split("\\s*U\\+\\s*");
        StringBuilder encoded = new StringBuilder();
        for (String part : codePoints) {
            String utf16 = EncodingUtil.decodeCodepoint(part, 16);
            String urlEncoded = EncodingUtil.encodeUTF8(utf16);
            encoded.append(urlEncoded);
        }
        return encoded.toString();
    }

    public static String decodeCodepoint(String codepoint) {
        if (!codepoint.startsWith("U+")) {
            throw new IllegalArgumentException("Invalid format");
        }
        return EncodingUtil.decodeCodepoint(codepoint.substring(2), 16);
    }

    public static String encodeCodepoints(String unicode) {
        return unicode.codePoints().mapToObj(code -> "U+" + Integer.toHexString(code)).collect(Collectors.joining());
    }

    private static String decodeCodepoint(String hex, int radix) {
        int codePoint = Integer.parseUnsignedInt(hex, radix);
        return String.valueOf(Character.toChars(codePoint));
    }

    public static String encodeReaction(String unicode) {
        if (unicode.startsWith("U+") || unicode.startsWith("u+")) {
            return EncodingUtil.encodeCodepointsUTF8(unicode);
        }
        return EncodingUtil.encodeUTF8(unicode);
    }
}

