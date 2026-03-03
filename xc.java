/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class xc
extends wy {
    private zx c;

    public xc(adm adm2) {
        super(adm2);
    }

    public xc(adm adm2, pr pr2, int n2) {
        this(adm2, pr2, new zx(zy.bz, 1, n2));
    }

    public xc(adm adm2, pr pr2, zx zx2) {
        super(adm2, pr2);
        this.c = zx2;
    }

    public xc(adm adm2, double d2, double d3, double d4, int n2) {
        this(adm2, d2, d3, d4, new zx(zy.bz, 1, n2));
    }

    public xc(adm adm2, double d2, double d3, double d4, zx zx2) {
        super(adm2, d2, d3, d4);
        this.c = zx2;
    }

    @Override
    protected float m() {
        return 0.05f;
    }

    @Override
    protected float j() {
        return 0.5f;
    }

    @Override
    protected float l() {
        return -20.0f;
    }

    public void a(int n2) {
        if (this.c == null) {
            this.c = new zx(zy.bz, 1, 0);
        }
        this.c.b(n2);
    }

    public int o() {
        if (this.c == null) {
            this.c = new zx(zy.bz, 1, 0);
        }
        return this.c.i();
    }

    @Override
    protected void a(auh auh2) {
        if (!this.o.D) {
            List<pf> list = zy.bz.h(this.c);
            if (list != null && !list.isEmpty() && !(\u2603 = this.o.a(pr.class, \u2603 = this.aR().b(4.0, 2.0, 4.0))).isEmpty()) {
                for (pr pr2 : \u2603) {
                    double d2 = this.h(pr2);
                    if (!(d2 < 16.0)) continue;
                    \u2603 = 1.0 - Math.sqrt(d2) / 4.0;
                    if (pr2 == auh2.d) {
                        \u2603 = 1.0;
                    }
                    for (pf pf2 : list) {
                        int n2 = pf2.a();
                        if (pe.a[n2].b()) {
                            pe.a[n2].a(this, this.n(), pr2, pf2.c(), \u2603);
                            continue;
                        }
                        \u2603 = (int)(\u2603 * (double)pf2.b() + 0.5);
                        if (\u2603 <= 20) continue;
                        pr2.c(new pf(n2, \u2603, pf2.c()));
                    }
                }
            }
            this.o.b(2002, new cj(this), this.o());
            this.J();
        }
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        if (dn2.b("Potion", 10)) {
            this.c = zx.a(dn2.m("Potion"));
        } else {
            this.a(dn2.f("potionValue"));
        }
        if (this.c == null) {
            this.J();
        }
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        if (this.c != null) {
            dn2.a("Potion", this.c.b(new dn()));
        }
    }
}

