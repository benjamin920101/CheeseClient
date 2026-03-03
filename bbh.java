/*
 * Decompiled with CFR 0.152.
 */
public class bbh
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
    private bct m;
    private bct n;
    private bct o;
    private bct v;
    private bct w;
    private bct x;
    private bct y;
    private bct z;
    private bct A;
    private bct B;
    private bct C;
    private bct D;
    private bct E;
    private bct F;
    private bct G;
    private bct H;
    private bct I;
    private bct J;
    private bct K;
    private bct L;
    private bct M;
    private bct N;
    private bct O;
    private bct P;
    private bct Q;
    private bct R;
    private bct S;

    public bbh() {
        this.t = 128;
        this.u = 128;
        this.k = new bct(this, 0, 34);
        this.k.a(-5.0f, -8.0f, -19.0f, 10, 10, 24);
        this.k.a(0.0f, 11.0f, 9.0f);
        this.l = new bct(this, 44, 0);
        this.l.a(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.l.a(0.0f, 3.0f, 14.0f);
        this.a(this.l, -1.134464f, 0.0f, 0.0f);
        this.m = new bct(this, 38, 7);
        this.m.a(-1.5f, -2.0f, 3.0f, 3, 4, 7);
        this.m.a(0.0f, 3.0f, 14.0f);
        this.a(this.m, -1.134464f, 0.0f, 0.0f);
        this.n = new bct(this, 24, 3);
        this.n.a(-1.5f, -4.5f, 9.0f, 3, 4, 7);
        this.n.a(0.0f, 3.0f, 14.0f);
        this.a(this.n, -1.40215f, 0.0f, 0.0f);
        this.o = new bct(this, 78, 29);
        this.o.a(-2.5f, -2.0f, -2.5f, 4, 9, 5);
        this.o.a(4.0f, 9.0f, 11.0f);
        this.v = new bct(this, 78, 43);
        this.v.a(-2.0f, 0.0f, -1.5f, 3, 5, 3);
        this.v.a(4.0f, 16.0f, 11.0f);
        this.w = new bct(this, 78, 51);
        this.w.a(-2.5f, 5.1f, -2.0f, 4, 3, 4);
        this.w.a(4.0f, 16.0f, 11.0f);
        this.x = new bct(this, 96, 29);
        this.x.a(-1.5f, -2.0f, -2.5f, 4, 9, 5);
        this.x.a(-4.0f, 9.0f, 11.0f);
        this.y = new bct(this, 96, 43);
        this.y.a(-1.0f, 0.0f, -1.5f, 3, 5, 3);
        this.y.a(-4.0f, 16.0f, 11.0f);
        this.z = new bct(this, 96, 51);
        this.z.a(-1.5f, 5.1f, -2.0f, 4, 3, 4);
        this.z.a(-4.0f, 16.0f, 11.0f);
        this.A = new bct(this, 44, 29);
        this.A.a(-1.9f, -1.0f, -2.1f, 3, 8, 4);
        this.A.a(4.0f, 9.0f, -8.0f);
        this.B = new bct(this, 44, 41);
        this.B.a(-1.9f, 0.0f, -1.6f, 3, 5, 3);
        this.B.a(4.0f, 16.0f, -8.0f);
        this.C = new bct(this, 44, 51);
        this.C.a(-2.4f, 5.1f, -2.1f, 4, 3, 4);
        this.C.a(4.0f, 16.0f, -8.0f);
        this.D = new bct(this, 60, 29);
        this.D.a(-1.1f, -1.0f, -2.1f, 3, 8, 4);
        this.D.a(-4.0f, 9.0f, -8.0f);
        this.E = new bct(this, 60, 41);
        this.E.a(-1.1f, 0.0f, -1.6f, 3, 5, 3);
        this.E.a(-4.0f, 16.0f, -8.0f);
        this.F = new bct(this, 60, 51);
        this.F.a(-1.6f, 5.1f, -2.1f, 4, 3, 4);
        this.F.a(-4.0f, 16.0f, -8.0f);
        this.a = new bct(this, 0, 0);
        this.a.a(-2.5f, -10.0f, -1.5f, 5, 5, 7);
        this.a.a(0.0f, 4.0f, -10.0f);
        this.a(this.a, 0.5235988f, 0.0f, 0.0f);
        this.b = new bct(this, 24, 18);
        this.b.a(-2.0f, -10.0f, -7.0f, 4, 3, 6);
        this.b.a(0.0f, 3.95f, -10.0f);
        this.a(this.b, 0.5235988f, 0.0f, 0.0f);
        this.c = new bct(this, 24, 27);
        this.c.a(-2.0f, -7.0f, -6.5f, 4, 2, 5);
        this.c.a(0.0f, 4.0f, -10.0f);
        this.a(this.c, 0.5235988f, 0.0f, 0.0f);
        this.a.a(this.b);
        this.a.a(this.c);
        this.d = new bct(this, 0, 0);
        this.d.a(0.45f, -12.0f, 4.0f, 2, 3, 1);
        this.d.a(0.0f, 4.0f, -10.0f);
        this.a(this.d, 0.5235988f, 0.0f, 0.0f);
        this.e = new bct(this, 0, 0);
        this.e.a(-2.45f, -12.0f, 4.0f, 2, 3, 1);
        this.e.a(0.0f, 4.0f, -10.0f);
        this.a(this.e, 0.5235988f, 0.0f, 0.0f);
        this.f = new bct(this, 0, 12);
        this.f.a(-2.0f, -16.0f, 4.0f, 2, 7, 1);
        this.f.a(0.0f, 4.0f, -10.0f);
        this.a(this.f, 0.5235988f, 0.0f, 0.2617994f);
        this.g = new bct(this, 0, 12);
        this.g.a(0.0f, -16.0f, 4.0f, 2, 7, 1);
        this.g.a(0.0f, 4.0f, -10.0f);
        this.a(this.g, 0.5235988f, 0.0f, -0.2617994f);
        this.h = new bct(this, 0, 12);
        this.h.a(-2.05f, -9.8f, -2.0f, 4, 14, 8);
        this.h.a(0.0f, 4.0f, -10.0f);
        this.a(this.h, 0.5235988f, 0.0f, 0.0f);
        this.G = new bct(this, 0, 34);
        this.G.a(-3.0f, 0.0f, 0.0f, 8, 8, 3);
        this.G.a(-7.5f, 3.0f, 10.0f);
        this.a(this.G, 0.0f, 1.5707964f, 0.0f);
        this.H = new bct(this, 0, 47);
        this.H.a(-3.0f, 0.0f, 0.0f, 8, 8, 3);
        this.H.a(4.5f, 3.0f, 10.0f);
        this.a(this.H, 0.0f, 1.5707964f, 0.0f);
        this.I = new bct(this, 80, 0);
        this.I.a(-5.0f, 0.0f, -3.0f, 10, 1, 8);
        this.I.a(0.0f, 2.0f, 2.0f);
        this.J = new bct(this, 106, 9);
        this.J.a(-1.5f, -1.0f, -3.0f, 3, 1, 2);
        this.J.a(0.0f, 2.0f, 2.0f);
        this.K = new bct(this, 80, 9);
        this.K.a(-4.0f, -1.0f, 3.0f, 8, 1, 2);
        this.K.a(0.0f, 2.0f, 2.0f);
        this.M = new bct(this, 74, 0);
        this.M.a(-0.5f, 6.0f, -1.0f, 1, 2, 2);
        this.M.a(5.0f, 3.0f, 2.0f);
        this.L = new bct(this, 70, 0);
        this.L.a(-0.5f, 0.0f, -0.5f, 1, 6, 1);
        this.L.a(5.0f, 3.0f, 2.0f);
        this.O = new bct(this, 74, 4);
        this.O.a(-0.5f, 6.0f, -1.0f, 1, 2, 2);
        this.O.a(-5.0f, 3.0f, 2.0f);
        this.N = new bct(this, 80, 0);
        this.N.a(-0.5f, 0.0f, -0.5f, 1, 6, 1);
        this.N.a(-5.0f, 3.0f, 2.0f);
        this.P = new bct(this, 74, 13);
        this.P.a(1.5f, -8.0f, -4.0f, 1, 2, 2);
        this.P.a(0.0f, 4.0f, -10.0f);
        this.a(this.P, 0.5235988f, 0.0f, 0.0f);
        this.Q = new bct(this, 74, 13);
        this.Q.a(-2.5f, -8.0f, -4.0f, 1, 2, 2);
        this.Q.a(0.0f, 4.0f, -10.0f);
        this.a(this.Q, 0.5235988f, 0.0f, 0.0f);
        this.R = new bct(this, 44, 10);
        this.R.a(2.6f, -6.0f, -6.0f, 0, 3, 16);
        this.R.a(0.0f, 4.0f, -10.0f);
        this.S = new bct(this, 44, 5);
        this.S.a(-2.6f, -6.0f, -6.0f, 0, 3, 16);
        this.S.a(0.0f, 4.0f, -10.0f);
        this.j = new bct(this, 58, 0);
        this.j.a(-1.0f, -11.5f, 5.0f, 2, 16, 4);
        this.j.a(0.0f, 4.0f, -10.0f);
        this.a(this.j, 0.5235988f, 0.0f, 0.0f);
        this.i = new bct(this, 80, 12);
        this.i.a(-2.5f, -10.1f, -7.0f, 5, 5, 12, 0.2f);
        this.i.a(0.0f, 4.0f, -10.0f);
        this.a(this.i, 0.5235988f, 0.0f, 0.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f72) {
        float f72;
        tp tp2 = (tp)pk2;
        int \u26032 = tp2.cl();
        float \u26033 = tp2.p(0.0f);
        boolean \u26034 = tp2.cn();
        boolean \u26035 = \u26034 && tp2.cG();
        boolean \u26036 = \u26034 && tp2.cw();
        boolean \u26037 = \u26032 == 1 || \u26032 == 2;
        float \u26038 = tp2.cu();
        boolean bl2 = \u2603 = tp2.l != null;
        if (\u26035) {
            this.i.a(f72);
            this.I.a(f72);
            this.J.a(f72);
            this.K.a(f72);
            this.L.a(f72);
            this.M.a(f72);
            this.N.a(f72);
            this.O.a(f72);
            this.P.a(f72);
            this.Q.a(f72);
            if (\u2603) {
                this.R.a(f72);
                this.S.a(f72);
            }
        }
        if (!\u26034) {
            bfl.E();
            bfl.a(\u26038, 0.5f + \u26038 * 0.5f, \u26038);
            bfl.b(0.0f, 0.95f * (1.0f - \u26038), 0.0f);
        }
        this.o.a(f72);
        this.v.a(f72);
        this.w.a(f72);
        this.x.a(f72);
        this.y.a(f72);
        this.z.a(f72);
        this.A.a(f72);
        this.B.a(f72);
        this.C.a(f72);
        this.D.a(f72);
        this.E.a(f72);
        this.F.a(f72);
        if (!\u26034) {
            bfl.F();
            bfl.E();
            bfl.a(\u26038, \u26038, \u26038);
            bfl.b(0.0f, 1.35f * (1.0f - \u26038), 0.0f);
        }
        this.k.a(f72);
        this.l.a(f72);
        this.m.a(f72);
        this.n.a(f72);
        this.h.a(f72);
        this.j.a(f72);
        if (!\u26034) {
            bfl.F();
            bfl.E();
            float f8 = 0.5f + \u26038 * \u26038 * 0.5f;
            bfl.a(f8, f8, f8);
            if (\u26033 <= 0.0f) {
                bfl.b(0.0f, 1.35f * (1.0f - \u26038), 0.0f);
            } else {
                bfl.b(0.0f, 0.9f * (1.0f - \u26038) * \u26033 + 1.35f * (1.0f - \u26038) * (1.0f - \u26033), 0.15f * (1.0f - \u26038) * \u26033);
            }
        }
        if (\u26037) {
            this.f.a(f72);
            this.g.a(f72);
        } else {
            this.d.a(f72);
            this.e.a(f72);
        }
        this.a.a(f72);
        if (!\u26034) {
            bfl.F();
        }
        if (\u26036) {
            this.G.a(f72);
            this.H.a(f72);
        }
    }

    private void a(bct bct2, float f2, float f3, float f4) {
        bct2.f = f2;
        bct2.g = f3;
        bct2.h = f4;
    }

    private float a(float f2, float f3, float f4) {
        for (\u2603 = f3 - f2; \u2603 < -180.0f; \u2603 += 360.0f) {
        }
        while (\u2603 >= 180.0f) {
            \u2603 -= 360.0f;
        }
        return f2 + f4 * \u2603;
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
        super.a(pr2, f2, f3, f4);
        \u2603 = this.a(pr2.aJ, pr2.aI, f4);
        \u2603 = this.a(pr2.aL, pr2.aK, f4);
        \u2603 = pr2.B + (pr2.z - pr2.B) * f4;
        \u2603 = \u2603 - \u2603;
        \u2603 = \u2603 / 57.295776f;
        if (\u2603 > 20.0f) {
            \u2603 = 20.0f;
        }
        if (\u2603 < -20.0f) {
            \u2603 = -20.0f;
        }
        if (f3 > 0.2f) {
            \u2603 += ns.b(f2 * 0.4f) * 0.15f * f3;
        }
        tp tp2 = (tp)pr2;
        float \u26032 = tp2.p(f4);
        float \u26033 = tp2.q(f4);
        float \u26034 = 1.0f - \u26033;
        float \u26035 = tp2.r(f4);
        boolean \u26036 = tp2.bm != 0;
        boolean \u26037 = tp2.cG();
        boolean \u26038 = tp2.l != null;
        float \u26039 = (float)pr2.W + f4;
        float \u260310 = ns.b(f2 * 0.6662f + (float)Math.PI);
        float \u260311 = \u260310 * 0.8f * f3;
        this.a.d = 4.0f;
        this.a.e = -10.0f;
        this.l.d = 3.0f;
        this.m.e = 14.0f;
        this.H.d = 3.0f;
        this.H.e = 10.0f;
        this.k.f = 0.0f;
        this.a.f = 0.5235988f + \u2603;
        this.a.g = \u2603 / 57.295776f;
        this.a.f = \u26033 * (0.2617994f + \u2603) + \u26032 * 2.18166f + (1.0f - Math.max(\u26033, \u26032)) * this.a.f;
        this.a.g = \u26033 * \u2603 / 57.295776f + (1.0f - Math.max(\u26033, \u26032)) * this.a.g;
        this.a.d = \u26033 * -6.0f + \u26032 * 11.0f + (1.0f - Math.max(\u26033, \u26032)) * this.a.d;
        this.a.e = \u26033 * -1.0f + \u26032 * -10.0f + (1.0f - Math.max(\u26033, \u26032)) * this.a.e;
        this.l.d = \u26033 * 9.0f + \u26034 * this.l.d;
        this.m.e = \u26033 * 18.0f + \u26034 * this.m.e;
        this.H.d = \u26033 * 5.5f + \u26034 * this.H.d;
        this.H.e = \u26033 * 15.0f + \u26034 * this.H.e;
        this.k.f = \u26033 * -45.0f / 57.295776f + \u26034 * this.k.f;
        this.d.d = this.a.d;
        this.e.d = this.a.d;
        this.f.d = this.a.d;
        this.g.d = this.a.d;
        this.h.d = this.a.d;
        this.b.d = 0.02f;
        this.c.d = 0.0f;
        this.j.d = this.a.d;
        this.d.e = this.a.e;
        this.e.e = this.a.e;
        this.f.e = this.a.e;
        this.g.e = this.a.e;
        this.h.e = this.a.e;
        this.b.e = 0.02f - \u26035 * 1.0f;
        this.c.e = 0.0f + \u26035 * 1.0f;
        this.j.e = this.a.e;
        this.d.f = this.a.f;
        this.e.f = this.a.f;
        this.f.f = this.a.f;
        this.g.f = this.a.f;
        this.h.f = this.a.f;
        this.b.f = 0.0f - 0.09424778f * \u26035;
        this.c.f = 0.0f + 0.15707964f * \u26035;
        this.j.f = this.a.f;
        this.d.g = this.a.g;
        this.e.g = this.a.g;
        this.f.g = this.a.g;
        this.g.g = this.a.g;
        this.h.g = this.a.g;
        this.b.g = 0.0f;
        this.c.g = 0.0f;
        this.j.g = this.a.g;
        this.G.f = \u260311 / 5.0f;
        this.H.f = -\u260311 / 5.0f;
        float \u260312 = 1.5707964f;
        float \u260313 = 4.712389f;
        float \u260314 = -1.0471976f;
        float \u260315 = 0.2617994f * \u26033;
        float \u260316 = ns.b(\u26039 * 0.6f + (float)Math.PI);
        this.A.d = -2.0f * \u26033 + 9.0f * \u26034;
        this.A.e = -2.0f * \u26033 + -8.0f * \u26034;
        this.D.d = this.A.d;
        this.D.e = this.A.e;
        this.v.d = this.o.d + ns.a(1.5707964f + \u260315 + \u26034 * -\u260310 * 0.5f * f3) * 7.0f;
        this.v.e = this.o.e + ns.b(4.712389f + \u260315 + \u26034 * -\u260310 * 0.5f * f3) * 7.0f;
        this.y.d = this.x.d + ns.a(1.5707964f + \u260315 + \u26034 * \u260310 * 0.5f * f3) * 7.0f;
        this.y.e = this.x.e + ns.b(4.712389f + \u260315 + \u26034 * \u260310 * 0.5f * f3) * 7.0f;
        float \u260317 = (-1.0471976f + \u260316) * \u26033 + \u260311 * \u26034;
        float \u260318 = (-1.0471976f + -\u260316) * \u26033 + -\u260311 * \u26034;
        this.B.d = this.A.d + ns.a(1.5707964f + \u260317) * 7.0f;
        this.B.e = this.A.e + ns.b(4.712389f + \u260317) * 7.0f;
        this.E.d = this.D.d + ns.a(1.5707964f + \u260318) * 7.0f;
        this.E.e = this.D.e + ns.b(4.712389f + \u260318) * 7.0f;
        this.o.f = \u260315 + -\u260310 * 0.5f * f3 * \u26034;
        this.w.f = this.v.f = -0.08726646f * \u26033 + (-\u260310 * 0.5f * f3 - Math.max(0.0f, \u260310 * 0.5f * f3)) * \u26034;
        this.x.f = \u260315 + \u260310 * 0.5f * f3 * \u26034;
        this.z.f = this.y.f = -0.08726646f * \u26033 + (\u260310 * 0.5f * f3 - Math.max(0.0f, -\u260310 * 0.5f * f3)) * \u26034;
        this.A.f = \u260317;
        this.C.f = this.B.f = (this.A.f + (float)Math.PI * Math.max(0.0f, 0.2f + \u260316 * 0.2f)) * \u26033 + (\u260311 + Math.max(0.0f, \u260310 * 0.5f * f3)) * \u26034;
        this.D.f = \u260318;
        this.F.f = this.E.f = (this.D.f + (float)Math.PI * Math.max(0.0f, 0.2f - \u260316 * 0.2f)) * \u26033 + (-\u260311 + Math.max(0.0f, -\u260310 * 0.5f * f3)) * \u26034;
        this.w.d = this.v.d;
        this.w.e = this.v.e;
        this.z.d = this.y.d;
        this.z.e = this.y.e;
        this.C.d = this.B.d;
        this.C.e = this.B.e;
        this.F.d = this.E.d;
        this.F.e = this.E.e;
        if (\u26037) {
            this.I.d = \u26033 * 0.5f + \u26034 * 2.0f;
            this.I.e = \u26033 * 11.0f + \u26034 * 2.0f;
            this.J.d = this.I.d;
            this.K.d = this.I.d;
            this.L.d = this.I.d;
            this.N.d = this.I.d;
            this.M.d = this.I.d;
            this.O.d = this.I.d;
            this.G.d = this.H.d;
            this.J.e = this.I.e;
            this.K.e = this.I.e;
            this.L.e = this.I.e;
            this.N.e = this.I.e;
            this.M.e = this.I.e;
            this.O.e = this.I.e;
            this.G.e = this.H.e;
            this.I.f = this.k.f;
            this.J.f = this.k.f;
            this.K.f = this.k.f;
            this.R.d = this.a.d;
            this.S.d = this.a.d;
            this.i.d = this.a.d;
            this.P.d = this.a.d;
            this.Q.d = this.a.d;
            this.R.e = this.a.e;
            this.S.e = this.a.e;
            this.i.e = this.a.e;
            this.P.e = this.a.e;
            this.Q.e = this.a.e;
            this.R.f = \u2603;
            this.S.f = \u2603;
            this.i.f = this.a.f;
            this.P.f = this.a.f;
            this.Q.f = this.a.f;
            this.i.g = this.a.g;
            this.P.g = this.a.g;
            this.R.g = this.a.g;
            this.Q.g = this.a.g;
            this.S.g = this.a.g;
            if (\u26038) {
                this.L.f = -1.0471976f;
                this.M.f = -1.0471976f;
                this.N.f = -1.0471976f;
                this.O.f = -1.0471976f;
                this.L.h = 0.0f;
                this.M.h = 0.0f;
                this.N.h = 0.0f;
                this.O.h = 0.0f;
            } else {
                this.L.f = \u260311 / 3.0f;
                this.M.f = \u260311 / 3.0f;
                this.N.f = \u260311 / 3.0f;
                this.O.f = \u260311 / 3.0f;
                this.L.h = \u260311 / 5.0f;
                this.M.h = \u260311 / 5.0f;
                this.N.h = -\u260311 / 5.0f;
                this.O.h = -\u260311 / 5.0f;
            }
        }
        if ((\u260312 = -1.3089f + f3 * 1.5f) > 0.0f) {
            \u260312 = 0.0f;
        }
        if (\u26036) {
            this.l.g = ns.b(\u26039 * 0.7f);
            \u260312 = 0.0f;
        } else {
            this.l.g = 0.0f;
        }
        this.m.g = this.l.g;
        this.n.g = this.l.g;
        this.m.d = this.l.d;
        this.n.d = this.l.d;
        this.m.e = this.l.e;
        this.n.e = this.l.e;
        this.l.f = \u260312;
        this.m.f = \u260312;
        this.n.f = -0.2618f + \u260312;
    }
}

