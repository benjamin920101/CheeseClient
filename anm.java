/*
 * Decompiled with CFR 0.152.
 */
public abstract class anm {
    public static final float[] a = new float[]{1.0f, 0.75f, 0.5f, 0.25f, 0.0f, 0.25f, 0.5f, 0.75f};
    protected adm b;
    private adr h;
    private String i;
    protected aec c;
    protected boolean d;
    protected boolean e;
    protected final float[] f = new float[16];
    protected int g;
    private final float[] j = new float[4];

    public final void a(adm adm2) {
        this.b = adm2;
        this.h = adm2.P().u();
        this.i = adm2.P().B();
        this.b();
        this.a();
    }

    protected void a() {
        float f2 = 0.0f;
        for (int i2 = 0; i2 <= 15; ++i2) {
            float f3 = 1.0f - (float)i2 / 15.0f;
            this.f[i2] = (1.0f - f3) / (f3 * 3.0f + 1.0f) * (1.0f - f2) + f2;
        }
    }

    protected void b() {
        adr adr2 = this.b.P().u();
        if (adr2 == adr.c) {
            apz apz2 = apz.a(this.b.P().B());
            this.c = new aef(ady.a(apz2.a(), ady.ad), 0.5f);
        } else {
            this.c = adr2 == adr.g ? new aef(ady.q, 0.0f) : new aec(this.b);
        }
    }

    public amv c() {
        if (this.h == adr.c) {
            return new anv(this.b, this.b.J(), this.b.P().s(), this.i);
        }
        if (this.h == adr.g) {
            return new anu(this.b);
        }
        if (this.h == adr.f) {
            return new aoa(this.b, this.b.J(), this.b.P().s(), this.i);
        }
        return new aoa(this.b, this.b.J(), this.b.P().s(), this.i);
    }

    public boolean a(int n2, int n3) {
        return this.b.c(new cj(n2, 0, n3)) == afi.c;
    }

    public float a(long l2, float f2) {
        int n2 = (int)(l2 % 24000L);
        float \u26032 = ((float)n2 + f2) / 24000.0f - 0.25f;
        if (\u26032 < 0.0f) {
            \u26032 += 1.0f;
        }
        if (\u26032 > 1.0f) {
            \u26032 -= 1.0f;
        }
        float \u26033 = \u26032;
        \u26032 = 1.0f - (float)((Math.cos((double)\u26032 * Math.PI) + 1.0) / 2.0);
        \u26032 = \u26033 + (\u26032 - \u26033) / 3.0f;
        return \u26032;
    }

    public int a(long l2) {
        return (int)(l2 / 24000L % 8L + 8L) % 8;
    }

    public boolean d() {
        return true;
    }

    public float[] a(float f2, float f3) {
        \u2603 = 0.4f;
        \u2603 = ns.b(f2 * (float)Math.PI * 2.0f) - 0.0f;
        if (\u2603 >= (\u2603 = -0.0f) - \u2603 && \u2603 <= \u2603 + \u2603) {
            \u2603 = (\u2603 - \u2603) / \u2603 * 0.5f + 0.5f;
            \u2603 = 1.0f - (1.0f - ns.a(\u2603 * (float)Math.PI)) * 0.99f;
            \u2603 *= \u2603;
            this.j[0] = \u2603 * 0.3f + 0.7f;
            this.j[1] = \u2603 * \u2603 * 0.7f + 0.2f;
            this.j[2] = \u2603 * \u2603 * 0.0f + 0.2f;
            this.j[3] = \u2603;
            return this.j;
        }
        return null;
    }

    public aui b(float f2, float f3) {
        \u2603 = ns.b(f2 * (float)Math.PI * 2.0f) * 2.0f + 0.5f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 = 0.7529412f;
        \u2603 = 0.84705883f;
        \u2603 = 1.0f;
        return new aui(\u2603 *= \u2603 * 0.94f + 0.06f, \u2603 *= \u2603 * 0.94f + 0.06f, \u2603 *= \u2603 * 0.91f + 0.09f);
    }

    public boolean e() {
        return true;
    }

    public static anm a(int n2) {
        if (n2 == -1) {
            return new ann();
        }
        if (n2 == 0) {
            return new ano();
        }
        if (n2 == 1) {
            return new anp();
        }
        return null;
    }

    public float f() {
        return 128.0f;
    }

    public boolean g() {
        return true;
    }

    public cj h() {
        return null;
    }

    public int i() {
        if (this.h == adr.c) {
            return 4;
        }
        return this.b.F() + 1;
    }

    public double j() {
        if (this.h == adr.c) {
            return 1.0;
        }
        return 0.03125;
    }

    public boolean b(int n2, int n3) {
        return false;
    }

    public abstract String k();

    public abstract String l();

    public aec m() {
        return this.c;
    }

    public boolean n() {
        return this.d;
    }

    public boolean o() {
        return this.e;
    }

    public float[] p() {
        return this.f;
    }

    public int q() {
        return this.g;
    }

    public ams r() {
        return new ams();
    }
}

