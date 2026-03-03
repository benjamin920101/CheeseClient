/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;

public class aiv
extends age {
    private amd a;
    private amd b;
    private amd N;
    private amd P;
    private static final Predicate<alz> Q = new Predicate<alz>(){

        public boolean a(alz alz2) {
            return alz2 != null && (alz2.c() == afi.aU || alz2.c() == afi.aZ);
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((alz)object);
        }
    };

    protected aiv() {
        super(arm.C, arn.q);
        this.j(this.M.b().a(O, cq.c));
        this.a(true);
        this.a(yz.b);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        super.c(adm2, cj2, alz2);
        this.f(adm2, cj2);
    }

    public boolean e(adm adm2, cj cj2) {
        return this.l().a(adm2, cj2) != null || this.T().a(adm2, cj2) != null;
    }

    private void f(adm adm22, cj cj2) {
        block9: {
            int n2;
            adm adm22;
            amd.b \u26034;
            block8: {
                int n3;
                Object \u26033;
                \u26034 = this.n().a(adm22, cj2);
                if (\u26034 == null) break block8;
                for (int i2 = 0; i2 < this.n().b(); ++i2) {
                    \u26033 = \u26034.a(0, i2, 0);
                    adm22.a(((amc)\u26033).d(), afi.a.Q(), 2);
                }
                tw \u26032 = new tw(adm22);
                \u26033 = \u26034.a(0, 2, 0).d();
                \u26032.b((double)((df)\u26033).n() + 0.5, (double)((df)\u26033).o() + 0.05, (double)((df)\u26033).p() + 0.5, 0.0f, 0.0f);
                adm22.d(\u26032);
                for (n3 = 0; n3 < 120; ++n3) {
                    adm22.a(cy.G, (double)((df)\u26033).n() + adm22.s.nextDouble(), (double)((df)\u26033).o() + adm22.s.nextDouble() * 2.5, (double)((df)\u26033).p() + adm22.s.nextDouble(), 0.0, 0.0, 0.0, new int[0]);
                }
                for (n3 = 0; n3 < this.n().b(); ++n3) {
                    amc amc2 = \u26034.a(0, n3, 0);
                    adm22.b(amc2.d(), afi.a);
                }
                break block9;
            }
            \u26034 = this.U().a(adm22, cj2);
            if (\u26034 == null) break block9;
            for (int i3 = 0; i3 < this.U().c(); ++i3) {
                for (\u2603 = 0; \u2603 < this.U().b(); ++\u2603) {
                    adm22.a(\u26034.a(i3, \u2603, 0).d(), afi.a.Q(), 2);
                }
            }
            cj cj3 = \u26034.a(1, 2, 0).d();
            ty \u26035 = new ty(adm22);
            \u26035.l(true);
            \u26035.b((double)cj3.n() + 0.5, (double)cj3.o() + 0.05, (double)cj3.p() + 0.5, 0.0f, 0.0f);
            adm22.d(\u26035);
            for (n2 = 0; n2 < 120; ++n2) {
                adm22.a(cy.F, (double)cj3.n() + adm22.s.nextDouble(), (double)cj3.o() + adm22.s.nextDouble() * 3.9, (double)cj3.p() + adm22.s.nextDouble(), 0.0, 0.0, 0.0, new int[0]);
            }
            for (n2 = 0; n2 < this.U().c(); ++n2) {
                for (\u2603 = 0; \u2603 < this.U().b(); ++\u2603) {
                    amc amc3 = \u26034.a(n2, \u2603, 0);
                    adm22.b(amc3.d(), afi.a);
                }
            }
        }
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return adm2.p((cj)cj2).c().J.j() && adm.a(adm2, cj2.b());
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(O, pr2.aP().d());
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, cq.b(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(O).b();
    }

    @Override
    protected ama e() {
        return new ama(this, O);
    }

    protected amd l() {
        if (this.a == null) {
            this.a = ame.a().a(" ", "#", "#").a('#', amc.a(amh.a(afi.aJ))).b();
        }
        return this.a;
    }

    protected amd n() {
        if (this.b == null) {
            this.b = ame.a().a("^", "#", "#").a('^', amc.a(Q)).a('#', amc.a(amh.a(afi.aJ))).b();
        }
        return this.b;
    }

    protected amd T() {
        if (this.N == null) {
            this.N = ame.a().a("~ ~", "###", "~#~").a('#', amc.a(amh.a(afi.S))).a('~', amc.a(amh.a(afi.a))).b();
        }
        return this.N;
    }

    protected amd U() {
        if (this.P == null) {
            this.P = ame.a().a("~^~", "###", "~#~").a('^', amc.a(Q)).a('#', amc.a(amh.a(afi.S))).a('~', amc.a(amh.a(afi.a))).b();
        }
        return this.P;
    }
}

