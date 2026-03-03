/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.opengl.GL11;

public class bih
extends biv<wq> {
    private static final jy a = new jy("textures/entity/arrow.png");

    public bih(biu biu2) {
        super(biu2);
    }

    @Override
    public void a(wq wq22, double d2, double d3, double d4, float f2, float f3) {
        wq wq22;
        this.c(wq22);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        bfl.b(wq22.A + (wq22.y - wq22.A) * f3 - 90.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(wq22.B + (wq22.z - wq22.B) * f3, 0.0f, 0.0f, 1.0f);
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        int \u26033 = 0;
        float \u26034 = 0.0f;
        float \u26035 = 0.5f;
        float \u26036 = (float)(0 + \u26033 * 10) / 32.0f;
        float \u26037 = (float)(5 + \u26033 * 10) / 32.0f;
        float \u26038 = 0.0f;
        float \u26039 = 0.15625f;
        float \u260310 = (float)(5 + \u26033 * 10) / 32.0f;
        float \u260311 = (float)(10 + \u26033 * 10) / 32.0f;
        float \u260312 = 0.05625f;
        bfl.B();
        float \u260313 = (float)wq22.b - f3;
        if (\u260313 > 0.0f) {
            float f4 = -ns.a(\u260313 * 3.0f) * \u260313;
            bfl.b(f4, 0.0f, 0.0f, 1.0f);
        }
        bfl.b(45.0f, 1.0f, 0.0f, 0.0f);
        bfl.a(\u260312, \u260312, \u260312);
        bfl.b(-4.0f, 0.0f, 0.0f);
        GL11.glNormal3f(\u260312, 0.0f, 0.0f);
        \u26032.a(7, bms.g);
        \u26032.b(-7.0, -2.0, -2.0).a(\u26038, \u260310).d();
        \u26032.b(-7.0, -2.0, 2.0).a(\u26039, \u260310).d();
        \u26032.b(-7.0, 2.0, 2.0).a(\u26039, \u260311).d();
        \u26032.b(-7.0, 2.0, -2.0).a(\u26038, \u260311).d();
        bfx2.b();
        GL11.glNormal3f(-\u260312, 0.0f, 0.0f);
        \u26032.a(7, bms.g);
        \u26032.b(-7.0, 2.0, -2.0).a(\u26038, \u260310).d();
        \u26032.b(-7.0, 2.0, 2.0).a(\u26039, \u260310).d();
        \u26032.b(-7.0, -2.0, 2.0).a(\u26039, \u260311).d();
        \u26032.b(-7.0, -2.0, -2.0).a(\u26038, \u260311).d();
        bfx2.b();
        for (int i2 = 0; i2 < 4; ++i2) {
            bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glNormal3f(0.0f, 0.0f, \u260312);
            \u26032.a(7, bms.g);
            \u26032.b(-8.0, -2.0, 0.0).a(\u26034, \u26036).d();
            \u26032.b(8.0, -2.0, 0.0).a(\u26035, \u26036).d();
            \u26032.b(8.0, 2.0, 0.0).a(\u26035, \u26037).d();
            \u26032.b(-8.0, 2.0, 0.0).a(\u26034, \u26037).d();
            bfx2.b();
        }
        bfl.C();
        bfl.F();
        super.a(wq22, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(wq wq2) {
        return a;
    }
}

