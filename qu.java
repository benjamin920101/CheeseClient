/*
 * Decompiled with CFR 0.152.
 */
public class qu
extends qx {
    private int g;
    private int h = -1;

    public qu(ps ps2) {
        super(ps2);
    }

    @Override
    public boolean a() {
        if (!super.a()) {
            return false;
        }
        if (!this.a.o.Q().b("mobGriefing")) {
            return false;
        }
        return !agh.f(this.a.o, this.b);
    }

    @Override
    public void c() {
        super.c();
        this.g = 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean b() {
        double d2 = this.a.b(this.b);
        if (this.g > 240) return false;
        if (agh.f(this.a.o, this.b)) return false;
        if (!(d2 < 4.0)) return false;
        return true;
    }

    @Override
    public void d() {
        super.d();
        this.a.o.c(this.a.F(), this.b, -1);
    }

    @Override
    public void e() {
        super.e();
        if (this.a.bc().nextInt(20) == 0) {
            this.a.o.b(1010, this.b, 0);
        }
        ++this.g;
        int n2 = (int)((float)this.g / 240.0f * 10.0f);
        if (n2 != this.h) {
            this.a.o.c(this.a.F(), this.b, n2);
            this.h = n2;
        }
        if (this.g == 240 && this.a.o.aa() == oj.d) {
            this.a.o.g(this.b);
            this.a.o.b(1012, this.b, 0);
            this.a.o.b(2001, this.b, afh.a(this.c));
        }
    }
}

