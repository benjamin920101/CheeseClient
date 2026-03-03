/*
 * Decompiled with CFR 0.152.
 */
public class aao
extends zw {
    private afh a;
    private afh b;

    public aao(afh afh2, afh afh3) {
        this.a = afh2;
        this.b = afh3;
        this.a(yz.l);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (cq2 != cq.b) {
            return false;
        }
        if (!wn2.a(cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        if (adm2.p(cj2).c() == this.b && adm2.d(cj2.a())) {
            adm2.a(cj2.a(), this.a.Q());
            --zx2.b;
            return true;
        }
        return false;
    }
}

