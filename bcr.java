/*
 * Decompiled with CFR 0.152.
 */
public class bcr {
    private bcg[] h;
    private bbs[] i;
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public String g;

    public bcr(bct bct2, int n2, int n3, float f2, float f3, float f4, int n4, int n5, int n6, float f5) {
        this(bct2, n2, n3, f2, f3, f4, n4, n5, n6, f5, bct2.i);
    }

    public bcr(bct bct2, int n2, int n3, float f2, float f3, float f4, int n4, int n5, int n6, float f5, boolean bl2) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
        this.d = f2 + (float)n4;
        this.e = f3 + (float)n5;
        this.f = f4 + (float)n6;
        this.h = new bcg[8];
        this.i = new bbs[6];
        float f6 = f2 + (float)n4;
        \u2603 = f3 + (float)n5;
        \u2603 = f4 + (float)n6;
        f2 -= f5;
        f3 -= f5;
        f4 -= f5;
        f6 += f5;
        \u2603 += f5;
        \u2603 += f5;
        if (bl2) {
            \u2603 = f6;
            f6 = f2;
            f2 = \u2603;
        }
        bcg \u26032 = new bcg(f2, f3, f4, 0.0f, 0.0f);
        bcg \u26033 = new bcg(f6, f3, f4, 0.0f, 8.0f);
        bcg \u26034 = new bcg(f6, \u2603, f4, 8.0f, 8.0f);
        bcg \u26035 = new bcg(f2, \u2603, f4, 8.0f, 0.0f);
        bcg \u26036 = new bcg(f2, f3, \u2603, 0.0f, 0.0f);
        bcg \u26037 = new bcg(f6, f3, \u2603, 0.0f, 8.0f);
        bcg \u26038 = new bcg(f6, \u2603, \u2603, 8.0f, 8.0f);
        bcg \u26039 = new bcg(f2, \u2603, \u2603, 8.0f, 0.0f);
        this.h[0] = \u26032;
        this.h[1] = \u26033;
        this.h[2] = \u26034;
        this.h[3] = \u26035;
        this.h[4] = \u26036;
        this.h[5] = \u26037;
        this.h[6] = \u26038;
        this.h[7] = \u26039;
        this.i[0] = new bbs(new bcg[]{\u26037, \u26033, \u26034, \u26038}, n2 + n6 + n4, n3 + n6, n2 + n6 + n4 + n6, n3 + n6 + n5, bct2.a, bct2.b);
        this.i[1] = new bbs(new bcg[]{\u26032, \u26036, \u26039, \u26035}, n2, n3 + n6, n2 + n6, n3 + n6 + n5, bct2.a, bct2.b);
        this.i[2] = new bbs(new bcg[]{\u26037, \u26036, \u26032, \u26033}, n2 + n6, n3, n2 + n6 + n4, n3 + n6, bct2.a, bct2.b);
        this.i[3] = new bbs(new bcg[]{\u26034, \u26035, \u26039, \u26038}, n2 + n6 + n4, n3 + n6, n2 + n6 + n4 + n4, n3, bct2.a, bct2.b);
        this.i[4] = new bbs(new bcg[]{\u26033, \u26032, \u26035, \u26034}, n2 + n6, n3 + n6, n2 + n6 + n4, n3 + n6 + n5, bct2.a, bct2.b);
        this.i[5] = new bbs(new bcg[]{\u26036, \u26037, \u26038, \u26039}, n2 + n6 + n4 + n6, n3 + n6, n2 + n6 + n4 + n6 + n4, n3 + n6 + n5, bct2.a, bct2.b);
        if (bl2) {
            for (int i2 = 0; i2 < this.i.length; ++i2) {
                this.i[i2].a();
            }
        }
    }

    public void a(bfd bfd2, float f2) {
        for (int i2 = 0; i2 < this.i.length; ++i2) {
            this.i[i2].a(bfd2, f2);
        }
    }

    public bcr a(String string) {
        this.g = string;
        return this;
    }
}

