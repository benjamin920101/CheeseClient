/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aib
extends afh {
    public static final amk a = amk.a("snowy");

    protected aib() {
        super(arm.b, arn.z);
        this.j(this.M.b().a(a, false));
        this.a(true);
        this.a(yz.b);
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        afh afh2 = adq2.p(cj2.a()).c();
        return alz2.a(a, afh2 == afi.aJ || afh2 == afi.aH);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        if (adm2.l(cj2.a()) < 4 && adm2.p(cj2.a()).c().p() > 2) {
            adm2.a(cj2, afi.d.Q().a(agf.a, agf.a.a));
            return;
        }
        if (adm2.l(cj2.a()) >= 9) {
            for (int i2 = 0; i2 < 4; ++i2) {
                cj cj3 = cj2.a(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                alz \u26032 = adm2.p(cj3);
                afh \u26033 = adm2.p(cj3.a()).c();
                if (\u26032.c() != afi.d || \u26032.b(agf.a) != agf.a.a || adm2.l(cj3.a()) < 4 || \u26033.p() > 2) continue;
                adm2.a(cj3, this.Q());
            }
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        super.c(adm2, cj2, alz2, random);
        if (random.nextInt(10) == 0) {
            adm2.a(cy.w, (float)cj2.n() + random.nextFloat(), (double)((float)cj2.o() + 1.1f), (double)((float)cj2.p() + random.nextFloat()), 0.0, 0.0, 0.0, new int[0]);
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return afi.d.a(afi.d.Q().a(agf.a, agf.a.a), random, n2);
    }

    @Override
    public int c(alz alz2) {
        return 0;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

