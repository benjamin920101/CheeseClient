/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.Map;

public class bgc {
    private final Map<alz, boq> a = Maps.newIdentityHashMap();
    private final bgv b = new bgv();
    private final bou c;

    public bgc(bou bou2) {
        this.c = bou2;
        this.d();
    }

    public bgv a() {
        return this.b;
    }

    public bmi a(alz alz2) {
        afh afh2 = alz2.c();
        boq \u26032 = this.b(alz2);
        if (\u26032 == null || \u26032 == this.c.a()) {
            if (afh2 == afi.ax || afh2 == afi.an || afh2 == afi.ae || afh2 == afi.cg || afh2 == afi.cK || afh2 == afi.cL) {
                return this.c.b().a("minecraft:blocks/planks_oak");
            }
            if (afh2 == afi.bQ) {
                return this.c.b().a("minecraft:blocks/obsidian");
            }
            if (afh2 == afi.k || afh2 == afi.l) {
                return this.c.b().a("minecraft:blocks/lava_still");
            }
            if (afh2 == afi.i || afh2 == afi.j) {
                return this.c.b().a("minecraft:blocks/water_still");
            }
            if (afh2 == afi.ce) {
                return this.c.b().a("minecraft:blocks/soul_sand");
            }
            if (afh2 == afi.cv) {
                return this.c.b().a("minecraft:items/barrier");
            }
        }
        if (\u26032 == null) {
            \u26032 = this.c.a();
        }
        return \u26032.e();
    }

    public boq b(alz alz2) {
        boq boq2 = this.a.get(alz2);
        if (boq2 == null) {
            boq2 = this.c.a();
        }
        return boq2;
    }

    public bou b() {
        return this.c;
    }

    public void c() {
        this.a.clear();
        for (Map.Entry<alz, bov> entry : this.b.a().entrySet()) {
            this.a.put(entry.getKey(), this.c.a(entry.getValue()));
        }
    }

    public void a(afh afh2, bgy bgy2) {
        this.b.a(afh2, bgy2);
    }

    public void a(afh ... afhArray) {
        this.b.a(afhArray);
    }

