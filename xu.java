/*
 * Decompiled with CFR 0.152.
 */
public class xu
extends xi {
    private final og a;
    private int f;
    private int g;
    private int h;
    private int i;

    public xu(wm wm2, og og2) {
        int n2;
        this.a = og2;
        this.a(new yg(og2, 0, 56, 17));
        this.a(new xt(og2, 1, 56, 53));
        this.a(new xv(wm2.d, og2, 2, 116, 35));
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
    }

    @Override
    public void a(xn xn2) {
        super.a(xn2);
        xn2.a((xi)this, this.a);
    }

    @Override
    public void b() {
        super.b();
        for (int i2 = 0; i2 < this.e.size(); ++i2) {
            xn xn2 = (xn)this.e.get(i2);
            if (this.f != this.a.a_(2)) {
                xn2.a((xi)this, 2, this.a.a_(2));
            }
            if (this.h != this.a.a_(0)) {
                xn2.a((xi)this, 0, this.a.a_(0));
            }
            if (this.i != this.a.a_(1)) {
                xn2.a((xi)this, 1, this.a.a_(1));
            }
            if (this.g == this.a.a_(3)) continue;
            xn2.a((xi)this, 3, this.a.a_(3));
        }
        this.f = this.a.a_(2);
        this.h = this.a.a_(0);
        this.i = this.a.a_(1);
        this.g = this.a.a_(3);
    }

    @Override
    public void b(int n2, int n3) {
        this.a.b(n2, n3);
    }

    @Override
    public boolean a(wn wn2) {
        return this.a.a(wn2);
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 == 2) {
                if (!this.a(\u2603, 3, 39, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (n2 == 1 || n2 == 0 ? !this.a(\u2603, 3, 39, false) : (abo.a().a(\u2603) != null ? !this.a(\u2603, 0, 1, false) : (alh.c(\u2603) ? !this.a(\u2603, 1, 2, false) : (n2 >= 3 && n2 < 30 ? !this.a(\u2603, 30, 39, false) : n2 >= 30 && n2 < 39 && !this.a(\u2603, 3, 30, false))))) {
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
}

