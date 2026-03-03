/*
 * Decompiled with CFR 0.152.
 */
public class bcf
extends bbo {
    bct a;
    bct[] b = new bct[8];

    public bcf() {
        int n2 = -16;
        this.a = new bct(this, 0, 0);
        this.a.a(-6.0f, -8.0f, -6.0f, 12, 16, 12);
        this.a.d += (float)(24 + n2);
        for (\u2603 = 0; \u2603 < this.b.length; ++\u2603) {
            this.b[\u2603] = new bct(this, 48, 0);
            double d2 = (double)\u2603 * Math.PI * 2.0 / (double)this.b.length;
            float \u26032 = (float)Math.cos(d2) * 5.0f;
            float \u26033 = (float)Math.sin(d2) * 5.0f;
            this.b[\u2603].a(-1.0f, 0.0f, -1.0f, 2, 18, 2);
            this.b[\u2603].c = \u26032;
            this.b[\u2603].e = \u26033;
            this.b[\u2603].d = 31 + n2;
            d2 = (double)\u2603 * Math.PI * -2.0 / (double)this.b.length + 1.5707963267948966;
            this.b[\u2603].g = (float)d2;
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        for (bct bct2 : this.b) {
            bct2.f = f4;
        }
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        this.a.a(f7);
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2].a(f7);
        }
    }
}

