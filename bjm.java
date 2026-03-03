/*
 * Decompiled with CFR 0.152.
 */
public class bjm<T extends va>
extends biv<T> {
    private static final jy e = new jy("textures/entity/minecart.png");
    protected bbo a = new bbn();

    public bjm(biu biu2) {
        super(biu2);
        this.c = 0.5f;
    }

    @Override
    public void a(T t22, double d22, double d3, double d4, float f22, float f3) {
        T t22;
        double d22;
        bfl.E();
        this.c(t22);
        long l2 = (long)((pk)t22).F() * 493286711L;
        l2 = l2 * l2 * 4392167121L + l2 * 98761L;
        float \u26032 = (((float)(l2 >> 16 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        float \u26033 = (((float)(l2 >> 20 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        float \u26034 = (((float)(l2 >> 24 & 7L) + 0.5f) / 8.0f - 0.5f) * 0.004f;
        bfl.b(\u26032, \u26033, \u26034);
        double \u26035 = ((va)t22).P + (((va)t22).s - ((va)t22).P) * (double)f3;
        double \u26036 = ((va)t22).Q + (((va)t22).t - ((va)t22).Q) * (double)f3;
        double \u26037 = ((va)t22).R + (((va)t22).u - ((va)t22).R) * (double)f3;
        double \u26038 = 0.3f;
        aui \u26039 = ((va)t22).k(\u26035, \u26036, \u26037);
        float \u260310 = ((va)t22).B + (((va)t22).z - ((va)t22).B) * f3;
        if (\u26039 != null) {
            aui aui2 = ((va)t22).a(\u26035, \u26036, \u26037, \u26038);
            \u2603 = ((va)t22).a(\u26035, \u26036, \u26037, -\u26038);
            if (aui2 == null) {
                aui2 = \u26039;
            }
            if (\u2603 == null) {
                \u2603 = \u26039;
            }
            d22 += \u26039.a - \u26035;
            d3 += (aui2.b + \u2603.b) / 2.0 - \u26036;
            d4 += \u26039.c - \u26037;
            \u2603 = \u2603.b(-aui2.a, -aui2.b, -aui2.c);
            if (\u2603.b() != 0.0) {
                \u2603 = \u2603.a();
                float f22 = (float)(Math.atan2(\u2603.c, \u2603.a) * 180.0 / Math.PI);
                \u260310 = (float)(Math.atan(\u2603.b) * 73.0);
            }
        }
        bfl.b((float)d22, (float)d3 + 0.375f, (float)d4);
        bfl.b(180.0f - f22, 0.0f, 1.0f, 0.0f);
        bfl.b(-\u260310, 0.0f, 0.0f, 1.0f);
        float \u260311 = (float)((va)t22).q() - f3;
        float \u260312 = ((va)t22).p() - f3;
        if (\u260312 < 0.0f) {
            \u260312 = 0.0f;
        }
        if (\u260311 > 0.0f) {
            bfl.b(ns.a(\u260311) * \u260311 * \u260312 / 10.0f * (float)((va)t22).r(), 1.0f, 0.0f, 0.0f);
        }
        int \u260313 = ((va)t22).v();
        alz \u260314 = ((va)t22).t();
        if (\u260314.c().b() != -1) {
            bfl.E();
            this.a(bmh.g);
            float f4 = 0.75f;
            bfl.a(f4, f4, f4);
            bfl.b(-0.5f, (float)(\u260313 - 8) / 16.0f, 0.5f);
            this.a(t22, f3, \u260314);
            bfl.F();
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            this.c(t22);
        }
        bfl.a(-1.0f, -1.0f, 1.0f);
        this.a.a((pk)t22, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        bfl.F();
        super.a(t22, d22, d3, d4, f22, f3);
    }

    @Override
    protected jy a(T t2) {
        return e;
    }

    protected void a(T t2, float f2, alz alz2) {
        bfl.E();
        ave.A().ae().a(alz2, ((pk)t2).c(f2));
        bfl.F();
    }
}

