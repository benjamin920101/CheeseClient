/*
 * Decompiled with CFR 0.152.
 */
public class biw
extends biv<pp> {
    private static final jy a = new jy("textures/entity/experience_orb.png");

    public biw(biu biu2) {
        super(biu2);
        this.c = 0.15f;
        this.d = 0.75f;
    }

    @Override
    public void a(pp pp2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        this.c(pp2);
        int n2 = pp2.l();
        float \u26032 = (float)(n2 % 4 * 16 + 0) / 64.0f;
        float \u26033 = (float)(n2 % 4 * 16 + 16) / 64.0f;
        float \u26034 = (float)(n2 / 4 * 16 + 0) / 64.0f;
        float \u26035 = (float)(n2 / 4 * 16 + 16) / 64.0f;
        float \u26036 = 1.0f;
        float \u26037 = 0.5f;
        float \u26038 = 0.25f;
        \u2603 = pp2.b(f3);
        \u2603 = \u2603 % 65536;
        \u2603 = \u2603 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        float \u26039 = 255.0f;
        float \u260310 = ((float)pp2.a + f3) / 2.0f;
        \u2603 = (int)((ns.a(\u260310 + 0.0f) + 1.0f) * 0.5f * 255.0f);
        \u2603 = 255;
        \u2603 = (int)((ns.a(\u260310 + 4.1887903f) + 1.0f) * 0.1f * 255.0f);
        bfl.b(180.0f - this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(-this.b.f, 1.0f, 0.0f, 0.0f);
        float \u260311 = 0.3f;
        bfl.a(0.3f, 0.3f, 0.3f);
        bfx \u260312 = bfx.a();
        bfd \u260313 = \u260312.c();
        \u260313.a(7, bms.l);
        \u260313.b((double)(0.0f - \u26037), (double)(0.0f - \u26038), 0.0).a(\u26032, \u26035).b(\u2603, 255, \u2603, 128).c(0.0f, 1.0f, 0.0f).d();
        \u260313.b((double)(\u26036 - \u26037), (double)(0.0f - \u26038), 0.0).a(\u26033, \u26035).b(\u2603, 255, \u2603, 128).c(0.0f, 1.0f, 0.0f).d();
        \u260313.b((double)(\u26036 - \u26037), (double)(1.0f - \u26038), 0.0).a(\u26033, \u26034).b(\u2603, 255, \u2603, 128).c(0.0f, 1.0f, 0.0f).d();
        \u260313.b((double)(0.0f - \u26037), (double)(1.0f - \u26038), 0.0).a(\u26032, \u26034).b(\u2603, 255, \u2603, 128).c(0.0f, 1.0f, 0.0f).d();
        \u260312.b();
        bfl.k();
        bfl.C();
        bfl.F();
        super.a(pp2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(pp pp2) {
        return a;
    }
}

