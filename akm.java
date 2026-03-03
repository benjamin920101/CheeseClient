/*
 * Decompiled with CFR 0.152.
 */
public class akm
extends ajl {
    public static final aml a = aml.a("facing", cq.c.a);

    public akm() {
        this.j(this.M.b().a(a, cq.c));
    }

    @Override
    public void a(adq adq2, cj cj2) {
        cq cq2 = adq2.p(cj2).b(a);
        float \u26032 = 0.28125f;
        float \u26033 = 0.78125f;
        float \u26034 = 0.0f;
        float \u26035 = 1.0f;
        float \u26036 = 0.125f;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        switch (cq2) {
            case c: {
                this.a(\u26034, \u26032, 1.0f - \u26036, \u26035, \u26033, 1.0f);
                break;
            }
            case d: {
                this.a(\u26034, \u26032, 0.0f, \u26035, \u26033, \u26036);
                break;
            }
            case e: {
                this.a(1.0f - \u26036, \u26032, \u26034, 1.0f, \u26033, \u26035);
                break;
            }
            case f: {
                this.a(0.0f, \u26032, \u26034, \u26036, \u26033, \u26035);
            }
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        cq cq2 = alz2.b(a);
        if (!adm2.p(cj2.a(cq2.d())).c().t().a()) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
        super.a(adm2, cj2, alz2, afh2);
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

