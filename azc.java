/*
 * Decompiled with CFR 0.152.
 */
public class azc
extends ayw {
    private float u;
    private float v;

    public azc(wn wn2) {
        super(wn2.bj);
        this.p = true;
    }

    @Override
    public void e() {
        if (this.j.c.h()) {
            this.j.a(new ayu(this.j.h));
        }
        this.a();
    }

    @Override
    public void b() {
        this.n.clear();
        if (this.j.c.h()) {
            this.j.a(new ayu(this.j.h));
        } else {
            super.b();
        }
    }

    @Override
    protected void b(int n2, int n3) {
        this.q.a(bnq.a("container.crafting", new Object[0]), 86, 16, 0x404040);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        super.a(n2, n3, f2);
        this.u = n2;
        this.v = n3;
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(a);
        \u2603 = this.i;
        \u2603 = this.r;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        azc.a(\u2603 + 51, \u2603 + 75, 30, (float)(\u2603 + 51) - this.u, (float)(\u2603 + 75 - 50) - this.v, this.j.h);
    }

    public static void a(int n2, int n3, int n4, float f2, float f3, pr pr2) {
        bfl.g();
        bfl.E();
        bfl.b(n2, n3, 50.0f);
        bfl.a(-n4, (float)n4, n4);
        bfl.b(180.0f, 0.0f, 0.0f, 1.0f);
        float f4 = pr2.aI;
        \u2603 = pr2.y;
        \u2603 = pr2.z;
        \u2603 = pr2.aL;
        \u2603 = pr2.aK;
        bfl.b(135.0f, 0.0f, 1.0f, 0.0f);
        avc.b();
        bfl.b(-135.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(-((float)Math.atan(f3 / 40.0f)) * 20.0f, 1.0f, 0.0f, 0.0f);
        pr2.aI = (float)Math.atan(f2 / 40.0f) * 20.0f;
        pr2.y = (float)Math.atan(f2 / 40.0f) * 40.0f;
        pr2.z = -((float)Math.atan(f3 / 40.0f)) * 20.0f;
        pr2.aK = pr2.y;
        pr2.aL = pr2.y;
        bfl.b(0.0f, 0.0f, 0.0f);
        biu \u26032 = ave.A().af();
        \u26032.a(180.0f);
        \u26032.a(false);
        \u26032.a(pr2, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        \u26032.a(true);
        pr2.aI = f4;
        pr2.y = \u2603;
        pr2.z = \u2603;
        pr2.aL = \u2603;
        pr2.aK = \u2603;
        bfl.F();
        avc.a();
        bfl.C();
        bfl.g(bqs.r);
        bfl.x();
        bfl.g(bqs.q);
    }

    @Override
    protected void a(avs avs2) {
        if (avs2.k == 0) {
            this.j.a(new aye(this, this.j.h.x()));
        }
        if (avs2.k == 1) {
            this.j.a(new ayf(this, this.j.h.x()));
        }
    }
}

