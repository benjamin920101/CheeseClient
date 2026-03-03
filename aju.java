/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class aju
extends afh {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amm<a> b = amm.a("half", a.class);
    public static final amm<b> N = amm.a("shape", b.class);
    private static final int[][] O = new int[][]{{4, 5}, {5, 7}, {6, 7}, {4, 6}, {0, 1}, {1, 3}, {2, 3}, {0, 2}};
    private final afh P;
    private final alz Q;
    private boolean R;
    private int S;

    protected aju(alz alz2) {
        super(alz2.c().J);
        this.j(this.M.b().a(a, cq.c).a(b, aju$a.b).a(N, aju$b.a));
        this.P = alz2.c();
        this.Q = alz2;
        this.c(this.P.w);
        this.b(this.P.x / 3.0f);
        this.a(this.P.H);
        this.e(255);
        this.a(yz.b);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        if (this.R) {
            this.a(0.5f * (float)(this.S % 2), 0.5f * (float)(this.S / 4 % 2), 0.5f * (float)(this.S / 2 % 2), 0.5f + 0.5f * (float)(this.S % 2), 0.5f + 0.5f * (float)(this.S / 4 % 2), 0.5f + 0.5f * (float)(this.S / 2 % 2));
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    public void e(adq adq2, cj cj2) {
        if (adq2.p(cj2).b(b) == aju$a.a) {
            this.a(0.0f, 0.5f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        }
    }

    public static boolean c(afh afh2) {
        return afh2 instanceof aju;
    }

    public static boolean a(adq adq2, cj cj2, alz alz2) {
        \u2603 = adq2.p(cj2);
        afh afh2 = \u2603.c();
        return aju.c(afh2) && \u2603.b(b) == alz2.b(b) && \u2603.b(a) == alz2.b(a);
    }

    public int f(adq adq22, cj cj2) {
        afh afh2;
        adq adq22;
        alz alz2 = adq22.p(cj2);
        cq \u26032 = alz2.b(a);
        a \u26033 = alz2.b(b);
        boolean bl2 = \u2603 = \u26033 == aju$a.a;
        if (\u26032 == cq.f) {
            \u2603 = adq22.p(cj2.f());
            afh afh3 = \u2603.c();
            if (aju.c(afh3) && \u26033 == \u2603.b(b)) {
                cq cq2 = \u2603.b(a);
                if (cq2 == cq.c && !aju.a(adq22, cj2.d(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
                if (cq2 == cq.d && !aju.a(adq22, cj2.c(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
            }
        } else if (\u26032 == cq.e) {
            alz \u26034 = adq22.p(cj2.e());
            afh \u26035 = \u26034.c();
            if (aju.c(\u26035) && \u26033 == \u26034.b(b)) {
                cq cq3 = \u26034.b(a);
                if (cq3 == cq.c && !aju.a(adq22, cj2.d(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
                if (cq3 == cq.d && !aju.a(adq22, cj2.c(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
            }
        } else if (\u26032 == cq.d) {
            alz \u26036 = adq22.p(cj2.d());
            afh \u26037 = \u26036.c();
            if (aju.c(\u26037) && \u26033 == \u26036.b(b)) {
                cq cq4 = \u26036.b(a);
                if (cq4 == cq.e && !aju.a(adq22, cj2.f(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
                if (cq4 == cq.f && !aju.a(adq22, cj2.e(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
            }
        } else if (\u26032 == cq.c && aju.c(afh2 = (\u2603 = adq22.p(cj2.c())).c()) && \u26033 == \u2603.b(b)) {
            cq cq5 = \u2603.b(a);
            if (cq5 == cq.e && !aju.a(adq22, cj2.f(), alz2)) {
                return \u2603 ? 1 : 2;
            }
            if (cq5 == cq.f && !aju.a(adq22, cj2.e(), alz2)) {
                return \u2603 ? 2 : 1;
            }
        }
        return 0;
    }

    public int g(adq adq22, cj cj2) {
        afh afh2;
        adq adq22;
        alz alz2 = adq22.p(cj2);
        cq \u26032 = alz2.b(a);
        a \u26033 = alz2.b(b);
        boolean bl2 = \u2603 = \u26033 == aju$a.a;
        if (\u26032 == cq.f) {
            \u2603 = adq22.p(cj2.e());
            afh afh3 = \u2603.c();
            if (aju.c(afh3) && \u26033 == \u2603.b(b)) {
                cq cq2 = \u2603.b(a);
                if (cq2 == cq.c && !aju.a(adq22, cj2.c(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
                if (cq2 == cq.d && !aju.a(adq22, cj2.d(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
            }
        } else if (\u26032 == cq.e) {
            alz \u26034 = adq22.p(cj2.f());
            afh \u26035 = \u26034.c();
            if (aju.c(\u26035) && \u26033 == \u26034.b(b)) {
                cq cq3 = \u26034.b(a);
                if (cq3 == cq.c && !aju.a(adq22, cj2.c(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
                if (cq3 == cq.d && !aju.a(adq22, cj2.d(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
            }
        } else if (\u26032 == cq.d) {
            alz \u26036 = adq22.p(cj2.c());
            afh \u26037 = \u26036.c();
            if (aju.c(\u26037) && \u26033 == \u26036.b(b)) {
                cq cq4 = \u26036.b(a);
                if (cq4 == cq.e && !aju.a(adq22, cj2.e(), alz2)) {
                    return \u2603 ? 2 : 1;
                }
                if (cq4 == cq.f && !aju.a(adq22, cj2.f(), alz2)) {
                    return \u2603 ? 1 : 2;
                }
            }
        } else if (\u26032 == cq.c && aju.c(afh2 = (\u2603 = adq22.p(cj2.d())).c()) && \u26033 == \u2603.b(b)) {
            cq cq5 = \u2603.b(a);
            if (cq5 == cq.e && !aju.a(adq22, cj2.e(), alz2)) {
                return \u2603 ? 1 : 2;
            }
            if (cq5 == cq.f && !aju.a(adq22, cj2.f(), alz2)) {
                return \u2603 ? 2 : 1;
            }
        }
        return 0;
    }

    public boolean h(adq adq22, cj cj2) {
        afh afh2;
        adq adq22;
        alz alz2 = adq22.p(cj2);
        cq \u26032 = alz2.b(a);
        a \u26033 = alz2.b(b);
        boolean \u26034 = \u26033 == aju$a.a;
        float \u26035 = 0.5f;
        float \u26036 = 1.0f;
        if (\u26034) {
            \u26035 = 0.0f;
            \u26036 = 0.5f;
        }
        float \u26037 = 0.0f;
        float \u26038 = 1.0f;
        float \u26039 = 0.0f;
        float \u260310 = 0.5f;
        boolean \u260311 = true;
        if (\u26032 == cq.f) {
            \u26037 = 0.5f;
            \u260310 = 1.0f;
            \u2603 = adq22.p(cj2.f());
            afh afh3 = \u2603.c();
            if (aju.c(afh3) && \u26033 == \u2603.b(b)) {
                cq cq2 = \u2603.b(a);
                if (cq2 == cq.c && !aju.a(adq22, cj2.d(), alz2)) {
                    \u260310 = 0.5f;
                    \u260311 = false;
                } else if (cq2 == cq.d && !aju.a(adq22, cj2.c(), alz2)) {
                    \u26039 = 0.5f;
                    \u260311 = false;
                }
            }
        } else if (\u26032 == cq.e) {
            \u26038 = 0.5f;
            \u260310 = 1.0f;
            alz \u260312 = adq22.p(cj2.e());
            afh \u260313 = \u260312.c();
            if (aju.c(\u260313) && \u26033 == \u260312.b(b)) {
                cq cq3 = \u260312.b(a);
                if (cq3 == cq.c && !aju.a(adq22, cj2.d(), alz2)) {
                    \u260310 = 0.5f;
                    \u260311 = false;
                } else if (cq3 == cq.d && !aju.a(adq22, cj2.c(), alz2)) {
                    \u26039 = 0.5f;
                    \u260311 = false;
                }
            }
        } else if (\u26032 == cq.d) {
            \u26039 = 0.5f;
            \u260310 = 1.0f;
            alz \u260314 = adq22.p(cj2.d());
            afh \u260315 = \u260314.c();
            if (aju.c(\u260315) && \u26033 == \u260314.b(b)) {
                cq cq4 = \u260314.b(a);
                if (cq4 == cq.e && !aju.a(adq22, cj2.f(), alz2)) {
                    \u26038 = 0.5f;
                    \u260311 = false;
                } else if (cq4 == cq.f && !aju.a(adq22, cj2.e(), alz2)) {
                    \u26037 = 0.5f;
                    \u260311 = false;
                }
            }
        } else if (\u26032 == cq.c && aju.c(afh2 = (\u2603 = adq22.p(cj2.c())).c()) && \u26033 == \u2603.b(b)) {
            cq cq5 = \u2603.b(a);
            if (cq5 == cq.e && !aju.a(adq22, cj2.f(), alz2)) {
                \u26038 = 0.5f;
                \u260311 = false;
            } else if (cq5 == cq.f && !aju.a(adq22, cj2.e(), alz2)) {
                \u26037 = 0.5f;
                \u260311 = false;
            }
        }
        this.a(\u26037, \u26035, \u26039, \u26038, \u26036, \u260310);
        return \u260311;
    }

    public boolean i(adq adq22, cj cj2) {
        afh afh2;
        adq adq22;
        alz alz2 = adq22.p(cj2);
        cq \u26032 = alz2.b(a);
        a \u26033 = alz2.b(b);
        boolean \u26034 = \u26033 == aju$a.a;
        float \u26035 = 0.5f;
        float \u26036 = 1.0f;
        if (\u26034) {
            \u26035 = 0.0f;
            \u26036 = 0.5f;
        }
        float \u26037 = 0.0f;
        float \u26038 = 0.5f;
        float \u26039 = 0.5f;
        float \u260310 = 1.0f;
        boolean \u260311 = false;
        if (\u26032 == cq.f) {
            \u2603 = adq22.p(cj2.e());
            afh afh3 = \u2603.c();
            if (aju.c(afh3) && \u26033 == \u2603.b(b)) {
                cq cq2 = \u2603.b(a);
                if (cq2 == cq.c && !aju.a(adq22, cj2.c(), alz2)) {
                    \u26039 = 0.0f;
                    \u260310 = 0.5f;
                    \u260311 = true;
                } else if (cq2 == cq.d && !aju.a(adq22, cj2.d(), alz2)) {
                    \u26039 = 0.5f;
                    \u260310 = 1.0f;
                    \u260311 = true;
                }
            }
        } else if (\u26032 == cq.e) {
            alz \u260312 = adq22.p(cj2.f());
            afh \u260313 = \u260312.c();
            if (aju.c(\u260313) && \u26033 == \u260312.b(b)) {
                \u26037 = 0.5f;
                \u26038 = 1.0f;
                cq cq3 = \u260312.b(a);
                if (cq3 == cq.c && !aju.a(adq22, cj2.c(), alz2)) {
                    \u26039 = 0.0f;
                    \u260310 = 0.5f;
                    \u260311 = true;
                } else if (cq3 == cq.d && !aju.a(adq22, cj2.d(), alz2)) {
                    \u26039 = 0.5f;
                    \u260310 = 1.0f;
                    \u260311 = true;
                }
            }
        } else if (\u26032 == cq.d) {
            alz \u260314 = adq22.p(cj2.c());
            afh \u260315 = \u260314.c();
            if (aju.c(\u260315) && \u26033 == \u260314.b(b)) {
                \u26039 = 0.0f;
                \u260310 = 0.5f;
                cq cq4 = \u260314.b(a);
                if (cq4 == cq.e && !aju.a(adq22, cj2.e(), alz2)) {
                    \u260311 = true;
                } else if (cq4 == cq.f && !aju.a(adq22, cj2.f(), alz2)) {
                    \u26037 = 0.5f;
                    \u26038 = 1.0f;
                    \u260311 = true;
                }
            }
        } else if (\u26032 == cq.c && aju.c(afh2 = (\u2603 = adq22.p(cj2.d())).c()) && \u26033 == \u2603.b(b)) {
            cq cq5 = \u2603.b(a);
            if (cq5 == cq.e && !aju.a(adq22, cj2.e(), alz2)) {
                \u260311 = true;
            } else if (cq5 == cq.f && !aju.a(adq22, cj2.f(), alz2)) {
                \u26037 = 0.5f;
                \u26038 = 1.0f;
                \u260311 = true;
            }
        }
        if (\u260311) {
            this.a(\u26037, \u26035, \u26039, \u26038, \u26036, \u260310);
        }
        return \u260311;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.e(adm2, cj2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        boolean bl2 = this.h(adm2, cj2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        if (bl2 && this.i(adm2, cj2)) {
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        this.P.c(adm2, cj2, alz2, random);
    }

    @Override
    public void a(adm adm2, cj cj2, wn wn2) {
        this.P.a(adm2, cj2, wn2);
    }

    @Override
    public void d(adm adm2, cj cj2, alz alz2) {
        this.P.d(adm2, cj2, alz2);
    }

    @Override
    public int c(adq adq2, cj cj2) {
        return this.P.c(adq2, cj2);
    }

    @Override
    public float a(pk pk2) {
        return this.P.a(pk2);
    }

    @Override
    public adf m() {
        return this.P.m();
    }

    @Override
    public int a(adm adm2) {
        return this.P.a(adm2);
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        return this.P.b(adm2, cj2);
    }

    @Override
    public aui a(adm adm2, cj cj2, pk pk2, aui aui2) {
        return this.P.a(adm2, cj2, pk2, aui2);
    }

    @Override
    public boolean A() {
        return this.P.A();
    }

    @Override
    public boolean a(alz alz2, boolean bl2) {
        return this.P.a(alz2, bl2);
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return this.P.d(adm2, cj2);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        this.a(adm2, cj2, this.Q, afi.a);
        this.P.c(adm2, cj2, this.Q);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        this.P.b(adm2, cj2, this.Q);
    }

    @Override
    public void a(adm adm2, cj cj2, pk pk2) {
        this.P.a(adm2, cj2, pk2);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        this.P.b(adm2, cj2, alz2, random);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        return this.P.a(adm2, cj2, this.Q, wn2, cq.a, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void a(adm adm2, cj cj2, adi adi2) {
        this.P.a(adm2, cj2, adi2);
    }

    @Override
    public arn g(alz alz2) {
        return this.P.g(this.Q);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        alz alz2 = super.a(adm2, cj2, cq2, f2, f3, f4, n2, pr2);
        alz2 = alz2.a(a, pr2.aP()).a(N, aju$b.a);
        if (cq2 == cq.a || cq2 != cq.b && (double)f3 > 0.5) {
            return alz2.a(b, aju$a.a);
        }
        return alz2.a(b, aju$a.b);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        void var10_14;
        void var10_11;
        auh[] auhArray = new auh[8];
        alz \u26032 = adm2.p(cj2);
        int \u26033 = \u26032.b(a).b();
        boolean \u26034 = \u26032.b(b) == aju$a.a;
        int[] \u26035 = O[\u26033 + (\u26034 ? 4 : 0)];
        this.R = true;
        boolean bl2 = false;
        while (var10_11 < 8) {
            this.S = var10_11;
            if (Arrays.binarySearch(\u26035, (int)var10_11) < 0) {
                auhArray[var10_11] = super.a(adm2, cj2, aui2, aui3);
            }
            ++var10_11;
        }
        for (int n2 : \u26035) {
            auhArray[n2] = null;
        }
        Object var10_13 = null;
        double \u26036 = 0.0;
        for (auh auh2 : auhArray) {
            double d2;
            if (auh2 == null || !((d2 = auh2.c.g(aui3)) > \u26036)) continue;
            auh auh3 = auh2;
            \u26036 = d2;
        }
        return var10_14;
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q().a(b, (n2 & 4) > 0 ? aju$a.a : aju$a.b);
        alz2 = alz2.a(a, cq.a(5 - (n2 & 3)));
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        if (alz2.b(b) == aju$a.a) {
            n2 |= 4;
        }
        return n2 |= 5 - alz2.b(a).a();
    }

    @Override
    public alz a(alz alz22, adq adq22, cj cj2) {
        alz alz22;
        if (this.h(adq22, cj2)) {
            switch (this.g(adq22, cj2)) {
                case 0: {
                    alz22 = alz22.a(N, aju$b.a);
                    break;
                }
                case 1: {
                    alz22 = alz22.a(N, aju$b.c);
                    break;
                }
                case 2: {
                    alz22 = alz22.a(N, aju$b.b);
                }
            }
        } else {
            adq adq22;
            switch (this.f(adq22, cj2)) {
                case 0: {
                    alz22 = alz22.a(N, aju$b.a);
                    break;
                }
                case 1: {
                    alz22 = alz22.a(N, aju$b.e);
                    break;
                }
                case 2: {
                    alz22 = alz22.a(N, aju$b.d);
                }
            }
        }
        return alz22;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N);
    }

    public static enum b implements nw
    {
        a("straight"),
        b("inner_left"),
        c("inner_right"),
        d("outer_left"),
        e("outer_right");

        private final String f;

        private b(String string2) {
            this.f = string2;
        }

        public String toString() {
            return this.f;
        }

        @Override
        public String l() {
            return this.f;
        }
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

