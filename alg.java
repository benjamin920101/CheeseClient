/*
 * Decompiled with CFR 0.152.
 */
public class alg
extends akw {
    private zw a;
    private int f;

    public alg() {
    }

    public alg(zw zw2, int n2) {
        this.a = zw2;
        this.f = n2;
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        jy jy2 = zw.e.c(this.a);
        dn2.a("Item", jy2 == null ? "" : jy2.toString());
        dn2.a("Data", this.f);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.b("Item", 8) ? zw.d(dn2.j("Item")) : zw.b(dn2.f("Item"));
        this.f = dn2.f("Data");
    }

    @Override
    public ff y_() {
        dn dn2 = new dn();
        this.b(dn2);
        dn2.o("Item");
        dn2.a("Item", zw.b(this.a));
        return new ft(this.c, 5, dn2);
    }

    public void a(zw zw2, int n2) {
        this.a = zw2;
        this.f = n2;
    }

    public zw b() {
        return this.a;
    }

    public int c() {
        return this.f;
    }
}

