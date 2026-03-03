/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class als
extends afh {
    public static final aml a = aml.a("facing");
    public static final amk b = amk.a("extended");
    private final boolean N;

    public als(boolean bl2) {
        super(arm.H);
        this.j(this.M.b().a(a, cq.c).a(b, false));
        this.N = bl2;
        this.a(i);
        this.c(0.5f);
        this.a(yz.d);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        adm2.a(cj2, alz2.a(a, als.a(adm2, cj2, pr2)), 2);
        if (!adm2.D) {
            this.e(adm2, cj2, alz2);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm2.D) {
            this.e(adm2, cj2, alz2);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (!adm2.D && adm2.s(cj2) == null) {
            this.e(adm2, cj2, alz2);
        }
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, als.a(adm2, cj2, pr2)).a(b, false);
    }

    private void e(adm adm2, cj cj2, alz alz2) {
        cq cq2 = alz2.b(a);
        boolean \u26032 = this.a(adm2, cj2, cq2);
        if (\u26032 && !alz2.b(b).booleanValue()) {
            if (new alw(adm2, cj2, cq2, true).a()) {
                adm2.c(cj2, this, 0, cq2.a());
            }
        } else if (!\u26032 && alz2.b(b).booleanValue()) {
            adm2.a(cj2, alz2.a(b, false), 2);
            adm2.c(cj2, this, 1, cq2.a());
        }
    }

    private boolean a(adm adm2, cj cj2, cq cq2) {
        for (cq cq3 : cq.values()) {
            if (cq3 == cq2 || !adm2.b(cj2.a(cq3), cq3)) continue;
            return true;
        }
        if (adm2.b(cj2, cq.a)) {
            return true;
        }
        cj cj3 = cj2.a();
        for (cq cq4 : cq.values()) {
            if (cq4 == cq.a || !adm2.b(cj3.a(cq4), cq4)) continue;
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, int n22, int n3) {
        int n22;
        cq cq2 = alz2.b(a);
        if (!adm2.D) {
            boolean bl2 = this.a(adm2, cj2, cq2);
            if (bl2 && n22 == 1) {
                adm2.a(cj2, alz2.a(b, true), 2);
                return false;
            }
            if (!bl2 && n22 == 0) {
                return false;
            }
        }
        if (n22 == 0) {
            if (!this.a(adm2, cj2, cq2, true)) return false;
            adm2.a(cj2, alz2.a(b, true), 2);
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "tile.piston.out", 0.5f, adm2.s.nextFloat() * 0.25f + 0.6f);
            return true;
        } else {
            if (n22 != 1) return true;
            akw akw2 = adm2.s(cj2.a(cq2));
            if (akw2 instanceof alu) {
                ((alu)akw2).h();
            }
            adm2.a(cj2, afi.M.Q().a(alv.a, cq2).a(alv.b, this.N ? alt.a.b : alt.a.a), 3);
            adm2.a(cj2, alv.a(this.a(n3), cq2, false, true));
            if (this.N) {
                cj cj3 = cj2.a(cq2.g() * 2, cq2.h() * 2, cq2.i() * 2);
                afh \u26032 = adm2.p(cj3).c();
                boolean \u26033 = false;
                if (\u26032 == afi.M && (\u2603 = adm2.s(cj3)) instanceof alu && (\u2603 = (alu)\u2603).e() == cq2 && \u2603.d()) {
                    \u2603.h();
                    \u26033 = true;
                }
                if (!\u26033 && \u26032.t() != arm.a && als.a(\u26032, adm2, cj3, cq2.d(), false) && (\u26032.k() == 0 || \u26032 == afi.J || \u26032 == afi.F)) {
                    this.a(adm2, cj2, cq2, false);
                }
            } else {
                adm2.g(cj2.a(cq2));
            }
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "tile.piston.in", 0.5f, adm2.s.nextFloat() * 0.15f + 0.6f);
        }
        return true;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        if (alz2.c() == this && alz2.b(b).booleanValue()) {
            float f2 = 0.25f;
            cq \u26032 = alz2.b(a);
            if (\u26032 != null) {
                switch (\u26032) {
                    case a: {
                        this.a(0.0f, 0.25f, 0.0f, 1.0f, 1.0f, 1.0f);
                        break;
                    }
                    case b: {
                        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
                        break;
                    }
                    case c: {
                        this.a(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                        break;
                    }
                    case d: {
                        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                        break;
                    }
                    case e: {
                        this.a(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        break;
                    }
                    case f: {
                        this.a(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
                    }
                }
            }
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public boolean d() {
        return false;
    }

    public static cq b(int n2) {
        \u2603 = n2 & 7;
        if (\u2603 > 5) {
            return null;
        }
        return cq.a(\u2603);
    }

    public static cq a(adm adm2, cj cj2, pr pr22) {
        pr pr22;
        if (ns.e((float)pr22.s - (float)cj2.n()) < 2.0f && ns.e((float)pr22.u - (float)cj2.p()) < 2.0f) {
            double d2 = pr22.t + (double)pr22.aS();
            if (d2 - (double)cj2.o() > 2.0) {
                return cq.b;
            }
            if ((double)cj2.o() - d2 > 0.0) {
                return cq.a;
            }
        }
        return pr22.aP().d();
    }

    public static boolean a(afh afh2, adm adm2, cj cj2, cq cq2, boolean bl2) {
        if (afh2 == afi.Z) {
            return false;
        }
        if (!adm2.af().a(cj2)) {
            return false;
        }
        if (cj2.o() < 0 || cq2 == cq.a && cj2.o() == 0) {
            return false;
        }
        if (cj2.o() > adm2.U() - 1 || cq2 == cq.b && cj2.o() == adm2.U() - 1) {
            return false;
        }
        if (afh2 == afi.J || afh2 == afi.F) {
            if (adm2.p(cj2).b(b).booleanValue()) {
                return false;
            }
        } else {
            if (afh2.g(adm2, cj2) == -1.0f) {
                return false;
            }
            if (afh2.k() == 2) {
                return false;
            }
            if (afh2.k() == 1) {
                return bl2;
            }
        }
        return !(afh2 instanceof agq);
    }

    private boolean a(adm adm2, cj cj2, cq cq2, boolean bl22) {
        boolean bl22;
        int n2;
        Object object;
        int n3;
        if (!bl22) {
            adm2.g(cj2.a(cq2));
        }
        alw alw2 = new alw(adm2, cj2, cq2, bl22);
        List<cj> \u26032 = alw2.c();
        List<cj> \u26033 = alw2.d();
        if (!alw2.a()) {
            return false;
        }
        int \u26034 = \u26032.size() + \u26033.size();
        afh[] \u26035 = new afh[\u26034];
        cq \u26036 = bl22 ? cq2 : cq2.d();
        for (n3 = \u26033.size() - 1; n3 >= 0; --n3) {
            object = \u26033.get(n3);
            \u2603 = adm2.p((cj)object).c();
            ((afh)\u2603).b(adm2, (cj)object, adm2.p((cj)object), 0);
            adm2.g((cj)object);
            \u26035[--\u26034] = \u2603;
        }
        for (n3 = \u26032.size() - 1; n3 >= 0; --n3) {
            object = \u26032.get(n3);
            \u2603 = adm2.p((cj)object);
            \u2603 = \u2603.c();
            int \u26037 = ((afh)\u2603).c((alz)\u2603);
            adm2.g((cj)object);
            object = ((cj)object).a(\u26036);
            adm2.a((cj)object, afi.M.Q().a(a, cq2), 4);
            adm2.a((cj)object, alv.a((alz)\u2603, cq2, bl22, false));
            \u26035[--\u26034] = \u2603;
        }
        cj cj3 = cj2.a(cq2);
        if (bl22) {
            object = this.N ? alt.a.b : alt.a.a;
            \u2603 = afi.K.Q().a(alt.a, cq2).a(alt.b, object);
            \u2603 = afi.M.Q().a(alv.a, cq2).a(alv.b, this.N ? alt.a.b : alt.a.a);
            adm2.a(cj3, (alz)\u2603, 4);
            adm2.a(cj3, alv.a((alz)\u2603, cq2, true, false));
        }
        for (n2 = \u26033.size() - 1; n2 >= 0; --n2) {
            adm2.c(\u26033.get(n2), \u26035[\u26034++]);
        }
        for (n2 = \u26032.size() - 1; n2 >= 0; --n2) {
            adm2.c(\u26032.get(n2), \u26035[\u26034++]);
        }
        if (bl22) {
            adm2.c(cj3, afi.K);
            adm2.c(cj2, this);
        }
        return true;
    }

    @Override
    public alz b(alz alz2) {
        return this.Q().a(a, cq.b);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, als.b(n2)).a(b, (n2 & 8) > 0);
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

