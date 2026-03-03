/*
 * Decompiled with CFR 0.152.
 */
public class vj
extends pk {
    public int a;
    private pr b;

    public vj(adm adm2) {
        super(adm2);
        this.k = true;
        this.a(0.98f, 0.98f);
    }

    public vj(adm adm2, double d2, double d3, double d4, pr pr2) {
        this(adm2);
        this.b(d2, d3, d4);
        float f2 = (float)(Math.random() * 3.1415927410125732 * 2.0);
        this.v = -((float)Math.sin(f2)) * 0.02f;
        this.w = 0.2f;
        this.x = -((float)Math.cos(f2)) * 0.02f;
        this.a = 80;
        this.p = d2;
        this.q = d3;
        this.r = d4;
        this.b = pr2;
    }

    @Override
    protected void h() {
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    public boolean ad() {
        return !this.I;
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w -= (double)0.04f;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.98f;
        this.w *= (double)0.98f;
        this.x *= (double)0.98f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
            this.w *= -0.5;
        }
        if (this.a-- <= 0) {
            this.J();
            if (!this.o.D) {
                this.l();
            }
        } else {
            this.W();
            this.o.a(cy.l, this.s, this.t + 0.5, this.u, 0.0, 0.0, 0.0, new int[0]);
        }
    }

    private void l() {
        float f2 = 4.0f;
        this.o.a(this, this.s, this.t + (double)(this.K / 16.0f), this.u, f2, true);
    }

    @Override
    protected void b(dn dn2) {
        dn2.a("Fuse", (byte)this.a);
    }

    @Override
    protected void a(dn dn2) {
        this.a = dn2.d("Fuse");
    }

    public pr j() {
        return this.b;
    }

    @Override
    public float aS() {
        return 0.0f;
    }
}

