/*
 * Decompiled with CFR 0.152.
 */
public class bby
extends bbo {
    private bct[] a;
    private bct[] b;
    private float[] c = new float[7];
    private static final int[][] d = new int[][]{{3, 2, 2}, {4, 3, 2}, {6, 4, 3}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
    private static final int[][] e = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

    public bby() {
        this.a = new bct[7];
        float f2 = -3.5f;
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = new bct(this, e[i2][0], e[i2][1]);
            this.a[i2].a((float)d[i2][0] * -0.5f, 0.0f, (float)d[i2][2] * -0.5f, d[i2][0], d[i2][1], d[i2][2]);
            this.a[i2].a(0.0f, 24 - d[i2][1], f2);
            this.c[i2] = f2;
            if (i2 >= this.a.length - 1) continue;
            f2 += (float)(d[i2][2] + d[i2 + 1][2]) * 0.5f;
        }
        this.b = new bct[3];
        this.b[0] = new bct(this, 20, 0);
        this.b[0].a(-5.0f, 0.0f, (float)d[2][2] * -0.5f, 10, 8, d[2][2]);
        this.b[0].a(0.0f, 16.0f, this.c[2]);
        this.b[1] = new bct(this, 20, 11);
        this.b[1].a(-3.0f, 0.0f, (float)d[4][2] * -0.5f, 6, 4, d[4][2]);
        this.b[1].a(0.0f, 20.0f, this.c[4]);
        this.b[2] = new bct(this, 20, 18);
        this.b[2].a(-3.0f, 0.0f, (float)d[4][2] * -0.5f, 6, 5, d[1][2]);
        this.b[2].a(0.0f, 19.0f, this.c[1]);
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        int n2;
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        for (n2 = 0; n2 < this.a.length; ++n2) {
            this.a[n2].a(f7);
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            this.b[n2].a(f7);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2].g = ns.b(f4 * 0.9f + (float)i2 * 0.15f * (float)Math.PI) * (float)Math.PI * 0.05f * (float)(1 + Math.abs(i2 - 2));
            this.a[i2].c = ns.a(f4 * 0.9f + (float)i2 * 0.15f * (float)Math.PI) * (float)Math.PI * 0.2f * (float)Math.abs(i2 - 2);
        }
        this.b[0].g = this.a[2].g;
        this.b[1].g = this.a[4].g;
        this.b[1].c = this.a[4].c;
        this.b[2].g = this.a[1].g;
        this.b[2].c = this.a[1].c;
    }
}

