/*
 * Decompiled with CFR 0.152.
 */
public class avp {
    public static final jy b = new jy("textures/gui/options_background.png");
    public static final jy c = new jy("textures/gui/container/stats_icons.png");
    public static final jy d = new jy("textures/gui/icons.png");
    protected float e;

    protected void a(int n2, int n3, int n4, int n5) {
        if (n3 < n2) {
            \u2603 = n2;
            n2 = n3;
            n3 = \u2603;
        }
        avp.a(n2, n4, n3 + 1, n4 + 1, n5);
    }

    protected void b(int n2, int n3, int n4, int n5) {
        if (n4 < n3) {
            \u2603 = n3;
            n3 = n4;
            n4 = \u2603;
        }
        avp.a(n2, n3 + 1, n2 + 1, n4, n5);
    }

    public static void a(int n2, int n3, int n4, int n5, int n6) {
        if (n2 < n4) {
            \u2603 = n2;
            n2 = n4;
            n4 = \u2603;
        }
        if (n3 < n5) {
            \u2603 = n3;
            n3 = n5;
            n5 = \u2603;
        }
        float f2 = (float)(n6 >> 24 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 & 0xFF) / 255.0f;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        bfl.l();
        bfl.x();
        bfl.a(770, 771, 1, 0);
        bfl.c(\u2603, \u2603, \u2603, f2);
        \u26033.a(7, bms.e);
        \u26033.b((double)n2, (double)n5, 0.0).d();
        \u26033.b((double)n4, (double)n5, 0.0).d();
        \u26033.b((double)n4, (double)n3, 0.0).d();
        \u26033.b((double)n2, (double)n3, 0.0).d();
        \u26032.b();
        bfl.w();
        bfl.k();
    }

    protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        float f2 = (float)(n6 >> 24 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        \u2603 = (float)(n6 & 0xFF) / 255.0f;
        \u2603 = (float)(n7 >> 24 & 0xFF) / 255.0f;
        \u2603 = (float)(n7 >> 16 & 0xFF) / 255.0f;
        \u2603 = (float)(n7 >> 8 & 0xFF) / 255.0f;
        \u2603 = (float)(n7 & 0xFF) / 255.0f;
        bfl.x();
        bfl.l();
        bfl.c();
        bfl.a(770, 771, 1, 0);
        bfl.j(7425);
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.f);
        \u26033.b((double)n4, (double)n3, (double)this.e).a(\u2603, \u2603, \u2603, f2).d();
        \u26033.b((double)n2, (double)n3, (double)this.e).a(\u2603, \u2603, \u2603, f2).d();
        \u26033.b((double)n2, (double)n5, (double)this.e).a(\u2603, \u2603, \u2603, \u2603).d();
        \u26033.b((double)n4, (double)n5, (double)this.e).a(\u2603, \u2603, \u2603, \u2603).d();
        \u26032.b();
        bfl.j(7424);
        bfl.k();
        bfl.d();
        bfl.w();
    }

    public void a(avn avn2, String string, int n2, int n3, int n4) {
        avn2.a(string, (float)(n2 - avn2.a(string) / 2), (float)n3, n4);
    }

    public void c(avn avn2, String string, int n2, int n3, int n4) {
        avn2.a(string, (float)n2, (float)n3, n4);
    }

    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
        float f2 = 0.00390625f;
        \u2603 = 0.00390625f;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.g);
        \u26033.b((double)(n2 + 0), (double)(n3 + n7), (double)this.e).a((float)(n4 + 0) * f2, (float)(n5 + n7) * \u2603).d();
        \u26033.b((double)(n2 + n6), (double)(n3 + n7), (double)this.e).a((float)(n4 + n6) * f2, (float)(n5 + n7) * \u2603).d();
        \u26033.b((double)(n2 + n6), (double)(n3 + 0), (double)this.e).a((float)(n4 + n6) * f2, (float)(n5 + 0) * \u2603).d();
        \u26033.b((double)(n2 + 0), (double)(n3 + 0), (double)this.e).a((float)(n4 + 0) * f2, (float)(n5 + 0) * \u2603).d();
        \u26032.b();
    }

    public void a(float f2, float f3, int n2, int n3, int n4, int n5) {
        float f4 = 0.00390625f;
        \u2603 = 0.00390625f;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.g);
        \u26033.b((double)(f2 + 0.0f), (double)(f3 + (float)n5), (double)this.e).a((float)(n2 + 0) * f4, (float)(n3 + n5) * \u2603).d();
        \u26033.b((double)(f2 + (float)n4), (double)(f3 + (float)n5), (double)this.e).a((float)(n2 + n4) * f4, (float)(n3 + n5) * \u2603).d();
        \u26033.b((double)(f2 + (float)n4), (double)(f3 + 0.0f), (double)this.e).a((float)(n2 + n4) * f4, (float)(n3 + 0) * \u2603).d();
        \u26033.b((double)(f2 + 0.0f), (double)(f3 + 0.0f), (double)this.e).a((float)(n2 + 0) * f4, (float)(n3 + 0) * \u2603).d();
        \u26032.b();
    }

    public void a(int n2, int n3, bmi bmi2, int n4, int n5) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.g);
        \u26032.b((double)(n2 + 0), (double)(n3 + n5), (double)this.e).a(bmi2.e(), bmi2.h()).d();
        \u26032.b((double)(n2 + n4), (double)(n3 + n5), (double)this.e).a(bmi2.f(), bmi2.h()).d();
        \u26032.b((double)(n2 + n4), (double)(n3 + 0), (double)this.e).a(bmi2.f(), bmi2.g()).d();
        \u26032.b((double)(n2 + 0), (double)(n3 + 0), (double)this.e).a(bmi2.e(), bmi2.g()).d();
        bfx2.b();
    }

    public static void a(int n2, int n3, float f2, float f3, int n4, int n5, float f4, float f5) {
        \u2603 = 1.0f / f4;
        \u2603 = 1.0f / f5;
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.g);
        \u26032.b((double)n2, (double)(n3 + n5), 0.0).a(f2 * \u2603, (f3 + (float)n5) * \u2603).d();
        \u26032.b((double)(n2 + n4), (double)(n3 + n5), 0.0).a((f2 + (float)n4) * \u2603, (f3 + (float)n5) * \u2603).d();
        \u26032.b((double)(n2 + n4), (double)n3, 0.0).a((f2 + (float)n4) * \u2603, f3 * \u2603).d();
        \u26032.b((double)n2, (double)n3, 0.0).a(f2 * \u2603, f3 * \u2603).d();
        bfx2.b();
    }

    public static void a(int n2, int n3, float f2, float f3, int n4, int n5, int n6, int n7, float f4, float f5) {
        \u2603 = 1.0f / f4;
        \u2603 = 1.0f / f5;
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.g);
        \u26032.b((double)n2, (double)(n3 + n7), 0.0).a(f2 * \u2603, (f3 + (float)n5) * \u2603).d();
        \u26032.b((double)(n2 + n6), (double)(n3 + n7), 0.0).a((f2 + (float)n4) * \u2603, (f3 + (float)n5) * \u2603).d();
        \u26032.b((double)(n2 + n6), (double)n3, 0.0).a((f2 + (float)n4) * \u2603, f3 * \u2603).d();
        \u26032.b((double)n2, (double)n3, 0.0).a(f2 * \u2603, f3 * \u2603).d();
        bfx2.b();
    }
}

