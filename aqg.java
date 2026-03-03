/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqg {
    private static final List<ob> a = Lists.newArrayList(new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 1, 3, 5), new ob(zy.aC, 0, 4, 9, 5), new ob(zy.aW, zd.l.b(), 4, 9, 5), new ob(zy.i, 0, 1, 2, 3), new ob(zy.h, 0, 3, 8, 10), new ob(zy.P, 0, 1, 3, 15), new ob(zy.b, 0, 1, 1, 1), new ob(zw.a(afi.av), 0, 4, 8, 1), new ob(zy.bh, 0, 2, 4, 10), new ob(zy.bg, 0, 2, 4, 10), new ob(zy.aA, 0, 1, 1, 3), new ob(zy.ck, 0, 1, 1, 1));

    public static void a() {
        aqr.a(a.class, "MSCorridor");
        aqr.a(b.class, "MSCrossing");
        aqr.a(c.class, "MSRoom");
        aqr.a(d.class, "MSStairs");
    }

    private static aqt a(List<aqt> list2, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        int n6;
        n6 = random.nextInt(100);
        if (n6 >= 80) {
            aqe aqe2 = b.a(list2, random, n2, n3, n4, cq2);
            if (aqe2 != null) {
                return new b(n5, random, aqe2, cq2);
            }
        } else if (n6 >= 70) {
            aqe aqe3 = d.a(list2, random, n2, n3, n4, cq2);
            if (aqe3 != null) {
                return new d(n5, random, aqe3, cq2);
            }
        } else {
            List<aqt> list2;
            aqe \u26032 = aqg$a.a(list2, random, n2, n3, n4, cq2);
            if (\u26032 != null) {
                return new a(n5, random, \u26032, cq2);
            }
        }
        return null;
    }

    private static aqt b(aqt aqt2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
        if (n5 > 8) {
            return null;
        }
        if (Math.abs(n2 - aqt2.c().a) > 80 || Math.abs(n4 - aqt2.c().c) > 80) {
            return null;
        }
        aqt aqt3 = aqg.a(list, random, n2, n3, n4, cq2, n5 + 1);
        if (aqt3 != null) {
            list.add(aqt3);
            aqt3.a(aqt2, list, random);
        }
        return aqt3;
    }

    public static class d
    extends aqt {
        public d() {
        }

        public d(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        protected void a(dn dn2) {
        }

        @Override
        protected void b(dn dn2) {
        }

        public static aqe a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            aqe aqe2 = new aqe(n2, n3 - 5, n4, n2, n3 + 2, n4);
            switch (cq2) {
                case c: {
                    aqe2.d = n2 + 2;
                    aqe2.c = n4 - 8;
                    break;
                }
                case d: {
                    aqe2.d = n2 + 2;
                    aqe2.f = n4 + 8;
                    break;
                }
                case e: {
                    aqe2.a = n2 - 8;
                    aqe2.f = n4 + 2;
                    break;
                }
                case f: {
                    aqe2.d = n2 + 8;
                    aqe2.f = n4 + 2;
                }
            }
            if (aqt.a(list, aqe2) != null) {
                return null;
            }
            return aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            int n2 = this.d();
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        aqg.b(aqt2, list, random, this.l.a, this.l.b, this.l.c - 1, cq.c, n2);
                        break;
                    }
                    case d: {
                        aqg.b(aqt2, list, random, this.l.a, this.l.b, this.l.f + 1, cq.d, n2);
                        break;
                    }
                    case e: {
                        aqg.b(aqt2, list, random, this.l.a - 1, this.l.b, this.l.c, cq.e, n2);
                        break;
                    }
                    case f: {
                        aqg.b(aqt2, list, random, this.l.d + 1, this.l.b, this.l.c, cq.f, n2);
                    }
                }
            }
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, 0, 5, 0, 2, 7, 1, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 0, 7, 2, 2, 8, afi.a.Q(), afi.a.Q(), false);
            for (int i2 = 0; i2 < 5; ++i2) {
                this.a(adm2, aqe2, 0, 5 - i2 - (i2 < 4 ? 1 : 0), 2 + i2, 2, 7 - i2, 2 + i2, afi.a.Q(), afi.a.Q(), false);
            }
            return true;
        }
    }

    public static class b
    extends aqt {
        private cq a;
        private boolean b;

        public b() {
        }

        @Override
        protected void a(dn dn2) {
            dn2.a("tf", this.b);
            dn2.a("D", this.a.b());
        }

        @Override
        protected void b(dn dn2) {
            this.b = dn2.n("tf");
            this.a = cq.b(dn2.f("D"));
        }

        public b(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.a = cq2;
            this.l = aqe2;
            this.b = aqe2.d() > 3;
        }

        public static aqe a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            aqe aqe2 = new aqe(n2, n3, n4, n2, n3 + 2, n4);
            if (random.nextInt(4) == 0) {
                aqe2.e += 4;
            }
            switch (cq2) {
                case c: {
                    aqe2.a = n2 - 1;
                    aqe2.d = n2 + 3;
                    aqe2.c = n4 - 4;
                    break;
                }
                case d: {
                    aqe2.a = n2 - 1;
                    aqe2.d = n2 + 3;
                    aqe2.f = n4 + 4;
                    break;
                }
                case e: {
                    aqe2.a = n2 - 4;
                    aqe2.c = n4 - 1;
                    aqe2.f = n4 + 3;
                    break;
                }
                case f: {
                    aqe2.d = n2 + 4;
                    aqe2.c = n4 - 1;
                    aqe2.f = n4 + 3;
                }
            }
            if (aqt.a(list, aqe2) != null) {
                return null;
            }
            return aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            int n2 = this.d();
            switch (this.a) {
                case c: {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, n2);
                    aqg.b(aqt2, list, random, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, n2);
                    aqg.b(aqt2, list, random, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, n2);
                    break;
                }
                case d: {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, n2);
                    aqg.b(aqt2, list, random, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, n2);
                    aqg.b(aqt2, list, random, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, n2);
                    break;
                }
                case e: {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, n2);
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, n2);
                    aqg.b(aqt2, list, random, this.l.a - 1, this.l.b, this.l.c + 1, cq.e, n2);
                    break;
                }
                case f: {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.c - 1, cq.c, n2);
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b, this.l.f + 1, cq.d, n2);
                    aqg.b(aqt2, list, random, this.l.d + 1, this.l.b, this.l.c + 1, cq.f, n2);
                }
            }
            if (this.b) {
                if (random.nextBoolean()) {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b + 3 + 1, this.l.c - 1, cq.c, n2);
                }
                if (random.nextBoolean()) {
                    aqg.b(aqt2, list, random, this.l.a - 1, this.l.b + 3 + 1, this.l.c + 1, cq.e, n2);
                }
                if (random.nextBoolean()) {
                    aqg.b(aqt2, list, random, this.l.d + 1, this.l.b + 3 + 1, this.l.c + 1, cq.f, n2);
                }
                if (random.nextBoolean()) {
                    aqg.b(aqt2, list, random, this.l.a + 1, this.l.b + 3 + 1, this.l.f + 1, cq.d, n2);
                }
            }
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            if (this.b) {
                this.a(adm2, aqe2, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.b + 3 - 1, this.l.f, afi.a.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.b + 3 - 1, this.l.f - 1, afi.a.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, this.l.a + 1, this.l.e - 2, this.l.c, this.l.d - 1, this.l.e, this.l.f, afi.a.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, this.l.a, this.l.e - 2, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, afi.a.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, this.l.a + 1, this.l.b + 3, this.l.c + 1, this.l.d - 1, this.l.b + 3, this.l.f - 1, afi.a.Q(), afi.a.Q(), false);
            } else {
                this.a(adm2, aqe2, this.l.a + 1, this.l.b, this.l.c, this.l.d - 1, this.l.e, this.l.f, afi.a.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, this.l.a, this.l.b, this.l.c + 1, this.l.d, this.l.e, this.l.f - 1, afi.a.Q(), afi.a.Q(), false);
            }
            this.a(adm2, aqe2, this.l.a + 1, this.l.b, this.l.c + 1, this.l.a + 1, this.l.e, this.l.c + 1, afi.f.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.l.a + 1, this.l.b, this.l.f - 1, this.l.a + 1, this.l.e, this.l.f - 1, afi.f.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.l.d - 1, this.l.b, this.l.c + 1, this.l.d - 1, this.l.e, this.l.c + 1, afi.f.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.l.d - 1, this.l.b, this.l.f - 1, this.l.d - 1, this.l.e, this.l.f - 1, afi.f.Q(), afi.a.Q(), false);
            for (int i2 = this.l.a; i2 <= this.l.d; ++i2) {
                for (\u2603 = this.l.c; \u2603 <= this.l.f; ++\u2603) {
                    if (this.a(adm2, i2, this.l.b - 1, \u2603, aqe2).c().t() != arm.a) continue;
                    this.a(adm2, afi.f.Q(), i2, this.l.b - 1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class a
    extends aqt {
        private boolean a;
        private boolean b;
        private boolean c;
        private int d;

        public a() {
        }

        @Override
        protected void a(dn dn2) {
            dn2.a("hr", this.a);
            dn2.a("sc", this.b);
            dn2.a("hps", this.c);
            dn2.a("Num", this.d);
        }

        @Override
        protected void b(dn dn2) {
            this.a = dn2.n("hr");
            this.b = dn2.n("sc");
            this.c = dn2.n("hps");
            this.d = dn2.f("Num");
        }

        public a(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
            this.a = random.nextInt(3) == 0;
            this.b = !this.a && random.nextInt(23) == 0;
            this.d = this.m == cq.c || this.m == cq.d ? aqe2.e() / 5 : aqe2.c() / 5;
        }

        public static aqe a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2) {
            int n5;
            aqe aqe2 = new aqe(n2, n3, n4, n2, n3 + 2, n4);
            for (n5 = random.nextInt(3) + 2; n5 > 0; --n5) {
                \u2603 = n5 * 5;
                switch (cq2) {
                    case c: {
                        aqe2.d = n2 + 2;
                        aqe2.c = n4 - (\u2603 - 1);
                        break;
                    }
                    case d: {
                        aqe2.d = n2 + 2;
                        aqe2.f = n4 + (\u2603 - 1);
                        break;
                    }
                    case e: {
                        aqe2.a = n2 - (\u2603 - 1);
                        aqe2.f = n4 + 2;
                        break;
                    }
                    case f: {
                        aqe2.d = n2 + (\u2603 - 1);
                        aqe2.f = n4 + 2;
                    }
                }
                if (aqt.a(list, aqe2) == null) break;
            }
            if (n5 > 0) {
                return aqe2;
            }
            return null;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            block25: {
                int n2 = this.d();
                \u2603 = random.nextInt(4);
                if (this.m != null) {
                    switch (this.m) {
                        case c: {
                            if (\u2603 <= 1) {
                                aqg.b(aqt2, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.c - 1, this.m, n2);
                                break;
                            }
                            if (\u2603 == 2) {
                                aqg.b(aqt2, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.c, cq.e, n2);
                                break;
                            }
                            aqg.b(aqt2, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.c, cq.f, n2);
                            break;
                        }
                        case d: {
                            if (\u2603 <= 1) {
                                aqg.b(aqt2, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.f + 1, this.m, n2);
                                break;
                            }
                            if (\u2603 == 2) {
                                aqg.b(aqt2, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.f - 3, cq.e, n2);
                                break;
                            }
                            aqg.b(aqt2, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.f - 3, cq.f, n2);
                            break;
                        }
                        case e: {
                            if (\u2603 <= 1) {
                                aqg.b(aqt2, list, random, this.l.a - 1, this.l.b - 1 + random.nextInt(3), this.l.c, this.m, n2);
                                break;
                            }
                            if (\u2603 == 2) {
                                aqg.b(aqt2, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.c - 1, cq.c, n2);
                                break;
                            }
                            aqg.b(aqt2, list, random, this.l.a, this.l.b - 1 + random.nextInt(3), this.l.f + 1, cq.d, n2);
                            break;
                        }
                        case f: {
                            if (\u2603 <= 1) {
                                aqg.b(aqt2, list, random, this.l.d + 1, this.l.b - 1 + random.nextInt(3), this.l.c, this.m, n2);
                                break;
                            }
                            if (\u2603 == 2) {
                                aqg.b(aqt2, list, random, this.l.d - 3, this.l.b - 1 + random.nextInt(3), this.l.c - 1, cq.c, n2);
                                break;
                            }
                            aqg.b(aqt2, list, random, this.l.d - 3, this.l.b - 1 + random.nextInt(3), this.l.f + 1, cq.d, n2);
                        }
                    }
                }
                if (n2 >= 8) break block25;
                if (this.m == cq.c || this.m == cq.d) {
                    \u2603 = this.l.c + 3;
                    while (\u2603 + 3 <= this.l.f) {
                        \u2603 = random.nextInt(5);
                        if (\u2603 == 0) {
                            aqg.b(aqt2, list, random, this.l.a - 1, this.l.b, \u2603, cq.e, n2 + 1);
                        } else if (\u2603 == 1) {
                            aqg.b(aqt2, list, random, this.l.d + 1, this.l.b, \u2603, cq.f, n2 + 1);
                        }
                        \u2603 += 5;
                    }
                } else {
                    \u2603 = this.l.a + 3;
                    while (\u2603 + 3 <= this.l.d) {
                        \u2603 = random.nextInt(5);
                        if (\u2603 == 0) {
                            aqg.b(aqt2, list, random, \u2603, this.l.b, this.l.c - 1, cq.c, n2 + 1);
                        } else if (\u2603 == 1) {
                            aqg.b(aqt2, list, random, \u2603, this.l.b, this.l.f + 1, cq.d, n2 + 1);
                        }
                        \u2603 += 5;
                    }
                }
            }
        }

        @Override
        protected boolean a(adm adm2, aqe aqe2, Random random, int n2, int n3, int n4, List<ob> list, int n5) {
            cj cj2 = new cj(this.a(n2, n4), this.d(n3), this.b(n2, n4));
            if (aqe2.b(cj2) && adm2.p(cj2).c().t() == arm.a) {
                int n6 = random.nextBoolean() ? 1 : 0;
                adm2.a(cj2, afi.av.a(this.a(afi.av, n6)), 2);
                vb \u26032 = new vb(adm2, (float)cj2.n() + 0.5f, (float)cj2.o() + 0.5f, (float)cj2.p() + 0.5f);
                ob.a(random, list, \u26032, n5);
                adm2.d(\u26032);
                return true;
            }
            return false;
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2;
            if (this.a(adm2, aqe2)) {
                return false;
            }
            boolean bl2 = false;
            int \u26032 = 2;
            \u2603 = false;
            int \u26033 = 2;
            int \u26034 = this.d * 5 - 1;
            this.a(adm2, aqe2, 0, 0, 0, 2, 1, \u26034, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, random, 0.8f, 0, 2, 0, 2, 2, \u26034, afi.a.Q(), afi.a.Q(), false);
            if (this.b) {
                this.a(adm2, aqe2, random, 0.6f, 0, 0, 0, 2, 1, \u26034, afi.G.Q(), afi.a.Q(), false);
            }
            for (n2 = 0; n2 < this.d; ++n2) {
                \u2603 = 2 + n2 * 5;
                this.a(adm2, aqe2, 0, 0, \u2603, 0, 1, \u2603, afi.aO.Q(), afi.a.Q(), false);
                this.a(adm2, aqe2, 2, 0, \u2603, 2, 1, \u2603, afi.aO.Q(), afi.a.Q(), false);
                if (random.nextInt(4) == 0) {
                    this.a(adm2, aqe2, 0, 2, \u2603, 0, 2, \u2603, afi.f.Q(), afi.a.Q(), false);
                    this.a(adm2, aqe2, 2, 2, \u2603, 2, 2, \u2603, afi.f.Q(), afi.a.Q(), false);
                } else {
                    this.a(adm2, aqe2, 0, 2, \u2603, 2, 2, \u2603, afi.f.Q(), afi.a.Q(), false);
                }
                this.a(adm2, aqe2, random, 0.1f, 0, 2, \u2603 - 1, afi.G.Q());
                this.a(adm2, aqe2, random, 0.1f, 2, 2, \u2603 - 1, afi.G.Q());
                this.a(adm2, aqe2, random, 0.1f, 0, 2, \u2603 + 1, afi.G.Q());
                this.a(adm2, aqe2, random, 0.1f, 2, 2, \u2603 + 1, afi.G.Q());
                this.a(adm2, aqe2, random, 0.05f, 0, 2, \u2603 - 2, afi.G.Q());
                this.a(adm2, aqe2, random, 0.05f, 2, 2, \u2603 - 2, afi.G.Q());
                this.a(adm2, aqe2, random, 0.05f, 0, 2, \u2603 + 2, afi.G.Q());
                this.a(adm2, aqe2, random, 0.05f, 2, 2, \u2603 + 2, afi.G.Q());
                this.a(adm2, aqe2, random, 0.05f, 1, 2, \u2603 - 1, afi.aa.a(cq.b.a()));
                this.a(adm2, aqe2, random, 0.05f, 1, 2, \u2603 + 1, afi.aa.a(cq.b.a()));
                if (random.nextInt(100) == 0) {
                    this.a(adm2, aqe2, random, 2, 0, \u2603 - 1, ob.a(a, zy.cd.b(random)), 3 + random.nextInt(4));
                }
                if (random.nextInt(100) == 0) {
                    this.a(adm2, aqe2, random, 0, 0, \u2603 + 1, ob.a(a, zy.cd.b(random)), 3 + random.nextInt(4));
                }
                if (!this.b || this.c) continue;
                \u2603 = this.d(0);
                \u2603 = \u2603 - 1 + random.nextInt(3);
                \u26036 = this.a(1, \u2603);
                cj cj2 = new cj(\u26036, \u2603, \u2603 = this.b(1, \u2603));
                if (!aqe2.b(cj2)) continue;
                this.c = true;
                adm2.a(cj2, afi.ac.Q(), 2);
                akw \u26035 = adm2.s(cj2);
                if (!(\u26035 instanceof all)) continue;
                ((all)\u26035).b().a("CaveSpider");
            }
            for (n2 = 0; n2 <= 2; ++n2) {
                for (\u2603 = 0; \u2603 <= \u26034; ++\u2603) {
                    \u2603 = -1;
                    alz alz2 = this.a(adm2, n2, \u2603, \u2603, aqe2);
                    if (alz2.c().t() != arm.a) continue;
                    int \u26036 = -1;
                    this.a(adm2, afi.f.Q(), n2, \u26036, \u2603, aqe2);
                }
            }
            if (this.a) {
                for (n2 = 0; n2 <= \u26034; ++n2) {
                    alz alz3 = this.a(adm2, 1, -1, n2, aqe2);
                    if (alz3.c().t() == arm.a || !alz3.c().o()) continue;
                    this.a(adm2, aqe2, random, 0.7f, 1, 0, n2, afi.av.a(this.a(afi.av, 0)));
                }
            }
            return true;
        }
    }

    public static class c
    extends aqt {
        private List<aqe> a = Lists.newLinkedList();

        public c() {
        }

        public c(int n2, Random random, int n3, int n4) {
            super(n2);
            this.l = new aqe(n3, 50, n4, n3 + 7 + random.nextInt(6), 54 + random.nextInt(6), n4 + 7 + random.nextInt(6));
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            int n2;
            aqe \u26032;
            aqt aqt3;
            int n3 = this.d();
            \u2603 = this.l.d() - 3 - 1;
            if (\u2603 <= 0) {
                \u2603 = 1;
            }
            for (n2 = 0; n2 < this.l.c() && (n2 += random.nextInt(this.l.c())) + 3 <= this.l.c(); n2 += 4) {
                aqt3 = aqg.b(aqt2, list, random, this.l.a + n2, this.l.b + random.nextInt(\u2603) + 1, this.l.c - 1, cq.c, n3);
                if (aqt3 == null) continue;
                \u26032 = aqt3.c();
                this.a.add(new aqe(\u26032.a, \u26032.b, this.l.c, \u26032.d, \u26032.e, this.l.c + 1));
            }
            for (n2 = 0; n2 < this.l.c() && (n2 += random.nextInt(this.l.c())) + 3 <= this.l.c(); n2 += 4) {
                aqt3 = aqg.b(aqt2, list, random, this.l.a + n2, this.l.b + random.nextInt(\u2603) + 1, this.l.f + 1, cq.d, n3);
                if (aqt3 == null) continue;
                \u26032 = aqt3.c();
                this.a.add(new aqe(\u26032.a, \u26032.b, this.l.f - 1, \u26032.d, \u26032.e, this.l.f));
            }
            for (n2 = 0; n2 < this.l.e() && (n2 += random.nextInt(this.l.e())) + 3 <= this.l.e(); n2 += 4) {
                aqt3 = aqg.b(aqt2, list, random, this.l.a - 1, this.l.b + random.nextInt(\u2603) + 1, this.l.c + n2, cq.e, n3);
                if (aqt3 == null) continue;
                \u26032 = aqt3.c();
                this.a.add(new aqe(this.l.a, \u26032.b, \u26032.c, this.l.a + 1, \u26032.e, \u26032.f));
            }
            for (n2 = 0; n2 < this.l.e() && (n2 += random.nextInt(this.l.e())) + 3 <= this.l.e(); n2 += 4) {
                aqt3 = aqg.b(aqt2, list, random, this.l.d + 1, this.l.b + random.nextInt(\u2603) + 1, this.l.c + n2, cq.f, n3);
                if (aqt3 == null) continue;
                \u26032 = aqt3.c();
                this.a.add(new aqe(this.l.d - 1, \u26032.b, \u26032.c, this.l.d, \u26032.e, \u26032.f));
            }
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.a(adm2, aqe2)) {
                return false;
            }
            this.a(adm2, aqe2, this.l.a, this.l.b, this.l.c, this.l.d, this.l.b, this.l.f, afi.d.Q(), afi.a.Q(), true);
            this.a(adm2, aqe2, this.l.a, this.l.b + 1, this.l.c, this.l.d, Math.min(this.l.b + 3, this.l.e), this.l.f, afi.a.Q(), afi.a.Q(), false);
            for (aqe aqe3 : this.a) {
                this.a(adm2, aqe2, aqe3.a, aqe3.e - 2, aqe3.c, aqe3.d, aqe3.e, aqe3.f, afi.a.Q(), afi.a.Q(), false);
            }
            this.a(adm2, aqe2, this.l.a, this.l.b + 4, this.l.c, this.l.d, this.l.e, this.l.f, afi.a.Q(), false);
            return true;
        }

        @Override
        public void a(int n2, int n3, int n4) {
            super.a(n2, n3, n4);
            for (aqe aqe2 : this.a) {
                aqe2.a(n2, n3, n4);
            }
        }

        @Override
        protected void a(dn dn22) {
            dn dn22;
            du du2 = new du();
            for (aqe aqe2 : this.a) {
                du2.a(aqe2.g());
            }
            dn22.a("Entrances", du2);
        }

        @Override
        protected void b(dn dn2) {
            du du2 = dn2.c("Entrances", 11);
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                this.a.add(new aqe(du2.c(i2)));
            }
        }
    }
}

