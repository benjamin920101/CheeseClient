/*
 * Decompiled with CFR 0.152.
 */
public class yb
extends xi {
    private acy a;
    private ya f;
    private final adm g;

    public yb(wm wm2, acy acy2, adm adm2) {
        int n2;
        this.a = acy2;
        this.g = adm2;
        this.f = new ya(wm2.d, acy2);
        this.a(new yg(this.f, 0, 36, 53));
        this.a(new yg(this.f, 1, 62, 53));
        this.a(new yc(wm2.d, acy2, this.f, 2, 120, 53));
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(wm2, n2, 8 + n2 * 18, 142));
        }
    }

    public ya e() {
        return this.f;
    }

    @Override
    public void a(xn xn2) {
        super.a(xn2);
    }

    @Override
    public void b() {
        super.b();
    }

    @Override
    public void a(og og2) {
        this.f.h();
        super.a(og2);
    }

    public void d(int n2) {
        this.f.d(n2);
    }

    @Override
    public void b(int n2, int n3) {
    }

    @Override
    public boolean a(wn wn2) {
        return this.a.v_() == wn2;
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
            } else if (n2 == 0 || n2 == 1 ? !this.a(\u2603, 3, 39, false) : (n2 >= 3 && n2 < 30 ? !this.a(\u2603, 30, 39, false) : n2 >= 30 && n2 < 39 && !this.a(\u2603, 3, 30, false))) {
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
    public void b(wn wn2) {
        super.b(wn2);
        this.a.a_((wn)null);
        super.b(wn2);
        if (this.g.D) {
            return;
        }
        zx zx2 = this.f.b(0);
        if (zx2 != null) {
            wn2.a(zx2, false);
        }
        if ((zx2 = this.f.b(1)) != null) {
            wn2.a(zx2, false);
        }
    }
}

