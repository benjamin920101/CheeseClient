/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class aga
extends afc {
    public static final amn a = amn.a("power", 0, 15);
    private final boolean b;

    public aga(boolean bl2) {
        super(arm.d);
        this.b = bl2;
        this.j(this.M.b().a(a, 0));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.375f, 1.0f);
        this.a(yz.d);
        this.c(0.2f);
        this.a(f);
        this.c("daylightDetector");
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.375f, 1.0f);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        return alz2.b(a);
    }

    public void f(adm adm2, cj cj2) {
        if (adm2.t.o()) {
            return;
        }
        alz alz2 = adm2.p(cj2);
        int \u26032 = adm2.b(ads.a, cj2) - adm2.ab();
        float \u26033 = adm2.d(1.0f);
        float \u26034 = \u26033 < (float)Math.PI ? 0.0f : (float)Math.PI * 2;
        \u26033 += (\u26034 - \u26033) * 0.2f;
        \u26032 = Math.round((float)\u26032 * ns.b(\u26033));
        \u26032 = ns.a(\u26032, 0, 15);
        if (this.b) {
            \u26032 = 15 - \u26032;
        }
        if (alz2.b(a) != \u26032) {
            adm2.a(cj2, alz2.a(a, \u26032), 3);
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (wn2.cn()) {
            if (adm2.D) {
                return true;
            }
            if (this.b) {
                adm2.a(cj2, afi.cl.Q().a(a, alz2.b(a)), 4);
                afi.cl.f(adm2, cj2);
            } else {
                adm2.a(cj2, afi.cm.Q().a(a, alz2.b(a)), 4);
                afi.cm.f(adm2, cj2);
            }
            return true;
        }
        return super.a(adm2, cj2, alz2, wn2, cq2, f2, f3, f4);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.cl);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.cl);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alb();
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

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        if (!this.b) {
            super.a(zw2, yz2, list);
        }
    }
}

