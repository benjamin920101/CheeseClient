/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

public class aqn {
    public static void a() {
        aqr.a(a.class, "TeDP");
        aqr.a(b.class, "TeJP");
        aqr.a(d.class, "TeSH");
    }

    public static class d
    extends c {
        private boolean e;

        public d() {
        }

        public d(Random random, int n2, int n3) {
            super(random, n2, 64, n3, 7, 7, 9);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("Witch", this.e);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.e = dn2.n("Witch");
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (!this.a(adm2, aqe2, 0)) {
                return false;
            }
            this.a(adm2, aqe2, 1, 1, 1, 5, 1, 7, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 1, 4, 2, 5, 4, 7, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 2, 1, 0, 4, 1, 0, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 2, 2, 2, 3, 3, 2, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 1, 2, 3, 1, 3, 6, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 5, 2, 3, 5, 3, 6, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 2, 2, 7, 4, 3, 7, afi.f.a(aio.a.b.a()), afi.f.a(aio.a.b.a()), false);
            this.a(adm2, aqe2, 1, 0, 2, 1, 3, 2, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 5, 0, 2, 5, 3, 2, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 1, 0, 7, 1, 3, 7, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, aqe2, 5, 0, 7, 5, 3, 7, afi.r.Q(), afi.r.Q(), false);
            this.a(adm2, afi.aO.Q(), 2, 3, 2, aqe2);
            this.a(adm2, afi.aO.Q(), 3, 3, 7, aqe2);
            this.a(adm2, afi.a.Q(), 1, 3, 4, aqe2);
            this.a(adm2, afi.a.Q(), 5, 3, 4, aqe2);
            this.a(adm2, afi.a.Q(), 5, 3, 5, aqe2);
            this.a(adm2, afi.ca.Q().a(agx.b, agx.a.r), 1, 3, 5, aqe2);
            this.a(adm2, afi.ai.Q(), 3, 2, 6, aqe2);
            this.a(adm2, afi.bE.Q(), 4, 2, 6, aqe2);
            this.a(adm2, afi.aO.Q(), 1, 2, 1, aqe2);
            this.a(adm2, afi.aO.Q(), 5, 2, 1, aqe2);
            int n2 = this.a(afi.ad, 3);
            \u2603 = this.a(afi.ad, 1);
            \u2603 = this.a(afi.ad, 0);
            \u2603 = this.a(afi.ad, 2);
            this.a(adm2, aqe2, 0, 4, 1, 6, 4, 1, afi.bU.a(n2), afi.bU.a(n2), false);
            this.a(adm2, aqe2, 0, 4, 2, 0, 4, 7, afi.bU.a(\u2603), afi.bU.a(\u2603), false);
            this.a(adm2, aqe2, 6, 4, 2, 6, 4, 7, afi.bU.a(\u2603), afi.bU.a(\u2603), false);
            this.a(adm2, aqe2, 0, 4, 8, 6, 4, 8, afi.bU.a(\u2603), afi.bU.a(\u2603), false);
            for (\u2603 = 2; \u2603 <= 7; \u2603 += 5) {
                for (\u2603 = 1; \u2603 <= 5; \u2603 += 4) {
                    this.b(adm2, afi.r.Q(), \u2603, -1, \u2603, aqe2);
                }
            }
            if (!this.e && aqe2.b(new cj(\u2603 = this.a(2, 5), \u2603 = this.d(2), \u2603 = this.b(2, 5)))) {
                this.e = true;
                wd wd2 = new wd(adm2);
                wd2.b((double)\u2603 + 0.5, \u2603, (double)\u2603 + 0.5, 0.0f, 0.0f);
                wd2.a(adm2.E(new cj(\u2603, \u2603, \u2603)), null);
                adm2.d(wd2);
            }
            return true;
        }
    }

