/*
 * Decompiled with CFR 0.152.
 */
public class bas
extends bbj {
    public bas() {
        this(0.0f);
    }

    public bas(float f2) {
        this(f2, 64, 32);
    }

    protected bas(float f2, int n2, int n3) {
        super(f2, 0.0f, n2, n3);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        if (!(pk2 instanceof um)) {
            return;
        }
        um um2 = (um)pk2;
        this.e.f = (float)Math.PI / 180 * um2.t().b();
        this.e.g = (float)Math.PI / 180 * um2.t().c();
        this.e.h = (float)Math.PI / 180 * um2.t().d();
        this.e.a(0.0f, 1.0f, 0.0f);
        this.g.f = (float)Math.PI / 180 * um2.u().b();
        this.g.g = (float)Math.PI / 180 * um2.u().c();
        this.g.h = (float)Math.PI / 180 * um2.u().d();
        this.i.f = (float)Math.PI / 180 * um2.v().b();
        this.i.g = (float)Math.PI / 180 * um2.v().c();
        this.i.h = (float)Math.PI / 180 * um2.v().d();
        this.h.f = (float)Math.PI / 180 * um2.w().b();
        this.h.g = (float)Math.PI / 180 * um2.w().c();
        this.h.h = (float)Math.PI / 180 * um2.w().d();
        this.k.f = (float)Math.PI / 180 * um2.x().b();
        this.k.g = (float)Math.PI / 180 * um2.x().c();
        this.k.h = (float)Math.PI / 180 * um2.x().d();
        this.k.a(1.9f, 11.0f, 0.0f);
        this.j.f = (float)Math.PI / 180 * um2.y().b();
        this.j.g = (float)Math.PI / 180 * um2.y().c();
        this.j.h = (float)Math.PI / 180 * um2.y().d();
        this.j.a(-1.9f, 11.0f, 0.0f);
        bas.a(this.e, this.f);
    }
}

