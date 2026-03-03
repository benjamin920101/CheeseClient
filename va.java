/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.server.MinecraftServer;

public abstract class va
extends pk
implements op {
    private boolean a;
    private String b;
    private static final int[][][] c = new int[][][]{new int[][]{{0, 0, -1}, {0, 0, 1}}, new int[][]{{-1, 0, 0}, {1, 0, 0}}, new int[][]{{-1, -1, 0}, {1, 0, 0}}, new int[][]{{-1, 0, 0}, {1, -1, 0}}, new int[][]{{0, 0, -1}, {0, -1, 1}}, new int[][]{{0, -1, -1}, {0, 0, 1}}, new int[][]{{0, 0, 1}, {1, 0, 0}}, new int[][]{{0, 0, 1}, {-1, 0, 0}}, new int[][]{{0, 0, -1}, {-1, 0, 0}}, new int[][]{{0, 0, -1}, {1, 0, 0}}};
    private int d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double ar;
    private double as;
    private double at;

    public va(adm adm2) {
        super(adm2);
        this.k = true;
        this.a(0.98f, 0.7f);
    }

    public static va a(adm adm2, double d2, double d3, double d4, a a2) {
        switch (a2) {
            case b: {
                return new vb(adm2, d2, d3, d4);
            }
            case c: {
                return new ve(adm2, d2, d3, d4);
            }
            case d: {
                return new vi(adm2, d2, d3, d4);
            }
            case e: {
                return new vh(adm2, d2, d3, d4);
            }
            case f: {
                return new vf(adm2, d2, d3, d4);
            }
            case g: {
                return new vc(adm2, d2, d3, d4);
            }
        }
        return new vg(adm2, d2, d3, d4);
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void h() {
        this.ac.a(17, new Integer(0));
        this.ac.a(18, new Integer(1));
        this.ac.a(19, new Float(0.0f));
        this.ac.a(20, new Integer(0));
        this.ac.a(21, new Integer(6));
        this.ac.a(22, Byte.valueOf((byte)0));
    }

    @Override
    public aug j(pk pk2) {
        if (pk2.ae()) {
            return pk2.aR();
        }
        return null;
    }

    @Override
    public aug S() {
        return null;
    }

    @Override
    public boolean ae() {
        return true;
    }

    public va(adm adm2, double d2, double d3, double d4) {
        this(adm2);
        this.b(d2, d3, d4);
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.p = d2;
        this.q = d3;
        this.r = d4;
    }

    @Override
    public double an() {
        return 0.0;
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.o.D || this.I) {
            return true;
        }
        if (this.b(ow2)) {
            return false;
        }
        this.k(-this.r());
        this.j(10);
        this.ac();
        this.a(this.p() + f2 * 10.0f);
        boolean bl2 = \u2603 = ow2.j() instanceof wn && ((wn)ow2.j()).bA.d;
        if (\u2603 || this.p() > 40.0f) {
            if (this.l != null) {
                this.l.a((pk)null);
            }
            if (!\u2603 || this.l_()) {
                this.a(ow2);
            } else {
                this.J();
            }
        }
        return true;
    }

    public void a(ow ow2) {
        this.J();
        if (this.o.Q().b("doEntityDrops")) {
            zx zx2 = new zx(zy.az, 1);
            if (this.b != null) {
                zx2.c(this.b);
            }
            this.a(zx2, 0.0f);
        }
    }

    @Override
    public void ar() {
        this.k(-this.r());
        this.j(10);
        this.a(this.p() + this.p() * 10.0f);
    }

    @Override
    public boolean ad() {
        return !this.I;
    }

    @Override
    public void J() {
        super.J();
    }

    @Override
    public void t_() {
        int \u26032;
        if (this.q() > 0) {
            this.j(this.q() - 1);
        }
        if (this.p() > 0.0f) {
            this.a(this.p() - 1.0f);
        }
        if (this.t < -64.0) {
            this.O();
        }
        if (!this.o.D && this.o instanceof le) {
            this.o.B.a("portal");
            MinecraftServer minecraftServer = ((le)this.o).r();
            \u26032 = this.L();
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
        if (this.o.D) {
            if (this.d > 0) {
                double d2 = this.s + (this.e - this.s) / (double)this.d;
                \u2603 = this.t + (this.f - this.t) / (double)this.d;
                \u2603 = this.u + (this.g - this.u) / (double)this.d;
                \u2603 = ns.g(this.h - (double)this.y);
                this.y = (float)((double)this.y + \u2603 / (double)this.d);
                this.z = (float)((double)this.z + (this.i - (double)this.z) / (double)this.d);
                --this.d;
                this.b(d2, \u2603, \u2603);
                this.b(this.y, this.z);
            } else {
                this.b(this.s, this.t, this.u);
                this.b(this.y, this.z);
            }
            return;
        }
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w -= (double)0.04f;
        int n3 = ns.c(this.s);
        if (afe.e(this.o, new cj(n3, (\u26032 = ns.c(this.t)) - 1, n2 = ns.c(this.u)))) {
            --\u26032;
        }
        if (afe.d(\u2603 = this.o.p(\u2603 = new cj(n3, \u26032, n2)))) {
            this.a(\u2603, \u2603);
            if (\u2603.c() == afi.cs) {
                this.a(n3, \u26032, n2, \u2603.b(ais.N));
            }
        } else {
            this.n();
        }
        this.Q();
        this.z = 0.0f;
        double \u26033 = this.p - this.s;
        double \u26034 = this.r - this.u;
        if (\u26033 * \u26033 + \u26034 * \u26034 > 0.001) {
            this.y = (float)(ns.b(\u26034, \u26033) * 180.0 / Math.PI);
            if (this.a) {
                this.y += 180.0f;
            }
        }
        if ((\u2603 = (double)ns.g(this.y - this.A)) < -170.0 || \u2603 >= 170.0) {
            this.y += 180.0f;
            this.a = !this.a;
        }
        this.b(this.y, this.z);
        for (pk pk2 : this.o.b(this, this.aR().b(0.2f, 0.0, 0.2f))) {
            if (pk2 == this.l || !pk2.ae() || !(pk2 instanceof va)) continue;
            pk2.i(this);
        }
        if (this.l != null && this.l.I) {
            if (this.l.m == this) {
                this.l.m = null;
            }
            this.l = null;
        }
        this.W();
    }

    protected double m() {
        return 0.4;
    }

    public void a(int n2, int n3, int n4, boolean bl2) {
    }

    protected void n() {
        double d2 = this.m();
        this.v = ns.a(this.v, -d2, d2);
        this.x = ns.a(this.x, -d2, d2);
        if (this.C) {
            this.v *= 0.5;
            this.w *= 0.5;
            this.x *= 0.5;
        }
        this.d(this.v, this.w, this.x);
        if (!this.C) {
            this.v *= (double)0.95f;
            this.w *= (double)0.95f;
            this.x *= (double)0.95f;
        }
    }

    protected void a(cj cj2, alz alz2) {
        double d2;
        this.O = 0.0f;
        aui aui2 = this.k(this.s, this.t, this.u);
        this.t = cj2.o();
        boolean \u26032 = false;
        boolean \u26033 = false;
        afe \u26034 = (afe)alz2.c();
        if (\u26034 == afi.D) {
            \u26032 = alz2.b(ais.N);
            \u26033 = !\u26032;
        }
        double \u26035 = 0.0078125;
        afe.b \u26036 = alz2.b(\u26034.n());
        switch (\u26036) {
            case c: {
                this.v -= 0.0078125;
                this.t += 1.0;
                break;
            }
            case d: {
                this.v += 0.0078125;
                this.t += 1.0;
                break;
            }
            case e: {
                this.x += 0.0078125;
                this.t += 1.0;
                break;
            }
            case f: {
                this.x -= 0.0078125;
                this.t += 1.0;
            }
        }
        int[][] \u26037 = c[\u26036.a()];
        double \u26038 = \u26037[1][0] - \u26037[0][0];
        double \u26039 = \u26037[1][2] - \u26037[0][2];
        double \u260310 = Math.sqrt(\u26038 * \u26038 + \u26039 * \u26039);
        double \u260311 = this.v * \u26038 + this.x * \u26039;
        if (\u260311 < 0.0) {
            \u26038 = -\u26038;
            \u26039 = -\u26039;
        }
        if ((d2 = Math.sqrt(this.v * this.v + this.x * this.x)) > 2.0) {
            d2 = 2.0;
        }
        this.v = d2 * \u26038 / \u260310;
        this.x = d2 * \u26039 / \u260310;
        if (this.l instanceof pr && (\u2603 = (double)((pr)this.l).ba) > 0.0) {
            \u2603 = -Math.sin(this.l.y * (float)Math.PI / 180.0f);
            \u2603 = Math.cos(this.l.y * (float)Math.PI / 180.0f);
            \u2603 = this.v * this.v + this.x * this.x;
            if (\u2603 < 0.01) {
                this.v += \u2603 * 0.1;
                this.x += \u2603 * 0.1;
                \u26033 = false;
            }
        }
        if (\u26033) {
            \u2603 = Math.sqrt(this.v * this.v + this.x * this.x);
            if (\u2603 < 0.03) {
                this.v *= 0.0;
                this.w *= 0.0;
                this.x *= 0.0;
            } else {
                this.v *= 0.5;
                this.w *= 0.0;
                this.x *= 0.5;
            }
        }
        \u2603 = 0.0;
        \u2603 = (double)cj2.n() + 0.5 + (double)\u26037[0][0] * 0.5;
        \u2603 = (double)cj2.p() + 0.5 + (double)\u26037[0][2] * 0.5;
        \u2603 = (double)cj2.n() + 0.5 + (double)\u26037[1][0] * 0.5;
        \u2603 = (double)cj2.p() + 0.5 + (double)\u26037[1][2] * 0.5;
        \u26038 = \u2603 - \u2603;
        \u26039 = \u2603 - \u2603;
        if (\u26038 == 0.0) {
            this.s = (double)cj2.n() + 0.5;
            \u2603 = this.u - (double)cj2.p();
        } else if (\u26039 == 0.0) {
            this.u = (double)cj2.p() + 0.5;
            \u2603 = this.s - (double)cj2.n();
        } else {
            \u2603 = this.s - \u2603;
            \u2603 = this.u - \u2603;
            \u2603 = (\u2603 * \u26038 + \u2603 * \u26039) * 2.0;
        }
        this.s = \u2603 + \u26038 * \u2603;
        this.u = \u2603 + \u26039 * \u2603;
        this.b(this.s, this.t, this.u);
        \u2603 = this.v;
        \u2603 = this.x;
        if (this.l != null) {
            \u2603 *= 0.75;
            \u2603 *= 0.75;
        }
        \u2603 = this.m();
        \u2603 = ns.a(\u2603, -\u2603, \u2603);
        \u2603 = ns.a(\u2603, -\u2603, \u2603);
        this.d(\u2603, 0.0, \u2603);
        if (\u26037[0][1] != 0 && ns.c(this.s) - cj2.n() == \u26037[0][0] && ns.c(this.u) - cj2.p() == \u26037[0][2]) {
            this.b(this.s, this.t + (double)\u26037[0][1], this.u);
        } else if (\u26037[1][1] != 0 && ns.c(this.s) - cj2.n() == \u26037[1][0] && ns.c(this.u) - cj2.p() == \u26037[1][2]) {
            this.b(this.s, this.t + (double)\u26037[1][1], this.u);
        }
        this.o();
        aui \u260312 = this.k(this.s, this.t, this.u);
        if (\u260312 != null && aui2 != null) {
            \u2603 = (aui2.b - \u260312.b) * 0.05;
            d2 = Math.sqrt(this.v * this.v + this.x * this.x);
            if (d2 > 0.0) {
                this.v = this.v / d2 * (d2 + \u2603);
                this.x = this.x / d2 * (d2 + \u2603);
            }
            this.b(this.s, \u260312.b, this.u);
        }
        int \u260313 = ns.c(this.s);
        int \u260314 = ns.c(this.u);
        if (\u260313 != cj2.n() || \u260314 != cj2.p()) {
            d2 = Math.sqrt(this.v * this.v + this.x * this.x);
            this.v = d2 * (double)(\u260313 - cj2.n());
            this.x = d2 * (double)(\u260314 - cj2.p());
        }
        if (\u26032) {
            \u2603 = Math.sqrt(this.v * this.v + this.x * this.x);
            if (\u2603 > 0.01) {
                \u2603 = 0.06;
                this.v += this.v / \u2603 * \u2603;
                this.x += this.x / \u2603 * \u2603;
            } else if (\u26036 == afe.b.b) {
                if (this.o.p(cj2.e()).c().v()) {
                    this.v = 0.02;
                } else if (this.o.p(cj2.f()).c().v()) {
                    this.v = -0.02;
                }
            } else if (\u26036 == afe.b.a) {
                if (this.o.p(cj2.c()).c().v()) {
                    this.x = 0.02;
                } else if (this.o.p(cj2.d()).c().v()) {
                    this.x = -0.02;
                }
            }
        }
    }

    protected void o() {
        if (this.l != null) {
            this.v *= (double)0.997f;
            this.w *= 0.0;
            this.x *= (double)0.997f;
        } else {
            this.v *= (double)0.96f;
            this.w *= 0.0;
            this.x *= (double)0.96f;
        }
    }

    @Override
    public void b(double d2, double d3, double d4) {
        this.s = d2;
        this.t = d3;
        this.u = d4;
        float f2 = this.J / 2.0f;
        \u2603 = this.K;
        this.a(new aug(d2 - (double)f2, d3, d4 - (double)f2, d2 + (double)f2, d3 + (double)\u2603, d4 + (double)f2));
    }

    public aui a(double d2, double \u260322, double d3, double d4) {
        int n2 = ns.c(d2);
        if (afe.e(this.o, new cj(n2, (\u2603 = ns.c(\u260322)) - 1, \u2603 = ns.c(d3)))) {
            --\u2603;
        }
        if (afe.d(\u2603 = this.o.p(new cj(n2, \u2603, \u2603)))) {
            afe.b b2 = \u2603.b(((afe)\u2603.c()).n());
            double \u260322 = \u2603;
            if (b2.c()) {
                \u260322 = \u2603 + 1;
            }
            int[][] \u26033 = c[b2.a()];
            double \u26034 = \u26033[1][0] - \u26033[0][0];
            double \u26035 = \u26033[1][2] - \u26033[0][2];
            double \u26036 = Math.sqrt(\u26034 * \u26034 + \u26035 * \u26035);
            if (\u26033[0][1] != 0 && ns.c(d2 += (\u26034 /= \u26036) * d4) - n2 == \u26033[0][0] && ns.c(d3 += (\u26035 /= \u26036) * d4) - \u2603 == \u26033[0][2]) {
                \u260322 += (double)\u26033[0][1];
            } else if (\u26033[1][1] != 0 && ns.c(d2) - n2 == \u26033[1][0] && ns.c(d3) - \u2603 == \u26033[1][2]) {
                \u260322 += (double)\u26033[1][1];
            }
            return this.k(d2, \u260322, d3);
        }
        return null;
    }

    public aui k(double d22, double d3, double \u2603132) {
        int n2 = ns.c(d22);
        if (afe.e(this.o, new cj(n2, (\u2603 = ns.c(d3)) - 1, n3 = ns.c(\u2603132)))) {
            --\u2603;
        }
        if (afe.d(\u2603 = this.o.p(new cj(n2, \u2603, n3)))) {
            double \u2603132;
            double d22;
            afe.b b2 = \u2603.b(((afe)\u2603.c()).n());
            int[][] \u26032 = c[b2.a()];
            double \u26033 = 0.0;
            double \u26034 = (double)n2 + 0.5 + (double)\u26032[0][0] * 0.5;
            double \u26035 = (double)\u2603 + 0.0625 + (double)\u26032[0][1] * 0.5;
            double \u26036 = (double)n3 + 0.5 + (double)\u26032[0][2] * 0.5;
            double \u26037 = (double)n2 + 0.5 + (double)\u26032[1][0] * 0.5;
            double \u26038 = (double)\u2603 + 0.0625 + (double)\u26032[1][1] * 0.5;
            double \u26039 = (double)n3 + 0.5 + (double)\u26032[1][2] * 0.5;
            double \u260310 = \u26037 - \u26034;
            double \u260311 = (\u26038 - \u26035) * 2.0;
            double \u260312 = \u26039 - \u26036;
            if (\u260310 == 0.0) {
                d22 = (double)n2 + 0.5;
                \u26033 = \u2603132 - (double)n3;
            } else if (\u260312 == 0.0) {
                int n3;
                \u2603132 = (double)n3 + 0.5;
                \u26033 = d22 - (double)n2;
            } else {
                double \u260314 = d22 - \u26034;
                double \u260315 = \u2603132 - \u26036;
                \u26033 = (\u260314 * \u260310 + \u260315 * \u260312) * 2.0;
            }
            d22 = \u26034 + \u260310 * \u26033;
            d3 = \u26035 + \u260311 * \u26033;
            \u2603132 = \u26036 + \u260312 * \u26033;
            if (\u260311 < 0.0) {
                d3 += 1.0;
            }
            if (\u260311 > 0.0) {
                d3 += 0.5;
            }
            return new aui(d22, d3, \u2603132);
        }
        return null;
    }

    @Override
    protected void a(dn dn22) {
        if (dn22.n("CustomDisplayTile")) {
            int n2 = dn22.f("DisplayData");
            if (dn22.b("DisplayTile", 8)) {
                afh afh2 = afh.b(dn22.j("DisplayTile"));
                if (afh2 == null) {
                    this.a(afi.a.Q());
                } else {
                    this.a(afh2.a(n2));
                }
            } else {
                dn dn22;
                afh \u26032 = afh.c(dn22.f("DisplayTile"));
                if (\u26032 == null) {
                    this.a(afi.a.Q());
                } else {
                    this.a(\u26032.a(n2));
                }
            }
            this.l(dn22.f("DisplayOffset"));
        }
        if (dn22.b("CustomName", 8) && dn22.j("CustomName").length() > 0) {
            this.b = dn22.j("CustomName");
        }
    }

    @Override
    protected void b(dn dn2) {
        if (this.x()) {
            dn2.a("CustomDisplayTile", true);
            alz alz2 = this.t();
            jy \u26032 = (jy)afh.c.c(alz2.c());
            dn2.a("DisplayTile", \u26032 == null ? "" : \u26032.toString());
            dn2.a("DisplayData", alz2.c().c(alz2));
            dn2.a("DisplayOffset", this.v());
        }
        if (this.b != null && this.b.length() > 0) {
            dn2.a("CustomName", this.b);
        }
    }

    @Override
    public void i(pk pk2) {
        if (this.o.D) {
            return;
        }
        if (pk2.T || this.T) {
            return;
        }
        if (pk2 == this.l) {
            return;
        }
        if (pk2 instanceof pr && !(pk2 instanceof wn) && !(pk2 instanceof ty) && this.s() == va$a.a && this.v * this.v + this.x * this.x > 0.01 && this.l == null && pk2.m == null) {
            pk2.a(this);
        }
        if ((d2 = (\u2603 = pk2.s - this.s) * \u2603 + (\u2603 = pk2.u - this.u) * \u2603) >= (double)1.0E-4f) {
            double d2 = ns.a(d2);
            \u2603 /= d2;
            \u2603 /= d2;
            \u2603 = 1.0 / d2;
            if (\u2603 > 1.0) {
                \u2603 = 1.0;
            }
            \u2603 *= \u2603;
            \u2603 *= \u2603;
            \u2603 *= (double)0.1f;
            \u2603 *= (double)0.1f;
            \u2603 *= (double)(1.0f - this.U);
            \u2603 *= (double)(1.0f - this.U);
            \u2603 *= 0.5;
            \u2603 *= 0.5;
            if (pk2 instanceof va) {
                \u2603 = pk2.s - this.s;
                \u2603 = pk2.u - this.u;
                aui aui2 = new aui(\u2603, 0.0, \u2603).a();
                double \u26032 = Math.abs(aui2.b(\u2603 = new aui(ns.b(this.y * (float)Math.PI / 180.0f), 0.0, ns.a(this.y * (float)Math.PI / 180.0f)).a()));
                if (\u26032 < (double)0.8f) {
                    return;
                }
                double \u26033 = pk2.v + this.v;
                double \u26034 = pk2.x + this.x;
                if (((va)pk2).s() == va$a.c && this.s() != va$a.c) {
                    this.v *= (double)0.2f;
                    this.x *= (double)0.2f;
                    this.g(pk2.v - \u2603, 0.0, pk2.x - \u2603);
                    pk2.v *= (double)0.95f;
                    pk2.x *= (double)0.95f;
                } else if (((va)pk2).s() != va$a.c && this.s() == va$a.c) {
                    pk2.v *= (double)0.2f;
                    pk2.x *= (double)0.2f;
                    pk2.g(this.v + \u2603, 0.0, this.x + \u2603);
                    this.v *= (double)0.95f;
                    this.x *= (double)0.95f;
                } else {
                    this.v *= (double)0.2f;
                    this.x *= (double)0.2f;
                    this.g((\u26033 /= 2.0) - \u2603, 0.0, (\u26034 /= 2.0) - \u2603);
                    pk2.v *= (double)0.2f;
                    pk2.x *= (double)0.2f;
                    pk2.g(\u26033 + \u2603, 0.0, \u26034 + \u2603);
                }
            } else {
                this.g(-\u2603, 0.0, -\u2603);
                pk2.g(\u2603 / 4.0, 0.0, \u2603 / 4.0);
            }
        }
    }

    @Override
    public void a(double d2, double d3, double d4, float f2, float f3, int n2, boolean bl2) {
        this.e = d2;
        this.f = d3;
        this.g = d4;
        this.h = f2;
        this.i = f3;
        this.d = n2 + 2;
        this.v = this.ar;
        this.w = this.as;
        this.x = this.at;
    }

    @Override
    public void i(double d2, double d3, double d4) {
        this.ar = this.v = d2;
        this.as = this.w = d3;
        this.at = this.x = d4;
    }

    public void a(float f2) {
        this.ac.b(19, Float.valueOf(f2));
    }

    public float p() {
        return this.ac.d(19);
    }

    public void j(int n2) {
        this.ac.b(17, n2);
    }

    public int q() {
        return this.ac.c(17);
    }

    public void k(int n2) {
        this.ac.b(18, n2);
    }

    public int r() {
        return this.ac.c(18);
    }

    public abstract a s();

    public alz t() {
        if (!this.x()) {
            return this.u();
        }
        return afh.d(this.H().c(20));
    }

    public alz u() {
        return afi.a.Q();
    }

    public int v() {
        if (!this.x()) {
            return this.w();
        }
        return this.H().c(21);
    }

    public int w() {
        return 6;
    }

    public void a(alz alz2) {
        this.H().b(20, afh.f(alz2));
        this.a(true);
    }

    public void l(int n2) {
        this.H().b(21, n2);
        this.a(true);
    }

    public boolean x() {
        return this.H().a(22) == 1;
    }

    public void a(boolean bl2) {
        this.H().b(22, (byte)(bl2 ? (char)'\u0001' : '\u0000'));
    }

    @Override
    public void a(String string) {
        this.b = string;
    }

    @Override
    public String e_() {
        if (this.b != null) {
            return this.b;
        }
        return super.e_();
    }

    @Override
    public boolean l_() {
        return this.b != null;
    }

    @Override
    public String aM() {
        return this.b;
    }

    @Override
    public eu f_() {
        if (this.l_()) {
            fa fa2 = new fa(this.b);
            fa2.b().a(this.aQ());
            fa2.b().a(this.aK().toString());
            return fa2;
        }
        fb fb2 = new fb(this.e_(), new Object[0]);
        fb2.b().a(this.aQ());
        fb2.b().a(this.aK().toString());
        return fb2;
    }

    public static enum a {
        a(0, "MinecartRideable"),
        b(1, "MinecartChest"),
        c(2, "MinecartFurnace"),
        d(3, "MinecartTNT"),
        e(4, "MinecartSpawner"),
        f(5, "MinecartHopper"),
        g(6, "MinecartCommandBlock");

        private static final Map<Integer, a> h;
        private final int i;
        private final String j;

        private a(int n3, String string2) {
            this.i = n3;
            this.j = string2;
        }

        public int a() {
            return this.i;
        }

        public String b() {
            return this.j;
        }

        public static a a(int n2) {
            a a2 = h.get(n2);
            return a2 == null ? a : a2;
        }

        static {
            h = Maps.newHashMap();
            for (a a2 : va$a.values()) {
                h.put(a2.a(), a2);
            }
        }
    }
}

