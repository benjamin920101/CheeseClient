/*
 * Decompiled with CFR 0.152.
 */
public class bbr
extends bbj {
    public bct a;
    public bct b;
    public bct c;
    public bct d;
    public bct v;
    private bct w;
    private bct x;
    private boolean y;

    public bbr(float f2, boolean bl2) {
        super(f2, 0.0f, 64, 64);
        this.y = bl2;
        this.x = new bct(this, 24, 0);
        this.x.a(-3.0f, -6.0f, -1.0f, 6, 6, 1, f2);
        this.w = new bct(this, 0, 0);
        this.w.b(64, 32);
        this.w.a(-5.0f, 0.0f, -1.0f, 10, 16, 1, f2);
        if (bl2) {
            this.i = new bct(this, 32, 48);
            this.i.a(-1.0f, -2.0f, -2.0f, 3, 12, 4, f2);
            this.i.a(5.0f, 2.5f, 0.0f);
            this.h = new bct(this, 40, 16);
            this.h.a(-2.0f, -2.0f, -2.0f, 3, 12, 4, f2);
            this.h.a(-5.0f, 2.5f, 0.0f);
            this.a = new bct(this, 48, 48);
            this.a.a(-1.0f, -2.0f, -2.0f, 3, 12, 4, f2 + 0.25f);
            this.a.a(5.0f, 2.5f, 0.0f);
            this.b = new bct(this, 40, 32);
            this.b.a(-2.0f, -2.0f, -2.0f, 3, 12, 4, f2 + 0.25f);
            this.b.a(-5.0f, 2.5f, 10.0f);
        } else {
            this.i = new bct(this, 32, 48);
            this.i.a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f2);
            this.i.a(5.0f, 2.0f, 0.0f);
            this.a = new bct(this, 48, 48);
            this.a.a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f2 + 0.25f);
            this.a.a(5.0f, 2.0f, 0.0f);
            this.b = new bct(this, 40, 32);
            this.b.a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f2 + 0.25f);
            this.b.a(-5.0f, 2.0f, 10.0f);
        }
        this.k = new bct(this, 16, 48);
        this.k.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2);
        this.k.a(1.9f, 12.0f, 0.0f);
        this.c = new bct(this, 0, 48);
        this.c.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2 + 0.25f);
        this.c.a(1.9f, 12.0f, 0.0f);
        this.d = new bct(this, 0, 32);
        this.d.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2 + 0.25f);
        this.d.a(-1.9f, 12.0f, 0.0f);
        this.v = new bct(this, 16, 32);
        this.v.a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f2 + 0.25f);
        this.v.a(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.a(pk2, f2, f3, f4, f5, f6, f7);
        bfl.E();
        if (this.r) {
            \u2603 = 2.0f;
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.c.a(f7);
            this.d.a(f7);
            this.a.a(f7);
            this.b.a(f7);
            this.v.a(f7);
        } else {
            if (pk2.av()) {
                bfl.b(0.0f, 0.2f, 0.0f);
            }
            this.c.a(f7);
            this.d.a(f7);
            this.a.a(f7);
            this.b.a(f7);
            this.v.a(f7);
        }
        bfl.F();
    }

    public void b(float f2) {
        bbr.a(this.e, this.x);
        this.x.c = 0.0f;
        this.x.d = 0.0f;
        this.x.a(f2);
    }

    public void c(float f2) {
        this.w.a(f2);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        bbr.a(this.k, this.c);
        bbr.a(this.j, this.d);
        bbr.a(this.i, this.a);
        bbr.a(this.h, this.b);
        bbr.a(this.g, this.v);
        this.w.d = pk2.av() ? 2.0f : 0.0f;
    }

    public void a() {
        this.h.a(0.0625f);
        this.b.a(0.0625f);
    }

    public void b() {
        this.i.a(0.0625f);
        this.a.a(0.0625f);
    }

    @Override
    public void a(boolean bl2) {
        super.a(bl2);
        this.a.j = bl2;
        this.b.j = bl2;
        this.c.j = bl2;
        this.d.j = bl2;
        this.v.j = bl2;
        this.w.j = bl2;
        this.x.j = bl2;
    }

    @Override
    public void a(float f2) {
        if (this.y) {
            this.h.c += 1.0f;
            this.h.c(f2);
            this.h.c -= 1.0f;
        } else {
            this.h.c(f2);
        }
    }
}

