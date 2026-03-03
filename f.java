/*
 * Decompiled with CFR 0.152.
 */
public class f {
    public static final char[] a = new char[]{'/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '\"', ':'};

    public static boolean a(char c2) {
        return c2 != '\u00a7' && c2 >= ' ' && c2 != '\u007f';
    }

    public static String a(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c2 : string.toCharArray()) {
            if (!f.a(c2)) continue;
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }
}

