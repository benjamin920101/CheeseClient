/*
 * Decompiled with CFR 0.152.
 */
public class bco
extends bbo {
    private bct a;
    private bct b;
    private bct c;
    private bct d;
    private bct e;
    private bct f;
    private bct g;
    private bct h;
    private bct i;
    private bct j;
    private bct k;
    private bct l;
    private float m;

    public bco(float f2) {
        this.t = 256;
        this.u = 256;
        this.a("body.body", 0, 0);
        this.a("wing.skin", -56, 88);
        this.a("wingtip.skin", -56, 144);
        this.a("rearleg.main", 0, 0);
        this.a("rearfoot.main", 112, 0);
        this.a("rearlegtip.main", 196, 0);
        this.a("head.upperhead", 112, 30);
        this.a("wing.bone", 112, 88);
        this.a("head.upperlip", 176, 44);
        this.a("jaw.jaw", 176, 65);
        this.a("frontleg.main", 112, 104);
        this.a("wingtip.bone", 112, 136);
        this.a("frontfoot.main", 144, 104);
        this.a("neck.box", 192, 104);
        this.a("frontlegtip.main", 226, 138);
        this.a("body.scale", 220, 53);
        this.a("head.scale", 0, 0);
        this.a("neck.scale", 48, 0);
        this.a("head.nostril", 112, 0);
        \u2603 = -16.0f;
        this.a = new bct(this, "head");
        this.a.a("upperlip", -6.0f, -1.0f, -8.0f + \u2603, 12, 5, 16);
        this.a.a("upperhead", -8.0f, -8.0f, 6.0f + \u2603, 16, 16, 16);
        this.a.i = true;
        this.a.a("scale", -5.0f, -12.0f, 12.0f + \u2603, 2, 4, 6);
        this.a.a("nostril", -5.0f, -3.0f, -6.0f + \u2603, 2, 2, 4);
        this.a.i = false;
        this.a.a("scale", 3.0f, -12.0f, 12.0f + \u2603, 2, 4, 6);
        this.a.a("nostril", 3.0f, -3.0f, -6.0f + \u2603, 2, 2, 4);
        this.c = new bct(this, "jaw");
        this.c.a(0.0f, 4.0f, 8.0f + \u2603);
        this.c.a("jaw", -6.0f, 0.0f, -16.0f, 12, 4, 16);
        this.a.a(this.c);
        this.b = new bct(this, "neck");
        this.b.a("box", -5.0f, -5.0f, -5.0f, 10, 10, 10);
        this.b.a("scale", -1.0f, -9.0f, -3.0f, 2, 4, 6);
        this.d = new bct(this, "body");
        this.d.a(0.0f, 4.0f, 8.0f);
        this.d.a("body", -12.0f, 0.0f, -16.0f, 24, 24, 64);
        this.d.a("scale", -1.0f, -6.0f, -10.0f, 2, 6, 12);
        this.d.a("scale", -1.0f, -6.0f, 10.0f, 2, 6, 12);
        this.d.a("scale", -1.0f, -6.0f, 30.0f, 2, 6, 12);
        this.k = new bct(this, "wing");
        this.k.a(-12.0f, 5.0f, 2.0f);
        this.k.a("bone", -56.0f, -4.0f, -4.0f, 56, 8, 8);
        this.k.a("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
        this.l = new bct(this, "wingtip");
        this.l.a(-56.0f, 0.0f, 0.0f);
        this.l.a("bone", -56.0f, -2.0f, -2.0f, 56, 4, 4);
        this.l.a("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
        this.k.a(this.l);
        this.f = new bct(this, "frontleg");
        this.f.a(-12.0f, 20.0f, 2.0f);
        this.f.a("main", -4.0f, -4.0f, -4.0f, 8, 24, 8);
        this.h = new bct(this, "frontlegtip");
        this.h.a(0.0f, 20.0f, -1.0f);
        this.h.a("main", -3.0f, -1.0f, -3.0f, 6, 24, 6);
        this.f.a(this.h);
        this.j = new bct(this, "frontfoot");
        this.j.a(0.0f, 23.0f, 0.0f);
        this.j.a("main", -4.0f, 0.0f, -12.0f, 8, 4, 16);
        this.h.a(this.j);
        this.e = new bct(this, "rearleg");
        this.e.a(-16.0f, 16.0f, 42.0f);
        this.e.a("main", -8.0f, -4.0f, -8.0f, 16, 32, 16);
        this.g = new bct(this, "rearlegtip");
        this.g.a(0.0f, 32.0f, -4.0f);
        this.g.a("main", -6.0f, -2.0f, 0.0f, 12, 32, 12);
        this.e.a(this.g);
        this.i = new bct(this, "rearfoot");
        this.i.a(0.0f, 31.0f, 4.0f);
        this.i.a("main", -9.0f, 0.0f, -20.0f, 18, 6, 24);
        this.g.a(this.i);
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
        this.m = f4;
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        float \u260312;
        bfl.E();
        ug ug2 = (ug)pk2;
        float \u26032 = ug2.bu + (ug2.bv - ug2.bu) * this.m;
        this.c.f = (float)(Math.sin(\u26032 * (float)Math.PI * 2.0f) + 1.0) * 0.2f;
        float \u26033 = (float)(Math.sin(\u26032 * (float)Math.PI * 2.0f - 1.0f) + 1.0);
        \u26033 = (\u26033 * \u26033 * 1.0f + \u26033 * 2.0f) * 0.05f;
        bfl.b(0.0f, \u26033 - 2.0f, -3.0f);
        bfl.b(\u26033 * 2.0f, 1.0f, 0.0f, 0.0f);
        float \u26034 = -30.0f;
        float \u26035 = 0.0f;
        float \u26036 = 1.5f;
        double[] \u26037 = ug2.b(6, this.m);
        float \u26038 = this.a(ug2.b(5, this.m)[0] - ug2.b(10, this.m)[0]);
        float \u26039 = this.a(ug2.b(5, this.m)[0] + (double)(\u26038 / 2.0f));
        \u26034 += 2.0f;
        float \u260310 = \u26032 * (float)Math.PI * 2.0f;
        \u26034 = 20.0f;
        float \u260311 = -12.0f;
        for (int i2 = 0; i2 < 5; ++i2) {
            double[] dArray = ug2.b(5 - i2, this.m);
            \u260312 = (float)Math.cos((float)i2 * 0.45f + \u260310) * 0.15f;
            this.b.g = this.a(dArray[0] - \u26037[0]) * (float)Math.PI / 180.0f * \u26036;
            this.b.f = \u260312 + (float)(dArray[1] - \u26037[1]) * (float)Math.PI / 180.0f * \u26036 * 5.0f;
            this.b.h = -this.a(dArray[0] - (double)\u26039) * (float)Math.PI / 180.0f * \u26036;
            this.b.d = \u26034;
            this.b.e = \u260311;
            this.b.c = \u26035;
            \u26034 = (float)((double)\u26034 + Math.sin(this.b.f) * 10.0);
            \u260311 = (float)((double)\u260311 - Math.cos(this.b.g) * Math.cos(this.b.f) * 10.0);
            \u26035 = (float)((double)\u26035 - Math.sin(this.b.g) * Math.cos(this.b.f) * 10.0);
            this.b.a(f7);
        }
        this.a.d = \u26034;
        this.a.e = \u260311;
        this.a.c = \u26035;
        dArray = ug2.b(0, this.m);
        this.a.g = this.a(dArray[0] - \u26037[0]) * (float)Math.PI / 180.0f * 1.0f;
        this.a.h = -this.a(dArray[0] - (double)\u26039) * (float)Math.PI / 180.0f * 1.0f;
        this.a.a(f7);
        bfl.E();
        bfl.b(0.0f, 1.0f, 0.0f);
        bfl.b(-\u26038 * \u26036 * 1.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(0.0f, -1.0f, 0.0f);
        this.d.h = 0.0f;
        this.d.a(f7);
        for (int i3 = 0; i3 < 2; ++i3) {
            bfl.o();
            \u260312 = \u26032 * (float)Math.PI * 2.0f;
            this.k.f = 0.125f - (float)Math.cos(\u260312) * 0.2f;
            this.k.g = 0.25f;
            this.k.h = (float)(Math.sin(\u260312) + 0.125) * 0.8f;
            this.l.h = -((float)(Math.sin(\u260312 + 2.0f) + 0.5)) * 0.75f;
            this.e.f = 1.0f + \u26033 * 0.1f;
            this.g.f = 0.5f + \u26033 * 0.1f;
            this.i.f = 0.75f + \u26033 * 0.1f;
            this.f.f = 1.3f + \u26033 * 0.1f;
            this.h.f = -0.5f - \u26033 * 0.1f;
            this.j.f = 0.75f + \u26033 * 0.1f;
            this.k.a(f7);
            this.f.a(f7);
            this.e.a(f7);
            bfl.a(-1.0f, 1.0f, 1.0f);
            if (i3 != 0) continue;
            bfl.e(1028);
        }
        bfl.F();
        bfl.e(1029);
        bfl.p();
        float \u260313 = -((float)Math.sin(\u26032 * (float)Math.PI * 2.0f)) * 0.0f;
        \u260310 = \u26032 * (float)Math.PI * 2.0f;
        \u26034 = 10.0f;
        \u260311 = 60.0f;
        \u26035 = 0.0f;
        \u26037 = ug2.b(11, this.m);
        for (int i4 = 0; i4 < 12; ++i4) {
            double[] dArray = ug2.b(12 + i4, this.m);
            \u260313 = (float)((double)\u260313 + Math.sin((float)i4 * 0.45f + \u260310) * (double)0.05f);
            this.b.g = (this.a(dArray[0] - \u26037[0]) * \u26036 + 180.0f) * (float)Math.PI / 180.0f;
            this.b.f = \u260313 + (float)(dArray[1] - \u26037[1]) * (float)Math.PI / 180.0f * \u26036 * 5.0f;
            this.b.h = this.a(dArray[0] - (double)\u26039) * (float)Math.PI / 180.0f * \u26036;
            this.b.d = \u26034;
            this.b.e = \u260311;
            this.b.c = \u26035;
            \u26034 = (float)((double)\u26034 + Math.sin(this.b.f) * 10.0);
            \u260311 = (float)((double)\u260311 - Math.cos(this.b.g) * Math.cos(this.b.f) * 10.0);
            \u26035 = (float)((double)\u26035 - Math.sin(this.b.g) * Math.cos(this.b.f) * 10.0);
            this.b.a(f7);
        }
        bfl.F();
    }

    private float a(double d2) {
        while (d2 >= 180.0) {
            d2 -= 360.0;
        }
        while (d2 < -180.0) {
            d2 += 360.0;
        }
        return (float)d2;
    }
}

