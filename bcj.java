/*
 * Decompiled with CFR 0.152.
 */
public class bcj
extends bbj {
    public bcj() {
        this(0.0f, 0.0f, false);
    }

    public bcj(float f2, float f3, boolean bl2) {
        super(f2, 0.0f, 64, bl2 ? 32 : 64);
        if (bl2) {
            this.e = new bct(this, 0, 0);
            this.e.a(-4.0f, -10.0f, -4.0f, 8, 8, 8, f2);
            this.e.a(0.0f, 0.0f + f3, 0.0f);
        } else {
            this.e = new bct(this);
            this.e.a(0.0f, 0.0f + f3, 0.0f);
            this.e.a(0, 32).a(-4.0f, -10.0f, -4.0f, 8, 10, 8, f2);
            this.e.a(24, 32).a(-1.0f, -3.0f, -6.0f, 2, 4, 2, f2);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        super.a(f2, f3, f4, f5, f6, f7, pk2);
        float f8 = ns.a(this.p * (float)Math.PI);
        \u2603 = ns.a((1.0f - (1.0f - this.p) * (1.0f - this.p)) * (float)Math.PI);
        this.h.h = 0.0f;
        this.i.h = 0.0f;
        this.h.g = -(0.1f - f8 * 0.6f);
        this.i.g = 0.1f - f8 * 0.6f;
        this.h.f = -1.5707964f;
        this.i.f = -1.5707964f;
        this.h.f -= f8 * 1.2f - \u2603 * 0.4f;
        this.i.f -= f8 * 1.2f - \u2603 * 0.4f;
        this.h.h += ns.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.i.h -= ns.b(f4 * 0.09f) * 0.05f + 0.05f;
        this.h.f += ns.a(f4 * 0.067f) * 0.05f;
        this.i.f -= ns.a(f4 * 0.067f) * 0.05f;
    }
}

