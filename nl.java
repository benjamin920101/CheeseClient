/*
 * Decompiled with CFR 0.152.
 */
public class nl {
    private static final Integer[] a = new Integer[65535];

    public static Integer a(int n2) {
        if (n2 > 0 && n2 < a.length) {
            return a[n2];
        }
        return n2;
    }

    static {
        int n2 = a.length;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            nl.a[\u2603] = \u2603;
        }
    }
}

