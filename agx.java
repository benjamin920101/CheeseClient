/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agx
extends afc {
    public static final amn a = amn.a("legacy_data", 0, 15);
    public static final amm<a> b = amm.a("contents", a.class);

    public agx() {
        super(arm.q);
        this.j(this.M.b().a(b, agx$a.a).a(a, 0));
        this.j();
    }

    @Override
    public String f() {
        return di.a("item.flowerPot.name");
    }

    @Override
    public void j() {
        float f2 = 0.375f;
        \u2603 = f2 / 2.0f;
        this.a(0.5f - \u2603, 0.0f, 0.5f - \u2603, 0.5f + \u2603, f2, 0.5f + \u2603);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        akw akw2 = adq2.s(cj2);
        if (akw2 instanceof alg && (\u2603 = ((alg)akw2).b()) instanceof yo) {
            return afh.a(\u2603).a(adq2, cj2, n2);
        }
        return 0xFFFFFF;
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        zx zx2 = wn2.bi.h();
        if (zx2 == null || !(zx2.b() instanceof yo)) {
            return false;
        }
        alg \u26032 = this.f(adm2, cj2);
        if (\u26032 == null) {
            return false;
        }
        if (\u26032.b() != null) {
            return false;
        }
        afh \u26033 = afh.a(zx2.b());
        if (!this.a(\u26033, zx2.i())) {
            return false;
        }
        \u26032.a(zx2.b(), zx2.i());
        \u26032.p_();
        adm2.h(cj2);
        wn2.b(na.T);
        if (!wn2.bA.d && --zx2.b <= 0) {
            wn2.bi.a(wn2.bi.c, null);
        }
        return true;
    }

    private boolean a(afh afh2, int n2) {
        if (afh2 == afi.N || afh2 == afi.O || afh2 == afi.aK || afh2 == afi.P || afh2 == afi.Q || afh2 == afi.g || afh2 == afi.I) {
            return true;
        }
        return afh2 == afi.H && n2 == akc.a.c.a();
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        alg alg2 = this.f(adm2, cj2);
        if (alg2 != null && alg2.b() != null) {
            return alg2.b();
        }
        return zy.bQ;
    }

    @Override
    public int j(adm adm2, cj cj2) {
        alg alg2 = this.f(adm2, cj2);
        if (alg2 != null && alg2.b() != null) {
            return alg2.c();
        }
        return 0;
    }

    @Override
    public boolean M() {
        return true;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return super.d(adm2, cj2) && adm.a(adm2, cj2.b());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm.a(adm2, cj2.b())) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        alg alg2 = this.f(adm2, cj2);
        if (alg2 != null && alg2.b() != null) {
            agx.a(adm2, cj2, new zx(alg2.b(), 1, alg2.c()));
        }
        super.b(adm2, cj2, alz2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        super.a(adm2, cj2, alz2, wn2);
        if (wn2.bA.d && (\u2603 = this.f(adm2, cj2)) != null) {
            \u2603.a(null, 0);
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bQ;
    }

    private alg f(adm adm2, cj cj2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof alg) {
            return (alg)akw2;
        }
        return null;
    }

    @Override
    public akw a(adm adm2, int n2) {
        afh afh2 = null;
        int \u26032 = 0;
        switch (n2) {
            default: {
                break;
            }
            case 1: {
                afh2 = afi.O;
                \u26032 = agw.a.b.b();
                break;
            }
            case 2: {
                afh2 = afi.N;
                break;
            }
            case 3: {
                afh2 = afi.g;
                \u26032 = aio.a.a.a();
                break;
            }
            case 4: {
                afh2 = afi.g;
                \u26032 = aio.a.b.a();
                break;
            }
            case 5: {
                afh2 = afi.g;
                \u26032 = aio.a.c.a();
                break;
            }
            case 6: {
                afh2 = afi.g;
                \u26032 = aio.a.d.a();
                break;
            }
            case 12: {
                afh2 = afi.g;
                \u26032 = aio.a.e.a();
                break;
            }
            case 13: {
                afh2 = afi.g;
                \u26032 = aio.a.f.a();
                break;
            }
            case 7: {
                afh2 = afi.Q;
                break;
            }
            case 8: {
                afh2 = afi.P;
                break;
            }
            case 9: {
                afh2 = afi.aK;
                break;
            }
            case 10: {
                afh2 = afi.I;
                break;
            }
            case 11: {
                afh2 = afi.H;
                \u26032 = akc.a.c.a();
            }
        }
        return new alg(zw.a(afh2), \u26032);
    }

    @Override
    protected ama e() {
        return new ama(this, b, a);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    public alz a(alz alz22, adq adq2, cj cj2) {
        alz alz22;
        a a2 = agx$a.a;
        akw \u26032 = adq2.s(cj2);
        if (\u26032 instanceof alg && (\u2603 = (\u2603 = (alg)\u26032).b()) instanceof yo) {
            int n2 = \u2603.c();
            afh \u26033 = afh.a(\u2603);
            if (\u26033 == afi.g) {
                switch (aio.a.a(n2)) {
                    case a: {
                        a2 = agx$a.l;
                        break;
                    }
                    case b: {
                        a2 = agx$a.m;
                        break;
                    }
                    case c: {
                        a2 = agx$a.n;
                        break;
                    }
                    case d: {
                        a2 = agx$a.o;
                        break;
                    }
                    case e: {
                        a2 = agx$a.p;
                        break;
                    }
                    case f: {
                        a2 = agx$a.q;
                        break;
                    }
                    default: {
                        a2 = agx$a.a;
                        break;
                    }
                }
            } else if (\u26033 == afi.H) {
                switch (n2) {
                    case 0: {
                        a2 = agx$a.t;
                        break;
                    }
                    case 2: {
                        a2 = agx$a.u;
                        break;
                    }
                    default: {
                        a2 = agx$a.a;
                        break;
                    }
                }
            } else if (\u26033 == afi.N) {
                a2 = agx$a.k;
            } else if (\u26033 == afi.O) {
                switch (agw.a.a(agw.b.b, n2)) {
                    case b: {
                        a2 = agx$a.b;
                        break;
                    }
                    case c: {
                        a2 = agx$a.c;
                        break;
                    }
                    case d: {
                        a2 = agx$a.d;
                        break;
                    }
                    case e: {
                        a2 = agx$a.e;
                        break;
                    }
                    case f: {
                        a2 = agx$a.f;
                        break;
                    }
                    case g: {
                        a2 = agx$a.g;
                        break;
                    }
                    case h: {
                        a2 = agx$a.h;
                        break;
                    }
                    case i: {
                        a2 = agx$a.i;
                        break;
                    }
                    case j: {
                        a2 = agx$a.j;
                        break;
                    }
                    default: {
                        a2 = agx$a.a;
                        break;
                    }
                }
            } else if (\u26033 == afi.Q) {
                a2 = agx$a.r;
            } else if (\u26033 == afi.P) {
                a2 = agx$a.s;
            } else if (\u26033 == afi.I) {
                a2 = agx$a.t;
            } else if (\u26033 == afi.aK) {
                a2 = agx$a.v;
            }
        }
        return alz22.a(b, a2);
    }

    @Override
    public adf m() {
        return adf.c;
    }

    public static enum a implements nw
    {
        a("empty"),
        b("rose"),
        c("blue_orchid"),
        d("allium"),
        e("houstonia"),
        f("red_tulip"),
        g("orange_tulip"),
        h("white_tulip"),
        i("pink_tulip"),
        j("oxeye_daisy"),
        k("dandelion"),
        l("oak_sapling"),
        m("spruce_sapling"),
        n("birch_sapling"),
        o("jungle_sapling"),
        p("acacia_sapling"),
        q("dark_oak_sapling"),
        r("mushroom_red"),
        s("mushroom_brown"),
        t("dead_bush"),
        u("fern"),
        v("cactus");

        private final String w;

        private a(String string2) {
            this.w = string2;
        }

        public String toString() {
            return this.w;
        }

        @Override
        public String l() {
            return this.w;
        }
    }
}

