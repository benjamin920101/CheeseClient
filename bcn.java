/*
 * Decompiled with CFR 0.152.
 */
public class bcn
extends bbj {
    public bcn() {
        this(0.0f, false);
    }

    protected bcn(float f2, float f3, int n2, int n3) {
        super(f2, f3, n2, n3);
    }

    public bcn(float f2, boolean bl2) {
        super(f2, 0.0f, 64, bl2 ? 32 : 64);
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

