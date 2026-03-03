/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class afh {
    private static final jy a = new jy("air");
    public static final co<jy, afh> c = new co(a);
    public static final ct<alz> d = new ct();
    private yz b;
    public static final b e = new b("stone", 1.0f, 1.0f);
    public static final b f = new b("wood", 1.0f, 1.0f);
    public static final b g = new b("gravel", 1.0f, 1.0f);
    public static final b h = new b("grass", 1.0f, 1.0f);
    public static final b i = new b("stone", 1.0f, 1.0f);
    public static final b j = new b("stone", 1.0f, 1.5f);
    public static final b k = new b("stone", 1.0f, 1.0f){

        @Override
        public String a() {
            return "dig.glass";
        }

        @Override
        public String b() {
            return "step.stone";
        }
    };
    public static final b l = new b("cloth", 1.0f, 1.0f);
    public static final b m = new b("sand", 1.0f, 1.0f);
    public static final b n = new b("snow", 1.0f, 1.0f);
    public static final b o = new b("ladder", 1.0f, 1.0f){

        @Override
        public String a() {
            return "dig.wood";
        }
    };
    public static final b p = new b("anvil", 0.3f, 1.0f){

        @Override
        public String a() {
            return "dig.stone";
        }

        @Override
        public String b() {
            return "random.anvil_land";
        }
    };
    public static final b q = new b("slime", 1.0f, 1.0f){

        @Override
        public String a() {
            return "mob.slime.big";
        }

        @Override
        public String b() {
            return "mob.slime.big";
        }

        @Override
        public String c() {
            return "mob.slime.small";
        }
    };
    protected boolean r;
    protected int s;
    protected boolean t;
    protected int u;
    protected boolean v;
    protected float w;
    protected float x;
    protected boolean y = true;
    protected boolean z;
    protected boolean A;
    protected double B;
    protected double C;
    protected double D;
    protected double E;
    protected double F;
    protected double G;
    public b H = e;
    public float I = 1.0f;
    protected final arm J;
    protected final arn K;
    public float L = 0.6f;
    protected final ama M;
    private alz N;
    private String O;

    public static int a(afh afh2) {
        return c.b(afh2);
    }

    public static int f(alz alz2) {
        afh afh2 = alz2.c();
        return afh.a(afh2) + (afh2.c(alz2) << 12);
    }

    public static afh c(int n2) {
        return c.a(n2);
    }

    public static alz d(int n2) {
        \u2603 = n2 & 0xFFF;
        \u2603 = n2 >> 12 & 0xF;
        return afh.c(\u2603).a(\u2603);
    }

    public static afh a(zw zw2) {
        if (zw2 instanceof yo) {
            return ((yo)zw2).d();
        }
        return null;
    }

    public static afh b(String string) {
        jy jy2 = new jy(string);
        if (c.d(jy2)) {
            return c.a(jy2);
        }
        try {
            return c.a(Integer.parseInt(string));
        }
        catch (NumberFormatException numberFormatException) {
            return null;
        }
    }

    public boolean o() {
        return this.r;
    }

    public int p() {
        return this.s;
    }

    public boolean q() {
        return this.t;
    }

    public int r() {
        return this.u;
    }

    public boolean s() {
        return this.v;
    }

    public arm t() {
        return this.J;
    }

    public arn g(alz alz2) {
        return this.K;
    }

    public alz a(int n2) {
        return this.Q();
    }

    public int c(alz alz2) {
        if (alz2 == null || alz2.a().isEmpty()) {
            return 0;
        }
        throw new IllegalArgumentException("Don't know how to convert " + alz2 + " back into data...");
    }

    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2;
    }

    public afh(arm arm2, arn arn2) {
        this.J = arm2;
        this.K = arn2;
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.r = this.c();
        this.s = this.c() ? 255 : 0;
        this.t = !arm2.b();
        this.M = this.e();
        this.j(this.M.b());
    }

    protected afh(arm arm2) {
        this(arm2, arm2.r());
    }

    protected afh a(b b2) {
        this.H = b2;
        return this;
    }

    protected afh e(int n2) {
        this.s = n2;
        return this;
    }

    protected afh a(float f2) {
        this.u = (int)(15.0f * f2);
        return this;
    }

    protected afh b(float f2) {
        this.x = f2 * 3.0f;
        return this;
    }

    public boolean u() {
        return this.J.c() && this.d();
    }

    public boolean v() {
        return this.J.k() && this.d() && !this.i();
    }

    public boolean w() {
        return this.J.c() && this.d();
    }

    public boolean d() {
        return true;
    }

    public boolean b(adq adq2, cj cj2) {
        return !this.J.c();
    }

    public int b() {
        return 3;
    }

    public boolean a(adm adm2, cj cj2) {
        return false;
    }

    protected afh c(float f2) {
        this.w = f2;
        if (this.x < f2 * 5.0f) {
            this.x = f2 * 5.0f;
        }
        return this;
    }

    protected afh x() {
        this.c(-1.0f);
        return this;
    }

    public float g(adm adm2, cj cj2) {
        return this.w;
    }

    protected afh a(boolean bl2) {
        this.z = bl2;
        return this;
    }

    public boolean y() {
        return this.z;
    }

    public boolean z() {
        return this.A;
    }

    protected final void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        this.B = f2;
        this.C = f3;
        this.D = f4;
        this.E = f5;
        this.F = f6;
        this.G = f7;
    }

    public int c(adq adq2, cj cj22) {
        afh \u26033 = adq2.p(cj22).c();
        int \u26032 = adq2.b(cj22, \u26033.r());
        if (\u26032 == 0 && \u26033 instanceof ahh) {
            cj cj22 = cj22.b();
            \u26033 = adq2.p(cj22).c();
            return adq2.b(cj22, \u26033.r());
        }
        return \u26032;
    }

    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (cq2 == cq.a && this.C > 0.0) {
            return true;
        }
        if (cq2 == cq.b && this.F < 1.0) {
            return true;
        }
        if (cq2 == cq.c && this.D > 0.0) {
            return true;
        }
        if (cq2 == cq.d && this.G < 1.0) {
            return true;
        }
        if (cq2 == cq.e && this.B > 0.0) {
            return true;
        }
        if (cq2 == cq.f && this.E < 1.0) {
            return true;
        }
        return !adq2.p(cj2).c().c();
    }

    public boolean b(adq adq2, cj cj2, cq cq2) {
        return adq2.p(cj2).c().t().a();
    }

    public aug b(adm adm2, cj cj2) {
        return new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (double)cj2.o() + this.F, (double)cj2.p() + this.G);
    }

    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        aug aug3 = this.a(adm2, cj2, alz2);
        if (aug3 != null && aug2.b(aug3)) {
            list.add(aug3);
        }
    }

    public aug a(adm adm2, cj cj2, alz alz2) {
        return new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (double)cj2.o() + this.F, (double)cj2.p() + this.G);
    }

    public boolean c() {
        return true;
    }

    public boolean a(alz alz2, boolean bl2) {
        return this.A();
    }

    public boolean A() {
        return true;
    }

    public void a(adm adm2, cj cj2, alz alz2, Random random) {
        this.b(adm2, cj2, alz2, random);
    }

    public void b(adm adm2, cj cj2, alz alz2, Random random) {
    }

    public void c(adm adm2, cj cj2, alz alz2, Random random) {
    }

    public void d(adm adm2, cj cj2, alz alz2) {
    }

    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
    }

    public int a(adm adm2) {
        return 10;
    }

    public void c(adm adm2, cj cj2, alz alz2) {
    }

    public void b(adm adm2, cj cj2, alz alz2) {
    }

    public int a(Random random) {
        return 1;
    }

    public zw a(alz alz2, Random random, int n2) {
        return zw.a(this);
    }

    public float a(wn wn2, adm adm2, cj cj2) {
        float f2 = this.g(adm2, cj2);
        if (f2 < 0.0f) {
            return 0.0f;
        }
        if (!wn2.b(this)) {
            return wn2.a(this) / f2 / 100.0f;
        }
        return wn2.a(this) / f2 / 30.0f;
    }

    public final void b(adm adm2, cj cj2, alz alz2, int n2) {
        this.a(adm2, cj2, alz2, 1.0f, n2);
    }

    public void a(adm adm2, cj cj2, alz alz2, float f2, int n2) {
        if (adm2.D) {
            return;
        }
        \u2603 = this.a(n2, adm2.s);
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            if (adm2.s.nextFloat() > f2 || (\u2603 = this.a(alz2, adm2.s, n2)) == null) continue;
            afh.a(adm2, cj2, new zx(\u2603, 1, this.a(alz2)));
        }
    }

    public static void a(adm adm2, cj cj2, zx zx2) {
        if (adm2.D || !adm2.Q().b("doTileDrops")) {
            return;
        }
        float f2 = 0.5f;
        double \u26032 = (double)(adm2.s.nextFloat() * f2) + (double)(1.0f - f2) * 0.5;
        double \u26033 = (double)(adm2.s.nextFloat() * f2) + (double)(1.0f - f2) * 0.5;
        double \u26034 = (double)(adm2.s.nextFloat() * f2) + (double)(1.0f - f2) * 0.5;
        uz \u26035 = new uz(adm2, (double)cj2.n() + \u26032, (double)cj2.o() + \u26033, (double)cj2.p() + \u26034, zx2);
        \u26035.p();
        adm2.d(\u26035);
    }

    protected void b(adm adm2, cj cj2, int n2) {
        if (!adm2.D) {
            while (n2 > 0) {
                \u2603 = pp.a(n2);
                n2 -= \u2603;
                adm2.d(new pp(adm2, (double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, \u2603));
            }
        }
    }

    public int a(alz alz2) {
        return 0;
    }

    public float a(pk pk2) {
        return this.x / 5.0f;
    }

    public auh a(adm adm2, cj cj2, aui aui2, aui aui3) {
        this.a((adq)adm2, cj2);
        aui2 = aui2.b(-cj2.n(), -cj2.o(), -cj2.p());
        aui3 = aui3.b(-cj2.n(), -cj2.o(), -cj2.p());
        \u2603 = aui2.a(aui3, this.B);
        \u2603 = aui2.a(aui3, this.E);
        \u2603 = aui2.b(aui3, this.C);
        \u2603 = aui2.b(aui3, this.F);
        \u2603 = aui2.c(aui3, this.D);
        \u2603 = aui2.c(aui3, this.G);
        if (!this.a(\u2603)) {
            \u2603 = null;
        }
        if (!this.a(\u2603)) {
            \u2603 = null;
        }
        if (!this.b(\u2603)) {
            \u2603 = null;
        }
        if (!this.b(\u2603)) {
            \u2603 = null;
        }
        if (!this.c(\u2603)) {
            \u2603 = null;
        }
        if (!this.c(\u2603)) {
            \u2603 = null;
        }
        \u2603 = null;
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 != null && (\u2603 == null || aui2.g(\u2603) < aui2.g(\u2603))) {
            \u2603 = \u2603;
        }
        if (\u2603 == null) {
            return null;
        }
        cq cq2 = null;
        if (\u2603 == \u2603) {
            cq2 = cq.e;
        }
        if (\u2603 == \u2603) {
            cq2 = cq.f;
        }
        if (\u2603 == \u2603) {
            cq2 = cq.a;
        }
        if (\u2603 == \u2603) {
            cq2 = cq.b;
        }
        if (\u2603 == \u2603) {
            cq2 = cq.c;
        }
        if (\u2603 == \u2603) {
            cq2 = cq.d;
        }
        return new auh(\u2603.b(cj2.n(), cj2.o(), cj2.p()), cq2, cj2);
    }

    private boolean a(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.b >= this.C && aui2.b <= this.F && aui2.c >= this.D && aui2.c <= this.G;
    }

    private boolean b(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.a >= this.B && aui2.a <= this.E && aui2.c >= this.D && aui2.c <= this.G;
    }

    private boolean c(aui aui2) {
        if (aui2 == null) {
            return false;
        }
        return aui2.a >= this.B && aui2.a <= this.E && aui2.b >= this.C && aui2.b <= this.F;
    }

    public void a(adm adm2, cj cj2, adi adi2) {
    }

    public adf m() {
        return adf.a;
    }

    public boolean a(adm adm2, cj cj2, cq cq2, zx zx2) {
        return this.b(adm2, cj2, cq2);
    }

    public boolean b(adm adm2, cj cj2, cq cq2) {
        return this.d(adm2, cj2);
    }

    public boolean d(adm adm2, cj cj2) {
        return adm2.p((cj)cj2).c().J.j();
    }

    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        return false;
    }

    public void a(adm adm2, cj cj2, pk pk2) {
    }

    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.a(n2);
    }

    public void a(adm adm2, cj cj2, wn wn2) {
    }

    public aui a(adm adm2, cj cj2, pk pk2, aui aui2) {
        return aui2;
    }

    public void a(adq adq2, cj cj2) {
    }

    public final double B() {
        return this.B;
    }

    public final double C() {
        return this.E;
    }

    public final double D() {
        return this.C;
    }

    public final double E() {
        return this.F;
    }

    public final double F() {
        return this.D;
    }

    public final double G() {
        return this.G;
    }

    public int H() {
        return 0xFFFFFF;
    }

    public int h(alz alz2) {
        return 0xFFFFFF;
    }

    public int a(adq adq2, cj cj2, int n2) {
        return 0xFFFFFF;
    }

    public final int d(adq adq2, cj cj2) {
        return this.a(adq2, cj2, 0);
    }

    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        return 0;
    }

    public boolean i() {
        return false;
    }

    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
    }

    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        return 0;
    }

    public void j() {
    }

    public void a(adm adm2, wn wn22, cj cj2, alz alz2, akw akw2) {
        wn22.b(na.ab[afh.a(this)]);
        wn22.a(0.025f);
        if (this.I() && ack.e(wn22)) {
            zx zx2 = this.i(alz2);
            if (zx2 != null) {
                afh.a(adm2, cj2, zx2);
            }
        } else {
            wn wn22;
            int \u26032 = ack.f(wn22);
            this.b(adm2, cj2, alz2, \u26032);
        }
    }

    protected boolean I() {
        return this.d() && !this.A;
    }

    protected zx i(alz alz2) {
        int n2 = 0;
        zw \u26032 = zw.a(this);
        if (\u26032 != null && \u26032.k()) {
            n2 = this.c(alz2);
        }
        return new zx(\u26032, 1, n2);
    }

    public int a(int n2, Random random) {
        return this.a(random);
    }

    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
    }

    public boolean g() {
        return !this.J.a() && !this.J.d();
    }

    public afh c(String string) {
        this.O = string;
        return this;
    }

    public String f() {
        return di.a(this.a() + ".name");
    }

    public String a() {
        return "tile." + this.O;
    }

    public boolean a(adm adm2, cj cj2, alz alz2, int n2, int n3) {
        return false;
    }

    public boolean J() {
        return this.y;
    }

    protected afh K() {
        this.y = false;
        return this;
    }

    public int k() {
        return this.J.m();
    }

    public float h() {
        return this.u() ? 0.2f : 1.0f;
    }

    public void a(adm adm2, cj cj2, pk pk2, float f2) {
        pk2.e(f2, 1.0f);
    }

    public void a(adm adm2, pk pk2) {
        pk2.w = 0.0;
    }

    public zw c(adm adm2, cj cj2) {
        return zw.a(this);
    }

    public int j(adm adm2, cj cj2) {
        return this.a(adm2.p(cj2));
    }

    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
    }

    public yz L() {
        return this.b;
    }

    public afh a(yz yz2) {
        this.b = yz2;
        return this;
    }

    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
    }

    public void k(adm adm2, cj cj2) {
    }

    public boolean M() {
        return false;
    }

    public boolean N() {
        return true;
    }

    public boolean a(adi adi2) {
        return true;
    }

    public boolean b(afh afh2) {
        return this == afh2;
    }

    public static boolean a(afh afh2, afh afh3) {
        if (afh2 == null || afh3 == null) {
            return false;
        }
        if (afh2 == afh3) {
            return true;
        }
        return afh2.b(afh3);
    }

    public boolean O() {
        return false;
    }

    public int l(adm adm2, cj cj2) {
        return 0;
    }

    public alz b(alz alz2) {
        return alz2;
    }

    protected ama e() {
        return new ama(this, new amo[0]);
    }

    public ama P() {
        return this.M;
    }

    protected final void j(alz alz2) {
        this.N = alz2;
    }

    public final alz Q() {
        return this.N;
    }

    public a R() {
        return afh$a.a;
    }

    public String toString() {
        return "Block{" + c.c(this) + "}";
    }

    public static void S() {
        int \u26032;
        afh.a(0, a, new aey().c("air"));
        afh.a(1, "stone", new ajy().c(1.5f).b(10.0f).a(i).c("stone"));
        afh.a(2, "grass", new ahe().c(0.6f).a(h).c("grass"));
        afh.a(3, "dirt", new agf().c(0.5f).a(g).c("dirt"));
        afh afh2 = new afh(arm.e).c(2.0f).b(10.0f).a(i).c("stonebrick").a(yz.b);
        afh.a(4, "cobblestone", afh2);
        \u2603 = new aio().c(2.0f).b(5.0f).a(f).c("wood");
        afh.a(5, "planks", \u2603);
        afh.a(6, "sapling", new ajj().c(0.0f).a(h).c("sapling"));
        afh.a(7, "bedrock", new afh(arm.e).x().b(6000000.0f).a(i).c("bedrock").K().a(yz.b));
        afh.a(8, "flowing_water", new agl(arm.h).c(100.0f).e(3).c("water").K());
        afh.a(9, "water", new ajw(arm.h).c(100.0f).e(3).c("water").K());
        afh.a(10, "flowing_lava", new agl(arm.i).c(100.0f).a(1.0f).c("lava").K());
        afh.a(11, "lava", new ajw(arm.i).c(100.0f).a(1.0f).c("lava").K());
        afh.a(12, "sand", new ajh().c(0.5f).a(m).c("sand"));
        afh.a(13, "gravel", new ahf().c(0.6f).a(g).c("gravel"));
        afh.a(14, "gold_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreGold"));
        afh.a(15, "iron_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreIron"));
        afh.a(16, "coal_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreCoal"));
        afh.a(17, "log", new ail().c("log"));
        afh.a(18, "leaves", new aik().c("leaves"));
        afh.a(19, "sponge", new ajr().c(0.6f).a(h).c("sponge"));
        afh.a(20, "glass", new ahc(arm.s, false).c(0.3f).a(k).c("glass"));
        afh.a(21, "lapis_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreLapis"));
        afh.a(22, "lapis_block", new afh(arm.f, arn.H).c(3.0f).b(5.0f).a(i).c("blockLapis").a(yz.b));
        afh.a(23, "dispenser", new agg().c(3.5f).a(i).c("dispenser"));
        \u2603 = new aji().a(i).c(0.8f).c("sandStone");
        afh.a(24, "sandstone", \u2603);
        afh.a(25, "noteblock", new aii().c(0.8f).c("musicBlock"));
        afh.a(26, "bed", new afg().a(f).c(0.2f).c("bed").K());
        afh.a(27, "golden_rail", new ais().c(0.7f).a(j).c("goldenRail"));
        afh.a(28, "detector_rail", new agc().c(0.7f).a(j).c("detectorRail"));
        afh.a(29, "sticky_piston", new als(true).c("pistonStickyBase"));
        afh.a(30, "web", new ako().e(1).c(4.0f).c("web"));
        afh.a(31, "tallgrass", new akc().c(0.0f).a(h).c("tallgrass"));
        afh.a(32, "deadbush", new agb().c(0.0f).a(h).c("deadbush"));
        afh.a(33, "piston", new als(false).c("pistonBase"));
        afh.a(34, "piston_head", new alt().c("pistonBase"));
        afh.a(35, "wool", new afv(arm.n).c(0.8f).a(l).c("cloth"));
        afh.a(36, "piston_extension", (afh)new alv());
        afh.a(37, "yellow_flower", new akt().c(0.0f).a(h).c("flower1"));
        afh.a(38, "red_flower", new aiy().c(0.0f).a(h).c("flower2"));
        \u2603 = new aia().c(0.0f).a(h).a(0.125f).c("mushroom");
        afh.a(39, "brown_mushroom", \u2603);
        \u2603 = new aia().c(0.0f).a(h).c("mushroom");
        afh.a(40, "red_mushroom", \u2603);
        afh.a(41, "gold_block", new afh(arm.f, arn.F).c(3.0f).b(10.0f).a(j).c("blockGold").a(yz.b));
        afh.a(42, "iron_block", new afh(arm.f, arn.h).c(5.0f).b(10.0f).a(j).c("blockIron").a(yz.b));
        afh.a(43, "double_stone_slab", new agz().c(2.0f).b(10.0f).a(i).c("stoneSlab"));
        afh.a(44, "stone_slab", new ahi().c(2.0f).b(10.0f).a(i).c("stoneSlab"));
        \u2603 = new afh(arm.e, arn.D).c(2.0f).b(10.0f).a(i).c("brick").a(yz.b);
        afh.a(45, "brick_block", \u2603);
        afh.a(46, "tnt", new ake().c(0.0f).a(h).c("tnt"));
        afh.a(47, "bookshelf", new afk().c(1.5f).a(f).c("bookshelf"));
        afh.a(48, "mossy_cobblestone", new afh(arm.e).c(2.0f).b(10.0f).a(i).c("stoneMoss").a(yz.b));
        afh.a(49, "obsidian", new aij().c(50.0f).b(2000.0f).a(i).c("obsidian"));
        afh.a(50, "torch", new akf().c(0.0f).a(0.9375f).a(f).c("torch"));
        afh.a(51, "fire", new agv().c(0.0f).a(1.0f).a(l).c("fire").K());
        afh.a(52, "mob_spawner", new ahy().c(5.0f).a(j).c("mobSpawner").K());
        afh.a(53, "oak_stairs", new aju(\u2603.Q().a(aio.a, aio.a.a)).c("stairsWood"));
        afh.a(54, "chest", new afs(0).c(2.5f).a(f).c("chest"));
        afh.a(55, "redstone_wire", new ajb().c(0.0f).a(e).c("redstoneDust").K());
        afh.a(56, "diamond_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreDiamond"));
        afh.a(57, "diamond_block", new afh(arm.f, arn.G).c(5.0f).b(10.0f).a(j).c("blockDiamond").a(yz.b));
        afh.a(58, "crafting_table", new afy().c(2.5f).a(f).c("workbench"));
        afh.a(59, "wheat", new afz().c("crops"));
        \u2603 = new ags().c(0.6f).a(g).c("farmland");
        afh.a(60, "farmland", \u2603);
        afh.a(61, "furnace", new ahb(false).c(3.5f).a(i).c("furnace").a(yz.c));
        afh.a(62, "lit_furnace", new ahb(true).c(3.5f).a(i).a(0.875f).c("furnace"));
        afh.a(63, "standing_sign", new ajv().c(1.0f).a(f).c("sign").K());
        afh.a(64, "wooden_door", new agh(arm.d).c(3.0f).a(f).c("doorOak").K());
        afh.a(65, "ladder", new ahr().c(0.4f).a(o).c("ladder"));
        afh.a(66, "rail", new aix().c(0.7f).a(j).c("rail"));
        afh.a(67, "stone_stairs", new aju(afh2.Q()).c("stairsStone"));
        afh.a(68, "wall_sign", new akm().c(1.0f).a(f).c("sign").K());
        afh.a(69, "lever", new ahu().c(0.5f).a(f).c("lever"));
        afh.a(70, "stone_pressure_plate", new ait(arm.e, ait.a.b).c(0.5f).a(i).c("pressurePlateStone"));
        afh.a(71, "iron_door", new agh(arm.f).c(5.0f).a(j).c("doorIron").K());
        afh.a(72, "wooden_pressure_plate", new ait(arm.d, ait.a.a).c(0.5f).a(f).c("pressurePlateWood"));
        afh.a(73, "redstone_ore", new aja(false).c(3.0f).b(5.0f).a(i).c("oreRedstone").a(yz.b));
        afh.a(74, "lit_redstone_ore", new aja(true).a(0.625f).c(3.0f).b(5.0f).a(i).c("oreRedstone"));
        afh.a(75, "unlit_redstone_torch", new ajd(false).c(0.0f).a(f).c("notGate"));
        afh.a(76, "redstone_torch", new ajd(true).c(0.0f).a(0.5f).a(f).c("notGate").a(yz.d));
        afh.a(77, "stone_button", new aka().c(0.5f).a(i).c("button"));
        afh.a(78, "snow_layer", new ajp().c(0.1f).a(n).c("snow").e(0));
        afh.a(79, "ice", new ahp().c(0.5f).e(3).a(k).c("ice"));
        afh.a(80, "snow", new ajo().c(0.2f).a(n).c("snow"));
        afh.a(81, "cactus", new afo().c(0.4f).a(l).c("cactus"));
        afh.a(82, "clay", new aft().c(0.6f).a(g).c("clay"));
        afh.a(83, "reeds", new aje().c(0.0f).a(h).c("reeds").K());
        afh.a(84, "jukebox", new ahq().c(2.0f).b(10.0f).a(i).c("jukebox"));
        afh.a(85, "fence", new agt(arm.d, aio.a.a.c()).c(2.0f).b(5.0f).a(f).c("fence"));
        \u2603 = new aiv().c(1.0f).a(f).c("pumpkin");
        afh.a(86, "pumpkin", \u2603);
        afh.a(87, "netherrack", new aie().c(0.4f).a(i).c("hellrock"));
        afh.a(88, "soul_sand", new ajq().c(0.5f).a(m).c("hellsand"));
        afh.a(89, "glowstone", new ahd(arm.s).c(0.3f).a(k).a(1.0f).c("lightgem"));
        afh.a(90, "portal", new aip().c(-1.0f).a(k).a(0.75f).c("portal"));
        afh.a(91, "lit_pumpkin", new aiv().c(1.0f).a(f).a(1.0f).c("litpumpkin"));
        afh.a(92, "cake", new afp().c(0.5f).a(l).c("cake").K());
        afh.a(93, "unpowered_repeater", new ajf(false).c(0.0f).a(f).c("diode").K());
        afh.a(94, "powered_repeater", new ajf(true).c(0.0f).a(f).c("diode").K());
        afh.a(95, "stained_glass", new ajs(arm.s).c(0.3f).a(k).c("stainedGlass"));
        afh.a(96, "trapdoor", new akh(arm.d).c(3.0f).a(f).c("trapdoor").K());
        afh.a(97, "monster_egg", new ahz().c(0.75f).c("monsterStoneEgg"));
        \u2603 = new ajz().c(1.5f).b(10.0f).a(i).c("stonebricksmooth");
        afh.a(98, "stonebrick", \u2603);
        afh.a(99, "brown_mushroom_block", new aho(arm.d, arn.l, \u2603).c(0.2f).a(f).c("mushroom"));
        afh.a(100, "red_mushroom_block", new aho(arm.d, arn.D, \u2603).c(0.2f).a(f).c("mushroom"));
        afh.a(101, "iron_bars", new akd(arm.f, true).c(5.0f).b(10.0f).a(j).c("fenceIron"));
        afh.a(102, "glass_pane", new akd(arm.s, false).c(0.3f).a(k).c("thinGlass"));
        \u2603 = new ahx().c(1.0f).a(f).c("melon");
        afh.a(103, "melon_block", \u2603);
        afh.a(104, "pumpkin_stem", new ajx(\u2603).c(0.0f).a(f).c("pumpkinStem"));
        afh.a(105, "melon_stem", new ajx(\u2603).c(0.0f).a(f).c("pumpkinStem"));
        afh.a(106, "vine", new akk().c(0.2f).a(h).c("vine"));
        afh.a(107, "fence_gate", new agu(aio.a.a).c(2.0f).b(5.0f).a(f).c("fenceGate"));
        afh.a(108, "brick_stairs", new aju(\u2603.Q()).c("stairsBrick"));
        afh.a(109, "stone_brick_stairs", new aju(\u2603.Q().a(ajz.a, ajz.a.a)).c("stairsStoneBrickSmooth"));
        afh.a(110, "mycelium", new aib().c(0.6f).a(h).c("mycel"));
        afh.a(111, "waterlily", new akn().c(0.0f).a(h).c("waterlily"));
        \u2603 = new aic().c(2.0f).b(10.0f).a(i).c("netherBrick").a(yz.b);
        afh.a(112, "nether_brick", \u2603);
        afh.a(113, "nether_brick_fence", new agt(arm.e, arn.K).c(2.0f).b(10.0f).a(i).c("netherFence"));
        afh.a(114, "nether_brick_stairs", new aju(\u2603.Q()).c("stairsNetherBrick"));
        afh.a(115, "nether_wart", new aid().c("netherStalk"));
        afh.a(116, "enchanting_table", new agm().c(5.0f).b(2000.0f).c("enchantmentTable"));
        afh.a(117, "brewing_stand", new afl().c(0.5f).a(0.125f).c("brewingStand"));
        afh.a(118, "cauldron", new afr().c(2.0f).c("cauldron"));
        afh.a(119, "end_portal", new agn(arm.E).c(-1.0f).b(6000000.0f));
        afh.a(120, "end_portal_frame", new ago().a(k).a(0.125f).c(-1.0f).c("endPortalFrame").b(6000000.0f).a(yz.c));
        afh.a(121, "end_stone", new afh(arm.e, arn.d).c(3.0f).b(15.0f).a(i).c("whiteStone").a(yz.b));
        afh.a(122, "dragon_egg", new agj().c(3.0f).b(15.0f).a(i).a(0.125f).c("dragonEgg"));
        afh.a(123, "redstone_lamp", new ajc(false).c(0.3f).a(k).c("redstoneLight").a(yz.d));
        afh.a(124, "lit_redstone_lamp", new ajc(true).c(0.3f).a(k).c("redstoneLight"));
        afh.a(125, "double_wooden_slab", new aha().c(2.0f).b(5.0f).a(f).c("woodSlab"));
        afh.a(126, "wooden_slab", new ahk().c(2.0f).b(5.0f).a(f).c("woodSlab"));
        afh.a(127, "cocoa", new afu().c(0.2f).b(5.0f).a(f).c("cocoa"));
        afh.a(128, "sandstone_stairs", new aju(\u2603.Q().a(aji.a, aji.a.c)).c("stairsSandStone"));
        afh.a(129, "emerald_ore", new aim().c(3.0f).b(5.0f).a(i).c("oreEmerald"));
        afh.a(130, "ender_chest", new agp().c(22.5f).b(1000.0f).a(i).c("enderChest").a(0.5f));
        afh.a(131, "tripwire_hook", new akj().c("tripWireSource"));
        afh.a(132, "tripwire", new aki().c("tripWire"));
        afh.a(133, "emerald_block", new afh(arm.f, arn.I).c(5.0f).b(10.0f).a(j).c("blockEmerald").a(yz.b));
        afh.a(134, "spruce_stairs", new aju(\u2603.Q().a(aio.a, aio.a.b)).c("stairsWoodSpruce"));
        afh.a(135, "birch_stairs", new aju(\u2603.Q().a(aio.a, aio.a.c)).c("stairsWoodBirch"));
        afh.a(136, "jungle_stairs", new aju(\u2603.Q().a(aio.a, aio.a.d)).c("stairsWoodJungle"));
        afh.a(137, "command_block", new afw().x().b(6000000.0f).c("commandBlock"));
        afh.a(138, "beacon", new aff().c("beacon").a(1.0f));
        afh.a(139, "cobblestone_wall", new akl(afh2).c("cobbleWall"));
        afh.a(140, "flower_pot", new agx().c(0.0f).a(e).c("flowerPot"));
        afh.a(141, "carrots", new afq().c("carrots"));
        afh.a(142, "potatoes", new aiq().c("potatoes"));
        afh.a(143, "wooden_button", new akq().c(0.5f).a(f).c("button"));
        afh.a(144, "skull", new ajm().c(1.0f).a(i).c("skull"));
        afh.a(145, "anvil", new aez().c(5.0f).a(p).b(2000.0f).c("anvil"));
        afh.a(146, "trapped_chest", new afs(1).c(2.5f).a(f).c("chestTrap"));
        afh.a(147, "light_weighted_pressure_plate", new akp(arm.f, 15, arn.F).c(0.5f).a(f).c("weightedPlate_light"));
        afh.a(148, "heavy_weighted_pressure_plate", new akp(arm.f, 150).c(0.5f).a(f).c("weightedPlate_heavy"));
        afh.a(149, "unpowered_comparator", new afx(false).c(0.0f).a(f).c("comparator").K());
        afh.a(150, "powered_comparator", new afx(true).c(0.0f).a(0.625f).a(f).c("comparator").K());
        afh.a(151, "daylight_detector", (afh)new aga(false));
        afh.a(152, "redstone_block", new air(arm.f, arn.f).c(5.0f).b(10.0f).a(j).c("blockRedstone").a(yz.d));
        afh.a(153, "quartz_ore", new aim(arn.K).c(3.0f).b(5.0f).a(i).c("netherquartz"));
        afh.a(154, "hopper", new ahn().c(3.0f).b(8.0f).a(j).c("hopper"));
        \u2603 = new aiw().a(i).c(0.8f).c("quartzBlock");
        afh.a(155, "quartz_block", \u2603);
        afh.a(156, "quartz_stairs", new aju(\u2603.Q().a(aiw.a, aiw.a.a)).c("stairsQuartz"));
        afh.a(157, "activator_rail", new ais().c(0.7f).a(j).c("activatorRail"));
        afh.a(158, "dropper", new agk().c(3.5f).a(i).c("dropper"));
        afh.a(159, "stained_hardened_clay", new afv(arm.e).c(1.25f).b(7.0f).a(i).c("clayHardenedStained"));
        afh.a(160, "stained_glass_pane", new ajt().c(0.3f).a(k).c("thinStainedGlass"));
        afh.a(161, "leaves2", new aif().c("leaves"));
        afh.a(162, "log2", new aig().c("log"));
        afh.a(163, "acacia_stairs", new aju(\u2603.Q().a(aio.a, aio.a.e)).c("stairsWoodAcacia"));
        afh.a(164, "dark_oak_stairs", new aju(\u2603.Q().a(aio.a, aio.a.f)).c("stairsWoodDarkOak"));
        afh.a(165, "slime", new ajn().c("slime").a(q));
        afh.a(166, "barrier", new afb().c("barrier"));
        afh.a(167, "iron_trapdoor", new akh(arm.f).c(5.0f).a(j).c("ironTrapdoor").K());
        afh.a(168, "prismarine", new aiu().c(1.5f).b(10.0f).a(i).c("prismarine"));
        afh.a(169, "sea_lantern", new ajk(arm.s).c(0.3f).a(k).a(1.0f).c("seaLantern"));
        afh.a(170, "hay_block", new ahm().c(0.5f).a(h).c("hayBlock").a(yz.b));
        afh.a(171, "carpet", new aks().c(0.1f).a(l).c("woolCarpet").e(0));
        afh.a(172, "hardened_clay", new ahl().c(1.25f).b(7.0f).a(i).c("clayHardened"));
        afh.a(173, "coal_block", new afh(arm.e, arn.E).c(5.0f).b(10.0f).a(i).c("blockCoal").a(yz.b));
        afh.a(174, "packed_ice", new ain().c(0.5f).a(k).c("icePacked"));
        afh.a(175, "double_plant", (afh)new agi());
        afh.a(176, "standing_banner", new afa.a().c(1.0f).a(f).c("banner").K());
        afh.a(177, "wall_banner", new afa.b().c(1.0f).a(f).c("banner").K());
        afh.a(178, "daylight_detector_inverted", (afh)new aga(true));
        \u2603 = new aiz().a(i).c(0.8f).c("redSandStone");
        afh.a(179, "red_sandstone", \u2603);
        afh.a(180, "red_sandstone_stairs", new aju(\u2603.Q().a(aiz.a, aiz.a.c)).c("stairsRedSandStone"));
        afh.a(181, "double_stone_slab2", new agy().c(2.0f).b(10.0f).a(i).c("stoneSlab2"));
        afh.a(182, "stone_slab2", new ahg().c(2.0f).b(10.0f).a(i).c("stoneSlab2"));
        afh.a(183, "spruce_fence_gate", new agu(aio.a.b).c(2.0f).b(5.0f).a(f).c("spruceFenceGate"));
        afh.a(184, "birch_fence_gate", new agu(aio.a.c).c(2.0f).b(5.0f).a(f).c("birchFenceGate"));
        afh.a(185, "jungle_fence_gate", new agu(aio.a.d).c(2.0f).b(5.0f).a(f).c("jungleFenceGate"));
        afh.a(186, "dark_oak_fence_gate", new agu(aio.a.f).c(2.0f).b(5.0f).a(f).c("darkOakFenceGate"));
        afh.a(187, "acacia_fence_gate", new agu(aio.a.e).c(2.0f).b(5.0f).a(f).c("acaciaFenceGate"));
        afh.a(188, "spruce_fence", new agt(arm.d, aio.a.b.c()).c(2.0f).b(5.0f).a(f).c("spruceFence"));
        afh.a(189, "birch_fence", new agt(arm.d, aio.a.c.c()).c(2.0f).b(5.0f).a(f).c("birchFence"));
        afh.a(190, "jungle_fence", new agt(arm.d, aio.a.d.c()).c(2.0f).b(5.0f).a(f).c("jungleFence"));
        afh.a(191, "dark_oak_fence", new agt(arm.d, aio.a.f.c()).c(2.0f).b(5.0f).a(f).c("darkOakFence"));
        afh.a(192, "acacia_fence", new agt(arm.d, aio.a.e.c()).c(2.0f).b(5.0f).a(f).c("acaciaFence"));
        afh.a(193, "spruce_door", new agh(arm.d).c(3.0f).a(f).c("doorSpruce").K());
        afh.a(194, "birch_door", new agh(arm.d).c(3.0f).a(f).c("doorBirch").K());
        afh.a(195, "jungle_door", new agh(arm.d).c(3.0f).a(f).c("doorJungle").K());
        afh.a(196, "acacia_door", new agh(arm.d).c(3.0f).a(f).c("doorAcacia").K());
        afh.a(197, "dark_oak_door", new agh(arm.d).c(3.0f).a(f).c("doorDarkOak").K());
        c.a();
        for (afh afh3 : c) {
            if (afh3.J == arm.a) {
                afh3.v = false;
                continue;
            }
            boolean bl2 = false;
            \u2603 = afh3 instanceof aju;
            \u26032 = afh3 instanceof ahh;
            \u2603 = afh3 == \u2603;
            \u2603 = afh3.t;
            boolean bl3 = \u2603 = afh3.s == 0;
            if (\u2603 || \u26032 != 0 || \u2603 || \u2603 || \u2603) {
                bl2 = true;
            }
            afh3.v = bl2;
        }
        for (afh afh3 : c) {
            for (alz alz2 : afh3.P().a()) {
                \u26032 = c.b(afh3) << 4 | afh3.c(alz2);
                d.a(alz2, \u26032);
            }
        }
    }

    private static void a(int n2, jy jy2, afh afh2) {
        c.a(n2, jy2, afh2);
    }

    private static void a(int n2, String string, afh afh2) {
        afh.a(n2, new jy(string), afh2);
    }

    public static enum a {
        a,
        b,
        c;

    }

    public static class b {
        public final String a;
        public final float b;
        public final float c;

        public b(String string, float f2, float f3) {
            this.a = string;
            this.b = f2;
            this.c = f3;
        }

        public float d() {
            return this.b;
        }

        public float e() {
            return this.c;
        }

        public String a() {
            return "dig." + this.a;
        }

        public String c() {
            return "step." + this.a;
        }

        public String b() {
            return this.a();
        }
    }
}

