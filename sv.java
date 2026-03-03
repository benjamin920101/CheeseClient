/*
 * Decompiled with CFR 0.152.
 */
public class sv
extends sw {
    protected ata a;
    private boolean f;

    public sv(ps ps2, adm adm2) {
        super(ps2, adm2);
    }

    @Override
    protected asy a() {
        this.a = new ata();
        this.a.a(true);
        return new asy(this.a);
    }

    @Override
    protected boolean b() {
        return this.b.C || this.h() && this.o() || this.b.au() && this.b instanceof we && this.b.m instanceof tn;
    }

    @Override
    protected aui c() {
        return new aui(this.b.s, this.p(), this.b.u);
    }

    private int p() {
        if (!this.b.V() || !this.h()) {
            return (int)(this.b.aR().b + 0.5);
        }
        int n2 = (int)this.b.aR().b;
        afh \u26032 = this.c.p(new cj(ns.c(this.b.s), n2, ns.c(this.b.u))).c();
        \u2603 = 0;
        while (\u26032 == afi.i || \u26032 == afi.j) {
            \u26032 = this.c.p(new cj(ns.c(this.b.s), ++n2, ns.c(this.b.u))).c();
            if (++\u2603 <= 16) continue;
            return (int)this.b.aR().b;
        }
        return n2;
    }

    @Override
    protected void d() {
        super.d();
        if (this.f) {
            if (this.c.i(new cj(ns.c(this.b.s), (int)(this.b.aR().b + 0.5), ns.c(this.b.u)))) {
                return;
            }
            for (int i2 = 0; i2 < this.d.d(); ++i2) {
                asv asv2 = this.d.a(i2);
                if (!this.c.i(new cj(asv2.a, asv2.b, asv2.c))) continue;
                this.d.b(i2 - 1);
                return;
            }
        }
    }

    @Override
    protected boolean a(aui aui2, aui aui3, int n2, int n3, int n4) {
        \u2603 = ns.c(aui2.a);
        \u2603 = ns.c(aui2.c);
        double d2 = aui3.a - aui2.a;
        \u2603 = aui3.c - aui2.c;
        \u2603 = d2 * d2 + \u2603 * \u2603;
        if (\u2603 < 1.0E-8) {
            return false;
        }
        \u2603 = 1.0 / Math.sqrt(\u2603);
        if (!this.a(\u2603, (int)aui2.b, \u2603, n2 += 2, n3, n4 += 2, aui2, d2 *= \u2603, \u2603 *= \u2603)) {
            return false;
        }
        n2 -= 2;
        n4 -= 2;
        \u2603 = 1.0 / Math.abs(d2);
        \u2603 = 1.0 / Math.abs(\u2603);
        \u2603 = (double)(\u2603 * 1) - aui2.a;
        \u2603 = (double)(\u2603 * 1) - aui2.c;
        if (d2 >= 0.0) {
            \u2603 += 1.0;
        }
        if (\u2603 >= 0.0) {
            \u2603 += 1.0;
        }
        \u2603 /= d2;
        \u2603 /= \u2603;
        int \u26032 = d2 < 0.0 ? -1 : 1;
        int \u26033 = \u2603 < 0.0 ? -1 : 1;
        int \u26034 = ns.c(aui3.a);
        int \u26035 = ns.c(aui3.c);
        int \u26036 = \u26034 - \u2603;
        int \u26037 = \u26035 - \u2603;
        while (\u26036 * \u26032 > 0 || \u26037 * \u26033 > 0) {
            if (\u2603 < \u2603) {
                \u2603 += \u2603;
                \u26036 = \u26034 - (\u2603 += \u26032);
            } else {
                \u2603 += \u2603;
                \u26037 = \u26035 - (\u2603 += \u26033);
            }
            if (this.a(\u2603, (int)aui2.b, \u2603, n2, n3, n4, aui2, d2, \u2603)) continue;
            return false;
        }
        return true;
    }

    private boolean a(int n2, int n3, int n4, int n5, int n6, int n7, aui aui2, double d2, double d3) {
        int n8 = n2 - n5 / 2;
        \u2603 = n4 - n7 / 2;
        if (!this.b(n8, n3, \u2603, n5, n6, n7, aui2, d2, d3)) {
            return false;
        }
        for (\u2603 = n8; \u2603 < n8 + n5; ++\u2603) {
            for (\u2603 = \u2603; \u2603 < \u2603 + n7; ++\u2603) {
                double d4 = (double)\u2603 + 0.5 - aui2.a;
                \u2603 = (double)\u2603 + 0.5 - aui2.c;
                if (d4 * d2 + \u2603 * d3 < 0.0) continue;
                afh \u26032 = this.c.p(new cj(\u2603, n3 - 1, \u2603)).c();
                arm \u26033 = \u26032.t();
                if (\u26033 == arm.a) {
                    return false;
                }
                if (\u26033 == arm.h && !this.b.V()) {
                    return false;
                }
                if (\u26033 != arm.i) continue;
                return false;
            }
        }
        return true;
    }

    private boolean b(int n2, int n3, int n4, int n5, int n6, int n7, aui aui2, double d2, double d3) {
        for (cj cj2 : cj.a(new cj(n2, n3, n4), new cj(n2 + n5 - 1, n3 + n6 - 1, n4 + n7 - 1))) {
            double d4 = (double)cj2.n() + 0.5 - aui2.a;
            if (d4 * d2 + (\u2603 = (double)cj2.p() + 0.5 - aui2.c) * d3 < 0.0 || (\u2603 = this.c.p(cj2).c()).b((adq)this.c, cj2)) continue;
            return false;
        }
        return true;
    }

    public void a(boolean bl2) {
        this.a.c(bl2);
    }

    public boolean e() {
        return this.a.e();
    }

    public void b(boolean bl2) {
        this.a.b(bl2);
    }

    public void c(boolean bl2) {
        this.a.a(bl2);
    }

    public boolean g() {
        return this.a.b();
    }

    public void d(boolean bl2) {
        this.a.d(bl2);
    }

    public boolean h() {
        return this.a.d();
    }

    public void e(boolean bl2) {
        this.f = bl2;
    }
}

