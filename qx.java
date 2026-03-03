/*
 * Decompiled with CFR 0.152.
 */
public abstract class qx
extends rd {
    protected ps a;
    protected cj b = cj.a;
    protected agh c;
    boolean d;
    float e;
    float f;

    public qx(ps ps2) {
        this.a = ps2;
        if (!(ps2.s() instanceof sv)) {
            throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
        }
    }

    @Override
    public boolean a() {
        if (!this.a.D) {
            return false;
        }
        sv sv2 = (sv)this.a.s();
        asx \u26032 = sv2.j();
        if (\u26032 == null || \u26032.b() || !sv2.g()) {
            return false;
        }
        for (int i2 = 0; i2 < Math.min(\u26032.e() + 2, \u26032.d()); ++i2) {
            asv asv2 = \u26032.a(i2);
            this.b = new cj(asv2.a, asv2.b + 1, asv2.c);
            if (this.a.e(this.b.n(), this.a.t, this.b.p()) > 2.25) continue;
            this.c = this.a(this.b);
            if (this.c == null) continue;
            return true;
        }
        this.b = new cj(this.a).a();
        this.c = this.a(this.b);
        return this.c != null;
    }

    @Override
    public boolean b() {
        return !this.d;
    }

    @Override
    public void c() {
        this.d = false;
        this.e = (float)((double)((float)this.b.n() + 0.5f) - this.a.s);
        this.f = (float)((double)((float)this.b.p() + 0.5f) - this.a.u);
    }

    @Override
    public void e() {
        float f2 = (float)((double)((float)this.b.n() + 0.5f) - this.a.s);
        \u2603 = this.e * f2 + this.f * (\u2603 = (float)((double)((float)this.b.p() + 0.5f) - this.a.u));
        if (\u2603 < 0.0f) {
            this.d = true;
        }
    }

    private agh a(cj cj2) {
        afh afh2 = this.a.o.p(cj2).c();
        if (afh2 instanceof agh && afh2.t() == arm.d) {
            return (agh)afh2;
        }
        return null;
    }
}

