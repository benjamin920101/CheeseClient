/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class apc
extends aot {
    private afh a;

    public apc(afh afh2) {
        this.a = afh2;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        Object object;
        int n2;
        cj2 = cj2.a(-8, 0, -8);
        while (cj2.o() > 5 && adm2.d(cj2)) {
            cj2 = cj2.b();
        }
        if (cj2.o() <= 4) {
            return false;
        }
        cj2 = cj2.c(4);
        boolean[] blArray = new boolean[2048];
        int \u26032 = random.nextInt(4) + 4;
        for (n2 = 0; n2 < \u26032; ++n2) {
            double d2 = random.nextDouble() * 6.0 + 3.0;
            \u2603 = random.nextDouble() * 4.0 + 2.0;
            \u2603 = random.nextDouble() * 6.0 + 3.0;
            \u2603 = random.nextDouble() * (16.0 - d2 - 2.0) + 1.0 + d2 / 2.0;
            \u2603 = random.nextDouble() * (8.0 - \u2603 - 4.0) + 2.0 + \u2603 / 2.0;
            \u2603 = random.nextDouble() * (16.0 - \u2603 - 2.0) + 1.0 + \u2603 / 2.0;
            for (int i2 = 1; i2 < 15; ++i2) {
                for (\u2603 = 1; \u2603 < 15; ++\u2603) {
                    for (\u2603 = 1; \u2603 < 7; ++\u2603) {
                        double d3 = ((double)i2 - \u2603) / (d2 / 2.0);
                        \u2603 = ((double)\u2603 - \u2603) / (\u2603 / 2.0);
                        \u2603 = ((double)\u2603 - \u2603) / (\u2603 / 2.0);
                        \u2603 = d3 * d3 + \u2603 * \u2603 + \u2603 * \u2603;
                        if (!(\u2603 < 1.0)) continue;
                        blArray[(i2 * 16 + \u2603) * 8 + \u2603] = true;
                    }
                }
            }
        }
        for (n2 = 0; n2 < 16; ++n2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 0; \u2603 < 8; ++\u2603) {
                    boolean bl2 = \u2603 = !blArray[(n2 * 16 + \u2603) * 8 + \u2603] && (n2 < 15 && blArray[((n2 + 1) * 16 + \u2603) * 8 + \u2603] || n2 > 0 && blArray[((n2 - 1) * 16 + \u2603) * 8 + \u2603] || \u2603 < 15 && blArray[(n2 * 16 + (\u2603 + 1)) * 8 + \u2603] || \u2603 > 0 && blArray[(n2 * 16 + (\u2603 - 1)) * 8 + \u2603] || \u2603 < 7 && blArray[(n2 * 16 + \u2603) * 8 + (\u2603 + 1)] || \u2603 > 0 && blArray[(n2 * 16 + \u2603) * 8 + (\u2603 - 1)]);
                    if (!\u2603) continue;
                    object = adm2.p(cj2.a(n2, \u2603, \u2603)).c().t();
                    if (\u2603 >= 4 && ((arm)object).d()) {
                        return false;
                    }
                    if (\u2603 >= 4 || ((arm)object).a() || adm2.p(cj2.a(n2, \u2603, \u2603)).c() == this.a) continue;
                    return false;
                }
            }
        }
        for (n2 = 0; n2 < 16; ++n2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 0; \u2603 < 8; ++\u2603) {
                    if (!blArray[(n2 * 16 + \u2603) * 8 + \u2603]) continue;
                    adm2.a(cj2.a(n2, \u2603, \u2603), \u2603 >= 4 ? afi.a.Q() : this.a.Q(), 2);
                }
            }
        }
        for (n2 = 0; n2 < 16; ++n2) {
            for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                for (\u2603 = 4; \u2603 < 8; ++\u2603) {
                    if (!blArray[(n2 * 16 + \u2603) * 8 + \u2603] || adm2.p(\u2603 = cj2.a(n2, \u2603 - 1, \u2603)).c() != afi.d || adm2.b(ads.a, cj2.a(n2, \u2603, \u2603)) <= 0) continue;
                    object = adm2.b(\u2603);
                    if (((ady)object).ak.c() == afi.bw) {
                        adm2.a(\u2603, afi.bw.Q(), 2);
                        continue;
                    }
                    adm2.a(\u2603, afi.c.Q(), 2);
                }
            }
        }
        if (this.a.t() == arm.i) {
            for (n2 = 0; n2 < 16; ++n2) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    for (\u2603 = 0; \u2603 < 8; ++\u2603) {
                        boolean bl3 = \u2603 = !blArray[(n2 * 16 + \u2603) * 8 + \u2603] && (n2 < 15 && blArray[((n2 + 1) * 16 + \u2603) * 8 + \u2603] || n2 > 0 && blArray[((n2 - 1) * 16 + \u2603) * 8 + \u2603] || \u2603 < 15 && blArray[(n2 * 16 + (\u2603 + 1)) * 8 + \u2603] || \u2603 > 0 && blArray[(n2 * 16 + (\u2603 - 1)) * 8 + \u2603] || \u2603 < 7 && blArray[(n2 * 16 + \u2603) * 8 + (\u2603 + 1)] || \u2603 > 0 && blArray[(n2 * 16 + \u2603) * 8 + (\u2603 - 1)]);
                        if (!\u2603 || \u2603 >= 4 && random.nextInt(2) == 0 || !adm2.p(cj2.a(n2, \u2603, \u2603)).c().t().a()) continue;
                        adm2.a(cj2.a(n2, \u2603, \u2603), afi.b.Q(), 2);
                    }
                }
            }
        }
        if (this.a.t() == arm.h) {
            for (n2 = 0; n2 < 16; ++n2) {
                for (\u2603 = 0; \u2603 < 16; ++\u2603) {
                    \u2603 = 4;
                    if (!adm2.v(cj2.a(n2, \u2603, \u2603))) continue;
                    adm2.a(cj2.a(n2, \u2603, \u2603), afi.aI.Q(), 2);
                }
            }
        }
        return true;
    }
}

