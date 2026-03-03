/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public abstract class ahh
extends afh {
    public static final amm<a> a = amm.a("half", a.class);

    public ahh(arm arm2) {
        super(arm2);
        if (this.l()) {
            this.r = true;
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        }
        this.e(255);
    }

    @Override
    protected boolean I() {
        return false;
    }

    @Override
    public void a(adq adq2, cj cj2) {
        if (this.l()) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            return;
        }
        alz alz2 = adq2.p(cj2);
        if (alz2.c() == this) {
            if (alz2.b(a) == ahh$a.a) {
                this.a(0.0f, 0.5f, 0.0f, 1.0f, 1.0f, 1.0f);
            } else {
                this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
            }
        }
    }

    @Override
    public void j() {
        if (this.l()) {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else {
            this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.a((adq)adm2, cj2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
    }

    @Override
    public boolean c() {
        return this.l();
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        alz alz2 = super.a(adm2, cj2, cq2, f2, f3, f4, n2, pr2).a(a, ahh$a.b);
        if (this.l()) {
            return alz2;
        }
        if (cq2 == cq.a || cq2 != cq.b && (double)f3 > 0.5) {
            return alz2.a(a, ahh$a.a);
        }
        return alz2;
    }

    @Override
    public int a(Random random) {
        if (this.l()) {
            return 2;
        }
        return 1;
    }

    @Override
    public boolean d() {
        return this.l();
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        if (this.l()) {
            return super.a(adq2, cj2, cq2);
        }
        if (cq2 != cq.b && cq2 != cq.a && !super.a(adq2, cj2, cq2)) {
            return false;
        }
        cj cj3 = cj2.a(cq2.d());
        alz \u26032 = adq2.p(cj2);
        alz \u26033 = adq2.p(cj3);
        boolean \u26034 = ahh.c(\u26032.c()) && \u26032.b(a) == ahh$a.a;
        boolean bl2 = \u2603 = ahh.c(\u26033.c()) && \u26033.b(a) == ahh$a.a;
        if (\u2603) {
            if (cq2 == cq.a) {
                return true;
            }
            if (cq2 == cq.b && super.a(adq2, cj2, cq2)) {
                return true;
            }
            return !ahh.c(\u26032.c()) || !\u26034;
        }
        if (cq2 == cq.b) {
            return true;
        }
        if (cq2 == cq.a && super.a(adq2, cj2, cq2)) {
            return true;
        }
        return !ahh.c(\u26032.c()) || \u26034;
    }

    protected static boolean c(afh afh2) {
        return afh2 == afi.U || afh2 == afi.bM || afh2 == afi.cP;
    }

    public abstract String b(int var1);

    @Override
    public int j(adm adm2, cj cj2) {
        return super.j(adm2, cj2) & 7;
    }

    public abstract boolean l();

    public abstract amo<?> n();

    public abstract Object a(zx var1);

    public static enum a implements nw
    {
        a("top"),
        b("bottom");

        private final String c;

        private a(String string2) {
            this.c = string2;
        }

        public String toString() {
            return this.c;
        }

        @Override
        public String l() {
            return this.c;
        }
    }
}

