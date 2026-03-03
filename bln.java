/*
 * Decompiled with CFR 0.152.
 */
public class bln
extends bjl<bet> {
    private boolean a;

    public bln(biu biu2) {
        this(biu2, false);
    }

    public bln(biu biu2, boolean bl2) {
        super(biu2, new bbr(0.0f, bl2), 0.5f);
        this.a = bl2;
        this.a(new bkx(this));
        this.a(new bky(this));
        this.a(new bko(this));
        this.a(new bkt(this));
        this.a(new bkp(this));
        this.a(new bks(this.g().e));
    }

    public bbr g() {
        return (bbr)super.b();
    }

    @Override
    public void a(bet bet2, double d2, double d3, double d4, float f2, float f3) {
        if (bet2.cc() && this.b.c != bet2) {
            return;
        }
        double d5 = d3;
        if (bet2.av() && !(bet2 instanceof bew)) {
            d5 -= 0.125;
        }
        this.d(bet2);
        super.a(bet2, d2, d5, d4, f2, f3);
    }

    private void d(bet bet2) {
        bbr bbr2 = this.g();
        if (bet2.v()) {
            bbr2.a(false);
            bbr2.e.j = true;
            bbr2.f.j = true;
        } else {
            zx zx2 = bet2.bi.h();
            bbr2.a(true);
            bbr2.f.j = bet2.a(wo.g);
            bbr2.v.j = bet2.a(wo.b);
            bbr2.c.j = bet2.a(wo.e);
            bbr2.d.j = bet2.a(wo.f);
            bbr2.a.j = bet2.a(wo.c);
            bbr2.b.j = bet2.a(wo.d);
            bbr2.l = 0;
            bbr2.o = false;
            bbr2.n = bet2.av();
            if (zx2 == null) {
                bbr2.m = 0;
            } else {
                bbr2.m = 1;
                if (bet2.bR() > 0) {
                    aba aba2 = zx2.m();
                    if (aba2 == aba.d) {
                        bbr2.m = 3;
                    } else if (aba2 == aba.e) {
                        bbr2.o = true;
                    }
                }
            }
        }
    }

    @Override
    protected jy a(bet bet2) {
        return bet2.i();
    }

    @Override
    public void C_() {
        bfl.b(0.0f, 0.1875f, 0.0f);
    }

    @Override
    protected void a(bet bet2, float f2) {
        \u2603 = 0.9375f;
        bfl.a(\u2603, \u2603, \u2603);
    }

    @Override
    protected void a(bet bet22, double d2, double d3, double d4, String string, float f2, double d5) {
        bet bet22;
        if (d5 < 100.0 && (\u2603 = (\u2603 = bet22.cp()).a(2)) != null) {
            aum aum2 = \u2603.c(bet22.e_(), \u2603);
            this.a(bet22, aum2.c() + " " + \u2603.d(), d2, d3, d4, 64);
            d3 += (double)((float)this.c().a * 1.15f * f2);
        }
        super.a(bet22, d2, d3, d4, string, f2, d5);
    }

    public void b(bet bet2) {
        float f2 = 1.0f;
        bfl.c(f2, f2, f2);
        bbr \u26032 = this.g();
        this.d(bet2);
        \u26032.p = 0.0f;
        \u26032.n = false;
        \u26032.a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, bet2);
        \u26032.a();
    }

    public void c(bet bet2) {
        float f2 = 1.0f;
        bfl.c(f2, f2, f2);
        bbr \u26032 = this.g();
        this.d(bet2);
        \u26032.n = false;
        \u26032.p = 0.0f;
        \u26032.a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, bet2);
        \u26032.b();
    }

    @Override
    protected void a(bet bet2, double d2, double d3, double d4) {
        if (bet2.ai() && bet2.bJ()) {
            super.a(bet2, d2 + (double)bet2.by, d3 + (double)bet2.bZ, d4 + (double)bet2.bz);
        } else {
            super.a(bet2, d2, d3, d4);
        }
    }

    @Override
    protected void a(bet bet2, float f2, float f3, float f4) {
        if (bet2.ai() && bet2.bJ()) {
            bfl.b(bet2.ce(), 0.0f, 1.0f, 0.0f);
            bfl.b(this.b(bet2), 0.0f, 0.0f, 1.0f);
            bfl.b(270.0f, 0.0f, 1.0f, 0.0f);
        } else {
            super.a(bet2, f2, f3, f4);
        }
    }

    @Override
    public /* synthetic */ bbo b() {
        return this.g();
    }
}

