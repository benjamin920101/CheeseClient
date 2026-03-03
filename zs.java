/*
 * Decompiled with CFR 0.152.
 */
public class zs
extends zw {
    public final int a = 32;
    private final int b;
    private final float c;
    private final boolean d;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private float o;

    public zs(int n2, float f2, boolean bl2) {
        this.b = n2;
        this.d = bl2;
        this.c = f2;
        this.a(yz.h);
    }

    public zs(int n2, boolean bl2) {
        this(n2, 0.6f, bl2);
    }

    @Override
    public zx b(zx zx2, adm adm2, wn wn2) {
        --zx2.b;
        wn2.cl().a(this, zx2);
        adm2.a((pk)wn2, "random.burp", 0.5f, adm2.s.nextFloat() * 0.1f + 0.9f);
        this.c(zx2, adm2, wn2);
        wn2.b(na.ad[zw.b(this)]);
        return zx2;
    }

    protected void c(zx zx2, adm adm2, wn wn2) {
        if (!adm2.D && this.l > 0 && adm2.s.nextFloat() < this.o) {
            wn2.c(new pf(this.l, this.m * 20, this.n));
        }
    }

    @Override
    public int d(zx zx2) {
        return 32;
    }

    @Override
    public aba e(zx zx2) {
        return aba.b;
    }

    @Override
    public zx a(zx zx2, adm adm2, wn wn2) {
        if (wn2.j(this.k)) {
            wn2.a(zx2, this.d(zx2));
        }
        return zx2;
    }

    public int h(zx zx2) {
        return this.b;
    }

    public float i(zx zx2) {
        return this.c;
    }

    public boolean g() {
        return this.d;
    }

    public zs a(int n2, int n3, int n4, float f2) {
        this.l = n2;
        this.m = n3;
        this.n = n4;
        this.o = f2;
        return this;
    }

    public zs h() {
        this.k = true;
        return this;
    }
}

