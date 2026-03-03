/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class zw {
    public static final cx<jy, zw> e = new cx();
    private static final Map<afh, zw> a = Maps.newHashMap();
    protected static final UUID f = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private yz b;
    protected static Random g = new Random();
    protected int h = 64;
    private int c;
    protected boolean i;
    protected boolean j;
    private zw d;
    private String k;
    private String l;

    public static int b(zw zw2) {
        return zw2 == null ? 0 : e.b(zw2);
    }

    public static zw b(int n2) {
        return e.a(n2);
    }

    public static zw a(afh afh2) {
        return a.get(afh2);
    }

    public static zw d(String string) {
        zw zw2 = e.a(new jy(string));
        if (zw2 == null) {
            try {
                return zw.b(Integer.parseInt(string));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return zw2;
    }

    public boolean a(dn dn2) {
        return false;
    }

    public zw c(int n2) {
        this.h = n2;
        return this;
    }

    public boolean a(zx zx2, wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        return false;
    }

    public float a(zx zx2, afh afh2) {
        return 1.0f;
    }

    public zx a(zx zx2, adm adm2, wn wn2) {
        return zx2;
    }

    public zx b(zx zx2, adm adm2, wn wn2) {
        return zx2;
    }

    public int j() {
        return this.h;
    }

    public int a(int n2) {
        return 0;
    }

    public boolean k() {
        return this.j;
    }

    protected zw a(boolean bl2) {
        this.j = bl2;
        return this;
    }

    public int l() {
        return this.c;
    }

    protected zw d(int n2) {
        this.c = n2;
        return this;
    }

    public boolean m() {
        return this.c > 0 && !this.j;
    }

    public boolean a(zx zx2, pr pr2, pr pr3) {
        return false;
    }

    public boolean a(zx zx2, adm adm2, afh afh2, cj cj2, pr pr2) {
        return false;
    }

    public boolean b(afh afh2) {
        return false;
    }

    public boolean a(zx zx2, wn wn2, pr pr2) {
        return false;
    }

    public zw n() {
        this.i = true;
        return this;
    }

    public boolean w_() {
        return this.i;
    }

    public boolean e() {
        return false;
    }

    public zw c(String string) {
        this.l = string;
        return this;
    }

    public String k(zx zx2) {
        String string = this.e_(zx2);
        if (string == null) {
            return "";
        }
        return di.a(string);
    }

    public String a() {
        return "item." + this.l;
    }

    public String e_(zx zx2) {
        return "item." + this.l;
    }

    public zw c(zw zw2) {
        this.d = zw2;
        return this;
    }

    public boolean p() {
        return true;
    }

    public zw q() {
        return this.d;
    }

    public boolean r() {
        return this.d != null;
    }

    public int a(zx zx2, int n2) {
        return 0xFFFFFF;
    }

    public void a(zx zx2, adm adm2, pk pk2, int n2, boolean bl2) {
    }

    public void d(zx zx2, adm adm2, wn wn2) {
    }

    public boolean f() {
        return false;
    }

    public aba e(zx zx2) {
        return aba.a;
    }

    public int d(zx zx2) {
        return 0;
    }

    public void a(zx zx2, adm adm2, wn wn2, int n2) {
    }

    protected zw e(String string) {
        this.k = string;
        return this;
    }

    public String j(zx zx2) {
        return this.k;
    }

    public boolean l(zx zx2) {
        return this.j(zx2) != null;
    }

    public void a(zx zx2, wn wn2, List<String> list, boolean bl2) {
    }

    public String a(zx zx2) {
        return ("" + di.a(this.k(zx2) + ".name")).trim();
    }

    public boolean f(zx zx2) {
        return zx2.w();
    }

    public aaj g(zx zx2) {
        if (zx2.w()) {
            return aaj.c;
        }
        return aaj.a;
    }

    public boolean f_(zx zx2) {
        return this.j() == 1 && this.m();
    }

    protected auh a(adm adm2, wn wn2, boolean bl2) {
        float f2 = wn2.z;
        \u2603 = wn2.y;
        double \u26032 = wn2.s;
        double \u26033 = wn2.t + (double)wn2.aS();
        double \u26034 = wn2.u;
        aui \u26035 = new aui(\u26032, \u26033, \u26034);
        \u2603 = ns.b(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = ns.a(-\u2603 * ((float)Math.PI / 180) - (float)Math.PI);
        \u2603 = -ns.b(-f2 * ((float)Math.PI / 180));
        \u2603 = ns.a(-f2 * ((float)Math.PI / 180));
        \u2603 = \u2603 * \u2603;
        \u2603 = \u2603;
        \u2603 = \u2603 * \u2603;
        double \u26036 = 5.0;
        aui \u26037 = \u26035.b((double)\u2603 * \u26036, (double)\u2603 * \u26036, (double)\u2603 * \u26036);
        return adm2.a(\u26035, \u26037, bl2, !bl2, false);
    }

    public int b() {
        return 0;
    }

    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
    }

    public yz c() {
        return this.b;
    }

    public zw a(yz yz2) {
        this.b = yz2;
        return this;
    }

    public boolean s() {
        return false;
    }

    public boolean a(zx zx2, zx zx3) {
        return false;
    }

    public Multimap<String, qd> i() {
        return HashMultimap.create();
    }

    public static void t() {
        zw.a(afi.b, new aae(afi.b, afi.b, new Function<zx, String>(){

            public String a(zx zx2) {
                return ajy.a.a(zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("stone"));
        zw.a(afi.c, new aaz(afi.c, false));
        zw.a(afi.d, new aae(afi.d, afi.d, new Function<zx, String>(){

            public String a(zx zx2) {
                return agf.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("dirt"));
        zw.c(afi.e);
        zw.a(afi.f, new aae(afi.f, afi.f, new Function<zx, String>(){

            public String a(zx zx2) {
                return aio.a.a(zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("wood"));
        zw.a(afi.g, new aae(afi.g, afi.g, new Function<zx, String>(){

            public String a(zx zx2) {
                return aio.a.a(zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("sapling"));
        zw.c(afi.h);
        zw.a(afi.m, new aae((afh)afi.m, (afh)afi.m, new Function<zx, String>(){

            public String a(zx zx2) {
                return ajh.a.a(zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("sand"));
        zw.c(afi.n);
        zw.c(afi.o);
        zw.c(afi.p);
        zw.c(afi.q);
        zw.a(afi.r, new aae(afi.r, afi.r, new Function<zx, String>(){

            public String a(zx zx2) {
                return aio.a.a(zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("log"));
        zw.a(afi.s, new aae(afi.s, afi.s, new Function<zx, String>(){

            public String a(zx zx2) {
                return aio.a.a(zx2.i() + 4).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("log"));
        zw.a(afi.t, new aaa(afi.t).b("leaves"));
        zw.a(afi.u, new aaa(afi.u).b("leaves"));
        zw.a(afi.v, new aae(afi.v, afi.v, new Function<zx, String>(){

            public String a(zx zx2) {
                return (zx2.i() & 1) == 1 ? "wet" : "dry";
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("sponge"));
        zw.c(afi.w);
        zw.c(afi.x);
        zw.c(afi.y);
        zw.c(afi.z);
        zw.a(afi.A, new aae(afi.A, afi.A, new Function<zx, String>(){

            public String a(zx zx2) {
                return aji.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("sandStone"));
        zw.c(afi.B);
        zw.c(afi.D);
        zw.c(afi.E);
        zw.a(afi.F, new aah(afi.F));
        zw.c(afi.G);
        zw.a(afi.H, new aaz(afi.H, true).a(new String[]{"shrub", "grass", "fern"}));
        zw.c(afi.I);
        zw.a(afi.J, new aah(afi.J));
        zw.a(afi.L, new zf(afi.L).b("cloth"));
        zw.a(afi.N, new aae((afh)afi.N, (afh)afi.N, new Function<zx, String>(){

            public String a(zx zx2) {
                return agw.a.a(agw.b.a, zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("flower"));
        zw.a(afi.O, new aae((afh)afi.O, (afh)afi.O, new Function<zx, String>(){

            public String a(zx zx2) {
                return agw.a.a(agw.b.b, zx2.i()).d();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("rose"));
        zw.c(afi.P);
        zw.c(afi.Q);
        zw.c(afi.R);
        zw.c(afi.S);
        zw.a(afi.U, new aau(afi.U, afi.U, afi.T).b("stoneSlab"));
        zw.c(afi.V);
        zw.c(afi.W);
        zw.c(afi.X);
        zw.c(afi.Y);
        zw.c(afi.Z);
        zw.c(afi.aa);
        zw.c(afi.ac);
        zw.c(afi.ad);
        zw.c(afi.ae);
        zw.c(afi.ag);
        zw.c(afi.ah);
        zw.c(afi.ai);
        zw.c(afi.ak);
        zw.c(afi.al);
        zw.c(afi.am);
        zw.c(afi.au);
        zw.c(afi.av);
        zw.c(afi.aw);
        zw.c(afi.ay);
        zw.c(afi.az);
        zw.c(afi.aB);
        zw.c(afi.aC);
        zw.c(afi.aF);
        zw.c(afi.aG);
        zw.a(afi.aH, new aav(afi.aH));
        zw.c(afi.aI);
        zw.c(afi.aJ);
        zw.c(afi.aK);
        zw.c(afi.aL);
        zw.c(afi.aN);
        zw.c(afi.aO);
        zw.c(afi.aP);
        zw.c(afi.aQ);
        zw.c(afi.aR);
        zw.c(afi.aS);
        zw.c(afi.aT);
        zw.c(afi.aU);
        zw.c(afi.aV);
        zw.c(afi.aW);
        zw.c(afi.aX);
        zw.c(afi.aZ);
        zw.c(afi.bd);
        zw.a(afi.be, new aae(afi.be, afi.be, new Function<zx, String>(){

            public String a(zx zx2) {
                return ahz.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("monsterStoneEgg"));
        zw.a(afi.bf, new aae(afi.bf, afi.bf, new Function<zx, String>(){

            public String a(zx zx2) {
                return ajz.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("stonebricksmooth"));
        zw.c(afi.bg);
        zw.c(afi.bh);
        zw.c(afi.bi);
        zw.c(afi.bj);
        zw.c(afi.bk);
        zw.a(afi.bn, new aaz(afi.bn, false));
        zw.c(afi.bo);
        zw.c(afi.bp);
        zw.c(afi.bq);
        zw.c(afi.br);
        zw.c(afi.bs);
        zw.c(afi.bt);
        zw.c(afi.bu);
        zw.c(afi.bv);
        zw.c(afi.bw);
        zw.a(afi.bx, new abb(afi.bx));
        zw.c(afi.by);
        zw.c(afi.bz);
        zw.c(afi.bA);
        zw.c(afi.bC);
        zw.c(afi.bG);
        zw.c(afi.bH);
        zw.c(afi.bI);
        zw.c(afi.bJ);
        zw.a(afi.bM, new aau(afi.bM, afi.bM, afi.bL).b("woodSlab"));
        zw.c(afi.bO);
        zw.c(afi.bP);
        zw.c(afi.bQ);
        zw.c(afi.bR);
        zw.c(afi.bT);
        zw.c(afi.bU);
        zw.c(afi.bV);
        zw.c(afi.bW);
        zw.c(afi.bX);
        zw.c(afi.bY);
        zw.a(afi.bZ, new aae(afi.bZ, afi.bZ, new Function<zx, String>(){

            public String a(zx zx2) {
                return akl.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("cobbleWall"));
        zw.c(afi.cd);
        zw.a(afi.cf, new yi(afi.cf).b("anvil"));
        zw.c(afi.cg);
        zw.c(afi.ch);
        zw.c(afi.ci);
        zw.c(afi.cl);
        zw.c(afi.cn);
        zw.c(afi.co);
        zw.c(afi.cp);
        zw.a(afi.cq, new aae(afi.cq, afi.cq, new String[]{"default", "chiseled", "lines"}).b("quartzBlock"));
        zw.c(afi.cr);
        zw.c(afi.cs);
        zw.c(afi.ct);
        zw.a(afi.cu, new zf(afi.cu).b("clayHardenedStained"));
        zw.c(afi.cv);
        zw.c(afi.cw);
        zw.c(afi.cx);
        zw.a(afi.cy, new zf(afi.cy).b("woolCarpet"));
        zw.c(afi.cz);
        zw.c(afi.cA);
        zw.c(afi.cB);
        zw.c(afi.cC);
        zw.c(afi.cD);
        zw.c(afi.cE);
        zw.a(afi.cF, new zc((afh)afi.cF, (afh)afi.cF, new Function<zx, String>(){

            public String a(zx zx2) {
                return agi.b.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("doublePlant"));
        zw.a(afi.cG, new zf(afi.cG).b("stainedGlass"));
        zw.a(afi.cH, new zf(afi.cH).b("stainedGlassPane"));
        zw.a(afi.cI, new aae(afi.cI, afi.cI, new Function<zx, String>(){

            public String a(zx zx2) {
                return aiu.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("prismarine"));
        zw.c(afi.cJ);
        zw.a(afi.cM, new aae(afi.cM, afi.cM, new Function<zx, String>(){

            public String a(zx zx2) {
                return aiz.a.a(zx2.i()).c();
            }

            @Override
            public /* synthetic */ Object apply(Object object) {
                return this.a((zx)object);
            }
        }).b("redSandStone"));
        zw.c(afi.cN);
        zw.a(afi.cP, new aau(afi.cP, afi.cP, afi.cO).b("stoneSlab2"));
        zw.a(256, "iron_shovel", new aaq(zw$a.c).c("shovelIron"));
        zw.a(257, "iron_pickaxe", new aag(zw$a.c).c("pickaxeIron"));
        zw.a(258, "iron_axe", new yl(zw$a.c).c("hatchetIron"));
        zw.a(259, "flint_and_steel", new zr().c("flintAndSteel"));
        zw.a(260, "apple", new zs(4, 0.3f, false).c("apple"));
        zw.a(261, "bow", new yt().c("bow"));
        zw.a(262, "arrow", new zw().c("arrow").a(yz.j));
        zw.a(263, "coal", new yx().c("coal"));
        zw.a(264, "diamond", new zw().c("diamond").a(yz.l));
        zw.a(265, "iron_ingot", new zw().c("ingotIron").a(yz.l));
        zw.a(266, "gold_ingot", new zw().c("ingotGold").a(yz.l));
        zw.a(267, "iron_sword", new aay(zw$a.c).c("swordIron"));
        zw.a(268, "wooden_sword", new aay(zw$a.a).c("swordWood"));
        zw.a(269, "wooden_shovel", new aaq(zw$a.a).c("shovelWood"));
        zw.a(270, "wooden_pickaxe", new aag(zw$a.a).c("pickaxeWood"));
        zw.a(271, "wooden_axe", new yl(zw$a.a).c("hatchetWood"));
        zw.a(272, "stone_sword", new aay(zw$a.b).c("swordStone"));
        zw.a(273, "stone_shovel", new aaq(zw$a.b).c("shovelStone"));
        zw.a(274, "stone_pickaxe", new aag(zw$a.b).c("pickaxeStone"));
        zw.a(275, "stone_axe", new yl(zw$a.b).c("hatchetStone"));
        zw.a(276, "diamond_sword", new aay(zw$a.d).c("swordDiamond"));
        zw.a(277, "diamond_shovel", new aaq(zw$a.d).c("shovelDiamond"));
        zw.a(278, "diamond_pickaxe", new aag(zw$a.d).c("pickaxeDiamond"));
        zw.a(279, "diamond_axe", new yl(zw$a.d).c("hatchetDiamond"));
        zw.a(280, "stick", new zw().n().c("stick").a(yz.l));
        zw.a(281, "bowl", new zw().c("bowl").a(yz.l));
        zw.a(282, "mushroom_stew", new yu(6).c("mushroomStew"));
        zw.a(283, "golden_sword", new aay(zw$a.e).c("swordGold"));
        zw.a(284, "golden_shovel", new aaq(zw$a.e).c("shovelGold"));
        zw.a(285, "golden_pickaxe", new aag(zw$a.e).c("pickaxeGold"));
        zw.a(286, "golden_axe", new yl(zw$a.e).c("hatchetGold"));
        zw.a(287, "string", new yp(afi.bS).c("string").a(yz.l));
        zw.a(288, "feather", new zw().c("feather").a(yz.l));
        zw.a(289, "gunpowder", new zw().c("sulphur").e(abe.k).a(yz.l));
        zw.a(290, "wooden_hoe", new zv(zw$a.a).c("hoeWood"));
        zw.a(291, "stone_hoe", new zv(zw$a.b).c("hoeStone"));
        zw.a(292, "iron_hoe", new zv(zw$a.c).c("hoeIron"));
        zw.a(293, "diamond_hoe", new zv(zw$a.d).c("hoeDiamond"));
        zw.a(294, "golden_hoe", new zv(zw$a.e).c("hoeGold"));
        zw.a(295, "wheat_seeds", new aao(afi.aj, afi.ak).c("seeds"));
        zw.a(296, "wheat", new zw().c("wheat").a(yz.l));
        zw.a(297, "bread", new zs(5, 0.6f, false).c("bread"));
        zw.a(298, "leather_helmet", new yj(yj.a.a, 0, 0).c("helmetCloth"));
        zw.a(299, "leather_chestplate", new yj(yj.a.a, 0, 1).c("chestplateCloth"));
        zw.a(300, "leather_leggings", new yj(yj.a.a, 0, 2).c("leggingsCloth"));
        zw.a(301, "leather_boots", new yj(yj.a.a, 0, 3).c("bootsCloth"));
        zw.a(302, "chainmail_helmet", new yj(yj.a.b, 1, 0).c("helmetChain"));
        zw.a(303, "chainmail_chestplate", new yj(yj.a.b, 1, 1).c("chestplateChain"));
        zw.a(304, "chainmail_leggings", new yj(yj.a.b, 1, 2).c("leggingsChain"));
        zw.a(305, "chainmail_boots", new yj(yj.a.b, 1, 3).c("bootsChain"));
        zw.a(306, "iron_helmet", new yj(yj.a.c, 2, 0).c("helmetIron"));
        zw.a(307, "iron_chestplate", new yj(yj.a.c, 2, 1).c("chestplateIron"));
        zw.a(308, "iron_leggings", new yj(yj.a.c, 2, 2).c("leggingsIron"));
        zw.a(309, "iron_boots", new yj(yj.a.c, 2, 3).c("bootsIron"));
        zw.a(310, "diamond_helmet", new yj(yj.a.e, 3, 0).c("helmetDiamond"));
        zw.a(311, "diamond_chestplate", new yj(yj.a.e, 3, 1).c("chestplateDiamond"));
        zw.a(312, "diamond_leggings", new yj(yj.a.e, 3, 2).c("leggingsDiamond"));
        zw.a(313, "diamond_boots", new yj(yj.a.e, 3, 3).c("bootsDiamond"));
        zw.a(314, "golden_helmet", new yj(yj.a.d, 4, 0).c("helmetGold"));
        zw.a(315, "golden_chestplate", new yj(yj.a.d, 4, 1).c("chestplateGold"));
        zw.a(316, "golden_leggings", new yj(yj.a.d, 4, 2).c("leggingsGold"));
        zw.a(317, "golden_boots", new yj(yj.a.d, 4, 3).c("bootsGold"));
        zw.a(318, "flint", new zw().c("flint").a(yz.l));
        zw.a(319, "porkchop", new zs(3, 0.3f, true).c("porkchopRaw"));
        zw.a(320, "cooked_porkchop", new zs(8, 0.8f, true).c("porkchopCooked"));
        zw.a(321, "painting", new zu(uq.class).c("painting"));
        zw.a(322, "golden_apple", new zt(4, 1.2f, false).h().a(pe.l.H, 5, 1, 1.0f).c("appleGold"));
        zw.a(323, "sign", new aar().c("sign"));
        zw.a(324, "wooden_door", new zb(afi.ao).c("doorOak"));
        zw zw2 = new yv(afi.a).c("bucket").c(16);
        zw.a(325, "bucket", zw2);
        zw.a(326, "water_bucket", new yv(afi.i).c("bucketWater").c(zw2));
        zw.a(327, "lava_bucket", new yv(afi.k).c("bucketLava").c(zw2));
        zw.a(328, "minecart", new aad(va.a.a).c("minecart"));
        zw.a(329, "saddle", new aam().c("saddle"));
        zw.a(330, "iron_door", new zb(afi.aA).c("doorIron"));
        zw.a(331, "redstone", new aal().c("redstone").e(abe.i));
        zw.a(332, "snowball", new aaw().c("snowball"));
        zw.a(333, "boat", new yq().c("boat"));
        zw.a(334, "leather", new zw().c("leather").a(yz.l));
        zw.a(335, "milk_bucket", new aac().c("milk").c(zw2));
        zw.a(336, "brick", new zw().c("brick").a(yz.l));
        zw.a(337, "clay_ball", new zw().c("clay").a(yz.l));
        zw.a(338, "reeds", new yp(afi.aM).c("reeds").a(yz.l));
        zw.a(339, "paper", new zw().c("paper").a(yz.f));
        zw.a(340, "book", new yr().c("book").a(yz.f));
        zw.a(341, "slime_ball", new zw().c("slimeball").a(yz.f));
        zw.a(342, "chest_minecart", new aad(va.a.b).c("minecartChest"));
        zw.a(343, "furnace_minecart", new aad(va.a.c).c("minecartFurnace"));
        zw.a(344, "egg", new zg().c("egg"));
        zw.a(345, "compass", new zw().c("compass").a(yz.i));
        zw.a(346, "fishing_rod", new zq().c("fishingRod"));
        zw.a(347, "clock", new zw().c("clock").a(yz.i));
        zw.a(348, "glowstone_dust", new zw().c("yellowDust").e(abe.j).a(yz.l));
        zw.a(349, "fish", new zp(false).c("fish").a(true));
        zw.a(350, "cooked_fish", new zp(true).c("fish").a(true));
        zw.a(351, "dye", new ze().c("dyePowder"));
        zw.a(352, "bone", new zw().c("bone").n().a(yz.f));
        zw.a(353, "sugar", new zw().c("sugar").e(abe.b).a(yz.l));
        zw.a(354, "cake", new yp(afi.ba).c(1).c("cake").a(yz.h));
        zw.a(355, "bed", new yn().c(1).c("bed"));
        zw.a(356, "repeater", new yp(afi.bb).c("diode").a(yz.d));
        zw.a(357, "cookie", new zs(2, 0.1f, false).c("cookie"));
        zw.a(358, "filled_map", new aab().c("map"));
        zw.a(359, "shears", new aap().c("shears"));
        zw.a(360, "melon", new zs(2, 0.3f, false).c("melon"));
        zw.a(361, "pumpkin_seeds", new aao(afi.bl, afi.ak).c("seeds_pumpkin"));
        zw.a(362, "melon_seeds", new aao(afi.bm, afi.ak).c("seeds_melon"));
        zw.a(363, "beef", new zs(3, 0.3f, true).c("beefRaw"));
        zw.a(364, "cooked_beef", new zs(8, 0.8f, true).c("beefCooked"));
        zw.a(365, "chicken", new zs(2, 0.3f, true).a(pe.s.H, 30, 0, 0.3f).c("chickenRaw"));
        zw.a(366, "cooked_chicken", new zs(6, 0.6f, true).c("chickenCooked"));
        zw.a(367, "rotten_flesh", new zs(4, 0.1f, true).a(pe.s.H, 30, 0, 0.8f).c("rottenFlesh"));
        zw.a(368, "ender_pearl", new zk().c("enderPearl"));
        zw.a(369, "blaze_rod", new zw().c("blazeRod").a(yz.l).n());
        zw.a(370, "ghast_tear", new zw().c("ghastTear").e(abe.c).a(yz.k));
        zw.a(371, "gold_nugget", new zw().c("goldNugget").a(yz.l));
        zw.a(372, "nether_wart", new aao(afi.bB, afi.aW).c("netherStalkSeeds").e("+4"));
        zw.a(373, "potion", new aai().c("potion"));
        zw.a(374, "glass_bottle", new ys().c("glassBottle"));
        zw.a(375, "spider_eye", new zs(2, 0.8f, false).a(pe.u.H, 5, 0, 1.0f).c("spiderEye").e(abe.d));
        zw.a(376, "fermented_spider_eye", new zw().c("fermentedSpiderEye").e(abe.e).a(yz.k));
        zw.a(377, "blaze_powder", new zw().c("blazePowder").e(abe.g).a(yz.k));
        zw.a(378, "magma_cream", new zw().c("magmaCream").e(abe.h).a(yz.k));
        zw.a(379, "brewing_stand", new yp(afi.bD).c("brewingStand").a(yz.k));
        zw.a(380, "cauldron", new yp(afi.bE).c("cauldron").a(yz.k));
        zw.a(381, "ender_eye", new zj().c("eyeOfEnder"));
        zw.a(382, "speckled_melon", new zw().c("speckledMelon").e(abe.f).a(yz.k));
        zw.a(383, "spawn_egg", new aax().c("monsterPlacer"));
        zw.a(384, "experience_bottle", new zl().c("expBottle"));
        zw.a(385, "fire_charge", new zm().c("fireball"));
        zw.a(386, "writable_book", new abc().c("writingBook").a(yz.f));
        zw.a(387, "written_book", new abd().c("writtenBook").c(16));
        zw.a(388, "emerald", new zw().c("emerald").a(yz.l));
        zw.a(389, "item_frame", new zu(uo.class).c("frame"));
        zw.a(390, "flower_pot", new yp(afi.ca).c("flowerPot").a(yz.c));
        zw.a(391, "carrot", new aan(3, 0.6f, afi.cb, afi.ak).c("carrots"));
        zw.a(392, "potato", new aan(1, 0.3f, afi.cc, afi.ak).c("potato"));
        zw.a(393, "baked_potato", new zs(5, 0.6f, false).c("potatoBaked"));
        zw.a(394, "poisonous_potato", new zs(2, 0.3f, false).a(pe.u.H, 5, 0, 0.6f).c("potatoPoisonous"));
        zw.a(395, "map", new zh().c("emptyMap"));
        zw.a(396, "golden_carrot", new zs(6, 1.2f, false).c("carrotGolden").e(abe.l).a(yz.k));
        zw.a(397, "skull", new aat().c("skull"));
        zw.a(398, "carrot_on_a_stick", new yw().c("carrotOnAStick"));
        zw.a(399, "nether_star", new aas().c("netherStar").a(yz.l));
        zw.a(400, "pumpkin_pie", new zs(8, 0.3f, false).c("pumpkinPie").a(yz.h));
        zw.a(401, "fireworks", new zo().c("fireworks"));
        zw.a(402, "firework_charge", new zn().c("fireworksCharge").a(yz.f));
        zw.a(403, "enchanted_book", new zi().c(1).c("enchantedBook"));
        zw.a(404, "comparator", new yp(afi.cj).c("comparator").a(yz.d));
        zw.a(405, "netherbrick", new zw().c("netherbrick").a(yz.l));
        zw.a(406, "quartz", new zw().c("netherquartz").a(yz.l));
        zw.a(407, "tnt_minecart", new aad(va.a.d).c("minecartTnt"));
        zw.a(408, "hopper_minecart", new aad(va.a.f).c("minecartHopper"));
        zw.a(409, "prismarine_shard", new zw().c("prismarineShard").a(yz.l));
        zw.a(410, "prismarine_crystals", new zw().c("prismarineCrystals").a(yz.l));
        zw.a(411, "rabbit", new zs(3, 0.3f, true).c("rabbitRaw"));
        zw.a(412, "cooked_rabbit", new zs(5, 0.6f, true).c("rabbitCooked"));
        zw.a(413, "rabbit_stew", new yu(10).c("rabbitStew"));
        zw.a(414, "rabbit_foot", new zw().c("rabbitFoot").e(abe.n).a(yz.k));
        zw.a(415, "rabbit_hide", new zw().c("rabbitHide").a(yz.l));
        zw.a(416, "armor_stand", new yk().c("armorStand").c(16));
        zw.a(417, "iron_horse_armor", new zw().c("horsearmormetal").c(1).a(yz.f));
        zw.a(418, "golden_horse_armor", new zw().c("horsearmorgold").c(1).a(yz.f));
        zw.a(419, "diamond_horse_armor", new zw().c("horsearmordiamond").c(1).a(yz.f));
        zw.a(420, "lead", new zz().c("leash"));
        zw.a(421, "name_tag", new aaf().c("nameTag"));
        zw.a(422, "command_block_minecart", new aad(va.a.g).c("minecartCommandBlock").a((yz)null));
        zw.a(423, "mutton", new zs(2, 0.3f, true).c("muttonRaw"));
        zw.a(424, "cooked_mutton", new zs(6, 0.8f, true).c("muttonCooked"));
        zw.a(425, "banner", (zw)new ym().b("banner"));
        zw.a(427, "spruce_door", new zb(afi.ap).c("doorSpruce"));
        zw.a(428, "birch_door", new zb(afi.aq).c("doorBirch"));
        zw.a(429, "jungle_door", new zb(afi.ar).c("doorJungle"));
        zw.a(430, "acacia_door", new zb(afi.as).c("doorAcacia"));
        zw.a(431, "dark_oak_door", new zb(afi.at).c("doorDarkOak"));
        zw.a(2256, "record_13", new aak("13").c("record"));
        zw.a(2257, "record_cat", new aak("cat").c("record"));
        zw.a(2258, "record_blocks", new aak("blocks").c("record"));
        zw.a(2259, "record_chirp", new aak("chirp").c("record"));
        zw.a(2260, "record_far", new aak("far").c("record"));
        zw.a(2261, "record_mall", new aak("mall").c("record"));
        zw.a(2262, "record_mellohi", new aak("mellohi").c("record"));
        zw.a(2263, "record_stal", new aak("stal").c("record"));
        zw.a(2264, "record_strad", new aak("strad").c("record"));
        zw.a(2265, "record_ward", new aak("ward").c("record"));
        zw.a(2266, "record_11", new aak("11").c("record"));
        zw.a(2267, "record_wait", new aak("wait").c("record"));
    }

    private static void c(afh afh2) {
        zw.a(afh2, new yo(afh2));
    }

    protected static void a(afh afh2, zw zw2) {
        zw.a(afh.a(afh2), (jy)afh.c.c(afh2), zw2);
        a.put(afh2, zw2);
    }

    private static void a(int n2, String string, zw zw2) {
        zw.a(n2, new jy(string), zw2);
    }

    private static void a(int n2, jy jy2, zw zw2) {
        e.a(n2, jy2, zw2);
    }

    public static enum a {
        a(0, 59, 2.0f, 0.0f, 15),
        b(1, 131, 4.0f, 1.0f, 5),
        c(2, 250, 6.0f, 2.0f, 14),
        d(3, 1561, 8.0f, 3.0f, 10),
        e(0, 32, 12.0f, 0.0f, 22);

        private final int f;
        private final int g;
        private final float h;
        private final float i;
        private final int j;

        private a(int n3, int n4, float f2, float f3, int n5) {
            this.f = n3;
            this.g = n4;
            this.h = f2;
            this.i = f3;
            this.j = n5;
        }

        public int a() {
            return this.g;
        }

        public float b() {
            return this.h;
        }

        public float c() {
            return this.i;
        }

        public int d() {
            return this.f;
        }

        public int e() {
            return this.j;
        }

        public zw f() {
            if (this == a) {
                return zw.a(afi.f);
            }
            if (this == b) {
                return zw.a(afi.e);
            }
            if (this == e) {
                return zy.k;
            }
            if (this == c) {
                return zy.j;
            }
            if (this == d) {
                return zy.i;
            }
            return null;
        }
    }
}

