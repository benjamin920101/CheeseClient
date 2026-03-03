/*
 * Decompiled with CFR 0.152.
 */
public class kw {
    private final int a;
    private final cj b;
    private int c;
    private int d;

    public kw(int n2, cj cj2) {
        this.a = n2;
        this.b = cj2;
    }

    public cj b() {
        return this.b;
    }

    public void a(int n2) {
        if (n2 > 10) {
            n2 = 10;
        }
        this.c = n2;
    }

    public int c() {
        return this.c;
    }

    public void b(int n2) {
        this.d = n2;
    }

    public int d() {
        return this.d;
    }
}

