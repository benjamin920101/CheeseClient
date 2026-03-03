/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public abstract class sw {
    protected ps b;
    protected adm c;
    protected asx d;
    protected double e;
    private final qc a;
    private int f;
    private int g;
    private aui h = new aui(0.0, 0.0, 0.0);
    private float i = 1.0f;
    private final asy j;

    public sw(ps ps2, adm adm2) {
        this.b = ps2;
        this.c = adm2;
        this.a = ps2.a(vy.b);
        this.j = this.a();
    }

    protected abstract asy a();

    public void a(double d2) {
        this.e = d2;
    }

    public float i() {
        return (float)this.a.e();
    }

    public final asx a(double d2, double d3, double d4) {
        return this.a(new cj(ns.c(d2), (int)d3, ns.c(d4)));
    }

    public asx a(cj cj2) {
        if (!this.b()) {
            return null;
        }
        float f2 = this.i();
        this.c.B.a("pathfind");
        cj \u26032 = new cj(this.b);
        int \u26033 = (int)(f2 + 8.0f);
        adv \u26034 = new adv(this.c, \u26032.a(-\u26033, -\u26033, -\u26033), \u26032.a(\u26033, \u26033, \u26033), 0);
        asx \u26035 = this.j.a((adq)\u26034, (pk)this.b, cj2, f2);
        this.c.B.b();
        return \u26035;
    }

    public boolean a(double d2, double d3, double d4, double d5) {
        asx asx2 = this.a(ns.c(d2), (int)d3, ns.c(d4));
        return this.a(asx2, d5);
    }

    public void a(float f2) {
        this.i = f2;
    }

    public asx a(pk pk2) {
        if (!this.b()) {
            return null;
        }
        float f2 = this.i();
        this.c.B.a("pathfind");
        cj \u26032 = new cj(this.b).a();
        int \u26033 = (int)(f2 + 16.0f);
        adv \u26034 = new adv(this.c, \u26032.a(-\u26033, -\u26033, -\u26033), \u26032.a(\u26033, \u26033, \u26033), 0);
        asx \u26035 = this.j.a((adq)\u26034, (pk)this.b, pk2, f2);
        this.c.B.b();
        return \u26035;
    }

    public boolean a(pk pk2, double d2) {
        asx asx2 = this.a(pk2);
        if (asx2 != null) {
            return this.a(asx2, d2);
        }
        return false;
    }

    public boolean a(asx asx2, double d2) {
        if (asx2 == null) {
            this.d = null;
            return false;
        }
        if (!asx2.a(this.d)) {
            this.d = asx2;
        }
        this.d();
        if (this.d.d() == 0) {
            return false;
        }
        this.e = d2;
        aui aui2 = this.c();
        this.g = this.f;
        this.h = aui2;
        return true;
    }

    public asx j() {
        return this.d;
    }

    public void k() {
        Object \u26032;
        aui aui2;
        ++this.f;
        if (this.m()) {
            return;
        }
        if (this.b()) {
            this.l();
        } else if (this.d != null && this.d.e() < this.d.d()) {
            aui2 = this.c();
            \u26032 = this.d.a(this.b, this.d.e());
            if (aui2.b > ((aui)\u26032).b && !this.b.C && ns.c(aui2.a) == ns.c(((aui)\u26032).a) && ns.c(aui2.c) == ns.c(((aui)\u26032).c)) {
                this.d.c(this.d.e() + 1);
            }
        }
        if (this.m()) {
            return;
        }
        aui2 = this.d.a(this.b);
        if (aui2 == null) {
            return;
        }
        \u26032 = new aug(aui2.a, aui2.b, aui2.c, aui2.a, aui2.b, aui2.c).b(0.5, 0.5, 0.5);
        List<aug> list = this.c.a((pk)this.b, ((aug)\u26032).a(0.0, -1.0, 0.0));
        double \u26033 = -1.0;
        \u26032 = ((aug)\u26032).c(0.0, 1.0, 0.0);
        for (aug aug2 : list) {
            \u26033 = aug2.b((aug)\u26032, \u26033);
        }
        this.b.q().a(aui2.a, aui2.b + \u26033, aui2.c, this.e);
    }

    protected void l() {
        int n2;
        aui aui2 = this.c();
        int \u26032 = this.d.d();
        for (int i2 = this.d.e(); i2 < this.d.d(); ++i2) {
            if (this.d.a((int)i2).b == (int)aui2.b) continue;
            \u26032 = i2;
            break;
        }
        float f2 = this.b.J * this.b.J * this.i;
        for (n2 = this.d.e(); n2 < \u26032; ++n2) {
            aui aui3 = this.d.a(this.b, n2);
            if (!(aui2.g(aui3) < (double)f2)) continue;
            this.d.c(n2 + 1);
        }
        n2 = ns.f(this.b.J);
        \u2603 = (int)this.b.K + 1;
        \u2603 = n2;
        for (\u2603 = \u26032 - 1; \u2603 >= this.d.e(); --\u2603) {
            if (!this.a(aui2, this.d.a(this.b, \u2603), n2, \u2603, \u2603)) continue;
            this.d.c(\u2603);
            break;
        }
        this.a(aui2);
    }

    protected void a(aui aui2) {
        if (this.f - this.g > 100) {
            if (aui2.g(this.h) < 2.25) {
                this.n();
            }
            this.g = this.f;
            this.h = aui2;
        }
    }

    public boolean m() {
        return this.d == null || this.d.b();
    }

    public void n() {
        this.d = null;
    }

    protected abstract aui c();

    protected abstract boolean b();

    protected boolean o() {
        return this.b.V() || this.b.ab();
    }

    protected void d() {
    }

    protected abstract boolean a(aui var1, aui var2, int var3, int var4, int var5);
}

