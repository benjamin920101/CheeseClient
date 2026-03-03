/*
 * Decompiled with CFR 0.152.
 */
public class bck
extends bci {
    public boolean g;
    private bct h = new bct(this).b(64, 128);
    private bct i;

    public bck(float f2) {
        super(f2, 0.0f, 64, 128);
        this.h.a(0.0f, -2.0f, 0.0f);
        this.h.a(0, 0).a(0.0f, 3.0f, -6.75f, 1, 1, 1, -0.25f);
        this.f.a(this.h);
        this.i = new bct(this).b(64, 128);
        this.i.a(-5.0f, -10.03125f, -5.0f);
        this.i.a(0, 64).a(0.0f, 0.0f, 0.0f, 10, 2, 10);
        this.a.a(this.i);
        bct bct2 = new bct(this).b(64, 128);
        bct2.a(1.75f, -4.0f, 2.0f);
        bct2.a(0, 76).a(0.0f, 0.0f, 0.0f, 7, 4, 7);
        bct2.f = -0.05235988f;
        bct2.h = 0.02617994f;
        this.i.a(bct2);
        \u2603 = new bct(this).b(64, 128);
        \u2603.a(1.75f, -4.0f, 2.0f);
        \u2603.a(0, 87).a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        \u2603.f = -0.10471976f;
        \u2603.h = 0.05235988f;
        bct2.a(\u2603);
        \u2603 = new bct(this).b(64, 128);
        \u2603.a(1.75f, -2.0f, 2.0f);
        \u2603.a(0, 95).a(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.25f);
        \u2603.f = -0.20943952f;
        \u2603.h = 0.10471976f;
        \u2603.a(\u2603);
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        this.f.q = 0.0f;
        this.f.p = 0.0f;
        this.f.o = 0.0f;
        float f8 = 0.01f * (float)(pk2.F() % 10);
        this.f.f = ns.a((float)pk2.W * f8) * 4.5f * (float)Math.PI / 180.0f;
        this.f.g = 0.0f;
        this.f.h = ns.b((float)pk2.W * f8) * 2.5f * (float)Math.PI / 180.0f;
        if (this.g) {
            this.f.f = -0.9f;
            this.f.q = -0.09375f;
            this.f.p = 0.1875f;
        }
    }
}

