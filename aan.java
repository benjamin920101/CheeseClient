/*
 * Decompiled with CFR 0.152.
 */
public class aan
extends zs {
    private afh b;
    private afh c;

    public aan(int n2, float f2, afh afh2, afh afh3) {
        super(n2, f2, false);
        this.b = afh2;
        this.c = afh3;
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (cq2 != cq.b) {
            return false;
        }
        if (!wn2.a(cj2.a(cq2), cq2, zx2)) {
            return false;
        }
        if (adm2.p(cj2).c() == this.c && adm2.d(cj2.a())) {
            adm2.a(cj2.a(), this.b.Q());
            --zx2.b;
            return true;
        }
        return false;
    }
}

