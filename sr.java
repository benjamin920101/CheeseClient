/*
 * Decompiled with CFR 0.152.
 */
public class sr
extends st {
    qa a;
    pr b;
    private int c;

    public sr(qa qa2) {
        super(qa2, false);
        this.a = qa2;
        this.a(1);
    }

    @Override
    public boolean a() {
        if (!this.a.cl()) {
            return false;
        }
        pr pr2 = this.a.co();
        if (pr2 == null) {
            return false;
        }
        this.b = pr2.bd();
        int \u26032 = pr2.be();
        return \u26032 != this.c && this.a(this.b, false) && this.a.a(this.b, pr2);
    }

    @Override
    public void c() {
        this.e.d(this.b);
        pr pr2 = this.a.co();
        if (pr2 != null) {
            this.c = pr2.be();
        }
        super.c();
    }
}

