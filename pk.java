/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public abstract class pk
implements m {
    private static final aug a = new aug(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    private static int b;
    private int c = b++;
    public double j = 1.0;
    public boolean k;
    public pk l;
    public pk m;
    public boolean n;
    public adm o;
    public double p;
    public double q;
    public double r;
    public double s;
    public double t;
    public double u;
    public double v;
    public double w;
    public double x;
    public float y;
    public float z;
    public float A;
    public float B;
    private aug f = a;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    protected boolean H;
    private boolean g;
    public boolean I;
    public float J = 0.6f;
    public float K = 1.8f;
    public float L;
    public float M;
    public float N;
    public float O;
    private int h = 1;
    public double P;
    public double Q;
    public double R;
    public float S;
    public boolean T;
    public float U;
    protected Random V = new Random();
    public int W;
    public int X = 1;
    private int i;
    protected boolean Y;
    public int Z;
    protected boolean aa = true;
    protected boolean ab;
    protected pz ac;
    private double ar;
    private double as;
    public boolean ad;
    public int ae;
    public int af;
    public int ag;
    public int bW;
    public int bX;
    public int bY;
    public boolean ah;
    public boolean ai;
    public int aj;
    protected boolean ak;
    protected int al;
    public int am;
    protected cj an;
    protected aui ao;
    protected cq ap;
    private boolean at;
    protected UUID aq = ns.a(this.V);
    private final n au = new n();

    public int F() {
        return this.c;
    }

    public void d(int n2) {
        this.c = n2;
    }

    public void G() {
        this.J();
    }

    public pk(adm adm2) {
        this.o = adm2;
        this.b(0.0, 0.0, 0.0);
        if (adm2 != null) {
            this.am = adm2.t.q();
        }
        this.ac = new pz(this);
        this.ac.a(0, Byte.valueOf((byte)0));
        this.ac.a(1, Short.valueOf((short)300));
        this.ac.a(3, Byte.valueOf((byte)0));
        this.ac.a(2, "");
        this.ac.a(4, Byte.valueOf((byte)0));
        this.h();
    }

    protected abstract void h();

    public pz H() {
        return this.ac;
    }

    public boolean equals(Object object) {
        if (object instanceof pk) {
            return ((pk)object).c == this.c;
        }
        return false;
    }

    public int hashCode() {
        return this.c;
    }

    protected void I() {
        if (this.o == null) {
            return;
        }
        while (this.t > 0.0 && this.t < 256.0) {
            this.b(this.s, this.t, this.u);
            if (this.o.a(this, this.aR()).isEmpty()) break;
            this.t += 1.0;
        }
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
        this.z = 0.0f;
    }

    public void J() {
        this.I = true;
    }

    protected void a(float f2, float f3) {
        if (f2 != this.J || f3 != this.K) {
            \u2603 = this.J;
            this.J = f2;
            this.K = f3;
            this.a(new aug(this.aR().a, this.aR().b, this.aR().c, this.aR().a + (double)this.J, this.aR().b + (double)this.K, this.aR().c + (double)this.J));
            if (this.J > \u2603 && !this.aa && !this.o.D) {
                this.d(\u2603 - this.J, 0.0, \u2603 - this.J);
            }
        }
    }

    protected void b(float f2, float f3) {
        this.y = f2 % 360.0f;
        this.z = f3 % 360.0f;
    }

    public void b(double d2, double d3, double d4) {
        this.s = d2;
        this.t = d3;
        this.u = d4;
        float f2 = this.J / 2.0f;
        \u2603 = this.K;
        this.a(new aug(d2 - (double)f2, d3, d4 - (double)f2, d2 + (double)f2, d3 + (double)\u2603, d4 + (double)f2));
    }

    public void c(float f2, float f3) {
        \u2603 = this.z;
        \u2603 = this.y;
        this.y = (float)((double)this.y + (double)f2 * 0.15);
        this.z = (float)((double)this.z - (double)f3 * 0.15);
        this.z = ns.a(this.z, -90.0f, 90.0f);
        this.B += this.z - \u2603;
        this.A += this.y - \u2603;
    }

    public void t_() {
        this.K();
    }

    public void K() {
        this.o.B.a("entityBaseTick");
        if (this.m != null && this.m.I) {
            this.m = null;
        }
        this.L = this.M;
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.B = this.z;
        this.A = this.y;
        if (!this.o.D && this.o instanceof le) {
            this.o.B.a("portal");
            MinecraftServer minecraftServer = ((le)this.o).r();
            int \u26032 = this.L();
            if (this.ak) {
                if (minecraftServer.C()) {
                    if (this.m == null && this.al++ >= \u26032) {
                        this.al = \u26032;
                        this.aj = this.aq();
                        int n2 = this.o.t.q() == -1 ? 0 : -1;
                        this.c(n2);
                    }
                    this.ak = false;
                }
            } else {
                if (this.al > 0) {
                    this.al -= 4;
                }
                if (this.al < 0) {
                    this.al = 0;
                }
            }
            if (this.aj > 0) {
                --this.aj;
            }
            this.o.B.b();
        }
        this.Y();
        this.W();
        if (this.o.D) {
            this.i = 0;
        } else if (this.i > 0) {
            if (this.ab) {
                this.i -= 4;
                if (this.i < 0) {
                    this.i = 0;
                }
            } else {
                if (this.i % 20 == 0) {
                    this.a(ow.c, 1.0f);
                }
                --this.i;
            }
        }
        if (this.ab()) {
            this.M();
            this.O *= 0.5f;
        }
        if (this.t < -64.0) {
            this.O();
        }
        if (!this.o.D) {
            this.b(0, this.i > 0);
        }
        this.aa = false;
        this.o.B.b();
    }

    public int L() {
        return 0;
    }

    protected void M() {
        if (this.ab) {
            return;
        }
        this.a(ow.d, 4.0f);
        this.e(15);
    }

    public void e(int n2) {
        \u2603 = n2 * 20;
        if (this.i < (\u2603 = acr.a(this, \u2603))) {
            this.i = \u2603;
        }
    }

    public void N() {
        this.i = 0;
    }

    protected void O() {
        this.J();
    }

    public boolean c(double d2, double d3, double d4) {
        aug aug2 = this.aR().c(d2, d3, d4);
        return this.b(aug2);
    }

    private boolean b(aug aug2) {
        return this.o.a(this, aug2).isEmpty() && !this.o.d(aug2);
    }

    public void d(double \u260342, double \u260352, double \u260362) {
        double d2;
        if (this.T) {
            this.a(this.aR().c(\u260342, \u260352, \u260362));
            this.m();
            return;
        }
        this.o.B.a("move");
        double d3 = this.s;
        \u2603 = this.t;
        \u2603 = this.u;
        if (this.H) {
            this.H = false;
            \u260342 *= 0.25;
            \u260352 *= (double)0.05f;
            \u260362 *= 0.25;
            this.v = 0.0;
            this.w = 0.0;
            this.x = 0.0;
        }
        double d22 = \u260342;
        \u2603 = \u260352;
        d2 = \u260362;
        boolean bl2 = \u2603 = this.C && this.av() && this instanceof wn;
        if (\u2603) {
            \u2603 = 0.05;
            while (\u260342 != 0.0 && this.o.a(this, this.aR().c(\u260342, -1.0, 0.0)).isEmpty()) {
                \u260342 = \u260342 < \u2603 && \u260342 >= -\u2603 ? 0.0 : (\u260342 > 0.0 ? (\u260342 -= \u2603) : (\u260342 += \u2603));
                d22 = \u260342;
            }
            while (\u260362 != 0.0 && this.o.a(this, this.aR().c(0.0, -1.0, \u260362)).isEmpty()) {
                \u260362 = \u260362 < \u2603 && \u260362 >= -\u2603 ? 0.0 : (\u260362 > 0.0 ? (\u260362 -= \u2603) : (\u260362 += \u2603));
                d2 = \u260362;
            }
            while (\u260342 != 0.0 && \u260362 != 0.0 && this.o.a(this, this.aR().c(\u260342, -1.0, \u260362)).isEmpty()) {
                \u260342 = \u260342 < \u2603 && \u260342 >= -\u2603 ? 0.0 : (\u260342 > 0.0 ? (\u260342 -= \u2603) : (\u260342 += \u2603));
                d22 = \u260342;
                \u260362 = \u260362 < \u2603 && \u260362 >= -\u2603 ? 0.0 : (\u260362 > 0.0 ? (\u260362 -= \u2603) : (\u260362 += \u2603));
                d2 = \u260362;
            }
        }
        List<aug> list = this.o.a(this, this.aR().a(\u260342, \u260352, \u260362));
        aug \u26032 = this.aR();
        for (aug aug2 : list) {
            \u260352 = aug2.b(this.aR(), \u260352);
        }
        this.a(this.aR().c(0.0, \u260352, 0.0));
        boolean \u26033 = this.C || \u2603 != \u260352 && \u2603 < 0.0;
        for (aug aug2 : list) {
            \u260342 = aug2.a(this.aR(), \u260342);
        }
        this.a(this.aR().c(\u260342, 0.0, 0.0));
        for (aug aug3 : list) {
            \u260362 = aug3.c(this.aR(), \u260362);
        }
        this.a(this.aR().c(0.0, 0.0, \u260362));
        if (this.S > 0.0f && \u26033 && (d22 != \u260342 || d2 != \u260362)) {
            double d4 = \u260342;
            \u2603 = \u260352;
            \u2603 = \u260362;
            aug object = this.aR();
            this.a(\u26032);
            \u260342 = d22;
            \u260352 = this.S;
            \u260362 = d2;
            List<aug> object2 = this.o.a(this, this.aR().a(\u260342, \u260352, \u260362));
            aug \u26037 = this.aR();
            aug \u26038 = \u26037.a(\u260342, 0.0, \u260362);
            double \u26039 = \u260352;
            for (aug aug3 : object2) {
                \u26039 = aug3.b(\u26038, \u26039);
            }
            \u26037 = \u26037.c(0.0, \u26039, 0.0);
            double \u260312 = \u260342;
            for (aug aug4 : object2) {
                \u260312 = aug4.a(\u26037, \u260312);
            }
            \u26037 = \u26037.c(\u260312, 0.0, 0.0);
            double \u260314 = \u260362;
            for (aug aug5 : object2) {
                \u260314 = aug5.c(\u26037, \u260314);
            }
            \u26037 = \u26037.c(0.0, 0.0, \u260314);
            aug aug4 = this.aR();
            double \u260316 = \u260352;
            for (aug aug6 : object2) {
                \u260316 = aug6.b(aug4, \u260316);
            }
            aug4 = aug4.c(0.0, \u260316, 0.0);
            double \u260318 = \u260342;
            for (aug aug7 : object2) {
                \u260318 = aug7.a(aug4, \u260318);
            }
            aug4 = aug4.c(\u260318, 0.0, 0.0);
            double \u260320 = \u260362;
            for (aug aug8 : object2) {
                \u260320 = aug8.c(aug4, \u260320);
            }
            aug4 = aug4.c(0.0, 0.0, \u260320);
            double d42 = \u260312 * \u260312 + \u260314 * \u260314;
            double \u26034 = \u260318 * \u260318 + \u260320 * \u260320;
            if (d42 > \u26034) {
                \u260342 = \u260312;
                \u260362 = \u260314;
                \u260352 = -\u26039;
                this.a(\u26037);
            } else {
                \u260342 = \u260318;
                \u260362 = \u260320;
                \u260352 = -\u260316;
                this.a(aug4);
            }
            for (aug aug9 : object2) {
                \u260352 = aug9.b(this.aR(), \u260352);
            }
            this.a(this.aR().c(0.0, \u260352, 0.0));
            if (d4 * d4 + \u2603 * \u2603 >= \u260342 * \u260342 + \u260362 * \u260362) {
                \u260342 = d4;
                \u260352 = \u2603;
                \u260362 = \u2603;
                this.a(object);
            }
        }
        this.o.B.b();
        this.o.B.a("rest");
        this.m();
        this.D = d22 != \u260342 || d2 != \u260362;
        this.E = \u2603 != \u260352;
        this.C = this.E && \u2603 < 0.0;
        this.F = this.D || this.E;
        int n2 = ns.c(this.s);
        int \u260323 = ns.c(this.t - (double)0.2f);
        int \u260324 = ns.c(this.u);
        cj \u260325 = new cj(n2, \u260323, \u260324);
        afh \u260326 = this.o.p(\u260325).c();
        if (\u260326.t() == arm.a && ((\u2603 = this.o.p(\u260325.b()).c()) instanceof agt || \u2603 instanceof akl || \u2603 instanceof agu)) {
            \u260326 = \u2603;
            \u260325 = \u260325.b();
        }
        this.a(\u260352, this.C, \u260326, \u260325);
        if (d22 != \u260342) {
            this.v = 0.0;
        }
        if (d2 != \u260362) {
            this.x = 0.0;
        }
        if (\u2603 != \u260352) {
            \u260326.a(this.o, this);
        }
        if (this.s_() && !\u2603 && this.m == null) {
            double \u26035 = this.s - d3;
            double \u26036 = this.t - \u2603;
            double \u260327 = this.u - \u2603;
            if (\u260326 != afi.au) {
                \u26036 = 0.0;
            }
            if (\u260326 != null && this.C) {
                \u260326.a(this.o, \u260325, this);
            }
            this.M = (float)((double)this.M + (double)ns.a(\u26035 * \u26035 + \u260327 * \u260327) * 0.6);
            this.N = (float)((double)this.N + (double)ns.a(\u26035 * \u26035 + \u26036 * \u26036 + \u260327 * \u260327) * 0.6);
            if (this.N > (float)this.h && \u260326.t() != arm.a) {
                this.h = (int)this.N + 1;
                if (this.V()) {
                    float f2 = ns.a(this.v * this.v * (double)0.2f + this.w * this.w + this.x * this.x * (double)0.2f) * 0.35f;
                    if (f2 > 1.0f) {
                        f2 = 1.0f;
                    }
                    this.a(this.P(), f2, 1.0f + (this.V.nextFloat() - this.V.nextFloat()) * 0.4f);
                }
                this.a(\u260325, \u260326);
            }
        }
        try {
            this.Q();
        }
        catch (Throwable throwable) {
            b \u260328 = b.a(throwable, "Checking entity block collision");
            c \u260329 = \u260328.a("Entity being checked for collision");
            this.a(\u260329);
            throw new e(\u260328);
        }
        boolean bl3 = this.U();
        if (this.o.e(this.aR().d(0.001, 0.001, 0.001))) {
            this.f(1);
            if (!bl3) {
                ++this.i;
                if (this.i == 0) {
                    this.e(8);
                }
            }
        } else if (this.i <= 0) {
            this.i = -this.X;
        }
        if (bl3 && this.i > 0) {
            this.a("random.fizz", 0.7f, 1.6f + (this.V.nextFloat() - this.V.nextFloat()) * 0.4f);
            this.i = -this.X;
        }
        this.o.B.b();
    }

    private void m() {
        this.s = (this.aR().a + this.aR().d) / 2.0;
        this.t = this.aR().b;
        this.u = (this.aR().c + this.aR().f) / 2.0;
    }

    protected String P() {
        return "game.neutral.swim";
    }

    protected void Q() {
        cj cj2 = new cj(this.aR().a + 0.001, this.aR().b + 0.001, this.aR().c + 0.001);
        \u2603 = new cj(this.aR().d - 0.001, this.aR().e - 0.001, this.aR().f - 0.001);
        if (this.o.a(cj2, \u2603)) {
            for (int i2 = cj2.n(); i2 <= \u2603.n(); ++i2) {
                for (\u2603 = cj2.o(); \u2603 <= \u2603.o(); ++\u2603) {
                    for (\u2603 = cj2.p(); \u2603 <= \u2603.p(); ++\u2603) {
                        cj cj3 = new cj(i2, \u2603, \u2603);
                        alz \u26032 = this.o.p(cj3);
                        try {
                            \u26032.c().a(this.o, cj3, \u26032, this);
                            continue;
                        }
                        catch (Throwable \u26033) {
                            b b2 = b.a(\u26033, "Colliding entity with block");
                            c \u26034 = b2.a("Block being collided with");
                            c.a(\u26034, cj3, \u26032);
                            throw new e(b2);
                        }
                    }
                }
            }
        }
    }

    protected void a(cj cj2, afh afh2) {
        afh.b b2 = afh2.H;
        if (this.o.p(cj2.a()).c() == afi.aH) {
            b2 = afi.aH.H;
            this.a(b2.c(), b2.d() * 0.15f, b2.e());
        } else if (!afh2.t().d()) {
            this.a(b2.c(), b2.d() * 0.15f, b2.e());
        }
    }

    public void a(String string, float f2, float f3) {
        if (!this.R()) {
            this.o.a(this, string, f2, f3);
        }
    }

    public boolean R() {
        return this.ac.a(4) == 1;
    }

    public void b(boolean bl2) {
        this.ac.b(4, bl2 ? (byte)1 : 0);
    }

    protected boolean s_() {
        return true;
    }

    protected void a(double d2, boolean bl2, afh afh2, cj cj2) {
        if (bl2) {
            if (this.O > 0.0f) {
                if (afh2 != null) {
                    afh2.a(this.o, cj2, this, this.O);
                } else {
                    this.e(this.O, 1.0f);
                }
                this.O = 0.0f;
            }
        } else if (d2 < 0.0) {
            this.O = (float)((double)this.O - d2);
        }
    }

    public aug S() {
        return null;
    }

    protected void f(int n2) {
        if (!this.ab) {
            this.a(ow.a, (float)n2);
        }
    }

    public final boolean T() {
        return this.ab;
    }

    public void e(float f2, float f3) {
        if (this.l != null) {
            this.l.e(f2, f3);
        }
    }

    public boolean U() {
        return this.Y || this.o.C(new cj(this.s, this.t, this.u)) || this.o.C(new cj(this.s, this.t + (double)this.K, this.u));
    }

    public boolean V() {
        return this.Y;
    }

    public boolean W() {
        if (this.o.a(this.aR().b(0.0, -0.4f, 0.0).d(0.001, 0.001, 0.001), arm.h, this)) {
            if (!this.Y && !this.aa) {
                this.X();
            }
            this.O = 0.0f;
            this.Y = true;
            this.i = 0;
        } else {
            this.Y = false;
        }
        return this.Y;
    }

    protected void X() {
        float f2 = ns.a(this.v * this.v * (double)0.2f + this.w * this.w + this.x * this.x * (double)0.2f) * 0.2f;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.a(this.aa(), f2, 1.0f + (this.V.nextFloat() - this.V.nextFloat()) * 0.4f);
        \u2603 = ns.c(this.aR().b);
        int \u26032 = 0;
        while ((float)\u26032 < 1.0f + this.J * 20.0f) {
            \u2603 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J;
            \u2603 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J;
            this.o.a(cy.e, this.s + (double)\u2603, (double)(\u2603 + 1.0f), this.u + (double)\u2603, this.v, this.w - (double)(this.V.nextFloat() * 0.2f), this.x, new int[0]);
            ++\u26032;
        }
        \u26032 = 0;
        while ((float)\u26032 < 1.0f + this.J * 20.0f) {
            \u2603 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J;
            \u2603 = (this.V.nextFloat() * 2.0f - 1.0f) * this.J;
            this.o.a(cy.f, this.s + (double)\u2603, (double)(\u2603 + 1.0f), this.u + (double)\u2603, this.v, this.w, this.x, new int[0]);
            ++\u26032;
        }
    }

    public void Y() {
        if (this.aw() && !this.V()) {
            this.Z();
        }
    }

    protected void Z() {
        int n2 = ns.c(this.s);
        cj \u26032 = new cj(n2, \u2603 = ns.c(this.t - (double)0.2f), \u2603 = ns.c(this.u));
        alz \u26033 = this.o.p(\u26032);
        afh \u26034 = \u26033.c();
        if (\u26034.b() != -1) {
            this.o.a(cy.L, this.s + ((double)this.V.nextFloat() - 0.5) * (double)this.J, this.aR().b + 0.1, this.u + ((double)this.V.nextFloat() - 0.5) * (double)this.J, -this.v * 4.0, 1.5, -this.x * 4.0, afh.f(\u26033));
        }
    }

    protected String aa() {
        return "game.neutral.swim.splash";
    }

    public boolean a(arm arm2) {
        double d2 = this.t + (double)this.aS();
        cj \u26032 = new cj(this.s, d2, this.u);
        alz \u26033 = this.o.p(\u26032);
        afh \u26034 = \u26033.c();
        if (\u26034.t() == arm2) {
            float f2 = ahv.b(\u26033.c().c(\u26033)) - 0.11111111f;
            \u2603 = (float)(\u26032.o() + 1) - f2;
            boolean bl2 = \u2603 = d2 < (double)\u2603;
            if (!\u2603 && this instanceof wn) {
                return false;
            }
            return \u2603;
        }
        return false;
    }

    public boolean ab() {
        return this.o.a(this.aR().b(-0.1f, -0.4f, -0.1f), arm.i);
    }

    public void a(float f2, float f3, float f4) {
        \u2603 = f2 * f2 + f3 * f3;
        if (\u2603 < 1.0E-4f) {
            return;
        }
        if ((\u2603 = ns.c(\u2603)) < 1.0f) {
            \u2603 = 1.0f;
        }
        \u2603 = f4 / \u2603;
        \u2603 = ns.a(this.y * (float)Math.PI / 180.0f);
        \u2603 = ns.b(this.y * (float)Math.PI / 180.0f);
        this.v += (double)((f2 *= \u2603) * \u2603 - (f3 *= \u2603) * \u2603);
        this.x += (double)(f3 * \u2603 + f2 * \u2603);
    }

    public int b(float f2) {
        cj cj2 = new cj(this.s, this.t + (double)this.aS(), this.u);
        if (this.o.e(cj2)) {
            return this.o.b(cj2, 0);
        }
        return 0;
    }

    public float c(float f2) {
        cj cj2 = new cj(this.s, this.t + (double)this.aS(), this.u);
        if (this.o.e(cj2)) {
            return this.o.o(cj2);
        }
        return 0.0f;
    }

    public void a(adm adm2) {
        this.o = adm2;
    }

    public void a(double d2, double d3, double d4, float f2, float f3) {
        this.p = this.s = d2;
        this.q = this.t = d3;
        this.r = this.u = d4;
        this.A = this.y = f2;
        this.B = this.z = f3;
        double d5 = this.A - f2;
        if (d5 < -180.0) {
            this.A += 360.0f;
        }
        if (d5 >= 180.0) {
            this.A -= 360.0f;
        }
        this.b(this.s, this.t, this.u);
        this.b(f2, f3);
    }

    public void a(cj cj2, float f2, float f3) {
        this.b((double)cj2.n() + 0.5, cj2.o(), (double)cj2.p() + 0.5, f2, f3);
    }

    public void b(double d2, double d3, double d4, float f2, float f3) {
        this.p = this.s = d2;
        this.P = this.s;
        this.q = this.t = d3;
        this.Q = this.t;
        this.r = this.u = d4;
        this.R = this.u;
        this.y = f2;
        this.z = f3;
        this.b(this.s, this.t, this.u);
    }

    public float g(pk pk2) {
        float f2 = (float)(this.s - pk2.s);
        \u2603 = (float)(this.t - pk2.t);
        \u2603 = (float)(this.u - pk2.u);
        return ns.c(f2 * f2 + \u2603 * \u2603 + \u2603 * \u2603);
    }

    public double e(double d2, double d3, double d4) {
        \u2603 = this.s - d2;
        \u2603 = this.t - d3;
        \u2603 = this.u - d4;
        return \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
    }

    public double b(cj cj2) {
        return cj2.c(this.s, this.t, this.u);
    }

    public double c(cj cj2) {
        return cj2.d(this.s, this.t, this.u);
    }

    public double f(double d2, double d3, double d4) {
        \u2603 = this.s - d2;
        \u2603 = this.t - d3;
        \u2603 = this.u - d4;
        return ns.a(\u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603);
    }

    public double h(pk pk2) {
        double d2 = this.s - pk2.s;
        \u2603 = this.t - pk2.t;
        \u2603 = this.u - pk2.u;
        return d2 * d2 + \u2603 * \u2603 + \u2603 * \u2603;
    }

    public void d(wn wn2) {
    }

    public void i(pk pk2) {
        if (pk2.l == this || pk2.m == this) {
            return;
        }
        if (pk2.T || this.T) {
            return;
        }
        double d2 = pk2.s - this.s;
        \u2603 = pk2.u - this.u;
        \u2603 = ns.a(d2, \u2603);
        if (\u2603 >= (double)0.01f) {
            \u2603 = ns.a(\u2603);
            d2 /= \u2603;
            \u2603 /= \u2603;
            \u2603 = 1.0 / \u2603;
            if (\u2603 > 1.0) {
                \u2603 = 1.0;
            }
            d2 *= \u2603;
            \u2603 *= \u2603;
            d2 *= (double)0.05f;
            \u2603 *= (double)0.05f;
            d2 *= (double)(1.0f - this.U);
            \u2603 *= (double)(1.0f - this.U);
            if (this.l == null) {
                this.g(-d2, 0.0, -\u2603);
            }
            if (pk2.l == null) {
                pk2.g(d2, 0.0, \u2603);
            }
        }
    }

    public void g(double d2, double d3, double d4) {
        this.v += d2;
        this.w += d3;
        this.x += d4;
        this.ai = true;
    }

    protected void ac() {
        this.G = true;
    }

    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        this.ac();
        return false;
    }

    public aui d(float f2) {
        if (f2 == 1.0f) {
            return this.f(this.z, this.y);
        }
        \u2603 = this.B + (this.z - this.B) * f2;
        \u2603 = this.A + (this.y - this.A) * f2;
        return this.f(\u2603, \u2603);
    }

    protected final aui f(float f2, float f3) {
        \u2603 = ns.b(-f3 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = ns.a(-f3 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = -ns.b(-f2 * ((float)Math.PI / 180));
        \u2603 = ns.a(-f2 * ((float)Math.PI / 180));
        return new aui(\u2603 * \u2603, \u2603, \u2603 * \u2603);
    }

    public aui e(float f2) {
        if (f2 == 1.0f) {
            return new aui(this.s, this.t + (double)this.aS(), this.u);
        }
        double d2 = this.p + (this.s - this.p) * (double)f2;
        \u2603 = this.q + (this.t - this.q) * (double)f2 + (double)this.aS();
        \u2603 = this.r + (this.u - this.r) * (double)f2;
        return new aui(d2, \u2603, \u2603);
    }

    public auh a(double d2, float f2) {
        aui aui2 = this.e(f2);
        \u2603 = this.d(f2);
        \u2603 = aui2.b(\u2603.a * d2, \u2603.b * d2, \u2603.c * d2);
        return this.o.a(aui2, \u2603, false, false, true);
    }

    public boolean ad() {
        return false;
    }

    public boolean ae() {
        return false;
    }

    public void b(pk pk2, int n2) {
    }

    public boolean h(double d2, double d3, double d4) {
        \u2603 = this.s - d2;
        \u2603 = this.t - d3;
        \u2603 = this.u - d4;
        \u2603 = \u2603 * \u2603 + \u2603 * \u2603 + \u2603 * \u2603;
        return this.a(\u2603);
    }

    public boolean a(double d2) {
        \u2603 = this.aR().a();
        if (Double.isNaN(\u2603)) {
            \u2603 = 1.0;
        }
        return d2 < (\u2603 *= 64.0 * this.j) * \u2603;
    }

    public boolean c(dn dn2) {
        String string = this.ag();
        if (this.I || string == null) {
            return false;
        }
        dn2.a("id", string);
        this.e(dn2);
        return true;
    }

    public boolean d(dn dn2) {
        String string = this.ag();
        if (this.I || string == null || this.l != null) {
            return false;
        }
        dn2.a("id", string);
        this.e(dn2);
        return true;
    }

    public void e(dn dn2) {
        try {
            dn2.a("Pos", this.a(new double[]{this.s, this.t, this.u}));
            dn2.a("Motion", this.a(new double[]{this.v, this.w, this.x}));
            dn2.a("Rotation", this.a(new float[]{this.y, this.z}));
            dn2.a("FallDistance", this.O);
            dn2.a("Fire", (short)this.i);
            dn2.a("Air", (short)this.az());
            dn2.a("OnGround", this.C);
            dn2.a("Dimension", this.am);
            dn2.a("Invulnerable", this.at);
            dn2.a("PortalCooldown", this.aj);
            dn2.a("UUIDMost", this.aK().getMostSignificantBits());
            dn2.a("UUIDLeast", this.aK().getLeastSignificantBits());
            if (this.aM() != null && this.aM().length() > 0) {
                dn2.a("CustomName", this.aM());
                dn2.a("CustomNameVisible", this.aN());
            }
            this.au.b(dn2);
            if (this.R()) {
                dn2.a("Silent", this.R());
            }
            this.b(dn2);
            if (this.m != null && this.m.c(\u2603 = new dn())) {
                dn2.a("Riding", \u2603);
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Saving entity NBT");
            c \u26032 = b2.a("Entity being saved");
            this.a(\u26032);
            throw new e(b2);
        }
    }

    public void f(dn dn2) {
        try {
            du du2 = dn2.c("Pos", 6);
            \u2603 = dn2.c("Motion", 6);
            \u2603 = dn2.c("Rotation", 5);
            this.v = \u2603.d(0);
            this.w = \u2603.d(1);
            this.x = \u2603.d(2);
            if (Math.abs(this.v) > 10.0) {
                this.v = 0.0;
            }
            if (Math.abs(this.w) > 10.0) {
                this.w = 0.0;
            }
            if (Math.abs(this.x) > 10.0) {
                this.x = 0.0;
            }
            this.P = this.s = du2.d(0);
            this.p = this.s;
            this.Q = this.t = du2.d(1);
            this.q = this.t;
            this.R = this.u = du2.d(2);
            this.r = this.u;
            this.A = this.y = \u2603.e(0);
            this.B = this.z = \u2603.e(1);
            this.f(this.y);
            this.g(this.y);
            this.O = dn2.h("FallDistance");
            this.i = dn2.e("Fire");
            this.h(dn2.e("Air"));
            this.C = dn2.n("OnGround");
            this.am = dn2.f("Dimension");
            this.at = dn2.n("Invulnerable");
            this.aj = dn2.f("PortalCooldown");
            if (dn2.b("UUIDMost", 4) && dn2.b("UUIDLeast", 4)) {
                this.aq = new UUID(dn2.g("UUIDMost"), dn2.g("UUIDLeast"));
            } else if (dn2.b("UUID", 8)) {
                this.aq = UUID.fromString(dn2.j("UUID"));
            }
            this.b(this.s, this.t, this.u);
            this.b(this.y, this.z);
            if (dn2.b("CustomName", 8) && dn2.j("CustomName").length() > 0) {
                this.a(dn2.j("CustomName"));
            }
            this.g(dn2.n("CustomNameVisible"));
            this.au.a(dn2);
            this.b(dn2.n("Silent"));
            this.a(dn2);
            if (this.af()) {
                this.b(this.s, this.t, this.u);
            }
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Loading entity NBT");
            c \u26032 = b2.a("Entity being loaded");
            this.a(\u26032);
            throw new e(b2);
        }
    }

    protected boolean af() {
        return true;
    }

    protected final String ag() {
        return pm.b(this);
    }

    protected abstract void a(dn var1);

    protected abstract void b(dn var1);

    public void ah() {
    }

    protected du a(double ... dArray) {
        du du2 = new du();
        for (double d2 : dArray) {
            du2.a(new dp(d2));
        }
        return du2;
    }

    protected du a(float ... fArray) {
        du du2 = new du();
        for (float f2 : fArray) {
            du2.a(new dr(f2));
        }
        return du2;
    }

    public uz a(zw zw2, int n2) {
        return this.a(zw2, n2, 0.0f);
    }

    public uz a(zw zw2, int n2, float f2) {
        return this.a(new zx(zw2, n2, 0), f2);
    }

    public uz a(zx zx2, float f2) {
        if (zx2.b == 0 || zx2.b() == null) {
            return null;
        }
        uz uz2 = new uz(this.o, this.s, this.t + (double)f2, this.u, zx2);
        uz2.p();
        this.o.d(uz2);
        return uz2;
    }

    public boolean ai() {
        return !this.I;
    }

    public boolean aj() {
        if (this.T) {
            return false;
        }
        cj.a a2 = new cj.a(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        for (int i2 = 0; i2 < 8; ++i2) {
            \u2603 = ns.c(this.t + (double)(((float)((i2 >> 0) % 2) - 0.5f) * 0.1f) + (double)this.aS());
            \u2603 = ns.c(this.s + (double)(((float)((i2 >> 1) % 2) - 0.5f) * this.J * 0.8f));
            \u2603 = ns.c(this.u + (double)(((float)((i2 >> 2) % 2) - 0.5f) * this.J * 0.8f));
            if (a2.n() == \u2603 && a2.o() == \u2603 && a2.p() == \u2603) continue;
            a2.c(\u2603, \u2603, \u2603);
            if (!this.o.p(a2).c().w()) continue;
            return true;
        }
        return false;
    }

    public boolean e(wn wn2) {
        return false;
    }

    public aug j(pk pk2) {
        return null;
    }

    public void ak() {
        if (this.m.I) {
            this.m = null;
            return;
        }
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.t_();
        if (this.m == null) {
            return;
        }
        this.m.al();
        this.as += (double)(this.m.y - this.m.A);
        this.ar += (double)(this.m.z - this.m.B);
        while (this.as >= 180.0) {
            this.as -= 360.0;
        }
        while (this.as < -180.0) {
            this.as += 360.0;
        }
        while (this.ar >= 180.0) {
            this.ar -= 360.0;
        }
        while (this.ar < -180.0) {
            this.ar += 360.0;
        }
        double d2 = this.as * 0.5;
        \u2603 = this.ar * 0.5;
        float \u26032 = 10.0f;
        if (d2 > (double)\u26032) {
            d2 = \u26032;
        }
        if (d2 < (double)(-\u26032)) {
            d2 = -\u26032;
        }
        if (\u2603 > (double)\u26032) {
            \u2603 = \u26032;
        }
        if (\u2603 < (double)(-\u26032)) {
            \u2603 = -\u26032;
        }
        this.as -= d2;
        this.ar -= \u2603;
    }

    public void al() {
        if (this.l == null) {
            return;
        }
        this.l.b(this.s, this.t + this.an() + this.l.am(), this.u);
    }

    public double am() {
        return 0.0;
    }

    public double an() {
        return (double)this.K * 0.75;
    }

    public void a(pk pk2) {
        this.ar = 0.0;
        this.as = 0.0;
        if (pk2 == null) {
            if (this.m != null) {
                this.b(this.m.s, this.m.aR().b + (double)this.m.K, this.m.u, this.y, this.z);
                this.m.l = null;
            }
            this.m = null;
            return;
        }
        if (this.m != null) {
            this.m.l = null;
        }
        if (pk2 != null) {
            \u2603 = pk2.m;
            while (\u2603 != null) {
                if (\u2603 == this) {
                    return;
                }
                \u2603 = \u2603.m;
            }
        }
        this.m = pk2;
        pk2.l = this;
    }

    public void a(double d22, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.b(d22, d3, d4);
        this.b(f2, f3);
        List<aug> list = this.o.a(this, this.aR().d(0.03125, 0.0, 0.03125));
        if (!list.isEmpty()) {
            double d22;
            double d5 = 0.0;
            for (aug aug2 : list) {
                if (!(aug2.e > d5)) continue;
                d5 = aug2.e;
            }
            this.b(d22, d3 += d5 - this.aR().b, d4);
        }
    }

    public float ao() {
        return 0.1f;
    }

    public aui ap() {
        return null;
    }

    public void d(cj cj2) {
        if (this.aj > 0) {
            this.aj = this.aq();
            return;
        }
        if (!this.o.D && !cj2.equals(this.an)) {
            this.an = cj2;
            amd.b b2 = afi.aY.f(this.o, cj2);
            double \u26032 = b2.b().k() == cq.a.a ? (double)b2.a().p() : (double)b2.a().n();
            double \u26033 = b2.b().k() == cq.a.a ? this.u : this.s;
            \u26033 = Math.abs(ns.c(\u26033 - (double)(b2.b().e().c() == cq.b.b ? (char)'\u0001' : '\u0000'), \u26032, \u26032 - (double)b2.d()));
            double \u26034 = ns.c(this.t - 1.0, (double)b2.a().o(), (double)(b2.a().o() - b2.e()));
            this.ao = new aui(\u26033, \u26034, 0.0);
            this.ap = b2.b();
        }
        this.ak = true;
    }

    public int aq() {
        return 300;
    }

    public void i(double d2, double d3, double d4) {
        this.v = d2;
        this.w = d3;
        this.x = d4;
    }

    public void a(byte by) {
    }

    public void ar() {
    }

    public zx[] as() {
        return null;
    }

    public void c(int n2, zx zx2) {
    }

    public boolean at() {
        boolean bl2 = this.o != null && this.o.D;
        return !this.ab && (this.i > 0 || bl2 && this.g(0));
    }

    public boolean au() {
        return this.m != null;
    }

    public boolean av() {
        return this.g(1);
    }

    public void c(boolean bl2) {
        this.b(1, bl2);
    }

    public boolean aw() {
        return this.g(3);
    }

    public void d(boolean bl2) {
        this.b(3, bl2);
    }

    public boolean ax() {
        return this.g(5);
    }

    public boolean f(wn wn2) {
        if (wn2.v()) {
            return false;
        }
        return this.ax();
    }

    public void e(boolean bl2) {
        this.b(5, bl2);
    }

    public boolean ay() {
        return this.g(4);
    }

    public void f(boolean bl2) {
        this.b(4, bl2);
    }

    protected boolean g(int n2) {
        return (this.ac.a(0) & 1 << n2) != 0;
    }

    protected void b(int n2, boolean bl2) {
        byte by = this.ac.a(0);
        if (bl2) {
            this.ac.b(0, (byte)(by | 1 << n2));
        } else {
            this.ac.b(0, (byte)(by & ~(1 << n2)));
        }
    }

    public int az() {
        return this.ac.b(1);
    }

    public void h(int n2) {
        this.ac.b(1, (short)n2);
    }

    public void a(uv uv2) {
        this.a(ow.b, 5.0f);
        ++this.i;
        if (this.i == 0) {
            this.e(8);
        }
    }

    public void a(pr pr2) {
    }

    protected boolean j(double d2, double d3, double d4) {
        cj cj2 = new cj(d2, d3, d4);
        double \u26032 = d2 - (double)cj2.n();
        double \u26033 = d3 - (double)cj2.o();
        double \u26034 = d4 - (double)cj2.p();
        List<aug> \u26035 = this.o.a(this.aR());
        if (!\u26035.isEmpty() || this.o.u(cj2)) {
            int n2 = 3;
            double \u26036 = 9999.0;
            if (!this.o.u(cj2.e()) && \u26032 < \u26036) {
                \u26036 = \u26032;
                n2 = 0;
            }
            if (!this.o.u(cj2.f()) && 1.0 - \u26032 < \u26036) {
                \u26036 = 1.0 - \u26032;
                n2 = 1;
            }
            if (!this.o.u(cj2.a()) && 1.0 - \u26033 < \u26036) {
                \u26036 = 1.0 - \u26033;
                n2 = 3;
            }
            if (!this.o.u(cj2.c()) && \u26034 < \u26036) {
                \u26036 = \u26034;
                n2 = 4;
            }
            if (!this.o.u(cj2.d()) && 1.0 - \u26034 < \u26036) {
                \u26036 = 1.0 - \u26034;
                n2 = 5;
            }
            float \u26037 = this.V.nextFloat() * 0.2f + 0.1f;
            if (n2 == 0) {
                this.v = -\u26037;
            }
            if (n2 == 1) {
                this.v = \u26037;
            }
            if (n2 == 3) {
                this.w = \u26037;
            }
            if (n2 == 4) {
                this.x = -\u26037;
            }
            if (n2 == 5) {
                this.x = \u26037;
            }
            return true;
        }
        return false;
    }

    public void aA() {
        this.H = true;
        this.O = 0.0f;
    }

    @Override
    public String e_() {
        if (this.l_()) {
            return this.aM();
        }
        String string = pm.b(this);
        if (string == null) {
            string = "generic";
        }
        return di.a("entity." + string + ".name");
    }

    public pk[] aB() {
        return null;
    }

    public boolean k(pk pk2) {
        return this == pk2;
    }

    public float aC() {
        return 0.0f;
    }

    public void f(float f2) {
    }

    public void g(float f2) {
    }

    public boolean aD() {
        return true;
    }

    public boolean l(pk pk2) {
        return false;
    }

    public String toString() {
        return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", this.getClass().getSimpleName(), this.e_(), this.c, this.o == null ? "~NULL~" : this.o.P().k(), this.s, this.t, this.u);
    }

    public boolean b(ow ow2) {
        return this.at && ow2 != ow.j && !ow2.u();
    }

    public void m(pk pk2) {
        this.b(pk2.s, pk2.t, pk2.u, pk2.y, pk2.z);
    }

    public void n(pk pk2) {
        dn dn2 = new dn();
        pk2.e(dn2);
        this.f(dn2);
        this.aj = pk2.aj;
        this.an = pk2.an;
        this.ao = pk2.ao;
        this.ap = pk2.ap;
    }

    public void c(int n2) {
        if (this.o.D || this.I) {
            return;
        }
        this.o.B.a("changeDimension");
        MinecraftServer minecraftServer = MinecraftServer.N();
        int \u26032 = this.am;
        le \u26033 = minecraftServer.a(\u26032);
        le \u26034 = minecraftServer.a(n2);
        this.am = n2;
        if (\u26032 == 1 && n2 == 1) {
            \u26034 = minecraftServer.a(0);
            this.am = 0;
        }
        this.o.e(this);
        this.I = false;
        this.o.B.a("reposition");
        minecraftServer.ap().a(this, \u26032, \u26033, \u26034);
        this.o.B.c("reloading");
        pk \u26035 = pm.a(pm.b(this), (adm)\u26034);
        if (\u26035 != null) {
            \u26035.n(this);
            if (\u26032 == 1 && n2 == 1) {
                cj cj2 = this.o.r(\u26034.M());
                \u26035.a(cj2, \u26035.y, \u26035.z);
            }
            \u26034.d(\u26035);
        }
        this.I = true;
        this.o.B.b();
        \u26033.j();
        \u26034.j();
        this.o.B.b();
    }

    public float a(adi adi2, adm adm2, cj cj2, alz alz2) {
        return alz2.c().a(this);
    }

    public boolean a(adi adi2, adm adm2, cj cj2, alz alz2, float f2) {
        return true;
    }

    public int aE() {
        return 3;
    }

    public aui aG() {
        return this.ao;
    }

    public cq aH() {
        return this.ap;
    }

    public boolean aI() {
        return false;
    }

    public void a(c c2) {
        c2.a("Entity Type", new Callable<String>(){

            public String a() throws Exception {
                return pm.b(pk.this) + " (" + pk.this.getClass().getCanonicalName() + ")";
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Entity ID", this.c);
        c2.a("Entity Name", new Callable<String>(){

            public String a() throws Exception {
                return pk.this.e_();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Entity's Exact location", String.format("%.2f, %.2f, %.2f", this.s, this.t, this.u));
        c2.a("Entity's Block location", c.a(ns.c(this.s), ns.c(this.t), ns.c(this.u)));
        c2.a("Entity's Momentum", String.format("%.2f, %.2f, %.2f", this.v, this.w, this.x));
        c2.a("Entity's Rider", new Callable<String>(){

            public String a() throws Exception {
                return pk.this.l.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Entity's Vehicle", new Callable<String>(){

            public String a() throws Exception {
                return pk.this.m.toString();
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
    }

    public boolean aJ() {
        return this.at();
    }

    public UUID aK() {
        return this.aq;
    }

    public boolean aL() {
        return true;
    }

    @Override
    public eu f_() {
        fa fa2 = new fa(this.e_());
        fa2.b().a(this.aQ());
        fa2.b().a(this.aK().toString());
        return fa2;
    }

    public void a(String string) {
        this.ac.b(2, string);
    }

    public String aM() {
        return this.ac.e(2);
    }

    public boolean l_() {
        return this.ac.e(2).length() > 0;
    }

    public void g(boolean bl2) {
        this.ac.b(3, bl2 ? (byte)1 : 0);
    }

    public boolean aN() {
        return this.ac.a(3) == 1;
    }

    public void a(double d2, double d3, double d4) {
        this.b(d2, d3, d4, this.y, this.z);
    }

    public boolean aO() {
        return this.aN();
    }

    public void i(int n2) {
    }

    public cq aP() {
        return cq.b(ns.c((double)(this.y * 4.0f / 360.0f) + 0.5) & 3);
    }

    protected ew aQ() {
        dn dn2 = new dn();
        String \u26032 = pm.b(this);
        dn2.a("id", this.aK().toString());
        if (\u26032 != null) {
            dn2.a("type", \u26032);
        }
        dn2.a("name", this.e_());
        return new ew(ew.a.d, new fa(dn2.toString()));
    }

    public boolean a(lf lf2) {
        return true;
    }

    public aug aR() {
        return this.f;
    }

    public void a(aug aug2) {
        this.f = aug2;
    }

    public float aS() {
        return this.K * 0.85f;
    }

    public boolean aT() {
        return this.g;
    }

    public void h(boolean bl2) {
        this.g = bl2;
    }

    public boolean d(int n2, zx zx2) {
        return false;
    }

    @Override
    public void a(eu eu2) {
    }

    @Override
    public boolean a(int n2, String string) {
        return true;
    }

    @Override
    public cj c() {
        return new cj(this.s, this.t + 0.5, this.u);
    }

    @Override
    public aui d() {
        return new aui(this.s, this.t, this.u);
    }

    @Override
    public adm e() {
        return this.o;
    }

    @Override
    public pk f() {
        return this;
    }

    @Override
    public boolean u_() {
        return false;
    }

    @Override
    public void a(n.a a2, int n2) {
        this.au.a(this, a2, n2);
    }

    public n aU() {
        return this.au;
    }

    public void o(pk pk2) {
        this.au.a(pk2.aU());
    }

    public dn aV() {
        return null;
    }

    public void g(dn dn2) {
    }

    public boolean a(wn wn2, aui aui2) {
        return false;
    }

    public boolean aW() {
        return false;
    }

    protected void a(pr pr2, pk pk2) {
        if (pk2 instanceof pr) {
            ack.a((pr)pk2, (pk)pr2);
        }
        ack.b(pr2, pk2);
    }
}

