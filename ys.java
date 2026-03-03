/*
 * Decompiled with CFR 0.152.
 */
public class ys
extends zw {
    public ys() {
        this.a(yz.k);
    }

    @Override
    public zx a(zx zx22, adm adm2, wn wn2) {
        zx zx22;
        auh auh2 = this.a(adm2, wn2, true);
        if (auh2 == null) {
            return zx22;
        }
        if (auh2.a == auh.a.b) {
            cj cj2 = auh2.a();
            if (!adm2.a(wn2, cj2)) {
                return zx22;
            }
            if (!wn2.a(cj2.a(auh2.b), auh2.b, zx22)) {
                return zx22;
            }
            if (adm2.p(cj2).c().t() == arm.h) {
                --zx22.b;
                wn2.b(na.ad[zw.b(this)]);
                if (zx22.b <= 0) {
                    return new zx(zy.bz);
                }
                if (!wn2.bi.a(new zx(zy.bz))) {
                    wn2.a(new zx(zy.bz, 1, 0), false);
                }
            }
        }
        return zx22;
    }
}

