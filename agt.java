/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class agt
extends afh {
    public static final amk a = amk.a("north");
    public static final amk b = amk.a("east");
    public static final amk N = amk.a("south");
    public static final amk O = amk.a("west");

    public agt(arm arm2) {
        this(arm2, arm2.r());
    }

    public agt(arm arm2, arn arn2) {
        super(arm2, arn2);
        this.j(this.M.b().a(a, false).a(b, false).a(N, false).a(O, false));
        this.a(yz.c);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        boolean bl2 = this.e(adm2, cj2.c());
        \u2603 = this.e(adm2, cj2.d());
        \u2603 = this.e(adm2, cj2.e());
        \u2603 = this.e(adm2, cj2.f());
        float \u26032 = 0.375f;
        float \u26033 = 0.625f;
        float \u26034 = 0.375f;
        float \u26035 = 0.625f;
        if (bl2) {
            \u26034 = 0.0f;
        }
        if (\u2603) {
            \u26035 = 1.0f;
        }
        if (bl2 || \u2603) {
            this.a(\u26032, 0.0f, \u26034, \u26033, 1.5f, \u26035);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
        \u26034 = 0.375f;
        \u26035 = 0.625f;
        if (\u2603) {
            \u26032 = 0.0f;
        }
        if (\u2603) {
            \u26033 = 1.0f;
        }
        if (\u2603 || \u2603 || !bl2 && !\u2603) {
            this.a(\u26032, 0.0f, \u26034, \u26033, 1.5f, \u26035);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
        if (bl2) {
            \u26034 = 0.0f;
        }
        if (\u2603) {
            \u26035 = 1.0f;
        }
        this.a(\u26032, 0.0f, \u26034, \u26033, 1.0f, \u26035);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        boolean bl2 = this.e(adq2, cj2.c());
        \u2603 = this.e(adq2, cj2.d());
        \u2603 = this.e(adq2, cj2.e());
        \u2603 = this.e(adq2, cj2.f());
        float \u26032 = 0.375f;
        float \u26033 = 0.625f;
        float \u26034 = 0.375f;
        float \u26035 = 0.625f;
        if (bl2) {
            \u26034 = 0.0f;
        }
        if (\u2603) {
            \u26035 = 1.0f;
        }
        if (\u2603) {
            \u26032 = 0.0f;
        }
        if (\u2603) {
            \u26033 = 1.0f;
        }
        this.a(\u26032, 0.0f, \u26034, \u26033, 1.0f, \u26035);
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
    public boolean b(adq adq2, cj cj2) {
        return false;
    }

    public boolean e(adq adq2, cj cj2) {
        afh afh2 = adq2.p(cj2).c();
        if (afh2 == afi.cv) {
            return false;
        }
        if (afh2 instanceof agt && afh2.J == this.J || afh2 instanceof agu) {
            return true;
        }
        if (afh2.J.k() && afh2.d()) {
            return afh2.J != arm.C;
        }
        return false;
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return true;
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        return zz.a(wn2, adm2, cj2);
    }

    @Override
    public int c(alz alz2) {
        return 0;
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(a, this.e(adq2, cj2.c())).a(b, this.e(adq2, cj2.f())).a(N, this.e(adq2, cj2.d())).a(O, this.e(adq2, cj2.e()));
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, O, N);
    }
}

