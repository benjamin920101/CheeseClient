/*
 * Decompiled with CFR 0.152.
 */
public class avr {
    private final double a;
    private final double b;
    private int c;
    private int d;
    private int e;

    public avr(ave ave2) {
        this.c = ave2.d;
        this.d = ave2.e;
        this.e = 1;
        boolean bl2 = ave2.d();
        int \u26032 = ave2.t.aK;
        if (\u26032 == 0) {
            \u26032 = 1000;
        }
        while (this.e < \u26032 && this.c / (this.e + 1) >= 320 && this.d / (this.e + 1) >= 240) {
            ++this.e;
        }
        if (bl2 && this.e % 2 != 0 && this.e != 1) {
            --this.e;
        }
        this.a = (double)this.c / (double)this.e;
        this.b = (double)this.d / (double)this.e;
        this.c = ns.f(this.a);
        this.d = ns.f(this.b);
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public double c() {
        return this.a;
    }

    public double d() {
        return this.b;
    }

    public int e() {
        return this.e;
    }
}

