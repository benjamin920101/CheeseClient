/*
 * Decompiled with CFR 0.152.
 */
public class aac
extends zw {
    public aac() {
        this.c(1);
        this.a(yz.f);
    }

    @Override
    public zx b(zx zx2, adm adm2, wn wn2) {
        if (!wn2.bA.d) {
            --zx2.b;
        }
        if (!adm2.D) {
            wn2.bk();
        }
        wn2.b(na.ad[zw.b(this)]);
        if (zx2.b <= 0) {
            return new zx(zy.aw);
        }
        return zx2;
    }

    @Override
    public int d(zx zx2) {
        return 32;
    }

    @Override
    public aba e(zx zx2) {
        return aba.c;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        wn2.a(zx2, this.d(zx2));
        return zx2;
    }
}

