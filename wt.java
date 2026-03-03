/*
 * Decompiled with CFR 0.152.
 */
public class wt
extends pk {
    private int a;
    private int b;

    public wt(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
    }

    @Override
    protected void h() {
        this.ac.a(8, 5);
    }

    @Override
    public boolean a(double d2) {
        return d2 < 4096.0;
    }

    public wt(adm adm2, double d2, double d3, double d4, zx zx2) {
        super(adm2);
        this.a = 0;
        this.a(0.25f, 0.25f);
        this.b(d2, d3, d4);
        int n2 = 1;
        if (zx2 != null && zx2.n()) {
            this.ac.b(8, zx2);
            dn dn2 = zx2.o();
            \u2603 = dn2.m("Fireworks");
            if (\u2603 != null) {
                n2 += \u2603.d("Flight");
            }
        }
        this.v = this.V.nextGaussian() * 0.001;
        this.x = this.V.nextGaussian() * 0.001;
        this.w = 0.05;
        this.b = 10 * n2 + this.V.nextInt(6) + this.V.nextInt(7);
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
        this.v *= 1.15;
        this.x *= 1.15;
        this.w += 0.04;
        this.d(this.v, this.w, this.x);
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
        if (this.a == 0 && !this.R()) {
            this.o.a(this, "fireworks.launch", 3.0f, 1.0f);
        }
        ++this.a;
        if (this.o.D && this.a % 2 < 2) {
            this.o.a(cy.d, this.s, this.t - 0.3, this.u, this.V.nextGaussian() * 0.05, -this.w * 0.5, this.V.nextGaussian() * 0.05, new int[0]);
        }
        if (!this.o.D && this.a > this.b) {
            this.o.a((pk)this, (byte)17);
            this.J();
        }
    }

    @Override
    public void a(byte by) {
        if (by == 17 && this.o.D) {
            zx zx2 = this.ac.f(8);
            dn \u26032 = null;
            if (zx2 != null && zx2.n()) {
                \u26032 = zx2.o().m("Fireworks");
            }
            this.o.a(this.s, this.t, this.u, this.v, this.w, this.x, \u26032);
        }
        super.a(by);
    }

    @Override
    public void b(dn dn2) {
        dn2.a("Life", this.a);
        dn2.a("LifeTime", this.b);
        zx zx2 = this.ac.f(8);
        if (zx2 != null) {
            dn dn3 = new dn();
            zx2.b(dn3);
            dn2.a("FireworksItem", dn3);
        }
    }

    @Override
    public void a(dn dn2) {
        this.a = dn2.f("Life");
        this.b = dn2.f("LifeTime");
        \u2603 = dn2.m("FireworksItem");
        if (\u2603 != null && (\u2603 = zx.a(\u2603)) != null) {
            this.ac.b(8, \u2603);
        }
    }

    @Override
    public float c(float f2) {
        return super.c(f2);
    }

    @Override
    public int b(float f2) {
        return super.b(f2);
    }

    @Override
    public boolean aD() {
        return false;
    }
}

