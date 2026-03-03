/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqp {
    private static final f[] b = new f[]{new f(n.class, 40, 0), new f(h.class, 5, 5), new f(d.class, 20, 0), new f(i.class, 20, 0), new f(j.class, 10, 6), new f(o.class, 5, 5), new f(l.class, 5, 5), new f(c.class, 5, 4), new f(a.class, 5, 4), new f(e.class, 10, 2){

        @Override
        public boolean a(int n2) {
            return super.a(n2) && n2 > 4;
        }
    }, new f(g.class, 20, 1){

        @Override
        public boolean a(int n2) {
            return super.a(n2) && n2 > 5;
        }
    }};
    private static List<f> c;
    private static Class<? extends p> d;
    static int a;
    private static final k e;

    public static void a() {
        aqr.a(a.class, "SHCC");
        aqr.a(b.class, "SHFC");
        aqr.a(c.class, "SH5C");
        aqr.a(d.class, "SHLT");
        aqr.a(e.class, "SHLi");
        aqr.a(g.class, "SHPR");
        aqr.a(h.class, "SHPH");
        aqr.a(i.class, "SHRT");
        aqr.a(j.class, "SHRC");
        aqr.a(l.class, "SHSD");
        aqr.a(m.class, "SHStart");
        aqr.a(n.class, "SHS");
        aqr.a(o.class, "SHSSD");
    }

    public static void b() {
        c = Lists.newArrayList();
        for (f f2 : b) {
            f2.c = 0;
            c.add(f2);
        }
        d = null;
    }

    private static boolean d() {
        boolean bl2 = false;
        a = 0;
        for (f f22 : c) {
            f f22;
            if (f22.d > 0 && f22.c < f22.d) {
                bl2 = true;
            }
            a += f22.b;
        }
        return bl2;
    }

    private static p a(Class<? extends p> clazz, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        p p2 = null;
        if (clazz == n.class) {
            p2 = n.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == h.class) {
            p2 = h.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == d.class) {
            p2 = aqp$d.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == i.class) {
            p2 = i.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == j.class) {
            p2 = j.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == o.class) {
            p2 = o.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == l.class) {
            p2 = l.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == c.class) {
            p2 = aqp$c.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == a.class) {
            p2 = aqp$a.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == e.class) {
            p2 = aqp$e.a(list, random, n2, n3, n4, cq2, n5);
        } else if (clazz == g.class) {
            p2 = g.a(list, random, n2, n3, n4, cq2, n5);
        }
        return p2;
    }

    private static p b(m m2, List<aqt> list2, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        List<aqt> list2;
        if (!aqp.d()) {
            return null;
        }
        if (d != null) {
            p p2 = aqp.a(d, list2, random, n2, n3, n4, cq2, n5);
            d = null;
            if (p2 != null) {
                return p2;
            }
        }
        int n6 = 0;
        block0: while (n6 < 5) {
            ++n6;
            \u2603 = random.nextInt(a);
            for (f f2 : c) {
                if ((\u2603 -= f2.b) >= 0) continue;
                if (!f2.a(n5) || f2 == m2.a) continue block0;
                p p3 = aqp.a(f2.a, list2, random, n2, n3, n4, cq2, n5);
                if (p3 == null) continue;
                ++f2.c;
                m2.a = f2;
                if (!f2.a()) {
                    c.remove(f2);
                }
                return p3;
            }
        }
        aqe \u26032 = aqp$b.a(list2, random, n2, n3, n4, cq2);
        if (\u26032 != null && \u26032.b > 1) {
            return new b(n5, random, \u26032, cq2);
        }
        return null;
    }

    private static aqt c(m m2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        if (n5 > 50) {
            return null;
        }
        if (Math.abs(n2 - m2.c().a) > 112 || Math.abs(n4 - m2.c().c) > 112) {
            return null;
        }
        p p2 = aqp.b(m2, list, random, n2, n3, n4, cq2, n5 + 1);
        if (p2 != null) {
            list.add(p2);
            m2.c.add(p2);
        }
        return p2;
    }

    static {
        e = new k();
    }

    static class k
    extends aqt.a {
        private k() {
        }

        @Override
        public void a(Random random, int n2, int n3, int n4, boolean bl2) {
            this.a = bl2 ? ((\u2603 = random.nextFloat()) < 0.2f ? afi.bf.a(ajz.O) : (\u2603 < 0.5f ? afi.bf.a(ajz.N) : (\u2603 < 0.55f ? afi.be.a(ahz.a.c.a()) : afi.bf.Q()))) : afi.a.Q();
        }
    }

    public static class g
    extends p {
        private boolean a;

        public g() {
        }

        public g(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Mob", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.n("Mob");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            if (aqt2 != null) {
                ((m)aqt2).b = this;
            }
        }

        public static g a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -4, -1, 0, 11, 8, 16, cq2);
            if (!g.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new g(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 10, 7, 15, false, random, e);
            this.a(adm2, random, aqe2, p.a.c, 4, 1, 0);
            int n2 = 6;
            this.a(adm2, aqe2, 1, n2, 1, 1, n2, 14, false, random, e);
            this.a(adm2, aqe2, 9, n2, 1, 9, n2, 14, false, random, e);
            this.a(adm2, aqe2, 2, n2, 1, 8, n2, 2, false, random, e);
            this.a(adm2, aqe2, 2, n2, 14, 8, n2, 14, false, random, e);
            this.a(adm2, aqe2, 1, 1, 1, 2, 1, 4, false, random, e);
            this.a(adm2, aqe2, 8, 1, 1, 9, 1, 4, false, random, e);
            this.a(adm2, aqe2, 1, 1, 1, 1, 1, 3, afi.k.Q(), afi.k.Q(), false);
            this.a(adm2, aqe2, 9, 1, 1, 9, 1, 3, afi.k.Q(), afi.k.Q(), false);
            this.a(adm2, aqe2, 3, 1, 8, 7, 1, 12, false, random, e);
            this.a(adm2, aqe2, 4, 1, 9, 6, 1, 11, afi.k.Q(), afi.k.Q(), false);
            for (\u2603 = 3; \u2603 < 14; \u2603 += 2) {
                this.a(adm2, aqe2, 0, 3, \u2603, 0, 4, \u2603, afi.bi.Q(), afi.bi.Q(), false);
                this.a(adm2, aqe2, 10, 3, \u2603, 10, 4, \u2603, afi.bi.Q(), afi.bi.Q(), false);
            }
            for (\u2603 = 2; \u2603 < 9; \u2603 += 2) {
                this.a(adm2, aqe2, \u2603, 3, 15, \u2603, 4, 15, afi.bi.Q(), afi.bi.Q(), false);
            }
            \u2603 = this.a(afi.bv, 3);
            this.a(adm2, aqe2, 4, 1, 5, 6, 1, 7, false, random, e);
            this.a(adm2, aqe2, 4, 2, 6, 6, 2, 7, false, random, e);
            this.a(adm2, aqe2, 4, 3, 7, 6, 3, 7, false, random, e);
            for (\u2603 = 4; \u2603 <= 6; ++\u2603) {
                this.a(adm2, afi.bv.a(\u2603), \u2603, 1, 4, aqe2);
                this.a(adm2, afi.bv.a(\u2603), \u2603, 2, 5, aqe2);
                this.a(adm2, afi.bv.a(\u2603), \u2603, 3, 6, aqe2);
            }
            \u2603 = cq.c.b();
            \u2603 = cq.d.b();
            \u2603 = cq.f.b();
            \u2603 = cq.e.b();
            if (this.m != null) {
                switch (this.m) {
                    case d: {
                        \u2603 = cq.d.b();
                        \u2603 = cq.c.b();
                        break;
                    }
                    case f: {
                        \u2603 = cq.f.b();
                        \u2603 = cq.e.b();
                        \u2603 = cq.d.b();
                        \u2603 = cq.c.b();
                        break;
                    }
                    case e: {
                        \u2603 = cq.e.b();
                        \u2603 = cq.f.b();
                        \u2603 = cq.d.b();
                        \u2603 = cq.c.b();
                    }
                }
            }
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 4, 3, 8, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 5, 3, 8, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 6, 3, 8, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 4, 3, 12, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 5, 3, 12, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 6, 3, 12, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 3, 3, 9, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 3, 3, 10, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 3, 3, 11, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 7, 3, 9, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 7, 3, 10, aqe2);
            this.a(adm2, afi.bG.a(\u2603).a(ago.b, random.nextFloat() > 0.9f), 7, 3, 11, aqe2);
            if (!this.a) {
                n2 = this.d(3);
                cj cj2 = new cj(this.a(5, 6), n2, this.b(5, 6));
                if (aqe2.b(cj2)) {
                    this.a = true;
                    adm2.a(cj2, afi.ac.Q(), 2);
                    akw akw2 = adm2.s(cj2);
                    if (akw2 instanceof all) {
                        ((all)akw2).b().a("Silverfish");
                    }
                }
            }
            return true;
        }
    }

    public static class c
    extends p {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean e;

        public c() {
        }

        public c(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
            this.a = random.nextBoolean();
            this.b = random.nextBoolean();
            this.c = random.nextBoolean();
            this.e = random.nextInt(3) > 0;
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("leftLow", this.a);
            dn2.a("leftHigh", this.b);
            dn2.a("rightLow", this.c);
            dn2.a("rightHigh", this.e);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.n("leftLow");
            this.b = dn2.n("leftHigh");
            this.c = dn2.n("rightLow");
            this.e = dn2.n("rightHigh");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            int n2 = 3;
            \u2603 = 5;
            if (this.m == cq.e || this.m == cq.c) {
                n2 = 8 - n2;
                \u2603 = 8 - \u2603;
            }
            this.a((m)aqt2, list, random, 5, 1);
            if (this.a) {
                this.b((m)aqt2, list, random, n2, 1);
            }
            if (this.b) {
                this.b((m)aqt2, list, random, \u2603, 7);
            }
            if (this.c) {
                this.c((m)aqt2, list, random, n2, 1);
            }
            if (this.e) {
                this.c((m)aqt2, list, random, \u2603, 7);
            }
        }

        public static c a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -4, -3, 0, 10, 9, 11, cq2);
            if (!aqp$c.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new c(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 9, 8, 10, true, random, e);
            this.a(adm2, random, aqe2, this.d, 4, 3, 0);
            if (this.a) {
                this.a(adm2, aqe2, 0, 3, 1, 0, 5, 3, afi.a.Q(), afi.a.Q(), false);
            }
            if (this.c) {
                this.a(adm2, aqe2, 9, 3, 1, 9, 5, 3, afi.a.Q(), afi.a.Q(), false);
            }
            if (this.b) {
                this.a(adm2, aqe2, 0, 5, 7, 0, 7, 9, afi.a.Q(), afi.a.Q(), false);
            }
            if (this.e) {
                this.a(adm2, aqe2, 9, 5, 7, 9, 7, 9, afi.a.Q(), afi.a.Q(), false);
            }
            this.a(adm2, aqe2, 5, 1, 10, 7, 3, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 2, 1, 8, 2, 6, false, random, e);
            this.a(adm2, aqe2, 4, 1, 5, 4, 4, 9, false, random, e);
            this.a(adm2, aqe2, 8, 1, 5, 8, 4, 9, false, random, e);
            this.a(adm2, aqe2, 1, 4, 7, 3, 4, 9, false, random, e);
            this.a(adm2, aqe2, 1, 3, 5, 3, 3, 6, false, random, e);
            this.a(adm2, aqe2, 1, 3, 4, 3, 3, 4, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 1, 4, 6, 3, 4, 6, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 5, 1, 7, 7, 1, 8, false, random, e);
            this.a(adm2, aqe2, 5, 1, 9, 7, 1, 9, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 5, 2, 7, 7, 2, 7, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 4, 5, 7, 4, 5, 9, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 8, 5, 7, 8, 5, 9, afi.U.Q(), afi.U.Q(), false);
            this.a(adm2, aqe2, 5, 5, 7, 7, 5, 9, afi.T.Q(), afi.T.Q(), false);
            this.a(adm2, afi.aa.Q(), 6, 5, 6, aqe2);
            return true;
        }
    }

    public static class e
    extends p {
        private static final List<ob> a = Lists.newArrayList(new ob(zy.aL, 0, 1, 3, 20), new ob(zy.aK, 0, 2, 7, 20), new ob(zy.bV, 0, 1, 1, 1), new ob(zy.aQ, 0, 1, 1, 1));
        private boolean b;

        public e() {
        }

        public e(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
            this.b = aqe2.d() > 6;
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Tall", this.b);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.n("Tall");
        }

        public static e a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -4, -1, 0, 14, 11, 15, cq2);
            if (!(aqp$e.a(aqe2) && aqt.a(list, aqe2) == null || aqp$e.a(aqe2 = aqe.a(n2, n3, n4, -4, -1, 0, 14, 6, 15, cq2)) && aqt.a(list, aqe2) == null)) {
                return null;
            }
            return new e(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            int n2 = 11;
            if (!this.b) {
                n2 = 6;
            }
            this.a(adm2, aqe2, 0, 0, 0, 13, n2 - 1, 14, true, random, e);
            this.a(adm2, random, aqe2, this.d, 4, 1, 0);
            this.a(adm2, aqe2, random, 0.07f, 2, 1, 1, 11, 4, 13, afi.G.Q(), afi.G.Q(), false);
            boolean \u26032 = true;
            \u2603 = 12;
            for (\u2603 = 1; \u2603 <= 13; ++\u2603) {
                if ((\u2603 - 1) % 4 == 0) {
                    this.a(adm2, aqe2, 1, 1, \u2603, 1, 4, \u2603, afi.f.Q(), afi.f.Q(), false);
                    this.a(adm2, aqe2, 12, 1, \u2603, 12, 4, \u2603, afi.f.Q(), afi.f.Q(), false);
                    this.a(adm2, afi.aa.Q(), 2, 3, \u2603, aqe2);
                    this.a(adm2, afi.aa.Q(), 11, 3, \u2603, aqe2);
                    if (!this.b) continue;
                    this.a(adm2, aqe2, 1, 6, \u2603, 1, 9, \u2603, afi.f.Q(), afi.f.Q(), false);
                    this.a(adm2, aqe2, 12, 6, \u2603, 12, 9, \u2603, afi.f.Q(), afi.f.Q(), false);
                    continue;
                }
                this.a(adm2, aqe2, 1, 1, \u2603, 1, 4, \u2603, afi.X.Q(), afi.X.Q(), false);
                this.a(adm2, aqe2, 12, 1, \u2603, 12, 4, \u2603, afi.X.Q(), afi.X.Q(), false);
                if (!this.b) continue;
                this.a(adm2, aqe2, 1, 6, \u2603, 1, 9, \u2603, afi.X.Q(), afi.X.Q(), false);
                this.a(adm2, aqe2, 12, 6, \u2603, 12, 9, \u2603, afi.X.Q(), afi.X.Q(), false);
            }
            for (\u2603 = 3; \u2603 < 12; \u2603 += 2) {
                this.a(adm2, aqe2, 3, 1, \u2603, 4, 3, \u2603, afi.X.Q(), afi.X.Q(), false);
                this.a(adm2, aqe2, 6, 1, \u2603, 7, 3, \u2603, afi.X.Q(), afi.X.Q(), false);
                this.a(adm2, aqe2, 9, 1, \u2603, 10, 3, \u2603, afi.X.Q(), afi.X.Q(), false);
            }
            if (this.b) {
                this.a(adm2, aqe2, 1, 5, 1, 3, 5, 13, afi.f.Q(), afi.f.Q(), false);
                this.a(adm2, aqe2, 10, 5, 1, 12, 5, 13, afi.f.Q(), afi.f.Q(), false);
                this.a(adm2, aqe2, 4, 5, 1, 9, 5, 2, afi.f.Q(), afi.f.Q(), false);
                this.a(adm2, aqe2, 4, 5, 12, 9, 5, 13, afi.f.Q(), afi.f.Q(), false);
                this.a(adm2, afi.f.Q(), 9, 5, 11, aqe2);
                this.a(adm2, afi.f.Q(), 8, 5, 11, aqe2);
                this.a(adm2, afi.f.Q(), 9, 5, 10, aqe2);
                this.a(adm2, aqe2, 3, 6, 2, 3, 6, 12, afi.aO.Q(), afi.aO.Q(), false);
                this.a(adm2, aqe2, 10, 6, 2, 10, 6, 10, afi.aO.Q(), afi.aO.Q(), false);
                this.a(adm2, aqe2, 4, 6, 2, 9, 6, 2, afi.aO.Q(), afi.aO.Q(), false);
                this.a(adm2, aqe2, 4, 6, 12, 8, 6, 12, afi.aO.Q(), afi.aO.Q(), false);
                this.a(adm2, afi.aO.Q(), 9, 6, 11, aqe2);
                this.a(adm2, afi.aO.Q(), 8, 6, 11, aqe2);
                this.a(adm2, afi.aO.Q(), 9, 6, 10, aqe2);
                \u2603 = this.a(afi.au, 3);
                this.a(adm2, afi.au.a(\u2603), 10, 1, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 2, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 3, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 4, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 5, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 6, 13, aqe2);
                this.a(adm2, afi.au.a(\u2603), 10, 7, 13, aqe2);
                \u2603 = 7;
                \u2603 = 7;
                this.a(adm2, afi.aO.Q(), \u2603 - 1, 9, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603, 9, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 - 1, 8, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603, 8, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 - 1, 7, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603, 7, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 - 2, 7, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 + 1, 7, \u2603, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 - 1, 7, \u2603 - 1, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603 - 1, 7, \u2603 + 1, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603, 7, \u2603 - 1, aqe2);
                this.a(adm2, afi.aO.Q(), \u2603, 7, \u2603 + 1, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603 - 2, 8, \u2603, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603 + 1, 8, \u2603, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603 - 1, 8, \u2603 - 1, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603 - 1, 8, \u2603 + 1, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603, 8, \u2603 - 1, aqe2);
                this.a(adm2, afi.aa.Q(), \u2603, 8, \u2603 + 1, aqe2);
            }
            this.a(adm2, aqe2, random, 3, 3, 5, ob.a(a, zy.cd.a(random, 1, 5, 2)), 1 + random.nextInt(4));
            if (this.b) {
                this.a(adm2, afi.a.Q(), 12, 9, 1, aqe2);
                this.a(adm2, aqe2, random, 12, 8, 1, ob.a(a, zy.cd.a(random, 1, 5, 2)), 1 + random.nextInt(4));
            }
            return true;
        }
    }

    public static class h
    extends p {
        public h() {
        }

        public h(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((m)aqt2, list, random, 1, 1);
        }

        public static h a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -1, 0, 9, 5, 11, cq2);
            if (!h.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new h(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 8, 4, 10, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 1, 0);
            this.a(adm2, aqe2, 1, 1, 10, 3, 3, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 4, 1, 1, 4, 3, 1, false, random, e);
            this.a(adm2, aqe2, 4, 1, 3, 4, 3, 3, false, random, e);
            this.a(adm2, aqe2, 4, 1, 7, 4, 3, 7, false, random, e);
            this.a(adm2, aqe2, 4, 1, 9, 4, 3, 9, false, random, e);
            this.a(adm2, aqe2, 4, 1, 4, 4, 3, 6, afi.bi.Q(), afi.bi.Q(), false);
            this.a(adm2, aqe2, 5, 1, 5, 7, 3, 5, afi.bi.Q(), afi.bi.Q(), false);
            this.a(adm2, afi.bi.Q(), 4, 3, 2, aqe2);
            this.a(adm2, afi.bi.Q(), 4, 3, 8, aqe2);
            this.a(adm2, afi.aA.a(this.a(afi.aA, 3)), 4, 1, 2, aqe2);
            this.a(adm2, afi.aA.a(this.a(afi.aA, 3) + 8), 4, 2, 2, aqe2);
            this.a(adm2, afi.aA.a(this.a(afi.aA, 3)), 4, 1, 8, aqe2);
            this.a(adm2, afi.aA.a(this.a(afi.aA, 3) + 8), 4, 2, 8, aqe2);
            return true;
        }
    }

    public static class j
    extends p {
        private static final List<ob> b = Lists.newArrayList(new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 1, 3, 5), new ob(zy.aC, 0, 4, 9, 5), new ob(zy.h, 0, 3, 8, 10), new ob(zy.P, 0, 1, 3, 15), new ob(zy.e, 0, 1, 3, 15), new ob(zy.b, 0, 1, 1, 1));
        protected int a;

        public j() {
        }

        public j(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
            this.a = random.nextInt(5);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Type", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.f("Type");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((m)aqt2, list, random, 4, 1);
            this.b((m)aqt2, list, random, 1, 4);
            this.c((m)aqt2, list, random, 1, 4);
        }

        public static j a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -4, -1, 0, 11, 7, 11, cq2);
            if (!j.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new j(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            if (this.a(adm22, aqe2)) {
                return false;
            }
            this.a(adm22, aqe2, 0, 0, 0, 10, 6, 10, true, random, e);
            this.a(adm22, random, aqe2, this.d, 4, 1, 0);
            this.a(adm22, aqe2, 4, 1, 10, 6, 3, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 1, 4, 0, 3, 6, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 10, 1, 4, 10, 3, 6, afi.a.Q(), afi.a.Q(), false);
            switch (this.a) {
                default: {
                    break;
                }
                case 0: {
                    this.a(adm22, afi.bf.Q(), 5, 1, 5, aqe2);
                    this.a(adm22, afi.bf.Q(), 5, 2, 5, aqe2);
                    this.a(adm22, afi.bf.Q(), 5, 3, 5, aqe2);
                    this.a(adm22, afi.aa.Q(), 4, 3, 5, aqe2);
                    this.a(adm22, afi.aa.Q(), 6, 3, 5, aqe2);
                    this.a(adm22, afi.aa.Q(), 5, 3, 4, aqe2);
                    this.a(adm22, afi.aa.Q(), 5, 3, 6, aqe2);
                    this.a(adm22, afi.U.Q(), 4, 1, 4, aqe2);
                    this.a(adm22, afi.U.Q(), 4, 1, 5, aqe2);
                    this.a(adm22, afi.U.Q(), 4, 1, 6, aqe2);
                    this.a(adm22, afi.U.Q(), 6, 1, 4, aqe2);
                    this.a(adm22, afi.U.Q(), 6, 1, 5, aqe2);
                    this.a(adm22, afi.U.Q(), 6, 1, 6, aqe2);
                    this.a(adm22, afi.U.Q(), 5, 1, 4, aqe2);
                    this.a(adm22, afi.U.Q(), 5, 1, 6, aqe2);
                    break;
                }
                case 1: {
                    adm adm22;
                    for (int i2 = 0; i2 < 5; ++i2) {
                        this.a(adm22, afi.bf.Q(), 3, 1, 3 + i2, aqe2);
                        this.a(adm22, afi.bf.Q(), 7, 1, 3 + i2, aqe2);
                        this.a(adm22, afi.bf.Q(), 3 + i2, 1, 3, aqe2);
                        this.a(adm22, afi.bf.Q(), 3 + i2, 1, 7, aqe2);
                    }
                    this.a(adm22, afi.bf.Q(), 5, 1, 5, aqe2);
                    this.a(adm22, afi.bf.Q(), 5, 2, 5, aqe2);
                    this.a(adm22, afi.bf.Q(), 5, 3, 5, aqe2);
                    this.a(adm22, afi.i.Q(), 5, 4, 5, aqe2);
                    break;
                }
                case 2: {
                    int n2;
                    adm adm22;
                    for (n2 = 1; n2 <= 9; ++n2) {
                        this.a(adm22, afi.e.Q(), 1, 3, n2, aqe2);
                        this.a(adm22, afi.e.Q(), 9, 3, n2, aqe2);
                    }
                    for (n2 = 1; n2 <= 9; ++n2) {
                        this.a(adm22, afi.e.Q(), n2, 3, 1, aqe2);
                        this.a(adm22, afi.e.Q(), n2, 3, 9, aqe2);
                    }
                    this.a(adm22, afi.e.Q(), 5, 1, 4, aqe2);
                    this.a(adm22, afi.e.Q(), 5, 1, 6, aqe2);
                    this.a(adm22, afi.e.Q(), 5, 3, 4, aqe2);
                    this.a(adm22, afi.e.Q(), 5, 3, 6, aqe2);
                    this.a(adm22, afi.e.Q(), 4, 1, 5, aqe2);
                    this.a(adm22, afi.e.Q(), 6, 1, 5, aqe2);
                    this.a(adm22, afi.e.Q(), 4, 3, 5, aqe2);
                    this.a(adm22, afi.e.Q(), 6, 3, 5, aqe2);
                    for (n2 = 1; n2 <= 3; ++n2) {
                        this.a(adm22, afi.e.Q(), 4, n2, 4, aqe2);
                        this.a(adm22, afi.e.Q(), 6, n2, 4, aqe2);
                        this.a(adm22, afi.e.Q(), 4, n2, 6, aqe2);
                        this.a(adm22, afi.e.Q(), 6, n2, 6, aqe2);
                    }
                    this.a(adm22, afi.aa.Q(), 5, 3, 5, aqe2);
                    for (n2 = 2; n2 <= 8; ++n2) {
                        this.a(adm22, afi.f.Q(), 2, 3, n2, aqe2);
                        this.a(adm22, afi.f.Q(), 3, 3, n2, aqe2);
                        if (n2 <= 3 || n2 >= 7) {
                            this.a(adm22, afi.f.Q(), 4, 3, n2, aqe2);
                            this.a(adm22, afi.f.Q(), 5, 3, n2, aqe2);
                            this.a(adm22, afi.f.Q(), 6, 3, n2, aqe2);
                        }
                        this.a(adm22, afi.f.Q(), 7, 3, n2, aqe2);
                        this.a(adm22, afi.f.Q(), 8, 3, n2, aqe2);
                    }
                    this.a(adm22, afi.au.a(this.a(afi.au, cq.e.a())), 9, 1, 3, aqe2);
                    this.a(adm22, afi.au.a(this.a(afi.au, cq.e.a())), 9, 2, 3, aqe2);
                    this.a(adm22, afi.au.a(this.a(afi.au, cq.e.a())), 9, 3, 3, aqe2);
                    this.a(adm22, aqe2, random, 3, 4, 8, ob.a(b, zy.cd.b(random)), 1 + random.nextInt(4));
                }
            }
            return true;
        }
    }

    public static class i
    extends d {
        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            if (this.m == cq.c || this.m == cq.f) {
                this.c((m)aqt2, list, random, 1, 1);
            } else {
                this.b((m)aqt2, list, random, 1, 1);
            }
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 4, 4, 4, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 1, 0);
            if (this.m == cq.c || this.m == cq.f) {
                this.a(adm2, aqe2, 4, 1, 1, 4, 3, 3, afi.a.Q(), afi.a.Q(), false);
            } else {
                this.a(adm2, aqe2, 0, 1, 1, 0, 3, 3, afi.a.Q(), afi.a.Q(), false);
            }
            return true;
        }
    }

    public static class d
    extends p {
        public d() {
        }

        public d(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            if (this.m == cq.c || this.m == cq.f) {
                this.b((m)aqt2, list, random, 1, 1);
            } else {
                this.c((m)aqt2, list, random, 1, 1);
            }
        }

        public static d a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, 5, cq2);
            if (!aqp$d.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new d(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 4, 4, 4, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 1, 0);
            if (this.m == cq.c || this.m == cq.f) {
                this.a(adm2, aqe2, 0, 1, 1, 0, 3, 3, afi.a.Q(), afi.a.Q(), false);
            } else {
                this.a(adm2, aqe2, 4, 1, 1, 4, 3, 3, afi.a.Q(), afi.a.Q(), false);
            }
            return true;
        }
    }

    public static class o
    extends p {
        public o() {
        }

        public o(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((m)aqt2, list, random, 1, 1);
        }

        public static o a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -7, 0, 5, 11, 8, cq2);
            if (!o.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new o(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 4, 10, 7, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 7, 0);
            this.a(adm2, random, aqe2, p.a.a, 1, 1, 7);
            int n2 = this.a(afi.aw, 2);
            for (\u2603 = 0; \u2603 < 6; ++\u2603) {
                this.a(adm2, afi.aw.a(n2), 1, 6 - \u2603, 1 + \u2603, aqe2);
                this.a(adm2, afi.aw.a(n2), 2, 6 - \u2603, 1 + \u2603, aqe2);
                this.a(adm2, afi.aw.a(n2), 3, 6 - \u2603, 1 + \u2603, aqe2);
                if (\u2603 >= 5) continue;
                this.a(adm2, afi.bf.Q(), 1, 5 - \u2603, 1 + \u2603, aqe2);
                this.a(adm2, afi.bf.Q(), 2, 5 - \u2603, 1 + \u2603, aqe2);
                this.a(adm2, afi.bf.Q(), 3, 5 - \u2603, 1 + \u2603, aqe2);
            }
            return true;
        }
    }

    public static class a
    extends p {
        private static final List<ob> a = Lists.newArrayList(new ob(zy.bu, 0, 1, 1, 10), new ob(zy.i, 0, 1, 3, 3), new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 1, 3, 5), new ob(zy.aC, 0, 4, 9, 5), new ob(zy.P, 0, 1, 3, 15), new ob(zy.e, 0, 1, 3, 15), new ob(zy.b, 0, 1, 1, 5), new ob(zy.l, 0, 1, 1, 5), new ob(zy.Z, 0, 1, 1, 5), new ob(zy.Y, 0, 1, 1, 5), new ob(zy.aa, 0, 1, 1, 5), new ob(zy.ab, 0, 1, 1, 5), new ob(zy.ao, 0, 1, 1, 1), new ob(zy.aA, 0, 1, 1, 1), new ob(zy.ck, 0, 1, 1, 1), new ob(zy.cl, 0, 1, 1, 1), new ob(zy.cm, 0, 1, 1, 1));
        private boolean b;

        public a() {
        }

        public a(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
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
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((m)aqt2, list, random, 1, 1);
        }

        public static a a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, 7, cq2);
            if (!aqp$a.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new a(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe22) {
            aqe aqe22;
            if (this.a(adm2, aqe22)) {
                return false;
            }
            this.a(adm2, aqe22, 0, 0, 0, 4, 4, 6, true, random, e);
            this.a(adm2, random, aqe22, this.d, 1, 1, 0);
            this.a(adm2, random, aqe22, p.a.a, 1, 1, 6);
            this.a(adm2, aqe22, 3, 1, 2, 3, 1, 4, afi.bf.Q(), afi.bf.Q(), false);
            this.a(adm2, afi.U.a(akb.a.f.a()), 3, 1, 1, aqe22);
            this.a(adm2, afi.U.a(akb.a.f.a()), 3, 1, 5, aqe22);
            this.a(adm2, afi.U.a(akb.a.f.a()), 3, 2, 2, aqe22);
            this.a(adm2, afi.U.a(akb.a.f.a()), 3, 2, 4, aqe22);
            for (int i2 = 2; i2 <= 4; ++i2) {
                this.a(adm2, afi.U.a(akb.a.f.a()), 2, 1, i2, aqe22);
            }
            if (!this.b && aqe22.b(new cj(this.a(3, 3), this.d(2), this.b(3, 3)))) {
                this.b = true;
                this.a(adm2, aqe22, random, 3, 2, 3, ob.a(a, zy.cd.b(random)), 2 + random.nextInt(2));
            }
            return true;
        }
    }

    public static class n
    extends p {
        private boolean a;
        private boolean b;

        public n() {
        }

        public n(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
            this.a = random.nextInt(2) == 0;
            this.b = random.nextInt(2) == 0;
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Left", this.a);
            dn2.a("Right", this.b);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.n("Left");
            this.b = dn2.n("Right");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((m)aqt2, list, random, 1, 1);
            if (this.a) {
                this.b((m)aqt2, list, random, 1, 2);
            }
            if (this.b) {
                this.c((m)aqt2, list, random, 1, 2);
            }
        }

        public static n a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, 7, cq2);
            if (!n.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new n(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 4, 4, 6, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 1, 0);
            this.a(adm2, random, aqe2, p.a.a, 1, 1, 6);
            this.a(adm2, aqe2, random, 0.1f, 1, 2, 1, afi.aa.Q());
            this.a(adm2, aqe2, random, 0.1f, 3, 2, 1, afi.aa.Q());
            this.a(adm2, aqe2, random, 0.1f, 1, 2, 5, afi.aa.Q());
            this.a(adm2, aqe2, random, 0.1f, 3, 2, 5, afi.aa.Q());
            if (this.a) {
                this.a(adm2, aqe2, 0, 1, 2, 0, 3, 4, afi.a.Q(), afi.a.Q(), false);
            }
            if (this.b) {
                this.a(adm2, aqe2, 4, 1, 2, 4, 3, 4, afi.a.Q(), afi.a.Q(), false);
            }
            return true;
        }
    }

    public static class m
    extends l {
        public f a;
        public g b;
        public List<aqt> c = Lists.newArrayList();

        public m() {
        }

        public m(int n2, Random random, int n3, int n4) {
            super(0, random, n3, n4);
        }

        @Override
        public cj a() {
            if (this.b != null) {
                return this.b.a();
            }
            return super.a();
        }
    }

    public static class l
    extends p {
        private boolean a;

        public l() {
        }

        public l(int n2, Random random, int n3, int n4) {
            super(n2);
            this.a = true;
            this.m = cq.c.a.a(random);
            this.d = p.a.a;
            switch (this.m) {
                case c: 
                case d: {
                    this.l = new aqe(n3, 64, n4, n3 + 5 - 1, 74, n4 + 5 - 1);
                    break;
                }
                default: {
                    this.l = new aqe(n3, 64, n4, n3 + 5 - 1, 74, n4 + 5 - 1);
                }
            }
        }

        public l(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.a = false;
            this.m = cq2;
            this.d = this.a(random);
            this.l = aqe2;
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Source", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.n("Source");
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            if (this.a) {
                d = c.class;
            }
            this.a((m)aqt2, list, random, 1, 1);
        }

        public static l a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -7, 0, 5, 11, 5, cq2);
            if (!l.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new l(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 0, 0, 4, 10, 4, true, random, e);
            this.a(adm2, random, aqe2, this.d, 1, 7, 0);
            this.a(adm2, random, aqe2, p.a.a, 1, 1, 4);
            this.a(adm2, afi.bf.Q(), 2, 6, 1, aqe2);
            this.a(adm2, afi.bf.Q(), 1, 5, 1, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 1, 6, 1, aqe2);
            this.a(adm2, afi.bf.Q(), 1, 5, 2, aqe2);
            this.a(adm2, afi.bf.Q(), 1, 4, 3, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 1, 5, 3, aqe2);
            this.a(adm2, afi.bf.Q(), 2, 4, 3, aqe2);
            this.a(adm2, afi.bf.Q(), 3, 3, 3, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 3, 4, 3, aqe2);
            this.a(adm2, afi.bf.Q(), 3, 3, 2, aqe2);
            this.a(adm2, afi.bf.Q(), 3, 2, 1, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 3, 3, 1, aqe2);
            this.a(adm2, afi.bf.Q(), 2, 2, 1, aqe2);
            this.a(adm2, afi.bf.Q(), 1, 1, 1, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 1, 2, 1, aqe2);
            this.a(adm2, afi.bf.Q(), 1, 1, 2, aqe2);
            this.a(adm2, afi.U.a(akb.a.a.a()), 1, 1, 3, aqe2);
            return true;
        }
    }

    public static class b
    extends p {
        private int a;

        public b() {
        }

        public b(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = cq2 == cq.c || cq2 == cq.d ? aqe2.e() : aqe2.c();
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Steps", this.a);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.a = dn2.f("Steps");
        }

        public static aqe a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            int n5 = 3;
            aqe \u26032 = aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, 4, cq2);
            aqt \u26033 = aqt.a(list, \u26032);
            if (\u26033 == null) {
                return null;
            }
            if (\u26033.c().b == \u26032.b) {
                for (\u2603 = 3; \u2603 >= 1; --\u2603) {
                    \u26032 = aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, \u2603 - 1, cq2);
                    if (\u26033.c().a(\u26032)) continue;
                    return aqe.a(n2, n3, n4, -1, -1, 0, 5, 5, \u2603, cq2);
                }
            }
            return null;
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            for (int i2 = 0; i2 < this.a; ++i2) {
                this.a(adm2, afi.bf.Q(), 0, 0, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 1, 0, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 2, 0, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 3, 0, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 4, 0, i2, aqe2);
                for (\u2603 = 1; \u2603 <= 3; ++\u2603) {
                    this.a(adm2, afi.bf.Q(), 0, \u2603, i2, aqe2);
                    this.a(adm2, afi.a.Q(), 1, \u2603, i2, aqe2);
                    this.a(adm2, afi.a.Q(), 2, \u2603, i2, aqe2);
                    this.a(adm2, afi.a.Q(), 3, \u2603, i2, aqe2);
                    this.a(adm2, afi.bf.Q(), 4, \u2603, i2, aqe2);
                }
                this.a(adm2, afi.bf.Q(), 0, 4, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 1, 4, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 2, 4, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 3, 4, i2, aqe2);
                this.a(adm2, afi.bf.Q(), 4, 4, i2, aqe2);
            }
            return true;
        }
    }

    static abstract class p
    extends aqt {
        protected a d = a.a;

        public p() {
        }

        protected p(int n2) {
            super(n2);
        }

        @Override
        protected void a(dn dn2) {
            dn2.a("EntryDoor", this.d.name());
        }

        @Override
        protected void b(dn dn2) {
            this.d = a.valueOf(dn2.j("EntryDoor"));
        }

        protected void a(adm adm2, Random random, aqe aqe2, a a2, int n2, int n3, int n4) {
            switch (a2) {
                default: {
                    this.a(adm2, aqe2, n2, n3, n4, n2 + 3 - 1, n3 + 3 - 1, n4, afi.a.Q(), afi.a.Q(), false);
                    break;
                }
                case b: {
                    this.a(adm2, afi.bf.Q(), n2, n3, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 1, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3, n4, aqe2);
                    this.a(adm2, afi.ao.Q(), n2 + 1, n3, n4, aqe2);
                    this.a(adm2, afi.ao.a(8), n2 + 1, n3 + 1, n4, aqe2);
                    break;
                }
                case c: {
                    this.a(adm2, afi.a.Q(), n2 + 1, n3, n4, aqe2);
                    this.a(adm2, afi.a.Q(), n2 + 1, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2, n3, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2 + 1, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2 + 2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2 + 2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bi.Q(), n2 + 2, n3, n4, aqe2);
                    break;
                }
                case d: {
                    this.a(adm2, afi.bf.Q(), n2, n3, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 1, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3 + 2, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.bf.Q(), n2 + 2, n3, n4, aqe2);
                    this.a(adm2, afi.aA.Q(), n2 + 1, n3, n4, aqe2);
                    this.a(adm2, afi.aA.a(8), n2 + 1, n3 + 1, n4, aqe2);
                    this.a(adm2, afi.aG.a(this.a(afi.aG, 4)), n2 + 2, n3 + 1, n4 + 1, aqe2);
                    this.a(adm2, afi.aG.a(this.a(afi.aG, 3)), n2 + 2, n3 + 1, n4 - 1, aqe2);
                }
            }
        }

        protected a a(Random random) {
            int n2 = random.nextInt(5);
            switch (n2) {
                default: {
                    return a.a;
                }
                case 2: {
                    return a.b;
                }
                case 3: {
                    return a.c;
                }
                case 4: 
            }
            return a.d;
        }

        protected aqt a(m m2, List<aqt> list, Random random, int n2, int n3) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return aqp.c(m2, list, random, this.l.a + n2, this.l.b + n3, this.l.c - 1, this.m, this.d());
                    }
                    case d: {
                        return aqp.c(m2, list, random, this.l.a + n2, this.l.b + n3, this.l.f + 1, this.m, this.d());
                    }
                    case e: {
                        return aqp.c(m2, list, random, this.l.a - 1, this.l.b + n3, this.l.c + n2, this.m, this.d());
                    }
                    case f: {
                        return aqp.c(m2, list, random, this.l.d + 1, this.l.b + n3, this.l.c + n2, this.m, this.d());
                    }
                }
            }
            return null;
        }

        protected aqt b(m m2, List<aqt> list, Random random, int n2, int n3) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return aqp.c(m2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d());
                    }
                    case d: {
                        return aqp.c(m2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d());
                    }
                    case e: {
                        return aqp.c(m2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d());
                    }
                    case f: {
                        return aqp.c(m2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d());
                    }
                }
            }
            return null;
        }

        protected aqt c(m m2, List<aqt> list, Random random, int n2, int n3) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return aqp.c(m2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d());
                    }
                    case d: {
                        return aqp.c(m2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d());
                    }
                    case e: {
                        return aqp.c(m2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d());
                    }
                    case f: {
                        return aqp.c(m2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d());
                    }
                }
            }
            return null;
        }

        protected static boolean a(aqe aqe2) {
            return aqe2 != null && aqe2.b > 10;
        }

        public static enum a {
            a,
            b,
            c,
            d;

        }
    }

    static class f {
        public Class<? extends p> a;
        public final int b;
        public int c;
        public int d;

        public f(Class<? extends p> clazz, int n2, int n3) {
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

