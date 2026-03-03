/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class agi
extends afm
implements afj {
    public static final amm<b> a = amm.a("variant", b.class);
    public static final amm<a> b = amm.a("half", a.class);
    public static final amm<cq> N = age.O;

    public agi() {
        super(arm.l);
        this.j(this.M.b().a(a, agi$b.a).a(b, agi$a.b).a(N, cq.c));
        this.c(0.0f);
        this.a(h);
        this.c("doublePlant");
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public b e(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        if (alz2.c() == this) {
            alz2 = this.a(alz2, adq2, cj2);
            return alz2.b(a);
        }
        return agi$b.d;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return super.d(adm2, cj2) && adm2.d(cj2.a());
    }

    @Override
    public boolean a(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        if (alz2.c() == this) {
            b b2 = this.a(alz2, (adq)adm2, cj2).b(a);
            return b2 == agi$b.d || b2 == agi$b.c;
        }
        return true;
    }

    @Override
    protected void e(adm adm2, cj cj2, alz alz2) {
        if (this.f(adm2, cj2, alz2)) {
            return;
        }
        boolean bl2 = alz2.b(b) == agi$a.a;
        cj \u26032 = bl2 ? cj2 : cj2.a();
        cj \u26033 = bl2 ? cj2.b() : cj2;
        agi \u26034 = bl2 ? this : adm2.p(\u26032).c();
        afh afh2 = \u2603 = bl2 ? adm2.p(\u26033).c() : this;
        if (\u26034 == this) {
            adm2.a(\u26032, afi.a.Q(), 2);
        }
        if (\u2603 == this) {
            adm2.a(\u26033, afi.a.Q(), 3);
            if (!bl2) {
                this.b(adm2, \u26033, alz2, 0);
            }
        }
    }

    @Override
    public boolean f(adm adm2, cj cj2, alz alz2) {
        if (alz2.b(b) == agi$a.a) {
            return adm2.p(cj2.b()).c() == this;
        }
        \u2603 = adm2.p(cj2.a());
        return \u2603.c() == this && super.f(adm2, cj2, \u2603);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (alz2.b(b) == agi$a.a) {
            return null;
        }
        b b2 = alz2.b(a);
        if (b2 == agi$b.d) {
            return null;
        }
        if (b2 == agi$b.c) {
            if (random.nextInt(8) == 0) {
                return zy.N;
            }
            return null;
        }
        return zw.a(this);
    }

    @Override
    public int a(alz alz2) {
        if (alz2.b(b) == agi$a.a || alz2.b(a) == agi$b.c) {
            return 0;
        }
        return alz2.b(a).a();
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        b b2 = this.e(adq2, cj2);
        if (b2 == agi$b.c || b2 == agi$b.d) {
            return aea.a(adq2, cj2);
        }
        return 0xFFFFFF;
    }

    public void a(adm adm2, cj cj2, b b2, int n2) {
        adm2.a(cj2, this.Q().a(b, agi$a.b).a(a, b2), n2);
        adm2.a(cj2.a(), this.Q().a(b, agi$a.a), n2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        adm2.a(cj2.a(), this.Q().a(b, agi$a.a), 2);
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (!adm2.D && wn2.bZ() != null && wn2.bZ().b() == zy.be && alz2.b(b) == agi$a.b && this.b(adm2, cj2, alz2, wn2)) {
            return;
        }
        super.a(adm2, wn2, cj2, alz2, akw2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        if (alz2.b(b) == agi$a.a) {
            if (adm2.p(cj2.b()).c() == this) {
                if (!wn2.bA.d) {
                    alz alz3 = adm2.p(cj2.b());
                    b \u26032 = alz3.b(a);
                    if (\u26032 == agi$b.d || \u26032 == agi$b.c) {
                        if (!adm2.D) {
                            if (wn2.bZ() != null && wn2.bZ().b() == zy.be) {
                                this.b(adm2, cj2, alz3, wn2);
                                adm2.g(cj2.b());
                            } else {
                                adm2.b(cj2.b(), true);
                            }
                        } else {
                            adm2.g(cj2.b());
                        }
                    } else {
                        adm2.b(cj2.b(), true);
                    }
                } else {
                    adm2.g(cj2.b());
                }
            }
        } else if (wn2.bA.d && adm2.p(cj2.a()).c() == this) {
            adm2.a(cj2.a(), afi.a.Q(), 2);
        }
        super.a(adm2, cj2, alz2, wn2);
    }

    private boolean b(adm adm2, cj cj2, alz alz2, wn wn2) {
        b b2 = alz2.b(a);
        if (b2 == agi$b.d || b2 == agi$b.c) {
            wn2.b(na.ab[afh.a(this)]);
            int n2 = (b2 == agi$b.c ? akc.a.b : akc.a.c).a();
            agi.a(adm2, cj2, new zx(afi.H, 2, n2));
            return true;
        }
        return false;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (b b2 : agi$b.values()) {
            list.add(new zx(zw2, 1, b2.a()));
        }
    }

    @Override
    public int j(adm adm2, cj cj2) {
        return this.e(adm2, cj2).a();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        b b2 = this.e(adm2, cj2);
        return b2 != agi$b.c && b2 != agi$b.d;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return true;
    }

    @Override
    public void b(adm adm2, Random random, cj cj2, alz alz2) {
        agi.a(adm2, cj2, new zx(this, 1, this.e(adm2, cj2).a()));
    }

    @Override
    public alz a(int n2) {
        if ((n2 & 8) > 0) {
            return this.Q().a(b, agi$a.a);
        }
        return this.Q().a(b, agi$a.b).a(a, agi$b.a(n2 & 7));
    }

    @Override
    public alz a(alz alz22, adq adq2, cj cj2) {
        alz alz22;
        if (alz22.b(b) == agi$a.a && (\u2603 = adq2.p(cj2.b())).c() == this) {
            alz22 = alz22.a(a, \u2603.b(a));
        }
        return alz22;
    }

    @Override
    public int c(alz alz2) {
        if (alz2.b(b) == agi$a.a) {
            return 8 | alz2.b(N).b();
        }
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, b, a, N);
    }

    @Override
    public afh.a R() {
        return afh.a.b;
    }

    public static enum a implements nw
    {
        a,
        b;


        public String toString() {
            return this.l();
        }

        @Override
        public String l() {
            return this == a ? "upper" : "lower";
        }
    }

    public static enum b implements nw
    {
        a(0, "sunflower"),
        b(1, "syringa"),
        c(2, "double_grass", "grass"),
        d(3, "double_fern", "fern"),
        e(4, "double_rose", "rose"),
        f(5, "paeonia");

        private static final b[] g;
        private final int h;
        private final String i;
        private final String j;

        private b(int n3, String string2) {
            this(n3, string2, string2);
        }

        private b(int n3, String string2, String string3) {
            this.h = n3;
            this.i = string2;
            this.j = string3;
        }

        public int a() {
            return this.h;
        }

        public String toString() {
            return this.i;
        }

        public static b a(int n2) {
            if (n2 < 0 || n2 >= g.length) {
                n2 = 0;
            }
            return g[n2];
        }

        @Override
        public String l() {
            return this.i;
        }

        public String c() {
            return this.j;
        }

        static {
            g = new b[agi$b.values().length];
            b[] bArray = agi$b.values();
            int \u26032 = bArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                agi$b.g[\u2603.a()] = \u2603 = bArray[i2];
            }
        }
    }
}

