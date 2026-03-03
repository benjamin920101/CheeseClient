/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class akk
extends afh {
    public static final amk a = amk.a("up");
    public static final amk b = amk.a("north");
    public static final amk N = amk.a("east");
    public static final amk O = amk.a("south");
    public static final amk P = amk.a("west");
    public static final amk[] Q = new amk[]{a, b, O, P, N};

    public akk() {
        super(arm.l);
        this.j(this.M.b().a(a, false).a(b, false).a(N, false).a(O, false).a(P, false));
        this.a(true);
        this.a(yz.c);
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(a, adq2.p(cj2.a()).c().u());
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
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
    public boolean a(adm adm2, cj cj2) {
        return true;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        float f2 = 0.0625f;
        \u2603 = 1.0f;
        \u2603 = 1.0f;
        \u2603 = 1.0f;
        \u2603 = 0.0f;
        \u2603 = 0.0f;
        \u2603 = 0.0f;
        boolean \u26032 = false;
        if (adq2.p(cj2).b(P).booleanValue()) {
            \u2603 = Math.max(\u2603, 0.0625f);
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u26032 = true;
        }
        if (adq2.p(cj2).b(N).booleanValue()) {
            \u2603 = Math.min(\u2603, 0.9375f);
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u26032 = true;
        }
        if (adq2.p(cj2).b(b).booleanValue()) {
            \u2603 = Math.max(\u2603, 0.0625f);
            \u2603 = 0.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u26032 = true;
        }
        if (adq2.p(cj2).b(O).booleanValue()) {
            \u2603 = Math.min(\u2603, 0.9375f);
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u26032 = true;
        }
        if (!\u26032 && this.c(adq2.p(cj2.a()).c())) {
            \u2603 = Math.min(\u2603, 0.9375f);
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
            \u2603 = 0.0f;
            \u2603 = 1.0f;
        }
        this.a(\u2603, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public boolean b(adm adm2, cj cj2, cq cq2) {
        switch (cq2) {
            default: {
                return false;
            }
            case b: {
                return this.c(adm2.p(cj2.a()).c());
            }
            case c: 
            case d: 
            case f: 
            case e: 
        }
        return this.c(adm2.p(cj2.a(cq2.d())).c());
    }

    private boolean c(afh afh2) {
        return afh2.d() && afh2.J.c();
    }

    private boolean e(adm adm2, cj cj2, alz \u260322) {
        alz \u260322;
        \u2603 = \u260322;
        for (cq cq2 : cq.c.a) {
            amk amk2 = akk.a(cq2);
            if (!\u260322.b(amk2).booleanValue() || this.c(adm2.p(cj2.a(cq2)).c()) || (\u2603 = adm2.p(cj2.a())).c() == this && \u2603.b(amk2).booleanValue()) continue;
            \u260322 = \u260322.a(amk2, false);
        }
        if (akk.d(\u260322) == 0) {
            return false;
        }
        if (\u2603 != \u260322) {
            adm2.a(cj2, \u260322, 2);
        }
        return true;
    }

    @Override
    public int H() {
        return adj.c();
    }

    @Override
    public int h(alz alz2) {
        return adj.c();
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return adq2.b(cj2).c(cj2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm2.D && !this.e(adm2, cj2, alz2)) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz22, Random random) {
        alz alz22;
        if (adm22.D) {
            return;
        }
        if (adm22.s.nextInt(4) != 0) {
            return;
        }
        int n2 = 4;
        \u2603 = 5;
        boolean \u26032 = false;
        block0: for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
            for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                for (\u2603 = -1; \u2603 <= 1; ++\u2603) {
                    if (adm22.p(cj2.a(\u2603, \u2603, \u2603)).c() != this || --\u2603 > 0) continue;
                    \u26032 = true;
                    break block0;
                }
            }
        }
        cq \u26033 = cq.a(random);
        cj \u26034 = cj2.a();
        if (\u26033 == cq.b && cj2.o() < 255 && adm22.d(\u26034)) {
            if (\u26032) {
                return;
            }
            alz alz3 = alz22;
            for (cq cq2 : cq.c.a) {
                if (!random.nextBoolean() && this.c(adm22.p(\u26034.a(cq2)).c())) continue;
                alz3 = alz3.a(akk.a(cq2), false);
            }
            if (alz3.b(b).booleanValue() || alz3.b(N).booleanValue() || alz3.b(O).booleanValue() || alz3.b(P).booleanValue()) {
                adm22.a(\u26034, alz3, 2);
            }
            return;
        }
        if (\u26033.k().c() && !alz22.b(akk.a(\u26033)).booleanValue()) {
            if (\u26032) {
                return;
            }
            cj cj3 = cj2.a(\u26033);
            afh \u26035 = adm22.p(cj3).c();
            if (\u26035.J == arm.a) {
                cq cq3 = \u26033.e();
                \u2603 = \u26033.f();
                boolean \u26036 = alz22.b(akk.a(cq3));
                boolean \u26037 = alz22.b(akk.a(\u2603));
                cj \u26038 = cj3.a(cq3);
                cj \u26039 = cj3.a(\u2603);
                if (\u26036 && this.c(adm22.p(\u26038).c())) {
                    adm22.a(cj3, this.Q().a(akk.a(cq3), true), 2);
                } else if (\u26037 && this.c(adm22.p(\u26039).c())) {
                    adm22.a(cj3, this.Q().a(akk.a(\u2603), true), 2);
                } else if (\u26036 && adm22.d(\u26038) && this.c(adm22.p(cj2.a(cq3)).c())) {
                    adm22.a(\u26038, this.Q().a(akk.a(\u26033.d()), true), 2);
                } else if (\u26037 && adm22.d(\u26039) && this.c(adm22.p(cj2.a(\u2603)).c())) {
                    adm22.a(\u26039, this.Q().a(akk.a(\u26033.d()), true), 2);
                } else if (this.c(adm22.p(cj3.a()).c())) {
                    adm22.a(cj3, this.Q(), 2);
                }
            } else if (\u26035.J.k() && \u26035.d()) {
                adm22.a(cj2, alz22.a(akk.a(\u26033), true), 2);
            }
            return;
        }
        if (cj2.o() > 1) {
            cj cj4 = cj2.b();
            alz \u260310 = adm22.p(cj4);
            afh \u260311 = \u260310.c();
            if (\u260311.J == arm.a) {
                alz alz4 = alz22;
                for (cq cq4 : cq.c.a) {
                    if (!random.nextBoolean()) continue;
                    alz4 = alz4.a(akk.a(cq4), false);
                }
                if (alz4.b(b).booleanValue() || alz4.b(N).booleanValue() || alz4.b(O).booleanValue() || alz4.b(P).booleanValue()) {
                    adm22.a(cj4, alz4, 2);
                }
            } else if (\u260311 == this) {
                alz \u260312 = \u260310;
                for (cq cq5 : cq.c.a) {
                    amk amk2 = akk.a(cq5);
                    if (!random.nextBoolean() || !alz22.b(amk2).booleanValue()) continue;
                    \u260312 = \u260312.a(amk2, true);
                }
                if (\u260312.b(b).booleanValue() || \u260312.b(N).booleanValue() || \u260312.b(O).booleanValue() || \u260312.b(P).booleanValue()) {
                    adm adm22;
                    adm22.a(cj4, \u260312, 2);
                }
            }
        }
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        alz alz2 = this.Q().a(a, false).a(b, false).a(N, false).a(O, false).a(P, false);
        if (cq2.k().c()) {
            return alz2.a(akk.a(cq2.d()), true);
        }
        return alz2;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (!adm2.D && wn2.bZ() != null && wn2.bZ().b() == zy.be) {
            wn2.b(na.ab[afh.a(this)]);
            akk.a(adm2, cj2, new zx(afi.bn, 1, 0));
        } else {
            super.a(adm2, wn2, cj2, alz2, akw2);
        }
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, (n2 & 1) > 0).a(P, (n2 & 2) > 0).a(b, (n2 & 4) > 0).a(N, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        if (alz2.b(O).booleanValue()) {
            n2 |= 1;
        }
        if (alz2.b(P).booleanValue()) {
            n2 |= 2;
        }
        if (alz2.b(b).booleanValue()) {
            n2 |= 4;
        }
        if (alz2.b(N).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N, O, P);
    }

    public static amk a(cq cq2) {
        switch (cq2) {
            case b: {
                return a;
            }
            case c: {
                return b;
            }
            case d: {
                return O;
            }
            case e: {
                return P;
            }
            case f: {
                return N;
            }
        }
        throw new IllegalArgumentException(cq2 + " is an invalid choice");
    }

    public static int d(alz alz2) {
        int n2 = 0;
        for (amk amk2 : Q) {
            if (!alz2.b(amk2).booleanValue()) continue;
            ++n2;
        }
        return n2;
    }
}

