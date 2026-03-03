/*
 * Decompiled with CFR 0.152.
 */
public class ajv
extends ajl {
    public static final amn a = amn.a("rotation", 0, 15);

    public ajv() {
        this.j(this.M.b().a(a, 0));
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm2.p(cj2.b()).c().t().a()) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
        super.a(adm2, cj2, alz2, afh2);
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

