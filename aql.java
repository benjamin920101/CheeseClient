/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class aql {
    public static void a() {
        aqr.a(h.class, "OMB");
        aqr.a(j.class, "OMCR");
        aqr.a(k.class, "OMDXR");
        aqr.a(l.class, "OMDXYR");
        aqr.a(m.class, "OMDYR");
        aqr.a(n.class, "OMDYZR");
        aqr.a(o.class, "OMDZR");
        aqr.a(p.class, "OMEntry");
        aqr.a(q.class, "OMPenthouse");
        aqr.a(s.class, "OMSimple");
        aqr.a(t.class, "OMSimpleT");
    }

    static class d
    implements i {
        private d() {
        }

        @Override
        public boolean a(v v2) {
            if (v2.c[cq.c.a()] && !v2.b[cq.c.a()].d && v2.c[cq.b.a()] && !v2.b[cq.b.a()].d) {
                \u2603 = v2.b[cq.c.a()];
                return \u2603.c[cq.b.a()] && !\u2603.b[cq.b.a()].d;
            }
            return false;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v2.d = true;
            v2.b[cq.c.a()].d = true;
            v2.b[cq.b.a()].d = true;
            v2.b[cq.c.a()].b[cq.b.a()].d = true;
            return new n(cq2, v2, random);
        }
    }

    static class b
    implements i {
        private b() {
        }

        @Override
        public boolean a(v v2) {
            if (v2.c[cq.f.a()] && !v2.b[cq.f.a()].d && v2.c[cq.b.a()] && !v2.b[cq.b.a()].d) {
                \u2603 = v2.b[cq.f.a()];
                return \u2603.c[cq.b.a()] && !\u2603.b[cq.b.a()].d;
            }
            return false;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v2.d = true;
            v2.b[cq.f.a()].d = true;
            v2.b[cq.b.a()].d = true;
            v2.b[cq.f.a()].b[cq.b.a()].d = true;
            return new l(cq2, v2, random);
        }
    }

    static class e
    implements i {
        private e() {
        }

        @Override
        public boolean a(v v2) {
            return v2.c[cq.c.a()] && !v2.b[cq.c.a()].d;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v v3 = v2;
            if (!v2.c[cq.c.a()] || v2.b[cq.c.a()].d) {
                v3 = v2.b[cq.d.a()];
            }
            v3.d = true;
            v3.b[cq.c.a()].d = true;
            return new o(cq2, v3, random);
        }
    }

    static class a
    implements i {
        private a() {
        }

        @Override
        public boolean a(v v2) {
            return v2.c[cq.f.a()] && !v2.b[cq.f.a()].d;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v v3 = v2;
            v3.d = true;
            v3.b[cq.f.a()].d = true;
            return new k(cq2, v3, random);
        }
    }

    static class c
    implements i {
        private c() {
        }

        @Override
        public boolean a(v v2) {
            return v2.c[cq.b.a()] && !v2.b[cq.b.a()].d;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v v3 = v2;
            v3.d = true;
            v3.b[cq.b.a()].d = true;
            return new m(cq2, v3, random);
        }
    }

    static class g
    implements i {
        private g() {
        }

        @Override
        public boolean a(v v2) {
            return !v2.c[cq.e.a()] && !v2.c[cq.f.a()] && !v2.c[cq.c.a()] && !v2.c[cq.d.a()] && !v2.c[cq.b.a()];
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v2.d = true;
            return new t(cq2, v2, random);
        }
    }

    static class f
    implements i {
        private f() {
        }

        @Override
        public boolean a(v v2) {
            return true;
        }

        @Override
        public r a(cq cq2, v v2, Random random) {
            v2.d = true;
            return new s(cq2, v2, random);
        }
    }

    static interface i {
        public boolean a(v var1);

        public r a(cq var1, v var2, Random var3);
    }

    static class v {
        int a;
        v[] b = new v[6];
        boolean[] c = new boolean[6];
        boolean d;
        boolean e;
        int f;

        public v(int n2) {
            this.a = n2;
        }

        public void a(cq cq2, v v2) {
            this.b[cq2.a()] = v2;
            v2.b[cq2.d().a()] = this;
        }

        public void a() {
            for (int i2 = 0; i2 < 6; ++i2) {
                this.c[i2] = this.b[i2] != null;
            }
        }

        public boolean a(int n2) {
            if (this.e) {
                return true;
            }
            this.f = n2;
            for (\u2603 = 0; \u2603 < 6; ++\u2603) {
                if (this.b[\u2603] == null || !this.c[\u2603] || this.b[\u2603].f == n2 || !this.b[\u2603].a(n2)) continue;
                return true;
            }
            return false;
        }

        public boolean b() {
            return this.a >= 75;
        }

        public int c() {
            int n2 = 0;
            for (\u2603 = 0; \u2603 < 6; ++\u2603) {
                if (!this.c[\u2603]) continue;
                ++n2;
            }
            return n2;
        }
    }

    public static class q
    extends r {
        public q() {
        }

        public q(cq cq2, aqe aqe2) {
            super(cq2, aqe2);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            int \u26032;
            this.a(adm22, aqe2, 2, -1, 2, 11, -1, 11, b, b, false);
            this.a(adm22, aqe2, 0, -1, 0, 1, -1, 11, a, a, false);
            this.a(adm22, aqe2, 12, -1, 0, 13, -1, 11, a, a, false);
            this.a(adm22, aqe2, 2, -1, 0, 11, -1, 1, a, a, false);
            this.a(adm22, aqe2, 2, -1, 12, 11, -1, 13, a, a, false);
            this.a(adm22, aqe2, 0, 0, 0, 0, 0, 13, b, b, false);
            this.a(adm22, aqe2, 13, 0, 0, 13, 0, 13, b, b, false);
            this.a(adm22, aqe2, 1, 0, 0, 12, 0, 0, b, b, false);
            this.a(adm22, aqe2, 1, 0, 13, 12, 0, 13, b, b, false);
            for (\u26032 = 2; \u26032 <= 11; \u26032 += 3) {
                this.a(adm22, e, 0, 0, \u26032, aqe2);
                this.a(adm22, e, 13, 0, \u26032, aqe2);
                this.a(adm22, e, \u26032, 0, 0, aqe2);
            }
            this.a(adm22, aqe2, 2, 0, 3, 4, 0, 9, b, b, false);
            this.a(adm22, aqe2, 9, 0, 3, 11, 0, 9, b, b, false);
            this.a(adm22, aqe2, 4, 0, 9, 9, 0, 11, b, b, false);
            this.a(adm22, b, 5, 0, 8, aqe2);
            this.a(adm22, b, 8, 0, 8, aqe2);
            this.a(adm22, b, 10, 0, 10, aqe2);
            this.a(adm22, b, 3, 0, 10, aqe2);
            this.a(adm22, aqe2, 3, 0, 3, 3, 0, 7, c, c, false);
            this.a(adm22, aqe2, 10, 0, 3, 10, 0, 7, c, c, false);
            this.a(adm22, aqe2, 6, 0, 10, 7, 0, 10, c, c, false);
            \u26032 = 3;
            for (int i2 = 0; i2 < 2; ++i2) {
                for (\u2603 = 2; \u2603 <= 8; \u2603 += 3) {
                    this.a(adm22, aqe2, \u26032, 0, \u2603, \u26032, 2, \u2603, b, b, false);
                }
                \u26032 = 10;
            }
            this.a(adm22, aqe2, 5, 0, 10, 5, 2, 10, b, b, false);
            this.a(adm22, aqe2, 8, 0, 10, 8, 2, 10, b, b, false);
            this.a(adm22, aqe2, 6, -1, 7, 7, -1, 8, c, c, false);
            this.a(adm22, aqe2, 6, -1, 3, 7, -1, 4, false);
            this.a(adm22, aqe2, 6, 1, 6);
            return true;
        }
    }

    public static class u
    extends r {
        private int o;

        public u() {
        }

        public u(cq cq2, aqe aqe2, int n2) {
            super(cq2, aqe2);
            this.o = n2 & 1;
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            if (this.o == 0) {
                int n2;
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe2, 10 - n2, 3 - n2, 20 - n2, 12 + n2, 3 - n2, 20, b, b, false);
                }
                this.a(adm22, aqe2, 7, 0, 6, 15, 0, 16, b, b, false);
                this.a(adm22, aqe2, 6, 0, 6, 6, 3, 20, b, b, false);
                this.a(adm22, aqe2, 16, 0, 6, 16, 3, 20, b, b, false);
                this.a(adm22, aqe2, 7, 1, 7, 7, 1, 20, b, b, false);
                this.a(adm22, aqe2, 15, 1, 7, 15, 1, 20, b, b, false);
                this.a(adm22, aqe2, 7, 1, 6, 9, 3, 6, b, b, false);
                this.a(adm22, aqe2, 13, 1, 6, 15, 3, 6, b, b, false);
                this.a(adm22, aqe2, 8, 1, 7, 9, 1, 7, b, b, false);
                this.a(adm22, aqe2, 13, 1, 7, 14, 1, 7, b, b, false);
                this.a(adm22, aqe2, 9, 0, 5, 13, 0, 5, b, b, false);
                this.a(adm22, aqe2, 10, 0, 7, 12, 0, 7, c, c, false);
                this.a(adm22, aqe2, 8, 0, 10, 8, 0, 12, c, c, false);
                this.a(adm22, aqe2, 14, 0, 10, 14, 0, 12, c, c, false);
                for (n2 = 18; n2 >= 7; n2 -= 3) {
                    this.a(adm22, e, 6, 3, n2, aqe2);
                    this.a(adm22, e, 16, 3, n2, aqe2);
                }
                this.a(adm22, e, 10, 0, 10, aqe2);
                this.a(adm22, e, 12, 0, 10, aqe2);
                this.a(adm22, e, 10, 0, 12, aqe2);
                this.a(adm22, e, 12, 0, 12, aqe2);
                this.a(adm22, e, 8, 3, 6, aqe2);
                this.a(adm22, e, 14, 3, 6, aqe2);
                this.a(adm22, b, 4, 2, 4, aqe2);
                this.a(adm22, e, 4, 1, 4, aqe2);
                this.a(adm22, b, 4, 0, 4, aqe2);
                this.a(adm22, b, 18, 2, 4, aqe2);
                this.a(adm22, e, 18, 1, 4, aqe2);
                this.a(adm22, b, 18, 0, 4, aqe2);
                this.a(adm22, b, 4, 2, 18, aqe2);
                this.a(adm22, e, 4, 1, 18, aqe2);
                this.a(adm22, b, 4, 0, 18, aqe2);
                this.a(adm22, b, 18, 2, 18, aqe2);
                this.a(adm22, e, 18, 1, 18, aqe2);
                this.a(adm22, b, 18, 0, 18, aqe2);
                this.a(adm22, b, 9, 7, 20, aqe2);
                this.a(adm22, b, 13, 7, 20, aqe2);
                this.a(adm22, aqe2, 6, 0, 21, 7, 4, 21, b, b, false);
                this.a(adm22, aqe2, 15, 0, 21, 16, 4, 21, b, b, false);
                this.a(adm22, aqe2, 11, 2, 16);
            } else if (this.o == 1) {
                int n3;
                this.a(adm22, aqe2, 9, 3, 18, 13, 3, 20, b, b, false);
                this.a(adm22, aqe2, 9, 0, 18, 9, 2, 18, b, b, false);
                this.a(adm22, aqe2, 13, 0, 18, 13, 2, 18, b, b, false);
                int \u26032 = 9;
                int \u26033 = 20;
                int \u26034 = 5;
                for (n3 = 0; n3 < 2; ++n3) {
                    this.a(adm22, b, \u26032, \u26034 + 1, \u26033, aqe2);
                    this.a(adm22, e, \u26032, \u26034, \u26033, aqe2);
                    this.a(adm22, b, \u26032, \u26034 - 1, \u26033, aqe2);
                    \u26032 = 13;
                }
                this.a(adm22, aqe2, 7, 3, 7, 15, 3, 14, b, b, false);
                \u26032 = 10;
                for (n3 = 0; n3 < 2; ++n3) {
                    this.a(adm22, aqe2, \u26032, 0, 10, \u26032, 6, 10, b, b, false);
                    this.a(adm22, aqe2, \u26032, 0, 12, \u26032, 6, 12, b, b, false);
                    this.a(adm22, e, \u26032, 0, 10, aqe2);
                    this.a(adm22, e, \u26032, 0, 12, aqe2);
                    this.a(adm22, e, \u26032, 4, 10, aqe2);
                    this.a(adm22, e, \u26032, 4, 12, aqe2);
                    \u26032 = 12;
                }
                \u26032 = 8;
                for (n3 = 0; n3 < 2; ++n3) {
                    this.a(adm22, aqe2, \u26032, 0, 7, \u26032, 2, 7, b, b, false);
                    this.a(adm22, aqe2, \u26032, 0, 14, \u26032, 2, 14, b, b, false);
                    \u26032 = 14;
                }
                this.a(adm22, aqe2, 8, 3, 8, 8, 3, 13, c, c, false);
                this.a(adm22, aqe2, 14, 3, 8, 14, 3, 13, c, c, false);
                this.a(adm22, aqe2, 11, 5, 13);
            }
            return true;
        }
    }

    public static class j
    extends r {
        public j() {
        }

        public j(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 2, 2, 2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 1, 8, 0, 14, 8, 14, a);
            int n2 = 7;
            alz \u26032 = b;
            this.a(adm2, aqe2, 0, n2, 0, 0, n2, 15, \u26032, \u26032, false);
            this.a(adm2, aqe2, 15, n2, 0, 15, n2, 15, \u26032, \u26032, false);
            this.a(adm2, aqe2, 1, n2, 0, 15, n2, 0, \u26032, \u26032, false);
            this.a(adm2, aqe2, 1, n2, 15, 14, n2, 15, \u26032, \u26032, false);
            for (n2 = 1; n2 <= 6; ++n2) {
                \u26032 = b;
                if (n2 == 2 || n2 == 6) {
                    \u26032 = a;
                }
                for (\u2603 = 0; \u2603 <= 15; \u2603 += 15) {
                    this.a(adm2, aqe2, \u2603, n2, 0, \u2603, n2, 1, \u26032, \u26032, false);
                    this.a(adm2, aqe2, \u2603, n2, 6, \u2603, n2, 9, \u26032, \u26032, false);
                    this.a(adm2, aqe2, \u2603, n2, 14, \u2603, n2, 15, \u26032, \u26032, false);
                }
                this.a(adm2, aqe2, 1, n2, 0, 1, n2, 0, \u26032, \u26032, false);
                this.a(adm2, aqe2, 6, n2, 0, 9, n2, 0, \u26032, \u26032, false);
                this.a(adm2, aqe2, 14, n2, 0, 14, n2, 0, \u26032, \u26032, false);
                this.a(adm2, aqe2, 1, n2, 15, 14, n2, 15, \u26032, \u26032, false);
            }
            this.a(adm2, aqe2, 6, 3, 6, 9, 6, 9, c, c, false);
            this.a(adm2, aqe2, 7, 4, 7, 8, 5, 8, afi.R.Q(), afi.R.Q(), false);
            for (n2 = 3; n2 <= 6; n2 += 3) {
                for (\u2603 = 6; \u2603 <= 9; \u2603 += 3) {
                    this.a(adm2, e, \u2603, n2, 6, aqe2);
                    this.a(adm2, e, \u2603, n2, 9, aqe2);
                }
            }
            this.a(adm2, aqe2, 5, 1, 6, 5, 2, 6, b, b, false);
            this.a(adm2, aqe2, 5, 1, 9, 5, 2, 9, b, b, false);
            this.a(adm2, aqe2, 10, 1, 6, 10, 2, 6, b, b, false);
            this.a(adm2, aqe2, 10, 1, 9, 10, 2, 9, b, b, false);
            this.a(adm2, aqe2, 6, 1, 5, 6, 2, 5, b, b, false);
            this.a(adm2, aqe2, 9, 1, 5, 9, 2, 5, b, b, false);
            this.a(adm2, aqe2, 6, 1, 10, 6, 2, 10, b, b, false);
            this.a(adm2, aqe2, 9, 1, 10, 9, 2, 10, b, b, false);
            this.a(adm2, aqe2, 5, 2, 5, 5, 6, 5, b, b, false);
            this.a(adm2, aqe2, 5, 2, 10, 5, 6, 10, b, b, false);
            this.a(adm2, aqe2, 10, 2, 5, 10, 6, 5, b, b, false);
            this.a(adm2, aqe2, 10, 2, 10, 10, 6, 10, b, b, false);
            this.a(adm2, aqe2, 5, 7, 1, 5, 7, 6, b, b, false);
            this.a(adm2, aqe2, 10, 7, 1, 10, 7, 6, b, b, false);
            this.a(adm2, aqe2, 5, 7, 9, 5, 7, 14, b, b, false);
            this.a(adm2, aqe2, 10, 7, 9, 10, 7, 14, b, b, false);
            this.a(adm2, aqe2, 1, 7, 5, 6, 7, 5, b, b, false);
            this.a(adm2, aqe2, 1, 7, 10, 6, 7, 10, b, b, false);
            this.a(adm2, aqe2, 9, 7, 5, 14, 7, 5, b, b, false);
            this.a(adm2, aqe2, 9, 7, 10, 14, 7, 10, b, b, false);
            this.a(adm2, aqe2, 2, 1, 2, 2, 1, 3, b, b, false);
            this.a(adm2, aqe2, 3, 1, 2, 3, 1, 2, b, b, false);
            this.a(adm2, aqe2, 13, 1, 2, 13, 1, 3, b, b, false);
            this.a(adm2, aqe2, 12, 1, 2, 12, 1, 2, b, b, false);
            this.a(adm2, aqe2, 2, 1, 12, 2, 1, 13, b, b, false);
            this.a(adm2, aqe2, 3, 1, 13, 3, 1, 13, b, b, false);
            this.a(adm2, aqe2, 13, 1, 12, 13, 1, 13, b, b, false);
            this.a(adm2, aqe2, 12, 1, 13, 12, 1, 13, b, b, false);
            return true;
        }
    }

    public static class n
    extends r {
        public n() {
        }

        public n(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 1, 2, 2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            v v2;
            alz alz2;
            int n2;
            v v3 = this.k.b[cq.c.a()];
            v2 = this.k;
            \u2603 = v3.b[cq.b.a()];
            \u2603 = v2.b[cq.b.a()];
            if (this.k.a / 25 > 0) {
                this.a(adm2, aqe2, 0, 8, v3.c[cq.a.a()]);
                this.a(adm2, aqe2, 0, 0, v2.c[cq.a.a()]);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 8, 1, 6, 8, 7, a);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 8, 8, 6, 8, 14, a);
            }
            for (n2 = 1; n2 <= 7; ++n2) {
                alz2 = b;
                if (n2 == 2 || n2 == 6) {
                    alz2 = a;
                }
                this.a(adm2, aqe2, 0, n2, 0, 0, n2, 15, alz2, alz2, false);
                this.a(adm2, aqe2, 7, n2, 0, 7, n2, 15, alz2, alz2, false);
                this.a(adm2, aqe2, 1, n2, 0, 6, n2, 0, alz2, alz2, false);
                this.a(adm2, aqe2, 1, n2, 15, 6, n2, 15, alz2, alz2, false);
            }
            for (n2 = 1; n2 <= 7; ++n2) {
                alz2 = c;
                if (n2 == 2 || n2 == 6) {
                    alz2 = e;
                }
                this.a(adm2, aqe2, 3, n2, 7, 4, n2, 8, alz2, alz2, false);
            }
            if (v2.c[cq.d.a()]) {
                this.a(adm2, aqe2, 3, 1, 0, 4, 2, 0, false);
            }
            if (v2.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 1, 3, 7, 2, 4, false);
            }
            if (v2.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 3, 0, 2, 4, false);
            }
            if (v3.c[cq.c.a()]) {
                this.a(adm2, aqe2, 3, 1, 15, 4, 2, 15, false);
            }
            if (v3.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 11, 0, 2, 12, false);
            }
            if (v3.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 1, 11, 7, 2, 12, false);
            }
            if (\u2603.c[cq.d.a()]) {
                this.a(adm2, aqe2, 3, 5, 0, 4, 6, 0, false);
            }
            if (\u2603.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 5, 3, 7, 6, 4, false);
                this.a(adm2, aqe2, 5, 4, 2, 6, 4, 5, b, b, false);
                this.a(adm2, aqe2, 6, 1, 2, 6, 3, 2, b, b, false);
                this.a(adm2, aqe2, 6, 1, 5, 6, 3, 5, b, b, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 5, 3, 0, 6, 4, false);
                this.a(adm2, aqe2, 1, 4, 2, 2, 4, 5, b, b, false);
                this.a(adm2, aqe2, 1, 1, 2, 1, 3, 2, b, b, false);
                this.a(adm2, aqe2, 1, 1, 5, 1, 3, 5, b, b, false);
            }
            if (\u2603.c[cq.c.a()]) {
                this.a(adm2, aqe2, 3, 5, 15, 4, 6, 15, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 5, 11, 0, 6, 12, false);
                this.a(adm2, aqe2, 1, 4, 10, 2, 4, 13, b, b, false);
                this.a(adm2, aqe2, 1, 1, 10, 1, 3, 10, b, b, false);
                this.a(adm2, aqe2, 1, 1, 13, 1, 3, 13, b, b, false);
            }
            if (\u2603.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 5, 11, 7, 6, 12, false);
                this.a(adm2, aqe2, 5, 4, 10, 6, 4, 13, b, b, false);
                this.a(adm2, aqe2, 6, 1, 10, 6, 3, 10, b, b, false);
                this.a(adm2, aqe2, 6, 1, 13, 6, 3, 13, b, b, false);
            }
            return true;
        }
    }

    public static class l
    extends r {
        public l() {
        }

        public l(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 2, 2, 1);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            v v2 = this.k.b[cq.f.a()];
            \u2603 = this.k;
            \u2603 = \u2603.b[cq.b.a()];
            \u2603 = v2.b[cq.b.a()];
            if (this.k.a / 25 > 0) {
                this.a(adm22, aqe2, 8, 0, v2.c[cq.a.a()]);
                this.a(adm22, aqe2, 0, 0, \u2603.c[cq.a.a()]);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm22, aqe2, 1, 8, 1, 7, 8, 6, a);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm22, aqe2, 8, 8, 1, 14, 8, 6, a);
            }
            for (int i2 = 1; i2 <= 7; ++i2) {
                alz alz2 = b;
                if (i2 == 2 || i2 == 6) {
                    alz2 = a;
                }
                this.a(adm22, aqe2, 0, i2, 0, 0, i2, 7, alz2, alz2, false);
                this.a(adm22, aqe2, 15, i2, 0, 15, i2, 7, alz2, alz2, false);
                this.a(adm22, aqe2, 1, i2, 0, 15, i2, 0, alz2, alz2, false);
                this.a(adm22, aqe2, 1, i2, 7, 14, i2, 7, alz2, alz2, false);
            }
            this.a(adm22, aqe2, 2, 1, 3, 2, 7, 4, b, b, false);
            this.a(adm22, aqe2, 3, 1, 2, 4, 7, 2, b, b, false);
            this.a(adm22, aqe2, 3, 1, 5, 4, 7, 5, b, b, false);
            this.a(adm22, aqe2, 13, 1, 3, 13, 7, 4, b, b, false);
            this.a(adm22, aqe2, 11, 1, 2, 12, 7, 2, b, b, false);
            this.a(adm22, aqe2, 11, 1, 5, 12, 7, 5, b, b, false);
            this.a(adm22, aqe2, 5, 1, 3, 5, 3, 4, b, b, false);
            this.a(adm22, aqe2, 10, 1, 3, 10, 3, 4, b, b, false);
            this.a(adm22, aqe2, 5, 7, 2, 10, 7, 5, b, b, false);
            this.a(adm22, aqe2, 5, 5, 2, 5, 7, 2, b, b, false);
            this.a(adm22, aqe2, 10, 5, 2, 10, 7, 2, b, b, false);
            this.a(adm22, aqe2, 5, 5, 5, 5, 7, 5, b, b, false);
            this.a(adm22, aqe2, 10, 5, 5, 10, 7, 5, b, b, false);
            this.a(adm22, b, 6, 6, 2, aqe2);
            this.a(adm22, b, 9, 6, 2, aqe2);
            this.a(adm22, b, 6, 6, 5, aqe2);
            this.a(adm22, b, 9, 6, 5, aqe2);
            this.a(adm22, aqe2, 5, 4, 3, 6, 4, 4, b, b, false);
            this.a(adm22, aqe2, 9, 4, 3, 10, 4, 4, b, b, false);
            this.a(adm22, e, 5, 4, 2, aqe2);
            this.a(adm22, e, 5, 4, 5, aqe2);
            this.a(adm22, e, 10, 4, 2, aqe2);
            this.a(adm22, e, 10, 4, 5, aqe2);
            if (\u2603.c[cq.d.a()]) {
                this.a(adm22, aqe2, 3, 1, 0, 4, 2, 0, false);
            }
            if (\u2603.c[cq.c.a()]) {
                this.a(adm22, aqe2, 3, 1, 7, 4, 2, 7, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm22, aqe2, 0, 1, 3, 0, 2, 4, false);
            }
            if (v2.c[cq.d.a()]) {
                this.a(adm22, aqe2, 11, 1, 0, 12, 2, 0, false);
            }
            if (v2.c[cq.c.a()]) {
                this.a(adm22, aqe2, 11, 1, 7, 12, 2, 7, false);
            }
            if (v2.c[cq.f.a()]) {
                this.a(adm22, aqe2, 15, 1, 3, 15, 2, 4, false);
            }
            if (\u2603.c[cq.d.a()]) {
                this.a(adm22, aqe2, 3, 5, 0, 4, 6, 0, false);
            }
            if (\u2603.c[cq.c.a()]) {
                this.a(adm22, aqe2, 3, 5, 7, 4, 6, 7, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm22, aqe2, 0, 5, 3, 0, 6, 4, false);
            }
            if (\u2603.c[cq.d.a()]) {
                this.a(adm22, aqe2, 11, 5, 0, 12, 6, 0, false);
            }
            if (\u2603.c[cq.c.a()]) {
                this.a(adm22, aqe2, 11, 5, 7, 12, 6, 7, false);
            }
            if (\u2603.c[cq.f.a()]) {
                this.a(adm22, aqe2, 15, 5, 3, 15, 6, 4, false);
            }
            return true;
        }
    }

    public static class o
    extends r {
        public o() {
        }

        public o(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 1, 1, 2);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            v v2 = this.k.b[cq.c.a()];
            \u2603 = this.k;
            if (this.k.a / 25 > 0) {
                this.a(adm2, aqe2, 0, 8, v2.c[cq.a.a()]);
                this.a(adm2, aqe2, 0, 0, \u2603.c[cq.a.a()]);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 4, 1, 6, 4, 7, a);
            }
            if (v2.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 4, 8, 6, 4, 14, a);
            }
            this.a(adm2, aqe2, 0, 3, 0, 0, 3, 15, b, b, false);
            this.a(adm2, aqe2, 7, 3, 0, 7, 3, 15, b, b, false);
            this.a(adm2, aqe2, 1, 3, 0, 7, 3, 0, b, b, false);
            this.a(adm2, aqe2, 1, 3, 15, 6, 3, 15, b, b, false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 2, 15, a, a, false);
            this.a(adm2, aqe2, 7, 2, 0, 7, 2, 15, a, a, false);
            this.a(adm2, aqe2, 1, 2, 0, 7, 2, 0, a, a, false);
            this.a(adm2, aqe2, 1, 2, 15, 6, 2, 15, a, a, false);
            this.a(adm2, aqe2, 0, 1, 0, 0, 1, 15, b, b, false);
            this.a(adm2, aqe2, 7, 1, 0, 7, 1, 15, b, b, false);
            this.a(adm2, aqe2, 1, 1, 0, 7, 1, 0, b, b, false);
            this.a(adm2, aqe2, 1, 1, 15, 6, 1, 15, b, b, false);
            this.a(adm2, aqe2, 1, 1, 1, 1, 1, 2, b, b, false);
            this.a(adm2, aqe2, 6, 1, 1, 6, 1, 2, b, b, false);
            this.a(adm2, aqe2, 1, 3, 1, 1, 3, 2, b, b, false);
            this.a(adm2, aqe2, 6, 3, 1, 6, 3, 2, b, b, false);
            this.a(adm2, aqe2, 1, 1, 13, 1, 1, 14, b, b, false);
            this.a(adm2, aqe2, 6, 1, 13, 6, 1, 14, b, b, false);
            this.a(adm2, aqe2, 1, 3, 13, 1, 3, 14, b, b, false);
            this.a(adm2, aqe2, 6, 3, 13, 6, 3, 14, b, b, false);
            this.a(adm2, aqe2, 2, 1, 6, 2, 3, 6, b, b, false);
            this.a(adm2, aqe2, 5, 1, 6, 5, 3, 6, b, b, false);
            this.a(adm2, aqe2, 2, 1, 9, 2, 3, 9, b, b, false);
            this.a(adm2, aqe2, 5, 1, 9, 5, 3, 9, b, b, false);
            this.a(adm2, aqe2, 3, 2, 6, 4, 2, 6, b, b, false);
            this.a(adm2, aqe2, 3, 2, 9, 4, 2, 9, b, b, false);
            this.a(adm2, aqe2, 2, 2, 7, 2, 2, 8, b, b, false);
            this.a(adm2, aqe2, 5, 2, 7, 5, 2, 8, b, b, false);
            this.a(adm2, e, 2, 2, 5, aqe2);
            this.a(adm2, e, 5, 2, 5, aqe2);
            this.a(adm2, e, 2, 2, 10, aqe2);
            this.a(adm2, e, 5, 2, 10, aqe2);
            this.a(adm2, b, 2, 3, 5, aqe2);
            this.a(adm2, b, 5, 3, 5, aqe2);
            this.a(adm2, b, 2, 3, 10, aqe2);
            this.a(adm2, b, 5, 3, 10, aqe2);
            if (\u2603.c[cq.d.a()]) {
                this.a(adm2, aqe2, 3, 1, 0, 4, 2, 0, false);
            }
            if (\u2603.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 1, 3, 7, 2, 4, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 3, 0, 2, 4, false);
            }
            if (v2.c[cq.c.a()]) {
                this.a(adm2, aqe2, 3, 1, 15, 4, 2, 15, false);
            }
            if (v2.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 11, 0, 2, 12, false);
            }
            if (v2.c[cq.f.a()]) {
                this.a(adm2, aqe2, 7, 1, 11, 7, 2, 12, false);
            }
            return true;
        }
    }

    public static class k
    extends r {
        public k() {
        }

        public k(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 2, 1, 1);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            v v2 = this.k.b[cq.f.a()];
            \u2603 = this.k;
            if (this.k.a / 25 > 0) {
                this.a(adm2, aqe2, 8, 0, v2.c[cq.a.a()]);
                this.a(adm2, aqe2, 0, 0, \u2603.c[cq.a.a()]);
            }
            if (\u2603.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 4, 1, 7, 4, 6, a);
            }
            if (v2.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 8, 4, 1, 14, 4, 6, a);
            }
            this.a(adm2, aqe2, 0, 3, 0, 0, 3, 7, b, b, false);
            this.a(adm2, aqe2, 15, 3, 0, 15, 3, 7, b, b, false);
            this.a(adm2, aqe2, 1, 3, 0, 15, 3, 0, b, b, false);
            this.a(adm2, aqe2, 1, 3, 7, 14, 3, 7, b, b, false);
            this.a(adm2, aqe2, 0, 2, 0, 0, 2, 7, a, a, false);
            this.a(adm2, aqe2, 15, 2, 0, 15, 2, 7, a, a, false);
            this.a(adm2, aqe2, 1, 2, 0, 15, 2, 0, a, a, false);
            this.a(adm2, aqe2, 1, 2, 7, 14, 2, 7, a, a, false);
            this.a(adm2, aqe2, 0, 1, 0, 0, 1, 7, b, b, false);
            this.a(adm2, aqe2, 15, 1, 0, 15, 1, 7, b, b, false);
            this.a(adm2, aqe2, 1, 1, 0, 15, 1, 0, b, b, false);
            this.a(adm2, aqe2, 1, 1, 7, 14, 1, 7, b, b, false);
            this.a(adm2, aqe2, 5, 1, 0, 10, 1, 4, b, b, false);
            this.a(adm2, aqe2, 6, 2, 0, 9, 2, 3, a, a, false);
            this.a(adm2, aqe2, 5, 3, 0, 10, 3, 4, b, b, false);
            this.a(adm2, e, 6, 2, 3, aqe2);
            this.a(adm2, e, 9, 2, 3, aqe2);
            if (\u2603.c[cq.d.a()]) {
                this.a(adm2, aqe2, 3, 1, 0, 4, 2, 0, false);
            }
            if (\u2603.c[cq.c.a()]) {
                this.a(adm2, aqe2, 3, 1, 7, 4, 2, 7, false);
            }
            if (\u2603.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 3, 0, 2, 4, false);
            }
            if (v2.c[cq.d.a()]) {
                this.a(adm2, aqe2, 11, 1, 0, 12, 2, 0, false);
            }
            if (v2.c[cq.c.a()]) {
                this.a(adm2, aqe2, 11, 1, 7, 12, 2, 7, false);
            }
            if (v2.c[cq.f.a()]) {
                this.a(adm2, aqe2, 15, 1, 3, 15, 2, 4, false);
            }
            return true;
        }
    }

    public static class m
    extends r {
        public m() {
        }

        public m(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 1, 2, 1);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.k.a / 25 > 0) {
                this.a(adm2, aqe2, 0, 0, this.k.c[cq.a.a()]);
            }
            v v2 = this.k.b[cq.b.a()];
            if (v2.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 8, 1, 6, 8, 6, a);
            }
            this.a(adm2, aqe2, 0, 4, 0, 0, 4, 7, b, b, false);
            this.a(adm2, aqe2, 7, 4, 0, 7, 4, 7, b, b, false);
            this.a(adm2, aqe2, 1, 4, 0, 6, 4, 0, b, b, false);
            this.a(adm2, aqe2, 1, 4, 7, 6, 4, 7, b, b, false);
            this.a(adm2, aqe2, 2, 4, 1, 2, 4, 2, b, b, false);
            this.a(adm2, aqe2, 1, 4, 2, 1, 4, 2, b, b, false);
            this.a(adm2, aqe2, 5, 4, 1, 5, 4, 2, b, b, false);
            this.a(adm2, aqe2, 6, 4, 2, 6, 4, 2, b, b, false);
            this.a(adm2, aqe2, 2, 4, 5, 2, 4, 6, b, b, false);
            this.a(adm2, aqe2, 1, 4, 5, 1, 4, 5, b, b, false);
            this.a(adm2, aqe2, 5, 4, 5, 5, 4, 6, b, b, false);
            this.a(adm2, aqe2, 6, 4, 5, 6, 4, 5, b, b, false);
            v3 = this.k;
            for (int i2 = 1; i2 <= 5; i2 += 4) {
                \u2603 = 0;
                if (v3.c[cq.d.a()]) {
                    this.a(adm2, aqe2, 2, i2, \u2603, 2, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 5, i2, \u2603, 5, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 3, i2 + 2, \u2603, 4, i2 + 2, \u2603, b, b, false);
                } else {
                    this.a(adm2, aqe2, 0, i2, \u2603, 7, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 0, i2 + 1, \u2603, 7, i2 + 1, \u2603, a, a, false);
                }
                \u2603 = 7;
                if (v3.c[cq.c.a()]) {
                    this.a(adm2, aqe2, 2, i2, \u2603, 2, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 5, i2, \u2603, 5, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 3, i2 + 2, \u2603, 4, i2 + 2, \u2603, b, b, false);
                } else {
                    this.a(adm2, aqe2, 0, i2, \u2603, 7, i2 + 2, \u2603, b, b, false);
                    this.a(adm2, aqe2, 0, i2 + 1, \u2603, 7, i2 + 1, \u2603, a, a, false);
                }
                \u2603 = 0;
                if (v3.c[cq.e.a()]) {
                    this.a(adm2, aqe2, \u2603, i2, 2, \u2603, i2 + 2, 2, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2, 5, \u2603, i2 + 2, 5, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2 + 2, 3, \u2603, i2 + 2, 4, b, b, false);
                } else {
                    this.a(adm2, aqe2, \u2603, i2, 0, \u2603, i2 + 2, 7, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2 + 1, 0, \u2603, i2 + 1, 7, a, a, false);
                }
                \u2603 = 7;
                if (v3.c[cq.f.a()]) {
                    this.a(adm2, aqe2, \u2603, i2, 2, \u2603, i2 + 2, 2, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2, 5, \u2603, i2 + 2, 5, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2 + 2, 3, \u2603, i2 + 2, 4, b, b, false);
                } else {
                    this.a(adm2, aqe2, \u2603, i2, 0, \u2603, i2 + 2, 7, b, b, false);
                    this.a(adm2, aqe2, \u2603, i2 + 1, 0, \u2603, i2 + 1, 7, a, a, false);
                }
                v v3 = v2;
            }
            return true;
        }
    }

    public static class t
    extends r {
        public t() {
        }

        public t(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 1, 1, 1);
        }

        @Override
        public boolean a(adm adm22, Random random, aqe aqe2) {
            adm adm22;
            if (this.k.a / 25 > 0) {
                this.a(adm22, aqe2, 0, 0, this.k.c[cq.a.a()]);
            }
            if (this.k.b[cq.b.a()] == null) {
                this.a(adm22, aqe2, 1, 4, 1, 6, 4, 6, a);
            }
            for (int i2 = 1; i2 <= 6; ++i2) {
                for (\u2603 = 1; \u2603 <= 6; ++\u2603) {
                    if (random.nextInt(3) == 0) continue;
                    \u2603 = 2 + (random.nextInt(4) == 0 ? 0 : 1);
                    this.a(adm22, aqe2, i2, \u2603, \u2603, i2, 3, \u2603, afi.v.a(1), afi.v.a(1), false);
                }
            }
            this.a(adm22, aqe2, 0, 1, 0, 0, 1, 7, b, b, false);
            this.a(adm22, aqe2, 7, 1, 0, 7, 1, 7, b, b, false);
            this.a(adm22, aqe2, 1, 1, 0, 6, 1, 0, b, b, false);
            this.a(adm22, aqe2, 1, 1, 7, 6, 1, 7, b, b, false);
            this.a(adm22, aqe2, 0, 2, 0, 0, 2, 7, c, c, false);
            this.a(adm22, aqe2, 7, 2, 0, 7, 2, 7, c, c, false);
            this.a(adm22, aqe2, 1, 2, 0, 6, 2, 0, c, c, false);
            this.a(adm22, aqe2, 1, 2, 7, 6, 2, 7, c, c, false);
            this.a(adm22, aqe2, 0, 3, 0, 0, 3, 7, b, b, false);
            this.a(adm22, aqe2, 7, 3, 0, 7, 3, 7, b, b, false);
            this.a(adm22, aqe2, 1, 3, 0, 6, 3, 0, b, b, false);
            this.a(adm22, aqe2, 1, 3, 7, 6, 3, 7, b, b, false);
            this.a(adm22, aqe2, 0, 1, 3, 0, 2, 4, c, c, false);
            this.a(adm22, aqe2, 7, 1, 3, 7, 2, 4, c, c, false);
            this.a(adm22, aqe2, 3, 1, 0, 4, 2, 0, c, c, false);
            this.a(adm22, aqe2, 3, 1, 7, 4, 2, 7, c, c, false);
            if (this.k.c[cq.d.a()]) {
                this.a(adm22, aqe2, 3, 1, 0, 4, 2, 0, false);
            }
            return true;
        }
    }

    public static class s
    extends r {
        private int o;

        public s() {
        }

        public s(cq cq2, v v2, Random random) {
            super(1, cq2, v2, 1, 1, 1);
            this.o = random.nextInt(3);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (this.k.a / 25 > 0) {
                this.a(adm2, aqe2, 0, 0, this.k.c[cq.a.a()]);
            }
            if (this.k.b[cq.b.a()] == null) {
                this.a(adm2, aqe2, 1, 4, 1, 6, 4, 6, a);
            }
            boolean bl2 = \u2603 = this.o != 0 && random.nextBoolean() && !this.k.c[cq.a.a()] && !this.k.c[cq.b.a()] && this.k.c() > 1;
            if (this.o == 0) {
                this.a(adm2, aqe2, 0, 1, 0, 2, 1, 2, b, b, false);
                this.a(adm2, aqe2, 0, 3, 0, 2, 3, 2, b, b, false);
                this.a(adm2, aqe2, 0, 2, 0, 0, 2, 2, a, a, false);
                this.a(adm2, aqe2, 1, 2, 0, 2, 2, 0, a, a, false);
                this.a(adm2, e, 1, 2, 1, aqe2);
                this.a(adm2, aqe2, 5, 1, 0, 7, 1, 2, b, b, false);
                this.a(adm2, aqe2, 5, 3, 0, 7, 3, 2, b, b, false);
                this.a(adm2, aqe2, 7, 2, 0, 7, 2, 2, a, a, false);
                this.a(adm2, aqe2, 5, 2, 0, 6, 2, 0, a, a, false);
                this.a(adm2, e, 6, 2, 1, aqe2);
                this.a(adm2, aqe2, 0, 1, 5, 2, 1, 7, b, b, false);
                this.a(adm2, aqe2, 0, 3, 5, 2, 3, 7, b, b, false);
                this.a(adm2, aqe2, 0, 2, 5, 0, 2, 7, a, a, false);
                this.a(adm2, aqe2, 1, 2, 7, 2, 2, 7, a, a, false);
                this.a(adm2, e, 1, 2, 6, aqe2);
                this.a(adm2, aqe2, 5, 1, 5, 7, 1, 7, b, b, false);
                this.a(adm2, aqe2, 5, 3, 5, 7, 3, 7, b, b, false);
                this.a(adm2, aqe2, 7, 2, 5, 7, 2, 7, a, a, false);
                this.a(adm2, aqe2, 5, 2, 7, 6, 2, 7, a, a, false);
                this.a(adm2, e, 6, 2, 6, aqe2);
                if (this.k.c[cq.d.a()]) {
                    this.a(adm2, aqe2, 3, 3, 0, 4, 3, 0, b, b, false);
                } else {
                    this.a(adm2, aqe2, 3, 3, 0, 4, 3, 1, b, b, false);
                    this.a(adm2, aqe2, 3, 2, 0, 4, 2, 0, a, a, false);
                    this.a(adm2, aqe2, 3, 1, 0, 4, 1, 1, b, b, false);
                }
                if (this.k.c[cq.c.a()]) {
                    this.a(adm2, aqe2, 3, 3, 7, 4, 3, 7, b, b, false);
                } else {
                    this.a(adm2, aqe2, 3, 3, 6, 4, 3, 7, b, b, false);
                    this.a(adm2, aqe2, 3, 2, 7, 4, 2, 7, a, a, false);
                    this.a(adm2, aqe2, 3, 1, 6, 4, 1, 7, b, b, false);
                }
                if (this.k.c[cq.e.a()]) {
                    this.a(adm2, aqe2, 0, 3, 3, 0, 3, 4, b, b, false);
                } else {
                    this.a(adm2, aqe2, 0, 3, 3, 1, 3, 4, b, b, false);
                    this.a(adm2, aqe2, 0, 2, 3, 0, 2, 4, a, a, false);
                    this.a(adm2, aqe2, 0, 1, 3, 1, 1, 4, b, b, false);
                }
                if (this.k.c[cq.f.a()]) {
                    this.a(adm2, aqe2, 7, 3, 3, 7, 3, 4, b, b, false);
                } else {
                    this.a(adm2, aqe2, 6, 3, 3, 7, 3, 4, b, b, false);
                    this.a(adm2, aqe2, 7, 2, 3, 7, 2, 4, a, a, false);
                    this.a(adm2, aqe2, 6, 1, 3, 7, 1, 4, b, b, false);
                }
            } else if (this.o == 1) {
                this.a(adm2, aqe2, 2, 1, 2, 2, 3, 2, b, b, false);
                this.a(adm2, aqe2, 2, 1, 5, 2, 3, 5, b, b, false);
                this.a(adm2, aqe2, 5, 1, 5, 5, 3, 5, b, b, false);
                this.a(adm2, aqe2, 5, 1, 2, 5, 3, 2, b, b, false);
                this.a(adm2, e, 2, 2, 2, aqe2);
                this.a(adm2, e, 2, 2, 5, aqe2);
                this.a(adm2, e, 5, 2, 5, aqe2);
                this.a(adm2, e, 5, 2, 2, aqe2);
                this.a(adm2, aqe2, 0, 1, 0, 1, 3, 0, b, b, false);
                this.a(adm2, aqe2, 0, 1, 1, 0, 3, 1, b, b, false);
                this.a(adm2, aqe2, 0, 1, 7, 1, 3, 7, b, b, false);
                this.a(adm2, aqe2, 0, 1, 6, 0, 3, 6, b, b, false);
                this.a(adm2, aqe2, 6, 1, 7, 7, 3, 7, b, b, false);
                this.a(adm2, aqe2, 7, 1, 6, 7, 3, 6, b, b, false);
                this.a(adm2, aqe2, 6, 1, 0, 7, 3, 0, b, b, false);
                this.a(adm2, aqe2, 7, 1, 1, 7, 3, 1, b, b, false);
                this.a(adm2, a, 1, 2, 0, aqe2);
                this.a(adm2, a, 0, 2, 1, aqe2);
                this.a(adm2, a, 1, 2, 7, aqe2);
                this.a(adm2, a, 0, 2, 6, aqe2);
                this.a(adm2, a, 6, 2, 7, aqe2);
                this.a(adm2, a, 7, 2, 6, aqe2);
                this.a(adm2, a, 6, 2, 0, aqe2);
                this.a(adm2, a, 7, 2, 1, aqe2);
                if (!this.k.c[cq.d.a()]) {
                    this.a(adm2, aqe2, 1, 3, 0, 6, 3, 0, b, b, false);
                    this.a(adm2, aqe2, 1, 2, 0, 6, 2, 0, a, a, false);
                    this.a(adm2, aqe2, 1, 1, 0, 6, 1, 0, b, b, false);
                }
                if (!this.k.c[cq.c.a()]) {
                    this.a(adm2, aqe2, 1, 3, 7, 6, 3, 7, b, b, false);
                    this.a(adm2, aqe2, 1, 2, 7, 6, 2, 7, a, a, false);
                    this.a(adm2, aqe2, 1, 1, 7, 6, 1, 7, b, b, false);
                }
                if (!this.k.c[cq.e.a()]) {
                    this.a(adm2, aqe2, 0, 3, 1, 0, 3, 6, b, b, false);
                    this.a(adm2, aqe2, 0, 2, 1, 0, 2, 6, a, a, false);
                    this.a(adm2, aqe2, 0, 1, 1, 0, 1, 6, b, b, false);
                }
                if (!this.k.c[cq.f.a()]) {
                    this.a(adm2, aqe2, 7, 3, 1, 7, 3, 6, b, b, false);
                    this.a(adm2, aqe2, 7, 2, 1, 7, 2, 6, a, a, false);
                    this.a(adm2, aqe2, 7, 1, 1, 7, 1, 6, b, b, false);
                }
            } else if (this.o == 2) {
                this.a(adm2, aqe2, 0, 1, 0, 0, 1, 7, b, b, false);
                this.a(adm2, aqe2, 7, 1, 0, 7, 1, 7, b, b, false);
                this.a(adm2, aqe2, 1, 1, 0, 6, 1, 0, b, b, false);
                this.a(adm2, aqe2, 1, 1, 7, 6, 1, 7, b, b, false);
                this.a(adm2, aqe2, 0, 2, 0, 0, 2, 7, c, c, false);
                this.a(adm2, aqe2, 7, 2, 0, 7, 2, 7, c, c, false);
                this.a(adm2, aqe2, 1, 2, 0, 6, 2, 0, c, c, false);
                this.a(adm2, aqe2, 1, 2, 7, 6, 2, 7, c, c, false);
                this.a(adm2, aqe2, 0, 3, 0, 0, 3, 7, b, b, false);
                this.a(adm2, aqe2, 7, 3, 0, 7, 3, 7, b, b, false);
                this.a(adm2, aqe2, 1, 3, 0, 6, 3, 0, b, b, false);
                this.a(adm2, aqe2, 1, 3, 7, 6, 3, 7, b, b, false);
                this.a(adm2, aqe2, 0, 1, 3, 0, 2, 4, c, c, false);
                this.a(adm2, aqe2, 7, 1, 3, 7, 2, 4, c, c, false);
                this.a(adm2, aqe2, 3, 1, 0, 4, 2, 0, c, c, false);
                this.a(adm2, aqe2, 3, 1, 7, 4, 2, 7, c, c, false);
                if (this.k.c[cq.d.a()]) {
                    this.a(adm2, aqe2, 3, 1, 0, 4, 2, 0, false);
                }
                if (this.k.c[cq.c.a()]) {
                    this.a(adm2, aqe2, 3, 1, 7, 4, 2, 7, false);
                }
                if (this.k.c[cq.e.a()]) {
                    this.a(adm2, aqe2, 0, 1, 3, 0, 2, 4, false);
                }
                if (this.k.c[cq.f.a()]) {
                    this.a(adm2, aqe2, 7, 1, 3, 7, 2, 4, false);
                }
            }
            if (\u2603) {
                this.a(adm2, aqe2, 3, 1, 3, 4, 1, 4, b, b, false);
                this.a(adm2, aqe2, 3, 2, 3, 4, 2, 4, a, a, false);
                this.a(adm2, aqe2, 3, 3, 3, 4, 3, 4, b, b, false);
            }
            return true;
        }
    }

    public static class p
    extends r {
        public p() {
        }

        public p(cq cq2, v v2) {
            super(1, cq2, v2, 1, 1, 1);
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            this.a(adm2, aqe2, 0, 3, 0, 2, 3, 7, b, b, false);
            this.a(adm2, aqe2, 5, 3, 0, 7, 3, 7, b, b, false);
            this.a(adm2, aqe2, 0, 2, 0, 1, 2, 7, b, b, false);
            this.a(adm2, aqe2, 6, 2, 0, 7, 2, 7, b, b, false);
            this.a(adm2, aqe2, 0, 1, 0, 0, 1, 7, b, b, false);
            this.a(adm2, aqe2, 7, 1, 0, 7, 1, 7, b, b, false);
            this.a(adm2, aqe2, 0, 1, 7, 7, 3, 7, b, b, false);
            this.a(adm2, aqe2, 1, 1, 0, 2, 3, 0, b, b, false);
            this.a(adm2, aqe2, 5, 1, 0, 6, 3, 0, b, b, false);
            if (this.k.c[cq.c.a()]) {
                this.a(adm2, aqe2, 3, 1, 7, 4, 2, 7, false);
            }
            if (this.k.c[cq.e.a()]) {
                this.a(adm2, aqe2, 0, 1, 3, 1, 2, 4, false);
            }
            if (this.k.c[cq.f.a()]) {
                this.a(adm2, aqe2, 6, 1, 3, 7, 2, 4, false);
            }
            return true;
        }
    }

    public static class h
    extends r {
        private v o;
        private v p;
        private List<r> q = Lists.newArrayList();

        public h() {
        }

        public h(Random random, int n2, int n3, cq cq2) {
            super(0);
            this.m = cq2;
            switch (this.m) {
                case c: 
                case d: {
                    this.l = new aqe(n2, 39, n3, n2 + 58 - 1, 61, n3 + 58 - 1);
                    break;
                }
                default: {
                    this.l = new aqe(n2, 39, n3, n2 + 58 - 1, 61, n3 + 58 - 1);
                }
            }
            List<v> list = this.a(random);
            this.o.d = true;
            this.q.add(new p(this.m, this.o));
            this.q.add(new j(this.m, this.p, random));
            ArrayList<i> \u26032 = Lists.newArrayList();
            \u26032.add(new b());
            \u26032.add(new d());
            \u26032.add(new e());
            \u26032.add(new a());
            \u26032.add(new c());
            \u26032.add(new g());
            \u26032.add(new f());
            block3: for (v v2 : list) {
                if (v2.d || v2.b()) continue;
                for (i i2 : \u26032) {
                    if (!i2.a(v2)) continue;
                    this.q.add(i2.a(this.m, v2, random));
                    continue block3;
                }
            }
            int n4 = this.l.b;
            int n5 = this.a(9, 22);
            \u2603 = this.b(9, 22);
            for (r r2 : this.q) {
                r2.c().a(n5, n4, \u2603);
            }
            aqe aqe2 = aqe.a(this.a(1, 1), this.d(1), this.b(1, 1), this.a(23, 21), this.d(8), this.b(23, 21));
            aqe aqe3 = aqe.a(this.a(34, 1), this.d(1), this.b(34, 1), this.a(56, 21), this.d(8), this.b(56, 21));
            aqe \u26033 = aqe.a(this.a(22, 22), this.d(13), this.b(22, 22), this.a(35, 35), this.d(17), this.b(35, 35));
            int \u26034 = random.nextInt();
            this.q.add(new u(this.m, aqe2, \u26034++));
            this.q.add(new u(this.m, aqe3, \u26034++));
            this.q.add(new q(this.m, \u26033));
        }

        private List<v> a(Random random) {
            int n2;
            v[] vArray = new v[75];
            for (int n22 = 0; n22 < 5; ++n22) {
                for (int i2 = 0; i2 < 4; ++i2) {
                    \u2603 = 0;
                    \u2603 = h.b(n22, \u2603, i2);
                    vArray[\u2603] = new v(\u2603);
                }
            }
            for (n2 = 0; n2 < 5; ++n2) {
                for (i2 = 0; i2 < 4; ++i2) {
                    \u2603 = 1;
                    \u2603 = h.b(n2, \u2603, i2);
                    vArray[\u2603] = new v(\u2603);
                }
            }
            for (n2 = 1; n2 < 4; ++n2) {
                for (i2 = 0; i2 < 2; ++i2) {
                    \u2603 = 2;
                    \u2603 = h.b(n2, \u2603, i2);
                    vArray[\u2603] = new v(\u2603);
                }
            }
            this.o = vArray[g];
            for (n2 = 0; n2 < 5; ++n2) {
                for (i2 = 0; i2 < 5; ++i2) {
                    for (\u2603 = 0; \u2603 < 3; ++\u2603) {
                        \u2603 = h.b(n2, \u2603, i2);
                        if (vArray[\u2603] == null) continue;
                        for (cq cq2 : cq.values()) {
                            int n3 = n2 + cq2.g();
                            \u2603 = \u2603 + cq2.h();
                            \u2603 = i2 + cq2.i();
                            if (n3 < 0 || n3 >= 5 || \u2603 < 0 || \u2603 >= 5 || \u2603 < 0 || \u2603 >= 3 || vArray[\u2603 = h.b(n3, \u2603, \u2603)] == null) continue;
                            if (\u2603 != i2) {
                                vArray[\u2603].a(cq2.d(), vArray[\u2603]);
                                continue;
                            }
                            vArray[\u2603].a(cq2, vArray[\u2603]);
                        }
                    }
                }
            }
            v v2 = new v(1003);
            vArray[h].a(cq.b, v2);
            v v3 = new v(1001);
            vArray[i].a(cq.d, v3);
            \u2603 = new v(1002);
            vArray[j].a(cq.d, \u2603);
            v2.d = true;
            v3.d = true;
            \u2603.d = true;
            this.o.e = true;
            this.p = vArray[h.b(random.nextInt(4), 0, 2)];
            this.p.d = true;
            this.p.b[cq.f.a()].d = true;
            this.p.b[cq.c.a()].d = true;
            this.p.b[cq.f.a()].b[cq.c.a()].d = true;
            this.p.b[cq.b.a()].d = true;
            this.p.b[cq.f.a()].b[cq.b.a()].d = true;
            this.p.b[cq.c.a()].b[cq.b.a()].d = true;
            this.p.b[cq.f.a()].b[cq.c.a()].b[cq.b.a()].d = true;
            ArrayList<v> \u26032 = Lists.newArrayList();
            for (v v4 : vArray) {
                if (v4 == null) continue;
                v4.a();
                \u26032.add(v4);
            }
            v2.a();
            Collections.shuffle(\u26032, random);
            int \u26033 = 1;
            for (v v32 : \u26032) {
                int n3 = 0;
                for (int i3 = 0; n3 < 2 && i3 < 5; ++i3) {
                    \u2603 = random.nextInt(6);
                    if (!v32.c[\u2603]) continue;
                    \u2603 = cq.a(\u2603).d().a();
                    v32.c[\u2603] = false;
                    v32.b[\u2603].c[\u2603] = false;
                    if (v32.a(\u26033++) && v32.b[\u2603].a(\u26033++)) {
                        ++n3;
                        continue;
                    }
                    v32.c[\u2603] = true;
                    v32.b[\u2603].c[\u2603] = true;
                }
            }
            \u26032.add(v2);
            \u26032.add(v3);
            \u26032.add(\u2603);
            return \u26032;
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2 = Math.max(adm2.F(), 64) - this.l.b;
            this.a(adm2, aqe2, 0, 0, 0, 58, n2, 58, false);
            this.a(false, 0, adm2, random, aqe2);
            this.a(true, 33, adm2, random, aqe2);
            this.b(adm2, random, aqe2);
            this.c(adm2, random, aqe2);
            this.d(adm2, random, aqe2);
            this.e(adm2, random, aqe2);
            this.f(adm2, random, aqe2);
            this.g(adm2, random, aqe2);
            for (\u2603 = 0; \u2603 < 7; ++\u2603) {
                \u2603 = 0;
                while (\u2603 < 7) {
                    if (\u2603 == 0 && \u2603 == 3) {
                        \u2603 = 6;
                    }
                    \u2603 = \u2603 * 9;
                    \u2603 = \u2603 * 9;
                    for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                        for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                            this.a(adm2, b, \u2603 + \u2603, 0, \u2603 + \u2603, aqe2);
                            this.b(adm2, b, \u2603 + \u2603, -1, \u2603 + \u2603, aqe2);
                        }
                    }
                    if (\u2603 == 0 || \u2603 == 6) {
                        ++\u2603;
                        continue;
                    }
                    \u2603 += 6;
                }
            }
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                this.a(adm2, aqe2, -1 - \u2603, 0 + \u2603 * 2, -1 - \u2603, -1 - \u2603, 23, 58 + \u2603, false);
                this.a(adm2, aqe2, 58 + \u2603, 0 + \u2603 * 2, -1 - \u2603, 58 + \u2603, 23, 58 + \u2603, false);
                this.a(adm2, aqe2, 0 - \u2603, 0 + \u2603 * 2, -1 - \u2603, 57 + \u2603, 23, -1 - \u2603, false);
                this.a(adm2, aqe2, 0 - \u2603, 0 + \u2603 * 2, 58 + \u2603, 57 + \u2603, 23, 58 + \u2603, false);
            }
            for (r r2 : this.q) {
                if (!r2.c().a(aqe2)) continue;
                r2.a(adm2, random, aqe2);
            }
            return true;
        }

        private void a(boolean bl2, int n2, adm adm2, Random random, aqe aqe2) {
            int n3 = 24;
            if (this.a(aqe2, n2, 0, n2 + 23, 20)) {
                this.a(adm2, aqe2, n2 + 0, 0, 0, n2 + 24, 0, 20, a, a, false);
                this.a(adm2, aqe2, n2 + 0, 1, 0, n2 + 24, 10, 20, false);
                for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                    this.a(adm2, aqe2, n2 + \u2603, \u2603 + 1, \u2603, n2 + \u2603, \u2603 + 1, 20, b, b, false);
                    this.a(adm2, aqe2, n2 + \u2603 + 7, \u2603 + 5, \u2603 + 7, n2 + \u2603 + 7, \u2603 + 5, 20, b, b, false);
                    this.a(adm2, aqe2, n2 + 17 - \u2603, \u2603 + 5, \u2603 + 7, n2 + 17 - \u2603, \u2603 + 5, 20, b, b, false);
                    this.a(adm2, aqe2, n2 + 24 - \u2603, \u2603 + 1, \u2603, n2 + 24 - \u2603, \u2603 + 1, 20, b, b, false);
                    this.a(adm2, aqe2, n2 + \u2603 + 1, \u2603 + 1, \u2603, n2 + 23 - \u2603, \u2603 + 1, \u2603, b, b, false);
                    this.a(adm2, aqe2, n2 + \u2603 + 8, \u2603 + 5, \u2603 + 7, n2 + 16 - \u2603, \u2603 + 5, \u2603 + 7, b, b, false);
                }
                this.a(adm2, aqe2, n2 + 4, 4, 4, n2 + 6, 4, 20, a, a, false);
                this.a(adm2, aqe2, n2 + 7, 4, 4, n2 + 17, 4, 6, a, a, false);
                this.a(adm2, aqe2, n2 + 18, 4, 4, n2 + 20, 4, 20, a, a, false);
                this.a(adm2, aqe2, n2 + 11, 8, 11, n2 + 13, 8, 20, a, a, false);
                this.a(adm2, d, n2 + 12, 9, 12, aqe2);
                this.a(adm2, d, n2 + 12, 9, 15, aqe2);
                this.a(adm2, d, n2 + 12, 9, 18, aqe2);
                \u2603 = bl2 ? n2 + 19 : n2 + 5;
                \u2603 = bl2 ? n2 + 5 : n2 + 19;
                for (\u2603 = 20; \u2603 >= 5; \u2603 -= 3) {
                    this.a(adm2, d, \u2603, 5, \u2603, aqe2);
                }
                for (\u2603 = 19; \u2603 >= 7; \u2603 -= 3) {
                    this.a(adm2, d, \u2603, 5, \u2603, aqe2);
                }
                for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                    \u2603 = bl2 ? n2 + (24 - (17 - \u2603 * 3)) : n2 + 17 - \u2603 * 3;
                    this.a(adm2, d, \u2603, 5, 5, aqe2);
                }
                this.a(adm2, d, \u2603, 5, 5, aqe2);
                this.a(adm2, aqe2, n2 + 11, 1, 12, n2 + 13, 7, 12, a, a, false);
                this.a(adm2, aqe2, n2 + 12, 1, 11, n2 + 12, 7, 13, a, a, false);
            }
        }

        private void b(adm adm2, Random random, aqe aqe2) {
            if (this.a(aqe2, 22, 5, 35, 17)) {
                this.a(adm2, aqe2, 25, 0, 0, 32, 8, 20, false);
                for (int i2 = 0; i2 < 4; ++i2) {
                    this.a(adm2, aqe2, 24, 2, 5 + i2 * 4, 24, 4, 5 + i2 * 4, b, b, false);
                    this.a(adm2, aqe2, 22, 4, 5 + i2 * 4, 23, 4, 5 + i2 * 4, b, b, false);
                    this.a(adm2, b, 25, 5, 5 + i2 * 4, aqe2);
                    this.a(adm2, b, 26, 6, 5 + i2 * 4, aqe2);
                    this.a(adm2, e, 26, 5, 5 + i2 * 4, aqe2);
                    this.a(adm2, aqe2, 33, 2, 5 + i2 * 4, 33, 4, 5 + i2 * 4, b, b, false);
                    this.a(adm2, aqe2, 34, 4, 5 + i2 * 4, 35, 4, 5 + i2 * 4, b, b, false);
                    this.a(adm2, b, 32, 5, 5 + i2 * 4, aqe2);
                    this.a(adm2, b, 31, 6, 5 + i2 * 4, aqe2);
                    this.a(adm2, e, 31, 5, 5 + i2 * 4, aqe2);
                    this.a(adm2, aqe2, 27, 6, 5 + i2 * 4, 30, 6, 5 + i2 * 4, a, a, false);
                }
            }
        }

        private void c(adm adm22, Random random, aqe aqe2) {
            if (this.a(aqe2, 15, 20, 42, 21)) {
                adm adm22;
                int n2;
                this.a(adm22, aqe2, 15, 0, 21, 42, 0, 21, a, a, false);
                this.a(adm22, aqe2, 26, 1, 21, 31, 3, 21, false);
                this.a(adm22, aqe2, 21, 12, 21, 36, 12, 21, a, a, false);
                this.a(adm22, aqe2, 17, 11, 21, 40, 11, 21, a, a, false);
                this.a(adm22, aqe2, 16, 10, 21, 41, 10, 21, a, a, false);
                this.a(adm22, aqe2, 15, 7, 21, 42, 9, 21, a, a, false);
                this.a(adm22, aqe2, 16, 6, 21, 41, 6, 21, a, a, false);
                this.a(adm22, aqe2, 17, 5, 21, 40, 5, 21, a, a, false);
                this.a(adm22, aqe2, 21, 4, 21, 36, 4, 21, a, a, false);
                this.a(adm22, aqe2, 22, 3, 21, 26, 3, 21, a, a, false);
                this.a(adm22, aqe2, 31, 3, 21, 35, 3, 21, a, a, false);
                this.a(adm22, aqe2, 23, 2, 21, 25, 2, 21, a, a, false);
                this.a(adm22, aqe2, 32, 2, 21, 34, 2, 21, a, a, false);
                this.a(adm22, aqe2, 28, 4, 20, 29, 4, 21, b, b, false);
                this.a(adm22, b, 27, 3, 21, aqe2);
                this.a(adm22, b, 30, 3, 21, aqe2);
                this.a(adm22, b, 26, 2, 21, aqe2);
                this.a(adm22, b, 31, 2, 21, aqe2);
                this.a(adm22, b, 25, 1, 21, aqe2);
                this.a(adm22, b, 32, 1, 21, aqe2);
                for (n2 = 0; n2 < 7; ++n2) {
                    this.a(adm22, c, 28 - n2, 6 + n2, 21, aqe2);
                    this.a(adm22, c, 29 + n2, 6 + n2, 21, aqe2);
                }
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, c, 28 - n2, 9 + n2, 21, aqe2);
                    this.a(adm22, c, 29 + n2, 9 + n2, 21, aqe2);
                }
                this.a(adm22, c, 28, 12, 21, aqe2);
                this.a(adm22, c, 29, 12, 21, aqe2);
                for (n2 = 0; n2 < 3; ++n2) {
                    this.a(adm22, c, 22 - n2 * 2, 8, 21, aqe2);
                    this.a(adm22, c, 22 - n2 * 2, 9, 21, aqe2);
                    this.a(adm22, c, 35 + n2 * 2, 8, 21, aqe2);
                    this.a(adm22, c, 35 + n2 * 2, 9, 21, aqe2);
                }
                this.a(adm22, aqe2, 15, 13, 21, 42, 15, 21, false);
                this.a(adm22, aqe2, 15, 1, 21, 15, 6, 21, false);
                this.a(adm22, aqe2, 16, 1, 21, 16, 5, 21, false);
                this.a(adm22, aqe2, 17, 1, 21, 20, 4, 21, false);
                this.a(adm22, aqe2, 21, 1, 21, 21, 3, 21, false);
                this.a(adm22, aqe2, 22, 1, 21, 22, 2, 21, false);
                this.a(adm22, aqe2, 23, 1, 21, 24, 1, 21, false);
                this.a(adm22, aqe2, 42, 1, 21, 42, 6, 21, false);
                this.a(adm22, aqe2, 41, 1, 21, 41, 5, 21, false);
                this.a(adm22, aqe2, 37, 1, 21, 40, 4, 21, false);
                this.a(adm22, aqe2, 36, 1, 21, 36, 3, 21, false);
                this.a(adm22, aqe2, 33, 1, 21, 34, 1, 21, false);
                this.a(adm22, aqe2, 35, 1, 21, 35, 2, 21, false);
            }
        }

        private void d(adm adm22, Random random, aqe aqe2) {
            if (this.a(aqe2, 21, 21, 36, 36)) {
                adm adm22;
                this.a(adm22, aqe2, 21, 0, 22, 36, 0, 36, a, a, false);
                this.a(adm22, aqe2, 21, 1, 22, 36, 23, 36, false);
                for (int i2 = 0; i2 < 4; ++i2) {
                    this.a(adm22, aqe2, 21 + i2, 13 + i2, 21 + i2, 36 - i2, 13 + i2, 21 + i2, b, b, false);
                    this.a(adm22, aqe2, 21 + i2, 13 + i2, 36 - i2, 36 - i2, 13 + i2, 36 - i2, b, b, false);
                    this.a(adm22, aqe2, 21 + i2, 13 + i2, 22 + i2, 21 + i2, 13 + i2, 35 - i2, b, b, false);
                    this.a(adm22, aqe2, 36 - i2, 13 + i2, 22 + i2, 36 - i2, 13 + i2, 35 - i2, b, b, false);
                }
                this.a(adm22, aqe2, 25, 16, 25, 32, 16, 32, a, a, false);
                this.a(adm22, aqe2, 25, 17, 25, 25, 19, 25, b, b, false);
                this.a(adm22, aqe2, 32, 17, 25, 32, 19, 25, b, b, false);
                this.a(adm22, aqe2, 25, 17, 32, 25, 19, 32, b, b, false);
                this.a(adm22, aqe2, 32, 17, 32, 32, 19, 32, b, b, false);
                this.a(adm22, b, 26, 20, 26, aqe2);
                this.a(adm22, b, 27, 21, 27, aqe2);
                this.a(adm22, e, 27, 20, 27, aqe2);
                this.a(adm22, b, 26, 20, 31, aqe2);
                this.a(adm22, b, 27, 21, 30, aqe2);
                this.a(adm22, e, 27, 20, 30, aqe2);
                this.a(adm22, b, 31, 20, 31, aqe2);
                this.a(adm22, b, 30, 21, 30, aqe2);
                this.a(adm22, e, 30, 20, 30, aqe2);
                this.a(adm22, b, 31, 20, 26, aqe2);
                this.a(adm22, b, 30, 21, 27, aqe2);
                this.a(adm22, e, 30, 20, 27, aqe2);
                this.a(adm22, aqe2, 28, 21, 27, 29, 21, 27, a, a, false);
                this.a(adm22, aqe2, 27, 21, 28, 27, 21, 29, a, a, false);
                this.a(adm22, aqe2, 28, 21, 30, 29, 21, 30, a, a, false);
                this.a(adm22, aqe2, 30, 21, 28, 30, 21, 29, a, a, false);
            }
        }

        private void e(adm adm22, Random random, aqe aqe22) {
            aqe aqe22;
            adm adm22;
            int n2;
            if (this.a(aqe22, 0, 21, 6, 58)) {
                this.a(adm22, aqe22, 0, 0, 21, 6, 0, 57, a, a, false);
                this.a(adm22, aqe22, 0, 1, 21, 6, 7, 57, false);
                this.a(adm22, aqe22, 4, 4, 21, 6, 4, 53, a, a, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, n2, n2 + 1, 21, n2, n2 + 1, 57 - n2, b, b, false);
                }
                for (n2 = 23; n2 < 53; n2 += 3) {
                    this.a(adm22, d, 5, 5, n2, aqe22);
                }
                this.a(adm22, d, 5, 5, 52, aqe22);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, n2, n2 + 1, 21, n2, n2 + 1, 57 - n2, b, b, false);
                }
                this.a(adm22, aqe22, 4, 1, 52, 6, 3, 52, a, a, false);
                this.a(adm22, aqe22, 5, 1, 51, 5, 3, 53, a, a, false);
            }
            if (this.a(aqe22, 51, 21, 58, 58)) {
                this.a(adm22, aqe22, 51, 0, 21, 57, 0, 57, a, a, false);
                this.a(adm22, aqe22, 51, 1, 21, 57, 7, 57, false);
                this.a(adm22, aqe22, 51, 4, 21, 53, 4, 53, a, a, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, 57 - n2, n2 + 1, 21, 57 - n2, n2 + 1, 57 - n2, b, b, false);
                }
                for (n2 = 23; n2 < 53; n2 += 3) {
                    this.a(adm22, d, 52, 5, n2, aqe22);
                }
                this.a(adm22, d, 52, 5, 52, aqe22);
                this.a(adm22, aqe22, 51, 1, 52, 53, 3, 52, a, a, false);
                this.a(adm22, aqe22, 52, 1, 51, 52, 3, 53, a, a, false);
            }
            if (this.a(aqe22, 0, 51, 57, 57)) {
                this.a(adm22, aqe22, 7, 0, 51, 50, 0, 57, a, a, false);
                this.a(adm22, aqe22, 7, 1, 51, 50, 10, 57, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, n2 + 1, n2 + 1, 57 - n2, 56 - n2, n2 + 1, 57 - n2, b, b, false);
                }
            }
        }

        private void f(adm adm22, Random random, aqe aqe22) {
            aqe aqe22;
            int n2;
            if (this.a(aqe22, 7, 21, 13, 50)) {
                this.a(adm22, aqe22, 7, 0, 21, 13, 0, 50, a, a, false);
                this.a(adm22, aqe22, 7, 1, 21, 13, 10, 50, false);
                this.a(adm22, aqe22, 11, 8, 21, 13, 8, 53, a, a, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, n2 + 7, n2 + 5, 21, n2 + 7, n2 + 5, 54, b, b, false);
                }
                for (n2 = 21; n2 <= 45; n2 += 3) {
                    this.a(adm22, d, 12, 9, n2, aqe22);
                }
            }
            if (this.a(aqe22, 44, 21, 50, 54)) {
                this.a(adm22, aqe22, 44, 0, 21, 50, 0, 50, a, a, false);
                this.a(adm22, aqe22, 44, 1, 21, 50, 10, 50, false);
                this.a(adm22, aqe22, 44, 8, 21, 46, 8, 53, a, a, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm22, aqe22, 50 - n2, n2 + 5, 21, 50 - n2, n2 + 5, 54, b, b, false);
                }
                for (n2 = 21; n2 <= 45; n2 += 3) {
                    this.a(adm22, d, 45, 9, n2, aqe22);
                }
            }
            if (this.a(aqe22, 8, 44, 49, 54)) {
                adm adm22;
                this.a(adm22, aqe22, 14, 0, 44, 43, 0, 50, a, a, false);
                this.a(adm22, aqe22, 14, 1, 44, 43, 10, 50, false);
                for (n2 = 12; n2 <= 45; n2 += 3) {
                    this.a(adm22, d, n2, 9, 45, aqe22);
                    this.a(adm22, d, n2, 9, 52, aqe22);
                    if (n2 != 12 && n2 != 18 && n2 != 24 && n2 != 33 && n2 != 39 && n2 != 45) continue;
                    this.a(adm22, d, n2, 9, 47, aqe22);
                    this.a(adm22, d, n2, 9, 50, aqe22);
                    this.a(adm22, d, n2, 10, 45, aqe22);
                    this.a(adm22, d, n2, 10, 46, aqe22);
                    this.a(adm22, d, n2, 10, 51, aqe22);
                    this.a(adm22, d, n2, 10, 52, aqe22);
                    this.a(adm22, d, n2, 11, 47, aqe22);
                    this.a(adm22, d, n2, 11, 50, aqe22);
                    this.a(adm22, d, n2, 12, 48, aqe22);
                    this.a(adm22, d, n2, 12, 49, aqe22);
                }
                for (n2 = 0; n2 < 3; ++n2) {
                    this.a(adm22, aqe22, 8 + n2, 5 + n2, 54, 49 - n2, 5 + n2, 54, a, a, false);
                }
                this.a(adm22, aqe22, 11, 8, 54, 46, 8, 54, b, b, false);
                this.a(adm22, aqe22, 14, 8, 44, 43, 8, 53, a, a, false);
            }
        }

        private void g(adm adm2, Random random, aqe aqe22) {
            aqe aqe22;
            int n2;
            if (this.a(aqe22, 14, 21, 20, 43)) {
                this.a(adm2, aqe22, 14, 0, 21, 20, 0, 43, a, a, false);
                this.a(adm2, aqe22, 14, 1, 22, 20, 14, 43, false);
                this.a(adm2, aqe22, 18, 12, 22, 20, 12, 39, a, a, false);
                this.a(adm2, aqe22, 18, 12, 21, 20, 12, 21, b, b, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm2, aqe22, n2 + 14, n2 + 9, 21, n2 + 14, n2 + 9, 43 - n2, b, b, false);
                }
                for (n2 = 23; n2 <= 39; n2 += 3) {
                    this.a(adm2, d, 19, 13, n2, aqe22);
                }
            }
            if (this.a(aqe22, 37, 21, 43, 43)) {
                this.a(adm2, aqe22, 37, 0, 21, 43, 0, 43, a, a, false);
                this.a(adm2, aqe22, 37, 1, 22, 43, 14, 43, false);
                this.a(adm2, aqe22, 37, 12, 22, 39, 12, 39, a, a, false);
                this.a(adm2, aqe22, 37, 12, 21, 39, 12, 21, b, b, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm2, aqe22, 43 - n2, n2 + 9, 21, 43 - n2, n2 + 9, 43 - n2, b, b, false);
                }
                for (n2 = 23; n2 <= 39; n2 += 3) {
                    this.a(adm2, d, 38, 13, n2, aqe22);
                }
            }
            if (this.a(aqe22, 15, 37, 42, 43)) {
                this.a(adm2, aqe22, 21, 0, 37, 36, 0, 43, a, a, false);
                this.a(adm2, aqe22, 21, 1, 37, 36, 14, 43, false);
                this.a(adm2, aqe22, 21, 12, 37, 36, 12, 39, a, a, false);
                for (n2 = 0; n2 < 4; ++n2) {
                    this.a(adm2, aqe22, 15 + n2, n2 + 9, 43 - n2, 42 - n2, n2 + 9, 43 - n2, b, b, false);
                }
                for (n2 = 21; n2 <= 36; n2 += 3) {
                    this.a(adm2, d, n2, 13, 38, aqe22);
                }
            }
        }
    }

    public static abstract class r
    extends aqt {
        protected static final alz a = afi.cI.a(aiu.b);
        protected static final alz b = afi.cI.a(aiu.N);
        protected static final alz c = afi.cI.a(aiu.O);
        protected static final alz d = b;
        protected static final alz e = afi.cJ.Q();
        protected static final alz f = afi.j.Q();
        protected static final int g = r.b(2, 0, 0);
        protected static final int h = r.b(2, 2, 0);
        protected static final int i = r.b(0, 1, 0);
        protected static final int j = r.b(4, 1, 0);
        protected v k;

        protected static final int b(int n2, int n3, int n4) {
            return n3 * 25 + n4 * 5 + n2;
        }

        public r() {
            super(0);
        }

        public r(int n2) {
            super(n2);
        }

        public r(cq cq2, aqe aqe2) {
            super(1);
            this.m = cq2;
            this.l = aqe2;
        }

        protected r(int n2, cq cq2, v v2, int n3, int n4, int n5) {
            super(n2);
            this.m = cq2;
            this.k = v2;
            \u2603 = v2.a;
            \u2603 = \u2603 % 5;
            \u2603 = \u2603 / 5 % 5;
            \u2603 = \u2603 / 25;
            this.l = cq2 == cq.c || cq2 == cq.d ? new aqe(0, 0, 0, n3 * 8 - 1, n4 * 4 - 1, n5 * 8 - 1) : new aqe(0, 0, 0, n5 * 8 - 1, n4 * 4 - 1, n3 * 8 - 1);
            switch (cq2) {
                case c: {
                    this.l.a(\u2603 * 8, \u2603 * 4, -(\u2603 + n5) * 8 + 1);
                    break;
                }
                case d: {
                    this.l.a(\u2603 * 8, \u2603 * 4, \u2603 * 8);
                    break;
                }
                case e: {
                    this.l.a(-(\u2603 + n5) * 8 + 1, \u2603 * 4, \u2603 * 8);
                    break;
                }
                default: {
                    this.l.a(\u2603 * 8, \u2603 * 4, \u2603 * 8);
                }
            }
        }

        @Override
        protected void a(dn dn2) {
        }

        @Override
        protected void b(dn dn2) {
        }

        protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, boolean bl2) {
            for (int i2 = n3; i2 <= n6; ++i2) {
                for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                    for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                        if (bl2 && this.a(adm2, \u2603, i2, \u2603, aqe2).c().t() == arm.a) continue;
                        if (this.d(i2) >= adm2.F()) {
                            this.a(adm2, afi.a.Q(), \u2603, i2, \u2603, aqe2);
                            continue;
                        }
                        this.a(adm2, f, \u2603, i2, \u2603, aqe2);
                    }
                }
            }
        }

        protected void a(adm adm2, aqe aqe2, int n2, int n3, boolean bl2) {
            if (bl2) {
                this.a(adm2, aqe2, n2 + 0, 0, n3 + 0, n2 + 2, 0, n3 + 8 - 1, a, a, false);
                this.a(adm2, aqe2, n2 + 5, 0, n3 + 0, n2 + 8 - 1, 0, n3 + 8 - 1, a, a, false);
                this.a(adm2, aqe2, n2 + 3, 0, n3 + 0, n2 + 4, 0, n3 + 2, a, a, false);
                this.a(adm2, aqe2, n2 + 3, 0, n3 + 5, n2 + 4, 0, n3 + 8 - 1, a, a, false);
                this.a(adm2, aqe2, n2 + 3, 0, n3 + 2, n2 + 4, 0, n3 + 2, b, b, false);
                this.a(adm2, aqe2, n2 + 3, 0, n3 + 5, n2 + 4, 0, n3 + 5, b, b, false);
                this.a(adm2, aqe2, n2 + 2, 0, n3 + 3, n2 + 2, 0, n3 + 4, b, b, false);
                this.a(adm2, aqe2, n2 + 5, 0, n3 + 3, n2 + 5, 0, n3 + 4, b, b, false);
            } else {
                this.a(adm2, aqe2, n2 + 0, 0, n3 + 0, n2 + 8 - 1, 0, n3 + 8 - 1, a, a, false);
            }
        }

        protected void a(adm adm2, aqe aqe2, int n2, int n3, int n4, int n5, int n6, int n7, alz alz2) {
            for (int i2 = n3; i2 <= n6; ++i2) {
                for (\u2603 = n2; \u2603 <= n5; ++\u2603) {
                    for (\u2603 = n4; \u2603 <= n7; ++\u2603) {
                        if (this.a(adm2, \u2603, i2, \u2603, aqe2) != f) continue;
                        this.a(adm2, alz2, \u2603, i2, \u2603, aqe2);
                    }
                }
            }
        }

        protected boolean a(aqe aqe2, int n2, int n3, int n4, int n5) {
            \u2603 = this.a(n2, n3);
            \u2603 = this.b(n2, n3);
            \u2603 = this.a(n4, n5);
            \u2603 = this.b(n4, n5);
            return aqe2.a(Math.min(\u2603, \u2603), Math.min(\u2603, \u2603), Math.max(\u2603, \u2603), Math.max(\u2603, \u2603));
        }

        protected boolean a(adm adm2, aqe aqe2, int n2, int n3, int n4) {
            \u2603 = this.a(n2, n4);
            if (aqe2.b(new cj(\u2603, \u2603 = this.d(n3), \u2603 = this.b(n2, n4)))) {
                vt vt2 = new vt(adm2);
                vt2.a(true);
                vt2.h(vt2.bu());
                vt2.b((double)\u2603 + 0.5, \u2603, (double)\u2603 + 0.5, 0.0f, 0.0f);
                vt2.a(adm2.E(new cj(vt2)), null);
                adm2.d(vt2);
                return true;
            }
            return false;
        }
    }
}

