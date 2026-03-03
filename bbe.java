/*
 * Decompiled with CFR 0.152.
 */
public class bbe
extends bbo {
    private static final int[][] a = new int[][]{{4, 3, 2}, {6, 4, 5}, {3, 3, 1}, {1, 2, 1}};
    private static final int[][] b = new int[][]{{0, 0}, {0, 5}, {0, 14}, {0, 18}};
    private static final int c = a.length;
    private final bct[] d = new bct[c];

    public bbe() {
        float f2 = -3.5f;
        for (int i2 = 0; i2 < this.d.length; ++i2) {
            this.d[i2] = new bct(this, b[i2][0], b[i2][1]);
            this.d[i2].a((float)a[i2][0] * -0.5f, 0.0f, (float)a[i2][2] * -0.5f, a[i2][0], a[i2][1], a[i2][2]);
            this.d[i2].a(0.0f, 24 - a[i2][1], f2);
            if (i2 >= this.d.length - 1) continue;
            f2 += (float)(a[i2][2] + a[i2 + 1][2]) * 0.5f;
        }
    }

    @Override
    public void a(pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.a(f2, f3, f4, f5, f6, f7, pk2);
        for (int i2 = 0; i2 < this.d.length; ++i2) {
            this.d[i2].a(f7);
        }
    }

    @Override
    public void a(float f2, float f3, float f4, float f5, float f6, float f7, pk pk2) {
        for (int i2 = 0; i2 < this.d.length; ++i2) {
            this.d[i2].g = ns.b(f4 * 0.9f + (float)i2 * 0.15f * (float)Math.PI) * (float)Math.PI * 0.01f * (float)(1 + Math.abs(i2 - 2));
            this.d[i2].c = ns.a(f4 * 0.9f + (float)i2 * 0.15f * (float)Math.PI) * (float)Math.PI * 0.1f * (float)Math.abs(i2 - 2);
        }
    }
}

