/*
 * Decompiled with CFR 0.152.
 */
public class rm
extends rd {
    private py a;
    private te b;
    private int c = -1;
    private int d = -1;

    public rm(py py2) {
        this.a = py2;
        this.a(1);
    }

    @Override
    public boolean a() {
        cj cj2 = new cj(this.a);
        if (this.a.o.w() && (!this.a.o.S() || this.a.o.b(cj2).e()) || this.a.o.t.o()) {
            return false;
        }
        if (this.a.bc().nextInt(50) != 0) {
            return false;
        }
        if (this.c != -1 && this.a.e(this.c, this.a.t, this.d) < 4.0) {
            return false;
        }
        tf \u26032 = this.a.o.ae().a(cj2, 14);
        if (\u26032 == null) {
            return false;
        }
        this.b = \u26032.c(cj2);
        return this.b != null;
    }

    @Override
    public boolean b() {
        return !this.a.s().m();
    }

    @Override
    public void c() {
        this.c = -1;
        cj cj2 = this.b.e();
        int \u26032 = cj2.n();
        int \u26033 = cj2.o();
        int \u26034 = cj2.p();
        if (this.a.b(cj2) > 256.0) {
            aui aui2 = tc.a(this.a, 14, 3, new aui((double)\u26032 + 0.5, \u26033, (double)\u26034 + 0.5));
            if (aui2 != null) {
                this.a.s().a(aui2.a, aui2.b, aui2.c, 1.0);
            }
        } else {
            this.a.s().a((double)\u26032 + 0.5, \u26033, (double)\u26034 + 0.5, 1.0);
        }
    }

    @Override
    public void d() {
        this.c = this.b.e().n();
        this.d = this.b.e().p();
        this.b = null;
    }
}

