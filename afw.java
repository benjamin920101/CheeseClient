/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class afw
extends afc {
    public static final amk a = amk.a("triggered");

    public afw() {
        super(arm.f, arn.q);
        this.j(this.M.b().a(a, false));
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new akz();
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (!adm2.D) {
            boolean bl2 = adm2.z(cj2);
            \u2603 = alz2.b(a);
            if (bl2 && !\u2603) {
                adm2.a(cj2, alz2.a(a, true), 4);
                adm2.a(cj2, (afh)this, this.a(adm2));
            } else if (!bl2 && \u2603) {
                adm2.a(cj2, alz2.a(a, false), 4);
            }
        }
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akz) {
            ((akz)akw2).b().a(adm2);
            adm2.e(cj2, this);
        }
    }

    @Override
    public int a(adm adm2) {
        return 1;
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akz) {
            return ((akz)akw2).b().a(wn2);
        }
        return false;
    }

    @Override
    public boolean O() {
        return true;
    }

    @Override
    public int l(adm adm2, cj cj2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akz) {
            return ((akz)akw2).b().j();
        }
        return 0;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        akw akw2 = adm2.s(cj2);
        if (!(akw2 instanceof akz)) {
            return;
        }
        adc \u26032 = ((akz)akw2).b();
        if (zx2.s()) {
            \u26032.b(zx2.q());
        }
        if (!adm2.D) {
            \u26032.a(adm2.Q().b("sendCommandFeedback"));
        }
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public int b() {
        return 3;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, (n2 & 1) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        if (alz2.b(a).booleanValue()) {
            n2 |= 1;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a);
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return this.Q().a(a, false);
    }
}

