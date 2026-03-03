/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class akn
extends afm {
    protected akn() {
        float f2 = 0.5f;
        \u2603 = 0.015625f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, \u2603, 0.5f + f2);
        this.a(yz.c);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        if (pk2 == null || !(pk2 instanceof ux)) {
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (double)cj2.o() + this.F, (double)cj2.p() + this.G);
    }

    @Override
    public int H() {
        return 7455580;
    }

    @Override
    public int h(alz alz2) {
        return 7455580;
    }

    @Override
    public int a(adq adq2, cj cj2, int n2) {
        return 2129968;
    }

    @Override
    protected boolean c(afh afh2) {
        return afh2 == afi.j;
    }

    @Override
    public boolean f(adm adm2, cj cj2, alz alz2) {
        if (cj2.o() < 0 || cj2.o() >= 256) {
            return false;
        }
        \u2603 = adm2.p(cj2.b());
        return \u2603.c().t() == arm.h && \u2603.b(ahv.b) == 0;
    }

    @Override
    public int c(alz alz2) {
        return 0;
    }
}

