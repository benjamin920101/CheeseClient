/*
 * Decompiled with CFR 0.152.
 */
public class zk
extends zw {
    public zk() {
        this.h = 16;
        this.a(yz.f);
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (wn2.bA.d) {
            return zx2;
        }
        --zx2.b;
        adm2.a((pk)wn2, "random.bow", 0.5f, 0.4f / (g.nextFloat() * 0.4f + 0.8f));
        if (!adm2.D) {
            adm2.d(new xa(adm2, wn2));
        }
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }
}

