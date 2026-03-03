/*
 * Decompiled with CFR 0.152.
 */
public class bba
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;
    public bct f;
    public bct g;
    public bct h;

    public bba() {
        int n2 = 16;
        this.a = new bct(this, 0, 0);
        this.a.a(-2.0f, -6.0f, -2.0f, 4, 6, 3, 0.0f);
        this.a.a(0.0f, -1 + n2, -4.0f);
        this.g = new bct(this, 14, 0);
        this.g.a(-2.0f, -4.0f, -4.0f, 4, 2, 2, 0.0f);
        this.g.a(0.0f, -1 + n2, -4.0f);
        this.h = new bct(this, 14, 4);
        this.h.a(-1.0f, -2.0f, -3.0f, 2, 2, 2, 0.0f);
        this.h.a(0.0f, -1 + n2, -4.0f);
        this.b = new bct(this, 0, 9);
        this.b.a(-3.0f, -4.0f, -3.0f, 6, 8, 6, 0.0f);
        this.b.a(0.0f, n2, 0.0f);
        this.c = new bct(this, 26, 0);
        this.c.a(-1.0f, 0.0f, -3.0f, 3, 5, 3);
        this.c.a(-2.0f, 3 + n2, 1.0f);
        this.d = new bct(this, 26, 0);
        this.d.a(-1.0f, 0.0f, -3.0f, 3, 5, 3);
        this.d.a(1.0f, 3 + n2, 1.0f);
        this.e = new bct(this, 24, 13);
        this.e.a(0.0f, 0.0f, -3.0f, 1, 4, 6);
        this.e.a(-4.0f, -3 + n2, 0.0f);
        this.f = new bct(this, 24, 13);
        this.f.a(-1.0f, 0.0f, -3.0f, 1, 4, 6);
        this.f.a(4.0f, -3 + n2, 0.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        if (this.r) {
            \u2603 = 2.0f;
            bfl.E();
            bfl.b(0.0f, 5.0f * f7, 2.0f * f7);
            this.a.a(f7);
            this.g.a(f7);
            this.h.a(f7);
            bfl.F();
            bfl.E();
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            bfl.F();
        } else {
            this.a.a(f7);
            this.g.a(f7);
            this.h.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        this.a.f = f6 / 57.295776f;
        this.a.g = f5 / 57.295776f;
        this.g.f = this.a.f;
        this.g.g = this.a.g;
        this.h.f = this.a.f;
        this.h.g = this.a.g;
        this.b.f = 1.5707964f;
        this.c.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
        this.d.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.e.h = f4;
        this.f.h = -f4;
    }
}

