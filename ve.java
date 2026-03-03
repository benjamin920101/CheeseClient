/*
 * Decompiled with CFR 0.152.
 */
public class ve
extends va {
    private int c;
    public double a;
    public double b;

    public ve(adm adm2) {
        super(adm2);
    }

    public ve(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    public va.a s() {
        return va.a.c;
    }

    @Override
    protected void h() {
        super.h();
        this.ac.a(16, new Byte(0));
    }

    @Override
    public void t_() {
        super.t_();
        if (this.c > 0) {
            --this.c;
        }
        if (this.c <= 0) {
            this.b = 0.0;
            this.a = 0.0;
        }
        this.i(this.c > 0);
        if (this.j() && this.V.nextInt(4) == 0) {
            this.o.a(cy.m, this.s, this.t + 0.8, this.u, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    @Override
    protected double m() {
        return 0.2;
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (!ow2.c() && this.o.Q().b("doEntityDrops")) {
            this.a(new zx(afi.al, 1), 0.0f);
        }
    }

    @Override
    protected void a(cj cj2, alz alz2) {
        super.a(cj2, alz2);
        double d2 = this.a * this.a + this.b * this.b;
        if (d2 > 1.0E-4 && this.v * this.v + this.x * this.x > 0.001) {
            d2 = ns.a(d2);
            this.a /= d2;
            this.b /= d2;
            if (this.a * this.v + this.b * this.x < 0.0) {
                this.a = 0.0;
                this.b = 0.0;
            } else {
                \u2603 = d2 / this.m();
                this.a *= \u2603;
                this.b *= \u2603;
            }
        }
    }

    @Override
    protected void o() {
        double d2 = this.a * this.a + this.b * this.b;
        if (d2 > 1.0E-4) {
            d2 = ns.a(d2);
            this.a /= d2;
            this.b /= d2;
            \u2603 = 1.0;
            this.v *= (double)0.8f;
            this.w *= 0.0;
            this.x *= (double)0.8f;
            this.v += this.a * \u2603;
            this.x += this.b * \u2603;
        } else {
            this.v *= (double)0.98f;
            this.w *= 0.0;
            this.x *= (double)0.98f;
        }
        super.o();
    }

    @Override
    public boolean e(wn wn2) {
        zx zx2 = wn2.bi.h();
        if (zx2 != null && zx2.b() == zy.h) {
            if (!wn2.bA.d && --zx2.b == 0) {
                wn2.bi.a(wn2.bi.c, null);
            }
            this.c += 3600;
        }
        this.a = this.s - wn2.s;
        this.b = this.u - wn2.u;
        return true;
    }

    @Override
    protected void b(dn dn2) {
        super.b(dn2);
        dn2.a("PushX", this.a);
        dn2.a("PushZ", this.b);
        dn2.a("Fuel", (short)this.c);
    }

    @Override
    protected void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.i("PushX");
        this.b = dn2.i("PushZ");
        this.c = dn2.e("Fuel");
    }

    protected boolean j() {
        return (this.ac.a(16) & 1) != 0;
    }

    protected void i(boolean bl2) {
        if (bl2) {
            this.ac.b(16, (byte)(this.ac.a(16) | 1));
        } else {
            this.ac.b(16, (byte)(this.ac.a(16) & 0xFFFFFFFE));
        }
    }

    @Override
    public alz u() {
        return (this.j() ? afi.am : afi.al).Q().a(ahb.a, cq.c);
    }
}

