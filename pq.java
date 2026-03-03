/*
 * Decompiled with CFR 0.152.
 */
public abstract class pq
extends ps {
    public pq(adm adm2) {
        super(adm2);
    }

    @Override
    public void e(float f2, float f3) {
    }

    @Override
    protected void a(double d2, boolean bl2, afh afh2, cj cj2) {
    }

    @Override
    public void g(float f2, float f3) {
        if (this.V()) {
            this.a(f2, f3, 0.02f);
            this.d(this.v, this.w, this.x);
            this.v *= (double)0.8f;
            this.w *= (double)0.8f;
            this.x *= (double)0.8f;
        } else if (this.ab()) {
            this.a(f2, f3, 0.02f);
            this.d(this.v, this.w, this.x);
            this.v *= 0.5;
            this.w *= 0.5;
            this.x *= 0.5;
        } else {
            \u2603 = 0.91f;
            if (this.C) {
                \u2603 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.91f;
            }
            \u2603 = 0.16277136f / (\u2603 * \u2603 * \u2603);
            this.a(f2, f3, this.C ? 0.1f * \u2603 : 0.02f);
            \u2603 = 0.91f;
            if (this.C) {
                \u2603 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.91f;
            }
            this.d(this.v, this.w, this.x);
            this.v *= (double)\u2603;
            this.w *= (double)\u2603;
            this.x *= (double)\u2603;
        }
        this.aA = this.aB;
        double d2 = this.s - this.p;
        \u2603 = this.u - this.r;
        float \u26032 = ns.a(d2 * d2 + \u2603 * \u2603) * 4.0f;
        if (\u26032 > 1.0f) {
            \u26032 = 1.0f;
        }
        this.aB += (\u26032 - this.aB) * 0.4f;
        this.aC += this.aB;
    }

    @Override
    public boolean k_() {
        return false;
    }
}

