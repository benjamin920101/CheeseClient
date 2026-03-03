/*
 * Decompiled with CFR 0.152.
 */
public class abq
extends abv {
    public abq() {
        super(3, 3, new zx[]{new zx(zy.aK), new zx(zy.aK), new zx(zy.aK), new zx(zy.aK), new zx(zy.bd, 0, Short.MAX_VALUE), new zx(zy.aK), new zx(zy.aK), new zx(zy.aK), new zx(zy.aK)}, new zx(zy.bV, 0, 0));
    }

    @Override
    public boolean a(xp xp2, adm adm2) {
        if (!super.a(xp2, adm2)) {
            return false;
        }
        zx zx2 = null;
        for (int i2 = 0; i2 < xp2.o_() && zx2 == null; ++i2) {
            zx zx3 = xp2.a(i2);
            if (zx3 == null || zx3.b() != zy.bd) continue;
            zx2 = zx3;
        }
        if (zx2 == null) {
            return false;
        }
        atg \u26032 = zy.bd.a(zx2, adm2);
        if (\u26032 == null) {
            return false;
        }
        return \u26032.e < 4;
    }

    @Override
    public zx a(xp xp2) {
        zx zx2 = null;
        for (int i2 = 0; i2 < xp2.o_() && zx2 == null; ++i2) {
            zx zx3 = xp2.a(i2);
            if (zx3 == null || zx3.b() != zy.bd) continue;
            zx2 = zx3;
        }
        zx2 = zx2.k();
        zx2.b = 1;
        if (zx2.o() == null) {
            zx2.d(new dn());
        }
        zx2.o().a("map_is_scaling", true);
        return zx2;
    }
}

