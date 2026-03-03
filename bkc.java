/*
 * Decompiled with CFR 0.152.
 */
public class bkc<T extends pk>
extends biv<T> {
    protected final zw a;
    private final bjh e;

    public bkc(biu biu2, zw zw2, bjh bjh2) {
        super(biu2);
        this.a = zw2;
        this.e = bjh2;
    }

    @Override
    public void a(T t2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        bfl.B();
        bfl.a(0.5f, 0.5f, 0.5f);
        bfl.b(-this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(this.b.f, 1.0f, 0.0f, 0.0f);
        this.a(bmh.g);
        this.e.a(this.d(t2), bgr.b.f);
        bfl.C();
        bfl.F();
        super.a(t2, d2, d3, d4, f2, f3);
    }

    public zx d(T t2) {
        return new zx(this.a, 1, 0);
    }

    @Override
    protected jy a(pk pk2) {
        return bmh.g;
    }
}

