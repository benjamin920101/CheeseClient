/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class avc {
    private static FloatBuffer a = avd.h(16);
    private static final aui b = new aui(0.2f, 1.0, -0.7f).a();
    private static final aui c = new aui(-0.2f, 1.0, 0.7f).a();

    public static void a() {
        bfl.f();
        bfl.b(0);
        bfl.b(1);
        bfl.h();
    }

    public static void b() {
        bfl.e();
        bfl.a(0);
        bfl.a(1);
        bfl.g();
        bfl.a(1032, 5634);
        float f2 = 0.4f;
        \u2603 = 0.6f;
        \u2603 = 0.0f;
        GL11.glLight(16384, 4611, avc.a(avc.b.a, avc.b.b, avc.b.c, 0.0));
        GL11.glLight(16384, 4609, avc.a(\u2603, \u2603, \u2603, 1.0f));
        GL11.glLight(16384, 4608, avc.a(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight(16384, 4610, avc.a(\u2603, \u2603, \u2603, 1.0f));
        GL11.glLight(16385, 4611, avc.a(avc.c.a, avc.c.b, avc.c.c, 0.0));
        GL11.glLight(16385, 4609, avc.a(\u2603, \u2603, \u2603, 1.0f));
        GL11.glLight(16385, 4608, avc.a(0.0f, 0.0f, 0.0f, 1.0f));
        GL11.glLight(16385, 4610, avc.a(\u2603, \u2603, \u2603, 1.0f));
        bfl.j(7424);
        GL11.glLightModel(2899, avc.a(f2, f2, f2, 1.0f));
    }

    private static FloatBuffer a(double d2, double d3, double d4, double d5) {
        return avc.a((float)d2, (float)d3, (float)d4, (float)d5);
    }

    private static FloatBuffer a(float f2, float f3, float f4, float f5) {
        a.clear();
        a.put(f2).put(f3).put(f4).put(f5);
        a.flip();
        return a;
    }

    public static void c() {
        bfl.E();
        bfl.b(-30.0f, 0.0f, 1.0f, 0.0f);
        bfl.b(165.0f, 1.0f, 0.0f, 0.0f);
        avc.b();
        bfl.F();
    }
}

