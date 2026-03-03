/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqj {
    private static final n[] a = new n[]{new n(c.class, 30, 0, true), new n(a.class, 10, 4), new n(o.class, 10, 4), new n(p.class, 10, 3), new n(l.class, 5, 2), new n(f.class, 5, 1)};
    private static final n[] b = new n[]{new n(i.class, 25, 0, true), new n(g.class, 15, 5), new n(j.class, 5, 10), new n(h.class, 5, 10), new n(d.class, 10, 3, true), new n(e.class, 7, 2), new n(k.class, 5, 2)};

    public static void a() {
        aqr.a(a.class, "NeBCr");
        aqr.a(b.class, "NeBEF");
        aqr.a(c.class, "NeBS");
        aqr.a(d.class, "NeCCS");
        aqr.a(e.class, "NeCTB");
        aqr.a(f.class, "NeCE");
        aqr.a(g.class, "NeSCSC");
        aqr.a(h.class, "NeSCLT");
        aqr.a(i.class, "NeSC");
        aqr.a(j.class, "NeSCRT");
        aqr.a(k.class, "NeCSR");
        aqr.a(l.class, "NeMT");
        aqr.a(o.class, "NeRC");
        aqr.a(p.class, "NeSR");
        aqr.a(q.class, "NeStart");
    }

    private static m b(n n2, List<aqt> list, Random random, int n3, int n4, int n5, cq cq2, int n6) {
        Class<? extends m> clazz = n2.a;
        m \u26032 = null;
        if (clazz == c.class) {
            \u26032 = c.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == a.class) {
            \u26032 = aqj$a.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == o.class) {
            \u26032 = o.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == p.class) {
            \u26032 = p.a(list, random, n3, n4, n5, n6, cq2);
        } else if (clazz == l.class) {
            \u26032 = l.a(list, random, n3, n4, n5, n6, cq2);
        } else if (clazz == f.class) {
            \u26032 = f.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == i.class) {
            \u26032 = i.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == j.class) {
            \u26032 = j.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == h.class) {
            \u26032 = h.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == d.class) {
            \u26032 = d.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == e.class) {
            \u26032 = e.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == g.class) {
            \u26032 = g.a(list, random, n3, n4, n5, cq2, n6);
        } else if (clazz == k.class) {
            \u26032 = k.a(list, random, n3, n4, n5, cq2, n6);
        }
        return \u26032;
    }

    public static class e
    extends m {
        public e() {
        }

        public e(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            int n2 = 1;
            if (this.m == cq.e || this.m == cq.c) {
                n2 = 5;
            }
            this.b((q)aqt2, list, random, 0, n2, random.nextInt(8) > 0);
            this.c((q)aqt2, list, random, 0, n2, random.nextInt(8) > 0);
        }

        public static e a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -3, 0, 0, 9, 7, 9, cq2);
            if (!e.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new e(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 8, 1, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 8, 5, 8, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 6, 0, 8, 6, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 2, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 2, 0, 8, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 3, 0, 1, 4, 0, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 7, 3, 0, 7, 4, 0, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 2, 4, 8, 2, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 1, 4, 2, 2, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 6, 1, 4, 7, 2, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 3, 8, 8, 3, 8, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 3, 6, 0, 3, 7, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 8, 3, 6, 8, 3, 7, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 3, 4, 0, 5, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 8, 3, 4, 8, 5, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 3, 5, 2, 5, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 3, 5, 7, 5, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 4, 5, 1, 5, 5, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 7, 4, 5, 7, 5, 5, afi.bz.Q(), afi.bz.Q(), false);
            for (int i2 = 0; i2 <= 5; ++i2) {
                for (\u2603 = 0; \u2603 <= 8; ++\u2603) {
                    this.b(adm2, afi.by.Q(), \u2603, -1, i2, aqe2);
                }
            }
            return true;
        }
    }

    public static class d
    extends m {
        public d() {
        }

        public d(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 1, 0, true);
        }

        public static d a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -7, 0, 5, 14, 10, cq2);
            if (!d.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new d(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2 = this.a(afi.bA, 2);
            for (\u2603 = 0; \u2603 <= 9; ++\u2603) {
                \u2603 = Math.max(1, 7 - \u2603);
                \u2603 = Math.min(Math.max(\u2603 + 5, 14 - \u2603), 13);
                \u2603 = \u2603;
                this.a(adm2, aqe2, 0, 0, \u2603, 4, \u2603, \u2603, afi.by.Q(), afi.by.Q(), false);
                this.a(adm2, aqe2, 1, \u2603 + 1, \u2603, 3, \u2603 - 1, \u2603, afi.a.Q(), afi.a.Q(), false);
                if (\u2603 <= 6) {
                    this.a(adm2, afi.bA.a(n2), 1, \u2603 + 1, \u2603, aqe2);
                    this.a(adm2, afi.bA.a(n2), 2, \u2603 + 1, \u2603, aqe2);
                    this.a(adm2, afi.bA.a(n2), 3, \u2603 + 1, \u2603, aqe2);
                }
                this.a(adm2, aqe2, 0, \u2603, \u2603, 4, \u2603, \u2603, afi.by.Q(), afi.by.Q(), false);
                this.a(adm2, aqe2, 0, \u2603 + 1, \u2603, 0, \u2603 - 1, \u2603, afi.by.Q(), afi.by.Q(), false);
                this.a(adm2, aqe2, 4, \u2603 + 1, \u2603, 4, \u2603 - 1, \u2603, afi.by.Q(), afi.by.Q(), false);
                if ((\u2603 & 1) == 0) {
                    this.a(adm2, aqe2, 0, \u2603 + 2, \u2603, 0, \u2603 + 3, \u2603, afi.bz.Q(), afi.bz.Q(), false);
                    this.a(adm2, aqe2, 4, \u2603 + 2, \u2603, 4, \u2603 + 3, \u2603, afi.bz.Q(), afi.bz.Q(), false);
                }
                for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                    this.b(adm2, afi.by.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class h
    extends m {
        private boolean b;

        public h() {
        }

        public h(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
            this.b = random.nextInt(3) == 0;
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.n("Chest");
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Chest", this.b);
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.b((q)aqt2, list, random, 0, 1, true);
        }

        public static h a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, 0, 0, 5, 7, 5, cq2);
            if (!h.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new h(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 4, 1, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 4, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 4, 2, 0, 4, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 4, 3, 1, 4, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 4, 3, 3, 4, 4, 3, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 4, 3, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 3, 4, 1, 4, 4, afi.bz.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 3, 3, 4, 3, 4, 4, afi.bz.Q(), afi.by.Q(), false);
            if (this.b && aqe2.b(new cj(this.a(3, 3), this.d(2), this.b(3, 3)))) {
                this.b = false;
                this.a(adm2, aqe2, random, 3, 2, 3, a, 2 + random.nextInt(4));
            }
            this.a(adm2, aqe2, 0, 6, 0, 4, 6, 4, afi.by.Q(), afi.by.Q(), false);
            for (int i2 = 0; i2 <= 4; ++i2) {
                for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class j
    extends m {
        private boolean b;

        public j() {
        }

        public j(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
            this.b = random.nextInt(3) == 0;
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.n("Chest");
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Chest", this.b);
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.c((q)aqt2, list, random, 0, 1, true);
        }

        public static j a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, 0, 0, 5, 7, 5, cq2);
            if (!j.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new j(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 4, 1, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 4, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 3, 1, 0, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 3, 3, 0, 4, 3, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 4, 2, 0, 4, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 2, 4, 4, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 3, 4, 1, 4, 4, afi.bz.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 3, 3, 4, 3, 4, 4, afi.bz.Q(), afi.by.Q(), false);
            if (this.b && aqe2.b(new cj(this.a(1, 3), this.d(2), this.b(1, 3)))) {
                this.b = false;
                this.a(adm2, aqe2, random, 1, 2, 3, a, 2 + random.nextInt(4));
            }
            this.a(adm2, aqe2, 0, 6, 0, 4, 6, 4, afi.by.Q(), afi.by.Q(), false);
            for (int i2 = 0; i2 <= 4; ++i2) {
                for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class g
    extends m {
        public g() {
        }

        public g(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 1, 0, true);
            this.b((q)aqt2, list, random, 0, 1, true);
            this.c((q)aqt2, list, random, 0, 1, true);
        }

        public static g a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, 0, 0, 5, 7, 5, cq2);
            if (!g.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new g(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 4, 1, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 4, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 4, 2, 0, 4, 5, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 4, 0, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 4, 2, 4, 4, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 6, 0, 4, 6, 4, afi.by.Q(), afi.by.Q(), false);
            for (int i2 = 0; i2 <= 4; ++i2) {
                for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class i
    extends m {
        public i() {
        }

        public i(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 1, 0, true);
        }

        public static i a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, 0, 0, 5, 7, 5, cq2);
            if (!i.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new i(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 4, 1, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 4, 5, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 4, 2, 0, 4, 5, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 3, 1, 0, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 3, 3, 0, 4, 3, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 4, 3, 1, 4, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 4, 3, 3, 4, 4, 3, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 6, 0, 4, 6, 4, afi.by.Q(), afi.by.Q(), false);
            for (int i2 = 0; i2 <= 4; ++i2) {
                for (\u2603 = 0; \u2603 <= 4; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class k
    extends m {
        public k() {
        }

        public k(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 5, 3, true);
            this.a((q)aqt2, list, random, 5, 11, true);
        }

        public static k a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -5, -3, 0, 13, 14, 13, cq2);
            if (!k.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new k(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int n2;
            this.a(adm22, aqe2, 0, 3, 0, 12, 4, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 12, 13, 12, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 1, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 0, 12, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 11, 4, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 8, 5, 11, 10, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 5, 9, 11, 7, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 0, 4, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 8, 5, 0, 10, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 5, 9, 0, 7, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 11, 2, 10, 12, 10, afi.by.Q(), afi.by.Q(), false);
            for (n2 = 1; n2 <= 11; n2 += 2) {
                this.a(adm22, aqe2, n2, 10, 0, n2, 11, 0, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, n2, 10, 12, n2, 11, 12, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 0, 10, n2, 0, 11, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 12, 10, n2, 12, 11, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, afi.by.Q(), n2, 13, 0, aqe2);
                this.a(adm22, afi.by.Q(), n2, 13, 12, aqe2);
                this.a(adm22, afi.by.Q(), 0, 13, n2, aqe2);
                this.a(adm22, afi.by.Q(), 12, 13, n2, aqe2);
                this.a(adm22, afi.bz.Q(), n2 + 1, 13, 0, aqe2);
                this.a(adm22, afi.bz.Q(), n2 + 1, 13, 12, aqe2);
                this.a(adm22, afi.bz.Q(), 0, 13, n2 + 1, aqe2);
                this.a(adm22, afi.bz.Q(), 12, 13, n2 + 1, aqe2);
            }
            this.a(adm22, afi.bz.Q(), 0, 13, 0, aqe2);
            this.a(adm22, afi.bz.Q(), 0, 13, 12, aqe2);
            this.a(adm22, afi.bz.Q(), 0, 13, 0, aqe2);
            this.a(adm22, afi.bz.Q(), 12, 13, 0, aqe2);
            for (n2 = 3; n2 <= 9; n2 += 2) {
                this.a(adm22, aqe2, 1, 7, n2, 1, 8, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 11, 7, n2, 11, 8, n2, afi.bz.Q(), afi.bz.Q(), false);
            }
            n2 = this.a(afi.bA, 3);
            for (\u2603 = 0; \u2603 <= 6; ++\u2603) {
                \u2603 = \u2603 + 4;
                for (\u2603 = 5; \u2603 <= 7; ++\u2603) {
                    this.a(adm22, afi.bA.a(n2), \u2603, 5 + \u2603, \u2603, aqe2);
                }
                if (\u2603 >= 5 && \u2603 <= 8) {
                    this.a(adm22, aqe2, 5, 5, \u2603, 7, \u2603 + 4, \u2603, afi.by.Q(), afi.by.Q(), false);
                } else if (\u2603 >= 9 && \u2603 <= 10) {
                    this.a(adm22, aqe2, 5, 8, \u2603, 7, \u2603 + 4, \u2603, afi.by.Q(), afi.by.Q(), false);
                }
                if (\u2603 < 1) continue;
                this.a(adm22, aqe2, 5, 6 + \u2603, \u2603, 7, 9 + \u2603, \u2603, afi.a.Q(), afi.a.Q(), false);
            }
            for (\u2603 = 5; \u2603 <= 7; ++\u2603) {
                this.a(adm22, afi.bA.a(n2), \u2603, 12, 11, aqe2);
            }
            this.a(adm22, aqe2, 5, 6, 7, 5, 7, 7, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 7, 6, 7, 7, 7, 7, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 5, 13, 12, 7, 13, 12, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 2, 5, 2, 3, 5, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 9, 3, 5, 10, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 4, 2, 5, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 9, 5, 2, 10, 5, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 9, 5, 9, 10, 5, 10, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 10, 5, 4, 10, 5, 8, afi.by.Q(), afi.by.Q(), false);
            \u2603 = this.a(afi.bA, 0);
            \u2603 = this.a(afi.bA, 1);
            this.a(adm22, afi.bA.a(\u2603), 4, 5, 2, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 4, 5, 3, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 4, 5, 9, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 4, 5, 10, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 8, 5, 2, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 8, 5, 3, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 8, 5, 9, aqe2);
            this.a(adm22, afi.bA.a(\u2603), 8, 5, 10, aqe2);
            this.a(adm22, aqe2, 3, 4, 4, 4, 4, 8, afi.aW.Q(), afi.aW.Q(), false);
            this.a(adm22, aqe2, 8, 4, 4, 9, 4, 8, afi.aW.Q(), afi.aW.Q(), false);
            this.a(adm22, aqe2, 3, 5, 4, 4, 5, 8, afi.bB.Q(), afi.bB.Q(), false);
            this.a(adm22, aqe2, 8, 5, 4, 9, 5, 8, afi.bB.Q(), afi.bB.Q(), false);
            this.a(adm22, aqe2, 4, 2, 0, 8, 2, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 2, 4, 12, 2, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 4, 0, 0, 8, 1, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 4, 0, 9, 8, 1, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 0, 4, 3, 1, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 9, 0, 4, 12, 1, 8, afi.by.Q(), afi.by.Q(), false);
            for (\u2603 = 4; \u2603 <= 8; ++\u2603) {
                for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                    this.b(adm22, afi.by.Q(), \u2603, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), \u2603, -1, 12 - \u2603, aqe2);
                }
            }
            for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                for (\u2603 = 4; \u2603 <= 8; ++\u2603) {
                    this.b(adm22, afi.by.Q(), \u2603, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), 12 - \u2603, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class f
    extends m {
        public f() {
        }

        public f(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 5, 3, true);
        }

        public static f a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -5, -3, 0, 13, 14, 13, cq2);
            if (!f.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new f(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int n2;
            this.a(adm22, aqe2, 0, 3, 0, 12, 4, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 12, 13, 12, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 1, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 0, 12, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 11, 4, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 8, 5, 11, 10, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 5, 9, 11, 7, 12, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 5, 0, 4, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 8, 5, 0, 10, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 5, 9, 0, 7, 12, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 2, 11, 2, 10, 12, 10, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 5, 8, 0, 7, 8, 0, afi.bz.Q(), afi.bz.Q(), false);
            for (n2 = 1; n2 <= 11; n2 += 2) {
                this.a(adm22, aqe2, n2, 10, 0, n2, 11, 0, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, n2, 10, 12, n2, 11, 12, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 0, 10, n2, 0, 11, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 12, 10, n2, 12, 11, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, afi.by.Q(), n2, 13, 0, aqe2);
                this.a(adm22, afi.by.Q(), n2, 13, 12, aqe2);
                this.a(adm22, afi.by.Q(), 0, 13, n2, aqe2);
                this.a(adm22, afi.by.Q(), 12, 13, n2, aqe2);
                this.a(adm22, afi.bz.Q(), n2 + 1, 13, 0, aqe2);
                this.a(adm22, afi.bz.Q(), n2 + 1, 13, 12, aqe2);
                this.a(adm22, afi.bz.Q(), 0, 13, n2 + 1, aqe2);
                this.a(adm22, afi.bz.Q(), 12, 13, n2 + 1, aqe2);
            }
            this.a(adm22, afi.bz.Q(), 0, 13, 0, aqe2);
            this.a(adm22, afi.bz.Q(), 0, 13, 12, aqe2);
            this.a(adm22, afi.bz.Q(), 0, 13, 0, aqe2);
            this.a(adm22, afi.bz.Q(), 12, 13, 0, aqe2);
            for (n2 = 3; n2 <= 9; n2 += 2) {
                this.a(adm22, aqe2, 1, 7, n2, 1, 8, n2, afi.bz.Q(), afi.bz.Q(), false);
                this.a(adm22, aqe2, 11, 7, n2, 11, 8, n2, afi.bz.Q(), afi.bz.Q(), false);
            }
            this.a(adm22, aqe2, 4, 2, 0, 8, 2, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 2, 4, 12, 2, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 4, 0, 0, 8, 1, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 4, 0, 9, 8, 1, 12, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 0, 4, 3, 1, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 9, 0, 4, 12, 1, 8, afi.by.Q(), afi.by.Q(), false);
            for (n2 = 4; n2 <= 8; ++n2) {
                for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                    this.b(adm22, afi.by.Q(), n2, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), n2, -1, 12 - \u2603, aqe2);
                }
            }
            for (n2 = 0; n2 <= 2; ++n2) {
                for (\u2603 = 4; \u2603 <= 8; ++\u2603) {
                    this.b(adm22, afi.by.Q(), n2, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), 12 - n2, -1, \u2603, aqe2);
                }
            }
            this.a(adm22, aqe2, 5, 5, 5, 7, 5, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 6, 1, 6, 6, 4, 6, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, afi.by.Q(), 6, 0, 6, aqe2);
            this.a(adm22, afi.k.Q(), 6, 5, 6, aqe2);
            cj \u26032 = new cj(this.a(6, 6), this.d(5), this.b(6, 6));
            if (aqe2.b(\u26032)) {
                adm22.a(afi.k, \u26032, random);
            }
            return true;
        }
    }

    public static class l
    extends m {
        private boolean b;

        public l() {
        }

        public l(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.n("Mob");
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Mob", this.b);
        }

        public static l a(List<aqt> list, Random random, int n2, int n3, int n4, int n5, cq cq2) {
            aqe aqe2 = aqe.a(n2, n3, n4, -2, 0, 0, 7, 8, 9, cq2);
            if (!l.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new l(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 2, 0, 6, 7, 7, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 0, 0, 5, 1, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 2, 1, 5, 2, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 3, 2, 5, 3, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 4, 3, 5, 4, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 2, 0, 1, 4, 2, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 5, 2, 0, 5, 4, 2, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 5, 2, 1, 5, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 5, 5, 2, 5, 5, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 5, 3, 0, 5, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 5, 3, 6, 5, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 5, 8, 5, 5, 8, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, afi.bz.Q(), 1, 6, 3, aqe2);
            this.a(adm2, afi.bz.Q(), 5, 6, 3, aqe2);
            this.a(adm2, aqe2, 0, 6, 3, 0, 6, 8, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 6, 6, 3, 6, 6, 8, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 1, 6, 8, 5, 7, 8, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 2, 8, 8, 4, 8, 8, afi.bz.Q(), afi.bz.Q(), false);
            if (!this.b && aqe2.b(\u2603 = new cj(this.a(3, 5), this.d(5), this.b(3, 5)))) {
                this.b = true;
                adm2.a(\u2603, afi.ac.Q(), 2);
                akw akw2 = adm2.s(\u2603);
                if (akw2 instanceof all) {
                    ((all)akw2).b().a("Blaze");
                }
            }
            for (int i2 = 0; i2 <= 6; ++i2) {
                for (\u2603 = 0; \u2603 <= 6; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class p
    extends m {
        public p() {
        }

        public p(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.c((q)aqt2, list, random, 6, 2, false);
        }

        public static p a(List<aqt> list, Random random, int n2, int n3, int n4, int n5, cq cq2) {
            aqe aqe2 = aqe.a(n2, n3, n4, -2, 0, 0, 7, 11, 7, cq2);
            if (!p.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new p(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 6, 1, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 6, 10, 6, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 1, 8, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 5, 2, 0, 6, 8, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 1, 0, 8, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 2, 1, 6, 8, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 2, 6, 5, 8, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 3, 2, 0, 5, 4, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 6, 3, 2, 6, 5, 2, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 6, 3, 4, 6, 5, 4, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, afi.by.Q(), 5, 2, 5, aqe2);
            this.a(adm2, aqe2, 4, 2, 5, 4, 3, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 3, 2, 5, 3, 4, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 2, 2, 5, 2, 5, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 2, 5, 1, 6, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 1, 7, 1, 5, 7, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 8, 2, 6, 8, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 2, 6, 0, 4, 8, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 2, 5, 0, 4, 5, 0, afi.bz.Q(), afi.bz.Q(), false);
            for (int i2 = 0; i2 <= 6; ++i2) {
                for (\u2603 = 0; \u2603 <= 6; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class o
    extends m {
        public o() {
        }

        public o(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 2, 0, false);
            this.b((q)aqt2, list, random, 0, 2, false);
            this.c((q)aqt2, list, random, 0, 2, false);
        }

        public static o a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -2, 0, 0, 7, 9, 7, cq2);
            if (!o.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new o(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 0, 0, 6, 1, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 6, 7, 6, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 1, 6, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 6, 1, 6, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 5, 2, 0, 6, 6, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 5, 2, 6, 6, 6, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 6, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 2, 5, 0, 6, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 2, 0, 6, 6, 1, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 2, 5, 6, 6, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 2, 6, 0, 4, 6, 0, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 2, 5, 0, 4, 5, 0, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 2, 6, 6, 4, 6, 6, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 2, 5, 6, 4, 5, 6, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 0, 6, 2, 0, 6, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 0, 5, 2, 0, 5, 4, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm2, aqe2, 6, 6, 2, 6, 6, 4, afi.by.Q(), afi.by.Q(), false);
            this.a(adm2, aqe2, 6, 5, 2, 6, 5, 4, afi.bz.Q(), afi.bz.Q(), false);
            for (int i2 = 0; i2 <= 6; ++i2) {
                for (\u2603 = 0; \u2603 <= 6; ++\u2603) {
                    this.b(adm2, afi.by.Q(), i2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class a
    extends m {
        public a() {
        }

        public a(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        protected a(Random random, int n2, int n3) {
            super(0);
            this.m = cq.c.a.a(random);
            switch (this.m) {
                case c: 
                case d: {
                    this.l = new aqe(n2, 64, n3, n2 + 19 - 1, 73, n3 + 19 - 1);
                    break;
                }
                default: {
                    this.l = new aqe(n2, 64, n3, n2 + 19 - 1, 73, n3 + 19 - 1);
                }
            }
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 8, 3, false);
            this.b((q)aqt2, list, random, 3, 8, false);
            this.c((q)aqt2, list, random, 3, 8, false);
        }

        public static a a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -8, -3, 0, 19, 10, 19, cq2);
            if (!aqj$a.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new a(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int n2;
            this.a(adm22, aqe2, 7, 3, 0, 11, 4, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 3, 7, 18, 4, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 8, 5, 0, 10, 7, 18, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 5, 8, 18, 7, 10, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 7, 5, 0, 7, 5, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 7, 5, 11, 7, 5, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 0, 11, 5, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 11, 11, 5, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 5, 7, 7, 5, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 7, 18, 5, 7, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 5, 11, 7, 5, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 11, 5, 11, 18, 5, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 7, 2, 0, 11, 2, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 7, 2, 13, 11, 2, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 7, 0, 0, 11, 1, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 7, 0, 15, 11, 1, 18, afi.by.Q(), afi.by.Q(), false);
            for (n2 = 7; n2 <= 11; ++n2) {
                for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                    this.b(adm22, afi.by.Q(), n2, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), n2, -1, 18 - \u2603, aqe2);
                }
            }
            this.a(adm22, aqe2, 0, 2, 7, 5, 2, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 13, 2, 7, 18, 2, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 0, 7, 3, 1, 11, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 15, 0, 7, 18, 1, 11, afi.by.Q(), afi.by.Q(), false);
            for (n2 = 0; n2 <= 2; ++n2) {
                for (\u2603 = 7; \u2603 <= 11; ++\u2603) {
                    this.b(adm22, afi.by.Q(), n2, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), 18 - n2, -1, \u2603, aqe2);
                }
            }
            return true;
        }
    }

    public static class b
    extends m {
        private int b;

        public b() {
        }

        public b(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
            this.b = random.nextInt();
        }

        public static b a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -3, 0, 5, 10, 8, cq2);
            if (!aqj$b.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new b(n5, random, aqe2, cq2);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.b = dn2.f("Seed");
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Seed", this.b);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2;
            int \u26032;
            Random random2 = new Random(this.b);
            for (\u26032 = 0; \u26032 <= 4; ++\u26032) {
                for (n2 = 3; n2 <= 4; ++n2) {
                    \u2603 = random2.nextInt(8);
                    this.a(adm2, aqe2, \u26032, n2, 0, \u26032, n2, \u2603, afi.by.Q(), afi.by.Q(), false);
                }
            }
            \u26032 = random2.nextInt(8);
            this.a(adm2, aqe2, 0, 5, 0, 0, 5, \u26032, afi.by.Q(), afi.by.Q(), false);
            \u26032 = random2.nextInt(8);
            this.a(adm2, aqe2, 4, 5, 0, 4, 5, \u26032, afi.by.Q(), afi.by.Q(), false);
            for (\u26032 = 0; \u26032 <= 4; ++\u26032) {
                n2 = random2.nextInt(5);
                this.a(adm2, aqe2, \u26032, 2, 0, \u26032, 2, n2, afi.by.Q(), afi.by.Q(), false);
            }
            for (\u26032 = 0; \u26032 <= 4; ++\u26032) {
                for (n2 = 0; n2 <= 1; ++n2) {
                    \u2603 = random2.nextInt(3);
                    this.a(adm2, aqe2, \u26032, n2, 0, \u26032, n2, \u2603, afi.by.Q(), afi.by.Q(), false);
                }
            }
            return true;
        }
    }

    public static class c
    extends m {
        public c() {
        }

        public c(int n2, Random random, aqe aqe2, cq cq2) {
            super(n2);
            this.m = cq2;
            this.l = aqe2;
        }

        @Override
        public void a(aqt aqt2, List<aqt> list, Random random) {
            this.a((q)aqt2, list, random, 1, 3, false);
        }

        public static c a(List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            aqe aqe2 = aqe.a(n2, n3, n4, -1, -3, 0, 5, 10, 19, cq2);
            if (!c.a(aqe2) || aqt.a(list, aqe2) != null) {
                return null;
            }
            return new c(n5, random, aqe2, cq2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            this.a(adm22, aqe2, 0, 3, 0, 4, 4, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 1, 5, 0, 3, 7, 18, afi.a.Q(), afi.a.Q(), false);
            this.a(adm22, aqe2, 0, 5, 0, 0, 5, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 4, 5, 0, 4, 5, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 2, 0, 4, 2, 5, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 2, 13, 4, 2, 18, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 0, 0, 4, 1, 3, afi.by.Q(), afi.by.Q(), false);
            this.a(adm22, aqe2, 0, 0, 15, 4, 1, 18, afi.by.Q(), afi.by.Q(), false);
            for (int i2 = 0; i2 <= 4; ++i2) {
                for (\u2603 = 0; \u2603 <= 2; ++\u2603) {
                    this.b(adm22, afi.by.Q(), i2, -1, \u2603, aqe2);
                    this.b(adm22, afi.by.Q(), i2, -1, 18 - \u2603, aqe2);
                }
            }
            this.a(adm22, aqe2, 0, 1, 1, 0, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 0, 3, 4, 0, 4, 4, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 0, 3, 14, 0, 4, 14, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 0, 1, 17, 0, 4, 17, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 4, 1, 1, 4, 4, 1, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 4, 3, 4, 4, 4, 4, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 4, 3, 14, 4, 4, 14, afi.bz.Q(), afi.bz.Q(), false);
            this.a(adm22, aqe2, 4, 1, 17, 4, 4, 17, afi.bz.Q(), afi.bz.Q(), false);
            return true;
        }
    }

    public static class q
    extends a {
        public n b;
        public List<n> c;
        public List<n> d;
        public List<aqt> e = Lists.newArrayList();

        public q() {
        }

        public q(Random random, int n2, int n3) {
            super(random, n2, n3);
            this.c = Lists.newArrayList();
            for (n n4 : a) {
                n4.c = 0;
                this.c.add(n4);
            }
            this.d = Lists.newArrayList();
            for (n n4 : b) {
                n4.c = 0;
                this.d.add(n4);
            }
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
        }
    }

    static abstract class m
    extends aqt {
        protected static final List<ob> a = Lists.newArrayList(new ob(zy.i, 0, 1, 3, 5), new ob(zy.j, 0, 1, 5, 5), new ob(zy.k, 0, 1, 3, 15), new ob(zy.B, 0, 1, 1, 5), new ob(zy.ah, 0, 1, 1, 5), new ob(zy.d, 0, 1, 1, 5), new ob(zy.by, 0, 3, 7, 5), new ob(zy.aA, 0, 1, 1, 10), new ob(zy.cl, 0, 1, 1, 8), new ob(zy.ck, 0, 1, 1, 5), new ob(zy.cm, 0, 1, 1, 3), new ob(zw.a(afi.Z), 0, 2, 4, 2));

        public m() {
        }

        protected m(int n2) {
            super(n2);
        }

        @Override
        protected void b(dn dn2) {
        }

        @Override
        protected void a(dn dn2) {
        }

        private int a(List<n> list) {
            boolean bl2 = false;
            int \u26032 = 0;
            for (n n22 : list) {
                n n22;
                if (n22.d > 0 && n22.c < n22.d) {
                    bl2 = true;
                }
                \u26032 += n22.b;
            }
            return bl2 ? \u26032 : -1;
        }

        private m a(q q2, List<n> list, List<aqt> list22, Random random, int n2, int n3, int n4, cq cq2, int n5) {
            List<aqt> list22;
            \u2603 = this.a(list);
            boolean bl2 = \u2603 > 0 && n5 <= 30;
            int \u26032 = 0;
            block0: while (\u26032 < 5 && bl2) {
                ++\u26032;
                int n6 = random.nextInt(\u2603);
                for (n n7 : list) {
                    if ((n6 -= n7.b) >= 0) continue;
                    if (!n7.a(n5) || n7 == q2.b && !n7.e) continue block0;
                    m m2 = aqj.b(n7, list22, random, n2, n3, n4, cq2, n5);
                    if (m2 == null) continue;
                    ++n7.c;
                    q2.b = n7;
                    if (!n7.a()) {
                        list.remove(n7);
                    }
                    return m2;
                }
            }
            return aqj$b.a(list22, random, n2, n3, n4, cq2, n5);
        }

        private aqt a(q q2, List<aqt> list, Random random, int n2, int n3, int n4, cq cq2, int n5, boolean bl2) {
            if (Math.abs(n2 - q2.c().a) > 112 || Math.abs(n4 - q2.c().c) > 112) {
                return aqj$b.a(list, random, n2, n3, n4, cq2, n5);
            }
            List<n> list2 = q2.c;
            if (bl2) {
                list2 = q2.d;
            }
            if ((\u2603 = this.a(q2, list2, list, random, n2, n3, n4, cq2, n5 + 1)) != null) {
                list.add(\u2603);
                q2.e.add(\u2603);
            }
            return \u2603;
        }

        protected aqt a(q q2, List<aqt> list, Random random, int n2, int n3, boolean bl2) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return this.a(q2, list, random, this.l.a + n2, this.l.b + n3, this.l.c - 1, this.m, this.d(), bl2);
                    }
                    case d: {
                        return this.a(q2, list, random, this.l.a + n2, this.l.b + n3, this.l.f + 1, this.m, this.d(), bl2);
                    }
                    case e: {
                        return this.a(q2, list, random, this.l.a - 1, this.l.b + n3, this.l.c + n2, this.m, this.d(), bl2);
                    }
                    case f: {
                        return this.a(q2, list, random, this.l.d + 1, this.l.b + n3, this.l.c + n2, this.m, this.d(), bl2);
                    }
                }
            }
            return null;
        }

        protected aqt b(q q2, List<aqt> list, Random random, int n2, int n3, boolean bl2) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return this.a(q2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d(), bl2);
                    }
                    case d: {
                        return this.a(q2, list, random, this.l.a - 1, this.l.b + n2, this.l.c + n3, cq.e, this.d(), bl2);
                    }
                    case e: {
                        return this.a(q2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d(), bl2);
                    }
                    case f: {
                        return this.a(q2, list, random, this.l.a + n3, this.l.b + n2, this.l.c - 1, cq.c, this.d(), bl2);
                    }
                }
            }
            return null;
        }

        protected aqt c(q q2, List<aqt> list, Random random, int n2, int n3, boolean bl2) {
            if (this.m != null) {
                switch (this.m) {
                    case c: {
                        return this.a(q2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d(), bl2);
                    }
                    case d: {
                        return this.a(q2, list, random, this.l.d + 1, this.l.b + n2, this.l.c + n3, cq.f, this.d(), bl2);
                    }
                    case e: {
                        return this.a(q2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d(), bl2);
                    }
                    case f: {
                        return this.a(q2, list, random, this.l.a + n3, this.l.b + n2, this.l.f + 1, cq.d, this.d(), bl2);
                    }
                }
            }
            return null;
        }

        protected static boolean a(aqe aqe2) {
            return aqe2 != null && aqe2.b > 10;
        }
    }

    static class n {
        public Class<? extends m> a;
        public final int b;
        public int c;
        public int d;
        public boolean e;

        public n(Class<? extends m> clazz, int n2, int n3, boolean bl2) {
            this.a = clazz;
            this.b = n2;
            this.d = n3;
            this.e = bl2;
        }

        public n(Class<? extends m> clazz, int n2, int n3) {
            this(clazz, n2, n3, false);
        }

        public boolean a(int n2) {
            return this.d == 0 || this.c < this.d;
        }

        public boolean a() {
            return this.d == 0 || this.c < this.d;
        }
    }
}

