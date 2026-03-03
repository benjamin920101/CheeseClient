/*
 * Decompiled with CFR 0.152.
 */
public class aff
extends afc {
    public aff() {
        super(arm.s, arn.G);
        this.c(3.0f);
        this.a(yz.f);
    }

    @Override
    public akw a(adm adm2, int n2) {
        return new akv();
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, wn wn2, cq cq2, float f2, float f3, float f4) {
        if (adm2.D) {
            return true;
        }
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akv) {
            wn2.a((akv)akw2);
            wn2.b(na.N);
        }
        return true;
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
    public int b() {
        return 3;
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, pr pr2, zx zx2) {
        super.a(adm2, cj2, alz2, pr2, zx2);
        if (zx2.s() && (\u2603 = adm2.s(cj2)) instanceof akv) {
            ((akv)\u2603).a(zx2.q());
        }
    }

    @Override
    public void a(adm adm2, cj cj2, alz alz2, afh afh2) {
        akw akw2 = adm2.s(cj2);
        if (akw2 instanceof akv) {
            ((akv)akw2).m();
            adm2.c(cj2, this, 1, 0);
        }
    }

    @Override
    public adf m() {
        return adf.c;
    }

    public static void f(final adm adm2, final cj cj2) {
        nj.a.submit(new Runnable(){

            @Override
            public void run() {
                amy amy2 = adm2.f(cj2);
                for (int i2 = cj2.o() - 1; i2 >= 0 && amy2.d(\u2603 = new cj(cj2.n(), i2, cj2.p())); --i2) {
                    alz alz2 = adm2.p(\u2603);
                    if (alz2.c() != afi.bY) continue;
                    ((le)adm2).a(new Runnable(){

                        @Override
                        public void run() {
                            akw akw2 = adm2.s(\u2603);
                            if (akw2 instanceof akv) {
                                ((akv)akw2).m();
                                adm2.c(\u2603, afi.bY, 1, 0);
                            }
                        }
                    });
                }
            }
        });
    }
}

