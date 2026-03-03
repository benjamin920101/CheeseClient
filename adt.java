/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class adt {
    private static final int a = (int)Math.pow(17.0, 2.0);
    private final Set<adg> b = Sets.newHashSet();

    public int a(le le2, boolean bl2, boolean bl3, boolean bl4) {
        Object \u260322;
        if (!bl2 && !bl3) {
            return 0;
        }
        this.b.clear();
        int n2 = 0;
        for (Object \u260322 : le2.j) {
            if (((wn)\u260322).v()) continue;
            int n3 = ns.c(((wn)\u260322).s / 16.0);
            \u2603 = ns.c(((wn)\u260322).u / 16.0);
            \u2603 = 8;
            for (\u2603 = -\u2603; \u2603 <= \u2603; ++\u2603) {
                for (\u2603 = -\u2603; \u2603 <= \u2603; ++\u2603) {
                    \u2603 = \u2603 == -\u2603 || \u2603 == \u2603 || \u2603 == -\u2603 || \u2603 == \u2603 ? 1 : 0;
                    adg adg2 = new adg(\u2603 + n3, \u2603 + \u2603);
                    if (this.b.contains(adg2)) continue;
                    ++n2;
                    if (\u2603 != 0 || !le2.af().a(adg2)) continue;
                    this.b.add(adg2);
                }
            }
        }
        int n4 = 0;
        \u260322 = le2.M();
        for (pt pt2 : pt.values()) {
            if (pt2.d() && !bl3 || !pt2.d() && !bl2 || pt2.e() && !bl4 || (\u2603 = le2.a(pt2.a())) > (\u2603 = pt2.b() * n2 / a)) continue;
            block6: for (adg adg3 : this.b) {
                cj cj2 = adt.a(le2, adg3.a, adg3.b);
                int \u26033 = cj2.n();
                int \u26034 = cj2.o();
                int \u26035 = cj2.p();
                afh \u26036 = le2.p(cj2).c();
                if (\u26036.v()) continue;
                int \u26037 = 0;
                block7: for (int i2 = 0; i2 < 3; ++i2) {
                    \u2603 = \u26033;
                    \u2603 = \u26034;
                    \u2603 = \u26035;
                    \u2603 = 6;
                    ady.c c2 = null;
                    pu \u26038 = null;
                    for (int i3 = 0; i3 < 4; ++i3) {
                        cj cj3 = new cj(\u2603 += le2.s.nextInt(\u2603) - le2.s.nextInt(\u2603), \u2603 += le2.s.nextInt(1) - le2.s.nextInt(1), \u2603 += le2.s.nextInt(\u2603) - le2.s.nextInt(\u2603));
                        float \u26039 = (float)\u2603 + 0.5f;
                        float \u260310 = (float)\u2603 + 0.5f;
                        if (le2.b(\u26039, \u2603, \u260310, 24.0) || ((df)\u260322).c(\u26039, \u2603, \u260310) < 576.0) continue;
                        if (c2 == null && (c2 = le2.a(pt2, cj3)) == null) continue block7;
                        if (!le2.a(pt2, c2, cj3) || !adt.a(pv.a(c2.b), le2, cj3)) continue;
                        try {
                            ps ps2 = c2.b.getConstructor(adm.class).newInstance(le2);
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                            return n4;
                        }
                        ps2.b(\u26039, \u2603, \u260310, le2.s.nextFloat() * 360.0f, 0.0f);
                        if (ps2.bR() && ps2.bS()) {
                            \u26038 = ps2.a(le2.E(new cj(ps2)), \u26038);
                            if (ps2.bS()) {
                                ++\u26037;
                                le2.d(ps2);
                            }
                            if (\u26037 >= ps2.bV()) continue block6;
                        }
                        n4 += \u26037;
                    }
                }
            }
        }
        return n4;
    }

    protected static cj a(adm adm2, int n2, int n3) {
        amy amy2 = adm2.a(n2, n3);
        int \u26032 = n2 * 16 + adm2.s.nextInt(16);
        int \u26033 = n3 * 16 + adm2.s.nextInt(16);
        int \u26034 = ns.c(amy2.f(new cj(\u26032, 0, \u26033)) + 1, 16);
        int \u26035 = adm2.s.nextInt(\u26034 > 0 ? \u26034 : amy2.g() + 16 - 1);
        return new cj(\u26032, \u26035, \u26033);
    }

    public static boolean a(ps.a a2, adm adm2, cj cj2) {
        if (adm2.af().a(cj2)) {
            afh afh2 = adm2.p(cj2).c();
            if (a2 == ps.a.c) {
                return afh2.t().d() && adm2.p(cj2.b()).c().t().d() && !adm2.p(cj2.a()).c().v();
            }
            cj \u26032 = cj2.b();
            if (!adm.a(adm2, \u26032)) {
                return false;
            }
            \u2603 = adm2.p(\u26032).c();
            boolean \u26033 = \u2603 != afi.h && \u2603 != afi.cv;
            return \u26033 && !afh2.v() && !afh2.t().d() && !adm2.p(cj2.a()).c().v();
        }
        return false;
    }

    public static void a(adm adm2, ady ady2, int n2, int n3, int n4, int n5, Random random2) {
        List<ady.c> list = ady2.a(pt.b);
        if (list.isEmpty()) {
            return;
        }
        while (random2.nextFloat() < ady2.g()) {
            ady.c c2 = oa.a(adm2.s, list);
            int \u26032 = c2.c + random2.nextInt(1 + c2.d - c2.c);
            pu \u26033 = null;
            int \u26034 = n2 + random2.nextInt(n4);
            int \u26035 = n3 + random2.nextInt(n5);
            int \u26036 = \u26034;
            int \u26037 = \u26035;
            for (int i2 = 0; i2 < \u26032; ++i2) {
                boolean bl2 = false;
                for (int i3 = 0; !bl2 && i3 < 4; ++i3) {
                    Random random2;
                    cj cj2 = adm2.r(new cj(\u26034, 0, \u26035));
                    if (adt.a(ps.a.a, adm2, cj2)) {
                        try {
                            ps ps2 = c2.b.getConstructor(adm.class).newInstance(adm2);
                        }
                        catch (Exception exception) {
                            exception.printStackTrace();
                            continue;
                        }
                        ps2.b((float)\u26034 + 0.5f, cj2.o(), (float)\u26035 + 0.5f, random2.nextFloat() * 360.0f, 0.0f);
                        adm2.d(ps2);
                        \u26033 = ps2.a(adm2.E(new cj(ps2)), \u26033);
                        bl2 = true;
                    }
                    \u26034 += random2.nextInt(5) - random2.nextInt(5);
                    \u26035 += random2.nextInt(5) - random2.nextInt(5);
                    while (\u26034 < n2 || \u26034 >= n2 + n4 || \u26035 < n3 || \u26035 >= n3 + n4) {
                        \u26034 = \u26036 + random2.nextInt(5) - random2.nextInt(5);
                        \u26035 = \u26037 + random2.nextInt(5) - random2.nextInt(5);
                    }
                }
            }
        }
    }
}

