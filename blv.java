/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;

public class blv {
    private static final Logger a = LogManager.getLogger();
    private int b;
    private final int c;
    private final int d;
    private final IntBuffer e;
    private final FloatBuffer f;
    private final String g;
    private boolean h;
    private final blq i;

    public blv(String string, int n2, int n3, blq blq2) {
        this.g = string;
        this.c = n3;
        this.d = n2;
        this.i = blq2;
        if (n2 <= 3) {
            this.e = BufferUtils.createIntBuffer(n3);
            this.f = null;
        } else {
            this.e = null;
            this.f = BufferUtils.createFloatBuffer(n3);
        }
        this.b = -1;
        this.h();
    }

    private void h() {
        this.h = true;
        if (this.i != null) {
            this.i.d();
        }
    }

    public static int a(String string) {
        int n2 = -1;
        if (string.equals("int")) {
            n2 = 0;
        } else if (string.equals("float")) {
            n2 = 4;
        } else if (string.startsWith("matrix")) {
            if (string.endsWith("2x2")) {
                n2 = 8;
            } else if (string.endsWith("3x3")) {
                n2 = 9;
            } else if (string.endsWith("4x4")) {
                n2 = 10;
            }
        }
        return n2;
    }

    public void b(int n2) {
        this.b = n2;
    }

    public String a() {
        return this.g;
    }

    public void a(float f2) {
        this.f.position(0);
        this.f.put(0, f2);
        this.h();
    }

    public void a(float f2, float f3) {
        this.f.position(0);
        this.f.put(0, f2);
        this.f.put(1, f3);
        this.h();
    }

    public void a(float f2, float f3, float f4) {
        this.f.position(0);
        this.f.put(0, f2);
        this.f.put(1, f3);
        this.f.put(2, f4);
        this.h();
    }

    public void a(float f2, float f3, float f4, float f5) {
        this.f.position(0);
        this.f.put(f2);
        this.f.put(f3);
        this.f.put(f4);
        this.f.put(f5);
        this.f.flip();
        this.h();
    }

    public void b(float f2, float f3, float f4, float f5) {
        this.f.position(0);
        if (this.d >= 4) {
            this.f.put(0, f2);
        }
        if (this.d >= 5) {
            this.f.put(1, f3);
        }
        if (this.d >= 6) {
            this.f.put(2, f4);
        }
        if (this.d >= 7) {
            this.f.put(3, f5);
        }
        this.h();
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.e.position(0);
        if (this.d >= 0) {
            this.e.put(0, n2);
        }
        if (this.d >= 1) {
            this.e.put(1, n3);
        }
        if (this.d >= 2) {
            this.e.put(2, n4);
        }
        if (this.d >= 3) {
            this.e.put(3, n5);
        }
        this.h();
    }

    public void a(float[] fArray) {
        if (fArray.length < this.c) {
            a.warn("Uniform.set called with a too-small value array (expected " + this.c + ", got " + fArray.length + "). Ignoring.");
            return;
        }
        this.f.position(0);
        this.f.put(fArray);
        this.f.position(0);
        this.h();
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17) {
        this.f.position(0);
        this.f.put(0, f2);
        this.f.put(1, f3);
        this.f.put(2, f4);
        this.f.put(3, f5);
        this.f.put(4, f6);
        this.f.put(5, f7);
        this.f.put(6, f8);
        this.f.put(7, f9);
        this.f.put(8, f10);
        this.f.put(9, f11);
        this.f.put(10, f12);
        this.f.put(11, f13);
        this.f.put(12, f14);
        this.f.put(13, f15);
        this.f.put(14, f16);
        this.f.put(15, f17);
        this.h();
    }

    public void a(Matrix4f matrix4f) {
        this.a(matrix4f.m00, matrix4f.m01, matrix4f.m02, matrix4f.m03, matrix4f.m10, matrix4f.m11, matrix4f.m12, matrix4f.m13, matrix4f.m20, matrix4f.m21, matrix4f.m22, matrix4f.m23, matrix4f.m30, matrix4f.m31, matrix4f.m32, matrix4f.m33);
    }

    public void b() {
        if (!this.h) {
            // empty if block
        }
        this.h = false;
        if (this.d <= 3) {
            this.i();
        } else if (this.d <= 7) {
            this.j();
        } else if (this.d <= 10) {
            this.k();
        } else {
            a.warn("Uniform.upload called, but type value (" + this.d + ") is not " + "a valid type. Ignoring.");
            return;
        }
    }

    private void i() {
        switch (this.d) {
            case 0: {
                bqs.a(this.b, this.e);
                break;
            }
            case 1: {
                bqs.b(this.b, this.e);
                break;
            }
            case 2: {
                bqs.c(this.b, this.e);
                break;
            }
            case 3: {
                bqs.d(this.b, this.e);
                break;
            }
            default: {
                a.warn("Uniform.upload called, but count value (" + this.c + ") is " + " not in the range of 1 to 4. Ignoring.");
            }
        }
    }

    private void j() {
        switch (this.d) {
            case 4: {
                bqs.a(this.b, this.f);
                break;
            }
            case 5: {
                bqs.b(this.b, this.f);
                break;
            }
            case 6: {
                bqs.c(this.b, this.f);
                break;
            }
            case 7: {
                bqs.d(this.b, this.f);
                break;
            }
            default: {
                a.warn("Uniform.upload called, but count value (" + this.c + ") is " + "not in the range of 1 to 4. Ignoring.");
            }
        }
    }

    private void k() {
        switch (this.d) {
            case 8: {
                bqs.a(this.b, true, this.f);
                break;
            }
            case 9: {
                bqs.b(this.b, true, this.f);
                break;
            }
            case 10: {
                bqs.c(this.b, true, this.f);
            }
        }
    }
}

