/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ags
extends afh {
    public static final amn a = amn.a("moisture", 0, 7);

    protected ags() {
        super(arm.c);
        this.j(this.M.b().a(a, 0));
        this.a(true);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.9375f, 1.0f);
        this.e(255);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return new aug(cj2.n(), cj2.o(), cj2.p(), cj2.n() + 1, cj2.o() + 1, cj2.p() + 1);
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
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        int n2 = alz2.b(a);
        if (this.f(adm2, cj2) || adm2.C(cj2.a())) {
            if (n2 < 7) {
                adm2.a(cj2, alz2.a(a, 7), 2);
            }
        } else if (n2 > 0) {
            adm2.a(cj2, alz2.a(a, n2 - 1), 2);
        } else if (!this.e(adm2, cj2)) {
            adm2.a(cj2, afi.d.Q());
        }
    }

    @Override
    public void a(adm adm2, cj cj2, pk pk2, float f2) {
        if (!(pk2 instanceof pr)) {
            return;
        }
        if (!adm2.D && adm2.s.nextFloat() < f2 - 0.5f) {
            if (!(pk2 instanceof wn) && !adm2.Q().b("mobGriefing")) {
                return;
            }
            adm2.a(cj2, afi.d.Q());
        }
        super.a(adm2, cj2, pk2, f2);
    }

    private boolean e(adm adm2, cj cj2) {
        afh afh2 = adm2.p(cj2.a()).c();
        return afh2 instanceof afz || afh2 instanceof ajx;
    }

    private boolean f(adm adm2, cj cj2) {
        for (cj.a a2 : cj.b(cj2.a(-4, 0, -4), cj2.a(4, 1, 4))) {
            if (adm2.p(a2).c().t() != arm.h) continue;
            return true;
        }
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        super.a(adm2, cj2, alz2, afh2);
        if (adm2.p(cj2.a()).c().t().a()) {
            adm2.a(cj2, afi.d.Q());
        }
    }

    @Override
    public boolean a(adq adq22, cj cj2, cq cq2) {
        adq adq22;
        switch (cq2) {
            case b: {
                return true;
            }
            case c: 
            case d: 
            case e: 
            case f: {
                afh afh2 = adq22.p(cj2).c();
                return !afh2.c() && afh2 != afi.ak;
            }
        }
        return super.a(adq22, cj2, cq2);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return afi.d.a(afi.d.Q().a(agf.a, agf.a.a), random, n2);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.d);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2 & 7);
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

