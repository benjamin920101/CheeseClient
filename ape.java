/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ape
extends apg {
    public ape(boolean bl2, int n2, int n3, alz alz2, alz alz3) {
        super(bl2, n2, n3, alz2, alz3);
    }

    @Override
    public boolean b(adm adm2, Random random2, cj cj2) {
        int n2 = this.a(random2);
        if (!this.a(adm2, random2, cj2, n2)) {
            return false;
        }
        this.c(adm2, cj2.b(n2), 2);
        for (\u2603 = cj2.o() + n2 - 2 - random2.nextInt(4); \u2603 > cj2.o() + n2 / 2; \u2603 -= 2 + random2.nextInt(4)) {
            Random random2;
            int \u26034;
            float f2 = random2.nextFloat() * (float)Math.PI * 2.0f;
            int \u26032 = cj2.n() + (int)(0.5f + ns.b(f2) * 4.0f);
            int \u26033 = cj2.p() + (int)(0.5f + ns.a(f2) * 4.0f);
            for (\u26034 = 0; \u26034 < 5; ++\u26034) {
                \u26032 = cj2.n() + (int)(1.5f + ns.b(f2) * (float)\u26034);
                \u26033 = cj2.p() + (int)(1.5f + ns.a(f2) * (float)\u26034);
                this.a(adm2, new cj(\u26032, \u2603 - 3 + \u26034 / 2, \u26033), this.b);
            }
            \u26034 = 1 + random2.nextInt(2);
            int \u26035 = \u2603;
            for (int i2 = \u26035 - \u26034; i2 <= \u26035; ++i2) {
                \u2603 = i2 - \u26035;
                this.b(adm2, new cj(\u26032, i2, \u26033), 1 - \u2603);
            }
        }
        for (int i3 = 0; i3 < n2; ++i3) {
            cj cj3 = cj2.b(i3);
            if (this.a(adm2.p(cj3).c())) {
                this.a(adm2, cj3, this.b);
                if (i3 > 0) {
                    this.a(adm2, random2, cj3.e(), akk.N);
                    this.a(adm2, random2, cj3.c(), akk.O);
                }
            }
            if (i3 >= n2 - 1) continue;
            \u2603 = cj3.f();
            if (this.a(adm2.p(\u2603).c())) {
                this.a(adm2, \u2603, this.b);
                if (i3 > 0) {
                    this.a(adm2, random2, \u2603.f(), akk.P);
                    this.a(adm2, random2, \u2603.c(), akk.O);
                }
            }
            if (this.a(adm2.p(\u2603 = cj3.d().f()).c())) {
                this.a(adm2, \u2603, this.b);
                if (i3 > 0) {
                    this.a(adm2, random2, \u2603.f(), akk.P);
                    this.a(adm2, random2, \u2603.d(), akk.b);
                }
            }
            if (!this.a(adm2.p(\u2603 = cj3.d()).c())) continue;
            this.a(adm2, \u2603, this.b);
            if (i3 <= 0) continue;
            this.a(adm2, random2, \u2603.e(), akk.N);
            this.a(adm2, random2, \u2603.d(), akk.b);
        }
        return true;
    }

    private void a(adm adm2, Random random, cj cj2, amk amk2) {
        if (random.nextInt(3) > 0 && adm2.d(cj2)) {
            this.a(adm2, cj2, afi.bn.Q().a(amk2, true));
        }
    }

    private void c(adm adm2, cj cj2, int n2) {
        \u2603 = 2;
        for (\u2603 = -\u2603; \u2603 <= 0; ++\u2603) {
            this.a(adm2, cj2.b(\u2603), n2 + 1 - \u2603);
        }
    }
}

