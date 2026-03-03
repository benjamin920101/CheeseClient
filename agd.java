/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class agd
extends age {
    protected final boolean N;

    protected agd(boolean bl2) {
        super(arm.q);
        this.N = bl2;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        if (adm.a(adm2, cj2.b())) {
            return super.d(adm2, cj2);
        }
        return false;
    }

    public boolean e(adm adm2, cj cj2) {
        return adm.a(adm2, cj2.b());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (this.b((adq)adm2, cj2, alz2)) {
            return;
        }
        boolean bl2 = this.e(adm2, cj2, alz2);
        if (this.N && !bl2) {
            adm2.a(cj2, this.k(alz2), 2);
        } else if (!this.N) {
            adm2.a(cj2, this.e(alz2), 2);
            if (!bl2) {
                adm2.a(cj2, this.e(alz2).c(), this.m(alz2), -1);
            }
        }
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return cq2.k() != cq.a.b;
    }

    protected boolean l(alz alz2) {
        return this.N;
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        return this.a(adq2, cj2, alz2, cq2);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (!this.l(alz2)) {
            return 0;
        }
        if (alz2.b(O) == cq2) {
            return this.a(adq2, cj2, alz2);
        }
        return 0;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (this.e(adm2, cj2)) {
            this.g(adm2, cj2, alz2);
            return;
        }
        this.b(adm2, cj2, alz2, 0);
        adm2.g(cj2);
        for (cq cq2 : cq.values()) {
            adm2.c(cj2.a(cq2), this);
        }
    }

    protected void g(adm adm2, cj cj2, alz alz2) {
        if (this.b((adq)adm2, cj2, alz2)) {
            return;
        }
        boolean bl2 = this.e(adm2, cj2, alz2);
        if ((this.N && !bl2 || !this.N && bl2) && !adm2.a(cj2, this)) {
            int n2 = -1;
            if (this.i(adm2, cj2, alz2)) {
                n2 = -3;
            } else if (this.N) {
                n2 = -2;
            }
            adm2.a(cj2, this, this.d(alz2), n2);
        }
    }

    public boolean b(adq adq2, cj cj2, alz alz2) {
        return false;
    }

    protected boolean e(adm adm2, cj cj2, alz alz2) {
        return this.f(adm2, cj2, alz2) > 0;
    }

    protected int f(adm adm2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(O);
        cj \u26032 = cj2.a(cq2);
        int \u26033 = adm2.c(\u26032, cq2);
        if (\u26033 >= 15) {
            return \u26033;
        }
        alz \u26034 = adm2.p(\u26032);
        return Math.max(\u26033, \u26034.c() == afi.af ? \u26034.b(ajb.P) : 0);
    }

    protected int c(adq adq2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(O);
        \u2603 = cq2.e();
        \u2603 = cq2.f();
        return Math.max(this.c(adq2, cj2.a(\u2603), \u2603), this.c(adq2, cj2.a(\u2603), \u2603));
    }

    protected int c(adq adq2, cj cj2, cq cq2) {
        alz alz2 = adq2.p(cj2);
        afh \u26032 = alz2.c();
        if (this.c(\u26032)) {
            if (\u26032 == afi.af) {
                return alz2.b(ajb.P);
            }
            return adq2.a(cj2, cq2);
        }
        return 0;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(O, pr2.aP().d());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        if (this.e(adm2, cj2, alz2)) {
            adm2.a(cj2, (afh)this, 1);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.h(adm2, cj2, alz2);
    }

    protected void h(adm adm2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(O);
        cj \u26032 = cj2.a(cq2.d());
        adm2.d(\u26032, this);
        adm2.a(\u26032, (afh)this, cq2);
    }

    @Override
    public void d(adm adm22, cj cj2, alz alz2) {
        adm adm22;
        if (this.N) {
            for (cq cq2 : cq.values()) {
                adm22.c(cj2.a(cq2), this);
            }
        }
        super.d(adm22, cj2, alz2);
    }

    @Override
    public boolean c() {
        return false;
    }

    protected boolean c(afh afh2) {
        return afh2.i();
    }

    protected int a(adq adq2, cj cj2, alz alz2) {
        return 15;
    }

    public static boolean d(afh afh2) {
        return afi.bb.e(afh2) || afi.cj.e(afh2);
    }

    public boolean e(afh afh2) {
        return afh2 == this.e(this.Q()).c() || afh2 == this.k(this.Q()).c();
    }

    public boolean i(adm adm2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(O).d();
        cj \u26032 = cj2.a(cq2);
        if (agd.d(adm2.p(\u26032).c())) {
            return adm2.p(\u26032).b(O) != cq2;
        }
        return false;
    }

    protected int m(alz alz2) {
        return this.d(alz2);
    }

    protected abstract int d(alz var1);

    protected abstract alz e(alz var1);

    protected abstract alz k(alz var1);

    @Override
    public boolean b(afh afh2) {
        return this.e(afh2);
    }

    @Override
    public adf m() {
        return adf.c;
    }
}

