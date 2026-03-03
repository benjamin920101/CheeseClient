/*
 * Decompiled with CFR 0.152.
 */
public class xd
extends ws {
    public xd(adm adm2) {
        super(adm2);
        this.a(0.3125f, 0.3125f);
    }

    public xd(adm adm2, pr pr2, double d2, double d3, double d4) {
        super(adm2, pr2, d2, d3, d4);
        this.a(0.3125f, 0.3125f);
    }

    @Override
    protected float j() {
        return this.l() ? 0.73f : super.j();
    }

    public xd(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.a(0.3125f, 0.3125f);
    }

    @Override
    public boolean at() {
        return false;
    }

    @Override
    public float a(adi adi2, adm adm2, cj cj2, alz alz2) {
        float f2 = super.a(adi2, adm2, cj2, alz2);
        afh \u26032 = alz2.c();
        if (this.l() && uk.a(\u26032)) {
            f2 = Math.min(0.8f, f2);
        }
        return f2;
    }

    @Override
    protected void a(auh auh2) {
        if (!this.o.D) {
            if (auh2.d != null) {
                if (this.a != null) {
                    if (auh2.d.a(ow.a(this.a), 8.0f)) {
                        if (!auh2.d.ai()) {
                            this.a.h(5.0f);
                        } else {
                            this.a(this.a, auh2.d);
                        }
                    }
                } else {
                    auh2.d.a(ow.l, 5.0f);
                }
                if (auh2.d instanceof pr) {
                    int n2 = 0;
                    if (this.o.aa() == oj.c) {
                        n2 = 10;
                    } else if (this.o.aa() == oj.d) {
                        n2 = 40;
                    }
                    if (n2 > 0) {
                        ((pr)auh2.d).c(new pf(pe.v.H, 20 * n2, 1));
                    }
                }
            }
            this.o.a(this, this.s, this.t, this.u, 1.0f, false, this.o.Q().b("mobGriefing"));
            this.J();
        }
    }

    @Override
    public boolean ad() {
        return false;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        return false;
    }

    @Override
    protected void h() {
        this.ac.a(10, Byte.valueOf((byte)0));
    }

    public boolean l() {
        return this.ac.a(10) == 1;
    }

    public void a(boolean bl2) {
        this.ac.b(10, bl2 ? (byte)1 : 0);
    }
}

