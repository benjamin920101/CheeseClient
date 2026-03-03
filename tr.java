/*
 * Decompiled with CFR 0.152.
 */
public class tr
extends to {
    public tr(adm adm2) {
        super(adm2);
        this.a(0.9f, 1.3f);
        this.bn = afi.bw;
    }

    @Override
    public boolean a(wn wn22) {
        wn wn22;
        zx zx2 = wn22.bi.h();
        if (zx2 != null && zx2.b() == zy.z && this.l() >= 0) {
            if (zx2.b == 1) {
                wn22.bi.a(wn22.bi.c, new zx(zy.A));
                return true;
            }
            if (wn22.bi.a(new zx(zy.A)) && !wn22.bA.d) {
                wn22.bi.a(wn22.bi.c, 1);
                return true;
            }
        }
        if (zx2 != null && zx2.b() == zy.be && this.l() >= 0) {
            this.J();
            this.o.a(cy.b, this.s, this.t + (double)(this.K / 2.0f), this.u, 0.0, 0.0, 0.0, new int[0]);
            if (!this.o.D) {
                to to2 = new to(this.o);
                to2.b(this.s, this.t, this.u, this.y, this.z);
                to2.i(this.bn());
                to2.aI = this.aI;
                if (this.l_()) {
                    to2.a(this.aM());
                }
                this.o.d(to2);
                for (int i2 = 0; i2 < 5; ++i2) {
                    this.o.d(new uz(this.o, this.s, this.t + (double)this.K, this.u, new zx(afi.Q)));
                }
                zx2.a(1, (pr)wn22);
                this.a("mob.sheep.shear", 1.0f, 1.0f);
            }
            return true;
        }
        return super.a(wn22);
    }

    public tr c(ph ph2) {
        return new tr(this.o);
    }

    @Override
    public /* synthetic */ to b(ph ph2) {
        return this.c(ph2);
    }

    @Override
    public /* synthetic */ ph a(ph ph2) {
        return this.c(ph2);
    }
}

