/*
 * Decompiled with CFR 0.152.
 */
public class se
extends rd {
    private qa a;
    private boolean b;

    public se(qa qa2) {
        this.a = qa2;
        this.a(5);
    }

    @Override
    public boolean a() {
        if (!this.a.cl()) {
            return false;
        }
        if (this.a.V()) {
            return false;
        }
        if (!this.a.C) {
            return false;
        }
        pr pr2 = this.a.co();
        if (pr2 == null) {
            return true;
        }
        if (this.a.h(pr2) < 144.0 && pr2.bd() != null) {
            return false;
        }
        return this.b;
    }

    @Override
    public void c() {
        this.a.s().n();
        this.a.n(true);
    }

    @Override
    public void d() {
        this.a.n(false);
    }

    public void a(boolean bl2) {
        this.b = bl2;
    }
}

