/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class alv
extends afc {
    public static final aml a = alt.a;
    public static final amm<alt.a> b = alt.b;

    public alv() {
        super(arm.H);
        this.j(this.M.b().a(a, cq.c).a(b, alt.a.a));
        this.c(-1.0f);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return null;
    }

    public static akw a(alz alz2, cq cq2, boolean bl2, boolean bl3) {
        return new alu(alz2, cq2, bl2, bl3);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alu) {
            ((alu)akw2).h();
        } else {
            super.b(adm2, cj2, alz2);
        }
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return false;
    }

    @Override
    public boolean b(adm adm2, cj cj2, cq cq2) {
        return false;
    }

    @Override
    public void d(adm adm2, cj cj2, alz alz2) {
        cj cj3 = cj2.a(alz2.b(a).d());
        alz \u26032 = adm2.p(cj3);
        if (\u26032.c() instanceof als && \u26032.b(als.b).booleanValue()) {
            adm2.g(cj3);
        }
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (!adm2.D && adm2.s(cj2) == null) {
            adm2.g(cj2);
            return true;
        }
        return false;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (adm2.D) {
            return;
        }
        alu alu2 = this.e((adq)adm2, cj2);
        if (alu2 == null) {
            return;
        }
        alz \u26032 = alu2.b();
        \u26032.c().b(adm2, cj2, \u26032, 0);
    }

    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        return null;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm2.D) {
            adm2.s(cj2);
        }
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        alu alu2 = this.e((adq)adm2, cj2);
        if (alu2 == null) {
            return null;
        }
        float \u26032 = alu2.a(0.0f);
        if (alu2.d()) {
            \u26032 = 1.0f - \u26032;
        }
        return this.a(adm2, cj2, alu2.b(), \u26032, alu2.e());
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alu alu2 = this.e(adq2, cj2);
        if (alu2 != null) {
            alz alz2 = alu2.b();
            afh \u26032 = alz2.c();
            if (\u26032 == this || \u26032.t() == arm.a) {
                return;
            }
            float \u26033 = alu2.a(0.0f);
            if (alu2.d()) {
                \u26033 = 1.0f - \u26033;
            }
            \u26032.a(adq2, cj2);
            if (\u26032 == afi.J || \u26032 == afi.F) {
                \u26033 = 0.0f;
            }
            cq \u26034 = alu2.e();
            this.B = \u26032.B() - (double)((float)\u26034.g() * \u26033);
            this.C = \u26032.D() - (double)((float)\u26034.h() * \u26033);
            this.D = \u26032.F() - (double)((float)\u26034.i() * \u26033);
            this.E = \u26032.C() - (double)((float)\u26034.g() * \u26033);
            this.F = \u26032.E() - (double)((float)\u26034.h() * \u26033);
            this.G = \u26032.G() - (double)((float)\u26034.i() * \u26033);
        }
    }

    public aug a(adm adm2, cj cj2, alz alz2, float f2, cq cq2) {
        if (alz2.c() == this || alz2.c().t() == arm.a) {
            return null;
        }
        aug aug2 = alz2.c().a(adm2, cj2, alz2);
        if (aug2 == null) {
            return null;
        }
        double \u26032 = aug2.a;
        double \u26033 = aug2.b;
        double \u26034 = aug2.c;
        double \u26035 = aug2.d;
        double \u26036 = aug2.e;
        double \u26037 = aug2.f;
        if (cq2.g() < 0) {
            \u26032 -= (double)((float)cq2.g() * f2);
        } else {
            \u26035 -= (double)((float)cq2.g() * f2);
        }
        if (cq2.h() < 0) {
            \u26033 -= (double)((float)cq2.h() * f2);
        } else {
            \u26036 -= (double)((float)cq2.h() * f2);
        }
        if (cq2.i() < 0) {
            \u26034 -= (double)((float)cq2.i() * f2);
        } else {
            \u26037 -= (double)((float)cq2.i() * f2);
        }
        return new aug(\u26032, \u26033, \u26034, \u26035, \u26036, \u26037);
    }

    private alu e(adq adq2, cj cj2) {
        akw akw2 = adq2.s(cj2);
        if (akw2 instanceof alu) {
            return (alu)akw2;
        }
        return null;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return null;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, alt.b(n2)).a(b, (n2 & 8) > 0 ? alt.a.b : alt.a.a);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).a();
        if (alz2.b(b) == alt.a.b) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }
}

