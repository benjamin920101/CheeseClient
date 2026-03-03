/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afg
extends age {
    public static final amm<a> a = amm.a("part", a.class);
    public static final amk b = amk.a("occupied");

    public afg() {
        super(arm.n);
        this.j(this.M.b().a(a, afg$a.b).a(b, false));
        this.l();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz22, wn wn22, cq cq2, float f2, float f3, float f4) {
        Object object;
        alz alz22;
        if (adm2.D) {
            return true;
        }
        if (alz22.b(a) != afg$a.a && (alz22 = adm2.p(cj2 = cj2.a(alz22.b(O)))).c() != this) {
            return true;
        }
        if (!adm2.t.e() || adm2.b(cj2) == ady.x) {
            adm2.g(cj2);
            cj cj3 = cj2.a(alz22.b(O).d());
            if (adm2.p(cj3).c() == this) {
                adm2.g(cj3);
            }
            adm2.a(null, (double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, 5.0f, true, true);
            return true;
        }
        if (alz22.b(b).booleanValue()) {
            object = this.f(adm2, cj2);
            if (object == null) {
                alz22 = alz22.a(b, false);
                adm2.a(cj2, alz22, 4);
            } else {
                wn wn22;
                wn22.b(new fb("tile.bed.occupied", new Object[0]));
                return true;
            }
        }
        if ((object = wn22.a(cj2)) == wn.a.a) {
            alz22 = alz22.a(b, true);
            adm2.a(cj2, alz22, 4);
            return true;
        }
        if (object == wn.a.c) {
            wn22.b(new fb("tile.bed.noSleep", new Object[0]));
        } else if (object == wn.a.f) {
            wn22.b(new fb("tile.bed.notSafe", new Object[0]));
        }
        return true;
    }

    private wn f(adm adm2, cj cj2) {
        for (wn wn2 : adm2.j) {
            if (!wn2.bJ() || !wn2.bx.equals(cj2)) continue;
            return wn2;
        }
        return null;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.l();
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        cq cq2 = alz2.b(O);
        if (alz2.b(a) == afg$a.a) {
            if (adm2.p(cj2.a(cq2.d())).c() != this) {
                adm2.g(cj2);
            }
        } else if (adm2.p(cj2.a(cq2)).c() != this) {
            adm2.g(cj2);
            if (!adm2.D) {
                this.b(adm2, cj2, alz2, 0);
            }
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (alz2.b(a) == afg$a.a) {
            return null;
        }
        return zy.ba;
    }

    private void l() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5625f, 1.0f);
    }

    public static cj a(adm adm2, cj cj2, int n2) {
        cq cq2 = adm2.p(cj2).b(O);
        int \u26032 = cj2.n();
        int \u26033 = cj2.o();
        int \u26034 = cj2.p();
        for (int i2 = 0; i2 <= 1; ++i2) {
            \u2603 = \u26032 - cq2.g() * i2 - 1;
            \u2603 = \u26034 - cq2.i() * i2 - 1;
            \u2603 = \u2603 + 2;
            \u2603 = \u2603 + 2;
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    cj cj3 = new cj(\u2603, \u26033, \u2603);
                    if (!afg.e(adm2, cj3)) continue;
                    if (n2 > 0) {
                        --n2;
                        continue;
                    }
                    return cj3;
                }
            }
        }
        return null;
    }

    protected static boolean e(adm adm2, cj cj2) {
        return adm.a(adm2, cj2.b()) && !adm2.p(cj2).c().t().a() && !adm2.p(cj2.a()).c().t().a();
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (alz2.b(a) == afg$a.b) {
            super.a(adm2, cj2, alz2, f2, 0);
        }
    }

    @Override
    public int k() {
        return 1;
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.ba;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        if (wn2.bA.d && alz2.b(a) == afg$a.a && adm2.p(\u2603 = cj2.a(alz2.b(O).d())).c() == this) {
            adm2.g(\u2603);
        }
    }

    @Override
    public alz a(int n2) {
        cq cq2 = cq.b(n2);
        if ((n2 & 8) > 0) {
            return this.Q().a(a, afg$a.a).a(O, cq2).a(b, (n2 & 4) > 0);
        }
        return this.Q().a(a, afg$a.b).a(O, cq2);
    }

    @Override
    public alz a(alz alz22, adq adq2, cj cj2) {
        alz alz22;
        if (alz22.b(a) == afg$a.b && (\u2603 = adq2.p(cj2.a(alz22.b(O)))).c() == this) {
            alz22 = alz22.a(b, \u2603.b(b));
        }
        return alz22;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(O).b();
        if (alz2.b(a) == afg$a.a) {
            n2 |= 8;
            if (alz2.b(b).booleanValue()) {
                n2 |= 4;
            }
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, O, a, b);
    }

    public static enum a implements nw
    {
        a("head"),
        b("foot");

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

