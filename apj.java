/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.Random;

public class apj
extends aot {
    private final alz a;
    private final int b;
    private final Predicate<alz> c;

    public apj(alz alz2, int n2) {
        this(alz2, n2, amg.a(afi.b));
    }

    public apj(alz alz2, int n2, Predicate<alz> predicate) {
        this.a = alz2;
        this.b = n2;
        this.c = predicate;
    }

    @Override
    public boolean b(adm adm2, Random random, cj cj2) {
        float f2 = random.nextFloat() * (float)Math.PI;
        double \u26032 = (float)(cj2.n() + 8) + ns.a(f2) * (float)this.b / 8.0f;
        double \u26033 = (float)(cj2.n() + 8) - ns.a(f2) * (float)this.b / 8.0f;
        double \u26034 = (float)(cj2.p() + 8) + ns.b(f2) * (float)this.b / 8.0f;
        double \u26035 = (float)(cj2.p() + 8) - ns.b(f2) * (float)this.b / 8.0f;
        double \u26036 = cj2.o() + random.nextInt(3) - 2;
        double \u26037 = cj2.o() + random.nextInt(3) - 2;
        for (int i2 = 0; i2 < this.b; ++i2) {
            float f3 = (float)i2 / (float)this.b;
            double \u26038 = \u26032 + (\u26033 - \u26032) * (double)f3;
            double \u26039 = \u26036 + (\u26037 - \u26036) * (double)f3;
            double \u260310 = \u26034 + (\u26035 - \u26034) * (double)f3;
            double \u260311 = random.nextDouble() * (double)this.b / 16.0;
            double \u260312 = (double)(ns.a((float)Math.PI * f3) + 1.0f) * \u260311 + 1.0;
            double \u260313 = (double)(ns.a((float)Math.PI * f3) + 1.0f) * \u260311 + 1.0;
            int \u260314 = ns.c(\u26038 - \u260312 / 2.0);
            int \u260315 = ns.c(\u26039 - \u260313 / 2.0);
            int \u260316 = ns.c(\u260310 - \u260312 / 2.0);
            int \u260317 = ns.c(\u26038 + \u260312 / 2.0);
            int \u260318 = ns.c(\u26039 + \u260313 / 2.0);
            int \u260319 = ns.c(\u260310 + \u260312 / 2.0);
            for (int i3 = \u260314; i3 <= \u260317; ++i3) {
                double d2 = ((double)i3 + 0.5 - \u26038) / (\u260312 / 2.0);
                if (!(d2 * d2 < 1.0)) continue;
                for (int i4 = \u260315; i4 <= \u260318; ++i4) {
                    double d3 = ((double)i4 + 0.5 - \u26039) / (\u260313 / 2.0);
                    if (!(d2 * d2 + d3 * d3 < 1.0)) continue;
                    for (int i5 = \u260316; i5 <= \u260319; ++i5) {
                        double d4 = ((double)i5 + 0.5 - \u260310) / (\u260312 / 2.0);
                        if (!(d2 * d2 + d3 * d3 + d4 * d4 < 1.0) || !this.c.apply(adm2.p(\u2603 = new cj(i3, i4, i5)))) continue;
                        adm2.a(\u2603, this.a, 2);
                    }
                }
            }
        }
        return true;
    }
}

