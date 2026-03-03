/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class qz
extends rd {
    private py a;
    private double b;
    private double c;
    private double d;
    private double e;
    private adm f;

    public qz(py py2, double d2) {
        this.a = py2;
        this.e = d2;
        this.f = py2.o;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (!this.f.w()) {
            return false;
        }
        if (!this.a.at()) {
            return false;
        }
        if (!this.f.i(new cj(this.a.s, this.a.aR().b, this.a.u))) {
            return false;
        }
        aui aui2 = this.f();
        if (aui2 == null) {
            return false;
        }
        this.b = aui2.a;
        this.c = aui2.b;
        this.d = aui2.c;
        return true;
    }

    @Override
    public boolean b() {
        return !this.a.s().m();
    }

    @Override
    public void c() {
        this.a.s().a(this.b, this.c, this.d, this.e);
    }

    private aui f() {
        Random random = this.a.bc();
        cj \u26032 = new cj(this.a.s, this.a.aR().b, this.a.u);
        for (int i2 = 0; i2 < 10; ++i2) {
            cj cj2 = \u26032.a(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);
            if (this.f.i(cj2) || !(this.a.a(cj2) < 0.0f)) continue;
            return new aui(cj2.n(), cj2.o(), cj2.p());
        }
        return null;
    }
}

