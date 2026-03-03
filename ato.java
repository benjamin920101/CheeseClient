/*
 * Decompiled with CFR 0.152.
 */
import java.util.concurrent.Callable;
import net.minecraft.server.MinecraftServer;

public class ato {
    public static final oj a = oj.c;
    private long b;
    private adr c = adr.b;
    private String d = "";
    private int e;
    private int f;
    private int g;
    private long h;
    private long i;
    private long j;
    private long k;
    private dn l;
    private int m;
    private String n;
    private int o;
    private int p;
    private boolean q;
    private int r;
    private boolean s;
    private int t;
    private adp.a u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private oj z;
    private boolean A;
    private double B = 0.0;
    private double C = 0.0;
    private double D = 6.0E7;
    private long E = 0L;
    private double F = 0.0;
    private double G = 5.0;
    private double H = 0.2;
    private int I = 5;
    private int J = 15;
    private adk K = new adk();

    protected ato() {
    }

    public ato(dn dn22) {
        dn dn22;
        this.b = dn22.g("RandomSeed");
        if (dn22.b("generatorName", 8)) {
            String string = dn22.j("generatorName");
            this.c = adr.a(string);
            if (this.c == null) {
                this.c = adr.b;
            } else if (this.c.f()) {
                int n2 = 0;
                if (dn22.b("generatorVersion", 99)) {
                    n2 = dn22.f("generatorVersion");
                }
                this.c = this.c.a(n2);
            }
            if (dn22.b("generatorOptions", 8)) {
                this.d = dn22.j("generatorOptions");
            }
        }
        this.u = adp.a.a(dn22.f("GameType"));
        this.v = dn22.b("MapFeatures", 99) ? dn22.n("MapFeatures") : true;
        this.e = dn22.f("SpawnX");
        this.f = dn22.f("SpawnY");
        this.g = dn22.f("SpawnZ");
        this.h = dn22.g("Time");
        this.i = dn22.b("DayTime", 99) ? dn22.g("DayTime") : this.h;
        this.j = dn22.g("LastPlayed");
        this.k = dn22.g("SizeOnDisk");
        this.n = dn22.j("LevelName");
        this.o = dn22.f("version");
        this.p = dn22.f("clearWeatherTime");
        this.r = dn22.f("rainTime");
        this.q = dn22.n("raining");
        this.t = dn22.f("thunderTime");
        this.s = dn22.n("thundering");
        this.w = dn22.n("hardcore");
        this.y = dn22.b("initialized", 99) ? dn22.n("initialized") : true;
        if (dn22.b("allowCommands", 99)) {
            this.x = dn22.n("allowCommands");
        } else {
            boolean bl2 = this.x = this.u == adp.a.c;
        }
        if (dn22.b("Player", 10)) {
            this.l = dn22.m("Player");
            this.m = this.l.f("Dimension");
        }
        if (dn22.b("GameRules", 10)) {
            this.K.a(dn22.m("GameRules"));
        }
        if (dn22.b("Difficulty", 99)) {
            this.z = oj.a(dn22.d("Difficulty"));
        }
        if (dn22.b("DifficultyLocked", 1)) {
            this.A = dn22.n("DifficultyLocked");
        }
        if (dn22.b("BorderCenterX", 99)) {
            this.B = dn22.i("BorderCenterX");
        }
        if (dn22.b("BorderCenterZ", 99)) {
            this.C = dn22.i("BorderCenterZ");
        }
        if (dn22.b("BorderSize", 99)) {
            this.D = dn22.i("BorderSize");
        }
        if (dn22.b("BorderSizeLerpTime", 99)) {
            this.E = dn22.g("BorderSizeLerpTime");
        }
        if (dn22.b("BorderSizeLerpTarget", 99)) {
            this.F = dn22.i("BorderSizeLerpTarget");
        }
        if (dn22.b("BorderSafeZone", 99)) {
            this.G = dn22.i("BorderSafeZone");
        }
        if (dn22.b("BorderDamagePerBlock", 99)) {
            this.H = dn22.i("BorderDamagePerBlock");
        }
        if (dn22.b("BorderWarningBlocks", 99)) {
            this.I = dn22.f("BorderWarningBlocks");
        }
        if (dn22.b("BorderWarningTime", 99)) {
            this.J = dn22.f("BorderWarningTime");
        }
    }

