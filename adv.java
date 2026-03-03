/*
 * Decompiled with CFR 0.152.
 */
public class adv
implements adq {
    protected int a;
    protected int b;
    protected amy[][] c;
    protected boolean d;
    protected adm e;

    public adv(adm adm2, cj cj2, cj cj3, int n2) {
        this.e = adm2;
        this.a = cj2.n() - n2 >> 4;
        this.b = cj2.p() - n2 >> 4;
        \u2603 = cj3.n() + n2 >> 4;
        \u2603 = cj3.p() + n2 >> 4;
        this.c = new amy[\u2603 - this.a + 1][\u2603 - this.b + 1];
        this.d = true;
        for (\u2603 = this.a; \u2603 <= \u2603; ++\u2603) {
            for (\u2603 = this.b; \u2603 <= \u2603; ++\u2603) {
                this.c[\u2603 - this.a][\u2603 - this.b] = adm2.a(\u2603, \u2603);
            }
        }
        for (\u2603 = cj2.n() >> 4; \u2603 <= cj3.n() >> 4; ++\u2603) {
            for (\u2603 = cj2.p() >> 4; \u2603 <= cj3.p() >> 4; ++\u2603) {
                amy amy2 = this.c[\u2603 - this.a][\u2603 - this.b];
                if (amy2 == null || amy2.c(cj2.o(), cj3.o())) continue;
                this.d = false;
            }
        }
    }

    @Override
    public boolean W() {
        return this.d;
    }

    @Override
    public akw s(cj cj2) {
        int n2 = (cj2.n() >> 4) - this.a;
        \u2603 = (cj2.p() >> 4) - this.b;
        return this.c[n2][\u2603].a(cj2, amy.a.a);
    }

    @Override
    public int b(cj cj2, int n2) {
        \u2603 = this.a(ads.a, cj2);
        \u2603 = this.a(ads.b, cj2);
        if (\u2603 < n2) {
            \u2603 = n2;
        }
        return \u2603 << 20 | \u2603 << 4;
    }

    @Override
    public alz p(cj cj2) {
        if (cj2.o() >= 0 && cj2.o() < 256) {
            int n2 = (cj2.n() >> 4) - this.a;
            \u2603 = (cj2.p() >> 4) - this.b;
            if (n2 >= 0 && n2 < this.c.length && \u2603 >= 0 && \u2603 < this.c[n2].length && (\u2603 = this.c[n2][\u2603]) != null) {
                return \u2603.g(cj2);
            }
        }
        return afi.a.Q();
    }

    @Override
    public ady b(cj cj2) {
        return this.e.b(cj2);
    }

    private int a(ads ads2, cj cj22) {
        cj cj22;
        if (ads2 == ads.a && this.e.t.o()) {
            return 0;
        }
        if (cj22.o() < 0 || cj22.o() >= 256) {
            return ads2.c;
        }
        if (this.p(cj22).c().s()) {
            int n2 = 0;
            for (cq cq2 : cq.values()) {
                int n3 = this.b(ads2, cj22.a(cq2));
                if (n3 > n2) {
                    n2 = n3;
                }
                if (n2 < 15) continue;
                return n2;
            }
            return n2;
        }
        int \u26032 = (cj22.n() >> 4) - this.a;
        int \u26033 = (cj22.p() >> 4) - this.b;
        return this.c[\u26032][\u26033].a(ads2, cj22);
    }

    @Override
    public boolean d(cj cj2) {
        return this.p(cj2).c().t() == arm.a;
    }

    public int b(ads ads2, cj cj2) {
        if (cj2.o() < 0 || cj2.o() >= 256) {
            return ads2.c;
        }
        int n2 = (cj2.n() >> 4) - this.a;
        \u2603 = (cj2.p() >> 4) - this.b;
        return this.c[n2][\u2603].a(ads2, cj2);
    }

    @Override
    public int a(cj cj2, cq cq2) {
        alz alz2 = this.p(cj2);
        return alz2.c().b(this, cj2, alz2, cq2);
    }

    @Override
    public adr G() {
        return this.e.G();
    }
}

