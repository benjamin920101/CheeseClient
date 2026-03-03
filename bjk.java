/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bjk
extends biv<uv> {
    public bjk(biu biu2) {
        super(biu2);
    }

    @Override
    public void a(uv uv2, double d2, double d3, double d4, float f2, float f3) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        bfl.x();
        bfl.f();
        bfl.l();
        bfl.b(770, 1);
        double[] \u26033 = new double[8];
        double[] \u26034 = new double[8];
        double \u26035 = 0.0;
        double \u26036 = 0.0;
        Random \u26037 = new Random(uv2.a);
        for (int i2 = 7; i2 >= 0; --i2) {
            \u26033[i2] = \u26035;
            \u26034[i2] = \u26036;
            \u26035 += (double)(\u26037.nextInt(11) - 5);
            \u26036 += (double)(\u26037.nextInt(11) - 5);
        }
        for (int i3 = 0; i3 < 4; ++i3) {
            Random random = new Random(uv2.a);
            for (int i4 = 0; i4 < 3; ++i4) {
                \u2603 = 7;
                \u2603 = 0;
                if (i4 > 0) {
                    \u2603 = 7 - i4;
                }
                if (i4 > 0) {
                    \u2603 = \u2603 - 2;
                }
                double d5 = \u26033[\u2603] - \u26035;
                \u2603 = \u26034[\u2603] - \u26036;
                for (int i5 = \u2603; i5 >= \u2603; --i5) {
                    double d6 = d5;
                    \u2603 = \u2603;
                    if (i4 == 0) {
                        d5 += (double)(random.nextInt(11) - 5);
                        \u2603 += (double)(random.nextInt(11) - 5);
                    } else {
                        d5 += (double)(random.nextInt(31) - 15);
                        \u2603 += (double)(random.nextInt(31) - 15);
                    }
                    \u26032.a(5, bms.f);
                    float \u26038 = 0.5f;
                    float \u26039 = 0.45f;
                    float \u260310 = 0.45f;
                    float \u260311 = 0.5f;
                    \u2603 = 0.1 + (double)i3 * 0.2;
                    if (i4 == 0) {
                        \u2603 *= (double)i5 * 0.1 + 1.0;
                    }
                    \u2603 = 0.1 + (double)i3 * 0.2;
                    if (i4 == 0) {
                        \u2603 *= (double)(i5 - 1) * 0.1 + 1.0;
                    }
                    for (int i6 = 0; i6 < 5; ++i6) {
                        double d7 = d2 + 0.5 - \u2603;
                        \u2603 = d4 + 0.5 - \u2603;
                        if (i6 == 1 || i6 == 2) {
                            d7 += \u2603 * 2.0;
                        }
                        if (i6 == 2 || i6 == 3) {
                            \u2603 += \u2603 * 2.0;
                        }
                        \u2603 = d2 + 0.5 - \u2603;
                        \u2603 = d4 + 0.5 - \u2603;
                        if (i6 == 1 || i6 == 2) {
                            \u2603 += \u2603 * 2.0;
                        }
                        if (i6 == 2 || i6 == 3) {
                            \u2603 += \u2603 * 2.0;
                        }
                        \u26032.b(\u2603 + d5, d3 + (double)(i5 * 16), \u2603 + \u2603).a(0.45f, 0.45f, 0.5f, 0.3f).d();
                        \u26032.b(d7 + d6, d3 + (double)((i5 + 1) * 16), \u2603 + \u2603).a(0.45f, 0.45f, 0.5f, 0.3f).d();
                    }
                    bfx2.b();
                }
            }
        }
        bfl.k();
        bfl.e();
        bfl.w();
    }

    @Override
    protected jy a(uv uv2) {
        return null;
    }
}

