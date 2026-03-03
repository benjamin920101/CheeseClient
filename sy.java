/*
 * Decompiled with CFR 0.152.
 */
public class sy
extends sw {
    public sy(ps ps2, adm adm2) {
        super(ps2, adm2);
    }

    @Override
    protected asy a() {
        return new asy(new asz());
    }

    @Override
    protected boolean b() {
        return this.o();
    }

    @Override
    protected aui c() {
        return new aui(this.b.s, this.b.t + (double)this.b.K * 0.5, this.b.u);
    }

    @Override
    protected void l() {
        aui aui2 = this.c();
        float \u26032 = this.b.J * this.b.J;
        int \u26033 = 6;
        if (aui2.g(this.d.a(this.b, this.d.e())) < (double)\u26032) {
            this.d.a();
        }
        for (int i2 = Math.min(this.d.e() + \u26033, this.d.d() - 1); i2 > this.d.e(); --i2) {
            aui aui3 = this.d.a(this.b, i2);
            if (aui3.g(aui2) > 36.0 || !this.a(aui2, aui3, 0, 0, 0)) continue;
            this.d.c(i2);
            break;
        }
        this.a(aui2);
    }

    @Override
    protected void d() {
        super.d();
    }

    @Override
    protected boolean a(aui aui2, aui aui3, int n2, int n3, int n4) {
        auh auh2 = this.c.a(aui2, new aui(aui3.a, aui3.b + (double)this.b.K * 0.5, aui3.c), false, true, false);
        return auh2 == null || auh2.a == auh.a.a;
    }
}