    private void d() {
        this.a(afi.a, afi.i, afi.j, afi.k, afi.l, afi.M, afi.ae, afi.bQ, afi.cg, afi.an, afi.ce, afi.bF, afi.cv, afi.ax, afi.cL, afi.cK);
        this.a(afi.b, new bgx.a().a((amo<?>)ajy.a).a());
        this.a(afi.cI, new bgx.a().a((amo<?>)aiu.a).a());
        this.a((afh)afi.t, new bgx.a().a((amo<?>)aik.Q).a("_leaves").a(ahs.b, ahs.a).a());
        this.a((afh)afi.u, new bgx.a().a((amo<?>)aif.Q).a("_leaves").a(ahs.b, ahs.a).a());
        this.a((afh)afi.aK, new bgx.a().a(new amo[]{afo.a}).a());
        this.a((afh)afi.aM, new bgx.a().a(new amo[]{aje.a}).a());
        this.a(afi.aN, new bgx.a().a(new amo[]{ahq.a}).a());
        this.a(afi.bX, new bgx.a().a(new amo[]{afw.a}).a());
        this.a(afi.bZ, new bgx.a().a((amo<?>)akl.Q).a("_wall").a());
        this.a((afh)afi.cF, new bgx.a().a((amo<?>)agi.a).a(new amo[]{agi.N}).a());
        this.a(afi.bo, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.bp, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.bq, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.br, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.bs, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.bt, new bgx.a().a(new amo[]{agu.b}).a());
        this.a(afi.bS, new bgx.a().a(aki.O, aki.a).a());
        this.a((afh)afi.bL, new bgx.a().a((amo<?>)aio.a).a("_double_slab").a());
        this.a((afh)afi.bM, new bgx.a().a((amo<?>)aio.a).a("_slab").a());
        this.a(afi.W, new bgx.a().a(new amo[]{ake.a}).a());
        this.a((afh)afi.ab, new bgx.a().a(new amo[]{agv.a}).a());
        this.a((afh)afi.af, new bgx.a().a(new amo[]{ajb.P}).a());
        this.a(afi.ao, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.ap, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.aq, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.ar, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.as, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.at, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.aA, new bgx.a().a(new amo[]{agh.O}).a());
        this.a(afi.L, new bgx.a().a((amo<?>)afv.a).a("_wool").a());
        this.a(afi.cy, new bgx.a().a((amo<?>)afv.a).a("_carpet").a());
        this.a(afi.cu, new bgx.a().a((amo<?>)afv.a).a("_stained_hardened_clay").a());
        this.a((afh)afi.cH, new bgx.a().a((amo<?>)afv.a).a("_stained_glass_pane").a());
        this.a((afh)afi.cG, new bgx.a().a((amo<?>)afv.a).a("_stained_glass").a());
        this.a(afi.A, new bgx.a().a((amo<?>)aji.a).a());
        this.a(afi.cM, new bgx.a().a((amo<?>)aiz.a).a());
        this.a((afh)afi.H, new bgx.a().a((amo<?>)akc.a).a());
        this.a(afi.C, new bgx.a().a(new amo[]{afg.b}).a());
        this.a((afh)afi.N, new bgx.a().a((amo<?>)afi.N.n()).a());
        this.a((afh)afi.O, new bgx.a().a((amo<?>)afi.O.n()).a());
        this.a((afh)afi.U, new bgx.a().a((amo<?>)akb.N).a("_slab").a());
        this.a((afh)afi.cP, new bgx.a().a((amo<?>)aih.N).a("_slab").a());
        this.a(afi.be, new bgx.a().a((amo<?>)ahz.a).a("_monster_egg").a());
        this.a(afi.bf, new bgx.a().a((amo<?>)ajz.a).a());
        this.a(afi.z, new bgx.a().a(new amo[]{agg.b}).a());
        this.a(afi.ct, new bgx.a().a(new amo[]{agk.b}).a());
        this.a(afi.r, new bgx.a().a((amo<?>)ail.b).a("_log").a());
        this.a(afi.s, new bgx.a().a((amo<?>)aig.b).a("_log").a());
        this.a(afi.f, new bgx.a().a((amo<?>)aio.a).a("_planks").a());
        this.a(afi.g, new bgx.a().a((amo<?>)ajj.a).a("_sapling").a());
        this.a((afh)afi.m, new bgx.a().a((amo<?>)ajh.a).a());
        this.a((afh)afi.cp, new bgx.a().a(new amo[]{ahn.b}).a());
        this.a(afi.ca, new bgx.a().a(new amo[]{agx.a}).a());
        this.a(afi.cq, new bgu(){

            @Override
            protected bov a(alz alz2) {
                aiw.a a2 = alz2.b(aiw.a);
                switch (a2) {
                    default: {
                        return new bov("quartz_block", "normal");
                    }
                    case b: {
                        return new bov("chiseled_quartz_block", "normal");
                    }
                    case c: {
                        return new bov("quartz_column", "axis=y");
                    }
                    case d: {
                        return new bov("quartz_column", "axis=x");
                    }
                    case e: 
                }
                return new bov("quartz_column", "axis=z");
            }
        });
        this.a((afh)afi.I, new bgu(){

            @Override
            protected bov a(alz alz2) {
                return new bov("dead_bush", "normal");
            }
        });
        this.a(afi.bl, new bgu(){

            @Override
            protected bov a(alz alz2) {
                LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
                if (alz2.b(ajx.b) != cq.b) {
                    linkedHashMap.remove(ajx.a);
                }
                return new bov((jy)afh.c.c(alz2.c()), this.a(linkedHashMap));
            }
        });
        this.a(afi.bm, new bgu(){

            @Override
            protected bov a(alz alz2) {
                LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
                if (alz2.b(ajx.b) != cq.b) {
                    linkedHashMap.remove(ajx.a);
                }
                return new bov((jy)afh.c.c(alz2.c()), this.a(linkedHashMap));
            }
        });
        this.a(afi.d, new bgu(){

            @Override
            protected bov a(alz alz2) {
                LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
                String \u26032 = agf.a.a((agf.a)((Object)((Comparable)linkedHashMap.remove(agf.a))));
                if (agf.a.c != alz2.b(agf.a)) {
                    linkedHashMap.remove(agf.b);
                }
                return new bov(\u26032, this.a(linkedHashMap));
            }
        });
        this.a((afh)afi.T, new bgu(){

            @Override
            protected bov a(alz alz2) {
                LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
                String \u26032 = akb.N.a((akb.a)((Object)((Comparable)linkedHashMap.remove(akb.N))));
                linkedHashMap.remove(akb.b);
                String \u26033 = alz2.b(akb.b) != false ? "all" : "normal";
                return new bov(\u26032 + "_double_slab", \u26033);
            }
        });
        this.a((afh)afi.cO, new bgu(){

            @Override
            protected bov a(alz alz2) {
                LinkedHashMap<amo, Comparable> linkedHashMap = Maps.newLinkedHashMap(alz2.b());
                String \u26032 = aih.N.a((aih.a)((Object)((Comparable)linkedHashMap.remove(aih.N))));
                linkedHashMap.remove(akb.b);
                String \u26033 = alz2.b(aih.b) != false ? "all" : "normal";
                return new bov(\u26032 + "_double_slab", \u26033);
            }
        });
    }
}

