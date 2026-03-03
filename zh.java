/*
 * Decompiled with CFR 0.152.
 */
public class zh
extends yy {
    protected zh() {
        this.a(yz.f);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        zx zx3 = new zx(zy.bd, 1, adm2.b("map"));
        String \u26032 = "map_" + zx3.i();
        atg \u26033 = new atg(\u26032);
        adm2.a(\u26032, \u26033);
        \u26033.e = 0;
        \u26033.a(wn2.s, wn2.u, \u26033.e);
        \u26033.d = (byte)adm2.t.q();
        \u26033.c();
        --zx2.b;
        if (zx2.b <= 0) {
            return zx3;
        }
        if (!wn2.bi.a(zx3.k())) {
            wn2.a(zx3, false);
        }
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }
}

