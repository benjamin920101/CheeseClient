/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahe
extends afh
implements afj {
    public static final amk a = amk.a("snowy");

    protected ahe() {
        super(arm.b);
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
    public int H() {
        return adl.a(0.5, 1.0);
    }

    @Override
    public int h(alz alz2) {
        return this.H();
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return aea.a(adq2, cj2);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        if (adm2.l(cj2.a()) < 4 && adm2.p(cj2.a()).c().p() > 2) {
            adm2.a(cj2, afi.d.Q());
            return;
        }
        if (adm2.l(cj2.a()) >= 9) {
            for (int i2 = 0; i2 < 4; ++i2) {
                cj cj3 = cj2.a(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                afh \u26032 = adm2.p(cj3.a()).c();
                alz \u26033 = adm2.p(cj3);
                if (\u26033.c() != afi.d || \u26033.b(agf.a) != agf.a.a || adm2.l(cj3.a()) < 4 || \u26032.p() > 2) continue;
                adm2.a(cj3, afi.c.Q());
            }
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return afi.d.a(afi.d.Q().a(agf.a, agf.a.a), random, n2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        return true;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return true;
    }

    @Override
    public void b(adm adm22, Random random, cj cj2, alz alz2) {
        cj cj3 = cj2.a();
        block0: for (int i2 = 0; i2 < 128; ++i2) {
            adm adm22;
            cj cj4 = cj3;
            for (int i3 = 0; i3 < i2 / 16; ++i3) {
                if (adm22.p((cj4 = cj4.a(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1)).b()).c() != afi.c || adm22.p(cj4).c().v()) continue block0;
            }
            if (adm22.p((cj)cj4).c().J != arm.a) continue;
            if (random.nextInt(8) == 0) {
                agw.a a2 = adm22.b(cj4).a(random, cj4);
                agw \u26032 = a2.a().a();
                if (!\u26032.f(adm22, cj4, \u2603 = \u26032.Q().a(\u26032.n(), a2))) continue;
                adm22.a(cj4, \u2603, 3);
                continue;
            }
            alz alz3 = afi.H.Q().a(akc.a, akc.a.b);
            if (!afi.H.f(adm22, cj4, alz3)) continue;
            adm22.a(cj4, alz3, 3);
        }
    }

    @Override
    public adf m() {
        return adf.b;
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

