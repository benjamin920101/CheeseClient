/*
 * Decompiled with CFR 0.152.
 */
public class ahr
extends afh {
    public static final aml a = aml.a("facing", cq.c.a);

    protected ahr() {
        super(arm.q);
        this.j(this.M.b().a(a, cq.c));
        this.a(yz.c);
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        this.a((adq)adm2, cj2);
        return super.a(adm2, cj2, alz2);
    }

    @Override
    public aug b(adm adm2, cj cj2) {
        this.a((adq)adm2, cj2);
        return super.b(adm2, cj2);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        alz alz2 = adq2.p(cj2);
        if (alz2.c() != this) {
            return;
        }
        float \u26032 = 0.125f;
        switch (alz2.b(a)) {
            case c: {
                this.a(0.0f, 0.0f, 1.0f - \u26032, 1.0f, 1.0f, 1.0f);
                break;
            }
            case d: {
                this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, \u26032);
                break;
            }
            case e: {
                this.a(1.0f - \u26032, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            }
            default: {
                this.a(0.0f, 0.0f, 0.0f, \u26032, 1.0f, 1.0f);
            }
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
    public boolean d(adm adm2, cj cj2) {
        if (adm2.p(cj2.e()).c().v()) {
            return true;
        }
        if (adm2.p(cj2.f()).c().v()) {
            return true;
        }
        if (adm2.p(cj2.c()).c().v()) {
            return true;
        }
        return adm2.p(cj2.d()).c().v();
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        if (cq2.k().c() && this.a(adm2, cj2, cq2)) {
            return this.Q().a(a, cq2);
        }
        for (cq cq3 : cq.c.a) {
            if (!this.a(adm2, cj2, cq3)) continue;
            return this.Q().a(a, cq3);
        }
        return this.Q();
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        cq cq2 = alz2.b(a);
        if (!this.a(adm2, cj2, cq2)) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
        super.a(adm2, cj2, alz2, afh2);
    }

    protected boolean a(adm adm2, cj cj2, cq cq2) {
        return adm2.p(cj2.a(cq2.d())).c().v();
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        cq cq2 = cq.a(n2);
        if (cq2.k() == cq.a.b) {
            cq2 = cq.c;
        }
        return this.Q().a(a, cq2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

