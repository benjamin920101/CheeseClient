/*
 * Decompiled with CFR 0.152.
 */
public class tw
extends tq
implements vx {
    public tw(adm adm2) {
        super(adm2);
        this.a(0.7f, 1.9f);
        ((sv)this.s()).a(true);
        this.i.a(1, new sa(this, 1.25, 20, 10.0f));
        this.i.a(2, new rz(this, 1.0));
        this.i.a(3, new ri(this, wn.class, 6.0f));
        this.i.a(4, new ry(this));
        this.bi.a(1, new sp<pk>(this, ps.class, 10, true, false, vq.d));
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(4.0);
        this.a(vy.d).a(0.2f);
    }

    @Override
    public void m() {
        super.m();
        if (!this.o.D) {
            int n2 = ns.c(this.s);
            \u2603 = ns.c(this.t);
            \u2603 = ns.c(this.u);
            if (this.U()) {
                this.a(ow.f, 1.0f);
            }
            cj cj2 = new cj(n2, 0, \u2603);
            cj cj3 = new cj(n2, \u2603, \u2603);
            if (this.o.b(cj2).a(cj3) > 1.0f) {
                this.a(ow.c, 1.0f);
            }
            for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                n2 = ns.c(this.s + (double)((float)(\u2603 % 2 * 2 - 1) * 0.25f));
                cj cj4 = new cj(n2, \u2603 = ns.c(this.t), \u2603 = ns.c(this.u + (double)((float)(\u2603 / 2 % 2 * 2 - 1) * 0.25f)));
                if (this.o.p(cj4).c().t() != arm.a) continue;
                cj cj5 = new cj(n2, 0, \u2603);
                if (!(this.o.b(cj5).a(cj4) < 0.8f) || !afi.aH.d(this.o, cj4)) continue;
                this.o.a(cj4, afi.aH.Q());
            }
        }
    }

    @Override
    protected zw A() {
        return zy.aD;
    }

    @Override
    protected void b(boolean bl2, int n2) {
        \u2603 = this.V.nextInt(16);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            this.a(zy.aD, 1);
        }
    }

    @Override
    public void a(pr pr2, float f2) {
        wx wx2 = new wx(this.o, this);
        double \u26032 = pr2.t + (double)pr2.aS() - (double)1.1f;
        double \u26033 = pr2.s - this.s;
        double \u26034 = \u26032 - wx2.t;
        double \u26035 = pr2.u - this.u;
        float \u26036 = ns.a(\u26033 * \u26033 + \u26035 * \u26035) * 0.2f;
        wx2.c(\u26033, \u26034 + (double)\u26036, \u26035, 1.6f, 12.0f);
        this.a("random.bow", 1.0f, 1.0f / (this.bc().nextFloat() * 0.4f + 0.8f));
        this.o.d(wx2);
    }

    @Override
    public float aS() {
        return 1.7f;
    }
}

