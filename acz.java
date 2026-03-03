/*
 * Decompiled with CFR 0.152.
 */
public class acz {
    private zx a;
    private zx b;
    private zx c;
    private int d;
    private int e;
    private boolean f;

    public acz(dn dn2) {
        this.a(dn2);
    }

    public acz(zx zx2, zx zx3, zx zx4) {
        this(zx2, zx3, zx4, 0, 7);
    }

    public acz(zx zx2, zx zx3, zx zx4, int n2, int n3) {
        this.a = zx2;
        this.b = zx3;
        this.c = zx4;
        this.d = n2;
        this.e = n3;
        this.f = true;
    }

    public acz(zx zx2, zx zx3) {
        this(zx2, null, zx3);
    }

    public acz(zx zx2, zw zw2) {
        this(zx2, new zx(zw2));
    }

    public zx a() {
        return this.a;
    }

    public zx b() {
        return this.b;
    }

    public boolean c() {
        return this.b != null;
    }

    public zx d() {
        return this.c;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.e;
    }

    public void g() {
        ++this.d;
    }

    public void a(int n2) {
        this.e += n2;
    }

    public boolean h() {
        return this.d >= this.e;
    }

    public void i() {
        this.d = this.e;
    }

    public boolean j() {
        return this.f;
    }

    public void a(dn dn2) {
        \u2603 = dn2.m("buy");
        this.a = zx.a(\u2603);
        \u2603 = dn2.m("sell");
        this.c = zx.a(\u2603);
        if (dn2.b("buyB", 10)) {
            this.b = zx.a(dn2.m("buyB"));
        }
        if (dn2.b("uses", 99)) {
            this.d = dn2.f("uses");
        }
        this.e = dn2.b("maxUses", 99) ? dn2.f("maxUses") : 7;
        this.f = dn2.b("rewardExp", 1) ? dn2.n("rewardExp") : true;
    }

    public dn k() {
        dn dn2 = new dn();
        dn2.a("buy", this.a.b(new dn()));
        dn2.a("sell", this.c.b(new dn()));
        if (this.b != null) {
            dn2.a("buyB", this.b.b(new dn()));
        }
        dn2.a("uses", this.d);
        dn2.a("maxUses", this.e);
        dn2.a("rewardExp", this.f);
        return dn2;
    }
}

