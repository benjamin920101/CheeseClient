/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class alt
extends afh {
    public static final aml a = aml.a("facing");
    public static final amm<a> b = amm.a("type", a.class);
    public static final amk N = amk.a("short");

    public alt() {
        super(arm.H);
        this.j(this.M.b().a(a, cq.c).a(b, alt$a.a).a(N, false));
        this.a(i);
        this.c(0.5f);
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, wn wn2) {
        if (wn2.bA.d && (\u2603 = alz2.b(a)) != null && ((\u2603 = adm2.p(\u2603 = cj2.a(\u2603.d())).c()) == afi.J || \u2603 == afi.F)) {
            adm2.g(\u2603);
        }
        super.a(adm2, cj2, alz2, wn2);
    }

    @Override
    public void b(adm adm2, cj \u260322, alz alz2) {
        super.b(adm2, \u260322, alz2);
        cq cq2 = alz2.b(a).d();
        cj \u260322 = \u260322.a(cq2);
        alz \u26033 = adm2.p(\u260322);
        if ((\u26033.c() == afi.J || \u26033.c() == afi.F) && \u26033.b(als.b).booleanValue()) {
            \u26033.c().b(adm2, \u260322, \u26033, 0);
            adm2.g(\u260322);
        }
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
    public boolean d(adm adm2, cj cj2) {
        return false;
    }

    @Override
    public boolean b(adm adm2, cj cj2, cq cq2) {
        return false;
    }

    @Override
    public int a(Random random) {
        return 0;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, aug aug2, List<aug> list, pk pk2) {
        this.d(alz2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.e(alz2);
        super.a(adm2, cj2, alz2, aug2, list, pk2);
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    private void e(alz alz2) {
        float f2 = 0.25f;
        \u2603 = 0.375f;
        \u2603 = 0.625f;
        \u2603 = 0.25f;
        \u2603 = 0.75f;
        switch (alz2.b(a)) {
            case a: {
                this.a(0.375f, 0.25f, 0.375f, 0.625f, 1.0f, 0.625f);
                break;
            }
            case b: {
                this.a(0.375f, 0.0f, 0.375f, 0.625f, 0.75f, 0.625f);
                break;
            }
            case c: {
                this.a(0.25f, 0.375f, 0.25f, 0.75f, 0.625f, 1.0f);
                break;
            }
            case d: {
                this.a(0.25f, 0.375f, 0.0f, 0.75f, 0.625f, 0.75f);
                break;
            }
            case e: {
                this.a(0.375f, 0.25f, 0.25f, 0.625f, 0.75f, 1.0f);
                break;
            }
            case f: {
                this.a(0.0f, 0.375f, 0.25f, 0.75f, 0.625f, 0.75f);
            }
        }
    }

    @Override
    public void a(adq adq2, cj cj2) {
        this.d(adq2.p(cj2));
    }

    public void d(alz alz2) {
        float f2 = 0.25f;
        cq \u26032 = alz2.b(a);
        if (\u26032 == null) {
            return;
        }
        switch (\u26032) {
            case a: {
                this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
                break;
            }
            case b: {
                this.a(0.0f, 0.75f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case c: {
                this.a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.25f);
                break;
            }
            case d: {
                this.a(0.0f, 0.0f, 0.75f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case e: {
                this.a(0.0f, 0.0f, 0.0f, 0.25f, 1.0f, 1.0f);
                break;
            }
            case f: {
                this.a(0.75f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        cq cq2 = alz2.b(a);
        cj \u26032 = cj2.a(cq2.d());
        alz \u26033 = adm2.p(\u26032);
        if (\u26033.c() != afi.J && \u26033.c() != afi.F) {
            adm2.g(cj2);
        } else {
            \u26033.c().a(adm2, \u26032, \u26033, afh2);
        }
    }

    @Override
    public boolean a(adq adq2, cj cj2, cq cq2) {
        return true;
    }

    public static cq b(int n2) {
        \u2603 = n2 & 7;
        if (\u2603 > 5) {
            return null;
        }
        return cq.a(\u2603);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        if (adm2.p(cj2).b(b) == alt$a.b) {
            return zw.a(afi.F);
        }
        return zw.a(afi.J);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, alt.b(n2)).a(b, (n2 & 8) > 0 ? alt$a.b : alt$a.a);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).a();
        if (alz2.b(b) == alt$a.b) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N);
    }

    public static enum a implements nw
    {
        a("normal"),
        b("sticky");

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

