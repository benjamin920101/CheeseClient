/*
 * Decompiled with CFR 0.152.
 */
public class zl
extends zw {
    public zl() {
        this.a(yz.f);
    }

    @Override
    public boolean f(zx zx2) {
        return true;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (!wn2.bA.d) {
            --zx2.b;
        }
        adm2.a((pk)wn2, "random.bow", 0.5f, 0.4f / (g.nextFloat() * 0.4f + 0.8f));
        if (!adm2.D) {
            adm2.d(new xb(adm2, wn2));
        }
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }
}

