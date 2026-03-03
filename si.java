/*
 * Decompiled with CFR 0.152.
 */
public class si
extends rd {
    private wi a;

    public si(wi wi2) {
        this.a = wi2;
        this.a(5);
    }

    @Override
    public boolean a() {
        if (!this.a.ai()) {
            return false;
        }
        if (this.a.V()) {
            return false;
        }
        if (!this.a.C) {
            return false;
        }
        if (this.a.G) {
            return false;
        }
        wn wn2 = this.a.v_();
        if (wn2 == null) {
            return false;
        }
        if (this.a.h(wn2) > 16.0) {
            return false;
        }
        return wn2.bk instanceof xi;
    }

    @Override
    public void c() {
        this.a.s().n();
    }

    @Override
    public void d() {
        this.a.a_((wn)null);
    }
}

