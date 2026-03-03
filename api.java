/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class api
extends aot {
    private static final Logger a = LogManager.getLogger();
    private static final String[] b = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};
    private static final List<ob> c = Lists.newArrayList(new ob(zy.aA, 0, 1, 1, 10), new ob(zy.j, 0, 1, 4, 10), new ob(zy.P, 0, 1, 1, 10), new ob(zy.O, 0, 1, 4, 10), new ob(zy.H, 0, 1, 4, 10), new ob(zy.F, 0, 1, 4, 10), new ob(zy.aw, 0, 1, 1, 10), new ob(zy.ao, 0, 1, 1, 1), new ob(zy.aC, 0, 1, 4, 10), new ob(zy.cq, 0, 1, 1, 4), new ob(zy.cr, 0, 1, 1, 4), new ob(zy.co, 0, 1, 1, 10), new ob(zy.cl, 0, 1, 1, 2), new ob(zy.ck, 0, 1, 1, 5), new ob(zy.cm, 0, 1, 1, 1));

    @Override
    public boolean b(adm adm22, Random random, cj cj2) {
        int n2;
        cj cj3;
        int n3 = 3;
        \u2603 = random.nextInt(2) + 2;
        \u2603 = -\u2603 - 1;
        \u2603 = \u2603 + 1;
        \u2603 = -1;
        \u2603 = 4;
        \u2603 = random.nextInt(2) + 2;
        \u2603 = -\u2603 - 1;
        \u2603 = \u2603 + 1;
        \u2603 = 0;
        for (n2 = \u2603; n2 <= \u2603; ++n2) {
            for (\u2603 = -1; \u2603 <= 4; ++\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    cj3 = cj2.a(n2, \u2603, \u2603);
                    arm \u26032 = adm22.p(cj3).c().t();
                    boolean \u26033 = \u26032.a();
                    if (\u2603 == -1 && !\u26033) {
                        return false;
                    }
                    if (\u2603 == 4 && !\u26033) {
                        return false;
                    }
                    if (n2 != \u2603 && n2 != \u2603 && \u2603 != \u2603 && \u2603 != \u2603 || \u2603 != 0 || !adm22.d(cj3) || !adm22.d(cj3.a())) continue;
                    ++\u2603;
                }
            }
        }
        if (\u2603 < 1 || \u2603 > 5) {
            return false;
        }
        for (n2 = \u2603; n2 <= \u2603; ++n2) {
            for (\u2603 = 3; \u2603 >= -1; --\u2603) {
                for (\u2603 = \u2603; \u2603 <= \u2603; ++\u2603) {
                    cj3 = cj2.a(n2, \u2603, \u2603);
                    if (n2 == \u2603 || \u2603 == -1 || \u2603 == \u2603 || n2 == \u2603 || \u2603 == 4 || \u2603 == \u2603) {
                        if (cj3.o() >= 0 && !adm22.p(cj3.b()).c().t().a()) {
                            adm22.g(cj3);
                            continue;
                        }
                        if (!adm22.p(cj3).c().t().a() || adm22.p(cj3).c() == afi.ae) continue;
                        if (\u2603 == -1 && random.nextInt(4) != 0) {
                            adm22.a(cj3, afi.Y.Q(), 2);
                            continue;
                        }
                        adm22.a(cj3, afi.e.Q(), 2);
                        continue;
                    }
                    if (adm22.p(cj3).c() == afi.ae) continue;
                    adm22.g(cj3);
                }
            }
        }
        block6: for (n2 = 0; n2 < 2; ++n2) {
            for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                adm adm22;
                Object \u260362;
                \u2603 = cj2.n() + random.nextInt(\u2603 * 2 + 1) - \u2603;
                cj cj4 = new cj(\u2603, \u2603 = cj2.o(), \u2603 = cj2.p() + random.nextInt(\u2603 * 2 + 1) - \u2603);
                if (!adm22.d(cj4)) continue;
                int \u26034 = 0;
                for (Object \u260362 : cq.c.a) {
                    if (!adm22.p(cj4.a((cq)\u260362)).c().t().a()) continue;
                    ++\u26034;
                }
                if (\u26034 != 1) continue;
                adm22.a(cj4, afi.ae.f(adm22, cj4, afi.ae.Q()), 2);
                List<ob> \u26035 = ob.a(c, zy.cd.b(random));
                \u260362 = adm22.s(cj4);
                if (!(\u260362 instanceof aky)) continue block6;
                ob.a(random, \u26035, (aky)\u260362, 8);
                continue block6;
            }
        }
        adm22.a(cj2, afi.ac.Q(), 2);
        akw akw2 = adm22.s(cj2);
        if (akw2 instanceof all) {
            ((all)akw2).b().a(this.a(random));
        } else {
            a.error("Failed to fetch mob spawner entity at (" + cj2.n() + ", " + cj2.o() + ", " + cj2.p() + ")");
        }
        return true;
    }

    private String a(Random random) {
        return b[random.nextInt(b.length)];
    }
}

