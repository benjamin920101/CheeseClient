/*
 * Decompiled with CFR 0.152.
 */
public class bbs {
    public bcg[] a;
    public int b;
    private boolean c;

    public bbs(bcg[] bcgArray) {
        this.a = bcgArray;
        this.b = bcgArray.length;
    }

    public bbs(bcg[] bcgArray, int n2, int n3, int n4, int n5, float f2, float f3) {
        this(bcgArray);
        \u2603 = 0.0f / f2;
        \u2603 = 0.0f / f3;
        bcgArray[0] = bcgArray[0].a((float)n4 / f2 - \u2603, (float)n3 / f3 + \u2603);
        bcgArray[1] = bcgArray[1].a((float)n2 / f2 + \u2603, (float)n3 / f3 + \u2603);
        bcgArray[2] = bcgArray[2].a((float)n2 / f2 + \u2603, (float)n5 / f3 - \u2603);
        bcgArray[3] = bcgArray[3].a((float)n4 / f2 - \u2603, (float)n5 / f3 - \u2603);
    }

    public void a() {
        bcg[] bcgArray = new bcg[this.a.length];
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            bcgArray[i2] = this.a[this.a.length - i2 - 1];
        }
        this.a = bcgArray;
    }

    public void a(bfd bfd2, float f2) {
        aui aui2 = this.a[1].a.a(this.a[0].a);
        \u2603 = this.a[1].a.a(this.a[2].a);
        \u2603 = \u2603.c(aui2).a();
        float \u26032 = (float)\u2603.a;
        float \u26033 = (float)\u2603.b;
        float \u26034 = (float)\u2603.c;
        if (this.c) {
            \u26032 = -\u26032;
            \u26033 = -\u26033;
            \u26034 = -\u26034;
        }
        bfd2.a(7, bms.c);
        for (int i2 = 0; i2 < 4; ++i2) {
            bcg bcg2 = this.a[i2];
            bfd2.b(bcg2.a.a * (double)f2, bcg2.a.b * (double)f2, bcg2.a.c * (double)f2).a(bcg2.b, bcg2.c).c(\u26032, \u26033, \u26034).d();
        }
        bfx.a().b();
    }
}

