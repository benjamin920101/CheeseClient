/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.Random;

public class aek
extends ady {
    private alz[] aD;
    private long aE;
    private ard aF;
    private ard aG;
    private ard aH;
    private boolean aI;
    private boolean aJ;

    public aek(int n2, boolean bl2, boolean bl3) {
        super(n2);
        this.aI = bl2;
        this.aJ = bl3;
        this.b();
        this.a(2.0f, 0.0f);
        this.au.clear();
        this.ak = afi.m.Q().a(ajh.a, ajh.a.b);
        this.al = afi.cu.Q();
        this.as.A = -999;
        this.as.D = 20;
        this.as.F = 3;
        this.as.G = 5;
        this.as.B = 0;
        this.au.clear();
        if (bl3) {
            this.as.A = 5;
        }
    }

    @Override
    public aoh a(Random random) {
        return this.aA;
    }

    @Override
    public int c(cj cj2) {
        return 10387789;
    }

    @Override
    public int b(cj cj2) {
        return 9470285;
    }

    @Override
    public void a(adm adm2, Random random, cj cj2) {
        super.a(adm2, random, cj2);
    }

    @Override
    public void a(adm adm22, Random random, ans ans22, int n22, int n3, double d2) {
        int n22;
        adm adm22;
        if (this.aD == null || this.aE != adm22.J()) {
            this.a(adm22.J());
        }
        if (this.aF == null || this.aG == null || this.aE != adm22.J()) {
            Random random2 = new Random(this.aE);
            this.aF = new ard(random2, 4);
            this.aG = new ard(random2, 1);
        }
        this.aE = adm22.J();
        double \u26032 = 0.0;
        if (this.aI) {
            int n4 = (n22 & 0xFFFFFFF0) + (n3 & 0xF);
            \u2603 = (n3 & 0xFFFFFFF0) + (n22 & 0xF);
            double \u26033 = Math.min(Math.abs(d2), this.aF.a((double)n4 * 0.25, (double)\u2603 * 0.25));
            if (\u26033 > 0.0) {
                \u26032 = \u26033 * \u26033 * 2.5;
                double d3 = 0.001953125;
                \u2603 = Math.abs(this.aG.a((double)n4 * d3, (double)\u2603 * d3));
                \u2603 = Math.ceil(\u2603 * 50.0) + 14.0;
                if (\u26032 > \u2603) {
                    \u26032 = \u2603;
                }
                \u26032 += 64.0;
            }
        }
        n4 = n22 & 0xF;
        \u2603 = n3 & 0xF;
        \u2603 = adm22.F();
        alz \u26034 = afi.cu.Q();
        alz \u26035 = this.al;
        \u2603 = (int)(d2 / 3.0 + 3.0 + random.nextDouble() * 0.25);
        boolean \u26036 = Math.cos(d2 / 3.0 * Math.PI) > 0.0;
        n5 = -1;
        boolean \u26037 = false;
        for (\u2603 = 255; \u2603 >= 0; --\u2603) {
            alz \u26038;
            int n5;
            if (ans22.a(\u2603, \u2603, n4).c().t() == arm.a && \u2603 < (int)\u26032) {
                ans22.a(\u2603, \u2603, n4, afi.b.Q());
            }
            if (\u2603 <= random.nextInt(5)) {
                ans22.a(\u2603, \u2603, n4, afi.h.Q());
                continue;
            }
            alz alz2 = ans22.a(\u2603, \u2603, n4);
            if (alz2.c().t() == arm.a) {
                n5 = -1;
                continue;
            }
            if (alz2.c() != afi.b) continue;
            if (n5 == -1) {
                ans ans22;
                \u26037 = false;
                if (\u2603 <= 0) {
                    \u26034 = null;
                    \u26035 = afi.b.Q();
                } else if (\u2603 >= \u2603 - 4 && \u2603 <= \u2603 + 1) {
                    \u26034 = afi.cu.Q();
                    \u26035 = this.al;
                }
                if (\u2603 < \u2603 && (\u26034 == null || \u26034.c().t() == arm.a)) {
                    \u26034 = afi.j.Q();
                }
                n5 = \u2603 + Math.max(0, \u2603 - \u2603);
                if (\u2603 >= \u2603 - 1) {
                    if (this.aJ && \u2603 > 86 + \u2603 * 2) {
                        if (\u26036) {
                            ans22.a(\u2603, \u2603, n4, afi.d.Q().a(agf.a, agf.a.b));
                            continue;
                        }
                        ans22.a(\u2603, \u2603, n4, afi.c.Q());
                        continue;
                    }
                    if (\u2603 > \u2603 + 3 + \u2603) {
                        \u26038 = \u2603 < 64 || \u2603 > 127 ? afi.cu.Q().a(afv.a, zd.b) : (\u26036 ? afi.cz.Q() : this.a(n22, \u2603, n3));
                        ans22.a(\u2603, \u2603, n4, \u26038);
                        continue;
                    }
                    ans22.a(\u2603, \u2603, n4, this.ak);
                    \u26037 = true;
                    continue;
                }
                ans22.a(\u2603, \u2603, n4, \u26035);
                if (\u26035.c() != afi.cu) continue;
                ans22.a(\u2603, \u2603, n4, \u26035.c().Q().a(afv.a, zd.b));
                continue;
            }
            if (n5 <= 0) continue;
            --n5;
            if (\u26037) {
                ans22.a(\u2603, \u2603, n4, afi.cu.Q().a(afv.a, zd.b));
                continue;
            }
            \u26038 = this.a(n22, \u2603, n3);
            ans22.a(\u2603, \u2603, n4, \u26038);
        }
    }

    private void a(long l2) {
        int \u26035;
        int \u26034;
        int \u26033;
        int \u26032;
        this.aD = new alz[64];
        Arrays.fill(this.aD, afi.cz.Q());
        Random random = new Random(l2);
        this.aH = new ard(random, 1);
        for (\u26032 = 0; \u26032 < 64; ++\u26032) {
            if ((\u26032 += random.nextInt(5) + 1) >= 64) continue;
            this.aD[\u26032] = afi.cu.Q().a(afv.a, zd.b);
        }
        \u26032 = random.nextInt(4) + 2;
        for (\u26033 = 0; \u26033 < \u26032; ++\u26033) {
            \u26034 = random.nextInt(3) + 1;
            \u26035 = random.nextInt(64);
            for (\u26036 = 0; \u26035 + \u26036 < 64 && \u26036 < \u26034; ++\u26036) {
                this.aD[\u26035 + \u26036] = afi.cu.Q().a(afv.a, zd.e);
            }
        }
        \u26033 = random.nextInt(4) + 2;
        for (\u26034 = 0; \u26034 < \u26033; ++\u26034) {
            \u26035 = random.nextInt(3) + 2;
            \u26036 = random.nextInt(64);
            for (i2 = 0; \u26036 + i2 < 64 && i2 < \u26035; ++i2) {
                this.aD[\u26036 + i2] = afi.cu.Q().a(afv.a, zd.m);
            }
        }
        \u26034 = random.nextInt(4) + 2;
        for (\u26035 = 0; \u26035 < \u26034; ++\u26035) {
            \u26036 = random.nextInt(3) + 1;
            i2 = random.nextInt(64);
            for (\u2603 = 0; i2 + \u2603 < 64 && \u2603 < \u26036; ++\u2603) {
                this.aD[i2 + \u2603] = afi.cu.Q().a(afv.a, zd.o);
            }
        }
        \u26035 = random.nextInt(3) + 3;
        int \u26036 = 0;
        for (int i2 = 0; i2 < \u26035; ++i2) {
            \u2603 = 1;
            \u26036 += random.nextInt(16) + 4;
            for (\u2603 = 0; \u26036 + \u2603 < 64 && \u2603 < \u2603; ++\u2603) {
                this.aD[\u26036 + \u2603] = afi.cu.Q().a(afv.a, zd.a);
                if (\u26036 + \u2603 > 1 && random.nextBoolean()) {
                    this.aD[\u26036 + \u2603 - 1] = afi.cu.Q().a(afv.a, zd.i);
                }
                if (\u26036 + \u2603 >= 63 || !random.nextBoolean()) continue;
                this.aD[\u26036 + \u2603 + 1] = afi.cu.Q().a(afv.a, zd.i);
            }
        }
    }

    private alz a(int n2, int n3, int n4) {
        \u2603 = (int)Math.round(this.aH.a((double)n2 * 1.0 / 512.0, (double)n2 * 1.0 / 512.0) * 2.0);
        return this.aD[(n3 + \u2603 + 64) % 64];
    }

    @Override
    protected ady d(int n2) {
        boolean bl2 = this.az == ady.aa.az;
        aek \u26032 = new aek(n2, bl2, this.aJ);
        if (!bl2) {
            \u26032.a(g);
            \u26032.a(this.ah + " M");
        } else {
            \u26032.a(this.ah + " (Bryce)");
        }
        \u26032.a(this.ai, true);
        return \u26032;
    }
}

