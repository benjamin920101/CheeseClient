/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ajf
extends agd {
    public static final amk a = amk.a("locked");
    public static final amn b = amn.a("delay", 1, 4);

    protected ajf(boolean bl2) {
        super(bl2);
        this.j(this.M.b().a(O, cq.c).a(b, 1).a(a, false));
    }

    @Override
    public String f() {
        return di.a("item.diode.name");
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(a, this.b(adq2, cj2, alz2));
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (!wn2.bA.e) {
            return false;
        }
        adm2.a(cj2, alz2.a(b), 3);
        return true;
    }

    @Override
    protected int d(alz alz2) {
        return alz2.b(b) * 2;
    }

    @Override
    protected alz e(alz alz2) {
        Integer n2 = alz2.b(b);
        Boolean \u26032 = alz2.b(a);
        cq \u26033 = alz2.b(O);
        return afi.bc.Q().a(O, \u26033).a(b, n2).a(a, \u26032);
    }

    @Override
    protected alz k(alz alz2) {
        Integer n2 = alz2.b(b);
        Boolean \u26032 = alz2.b(a);
        cq \u26033 = alz2.b(O);
        return afi.bb.Q().a(O, \u26033).a(b, n2).a(a, \u26032);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bb;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.bb;
    }

    @Override
    public boolean b(adq adq2, cj cj2, alz alz2) {
        return this.c(adq2, cj2, alz2) > 0;
    }

    @Override
    protected boolean c(afh afh2) {
        return ajf.d(afh2);
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        if (!this.N) {
            return;
        }
        cq cq2 = alz2.b(O);
        double \u26032 = (double)((float)cj2.n() + 0.5f) + (double)(random.nextFloat() - 0.5f) * 0.2;
        double \u26033 = (double)((float)cj2.o() + 0.4f) + (double)(random.nextFloat() - 0.5f) * 0.2;
        double \u26034 = (double)((float)cj2.p() + 0.5f) + (double)(random.nextFloat() - 0.5f) * 0.2;
        float \u26035 = -5.0f;
        if (random.nextBoolean()) {
            \u26035 = alz2.b(b) * 2 - 1;
        }
        double \u26036 = (\u26035 /= 16.0f) * (float)cq2.g();
        double \u26037 = \u26035 * (float)cq2.i();
        adm2.a(cy.E, \u26032 + \u26036, \u26033, \u26034 + \u26037, 0.0, 0.0, 0.0, new int[0]);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        super.b(adm2, cj2, alz2);
        this.h(adm2, cj2, alz2);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(O, cq.b(n2)).a(a, false).a(b, 1 + (n2 >> 2));
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(O).b();
        return n2 |= alz2.b(b) - 1 << 2;
    }

    @Override
    protected ama e() {
        return new ama(this, O, b, a);
    }
}

