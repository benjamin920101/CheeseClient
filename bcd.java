/*
 * Decompiled with CFR 0.152.
 */
public class bcd
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;

    public bcd() {
        float f2 = 4.0f;
        \u2603 = 0.0f;
        this.c = new bct(this, 0, 0).b(64, 64);
        this.c.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, \u2603 - 0.5f);
        this.c.a(0.0f, 0.0f + f2, 0.0f);
        this.d = new bct(this, 32, 0).b(64, 64);
        this.d.a(-1.0f, 0.0f, -1.0f, 12, 2, 2, \u2603 - 0.5f);
        this.d.a(0.0f, 0.0f + f2 + 9.0f - 7.0f, 0.0f);
        this.e = new bct(this, 32, 0).b(64, 64);
        this.e.a(-1.0f, 0.0f, -1.0f, 12, 2, 2, \u2603 - 0.5f);
        this.e.a(0.0f, 0.0f + f2 + 9.0f - 7.0f, 0.0f);
        this.a = new bct(this, 0, 16).b(64, 64);
        this.a.a(-5.0f, -10.0f, -5.0f, 10, 10, 10, \u2603 - 0.5f);
        this.a.a(0.0f, 0.0f + f2 + 9.0f, 0.0f);
        this.b = new bct(this, 0, 36).b(64, 64);
        this.b.a(-6.0f, -12.0f, -6.0f, 12, 12, 12, \u2603 - 0.5f);
        this.b.a(0.0f, 0.0f + f2 + 20.0f, 0.0f);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        this.c.g = f5 / 57.295776f;
        this.c.f = f6 / 57.295776f;
        this.a.g = f5 / 57.295776f * 0.25f;
        float f8 = ns.a(this.a.g);
        \u2603 = ns.b(this.a.g);
        this.d.h = 1.0f;
        this.e.h = -1.0f;
        this.d.g = 0.0f + this.a.g;
        this.e.g = (float)Math.PI + this.a.g;
        this.d.c = \u2603 * 5.0f;
        this.d.e = -f8 * 5.0f;
        this.e.c = -\u2603 * 5.0f;
        this.e.e = f8 * 5.0f;
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.a(f7);
        this.b.a(f7);
        this.c.a(f7);
        this.d.a(f7);
        this.e.a(f7);
    }
}

