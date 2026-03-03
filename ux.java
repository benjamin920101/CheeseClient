/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ux
extends pk {
    private boolean a = true;
    private double b = 0.07;
    private int c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double ar;
    private double as;

    public ux(adm adm2) {
        super(adm2);
        this.k = true;
        this.a(1.5f, 0.6f);
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void h() {
        this.ac.a(17, new Integer(0));
        this.ac.a(18, new Integer(1));
        this.ac.a(19, new Float(0.0f));
    }

    @Override
    public aug j(pk pk2) {
        return pk2.aR();
    }

    @Override
    public aug S() {
        return this.aR();
    }

    @Override
    public boolean ae() {
        return true;
    }

    public ux(adm adm2, double d2, double d3, double d4) {
        this(adm2);
        this.b(d2, d3, d4);
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.p = d2;
        this.q = d3;
        this.r = d4;
    }

    @Override
    public double an() {
        return -0.3;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (this.o.D || this.I) {
            return true;
        }
        if (this.l != null && this.l == ow2.j() && ow2 instanceof oy) {
            return false;
        }
        this.b(-this.m());
        this.a(10);
        this.a(this.j() + f2 * 10.0f);
        this.ac();
        boolean bl2 = \u2603 = ow2.j() instanceof wn && ((wn)ow2.j()).bA.d;
        if (\u2603 || this.j() > 40.0f) {
            if (this.l != null) {
                this.l.a(this);
            }
            if (!\u2603 && this.o.Q().b("doEntityDrops")) {
                this.a(zy.aE, 1, 0.0f);
            }
            this.J();
        }
        return true;
    }

    @Override
    public void ar() {
        this.b(-this.m());
        this.a(10);
        this.a(this.j() * 11.0f);
    }

    @Override
    public boolean ad() {
        return !this.I;
    }

    @Override
    public void a(double d22, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        double d22;
        if (bl2 && this.l != null) {
            this.p = this.s = d22;
            this.q = this.t = d3;
            this.r = this.u = d4;
            this.y = f2;
            this.z = f3;
            this.c = 0;
            this.b(d22, d3, d4);
            this.i = 0.0;
            this.v = 0.0;
            this.ar = 0.0;
            this.w = 0.0;
            this.as = 0.0;
            this.x = 0.0;
            return;
        }
        if (this.a) {
            this.c = n2 + 5;
        } else {
            double d5 = d22 - this.s;
            \u2603 = d3 - this.t;
            \u2603 = d4 - this.u;
            \u2603 = d5 * d5 + \u2603 * \u2603 + \u2603 * \u2603;
            if (\u2603 > 1.0) {
                this.c = 3;
            } else {
                return;
            }
        }
        this.d = d22;
        this.e = d3;
        this.f = d4;
        this.g = f2;
        this.h = f3;
        this.v = this.i;
        this.w = this.ar;
        this.x = this.as;
    }

    @Override
    public void i(double d2, double d3, double d4) {
        this.i = this.v = d2;
        this.ar = this.w = d3;
        this.as = this.x = d4;
    }

    @Override
    public void t_() {
        int \u26037;
        double \u26036;
        double d2;
        super.t_();
        if (this.l() > 0) {
            this.a(this.l() - 1);
        }
        if (this.j() > 0.0f) {
            this.a(this.j() - 1.0f);
        }
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        int n2 = 5;
        double \u26032 = 0.0;
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            double d3 = this.aR().b + (this.aR().e - this.aR().b) * (double)(\u2603 + 0) / (double)n2 - 0.125;
            \u2603 = this.aR().b + (this.aR().e - this.aR().b) * (double)(\u2603 + 1) / (double)n2 - 0.125;
            aug \u26033 = new aug(this.aR().a, d3, this.aR().c, this.aR().d, \u2603, this.aR().f);
            if (!this.o.b(\u26033, arm.h)) continue;
            \u26032 += 1.0 / (double)n2;
        }
        \u2603 = Math.sqrt(this.v * this.v + this.x * this.x);
        if (\u2603 > 0.2975) {
            d2 = Math.cos((double)this.y * Math.PI / 180.0);
            \u26035 = Math.sin((double)this.y * Math.PI / 180.0);
            int n3 = 0;
            while ((double)n3 < 1.0 + \u2603 * 60.0) {
                double d4 = this.V.nextFloat() * 2.0f - 1.0f;
                \u2603 = (double)(this.V.nextInt(2) * 2 - 1) * 0.7;
                if (this.V.nextBoolean()) {
                    \u2603 = this.s - d2 * d4 * 0.8 + \u26035 * \u2603;
                    \u2603 = this.u - \u26035 * d4 * 0.8 - d2 * \u2603;
                    this.o.a(cy.f, \u2603, this.t - 0.125, \u2603, this.v, this.w, this.x, new int[0]);
                } else {
                    \u2603 = this.s + d2 + \u26035 * d4 * 0.7;
                    \u2603 = this.u + \u26035 - d2 * d4 * 0.7;
                    this.o.a(cy.f, \u2603, this.t - 0.125, \u2603, this.v, this.w, this.x, new int[0]);
                }
                ++n3;
            }
        }
        if (this.o.D && this.a) {
            if (this.c > 0) {
                d2 = this.s + (this.d - this.s) / (double)this.c;
                \u26035 = this.t + (this.e - this.t) / (double)this.c;
                \u2603 = this.u + (this.f - this.u) / (double)this.c;
                \u2603 = ns.g(this.g - (double)this.y);
                this.y = (float)((double)this.y + \u2603 / (double)this.c);
                this.z = (float)((double)this.z + (this.h - (double)this.z) / (double)this.c);
                --this.c;
                this.b(d2, \u26035, \u2603);
                this.b(this.y, this.z);
            } else {
                d2 = this.s + this.v;
                \u26035 = this.t + this.w;
                \u2603 = this.u + this.x;
                this.b(d2, \u26035, \u2603);
                if (this.C) {
                    this.v *= 0.5;
                    this.w *= 0.5;
                    this.x *= 0.5;
                }
                this.v *= (double)0.99f;
                this.w *= (double)0.95f;
                this.x *= (double)0.99f;
            }
            return;
        }
        if (\u26032 < 1.0) {
            d2 = \u26032 * 2.0 - 1.0;
            this.w += (double)0.04f * d2;
        } else {
            if (this.w < 0.0) {
                this.w /= 2.0;
            }
            this.w += (double)0.007f;
        }
        if (this.l instanceof pr) {
            pr pr2 = (pr)this.l;
            float \u26034 = this.l.y + -pr2.aZ * 90.0f;
            this.v += -Math.sin(\u26034 * (float)Math.PI / 180.0f) * this.b * (double)pr2.ba * (double)0.05f;
            this.x += Math.cos(\u26034 * (float)Math.PI / 180.0f) * this.b * (double)pr2.ba * (double)0.05f;
        }
        if ((\u26036 = Math.sqrt(this.v * this.v + this.x * this.x)) > 0.35) {
            double \u26035 = 0.35 / \u26036;
            this.v *= \u26035;
            this.x *= \u26035;
            \u26036 = 0.35;
        }
        if (\u26036 > \u2603 && this.b < 0.35) {
            this.b += (0.35 - this.b) / 35.0;
            if (this.b > 0.35) {
                this.b = 0.35;
            }
        } else {
            this.b -= (this.b - 0.07) / 35.0;
            if (this.b < 0.07) {
                this.b = 0.07;
            }
        }
        for (\u26037 = 0; \u26037 < 4; ++\u26037) {
            int n4 = ns.c(this.s + ((double)(\u26037 % 2) - 0.5) * 0.8);
            n3 = ns.c(this.u + ((double)(\u26037 / 2) - 0.5) * 0.8);
            for (\u2603 = 0; \u2603 < 2; ++\u2603) {
                \u2603 = ns.c(this.t) + \u2603;
                cj cj2 = new cj(n4, \u2603, n3);
                afh \u26038 = this.o.p(cj2).c();
                if (\u26038 == afi.aH) {
                    this.o.g(cj2);
                    this.D = false;
                    continue;
                }
                if (\u26038 != afi.bx) continue;
                this.o.b(cj2, true);
                this.D = false;
            }
        }
        if (this.C) {
            this.v *= 0.5;
            this.w *= 0.5;
            this.x *= 0.5;
        }
        this.d(this.v, this.w, this.x);
        if (this.D && \u2603 > 0.2975) {
            if (!this.o.D && !this.I) {
                this.J();
                if (this.o.Q().b("doEntityDrops")) {
                    for (\u26037 = 0; \u26037 < 3; ++\u26037) {
                        this.a(zw.a(afi.f), 1, 0.0f);
                    }
                    for (\u26037 = 0; \u26037 < 2; ++\u26037) {
                        this.a(zy.y, 1, 0.0f);
                    }
                }
            }
        } else {
            this.v *= (double)0.99f;
            this.w *= (double)0.95f;
            this.x *= (double)0.99f;
        }
        this.z = 0.0f;
        double d5 = this.y;
        \u2603 = this.p - this.s;
        \u2603 = this.r - this.u;
        if (\u2603 * \u2603 + \u2603 * \u2603 > 0.001) {
            d5 = (float)(ns.b(\u2603, \u2603) * 180.0 / Math.PI);
        }
        if ((\u2603 = ns.g(d5 - (double)this.y)) > 20.0) {
            \u2603 = 20.0;
        }
        if (\u2603 < -20.0) {
            \u2603 = -20.0;
        }
        this.y = (float)((double)this.y + \u2603);
        this.b(this.y, this.z);
        if (this.o.D) {
            return;
        }
        List<pk> \u26039 = this.o.b(this, this.aR().b(0.2f, 0.0, 0.2f));
        if (\u26039 != null && !\u26039.isEmpty()) {
            for (int i2 = 0; i2 < \u26039.size(); ++i2) {
                pk pk2 = \u26039.get(i2);
                if (pk2 == this.l || !pk2.ae() || !(pk2 instanceof ux)) continue;
                pk2.i(this);
            }
        }
        if (this.l != null && this.l.I) {
            this.l = null;
        }
    }

    @Override
    public void al() {
        if (this.l == null) {
            return;
        }
        double d2 = Math.cos((double)this.y * Math.PI / 180.0) * 0.4;
        \u2603 = Math.sin((double)this.y * Math.PI / 180.0) * 0.4;
        this.l.b(this.s + d2, this.t + this.an() + this.l.am(), this.u + \u2603);
    }

    @Override
    protected void b(dn dn2) {
    }

    @Override
    protected void a(dn dn2) {
    }

    @Override
    public boolean e(wn wn2) {
        if (this.l != null && this.l instanceof wn && this.l != wn2) {
            return true;
        }
        if (!this.o.D) {
            wn2.a(this);
        }
        return true;
    }

    @Override
    protected void a(double d22, boolean bl2, afh afh2, cj cj2) {
        double d22;
        if (bl2) {
            if (this.O > 3.0f) {
                this.e(this.O, 1.0f);
                if (!this.o.D && !this.I) {
                    this.J();
                    if (this.o.Q().b("doEntityDrops")) {
                        int n2;
                        for (n2 = 0; n2 < 3; ++n2) {
                            this.a(zw.a(afi.f), 1, 0.0f);
                        }
                        for (n2 = 0; n2 < 2; ++n2) {
                            this.a(zy.y, 1, 0.0f);
                        }
                    }
                }
                this.O = 0.0f;
            }
        } else if (this.o.p(new cj(this).b()).c().t() != arm.h && d22 < 0.0) {
            this.O = (float)((double)this.O - d22);
        }
    }

    public void a(float f2) {
        this.ac.b(19, Float.valueOf(f2));
    }

    public float j() {
        return this.ac.d(19);
    }

    public void a(int n2) {
        this.ac.b(17, n2);
    }

    public int l() {
        return this.ac.c(17);
    }

    public void b(int n2) {
        this.ac.b(18, n2);
    }

    public int m() {
        return this.ac.c(18);
    }

    public void a(boolean bl2) {
        this.a = bl2;
    }
}

