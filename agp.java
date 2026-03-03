/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class agp
extends afc {
    public static final aml a = aml.a("facing", cq.c.a);

    protected agp() {
        super(arm.e);
        this.j(this.M.b().a(a, cq.c));
        this.a(yz.c);
        this.a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public int b() {
        return 2;
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zw.a(afi.Z);
    }

    @Override
    public int a(Random random) {
        return 8;
    }

    @Override
    protected boolean I() {
        return true;
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, pr2.aP().d());
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        adm2.a(cj2, alz2.a(a, pr2.aP().d()), 2);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        yd yd2 = wn2.co();
        akw \u26032 = adm2.s(cj2);
        if (yd2 == null || !(\u26032 instanceof alf)) {
            return true;
        }
        if (adm2.p(cj2.a()).c().v()) {
            return true;
        }
        if (adm2.D) {
            return true;
        }
        yd2.a((alf)\u26032);
        wn2.a(yd2);
        wn2.b(na.V);
        return true;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new alf();
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        for (int i2 = 0; i2 < 3; ++i2) {
            \u2603 = random.nextInt(2) * 2 - 1;
            \u2603 = random.nextInt(2) * 2 - 1;
            double d2 = (double)cj2.n() + 0.5 + 0.25 * (double)\u2603;
            \u2603 = (float)cj2.o() + random.nextFloat();
            \u2603 = (double)cj2.p() + 0.5 + 0.25 * (double)\u2603;
            \u2603 = random.nextFloat() * (float)\u2603;
            \u2603 = ((double)random.nextFloat() - 0.5) * 0.125;
            \u2603 = random.nextFloat() * (float)\u2603;
            adm2.a(cy.y, d2, \u2603, \u2603, \u2603, \u2603, \u2603, new int[0]);
        }
    }

    @Override
    public alz a(int n2) {
        cq cq2 = cq.a(n2);
        if (cq2.k() == cq.a.b) {
            cq2 = cq.c;
        }
        return this.Q().a(a, cq2);
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

