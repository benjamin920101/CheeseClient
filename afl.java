/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class afl
extends afc {
    public static final amk[] a = new amk[]{amk.a("has_bottle_0"), amk.a("has_bottle_1"), amk.a("has_bottle_2")};

    public afl() {
        super(arm.f);
        this.j(this.M.b().a(a[0], false).a(a[1], false).a(a[2], false));
    }

    @Override
    public String f() {
        return di.a("item.brewingStand.name");
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new akx();
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.a(0.4375f, 0.0f, 0.4375f, 0.5625f, 0.875f, 0.5625f);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.j();
        super.a(adm2, cj2, alz2, aug2, list, pk2);
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akx) {
            wn2.a((akx)akw2);
            wn2.b(na.M);
        }
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof akx) {
            ((akx)\u2603).a(zx2.q());
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        double d2 = (float)cj2.n() + 0.4f + random.nextFloat() * 0.2f;
        \u2603 = (float)cj2.o() + 0.7f + random.nextFloat() * 0.3f;
        \u2603 = (float)cj2.p() + 0.4f + random.nextFloat() * 0.2f;
        adm2.a(cy.l, d2, \u2603, \u2603, 0.0, 0.0, 0.0, new int[0]);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akx) {
            oi.a(adm2, cj2, (og)((akx)akw2));
        }
        super.b(adm2, cj2, alz2);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bF;
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.bF;
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        return xi.a(adm2.s(cj2));
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        alz alz2 = this.Q();
        for (int i2 = 0; i2 < 3; ++i2) {
            alz2 = alz2.a(a[i2], (n2 & 1 << i2) > 0);
        }
        return alz2;
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        for (\u2603 = 0; \u2603 < 3; ++\u2603) {
            if (!alz2.b(a[\u2603]).booleanValue()) continue;
            n2 |= 1 << \u2603;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a[0], a[1], a[2]);
    }
}

