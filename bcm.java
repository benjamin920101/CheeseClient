/*
 * Decompiled with CFR 0.152.
 */
public class bcm
extends bbo {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct e;
    public bct f;
    bct g;
    bct h;

    public bcm() {
        float f2 = 0.0f;
        \u2603 = 13.5f;
        this.a = new bct(this, 0, 0);
        this.a.a(-3.0f, -3.0f, -2.0f, 6, 6, 4, f2);
        this.a.a(-1.0f, \u2603, -7.0f);
        this.b = new bct(this, 18, 14);
        this.b.a(-4.0f, -2.0f, -3.0f, 6, 9, 6, f2);
        this.b.a(0.0f, 14.0f, 2.0f);
        this.h = new bct(this, 21, 0);
        this.h.a(-4.0f, -3.0f, -3.0f, 8, 6, 7, f2);
        this.h.a(-1.0f, 14.0f, 2.0f);
        this.c = new bct(this, 0, 18);
        this.c.a(-1.0f, 0.0f, -1.0f, 2, 8, 2, f2);
        this.c.a(-2.5f, 16.0f, 7.0f);
        this.d = new bct(this, 0, 18);
        this.d.a(-1.0f, 0.0f, -1.0f, 2, 8, 2, f2);
        this.d.a(0.5f, 16.0f, 7.0f);
        this.e = new bct(this, 0, 18);
        this.e.a(-1.0f, 0.0f, -1.0f, 2, 8, 2, f2);
        this.e.a(-2.5f, 16.0f, -4.0f);
        this.f = new bct(this, 0, 18);
        this.f.a(-1.0f, 0.0f, -1.0f, 2, 8, 2, f2);
        this.f.a(0.5f, 16.0f, -4.0f);
        this.g = new bct(this, 9, 18);
        this.g.a(-1.0f, 0.0f, -1.0f, 2, 8, 2, f2);
        this.g.a(-1.0f, 12.0f, 8.0f);
        this.a.a(16, 14).a(-3.0f, -5.0f, 0.0f, 2, 2, 1, f2);
        this.a.a(16, 14).a(1.0f, -5.0f, 0.0f, 2, 2, 1, f2);
        this.a.a(0, 10).a(-1.5f, 0.0f, -5.0f, 3, 3, 4, f2);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.a(pk2, f2, f3, f4, f5, f6, f7);
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        if (this.r) {
            \u2603 = 2.0f;
            bfl.E();
            bfl.b(0.0f, 5.0f * f7, 2.0f * f7);
            this.a.b(f7);
            bfl.F();
            bfl.E();
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            this.g.b(f7);
            this.h.a(f7);
            bfl.F();
        } else {
            this.a.b(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
            this.e.a(f7);
            this.f.a(f7);
            this.g.b(f7);
            this.h.a(f7);
        }
    }

    @Override
    public void a(pr pr2, float f2, float f3, float f4) {
        ua ua2 = (ua)pr2;
        this.g.g = ua2.cv() ? 0.0f : ns.b(f2 * 0.6662f) * 1.4f * f3;
        if (ua2.cn()) {
            this.h.a(-1.0f, 16.0f, -3.0f);
            this.h.f = 1.2566371f;
            this.h.g = 0.0f;
            this.b.a(0.0f, 18.0f, 0.0f);
            this.b.f = 0.7853982f;
            this.g.a(-1.0f, 21.0f, 6.0f);
            this.c.a(-2.5f, 22.0f, 2.0f);
            this.c.f = 4.712389f;
            this.d.a(0.5f, 22.0f, 2.0f);
            this.d.f = 4.712389f;
            this.e.f = 5.811947f;
            this.e.a(-2.49f, 17.0f, -4.0f);
            this.f.f = 5.811947f;
            this.f.a(0.51f, 17.0f, -4.0f);
        } else {
            this.b.a(0.0f, 14.0f, 2.0f);
            this.b.f = 1.5707964f;
            this.h.a(-1.0f, 14.0f, -3.0f);
            this.h.f = this.b.f;
            this.g.a(-1.0f, 12.0f, 8.0f);
            this.c.a(-2.5f, 16.0f, 7.0f);
            this.d.a(0.5f, 16.0f, 7.0f);
            this.e.a(-2.5f, 16.0f, -4.0f);
            this.f.a(0.5f, 16.0f, -4.0f);
            this.c.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
            this.d.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
            this.e.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
            this.f.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
        }
        this.a.h = ua2.q(f4) + ua2.i(f4, 0.0f);
        this.h.h = ua2.i(f4, -0.08f);
        this.b.h = ua2.i(f4, -0.16f);
        this.g.h = ua2.i(f4, -0.2f);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.f = f6 / 57.295776f;
        this.a.g = f5 / 57.295776f;
        this.g.f = f4;
    }
}

