/*
 * Decompiled with CFR 0.152.
 */
public class sf
extends rd {
    vn a;
    pr b;

    public sf(vn vn2) {
        this.a = vn2;
        this.a(1);
    }

    @Override
    public boolean a() {
        pr pr2 = this.a.u();
        return this.a.cm() > 0 || pr2 != null && this.a.h(pr2) < 9.0;
    }

    @Override
    public void c() {
        this.a.s().n();
        this.b = this.a.u();
    }

    @Override
    public void d() {
        this.b = null;
    }

    @Override
    public void e() {
        if (this.b == null) {
            this.a.a(-1);
            return;
        }
        if (this.a.h(this.b) > 49.0) {
            this.a.a(-1);
            return;
        }
        if (!this.a.t().a(this.b)) {
            this.a.a(-1);
            return;
        }
        this.a.a(1);
    }
}

