/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public abstract class apg
extends aoh {
    protected final int a;
    protected final alz b;
    protected final alz c;
    protected int d;

    public apg(boolean bl2, int n2, int n3, alz alz2, alz alz3) {
        super(bl2);
        this.a = n2;
        this.d = n3;
        this.b = alz2;
        this.c = alz3;
    }

    protected int a(Random random) {
        int n2 = random.nextInt(3) + this.a;
        if (this.d > 1) {
            n2 += random.nextInt(this.d);
        }
        return n2;
    }

    private boolean c(adm adm2, cj cj2, int n2) {
        boolean bl2 = true;
        if (cj2.o() < 1 || cj2.o() + n2 + 1 > 256) {
            return false;
        }
        for (int i2 = 0; i2 <= 1 + n2; ++i2) {
            \u2603 = 2;
            if (i2 == 0) {
                \u2603 = 1;
            } else if (i2 >= 1 + n2 - 2) {
                \u2603 = 2;
            }
            for (\u2603 = -\u2603; \u2603 <= \u2603 && bl2; ++\u2603) {
                for (\u2603 = -\u2603; \u2603 <= \u2603 && bl2; ++\u2603) {
                    if (cj2.o() + i2 >= 0 && cj2.o() + i2 < 256 && this.a(adm2.p(cj2.a(\u2603, i2, \u2603)).c())) continue;
                    bl2 = false;
                }
            }
        }
        return bl2;
    }

    private boolean a(cj cj2, adm adm2) {
        cj cj3 = cj2.b();
        afh \u26032 = adm2.p(cj3).c();
        if (\u26032 != afi.c && \u26032 != afi.d || cj2.o() < 2) {
            return false;
        }
        this.a(adm2, cj3);
        this.a(adm2, cj3.f());
        this.a(adm2, cj3.d());
        this.a(adm2, cj3.d().f());
        return true;
    }

    protected boolean a(adm adm2, Random random, cj cj2, int n2) {
        return this.c(adm2, cj2, n2) && this.a(cj2, adm2);
    }

    protected void a(adm adm2, cj cj2, int n2) {
        \u2603 = n2 * n2;
        for (\u2603 = -n2; \u2603 <= n2 + 1; ++\u2603) {
            for (\u2603 = -n2; \u2603 <= n2 + 1; ++\u2603) {
                \u2603 = \u2603 - 1;
                \u2603 = \u2603 - 1;
                if (\u2603 * \u2603 + \u2603 * \u2603 > \u2603 && \u2603 * \u2603 + \u2603 * \u2603 > \u2603 && \u2603 * \u2603 + \u2603 * \u2603 > \u2603 && \u2603 * \u2603 + \u2603 * \u2603 > \u2603 || (\u2603 = adm2.p(\u2603 = cj2.a(\u2603, 0, \u2603)).c().t()) != arm.a && \u2603 != arm.j) continue;
                this.a(adm2, \u2603, this.c);
            }
        }
    }

    protected void b(adm adm2, cj cj2, int n2) {
        \u2603 = n2 * n2;
        for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
            for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                if (\u2603 * \u2603 + \u2603 * \u2603 > \u2603 || (\u2603 = adm2.p(\u2603 = cj2.a(\u2603, 0, \u2603)).c().t()) != arm.a && \u2603 != arm.j) continue;
                this.a(adm2, \u2603, this.c);
            }
        }
    }
}

