/*
 * Decompiled with CFR 0.152.
 */
public class aug {
    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public aug(double d2, double d3, double d4, double d5, double d6, double d7) {
        this.a = Math.min(d2, d5);
        this.b = Math.min(d3, d6);
        this.c = Math.min(d4, d7);
        this.d = Math.max(d2, d5);
        this.e = Math.max(d3, d6);
        this.f = Math.max(d4, d7);
    }

    public aug(cj cj2, cj cj3) {
        this.a = cj2.n();
        this.b = cj2.o();
        this.c = cj2.p();
        this.d = cj3.n();
        this.e = cj3.o();
        this.f = cj3.p();
    }

    public aug a(double d2, double d3, double d4) {
        \u2603 = this.a;
        \u2603 = this.b;
        \u2603 = this.c;
        \u2603 = this.d;
        \u2603 = this.e;
        \u2603 = this.f;
        if (d2 < 0.0) {
            \u2603 += d2;
        } else if (d2 > 0.0) {
            \u2603 += d2;
        }
        if (d3 < 0.0) {
            \u2603 += d3;
        } else if (d3 > 0.0) {
            \u2603 += d3;
        }
        if (d4 < 0.0) {
            \u2603 += d4;
        } else if (d4 > 0.0) {
            \u2603 += d4;
        }
        return new aug(\u2603, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public aug b(double d2, double d3, double d4) {
        \u2603 = this.a - d2;
        \u2603 = this.b - d3;
        \u2603 = this.c - d4;
        \u2603 = this.d + d2;
        \u2603 = this.e + d3;
        \u2603 = this.f + d4;
        return new aug(\u2603, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public aug a(aug aug2) {
        double d2 = Math.min(this.a, aug2.a);
        \u2603 = Math.min(this.b, aug2.b);
        \u2603 = Math.min(this.c, aug2.c);
        \u2603 = Math.max(this.d, aug2.d);
        \u2603 = Math.max(this.e, aug2.e);
        \u2603 = Math.max(this.f, aug2.f);
        return new aug(d2, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public static aug a(double d2, double d3, double d4, double d5, double d6, double d7) {
        \u2603 = Math.min(d2, d5);
        \u2603 = Math.min(d3, d6);
        \u2603 = Math.min(d4, d7);
        \u2603 = Math.max(d2, d5);
        \u2603 = Math.max(d3, d6);
        \u2603 = Math.max(d4, d7);
        return new aug(\u2603, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public aug c(double d2, double d3, double d4) {
        return new aug(this.a + d2, this.b + d3, this.c + d4, this.d + d2, this.e + d3, this.f + d4);
    }

    public double a(aug aug2, double d2) {
        if (aug2.e <= this.b || aug2.b >= this.e || aug2.f <= this.c || aug2.c >= this.f) {
            return d2;
        }
        if (d2 > 0.0 && aug2.d <= this.a) {
            \u2603 = this.a - aug2.d;
            if (\u2603 < d2) {
                d2 = \u2603;
            }
        } else if (d2 < 0.0 && aug2.a >= this.d && (\u2603 = this.d - aug2.a) > d2) {
            d2 = \u2603;
        }
        return d2;
    }

    public double b(aug aug2, double d2) {
        if (aug2.d <= this.a || aug2.a >= this.d || aug2.f <= this.c || aug2.c >= this.f) {
            return d2;
        }
        if (d2 > 0.0 && aug2.e <= this.b) {
            \u2603 = this.b - aug2.e;
            if (\u2603 < d2) {
                d2 = \u2603;
            }
        } else if (d2 < 0.0 && aug2.b >= this.e && (\u2603 = this.e - aug2.b) > d2) {
            d2 = \u2603;
        }
        return d2;
    }

    public double c(aug aug2, double d2) {
        if (aug2.d <= this.a || aug2.a >= this.d || aug2.e <= this.b || aug2.b >= this.e) {
            return d2;
        }
        if (d2 > 0.0 && aug2.f <= this.c) {
            \u2603 = this.c - aug2.f;
            if (\u2603 < d2) {
                d2 = \u2603;
            }
        } else if (d2 < 0.0 && aug2.c >= this.f && (\u2603 = this.f - aug2.c) > d2) {
            d2 = \u2603;
        }
        return d2;
    }

    public boolean b(aug aug2) {
        if (aug2.d <= this.a || aug2.a >= this.d) {
            return false;
        }
        if (aug2.e <= this.b || aug2.b >= this.e) {
            return false;
        }
        return !(aug2.f <= this.c) && !(aug2.c >= this.f);
    }

    public boolean a(aui aui2) {
        if (aui2.a <= this.a || aui2.a >= this.d) {
            return false;
        }
        if (aui2.b <= this.b || aui2.b >= this.e) {
            return false;
        }
        return !(aui2.c <= this.c) && !(aui2.c >= this.f);
    }

    public double a() {
        double d2 = this.d - this.a;
        \u2603 = this.e - this.b;
        \u2603 = this.f - this.c;
        return (d2 + \u2603 + \u2603) / 3.0;
    }

    public aug d(double d2, double d3, double d4) {
        \u2603 = this.a + d2;
        \u2603 = this.b + d3;
        \u2603 = this.c + d4;
        \u2603 = this.d - d2;
        \u2603 = this.e - d3;
        \u2603 = this.f - d4;
        return new aug(\u2603, \u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public auh a(aui aui2, aui aui3) {
        \u2603 = aui2.a(aui3, this.a);
        \u2603 = aui2.a(aui3, this.d);
        \u2603 = aui2.b(aui3, this.b);
        \u2603 = aui2.b(aui3, this.e);
        \u2603 = aui2.c(aui3, this.c);
        \u2603 = aui2.c(aui3, this.f);
        if (!this.b(\u2603)) {
            \u2603 = null;
        }
        if (!this.b(\u2603)) {
            \u2603 = null;
        }
        if (!this.c(\u2603)) {
            \u2603 = null;
        }
        if (!this.c(\u2603)) {
            \u2603 = null;
        }
        if (!this.d(\u2603)) {
            \u2603 = null;
        }
        if (!this.d(\u2603)) {
            \u2603 = null;
        }
        \u2603 = null;
        if (\u2603 != null) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 == null) {
            return null;
        }
        cq cq2 = null;
        cq2 = \u2603 == \u2603 ? cq.e : (\u2603 == \u2603 ? cq.f : (\u2603 == \u2603 ? cq.a : (\u2603 == \u2603 ? cq.b : (\u2603 == \u2603 ? cq.c : cq.d))));
        return new auh(\u2603, cq2);
    }

    private boolean b(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.b >= this.b && aui2.b <= this.e && aui2.c >= this.c && aui2.c <= this.f;
    }

    private boolean c(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.a >= this.a && aui2.a <= this.d && aui2.c >= this.c && aui2.c <= this.f;
    }

    private boolean d(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.a >= this.a && aui2.a <= this.d && aui2.b >= this.b && aui2.b <= this.e;
    }

    public String toString() {
        return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
    }

    public boolean b() {
        return Double.isNaN(this.a) || Double.isNaN(this.b) || Double.isNaN(this.c) || Double.isNaN(this.d) || Double.isNaN(this.e) || Double.isNaN(this.f);
    }
}

