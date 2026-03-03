/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Random;

public class akf
extends afh {
    public static final aml a = aml.a("facing", new Predicate<cq>(){

        public boolean a(cq cq2) {
            return cq2 != cq.a;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((cq)object);
        }
    });

    protected akf() {
        super(arm.q);
        this.j(this.M.b().a(a, cq.b));
        this.a(true);
        this.a(yz.c);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    private boolean e(adm adm2, cj cj2) {
        if (adm.a(adm2, cj2)) {
            return true;
        }
        afh afh2 = adm2.p(cj2).c();
        return afh2 instanceof agt || afh2 == afi.w || afh2 == afi.bZ || afh2 == afi.cG;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        for (cq cq2 : a.c()) {
            if (!this.a(adm2, cj2, cq2)) continue;
            return true;
        }
        return false;
    }

    private boolean a(adm adm2, cj cj2, cq cq2) {
        cj cj3 = cj2.a(cq2.d());
        boolean \u26032 = cq2.k().c();
        return \u26032 && adm2.d(cj3, true) || cq2.equals(cq.b) && this.e(adm2, cj3);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        if (this.a(adm2, cj2, cq2)) {
            return this.Q().a(a, cq2);
        }
        for (cq cq3 : cq.c.a) {
            if (!adm2.d(cj2.a(cq3.d()), true)) continue;
            return this.Q().a(a, cq3);
        }
        return this.Q();
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.f(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
    }

    protected boolean e(adm adm2, cj cj2, alz alz2) {
        if (!this.f(adm2, cj2, alz2)) {
            return true;
        }
        cq cq2 = alz2.b(a);
        cq.a \u26032 = cq2.k();
        \u2603 = cq2.d();
        boolean \u26033 = false;
        if (\u26032.c() && !adm2.d(cj2.a(\u2603), true)) {
            \u26033 = true;
        } else if (\u26032.b() && !this.e(adm2, cj2.a(\u2603))) {
            \u26033 = true;
        }
        if (\u26033) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
            return true;
        }
        return false;
    }

    protected boolean f(adm adm2, cj cj2, alz alz2) {
        if (alz2.c() == this && this.a(adm2, cj2, alz2.b(a))) {
            return true;
        }
        if (adm2.p(cj2).c() == this) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
        return false;
    }

    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        cq cq2 = adm2.p(cj2).b(a);
        float \u26032 = 0.15f;
        if (cq2 == cq.f) {
            this.a(0.0f, 0.2f, 0.5f - \u26032, \u26032 * 2.0f, 0.8f, 0.5f + \u26032);
        } else if (cq2 == cq.e) {
            this.a(1.0f - \u26032 * 2.0f, 0.2f, 0.5f - \u26032, 1.0f, 0.8f, 0.5f + \u26032);
        } else if (cq2 == cq.d) {
            this.a(0.5f - \u26032, 0.2f, 0.0f, 0.5f + \u26032, 0.8f, \u26032 * 2.0f);
        } else if (cq2 == cq.c) {
            this.a(0.5f - \u26032, 0.2f, 1.0f - \u26032 * 2.0f, 0.5f + \u26032, 0.8f, 1.0f);
        } else {
            \u26032 = 0.1f;
            this.a(0.5f - \u26032, 0.0f, 0.5f - \u26032, 0.5f + \u26032, 0.6f, 0.5f + \u26032);
        }
        return super.a(adm2, cj2, aui2, aui3);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        cq cq2 = alz2.b(a);
        double \u26032 = (double)cj2.n() + 0.5;
        double \u26033 = (double)cj2.o() + 0.7;
        double \u26034 = (double)cj2.p() + 0.5;
        double \u26035 = 0.22;
        double \u26036 = 0.27;
        if (cq2.k().c()) {
            \u2603 = cq2.d();
            adm2.a(cy.l, \u26032 + \u26036 * (double)\u2603.g(), \u26033 + \u26035, \u26034 + \u26036 * (double)\u2603.i(), 0.0, 0.0, 0.0, new int[0]);
            adm2.a(cy.A, \u26032 + \u26036 * (double)\u2603.g(), \u26033 + \u26035, \u26034 + \u26036 * (double)\u2603.i(), 0.0, 0.0, 0.0, new int[0]);
        } else {
            adm2.a(cy.l, \u26032, \u26033, \u26034, 0.0, 0.0, 0.0, new int[0]);
            adm2.a(cy.A, \u26032, \u26033, \u26034, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q();
        switch (n2) {
            case 1: {
                alz2 = alz2.a(a, cq.f);
                break;
            }
            case 2: {
                alz2 = alz2.a(a, cq.e);
                break;
            }
            case 3: {
                alz2 = alz2.a(a, cq.d);
                break;
            }
            case 4: {
                alz2 = alz2.a(a, cq.c);
                break;
            }
            default: {
                alz2 = alz2.a(a, cq.b);
            }
        }
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        switch (alz2.b(a)) {
            case f: {
                n2 |= 1;
                break;
            }
            case e: {
                n2 |= 2;
                break;
            }
            case d: {
                n2 |= 3;
                break;
            }
            case c: {
                n2 |= 4;
                break;
            }
            default: {
                n2 |= 5;
            }
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

