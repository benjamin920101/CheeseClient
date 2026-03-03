/*
 * Decompiled with CFR 0.152.
 */
public class zu
extends zw {
    private final Class<? extends un> a;

    public zu(Class<? extends un> clazz) {
        this.a = clazz;
        this.a(yz.c);
    }

    @Override
    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        if (cq2 == cq.a) {
            return false;
        }
        if (cq2 == cq.b) {
            return false;
        }
        cj cj3 = cj2.a(cq2);
        if (!wn2.a(cj3, cq2, zx2)) {
            return false;
        }
        un \u26032 = this.a(adm2, cj3, cq2);
        if (\u26032 != null && \u26032.j()) {
            if (!adm2.D) {
                adm2.d(\u26032);
            }
            --zx2.b;
        }
        return true;
    }

    private un a(adm adm2, cj cj2, cq cq2) {
        if (this.a == uq.class) {
            return new uq(adm2, cj2, cq2);
        }
        if (this.a == uo.class) {
            return new uo(adm2, cj2, cq2);
        }
        return null;
    }
}

