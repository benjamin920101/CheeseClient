/*
 * Decompiled with CFR 0.152.
 */
public class bbj
extends bbo {
    public bct e;
    public bct f;
    public bct g;
    public bct h;
    public bct i;
    public bct j;
    public bct k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;

    public bbj() {
        this(0.0f);
    }

    public bbj(float f2) {
        this(f2, 0.0f, 64, 32);
    }

    public bbj(float f2, float f3, int n2, int n3) {
        this.t = n2;
        this.u = n3;
        this.e = new bct(this, 0, 0);
        this.e.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2);
        this.e.a(0.0f, 0.0f + f3, 0.0f);
        this.f = new bct(this, 32, 0);
        this.f.a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f2 + 0.5f);
        this.f.a(0.0f, 0.0f + f3, 0.0f);
        this.g = new bct(this, 16, 16);
        this.g.a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f2);
        this.g.a(0.0f, 0.0f + f3, 0.0f);
        this.h = new bct(this, 40, 16);
        this.h.a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f2);
        this.h.a(-5.0f, 2.0f + f3, 0.0f);
        this.i = new bct(this, 40, 16);
        this.i.i = true;
        this.i.a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f2);
        this.i.a(5.0f, 2.0f + f3, 0.0f);
        this.j = new bct(this, 0, 16);
        this.j.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2);
        this.j.a(-1.9f, 12.0f + f3, 0.0f);
        this.k = new bct(this, 0, 16);
        this.k.i = true;
        this.k.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f2);
        this.k.a(1.9f, 12.0f + f3, 0.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        bfl.E();
        if (this.r) {
            \u2603 = 2.0f;
            bfl.a(1.5f / \u2603, 1.5f / \u2603, 1.5f / \u2603);
            bfl.b(0.0f, 16.0f * f7, 0.0f);
            this.e.a(f7);
            bfl.F();
            bfl.E();
            bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
            bfl.b(0.0f, 24.0f * f7, 0.0f);
            this.g.a(f7);
            this.h.a(f7);
            this.i.a(f7);
            this.j.a(f7);
            this.k.a(f7);
            this.f.a(f7);
        } else {
            if (pk2.av()) {
                bfl.b(0.0f, 0.2f, 0.0f);
            }
            this.e.a(f7);
            this.g.a(f7);
            this.h.a(f7);
            this.i.a(f7);
            this.j.a(f7);
            this.k.a(f7);
            this.f.a(f7);
        }
        bfl.F();
    }

    @Override
    public void a(float f2, float f3, float f42, float f5, float f6, float f7, pk pk2) {
        float f42;
        this.e.g = f5 / 57.295776f;
        this.e.f = f6 / 57.295776f;
        this.h.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 2.0f * f3 * 0.5f;
        this.i.f = ns.b(f2 * 0.6662f) * 2.0f * f3 * 0.5f;
        this.h.h = 0.0f;
        this.i.h = 0.0f;
        this.j.f = ns.b(f2 * 0.6662f) * 1.4f * f3;
        this.k.f = ns.b(f2 * 0.6662f + (float)Math.PI) * 1.4f * f3;
        this.j.g = 0.0f;
        this.k.g = 0.0f;
        if (this.q) {
            this.h.f += -0.62831855f;
            this.i.f += -0.62831855f;
            this.j.f = -1.2566371f;
            this.k.f = -1.2566371f;
            this.j.g = 0.31415927f;
            this.k.g = -0.31415927f;
        }
        if (this.l != 0) {
            this.i.f = this.i.f * 0.5f - 0.31415927f * (float)this.l;
        }
        this.h.g = 0.0f;
        this.h.h = 0.0f;
        switch (this.m) {
            case 0: {
                break;
            }
            case 3: {
                this.h.f = this.h.f * 0.5f - 0.31415927f * (float)this.m;
                this.h.g = -0.5235988f;
                break;
            }
            case 1: {
                this.h.f = this.h.f * 0.5f - 0.31415927f * (float)this.m;
            }
        }
        this.i.g = 0.0f;
        if (this.p > -9990.0f) {
            float f8 = this.p;
            this.g.g = ns.a(ns.c(f8) * (float)Math.PI * 2.0f) * 0.2f;
            this.h.e = ns.a(this.g.g) * 5.0f;
            this.h.c = -ns.b(this.g.g) * 5.0f;
            this.i.e = -ns.a(this.g.g) * 5.0f;
            this.i.c = ns.b(this.g.g) * 5.0f;
            this.h.g += this.g.g;
            this.i.g += this.g.g;
            this.i.f += this.g.g;
            f8 = 1.0f - this.p;
            f8 *= f8;
            f8 *= f8;
            f8 = 1.0f - f8;
            \u2603 = ns.a(f8 * (float)Math.PI);
            \u2603 = ns.a(this.p * (float)Math.PI) * -(this.e.f - 0.7f) * 0.75f;
            this.h.f = (float)((double)this.h.f - ((double)\u2603 * 1.2 + (double)\u2603));
            this.h.g += this.g.g * 2.0f;
            this.h.h += ns.a(this.p * (float)Math.PI) * -0.4f;
        }
        if (this.n) {
            this.g.f = 0.5f;
            this.h.f += 0.4f;
            this.i.f += 0.4f;
            this.j.e = 4.0f;
            this.k.e = 4.0f;
            this.j.d = 9.0f;
            this.k.d = 9.0f;
            this.e.d = 1.0f;
        } else {
            this.g.f = 0.0f;
            this.j.e = 0.1f;
            this.k.e = 0.1f;
            this.j.d = 12.0f;
            this.k.d = 12.0f;
            this.e.d = 0.0f;
        }
        this.h.h += ns.b(f42 * 0.09f) * 0.05f + 0.05f;
        this.i.h -= ns.b(f42 * 0.09f) * 0.05f + 0.05f;
        this.h.f += ns.a(f42 * 0.067f) * 0.05f;
        this.i.f -= ns.a(f42 * 0.067f) * 0.05f;
        if (this.o) {
            f8 = 0.0f;
            \u2603 = 0.0f;
            this.h.h = 0.0f;
            this.i.h = 0.0f;
            this.h.g = -(0.1f - f8 * 0.6f) + this.e.g;
            this.i.g = 0.1f - f8 * 0.6f + this.e.g + 0.4f;
            this.h.f = -1.5707964f + this.e.f;
            this.i.f = -1.5707964f + this.e.f;
            this.h.f -= f8 * 1.2f - \u2603 * 0.4f;
            this.i.f -= f8 * 1.2f - \u2603 * 0.4f;
            this.h.h += ns.b(f42 * 0.09f) * 0.05f + 0.05f;
            this.i.h -= ns.b(f42 * 0.09f) * 0.05f + 0.05f;
            this.h.f += ns.a(f42 * 0.067f) * 0.05f;
            this.i.f -= ns.a(f42 * 0.067f) * 0.05f;
        }
        bbj.a(this.e, this.f);
    }

    @Override
    public void a(bbo bbo2) {
        super.a(bbo2);
        if (bbo2 instanceof bbj) {
            bbj bbj2 = (bbj)bbo2;
            this.l = bbj2.l;
            this.m = bbj2.m;
            this.n = bbj2.n;
            this.o = bbj2.o;
        }
    }

    public void a(boolean bl2) {
        this.e.j = bl2;
        this.f.j = bl2;
        this.g.j = bl2;
        this.h.j = bl2;
        this.i.j = bl2;
        this.j.j = bl2;
        this.k.j = bl2;
    }

    public void a(float f2) {
        this.h.c(f2);
    }
}

