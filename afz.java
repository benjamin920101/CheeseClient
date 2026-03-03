/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afz
extends afm
implements afj {
    public static final amn a = amn.a("age", 0, 7);

    protected afz() {
        this.j(this.M.b().a(a, 0));
        this.a(true);
        float f2 = 0.5f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 0.25f, 0.5f + f2);
        this.a((yz)null);
        this.c(0.0f);
        this.a(h);
        this.K();
    }

    @Override
    protected boolean c(afh afh2) {
        return afh2 == afi.ak;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        super.b(adm2, cj2, alz2, random);
        if (adm2.l(cj2.a()) >= 9 && (\u2603 = alz2.b(a).intValue()) < 7 && random.nextInt((int)(25.0f / (\u2603 = afz.a(this, adm2, cj2))) + 1) == 0) {
            adm2.a(cj2, alz2.a(a, \u2603 + 1), 2);
        }
    }

    public void g(adm adm2, cj cj2, alz alz2) {
        int n2 = alz2.b(a) + ns.a(adm2.s, 2, 5);
        if (n2 > 7) {
            n2 = 7;
        }
        adm2.a(cj2, alz2.a(a, n2), 2);
    }

    protected static float a(afh afh2, adm adm2, cj cj2) {
        Object \u26033;
        float f2 = 1.0f;
        cj \u26032 = cj2.b();
        for (int i2 = -1; i2 <= 1; ++i2) {
            for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                float f3 = 0.0f;
                \u26033 = adm2.p(\u26032.a(i2, 0, \u2603));
                if (\u26033.c() == afi.ak) {
                    f3 = 1.0f;
                    if (\u26033.b(ags.a) > 0) {
                        f3 = 3.0f;
                    }
                }
                if (i2 != 0 || \u2603 != 0) {
                    f3 /= 4.0f;
                }
                f2 += f3;
            }
        }
        cj cj3 = cj2.c();
        \u2603 = cj2.d();
        \u2603 = cj2.e();
        \u26033 = cj2.f();
        boolean \u26034 = afh2 == adm2.p(\u2603).c() || afh2 == adm2.p((cj)\u26033).c();
        boolean bl2 = \u2603 = afh2 == adm2.p(cj3).c() || afh2 == adm2.p(\u2603).c();
        if (\u26034 && \u2603) {
            f2 /= 2.0f;
        } else {
            boolean bl3 = \u2603 = afh2 == adm2.p(\u2603.c()).c() || afh2 == adm2.p(((cj)\u26033).c()).c() || afh2 == adm2.p(((cj)\u26033).d()).c() || afh2 == adm2.p(\u2603.d()).c();
            if (\u2603) {
                f2 /= 2.0f;
            }
        }
        return f2;
    }

    @Override
    public boolean f(adm adm2, cj cj2, alz alz2) {
        return (adm2.k(cj2) >= 8 || adm2.i(cj2)) && this.c(adm2.p(cj2.b()).c());
    }

    protected zw l() {
        return zy.N;
    }

    protected zw n() {
        return zy.O;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        super.a(adm2, cj2, alz2, f2, 0);
        if (adm2.D) {
            return;
        }
        \u2603 = alz2.b(a);
        if (\u2603 >= 7) {
            \u2603 = 3 + n2;
            for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
                if (adm2.s.nextInt(15) > \u2603) continue;
                afz.a(adm2, cj2, new zx(this.l(), 1, 0));
            }
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (alz2.b(a) == 7) {
            return this.n();
        }
        return this.l();
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return this.l();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        return alz2.b(a) < 7;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return true;
    }

    @Override
    public void b(adm adm2, Random random, cj cj2, alz alz2) {
        this.g(adm2, cj2, alz2);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

