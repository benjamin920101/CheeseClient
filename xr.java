/*
 * Decompiled with CFR 0.152.
 */
public class xr
extends xi {
    private og a;

    public xr(og og2, og og3) {
        int n2;
        this.a = og3;
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                this.a(new yg(og3, \u2603 + n2 * 3, 62 + \u2603 * 18, 17 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 3; ++n2) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(og2, \u2603 + n2 * 9 + 9, 8 + \u2603 * 18, 84 + n2 * 18));
            }
        }
        for (n2 = 0; n2 < 9; ++n2) {
            this.a(new yg(og2, n2, 8 + n2 * 18, 142));
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
            if (n2 < 9 ? !this.a(\u2603, 9, 45, true) : !this.a(\u2603, 0, 9, false)) {
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

