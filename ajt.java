/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ajt
extends akd {
    public static final amm<zd> a = amm.a("color", zd.class);

    public ajt() {
        super(arm.s, false);
        this.j(this.M.b().a(b, false).a(N, false).a(O, false).a(P, false).a(a, zd.a));
        this.a(yz.c);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (int i2 = 0; i2 < zd.values().length; ++i2) {
            list.add(new zx(zw2, 1, i2));
        }
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(a).e();
    }

    @Override
    public adf m() {
        return adf.d;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, zd.b(n2));
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    protected ama e() {
        return new ama(this, b, N, P, O, a);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (!adm2.D) {
            aff.f(adm2, cj2);
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        if (!adm2.D) {
            aff.f(adm2, cj2);
        }
    }
}

