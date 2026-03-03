/*
 * Decompiled with CFR 0.152.
 */
public class xm
extends xi {
    private og a;
    private final yg f;
    private int g;

    public xm(wm wm2, og og2) {
        int n2;
        this.a = og2;
        this.a(new b(wm2.d, og2, 0, 56, 46));
        this.a(new b(wm2.d, og2, 1, 79, 53));
        this.a(new b(wm2.d, og2, 2, 102, 46));
        this.f = this.a(new a(og2, 3, 79, 17));
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
            if (this.g == this.a.a_(0)) continue;
            xn2.a((xi)this, 0, this.a.a_(0));
        }
        this.g = this.a.a_(0);
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
            if (n2 >= 0 && n2 <= 2 || n2 == 3) {
                if (!this.a(\u2603, 4, 40, true)) {
                    return null;
                }
                \u26032.a(\u2603, zx2);
            } else if (!this.f.e() && this.f.a(\u2603) ? !this.a(\u2603, 3, 4, false) : (xm$b.b_(zx2) ? !this.a(\u2603, 0, 3, false) : (n2 >= 4 && n2 < 31 ? !this.a(\u2603, 31, 40, false) : (n2 >= 31 && n2 < 40 ? !this.a(\u2603, 4, 31, false) : !this.a(\u2603, 4, 40, false))))) {
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
                return zx2.b().l(zx2);
            }
            return false;
        }

        @Override
        public int a() {
            return 64;
        }
    }

    static class b
    extends yg {
        private wn a;

        public b(wn wn2, og og2, int n2, int n3, int n4) {
            super(og2, n2, n3, n4);
            this.a = wn2;
        }

        @Override
        public boolean a(zx zx2) {
            return xm$b.b_(zx2);
        }

        @Override
        public int a() {
            return 1;
        }

        @Override
        public void a(wn wn2, zx zx2) {
            if (zx2.b() == zy.bz && zx2.i() > 0) {
                this.a.b(mr.B);
            }
            super.a(wn2, zx2);
        }

        public static boolean b_(zx zx2) {
            return zx2 != null && (zx2.b() == zy.bz || zx2.b() == zy.bA);
        }
    }
}

