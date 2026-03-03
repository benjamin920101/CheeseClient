/*
 * Decompiled with CFR 0.152.
 */
public class bbc
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;
    public bct f;
    public bct g;

    public bbc() {
        this(0.0f);
    }

    public bbc(float f2) {
        int n2 = 6;
        this.a = new bct(this, 0, 0);
        this.a.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2);
        this.a.a(0.0f, n2, 0.0f);
        this.b = new bct(this, 32, 0);
        this.b.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2 + 0.5f);
        this.b.a(0.0f, n2, 0.0f);
        this.c = new bct(this, 16, 16);
        this.c.a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f2);
        this.c.a(0.0f, n2, 0.0f);
        this.d = new bct(this, 0, 16);
        this.d.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, f2);
        this.d.a(-2.0f, 12 + n2, 4.0f);
        this.e = new bct(this, 0, 16);
        this.e.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, f2);
        this.e.a(2.0f, 12 + n2, 4.0f);
        this.f = new bct(this, 0, 16);
        this.f.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, f2);
        this.f.a(-2.0f, 12 + n2, -4.0f);
        this.g = new bct(this, 0, 16);
        this.g.a(-2.0f, 0.0f, -2.0f, 4, 6, 4, f2);
        this.g.a(2.0f, 12 + n2, -4.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.a(f7);
        this.c.a(f7);
        this.d.a(f7);
        this.e.a(f7);
        this.f.a(f7);
        this.g.a(f7);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        this.a.g = f5 / 57.295776f;
        this.a.f = f6 / 57.295776f;
        this.d.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
        this.e.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.f.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.g.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
    }
}

