/*
 * Decompiled with CFR 0.152.
 */
public class xq
extends xi {
    public xp a = new xp(this, 3, 3);
    public og f = new ye();
    private adm g;
    private cj h;

    public xq(wm wm2, adm adm2, cj cj2) {
        int n2;
        this.g = adm2;
        this.h = cj2;
        this.a(new yf(wm2.d, this.a, this.f, 0, 124, 35));
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                this.a(new yg(this.a, \u2603 + n2 * 3, 30 + \u2603 * 18, 17 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
        this.a(this.a);
    }

    @Override
    public void a(og og2) {
        this.f.a(0, abt.a().a(this.a, this.g));
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        if (this.g.D) {
            return;
        }
        for (int i2 = 0; i2 < 9; ++i2) {
            zx zx2 = this.a.b(i2);
            if (zx2 == null) continue;
            wn2.a(zx2, false);
        }
    }

    @Override
    public boolean a(wn wn2) {
        if (this.g.p(this.h).c() != afi.ai) {
            return false;
        }
        return !(wn2.e((double)this.h.n() + 0.5, (double)this.h.o() + 0.5, (double)this.h.p() + 0.5) > 64.0);
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 == 0) {
                if (!this.a(\u2603, 10, 46, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (n2 >= 10 && n2 < 37 ? !this.a(\u2603, 37, 46, false) : (n2 >= 37 && n2 < 46 ? !this.a(\u2603, 10, 37, false) : !this.a(\u2603, 10, 46, false))) {
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

