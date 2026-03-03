/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class ait
extends afd {
    public static final amk a = amk.a("powered");
    private final a b;

    protected ait(arm arm2, a a2) {
        super(arm2);
        this.j(this.M.b().a(a, false));
        this.b = a2;
    }

    @Override
    protected int e(alz alz2) {
        return alz2.b(a) != false ? 15 : 0;
    }

    @Override
    protected alz a(alz alz2, int n2) {
        return alz2.a(a, n2 > 0);
    }

    @Override
    protected int f(adm adm22, cj cj2) {
        List<pk> \u26032;
        aug aug2 = this.a(cj2);
        switch (this.b) {
            case a: {
                \u26032 = adm22.b(null, aug2);
                break;
            }
            case b: {
                adm adm22;
                \u26032 = adm22.a(pr.class, aug2);
                break;
            }
            default: {
                return 0;
            }
        }
        if (!\u26032.isEmpty()) {
            for (pk pk2 : \u26032) {
                if (pk2.aI()) continue;
                return 15;
            }
        }
        return 0;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2 == 1);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a) != false ? 1 : 0;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    public static enum a {
        a,
        b;

    }
}

