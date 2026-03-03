/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aje
extends afh {
    public static final amn a = amn.a("age", 0, 15);

    protected aje() {
        super(arm.k);
        this.j(this.M.b().a(a, 0));
        float f2 = 0.375f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 1.0f, 0.5f + f2);
        this.a(true);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.p(cj2.b()).c() != afi.aM && !this.e(adm2, cj2, alz2)) {
            return;
        }
        if (adm2.d(cj2.a())) {
            int n2 = 1;
            while (adm2.p(cj2.c(n2)).c() == this) {
                ++n2;
            }
            if (n2 < 3) {
                \u2603 = alz2.b(a);
                if (\u2603 == 15) {
                    adm2.a(cj2.a(), this.Q());
                    adm2.a(cj2, alz2.a(a, 0), 4);
                } else {
                    adm2.a(cj2, alz2.a(a, \u2603 + 1), 4);
                }
            }
        }
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        afh afh2 = adm2.p(cj2.b()).c();
        if (afh2 == this) {
            return true;
        }
        if (afh2 == afi.c || afh2 == afi.d || afh2 == afi.m) {
            for (cq cq2 : cq.c.a) {
                if (adm2.p(cj2.a(cq2).b()).c().t() != arm.h) continue;
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        this.e(adm2, cj2, alz2);
    }

    protected final boolean e(adm adm2, cj cj2, alz alz2) {
        if (this.e(adm2, cj2)) {
            return true;
        }
        this.b(adm2, cj2, alz2, 0);
        adm2.g(cj2);
        return false;
    }

    public boolean e(adm adm2, cj cj2) {
        return this.d(adm2, cj2);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.aJ;
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
    public zw c(adm adm2, cj cj2) {
        return zy.aJ;
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return adq2.b(cj2).b(cj2);
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

