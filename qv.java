/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class qv
extends rd {
    private tm d;
    adm a;
    private tm e;
    int b;
    double c;

    public qv(tm tm2, double d2) {
        this.d = tm2;
        this.a = tm2.o;
        this.c = d2;
        this.a(3);
    }

    @Override
    public boolean a() {
        if (!this.d.cr()) {
            return false;
        }
        this.e = this.f();
        return this.e != null;
    }

    @Override
    public boolean b() {
        return this.e.ai() && this.e.cr() && this.b < 60;
    }

    @Override
    public void d() {
        this.e = null;
        this.b = 0;
    }

    @Override
    public void e() {
        this.d.p().a(this.e, 10.0f, (float)this.d.bQ());
        this.d.s().a(this.e, this.c);
        ++this.b;
        if (this.b >= 60 && this.d.h(this.e) < 9.0) {
            this.g();
        }
    }

    private tm f() {
        float f2 = 8.0f;
        List<?> \u26032 = this.a.a(this.d.getClass(), this.d.aR().b(f2, f2, f2));
        double \u26033 = Double.MAX_VALUE;
        tm \u26034 = null;
        for (tm tm2 : \u26032) {
            if (!this.d.a(tm2) || !(this.d.h(tm2) < \u26033)) continue;
            \u26034 = tm2;
            \u26033 = this.d.h(tm2);
        }
        return \u26034;
    }

    private void g() {
        ph ph2 = this.d.a((ph)this.e);
        if (ph2 == null) {
            return;
        }
        wn \u26032 = this.d.cq();
        if (\u26032 == null && this.e.cq() != null) {
            \u26032 = this.e.cq();
        }
        if (\u26032 != null) {
            \u26032.b(na.A);
            if (this.d instanceof to) {
                \u26032.b(mr.H);
            }
        }
        this.d.b(6000);
        this.e.b(6000);
        this.d.cs();
        this.e.cs();
        ph2.b(-24000);
        ph2.b(this.d.s, this.d.t, this.d.u, 0.0f, 0.0f);
        this.a.d(ph2);
        Random \u26033 = this.d.bc();
        for (int i2 = 0; i2 < 7; ++i2) {
            double d2 = \u26033.nextGaussian() * 0.02;
            \u2603 = \u26033.nextGaussian() * 0.02;
            \u2603 = \u26033.nextGaussian() * 0.02;
            \u2603 = \u26033.nextDouble() * (double)this.d.J * 2.0 - (double)this.d.J;
            \u2603 = 0.5 + \u26033.nextDouble() * (double)this.d.K;
            \u2603 = \u26033.nextDouble() * (double)this.d.J * 2.0 - (double)this.d.J;
            this.a.a(cy.I, this.d.s + \u2603, this.d.t + \u2603, this.d.u + \u2603, d2, \u2603, \u2603, new int[0]);
        }
        if (this.a.Q().b("doMobLoot")) {
            this.a.d(new pp(this.a, this.d.s, this.d.t, this.d.u, \u26033.nextInt(7) + 1));
        }
    }
}

