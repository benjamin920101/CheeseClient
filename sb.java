/*
 * Decompiled with CFR 0.152.
 */
public class sb
extends rd {
    private py a;
    private te b;

    public sb(py py2) {
        this.a = py2;
        if (!(py2.s() instanceof sv)) {
            throw new IllegalArgumentException("Unsupported mob type for RestrictOpenDoorGoal");
        }
    }

    @Override
    public boolean a() {
        if (this.a.o.w()) {
            return false;
        }
        cj cj2 = new cj(this.a);
        tf \u26032 = this.a.o.ae().a(cj2, 16);
        if (\u26032 == null) {
            return false;
        }
        this.b = \u26032.b(cj2);
        if (this.b == null) {
            return false;
        }
        return (double)this.b.b(cj2) < 2.25;
    }

    @Override
    public boolean b() {
        if (this.a.o.w()) {
            return false;
        }
        return !this.b.i() && this.b.c(new cj(this.a));
    }

    @Override
    public void c() {
        ((sv)this.a.s()).b(false);
        ((sv)this.a.s()).c(false);
    }

    @Override
    public void d() {
        ((sv)this.a.s()).b(true);
        ((sv)this.a.s()).c(true);
        this.b = null;
    }

    @Override
    public void e() {
        this.b.b();
    }
}