    public ato(adp adp2, String string) {
        this.a(adp2);
        this.n = string;
        this.z = a;
        this.y = false;
    }

    public void a(adp adp2) {
        this.b = adp2.d();
        this.u = adp2.e();
        this.v = adp2.g();
        this.w = adp2.f();
        this.c = adp2.h();
        this.d = adp2.j();
        this.x = adp2.i();
    }

    public ato(ato ato2) {
        this.b = ato2.b;
        this.c = ato2.c;
        this.d = ato2.d;
        this.u = ato2.u;
        this.v = ato2.v;
        this.e = ato2.e;
        this.f = ato2.f;
        this.g = ato2.g;
        this.h = ato2.h;
        this.i = ato2.i;
        this.j = ato2.j;
        this.k = ato2.k;
        this.l = ato2.l;
        this.m = ato2.m;
        this.n = ato2.n;
        this.o = ato2.o;
        this.r = ato2.r;
        this.q = ato2.q;
        this.t = ato2.t;
        this.s = ato2.s;
        this.w = ato2.w;
        this.x = ato2.x;
        this.y = ato2.y;
        this.K = ato2.K;
        this.z = ato2.z;
        this.A = ato2.A;
        this.B = ato2.B;
        this.C = ato2.C;
        this.D = ato2.D;
        this.E = ato2.E;
        this.F = ato2.F;
        this.G = ato2.G;
        this.H = ato2.H;
        this.J = ato2.J;
        this.I = ato2.I;
    }

    public dn a() {
        dn dn2 = new dn();
        this.a(dn2, this.l);
        return dn2;
    }

    public dn a(dn dn2) {
        \u2603 = new dn();
        this.a(\u2603, dn2);
        return \u2603;
    }

    private void a(dn dn2, dn dn3) {
        dn2.a("RandomSeed", this.b);
        dn2.a("generatorName", this.c.a());
        dn2.a("generatorVersion", this.c.d());
        dn2.a("generatorOptions", this.d);
        dn2.a("GameType", this.u.a());
        dn2.a("MapFeatures", this.v);
        dn2.a("SpawnX", this.e);
        dn2.a("SpawnY", this.f);
        dn2.a("SpawnZ", this.g);
        dn2.a("Time", this.h);
        dn2.a("DayTime", this.i);
        dn2.a("SizeOnDisk", this.k);
        dn2.a("LastPlayed", MinecraftServer.az());
        dn2.a("LevelName", this.n);
        dn2.a("version", this.o);
        dn2.a("clearWeatherTime", this.p);
        dn2.a("rainTime", this.r);
        dn2.a("raining", this.q);
        dn2.a("thunderTime", this.t);
        dn2.a("thundering", this.s);
        dn2.a("hardcore", this.w);
        dn2.a("allowCommands", this.x);
        dn2.a("initialized", this.y);
        dn2.a("BorderCenterX", this.B);
        dn2.a("BorderCenterZ", this.C);
        dn2.a("BorderSize", this.D);
        dn2.a("BorderSizeLerpTime", this.E);
        dn2.a("BorderSafeZone", this.G);
        dn2.a("BorderDamagePerBlock", this.H);
        dn2.a("BorderSizeLerpTarget", this.F);
        dn2.a("BorderWarningBlocks", (double)this.I);
        dn2.a("BorderWarningTime", (double)this.J);
        if (this.z != null) {
            dn2.a("Difficulty", (byte)this.z.a());
        }
        dn2.a("DifficultyLocked", this.A);
        dn2.a("GameRules", this.K.a());
        if (dn3 != null) {
            dn2.a("Player", dn3);
        }
    }

    public long b() {
        return this.b;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }

    public long f() {
        return this.h;
    }

    public long g() {
        return this.i;
    }

    public long h() {
        return this.k;
    }

    public dn i() {
        return this.l;
    }

    public void a(int n2) {
        this.e = n2;
    }

    public void b(int n2) {
        this.f = n2;
    }

    public void c(int n2) {
        this.g = n2;
    }

    public void b(long l2) {
        this.h = l2;
    }

    public void c(long l2) {
        this.i = l2;
    }

    public void a(cj cj2) {
        this.e = cj2.n();
        this.f = cj2.o();
        this.g = cj2.p();
    }

    public String k() {
        return this.n;
    }

