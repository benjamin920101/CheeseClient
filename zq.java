/*
 * Decompiled with CFR 0.152.
 */
public class zq
extends zw {
    public zq() {
        this.d(64);
        this.c(1);
        this.a(yz.i);
    }

    @Override
    public boolean w_() {
        return true;
    }

    @Override
    public boolean e() {
        return true;
    }

    @Override
    public zx a(zx zx22, adm adm22, wn wn2) {
        zx zx22;
        if (wn2.bG != null) {
            int n2 = wn2.bG.l();
            zx22.a(n2, (pr)wn2);
            wn2.bw();
        } else {
            adm adm22;
            adm22.a((pk)wn2, "random.bow", 0.5f, 0.4f / (g.nextFloat() * 0.4f + 0.8f));
            if (!adm22.D) {
                adm22.d(new ur(adm22, wn2));
            }
            wn2.bw();
            wn2.b(na.ad[zw.b(this)]);
        }
        return zx22;
    }

    @Override
    public boolean f_(zx zx2) {
        return super.f_(zx2);
    }

    @Override
    public int b() {
        return 1;
    }
}

