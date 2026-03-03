/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahp
extends ahj {
    public ahp() {
        super(arm.w, false);
        this.L = 0.98f;
        this.a(true);
        this.a(yz.b);
    }

    @Override
    public adf m() {
        return adf.d;
    }

    @Override
    public void a(adm adm22, wn wn2, cj cj2, alz alz2, akw akw2) {
        wn2.b(na.ab[afh.a(this)]);
        wn2.a(0.025f);
        if (this.I() && ack.e(wn2)) {
            zx zx2 = this.i(alz2);
            if (zx2 != null) {
                ahp.a(adm22, cj2, zx2);
            }
        } else {
            adm adm22;
            if (adm22.t.n()) {
                adm22.g(cj2);
                return;
            }
            int \u26032 = ack.f(wn2);
            this.b(adm22, cj2, alz2, \u26032);
            arm \u26033 = adm22.p(cj2.b()).c().t();
            if (\u26033.c() || \u26033.d()) {
                adm22.a(cj2, afi.i.Q());
            }
        }
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.b(ads.b, cj2) <= 11 - this.p()) {
            return;
        }
        if (adm2.t.n()) {
            adm2.g(cj2);
            return;
        }
        this.b(adm2, cj2, adm2.p(cj2), 0);
        adm2.a(cj2, afi.j.Q());
    }

    @Override
    public int k() {
        return 0;
    }
}

