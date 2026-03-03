/*
 * Decompiled with CFR 0.152.
 */
import java.util.UUID;

public class wd
extends vv
implements vx {
    private static final UUID a = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
    private static final qd b = new qd(a, "Drinking speed penalty", -0.25, 0).a(false);
    private static final zw[] c = new zw[]{zy.aT, zy.aY, zy.aC, zy.bB, zy.bA, zy.H, zy.y, zy.y};
    private int bm;

    public wd(adm adm2) {
        super(adm2);
        this.a(0.6f, 1.95f);
        this.i.a(1, new ra(this));
        this.i.a(2, new sa(this, 1.0, 60, 10.0f));
        this.i.a(2, new rz(this, 1.0));
        this.i.a(3, new ri(this, wn.class, 8.0f));
        this.i.a(3, new ry(this));
        this.bi.a(1, new sm((py)this, false, new Class[0]));
        this.bi.a(2, new sp<wn>((py)this, wn.class, true));
    }

    @Override
    protected void h() {
        super.h();
        this.H().a(21, Byte.valueOf((byte)0));
    }

    @Override
    protected String z() {
        return null;
    }

    @Override
    protected String bo() {
        return null;
    }

    @Override
    protected String bp() {
        return null;
    }

    public void a(boolean bl2) {
        this.H().b(21, bl2 ? (byte)1 : 0);
    }

    public boolean n() {
        return this.H().a(21) == 1;
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(26.0);
        this.a(vy.d).a(0.25);
    }

    @Override
    public void m() {
        if (!this.o.D) {
            if (this.n()) {
                if (this.bm-- <= 0) {
                    this.a(false);
                    zx zx2 = this.bA();
                    this.c(0, null);
                    if (zx2 != null && zx2.b() == zy.bz && (\u2603 = zy.bz.h(zx2)) != null) {
                        for (pf pf2 : \u2603) {
                            this.c(new pf(pf2));
                        }
                    }
                    this.a(vy.d).c(b);
                }
            } else {
                int n2 = -1;
                if (this.V.nextFloat() < 0.15f && this.a(arm.h) && !this.a(pe.o)) {
                    n2 = 8237;
                } else if (this.V.nextFloat() < 0.15f && this.at() && !this.a(pe.n)) {
                    n2 = 16307;
                } else if (this.V.nextFloat() < 0.05f && this.bn() < this.bu()) {
                    n2 = 16341;
                } else if (this.V.nextFloat() < 0.25f && this.u() != null && !this.a(pe.c) && this.u().h(this) > 121.0) {
                    n2 = 16274;
                } else if (this.V.nextFloat() < 0.25f && this.u() != null && !this.a(pe.c) && this.u().h(this) > 121.0) {
                    n2 = 16274;
                }
                if (n2 > -1) {
                    this.c(0, new zx(zy.bz, 1, n2));
                    this.bm = this.bA().l();
                    this.a(true);
                    qc qc2 = this.a(vy.d);
                    qc2.c(b);
                    qc2.b(b);
                }
            }
            if (this.V.nextFloat() < 7.5E-4f) {
                this.o.a((pk)this, (byte)15);
            }
        }
        super.m();
    }

    @Override
    public void a(byte by2) {
        if (by2 == 15) {
            for (int i2 = 0; i2 < this.V.nextInt(35) + 10; ++i2) {
                this.o.a(cy.r, this.s + this.V.nextGaussian() * (double)0.13f, this.aR().e + 0.5 + this.V.nextGaussian() * (double)0.13f, this.u + this.V.nextGaussian() * (double)0.13f, 0.0, 0.0, 0.0, new int[0]);
            }
        } else {
            byte by2;
            super.a(by2);
        }
    }

    @Override
    protected float c(ow ow2, float f2) {
        f2 = super.c(ow2, f2);
        if (ow2.j() == this) {
            f2 = 0.0f;
        }
        if (ow2.s()) {
            f2 = (float)((double)f2 * 0.15);
        }
        return f2;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(3) + 1;
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            \u2603 = this.V.nextInt(3);
            zw zw2 = c[this.V.nextInt(c.length)];
            if (n2 > 0) {
                \u2603 += this.V.nextInt(n2 + 1);
            }
            for (int i2 = 0; i2 < \u2603; ++i2) {
                this.a(zw2, 1);
            }
        }
    }

    @Override
    public void a(pr pr2, float f2) {
        if (this.n()) {
            return;
        }
        xc xc2 = new xc(this.o, (pr)this, 32732);
        double \u26032 = pr2.t + (double)pr2.aS() - (double)1.1f;
        xc2.z -= -20.0f;
        double \u26033 = pr2.s + pr2.v - this.s;
        double \u26034 = \u26032 - this.t;
        double \u26035 = pr2.u + pr2.x - this.u;
        float \u26036 = ns.a(\u26033 * \u26033 + \u26035 * \u26035);
        if (\u26036 >= 8.0f && !pr2.a(pe.d)) {
            xc2.a(32698);
        } else if (pr2.bn() >= 8.0f && !pr2.a(pe.u)) {
            xc2.a(32660);
        } else if (\u26036 <= 3.0f && !pr2.a(pe.t) && this.V.nextFloat() < 0.25f) {
            xc2.a(32696);
        }
        xc2.c(\u26033, \u26034 + (double)(\u26036 * 0.2f), \u26035, 0.75f, 8.0f);
        this.o.d(xc2);
    }

    @Override
    public float aS() {
        return 1.62f;
    }
}

