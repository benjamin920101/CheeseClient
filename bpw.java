/*
 * Decompiled with CFR 0.152.
 */
public class bpw {
    private final jy a;
    private final boolean b;
    private double c;
    private double d;

    public bpw(jy jy2, double d2, double d3, boolean bl2) {
        this.a = jy2;
        this.c = d2;
        this.d = d3;
        this.b = bl2;
    }

    public bpw(bpw bpw2) {
        this.a = bpw2.a;
        this.c = bpw2.c;
        this.d = bpw2.d;
        this.b = bpw2.b;
    }

    public jy a() {
        return this.a;
    }

    public double b() {
        return this.c;
    }

    public void a(double d2) {
        this.c = d2;
    }

    public double c() {
        return this.d;
    }

    public void b(double d2) {
        this.d = d2;
    }

    public boolean d() {
        return this.b;
    }
}

