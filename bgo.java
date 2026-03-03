/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class bgo {
    private static final float a = 1.0f / (float)Math.cos(0.3926991f) - 1.0f;
    private static final float b = 1.0f / (float)Math.cos(0.7853981852531433) - 1.0f;

    public bgg a(Vector3f vector3f, Vector3f vector3f2, bgi bgi2, bmi bmi2, cq cq2, bor bor2, bgj bgj2, boolean bl2, boolean bl3) {
        int[] nArray = this.a(bgi2, bmi2, cq2, this.a(vector3f, vector3f2), bor2, bgj2, bl2, bl3);
        cq \u26032 = bgo.a(nArray);
        if (bl2) {
            this.a(nArray, \u26032, bgi2.e, bmi2);
        }
        if (bgj2 == null) {
            this.a(nArray, \u26032);
        }
        return new bgg(nArray, bgi2.c, \u26032);
    }

    private int[] a(bgi bgi2, bmi bmi2, cq cq2, float[] fArray, bor bor2, bgj bgj2, boolean bl2, boolean bl3) {
        int[] nArray = new int[28];
        for (int i2 = 0; i2 < 4; ++i2) {
            this.a(nArray, i2, cq2, bgi2, fArray, bmi2, bor2, bgj2, bl2, bl3);
        }
        return nArray;
    }

    private int a(cq cq2) {
        float f2 = this.b(cq2);
        int \u26032 = ns.a((int)(f2 * 255.0f), 0, 255);
        return 0xFF000000 | \u26032 << 16 | \u26032 << 8 | \u26032;
    }

    private float b(cq cq2) {
        switch (cq2) {
            case a: {
                return 0.5f;
            }
            case b: {
                return 1.0f;
            }
            case c: 
            case d: {
                return 0.8f;
            }
            case e: 
            case f: {
                return 0.6f;
            }
        }
        return 1.0f;
    }

    private float[] a(Vector3f vector3f, Vector3f vector3f2) {
        float[] fArray = new float[cq.values().length];
        fArray[bfj.a.f] = vector3f.x / 16.0f;
        fArray[bfj.a.e] = vector3f.y / 16.0f;
        fArray[bfj.a.d] = vector3f.z / 16.0f;
        fArray[bfj.a.c] = vector3f2.x / 16.0f;
        fArray[bfj.a.b] = vector3f2.y / 16.0f;
        fArray[bfj.a.a] = vector3f2.z / 16.0f;
        return fArray;
    }

    private void a(int[] nArray, int n2, cq cq2, bgi bgi2, float[] fArray, bmi bmi2, bor bor2, bgj bgj2, boolean bl2, boolean bl3) {
        cq cq3 = bor2.a(cq2);
        int \u26032 = bl3 ? this.a(cq3) : -1;
        bfj.b \u26033 = bfj.a(cq2).a(n2);
        Vector3f \u26034 = new Vector3f(fArray[\u26033.a], fArray[\u26033.b], fArray[\u26033.c]);
        this.a(\u26034, bgj2);
        int \u26035 = this.a(\u26034, cq2, n2, bor2, bl2);
        this.a(nArray, \u26035, n2, \u26034, \u26032, bmi2, bgi2.e);
    }

    private void a(int[] nArray, int n2, int n3, Vector3f vector3f, int n4, bmi bmi2, bgk bgk2) {
        int n5 = n2 * 7;
        nArray[n5] = Float.floatToRawIntBits(vector3f.x);
        nArray[n5 + 1] = Float.floatToRawIntBits(vector3f.y);
        nArray[n5 + 2] = Float.floatToRawIntBits(vector3f.z);
        nArray[n5 + 3] = n4;
        nArray[n5 + 4] = Float.floatToRawIntBits(bmi2.a(bgk2.a(n3)));
        nArray[n5 + 4 + 1] = Float.floatToRawIntBits(bmi2.b(bgk2.b(n3)));
    }

    private void a(Vector3f vector3f, bgj bgj2) {
        if (bgj2 == null) {
            return;
        }
        Matrix4f matrix4f = this.a();
        Vector3f \u26032 = new Vector3f(0.0f, 0.0f, 0.0f);
        switch (bgj2.b) {
            case a: {
                Matrix4f.rotate(bgj2.c * ((float)Math.PI / 180), new Vector3f(1.0f, 0.0f, 0.0f), matrix4f, matrix4f);
                \u26032.set(0.0f, 1.0f, 1.0f);
                break;
            }
            case b: {
                Matrix4f.rotate(bgj2.c * ((float)Math.PI / 180), new Vector3f(0.0f, 1.0f, 0.0f), matrix4f, matrix4f);
                \u26032.set(1.0f, 0.0f, 1.0f);
                break;
            }
            case c: {
                Matrix4f.rotate(bgj2.c * ((float)Math.PI / 180), new Vector3f(0.0f, 0.0f, 1.0f), matrix4f, matrix4f);
                \u26032.set(1.0f, 1.0f, 0.0f);
            }
        }
        if (bgj2.d) {
            if (Math.abs(bgj2.c) == 22.5f) {
                \u26032.scale(a);
            } else {
                \u26032.scale(b);
            }
            Vector3f.add(\u26032, new Vector3f(1.0f, 1.0f, 1.0f), \u26032);
        } else {
            \u26032.set(1.0f, 1.0f, 1.0f);
        }
        this.a(vector3f, new Vector3f(bgj2.a), matrix4f, \u26032);
    }

    public int a(Vector3f vector3f, cq cq2, int n2, bor bor2, boolean bl2) {
        if (bor2 == bor.a) {
            return n2;
        }
        this.a(vector3f, new Vector3f(0.5f, 0.5f, 0.5f), bor2.a(), new Vector3f(1.0f, 1.0f, 1.0f));
        return bor2.a(cq2, n2);
    }

    private void a(Vector3f vector3f, Vector3f vector3f2, Matrix4f matrix4f, Vector3f vector3f3) {
        Vector4f vector4f = new Vector4f(vector3f.x - vector3f2.x, vector3f.y - vector3f2.y, vector3f.z - vector3f2.z, 1.0f);
        Matrix4f.transform(matrix4f, vector4f, vector4f);
        vector4f.x *= vector3f3.x;
        vector4f.y *= vector3f3.y;
        vector4f.z *= vector3f3.z;
        vector3f.set(vector4f.x + vector3f2.x, vector4f.y + vector3f2.y, vector4f.z + vector3f2.z);
    }

    private Matrix4f a() {
        Matrix4f matrix4f = new Matrix4f();
        matrix4f.setIdentity();
        return matrix4f;
    }

    public static cq a(int[] nArray) {
        Vector3f vector3f = new Vector3f(Float.intBitsToFloat(nArray[0]), Float.intBitsToFloat(nArray[1]), Float.intBitsToFloat(nArray[2]));
        \u2603 = new Vector3f(Float.intBitsToFloat(nArray[7]), Float.intBitsToFloat(nArray[8]), Float.intBitsToFloat(nArray[9]));
        \u2603 = new Vector3f(Float.intBitsToFloat(nArray[14]), Float.intBitsToFloat(nArray[15]), Float.intBitsToFloat(nArray[16]));
        \u2603 = new Vector3f();
        \u2603 = new Vector3f();
        \u2603 = new Vector3f();
        Vector3f.sub(vector3f, \u2603, \u2603);
        Vector3f.sub(\u2603, \u2603, \u2603);
        Vector3f.cross(\u2603, \u2603, \u2603);
        float \u26032 = (float)Math.sqrt(\u2603.x * \u2603.x + \u2603.y * \u2603.y + \u2603.z * \u2603.z);
        \u2603.x /= \u26032;
        \u2603.y /= \u26032;
        \u2603.z /= \u26032;
        cq \u26033 = null;
        float \u26034 = 0.0f;
        for (cq cq2 : cq.values()) {
            df df2 = cq2.m();
            Vector3f \u26035 = new Vector3f(df2.n(), df2.o(), df2.p());
            float \u26036 = Vector3f.dot(\u2603, \u26035);
            if (!(\u26036 >= 0.0f) || !(\u26036 > \u26034)) continue;
            \u26034 = \u26036;
            \u26033 = cq2;
        }
        if (\u26033 == null) {
            return cq.b;
        }
        return \u26033;
    }

    public void a(int[] nArray, cq cq2, bgk bgk2, bmi bmi2) {
        for (int i2 = 0; i2 < 4; ++i2) {
            this.a(i2, nArray, cq2, bgk2, bmi2);
        }
    }

    private void a(int[] nArray, cq cq22) {
        cq cq22;
        int[] nArray2 = new int[nArray.length];
        System.arraycopy(nArray, 0, nArray2, 0, nArray.length);
        float[] \u26032 = new float[cq.values().length];
        \u26032[bfj.a.f] = 999.0f;
        \u26032[bfj.a.e] = 999.0f;
        \u26032[bfj.a.d] = 999.0f;
        \u26032[bfj.a.c] = -999.0f;
        \u26032[bfj.a.b] = -999.0f;
        \u26032[bfj.a.a] = -999.0f;
        for (int i2 = 0; i2 < 4; ++i2) {
            i3 = 7 * i2;
            float f2 = Float.intBitsToFloat(nArray2[i3]);
            \u2603 = Float.intBitsToFloat(nArray2[i3 + 1]);
            \u26034 = Float.intBitsToFloat(nArray2[i3 + 2]);
            if (f2 < \u26032[bfj.a.f]) {
                \u26032[bfj.a.f] = f2;
            }
            if (\u2603 < \u26032[bfj.a.e]) {
                \u26032[bfj.a.e] = \u2603;
            }
            if (\u26034 < \u26032[bfj.a.d]) {
                \u26032[bfj.a.d] = \u26034;
            }
            if (f2 > \u26032[bfj.a.c]) {
                \u26032[bfj.a.c] = f2;
            }
            if (\u2603 > \u26032[bfj.a.b]) {
                \u26032[bfj.a.b] = \u2603;
            }
            if (!(\u26034 > \u26032[bfj.a.a])) continue;
            \u26032[bfj.a.a] = \u26034;
        }
        bfj \u26033 = bfj.a(cq22);
        for (int i3 = 0; i3 < 4; ++i3) {
            \u2603 = 7 * i3;
            bfj.b b2 = \u26033.a(i3);
            float \u26034 = \u26032[b2.a];
            float \u26035 = \u26032[b2.b];
            float \u26036 = \u26032[b2.c];
            nArray[\u2603] = Float.floatToRawIntBits(\u26034);
            nArray[\u2603 + 1] = Float.floatToRawIntBits(\u26035);
            nArray[\u2603 + 2] = Float.floatToRawIntBits(\u26036);
            for (int i4 = 0; i4 < 4; ++i4) {
                \u2603 = 7 * i4;
                float f3 = Float.intBitsToFloat(nArray2[\u2603]);
                \u2603 = Float.intBitsToFloat(nArray2[\u2603 + 1]);
                \u2603 = Float.intBitsToFloat(nArray2[\u2603 + 2]);
                if (!ns.a(\u26034, f3) || !ns.a(\u26035, \u2603) || !ns.a(\u26036, \u2603)) continue;
                nArray[\u2603 + 4] = nArray2[\u2603 + 4];
                nArray[\u2603 + 4 + 1] = nArray2[\u2603 + 4 + 1];
            }
        }
    }

    private void a(int n2, int[] nArray, cq cq2, bgk bgk2, bmi bmi2) {
        int n3 = 7 * n2;
        float \u26032 = Float.intBitsToFloat(nArray[n3]);
        float \u26033 = Float.intBitsToFloat(nArray[n3 + 1]);
        float \u26034 = Float.intBitsToFloat(nArray[n3 + 2]);
        if (\u26032 < -0.1f || \u26032 >= 1.1f) {
            \u26032 -= (float)ns.d(\u26032);
        }
        if (\u26033 < -0.1f || \u26033 >= 1.1f) {
            \u26033 -= (float)ns.d(\u26033);
        }
        if (\u26034 < -0.1f || \u26034 >= 1.1f) {
            \u26034 -= (float)ns.d(\u26034);
        }
        float \u26035 = 0.0f;
        float \u26036 = 0.0f;
        switch (cq2) {
            case a: {
                \u26035 = \u26032 * 16.0f;
                \u26036 = (1.0f - \u26034) * 16.0f;
                break;
            }
            case b: {
                \u26035 = \u26032 * 16.0f;
                \u26036 = \u26034 * 16.0f;
                break;
            }
            case c: {
                \u26035 = (1.0f - \u26032) * 16.0f;
                \u26036 = (1.0f - \u26033) * 16.0f;
                break;
            }
            case d: {
                \u26035 = \u26032 * 16.0f;
                \u26036 = (1.0f - \u26033) * 16.0f;
                break;
            }
            case e: {
                \u26035 = \u26034 * 16.0f;
                \u26036 = (1.0f - \u26033) * 16.0f;
                break;
            }
            case f: {
                \u26035 = (1.0f - \u26034) * 16.0f;
                \u26036 = (1.0f - \u26033) * 16.0f;
            }
        }
        \u2603 = bgk2.c(n2) * 7;
        nArray[\u2603 + 4] = Float.floatToRawIntBits(bmi2.a(\u26035));
        nArray[\u2603 + 4 + 1] = Float.floatToRawIntBits(bmi2.b(\u26036));
    }
}

