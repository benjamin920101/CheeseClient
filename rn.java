/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class rn
extends rd {
    private py a;
    private double b;
    private asx c;
    private te d;
    private boolean e;
    private List<te> f = Lists.newArrayList();

    public rn(py py2, double d2, boolean bl2) {
        this.a = py2;
        this.b = d2;
        this.e = bl2;
        this.a(1);
        if (!(py2.s() instanceof sv)) {
            throw new IllegalArgumentException("Unsupported mob for MoveThroughVillageGoal");
        }
    }

    @Override
    public boolean a() {
        this.f();
        if (this.e && this.a.o.w()) {
            return false;
        }
        tf tf2 = this.a.o.ae().a(new cj(this.a), 0);
        if (tf2 == null) {
            return false;
        }
        this.d = this.a(tf2);
        if (this.d == null) {
            return false;
        }
        sv \u26032 = (sv)this.a.s();
        boolean \u26033 = \u26032.g();
        \u26032.b(false);
        this.c = \u26032.a(this.d.d());
        \u26032.b(\u26033);
        if (this.c != null) {
            return true;
        }
        aui \u26034 = tc.a(this.a, 10, 7, new aui(this.d.d().n(), this.d.d().o(), this.d.d().p()));
        if (\u26034 == null) {
            return false;
        }
        \u26032.b(false);
        this.c = this.a.s().a(\u26034.a, \u26034.b, \u26034.c);
        \u26032.b(\u26033);
        return this.c != null;
    }

    @Override
    public boolean b() {
        if (this.a.s().m()) {
            return false;
        }
        float f2 = this.a.J + 4.0f;
        return this.a.b(this.d.d()) > (double)(f2 * f2);
    }

    @Override
    public void c() {
        this.a.s().a(this.c, this.b);
    }

    @Override
    public void d() {
        if (this.a.s().m() || this.a.b(this.d.d()) < 16.0) {
            this.f.add(this.d);
        }
    }

    private te a(tf tf2) {
        te \u26034 = null;
        int \u26032 = Integer.MAX_VALUE;
        List<te> \u26033 = tf2.f();
        for (te te2 : \u26033) {
            int n2 = te2.b(ns.c(this.a.s), ns.c(this.a.t), ns.c(this.a.u));
            if (n2 >= \u26032 || this.a(te2)) continue;
            \u26034 = te2;
            \u26032 = n2;
        }
        return \u26034;
    }

    private boolean a(te te2) {
        for (te te3 : this.f) {
            if (!te2.d().equals(te3.d())) continue;
            return true;
        }
        return false;
    }

    private void f() {
        if (this.f.size() > 15) {
            this.f.remove(0);
        }
    }
}

