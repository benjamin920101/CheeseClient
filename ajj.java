/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.Random;

public class ajj
extends afm
implements afj {
    public static final amm<aio.a> a = amm.a("type", aio.a.class);
    public static final amn b = amn.a("stage", 0, 1);

    protected ajj() {
        this.j(this.M.b().a(a, aio.a.a).a(b, 0));
        float f2 = 0.4f;
        this.a(0.5f - f2, 0.0f, 0.5f - f2, 0.5f + f2, f2 * 2.0f, 0.5f + f2);
        this.a(yz.c);
    }

    @Override
    public String f() {
        return di.a(this.a() + "." + aio.a.a.d() + ".name");
    }

    @Override
    public void b(adm adm2, cj cj2, alz alz2, Random random) {
        if (adm2.D) {
            return;
        }
        super.b(adm2, cj2, alz2, random);
        if (adm2.l(cj2.a()) >= 9 && random.nextInt(7) == 0) {
            this.d(adm2, cj2, alz2, random);
        }
    }

    public void d(adm adm2, cj cj2, alz alz2, Random random) {
        if (alz2.b(b) == 0) {
            adm2.a(cj2, alz2.a(b), 4);
        } else {
            this.e(adm2, cj2, alz2, random);
        }
    }

    public void e(adm adm2, cj cj2, alz alz2, Random random2) {
        alz alz3;
        aoh \u26035 = random2.nextInt(10) == 0 ? new aoi(true) : new apv(true);
        int \u26032 = 0;
        int \u26033 = 0;
        boolean \u26034 = false;
        switch (alz2.b(a)) {
            case b: {
                block7: for (\u26032 = 0; \u26032 >= -1; --\u26032) {
                    for (\u26033 = 0; \u26033 >= -1; --\u26033) {
                        if (!this.a(adm2, cj2, \u26032, \u26033, aio.a.b)) continue;
                        \u26035 = new apf(false, random2.nextBoolean());
                        \u26034 = true;
                        break block7;
                    }
                }
                if (\u26034) break;
                \u26033 = 0;
                \u26032 = 0;
                \u26035 = new aps(true);
                break;
            }
            case c: {
                \u26035 = new aoj(true, false);
                break;
            }
            case d: {
                Random random2;
                alz3 = afi.r.Q().a(ail.b, aio.a.d);
                \u2603 = afi.t.Q().a(aik.Q, aio.a.d).a(ahs.b, false);
                block9: for (\u26032 = 0; \u26032 >= -1; --\u26032) {
                    for (\u26033 = 0; \u26033 >= -1; --\u26033) {
                        if (!this.a(adm2, cj2, \u26032, \u26033, aio.a.d)) continue;
                        \u26035 = new ape(true, 10, 20, alz3, \u2603);
                        \u26034 = true;
                        break block9;
                    }
                }
                if (\u26034) break;
                \u26033 = 0;
                \u26032 = 0;
                \u26035 = new apv(true, 4 + random2.nextInt(7), alz3, \u2603, false);
                break;
            }
            case e: {
                \u26035 = new app(true);
                break;
            }
            case f: {
                block11: for (\u26032 = 0; \u26032 >= -1; --\u26032) {
                    for (\u26033 = 0; \u26033 >= -1; --\u26033) {
                        if (!this.a(adm2, cj2, \u26032, \u26033, aio.a.f)) continue;
                        \u26035 = new apn(true);
                        \u26034 = true;
                        break block11;
                    }
                }
                if (\u26034) break;
                return;
            }
        }
        alz3 = afi.a.Q();
        if (\u26034) {
            adm2.a(cj2.a(\u26032, 0, \u26033), alz3, 4);
            adm2.a(cj2.a(\u26032 + 1, 0, \u26033), alz3, 4);
            adm2.a(cj2.a(\u26032, 0, \u26033 + 1), alz3, 4);
            adm2.a(cj2.a(\u26032 + 1, 0, \u26033 + 1), alz3, 4);
        } else {
            adm2.a(cj2, alz3, 4);
        }
        if (!((aot)\u26035).b(adm2, random2, cj2.a(\u26032, 0, \u26033))) {
            if (\u26034) {
                adm2.a(cj2.a(\u26032, 0, \u26033), alz2, 4);
                adm2.a(cj2.a(\u26032 + 1, 0, \u26033), alz2, 4);
                adm2.a(cj2.a(\u26032, 0, \u26033 + 1), alz2, 4);
                adm2.a(cj2.a(\u26032 + 1, 0, \u26033 + 1), alz2, 4);
            } else {
                adm2.a(cj2, alz2, 4);
            }
        }
    }

    private boolean a(adm adm2, cj cj2, int n2, int n3, aio.a a2) {
        return this.a(adm2, cj2.a(n2, 0, n3), a2) && this.a(adm2, cj2.a(n2 + 1, 0, n3), a2) && this.a(adm2, cj2.a(n2, 0, n3 + 1), a2) && this.a(adm2, cj2.a(n2 + 1, 0, n3 + 1), a2);
    }

    public boolean a(adm adm2, cj cj2, aio.a a2) {
        alz alz2 = adm2.p(cj2);
        return alz2.c() == this && alz2.b(a) == a2;
    }

    @Override
    public int a(alz alz2) {
        return alz2.b(a).a();
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        for (aio.a a2 : aio.a.values()) {
            list.add(new zx(zw2, 1, a2.a()));
        }
    }

    @Override
    public boolean a(adm adm2, cj cj2, alz alz2, boolean bl2) {
        return true;
    }

    @Override
    public boolean a(adm adm2, Random random, cj cj2, alz alz2) {
        return (double)adm2.s.nextFloat() < 0.45;
    }

    @Override
    public void b(adm adm2, Random random, cj cj2, alz alz2) {
        this.d(adm2, cj2, alz2, random);
    }

    @Override
    public alz a(int n2) {
        return this.Q().a(a, aio.a.a(n2 & 7)).a(b, (n2 & 8) >> 3);
    }

    @Override
    public int c(alz alz2) {
        int n2 = 0;
        n2 |= alz2.b(a).a();
        return n2 |= alz2.b(b) << 3;
    }

    @Override
    protected ama e() {
        return new ama(this, a, b);
    }
}

