/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;

public class bgn
extends bgg {
    private final bmi d;

    public bgn(bgg bgg2, bmi bmi2) {
        super(Arrays.copyOf(bgg2.a(), bgg2.a().length), bgg2.b, bgo.a(bgg2.a()));
        this.d = bmi2;
        this.e();
    }

    private void e() {
        for (int i2 = 0; i2 < 4; ++i2) {
            this.a(i2);
        }
    }

    private void a(int n2) {
        \u2603 = 7 * n2;
        float f2 = Float.intBitsToFloat(this.a[\u2603]);
        \u2603 = Float.intBitsToFloat(this.a[\u2603 + 1]);
        \u2603 = Float.intBitsToFloat(this.a[\u2603 + 2]);
        \u2603 = 0.0f;
        \u2603 = 0.0f;
        switch (this.c) {
            case a: {
                \u2603 = f2 * 16.0f;
                \u2603 = (1.0f - \u2603) * 16.0f;
                break;
            }
            case b: {
                \u2603 = f2 * 16.0f;
                \u2603 = \u2603 * 16.0f;
                break;
            }
            case c: {
                \u2603 = (1.0f - f2) * 16.0f;
                \u2603 = (1.0f - \u2603) * 16.0f;
                break;
            }
            case d: {
                \u2603 = f2 * 16.0f;
                \u2603 = (1.0f - \u2603) * 16.0f;
                break;
            }
            case e: {
                \u2603 = \u2603 * 16.0f;
                \u2603 = (1.0f - \u2603) * 16.0f;
                break;
            }
            case f: {
                \u2603 = (1.0f - \u2603) * 16.0f;
                \u2603 = (1.0f - \u2603) * 16.0f;
            }
        }
        this.a[\u2603 + 4] = Float.floatToRawIntBits(this.d.a(\u2603));
        this.a[\u2603 + 4 + 1] = Float.floatToRawIntBits(this.d.b(\u2603));
    }
}

