/*
 * Decompiled with CFR 0.152.
 */
public class yt
extends zw {
    public static final String[] a = new String[]{"pulling_0", "pulling_1", "pulling_2"};

    public yt() {
        this.h = 1;
        this.d(384);
        this.a(yz.j);
    }

    @Override
    public void a(zx zx2, adm adm2, wn wn2, int n2) {
        boolean bl2 = \u2603 = wn2.bA.d || ack.a(aci.y.B, zx2) > 0;
        if (\u2603 || wn2.bi.b(zy.g)) {
            \u2603 = this.d(zx2) - n2;
            float f2 = (float)\u2603 / 20.0f;
            if ((double)(f2 = (f2 * f2 + f2 * 2.0f) / 3.0f) < 0.1) {
                return;
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            wq \u26032 = new wq(adm2, wn2, f2 * 2.0f);
            if (f2 == 1.0f) {
                \u26032.a(true);
            }
            if ((\u2603 = ack.a(aci.v.B, zx2)) > 0) {
                \u26032.b(\u26032.j() + (double)\u2603 * 0.5 + 0.5);
            }
            if ((\u2603 = ack.a(aci.w.B, zx2)) > 0) {
                \u26032.a(\u2603);
            }
            if (ack.a(aci.x.B, zx2) > 0) {
                \u26032.e(100);
            }
            zx2.a(1, (pr)wn2);
            adm2.a((pk)wn2, "random.bow", 1.0f, 1.0f / (g.nextFloat() * 0.4f + 1.2f) + f2 * 0.5f);
            if (\u2603) {
                \u26032.a = 2;
            } else {
                wn2.bi.a(zy.g);
            }
            wn2.b(na.ad[zw.b(this)]);
            if (!adm2.D) {
                adm2.d(\u26032);
            }
        }
    }

    @Override
    public zx b(zx zx2, adm adm2, wn wn2) {
        return zx2;
    }

    @Override
    public int d(zx zx2) {
        return 72000;
    }

    @Override
    public aba e(zx zx2) {
        return aba.e;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (wn2.bA.d || wn2.bi.b(zy.g)) {
            wn2.a(zx2, this.d(zx2));
        }
        return zx2;
    }

    @Override
    public int b() {
        return 1;
    }
}

