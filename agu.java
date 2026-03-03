/*
 * Decompiled with CFR 0.152.
 */
public class agu
extends age {
    public static final amk a = amk.a("open");
    public static final amk b = amk.a("powered");
    public static final amk N = amk.a("in_wall");

    public agu(aio.a a2) {
        super(arm.d, a2.c());
        this.j(this.M.b().a(a, false).a(b, false).a(N, false));
        this.a(yz.d);
    }

    @Override
    public alz a(alz alz22, adq adq2, cj cj2) {
        alz alz22;
        cq.a a2 = alz22.b(O).k();
        if (a2 == cq.a.c && (adq2.p(cj2.e()).c() == afi.bZ || adq2.p(cj2.f()).c() == afi.bZ) || a2 == cq.a.a && (adq2.p(cj2.c()).c() == afi.bZ || adq2.p(cj2.d()).c() == afi.bZ)) {
            alz22 = alz22.a(N, true);
        }
        return alz22;
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        if (adm2.p(cj2.b()).c().t().a()) {
            return super.d(adm2, cj2);
        }
        return false;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        if (alz2.b(a).booleanValue()) {
            return null;
        }
        cq.a a2 = alz2.b(O).k();
        if (a2 == cq.a.c) {
            return new aug(cj2.n(), cj2.o(), (float)cj2.p() + 0.375f, cj2.n() + 1, (float)cj2.o() + 1.5f, (float)cj2.p() + 0.625f);
        }
        return new aug((float)cj2.n() + 0.375f, cj2.o(), cj2.p(), (float)cj2.n() + 0.625f, (float)cj2.o() + 1.5f, cj2.p() + 1);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        cq.a a2 = adq2.p(cj2).b(O).k();
        if (a2 == cq.a.c) {
            this.a(0.0f, 0.0f, 0.375f, 1.0f, 1.0f, 0.625f);
        } else {
            this.a(0.375f, 0.0f, 0.0f, 0.625f, 1.0f, 1.0f);
        }
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
    public boolean b(adq adq2, cj cj2) {
        return adq2.p(cj2).b(a);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(O, pr2.aP()).a(a, false).a(b, false).a(N, false);
    }

    @Override
    public boolean a(adm adm22, cj cj2, alz alz22, wn wn22, cq cq2, float f2, float f3, float f4) {
        adm adm22;
        alz alz22;
        if (alz22.b(a).booleanValue()) {
            alz22 = alz22.a(a, false);
            adm22.a(cj2, alz22, 2);
        } else {
            wn wn22;
            cq \u26032 = cq.a(wn22.y);
            if (alz22.b(O) == \u26032.d()) {
                alz22 = alz22.a(O, \u26032);
            }
            alz22 = alz22.a(a, true);
            adm22.a(cj2, alz22, 2);
        }
        adm22.a(wn22, alz22.b(a) != false ? 1003 : 1006, cj2, 0);
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (adm2.D) {
            return;
        }
        boolean bl2 = adm2.z(cj2);
        if (bl2 || afh2.i()) {
            if (bl2 && !alz2.b(a).booleanValue() && !alz2.b(b).booleanValue()) {
                adm2.a(cj2, alz2.a(a, true).a(b, true), 2);
                adm2.a(null, 1003, cj2, 0);
            } else if (!bl2 && alz2.b(a).booleanValue() && alz2.b(b).booleanValue()) {
                adm2.a(cj2, alz2.a(a, false).a(b, false), 2);
                adm2.a(null, 1006, cj2, 0);
            } else if (bl2 != alz2.b(b)) {
                adm2.a(cj2, alz2.a(b, bl2), 2);
            }
        }
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return true;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, cq.b(n2)).a(a, (n2 & 4) != 0).a(b, (n2 & 8) != 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(O).b();
        if (alz2.b(b).booleanValue()) {
            n2 |= 8;
        }
        if (alz2.b(a).booleanValue()) {
            n2 |= 4;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, O, a, b, N);
    }
}

