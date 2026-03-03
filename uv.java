/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class uv
extends uu {
    private int b;
    public long a;
    private int c;

    public uv(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.b(d2, d3, d4, 0.0f, 0.0f);
        this.b = 2;
        this.a = this.V.nextLong();
        this.c = this.V.nextInt(3) + 1;
        cj cj2 = new cj(this);
        if (!adm2.D && adm2.Q().b("doFireTick") && (adm2.aa() == oj.c || adm2.aa() == oj.d) && adm2.a(cj2, 10)) {
            if (adm2.p(cj2).c().t() == arm.a && afi.ab.d(adm2, cj2)) {
                adm2.a(cj2, afi.ab.Q());
            }
            for (int i2 = 0; i2 < 4; ++i2) {
                cj cj3 = cj2.a(this.V.nextInt(3) - 1, this.V.nextInt(3) - 1, this.V.nextInt(3) - 1);
                if (adm2.p(cj3).c().t() != arm.a || !afi.ab.d(adm2, cj3)) continue;
                adm2.a(cj3, afi.ab.Q());
            }
        }
    }

    @Override
    public void t_() {
        super.t_();
        if (this.b == 2) {
            this.o.a(this.s, this.t, this.u, "ambient.weather.thunder", 10000.0f, 0.8f + this.V.nextFloat() * 0.2f);
            this.o.a(this.s, this.t, this.u, "random.explode", 2.0f, 0.5f + this.V.nextFloat() * 0.2f);
        }
        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.J();
            } else if (this.b < -this.V.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.V.nextLong();
                cj cj2 = new cj(this);
                if (!this.o.D && this.o.Q().b("doFireTick") && this.o.a(cj2, 10) && this.o.p(cj2).c().t() == arm.a && afi.ab.d(this.o, cj2)) {
                    this.o.a(cj2, afi.ab.Q());
                }
            }
        }
        if (this.b >= 0) {
            if (this.o.D) {
                this.o.d(2);
            } else {
                double d2 = 3.0;
                List<pk> \u26032 = this.o.b(this, new aug(this.s - d2, this.t - d2, this.u - d2, this.s + d2, this.t + 6.0 + d2, this.u + d2));
                for (int i2 = 0; i2 < \u26032.size(); ++i2) {
                    pk pk2 = \u26032.get(i2);
                    pk2.a(this);
                }
            }
        }
    }

    @Override
    protected void h() {
    }

    @Override
    protected void a(dn dn2) {
    }

    @Override
    protected void b(dn dn2) {
    }
}