    public static class b
    extends c {
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private static final List<ob> i = Lists.newArrayList(new ob(zy.i, 0, 1, 3, 3), new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 2, 7, 15), new ob(zy.bO, 0, 1, 3, 2), new ob(zy.aX, 0, 4, 6, 20), new ob(zy.bt, 0, 3, 7, 16), new ob(zy.aA, 0, 1, 1, 3), new ob(zy.ck, 0, 1, 1, 1), new ob(zy.cl, 0, 1, 1, 1), new ob(zy.cm, 0, 1, 1, 1));
        private static final List<ob> j = Lists.newArrayList(new ob(zy.g, 0, 2, 7, 30));
        private static a k = new a();

        public b() {
        }

        public b(Random random, int n2, int n3) {
            super(random, n2, 64, n3, 12, 10, 15);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("placedMainChest", this.e);
            dn2.a("placedHiddenChest", this.f);
            dn2.a("placedTrap1", this.g);
            dn2.a("placedTrap2", this.h);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.e = dn2.n("placedMainChest");
            this.f = dn2.n("placedHiddenChest");
            this.g = dn2.n("placedTrap1");
            this.h = dn2.n("placedTrap2");
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            if (!this.a(adm2, aqe2, 0)) {
                return false;
            }
            int n2 = this.a(afi.aw, 3);
            \u2603 = this.a(afi.aw, 2);
            \u2603 = this.a(afi.aw, 0);
            \u2603 = this.a(afi.aw, 1);
            this.a(adm2, aqe2, 0, -4, 0, this.a - 1, 0, this.c - 1, false, random, k);
            this.a(adm2, aqe2, 2, 1, 2, 9, 2, 2, false, random, k);
            this.a(adm2, aqe2, 2, 1, 12, 9, 2, 12, false, random, k);
            this.a(adm2, aqe2, 2, 1, 3, 2, 2, 11, false, random, k);
            this.a(adm2, aqe2, 9, 1, 3, 9, 2, 11, false, random, k);
            this.a(adm2, aqe2, 1, 3, 1, 10, 6, 1, false, random, k);
            this.a(adm2, aqe2, 1, 3, 13, 10, 6, 13, false, random, k);
            this.a(adm2, aqe2, 1, 3, 2, 1, 6, 12, false, random, k);
            this.a(adm2, aqe2, 10, 3, 2, 10, 6, 12, false, random, k);
            this.a(adm2, aqe2, 2, 3, 2, 9, 3, 12, false, random, k);
            this.a(adm2, aqe2, 2, 6, 2, 9, 6, 12, false, random, k);
            this.a(adm2, aqe2, 3, 7, 3, 8, 7, 11, false, random, k);
            this.a(adm2, aqe2, 4, 8, 4, 7, 8, 10, false, random, k);
            this.a(adm2, aqe2, 3, 1, 3, 8, 2, 11);
            this.a(adm2, aqe2, 4, 3, 6, 7, 3, 9);
            this.a(adm2, aqe2, 2, 4, 2, 9, 5, 12);
            this.a(adm2, aqe2, 4, 6, 5, 7, 6, 9);
            this.a(adm2, aqe2, 5, 7, 6, 6, 7, 8);
            this.a(adm2, aqe2, 5, 1, 2, 6, 2, 2);
            this.a(adm2, aqe2, 5, 2, 12, 6, 2, 12);
            this.a(adm2, aqe2, 5, 5, 1, 6, 5, 1);
            this.a(adm2, aqe2, 5, 5, 13, 6, 5, 13);
            this.a(adm2, afi.a.Q(), 1, 5, 5, aqe2);
            this.a(adm2, afi.a.Q(), 10, 5, 5, aqe2);
            this.a(adm2, afi.a.Q(), 1, 5, 9, aqe2);
            this.a(adm2, afi.a.Q(), 10, 5, 9, aqe2);
            for (\u2603 = 0; \u2603 <= 14; \u2603 += 14) {
                this.a(adm2, aqe2, 2, 4, \u2603, 2, 5, \u2603, false, random, k);
                this.a(adm2, aqe2, 4, 4, \u2603, 4, 5, \u2603, false, random, k);
                this.a(adm2, aqe2, 7, 4, \u2603, 7, 5, \u2603, false, random, k);
                this.a(adm2, aqe2, 9, 4, \u2603, 9, 5, \u2603, false, random, k);
            }
            this.a(adm2, aqe2, 5, 6, 0, 6, 6, 0, false, random, k);
            for (\u2603 = 0; \u2603 <= 11; \u2603 += 11) {
                for (\u2603 = 2; \u2603 <= 12; \u2603 += 2) {
                    this.a(adm2, aqe2, \u2603, 4, \u2603, \u2603, 5, \u2603, false, random, k);
                }
                this.a(adm2, aqe2, \u2603, 6, 5, \u2603, 6, 5, false, random, k);
                this.a(adm2, aqe2, \u2603, 6, 9, \u2603, 6, 9, false, random, k);
            }
            this.a(adm2, aqe2, 2, 7, 2, 2, 9, 2, false, random, k);
            this.a(adm2, aqe2, 9, 7, 2, 9, 9, 2, false, random, k);
            this.a(adm2, aqe2, 2, 7, 12, 2, 9, 12, false, random, k);
            this.a(adm2, aqe2, 9, 7, 12, 9, 9, 12, false, random, k);
            this.a(adm2, aqe2, 4, 9, 4, 4, 9, 4, false, random, k);
            this.a(adm2, aqe2, 7, 9, 4, 7, 9, 4, false, random, k);
            this.a(adm2, aqe2, 4, 9, 10, 4, 9, 10, false, random, k);
            this.a(adm2, aqe2, 7, 9, 10, 7, 9, 10, false, random, k);
            this.a(adm2, aqe2, 5, 9, 7, 6, 9, 7, false, random, k);
            this.a(adm2, afi.aw.a(n2), 5, 9, 6, aqe2);
            this.a(adm2, afi.aw.a(n2), 6, 9, 6, aqe2);
            this.a(adm2, afi.aw.a(\u2603), 5, 9, 8, aqe2);
            this.a(adm2, afi.aw.a(\u2603), 6, 9, 8, aqe2);
            this.a(adm2, afi.aw.a(n2), 4, 0, 0, aqe2);
            this.a(adm2, afi.aw.a(n2), 5, 0, 0, aqe2);
            this.a(adm2, afi.aw.a(n2), 6, 0, 0, aqe2);
            this.a(adm2, afi.aw.a(n2), 7, 0, 0, aqe2);
            this.a(adm2, afi.aw.a(n2), 4, 1, 8, aqe2);
            this.a(adm2, afi.aw.a(n2), 4, 2, 9, aqe2);
            this.a(adm2, afi.aw.a(n2), 4, 3, 10, aqe2);
            this.a(adm2, afi.aw.a(n2), 7, 1, 8, aqe2);
            this.a(adm2, afi.aw.a(n2), 7, 2, 9, aqe2);
            this.a(adm2, afi.aw.a(n2), 7, 3, 10, aqe2);
            this.a(adm2, aqe2, 4, 1, 9, 4, 1, 9, false, random, k);
            this.a(adm2, aqe2, 7, 1, 9, 7, 1, 9, false, random, k);
            this.a(adm2, aqe2, 4, 1, 10, 7, 2, 10, false, random, k);
            this.a(adm2, aqe2, 5, 4, 5, 6, 4, 5, false, random, k);
            this.a(adm2, afi.aw.a(\u2603), 4, 4, 5, aqe2);
            this.a(adm2, afi.aw.a(\u2603), 7, 4, 5, aqe2);
            for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                this.a(adm2, afi.aw.a(\u2603), 5, 0 - \u2603, 6 + \u2603, aqe2);
                this.a(adm2, afi.aw.a(\u2603), 6, 0 - \u2603, 6 + \u2603, aqe2);
                this.a(adm2, aqe2, 5, 0 - \u2603, 7 + \u2603, 6, 0 - \u2603, 9 + \u2603);
            }
            this.a(adm2, aqe2, 1, -3, 12, 10, -1, 13);
            this.a(adm2, aqe2, 1, -3, 1, 3, -1, 13);
            this.a(adm2, aqe2, 1, -3, 1, 9, -1, 5);
            for (\u2603 = 1; \u2603 <= 13; \u2603 += 2) {
                this.a(adm2, aqe2, 1, -3, \u2603, 1, -2, \u2603, false, random, k);
            }
            for (\u2603 = 2; \u2603 <= 12; \u2603 += 2) {
                this.a(adm2, aqe2, 1, -1, \u2603, 3, -1, \u2603, false, random, k);
            }
            this.a(adm2, aqe2, 2, -2, 1, 5, -2, 1, false, random, k);
            this.a(adm2, aqe2, 7, -2, 1, 9, -2, 1, false, random, k);
            this.a(adm2, aqe2, 6, -3, 1, 6, -3, 1, false, random, k);
            this.a(adm2, aqe2, 6, -1, 1, 6, -1, 1, false, random, k);
            this.a(adm2, afi.bR.a(this.a(afi.bR, cq.f.b())).a(akj.N, true), 1, -3, 8, aqe2);
            this.a(adm2, afi.bR.a(this.a(afi.bR, cq.e.b())).a(akj.N, true), 4, -3, 8, aqe2);
            this.a(adm2, afi.bS.Q().a(aki.N, true), 2, -3, 8, aqe2);
            this.a(adm2, afi.bS.Q().a(aki.N, true), 3, -3, 8, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 7, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 6, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 5, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 4, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 3, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 2, aqe2);
            this.a(adm2, afi.af.Q(), 5, -3, 1, aqe2);
            this.a(adm2, afi.af.Q(), 4, -3, 1, aqe2);
            this.a(adm2, afi.Y.Q(), 3, -3, 1, aqe2);
            if (!this.g) {
                this.g = this.a(adm2, aqe2, random, 3, -2, 1, cq.c.a(), j, 2);
            }
            this.a(adm2, afi.bn.a(15), 3, -2, 2, aqe2);
            this.a(adm2, afi.bR.a(this.a(afi.bR, cq.c.b())).a(akj.N, true), 7, -3, 1, aqe2);
            this.a(adm2, afi.bR.a(this.a(afi.bR, cq.d.b())).a(akj.N, true), 7, -3, 5, aqe2);
            this.a(adm2, afi.bS.Q().a(aki.N, true), 7, -3, 2, aqe2);
            this.a(adm2, afi.bS.Q().a(aki.N, true), 7, -3, 3, aqe2);
            this.a(adm2, afi.bS.Q().a(aki.N, true), 7, -3, 4, aqe2);
            this.a(adm2, afi.af.Q(), 8, -3, 6, aqe2);
            this.a(adm2, afi.af.Q(), 9, -3, 6, aqe2);
            this.a(adm2, afi.af.Q(), 9, -3, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 9, -3, 4, aqe2);
            this.a(adm2, afi.af.Q(), 9, -2, 4, aqe2);
            if (!this.h) {
                this.h = this.a(adm2, aqe2, random, 9, -2, 3, cq.e.a(), j, 2);
            }
            this.a(adm2, afi.bn.a(15), 8, -1, 3, aqe2);
            this.a(adm2, afi.bn.a(15), 8, -2, 3, aqe2);
            if (!this.e) {
                this.e = this.a(adm2, aqe2, random, 8, -3, 3, ob.a(i, zy.cd.b(random)), 2 + random.nextInt(5));
            }
            this.a(adm2, afi.Y.Q(), 9, -3, 2, aqe2);
            this.a(adm2, afi.Y.Q(), 8, -3, 1, aqe2);
            this.a(adm2, afi.Y.Q(), 4, -3, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 5, -2, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 5, -1, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 6, -3, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 7, -2, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 7, -1, 5, aqe2);
            this.a(adm2, afi.Y.Q(), 8, -3, 5, aqe2);
            this.a(adm2, aqe2, 9, -1, 1, 9, -1, 5, false, random, k);
            this.a(adm2, aqe2, 8, -3, 8, 10, -1, 10);
            this.a(adm2, afi.bf.a(ajz.P), 8, -2, 11, aqe2);
            this.a(adm2, afi.bf.a(ajz.P), 9, -2, 11, aqe2);
            this.a(adm2, afi.bf.a(ajz.P), 10, -2, 11, aqe2);
            this.a(adm2, afi.ay.a(ahu.a(cq.a(this.a(afi.ay, cq.c.a())))), 8, -2, 12, aqe2);
            this.a(adm2, afi.ay.a(ahu.a(cq.a(this.a(afi.ay, cq.c.a())))), 9, -2, 12, aqe2);
            this.a(adm2, afi.ay.a(ahu.a(cq.a(this.a(afi.ay, cq.c.a())))), 10, -2, 12, aqe2);
            this.a(adm2, aqe2, 8, -3, 8, 8, -3, 10, false, random, k);
            this.a(adm2, aqe2, 10, -3, 8, 10, -3, 10, false, random, k);
            this.a(adm2, afi.Y.Q(), 10, -2, 9, aqe2);
            this.a(adm2, afi.af.Q(), 8, -2, 9, aqe2);
            this.a(adm2, afi.af.Q(), 8, -2, 10, aqe2);
            this.a(adm2, afi.af.Q(), 10, -1, 9, aqe2);
            this.a(adm2, afi.F.a(cq.b.a()), 9, -2, 8, aqe2);
            this.a(adm2, afi.F.a(this.a(afi.F, cq.e.a())), 10, -2, 8, aqe2);
            this.a(adm2, afi.F.a(this.a(afi.F, cq.e.a())), 10, -1, 8, aqe2);
            this.a(adm2, afi.bb.a(this.a(afi.bb, cq.c.b())), 10, -2, 10, aqe2);
            if (!this.f) {
                this.f = this.a(adm2, aqe2, random, 9, -3, 10, ob.a(i, zy.cd.b(random)), 2 + random.nextInt(5));
            }
            return true;
        }

        static class a
        extends aqt.a {
            private a() {
            }

            @Override
            public void a(Random random, int n2, int n3, int n4, boolean bl2) {
                this.a = random.nextFloat() < 0.4f ? afi.e.Q() : afi.Y.Q();
            }
        }
    }

    public static class a
    extends c {
        private boolean[] e = new boolean[4];
        private static final List<ob> f = Lists.newArrayList(new ob(zy.i, 0, 1, 3, 3), new ob(zy.j, 0, 1, 5, 10), new ob(zy.k, 0, 2, 7, 15), new ob(zy.bO, 0, 1, 3, 2), new ob(zy.aX, 0, 4, 6, 20), new ob(zy.bt, 0, 3, 7, 16), new ob(zy.aA, 0, 1, 1, 3), new ob(zy.ck, 0, 1, 1, 1), new ob(zy.cl, 0, 1, 1, 1), new ob(zy.cm, 0, 1, 1, 1));

        public a() {
        }

        public a(Random random, int n2, int n3) {
            super(random, n2, 64, n3, 21, 15, 21);
        }

        @Override
        protected void a(dn dn2) {
            super.a(dn2);
            dn2.a("hasPlacedChest0", this.e[0]);
            dn2.a("hasPlacedChest1", this.e[1]);
            dn2.a("hasPlacedChest2", this.e[2]);
            dn2.a("hasPlacedChest3", this.e[3]);
        }

        @Override
        protected void b(dn dn2) {
            super.b(dn2);
            this.e[0] = dn2.n("hasPlacedChest0");
            this.e[1] = dn2.n("hasPlacedChest1");
            this.e[2] = dn2.n("hasPlacedChest2");
            this.e[3] = dn2.n("hasPlacedChest3");
        }

        @Override
        public boolean a(adm adm2, Random random, aqe aqe2) {
            int n2;
            this.a(adm2, aqe2, 0, -4, 0, this.a - 1, 0, this.c - 1, afi.A.Q(), afi.A.Q(), false);
            for (n2 = 1; n2 <= 9; ++n2) {
                this.a(adm2, aqe2, n2, n2, n2, this.a - 1 - n2, n2, this.c - 1 - n2, afi.A.Q(), afi.A.Q(), false);
                this.a(adm2, aqe2, n2 + 1, n2, n2 + 1, this.a - 2 - n2, n2, this.c - 2 - n2, afi.a.Q(), afi.a.Q(), false);
            }
            for (n2 = 0; n2 < this.a; ++n2) {
                for (\u2603 = 0; \u2603 < this.c; ++\u2603) {
                    \u2603 = -5;
                    this.b(adm2, afi.A.Q(), n2, \u2603, \u2603, aqe2);
                }
            }
            n2 = this.a(afi.bO, 3);
            \u2603 = this.a(afi.bO, 2);
            \u2603 = this.a(afi.bO, 0);
            \u2603 = this.a(afi.bO, 1);
            \u2603 = ~zd.b.b() & 0xF;
            \u2603 = ~zd.l.b() & 0xF;
            this.a(adm2, aqe2, 0, 0, 0, 4, 9, 4, afi.A.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 1, 10, 1, 3, 10, 3, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, afi.bO.a(n2), 2, 10, 0, aqe2);
            this.a(adm2, afi.bO.a(\u2603), 2, 10, 4, aqe2);
            this.a(adm2, afi.bO.a(\u2603), 0, 10, 2, aqe2);
            this.a(adm2, afi.bO.a(\u2603), 4, 10, 2, aqe2);
            this.a(adm2, aqe2, this.a - 5, 0, 0, this.a - 1, 9, 4, afi.A.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.a - 4, 10, 1, this.a - 2, 10, 3, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, afi.bO.a(n2), this.a - 3, 10, 0, aqe2);
            this.a(adm2, afi.bO.a(\u2603), this.a - 3, 10, 4, aqe2);
            this.a(adm2, afi.bO.a(\u2603), this.a - 5, 10, 2, aqe2);
            this.a(adm2, afi.bO.a(\u2603), this.a - 1, 10, 2, aqe2);
            this.a(adm2, aqe2, 8, 0, 0, 12, 4, 4, afi.A.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 9, 1, 0, 11, 3, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, afi.A.a(aji.a.c.a()), 9, 1, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 9, 2, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 9, 3, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 10, 3, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 11, 3, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 11, 2, 1, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 11, 1, 1, aqe2);
            this.a(adm2, aqe2, 4, 1, 1, 8, 3, 3, afi.A.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 4, 1, 2, 8, 2, 2, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 12, 1, 1, 16, 3, 3, afi.A.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 12, 1, 2, 16, 2, 2, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 5, 4, 5, this.a - 6, 4, this.c - 6, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, 9, 4, 9, 11, 4, 11, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, 8, 1, 8, 8, 3, 8, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 12, 1, 8, 12, 3, 8, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 8, 1, 12, 8, 3, 12, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 12, 1, 12, 12, 3, 12, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 1, 1, 5, 4, 4, 11, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, this.a - 5, 1, 5, this.a - 2, 4, 11, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, 6, 7, 9, 6, 7, 11, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, this.a - 7, 7, 9, this.a - 7, 7, 11, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, 5, 5, 9, 5, 7, 11, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, this.a - 6, 5, 9, this.a - 6, 7, 11, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, afi.a.Q(), 5, 5, 10, aqe2);
            this.a(adm2, afi.a.Q(), 5, 6, 10, aqe2);
            this.a(adm2, afi.a.Q(), 6, 6, 10, aqe2);
            this.a(adm2, afi.a.Q(), this.a - 6, 5, 10, aqe2);
            this.a(adm2, afi.a.Q(), this.a - 6, 6, 10, aqe2);
            this.a(adm2, afi.a.Q(), this.a - 7, 6, 10, aqe2);
            this.a(adm2, aqe2, 2, 4, 4, 2, 6, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.a - 3, 4, 4, this.a - 3, 6, 4, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, afi.bO.a(n2), 2, 4, 5, aqe2);
            this.a(adm2, afi.bO.a(n2), 2, 3, 4, aqe2);
            this.a(adm2, afi.bO.a(n2), this.a - 3, 4, 5, aqe2);
            this.a(adm2, afi.bO.a(n2), this.a - 3, 3, 4, aqe2);
            this.a(adm2, aqe2, 1, 1, 3, 2, 2, 3, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, this.a - 3, 1, 3, this.a - 2, 2, 3, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, afi.bO.Q(), 1, 1, 2, aqe2);
            this.a(adm2, afi.bO.Q(), this.a - 2, 1, 2, aqe2);
            this.a(adm2, afi.U.a(akb.a.b.a()), 1, 2, 2, aqe2);
            this.a(adm2, afi.U.a(akb.a.b.a()), this.a - 2, 2, 2, aqe2);
            this.a(adm2, afi.bO.a(\u2603), 2, 1, 2, aqe2);
            this.a(adm2, afi.bO.a(\u2603), this.a - 3, 1, 2, aqe2);
            this.a(adm2, aqe2, 4, 3, 5, 4, 3, 18, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, this.a - 5, 3, 5, this.a - 5, 3, 17, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, 3, 1, 5, 4, 2, 16, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, aqe2, this.a - 6, 1, 5, this.a - 5, 2, 16, afi.a.Q(), afi.a.Q(), false);
            for (\u2603 = 5; \u2603 <= 17; \u2603 += 2) {
                this.a(adm2, afi.A.a(aji.a.c.a()), 4, 1, \u2603, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), 4, 2, \u2603, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), this.a - 5, 1, \u2603, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), this.a - 5, 2, \u2603, aqe2);
            }
            this.a(adm2, afi.cu.a(\u2603), 10, 0, 7, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 10, 0, 8, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 9, 0, 9, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 11, 0, 9, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 8, 0, 10, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 12, 0, 10, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 7, 0, 10, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 13, 0, 10, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 9, 0, 11, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 11, 0, 11, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 10, 0, 12, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 10, 0, 13, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 10, 0, 10, aqe2);
            for (\u2603 = 0; \u2603 <= this.a - 1; \u2603 += this.a - 1) {
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 2, 1, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 2, 2, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 2, 3, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 3, 1, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 3, 2, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 3, 3, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 4, 1, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), \u2603, 4, 2, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 4, 3, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 5, 1, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 5, 2, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 5, 3, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 6, 1, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), \u2603, 6, 2, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 6, 3, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 7, 1, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 7, 2, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 7, 3, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 8, 1, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 8, 2, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 8, 3, aqe2);
            }
            for (\u2603 = 2; \u2603 <= this.a - 3; \u2603 += this.a - 3 - 2) {
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 - 1, 2, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 2, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 + 1, 2, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 - 1, 3, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 3, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 + 1, 3, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 - 1, 4, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), \u2603, 4, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 + 1, 4, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 - 1, 5, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 5, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 + 1, 5, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 - 1, 6, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.b.a()), \u2603, 6, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 + 1, 6, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 - 1, 7, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603, 7, 0, aqe2);
                this.a(adm2, afi.cu.a(\u2603), \u2603 + 1, 7, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 - 1, 8, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603, 8, 0, aqe2);
                this.a(adm2, afi.A.a(aji.a.c.a()), \u2603 + 1, 8, 0, aqe2);
            }
            this.a(adm2, aqe2, 8, 4, 0, 12, 6, 0, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, afi.a.Q(), 8, 6, 0, aqe2);
            this.a(adm2, afi.a.Q(), 12, 6, 0, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 9, 5, 0, aqe2);
            this.a(adm2, afi.A.a(aji.a.b.a()), 10, 5, 0, aqe2);
            this.a(adm2, afi.cu.a(\u2603), 11, 5, 0, aqe2);
            this.a(adm2, aqe2, 8, -14, 8, 12, -11, 12, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 8, -10, 8, 12, -10, 12, afi.A.a(aji.a.b.a()), afi.A.a(aji.a.b.a()), false);
            this.a(adm2, aqe2, 8, -9, 8, 12, -9, 12, afi.A.a(aji.a.c.a()), afi.A.a(aji.a.c.a()), false);
            this.a(adm2, aqe2, 8, -8, 8, 12, -1, 12, afi.A.Q(), afi.A.Q(), false);
            this.a(adm2, aqe2, 9, -11, 9, 11, -1, 11, afi.a.Q(), afi.a.Q(), false);
            this.a(adm2, afi.az.Q(), 10, -11, 10, aqe2);
            this.a(adm2, aqe2, 9, -13, 9, 11, -13, 11, afi.W.Q(), afi.a.Q(), false);
            this.a(adm2, afi.a.Q(), 8, -11, 10, aqe2);
            this.a(adm2, afi.a.Q(), 8, -10, 10, aqe2);
            this.a(adm2, afi.A.a(aji.a.b.a()), 7, -10, 10, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 7, -11, 10, aqe2);
            this.a(adm2, afi.a.Q(), 12, -11, 10, aqe2);
            this.a(adm2, afi.a.Q(), 12, -10, 10, aqe2);
            this.a(adm2, afi.A.a(aji.a.b.a()), 13, -10, 10, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 13, -11, 10, aqe2);
            this.a(adm2, afi.a.Q(), 10, -11, 8, aqe2);
            this.a(adm2, afi.a.Q(), 10, -10, 8, aqe2);
            this.a(adm2, afi.A.a(aji.a.b.a()), 10, -10, 7, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 10, -11, 7, aqe2);
            this.a(adm2, afi.a.Q(), 10, -11, 12, aqe2);
            this.a(adm2, afi.a.Q(), 10, -10, 12, aqe2);
            this.a(adm2, afi.A.a(aji.a.b.a()), 10, -10, 13, aqe2);
            this.a(adm2, afi.A.a(aji.a.c.a()), 10, -11, 13, aqe2);
            for (cq cq2 : cq.c.a) {
                if (this.e[cq2.b()]) continue;
                int n3 = cq2.g() * 2;
                \u2603 = cq2.i() * 2;
                this.e[cq2.b()] = this.a(adm2, aqe2, random, 10 + n3, -11, 10 + \u2603, ob.a(f, zy.cd.b(random)), 2 + random.nextInt(5));
            }
            return true;
        }
    }

    static abstract class c
    extends aqt {
        protected int a;
        protected int b;
        protected int c;
        protected int d = -1;

        public c() {
        }

        protected c(Random random, int n2, int n3, int n4, int n5, int n6, int n7) {
            super(0);
            this.a = n5;
            this.b = n6;
            this.c = n7;
            this.m = cq.c.a.a(random);
            switch (this.m) {
                case c: 
                case d: {
                    this.l = new aqe(n2, n3, n4, n2 + n5 - 1, n3 + n6 - 1, n4 + n7 - 1);
                    break;
                }
                default: {
                    this.l = new aqe(n2, n3, n4, n2 + n7 - 1, n3 + n6 - 1, n4 + n5 - 1);
                }
            }
        }

        @Override
        protected void a(dn dn2) {
            dn2.a("Width", this.a);
            dn2.a("Height", this.b);
            dn2.a("Depth", this.c);
            dn2.a("HPos", this.d);
        }

        @Override
        protected void b(dn dn2) {
            this.a = dn2.f("Width");
            this.b = dn2.f("Height");
            this.c = dn2.f("Depth");
            this.d = dn2.f("HPos");
        }

        protected boolean a(adm adm2, aqe aqe2, int n2) {
            int n3;
            if (this.d >= 0) {
                return true;
            }
            \u2603 = 0;
            n3 = 0;
            cj.a a2 = new cj.a();
            for (int i2 = this.l.c; i2 <= this.l.f; ++i2) {
                for (\u2603 = this.l.a; \u2603 <= this.l.d; ++\u2603) {
                    a2.c(\u2603, 64, i2);
                    if (!aqe2.b(a2)) continue;
                    \u2603 += Math.max(adm2.r(a2).o(), adm2.t.i());
                    ++n3;
                }
            }
            if (n3 == 0) {
                return false;
            }
            this.d = \u2603 / n3;
            this.l.a(0, this.d - this.l.b + n2, 0);
            return true;
        }
    }
}

