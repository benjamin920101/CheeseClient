/*
 * Decompiled with CFR 0.152.
 */
public enum wo {
    a(0, "cape"),
    b(1, "jacket"),
    c(2, "left_sleeve"),
    d(3, "right_sleeve"),
    e(4, "left_pants_leg"),
    f(5, "right_pants_leg"),
    g(6, "hat");

    private final int h;
    private final int i;
    private final String j;
    private final eu k;

    private wo(int n3, String string2) {
        this.h = n3;
        this.i = 1 << n3;
        this.j = string2;
        this.k = new fb("options.modelPart." + string2, new Object[0]);
    }

    public int a() {
        return this.i;
    }

    public int b() {
        return this.h;
    }

    public String c() {
        return this.j;
    }

    public eu d() {
        return this.k;
    }
}

