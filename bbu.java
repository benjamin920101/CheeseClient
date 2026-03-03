/*
 * Decompiled with CFR 0.152.
 */
public class bbu
extends bbo {
    bct a;
    bct b;
    bct c;
    bct d;
    bct e;
    bct f;
    bct g;
    bct h;
    bct i;
    bct j;
    bct k;
    bct l;
    private float m = 0.0f;
    private float n = 0.0f;

    public bbu() {
        this.a("head.main", 0, 0);
        this.a("head.nose", 0, 24);
        this.a("head.ear1", 0, 10);
        this.a("head.ear2", 6, 10);
        this.a = new bct(this, 26, 24);
        this.a.a(-1.0f, 5.5f, -3.7f, 2, 1, 7);
        this.a.a(3.0f, 17.5f, 3.7f);
        this.a.i = true;
        this.a(this.a, 0.0f, 0.0f, 0.0f);
        this.b = new bct(this, 8, 24);
        this.b.a(-1.0f, 5.5f, -3.7f, 2, 1, 7);
        this.b.a(-3.0f, 17.5f, 3.7f);
        this.b.i = true;
        this.a(this.b, 0.0f, 0.0f, 0.0f);
        this.c = new bct(this, 30, 15);
        this.c.a(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.c.a(3.0f, 17.5f, 3.7f);
        this.c.i = true;
        this.a(this.c, -0.34906584f, 0.0f, 0.0f);
        this.d = new bct(this, 16, 15);
        this.d.a(-1.0f, 0.0f, 0.0f, 2, 4, 5);
        this.d.a(-3.0f, 17.5f, 3.7f);
        this.d.i = true;
        this.a(this.d, -0.34906584f, 0.0f, 0.0f);
        this.e = new bct(this, 0, 0);
        this.e.a(-3.0f, -2.0f, -10.0f, 6, 5, 10);
        this.e.a(0.0f, 19.0f, 8.0f);
        this.e.i = true;
        this.a(this.e, -0.34906584f, 0.0f, 0.0f);
        this.f = new bct(this, 8, 15);
        this.f.a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.f.a(3.0f, 17.0f, -1.0f);
        this.f.i = true;
        this.a(this.f, -0.17453292f, 0.0f, 0.0f);
        this.g = new bct(this, 0, 15);
        this.g.a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.g.a(-3.0f, 17.0f, -1.0f);
        this.g.i = true;
        this.a(this.g, -0.17453292f, 0.0f, 0.0f);
        this.h = new bct(this, 32, 0);
        this.h.a(-2.5f, -4.0f, -5.0f, 5, 4, 5);
        this.h.a(0.0f, 16.0f, -1.0f);
        this.h.i = true;
        this.a(this.h, 0.0f, 0.0f, 0.0f);
        this.i = new bct(this, 52, 0);
        this.i.a(-2.5f, -9.0f, -1.0f, 2, 5, 1);
        this.i.a(0.0f, 16.0f, -1.0f);
        this.i.i = true;
        this.a(this.i, 0.0f, -0.2617994f, 0.0f);
        this.j = new bct(this, 58, 0);
        this.j.a(0.5f, -9.0f, -1.0f, 2, 5, 1);
        this.j.a(0.0f, 16.0f, -1.0f);
        this.j.i = true;
        this.a(this.j, 0.0f, 0.2617994f, 0.0f);
        this.k = new bct(this, 52, 6);
        this.k.a(-1.5f, -1.5f, 0.0f, 3, 3, 2);
        this.k.a(0.0f, 20.0f, 7.0f);
        this.k.i = true;
        this.a(this.k, -0.3490659f, 0.0f, 0.0f);
        this.l = new bct(this, 32, 9);
        this.l.a(-0.5f, -2.5f, -5.5f, 1, 1, 1);
        this.l.a(0.0f, 16.0f, -1.0f);
        this.l.i = true;
        this.a(this.l, 0.0f, 0.0f, 0.0f);
    }

    private void a(bct bct2, float f2, float f3, float f4) {
        bct2.f = f2;
        bct2.g = f3;
        bct2.h = f4;
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        if (this.r) {
            \u2603 = 2.0f;
            bfl.E();
            bfl.b(0.0f, 5.0f * f7, 2.0f * f7);
            this.h.a(f7);
            this.j.a(f7);
            this.i.a(f7);
            this.l.a(f7);
            bfl.F();
            bfl.E();
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            this.g.a(f7);
            this.k.a(f7);
            bfl.F();
        } else {
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            this.g.a(f7);
            this.h.a(f7);
            this.i.a(f7);
            this.j.a(f7);
            this.k.a(f7);
            this.l.a(f7);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        float f8 = f4 - (float)pk2.W;
        tu \u26032 = (tu)pk2;
        this.i.f = this.j.f = f6 * ((float)Math.PI / 180);
        this.h.f = this.j.f;
        this.l.f = this.j.f;
        this.l.g = this.h.g = f5 * ((float)Math.PI / 180);
        this.i.g = this.l.g - 0.2617994f;
        this.j.g = this.l.g + 0.2617994f;
        this.m = ns.a(\u26032.p(f8) * (float)Math.PI);
        this.c.f = this.d.f = (this.m * 50.0f - 21.0f) * ((float)Math.PI / 180);
        this.a.f = this.b.f = this.m * 50.0f * ((float)Math.PI / 180);
        this.f.f = this.g.f = (this.m * -40.0f - 11.0f) * ((float)Math.PI / 180);
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
    }
}

