/*
 * Decompiled with CFR 0.152.
 */
public class xl
extends xi {
    private og a;
    private final a f;

    public xl(og og2, og og3) {
        this.a = og3;
        this.f = new a(og3, 0, 136, 110);
        this.a(this.f);
        int n2 = 36;
        \u2603 = 137;
        for (\u2603 = 0; \u2603 < 3; ++\u2603) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(og2, \u2603 + \u2603 * 9 + 9, n2 + \u2603 * 18, \u2603 + \u2603 * 18));
            }
        }
        for (\u2603 = 0; \u2603 < 9; ++\u2603) {
            this.a(new yg(og2, \u2603, n2 + \u2603 * 18, 58 + \u2603));
        }
    }

    @Override
    public void a(xn xn2) {
        super.a(xn2);
        xn2.a((xi)this, this.a);
    }

    @Override
    public void b(int n2, int n3) {
        this.a.b(n2, n3);
    }

    public og e() {
        return this.a;
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        if (wn2 == null || wn2.o.D) {
            return;
        }
        zx zx2 = this.f.a(this.f.a());
        if (zx2 != null) {
            wn2.a(zx2, false);
        }
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
            if (n2 == 0) {
                if (!this.a(\u2603, 1, 37, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (!this.f.e() && this.f.a(\u2603) && \u2603.b == 1 ? !this.a(\u2603, 0, 1, false) : (n2 >= 1 && n2 < 28 ? !this.a(\u2603, 28, 37, false) : (n2 >= 28 && n2 < 37 ? !this.a(\u2603, 1, 28, false) : !this.a(\u2603, 1, 37, false)))) {
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

    class a
    extends yg {
        public a(og og2, int n2, int n3, int n4) {
            super(og2, n2, n3, n4);
        }

        @Override
        public boolean a(zx zx2) {
            if (zx2 != null) {
                return zx2.b() == zy.bO || zx2.b() == zy.i || zx2.b() == zy.k || zx2.b() == zy.j;
            }
            return false;
        }

        @Override
        public int a() {
            return 1;
        }
    }
}

