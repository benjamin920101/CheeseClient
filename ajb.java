/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

public class ajb
extends afh {
    public static final amm<a> a = amm.a("north", a.class);
    public static final amm<a> b = amm.a("east", a.class);
    public static final amm<a> N = amm.a("south", a.class);
    public static final amm<a> O = amm.a("west", a.class);
    public static final amn P = amn.a("power", 0, 15);
    private boolean Q = true;
    private final Set<cj> R = Sets.newHashSet();

    public ajb() {
        super(arm.q);
        this.j(this.M.b().a(a, ajb$a.c).a(b, ajb$a.c).a(N, ajb$a.c).a(O, ajb$a.c).a(P, 0));
        this.a(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f);
    }

    @Override
    public alz a(alz alz22, adq adq2, cj cj2) {
        alz alz22 = alz22.a(O, this.c(adq2, cj2, cq.e));
        alz22 = alz22.a(b, this.c(adq2, cj2, cq.f));
        alz22 = alz22.a(a, this.c(adq2, cj2, cq.c));
        alz22 = alz22.a(N, this.c(adq2, cj2, cq.d));
        return alz22;
    }

    private a c(adq adq2, cj cj2, cq cq2) {
        cj cj3 = cj2.a(cq2);
        afh \u26032 = adq2.p(cj2.a(cq2)).c();
        if (ajb.a(adq2.p(cj3), cq2) || !\u26032.u() && ajb.d(adq2.p(cj3.b()))) {
            return ajb$a.b;
        }
        afh \u26033 = adq2.p(cj2.a()).c();
        if (!\u26033.u() && \u26032.u() && ajb.d(adq2.p(cj3.a()))) {
            return ajb$a.a;
        }
        return ajb$a.c;
    }

    @Override
    public aug a(adm adm2, cj cj2, alz alz2) {
        return null;
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
    public int a(adq adq2, cj cj2, int n2) {
        alz alz2 = adq2.p(cj2);
        if (alz2.c() != this) {
            return super.a(adq2, cj2, n2);
        }
        return this.b(alz2.b(P));
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        return adm.a(adm2, cj2.b()) || adm2.p(cj2.b()).c() == afi.aX;
    }

    private alz e(adm adm2, cj cj2, alz alz22) {
        alz alz22;
        alz22 = this.a(adm2, cj2, cj2, alz22);
        ArrayList<cj> arrayList = Lists.newArrayList(this.R);
        this.R.clear();
        for (cj cj3 : arrayList) {
            adm2.c(cj3, this);
        }
        return alz22;
    }

    private alz a(adm adm22, cj cj2, cj cj3, alz alz22) {
        alz alz22;
        int \u26032;
        \u2603 = alz22;
        int n2 = \u2603.b(P);
        n3 = 0;
        n3 = this.a(adm22, cj3, n3);
        this.Q = false;
        \u2603 = adm22.A(cj2);
        this.Q = true;
        if (\u2603 > 0 && \u2603 > n3 - 1) {
            n3 = \u2603;
        }
        \u26032 = 0;
        for (cq cq2 : cq.c.a) {
            adm adm22;
            cj cj4 = cj2.a(cq2);
            boolean bl2 = \u2603 = cj4.n() != cj3.n() || cj4.p() != cj3.p();
            if (\u2603) {
                \u26032 = this.a(adm22, cj4, \u26032);
            }
            if (adm22.p(cj4).c().v() && !adm22.p(cj2.a()).c().v()) {
                if (!\u2603 || cj2.o() < cj3.o()) continue;
                \u26032 = this.a(adm22, cj4.a(), \u26032);
                continue;
            }
            if (adm22.p(cj4).c().v() || !\u2603 || cj2.o() > cj3.o()) continue;
            \u26032 = this.a(adm22, cj4.b(), \u26032);
        }
        int n3 = \u26032 > n3 ? \u26032 - 1 : (n3 > 0 ? --n3 : 0);
        if (\u2603 > n3 - 1) {
            n3 = \u2603;
        }
        if (n2 != n3) {
            alz22 = alz22.a(P, n3);
            if (adm22.p(cj2) == \u2603) {
                adm22.a(cj2, alz22, 2);
            }
            this.R.add(cj2);
            for (cq cq3 : cq.values()) {
                this.R.add(cj2.a(cq3));
            }
        }
        return alz22;
    }

    private void e(adm adm2, cj cj2) {
        if (adm2.p(cj2).c() != this) {
            return;
        }
        adm2.c(cj2, this);
        for (cq cq2 : cq.values()) {
            adm2.c(cj2.a(cq2), this);
        }
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2) {
        if (adm2.D) {
            return;
        }
        this.e(adm2, cj2, alz2);
        for (cq cq2 : cq.c.b) {
            adm2.c(cj2.a(cq2), this);
        }
        for (cq cq2 : cq.c.a) {
            this.e(adm2, cj2.a(cq2));
        }
        for (cq cq2 : cq.c.a) {
            cj cj3 = cj2.a(cq2);
            if (adm2.p(cj3).c().v()) {
                this.e(adm2, cj3.a());
                continue;
            }
            this.e(adm2, cj3.b());
        }
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz2) {
        adm adm22;
        super.b(adm22, cj2, alz2);
        if (adm22.D) {
            return;
        }
        for (cq cq2 : cq.values()) {
            adm22.c(cj2.a(cq2), this);
        }
        this.e(adm22, cj2, alz2);
        for (cq cq3 : cq.c.a) {
            this.e(adm22, cj2.a(cq3));
        }
        for (cq cq4 : cq.c.a) {
            cj cj3 = cj2.a(cq4);
            if (adm22.p(cj3).c().v()) {
                this.e(adm22, cj3.a());
                continue;
            }
            this.e(adm22, cj3.b());
        }
    }

    private int a(adm adm2, cj cj2, int n2) {
        if (adm2.p(cj2).c() != this) {
            return n2;
        }
        \u2603 = adm2.p(cj2).b(P);
        if (\u2603 > n2) {
            return \u2603;
        }
        return n2;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (adm2.D) {
            return;
        }
        if (this.d(adm2, cj2)) {
            this.e(adm2, cj2, alz2);
        } else {
            this.b(adm2, cj2, alz2, 0);
            adm2.g(cj2);
        }
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.aC;
    }

    @Override
    public int b(adq adq2, cj cj2, alz alz2, cq cq2) {
        if (!this.Q) {
            return 0;
        }
        return this.a(adq2, cj2, alz2, cq2);
    }

    @Override
    public int a(adq adq2, cj cj2, alz alz2, cq cq22) {
        cq cq22;
        if (!this.Q) {
            return 0;
        }
        int n2 = alz2.b(P);
        if (n2 == 0) {
            return 0;
        }
        if (cq22 == cq.b) {
            return n2;
        }
        EnumSet<cq> \u26032 = EnumSet.noneOf(cq.class);
        for (cq cq3 : cq.c.a) {
            if (!this.d(adq2, cj2, cq3)) continue;
            \u26032.add(cq3);
        }
        if (cq22.k().c() && \u26032.isEmpty()) {
            return n2;
        }
        if (\u26032.contains(cq22) && !\u26032.contains(cq22.f()) && !\u26032.contains(cq22.e())) {
            return n2;
        }
        return 0;
    }

    private boolean d(adq adq2, cj cj2, cq cq2) {
        cj cj3 = cj2.a(cq2);
        alz \u26032 = adq2.p(cj3);
        afh \u26033 = \u26032.c();
        boolean \u26034 = \u26033.v();
        boolean \u26035 = adq2.p(cj2.a()).c().v();
        if (!\u26035 && \u26034 && ajb.e(adq2, cj3.a())) {
            return true;
        }
        if (ajb.a(\u26032, cq2)) {
            return true;
        }
        if (\u26033 == afi.bc && \u26032.b(agd.O) == cq2) {
            return true;
        }
        return !\u26034 && ajb.e(adq2, cj3.b());
    }

    protected static boolean e(adq adq2, cj cj2) {
        return ajb.d(adq2.p(cj2));
    }

    protected static boolean d(alz alz2) {
        return ajb.a(alz2, null);
    }

    protected static boolean a(alz alz2, cq cq2) {
        afh afh2 = alz2.c();
        if (afh2 == afi.af) {
            return true;
        }
        if (afi.bb.e(afh2)) {
            cq cq3 = alz2.b(ajf.O);
            return cq3 == cq2 || cq3.d() == cq2;
        }
        return afh2.i() && cq2 != null;
    }

    @Override
    public boolean i() {
        return this.Q;
    }

    private int b(int n2) {
        float f2 = (float)n2 / 15.0f;
        \u2603 = f2 * 0.6f + 0.4f;
        if (n2 == 0) {
            \u2603 = 0.3f;
        }
        \u2603 = f2 * f2 * 0.7f - 0.5f;
        \u2603 = f2 * f2 * 0.6f - 0.7f;
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        }
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        }
        int \u26032 = ns.a((int)(\u2603 * 255.0f), 0, 255);
        int \u26033 = ns.a((int)(\u2603 * 255.0f), 0, 255);
        int \u26034 = ns.a((int)(\u2603 * 255.0f), 0, 255);
        return 0xFF000000 | \u26032 << 16 | \u26033 << 8 | \u26034;
    }

    @Override
    public void c(adm adm2, cj cj2, alz alz2, Random random) {
        int n2 = alz2.b(P);
        if (n2 == 0) {
            return;
        }
        double \u26032 = (double)cj2.n() + 0.5 + ((double)random.nextFloat() - 0.5) * 0.2;
        double \u26033 = (float)cj2.o() + 0.0625f;
        double \u26034 = (double)cj2.p() + 0.5 + ((double)random.nextFloat() - 0.5) * 0.2;
        float \u26035 = (float)n2 / 15.0f;
        float \u26036 = \u26035 * 0.6f + 0.4f;
        float \u26037 = Math.max(0.0f, \u26035 * \u26035 * 0.7f - 0.5f);
        float \u26038 = Math.max(0.0f, \u26035 * \u26035 * 0.6f - 0.7f);
        adm2.a(cy.E, \u26032, \u26033, \u26034, (double)\u26036, (double)\u26037, (double)\u26038, new int[0]);
    }

    @Override
    public zw c(adm adm2, cj cj2) {
        return zy.aC;
    }

    @Override
    public adf m() {
        return adf.c;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(P, n2);
    }

    @Override
    public int c(alz alz2) {
        return alz2.b(P);
    }

    @Override
    protected ama e() {
        return new ama(this, a, b, N, O, P);
    }

    static enum a implements nw
    {
        a("up"),
        b("side"),
        c("none");

        private final String d;

        private a(String string2) {
            this.d = string2;
        }

        public String toString() {
            return this.l();
        }

        @Override
        public String l() {
            return this.d;
        }
    }
}

