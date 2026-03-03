/*
 * Decompiled with CFR 0.152.
 */
public class bbp
extends bbo {
    bct a;
    bct b;
    bct c;
    bct d;
    bct e;
    bct f;
    bct g;
    bct h;
    int i = 1;

    public bbp() {
        this.a("head.main", 0, 0);
        this.a("head.nose", 0, 24);
        this.a("head.ear1", 0, 10);
        this.a("head.ear2", 6, 10);
        this.g = new bct(this, "head");
        this.g.a("main", -2.5f, -2.0f, -3.0f, 5, 4, 5);
        this.g.a("nose", -1.5f, 0.0f, -4.0f, 3, 2, 2);
        this.g.a("ear1", -2.0f, -3.0f, 0.0f, 1, 1, 2);
        this.g.a("ear2", 1.0f, -3.0f, 0.0f, 1, 1, 2);
        this.g.a(0.0f, 15.0f, -9.0f);
        this.h = new bct(this, 20, 0);
        this.h.a(-2.0f, 3.0f, -8.0f, 4, 16, 6, 0.0f);
        this.h.a(0.0f, 12.0f, -10.0f);
        this.e = new bct(this, 0, 15);
        this.e.a(-0.5f, 0.0f, 0.0f, 1, 8, 1);
        this.e.f = 0.9f;
        this.e.a(0.0f, 15.0f, 8.0f);
        this.f = new bct(this, 4, 15);
        this.f.a(-0.5f, 0.0f, 0.0f, 1, 8, 1);
        this.f.a(0.0f, 20.0f, 14.0f);
        this.a = new bct(this, 8, 13);
        this.a.a(-1.0f, 0.0f, 1.0f, 2, 6, 2);
        this.a.a(1.1f, 18.0f, 5.0f);
        this.b = new bct(this, 8, 13);
        this.b.a(-1.0f, 0.0f, 1.0f, 2, 6, 2);
        this.b.a(-1.1f, 18.0f, 5.0f);
        this.c = new bct(this, 40, 0);
        this.c.a(-1.0f, 0.0f, 0.0f, 2, 10, 2);
        this.c.a(1.2f, 13.8f, -5.0f);
        this.d = new bct(this, 40, 0);
        this.d.a(-1.0f, 0.0f, 0.0f, 2, 10, 2);
        this.d.a(-1.2f, 13.8f, -5.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        if (this.r) {
            \u2603 = 2.0f;
            bfl.E();
            bfl.a(1.5f / \u2603, 1.5f / \u2603, 1.5f / \u2603);
            bfl.b(0.0f, 10.0f * f7, 4.0f * f7);
            this.g.a(f7);
            bfl.F();
            bfl.E();
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.h.a(f7);
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            bfl.F();
        } else {
            this.g.a(f7);
            this.h.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        this.g.f = f6 / 57.295776f;
        this.g.g = f5 / 57.295776f;
        if (this.i != 3) {
            this.h.f = 1.5707964f;
            if (this.i == 2) {
                this.a.f = ns.b(f2 * 0.6662f) * 1.0f * f3;
                this.b.f = ns.b(f2 * 0.6662f + 0.3f) * 1.0f * f3;
                this.c.f = ns.b(f2 * 0.6662f + (float)Math.PI + 0.3f) * 1.0f * f3;
                this.d.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.0f * f3;
                this.f.f = 1.7278761f + 0.31415927f * ns.b(f2) * f3;
            } else {
                this.a.f = ns.b(f2 * 0.6662f) * 1.0f * f3;
                this.b.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.0f * f3;
                this.c.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.0f * f3;
                this.d.f = ns.b(f2 * 0.6662f) * 1.0f * f3;
                this.f.f = this.i == 1 ? 1.7278761f + 0.7853982f * ns.b(f2) * f3 : 1.7278761f + 0.47123894f * ns.b(f2) * f3;
            }
        }
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
        ts ts2 = (ts)pr2;
        this.h.d = 12.0f;
        this.h.e = -10.0f;
        this.g.d = 15.0f;
        this.g.e = -9.0f;
        this.e.d = 15.0f;
        this.e.e = 8.0f;
        this.f.d = 20.0f;
        this.f.e = 14.0f;
        this.d.d = 13.8f;
        this.c.d = 13.8f;
        this.d.e = -5.0f;
        this.c.e = -5.0f;
        this.b.d = 18.0f;
        this.a.d = 18.0f;
        this.b.e = 5.0f;
        this.a.e = 5.0f;
        this.e.f = 0.9f;
        if (ts2.av()) {
            this.h.d += 1.0f;
            this.g.d += 2.0f;
            this.e.d += 1.0f;
            this.f.d += -4.0f;
            this.f.e += 2.0f;
            this.e.f = 1.5707964f;
            this.f.f = 1.5707964f;
            this.i = 0;
        } else if (ts2.aw()) {
            this.f.d = this.e.d;
            this.f.e += 2.0f;
            this.e.f = 1.5707964f;
            this.f.f = 1.5707964f;
            this.i = 2;
        } else if (ts2.cn()) {
            this.h.f = 0.7853982f;
            this.h.d += -4.0f;
            this.h.e += 5.0f;
            this.g.d += -3.3f;
            this.g.e += 1.0f;
            this.e.d += 8.0f;
            this.e.e += -2.0f;
            this.f.d += 2.0f;
            this.f.e += -0.8f;
            this.e.f = 1.7278761f;
            this.f.f = 2.670354f;
            this.d.f = -0.15707964f;
            this.c.f = -0.15707964f;
            this.d.d = 15.8f;
            this.c.d = 15.8f;
            this.d.e = -7.0f;
            this.c.e = -7.0f;
            this.b.f = -1.5707964f;
            this.a.f = -1.5707964f;
            this.b.d = 21.0f;
            this.a.d = 21.0f;
            this.b.e = 1.0f;
            this.a.e = 1.0f;
            this.i = 3;
        } else {
            this.i = 1;
        }
    }
}

