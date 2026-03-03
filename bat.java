/*
 * Decompiled with CFR 0.152.
 */
public class bat
extends bas {
    public bct a;
    public bct b;
    public bct c;
    public bct d;

    public bat() {
        this(0.0f);
    }

    public bat(float f2) {
        super(f2, 64, 64);
        this.e = new bct(this, 0, 0);
        this.e.a(-1.0f, -7.0f, -1.0f, 2, 7, 2, f2);
        this.e.a(0.0f, 0.0f, 0.0f);
        this.g = new bct(this, 0, 26);
        this.g.a(-6.0f, 0.0f, -1.5f, 12, 3, 3, f2);
        this.g.a(0.0f, 0.0f, 0.0f);
        this.h = new bct(this, 24, 0);
        this.h.a(-2.0f, -2.0f, -1.0f, 2, 12, 2, f2);
        this.h.a(-5.0f, 2.0f, 0.0f);
        this.i = new bct(this, 32, 16);
        this.i.i = true;
        this.i.a(0.0f, -2.0f, -1.0f, 2, 12, 2, f2);
        this.i.a(5.0f, 2.0f, 0.0f);
        this.j = new bct(this, 8, 0);
        this.j.a(-1.0f, 0.0f, -1.0f, 2, 11, 2, f2);
        this.j.a(-1.9f, 12.0f, 0.0f);
        this.k = new bct(this, 40, 16);
        this.k.i = true;
        this.k.a(-1.0f, 0.0f, -1.0f, 2, 11, 2, f2);
        this.k.a(1.9f, 12.0f, 0.0f);
        this.a = new bct(this, 16, 0);
        this.a.a(-3.0f, 3.0f, -1.0f, 2, 7, 2, f2);
        this.a.a(0.0f, 0.0f, 0.0f);
        this.a.j = true;
        this.b = new bct(this, 48, 16);
        this.b.a(1.0f, 3.0f, -1.0f, 2, 7, 2, f2);
        this.b.a(0.0f, 0.0f, 0.0f);
        this.c = new bct(this, 0, 48);
        this.c.a(-4.0f, 10.0f, -1.0f, 8, 2, 2, f2);
        this.c.a(0.0f, 0.0f, 0.0f);
        this.d = new bct(this, 0, 32);
        this.d.a(-6.0f, 11.0f, -6.0f, 12, 1, 12, f2);
        this.d.a(0.0f, 12.0f, 0.0f);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        if (!(pk2 instanceof um)) {
            return;
        }
        um um2 = (um)pk2;
        this.i.j = um2.q();
        this.h.j = um2.q();
        this.d.j = !um2.r();
        this.k.a(1.9f, 12.0f, 0.0f);
        this.j.a(-1.9f, 12.0f, 0.0f);
        this.a.f = (float)Math.PI / 180 * um2.u().b();
        this.a.g = (float)Math.PI / 180 * um2.u().c();
        this.a.h = (float)Math.PI / 180 * um2.u().d();
        this.b.f = (float)Math.PI / 180 * um2.u().b();
        this.b.g = (float)Math.PI / 180 * um2.u().c();
        this.b.h = (float)Math.PI / 180 * um2.u().d();
        this.c.f = (float)Math.PI / 180 * um2.u().b();
        this.c.g = (float)Math.PI / 180 * um2.u().c();
        this.c.h = (float)Math.PI / 180 * um2.u().d();
        float \u26032 = (um2.x().b() + um2.y().b()) / 2.0f;
        float \u26033 = (um2.x().c() + um2.y().c()) / 2.0f;
        float \u26034 = (um2.x().d() + um2.y().d()) / 2.0f;
        this.d.f = 0.0f;
        this.d.g = (float)Math.PI / 180 * -pk2.y;
        this.d.h = 0.0f;
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.a(pk2, f2, f3, f4, f5, f6, f7);
        bfl.E();
        if (this.r) {
            \u2603 = 2.0f;
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
        } else {
            if (pk2.av()) {
                bfl.b(0.0f, 0.2f, 0.0f);
            }
            this.a.a(f7);
            this.b.a(f7);
            this.c.a(f7);
            this.d.a(f7);
        }
        bfl.F();
    }

    @Override
    public void a(float f2) {
        boolean bl2 = this.h.j;
        this.h.j = true;
        super.a(f2);
        this.h.j = bl2;
    }
}

