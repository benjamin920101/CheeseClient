/*
 * Decompiled with CFR 0.152.
 */
public class ok {
    private final oj a;
    private final float b;

    public ok(oj oj2, long l2, long l3, float f2) {
        this.a = oj2;
        this.b = this.a(oj2, l2, l3, f2);
    }

    public float b() {
        return this.b;
    }

    public float c() {
        if (this.b < 2.0f) {
            return 0.0f;
        }
        if (this.b > 4.0f) {
            return 1.0f;
        }
        return (this.b - 2.0f) / 2.0f;
    }

    private float a(oj oj2, long l2, long l3, float f2) {
        if (oj2 == oj.a) {
            return 0.0f;
        }
        boolean bl2 = oj2 == oj.d;
        float \u26032 = 0.75f;
        float \u26033 = ns.a(((float)l2 + -72000.0f) / 1440000.0f, 0.0f, 1.0f) * 0.25f;
        \u26032 += \u26033;
        float \u26034 = 0.0f;
        \u26034 += ns.a((float)l3 / 3600000.0f, 0.0f, 1.0f) * (bl2 ? 1.0f : 0.75f);
        \u26034 += ns.a(f2 * 0.25f, 0.0f, \u26033);
        if (oj2 == oj.b) {
            \u26034 *= 0.5f;
        }
        return (float)oj2.a() * (\u26032 += \u26034);
    }
}

