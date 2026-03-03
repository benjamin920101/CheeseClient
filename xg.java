/*
 * Decompiled with CFR 0.152.
 */
public class xg {
    private int a = 20;
    private float b = 5.0f;
    private float c;
    private int d;
    private int e = 20;

    public void a(int n2, float f2) {
        this.a = Math.min(n2 + this.a, 20);
        this.b = Math.min(this.b + (float)n2 * f2 * 2.0f, (float)this.a);
    }

    public void a(zs zs2, zx zx2) {
        this.a(zs2.h(zx2), zs2.i(zx2));
    }

    public void a(wn wn2) {
        oj oj2 = wn2.o.aa();
        this.e = this.a;
        if (this.c > 4.0f) {
            this.c -= 4.0f;
            if (this.b > 0.0f) {
                this.b = Math.max(this.b - 1.0f, 0.0f);
            } else if (oj2 != oj.a) {
                this.a = Math.max(this.a - 1, 0);
            }
        }
        if (wn2.o.Q().b("naturalRegeneration") && this.a >= 18 && wn2.cm()) {
            ++this.d;
            if (this.d >= 80) {
                wn2.h(1.0f);
                this.a(3.0f);
                this.d = 0;
            }
        } else if (this.a <= 0) {
            ++this.d;
            if (this.d >= 80) {
                if (wn2.bn() > 10.0f || oj2 == oj.d || wn2.bn() > 1.0f && oj2 == oj.c) {
                    wn2.a(ow.g, 1.0f);
                }
                this.d = 0;
            }
        } else {
            this.d = 0;
        }
    }

    public void a(dn dn2) {
        if (dn2.b("foodLevel", 99)) {
            this.a = dn2.f("foodLevel");
            this.d = dn2.f("foodTickTimer");
            this.b = dn2.h("foodSaturationLevel");
            this.c = dn2.h("foodExhaustionLevel");
        }
    }

    public void b(dn dn2) {
        dn2.a("foodLevel", this.a);
        dn2.a("foodTickTimer", this.d);
        dn2.a("foodSaturationLevel", this.b);
        dn2.a("foodExhaustionLevel", this.c);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.e;
    }

    public boolean c() {
        return this.a < 20;
    }

    public void a(float f2) {
        this.c = Math.min(this.c + f2, 40.0f);
    }

    public float e() {
        return this.b;
    }

    public void a(int n2) {
        this.a = n2;
    }

    public void b(float f2) {
        this.b = f2;
    }
}

