/*
 * Decompiled with CFR 0.152.
 */
public abstract class ahw
extends ajg {
    public static final amm<a> a = amm.a("axis", a.class);

    public ahw() {
        super(arm.d);
        this.a(yz.b);
        this.c(2.0f);
        this.a(f);
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2) {
        int n2 = 4;
        \u2603 = n2 + 1;
        if (!adm2.a(cj2.a(-\u2603, -\u2603, -\u2603), cj2.a(\u2603, \u2603, \u2603))) {
            return;
        }
        for (cj cj3 : cj.a(cj2.a(-n2, -n2, -n2), cj2.a(n2, n2, n2))) {
            alz alz3 = adm2.p(cj3);
            if (alz3.c().t() != arm.j || alz3.b(ahs.b).booleanValue()) continue;
            adm2.a(cj3, alz3.a(ahs.b, true), 4);
        }
    }

    @Override
    public alz a(adm adm2, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        return super.a(adm2, cj2, cq2, f2, f3, f4, n2, pr2).a(a, ahw$a.a(cq2.k()));
    }

    public static enum a implements nw
    {
        a("x"),
        b("y"),
        c("z"),
        d("none");

        private final String e;

        private a(String string2) {
            this.e = string2;
        }

        public String toString() {
            return this.e;
        }

        public static a a(cq.a a2) {
            switch (a2) {
                case a: {
                    return a;
                }
                case b: {
                    return b;
                }
                case c: {
                    return c;
                }
            }
            return d;
        }

        @Override
        public String l() {
            return this.e;
        }
    }
}

