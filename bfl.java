/*
 * Decompiled with CFR 0.152.
 */
import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class bfl {
    private static a a;
    private static c b;
    private static c[] c;
    private static h d;
    private static b e;
    private static j f;
    private static k g;
    private static i h;
    private static l i;
    private static f j;
    private static q k;
    private static d l;
    private static n m;
    private static c n;
    private static int o;
    private static r[] p;
    private static int q;
    private static c r;
    private static g s;
    private static e t;

    public static void a() {
        GL11.glPushAttrib(8256);
    }

    public static void b() {
        GL11.glPopAttrib();
    }

    public static void c() {
        bfl.a.a.a();
    }

    public static void d() {
        bfl.a.a.b();
    }

    public static void a(int n2, float f2) {
        if (n2 != bfl.a.b || f2 != bfl.a.c) {
            bfl.a.b = n2;
            bfl.a.c = f2;
            GL11.glAlphaFunc(n2, f2);
        }
    }

    public static void e() {
        b.b();
    }

    public static void f() {
        b.a();
    }

    public static void a(int n2) {
        c[n2].b();
    }

    public static void b(int n2) {
        c[n2].a();
    }

    public static void g() {
        bfl.d.a.b();
    }

    public static void h() {
        bfl.d.a.a();
    }

    public static void a(int n2, int n3) {
        if (n2 != bfl.d.b || n3 != bfl.d.c) {
            bfl.d.b = n2;
            bfl.d.c = n3;
            GL11.glColorMaterial(n2, n3);
        }
    }

    public static void i() {
        bfl.f.a.a();
    }

    public static void j() {
        bfl.f.a.b();
    }

    public static void c(int n2) {
        if (n2 != bfl.f.c) {
            bfl.f.c = n2;
            GL11.glDepthFunc(n2);
        }
    }

    public static void a(boolean bl2) {
        if (bl2 != bfl.f.b) {
            bfl.f.b = bl2;
            GL11.glDepthMask(bl2);
        }
    }

    public static void k() {
        bfl.e.a.a();
    }

    public static void l() {
        bfl.e.a.b();
    }

    public static void b(int n2, int n3) {
        if (n2 != bfl.e.b || n3 != bfl.e.c) {
            bfl.e.b = n2;
            bfl.e.c = n3;
            GL11.glBlendFunc(n2, n3);
        }
    }

    public static void a(int n2, int n3, int n4, int n5) {
        if (n2 != bfl.e.b || n3 != bfl.e.c || n4 != bfl.e.d || n5 != bfl.e.e) {
            bfl.e.b = n2;
            bfl.e.c = n3;
            bfl.e.d = n4;
            bfl.e.e = n5;
            bqs.c(n2, n3, n4, n5);
        }
    }

    public static void m() {
        bfl.g.a.b();
    }

    public static void n() {
        bfl.g.a.a();
    }

    public static void d(int n2) {
        if (n2 != bfl.g.b) {
            bfl.g.b = n2;
            GL11.glFogi(2917, n2);
        }
    }

    public static void a(float f2) {
        if (f2 != bfl.g.c) {
            bfl.g.c = f2;
            GL11.glFogf(2914, f2);
        }
    }

    public static void b(float f2) {
        if (f2 != bfl.g.d) {
            bfl.g.d = f2;
            GL11.glFogf(2915, f2);
        }
    }

    public static void c(float f2) {
        if (f2 != bfl.g.e) {
            bfl.g.e = f2;
            GL11.glFogf(2916, f2);
        }
    }

    public static void o() {
        bfl.h.a.b();
    }

    public static void p() {
        bfl.h.a.a();
    }

    public static void e(int n2) {
        if (n2 != bfl.h.b) {
            bfl.h.b = n2;
            GL11.glCullFace(n2);
        }
    }

    public static void q() {
        bfl.i.a.b();
    }

    public static void r() {
        bfl.i.a.a();
    }

    public static void a(float f2, float f3) {
        if (f2 != bfl.i.c || f3 != bfl.i.d) {
            bfl.i.c = f2;
            bfl.i.d = f3;
            GL11.glPolygonOffset(f2, f3);
        }
    }

    public static void u() {
        bfl.j.a.b();
    }

    public static void v() {
        bfl.j.a.a();
    }

    public static void f(int n2) {
        if (n2 != bfl.j.b) {
            bfl.j.b = n2;
            GL11.glLogicOp(n2);
        }
    }

    public static void a(o o2) {
        bfl.c((o)o2).a.b();
    }

    public static void b(o o2) {
        bfl.c((o)o2).a.a();
    }

    public static void a(o o2, int n2) {
        p p2 = bfl.c(o2);
        if (n2 != p2.c) {
            p2.c = n2;
            GL11.glTexGeni(p2.b, 9472, n2);
        }
    }

    public static void a(o o2, int n2, FloatBuffer floatBuffer) {
        GL11.glTexGen(bfl.c((o)o2).b, n2, floatBuffer);
    }

    private static p c(o o2) {
        switch (o2) {
            case a: {
                return bfl.k.a;
            }
            case b: {
                return bfl.k.b;
            }
            case c: {
                return bfl.k.c;
            }
            case d: {
                return bfl.k.d;
            }
        }
        return bfl.k.a;
    }

    public static void g(int n2) {
        if (o != n2 - bqs.q) {
            o = n2 - bqs.q;
            bqs.k(n2);
        }
    }

    public static void w() {
        bfl.p[bfl.o].a.b();
    }

    public static void x() {
        bfl.p[bfl.o].a.a();
    }

    public static int y() {
        return GL11.glGenTextures();
    }

    public static void h(int n2) {
        GL11.glDeleteTextures(n2);
        for (r r2 : p) {
            if (r2.b != n2) continue;
            r2.b = -1;
        }
    }

    public static void i(int n2) {
        if (n2 != bfl.p[bfl.o].b) {
            bfl.p[bfl.o].b = n2;
            GL11.glBindTexture(3553, n2);
        }
    }

    public static void z() {
        n.b();
    }

    public static void A() {
        n.a();
    }

    public static void j(int n2) {
        if (n2 != q) {
            q = n2;
            GL11.glShadeModel(n2);
        }
    }

    public static void B() {
        r.b();
    }

    public static void C() {
        r.a();
    }

    public static void b(int n2, int n3, int n4, int n5) {
        GL11.glViewport(n2, n3, n4, n5);
    }

    public static void a(boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        if (bl2 != bfl.s.a || bl3 != bfl.s.b || bl4 != bfl.s.c || bl5 != bfl.s.d) {
            bfl.s.a = bl2;
            bfl.s.b = bl3;
            bfl.s.c = bl4;
            bfl.s.d = bl5;
            GL11.glColorMask(bl2, bl3, bl4, bl5);
        }
    }

    public static void a(double d2) {
        if (d2 != bfl.l.a) {
            bfl.l.a = d2;
            GL11.glClearDepth(d2);
        }
    }

    public static void a(float f2, float f3, float f4, float f5) {
        if (f2 != bfl.l.b.a || f3 != bfl.l.b.b || f4 != bfl.l.b.c || f5 != bfl.l.b.d) {
            bfl.l.b.a = f2;
            bfl.l.b.b = f3;
            bfl.l.b.c = f4;
            bfl.l.b.d = f5;
            GL11.glClearColor(f2, f3, f4, f5);
        }
    }

    public static void m(int n2) {
        GL11.glClear(n2);
    }

    public static void n(int n2) {
        GL11.glMatrixMode(n2);
    }

    public static void D() {
        GL11.glLoadIdentity();
    }

    public static void E() {
        GL11.glPushMatrix();
    }

    public static void F() {
        GL11.glPopMatrix();
    }

    public static void a(int n2, FloatBuffer floatBuffer) {
        GL11.glGetFloat(n2, floatBuffer);
    }

    public static void a(double d2, double d3, double d4, double d5, double d6, double d7) {
        GL11.glOrtho(d2, d3, d4, d5, d6, d7);
    }

    public static void b(float f2, float f3, float f4, float f5) {
        GL11.glRotatef(f2, f3, f4, f5);
    }

    public static void a(float f2, float f3, float f4) {
        GL11.glScalef(f2, f3, f4);
    }

    public static void a(double d2, double d3, double d4) {
        GL11.glScaled(d2, d3, d4);
    }

    public static void b(float f2, float f3, float f4) {
        GL11.glTranslatef(f2, f3, f4);
    }

    public static void b(double d2, double d3, double d4) {
        GL11.glTranslated(d2, d3, d4);
    }

    public static void a(FloatBuffer floatBuffer) {
        GL11.glMultMatrix(floatBuffer);
    }

    public static void c(float f2, float f3, float f4, float f5) {
        if (f2 != bfl.t.a || f3 != bfl.t.b || f4 != bfl.t.c || f5 != bfl.t.d) {
            bfl.t.a = f2;
            bfl.t.b = f3;
            bfl.t.c = f4;
            bfl.t.d = f5;
            GL11.glColor4f(f2, f3, f4, f5);
        }
    }

    public static void c(float f2, float f3, float f4) {
        bfl.c(f2, f3, f4, 1.0f);
    }

    public static void G() {
        bfl.t.d = -1.0f;
        bfl.t.c = -1.0f;
        bfl.t.b = -1.0f;
        bfl.t.a = -1.0f;
    }

    public static void o(int n2) {
        GL11.glCallList(n2);
    }

    static {
        int n2;
        a = new a();
        b = new c(2896);
        c = new c[8];
        d = new h();
        e = new b();
        f = new j();
        g = new k();
        h = new i();
        i = new l();
        j = new f();
        k = new q();
        l = new d();
        m = new n();
        n = new c(2977);
        o = 0;
        p = new r[8];
        q = 7425;
        r = new c(32826);
        s = new g();
        t = new e();
        for (n2 = 0; n2 < 8; ++n2) {
            bfl.c[n2] = new c(16384 + n2);
        }
        for (n2 = 0; n2 < 8; ++n2) {
            bfl.p[n2] = new r();
        }
    }

    static class c {
        private final int a;
        private boolean b = false;

        public c(int n2) {
            this.a = n2;
        }

        public void a() {
            this.a(false);
        }

        public void b() {
            this.a(true);
        }

        public void a(boolean bl2) {
            if (bl2 != this.b) {
                this.b = bl2;
                if (bl2) {
                    GL11.glEnable(this.a);
                } else {
                    GL11.glDisable(this.a);
                }
            }
        }
    }

    static class e {
        public float a = 1.0f;
        public float b = 1.0f;
        public float c = 1.0f;
        public float d = 1.0f;

        public e() {
        }

        public e(float f2, float f3, float f4, float f5) {
            this.a = f2;
            this.b = f3;
            this.c = f4;
            this.d = f5;
        }
    }

    static class g {
        public boolean a = true;
        public boolean b = true;
        public boolean c = true;
        public boolean d = true;

        private g() {
        }
    }

    public static enum o {
        a,
        b,
        c,
        d;

    }

    static class p {
        public c a;
        public int b;
        public int c = -1;

        public p(int n2, int n3) {
            this.b = n2;
            this.a = new c(n3);
        }
    }

    static class q {
        public p a = new p(8192, 3168);
        public p b = new p(8193, 3169);
        public p c = new p(8194, 3170);
        public p d = new p(8195, 3171);

        private q() {
        }
    }

    static class n {
        public m a = new m();
        public int b = -1;
        public int c = 7680;
        public int d = 7680;
        public int e = 7680;

        private n() {
        }
    }

    static class m {
        public int a = 519;
        public int b = 0;
        public int c = -1;

        private m() {
        }
    }

    static class d {
        public double a = 1.0;
        public e b = new e(0.0f, 0.0f, 0.0f, 0.0f);
        public int c = 0;

        private d() {
        }
    }

    static class f {
        public c a = new c(3058);
        public int b = 5379;

        private f() {
        }
    }

    static class l {
        public c a = new c(32823);
        public c b = new c(10754);
        public float c = 0.0f;
        public float d = 0.0f;

        private l() {
        }
    }

    static class i {
        public c a = new c(2884);
        public int b = 1029;

        private i() {
        }
    }

    static class k {
        public c a = new c(2912);
        public int b = 2048;
        public float c = 1.0f;
        public float d = 0.0f;
        public float e = 1.0f;

        private k() {
        }
    }

    static class j {
        public c a = new c(2929);
        public boolean b = true;
        public int c = 513;

        private j() {
        }
    }

    static class b {
        public c a = new c(3042);
        public int b = 1;
        public int c = 0;
        public int d = 1;
        public int e = 0;

        private b() {
        }
    }

    static class h {
        public c a = new c(2903);
        public int b = 1032;
        public int c = 5634;

        private h() {
        }
    }

    static class a {
        public c a = new c(3008);
        public int b = 519;
        public float c = -1.0f;

        private a() {
        }
    }

    static class r {
        public c a = new c(3553);
        public int b = 0;

        private r() {
        }
    }
}

