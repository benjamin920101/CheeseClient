/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class ams {
    private final List<amq> a = Lists.newArrayList();
    private double b = 0.0;
    private double c = 0.0;
    private double d;
    private double e = this.d = 6.0E7;
    private long f;
    private long g;
    private int h = 29999984;
    private double i = 0.2;
    private double j = 5.0;
    private int k = 15;
    private int l = 5;

    public boolean a(cj cj2) {
        return (double)(cj2.n() + 1) > this.b() && (double)cj2.n() < this.d() && (double)(cj2.p() + 1) > this.c() && (double)cj2.p() < this.e();
    }

    public boolean a(adg adg2) {
        return (double)adg2.e() > this.b() && (double)adg2.c() < this.d() && (double)adg2.f() > this.c() && (double)adg2.d() < this.e();
    }

    public boolean a(aug aug2) {
        return aug2.d > this.b() && aug2.a < this.d() && aug2.f > this.c() && aug2.c < this.e();
    }

    public double a(pk pk2) {
        return this.b(pk2.s, pk2.u);
    }

    public double b(double d2, double d3) {
        \u2603 = d3 - this.c();
        \u2603 = this.e() - d3;
        \u2603 = d2 - this.b();
        \u2603 = this.d() - d2;
        \u2603 = Math.min(\u2603, \u2603);
        \u2603 = Math.min(\u2603, \u2603);
        return Math.min(\u2603, \u2603);
    }

    public amr a() {
        if (this.e < this.d) {
            return amr.b;
        }
        if (this.e > this.d) {
            return amr.a;
        }
        return amr.c;
    }

    public double b() {
        double d2 = this.f() - this.h() / 2.0;
        if (d2 < (double)(-this.h)) {
            d2 = -this.h;
        }
        return d2;
    }

    public double c() {
        double d2 = this.g() - this.h() / 2.0;
        if (d2 < (double)(-this.h)) {
            d2 = -this.h;
        }
        return d2;
    }

    public double d() {
        double d2 = this.f() + this.h() / 2.0;
        if (d2 > (double)this.h) {
            d2 = this.h;
        }
        return d2;
    }

    public double e() {
        double d2 = this.g() + this.h() / 2.0;
        if (d2 > (double)this.h) {
            d2 = this.h;
        }
        return d2;
    }

    public double f() {
        return this.b;
    }

    public double g() {
        return this.c;
    }

    public void c(double d2, double d3) {
        this.b = d2;
        this.c = d3;
        for (amq amq2 : this.k()) {
            amq2.a(this, d2, d3);
        }
    }

    public double h() {
        if (this.a() != amr.c) {
            double d2 = (float)(System.currentTimeMillis() - this.g) / (float)(this.f - this.g);
            if (d2 >= 1.0) {
                this.a(this.e);
            } else {
                return this.d + (this.e - this.d) * d2;
            }
        }
        return this.d;
    }

    public long i() {
        if (this.a() != amr.c) {
            return this.f - System.currentTimeMillis();
        }
        return 0L;
    }

    public double j() {
        return this.e;
    }

    public void a(double d2) {
        this.d = d2;
        this.e = d2;
        this.g = this.f = System.currentTimeMillis();
        for (amq amq2 : this.k()) {
            amq2.a(this, d2);
        }
    }

    public void a(double d2, double d3, long l2) {
        this.d = d2;
        this.e = d3;
        this.g = System.currentTimeMillis();
        this.f = this.g + l2;
        for (amq amq2 : this.k()) {
            amq2.a(this, d2, d3, l2);
        }
    }

    protected List<amq> k() {
        return Lists.newArrayList(this.a);
    }

    public void a(amq amq2) {
        this.a.add(amq2);
    }

    public void a(int n2) {
        this.h = n2;
    }

    public int l() {
        return this.h;
    }

    public double m() {
        return this.j;
    }

    public void b(double d2) {
        this.j = d2;
        for (amq amq2 : this.k()) {
            amq2.c(this, d2);
        }
    }

    public double n() {
        return this.i;
    }

    public void c(double d2) {
        this.i = d2;
        for (amq amq2 : this.k()) {
            amq2.b(this, d2);
        }
    }

    public double o() {
        if (this.f == this.g) {
            return 0.0;
        }
        return Math.abs(this.d - this.e) / (double)(this.f - this.g);
    }

    public int p() {
        return this.k;
    }

    public void b(int n2) {
        this.k = n2;
        for (amq amq2 : this.k()) {
            amq2.a(this, n2);
        }
    }

    public int q() {
        return this.l;
    }

    public void c(int n2) {
        this.l = n2;
        for (amq amq2 : this.k()) {
            amq2.b(this, n2);
        }
    }
}

