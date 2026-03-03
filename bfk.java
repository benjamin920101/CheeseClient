/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

public class bfk
implements bnj {
    private static final Logger e = LogManager.getLogger();
    private static final jy f = new jy("textures/environment/rain.png");
    private static final jy g = new jy("textures/environment/snow.png");
    public static boolean a;
    public static int b;
    private ave h;
    private final bni i;
    private Random j = new Random();
    private float k;
    public final bfn c;
    private final avq l;
    private int m;
    private pk n;
    private nv o = new nv();
    private nv p = new nv();
    private float q = 4.0f;
    private float r = 4.0f;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private boolean B;
    private boolean C = true;
    private boolean D = true;
    private long E = ave.J();
    private long F;
    private final blz G;
    private final int[] H;
    private final jy I;
    private boolean J;
    private float K;
    private float L;
    private int M;
    private float[] N = new float[1024];
    private float[] O = new float[1024];
    private FloatBuffer P = avd.h(16);
    private float Q;
    private float R;
    private float S;
    private float T;
    private float U;
    private int V = 0;
    private boolean W = false;
    private double X = 1.0;
    private double Y;
    private double Z;
    private blr aa;
    private static final jy[] ab;
    public static final int d;
    private int ac = d;
    private boolean ad = false;
    private int ae = 0;

    public bfk(ave ave2, bni bni2) {
        this.h = ave2;
        this.i = bni2;
        this.c = ave2.ah();
        this.l = new avq(ave2.P());
        this.G = new blz(16, 16);
        this.I = ave2.P().a("lightMap", this.G);
        this.H = this.G.e();
        this.aa = null;
        for (int i2 = 0; i2 < 32; ++i2) {
            for (\u2603 = 0; \u2603 < 32; ++\u2603) {
                float f2 = \u2603 - 16;
                \u2603 = i2 - 16;
                \u2603 = ns.c(f2 * f2 + \u2603 * \u2603);
                this.N[i2 << 5 | \u2603] = -\u2603 / \u2603;
                this.O[i2 << 5 | \u2603] = f2 / \u2603;
            }
        }
    }

    public boolean a() {
        return bqs.O && this.aa != null;
    }

    public void b() {
        if (this.aa != null) {
            this.aa.a();
        }
        this.aa = null;
        this.ac = d;
    }

    public void c() {
        this.ad = !this.ad;
    }

    public void a(pk pk2) {
        if (!bqs.O) {
            return;
        }
        if (this.aa != null) {
            this.aa.a();
        }
        this.aa = null;
        if (pk2 instanceof vn) {
            this.a(new jy("shaders/post/creeper.json"));
        } else if (pk2 instanceof wc) {
            this.a(new jy("shaders/post/spider.json"));
        } else if (pk2 instanceof vo) {
            this.a(new jy("shaders/post/invert.json"));
        }
    }

    public void d() {
        if (!bqs.O) {
            return;
        }
        if (!(this.h.ac() instanceof wn)) {
            return;
        }
        if (this.aa != null) {
            this.aa.a();
        }
        this.ac = (this.ac + 1) % (ab.length + 1);
        if (this.ac != d) {
            this.a(ab[this.ac]);
        } else {
            this.aa = null;
        }
    }

    private void a(jy jy2) {
        try {
            this.aa = new blr(this.h.P(), this.i, this.h.b(), jy2);
            this.aa.a(this.h.d, this.h.e);
            this.ad = true;
        }
        catch (IOException iOException) {
            e.warn("Failed to load shader: " + jy2, (Throwable)iOException);
            this.ac = d;
            this.ad = false;
        }
        catch (JsonSyntaxException jsonSyntaxException) {
            e.warn("Failed to load shader: " + jy2, (Throwable)jsonSyntaxException);
            this.ac = d;
            this.ad = false;
        }
    }

    @Override
    public void a(bni bni2) {
        if (this.aa != null) {
            this.aa.a();
        }
        this.aa = null;
        if (this.ac != d) {
            this.a(ab[this.ac]);
        } else {
            this.a(this.h.ac());
        }
    }

    public void e() {
        float f2;
        if (bqs.O && blu.b() == null) {
            blu.a();
        }
        this.l();
        this.m();
        this.T = this.U;
        this.r = this.q;
        if (this.h.t.aF) {
            f2 = this.h.t.a * 0.6f + 0.2f;
            \u2603 = f2 * f2 * f2 * 8.0f;
            this.u = this.o.a(this.s, 0.05f * \u2603);
            this.v = this.p.a(this.t, 0.05f * \u2603);
            this.w = 0.0f;
            this.s = 0.0f;
            this.t = 0.0f;
        } else {
            this.u = 0.0f;
            this.v = 0.0f;
            this.o.a();
            this.p.a();
        }
        if (this.h.ac() == null) {
            this.h.a(this.h.h);
        }
        f2 = this.h.f.o(new cj(this.h.ac()));
        \u2603 = (float)this.h.t.c / 32.0f;
        \u2603 = f2 * (1.0f - \u2603) + \u2603;
        this.U += (\u2603 - this.U) * 0.1f;
        ++this.m;
        this.c.a();
        this.o();
        this.A = this.z;
        if (bfc.d) {
            this.z += 0.05f;
            if (this.z > 1.0f) {
                this.z = 1.0f;
            }
            bfc.d = false;
        } else if (this.z > 0.0f) {
            this.z -= 0.0125f;
        }
    }

    public blr f() {
        return this.aa;
    }

    public void a(int n2, int n3) {
        if (!bqs.O) {
            return;
        }
        if (this.aa != null) {
            this.aa.a(n2, n3);
        }
        this.h.g.a(n2, n3);
    }

    public void a(float f2) {
        pk pk2 = this.h.ac();
        if (pk2 == null) {
            return;
        }
        if (this.h.f == null) {
            return;
        }
        this.h.A.a("pick");
        this.h.i = null;
        double \u26032 = this.h.c.d();
        this.h.s = pk2.a(\u26032, f2);
        double \u26033 = \u26032;
        aui \u26034 = pk2.e(f2);
        boolean \u26035 = false;
        int \u26036 = 3;
        if (this.h.c.i()) {
            \u26032 = 6.0;
            \u26033 = 6.0;
        } else {
            if (\u26033 > 3.0) {
                \u26035 = true;
            }
            \u26032 = \u26033;
        }
        if (this.h.s != null) {
            \u26033 = this.h.s.c.f(\u26034);
        }
        aui \u26037 = pk2.d(f2);
        aui \u26038 = \u26034.b(\u26037.a * \u26032, \u26037.b * \u26032, \u26037.c * \u26032);
        this.n = null;
        aui \u26039 = null;
        float \u260310 = 1.0f;
        List<pk> \u260311 = this.h.f.a(pk2, pk2.aR().a(\u26037.a * \u26032, \u26037.b * \u26032, \u26037.c * \u26032).b(\u260310, \u260310, \u260310), Predicates.and(po.d, new Predicate<pk>(){

            public boolean a(pk pk2) {
                return pk2.ad();
            }

            @Override
            public /* synthetic */ boolean apply(Object object) {
                return this.a((pk)object);
            }
        }));
        double \u260312 = \u26033;
        for (int i2 = 0; i2 < \u260311.size(); ++i2) {
            pk pk3 = \u260311.get(i2);
            float \u260313 = pk3.ao();
            aug \u260314 = pk3.aR().b(\u260313, \u260313, \u260313);
            auh \u260315 = \u260314.a(\u26034, \u26038);
            if (\u260314.a(\u26034)) {
                if (!(\u260312 >= 0.0)) continue;
                this.n = pk3;
                \u26039 = \u260315 == null ? \u26034 : \u260315.c;
                \u260312 = 0.0;
                continue;
            }
            if (\u260315 == null || !((\u2603 = \u26034.f(\u260315.c)) < \u260312) && \u260312 != 0.0) continue;
            if (pk3 == pk2.m) {
                if (\u260312 != 0.0) continue;
                this.n = pk3;
                \u26039 = \u260315.c;
                continue;
            }
            this.n = pk3;
            \u26039 = \u260315.c;
            \u260312 = \u2603;
        }
        if (this.n != null && \u26035 && \u26034.f(\u26039) > 3.0) {
            this.n = null;
            this.h.s = new auh(auh.a.a, \u26039, null, new cj(\u26039));
        }
        if (this.n != null && (\u260312 < \u26033 || this.h.s == null)) {
            this.h.s = new auh(this.n, \u26039);
            if (this.n instanceof pr || this.n instanceof uo) {
                this.h.i = this.n;
            }
        }
        this.h.A.b();
    }

    private void l() {
        float \u26032 = 1.0f;
        if (this.h.ac() instanceof bet) {
            bet bet2 = (bet)this.h.ac();
            \u26032 = bet2.o();
        }
        this.y = this.x;
        this.x += (\u26032 - this.x) * 0.5f;
        if (this.x > 1.5f) {
            this.x = 1.5f;
        }
        if (this.x < 0.1f) {
            this.x = 0.1f;
        }
    }

    private float a(float f2, boolean bl2) {
        afh afh2;
        if (this.W) {
            return 90.0f;
        }
        pk pk2 = this.h.ac();
        float \u26032 = 70.0f;
        if (bl2) {
            \u26032 = this.h.t.aH;
            \u26032 *= this.y + (this.x - this.y) * f2;
        }
        if (pk2 instanceof pr && ((pr)pk2).bn() <= 0.0f) {
            float f3 = (float)((pr)pk2).ax + f2;
            \u26032 /= (1.0f - 500.0f / (f3 + 500.0f)) * 2.0f + 1.0f;
        }
        if ((afh2 = auz.a(this.h.f, pk2, f2)).t() == arm.h) {
            \u26032 = \u26032 * 60.0f / 70.0f;
        }
        return \u26032;
    }

    private void d(float f2) {
        if (this.h.ac() instanceof pr) {
            float \u26033;
            pr pr2 = (pr)this.h.ac();
            float \u26032 = (float)pr2.au - f2;
            if (pr2.bn() <= 0.0f) {
                \u26033 = (float)pr2.ax + f2;
                bfl.b(40.0f - 8000.0f / (\u26033 + 200.0f), 0.0f, 0.0f, 1.0f);
            }
            if (\u26032 < 0.0f) {
                return;
            }
            \u26032 /= (float)pr2.av;
            \u26032 = ns.a(\u26032 * \u26032 * \u26032 * \u26032 * (float)Math.PI);
            \u26033 = pr2.aw;
            bfl.b(-\u26033, 0.0f, 1.0f, 0.0f);
            bfl.b(-\u26032 * 14.0f, 0.0f, 0.0f, 1.0f);
            bfl.b(\u26033, 0.0f, 1.0f, 0.0f);
        }
    }

    private void e(float f2) {
        if (!(this.h.ac() instanceof wn)) {
            return;
        }
        wn wn2 = (wn)this.h.ac();
        float \u26032 = wn2.M - wn2.L;
        float \u26033 = -(wn2.M + \u26032 * f2);
        float \u26034 = wn2.bn + (wn2.bo - wn2.bn) * f2;
        float \u26035 = wn2.aE + (wn2.aF - wn2.aE) * f2;
        bfl.b(ns.a(\u26033 * (float)Math.PI) * \u26034 * 0.5f, -Math.abs(ns.b(\u26033 * (float)Math.PI) * \u26034), 0.0f);
        bfl.b(ns.a(\u26033 * (float)Math.PI) * \u26034 * 3.0f, 0.0f, 0.0f, 1.0f);
        bfl.b(Math.abs(ns.b(\u26033 * (float)Math.PI - 0.2f) * \u26034) * 5.0f, 1.0f, 0.0f, 0.0f);
        bfl.b(\u26035, 1.0f, 0.0f, 0.0f);
    }

    private void f(float f22) {
        Object object;
        pk pk2 = this.h.ac();
        float \u26032 = pk2.aS();
        double \u26033 = pk2.p + (pk2.s - pk2.p) * (double)f22;
        double \u26034 = pk2.q + (pk2.t - pk2.q) * (double)f22 + (double)\u26032;
        double \u26035 = pk2.r + (pk2.u - pk2.r) * (double)f22;
        if (pk2 instanceof pr && ((pr)pk2).bJ()) {
            \u26032 = (float)((double)\u26032 + 1.0);
            bfl.b(0.0f, 0.3f, 0.0f);
            if (!this.h.t.aG) {
                object = new cj(pk2);
                alz \u26036 = this.h.f.p((cj)object);
                afh \u26037 = \u26036.c();
                if (\u26037 == afi.C) {
                    int n2 = \u26036.b(afg.O).b();
                    bfl.b((float)(n2 * 90), 0.0f, 1.0f, 0.0f);
                }
                bfl.b(pk2.A + (pk2.y - pk2.A) * f22 + 180.0f, 0.0f, -1.0f, 0.0f);
                bfl.b(pk2.B + (pk2.z - pk2.B) * f22, -1.0f, 0.0f, 0.0f);
            }
        } else if (this.h.t.aA > 0) {
            float f22;
            double \u26038 = this.r + (this.q - this.r) * f22;
            if (this.h.t.aG) {
                bfl.b(0.0f, 0.0f, (float)(-\u26038));
            } else {
                \u2603 = pk2.y;
                \u2603 = pk2.z;
                if (this.h.t.aA == 2) {
                    \u2603 += 180.0f;
                }
                double d2 = (double)(-ns.a(\u2603 / 180.0f * (float)Math.PI) * ns.b(\u2603 / 180.0f * (float)Math.PI)) * \u26038;
                \u2603 = (double)(ns.b(\u2603 / 180.0f * (float)Math.PI) * ns.b(\u2603 / 180.0f * (float)Math.PI)) * \u26038;
                \u2603 = (double)(-ns.a(\u2603 / 180.0f * (float)Math.PI)) * \u26038;
                for (int i2 = 0; i2 < 8; ++i2) {
                    float f3 = (i2 & 1) * 2 - 1;
                    \u2603 = (i2 >> 1 & 1) * 2 - 1;
                    \u2603 = (i2 >> 2 & 1) * 2 - 1;
                    if ((\u2603 = this.h.f.a(new aui(\u26033 + (double)(f3 *= 0.1f), \u26034 + (double)(\u2603 *= 0.1f), \u26035 + (double)(\u2603 *= 0.1f)), new aui(\u26033 - d2 + (double)f3 + (double)\u2603, \u26034 - \u2603 + (double)\u2603, \u26035 - \u2603 + (double)\u2603))) == null || !((\u2603 = \u2603.c.f(new aui(\u26033, \u26034, \u26035))) < \u26038)) continue;
                    \u26038 = \u2603;
                }
                if (this.h.t.aA == 2) {
                    bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
                }
                bfl.b(pk2.z - \u2603, 1.0f, 0.0f, 0.0f);
                bfl.b(pk2.y - \u2603, 0.0f, 1.0f, 0.0f);
                bfl.b(0.0f, 0.0f, (float)(-\u26038));
                bfl.b(\u2603 - pk2.y, 0.0f, 1.0f, 0.0f);
                bfl.b(\u2603 - pk2.z, 1.0f, 0.0f, 0.0f);
            }
        } else {
            bfl.b(0.0f, 0.0f, -0.1f);
        }
        if (!this.h.t.aG) {
            bfl.b(pk2.B + (pk2.z - pk2.B) * f22, 1.0f, 0.0f, 0.0f);
            if (pk2 instanceof tm) {
                object = (tm)pk2;
                bfl.b(((tm)object).aL + (((tm)object).aK - ((tm)object).aL) * f22 + 180.0f, 0.0f, 1.0f, 0.0f);
            } else {
                bfl.b(pk2.A + (pk2.y - pk2.A) * f22 + 180.0f, 0.0f, 1.0f, 0.0f);
            }
        }
        bfl.b(0.0f, -\u26032, 0.0f);
        \u26033 = pk2.p + (pk2.s - pk2.p) * (double)f22;
        \u26034 = pk2.q + (pk2.t - pk2.q) * (double)f22 + (double)\u26032;
        \u26035 = pk2.r + (pk2.u - pk2.r) * (double)f22;
        this.B = this.h.g.a(\u26033, \u26034, \u26035, f22);
    }

    private void a(float f2, int n2) {
        this.k = this.h.t.c * 16;
        bfl.n(5889);
        bfl.D();
        float f3 = 0.07f;
        if (this.h.t.e) {
            bfl.b((float)(-(n2 * 2 - 1)) * f3, 0.0f, 0.0f);
        }
        if (this.X != 1.0) {
            bfl.b((float)this.Y, (float)(-this.Z), 0.0f);
            bfl.a(this.X, this.X, 1.0);
        }
        Project.gluPerspective(this.a(f2, true), (float)this.h.d / (float)this.h.e, 0.05f, this.k * ns.a);
        bfl.n(5888);
        bfl.D();
        if (this.h.t.e) {
            bfl.b((float)(n2 * 2 - 1) * 0.1f, 0.0f, 0.0f);
        }
        this.d(f2);
        if (this.h.t.d) {
            this.e(f2);
        }
        if ((\u2603 = this.h.h.bI + (this.h.h.bH - this.h.h.bI) * f2) > 0.0f) {
            int n3 = 20;
            if (this.h.h.a(pe.k)) {
                n3 = 7;
            }
            float \u26032 = 5.0f / (\u2603 * \u2603 + 5.0f) - \u2603 * 0.04f;
            \u26032 *= \u26032;
            bfl.b(((float)this.m + f2) * (float)n3, 0.0f, 1.0f, 1.0f);
            bfl.a(1.0f / \u26032, 1.0f, 1.0f);
            bfl.b(-((float)this.m + f2) * (float)n3, 0.0f, 1.0f, 1.0f);
        }
        this.f(f2);
        if (this.W) {
            switch (this.V) {
                case 0: {
                    bfl.b(90.0f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 1: {
                    bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 2: {
                    bfl.b(-90.0f, 0.0f, 1.0f, 0.0f);
                    break;
                }
                case 3: {
                    bfl.b(90.0f, 1.0f, 0.0f, 0.0f);
                    break;
                }
                case 4: {
                    bfl.b(-90.0f, 1.0f, 0.0f, 0.0f);
                }
            }
        }
    }

    private void b(float f2, int n2) {
        if (this.W) {
            return;
        }
        bfl.n(5889);
        bfl.D();
        float f3 = 0.07f;
        if (this.h.t.e) {
            bfl.b((float)(-(n2 * 2 - 1)) * f3, 0.0f, 0.0f);
        }
        Project.gluPerspective(this.a(f2, false), (float)this.h.d / (float)this.h.e, 0.05f, this.k * 2.0f);
        bfl.n(5888);
        bfl.D();
        if (this.h.t.e) {
            bfl.b((float)(n2 * 2 - 1) * 0.1f, 0.0f, 0.0f);
        }
        bfl.E();
        this.d(f2);
        if (this.h.t.d) {
            this.e(f2);
        }
        boolean bl2 = \u2603 = this.h.ac() instanceof pr && ((pr)this.h.ac()).bJ();
        if (!(this.h.t.aA != 0 || \u2603 || this.h.t.az || this.h.c.a())) {
            this.i();
            this.c.a(f2);
            this.h();
        }
        bfl.F();
        if (this.h.t.aA == 0 && !\u2603) {
            this.c.b(f2);
            this.d(f2);
        }
        if (this.h.t.d) {
            this.e(f2);
        }
    }

    public void h() {
        bfl.g(bqs.r);
        bfl.x();
        bfl.g(bqs.q);
    }

    public void i() {
        bfl.g(bqs.r);
        bfl.n(5890);
        bfl.D();
        float f2 = 0.00390625f;
        bfl.a(f2, f2, f2);
        bfl.b(8.0f, 8.0f, 8.0f);
        bfl.n(5888);
        this.h.P().a(this.I);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glTexParameteri(3553, 10242, 10496);
        GL11.glTexParameteri(3553, 10243, 10496);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.w();
        bfl.g(bqs.q);
    }

    private void m() {
        this.L = (float)((double)this.L + (Math.random() - Math.random()) * Math.random() * Math.random());
        this.L = (float)((double)this.L * 0.9);
        this.K += (this.L - this.K) * 1.0f;
        this.J = true;
    }

    private void g(float f2) {
        if (!this.J) {
            return;
        }
        this.h.A.a("lightTex");
        bdb bdb2 = this.h.f;
        if (bdb2 == null) {
            return;
        }
        float \u26032 = bdb2.b(1.0f);
        float \u26033 = \u26032 * 0.95f + 0.05f;
        for (int i2 = 0; i2 < 256; ++i2) {
            float f3 = bdb2.t.p()[i2 / 16] * \u26033;
            \u2603 = bdb2.t.p()[i2 % 16] * (this.K * 0.1f + 1.5f);
            if (bdb2.ac() > 0) {
                f3 = bdb2.t.p()[i2 / 16];
            }
            \u2603 = f3 * (\u26032 * 0.65f + 0.35f);
            \u2603 = f3 * (\u26032 * 0.65f + 0.35f);
            \u2603 = f3;
            \u2603 = \u2603;
            \u2603 = \u2603 * ((\u2603 * 0.6f + 0.4f) * 0.6f + 0.4f);
            \u2603 = \u2603 * (\u2603 * \u2603 * 0.6f + 0.4f);
            \u2603 = \u2603 + \u2603;
            \u2603 = \u2603 + \u2603;
            \u2603 = \u2603 + \u2603;
            \u2603 = \u2603 * 0.96f + 0.03f;
            \u2603 = \u2603 * 0.96f + 0.03f;
            \u2603 = \u2603 * 0.96f + 0.03f;
            if (this.z > 0.0f) {
                \u2603 = this.A + (this.z - this.A) * f2;
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * 0.7f * \u2603;
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * 0.6f * \u2603;
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * 0.6f * \u2603;
            }
            if (bdb2.t.q() == 1) {
                \u2603 = 0.22f + \u2603 * 0.75f;
                \u2603 = 0.28f + \u2603 * 0.75f;
                \u2603 = 0.25f + \u2603 * 0.75f;
            }
            if (this.h.h.a(pe.r)) {
                \u2603 = this.a(this.h.h, f2);
                \u2603 = 1.0f / \u2603;
                if (\u2603 > 1.0f / \u2603) {
                    \u2603 = 1.0f / \u2603;
                }
                if (\u2603 > 1.0f / \u2603) {
                    \u2603 = 1.0f / \u2603;
                }
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603 * \u2603;
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603 * \u2603;
                \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603 * \u2603;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            \u2603 = this.h.t.aI;
            \u2603 = 1.0f - \u2603;
            \u2603 = 1.0f - \u2603;
            \u2603 = 1.0f - \u2603;
            \u2603 = 1.0f - \u2603 * \u2603 * \u2603 * \u2603;
            \u2603 = 1.0f - \u2603 * \u2603 * \u2603 * \u2603;
            \u2603 = 1.0f - \u2603 * \u2603 * \u2603 * \u2603;
            \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603;
            \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603;
            \u2603 = \u2603 * (1.0f - \u2603) + \u2603 * \u2603;
            \u2603 = \u2603 * 0.96f + 0.03f;
            \u2603 = \u2603 * 0.96f + 0.03f;
            \u2603 = \u2603 * 0.96f + 0.03f;
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            if (\u2603 > 1.0f) {
                \u2603 = 1.0f;
            }
            if (\u2603 < 0.0f) {
                \u2603 = 0.0f;
            }
            if (\u2603 < 0.0f) {
                \u2603 = 0.0f;
            }
            if (\u2603 < 0.0f) {
                \u2603 = 0.0f;
            }
            int \u26034 = 255;
            int \u26035 = (int)(\u2603 * 255.0f);
            int \u26036 = (int)(\u2603 * 255.0f);
            int \u26037 = (int)(\u2603 * 255.0f);
            this.H[i2] = \u26034 << 24 | \u26035 << 16 | \u26036 << 8 | \u26037;
        }
        this.G.d();
        this.J = false;
        this.h.A.b();
    }

    private float a(pr pr2, float f2) {
        int n2 = pr2.b(pe.r).b();
        if (n2 > 200) {
            return 1.0f;
        }
        return 0.7f + ns.a(((float)n2 - f2) * (float)Math.PI * 0.2f) * 0.3f;
    }

    public void a(float f2, long l2) {
        int \u26032;
        boolean bl2 = Display.isActive();
        if (bl2 || !this.h.t.z || this.h.t.A && Mouse.isButtonDown(1)) {
            this.E = ave.J();
        } else if (ave.J() - this.E > 500L) {
            this.h.p();
        }
        this.h.A.a("mouse");
        if (bl2 && ave.a && this.h.w && !Mouse.isInsideWindow()) {
            Mouse.setGrabbed(false);
            Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
            Mouse.setGrabbed(true);
        }
        if (this.h.w && bl2) {
            this.h.u.c();
            float f3 = this.h.t.a * 0.6f + 0.2f;
            \u2603 = f3 * f3 * f3 * 8.0f;
            \u2603 = (float)this.h.u.a * \u2603;
            \u2603 = (float)this.h.u.b * \u2603;
            \u26032 = 1;
            if (this.h.t.b) {
                \u26032 = -1;
            }
            if (this.h.t.aF) {
                this.s += \u2603;
                this.t += \u2603;
                \u2603 = f2 - this.w;
                this.w = f2;
                \u2603 = this.u * \u2603;
                \u2603 = this.v * \u2603;
                this.h.h.c(\u2603, \u2603 * (float)\u26032);
            } else {
                this.s = 0.0f;
                this.t = 0.0f;
                this.h.h.c(\u2603, \u2603 * (float)\u26032);
            }
        }
        this.h.A.b();
        if (this.h.r) {
            return;
        }
        a = this.h.t.e;
        final avr avr2 = new avr(this.h);
        int \u26033 = avr2.a();
        int \u26034 = avr2.b();
        final int \u26035 = Mouse.getX() * \u26033 / this.h.d;
        \u26032 = \u26034 - Mouse.getY() * \u26034 / this.h.e - 1;
        int \u26036 = this.h.t.g;
        if (this.h.f != null) {
            this.h.A.a("level");
            int n2 = Math.min(ave.ai(), \u26036);
            n2 = Math.max(n2, 60);
            long \u26037 = System.nanoTime() - l2;
            long \u26038 = Math.max((long)(1000000000 / n2 / 4) - \u26037, 0L);
            this.b(f2, System.nanoTime() + \u26038);
            if (bqs.O) {
                this.h.g.c();
                if (this.aa != null && this.ad) {
                    bfl.n(5890);
                    bfl.E();
                    bfl.D();
                    this.aa.a(f2);
                    bfl.F();
                }
                this.h.b().a(true);
            }
            this.F = System.nanoTime();
            this.h.A.c("gui");
            if (!this.h.t.az || this.h.m != null) {
                bfl.a(516, 0.1f);
                this.h.q.a(f2);
            }
            this.h.A.b();
        } else {
            bfl.b(0, 0, this.h.d, this.h.e);
            bfl.n(5889);
            bfl.D();
            bfl.n(5888);
            bfl.D();
            this.j();
            this.F = System.nanoTime();
        }
        if (this.h.m != null) {
            bfl.m(256);
            try {
                this.h.m.a(\u26035, \u26032, f2);
            }
            catch (Throwable \u26039) {
                b b2 = b.a(\u26039, "Rendering screen");
                c \u260310 = b2.a("Screen render details");
                \u260310.a("Screen name", new Callable<String>(){

                    public String a() throws Exception {
                        return ((bfk)bfk.this).h.m.getClass().getCanonicalName();
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                \u260310.a("Mouse location", new Callable<String>(){

                    public String a() throws Exception {
                        return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", \u26035, \u26032, Mouse.getX(), Mouse.getY());
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                \u260310.a("Screen size", new Callable<String>(){

                    public String a() throws Exception {
                        return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", avr2.a(), avr2.b(), ((bfk)bfk.this).h.d, ((bfk)bfk.this).h.e, avr2.e());
                    }

                    @Override
                    public /* synthetic */ Object call() throws Exception {
                        return this.a();
                    }
                });
                throw new e(b2);
            }
        }
    }

    public void b(float f2) {
        this.j();
        this.h.q.c(new avr(this.h));
    }

    private boolean n() {
        boolean \u26033;
        if (!this.D) {
            return false;
        }
        pk pk2 = this.h.ac();
        boolean bl2 = \u26033 = pk2 instanceof wn && !this.h.t.az;
        if (\u26033 && !((wn)pk2).bA.e) {
            zx zx2 = ((wn)pk2).bZ();
            if (this.h.s != null && this.h.s.a == auh.a.b) {
                cj cj2 = this.h.s.a();
                afh \u26032 = this.h.f.p(cj2).c();
                \u26033 = this.h.c.l() == adp.a.e ? \u26032.z() && this.h.f.s(cj2) instanceof og : zx2 != null && (zx2.c(\u26032) || zx2.d(\u26032));
            }
        }
        return \u26033;
    }

    private void h(float f2) {
        if (this.h.t.aB && !this.h.t.az && !this.h.h.cq() && !this.h.t.w) {
            pk pk2 = this.h.ac();
            bfl.l();
            bfl.a(770, 771, 1, 0);
            GL11.glLineWidth(1.0f);
            bfl.x();
            bfl.a(false);
            bfl.E();
            bfl.n(5888);
            bfl.D();
            this.f(f2);
            bfl.b(0.0f, pk2.aS(), 0.0f);
            bfr.a(new aug(0.0, 0.0, 0.0, 0.005, 1.0E-4, 1.0E-4), 255, 0, 0, 255);
            bfr.a(new aug(0.0, 0.0, 0.0, 1.0E-4, 1.0E-4, 0.005), 0, 0, 255, 255);
            bfr.a(new aug(0.0, 0.0, 0.0, 1.0E-4, 0.0033, 1.0E-4), 0, 255, 0, 255);
            bfl.F();
            bfl.a(true);
            bfl.w();
            bfl.k();
        }
    }

    public void b(float f2, long l2) {
        this.g(f2);
        if (this.h.ac() == null) {
            this.h.a(this.h.h);
        }
        this.a(f2);
        bfl.j();
        bfl.d();
        bfl.a(516, 0.5f);
        this.h.A.a("center");
        if (this.h.t.e) {
            b = 0;
            bfl.a(false, true, true, false);
            this.a(0, f2, l2);
            b = 1;
            bfl.a(true, false, false, false);
            this.a(1, f2, l2);
            bfl.a(true, true, true, false);
        } else {
            this.a(2, f2, l2);
        }
        this.h.A.b();
    }

    private void a(int n2, float f2, long l2) {
        wn wn2;
        bfr bfr2 = this.h.g;
        bec \u26032 = this.h.j;
        boolean \u26033 = this.n();
        bfl.o();
        this.h.A.c("clear");
        bfl.b(0, 0, this.h.d, this.h.e);
        this.i(f2);
        bfl.m(16640);
        this.h.A.c("camera");
        this.a(f2, n2);
        auz.a(this.h.h, this.h.t.aA == 2);
        this.h.A.c("frustum");
        bib.a();
        this.h.A.c("culling");
        bic \u26034 = new bic();
        pk \u26035 = this.h.ac();
        double \u26036 = \u26035.P + (\u26035.s - \u26035.P) * (double)f2;
        double \u26037 = \u26035.Q + (\u26035.t - \u26035.Q) * (double)f2;
        double \u26038 = \u26035.R + (\u26035.u - \u26035.R) * (double)f2;
        \u26034.a(\u26036, \u26037, \u26038);
        if (this.h.t.c >= 4) {
            this.a(-1, f2);
            this.h.A.c("sky");
            bfl.n(5889);
            bfl.D();
            Project.gluPerspective(this.a(f2, true), (float)this.h.d / (float)this.h.e, 0.05f, this.k * 2.0f);
            bfl.n(5888);
            bfr2.a(f2, n2);
            bfl.n(5889);
            bfl.D();
            Project.gluPerspective(this.a(f2, true), (float)this.h.d / (float)this.h.e, 0.05f, this.k * ns.a);
            bfl.n(5888);
        }
        this.a(0, f2);
        bfl.j(7425);
        if (\u26035.t + (double)\u26035.aS() < 128.0) {
            this.a(bfr2, f2, n2);
        }
        this.h.A.c("prepareterrain");
        this.a(0, f2);
        this.h.P().a(bmh.g);
        avc.a();
        this.h.A.c("terrain_setup");
        bfr2.a(\u26035, f2, \u26034, this.ae++, this.h.h.v());
        if (n2 == 0 || n2 == 2) {
            this.h.A.c("updatechunks");
            this.h.g.a(l2);
        }
        this.h.A.c("terrain");
        bfl.n(5888);
        bfl.E();
        bfl.c();
        bfr2.a(adf.a, (double)f2, n2, \u26035);
        bfl.d();
        bfr2.a(adf.b, (double)f2, n2, \u26035);
        this.h.P().b(bmh.g).b(false, false);
        bfr2.a(adf.c, (double)f2, n2, \u26035);
        this.h.P().b(bmh.g).a();
        bfl.j(7424);
        bfl.a(516, 0.1f);
        if (!this.W) {
            bfl.n(5888);
            bfl.F();
            bfl.E();
            avc.b();
            this.h.A.c("entities");
            bfr2.a(\u26035, \u26034, f2);
            avc.a();
            this.h();
            bfl.n(5888);
            bfl.F();
            bfl.E();
            if (this.h.s != null && \u26035.a(arm.h) && \u26033) {
                wn2 = (wn)\u26035;
                bfl.c();
                this.h.A.c("outline");
                bfr2.a(wn2, this.h.s, 0, f2);
                bfl.d();
            }
        }
        bfl.n(5888);
        bfl.F();
        if (\u26033 && this.h.s != null && !\u26035.a(arm.h)) {
            wn2 = (wn)\u26035;
            bfl.c();
            this.h.A.c("outline");
            bfr2.a(wn2, this.h.s, 0, f2);
            bfl.d();
        }
        this.h.A.c("destroyProgress");
        bfl.l();
        bfl.a(770, 1, 1, 0);
        this.h.P().b(bmh.g).b(false, false);
        bfr2.a(bfx.a(), bfx.a().c(), \u26035, f2);
        this.h.P().b(bmh.g).a();
        bfl.k();
        if (!this.W) {
            this.i();
            this.h.A.c("litParticles");
            \u26032.b(\u26035, f2);
            avc.a();
            this.a(0, f2);
            this.h.A.c("particles");
            \u26032.a(\u26035, f2);
            this.h();
        }
        bfl.a(false);
        bfl.o();
        this.h.A.c("weather");
        this.c(f2);
        bfl.a(true);
        bfr2.a(\u26035, f2);
        bfl.k();
        bfl.o();
        bfl.a(770, 771, 1, 0);
        bfl.a(516, 0.1f);
        this.a(0, f2);
        bfl.l();
        bfl.a(false);
        this.h.P().a(bmh.g);
        bfl.j(7425);
        this.h.A.c("translucent");
        bfr2.a(adf.d, (double)f2, n2, \u26035);
        bfl.j(7424);
        bfl.a(true);
        bfl.o();
        bfl.k();
        bfl.n();
        if (\u26035.t + (double)\u26035.aS() >= 128.0) {
            this.h.A.c("aboveClouds");
            this.a(bfr2, f2, n2);
        }
        this.h.A.c("hand");
        if (this.C) {
            bfl.m(256);
            this.b(f2, n2);
            this.h(f2);
        }
    }

    private void a(bfr bfr2, float f2, int n2) {
        if (this.h.t.e() != 0) {
            this.h.A.c("clouds");
            bfl.n(5889);
            bfl.D();
            Project.gluPerspective(this.a(f2, true), (float)this.h.d / (float)this.h.e, 0.05f, this.k * 4.0f);
            bfl.n(5888);
            bfl.E();
            this.a(0, f2);
            bfr2.b(f2, n2);
            bfl.n();
            bfl.F();
            bfl.n(5889);
            bfl.D();
            Project.gluPerspective(this.a(f2, true), (float)this.h.d / (float)this.h.e, 0.05f, this.k * ns.a);
            bfl.n(5888);
        }
    }

    private void o() {
        float f2 = this.h.f.j(1.0f);
        if (!this.h.t.i) {
            f2 /= 2.0f;
        }
        if (f2 == 0.0f) {
            return;
        }
        this.j.setSeed((long)this.m * 312987231L);
        pk \u26032 = this.h.ac();
        bdb \u26033 = this.h.f;
        cj \u26034 = new cj(\u26032);
        int \u26035 = 10;
        double \u26036 = 0.0;
        double \u26037 = 0.0;
        double \u26038 = 0.0;
        int \u26039 = 0;
        int \u260310 = (int)(100.0f * f2 * f2);
        if (this.h.t.aL == 1) {
            \u260310 >>= 1;
        } else if (this.h.t.aL == 2) {
            \u260310 = 0;
        }
        for (int i2 = 0; i2 < \u260310; ++i2) {
            cj cj2 = \u26033.q(\u26034.a(this.j.nextInt(\u26035) - this.j.nextInt(\u26035), 0, this.j.nextInt(\u26035) - this.j.nextInt(\u26035)));
            ady \u260311 = \u26033.b(cj2);
            \u2603 = cj2.b();
            afh \u260312 = \u26033.p(\u2603).c();
            if (cj2.o() > \u26034.o() + \u26035 || cj2.o() < \u26034.o() - \u26035 || !\u260311.e() || !(\u260311.a(cj2) >= 0.15f)) continue;
            double \u260313 = this.j.nextDouble();
            double \u260314 = this.j.nextDouble();
            if (\u260312.t() == arm.i) {
                this.h.f.a(cy.l, (double)cj2.n() + \u260313, (double)((float)cj2.o() + 0.1f) - \u260312.D(), (double)cj2.p() + \u260314, 0.0, 0.0, 0.0, new int[0]);
                continue;
            }
            if (\u260312.t() == arm.a) continue;
            \u260312.a((adq)\u26033, \u2603);
            if (this.j.nextInt(++\u26039) == 0) {
                \u26036 = (double)\u2603.n() + \u260313;
                \u26037 = (double)((float)\u2603.o() + 0.1f) + \u260312.E() - 1.0;
                \u26038 = (double)\u2603.p() + \u260314;
            }
            this.h.f.a(cy.N, (double)\u2603.n() + \u260313, (double)((float)\u2603.o() + 0.1f) + \u260312.E(), (double)\u2603.p() + \u260314, 0.0, 0.0, 0.0, new int[0]);
        }
        if (\u26039 > 0 && this.j.nextInt(3) < this.M++) {
            this.M = 0;
            if (\u26037 > (double)(\u26034.o() + 1) && \u26033.q(\u26034).o() > ns.d((float)\u26034.o())) {
                this.h.f.a(\u26036, \u26037, \u26038, "ambient.weather.rain", 0.1f, 0.5f, false);
            } else {
                this.h.f.a(\u26036, \u26037, \u26038, "ambient.weather.rain", 0.2f, 1.0f, false);
            }
        }
    }

    protected void c(float f2) {
        \u2603 = this.h.f.j(f2);
        if (\u2603 <= 0.0f) {
            return;
        }
        this.i();
        pk pk2 = this.h.ac();
        bdb \u26032 = this.h.f;
        int \u26033 = ns.c(pk2.s);
        int \u26034 = ns.c(pk2.t);
        int \u26035 = ns.c(pk2.u);
        bfx \u26036 = bfx.a();
        bfd \u26037 = \u26036.c();
        bfl.p();
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.a(516, 0.1f);
        double \u26038 = pk2.P + (pk2.s - pk2.P) * (double)f2;
        double \u26039 = pk2.Q + (pk2.t - pk2.Q) * (double)f2;
        double \u260310 = pk2.R + (pk2.u - pk2.R) * (double)f2;
        int \u260311 = ns.c(\u26039);
        int \u260312 = 5;
        if (this.h.t.i) {
            \u260312 = 10;
        }
        int \u260313 = -1;
        float \u260314 = (float)this.m + f2;
        \u26037.c(-\u26038, -\u26039, -\u260310);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        cj.a \u260315 = new cj.a();
        for (int i2 = \u26035 - \u260312; i2 <= \u26035 + \u260312; ++i2) {
            for (n2 = \u26033 - \u260312; n2 <= \u26033 + \u260312; ++n2) {
                int n2;
                \u2603 = (i2 - \u26035 + 16) * 32 + (n2 - \u26033 + 16);
                double d2 = (double)this.N[\u2603] * 0.5;
                \u2603 = (double)this.O[\u2603] * 0.5;
                \u260315.c(n2, 0, i2);
                ady \u260316 = \u26032.b(\u260315);
                if (!\u260316.e() && !\u260316.d()) continue;
                int \u260317 = \u26032.q(\u260315).o();
                int \u260318 = \u26034 - \u260312;
                int \u260319 = \u26034 + \u260312;
                if (\u260318 < \u260317) {
                    \u260318 = \u260317;
                }
                if (\u260319 < \u260317) {
                    \u260319 = \u260317;
                }
                if ((n3 = \u260317) < \u260311) {
                    int n3 = \u260311;
                }
                if (\u260318 == \u260319) continue;
                this.j.setSeed(n2 * n2 * 3121 + n2 * 45238971 ^ i2 * i2 * 418711 + i2 * 13761);
                \u260315.c(n2, \u260318, i2);
                float \u260320 = \u260316.a(\u260315);
                if (\u26032.v().a(\u260320, \u260317) >= 0.15f) {
                    if (\u260313 != 0) {
                        if (\u260313 >= 0) {
                            \u26036.b();
                        }
                        \u260313 = 0;
                        this.h.P().a(f);
                        \u26037.a(7, bms.d);
                    }
                    double d3 = ((double)(this.m + n2 * n2 * 3121 + n2 * 45238971 + i2 * i2 * 418711 + i2 * 13761 & 0x1F) + (double)f2) / 32.0 * (3.0 + this.j.nextDouble());
                    \u2603 = (double)((float)n2 + 0.5f) - pk2.s;
                    \u2603 = (double)((float)i2 + 0.5f) - pk2.u;
                    float \u260321 = ns.a(\u2603 * \u2603 + \u2603 * \u2603) / (float)\u260312;
                    float \u260322 = ((1.0f - \u260321 * \u260321) * 0.5f + 0.5f) * \u2603;
                    \u260315.c(n2, n3, i2);
                    int \u260323 = \u26032.b((cj)\u260315, 0);
                    int \u260324 = \u260323 >> 16 & 0xFFFF;
                    int \u260325 = \u260323 & 0xFFFF;
                    \u26037.b((double)n2 - d2 + 0.5, (double)\u260318, (double)i2 - \u2603 + 0.5).a(0.0, (double)\u260318 * 0.25 + d3).a(1.0f, 1.0f, 1.0f, \u260322).a(\u260324, \u260325).d();
                    \u26037.b((double)n2 + d2 + 0.5, (double)\u260318, (double)i2 + \u2603 + 0.5).a(1.0, (double)\u260318 * 0.25 + d3).a(1.0f, 1.0f, 1.0f, \u260322).a(\u260324, \u260325).d();
                    \u26037.b((double)n2 + d2 + 0.5, (double)\u260319, (double)i2 + \u2603 + 0.5).a(1.0, (double)\u260319 * 0.25 + d3).a(1.0f, 1.0f, 1.0f, \u260322).a(\u260324, \u260325).d();
                    \u26037.b((double)n2 - d2 + 0.5, (double)\u260319, (double)i2 - \u2603 + 0.5).a(0.0, (double)\u260319 * 0.25 + d3).a(1.0f, 1.0f, 1.0f, \u260322).a(\u260324, \u260325).d();
                    continue;
                }
                if (\u260313 != 1) {
                    if (\u260313 >= 0) {
                        \u26036.b();
                    }
                    \u260313 = 1;
                    this.h.P().a(g);
                    \u26037.a(7, bms.d);
                }
                d3 = ((float)(this.m & 0x1FF) + f2) / 512.0f;
                \u2603 = this.j.nextDouble() + (double)\u260314 * 0.01 * (double)((float)this.j.nextGaussian());
                \u2603 = this.j.nextDouble() + (double)(\u260314 * (float)this.j.nextGaussian()) * 0.001;
                \u2603 = (double)((float)n2 + 0.5f) - pk2.s;
                \u2603 = (double)((float)i2 + 0.5f) - pk2.u;
                float f3 = ns.a(\u2603 * \u2603 + \u2603 * \u2603) / (float)\u260312;
                \u2603 = ((1.0f - f3 * f3) * 0.3f + 0.5f) * \u2603;
                \u260315.c(n2, n3, i2);
                int \u260326 = (\u26032.b((cj)\u260315, 0) * 3 + 0xF000F0) / 4;
                int \u260327 = \u260326 >> 16 & 0xFFFF;
                int \u260328 = \u260326 & 0xFFFF;
                \u26037.b((double)n2 - d2 + 0.5, (double)\u260318, (double)i2 - \u2603 + 0.5).a(0.0 + \u2603, (double)\u260318 * 0.25 + d3 + \u2603).a(1.0f, 1.0f, 1.0f, \u2603).a(\u260327, \u260328).d();
                \u26037.b((double)n2 + d2 + 0.5, (double)\u260318, (double)i2 + \u2603 + 0.5).a(1.0 + \u2603, (double)\u260318 * 0.25 + d3 + \u2603).a(1.0f, 1.0f, 1.0f, \u2603).a(\u260327, \u260328).d();
                \u26037.b((double)n2 + d2 + 0.5, (double)\u260319, (double)i2 + \u2603 + 0.5).a(1.0 + \u2603, (double)\u260319 * 0.25 + d3 + \u2603).a(1.0f, 1.0f, 1.0f, \u2603).a(\u260327, \u260328).d();
                \u26037.b((double)n2 - d2 + 0.5, (double)\u260319, (double)i2 - \u2603 + 0.5).a(0.0 + \u2603, (double)\u260319 * 0.25 + d3 + \u2603).a(1.0f, 1.0f, 1.0f, \u2603).a(\u260327, \u260328).d();
            }
        }
        if (\u260313 >= 0) {
            \u26036.b();
        }
        \u26037.c(0.0, 0.0, 0.0);
        bfl.o();
        bfl.k();
        bfl.a(516, 0.1f);
        this.h();
    }

    public void j() {
        avr avr2 = new avr(this.h);
        bfl.m(256);
        bfl.n(5889);
        bfl.D();
        bfl.a(0.0, avr2.c(), avr2.d(), 0.0, 1000.0, 3000.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -2000.0f);
    }

    private void i(float f22) {
        float f22;
        float \u260310;
        Object \u26039;
        bdb bdb2 = this.h.f;
        pk \u26032 = this.h.ac();
        float \u26033 = 0.25f + 0.75f * (float)this.h.t.c / 32.0f;
        \u26033 = 1.0f - (float)Math.pow(\u26033, 0.25);
        aui \u26034 = bdb2.a(this.h.ac(), f22);
        float \u26035 = (float)\u26034.a;
        float \u26036 = (float)\u26034.b;
        float \u26037 = (float)\u26034.c;
        aui \u26038 = bdb2.f(f22);
        this.Q = (float)\u26038.a;
        this.R = (float)\u26038.b;
        this.S = (float)\u26038.c;
        if (this.h.t.c >= 4) {
            double d2 = -1.0;
            \u26039 = ns.a(bdb2.d(f22)) > 0.0f ? new aui(d2, 0.0, 0.0) : new aui(1.0, 0.0, 0.0);
            \u260310 = (float)\u26032.d(f22).b((aui)\u26039);
            if (\u260310 < 0.0f) {
                \u260310 = 0.0f;
            }
            if (\u260310 > 0.0f && (\u2603 = bdb2.t.a(bdb2.c(f22), f22)) != null) {
                this.Q = this.Q * (1.0f - (\u260310 *= \u2603[3])) + \u2603[0] * \u260310;
                this.R = this.R * (1.0f - \u260310) + \u2603[1] * \u260310;
                this.S = this.S * (1.0f - \u260310) + \u2603[2] * \u260310;
            }
        }
        this.Q += (\u26035 - this.Q) * \u26033;
        this.R += (\u26036 - this.R) * \u26033;
        this.S += (\u26037 - this.S) * \u26033;
        float f3 = bdb2.j(f22);
        if (f3 > 0.0f) {
            \u2603 = 1.0f - f3 * 0.5f;
            \u2603 = 1.0f - f3 * 0.4f;
            this.Q *= \u2603;
            this.R *= \u2603;
            this.S *= \u2603;
        }
        if ((\u2603 = bdb2.h(f22)) > 0.0f) {
            \u2603 = 1.0f - \u2603 * 0.5f;
            this.Q *= \u2603;
            this.R *= \u2603;
            this.S *= \u2603;
        }
        \u26039 = auz.a(this.h.f, \u26032, f22);
        if (this.B) {
            aui aui2 = bdb2.e(f22);
            this.Q = (float)aui2.a;
            this.R = (float)aui2.b;
            this.S = (float)aui2.c;
        } else if (((afh)\u26039).t() == arm.h) {
            \u260310 = (float)ack.a(\u26032) * 0.2f;
            if (\u26032 instanceof pr && ((pr)\u26032).a(pe.o)) {
                \u260310 = \u260310 * 0.3f + 0.6f;
            }
            this.Q = 0.02f + \u260310;
            this.R = 0.02f + \u260310;
            this.S = 0.2f + \u260310;
        } else if (((afh)\u26039).t() == arm.i) {
            this.Q = 0.6f;
            this.R = 0.1f;
            this.S = 0.0f;
        }
        \u2603 = this.T + (this.U - this.T) * f22;
        this.Q *= \u2603;
        this.R *= \u2603;
        this.S *= \u2603;
        double \u260311 = (\u26032.Q + (\u26032.t - \u26032.Q) * (double)f22) * bdb2.t.j();
        if (\u26032 instanceof pr && ((pr)\u26032).a(pe.q)) {
            int n2 = ((pr)\u26032).b(pe.q).b();
            \u260311 = n2 < 20 ? (\u260311 *= (double)(1.0f - (float)n2 / 20.0f)) : 0.0;
        }
        if (\u260311 < 1.0) {
            if (\u260311 < 0.0) {
                \u260311 = 0.0;
            }
            \u260311 *= \u260311;
            this.Q = (float)((double)this.Q * \u260311);
            this.R = (float)((double)this.R * \u260311);
            this.S = (float)((double)this.S * \u260311);
        }
        if (this.z > 0.0f) {
            \u2603 = this.A + (this.z - this.A) * f22;
            this.Q = this.Q * (1.0f - \u2603) + this.Q * 0.7f * \u2603;
            this.R = this.R * (1.0f - \u2603) + this.R * 0.6f * \u2603;
            this.S = this.S * (1.0f - \u2603) + this.S * 0.6f * \u2603;
        }
        if (\u26032 instanceof pr && ((pr)\u26032).a(pe.r)) {
            \u2603 = this.a((pr)\u26032, f22);
            \u2603 = 1.0f / this.Q;
            if (\u2603 > 1.0f / this.R) {
                \u2603 = 1.0f / this.R;
            }
            if (\u2603 > 1.0f / this.S) {
                \u2603 = 1.0f / this.S;
            }
            this.Q = this.Q * (1.0f - \u2603) + this.Q * \u2603 * \u2603;
            this.R = this.R * (1.0f - \u2603) + this.R * \u2603 * \u2603;
            this.S = this.S * (1.0f - \u2603) + this.S * \u2603 * \u2603;
        }
        if (this.h.t.e) {
            float f4 = (this.Q * 30.0f + this.R * 59.0f + this.S * 11.0f) / 100.0f;
            \u2603 = (this.Q * 30.0f + this.R * 70.0f) / 100.0f;
            \u2603 = (this.Q * 30.0f + this.S * 70.0f) / 100.0f;
            this.Q = f4;
            this.R = \u2603;
            this.S = \u2603;
        }
        bfl.a(this.Q, this.R, this.S, 0.0f);
    }

    private void a(int n2, float f2) {
        pk pk2 = this.h.ac();
        boolean \u26032 = false;
        if (pk2 instanceof wn) {
            \u26032 = ((wn)pk2).bA.d;
        }
        GL11.glFog(2918, this.a(this.Q, this.R, this.S, 1.0f));
        GL11.glNormal3f(0.0f, -1.0f, 0.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        afh \u26033 = auz.a(this.h.f, pk2, f2);
        if (pk2 instanceof pr && ((pr)pk2).a(pe.q)) {
            float f3 = 5.0f;
            int \u26034 = ((pr)pk2).b(pe.q).b();
            if (\u26034 < 20) {
                f3 = 5.0f + (this.k - 5.0f) * (1.0f - (float)\u26034 / 20.0f);
            }
            bfl.d(9729);
            if (n2 == -1) {
                bfl.b(0.0f);
                bfl.c(f3 * 0.8f);
            } else {
                bfl.b(f3 * 0.25f);
                bfl.c(f3);
            }
            if (GLContext.getCapabilities().GL_NV_fog_distance) {
                GL11.glFogi(34138, 34139);
            }
        } else if (this.B) {
            bfl.d(2048);
            bfl.a(0.1f);
        } else if (\u26033.t() == arm.h) {
            bfl.d(2048);
            if (pk2 instanceof pr && ((pr)pk2).a(pe.o)) {
                bfl.a(0.01f);
            } else {
                bfl.a(0.1f - (float)ack.a(pk2) * 0.03f);
            }
        } else if (\u26033.t() == arm.i) {
            bfl.d(2048);
            bfl.a(2.0f);
        } else {
            \u2603 = this.k;
            bfl.d(9729);
            if (n2 == -1) {
                bfl.b(0.0f);
                bfl.c(\u2603);
            } else {
                bfl.b(\u2603 * 0.75f);
                bfl.c(\u2603);
            }
            if (GLContext.getCapabilities().GL_NV_fog_distance) {
                GL11.glFogi(34138, 34139);
            }
            if (this.h.f.t.b((int)pk2.s, (int)pk2.u)) {
                bfl.b(\u2603 * 0.05f);
                bfl.c(Math.min(\u2603, 192.0f) * 0.5f);
            }
        }
        bfl.g();
        bfl.m();
        bfl.a(1028, 4608);
    }

    private FloatBuffer a(float f2, float f3, float f4, float f5) {
        this.P.clear();
        this.P.put(f2).put(f3).put(f4).put(f5);
        this.P.flip();
        return this.P;
    }

    public avq k() {
        return this.l;
    }

    static {
        ab = new jy[]{new jy("shaders/post/notch.json"), new jy("shaders/post/fxaa.json"), new jy("shaders/post/art.json"), new jy("shaders/post/bumpy.json"), new jy("shaders/post/blobs2.json"), new jy("shaders/post/pencil.json"), new jy("shaders/post/color_convolve.json"), new jy("shaders/post/deconverge.json"), new jy("shaders/post/flip.json"), new jy("shaders/post/invert.json"), new jy("shaders/post/ntsc.json"), new jy("shaders/post/outline.json"), new jy("shaders/post/phosphor.json"), new jy("shaders/post/scan_pincushion.json"), new jy("shaders/post/sobel.json"), new jy("shaders/post/bits.json"), new jy("shaders/post/desaturate.json"), new jy("shaders/post/green.json"), new jy("shaders/post/blur.json"), new jy("shaders/post/wobble.json"), new jy("shaders/post/blobs.json"), new jy("shaders/post/antialias.json"), new jy("shaders/post/creeper.json"), new jy("shaders/post/spider.json")};
        d = ab.length;
    }
}

