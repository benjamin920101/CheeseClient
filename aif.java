/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;

public class aif
extends ahs {
    public static final amm<aio.a> Q = amm.a("variant", aio.a.class, new Predicate<aio.a>(){

        public boolean a(aio.a a2) {
            return a2.a() >= 4;
        }

        @Override
        public /* synthetic */ boolean apply(Object object) {
            return this.a((aio.a)object);
        }
    });

    public aif() {
        this.j(this.M.b().a(Q, aio.a.e).a(b, true).a(a, true));
    }

    @Override
    protected void a(adm adm2, cj cj2, alz alz2, int n2) {
        if (alz2.b(Q) == aio.a.f && adm2.s.nextInt(n2) == 0) {
            aif.a(adm2, cj2, new zx(zy.e, 1, 0));
        }
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(Q).a();
    }

    @Override
    public int j(adm adm2, cj cj2) {
        alz alz2 = adm2.p(cj2);
        return alz2.c().c(alz2) & 3;
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
        list.add(new zx(zw2, 1, 1));
    }

    @Override
    protected zx i(alz alz2) {
        return new zx(zw.a(this), 1, alz2.b(Q).a() - 4);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(Q, this.b(n2)).a(a, (n2 & 4) == 0).a(b, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(Q).a() - 4;
        if (!alz2.b(a).booleanValue()) {
            n2 |= 4;
        }
        if (alz2.b(b).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    public aio.a b(int n2) {
        return aio.a.a((n2 & 3) + 4);
    }

    @Override
    protected ama e() {
        return new ama(this, Q, b, a);
    }

    @Override
    public void a(adm adm2, wn wn2, cj cj2, alz alz2, akw akw2) {
        if (!adm2.D && wn2.bZ() != null && wn2.bZ().b() == zy.be) {
            wn2.b(na.ab[afh.a(this)]);
            aif.a(adm2, cj2, new zx(zw.a(this), 1, alz2.b(Q).a() - 4));
            return;
        }
        super.a(adm2, wn2, cj2, alz2, akw2);
    }
}

