/*
 * Decompiled with CFR 0.152.
 */
public class abv
implements abs {
    private final int a;
    private final int b;
    private final zx[] c;
    private final zx d;
    private boolean e;

    public abv(int n2, int n3, zx[] zxArray, zx zx2) {
        this.a = n2;
        this.b = n3;
        this.c = zxArray;
        this.d = zx2;
    }

    @Override
    public zx b() {
        return this.d;
    }

    @Override
    public zx[] b(xp xp2) {
        zx[] zxArray = new zx[xp2.o_()];
        for (int i2 = 0; i2 < zxArray.length; ++i2) {
            zx zx2 = xp2.a(i2);
            if (zx2 == null || !zx2.b().r()) continue;
            zxArray[i2] = new zx(zx2.b().q());
        }
        return zxArray;
    }

    @Override
    public boolean a(xp xp2, adm adm2) {
        for (int i2 = 0; i2 <= 3 - this.a; ++i2) {
            for (\u2603 = 0; \u2603 <= 3 - this.b; ++\u2603) {
                if (this.a(xp2, i2, \u2603, true)) {
                    return true;
                }
                if (!this.a(xp2, i2, \u2603, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean a(xp xp2, int n2, int n3, boolean bl2) {
        for (int i2 = 0; i2 < 3; ++i2) {
            for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                \u2603 = i2 - n2;
                \u2603 = \u2603 - n3;
                zx zx2 = null;
                if (\u2603 >= 0 && \u2603 >= 0 && \u2603 < this.a && \u2603 < this.b) {
                    zx2 = bl2 ? this.c[this.a - \u2603 - 1 + \u2603 * this.a] : this.c[\u2603 + \u2603 * this.a];
                }
                if ((\u2603 = xp2.c(i2, \u2603)) == null && zx2 == null) continue;
                if (\u2603 == null && zx2 != null || \u2603 != null && zx2 == null) {
                    return false;
                }
                if (zx2.b() != \u2603.b()) {
                    return false;
                }
                if (zx2.i() == Short.MAX_VALUE || zx2.i() == \u2603.i()) continue;
                return false;
            }
        }
        return true;
    }

    @Override
    public zx a(xp xp2) {
        zx zx2 = this.b().k();
        if (this.e) {
            for (int i2 = 0; i2 < xp2.o_(); ++i2) {
                zx zx3 = xp2.a(i2);
                if (zx3 == null || !zx3.n()) continue;
                zx2.d((dn)zx3.o().b());
            }
        }
        return zx2;
    }

    @Override
    public int a() {
        return this.a * this.b;
    }
}

