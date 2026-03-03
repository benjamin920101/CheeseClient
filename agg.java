/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agg
extends afc {
    public static final aml a = aml.a("facing");
    public static final amk b = amk.a("triggered");
    public static final cp<zw, cr> N = new cp(new cn());
    protected Random O = new Random();

    protected agg() {
        super(arm.e);
        this.j(this.M.b().a(a, cq.c).a(b, false));
        this.a(yz.d);
    }

    @Override
    public int a(adm adm2) {
        return 4;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        super.c(adm2, cj2, alz2);
        this.e(adm2, cj2, alz2);
    }

    private void e(adm adm22, cj cj2, alz alz2) {
        adm adm22;
        if (adm22.D) {
            return;
        }
        cq cq2 = alz2.b(a);
        boolean \u26032 = adm22.p(cj2.c()).c().o();
        boolean \u26033 = adm22.p(cj2.d()).c().o();
        if (cq2 == cq.c && \u26032 && !\u26033) {
            cq2 = cq.d;
        } else if (cq2 == cq.d && \u26033 && !\u26032) {
            cq2 = cq.c;
        } else {
            boolean bl2 = adm22.p(cj2.e()).c().o();
            \u2603 = adm22.p(cj2.f()).c().o();
            if (cq2 == cq.e && bl2 && !\u2603) {
                cq2 = cq.f;
            } else if (cq2 == cq.f && \u2603 && !bl2) {
                cq2 = cq.e;
            }
        }
        adm22.a(cj2, alz2.a(a, cq2).a(b, false), 2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alc) {
            wn2.a((alc)akw2);
            if (akw2 instanceof ald) {
                wn2.b(na.O);
            } else {
                wn2.b(na.Q);
            }
        }
        return true;
    }

    protected void f(adm adm2, cj cj2) {
        cl cl2 = new cl(adm2, cj2);
        alc \u26032 = (alc)cl2.h();
        if (\u26032 == null) {
            return;
        }
        int \u26033 = \u26032.m();
        if (\u26033 < 0) {
            adm2.b(1001, cj2, 0);
            return;
        }
        zx \u26034 = \u26032.a(\u26033);
        cr \u26035 = this.a(\u26034);
        if (\u26035 != cr.a) {
            zx zx2 = \u26035.a(cl2, \u26034);
            \u26032.a(\u26033, zx2.b <= 0 ? null : zx2);
        }
    }

    protected cr a(zx zx2) {
        return N.a(zx2 == null ? null : zx2.b());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        boolean bl2 = adm2.z(cj2) || adm2.z(cj2.a());
        \u2603 = alz2.b(b);
        if (bl2 && !\u2603) {
            adm2.a(cj2, (afh)this, this.a(adm2));
            adm2.a(cj2, alz2.a(b, true), 4);
        } else if (!bl2 && \u2603) {
            adm2.a(cj2, alz2.a(b, false), 4);
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (!adm2.D) {
            this.f(adm2, cj2);
        }
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alc();
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, als.a(adm2, cj2, pr2)).a(b, false);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        adm2.a(cj2, alz2.a(a, als.a(adm2, cj2, pr2)), 2);
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof alc) {
            ((alc)\u2603).a(zx2.q());
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alc) {
            oi.a(adm2, cj2, (og)((alc)akw2));
            adm2.e(cj2, this);
        }
        super.b(adm2, cj2, alz2);
    }

    public static cz a(ck ck2) {
        cq cq2 = agg.b(ck2.f());
        double \u26032 = ck2.a() + 0.7 * (double)cq2.g();
        double \u26033 = ck2.b() + 0.7 * (double)cq2.h();
        double \u26034 = ck2.c() + 0.7 * (double)cq2.i();
        return new da(\u26032, \u26033, \u26034);
    }

    public static cq b(int n2) {
        return cq.a(n2 & 7);
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        return xi.a(adm2.s(cj2));
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public alz b(alz alz2) {
        return this.Q().a(a, cq.d);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, agg.b(n2)).a(b, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).a();
        if (alz2.b(b).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }
}

