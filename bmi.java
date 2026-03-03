/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class bmi {
    private final String j;
    protected List<int[][]> a = Lists.newArrayList();
    protected int[][] b;
    private boa k;
    protected boolean c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    private float l;
    private float m;
    private float n;
    private float o;
    protected int h;
    protected int i;
    private static String p = "builtin/clock";
    private static String q = "builtin/compass";

    protected bmi(String string) {
        this.j = string;
    }

    protected static bmi a(jy jy2) {
        String string = jy2.toString();
        if (p.equals(string)) {
            return new bmo(string);
        }
        if (q.equals(string)) {
            return new bmp(string);
        }
        return new bmi(string);
    }

    public static void a(String string) {
        p = string;
    }

    public static void b(String string) {
        q = string;
    }

    public void a(int n2, int n3, int n4, int n5, boolean bl2) {
        this.d = n4;
        this.e = n5;
        this.c = bl2;
        float f2 = (float)((double)0.01f / (double)n2);
        \u2603 = (float)((double)0.01f / (double)n3);
        this.l = (float)n4 / (float)((double)n2) + f2;
        this.m = (float)(n4 + this.f) / (float)((double)n2) - f2;
        this.n = (float)n5 / (float)n3 + \u2603;
        this.o = (float)(n5 + this.g) / (float)n3 - \u2603;
    }

    public void a(bmi bmi2) {
        this.d = bmi2.d;
        this.e = bmi2.e;
        this.f = bmi2.f;
        this.g = bmi2.g;
        this.c = bmi2.c;
        this.l = bmi2.l;
        this.m = bmi2.m;
        this.n = bmi2.n;
        this.o = bmi2.o;
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public float e() {
        return this.l;
    }

    public float f() {
        return this.m;
    }

    public float a(double d2) {
        float f2 = this.m - this.l;
        return this.l + f2 * (float)d2 / 16.0f;
    }

    public float g() {
        return this.n;
    }

    public float h() {
        return this.o;
    }

    public float b(double d2) {
        float f2 = this.o - this.n;
        return this.n + f2 * ((float)d2 / 16.0f);
    }

    public String i() {
        return this.j;
    }

    public void j() {
        ++this.i;
        if (this.i >= this.k.a(this.h)) {
            int n2 = this.k.c(this.h);
            \u2603 = this.k.c() == 0 ? this.a.size() : this.k.c();
            this.h = (this.h + 1) % \u2603;
            this.i = 0;
            \u2603 = this.k.c(this.h);
            if (n2 != \u2603 && \u2603 >= 0 && \u2603 < this.a.size()) {
                bml.a(this.a.get(\u2603), this.f, this.g, this.d, this.e, false, false);
            }
        } else if (this.k.e()) {
            this.n();
        }
    }

    private void n() {
        double d2 = 1.0 - (double)this.i / (double)this.k.a(this.h);
        int \u26032 = this.k.c(this.h);
        if (\u26032 != (\u2603 = this.k.c((this.h + 1) % (\u2603 = this.k.c() == 0 ? this.a.size() : this.k.c()))) && \u2603 >= 0 && \u2603 < this.a.size()) {
            int[][] nArray = this.a.get(\u26032);
            \u2603 = this.a.get(\u2603);
            if (this.b == null || this.b.length != nArray.length) {
                this.b = new int[nArray.length][];
            }
            for (int i2 = 0; i2 < nArray.length; ++i2) {
                if (this.b[i2] == null) {
                    this.b[i2] = new int[nArray[i2].length];
                }
                if (i2 >= \u2603.length || \u2603[i2].length != nArray[i2].length) continue;
                for (\u2603 = 0; \u2603 < nArray[i2].length; ++\u2603) {
                    \u2603 = nArray[i2][\u2603];
                    \u2603 = \u2603[i2][\u2603];
                    \u2603 = (int)((double)((\u2603 & 0xFF0000) >> 16) * d2 + (double)((\u2603 & 0xFF0000) >> 16) * (1.0 - d2));
                    \u2603 = (int)((double)((\u2603 & 0xFF00) >> 8) * d2 + (double)((\u2603 & 0xFF00) >> 8) * (1.0 - d2));
                    \u2603 = (int)((double)(\u2603 & 0xFF) * d2 + (double)(\u2603 & 0xFF) * (1.0 - d2));
                    this.b[i2][\u2603] = \u2603 & 0xFF000000 | \u2603 << 16 | \u2603 << 8 | \u2603;
                }
            }
            bml.a(this.b, this.f, this.g, this.d, this.e, false, false);
        }
    }

    public int[][] a(int n2) {
        return this.a.get(n2);
    }

    public int k() {
        return this.a.size();
    }

    public void b(int n2) {
        this.f = n2;
    }

    public void c(int n2) {
        this.g = n2;
    }

    public void a(BufferedImage[] bufferedImageArray, boa boa22) throws IOException {
        boa boa22;
        this.o();
        int n2 = bufferedImageArray[0].getWidth();
        \u2603 = bufferedImageArray[0].getHeight();
        this.f = n2;
        this.g = \u2603;
        int[][] \u26032 = new int[bufferedImageArray.length][];
        for (n3 = 0; n3 < bufferedImageArray.length; ++n3) {
            BufferedImage bufferedImage = bufferedImageArray[n3];
            if (bufferedImage == null) continue;
            if (n3 > 0 && (bufferedImage.getWidth() != n2 >> n3 || bufferedImage.getHeight() != \u2603 >> n3)) {
                throw new RuntimeException(String.format("Unable to load miplevel: %d, image is size: %dx%d, expected %dx%d", n3, bufferedImage.getWidth(), bufferedImage.getHeight(), n2 >> n3, \u2603 >> n3));
            }
            \u26032[n3] = new int[bufferedImage.getWidth() * bufferedImage.getHeight()];
            bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), \u26032[n3], 0, bufferedImage.getWidth());
        }
        if (boa22 == null) {
            if (\u2603 != n2) {
                throw new RuntimeException("broken aspect ratio and not an animation");
            }
            this.a.add(\u26032);
        } else {
            int n3 = \u2603 / n2;
            \u2603 = n2;
            \u2603 = n2;
            this.g = this.f;
            if (boa22.c() > 0) {
                for (int n4 : boa22.f()) {
                    if (n4 >= n3) {
                        throw new RuntimeException("invalid frameindex " + n4);
                    }
                    this.e(n4);
                    this.a.set(n4, bmi.a(\u26032, \u2603, \u2603, n4));
                }
                this.k = boa22;
            } else {
                ArrayList<bnz> arrayList = Lists.newArrayList();
                for (int i2 = 0; i2 < n3; ++i2) {
                    this.a.add(bmi.a(\u26032, \u2603, \u2603, i2));
                    arrayList.add(new bnz(i2, -1));
                }
                this.k = new boa(arrayList, this.f, this.g, boa22.d(), boa22.e());
            }
        }
    }

    public void d(int n2) {
        ArrayList<int[][]> arrayList = Lists.newArrayList();
        for (int i2 = 0; i2 < this.a.size(); ++i2) {
            final int[][] nArray = this.a.get(i2);
            if (nArray == null) continue;
            try {
                arrayList.add(bml.a(n2, this.f, nArray));
                continue;
            }
            catch (Throwable \u26032) {
                b b2 = b.a(\u26032, "Generating mipmaps for frame");
                c \u26033 = b2.a("Frame being iterated");
                \u26033.a("Frame index", i2);
                \u26033.a("Frame sizes", new Callable<String>(){

                    public String a() throws Exception {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int[] nArray2 : nArray) {
                            if (stringBuilder.length() > 0) {
                                stringBuilder.append(", ");
                            }
                            stringBuilder.append(nArray2 == null ? "null" : Integer.valueOf(nArray2.length));
                        }
                        return stringBuilder.toString();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                throw new e(b2);
            }
        }
        this.a(arrayList);
    }

    private void e(int n2) {
        if (this.a.size() > n2) {
            return;
        }
        for (\u2603 = this.a.size(); \u2603 <= n2; ++\u2603) {
            this.a.add(null);
        }
    }

    private static int[][] a(int[][] nArray, int n2, int n3, int n4) {
        int[][] nArrayArray = new int[nArray.length][];
        for (int i2 = 0; i2 < nArray.length; ++i2) {
            int[] nArray2 = nArray[i2];
            if (nArray2 == null) continue;
            nArrayArray[i2] = new int[(n2 >> i2) * (n3 >> i2)];
            System.arraycopy(nArray2, n4 * nArrayArray[i2].length, nArrayArray[i2], 0, nArrayArray[i2].length);
        }
        return nArrayArray;
    }

    public void l() {
        this.a.clear();
    }

    public boolean m() {
        return this.k != null;
    }

    public void a(List<int[][]> list) {
        this.a = list;
    }

    private void o() {
        this.k = null;
        this.a(Lists.<int[][]>newArrayList());
        this.h = 0;
        this.i = 0;
    }

    public String toString() {
        return "TextureAtlasSprite{name='" + this.j + '\'' + ", frameCount=" + this.a.size() + ", rotated=" + this.c + ", x=" + this.d + ", y=" + this.e + ", height=" + this.g + ", width=" + this.f + ", u0=" + this.l + ", u1=" + this.m + ", v0=" + this.n + ", v1=" + this.o + '}';
    }
}

