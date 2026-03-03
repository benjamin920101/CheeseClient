/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class akd
extends afh {
    public static final amk b = amk.a("north");
    public static final amk N = amk.a("east");
    public static final amk O = amk.a("south");
    public static final amk P = amk.a("west");
    private final boolean a;

    protected akd(arm arm2, boolean bl2) {
        super(arm2);
        this.j(this.M.b().a(b, false).a(N, false).a(O, false).a(P, false));
        this.a = bl2;
        this.a(yz.c);
    }

    @Override
    public alz a(alz alz2, adq adq2, cj cj2) {
        return alz2.a(b, this.c(adq2.p(cj2.c()).c())).a(O, this.c(adq2.p(cj2.d()).c())).a(P, this.c(adq2.p(cj2.e()).c())).a(N, this.c(adq2.p(cj2.f()).c()));
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (!this.a) {
            return null;
        }
        return super.a(alz2, random, n2);
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
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (adq2.p(cj2).c() == this) {
            return false;
        }
        return super.a(adq2, cj2, cq2);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        boolean bl2 = this.c(adm2.p(cj2.c()).c());
        \u2603 = this.c(adm2.p(cj2.d()).c());
        \u2603 = this.c(adm2.p(cj2.e()).c());
        \u2603 = this.c(adm2.p(cj2.f()).c());
        if (\u2603 && \u2603 || !\u2603 && !\u2603 && !bl2 && !\u2603) {
            this.a(0.0f, 0.0f, 0.4375f, 1.0f, 1.0f, 0.5625f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        } else if (\u2603) {
            this.a(0.0f, 0.0f, 0.4375f, 0.5f, 1.0f, 0.5625f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        } else if (\u2603) {
            this.a(0.5f, 0.0f, 0.4375f, 1.0f, 1.0f, 0.5625f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
        if (bl2 && \u2603 || !\u2603 && !\u2603 && !bl2 && !\u2603) {
            this.a(0.4375f, 0.0f, 0.0f, 0.5625f, 1.0f, 1.0f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        } else if (bl2) {
            this.a(0.4375f, 0.0f, 0.0f, 0.5625f, 1.0f, 0.5f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        } else if (\u2603) {
            this.a(0.4375f, 0.0f, 0.5f, 0.5625f, 1.0f, 1.0f);
            super.a(adm2, cj2, alz2, aug2, list, pk2);
        }
    }

    @Override
    public void j() {
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void a(adq adq2, cj cj2) {
        float f2 = 0.4375f;
        \u2603 = 0.5625f;
        \u2603 = 0.4375f;
        \u2603 = 0.5625f;
        boolean \u26032 = this.c(adq2.p(cj2.c()).c());
        boolean \u26033 = this.c(adq2.p(cj2.d()).c());
        boolean \u26034 = this.c(adq2.p(cj2.e()).c());
        boolean \u26035 = this.c(adq2.p(cj2.f()).c());
        if (\u26034 && \u26035 || !\u26034 && !\u26035 && !\u26032 && !\u26033) {
            f2 = 0.0f;
            \u2603 = 1.0f;
        } else if (\u26034) {
            f2 = 0.0f;
        } else if (\u26035) {
            \u2603 = 1.0f;
        }
        if (\u26032 && \u26033 || !\u26034 && !\u26035 && !\u26032 && !\u26033) {
            \u2603 = 0.0f;
            \u2603 = 1.0f;
        } else if (\u26032) {
            \u2603 = 0.0f;
        } else if (\u26033) {
            \u2603 = 1.0f;
        }
        this.a(f2, 0.0f, \u2603, \u2603, 1.0f, \u2603);
    }

    public final boolean c(afh afh2) {
        return afh2.o() || afh2 == this || afh2 == afi.w || afh2 == afi.cG || afh2 == afi.cH || afh2 instanceof akd;
    }

    @Override
    protected boolean I() {
        return true;
    }

    @Override
    public adf m() {
        return adf.b;
    }

    @Override
    public int c(alz alz2) {
        return 0;
    }

    @Override
    protected ama e() {
        return new ama(this, b, N, P, O);
    }
}