    public void a(String string) {
        this.n = string;
    }

    public int l() {
        return this.o;
    }

    public void e(int n2) {
        this.o = n2;
    }

    public long m() {
        return this.j;
    }

    public int A() {
        return this.p;
    }

    public void i(int n2) {
        this.p = n2;
    }

    public boolean n() {
        return this.s;
    }

    public void a(boolean bl2) {
        this.s = bl2;
    }

    public int o() {
        return this.t;
    }

    public void f(int n2) {
        this.t = n2;
    }

    public boolean p() {
        return this.q;
    }

    public void b(boolean bl2) {
        this.q = bl2;
    }

    public int q() {
        return this.r;
    }

    public void g(int n2) {
        this.r = n2;
    }

    public adp.a r() {
        return this.u;
    }

    public boolean s() {
        return this.v;
    }

    public void f(boolean bl2) {
        this.v = bl2;
    }

    public void a(adp.a a2) {
        this.u = a2;
    }

    public boolean t() {
        return this.w;
    }

    public void g(boolean bl2) {
        this.w = bl2;
    }

    public adr u() {
        return this.c;
    }

    public void a(adr adr2) {
        this.c = adr2;
    }

    public String B() {
        return this.d;
    }

    public boolean v() {
        return this.x;
    }

    public void c(boolean bl2) {
        this.x = bl2;
    }

    public boolean w() {
        return this.y;
    }

    public void d(boolean bl2) {
        this.y = bl2;
    }

    public adk x() {
        return this.K;
    }

    public double C() {
        return this.B;
    }

    public double D() {
        return this.C;
    }

    public double E() {
        return this.D;
    }

    public void a(double d2) {
        this.D = d2;
    }

    public long F() {
        return this.E;
    }

    public void e(long l2) {
        this.E = l2;
    }

    public double G() {
        return this.F;
    }

    public void b(double d2) {
        this.F = d2;
    }

    public void c(double d2) {
        this.C = d2;
    }

    public void d(double d2) {
        this.B = d2;
    }

    public double H() {
        return this.G;
    }

    public void e(double d2) {
        this.G = d2;
    }

    public double I() {
        return this.H;
    }

    public void f(double d2) {
        this.H = d2;
    }

    public int J() {
        return this.I;
    }

    public int K() {
        return this.J;
    }

    public void j(int n2) {
        this.I = n2;
    }

    public void k(int n2) {
        this.J = n2;
    }

    public oj y() {
        return this.z;
    }

    public void a(oj oj2) {
        this.z = oj2;
    }

    public boolean z() {
        return this.A;
    }

    public void e(boolean bl2) {
        this.A = bl2;
    }

    public void a(c c2) {
        c2.a("Level seed", new Callable<String>(){

            public String a() throws Exception {
                return String.valueOf(ato.this.b());
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level generator", new Callable<String>(){

            public String a() throws Exception {
                return String.format("ID %02d - %s, ver %d. Features enabled: %b", ato.this.c.g(), ato.this.c.a(), ato.this.c.d(), ato.this.v);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level generator options", new Callable<String>(){

            public String a() throws Exception {
                return ato.this.d;
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level spawn location", new Callable<String>(){

            public String a() throws Exception {
                return c.a(ato.this.e, ato.this.f, ato.this.g);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level time", new Callable<String>(){

            public String a() throws Exception {
                return String.format("%d game time, %d day time", ato.this.h, ato.this.i);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level dimension", new Callable<String>(){

            public String a() throws Exception {
                return String.valueOf(ato.this.m);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level storage version", new Callable<String>(){

            public String a() throws Exception {
                String string = "Unknown?";
                try {
                    switch (ato.this.o) {
                        case 19133: {
                            string = "Anvil";
                            break;
                        }
                        case 19132: {
                            string = "McRegion";
                        }
                    }
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                return String.format("0x%05X - %s", ato.this.o, string);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level weather", new Callable<String>(){

            public String a() throws Exception {
                return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", ato.this.r, ato.this.q, ato.this.t, ato.this.s);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
        c2.a("Level game mode", new Callable<String>(){

            public String a() throws Exception {
                return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", ato.this.u.b(), ato.this.u.a(), ato.this.w, ato.this.x);
            }

            @Override
            public /* synthetic */ Object call() throws Exception {
                return this.a();
            }
        });
    }
}

