/*
 * Decompiled with CFR 0.152.
 */
public class ahu
extends afh {
    public static final amm<a> a = amm.a("facing", a.class);
    public static final amk b = amk.a("powered");

    protected ahu() {
        super(arm.q);
        this.j(this.M.b().a(a, ahu$a.e).a(b, false));
        this.a(yz.d);
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
    public boolean b(adm adm2, cj cj2, cq cq2) {
        return ahu.a(adm2, cj2, cq2.d());
    }

    @Override
    public boolean d(adm adm2, cj cj2) {
        for (cq cq2 : cq.values()) {
            if (!ahu.a(adm2, cj2, cq2)) continue;
            return true;
        }
        return false;
    }

    protected static boolean a(adm adm2, cj cj2, cq cq2) {
        return afn.a(adm2, cj2, cq2);
    }

    @Override
    public alz a(adm adm22, cj cj2, cq cq2, float f2, float f3, float f4, int n2, pr pr2) {
        adm adm22;
        alz alz2 = this.Q().a(b, false);
        if (ahu.a(adm22, cj2, cq2.d())) {
            return alz2.a(a, ahu$a.a(cq2, pr2.aP()));
        }
        for (cq cq3 : cq.c.a) {
            if (cq3 == cq2 || !ahu.a(adm22, cj2, cq3.d())) continue;
            return alz2.a(a, ahu$a.a(cq3, pr2.aP()));
        }
        if (adm.a(adm22, cj2.b())) {
            return alz2.a(a, ahu$a.a(cq.b, pr2.aP()));
        }
        return alz2;
    }

    public static int a(cq cq2) {
        switch (cq2) {
            case a: {
                return 0;
            }
            case b: {
                return 5;
            }
            case c: {
                return 4;
            }
            case d: {
                return 3;
            }
            case e: {
                return 2;
            }
            case f: {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        if (this.e(adm2, cj2, alz2) && !ahu.a(adm2, cj2, alz2.b(a).c().d())) {
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
        float f2 = 0.1875f;
        switch (adq2.p(cj2).b(a)) {
            case b: {
                this.a(0.0f, 0.2f, 0.5f - f2, f2 * 2.0f, 0.8f, 0.5f + f2);
                break;
            }
            case c: {
                this.a(1.0f - f2 * 2.0f, 0.2f, 0.5f - f2, 1.0f, 0.8f, 0.5f + f2);
                break;
            }
            case d: {
                this.a(0.5f - f2, 0.2f, 0.0f, 0.5f + f2, 0.8f, f2 * 2.0f);
                break;
            }
            case e: {
                this.a(0.5f - f2, 0.2f, 1.0f - f2 * 2.0f, 0.5f + f2, 0.8f, 1.0f);
                break;
            }
            case f: 
            case g: {
                f2 = 0.25f;
                this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, 0.6f, 0.5f + f2);
                break;
            }
            case a: 
            case h: {
                f2 = 0.25f;
                this.a(0.5f - f2, 0.4f, 0.5f - f2, 0.5f + f2, 1.0f, 0.5f + f2);
            }
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz22, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        alz alz22 = alz22.a(b);
        adm2.a(cj2, alz22, 3);
        adm2.a((double)cj2.n() + 0.5, (double)cj2.o() + 0.5, (double)cj2.p() + 0.5, "random.click", 0.3f, alz22.b(b) != false ? 0.6f : 0.5f);
        adm2.c(cj2, this);
        cq \u26032 = alz22.b(a).c();
        adm2.c(cj2.a(\u26032.d()), this);
        return true;
    }

    @Override
    public void b(adm adm22, cj cj2, alz alz2) {
        adm adm22;
        if (alz2.b(b).booleanValue()) {
            adm22.c(cj2, this);
            cq cq2 = alz2.b(a).c();
            adm22.c(cj2.a(cq2.d()), this);
        }
        super.b(adm22, cj2, alz2);
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
        if (alz2.b(a).c() == cq2) {
            return 15;
        }
        return 0;
    }

    @Override
    public boolean i() {
        return true;
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, ahu$a.a(n2 & 7)).a(b, (n2 & 8) > 0);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).a();
        if (alz2.b(b).booleanValue()) {
            n2 |= 8;
        }
        return n2;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }

    public static enum a implements nw
    {
        a(0, "down_x", cq.a),
        b(1, "east", cq.f),
        c(2, "west", cq.e),
        d(3, "south", cq.d),
        e(4, "north", cq.c),
        f(5, "up_z", cq.b),
        g(6, "up_x", cq.b),
        h(7, "down_z", cq.a);

        private static final a[] i;
        private final int j;
        private final String k;
        private final cq l;

        private a(int n3, String string2, cq cq2) {
            this.j = n3;
            this.k = string2;
            this.l = cq2;
        }

        public int a() {
            return this.j;
        }

        public cq c() {
            return this.l;
        }

        public String toString() {
            return this.k;
        }

        public static a a(int n2) {
            if (n2 < 0 || n2 >= i.length) {
                n2 = 0;
            }
            return i[n2];
        }

        public static a a(cq cq2, cq cq3) {
            switch (cq2) {
                case a: {
                    switch (cq3.k()) {
                        case a: {
                            return a;
                        }
                        case c: {
                            return h;
                        }
                    }
                    throw new IllegalArgumentException("Invalid entityFacing " + cq3 + " for facing " + cq2);
                }
                case b: {
                    switch (cq3.k()) {
                        case a: {
                            return g;
                        }
                        case c: {
                            return f;
                        }
                    }
                    throw new IllegalArgumentException("Invalid entityFacing " + cq3 + " for facing " + cq2);
                }
                case c: {
                    return e;
                }
                case d: {
                    return d;
                }
                case e: {
                    return c;
                }
                case f: {
                    return b;
                }
            }
            throw new IllegalArgumentException("Invalid facing: " + cq2);
        }

        @Override
        public String l() {
            return this.k;
        }

        static {
            i = new a[ahu$a.values().length];
            a[] aArray = ahu$a.values();
            int \u26032 = aArray.length;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                ahu$a.i[\u2603.a()] = \u2603 = aArray[i2];
            }
        }
    }
}

