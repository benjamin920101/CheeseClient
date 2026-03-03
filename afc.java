/*
 * Decompiled with CFR 0.152.
 */
public abstract class afc
extends afh
implements agq {
    protected afc(arm arm2) {
        this(arm2, arm2.r());
    }

    protected afc(arm arm2, arn arn2) {
        super(arm2, arn2);
        this.A = true;
    }

    protected boolean a(adm adm2, cj cj2, cq cq2) {
        return adm2.p(cj2.a(cq2)).c().t() == arm.A;
    }

    protected boolean e(adm adm2, cj cj2) {
        return this.a(adm2, cj2, cq.c) || this.a(adm2, cj2, cq.d) || this.a(adm2, cj2, cq.e) || this.a(adm2, cj2, cq.f);
    }

    @Override
    public int b() {
        return -1;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        super.b(adm2, cj2, alz2);
        adm2.t(cj2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, int n2, int n3) {
        super.a(adm2, cj2, alz2, n2, n3);
        akw akw2 = adm2.s(cj2);
        if (akw2 == null) {
            return false;
        }
        return akw2.c(n2, n3);
    }
}

