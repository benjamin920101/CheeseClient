/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aoi
extends aoh {
    private Random k;
    private adm l;
    private cj m = cj.a;
    int a;
    int b;
    double c = 0.618;
    double d = 0.381;
    double e = 1.0;
    double f = 1.0;
    int g = 1;
    int h = 12;
    int i = 4;
    List<a> j;

    public aoi(boolean bl2) {
        super(bl2);
    }

    void a() {
        int n2;
        this.b = (int)((double)this.a * this.c);
        if (this.b >= this.a) {
            this.b = this.a - 1;
        }
        if ((n2 = (int)(1.382 + Math.pow(this.f * (double)this.a / 13.0, 2.0))) < 1) {
            n2 = 1;
        }
        \u2603 = this.m.o() + this.b;
        this.j = Lists.newArrayList();
        this.j.add(new a(this.m.b(\u2603), \u2603));
        for (\u2603 = this.a - this.i; \u2603 >= 0; --\u2603) {
            float f2 = this.a(\u2603);
            if (f2 < 0.0f) continue;
            for (int i2 = 0; i2 < n2; ++i2) {
                double d2 = this.e * (double)f2 * ((double)this.k.nextFloat() + 0.328);
                \u2603 = d2 * Math.sin(\u2603 = (double)(this.k.nextFloat() * 2.0f) * Math.PI) + 0.5;
                cj \u26032 = this.m.a(\u2603, (double)(\u2603 - 1), \u2603 = d2 * Math.cos(\u2603) + 0.5);
                if (this.a(\u26032, \u2603 = \u26032.b(this.i)) != -1) continue;
                int \u26033 = this.m.n() - \u26032.n();
                int \u26034 = this.m.p() - \u26032.p();
                \u2603 = (double)\u26032.o() - Math.sqrt(\u26033 * \u26033 + \u26034 * \u26034) * this.d;
                int \u26035 = \u2603 > (double)\u2603 ? \u2603 : (int)\u2603;
                cj \u26036 = new cj(this.m.n(), \u26035, this.m.p());
                if (this.a(\u26036, \u26032) != -1) continue;
                this.j.add(new a(\u26032, \u26036.o()));
            }
        }
    }

    void a(cj cj2, float f2, alz alz2) {
        int n2 = (int)((double)f2 + 0.618);
        for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
            for (\u2603 = -n2; \u2603 <= n2; ++\u2603) {
                if (!(Math.pow((double)Math.abs(\u2603) + 0.5, 2.0) + Math.pow((double)Math.abs(\u2603) + 0.5, 2.0) <= (double)(f2 * f2)) || (\u2603 = this.l.p(\u2603 = cj2.a(\u2603, 0, \u2603)).c().t()) != arm.a && \u2603 != arm.j) continue;
                this.a(this.l, \u2603, alz2);
            }
        }
    }

    float a(int n2) {
        if ((float)n2 < (float)this.a * 0.3f) {
            return -1.0f;
        }
        float f2 = (float)this.a / 2.0f;
        \u2603 = f2 - (float)n2;
        \u2603 = ns.c(f2 * f2 - \u2603 * \u2603);
        if (\u2603 == 0.0f) {
            \u2603 = f2;
        } else if (Math.abs(\u2603) >= f2) {
            return 0.0f;
        }
        return \u2603 * 0.5f;
    }

    float b(int n2) {
        if (n2 < 0 || n2 >= this.i) {
            return -1.0f;
        }
        if (n2 == 0 || n2 == this.i - 1) {
            return 2.0f;
        }
        return 3.0f;
    }

    void a(cj cj2) {
        for (int i2 = 0; i2 < this.i; ++i2) {
            this.a(cj2.b(i2), this.b(i2), afi.t.Q().a(ahs.b, false));
        }
    }

    void a(cj cj2, cj cj3, afh afh2) {
        cj cj4 = cj3.a(-cj2.n(), -cj2.o(), -cj2.p());
        int \u26032 = this.b(cj4);
        float \u26033 = (float)cj4.n() / (float)\u26032;
        float \u26034 = (float)cj4.o() / (float)\u26032;
        float \u26035 = (float)cj4.p() / (float)\u26032;
        for (int i2 = 0; i2 <= \u26032; ++i2) {
            cj cj5 = cj2.a(0.5f + (float)i2 * \u26033, 0.5f + (float)i2 * \u26034, 0.5f + (float)i2 * \u26035);
            ahw.a \u26036 = this.b(cj2, cj5);
            this.a(this.l, cj5, afh2.Q().a(ahw.a, \u26036));
        }
    }

    private int b(cj cj2) {
        int n2 = ns.a(cj2.n());
        \u2603 = ns.a(cj2.o());
        \u2603 = ns.a(cj2.p());
        if (\u2603 > n2 && \u2603 > \u2603) {
            return \u2603;
        }
        if (\u2603 > n2) {
            return \u2603;
        }
        return n2;
    }

    private ahw.a b(cj cj2, cj cj3) {
        ahw.a a2 = ahw.a.b;
        int \u26032 = Math.abs(cj3.n() - cj2.n());
        int \u26033 = Math.max(\u26032, \u2603 = Math.abs(cj3.p() - cj2.p()));
        if (\u26033 > 0) {
            if (\u26032 == \u26033) {
                a2 = ahw.a.a;
            } else if (\u2603 == \u26033) {
                a2 = ahw.a.c;
            }
        }
        return a2;
    }

    void b() {
        for (a a2 : this.j) {
            this.a(a2);
        }
    }

    boolean c(int n2) {
        return (double)n2 >= (double)this.a * 0.2;
    }

    void c() {
        cj cj2 = this.m;
        \u2603 = this.m.b(this.b);
        afh \u26032 = afi.r;
        this.a(cj2, \u2603, \u26032);
        if (this.g == 2) {
            this.a(cj2.f(), \u2603.f(), \u26032);
            this.a(cj2.f().d(), \u2603.f().d(), \u26032);
            this.a(cj2.d(), \u2603.d(), \u26032);
        }
    }

    void d() {
        for (a a2 : this.j) {
            int n2 = a2.q();
            cj \u26032 = new cj(this.m.n(), n2, this.m.p());
            if (\u26032.equals(a2) || !this.c(n2 - this.m.o())) continue;
            this.a(\u26032, (cj)a2, afi.r);
        }
    }

    int a(cj cj2, cj cj3) {
        \u2603 = cj3.a(-cj2.n(), -cj2.o(), -cj2.p());
        int n2 = this.b(\u2603);
        float \u26032 = (float)\u2603.n() / (float)n2;
        float \u26033 = (float)\u2603.o() / (float)n2;
        float \u26034 = (float)\u2603.p() / (float)n2;
        if (n2 == 0) {
            return -1;
        }
        for (\u2603 = 0; \u2603 <= n2; ++\u2603) {
            cj cj4 = cj2.a(0.5f + (float)\u2603 * \u26032, 0.5f + (float)\u2603 * \u26033, 0.5f + (float)\u2603 * \u26034);
            if (this.a(this.l.p(cj4).c())) continue;
            return \u2603;
        }
        return -1;
    }

    @Override
    public void e() {
        this.i = 5;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        this.l = adm2;
        this.m = cj2;
        this.k = new Random(random.nextLong());
        if (this.a == 0) {
            this.a = 5 + this.k.nextInt(this.h);
        }
        if (!this.f()) {
            return false;
        }
        this.a();
        this.b();
        this.c();
        this.d();
        return true;
    }

    private boolean f() {
        afh afh2 = this.l.p(this.m.b()).c();
        if (afh2 != afi.d && afh2 != afi.c && afh2 != afi.ak) {
            return false;
        }
        int \u26032 = this.a(this.m, this.m.b(this.a - 1));
        if (\u26032 == -1) {
            return true;
        }
        if (\u26032 < 6) {
            return false;
        }
        this.a = \u26032;
        return true;
    }

    static class a
    extends cj {
        private final int c;

        public a(cj cj2, int n2) {
            super(cj2.n(), cj2.o(), cj2.p());
            this.c = n2;
        }

        public int q() {
            return this.c;
        }
    }
}

