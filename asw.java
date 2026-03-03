/*
 * Decompiled with CFR 0.152.
 */
public abstract class asw {
    protected adq a;
    protected nm<asv> b = new nm();
    protected int c;
    protected int d;
    protected int e;

    public void a(adq adq2, pk pk2) {
        this.a = adq2;
        this.b.c();
        this.c = ns.d(pk2.J + 1.0f);
        this.d = ns.d(pk2.K + 1.0f);
        this.e = ns.d(pk2.J + 1.0f);
    }

    public void a() {
    }

    protected asv a(int n2, int n3, int n4) {
        \u2603 = asv.a(n2, n3, n4);
        asv asv2 = this.b.a(\u2603);
        if (asv2 == null) {
            asv2 = new asv(n2, n3, n4);
            this.b.a(\u2603, asv2);
        }
        return asv2;
    }

    public abstract asv a(pk var1);

    public abstract asv a(pk var1, double var2, double var4, double var6);

    public abstract int a(asv[] var1, pk var2, asv var3, asv var4, float var5);
}

