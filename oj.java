/*
 * Decompiled with CFR 0.152.
 */
public enum oj {
    a(0, "options.difficulty.peaceful"),
    b(1, "options.difficulty.easy"),
    c(2, "options.difficulty.normal"),
    d(3, "options.difficulty.hard");

    private static final oj[] e;
    private final int f;
    private final String g;

    private oj(int n3, String string2) {
        this.f = n3;
        this.g = string2;
    }

    public int a() {
        return this.f;
    }

    public static oj a(int n2) {
        return e[n2 % e.length];
    }

    public String b() {
        return this.g;
    }

    static {
        e = new oj[oj.values().length];
        oj[] ojArray = oj.values();
        int \u26032 = ojArray.length;
        for (int i2 = 0; i2 < \u26032; ++i2) {
            oj.e[\u2603.f] = \u2603 = ojArray[i2];
        }
    }
}

