/*
 * Decompiled with CFR 0.152.
 */
public class sx
extends sv {
    private cj f;

    public sx(ps ps2, adm adm2) {
        super(ps2, adm2);
    }

    @Override
    public asx a(cj cj2) {
        this.f = cj2;
        return super.a(cj2);
    }

    @Override
    public asx a(pk pk2) {
        this.f = new cj(pk2);
        return super.a(pk2);
    }

    @Override
    public boolean a(pk pk2, double d2) {
        asx asx2 = this.a(pk2);
        if (asx2 != null) {
            return this.a(asx2, d2);
        }
        this.f = new cj(pk2);
        this.e = d2;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void k() {
        block5: {
            block4: {
                if (!this.m()) {
                    super.k();
                    return;
                }
                if (this.f == null) return;
                double d2 = this.b.J * this.b.J;
                if (this.b.c(this.f) < d2) break block4;
                if (!(this.b.t > (double)this.f.o())) break block5;
                cj cj2 = new cj(this.f.n(), ns.c(this.b.t), this.f.p());
                if (!(this.b.c(cj2) < d2)) break block5;
            }
            this.f = null;
            return;
        }
        this.b.q().a(this.f.n(), this.f.o(), this.f.p(), this.e);
    }
}

