/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class rw
extends rd {
    private wi a;
    private pr b;
    private double c;
    private int d;

    public rw(wi wi2, double d2) {
        this.a = wi2;
        this.c = d2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (this.a.l() >= 0) {
            return false;
        }
        if (this.a.bc().nextInt(400) != 0) {
            return false;
        }
        List<wi> list = this.a.o.a(wi.class, this.a.aR().b(6.0, 3.0, 6.0));
        double \u26032 = Double.MAX_VALUE;
        Object \u26033 = list.iterator();
        while (\u26033.hasNext()) {
            wi wi2 = \u26033.next();
            if (wi2 == this.a || wi2.cn() || wi2.l() >= 0 || (\u2603 = wi2.h(this.a)) > \u26032) continue;
            \u26032 = \u2603;
            this.b = wi2;
        }
        return this.b != null || (\u26033 = tc.a(this.a, 16, 3)) != null;
    }

    @Override
    public boolean b() {
        return this.d > 0;
    }

    @Override
    public void c() {
        if (this.b != null) {
            this.a.m(true);
        }
        this.d = 1000;
    }

    @Override
    public void d() {
        this.a.m(false);
        this.b = null;
    }

    @Override
    public void e() {
        --this.d;
        if (this.b != null) {
            if (this.a.h(this.b) > 4.0) {
                this.a.s().a(this.b, this.c);
            }
        } else if (this.a.s().m()) {
            aui aui2 = tc.a(this.a, 16, 3);
            if (aui2 == null) {
                return;
            }
            this.a.s().a(aui2.a, aui2.b, aui2.c, this.c);
        }
    }
}

