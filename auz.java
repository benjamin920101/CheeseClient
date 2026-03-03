/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class auz {
    private static final IntBuffer a = avd.f(16);
    private static final FloatBuffer b = avd.h(16);
    private static final FloatBuffer c = avd.h(16);
    private static final FloatBuffer d = avd.h(3);
    private static aui e = new aui(0.0, 0.0, 0.0);
    private static float f;
    private static float g;
    private static float h;
    private static float i;
    private static float j;

    public static void a(wn wn2, boolean bl2) {
        bfl.a(2982, b);
        bfl.a(2983, c);
        GL11.glGetInteger(2978, a);
        float f2 = (a.get(0) + a.get(2)) / 2;
        \u2603 = (a.get(1) + a.get(3)) / 2;
        GLU.gluUnProject(f2, \u2603, 0.0f, b, c, a, d);
        e = new aui(d.get(0), d.get(1), d.get(2));
        int \u26032 = bl2 ? 1 : 0;
        \u2603 = wn2.z;
        \u2603 = wn2.y;
        f = ns.b(\u2603 * (float)Math.PI / 180.0f) * (float)(1 - \u26032 * 2);
        h = ns.a(\u2603 * (float)Math.PI / 180.0f) * (float)(1 - \u26032 * 2);
        i = -h * ns.a(\u2603 * (float)Math.PI / 180.0f) * (float)(1 - \u26032 * 2);
        j = f * ns.a(\u2603 * (float)Math.PI / 180.0f) * (float)(1 - \u26032 * 2);
        g = ns.b(\u2603 * (float)Math.PI / 180.0f);
    }

    public static aui a(pk pk2, double d2) {
        \u2603 = pk2.p + (pk2.s - pk2.p) * d2;
        \u2603 = pk2.q + (pk2.t - pk2.q) * d2;
        \u2603 = pk2.r + (pk2.u - pk2.r) * d2;
        \u2603 = \u2603 + auz.e.a;
        \u2603 = \u2603 + auz.e.b;
        \u2603 = \u2603 + auz.e.c;
        return new aui(\u2603, \u2603, \u2603);
    }

    public static afh a(adm adm2, pk pk2, float f2) {
        aui aui2 = auz.a(pk2, f2);
        cj \u26032 = new cj(aui2);
        alz \u26033 = adm2.p(\u26032);
        afh \u26034 = \u26033.c();
        if (\u26034.t().d()) {
            float f3 = 0.0f;
            if (\u26033.c() instanceof ahv) {
                f3 = ahv.b(\u26033.b(ahv.b)) - 0.11111111f;
            }
            if (aui2.b >= (double)(\u2603 = (float)(\u26032.o() + 1) - f3)) {
                \u26034 = adm2.p(\u26032.a()).c();
            }
        }
        return \u26034;
    }

    public static aui a() {
        return e;
    }

    public static float b() {
        return f;
    }

    public static float c() {
        return g;
    }

    public static float d() {
        return h;
    }

    public static float e() {
        return i;
    }

    public static float f() {
        return j;
    }
}

