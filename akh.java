/*
 * Decompiled with CFR 0.152.
 */
public class akh
extends afh {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amk b = amk.a("open");
    public static final amm<a> N = amm.a("half", a.class);

    protected akh(arm arm2) {
        super(arm2);
        this.j(this.M.b().a(a, cq.c).a(b, false).a(N, akh$a.b));
        float f2 = 0.5f;
        \u2603 = 1.0f;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.a(yz.d);
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
    public boolean b(adq adq2, cj cj2) {
        return adq2.p(cj2).b(b) == false;
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        this.a((adq)adm2, cj2);
        return super.b(adm2, cj2);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.d(adq2.p(cj2));
    }

    @Override
    public void j() {
        float f2 = 0.1875f;
        this.a(0.0f, 0.40625f, 0.0f, 1.0f, 0.59375f, 1.0f);
    }

    public void d(alz alz2) {
        if (alz2.c() != this) {
            return;
        }
        boolean bl2 = alz2.b(N) == akh$a.a;
        Boolean \u26032 = alz2.b(b);
        cq \u26033 = alz2.b(a);
        float \u26034 = 0.1875f;
        if (bl2) {
            this.a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
        }
        if (\u26032.booleanValue()) {
            if (\u26033 == cq.c) {
                this.a(0.0f, 0.0f, 0.8125f, 1.0f, 1.0f, 1.0f);
            }
            if (\u26033 == cq.d) {
                this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.1875f);
            }
            if (\u26033 == cq.e) {
                this.a(0.8125f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
            if (\u26033 == cq.f) {
                this.a(0.0f, 0.0f, 0.0f, 0.1875f, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz22, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (this.J == arm.f) {
            return true;
        }
        alz alz22 = alz22.a(b);
        adm2.a(cj2, alz22, 2);
        adm2.a(wn2, alz22.b(b) != false ? 1003 : 1006, cj2, 0);
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (adm2.D) {
            return;
        }
        cj cj3 = cj2.a(alz2.b(a).d());
        if (!akh.c(adm2.p(cj3).c())) {
            adm2.g(cj2);
            this.b(adm2, cj2, alz2, 0);
            return;
        }
        boolean \u26032 = adm2.z(cj2);
        if ((\u26032 || afh2.i()) && (\u2603 = alz2.b(b).booleanValue()) != \u26032) {
            adm2.a(cj2, alz2.a(b, \u26032), 2);
            adm2.a(null, \u26032 ? 1003 : 1006, cj2, 0);
        }
    }

    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, aui2, aui3);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        alz alz2 = this.Q();
        if (cq2.k().c()) {
            alz2 = alz2.a(a, cq2).a(b, false);
            alz2 = alz2.a(N, f3 > 0.5f ? akh$a.a : akh$a.b);
        }
        return alz2;
    }

    @Override
    public boolean b(adm adm2, cj cj2, cq cq2) {
        return !cq2.k().b() && akh.c(adm2.p(cj2.a(cq2.d())).c());
    }

    protected static cq b(int n2) {
        switch (n2 & 3) {
            case 0: {
                return cq.c;
            }
            case 1: {
                return cq.d;
            }
            case 2: {
                return cq.e;
            }
        }
        return cq.f;
    }

    protected static int a(cq cq2) {
        switch (cq2) {
            case c: {
                return 0;
            }
            case d: {
                return 1;
            }
            case e: {
                return 2;
            }
        }
        return 3;
    }

    private static boolean c(afh afh2) {
        return afh2.J.k() && afh2.d() || afh2 == afi.aX || afh2 instanceof ahh || afh2 instanceof aju;
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, akh.b(n2)).a(b, (n2 & 4) != 0).a(N, (n2 & 8) == 0 ? akh$a.b : akh$a.a);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= akh.a(alz2.b(a));
        if (alz2.b(b).booleanValue()) {
            n2 |= 4;
        }
        if (alz2.b(N) == akh$a.a) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N);
    }

    public static enum a implements nw
    {
        a("top"),
        b("bottom");

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

