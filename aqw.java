/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class aqw {
    public static void a() {
        aqr.a(a.class, "ViBH");
        aqr.a(b.class, "ViDF");
        aqr.a(c.class, "ViF");
        aqr.a(d.class, "ViL");
        aqr.a(f.class, "ViPH");
        aqr.a(g.class, "ViSH");
        aqr.a(h.class, "ViSmH");
        aqr.a(i.class, "ViST");
        aqr.a(j.class, "ViS");
        aqr.a(k.class, "ViStart");
        aqr.a(l.class, "ViSR");
        aqr.a(m.class, "ViTRH");
        aqr.a(p.class, "ViW");
    }

    public static List<e> a(Random random, int n2) {
        ArrayList<e> arrayList = Lists.newArrayList();
        arrayList.add(new e(g.class, 4, ns.a(random, 2 + n2, 4 + n2 * 2)));
        arrayList.add(new e(i.class, 20, ns.a(random, 0 + n2, 1 + n2)));
        arrayList.add(new e(a.class, 20, ns.a(random, 0 + n2, 2 + n2)));
        arrayList.add(new e(h.class, 3, ns.a(random, 2 + n2, 5 + n2 * 3)));
        arrayList.add(new e(f.class, 15, ns.a(random, 0 + n2, 2 + n2)));
        arrayList.add(new e(b.class, 3, ns.a(random, 1 + n2, 4 + n2)));
        arrayList.add(new e(c.class, 3, ns.a(random, 2 + n2, 4 + n2 * 2)));
        arrayList.add(new e(j.class, 15, ns.a(random, 0, 1 + n2)));
        arrayList.add(new e(m.class, 8, ns.a(random, 0 + n2, 3 + n2 * 2)));
        Iterator \u26032 = arrayList.iterator();
        while (\u26032.hasNext()) {
            if (((e)\u26032.next()).d != 0) continue;
            \u26032.remove();
        }
        return arrayList;
    }

    private static int a(List<e> list) {
        boolean bl2 = false;
        int \u26032 = 0;
        for (e e22 : list) {
            e e22;
            if (e22.d > 0 && e22.c < e22.d) {
                bl2 = true;
            }
            \u26032 += e22.b;
        }
        return bl2 ? \u26032 : -1;
    }

    private static n a(k k2, e e2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        Class<? extends n> clazz = e2.a;
        n \u26032 = null;
        if (clazz == g.class) {
            \u26032 = g.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == i.class) {
            \u26032 = i.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == a.class) {
            \u26032 = a.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == h.class) {
            \u26032 = h.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == f.class) {
            \u26032 = f.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == b.class) {
            \u26032 = b.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == c.class) {
            \u26032 = c.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == j.class) {
            \u26032 = j.a(k2, list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == m.class) {
            \u26032 = m.a(k2, list, random, n2, n3, n4, cq2, n5);
        }
        return \u26032;
    }

    private static n c(k k22, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        k k22;
        \u2603 = aqw.a(k22.e);
        if (\u2603 <= 0) {
            return null;
        }
        \u2603 = 0;
        block0: while (\u2603 < 5) {
            ++\u2603;
            \u2603 = random.nextInt(\u2603);
            for (e e2 : k22.e) {
                if ((\u2603 -= e2.b) >= 0) continue;
                if (!e2.a(n5) || e2 == k22.d && k22.e.size() > 1) continue block0;
                n n6 = aqw.a(k22, e2, list, random, n2, n3, n4, cq2, n5);
                if (n6 == null) continue;
                ++e2.c;
                k22.d = e2;
                if (!e2.a()) {
                    k22.e.remove(e2);
                }
                return n6;
            }
        }
        aqe \u26032 = d.a(k22, list, random, n2, n3, n4, cq2);
        if (\u26032 != null) {
            return new d(k22, n5, random, \u26032, cq2);
        }
        return null;
    }

    private static aqt d(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        if (n5 > 50) {
            return null;
        }
        if (Math.abs(n2 - k2.c().a) > 112 || Math.abs(n4 - k2.c().c) > 112) {
            return null;
        }
        n n6 = aqw.c(k2, list, random, n2, n3, n4, cq2, n5 + 1);
        if (n6 != null) {
            int n7 = (n6.l.a + n6.l.d) / 2;
            \u2603 = (n6.l.c + n6.l.f) / 2;
            \u2603 = n6.l.d - n6.l.a;
            \u2603 = n6.l.f - n6.l.c;
            int n8 = \u2603 = \u2603 > \u2603 ? \u2603 : \u2603;
            if (k2.e().a(n7, \u2603, \u2603 / 2 + 4, aqv.d)) {
                list.add(n6);
                k2.f.add(n6);
                return n6;
            }
        }
        return null;
    }

    private static aqt e(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        if (n5 > 3 + k2.c) {
            return null;
        }
        if (Math.abs(n2 - k2.c().a) > 112 || Math.abs(n4 - k2.c().c) > 112) {
            return null;
        }
        aqe aqe2 = l.a(k2, list, random, n2, n3, n4, cq2);
        if (aqe2 != null && aqe2.b > 10) {
            l l2 = new l(k2, n5, random, aqe2, cq2);
            int \u26032 = (l2.l.a + l2.l.d) / 2;
            int \u26033 = (l2.l.c + l2.l.f) / 2;
            int \u26034 = l2.l.d - l2.l.a;
            int \u26035 = l2.l.f - l2.l.c;
            int n6 = \u2603 = \u26034 > \u26035 ? \u26034 : \u26035;
            if (k2.e().a(\u26032, \u26033, \u2603 / 2 + 4, aqv.d)) {
                list.add(l2);
                k2.g.add(l2);
                return l2;
            }
        }
        return null;
    }

    public static class d
    extends n {
        public d() {
        }

        public d(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static aqe a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 3, 4, 2, cq2);
            if (aqt.a(list, aqe2) != null) {
                return null;
            }
            return aqe2;
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 4 - 1, 0);
            }
            this.a(adm2, aqe2, 0, 0, 0, 2, 3, 1, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, afi.aO.Q(), 1, 0, 0, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 1, 0, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 2, 0, aqe2);
            this.a(adm2, afi.L.a(zd.a.b()), 1, 3, 0, aqe2);
            boolean bl2 = this.m == cq.f || this.m == cq.c;
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.e()), bl2 ? 2 : 0, 3, 0, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m), 1, 3, 1, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.f()), bl2 ? 0 : 2, 3, 0, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.d()), 1, 3, -1, aqe2);
            return true;
        }
    }

    public static class b
    extends n {
        private afh a;
        private afh b;
        private afh c;
        private afh d;

        public b() {
        }

        public b(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = this.a(random);
            this.b = this.a(random);
            this.c = this.a(random);
            this.d = this.a(random);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("CA", afh.c.b(this.a));
            dn2.a("CB", afh.c.b(this.b));
            dn2.a("CC", afh.c.b(this.c));
            dn2.a("CD", afh.c.b(this.d));
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = afh.c(dn2.f("CA"));
            this.b = afh.c(dn2.f("CB"));
            this.c = afh.c(dn2.f("CC"));
            this.d = afh.c(dn2.f("CD"));
        }

        private afh a(Random random) {
            switch (random.nextInt(5)) {
                default: {
                    return afi.aj;
                }
                case 0: {
                    return afi.cb;
                }
                case 1: 
            }
            return afi.cc;
        }

        public static b a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 13, 4, 9, cq2);
            if (!aqw$b.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new b(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2;
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 4 - 1, 0);
            }
            this.a(adm2, aqe2, 0, 1, 0, 12, 4, 8, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 0, 1, 2, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 4, 0, 1, 5, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 7, 0, 1, 8, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 10, 0, 1, 11, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 0, 0, 0, 0, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 6, 0, 0, 6, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 12, 0, 0, 12, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 11, 0, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 1, 0, 8, 11, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 3, 0, 1, 3, 0, 7, afi.j.Q(), afi.j.Q(), false);
            this.a(adm2, aqe2, 9, 0, 1, 9, 0, 7, afi.j.Q(), afi.j.Q(), false);
            for (n2 = 1; n2 <= 7; ++n2) {
                this.a(adm2, this.a.a(ns.a(random, 2, 7)), 1, 1, n2, aqe2);
                this.a(adm2, this.a.a(ns.a(random, 2, 7)), 2, 1, n2, aqe2);
                this.a(adm2, this.b.a(ns.a(random, 2, 7)), 4, 1, n2, aqe2);
                this.a(adm2, this.b.a(ns.a(random, 2, 7)), 5, 1, n2, aqe2);
                this.a(adm2, this.c.a(ns.a(random, 2, 7)), 7, 1, n2, aqe2);
                this.a(adm2, this.c.a(ns.a(random, 2, 7)), 8, 1, n2, aqe2);
                this.a(adm2, this.d.a(ns.a(random, 2, 7)), 10, 1, n2, aqe2);
                this.a(adm2, this.d.a(ns.a(random, 2, 7)), 11, 1, n2, aqe2);
            }
            for (n2 = 0; n2 < 9; ++n2) {
                for (\u2603 = 0; \u2603 < 13; ++\u2603) {
                    this.b(adm2, \u2603, 4, n2, aqe2);
                    this.b(adm2, afi.d.Q(), \u2603, -1, n2, aqe2);
                }
            }
            return true;
        }
    }

    public static class c
    extends n {
        private afh a;
        private afh b;

        public c() {
        }

        public c(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = this.a(random);
            this.b = this.a(random);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("CA", afh.c.b(this.a));
            dn2.a("CB", afh.c.b(this.b));
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = afh.c(dn2.f("CA"));
            this.b = afh.c(dn2.f("CB"));
        }

        private afh a(Random random) {
            switch (random.nextInt(5)) {
                default: {
                    return afi.aj;
                }
                case 0: {
                    return afi.cb;
                }
                case 1: 
            }
            return afi.cc;
        }

        public static c a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 7, 4, 9, cq2);
            if (!c.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new c(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2;
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 4 - 1, 0);
            }
            this.a(adm2, aqe2, 0, 1, 0, 6, 4, 8, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 0, 1, 2, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 4, 0, 1, 5, 0, 7, afi.ak.Q(), afi.ak.Q(), false);
            this.a(adm2, aqe2, 0, 0, 0, 0, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 6, 0, 0, 6, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 5, 0, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 1, 0, 8, 5, 0, 8, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 3, 0, 1, 3, 0, 7, afi.j.Q(), afi.j.Q(), false);
            for (n2 = 1; n2 <= 7; ++n2) {
                this.a(adm2, this.a.a(ns.a(random, 2, 7)), 1, 1, n2, aqe2);
                this.a(adm2, this.a.a(ns.a(random, 2, 7)), 2, 1, n2, aqe2);
                this.a(adm2, this.b.a(ns.a(random, 2, 7)), 4, 1, n2, aqe2);
                this.a(adm2, this.b.a(ns.a(random, 2, 7)), 5, 1, n2, aqe2);
            }
            for (n2 = 0; n2 < 9; ++n2) {
                for (\u2603 = 0; \u2603 < 7; ++\u2603) {
                    this.b(adm2, \u2603, 4, n2, aqe2);
                    this.b(adm2, afi.d.Q(), \u2603, -1, n2, aqe2);
                }
            }
            return true;
        }
    }

    public static class j
    extends n {
        private static final List<ob> a = Lists.newArrayList(new ob(zy.i, 0, 1, 3, 3), new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 1, 3, 5), new ob(zy.P, 0, 1, 3, 15), new ob(zy.e, 0, 1, 3, 15), new ob(zy.b, 0, 1, 1, 5), new ob(zy.l, 0, 1, 1, 5), new ob(zy.Z, 0, 1, 1, 5), new ob(zy.Y, 0, 1, 1, 5), new ob(zy.aa, 0, 1, 1, 5), new ob(zy.ab, 0, 1, 1, 5), new ob(zw.a(afi.Z), 0, 3, 7, 5), new ob(zw.a(afi.g), 0, 3, 7, 5), new ob(zy.aA, 0, 1, 1, 3), new ob(zy.ck, 0, 1, 1, 1), new ob(zy.cl, 0, 1, 1, 1), new ob(zy.cm, 0, 1, 1, 1));
        private boolean b;

        public j() {
        }

        public j(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static j a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 10, 6, 7, cq2);
            if (!j.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new j(k2, n5, random, aqe2, cq2);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Chest", this.b);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.n("Chest");
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int n2;
            if (this.h < 0) {
                this.h = this.b(adm22, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 6 - 1, 0);
            }
            this.a(adm22, aqe2, 0, 1, 0, 9, 4, 6, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 0, 0, 9, 0, 6, afi.e.Q(), afi.e.Q(), false);
            this.a(adm22, aqe2, 0, 4, 0, 9, 4, 6, afi.e.Q(), afi.e.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 9, 5, 6, afi.U.Q(), afi.U.Q(), false);
            this.a(adm22, aqe2, 1, 5, 1, 8, 5, 5, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 1, 1, 0, 2, 3, 0, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 0, 1, 0, 0, 4, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 3, 1, 0, 3, 4, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 0, 1, 6, 0, 4, 6, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, afi.f.Q(), 3, 3, 1, aqe2);
            this.a(adm22, aqe2, 3, 1, 2, 3, 3, 2, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 4, 1, 3, 5, 3, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 0, 1, 1, 0, 3, 5, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 1, 1, 6, 5, 3, 6, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 5, 1, 0, 5, 3, 0, afi.aO.Q(), afi.aO.Q(), false);
            this.a(adm22, aqe2, 9, 1, 0, 9, 3, 0, afi.aO.Q(), afi.aO.Q(), false);
            this.a(adm22, aqe2, 6, 1, 4, 9, 4, 6, afi.e.Q(), afi.e.Q(), false);
            this.a(adm22, afi.k.Q(), 7, 1, 5, aqe2);
            this.a(adm22, afi.k.Q(), 8, 1, 5, aqe2);
            this.a(adm22, afi.bi.Q(), 9, 2, 5, aqe2);
            this.a(adm22, afi.bi.Q(), 9, 2, 4, aqe2);
            this.a(adm22, aqe2, 7, 2, 4, 8, 2, 5, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, afi.e.Q(), 6, 1, 3, aqe2);
            this.a(adm22, afi.al.Q(), 6, 2, 3, aqe2);
            this.a(adm22, afi.al.Q(), 6, 3, 3, aqe2);
            this.a(adm22, afi.T.Q(), 8, 1, 1, aqe2);
            this.a(adm22, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm22, afi.bj.Q(), 0, 2, 4, aqe2);
            this.a(adm22, afi.bj.Q(), 2, 2, 6, aqe2);
            this.a(adm22, afi.bj.Q(), 4, 2, 6, aqe2);
            this.a(adm22, afi.aO.Q(), 2, 1, 4, aqe2);
            this.a(adm22, afi.aB.Q(), 2, 2, 4, aqe2);
            this.a(adm22, afi.f.Q(), 1, 1, 5, aqe2);
            this.a(adm22, afi.ad.a(this.a(afi.ad, 3)), 2, 1, 5, aqe2);
            this.a(adm22, afi.ad.a(this.a(afi.ad, 1)), 1, 1, 4, aqe2);
            if (!this.b && aqe2.b(new cj(this.a(5, 5), this.d(1), this.b(5, 5)))) {
                this.b = true;
                this.a(adm22, aqe2, random, 5, 1, 5, a, 3 + random.nextInt(6));
            }
            for (n2 = 6; n2 <= 8; ++n2) {
                if (this.a(adm22, n2, 0, -1, aqe2).c().t() != arm.a || this.a(adm22, n2, -1, -1, aqe2).c().t() == arm.a) continue;
                this.a(adm22, afi.aw.a(this.a(afi.aw, 3)), n2, 0, -1, aqe2);
            }
            for (n2 = 0; n2 < 7; ++n2) {
                for (\u2603 = 0; \u2603 < 10; ++\u2603) {
                    this.b(adm22, \u2603, 6, n2, aqe2);
                    this.b(adm22, afi.e.Q(), \u2603, -1, n2, aqe2);
                }
            }
            this.a(adm22, aqe2, 7, 1, 1, 1);
            return true;
        }

        @Override
        protected int c(int n2, int n3) {
            return 3;
        }
    }

    public static class m
    extends n {
        public m() {
        }

        public m(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static m a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 9, 7, 12, cq2);
            if (!m.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new m(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 7 - 1, 0);
            }
            this.a(adm2, aqe2, 1, 1, 1, 7, 4, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 2, 1, 6, 8, 4, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 2, 0, 5, 8, 0, 10, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 0, 1, 7, 0, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 0, 0, 0, 3, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 8, 0, 0, 8, 3, 10, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 7, 2, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 0, 5, 2, 1, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 2, 0, 6, 2, 3, 10, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 3, 0, 10, 7, 3, 10, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 2, 0, 7, 3, 0, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 2, 5, 2, 3, 5, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 4, 1, 8, 4, 1, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 4, 4, 3, 4, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 5, 2, 8, 5, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, afi.f.Q(), 0, 4, 2, aqe2);
            this.a(adm2, afi.f.Q(), 0, 4, 3, aqe2);
            this.a(adm2, afi.f.Q(), 8, 4, 2, aqe2);
            this.a(adm2, afi.f.Q(), 8, 4, 3, aqe2);
            this.a(adm2, afi.f.Q(), 8, 4, 4, aqe2);
            int n2 = this.a(afi.ad, 3);
            \u2603 = this.a(afi.ad, 2);
            for (\u2603 = -1; \u2603 <= 2; ++\u2603) {
                for (\u2603 = 0; \u2603 <= 8; ++\u2603) {
                    this.a(adm2, afi.ad.a(n2), \u2603, 4 + \u2603, \u2603, aqe2);
                    if (\u2603 <= -1 && \u2603 > 1 || \u2603 <= 0 && \u2603 > 3 || \u2603 <= 1 && \u2603 > 4 && \u2603 < 6) continue;
                    this.a(adm2, afi.ad.a(\u2603), \u2603, 4 + \u2603, 5 - \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 3, 4, 5, 3, 4, 10, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 7, 4, 2, 7, 4, 10, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 4, 5, 4, 4, 5, 10, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 6, 5, 4, 6, 5, 10, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 5, 6, 3, 5, 6, 10, afi.f.Q(), afi.f.Q(), false);
            \u2603 = this.a(afi.ad, 0);
            for (\u2603 = 4; \u2603 >= 1; --\u2603) {
                this.a(adm2, afi.f.Q(), \u2603, 2 + \u2603, 7 - \u2603, aqe2);
                for (\u2603 = 8 - \u2603; \u2603 <= 10; ++\u2603) {
                    this.a(adm2, afi.ad.a(\u2603), \u2603, 2 + \u2603, \u2603, aqe2);
                }
            }
            \u2603 = this.a(afi.ad, 1);
            this.a(adm2, afi.f.Q(), 6, 6, 3, aqe2);
            this.a(adm2, afi.f.Q(), 7, 5, 4, aqe2);
            this.a(adm2, afi.ad.a(\u2603), 6, 6, 4, aqe2);
            for (\u2603 = 6; \u2603 <= 8; ++\u2603) {
                for (\u2603 = 5; \u2603 <= 10; ++\u2603) {
                    this.a(adm2, afi.ad.a(\u2603), \u2603, 12 - \u2603, \u2603, aqe2);
                }
            }
            this.a(adm2, afi.r.Q(), 0, 2, 1, aqe2);
            this.a(adm2, afi.r.Q(), 0, 2, 4, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 3, aqe2);
            this.a(adm2, afi.r.Q(), 4, 2, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 2, 0, aqe2);
            this.a(adm2, afi.r.Q(), 6, 2, 0, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 1, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 3, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 4, aqe2);
            this.a(adm2, afi.f.Q(), 8, 2, 5, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 6, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 7, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 8, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 9, aqe2);
            this.a(adm2, afi.r.Q(), 2, 2, 6, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 2, 7, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 2, 8, aqe2);
            this.a(adm2, afi.r.Q(), 2, 2, 9, aqe2);
            this.a(adm2, afi.r.Q(), 4, 4, 10, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 4, 10, aqe2);
            this.a(adm2, afi.r.Q(), 6, 4, 10, aqe2);
            this.a(adm2, afi.f.Q(), 5, 5, 10, aqe2);
            this.a(adm2, afi.a.Q(), 2, 1, 0, aqe2);
            this.a(adm2, afi.a.Q(), 2, 2, 0, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m), 2, 3, 1, aqe2);
            this.a(adm2, aqe2, random, 2, 1, 0, cq.b(this.a(afi.ao, 1)));
            this.a(adm2, aqe2, 1, 0, -1, 3, 2, -1, afi.a.Q(), afi.a.Q(), false);
            if (this.a(adm2, 2, 0, -1, aqe2).c().t() == arm.a && this.a(adm2, 2, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 2, 0, -1, aqe2);
            }
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    this.b(adm2, \u2603, 7, \u2603, aqe2);
                    this.b(adm2, afi.e.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            for (\u2603 = 5; \u2603 < 11; ++\u2603) {
                for (\u2603 = 2; \u2603 < 9; ++\u2603) {
                    this.b(adm2, \u2603, 7, \u2603, aqe2);
                    this.b(adm2, afi.e.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 4, 1, 2, 2);
            return true;
        }
    }

    public static class f
    extends n {
        public f() {
        }

        public f(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static f a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 9, 7, 11, cq2);
            if (!f.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new f(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 7 - 1, 0);
            }
            this.a(adm2, aqe2, 1, 1, 1, 7, 4, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 2, 1, 6, 8, 4, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 2, 0, 6, 8, 0, 10, afi.d.Q(), afi.d.Q(), false);
            this.a(adm2, afi.e.Q(), 6, 0, 6, aqe2);
            this.a(adm2, aqe2, 2, 1, 6, 2, 1, 10, afi.aO.Q(), afi.aO.Q(), false);
            this.a(adm2, aqe2, 8, 1, 6, 8, 1, 10, afi.aO.Q(), afi.aO.Q(), false);
            this.a(adm2, aqe2, 3, 1, 10, 7, 1, 10, afi.aO.Q(), afi.aO.Q(), false);
            this.a(adm2, aqe2, 1, 0, 1, 7, 0, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 0, 0, 0, 3, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 8, 0, 0, 8, 3, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 7, 1, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 0, 5, 7, 1, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 2, 0, 7, 3, 0, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 2, 5, 7, 3, 5, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 4, 1, 8, 4, 1, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 4, 4, 8, 4, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 0, 5, 2, 8, 5, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, afi.f.Q(), 0, 4, 2, aqe2);
            this.a(adm2, afi.f.Q(), 0, 4, 3, aqe2);
            this.a(adm2, afi.f.Q(), 8, 4, 2, aqe2);
            this.a(adm2, afi.f.Q(), 8, 4, 3, aqe2);
            int n2 = this.a(afi.ad, 3);
            \u2603 = this.a(afi.ad, 2);
            for (\u2603 = -1; \u2603 <= 2; ++\u2603) {
                for (\u2603 = 0; \u2603 <= 8; ++\u2603) {
                    this.a(adm2, afi.ad.a(n2), \u2603, 4 + \u2603, \u2603, aqe2);
                    this.a(adm2, afi.ad.a(\u2603), \u2603, 4 + \u2603, 5 - \u2603, aqe2);
                }
            }
            this.a(adm2, afi.r.Q(), 0, 2, 1, aqe2);
            this.a(adm2, afi.r.Q(), 0, 2, 4, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 1, aqe2);
            this.a(adm2, afi.r.Q(), 8, 2, 4, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 2, 5, aqe2);
            this.a(adm2, afi.bj.Q(), 3, 2, 5, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 2, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 6, 2, 5, aqe2);
            this.a(adm2, afi.aO.Q(), 2, 1, 3, aqe2);
            this.a(adm2, afi.aB.Q(), 2, 2, 3, aqe2);
            this.a(adm2, afi.f.Q(), 1, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(this.a(afi.ad, 3)), 2, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(this.a(afi.ad, 1)), 1, 1, 3, aqe2);
            this.a(adm2, aqe2, 5, 0, 1, 7, 0, 3, afi.T.Q(), afi.T.Q(), false);
            this.a(adm2, afi.T.Q(), 6, 1, 1, aqe2);
            this.a(adm2, afi.T.Q(), 6, 1, 2, aqe2);
            this.a(adm2, afi.a.Q(), 2, 1, 0, aqe2);
            this.a(adm2, afi.a.Q(), 2, 2, 0, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m), 2, 3, 1, aqe2);
            this.a(adm2, aqe2, random, 2, 1, 0, cq.b(this.a(afi.ao, 1)));
            if (this.a(adm2, 2, 0, -1, aqe2).c().t() == arm.a && this.a(adm2, 2, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 2, 0, -1, aqe2);
            }
            this.a(adm2, afi.a.Q(), 6, 1, 5, aqe2);
            this.a(adm2, afi.a.Q(), 6, 2, 5, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.d()), 6, 3, 4, aqe2);
            this.a(adm2, aqe2, random, 6, 1, 5, cq.b(this.a(afi.ao, 1)));
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    this.b(adm2, \u2603, 7, \u2603, aqe2);
                    this.b(adm2, afi.e.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 4, 1, 2, 2);
            return true;
        }

        @Override
        protected int c(int n2, int n3) {
            if (n2 == 0) {
                return 4;
            }
            return super.c(n2, n3);
        }
    }

    public static class h
    extends n {
        private boolean a;
        private int b;

        public h() {
        }

        public h(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = random.nextBoolean();
            this.b = random.nextInt(3);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("T", this.b);
            dn2.a("C", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.f("T");
            this.a = dn2.n("C");
        }

        public static h a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 4, 6, 5, cq2);
            if (!h.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new h(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            if (this.h < 0) {
                this.h = this.b(adm22, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 6 - 1, 0);
            }
            this.a(adm22, aqe2, 1, 1, 1, 3, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 0, 0, 3, 0, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm22, aqe2, 1, 0, 1, 2, 0, 3, afi.d.Q(), afi.d.Q(), false);
            if (this.a) {
                this.a(adm22, aqe2, 1, 4, 1, 2, 4, 3, afi.r.Q(), afi.r.Q(), false);
            } else {
                this.a(adm22, aqe2, 1, 5, 1, 2, 5, 3, afi.r.Q(), afi.r.Q(), false);
            }
            this.a(adm22, afi.r.Q(), 1, 4, 0, aqe2);
            this.a(adm22, afi.r.Q(), 2, 4, 0, aqe2);
            this.a(adm22, afi.r.Q(), 1, 4, 4, aqe2);
            this.a(adm22, afi.r.Q(), 2, 4, 4, aqe2);
            this.a(adm22, afi.r.Q(), 0, 4, 1, aqe2);
            this.a(adm22, afi.r.Q(), 0, 4, 2, aqe2);
            this.a(adm22, afi.r.Q(), 0, 4, 3, aqe2);
            this.a(adm22, afi.r.Q(), 3, 4, 1, aqe2);
            this.a(adm22, afi.r.Q(), 3, 4, 2, aqe2);
            this.a(adm22, afi.r.Q(), 3, 4, 3, aqe2);
            this.a(adm22, aqe2, 0, 1, 0, 0, 3, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 3, 1, 0, 3, 3, 0, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 0, 1, 4, 0, 3, 4, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 3, 1, 4, 3, 3, 4, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 0, 1, 1, 0, 3, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 3, 1, 1, 3, 3, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 1, 1, 0, 2, 3, 0, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 1, 1, 4, 2, 3, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm22, afi.bj.Q(), 3, 2, 2, aqe2);
            if (this.b > 0) {
                this.a(adm22, afi.aO.Q(), this.b, 1, 3, aqe2);
                this.a(adm22, afi.aB.Q(), this.b, 2, 3, aqe2);
            }
            this.a(adm22, afi.a.Q(), 1, 1, 0, aqe2);
            this.a(adm22, afi.a.Q(), 1, 2, 0, aqe2);
            this.a(adm22, aqe2, random, 1, 1, 0, cq.b(this.a(afi.ao, 1)));
            if (this.a(adm22, 1, 0, -1, aqe2).c().t() == arm.a && this.a(adm22, 1, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm22, afi.aw.a(this.a(afi.aw, 3)), 1, 0, -1, aqe2);
            }
            for (int i2 = 0; i2 < 5; ++i2) {
                for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                    this.b(adm22, \u2603, 6, i2, aqe2);
                    this.b(adm22, afi.e.Q(), \u2603, -1, i2, aqe2);
                }
            }
            this.a(adm22, aqe2, 1, 1, 2, 1);
            return true;
        }
    }

    public static class a
    extends n {
        public a() {
        }

        public a(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static a a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 9, 9, 6, cq2);
            if (!a.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new a(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 9 - 1, 0);
            }
            this.a(adm2, aqe2, 1, 1, 1, 7, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 0, 0, 8, 0, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 5, 0, 8, 5, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 6, 1, 8, 6, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 7, 2, 8, 7, 3, afi.e.Q(), afi.e.Q(), false);
            int n2 = this.a(afi.ad, 3);
            \u2603 = this.a(afi.ad, 2);
            for (\u2603 = -1; \u2603 <= 2; ++\u2603) {
                for (\u2603 = 0; \u2603 <= 8; ++\u2603) {
                    this.a(adm2, afi.ad.a(n2), \u2603, 6 + \u2603, \u2603, aqe2);
                    this.a(adm2, afi.ad.a(\u2603), \u2603, 6 + \u2603, 5 - \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 0, 1, 0, 0, 1, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 1, 5, 8, 1, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 8, 1, 0, 8, 1, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 2, 1, 0, 7, 1, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 4, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 2, 5, 0, 4, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 8, 2, 5, 8, 4, 5, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 8, 2, 0, 8, 4, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 2, 1, 0, 4, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 2, 5, 7, 4, 5, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 8, 2, 1, 8, 4, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 2, 0, 7, 4, 0, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, afi.bj.Q(), 4, 2, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 2, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 6, 2, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 3, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 3, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 6, 3, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 3, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 3, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 2, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 3, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 8, 3, 3, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 2, 5, aqe2);
            this.a(adm2, afi.bj.Q(), 3, 2, 5, aqe2);
            this.a(adm2, afi.bj.Q(), 5, 2, 5, aqe2);
            this.a(adm2, afi.bj.Q(), 6, 2, 5, aqe2);
            this.a(adm2, aqe2, 1, 4, 1, 7, 4, 1, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 4, 4, 7, 4, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm2, aqe2, 1, 3, 4, 7, 3, 4, afi.X.Q(), afi.X.Q(), false);
            this.a(adm2, afi.f.Q(), 7, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(this.a(afi.ad, 0)), 7, 1, 3, aqe2);
            \u2603 = this.a(afi.ad, 3);
            this.a(adm2, afi.ad.a(\u2603), 6, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(\u2603), 5, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(\u2603), 4, 1, 4, aqe2);
            this.a(adm2, afi.ad.a(\u2603), 3, 1, 4, aqe2);
            this.a(adm2, afi.aO.Q(), 6, 1, 3, aqe2);
            this.a(adm2, afi.aB.Q(), 6, 2, 3, aqe2);
            this.a(adm2, afi.aO.Q(), 4, 1, 3, aqe2);
            this.a(adm2, afi.aB.Q(), 4, 2, 3, aqe2);
            this.a(adm2, afi.ai.Q(), 7, 1, 1, aqe2);
            this.a(adm2, afi.a.Q(), 1, 1, 0, aqe2);
            this.a(adm2, afi.a.Q(), 1, 2, 0, aqe2);
            this.a(adm2, aqe2, random, 1, 1, 0, cq.b(this.a(afi.ao, 1)));
            if (this.a(adm2, 1, 0, -1, aqe2).c().t() == arm.a && this.a(adm2, 1, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 1, 0, -1, aqe2);
            }
            for (\u2603 = 0; \u2603 < 6; ++\u2603) {
                for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                    this.b(adm2, \u2603, 9, \u2603, aqe2);
                    this.b(adm2, afi.e.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 2, 1, 2, 1);
            return true;
        }

        @Override
        protected int c(int n2, int n3) {
            return 1;
        }
    }

    public static class i
    extends n {
        public i() {
        }

        public i(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
        }

        public static i a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 5, 12, 9, cq2);
            if (!i.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new i(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 12 - 1, 0);
            }
            this.a(adm2, aqe2, 1, 1, 1, 3, 3, 7, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 5, 1, 3, 9, 3, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 3, 0, 8, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 1, 0, 3, 10, 0, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 1, 1, 0, 10, 3, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 4, 1, 1, 4, 10, 3, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 0, 4, 0, 4, 7, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 4, 0, 4, 4, 4, 7, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 1, 8, 3, 4, 8, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 5, 4, 3, 10, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 1, 5, 5, 3, 5, 7, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 9, 0, 4, 9, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, aqe2, 0, 4, 0, 4, 4, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm2, afi.e.Q(), 0, 11, 2, aqe2);
            this.a(adm2, afi.e.Q(), 4, 11, 2, aqe2);
            this.a(adm2, afi.e.Q(), 2, 11, 0, aqe2);
            this.a(adm2, afi.e.Q(), 2, 11, 4, aqe2);
            this.a(adm2, afi.e.Q(), 1, 1, 6, aqe2);
            this.a(adm2, afi.e.Q(), 1, 1, 7, aqe2);
            this.a(adm2, afi.e.Q(), 2, 1, 7, aqe2);
            this.a(adm2, afi.e.Q(), 3, 1, 6, aqe2);
            this.a(adm2, afi.e.Q(), 3, 1, 7, aqe2);
            this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 1, 1, 5, aqe2);
            this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 2, 1, 6, aqe2);
            this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 3, 1, 5, aqe2);
            this.a(adm2, afi.aw.a(this.a(afi.aw, 1)), 1, 2, 7, aqe2);
            this.a(adm2, afi.aw.a(this.a(afi.aw, 0)), 3, 2, 7, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 3, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 2, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 3, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 6, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 7, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 6, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 7, 2, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 6, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 7, 0, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 6, 4, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 7, 4, aqe2);
            this.a(adm2, afi.bj.Q(), 0, 3, 6, aqe2);
            this.a(adm2, afi.bj.Q(), 4, 3, 6, aqe2);
            this.a(adm2, afi.bj.Q(), 2, 3, 8, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.d()), 2, 4, 7, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.e()), 1, 4, 6, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m.f()), 3, 4, 6, aqe2);
            this.a(adm2, afi.aa.Q().a(akf.a, this.m), 2, 4, 5, aqe2);
            int n2 = this.a(afi.au, 4);
            for (\u2603 = 1; \u2603 <= 9; ++\u2603) {
                this.a(adm2, afi.au.a(n2), 3, \u2603, 3, aqe2);
            }
            this.a(adm2, afi.a.Q(), 2, 1, 0, aqe2);
            this.a(adm2, afi.a.Q(), 2, 2, 0, aqe2);
            this.a(adm2, aqe2, random, 2, 1, 0, cq.b(this.a(afi.ao, 1)));
            if (this.a(adm2, 2, 0, -1, aqe2).c().t() == arm.a && this.a(adm2, 2, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm2, afi.aw.a(this.a(afi.aw, 3)), 2, 0, -1, aqe2);
            }
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                    this.b(adm2, \u2603, 12, \u2603, aqe2);
                    this.b(adm2, afi.e.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            this.a(adm2, aqe2, 2, 1, 2, 1);
            return true;
        }

        @Override
        protected int c(int n2, int n3) {
            return 2;
        }
    }

    public static class g
    extends n {
        private boolean a;

        public g() {
        }

        public g(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = random.nextBoolean();
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Terrace", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.n("Terrace");
        }

        public static g a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 5, 6, 5, cq2);
            if (aqt.a(list, aqe2) != null) {
                return null;
            }
            return new g(k2, n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int n2;
            if (this.h < 0) {
                this.h = this.b(adm22, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 6 - 1, 0);
            }
            this.a(adm22, aqe2, 0, 0, 0, 4, 0, 4, afi.e.Q(), afi.e.Q(), false);
            this.a(adm22, aqe2, 0, 4, 0, 4, 4, 4, afi.r.Q(), afi.r.Q(), false);
            this.a(adm22, aqe2, 1, 4, 1, 3, 4, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, afi.e.Q(), 0, 1, 0, aqe2);
            this.a(adm22, afi.e.Q(), 0, 2, 0, aqe2);
            this.a(adm22, afi.e.Q(), 0, 3, 0, aqe2);
            this.a(adm22, afi.e.Q(), 4, 1, 0, aqe2);
            this.a(adm22, afi.e.Q(), 4, 2, 0, aqe2);
            this.a(adm22, afi.e.Q(), 4, 3, 0, aqe2);
            this.a(adm22, afi.e.Q(), 0, 1, 4, aqe2);
            this.a(adm22, afi.e.Q(), 0, 2, 4, aqe2);
            this.a(adm22, afi.e.Q(), 0, 3, 4, aqe2);
            this.a(adm22, afi.e.Q(), 4, 1, 4, aqe2);
            this.a(adm22, afi.e.Q(), 4, 2, 4, aqe2);
            this.a(adm22, afi.e.Q(), 4, 3, 4, aqe2);
            this.a(adm22, aqe2, 0, 1, 1, 0, 3, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 4, 1, 1, 4, 3, 3, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, aqe2, 1, 1, 4, 3, 3, 4, afi.f.Q(), afi.f.Q(), false);
            this.a(adm22, afi.bj.Q(), 0, 2, 2, aqe2);
            this.a(adm22, afi.bj.Q(), 2, 2, 4, aqe2);
            this.a(adm22, afi.bj.Q(), 4, 2, 2, aqe2);
            this.a(adm22, afi.f.Q(), 1, 1, 0, aqe2);
            this.a(adm22, afi.f.Q(), 1, 2, 0, aqe2);
            this.a(adm22, afi.f.Q(), 1, 3, 0, aqe2);
            this.a(adm22, afi.f.Q(), 2, 3, 0, aqe2);
            this.a(adm22, afi.f.Q(), 3, 3, 0, aqe2);
            this.a(adm22, afi.f.Q(), 3, 2, 0, aqe2);
            this.a(adm22, afi.f.Q(), 3, 1, 0, aqe2);
            if (this.a(adm22, 2, 0, -1, aqe2).c().t() == arm.a && this.a(adm22, 2, -1, -1, aqe2).c().t() != arm.a) {
                this.a(adm22, afi.aw.a(this.a(afi.aw, 3)), 2, 0, -1, aqe2);
            }
            this.a(adm22, aqe2, 1, 1, 1, 3, 3, 3, afi.a.Q(), afi.a.Q(), false);
            if (this.a) {
                this.a(adm22, afi.aO.Q(), 0, 5, 0, aqe2);
                this.a(adm22, afi.aO.Q(), 1, 5, 0, aqe2);
                this.a(adm22, afi.aO.Q(), 2, 5, 0, aqe2);
                this.a(adm22, afi.aO.Q(), 3, 5, 0, aqe2);
                this.a(adm22, afi.aO.Q(), 4, 5, 0, aqe2);
                this.a(adm22, afi.aO.Q(), 0, 5, 4, aqe2);
                this.a(adm22, afi.aO.Q(), 1, 5, 4, aqe2);
                this.a(adm22, afi.aO.Q(), 2, 5, 4, aqe2);
                this.a(adm22, afi.aO.Q(), 3, 5, 4, aqe2);
                this.a(adm22, afi.aO.Q(), 4, 5, 4, aqe2);
                this.a(adm22, afi.aO.Q(), 4, 5, 1, aqe2);
                this.a(adm22, afi.aO.Q(), 4, 5, 2, aqe2);
                this.a(adm22, afi.aO.Q(), 4, 5, 3, aqe2);
                this.a(adm22, afi.aO.Q(), 0, 5, 1, aqe2);
                this.a(adm22, afi.aO.Q(), 0, 5, 2, aqe2);
                this.a(adm22, afi.aO.Q(), 0, 5, 3, aqe2);
            }
            if (this.a) {
                n2 = this.a(afi.au, 3);
                this.a(adm22, afi.au.a(n2), 3, 1, 3, aqe2);
                this.a(adm22, afi.au.a(n2), 3, 2, 3, aqe2);
                this.a(adm22, afi.au.a(n2), 3, 3, 3, aqe2);
                this.a(adm22, afi.au.a(n2), 3, 4, 3, aqe2);
            }
            this.a(adm22, afi.aa.Q().a(akf.a, this.m), 2, 3, 1, aqe2);
            for (n2 = 0; n2 < 5; ++n2) {
                for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                    this.b(adm22, \u2603, 6, n2, aqe2);
                    this.b(adm22, afi.e.Q(), \u2603, -1, n2, aqe2);
                }
            }
            this.a(adm22, aqe2, 1, 1, 2, 1);
            return true;
        }
    }

    public static class l
    extends o {
        private int a;

        public l() {
        }

        public l(k k2, int n2, Random random, aqe aqe2, cq cq2) {
            super(k2, n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = Math.max(aqe2.c(), aqe2.e());
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Length", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.f("Length");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random2) {
            Random random2;
            aqt aqt3;
            int n2;
            boolean \u26032 = false;
            for (n2 = random2.nextInt(5); n2 < this.a - 8; n2 += 2 + random2.nextInt(5)) {
                aqt3 = this.a((k)aqt2, list, random2, 0, n2);
                if (aqt3 == null) continue;
                n2 += Math.max(aqt3.l.c(), aqt3.l.e());
                \u26032 = true;
            }
            for (n2 = random2.nextInt(5); n2 < this.a - 8; n2 += 2 + random2.nextInt(5)) {
                aqt3 = this.b((k)aqt2, list, random2, 0, n2);
                if (aqt3 == null) continue;
                n2 += Math.max(aqt3.l.c(), aqt3.l.e());
                \u26032 = true;
            }
            if (\u26032 && random2.nextInt(3) > 0 && this.m != null) {
                switch (this.m) {
                    case c: {
                        aqw.e((k)aqt2, list, random2, this.l.a - 1, this.l.b, this.l.c, cq.e, this.d());
                        break;
                    }
                    case d: {
                        aqw.e((k)aqt2, list, random2, this.l.a - 1, this.l.b, this.l.f - 2, cq.e, this.d());
                        break;
                    }
                    case f: {
                        aqw.e((k)aqt2, list, random2, this.l.d - 2, this.l.b, this.l.c - 1, cq.c, this.d());
                        break;
                    }
                    case e: {
                        aqw.e((k)aqt2, list, random2, this.l.a, this.l.b, this.l.c - 1, cq.c, this.d());
                    }
                }
            }
            if (\u26032 && random2.nextInt(3) > 0 && this.m != null) {
                switch (this.m) {
                    case c: {
                        aqw.e((k)aqt2, list, random2, this.l.d + 1, this.l.b, this.l.c, cq.f, this.d());
                        break;
                    }
                    case d: {
                        aqw.e((k)aqt2, list, random2, this.l.d + 1, this.l.b, this.l.f - 2, cq.f, this.d());
                        break;
                    }
                    case f: {
                        aqw.e((k)aqt2, list, random2, this.l.d - 2, this.l.b, this.l.f + 1, cq.d, this.d());
                        break;
                    }
                    case e: {
                        aqw.e((k)aqt2, list, random2, this.l.a, this.l.b, this.l.f + 1, cq.d, this.d());
                    }
                }
            }
        }

        public static aqe a(k k2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            for (int i2 = 7 * ns.a(random, 3, 5); i2 >= 7; i2 -= 7) {
                aqe aqe2 = aqe.a(n2, n3, n4, 0, 0, 0, 3, 3, i2, cq2);
                if (aqt.a(list, aqe2) != null) continue;
                return aqe2;
            }
            return null;
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            alz alz2 = this.a(afi.n.Q());
            \u2603 = this.a(afi.e.Q());
            for (int i2 = this.l.a; i2 <= this.l.d; ++i2) {
                for (\u2603 = this.l.c; \u2603 <= this.l.f; ++\u2603) {
                    cj cj2 = new cj(i2, 64, \u2603);
                    if (!aqe2.b(cj2)) continue;
                    cj2 = adm2.r(cj2).b();
                    adm2.a(cj2, alz2, 2);
                    adm2.a(cj2.b(), \u2603, 2);
                }
            }
            return true;
        }
    }

    public static abstract class o
    extends n {
        public o() {
        }

        protected o(k k2, int n2) {
            super(k2, n2);
        }
    }

    public static class k
    extends p {
        public aec a;
        public boolean b;
        public int c;
        public e d;
        public List<e> e;
        public List<aqt> f = Lists.newArrayList();
        public List<aqt> g = Lists.newArrayList();

        public k() {
        }

        public k(aec aec2, int n2, Random random, int n3, int n4, List<e> list, int n5) {
            super(null, 0, random, n3, n4);
            this.a = aec2;
            this.e = list;
            this.c = n5;
            ady ady2 = aec2.a(new cj(n3, 0, n4), ady.ad);
            this.b = ady2 == ady.r || ady2 == ady.G;
            this.a(this.b);
        }

        public aec e() {
            return this.a;
        }
    }

    public static class p
    extends n {
        public p() {
        }

        public p(k k2, int n2, Random random, int n3, int n4) {
            super(k2, n2);
            this.m = cq.c.a.a(random);
            switch (this.m) {
                case c: 
                case d: {
                    this.l = new aqe(n3, 64, n4, n3 + 6 - 1, 78, n4 + 6 - 1);
                    break;
                }
                default: {
                    this.l = new aqe(n3, 64, n4, n3 + 6 - 1, 78, n4 + 6 - 1);
                }
            }
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            aqw.e((k)aqt2, list, random, this.l.a - 1, this.l.e - 4, this.l.c + 1, cq.e, this.d());
            aqw.e((k)aqt2, list, random, this.l.d + 1, this.l.e - 4, this.l.c + 1, cq.f, this.d());
            aqw.e((k)aqt2, list, random, this.l.a + 1, this.l.e - 4, this.l.c - 1, cq.c, this.d());
            aqw.e((k)aqt2, list, random, this.l.a + 1, this.l.e - 4, this.l.f + 1, cq.d, this.d());
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.h < 0) {
                this.h = this.b(adm2, aqe2);
                if (this.h < 0) {
                    return true;
                }
                this.l.a(0, this.h - this.l.e + 3, 0);
            }
            this.a(adm2, aqe2, 1, 0, 1, 4, 12, 4, afi.e.Q(), afi.i.Q(), false);
            this.a(adm2, afi.a.Q(), 2, 12, 2, aqe2);
            this.a(adm2, afi.a.Q(), 3, 12, 2, aqe2);
            this.a(adm2, afi.a.Q(), 2, 12, 3, aqe2);
            this.a(adm2, afi.a.Q(), 3, 12, 3, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 13, 1, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 14, 1, aqe2);
            this.a(adm2, afi.aO.Q(), 4, 13, 1, aqe2);
            this.a(adm2, afi.aO.Q(), 4, 14, 1, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 13, 4, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 14, 4, aqe2);
            this.a(adm2, afi.aO.Q(), 4, 13, 4, aqe2);
            this.a(adm2, afi.aO.Q(), 4, 14, 4, aqe2);
            this.a(adm2, aqe2, 1, 15, 1, 4, 15, 4, afi.e.Q(), afi.e.Q(), false);
            for (int i2 = 0; i2 <= 5; ++i2) {
                for (\u2603 = 0; \u2603 <= 5; ++\u2603) {
                    if (\u2603 != 0 && \u2603 != 5 && i2 != 0 && i2 != 5) continue;
                    this.a(adm2, afi.n.Q(), \u2603, 11, i2, aqe2);
                    this.b(adm2, \u2603, 12, i2, aqe2);
                }
            }
            return true;
        }
    }

    static abstract class n
    extends aqt {
        protected int h = -1;
        private int a;
        private boolean b;

        public n() {
        }

        protected n(k k2, int n2) {
            super(n2);
            if (k2 != null) {
                this.b = k2.b;
            }
        }

        @Override
        protected void a(dn dn2) {
            dn2.a("HPos", this.h);
            dn2.a("VCount", this.a);
            dn2.a("Desert", this.b);
        }

        @Override
        protected void b(dn dn2) {
            this.h = dn2.f("HPos");
            this.a = dn2.f("VCount");
            this.b = dn2.n("Desert");
        }

        protected aqt a(k k2, List<aqt> list, Random random, int n2, int n3) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return aqw.d(k2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d());
                    }
                    case d: {
                        return aqw.d(k2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d());
                    }
                    case e: {
                        return aqw.d(k2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d());
                    }
                    case f: {
                        return aqw.d(k2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d());
                    }
                }
            }
            return null;
        }

        protected aqt b(k k2, List<aqt> list, Random random, int n2, int n3) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return aqw.d(k2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d());
                    }
                    case d: {
                        return aqw.d(k2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d());
                    }
                    case e: {
                        return aqw.d(k2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d());
                    }
                    case f: {
                        return aqw.d(k2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d());
                    }
                }
            }
            return null;
        }

        protected int b(adm adm2, aqe aqe2) {
            int n2 = 0;
            \u2603 = 0;
            cj.a \u26032 = new cj.a();
            for (\u2603 = this.l.c; \u2603 <= this.l.f; ++\u2603) {
                for (\u2603 = this.l.a; \u2603 <= this.l.d; ++\u2603) {
                    \u26032.c(\u2603, 64, \u2603);
                    if (!aqe2.b(\u26032)) continue;
                    n2 += Math.max(adm2.r(\u26032).o(), adm2.t.i());
                    ++\u2603;
                }
            }
            if (\u2603 == 0) {
                return -1;
            }
            return n2 / \u2603;
        }

        protected static boolean a(aqe aqe2) {
            return aqe2 != null && aqe2.b > 10;
        }

        protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5) {
            if (this.a >= n5) {
                return;
            }
            for (\u2603 = this.a; \u2603 < n5 && aqe2.b(new cj(\u2603 = this.a(n2 + \u2603, n4), \u2603 = this.d(n3), \u2603 = this.b(n2 + \u2603, n4))); ++\u2603) {
                ++this.a;
                wi wi2 = new wi(adm2);
                wi2.b((double)\u2603 + 0.5, \u2603, (double)\u2603 + 0.5, 0.0f, 0.0f);
                wi2.a(adm2.E(new cj(wi2)), null);
                wi2.r(this.c(\u2603, wi2.cl()));
                adm2.d(wi2);
            }
        }

        protected int c(int n2, int n3) {
            return n3;
        }

        protected alz a(alz alz2) {
            if (this.b) {
                if (alz2.c() == afi.r || alz2.c() == afi.s) {
                    return afi.A.Q();
                }
                if (alz2.c() == afi.e) {
                    return afi.A.a(aji.a.a.a());
                }
                if (alz2.c() == afi.f) {
                    return afi.A.a(aji.a.c.a());
                }
                if (alz2.c() == afi.ad) {
                    return afi.bO.Q().a(aju.a, alz2.b(aju.a));
                }
                if (alz2.c() == afi.aw) {
                    return afi.bO.Q().a(aju.a, alz2.b(aju.a));
                }
                if (alz2.c() == afi.n) {
                    return afi.A.Q();
                }
            }
            return alz2;
        }

        @Override
        protected void a(adm adm2, alz alz2, int n2, int n3, int n4, aqe aqe2) {
            alz alz3 = this.a(alz2);
            super.a(adm2, alz3, n2, n3, n4, aqe2);
        }

        @Override
        protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, alz alz2, alz alz3, boolean bl2) {
            alz alz4 = this.a(alz2);
            \u2603 = this.a(alz3);
            super.a(adm2, aqe2, n2, n3, n4, n5, n6, n7, alz4, \u2603, bl2);
        }

        @Override
        protected void b(adm adm2, alz alz2, int n2, int n3, int n4, aqe aqe2) {
            alz alz3 = this.a(alz2);
            super.b(adm2, alz3, n2, n3, n4, aqe2);
        }

        protected void a(boolean bl2) {
            this.b = bl2;
        }
    }

    public static class e {
        public Class<? extends n> a;
        public final int b;
        public int c;
        public int d;

        public e(Class<? extends n> clazz, int n2, int n3) {
            this.a = clazz;
            this.b = n2;
            this.d = n3;
        }

        public boolean a(int n2) {
            return this.d == 0 || this.c < this.d;
        }

        public boolean a() {
            return this.d == 0 || this.c < this.d;
        }
    }
}

