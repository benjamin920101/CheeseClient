/*
 * Decompiled with CFR 0.152.
 */
public class bke
extends bjm<vi> {
    public bke(biu biu2) {
        super(biu2);
    }

    @Override
    protected void a(vi vi22, float f2, alz alz2) {
        vi vi22;
        int n2 = vi22.l();
        if (n2 > -1 && (float)n2 - f2 + 1.0f < 10.0f) {
            float f3 = 1.0f - ((float)n2 - f2 + 1.0f) / 10.0f;
            f3 = ns.a(f3, 0.0f, 1.0f);
            f3 *= f3;
            f3 *= f3;
            \u2603 = 1.0f + f3 * 0.3f;
            bfl.a(\u2603, \u2603, \u2603);
        }
        super.a(vi22, f2, alz2);
        if (n2 > -1 && n2 / 5 % 2 == 0) {
            bgd bgd2 = ave.A().ae();
            bfl.x();
            bfl.f();
            bfl.l();
            bfl.b(770, 772);
            bfl.c(1.0f, 1.0f, 1.0f, (1.0f - ((float)n2 - f2 + 1.0f) / 100.0f) * 0.8f);
            bfl.E();
            bgd2.a(afi.W.Q(), 1.0f);
            bfl.F();
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.k();
            bfl.e();
            bfl.w();
        }
    }
}

