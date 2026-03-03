/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.primitives.Floats;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import org.apache.logging.log4j.LogManager;

public class bfd {
    private ByteBuffer a;
    private IntBuffer b;
    private ShortBuffer c;
    private FloatBuffer d;
    private int e;
    private bmv f;
    private int g;
    private boolean h;
    private int i;
    private double j;
    private double k;
    private double l;
    private bmu m;
    private boolean n;

    public bfd(int n2) {
        this.a = avd.c(n2 * 4);
        this.b = this.a.asIntBuffer();
        this.c = this.a.asShortBuffer();
        this.d = this.a.asFloatBuffer();
    }

    private void b(int n2) {
        if (n2 <= this.b.remaining()) {
            return;
        }
        \u2603 = this.a.capacity();
        \u2603 = \u2603 % 0x200000;
        \u2603 = \u2603 + (((this.b.position() + n2) * 4 - \u2603) / 0x200000 + 1) * 0x200000;
        LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + \u2603 + " bytes, new size " + \u2603 + " bytes.");
        \u2603 = this.b.position();
        ByteBuffer byteBuffer = avd.c(\u2603);
        this.a.position(0);
        byteBuffer.put(this.a);
        byteBuffer.rewind();
        this.a = byteBuffer;
        this.d = this.a.asFloatBuffer().asReadOnlyBuffer();
        this.b = this.a.asIntBuffer();
        this.b.position(\u2603);
        this.c = this.a.asShortBuffer();
        this.c.position(\u2603 << 1);
    }

    public void a(float f2, float f3, float f4) {
        int n2 = this.e / 4;
        final float[] \u26032 = new float[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            \u26032[\u2603] = bfd.a(this.d, (float)((double)f2 + this.j), (float)((double)f3 + this.k), (float)((double)f4 + this.l), this.m.f(), \u2603 * this.m.g());
        }
        Integer[] \u26033 = new Integer[n2];
        for (\u2603 = 0; \u2603 < \u26033.length; ++\u2603) {
            \u26033[\u2603] = \u2603;
        }
        Arrays.sort(\u26033, new Comparator<Integer>(){

            public int a(Integer n2, Integer n3) {
                return Floats.compare(\u26032[n3], \u26032[n2]);
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((Integer)object, (Integer)object2);
            }
        });
        BitSet \u26034 = new BitSet();
        \u2603 = this.m.g();
        int[] \u26035 = new int[\u2603];
        \u2603 = 0;
        while ((\u2603 = \u26034.nextClearBit(\u2603)) < \u26033.length) {
            \u2603 = \u26033[\u2603];
            if (\u2603 != \u2603) {
                this.b.limit(\u2603 * \u2603 + \u2603);
                this.b.position(\u2603 * \u2603);
                this.b.get(\u26035);
                \u26036 = \u2603;
                \u26037 = \u26033[\u26036];
                while (\u26036 != \u2603) {
                    this.b.limit(\u26037 * \u2603 + \u2603);
                    this.b.position(\u26037 * \u2603);
                    IntBuffer intBuffer = this.b.slice();
                    this.b.limit(\u26036 * \u2603 + \u2603);
                    this.b.position(\u26036 * \u2603);
                    this.b.put(intBuffer);
                    \u26034.set(\u26036);
                    int \u26036 = \u26037;
                    int \u26037 = \u26033[\u26036];
                }
                this.b.limit(\u2603 * \u2603 + \u2603);
                this.b.position(\u2603 * \u2603);
                this.b.put(\u26035);
            }
            \u26034.set(\u2603);
            ++\u2603;
        }
    }

    public a a() {
        this.b.rewind();
        int n2 = this.j();
        this.b.limit(n2);
        int[] \u26032 = new int[n2];
        this.b.get(\u26032);
        this.b.limit(this.b.capacity());
        this.b.position(n2);
        return new a(\u26032, new bmu(this.m));
    }

    private int j() {
        return this.e * this.m.f();
    }

    private static float a(FloatBuffer floatBuffer, float f2, float f3, float f4, int n2, int n3) {
        float f5 = floatBuffer.get(n3 + n2 * 0 + 0);
        \u2603 = floatBuffer.get(n3 + n2 * 0 + 1);
        \u2603 = floatBuffer.get(n3 + n2 * 0 + 2);
        \u2603 = floatBuffer.get(n3 + n2 * 1 + 0);
        \u2603 = floatBuffer.get(n3 + n2 * 1 + 1);
        \u2603 = floatBuffer.get(n3 + n2 * 1 + 2);
        \u2603 = floatBuffer.get(n3 + n2 * 2 + 0);
        \u2603 = floatBuffer.get(n3 + n2 * 2 + 1);
        \u2603 = floatBuffer.get(n3 + n2 * 2 + 2);
        \u2603 = floatBuffer.get(n3 + n2 * 3 + 0);
        \u2603 = floatBuffer.get(n3 + n2 * 3 + 1);
        \u2603 = floatBuffer.get(n3 + n2 * 3 + 2);
        \u2603 = (f5 + \u2603 + \u2603 + \u2603) * 0.25f - f2;
        \u2603 = (\u2603 + \u2603 + \u2603 + \u2603) * 0.25f - f3;
        \u2603 = (\u2603 + \u2603 + \u2603 + \u2603) * 0.25f - f4;
        return \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
    }

    public void a(a a2) {
        this.b.clear();
        this.b(a2.a().length);
        this.b.put(a2.a());
        this.e = a2.b();
        this.m = new bmu(a2.c());
    }

    public void b() {
        this.e = 0;
        this.f = null;
        this.g = 0;
    }

    public void a(int n2, bmu bmu2) {
        if (this.n) {
            throw new IllegalStateException("Already building!");
        }
        this.n = true;
        this.b();
        this.i = n2;
        this.m = bmu2;
        this.f = bmu2.c(this.g);
        this.h = false;
        this.a.limit(this.a.capacity());
    }

    public bfd a(double d2, double d3) {
        int n2 = this.e * this.m.g() + this.m.d(this.g);
        switch (this.f.a()) {
            case a: {
                this.a.putFloat(n2, (float)d2);
                this.a.putFloat(n2 + 4, (float)d3);
                break;
            }
            case f: 
            case g: {
                this.a.putInt(n2, (int)d2);
                this.a.putInt(n2 + 4, (int)d3);
                break;
            }
            case d: 
            case e: {
                this.a.putShort(n2, (short)d3);
                this.a.putShort(n2 + 2, (short)d2);
                break;
            }
            case b: 
            case c: {
                this.a.put(n2, (byte)d3);
                this.a.put(n2 + 1, (byte)d2);
            }
        }
        this.k();
        return this;
    }

    public bfd a(int n2, int n3) {
        \u2603 = this.e * this.m.g() + this.m.d(this.g);
        switch (this.f.a()) {
            case a: {
                this.a.putFloat(\u2603, n2);
                this.a.putFloat(\u2603 + 4, n3);
                break;
            }
            case f: 
            case g: {
                this.a.putInt(\u2603, n2);
                this.a.putInt(\u2603 + 4, n3);
                break;
            }
            case d: 
            case e: {
                this.a.putShort(\u2603, (short)n3);
                this.a.putShort(\u2603 + 2, (short)n2);
                break;
            }
            case b: 
            case c: {
                this.a.put(\u2603, (byte)n3);
                this.a.put(\u2603 + 1, (byte)n2);
            }
        }
        this.k();
        return this;
    }

    public void a(int n2, int n3, int n4, int n5) {
        \u2603 = (this.e - 4) * this.m.f() + this.m.b(1) / 4;
        \u2603 = this.m.g() >> 2;
        this.b.put(\u2603, n2);
        this.b.put(\u2603 + \u2603, n3);
        this.b.put(\u2603 + \u2603 * 2, n4);
        this.b.put(\u2603 + \u2603 * 3, n5);
    }

    public void a(double d2, double d3, double d4) {
        int n2 = this.m.f();
        \u2603 = (this.e - 4) * n2;
        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
            \u2603 = \u2603 + \u2603 * n2;
            \u2603 = \u2603 + 1;
            \u2603 = \u2603 + 1;
            this.b.put(\u2603, Float.floatToRawIntBits((float)(d2 + this.j) + Float.intBitsToFloat(this.b.get(\u2603))));
            this.b.put(\u2603, Float.floatToRawIntBits((float)(d3 + this.k) + Float.intBitsToFloat(this.b.get(\u2603))));
            this.b.put(\u2603, Float.floatToRawIntBits((float)(d4 + this.l) + Float.intBitsToFloat(this.b.get(\u2603))));
        }
    }

    private int c(int n2) {
        return ((this.e - n2) * this.m.g() + this.m.e()) / 4;
    }

    public void a(float f2, float f3, float f4, int n2) {
        \u2603 = this.c(n2);
        \u2603 = -1;
        if (!this.h) {
            \u2603 = this.b.get(\u2603);
            if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                \u2603 = (int)((float)(\u2603 & 0xFF) * f2);
                \u2603 = (int)((float)(\u2603 >> 8 & 0xFF) * f3);
                \u2603 = (int)((float)(\u2603 >> 16 & 0xFF) * f4);
                \u2603 &= 0xFF000000;
                \u2603 |= \u2603 << 16 | \u2603 << 8 | \u2603;
            } else {
                \u2603 = (int)((float)(\u2603 >> 24 & 0xFF) * f2);
                \u2603 = (int)((float)(\u2603 >> 16 & 0xFF) * f3);
                \u2603 = (int)((float)(\u2603 >> 8 & 0xFF) * f4);
                \u2603 &= 0xFF;
                \u2603 |= \u2603 << 24 | \u2603 << 16 | \u2603 << 8;
            }
        }
        this.b.put(\u2603, \u2603);
    }

    private void b(int n2, int n3) {
        \u2603 = this.c(n3);
        \u2603 = n2 >> 16 & 0xFF;
        \u2603 = n2 >> 8 & 0xFF;
        \u2603 = n2 & 0xFF;
        \u2603 = n2 >> 24 & 0xFF;
        this.a(\u2603, \u2603, \u2603, \u2603, \u2603);
    }

    public void b(float f2, float f3, float f4, int n2) {
        \u2603 = this.c(n2);
        \u2603 = ns.a((int)(f2 * 255.0f), 0, 255);
        \u2603 = ns.a((int)(f3 * 255.0f), 0, 255);
        \u2603 = ns.a((int)(f4 * 255.0f), 0, 255);
        this.a(\u2603, \u2603, \u2603, \u2603, 255);
    }

    private void a(int n2, int n3, int n4, int n5, int n6) {
        if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
            this.b.put(n2, n6 << 24 | n5 << 16 | n4 << 8 | n3);
        } else {
            this.b.put(n2, n3 << 24 | n4 << 16 | n5 << 8 | n6);
        }
    }

    public void c() {
        this.h = true;
    }

    public bfd a(float f2, float f3, float f4, float f5) {
        return this.b((int)(f2 * 255.0f), (int)(f3 * 255.0f), (int)(f4 * 255.0f), (int)(f5 * 255.0f));
    }

    public bfd b(int n2, int n3, int n4, int n5) {
        if (this.h) {
            return this;
        }
        \u2603 = this.e * this.m.g() + this.m.d(this.g);
        switch (this.f.a()) {
            case a: {
                this.a.putFloat(\u2603, (float)n2 / 255.0f);
                this.a.putFloat(\u2603 + 4, (float)n3 / 255.0f);
                this.a.putFloat(\u2603 + 8, (float)n4 / 255.0f);
                this.a.putFloat(\u2603 + 12, (float)n5 / 255.0f);
                break;
            }
            case f: 
            case g: {
                this.a.putFloat(\u2603, n2);
                this.a.putFloat(\u2603 + 4, n3);
                this.a.putFloat(\u2603 + 8, n4);
                this.a.putFloat(\u2603 + 12, n5);
                break;
            }
            case d: 
            case e: {
                this.a.putShort(\u2603, (short)n2);
                this.a.putShort(\u2603 + 2, (short)n3);
                this.a.putShort(\u2603 + 4, (short)n4);
                this.a.putShort(\u2603 + 6, (short)n5);
                break;
            }
            case b: 
            case c: {
                if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
                    this.a.put(\u2603, (byte)n2);
                    this.a.put(\u2603 + 1, (byte)n3);
                    this.a.put(\u2603 + 2, (byte)n4);
                    this.a.put(\u2603 + 3, (byte)n5);
                    break;
                }
                this.a.put(\u2603, (byte)n5);
                this.a.put(\u2603 + 1, (byte)n4);
                this.a.put(\u2603 + 2, (byte)n3);
                this.a.put(\u2603 + 3, (byte)n2);
            }
        }
        this.k();
        return this;
    }

    public void a(int[] nArray) {
        this.b(nArray.length);
        this.b.position(this.j());
        this.b.put(nArray);
        this.e += nArray.length / this.m.f();
    }

    public void d() {
        ++this.e;
        this.b(this.m.f());
    }

    public bfd b(double d2, double d3, double d4) {
        int n2 = this.e * this.m.g() + this.m.d(this.g);
        switch (this.f.a()) {
            case a: {
                this.a.putFloat(n2, (float)(d2 + this.j));
                this.a.putFloat(n2 + 4, (float)(d3 + this.k));
                this.a.putFloat(n2 + 8, (float)(d4 + this.l));
                break;
            }
            case f: 
            case g: {
                this.a.putInt(n2, Float.floatToRawIntBits((float)(d2 + this.j)));
                this.a.putInt(n2 + 4, Float.floatToRawIntBits((float)(d3 + this.k)));
                this.a.putInt(n2 + 8, Float.floatToRawIntBits((float)(d4 + this.l)));
                break;
            }
            case d: 
            case e: {
                this.a.putShort(n2, (short)(d2 + this.j));
                this.a.putShort(n2 + 2, (short)(d3 + this.k));
                this.a.putShort(n2 + 4, (short)(d4 + this.l));
                break;
            }
            case b: 
            case c: {
                this.a.put(n2, (byte)(d2 + this.j));
                this.a.put(n2 + 1, (byte)(d3 + this.k));
                this.a.put(n2 + 2, (byte)(d4 + this.l));
            }
        }
        this.k();
        return this;
    }

    public void b(float f2, float f3, float f4) {
        int n2 = (byte)(f2 * 127.0f) & 0xFF;
        \u2603 = (byte)(f3 * 127.0f) & 0xFF;
        \u2603 = (byte)(f4 * 127.0f) & 0xFF;
        \u2603 = n2 | \u2603 << 8 | \u2603 << 16;
        \u2603 = this.m.g() >> 2;
        \u2603 = (this.e - 4) * \u2603 + this.m.c() / 4;
        this.b.put(\u2603, \u2603);
        this.b.put(\u2603 + \u2603, \u2603);
        this.b.put(\u2603 + \u2603 * 2, \u2603);
        this.b.put(\u2603 + \u2603 * 3, \u2603);
    }

    private void k() {
        ++this.g;
        this.g %= this.m.i();
        this.f = this.m.c(this.g);
        if (this.f.b() == bmv.b.g) {
            this.k();
        }
    }

    public bfd c(float f2, float f3, float f4) {
        int n2 = this.e * this.m.g() + this.m.d(this.g);
        switch (this.f.a()) {
            case a: {
                this.a.putFloat(n2, f2);
                this.a.putFloat(n2 + 4, f3);
                this.a.putFloat(n2 + 8, f4);
                break;
            }
            case f: 
            case g: {
                this.a.putInt(n2, (int)f2);
                this.a.putInt(n2 + 4, (int)f3);
                this.a.putInt(n2 + 8, (int)f4);
                break;
            }
            case d: 
            case e: {
                this.a.putShort(n2, (short)((int)f2 * Short.MAX_VALUE & 0xFFFF));
                this.a.putShort(n2 + 2, (short)((int)f3 * Short.MAX_VALUE & 0xFFFF));
                this.a.putShort(n2 + 4, (short)((int)f4 * Short.MAX_VALUE & 0xFFFF));
                break;
            }
            case b: 
            case c: {
                this.a.put(n2, (byte)((int)f2 * 127 & 0xFF));
                this.a.put(n2 + 1, (byte)((int)f3 * 127 & 0xFF));
                this.a.put(n2 + 2, (byte)((int)f4 * 127 & 0xFF));
            }
        }
        this.k();
        return this;
    }

    public void c(double d2, double d3, double d4) {
        this.j = d2;
        this.k = d3;
        this.l = d4;
    }

    public void e() {
        if (!this.n) {
            throw new IllegalStateException("Not building!");
        }
        this.n = false;
        this.a.position(0);
        this.a.limit(this.j() * 4);
    }

    public ByteBuffer f() {
        return this.a;
    }

    public bmu g() {
        return this.m;
    }

    public int h() {
        return this.e;
    }

    public int i() {
        return this.i;
    }

    public void a(int n2) {
        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
            this.b(n2, \u2603 + 1);
        }
    }

    public void d(float f2, float f3, float f4) {
        for (int i2 = 0; i2 < 4; ++i2) {
            this.b(f2, f3, f4, i2 + 1);
        }
    }

    public class a {
        private final int[] b;
        private final bmu c;

        public a(int[] nArray, bmu bmu2) {
            this.b = nArray;
            this.c = bmu2;
        }

        public int[] a() {
            return this.b;
        }

        public int b() {
            return this.b.length / this.c.f();
        }

        public bmu c() {
            return this.c;
        }
    }
}

