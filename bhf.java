/*
 * Decompiled with CFR 0.152.
 */
public class bhf
extends bhd<ale> {
    private static final jy c = new jy("textures/entity/enchanting_table_book.png");
    private bay d = new bay();

    @Override
    public void a(ale ale2, double d2, double d3, double d4, float f2, int n2) {
        bfl.E();
        bfl.b((float)d2 + 0.5f, (float)d3 + 0.75f, (float)d4 + 0.5f);
        float f3 = (float)ale2.a + f2;
        bfl.b(0.0f, 0.1f + ns.a(f3 * 0.1f) * 0.01f, 0.0f);
        for (\u2603 = ale2.l - ale2.m; \u2603 >= (float)Math.PI; \u2603 -= (float)Math.PI * 2) {
        }
        while (\u2603 < (float)(-Math.PI)) {
            \u2603 += (float)Math.PI * 2;
        }
        \u2603 = ale2.m + \u2603 * f2;
        bfl.b(-\u2603 * 180.0f / (float)Math.PI, 0.0f, 1.0f, 0.0f);
        bfl.b(80.0f, 0.0f, 0.0f, 1.0f);
        this.a(c);
        \u2603 = ale2.g + (ale2.f - ale2.g) * f2 + 0.25f;
        \u2603 = ale2.g + (ale2.f - ale2.g) * f2 + 0.75f;
        \u2603 = (\u2603 - (float)ns.b((double)\u2603)) * 1.6f - 0.3f;
        \u2603 = (\u2603 - (float)ns.b((double)\u2603)) * 1.6f - 0.3f;
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        }
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        }
        if (\u2603 > 1.0f) {
            \u2603 = 1.0f;
        }
        if (\u2603 > 1.0f) {
            \u2603 = 1.0f;
        }
        \u2603 = ale2.k + (ale2.j - ale2.k) * f2;
        bfl.o();
        this.d.a(null, f3, \u2603, \u2603, \u2603, 0.0f, 0.0625f);
        bfl.F();
    }
}

