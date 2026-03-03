/*
 * Decompiled with CFR 0.152.
 */
public class aiq
extends afz {
    @Override
    protected zw l() {
        return zy.bS;
    }

    @Override
    protected zw n() {
        return zy.bS;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        super.a(adm2, cj2, alz2, f2, n2);
        if (adm2.D) {
            return;
        }
        if (alz2.b(a) >= 7 && adm2.s.nextInt(50) == 0) {
            aiq.a(adm2, cj2, new zx(zy.bU));
        }
    }
}

