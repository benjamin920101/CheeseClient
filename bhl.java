/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;
import java.util.Random;

public class bhl
extends bhd<alp> {
    private static final jy d = new jy("textures/environment/end_sky.png");
    private static final jy e = new jy("textures/entity/end_portal.png");
    private static final Random f = new Random(31100L);
    FloatBuffer c = avd.h(16);

    @Override
    public void a(alp alp2, double d2, double d3, double d4, float f2, int n2) {
        float f3 = (float)this.b.j;
        \u2603 = (float)this.b.k;
        \u2603 = (float)this.b.l;
        bfl.f();
        f.setSeed(31100L);
        \u2603 = 0.75f;
        for (int i2 = 0; i2 < 16; ++i2) {
            bfl.E();
            float f4 = 16 - i2;
            \u2603 = 0.0625f;
            \u2603 = 1.0f / (f4 + 1.0f);
            if (i2 == 0) {
                this.a(d);
                \u2603 = 0.1f;
                f4 = 65.0f;
                \u2603 = 0.125f;
                bfl.l();
                bfl.b(770, 771);
            }
            if (i2 >= 1) {
                this.a(e);
            }
            if (i2 == 1) {
                bfl.l();
                bfl.b(1, 1);
                \u2603 = 0.5f;
            }
            \u2603 = (float)(-(d3 + (double)\u2603));
            \u2603 = \u2603 + (float)auz.a().b;
            \u2603 = \u2603 + f4 + (float)auz.a().b;
            \u2603 = \u2603 / \u2603;
            \u2603 = (float)(d3 + (double)\u2603) + \u2603;
            bfl.b(f3, \u2603, \u2603);
            bfl.a(bfl.o.a, 9217);
            bfl.a(bfl.o.b, 9217);
            bfl.a(bfl.o.c, 9217);
            bfl.a(bfl.o.d, 9216);
            bfl.a(bfl.o.a, 9473, this.a(1.0f, 0.0f, 0.0f, 0.0f));
            bfl.a(bfl.o.b, 9473, this.a(0.0f, 0.0f, 1.0f, 0.0f));
            bfl.a(bfl.o.c, 9473, this.a(0.0f, 0.0f, 0.0f, 1.0f));
            bfl.a(bfl.o.d, 9474, this.a(0.0f, 1.0f, 0.0f, 0.0f));
            bfl.a(bfl.o.a);
            bfl.a(bfl.o.b);
            bfl.a(bfl.o.c);
            bfl.a(bfl.o.d);
            bfl.F();
            bfl.n(5890);
            bfl.E();
            bfl.D();
            bfl.b(0.0f, (float)(ave.J() % 700000L) / 700000.0f, 0.0f);
            bfl.a(\u2603, \u2603, \u2603);
            bfl.b(0.5f, 0.5f, 0.0f);
            bfl.b((float)(i2 * i2 * 4321 + i2 * 9) * 2.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(-0.5f, -0.5f, 0.0f);
            bfl.b(-f3, -\u2603, -\u2603);
            \u2603 = \u2603 + (float)auz.a().b;
            bfl.b((float)auz.a().a * f4 / \u2603, (float)auz.a().c * f4 / \u2603, -\u2603);
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            \u26033.a(7, bms.f);
            \u2603 = (f.nextFloat() * 0.5f + 0.1f) * \u2603;
            \u2603 = (f.nextFloat() * 0.5f + 0.4f) * \u2603;
            \u2603 = (f.nextFloat() * 0.5f + 0.5f) * \u2603;
            if (i2 == 0) {
                \u2603 = \u2603 = 1.0f * \u2603;
                \u2603 = \u2603;
            }
            \u26033.b(d2, d3 + (double)\u2603, d4).a(\u2603, \u2603, \u2603, 1.0f).d();
            \u26033.b(d2, d3 + (double)\u2603, d4 + 1.0).a(\u2603, \u2603, \u2603, 1.0f).d();
            \u26033.b(d2 + 1.0, d3 + (double)\u2603, d4 + 1.0).a(\u2603, \u2603, \u2603, 1.0f).d();
            \u26033.b(d2 + 1.0, d3 + (double)\u2603, d4).a(\u2603, \u2603, \u2603, 1.0f).d();
            \u26032.b();
            bfl.F();
            bfl.n(5888);
            this.a(d);
        }
        bfl.k();
        bfl.b(bfl.o.a);
        bfl.b(bfl.o.b);
        bfl.b(bfl.o.c);
        bfl.b(bfl.o.d);
        bfl.e();
    }

    private FloatBuffer a(float f2, float f3, float f4, float f5) {
        this.c.clear();
        this.c.put(f2).put(f3).put(f4).put(f5);
        this.c.flip();
        return this.c;
    }
}

