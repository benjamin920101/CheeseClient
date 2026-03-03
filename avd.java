/*
 * Decompiled with CFR 0.152.
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class avd {
    public static synchronized int a(int n2) {
        int n3;
        n3 = GL11.glGenLists(n2);
        if (n3 == 0) {
            \u2603 = GL11.glGetError();
            String string = "No error code reported";
            if (\u2603 != 0) {
                string = GLU.gluErrorString(\u2603);
            }
            throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + n2 + ", GL error (" + \u2603 + "): " + string);
        }
        return n3;
    }

    public static synchronized void a(int n2, int n3) {
        GL11.glDeleteLists(n2, n3);
    }

    public static synchronized void b(int n2) {
        GL11.glDeleteLists(n2, 1);
    }

    public static synchronized ByteBuffer c(int n2) {
        return ByteBuffer.allocateDirect(n2).order(ByteOrder.nativeOrder());
    }

    public static IntBuffer f(int n2) {
        return avd.c(n2 << 2).asIntBuffer();
    }

    public static FloatBuffer h(int n2) {
        return avd.c(n2 << 2).asFloatBuffer();
    }
}

