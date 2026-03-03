/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class afv
extends afh {
    public static final amm<zd> a = amm.a("color", zd.class);

    public afv(arm arm2) {
        super(arm2);
        this.j(this.M.b().a(a, zd.a));
        this.a(yz.b);
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (zd zd2 : zd.values()) {
            list.add(new zx(zw2, 1, zd2.a()));
        }
    }

    @Override
    public arn g(alz alz2) {
        return alz2.b(a).e();
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
        return new ama(this, a);
    }
}

