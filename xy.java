/*
 * Decompiled with CFR 0.152.
 */
public class xy
extends xi {
    public xp a = new xp(this, 2, 2);
    public og f = new ye();
    public boolean g;
    private final wn h;

    public xy(wm wm2, boolean bl2, wn wn2) {
        int n2;
        this.g = bl2;
        this.h = wn2;
        this.a(new yf(wm2.d, this.a, this.f, 0, 144, 36));
        for (n2 = 0; n2 < 2; ++n2) {
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                this.a(new yg(this.a, \u2603 + n2 * 2, 88 + \u2603 * 18, 26 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 4; ++n2) {
            \u2603 = n2;
            this.a(new yg(wm2, wm2.o_() - 1 - n2, 8, 8 + n2 * 18){

                @Override
                public int a() {
                    return 1;
                }

                @Override
                public boolean a(zx zx2) {
                    if (zx2 == null) {
                        return false;
                    }
                    if (zx2.b() instanceof yj) {
                        return ((yj)zx2.b()).b == \u2603;
                    }
                    if (zx2.b() == zw.a(afi.aU) || zx2.b() == zy.bX) {
                        return \u2603 == 0;
                    }
                    return false;
                }

                @Override
                public String c() {
                    return yj.a[\u2603];
                }
            });
        }
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + (n2 + 1) * 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
        this.a(this.a);
    }

    @Override
    public void a(og og2) {
        this.f.a(0, abt.a().a(this.a, this.h.o));
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        for (int i2 = 0; i2 < 4; ++i2) {
            zx zx2 = this.a.b(i2);
            if (zx2 == null) continue;
            wn2.a(zx2, false);
        }
        this.f.a(0, null);
    }

    @Override
    public boolean a(wn wn2) {
        return true;
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 == 0) {
                if (!this.a(\u2603, 9, 45, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (n2 >= 1 && n2 < 5 ? !this.a(\u2603, 9, 45, false) : (n2 >= 5 && n2 < 9 ? !this.a(\u2603, 9, 45, false) : (zx2.b() instanceof yj && !((yg)this.c.get(5 + ((yj)zx2.b()).b)).e() ? !this.a(\u2603, \u2603 = 5 + ((yj)zx2.b()).b, \u2603 + 1, false) : (n2 >= 9 && n2 < 36 ? !this.a(\u2603, 36, 45, false) : (n2 >= 36 && n2 < 45 ? !this.a(\u2603, 9, 36, false) : !this.a(\u2603, 9, 45, false)))))) {
                return null;
            }
            if (\u2603.b == 0) {
                \u26032.d(null);
            } else {
                \u26032.f();
            }
            if (\u2603.b == zx2.b) {
                return null;
            }
            \u26032.a(wn2, \u2603);
        }
        return zx2;
    }

    @Override
    public boolean a(zx zx2, yg yg2) {
        return yg2.d != this.f && super.a(zx2, yg2);
    }
}

