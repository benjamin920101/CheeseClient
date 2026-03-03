/*
 * Decompiled with CFR 0.152.
 */
public class bbn
extends bbo {
    public bct[] a = new bct[7];

    public bbn() {
        this.a[0] = new bct(this, 0, 10);
        this.a[1] = new bct(this, 0, 0);
        this.a[2] = new bct(this, 0, 0);
        this.a[3] = new bct(this, 0, 0);
        this.a[4] = new bct(this, 0, 0);
        this.a[5] = new bct(this, 44, 10);
        int n2 = 20;
        \u2603 = 8;
        \u2603 = 16;
        \u2603 = 4;
        this.a[0].a((float)(-n2 / 2), (float)(-\u2603 / 2), -1.0f, n2, \u2603, 2, 0.0f);
        this.a[0].a(0.0f, \u2603, 0.0f);
        this.a[5].a((float)(-n2 / 2 + 1), (float)(-\u2603 / 2 + 1), -1.0f, n2 - 2, \u2603 - 2, 1, 0.0f);
        this.a[5].a(0.0f, \u2603, 0.0f);
        this.a[1].a((float)(-n2 / 2 + 2), (float)(-\u2603 - 1), -1.0f, n2 - 4, \u2603, 2, 0.0f);
        this.a[1].a(-n2 / 2 + 1, \u2603, 0.0f);
        this.a[2].a((float)(-n2 / 2 + 2), (float)(-\u2603 - 1), -1.0f, n2 - 4, \u2603, 2, 0.0f);
        this.a[2].a(n2 / 2 - 1, \u2603, 0.0f);
        this.a[3].a((float)(-n2 / 2 + 2), (float)(-\u2603 - 1), -1.0f, n2 - 4, \u2603, 2, 0.0f);
        this.a[3].a(0.0f, \u2603, -\u2603 / 2 + 1);
        this.a[4].a((float)(-n2 / 2 + 2), (float)(-\u2603 - 1), -1.0f, n2 - 4, \u2603, 2, 0.0f);
        this.a[4].a(0.0f, \u2603, \u2603 / 2 - 1);
        this.a[0].f = 1.5707964f;
        this.a[1].g = 4.712389f;
        this.a[2].g = 1.5707964f;
        this.a[3].g = (float)Math.PI;
        this.a[5].f = -1.5707964f;
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a[5].d = 4.0f - f4;
        for (int i2 = 0; i2 < 6; ++i2) {
            this.a[i2].a(f7);
        }
    }
}

