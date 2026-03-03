/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class rc
extends rd {
    tm a;
    tm b;
    double c;
    private int d;

    public rc(tm tm2, double d2) {
        this.a = tm2;
        this.c = d2;
    }

    @Override
    public boolean a() {
        if (this.a.l() >= 0) {
            return false;
        }
        List<?> list = this.a.o.a(this.a.getClass(), this.a.aR().b(8.0, 4.0, 8.0));
        tm \u26032 = null;
        double \u26033 = Double.MAX_VALUE;
        for (tm tm2 : list) {
            if (tm2.l() < 0 || (\u2603 = this.a.h(tm2)) > \u26033) continue;
            \u26033 = \u2603;
            \u26032 = tm2;
        }
        if (\u26032 == null) {
            return false;
        }
        if (\u26033 < 9.0) {
            return false;
        }
        this.b = \u26032;
        return true;
    }

    @Override
    public boolean b() {
        if (this.a.l() >= 0) {
            return false;
        }
        if (!this.b.ai()) {
            return false;
        }
        double d2 = this.a.h(this.b);
        return !(d2 < 9.0) && !(d2 > 256.0);
    }

    @Override
    public void c() {
        this.d = 0;
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (--this.d > 0) {
            return;
        }
        this.d = 10;
        this.a.s().a(this.b, this.c);
    }
}

