/*
 * Decompiled with CFR 0.152.
 */
public class akp
extends afd {
    public static final amn a = amn.a("power", 0, 15);
    private final int b;

    protected akp(arm arm2, int n2) {
        this(arm2, n2, arm2.r());
    }

    protected akp(arm arm2, int n2, arn arn2) {
        super(arm2, arn2);
        this.j(this.M.b().a(a, 0));
        this.b = n2;
    }

    @Override
    protected int f(adm adm2, cj cj2) {
        int n2 = Math.min(adm2.a(pk.class, this.a(cj2)).size(), this.b);
        if (n2 > 0) {
            float f2 = (float)Math.min(this.b, n2) / (float)this.b;
            return ns.f(f2 * 15.0f);
        }
        return 0;
    }

    @Override
    protected int e(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected alz a(alz alz2, int n2) {
        return alz2.a(a, n2);
    }

    @Override
    public int a(adm adm2) {
        return 10;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(a);
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }
}

