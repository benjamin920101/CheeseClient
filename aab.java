/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import java.util.List;

public class aab
extends yy {
    protected aab() {
        this.a(true);
    }

    public static atg a(int n2, adm adm2) {
        String string = "map_" + n2;
        atg \u26032 = (atg)adm2.a(atg.class, string);
        if (\u26032 == null) {
            \u26032 = new atg(string);
            adm2.a(string, \u26032);
        }
        return \u26032;
    }

    public atg a(zx zx2, adm adm2) {
        String string = "map_" + zx2.i();
        atg \u26032 = (atg)adm2.a(atg.class, string);
        if (\u26032 == null && !adm2.D) {
            zx2.b(adm2.b("map"));
            string = "map_" + zx2.i();
            \u26032 = new atg(string);
            \u26032.e = (byte)3;
            \u26032.a(adm2.P().c(), adm2.P().e(), \u26032.e);
            \u26032.d = (byte)adm2.t.q();
            \u26032.c();
            adm2.a(string, \u26032);
        }
        return \u26032;
    }

    public void a(adm adm2, pk pk2, atg atg2) {
        if (adm2.t.q() != atg2.d || !(pk2 instanceof wn)) {
            return;
        }
        int n2 = 1 << atg2.e;
        \u2603 = atg2.b;
        \u2603 = atg2.c;
        \u2603 = ns.c(pk2.s - (double)\u2603) / n2 + 64;
        \u2603 = ns.c(pk2.u - (double)\u2603) / n2 + 64;
        \u2603 = 128 / n2;
        if (adm2.t.o()) {
            \u2603 /= 2;
        }
        atg.a \u26032 = atg2.a((wn)pk2);
        ++\u26032.b;
        boolean \u26033 = false;
        for (\u2603 = \u2603 - \u2603 + 1; \u2603 < \u2603 + \u2603; ++\u2603) {
            if ((\u2603 & 0xF) != (\u26032.b & 0xF) && !\u26033) continue;
            \u26033 = false;
            double \u260313 = 0.0;
            for (int i2 = \u2603 - \u2603 - 1; i2 < \u2603 + \u2603; ++i2) {
                if (\u2603 < 0 || i2 < -1 || \u2603 >= 128 || i2 >= 128) continue;
                \u2603 = \u2603 - \u2603;
                \u2603 = i2 - \u2603;
                boolean bl2 = \u2603 * \u2603 + \u2603 * \u2603 > (\u2603 - 2) * (\u2603 - 2);
                int \u26034 = (\u2603 / n2 + \u2603 - 64) * n2;
                int \u26035 = (\u2603 / n2 + i2 - 64) * n2;
                HashMultiset<arn> \u26036 = HashMultiset.create();
                amy \u26037 = adm2.f(new cj(\u26034, 0, \u26035));
                if (\u26037.f()) continue;
                int \u26038 = \u26034 & 0xF;
                int \u26039 = \u26035 & 0xF;
                int \u260310 = 0;
                double \u260311 = 0.0;
                if (adm2.t.o()) {
                    int n3 = \u26034 + \u26035 * 231871;
                    if (((n3 = n3 * n3 * 31287121 + n3 * 11) >> 20 & 1) == 0) {
                        \u26036.add(afi.d.g(afi.d.Q().a(agf.a, agf.a.a)), 10);
                    } else {
                        \u26036.add(afi.b.g(afi.b.Q().a(ajy.a, ajy.a.a)), 100);
                    }
                    \u260311 = 100.0;
                } else {
                    cj.a a2 = new cj.a();
                    for (int i3 = 0; i3 < n2; ++i3) {
                        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                            int n4;
                            n4 = \u26037.b(i3 + \u26038, \u2603 + \u26039) + 1;
                            alz alz2 = afi.a.Q();
                            if (n4 > 1) {
                                while ((alz2 = \u26037.g(a2.c(i3 + \u26038, --n4, \u2603 + \u26039))).c().g(alz2) == arn.b && n4 > 0) {
                                }
                                if (n4 > 0 && alz2.c().t().d()) {
                                    int n5 = n4 - 1;
                                    do {
                                        afh afh2 = \u26037.a(i3 + \u26038, n5--, \u2603 + \u26039);
                                        ++\u260310;
                                    } while (n5 > 0 && afh2.t().d());
                                }
                            }
                            \u260311 += (double)n4 / (double)(n2 * n2);
                            \u26036.add(alz2.c().g(alz2));
                        }
                    }
                }
                \u260310 /= n2 * n2;
                double \u260312 = (\u260311 - \u260313) * 4.0 / (double)(n2 + 4) + ((double)(\u2603 + i2 & 1) - 0.5) * 0.4;
                \u2603 = 1;
                if (\u260312 > 0.6) {
                    \u2603 = 2;
                }
                if (\u260312 < -0.6) {
                    \u2603 = 0;
                }
                if ((\u2603 = Iterables.getFirst(Multisets.copyHighestCountFirst(\u26036), arn.b)) == arn.n) {
                    \u260312 = (double)\u260310 * 0.1 + (double)(\u2603 + i2 & 1) * 0.2;
                    \u2603 = 1;
                    if (\u260312 < 0.5) {
                        \u2603 = 2;
                    }
                    if (\u260312 > 0.9) {
                        \u2603 = 0;
                    }
                }
                \u260313 = \u260311;
                if (i2 < 0 || \u2603 * \u2603 + \u2603 * \u2603 >= \u2603 * \u2603 || bl2 && (\u2603 + i2 & 1) == 0 || (\u2603 = atg2.f[\u2603 + i2 * 128]) == (n5 = (int)((byte)(\u2603.M * 4 + \u2603)))) continue;
                atg2.f[\u2603 + i2 * 128] = n5;
                atg2.a(\u2603, i2);
                \u26033 = true;
            }
        }
    }

    @Override
    public void a(zx zx2, adm adm2, pk pk2, int n2, boolean bl22) {
        boolean bl22;
        if (adm2.D) {
            return;
        }
        atg atg2 = this.a(zx2, adm2);
        if (pk2 instanceof wn) {
            wn wn2 = (wn)pk2;
            atg2.a(wn2, zx2);
        }
        if (bl22) {
            this.a(adm2, pk2, atg2);
        }
    }

    @Override
    public ff c(zx zx2, adm adm2, wn wn2) {
        return this.a(zx2, adm2).a(zx2, adm2, wn2);
    }

    @Override
    public void d(zx zx2, adm adm2, wn wn2) {
        if (zx2.n() && zx2.o().n("map_is_scaling")) {
            atg atg2 = zy.bd.a(zx2, adm2);
            zx2.b(adm2.b("map"));
            \u2603 = new atg("map_" + zx2.i());
            \u2603.e = (byte)(atg2.e + 1);
            if (\u2603.e > 4) {
                \u2603.e = (byte)4;
            }
            \u2603.a(atg2.b, atg2.c, \u2603.e);
            \u2603.d = atg2.d;
            \u2603.c();
            adm2.a("map_" + zx2.i(), \u2603);
        }
    }

    @Override
    public void a(zx zx2, wn wn2, List<String> list, boolean bl2) {
        atg atg2 = this.a(zx2, wn2.o);
        if (bl2) {
            if (atg2 == null) {
                list.add("Unknown map");
            } else {
                list.add("Scaling at 1:" + (1 << atg2.e));
                list.add("(Level " + atg2.e + "/" + 4 + ")");
            }
        }
    }
}

