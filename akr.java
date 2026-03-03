/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class akr
extends ahh {
    public static final amm<aio.a> b = amm.a("variant", aio.a.class);

    public akr() {
        super(arm.d);
        alz alz2 = this.M.b();
        if (!this.l()) {
            alz2 = alz2.a(a, ahh.a.b);
        }
        this.j(alz2.a(b, aio.a.a));
        this.a(yz.b);
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(b).c();
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.bM);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zw.a(afi.bM);
    }

    @Override
    public String b(int n2) {
        return super.a() + "." + aio.a.a(n2).d();
    }

    @Override
    public amo<?> n() {
        return b;
    }

    @Override
    public Object a(zx zx2) {
        return aio.a.a(zx2.i() & 7);
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        if (zw2 == zw.a(afi.bL)) {
            return;
        }
        for (aio.a a2 : aio.a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q().a(b, aio.a.a(n2 & 7));
        if (!this.l()) {
            alz2 = alz2.a(a, (n2 & 8) == 0 ? ahh.a.b : ahh.a.a);
        }
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(b).a();
        if (!this.l() && alz2.b(a) == ahh.a.a) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        if (this.l()) {
            return new ama(this, b);
        }
        return new ama(this, a, b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(b).a();
    }
}

