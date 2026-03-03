/*
 * Decompiled with CFR 0.152.
 */
public class rh
extends rd {
    ps a;
    pr b;
    float c;

    public rh(ps ps2, float f2) {
        this.a = ps2;
        this.c = f2;
        this.a(5);
    }

    @Override
    public boolean a() {
        this.b = this.a.u();
        if (this.b == null) {
            return false;
        }
        double d2 = this.a.h(this.b);
        if (d2 < 4.0 || d2 > 16.0) {
            return false;
        }
        if (!this.a.C) {
            return false;
        }
        return this.a.bc().nextInt(5) == 0;
    }

    @Override
    public boolean b() {
        return !this.a.C;
    }

    @Override
    public void c() {
        double d2 = this.b.s - this.a.s;
        \u2603 = this.b.u - this.a.u;
        float \u26032 = ns.a(d2 * d2 + \u2603 * \u2603);
        this.a.v += d2 / (double)\u26032 * 0.5 * (double)0.8f + this.a.v * (double)0.2f;
        this.a.x += \u2603 / (double)\u26032 * 0.5 * (double)0.8f + this.a.x * (double)0.2f;
        this.a.w = this.c;
    }
}

