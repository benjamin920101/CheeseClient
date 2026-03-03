/*
 * Decompiled with CFR 0.152.
 */
public class pp
extends pk {
    public int a;
    public int b;
    public int c;
    private int d = 5;
    private int e;
    private wn f;
    private int g;

    public pp(adm adm2, double d2, double d3, double d4, int n2) {
        super(adm2);
        this.a(0.5f, 0.5f);
        this.b(d2, d3, d4);
        this.y = (float)(Math.random() * 360.0);
        this.v = (float)(Math.random() * (double)0.2f - (double)0.1f) * 2.0f;
        this.w = (float)(Math.random() * 0.2) * 2.0f;
        this.x = (float)(Math.random() * (double)0.2f - (double)0.1f) * 2.0f;
        this.e = n2;
    }

    @Override
    protected boolean s_() {
        return false;
    }

    public pp(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
    }

    @Override
    protected void h() {
    }

    @Override
    public int b(float f2) {
        \u2603 = 0.5f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        int n2 = super.b(f2);
        \u2603 = n2 & 0xFF;
        \u2603 = n2 >> 16 & 0xFF;
        if ((\u2603 += (int)(\u2603 * 15.0f * 16.0f)) > 240) {
            \u2603 = 240;
        }
        return \u2603 | \u2603 << 16;
    }

    @Override
    public void t_() {
        super.t_();
        if (this.c > 0) {
            --this.c;
        }
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w -= (double)0.03f;
        if (this.o.p(new cj(this)).c().t() == arm.i) {
            this.w = 0.2f;
            this.v = (this.V.nextFloat() - this.V.nextFloat()) * 0.2f;
            this.x = (this.V.nextFloat() - this.V.nextFloat()) * 0.2f;
            this.a("random.fizz", 0.4f, 2.0f + this.V.nextFloat() * 0.4f);
        }
        this.j(this.s, (this.aR().b + this.aR().e) / 2.0, this.u);
        double d2 = 8.0;
        if (this.g < this.a - 20 + this.F() % 100) {
            if (this.f == null || this.f.h(this) > d2 * d2) {
                this.f = this.o.a((pk)this, d2);
            }
            this.g = this.a;
        }
        if (this.f != null && this.f.v()) {
            this.f = null;
        }
        if (this.f != null && (\u2603 = 1.0 - (\u2603 = Math.sqrt((\u2603 = (this.f.s - this.s) / d2) * \u2603 + (\u2603 = (this.f.t + (double)this.f.aS() - this.t) / d2) * \u2603 + (\u2603 = (this.f.u - this.u) / d2) * \u2603))) > 0.0) {
            \u2603 *= \u2603;
            this.v += \u2603 / \u2603 * \u2603 * 0.1;
            this.w += \u2603 / \u2603 * \u2603 * 0.1;
            this.x += \u2603 / \u2603 * \u2603 * 0.1;
        }
        this.d(this.v, this.w, this.x);
        float \u26032 = 0.98f;
        if (this.C) {
            \u26032 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.98f;
        }
        this.v *= (double)\u26032;
        this.w *= (double)0.98f;
        this.x *= (double)\u26032;
        if (this.C) {
            this.w *= (double)-0.9f;
        }
        ++this.a;
        ++this.b;
        if (this.b >= 6000) {
            this.J();
        }
    }

    @Override
    public boolean W() {
        return this.o.a(this.aR(), arm.h, this);
    }

    @Override
    protected void f(int n2) {
        this.a(ow.a, (float)n2);
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        this.ac();
        this.d = (int)((float)this.d - f2);
        if (this.d <= 0) {
            this.J();
        }
        return false;
    }

    @Override
    public void b(dn dn2) {
        dn2.a("Health", (short)((byte)this.d));
        dn2.a("Age", (short)this.b);
        dn2.a("Value", (short)this.e);
    }

    @Override
    public void a(dn dn2) {
        this.d = dn2.e("Health") & 0xFF;
        this.b = dn2.e("Age");
        this.e = dn2.e("Value");
    }

    @Override
    public void d(wn wn2) {
        if (this.o.D) {
            return;
        }
        if (this.c == 0 && wn2.bp == 0) {
            wn2.bp = 2;
            this.o.a((pk)wn2, "random.orb", 0.1f, 0.5f * ((this.V.nextFloat() - this.V.nextFloat()) * 0.7f + 1.8f));
            wn2.a(this, 1);
            wn2.u(this.e);
            this.J();
        }
    }

    public int j() {
        return this.e;
    }

    public int l() {
        if (this.e >= 2477) {
            return 10;
        }
        if (this.e >= 1237) {
            return 9;
        }
        if (this.e >= 617) {
            return 8;
        }
        if (this.e >= 307) {
            return 7;
        }
        if (this.e >= 149) {
            return 6;
        }
        if (this.e >= 73) {
            return 5;
        }
        if (this.e >= 37) {
            return 4;
        }
        if (this.e >= 17) {
            return 3;
        }
        if (this.e >= 7) {
            return 2;
        }
        if (this.e >= 3) {
            return 1;
        }
        return 0;
    }

    public static int a(int n2) {
        if (n2 >= 2477) {
            return 2477;
        }
        if (n2 >= 1237) {
            return 1237;
        }
        if (n2 >= 617) {
            return 617;
        }
        if (n2 >= 307) {
            return 307;
        }
        if (n2 >= 149) {
            return 149;
        }
        if (n2 >= 73) {
            return 73;
        }
        if (n2 >= 37) {
            return 37;
        }
        if (n2 >= 17) {
            return 17;
        }
        if (n2 >= 7) {
            return 7;
        }
        if (n2 >= 3) {
            return 3;
        }
        return 1;
    }

    @Override
    public boolean aD() {
        return false;
    }
}

