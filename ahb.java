/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahb
extends afc {
    public static final aml a = aml.a("facing", cq.c.a);
    private final boolean b;
    private static boolean N;

    protected ahb(boolean bl2) {
        super(arm.e);
        this.j(this.M.b().a(a, cq.c));
        this.b = bl2;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.al);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2);
    }

    private void e(adm adm2, cj cj2, alz alz2) {
        if (adm2.D) {
            return;
        }
        afh afh2 = adm2.p(cj2.c()).c();
        \u2603 = adm2.p(cj2.d()).c();
        \u2603 = adm2.p(cj2.e()).c();
        \u2603 = adm2.p(cj2.f()).c();
        cq \u26032 = alz2.b(a);
        if (\u26032 == cq.c && afh2.o() && !\u2603.o()) {
            \u26032 = cq.d;
        } else if (\u26032 == cq.d && \u2603.o() && !afh2.o()) {
            \u26032 = cq.c;
        } else if (\u26032 == cq.e && \u2603.o() && !\u2603.o()) {
            \u26032 = cq.f;
        } else if (\u26032 == cq.f && \u2603.o() && !\u2603.o()) {
            \u26032 = cq.e;
        }
        adm2.a(cj2, alz2.a(a, \u26032), 2);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (!this.b) {
            return;
        }
        cq cq2 = alz2.b(a);
        double \u26032 = (double)cj2.n() + 0.5;
        double \u26033 = (double)cj2.o() + random.nextDouble() * 6.0 / 16.0;
        double \u26034 = (double)cj2.p() + 0.5;
        double \u26035 = 0.52;
        double \u26036 = random.nextDouble() * 0.6 - 0.3;
        switch (cq2) {
            case e: {
                adm2.a(cy.l, \u26032 - \u26035, \u26033, \u26034 + \u26036, 0.0, 0.0, 0.0, new int[0]);
                adm2.a(cy.A, \u26032 - \u26035, \u26033, \u26034 + \u26036, 0.0, 0.0, 0.0, new int[0]);
                break;
            }
            case f: {
                adm2.a(cy.l, \u26032 + \u26035, \u26033, \u26034 + \u26036, 0.0, 0.0, 0.0, new int[0]);
                adm2.a(cy.A, \u26032 + \u26035, \u26033, \u26034 + \u26036, 0.0, 0.0, 0.0, new int[0]);
                break;
            }
            case c: {
                adm2.a(cy.l, \u26032 + \u26036, \u26033, \u26034 - \u26035, 0.0, 0.0, 0.0, new int[0]);
                adm2.a(cy.A, \u26032 + \u26036, \u26033, \u26034 - \u26035, 0.0, 0.0, 0.0, new int[0]);
                break;
            }
            case d: {
                adm2.a(cy.l, \u26032 + \u26036, \u26033, \u26034 + \u26035, 0.0, 0.0, 0.0, new int[0]);
                adm2.a(cy.A, \u26032 + \u26036, \u26033, \u26034 + \u26035, 0.0, 0.0, 0.0, new int[0]);
            }
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alh) {
            wn2.a((alh)akw2);
            wn2.b(na.Y);
        }
        return true;
    }

    public static void a(boolean bl2, adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        akw \u26032 = adm2.s(cj2);
        N = true;
        if (bl2) {
            adm2.a(cj2, afi.am.Q().a(a, alz2.b(a)), 3);
            adm2.a(cj2, afi.am.Q().a(a, alz2.b(a)), 3);
        } else {
            adm2.a(cj2, afi.al.Q().a(a, alz2.b(a)), 3);
            adm2.a(cj2, afi.al.Q().a(a, alz2.b(a)), 3);
        }
        N = false;
        if (\u26032 != null) {
            \u26032.D();
            adm2.a(cj2, \u26032);
        }
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alh();
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, pr2.aP().d());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        adm2.a(cj2, alz2.a(a, pr2.aP().d()), 2);
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof alh) {
            ((alh)\u2603).a(zx2.q());
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        if (!N && (\u2603 = adm2.s(cj2)) instanceof alh) {
            oi.a(adm2, cj2, (og)((alh)\u2603));
            adm2.e(cj2, this);
        }
        super.b(adm2, cj2, alz2);
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
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.al);
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
        cq cq2 = cq.a(n2);
        if (cq2.k() == cq.a.b) {
            cq2 = cq.c;
        }
        return this.Q().a(a, cq2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

