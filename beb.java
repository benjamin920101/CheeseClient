/*
 * Decompiled with CFR 0.152.
 */
public class beb
extends pk {
    protected int b;
    protected int c;
    protected float d;
    protected float e;
    protected int f;
    protected int g;
    protected float h;
    protected float i;
    protected float ar;
    protected float as;
    protected float at;
    protected float au = 1.0f;
    protected bmi av;
    public static double aw;
    public static double ax;
    public static double ay;

    protected beb(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.a(0.2f, 0.2f);
        this.b(d2, d3, d4);
        this.P = this.p = d2;
        this.Q = this.q = d3;
        this.R = this.r = d4;
        this.at = 1.0f;
        this.as = 1.0f;
        this.ar = 1.0f;
        this.d = this.V.nextFloat() * 3.0f;
        this.e = this.V.nextFloat() * 3.0f;
        this.h = (this.V.nextFloat() * 0.5f + 0.5f) * 2.0f;
        this.g = (int)(4.0f / (this.V.nextFloat() * 0.9f + 0.1f));
        this.f = 0;
    }

    public beb(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        this(adm2, d2, d3, d4);
        this.v = d5 + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        this.w = d6 + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        this.x = d7 + (Math.random() * 2.0 - 1.0) * (double)0.4f;
        float f2 = (float)(Math.random() + Math.random() + 1.0) * 0.15f;
        \u2603 = ns.a(this.v * this.v + this.w * this.w + this.x * this.x);
        this.v = this.v / (double)\u2603 * (double)f2 * (double)0.4f;
        this.w = this.w / (double)\u2603 * (double)f2 * (double)0.4f + (double)0.1f;
        this.x = this.x / (double)\u2603 * (double)f2 * (double)0.4f;
    }

    public beb a(float f2) {
        this.v *= (double)f2;
        this.w = (this.w - (double)0.1f) * (double)f2 + (double)0.1f;
        this.x *= (double)f2;
        return this;
    }

    public beb h(float f2) {
        this.a(0.2f * f2, 0.2f * f2);
        this.h *= f2;
        return this;
    }

    public void b(float f2, float f3, float f4) {
        this.ar = f2;
        this.as = f3;
        this.at = f4;
    }

    public void i(float f2) {
        if (this.au == 1.0f && f2 < 1.0f) {
            ave.A().j.b(this);
        } else if (this.au < 1.0f && f2 == 1.0f) {
            ave.A().j.c(this);
        }
        this.au = f2;
    }

    public float b() {
        return this.ar;
    }

    public float g() {
        return this.as;
    }

    public float i() {
        return this.at;
    }

    public float j() {
        return this.au;
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void h() {
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        if (this.f++ >= this.g) {
            this.J();
        }
        this.w -= 0.04 * (double)this.i;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.98f;
        this.w *= (double)0.98f;
        this.x *= (double)0.98f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = (float)this.b / 16.0f;
        \u2603 = \u2603 + 0.0624375f;
        \u2603 = (float)this.c / 16.0f;
        \u2603 = \u2603 + 0.0624375f;
        \u2603 = 0.1f * this.h;
        if (this.av != null) {
            \u2603 = this.av.e();
            \u2603 = this.av.f();
            \u2603 = this.av.g();
            \u2603 = this.av.h();
        }
        \u2603 = (float)(this.p + (this.s - this.p) * (double)f2 - aw);
        \u2603 = (float)(this.q + (this.t - this.q) * (double)f2 - ax);
        \u2603 = (float)(this.r + (this.u - this.r) * (double)f2 - ay);
        int n2 = this.b(f2);
        \u2603 = n2 >> 16 & 0xFFFF;
        \u2603 = n2 & 0xFFFF;
        bfd2.b((double)(\u2603 - f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 - f5 * \u2603 - f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 - f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 - f5 * \u2603 + f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 + f5 * \u2603 + f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 + f5 * \u2603 - f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, this.au).a(\u2603, \u2603).d();
    }

    public int a() {
        return 0;
    }

    @Override
    public void b(dn dn2) {
    }

    @Override
    public void a(dn dn2) {
    }

    public void a(bmi bmi2) {
        int n2 = this.a();
        if (n2 != 1) {
            throw new RuntimeException("Invalid call to Particle.setTex, use coordinate methods");
        }
        this.av = bmi2;
    }

    public void k(int n2) {
        if (this.a() != 0) {
            throw new RuntimeException("Invalid call to Particle.setMiscTex");
        }
        this.b = n2 % 16;
        this.c = n2 / 16;
    }

    public void k() {
        ++this.b;
    }

    @Override
    public boolean aD() {
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ", Pos (" + this.s + "," + this.t + "," + this.u + "), RGBA (" + this.ar + "," + this.as + "," + this.at + "," + this.au + "), Age " + this.f;
    }
}

