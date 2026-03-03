/*
 * Decompiled with CFR 0.152.
 */
public class azj
implements awd.a {
    private final azh c;
    protected final ave a;
    protected final bpq.a b;
    private long d = 0L;

    protected azj(azh azh2, bpq.a a2) {
        this.c = azh2;
        this.b = a2;
        this.a = ave.A();
    }

    @Override
    public void a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, boolean bl2) {
        this.a.k.a(bnq.a("lanServer.title", new Object[0]), n3 + 32 + 3, n4 + 1, 0xFFFFFF);
        this.a.k.a(this.b.a(), n3 + 32 + 3, n4 + 12, 0x808080);
        if (this.a.t.x) {
            this.a.k.a(bnq.a("selectServer.hiddenAddress", new Object[0]), n3 + 32 + 3, n4 + 12 + 11, 0x303030);
        } else {
            this.a.k.a(this.b.b(), n3 + 32 + 3, n4 + 12 + 11, 0x303030);
        }
    }

    @Override
    public boolean a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.c.b(n2);
        if (ave.J() - this.d < 250L) {
            this.c.f();
        }
        this.d = ave.J();
        return false;
    }

    @Override
    public void a(int n2, int n3, int n4) {
    }

    @Override
    public void b(int n2, int n3, int n4, int n5, int n6, int n7) {
    }

    public bpq.a a() {
        return this.b;
    }
}

