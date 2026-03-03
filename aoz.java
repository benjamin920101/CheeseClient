/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aoz
extends aot {
    private afh a;

    public aoz(afh afh2) {
        super(true);
        this.a = afh2;
    }

    public aoz() {
        super(false);
    }

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        int n2;
        adm adm22;
        if (this.a == null) {
            this.a = random.nextBoolean() ? afi.bg : afi.bh;
        }
        int n3 = random.nextInt(3) + 4;
        boolean \u26032 = true;
        if (cj2.o() < 1 || cj2.o() + n3 + 1 >= 256) {
            return false;
        }
        for (\u2603 = cj2.o(); \u2603 <= cj2.o() + 1 + n3; ++\u2603) {
            \u26034 = 3;
            if (\u2603 <= cj2.o() + 3) {
                \u26034 = 0;
            }
            cj.a a2 = new cj.a();
            for (int i2 = cj2.n() - \u26034; i2 <= cj2.n() + \u26034 && \u26032; ++i2) {
                for (\u2603 = cj2.p() - \u26034; \u2603 <= cj2.p() + \u26034 && \u26032; ++\u2603) {
                    if (\u2603 >= 0 && \u2603 < 256) {
                        afh afh2 = adm22.p(a2.c(i2, \u2603, \u2603)).c();
                        if (afh2.t() == arm.a || afh2.t() == arm.j) continue;
                        \u26032 = false;
                        continue;
                    }
                    \u26032 = false;
                }
            }
        }
        if (!\u26032) {
            return false;
        }
        afh \u26033 = adm22.p(cj2.b()).c();
        if (\u26033 != afi.d && \u26033 != afi.c && \u26033 != afi.bw) {
            return false;
        }
        int \u26034 = cj2.o() + n3;
        if (this.a == afi.bh) {
            \u26034 = cj2.o() + n3 - 3;
        }
        for (n2 = \u26034; n2 <= cj2.o() + n3; ++n2) {
            i2 = 1;
            if (n2 < cj2.o() + n3) {
                ++i2;
            }
            if (this.a == afi.bg) {
                i2 = 3;
            }
            \u2603 = cj2.n() - i2;
            \u2603 = cj2.n() + i2;
            \u2603 = cj2.p() - i2;
            \u2603 = cj2.p() + i2;
            for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    \u2603 = 5;
                    if (\u2603 == \u2603) {
                        --\u2603;
                    } else if (\u2603 == \u2603) {
                        ++\u2603;
                    }
                    if (\u2603 == \u2603) {
                        \u2603 -= 3;
                    } else if (\u2603 == \u2603) {
                        \u2603 += 3;
                    }
                    aho.a a3 = aho.a.a(\u2603);
                    if (this.a == afi.bg || n2 < cj2.o() + n3) {
                        if ((\u2603 == \u2603 || \u2603 == \u2603) && (\u2603 == \u2603 || \u2603 == \u2603)) continue;
                        if (\u2603 == cj2.n() - (i2 - 1) && \u2603 == \u2603) {
                            a3 = aho.a.a;
                        }
                        if (\u2603 == \u2603 && \u2603 == cj2.p() - (i2 - 1)) {
                            a3 = aho.a.a;
                        }
                        if (\u2603 == cj2.n() + (i2 - 1) && \u2603 == \u2603) {
                            a3 = aho.a.c;
                        }
                        if (\u2603 == \u2603 && \u2603 == cj2.p() - (i2 - 1)) {
                            a3 = aho.a.c;
                        }
                        if (\u2603 == cj2.n() - (i2 - 1) && \u2603 == \u2603) {
                            a3 = aho.a.g;
                        }
                        if (\u2603 == \u2603 && \u2603 == cj2.p() + (i2 - 1)) {
                            a3 = aho.a.g;
                        }
                        if (\u2603 == cj2.n() + (i2 - 1) && \u2603 == \u2603) {
                            a3 = aho.a.i;
                        }
                        if (\u2603 == \u2603 && \u2603 == cj2.p() + (i2 - 1)) {
                            a3 = aho.a.i;
                        }
                    }
                    if (a3 == aho.a.e && n2 < cj2.o() + n3) {
                        a3 = aho.a.k;
                    }
                    if (cj2.o() < cj2.o() + n3 - 1 && a3 == aho.a.k || adm22.p(\u2603 = new cj(\u2603, n2, \u2603)).c().o()) continue;
                    this.a(adm22, \u2603, this.a.Q().a(aho.a, a3));
                }
            }
        }
        for (n2 = 0; n2 < n3; ++n2) {
            afh afh3 = adm22.p(cj2.b(n2)).c();
            if (afh3.o()) continue;
            this.a(adm22, cj2.b(n2), this.a.Q().a(aho.a, aho.a.j));
        }
        return true;
    }
}

