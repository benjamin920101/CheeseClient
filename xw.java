/*
 * Decompiled with CFR 0.152.
 */
public class xw
extends xi {
    private final og a;

    public xw(wm wm2, og og2, wn wn2) {
        this.a = og2;
        og2.b(wn2);
        int n2 = 51;
        for (\u2603 = 0; \u2603 < og2.o_(); ++\u2603) {
            this.a(new yg(og2, \u2603, 44 + \u2603 * 18, 20));
        }
        for (\u2603 = 0; \u2603 < 3; ++\u2603) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(wm2, \u2603 + \u2603 * 9 + 9, 8 + \u2603 * 18, \u2603 * 18 + n2));
            }
        }
        for (\u2603 = 0; \u2603 < 9; ++\u2603) {
            this.a(new yg(wm2, \u2603, 8 + \u2603 * 18, 58 + n2));
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
            if (n2 < this.a.o_() ? !this.a(\u2603, this.a.o_(), this.c.size(), true) : !this.a(\u2603, 0, this.a.o_(), false)) {
                return null;
            }
            if (\u2603.b == 0) {
                \u26032.d(null);
            } else {
                \u26032.f();
            }
        }
        return zx2;
    }

    @Override
    public void b(wn wn2) {
        super.b(wn2);
        this.a.c(wn2);
    }
}

