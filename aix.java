/*
 * Decompiled with CFR 0.152.
 */
public class aix
extends afe {
    public static final amm<afe.b> b = amm.a("shape", afe.b.class);

    protected aix() {
        super(false);
        this.j(this.M.b().a(b, afe.b.a));
    }

    @Override
    protected void b(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (afh2.i() && new afe.a(adm2, cj2, alz2).a() == 3) {
            this.a(adm2, cj2, alz2, false);
        }
    }

    @Override
    public amo<afe.b> n() {
        return b;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(b, afe.b.a(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(b).a();
    }

    @Override
    protected ama e() {
        return new ama(this, b);
    }
}

