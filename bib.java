/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;

public class bib
extends bid {
    private static bib e = new bib();
    private FloatBuffer f = avd.h(16);
    private FloatBuffer g = avd.h(16);
    private FloatBuffer h = avd.h(16);

    public static bid a() {
        e.b();
        return e;
    }

    private void a(float[] fArray) {
        float f2 = ns.c(fArray[0] * fArray[0] + fArray[1] * fArray[1] + fArray[2] * fArray[2]);
        fArray[0] = fArray[0] / f2;
        fArray[1] = fArray[1] / f2;
        fArray[2] = fArray[2] / f2;
        fArray[3] = fArray[3] / f2;
    }

    public void b() {
        this.f.clear();
        this.g.clear();
        this.h.clear();
        bfl.a(2983, this.f);
        bfl.a(2982, this.g);
        float[] fArray = this.b;
        \u2603 = this.c;
        this.f.flip().limit(16);
        this.f.get(fArray);
        this.g.flip().limit(16);
        this.g.get(\u2603);
        this.d[0] = \u2603[0] * fArray[0] + \u2603[1] * fArray[4] + \u2603[2] * fArray[8] + \u2603[3] * fArray[12];
        this.d[1] = \u2603[0] * fArray[1] + \u2603[1] * fArray[5] + \u2603[2] * fArray[9] + \u2603[3] * fArray[13];
        this.d[2] = \u2603[0] * fArray[2] + \u2603[1] * fArray[6] + \u2603[2] * fArray[10] + \u2603[3] * fArray[14];
        this.d[3] = \u2603[0] * fArray[3] + \u2603[1] * fArray[7] + \u2603[2] * fArray[11] + \u2603[3] * fArray[15];
        this.d[4] = \u2603[4] * fArray[0] + \u2603[5] * fArray[4] + \u2603[6] * fArray[8] + \u2603[7] * fArray[12];
        this.d[5] = \u2603[4] * fArray[1] + \u2603[5] * fArray[5] + \u2603[6] * fArray[9] + \u2603[7] * fArray[13];
        this.d[6] = \u2603[4] * fArray[2] + \u2603[5] * fArray[6] + \u2603[6] * fArray[10] + \u2603[7] * fArray[14];
        this.d[7] = \u2603[4] * fArray[3] + \u2603[5] * fArray[7] + \u2603[6] * fArray[11] + \u2603[7] * fArray[15];
        this.d[8] = \u2603[8] * fArray[0] + \u2603[9] * fArray[4] + \u2603[10] * fArray[8] + \u2603[11] * fArray[12];
        this.d[9] = \u2603[8] * fArray[1] + \u2603[9] * fArray[5] + \u2603[10] * fArray[9] + \u2603[11] * fArray[13];
        this.d[10] = \u2603[8] * fArray[2] + \u2603[9] * fArray[6] + \u2603[10] * fArray[10] + \u2603[11] * fArray[14];
        this.d[11] = \u2603[8] * fArray[3] + \u2603[9] * fArray[7] + \u2603[10] * fArray[11] + \u2603[11] * fArray[15];
        this.d[12] = \u2603[12] * fArray[0] + \u2603[13] * fArray[4] + \u2603[14] * fArray[8] + \u2603[15] * fArray[12];
        this.d[13] = \u2603[12] * fArray[1] + \u2603[13] * fArray[5] + \u2603[14] * fArray[9] + \u2603[15] * fArray[13];
        this.d[14] = \u2603[12] * fArray[2] + \u2603[13] * fArray[6] + \u2603[14] * fArray[10] + \u2603[15] * fArray[14];
        this.d[15] = \u2603[12] * fArray[3] + \u2603[13] * fArray[7] + \u2603[14] * fArray[11] + \u2603[15] * fArray[15];
        \u2603 = this.a[0];
        \u2603[0] = this.d[3] - this.d[0];
        \u2603[1] = this.d[7] - this.d[4];
        \u2603[2] = this.d[11] - this.d[8];
        \u2603[3] = this.d[15] - this.d[12];
        this.a(\u2603);
        \u2603 = this.a[1];
        \u2603[0] = this.d[3] + this.d[0];
        \u2603[1] = this.d[7] + this.d[4];
        \u2603[2] = this.d[11] + this.d[8];
        \u2603[3] = this.d[15] + this.d[12];
        this.a(\u2603);
        \u2603 = this.a[2];
        \u2603[0] = this.d[3] + this.d[1];
        \u2603[1] = this.d[7] + this.d[5];
        \u2603[2] = this.d[11] + this.d[9];
        \u2603[3] = this.d[15] + this.d[13];
        this.a(\u2603);
        \u2603 = this.a[3];
        \u2603[0] = this.d[3] - this.d[1];
        \u2603[1] = this.d[7] - this.d[5];
        \u2603[2] = this.d[11] - this.d[9];
        \u2603[3] = this.d[15] - this.d[13];
        this.a(\u2603);
        \u2603 = this.a[4];
        \u2603[0] = this.d[3] - this.d[2];
        \u2603[1] = this.d[7] - this.d[6];
        \u2603[2] = this.d[11] - this.d[10];
        \u2603[3] = this.d[15] - this.d[14];
        this.a(\u2603);
        \u2603 = this.a[5];
        \u2603[0] = this.d[3] + this.d[2];
        \u2603[1] = this.d[7] + this.d[6];
        \u2603[2] = this.d[11] + this.d[10];
        \u2603[3] = this.d[15] + this.d[14];
        this.a(\u2603);
    }
}

