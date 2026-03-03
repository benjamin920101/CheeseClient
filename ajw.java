/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ajw
extends ahv {
    protected ajw(arm arm2) {
        super(arm2);
        this.a(false);
        if (arm2 == arm.i) {
            this.a(true);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!this.e(adm2, cj2, alz2)) {
            this.f(adm2, cj2, alz2);
        }
    }

    private void f(adm adm2, cj cj2, alz alz2) {
        agl agl2 = ajw.a(this.J);
        adm2.a(cj2, agl2.Q().a(b, alz2.b(b)), 2);
        adm2.a(cj2, (afh)agl2, this.a(adm2));
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (this.J != arm.i) {
            return;
        }
        if (!adm2.Q().b("doFireTick")) {
            return;
        }
        int n2 = random.nextInt(3);
        if (n2 > 0) {
            cj cj3 = cj2;
            for (int i2 = 0; i2 < n2; ++i2) {
                cj3 = cj3.a(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                afh \u26032 = adm2.p(cj3).c();
                if (\u26032.J == arm.a) {
                    if (!this.f(adm2, cj3)) continue;
                    adm2.a(cj3, afi.ab.Q());
                    return;
                }
                if (!\u26032.J.c()) continue;
                return;
            }
        } else {
            for (int i3 = 0; i3 < 3; ++i3) {
                cj cj4 = cj2.a(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                if (!adm2.d(cj4.a()) || !this.m(adm2, cj4)) continue;
                adm2.a(cj4.a(), afi.ab.Q());
            }
        }
    }

    protected boolean f(adm adm2, cj cj2) {
        for (cq cq2 : cq.values()) {
            if (!this.m(adm2, cj2.a(cq2))) continue;
            return true;
        }
        return false;
    }

    private boolean m(adm adm2, cj cj2) {
        return adm2.p(cj2).c().t().h();
    }
}

