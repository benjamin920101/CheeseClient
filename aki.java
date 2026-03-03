/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class aki
extends afh {
    public static final amk a = amk.a("powered");
    public static final amk b = amk.a("suspended");
    public static final amk N = amk.a("attached");
    public static final amk O = amk.a("disarmed");
    public static final amk P = amk.a("north");
    public static final amk Q = amk.a("east");
    public static final amk R = amk.a("south");
    public static final amk S = amk.a("west");

    public aki() {
        super(arm.q);
        this.j(this.M.b().a(a, false).a(b, false).a(N, false).a(O, false).a(P, false).a(Q, false).a(R, false).a(S, false));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.15625f, 1.0f);
        this.a(true);
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(P, aki.c(adq2, cj2, alz2, cq.c)).a(Q, aki.c(adq2, cj2, alz2, cq.f)).a(R, aki.c(adq2, cj2, alz2, cq.d)).a(S, aki.c(adq2, cj2, alz2, cq.e));
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

    @Override
    public adf m() {
        return adf.d;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.F;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.F;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        boolean bl2 = alz2.b(b);
        boolean bl3 = \u2603 = !adm.a(adm2, cj2.b());
        if (bl2 != \u2603) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        boolean \u26032 = alz2.b(N);
        boolean \u26033 = alz2.b(b);
        if (!\u26033) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.09375f, 1.0f);
        } else if (!\u26032) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        } else {
            this.a(0.0f, 0.0625f, 0.0f, 1.0f, 0.15625f, 1.0f);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        alz2 = alz2.a(b, !adm.a(adm2, cj2.b()));
        adm2.a(cj2, alz2, 3);
        this.e(adm2, cj2, alz2);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        this.e(adm2, cj2, alz2.a(a, true));
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        if (adm2.D) {
            return;
        }
        if (wn2.bZ() != null && wn2.bZ().b() == zy.be) {
            adm2.a(cj2, alz2.a(O, true), 4);
        }
    }

    private void e(adm adm2, cj cj2, alz alz2) {
        block0: for (cq cq2 : new cq[]{cq.d, cq.e}) {
            for (int i2 = 1; i2 < 42; ++i2) {
                cj cj3 = cj2.a(cq2, i2);
                alz \u26032 = adm2.p(cj3);
                if (\u26032.c() == afi.bR) {
                    if (\u26032.b(akj.a) != cq2.d()) continue block0;
                    afi.bR.a(adm2, cj3, \u26032, false, true, i2, alz2);
                    continue block0;
                }
                if (\u26032.c() != afi.bS) continue block0;
            }
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (adm2.D) {
            return;
        }
        if (alz2.b(a).booleanValue()) {
            return;
        }
        this.e(adm2, cj2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        if (!adm2.p(cj2).b(a).booleanValue()) {
            return;
        }
        this.e(adm2, cj2);
    }

    private void e(adm adm22, cj cj2) {
        alz alz2 = adm22.p(cj2);
        boolean \u26032 = alz2.b(a);
        boolean \u26033 = false;
        List<pk> \u26034 = adm22.b(null, new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (double)cj2.o() + this.F, (double)cj2.p() + this.G));
        if (!\u26034.isEmpty()) {
            for (pk pk2 : \u26034) {
                if (pk2.aI()) continue;
                \u26033 = true;
                break;
            }
        }
        if (\u26033 != \u26032) {
            alz2 = alz2.a(a, \u26033);
            adm22.a(cj2, alz2, 3);
            this.e(adm22, cj2, alz2);
        }
        if (\u26033) {
            adm adm22;
            adm22.a(cj2, (afh)this, this.a(adm22));
        }
    }

    public static boolean c(adq adq2, cj cj2, alz alz22, cq cq2) {
        cj cj3 = cj2.a(cq2);
        alz \u26032 = adq2.p(cj3);
        afh \u26033 = \u26032.c();
        if (\u26033 == afi.bR) {
            cq cq3 = cq2.d();
            return \u26032.b(akj.a) == cq3;
        }
        if (\u26033 == afi.bS) {
            alz alz22;
            boolean \u26034 = alz22.b(b);
            return \u26034 == (\u2603 = \u26032.b(b).booleanValue());
        }
        return false;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 1) > 0).a(b, (n2 & 2) > 0).a(N, (n2 & 4) > 0).a(O, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        if (alz2.b(a).booleanValue()) {
            n2 |= 1;
        }
        if (alz2.b(b).booleanValue()) {
            n2 |= 2;
        }
        if (alz2.b(N).booleanValue()) {
            n2 |= 4;
        }
        if (alz2.b(O).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N, O, P, Q, S, R);
    }
}

