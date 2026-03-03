/*
 * Decompiled with CFR 0.152.
 */
public class xa
extends wy {
    private pr c;

    public xa(adm adm2) {
        super(adm2);
    }

    public xa(adm adm2, pr pr2) {
        super(adm2, pr2);
        this.c = pr2;
    }

    public xa(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    protected void a(auh auh2) {
        pr pr2 = this.n();
        if (auh2.d != null) {
            if (auh2.d == this.c) {
                return;
            }
            auh2.d.a(ow.a(this, (pk)pr2), 0.0f);
        }
        for (int i2 = 0; i2 < 32; ++i2) {
            this.o.a(cy.y, this.s, this.t + this.V.nextDouble() * 2.0, this.u, this.V.nextGaussian(), 0.0, this.V.nextGaussian(), new int[0]);
        }
        if (!this.o.D) {
            if (pr2 instanceof lf) {
                lf lf2 = (lf)pr2;
                if (lf2.a.a().g() && lf2.o == this.o && !lf2.bJ()) {
                    if (this.V.nextFloat() < 0.05f && this.o.Q().b("doMobSpawning")) {
                        vp vp2 = new vp(this.o);
                        vp2.a(true);
                        vp2.b(pr2.s, pr2.t, pr2.u, pr2.y, pr2.z);
                        this.o.d(vp2);
                    }
                    if (pr2.au()) {
                        pr2.a((pk)null);
                    }
                    pr2.a(this.s, this.t, this.u);
                    pr2.O = 0.0f;
                    pr2.a(ow.i, 5.0f);
                }
            } else if (pr2 != null) {
                pr2.a(this.s, this.t, this.u);
                pr2.O = 0.0f;
            }
            this.J();
        }
    }

    @Override
    public void t_() {
        pr pr2 = this.n();
        if (pr2 != null && pr2 instanceof wn && !pr2.ai()) {
            this.J();
        } else {
            super.t_();
        }
    }
}

