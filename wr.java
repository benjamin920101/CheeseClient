/*
 * Decompiled with CFR 0.152.
 */
public class wr
extends pk {
    private double a;
    private double b;
    private double c;
    private int d;
    private boolean e;

    public wr(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
    }

    @Override
    protected void h() {
    }

    @Override
    public boolean a(double d2) {
        \u2603 = this.aR().a() * 4.0;
        if (Double.isNaN(\u2603)) {
            \u2603 = 4.0;
        }
        return d2 < (\u2603 *= 64.0) * \u2603;
    }

    public wr(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.d = 0;
        this.a(0.25f, 0.25f);
        this.b(d2, d3, d4);
    }

    public void a(cj cj2) {
        double d2 = cj2.n();
        int \u26032 = cj2.o();
        \u2603 = d2 - this.s;
        \u2603 = cj2.p();
        \u2603 = \u2603 - this.u;
        float \u26033 = ns.a(\u2603 * \u2603 + \u2603 * \u2603);
        if (\u26033 > 12.0f) {
            this.a = this.s + \u2603 / (double)\u26033 * 12.0;
            this.c = this.u + \u2603 / (double)\u26033 * 12.0;
            this.b = this.t + 8.0;
        } else {
            this.a = d2;
            this.b = \u26032;
            this.c = \u2603;
        }
        this.d = 0;
        this.e = this.V.nextInt(5) > 0;
    }

    @Override
    public void i(double d2, double d3, double d4) {
        this.v = d2;
        this.w = d3;
        this.x = d4;
        if (this.B == 0.0f && this.A == 0.0f) {
            float f2 = ns.a(d2 * d2 + d4 * d4);
            this.A = this.y = (float)(ns.b(d2, d4) * 180.0 / 3.1415927410125732);
            this.B = this.z = (float)(ns.b(d3, f2) * 180.0 / 3.1415927410125732);
        }
    }

    @Override
    public void t_() {
        this.P = this.s;
        this.Q = this.t;
        this.R = this.u;
        super.t_();
        this.s += this.v;
        this.t += this.w;
        this.u += this.x;
        float f2 = ns.a(this.v * this.v + this.x * this.x);
        this.y = (float)(ns.b(this.v, this.x) * 180.0 / 3.1415927410125732);
        this.z = (float)(ns.b(this.w, f2) * 180.0 / 3.1415927410125732);
        while (this.z - this.B < -180.0f) {
            this.B -= 360.0f;
        }
        while (this.z - this.B >= 180.0f) {
            this.B += 360.0f;
        }
        while (this.y - this.A < -180.0f) {
            this.A -= 360.0f;
        }
        while (this.y - this.A >= 180.0f) {
            this.A += 360.0f;
        }
        this.z = this.B + (this.z - this.B) * 0.2f;
        this.y = this.A + (this.y - this.A) * 0.2f;
        if (!this.o.D) {
            double d2 = this.a - this.s;
            \u2603 = this.c - this.u;
            float \u26032 = (float)Math.sqrt(d2 * d2 + \u2603 * \u2603);
            float \u26033 = (float)ns.b(\u2603, d2);
            \u2603 = (double)f2 + (double)(\u26032 - f2) * 0.0025;
            if (\u26032 < 1.0f) {
                \u2603 *= 0.8;
                this.w *= 0.8;
            }
            this.v = Math.cos(\u26033) * \u2603;
            this.x = Math.sin(\u26033) * \u2603;
            this.w = this.t < this.b ? (this.w += (1.0 - this.w) * (double)0.015f) : (this.w += (-1.0 - this.w) * (double)0.015f);
        }
        float f3 = 0.25f;
        if (this.V()) {
            for (int i2 = 0; i2 < 4; ++i2) {
                this.o.a(cy.e, this.s - this.v * (double)f3, this.t - this.w * (double)f3, this.u - this.x * (double)f3, this.v, this.w, this.x, new int[0]);
            }
        } else {
            this.o.a(cy.y, this.s - this.v * (double)f3 + this.V.nextDouble() * 0.6 - 0.3, this.t - this.w * (double)f3 - 0.5, this.u - this.x * (double)f3 + this.V.nextDouble() * 0.6 - 0.3, this.v, this.w, this.x, new int[0]);
        }
        if (!this.o.D) {
            this.b(this.s, this.t, this.u);
            ++this.d;
            if (this.d > 80 && !this.o.D) {
                this.J();
                if (this.e) {
                    this.o.d(new uz(this.o, this.s, this.t, this.u, new zx(zy.bH)));
                } else {
                    this.o.b(2003, new cj(this), 0);
                }
            }
        }
    }

    @Override
    public void b(dn dn2) {
    }

    @Override
    public void a(dn dn2) {
    }

    @Override
    public float c(float f2) {
        return 1.0f;
    }

    @Override
    public int b(float f2) {
        return 0xF000F0;
    }

    @Override
    public boolean aD() {
        return false;
    }
}

