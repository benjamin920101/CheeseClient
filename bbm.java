/*
 * Decompiled with CFR 0.152.
 */
public class bbm
extends bbo {
    public bct a;

    public bbm() {
        this(0, 0, 32, 32);
    }

    public bbm(int n2, int n3, int n4, int n5) {
        this.t = n4;
        this.u = n5;
        this.a = new bct(this, n2, n3);
        this.a.a(-3.0f, -6.0f, -3.0f, 6, 8, 6, 0.0f);
        this.a.a(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.a(f7);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.g = f5 / 57.295776f;
        this.a.f = f6 / 57.295776f;
    }
}

