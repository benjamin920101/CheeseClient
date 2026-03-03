/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class afn
extends afh {
    public static final aml a = aml.a("facing");
    public static final amk b = amk.a("powered");
    private final boolean N;

    protected afn(boolean bl2) {
        super(arm.q);
        this.j(this.M.b().a(a, cq.c).a(b, false));
        this.a(true);
        this.a(yz.d);
        this.N = bl2;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
    }

    @Override
    public int a(adm adm2) {
        return this.N ? 30 : 20;
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
    public boolean b(adm adm2, cj cj2, cq cq2) {
        return afn.a(adm2, cj2, cq2.d());
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        for (cq cq2 : cq.values()) {
            if (!afn.a(adm2, cj2, cq2)) continue;
            return true;
        }
        return false;
    }

    protected static boolean a(adm adm2, cj cj2, cq cq2) {
        cj cj3 = cj2.a(cq2);
        if (cq2 == cq.a) {
            return adm.a(adm2, cj3);
        }
        return adm2.p(cj3).c().v();
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        if (afn.a(adm2, cj2, cq2.d())) {
            return this.Q().a(a, cq2).a(b, false);
        }
        return this.Q().a(a, cq.a).a(b, false);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (this.e(adm2, cj2, alz2) && !afn.a(adm2, cj2, alz2.b(a).d())) {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    private boolean e(adm adm2, cj cj2, alz alz2) {
        if (this.d(adm2, cj2)) {
            return true;
        }
        this.b(adm2, cj2, alz2, 0);
        adm2.g(cj2);
        return false;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.d(adq2.p(cj2));
    }

    private void d(alz alz2) {
        cq cq2 = alz2.b(a);
        boolean \u26032 = alz2.b(b);
        float \u26033 = 0.25f;
        float \u26034 = 0.375f;
        float \u26035 = (float)(\u26032 ? 1 : 2) / 16.0f;
        float \u26036 = 0.125f;
        float \u26037 = 0.1875f;
        switch (cq2) {
            case f: {
                this.a(0.0f, 0.375f, 0.3125f, \u26035, 0.625f, 0.6875f);
                break;
            }
            case e: {
                this.a(1.0f - \u26035, 0.375f, 0.3125f, 1.0f, 0.625f, 0.6875f);
                break;
            }
            case d: {
                this.a(0.3125f, 0.375f, 0.0f, 0.6875f, 0.625f, \u26035);
                break;
            }
            case c: {
                this.a(0.3125f, 0.375f, 1.0f - \u26035, 0.6875f, 0.625f, 1.0f);
                break;
            }
            case b: {
                this.a(0.3125f, 0.0f, 0.375f, 0.6875f, 0.0f + \u26035, 0.625f);
                break;
            }
            case a: {
                this.a(0.3125f, 1.0f - \u26035, 0.375f, 0.6875f, 1.0f, 0.625f);
            }
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (alz2.b(b).booleanValue()) {
            return true;
        }
        adm2.a(cj2, alz2.a(b, true), 3);
        adm2.b(cj2, cj2);
        adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.6f);
        this.c(adm2, cj2, alz2.b(a));
        adm2.a(cj2, (afh)this, this.a(adm2));
        return true;
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        if (alz2.b(b).booleanValue()) {
            this.c(adm2, cj2, alz2.b(a));
        }
        super.b(adm2, cj2, alz2);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq2) {
        return alz2.b(b) != false ? 15 : 0;
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (!alz2.b(b).booleanValue()) {
            return 0;
        }
        if (alz2.b(a) == cq2) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, Random random) {
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        if (!alz2.b(b).booleanValue()) {
            return;
        }
        if (this.N) {
            this.f(adm2, cj2, alz2);
        } else {
            adm2.a(cj2, alz2.a(b, false));
            this.c(adm2, cj2, alz2.b(a));
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.5f);
            adm2.b(cj2, cj2);
        }
    }

    @Override
    public void j() {
        float f2 = 0.1875f;
        \u2603 = 0.125f;
        \u2603 = 0.125f;
        this.a(0.5f - f2, 0.5f - \u2603, 0.5f - \u2603, 0.5f + f2, 0.5f + \u2603, 0.5f + \u2603);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pk pk2) {
        if (adm2.D) {
            return;
        }
        if (!this.N) {
            return;
        }
        if (alz2.b(b).booleanValue()) {
            return;
        }
        this.f(adm2, cj2, alz2);
    }

    private void f(adm adm2, cj cj2, alz alz2) {
        this.d(alz2);
        List<wq> list = adm2.a(wq.class, new aug((double)cj2.n() + this.B, (double)cj2.o() + this.C, (double)cj2.p() + this.D, (double)cj2.n() + this.E, (double)cj2.o() + this.F, (double)cj2.p() + this.G));
        boolean \u26032 = !list.isEmpty();
        boolean \u26033 = alz2.b(b);
        if (\u26032 && !\u26033) {
            adm2.a(cj2, alz2.a(b, true));
            this.c(adm2, cj2, alz2.b(a));
            adm2.b(cj2, cj2);
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.6f);
        }
        if (!\u26032 && \u26033) {
            adm2.a(cj2, alz2.a(b, false));
            this.c(adm2, cj2, alz2.b(a));
            adm2.b(cj2, cj2);
            adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, 0.5f);
        }
        if (\u26032) {
            adm2.a(cj2, (afh)this, this.a(adm2));
        }
    }

    private void c(adm adm2, cj cj2, cq cq2) {
        adm2.c(cj2, this);
        adm2.c(cj2.a(cq2.d()), this);
    }

    @Override
    public alz a(int n2) {
        cq cq2;
        switch (n2 & 7) {
            case 0: {
                cq2 = cq.a;
                break;
            }
            case 1: {
                cq2 = cq.f;
                break;
            }
            case 2: {
                cq2 = cq.e;
                break;
            }
            case 3: {
                cq2 = cq.d;
                break;
            }
            case 4: {
                cq2 = cq.c;
                break;
            }
            default: {
                cq2 = cq.b;
            }
        }
        return this.Q().a(a, cq2).a(b, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz22) {
        alz alz22;
        switch (alz22.b(a)) {
            case a: {
                int n2 = 0;
                break;
            }
            case f: {
                int n2 = 1;
                break;
            }
            case e: {
                int n2 = 2;
                break;
            }
            case d: {
                int n2 = 3;
                break;
            }
            case c: {
                int n2 = 4;
                break;
            }
            default: {
                int n2 = 5;
            }
        }
        if (alz22.b(b).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }
}

