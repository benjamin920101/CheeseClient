/*
 * Decompiled with CFR 0.152.
 */
public class bce
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;
    public bct f;
    public bct g;
    public bct h;
    public bct i;
    public bct j;
    public bct k;

    public bce() {
        float f2 = 0.0f;
        int \u26032 = 15;
        this.a = new bct(this, 32, 4);
        this.a.a(-4.0f, -4.0f, -8.0f, 8, 8, 8, f2);
        this.a.a(0.0f, \u26032, -3.0f);
        this.b = new bct(this, 0, 0);
        this.b.a(-3.0f, -3.0f, -3.0f, 6, 6, 6, f2);
        this.b.a(0.0f, \u26032, 0.0f);
        this.c = new bct(this, 0, 12);
        this.c.a(-5.0f, -4.0f, -6.0f, 10, 8, 12, f2);
        this.c.a(0.0f, \u26032, 9.0f);
        this.d = new bct(this, 18, 0);
        this.d.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.d.a(-4.0f, \u26032, 2.0f);
        this.e = new bct(this, 18, 0);
        this.e.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.e.a(4.0f, \u26032, 2.0f);
        this.f = new bct(this, 18, 0);
        this.f.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.f.a(-4.0f, \u26032, 1.0f);
        this.g = new bct(this, 18, 0);
        this.g.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.g.a(4.0f, \u26032, 1.0f);
        this.h = new bct(this, 18, 0);
        this.h.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.h.a(-4.0f, \u26032, 0.0f);
        this.i = new bct(this, 18, 0);
        this.i.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.i.a(4.0f, \u26032, 0.0f);
        this.j = new bct(this, 18, 0);
        this.j.a(-15.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.j.a(-4.0f, \u26032, -1.0f);
        this.k = new bct(this, 18, 0);
        this.k.a(-1.0f, -1.0f, -1.0f, 16, 2, 2, f2);
        this.k.a(4.0f, \u26032, -1.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
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
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        this.a.g = f5 / 57.295776f;
        this.a.f = f6 / 57.295776f;
        float f8 = 0.7853982f;
        this.d.h = -f8;
        this.e.h = f8;
        this.f.h = -f8 * 0.74f;
        this.g.h = f8 * 0.74f;
        this.h.h = -f8 * 0.74f;
        this.i.h = f8 * 0.74f;
        this.j.h = -f8;
        this.k.h = f8;
        \u2603 = -0.0f;
        \u2603 = 0.3926991f;
        this.d.g = \u2603 * 2.0f + \u2603;
        this.e.g = -\u2603 * 2.0f - \u2603;
        this.f.g = \u2603 * 1.0f + \u2603;
        this.g.g = -\u2603 * 1.0f - \u2603;
        this.h.g = -\u2603 * 1.0f + \u2603;
        this.i.g = \u2603 * 1.0f - \u2603;
        this.j.g = -\u2603 * 2.0f + \u2603;
        this.k.g = \u2603 * 2.0f - \u2603;
        \u2603 = -(ns.b(f2 * 0.6662f * 2.0f + 0.0f) * 0.4f) * f3;
        \u2603 = -(ns.b(f2 * 0.6662f * 2.0f + (float)Math.PI) * 0.4f) * f3;
        \u2603 = -(ns.b(f2 * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * f3;
        \u2603 = -(ns.b(f2 * 0.6662f * 2.0f + 4.712389f) * 0.4f) * f3;
        \u2603 = Math.abs(ns.a(f2 * 0.6662f + 0.0f) * 0.4f) * f3;
        \u2603 = Math.abs(ns.a(f2 * 0.6662f + (float)Math.PI) * 0.4f) * f3;
        \u2603 = Math.abs(ns.a(f2 * 0.6662f + 1.5707964f) * 0.4f) * f3;
        \u2603 = Math.abs(ns.a(f2 * 0.6662f + 4.712389f) * 0.4f) * f3;
        this.d.g += \u2603;
        this.e.g += -\u2603;
        this.f.g += \u2603;
        this.g.g += -\u2603;
        this.h.g += \u2603;
        this.i.g += -\u2603;
        this.j.g += \u2603;
        this.k.g += -\u2603;
        this.d.h += \u2603;
        this.e.h += -\u2603;
        this.f.h += \u2603;
        this.g.h += -\u2603;
        this.h.h += \u2603;
        this.i.h += -\u2603;
        this.j.h += \u2603;
        this.k.h += -\u2603;
    }
}

