/*
 * Decompiled with CFR 0.152.
 */
public class xx
extends xi {
    private og a;
    private tp f;

    public xx(og og2, og og3, final tp tp2, wn wn2) {
        this.a = og3;
        this.f = tp2;
        int n2 = 3;
        og3.b(wn2);
        \u2603 = (n2 - 4) * 18;
        this.a(new yg(og3, 0, 8, 18){

            @Override
            public boolean a(zx zx2) {
                return super.a(zx2) && zx2.b() == zy.aA && !this.e();
            }
        });
        this.a(new yg(og3, 1, 8, 36){

            @Override
            public boolean a(zx zx2) {
                return super.a(zx2) && tp2.cO() && tp.a(zx2.b());
            }

            @Override
            public boolean b() {
                return tp2.cO();
            }
        });
        if (tp2.cw()) {
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                    this.a(new yg(og3, 2 + \u2603 + \u2603 * 5, 80 + \u2603 * 18, 18 + \u2603 * 18));
                }
            }
        }
        for (\u2603 = 0; \u2603 < 3; ++\u2603) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                this.a(new yg(og2, \u2603 + \u2603 * 9 + 9, 8 + \u2603 * 18, 102 + \u2603 * 18 + \u2603));
            }
        }
        for (\u2603 = 0; \u2603 < 9; ++\u2603) {
            this.a(new yg(og2, \u2603, 8 + \u2603 * 18, 160 + \u2603));
        }
    }

    @Override
    public boolean a(wn wn2) {
        return this.a.a(wn2) && this.f.ai() && this.f.g((pk)wn2) < 8.0f;
    }

    @Override
    public zx b(wn wn2, int n2) {
        zx zx2 = null;
        yg \u26032 = (yg)this.c.get(n2);
        if (\u26032 != null && \u26032.e()) {
            \u2603 = \u26032.d();
            zx2 = \u2603.k();
            if (n2 < this.a.o_() ? !this.a(\u2603, this.a.o_(), this.c.size(), true) : (this.a(1).a(\u2603) && !this.a(1).e() ? !this.a(\u2603, 1, 2, false) : (this.a(0).a(\u2603) ? !this.a(\u2603, 0, 1, false) : this.a.o_() <= 2 || !this.a(\u2603, 2, this.a.o_(), false)))) {
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

