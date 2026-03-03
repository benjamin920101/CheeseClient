/*
 * Decompiled with CFR 0.152.
 */
public class ow {
    public static ow a = new ow("inFire").n();
    public static ow b = new ow("lightningBolt");
    public static ow c = new ow("onFire").k().n();
    public static ow d = new ow("lava").n();
    public static ow e = new ow("inWall").k();
    public static ow f = new ow("drown").k();
    public static ow g = new ow("starve").k().m();
    public static ow h = new ow("cactus");
    public static ow i = new ow("fall").k();
    public static ow j = new ow("outOfWorld").k().l();
    public static ow k = new ow("generic").k();
    public static ow l = new ow("magic").k().t();
    public static ow m = new ow("wither").k();
    public static ow n = new ow("anvil");
    public static ow o = new ow("fallingBlock");
    private boolean q;
    private boolean r;
    private boolean s;
    private float t = 0.3f;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    public String p;

    public static ow a(pr pr2) {
        return new ox("mob", pr2);
    }

    public static ow a(wn wn2) {
        return new ox("player", wn2);
    }

    public static ow a(wq wq2, pk pk2) {
        return new oy("arrow", wq2, pk2).b();
    }

    public static ow a(ws ws2, pk pk2) {
        if (pk2 == null) {
            return new oy("onFire", ws2, ws2).n().b();
        }
        return new oy("fireball", ws2, pk2).n().b();
    }

    public static ow a(pk pk2, pk pk3) {
        return new oy("thrown", pk2, pk3).b();
    }

    public static ow b(pk pk2, pk pk3) {
        return new oy("indirectMagic", pk2, pk3).k().t();
    }

    public static ow a(pk pk2) {
        return new ox("thorns", pk2).v().t();
    }

    public static ow a(adi adi2) {
        if (adi2 != null && adi2.c() != null) {
            return new ox("explosion.player", adi2.c()).q().d();
        }
        return new ow("explosion").q().d();
    }

    public boolean a() {
        return this.v;
    }

    public ow b() {
        this.v = true;
        return this;
    }

    public boolean c() {
        return this.y;
    }

    public ow d() {
        this.y = true;
        return this;
    }

    public boolean e() {
        return this.q;
    }

    public float f() {
        return this.t;
    }

    public boolean g() {
        return this.r;
    }

    public boolean h() {
        return this.s;
    }

    protected ow(String string) {
        this.p = string;
    }

    public pk i() {
        return this.j();
    }

    public pk j() {
        return null;
    }

    protected ow k() {
        this.q = true;
        this.t = 0.0f;
        return this;
    }

    protected ow l() {
        this.r = true;
        return this;
    }

    protected ow m() {
        this.s = true;
        this.t = 0.0f;
        return this;
    }

    protected ow n() {
        this.u = true;
        return this;
    }

    public eu b(pr pr2) {
        \u2603 = pr2.bt();
        String string = "death.attack." + this.p;
        \u2603 = string + ".player";
        if (\u2603 != null && di.c(\u2603)) {
            return new fb(\u2603, pr2.f_(), \u2603.f_());
        }
        return new fb(string, pr2.f_());
    }

    public boolean o() {
        return this.u;
    }

    public String p() {
        return this.p;
    }

    public ow q() {
        this.w = true;
        return this;
    }

    public boolean r() {
        return this.w;
    }

    public boolean s() {
        return this.x;
    }

    public ow t() {
        this.x = true;
        return this;
    }

    public boolean u() {
        pk pk2 = this.j();
        return pk2 instanceof wn && ((wn)pk2).bA.d;
    }
}

