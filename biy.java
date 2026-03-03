/*
 * Decompiled with CFR 0.152.
 */
public class biy
extends biv<ws> {
    private float a;

    public biy(biu biu2, float f2) {
        super(biu2);
        this.a = f2;
    }

    @Override
    public void a(ws ws2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        this.c(ws2);
        bfl.b((float)d2, (float)d3, (float)d4);
        bfl.B();
        bfl.a(this.a, this.a, this.a);
        bmi bmi2 = ave.A().ag().a().a(zy.bL);
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        float \u26034 = bmi2.e();
        float \u26035 = bmi2.f();
        float \u26036 = bmi2.g();
        float \u26037 = bmi2.h();
        float \u26038 = 1.0f;
        float \u26039 = 0.5f;
        float \u260310 = 0.25f;
        bfl.b(180.0f - this.b.e, 0.0f, 1.0f, 0.0f);
        bfl.b(-this.b.f, 1.0f, 0.0f, 0.0f);
        \u26033.a(7, bms.j);
        \u26033.b(-0.5, -0.25, 0.0).a(\u26034, \u26037).c(0.0f, 1.0f, 0.0f).d();
        \u26033.b(0.5, -0.25, 0.0).a(\u26035, \u26037).c(0.0f, 1.0f, 0.0f).d();
        \u26033.b(0.5, 0.75, 0.0).a(\u26035, \u26036).c(0.0f, 1.0f, 0.0f).d();
        \u26033.b(-0.5, 0.75, 0.0).a(\u26034, \u26036).c(0.0f, 1.0f, 0.0f).d();
        \u26032.b();
        bfl.C();
        bfl.F();
        super.a(ws2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(ws ws2) {
        return bmh.g;
    }
}

