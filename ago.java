/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class ago
extends afh {
    public static final aml a = aml.a("facing", cq.c.a);
    public static final amk b = amk.a("eye");

    public ago() {
        super(arm.e, arn.C);
        this.j(this.M.b().a(a, cq.c).a(b, false));
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.8125f, 1.0f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        if (adm2.p(cj2).b(b).booleanValue()) {
            this.a(0.3125f, 0.8125f, 0.3125f, 0.6875f, 1.0f, 0.6875f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
        this.j();
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return null;
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, pr2.aP().d()).a(b, false);
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        if (adm2.p(cj2).b(b).booleanValue()) {
            return 15;
        }
        return 0;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(b, (n2 & 4) != 0).a(a, cq.b(n2 & 3));
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).b();
        if (alz2.b(b).booleanValue()) {
            n2 |= 4;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }
}

