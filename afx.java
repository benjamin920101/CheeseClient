/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Random;

public class afx
extends agd
implements agq {
    public static final amk a = amk.a("powered");
    public static final amm<a> b = amm.a("mode", a.class);

    public afx(boolean bl2) {
        super(bl2);
        this.j(this.M.b().a(O, cq.c).a(a, false).a(b, afx$a.a));
        this.A = true;
    }

    @Override
    public String f() {
        return di.a("item.comparator.name");
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.ce;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.ce;
    }

    @Override
    protected int d(alz alz2) {
        return 2;
    }

    @Override
    protected alz e(alz alz2) {
        Boolean bl2 = alz2.b(a);
        a \u26032 = alz2.b(b);
        cq \u26033 = alz2.b(O);
        return afi.ck.Q().a(O, \u26033).a(a, bl2).a(b, \u26032);
    }

    @Override
    protected alz k(alz alz2) {
        Boolean bl2 = alz2.b(a);
        a \u26032 = alz2.b(b);
        cq \u26033 = alz2.b(O);
        return afi.cj.Q().a(O, \u26033).a(a, bl2).a(b, \u26032);
    }

    @Override
    protected boolean l(alz alz2) {
        return this.N || alz2.b(a) != false;
    }

    @Override
    protected int a(adq adq2, cj cj2, alz alz2) {
        akw akw2 = adq2.s(cj2);
        if (akw2 instanceof ala) {
            return ((ala)akw2).b();
        }
        return 0;
    }

    private int j(adm adm2, cj cj2, alz alz2) {
        if (alz2.b(b) == afx$a.b) {
            return Math.max(this.f(adm2, cj2, alz2) - this.c((adq)adm2, cj2, alz2), 0);
        }
        return this.f(adm2, cj2, alz2);
    }

    @Override
    protected boolean e(adm adm2, cj cj2, alz alz2) {
        int n2 = this.f(adm2, cj2, alz2);
        if (n2 >= 15) {
            return true;
        }
        if (n2 == 0) {
            return false;
        }
        \u2603 = this.c((adq)adm2, cj2, alz2);
        if (\u2603 == 0) {
            return true;
        }
        return n2 >= \u2603;
    }

    @Override
    protected int f(adm adm2, cj cj2, alz alz2) {
        int n2 = super.f(adm2, cj2, alz2);
        cq \u26032 = alz2.b(O);
        cj \u26033 = cj2.a(\u26032);
        afh \u26034 = adm2.p(\u26033).c();
        if (\u26034.O()) {
            n2 = \u26034.l(adm2, \u26033);
        } else if (n2 < 15 && \u26034.v()) {
            \u26034 = adm2.p(\u26033 = \u26033.a(\u26032)).c();
            if (\u26034.O()) {
                n2 = \u26034.l(adm2, \u26033);
            } else if (\u26034.t() == arm.a && (\u2603 = this.a(adm2, \u26032, \u26033)) != null) {
                n2 = \u2603.q();
            }
        }
        return n2;
    }

    private uo a(adm adm2, final cq cq2, cj cj2) {
        1 \u2603 = adm2.a(uo.class, new aug(cj2.n(), cj2.o(), cj2.p(), cj2.n() + 1, cj2.o() + 1, cj2.p() + 1), new Predicate<pk>(){

            public boolean a(pk pk2) {
                return pk2 != null && pk2.aP() == cq2;
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pk)object);
            }
        });
        if (\u2603.size() == 1) {
            return (uo)\u2603.get(0);
        }
        return null;
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz22, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (!wn2.bA.e) {
            return false;
        }
        alz alz22 = alz22.a(b);
        adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, alz22.b(b) == afx$a.b ? 0.55f : 0.5f);
        adm2.a(cj2, alz22, 2);
        this.k(adm2, cj2, alz22);
        return true;
    }

    @Override
    protected void g(adm adm2, cj cj2, alz alz2) {
        if (adm2.a(cj2, this)) {
            return;
        }
        int n2 = this.j(adm2, cj2, alz2);
        akw \u26032 = adm2.s(cj2);
        int n3 = \u2603 = \u26032 instanceof ala ? ((ala)\u26032).b() : 0;
        if (n2 != \u2603 || this.l(alz2) != this.e(adm2, cj2, alz2)) {
            if (this.i(adm2, cj2, alz2)) {
                adm2.a(cj2, this, 2, -1);
            } else {
                adm2.a(cj2, this, 2, 0);
            }
        }
    }

    private void k(adm adm2, cj cj2, alz alz2) {
        int \u26033;
        int n2 = this.j(adm2, cj2, alz2);
        akw \u26032 = adm2.s(cj2);
        \u26033 = 0;
        if (\u26032 instanceof ala) {
            ala ala2 = (ala)\u26032;
            \u26033 = ala2.b();
            ala2.a(n2);
        }
        if (\u26033 != n2 || alz2.b(b) == afx$a.a) {
            boolean \u26034 = this.e(adm2, cj2, alz2);
            boolean \u26035 = this.l(alz2);
            if (\u26035 && !\u26034) {
                adm2.a(cj2, alz2.a(a, false), 2);
            } else if (!\u26035 && \u26034) {
                adm2.a(cj2, alz2.a(a, true), 2);
            }
            this.h(adm2, cj2, alz2);
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (this.N) {
            adm2.a(cj2, this.k(alz2).a(a, true), 4);
        }
        this.k(adm2, cj2, alz2);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        super.c(adm2, cj2, alz2);
        adm2.a(cj2, this.a(adm2, 0));
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        super.b(adm2, cj2, alz2);
        adm2.t(cj2);
        this.h(adm2, cj2, alz2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, int n2, int n3) {
        super.a(adm2, cj2, alz2, n2, n3);
        akw akw2 = adm2.s(cj2);
        if (akw2 == null) {
            return false;
        }
        return akw2.c(n2, n3);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new ala();
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, cq.b(n2)).a(a, (n2 & 8) > 0).a(b, (n2 & 4) > 0 ? afx$a.b : afx$a.a);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(O).b();
        if (alz2.b(a).booleanValue()) {
            n2 |= 8;
        }
        if (alz2.b(b) == afx$a.b) {
            n2 |= 4;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, O, b, a);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(O, pr2.aP().d()).a(a, false).a(b, afx$a.a);
    }

    public static enum a implements nw
    {
        a("compare"),
        b("subtract");

        private final String c;

        private a(String string2) {
            this.c = string2;
        }

        public String toString() {
            return this.c;
        }

        @Override
        public String l() {
            return this.c;
        }
    }
}

