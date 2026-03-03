/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Random;

public class ajm
extends afc {
    public static final aml a = aml.a("facing");
    public static final amk b = amk.a("nodrop");
    private static final Predicate<amc> N = new Predicate<amc>(){

        public boolean a(amc amc2) {
            return amc2.a() != null && amc2.a().c() == afi.ce && amc2.b() instanceof alo && ((alo)amc2.b()).c() == 1;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((amc)object);
        }
    };
    private amd O;
    private amd P;

    protected ajm() {
        super(arm.q);
        this.j(this.M.b().a(a, cq.c).a(b, false));
        this.a(0.25f, 0.0f, 0.25f, 0.75f, 0.5f, 0.75f);
    }

    @Override
    public String f() {
        return di.a("tile.skull.skeleton.name");
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
    public void a(adq adq2, cj cj2) {
        switch (adq2.p(cj2).b(a)) {
            default: {
                this.a(0.25f, 0.0f, 0.25f, 0.75f, 0.5f, 0.75f);
                break;
            }
            case c: {
                this.a(0.25f, 0.25f, 0.5f, 0.75f, 0.75f, 1.0f);
                break;
            }
            case d: {
                this.a(0.25f, 0.25f, 0.0f, 0.75f, 0.75f, 0.5f);
                break;
            }
            case e: {
                this.a(0.5f, 0.25f, 0.25f, 1.0f, 0.75f, 0.75f);
                break;
            }
            case f: {
                this.a(0.0f, 0.25f, 0.25f, 0.5f, 0.75f, 0.75f);
            }
        }
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, pr2.aP()).a(b, false);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alo();
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.bX;
    }

    @Override
    public int j(adm adm2, cj cj2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alo) {
            return ((alo)akw2).c();
        }
        return super.j(adm2, cj2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
    }

    @Override
    public void a(adm adm22, cj cj2, alz alz22, wn wn2) {
        adm adm22;
        if (wn2.bA.d) {
            alz alz22 = alz22.a(b, true);
            adm22.a(cj2, alz22, 4);
        }
        super.a(adm22, cj2, alz22, wn2);
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz2) {
        adm adm22;
        if (adm22.D) {
            return;
        }
        if (!alz2.b(b).booleanValue() && (\u2603 = adm22.s(cj2)) instanceof alo) {
            alo alo2 = (alo)\u2603;
            zx \u26032 = new zx(zy.bX, 1, this.j(adm22, cj2));
            if (alo2.c() == 3 && alo2.b() != null) {
                \u26032.d(new dn());
                dn dn2 = new dn();
                dy.a(dn2, alo2.b());
                \u26032.o().a("SkullOwner", dn2);
            }
            ajm.a(adm22, cj2, \u26032);
        }
        super.b(adm22, cj2, alz2);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bX;
    }

    public boolean b(adm adm2, cj cj2, zx zx2) {
        if (zx2.i() == 1 && cj2.o() >= 2 && adm2.aa() != oj.a && !adm2.D) {
            return this.l().a(adm2, cj2) != null;
        }
        return false;
    }

    public void a(adm adm22, cj cj2, alo alo2) {
        int n2;
        adm adm22;
        Object \u26034;
        int n3;
        if (alo2.c() != 1 || cj2.o() < 2 || adm22.aa() == oj.a || adm22.D) {
            return;
        }
        amd amd2 = this.n();
        amd.b \u26032 = amd2.a(adm22, cj2);
        if (\u26032 == null) {
            return;
        }
        for (n3 = 0; n3 < 3; ++n3) {
            amc amc2 = \u26032.a(n3, 0, 0);
            adm22.a(amc2.d(), amc2.a().a(b, true), 2);
        }
        for (n3 = 0; n3 < amd2.c(); ++n3) {
            for (\u2603 = 0; \u2603 < amd2.b(); ++\u2603) {
                \u26034 = \u26032.a(n3, \u2603, 0);
                adm22.a(((amc)\u26034).d(), afi.a.Q(), 2);
            }
        }
        cj cj3 = \u26032.a(1, 0, 0).d();
        uk \u26033 = new uk(adm22);
        \u26034 = \u26032.a(1, 2, 0).d();
        \u26033.b((double)((df)\u26034).n() + 0.5, (double)((df)\u26034).o() + 0.55, (double)((df)\u26034).p() + 0.5, \u26032.b().k() == cq.a.a ? 0.0f : 90.0f, 0.0f);
        \u26033.aI = \u26032.b().k() == cq.a.a ? 0.0f : 90.0f;
        \u26033.n();
        for (wn wn2 : adm22.a(wn.class, \u26033.aR().b(50.0, 50.0, 50.0))) {
            wn2.b(mr.I);
        }
        adm22.d(\u26033);
        for (n2 = 0; n2 < 120; ++n2) {
            adm22.a(cy.F, (double)cj3.n() + adm22.s.nextDouble(), (double)(cj3.o() - 2) + adm22.s.nextDouble() * 3.9, (double)cj3.p() + adm22.s.nextDouble(), 0.0, 0.0, 0.0, new int[0]);
        }
        for (n2 = 0; n2 < amd2.c(); ++n2) {
            for (\u2603 = 0; \u2603 < amd2.b(); ++\u2603) {
                amc amc3 = \u26032.a(n2, \u2603, 0);
                adm22.b(amc3.d(), afi.a);
            }
        }
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, cq.a(n2 & 7)).a(b, (n2 & 8) > 0);
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

    protected amd l() {
        if (this.O == null) {
            this.O = ame.a().a("   ", "###", "~#~").a('#', amc.a(amh.a(afi.aW))).a('~', amc.a(amh.a(afi.a))).b();
        }
        return this.O;
    }

    protected amd n() {
        if (this.P == null) {
            this.P = ame.a().a("^^^", "###", "~#~").a('#', amc.a(amh.a(afi.aW))).a('^', N).a('~', amc.a(amh.a(afi.a))).b();
        }
        return this.P;
    }
}

