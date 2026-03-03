/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;
import java.util.concurrent.Callable;

public class bjh
implements bnj {
    private static final jy b = new jy("textures/misc/enchanted_item_glint.png");
    private boolean c = true;
    public float a;
    private final bfo d;
    private final bmj e;

    public bjh(bmj bmj2, bou bou2) {
        this.e = bmj2;
        this.d = new bfo(bou2);
        this.b();
    }

    public void a(boolean bl2) {
        this.c = bl2;
    }

    public bfo a() {
        return this.d;
    }

    protected void a(zw zw2, int n2, String string) {
        this.d.a(zw2, n2, new bov(string, "inventory"));
    }

    protected void a(afh afh2, int n2, String string) {
        this.a(zw.a(afh2), n2, string);
    }

    private void a(afh afh2, String string) {
        this.a(afh2, 0, string);
    }

    private void a(zw zw2, String string) {
        this.a(zw2, 0, string);
    }

    private void a(boq boq2, zx zx2) {
        this.a(boq2, -1, zx2);
    }

    private void a(boq boq2, int n2) {
        this.a(boq2, n2, null);
    }

    private void a(boq boq22, int n2, zx zx2) {
        boq boq22;
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        \u26032.a(7, bms.b);
        for (cq cq2 : cq.values()) {
            this.a(\u26032, boq22.a(cq2), n2, zx2);
        }
        this.a(\u26032, boq22.a(), n2, zx2);
        bfx2.b();
    }

    public void a(zx zx2, boq boq2) {
        if (zx2 == null) {
            return;
        }
        bfl.E();
        bfl.a(0.5f, 0.5f, 0.5f);
        if (boq2.d()) {
            bfl.b(180.0f, 0.0f, 1.0f, 0.0f);
            bfl.b(-0.5f, -0.5f, -0.5f);
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            bfl.B();
            bfi.a.a(zx2);
        } else {
            bfl.b(-0.5f, -0.5f, -0.5f);
            this.a(boq2, zx2);
            if (zx2.t()) {
                this.a(boq2);
            }
        }
        bfl.F();
    }

    private void a(boq boq2) {
        bfl.a(false);
        bfl.c(514);
        bfl.f();
        bfl.b(768, 1);
        this.e.a(b);
        bfl.n(5890);
        bfl.E();
        bfl.a(8.0f, 8.0f, 8.0f);
        float f2 = (float)(ave.J() % 3000L) / 3000.0f / 8.0f;
        bfl.b(f2, 0.0f, 0.0f);
        bfl.b(-50.0f, 0.0f, 0.0f, 1.0f);
        this.a(boq2, -8372020);
        bfl.F();
        bfl.E();
        bfl.a(8.0f, 8.0f, 8.0f);
        \u2603 = (float)(ave.J() % 4873L) / 4873.0f / 8.0f;
        bfl.b(-\u2603, 0.0f, 0.0f);
        bfl.b(10.0f, 0.0f, 0.0f, 1.0f);
        this.a(boq2, -8372020);
        bfl.F();
        bfl.n(5888);
        bfl.b(770, 771);
        bfl.e();
        bfl.c(515);
        bfl.a(true);
        this.e.a(bmh.g);
    }

    private void a(bfd bfd2, bgg bgg2) {
        df df2 = bgg2.d().m();
        bfd2.b(df2.n(), df2.o(), df2.p());
    }

    private void a(bfd bfd2, bgg bgg2, int n2) {
        bfd2.a(bgg2.a());
        bfd2.a(n2);
        this.a(bfd2, bgg2);
    }

    private void a(bfd bfd2, List<bgg> list, int n2, zx zx2) {
        boolean bl2 = n2 == -1 && zx2 != null;
        int \u26032 = list.size();
        for (int i2 = 0; i2 < \u26032; ++i2) {
            bgg bgg2 = list.get(i2);
            int \u26033 = n2;
            if (bl2 && bgg2.b()) {
                \u26033 = zx2.b().a(zx2, bgg2.c());
                if (bfk.a) {
                    \u26033 = bml.c(\u26033);
                }
                \u26033 |= 0xFF000000;
            }
            this.a(bfd2, bgg2, \u26033);
        }
    }

    public boolean a(zx zx2) {
        boq boq2 = this.d.a(zx2);
        if (boq2 == null) {
            return false;
        }
        return boq2.c();
    }

    private void b(zx zx2) {
        boq boq2 = this.d.a(zx2);
        zw \u26032 = zx2.b();
        if (\u26032 == null) {
            return;
        }
        boolean \u26033 = boq2.c();
        if (!\u26033) {
            bfl.a(2.0f, 2.0f, 2.0f);
        }
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void a(zx zx2, bgr.b b2) {
        if (zx2 == null) {
            return;
        }
        boq boq2 = this.d.a(zx2);
        this.a(zx2, boq2, b2);
    }

    public void a(zx zx22, pr pr2, bgr.b b2) {
        zx zx22;
        if (zx22 == null || pr2 == null) {
            return;
        }
        boq boq2 = this.d.a(zx22);
        if (pr2 instanceof wn) {
            wn wn2 = (wn)pr2;
            zw \u26032 = zx22.b();
            bov \u26033 = null;
            if (\u26032 == zy.aR && wn2.bG != null) {
                \u26033 = new bov("fishing_rod_cast", "inventory");
            } else if (\u26032 == zy.f && wn2.bQ() != null) {
                int n2 = zx22.l() - wn2.bR();
                if (n2 >= 18) {
                    \u26033 = new bov("bow_pulling_2", "inventory");
                } else if (n2 > 13) {
                    \u26033 = new bov("bow_pulling_1", "inventory");
                } else if (n2 > 0) {
                    \u26033 = new bov("bow_pulling_0", "inventory");
                }
            }
            if (\u26033 != null) {
                boq2 = this.d.a().a(\u26033);
            }
        }
        this.a(zx22, boq2, b2);
    }

    protected void a(zx zx2, boq boq2, bgr.b b2) {
        this.e.a(bmh.g);
        this.e.b(bmh.g).b(false, false);
        this.b(zx2);
        bfl.B();
        bfl.a(516, 0.1f);
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.E();
        bgr bgr2 = boq2.f();
        bgr2.a(b2);
        if (this.a(bgr2.b(b2))) {
            bfl.e(1028);
        }
        this.a(zx2, boq2);
        bfl.e(1029);
        bfl.F();
        bfl.C();
        bfl.k();
        this.e.a(bmh.g);
        this.e.b(bmh.g).a();
    }

    private boolean a(bgq bgq2) {
        return bgq2.d.x < 0.0f ^ bgq2.d.y < 0.0f ^ bgq2.d.z < 0.0f;
    }

    public void a(zx zx2, int n2, int n3) {
        boq boq2 = this.d.a(zx2);
        bfl.E();
        this.e.a(bmh.g);
        this.e.b(bmh.g).b(false, false);
        bfl.B();
        bfl.d();
        bfl.a(516, 0.1f);
        bfl.l();
        bfl.b(770, 771);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.a(n2, n3, boq2.c());
        boq2.f().a(bgr.b.e);
        this.a(zx2, boq2);
        bfl.c();
        bfl.C();
        bfl.f();
        bfl.F();
        this.e.a(bmh.g);
        this.e.b(bmh.g).a();
    }

    private void a(int n2, int n3, boolean bl2) {
        bfl.b(n2, n3, 100.0f + this.a);
        bfl.b(8.0f, 8.0f, 0.0f);
        bfl.a(1.0f, 1.0f, -1.0f);
        bfl.a(0.5f, 0.5f, 0.5f);
        if (bl2) {
            bfl.a(40.0f, 40.0f, 40.0f);
            bfl.b(210.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(-135.0f, 0.0f, 1.0f, 0.0f);
            bfl.e();
        } else {
            bfl.a(64.0f, 64.0f, 64.0f);
            bfl.b(180.0f, 1.0f, 0.0f, 0.0f);
            bfl.f();
        }
    }

    public void b(final zx zx2, int n2, int n3) {
        if (zx2 == null || zx2.b() == null) {
            return;
        }
        this.a += 50.0f;
        try {
            this.a(zx2, n2, n3);
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Rendering item");
            c \u26032 = b2.a("Item being rendered");
            \u26032.a("Item Type", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(zx2.b());
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Item Aux", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(zx2.i());
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Item NBT", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(zx2.o());
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            \u26032.a("Item Foil", new Callable<String>(){

                public String a() throws Exception {
                    return String.valueOf(zx2.t());
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
        this.a -= 50.0f;
    }

    public void a(avn avn2, zx zx2, int n2, int n3) {
        this.a(avn2, zx2, n2, n3, null);
    }

    public void a(avn avn2, zx zx2, int n2, int n3, String string) {
        if (zx2 == null) {
            return;
        }
        if (zx2.b != 1 || string != null) {
            String string2 = \u2603 = string == null ? String.valueOf(zx2.b) : string;
            if (string == null && zx2.b < 1) {
                \u2603 = (Object)((Object)a.m) + String.valueOf(zx2.b);
            }
            bfl.f();
            bfl.i();
            bfl.k();
            avn2.a(\u2603, (float)(n2 + 19 - 2 - avn2.a(\u2603)), (float)(n3 + 6 + 3), 0xFFFFFF);
            bfl.e();
            bfl.j();
        }
        if (zx2.g()) {
            int n4 = (int)Math.round(13.0 - (double)zx2.h() * 13.0 / (double)zx2.j());
            \u2603 = (int)Math.round(255.0 - (double)zx2.h() * 255.0 / (double)zx2.j());
            bfl.f();
            bfl.i();
            bfl.x();
            bfl.c();
            bfl.k();
            bfx \u26032 = bfx.a();
            bfd \u26033 = \u26032.c();
            this.a(\u26033, n2 + 2, n3 + 13, 13, 2, 0, 0, 0, 255);
            this.a(\u26033, n2 + 2, n3 + 13, 12, 1, (255 - \u2603) / 4, 64, 0, 255);
            this.a(\u26033, n2 + 2, n3 + 13, n4, 1, 255 - \u2603, \u2603, 0, 255);
            bfl.l();
            bfl.d();
            bfl.w();
            bfl.e();
            bfl.j();
        }
    }

    private void a(bfd bfd2, int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9) {
        bfd2.a(7, bms.f);
        bfd2.b((double)(n2 + 0), (double)(n3 + 0), 0.0).b(n6, n7, n8, n9).d();
        bfd2.b((double)(n2 + 0), (double)(n3 + n5), 0.0).b(n6, n7, n8, n9).d();
        bfd2.b((double)(n2 + n4), (double)(n3 + n5), 0.0).b(n6, n7, n8, n9).d();
        bfd2.b((double)(n2 + n4), (double)(n3 + 0), 0.0).b(n6, n7, n8, n9).d();
        bfx.a().b();
    }

    private void b() {
        this.a(afi.cf, "anvil_intact");
        this.a(afi.cf, 1, "anvil_slightly_damaged");
        this.a(afi.cf, 2, "anvil_very_damaged");
        this.a(afi.cy, zd.p.a(), "black_carpet");
        this.a(afi.cy, zd.l.a(), "blue_carpet");
        this.a(afi.cy, zd.m.a(), "brown_carpet");
        this.a(afi.cy, zd.j.a(), "cyan_carpet");
        this.a(afi.cy, zd.h.a(), "gray_carpet");
        this.a(afi.cy, zd.n.a(), "green_carpet");
        this.a(afi.cy, zd.d.a(), "light_blue_carpet");
        this.a(afi.cy, zd.f.a(), "lime_carpet");
        this.a(afi.cy, zd.c.a(), "magenta_carpet");
        this.a(afi.cy, zd.b.a(), "orange_carpet");
        this.a(afi.cy, zd.g.a(), "pink_carpet");
        this.a(afi.cy, zd.k.a(), "purple_carpet");
        this.a(afi.cy, zd.o.a(), "red_carpet");
        this.a(afi.cy, zd.i.a(), "silver_carpet");
        this.a(afi.cy, zd.a.a(), "white_carpet");
        this.a(afi.cy, zd.e.a(), "yellow_carpet");
        this.a(afi.bZ, akl.a.b.a(), "mossy_cobblestone_wall");
        this.a(afi.bZ, akl.a.a.a(), "cobblestone_wall");
        this.a(afi.d, agf.a.b.a(), "coarse_dirt");
        this.a(afi.d, agf.a.a.a(), "dirt");
        this.a(afi.d, agf.a.c.a(), "podzol");
        this.a(afi.cF, agi.b.d.a(), "double_fern");
        this.a(afi.cF, agi.b.c.a(), "double_grass");
        this.a(afi.cF, agi.b.f.a(), "paeonia");
        this.a(afi.cF, agi.b.e.a(), "double_rose");
        this.a(afi.cF, agi.b.a.a(), "sunflower");
        this.a(afi.cF, agi.b.b.a(), "syringa");
        this.a(afi.t, aio.a.c.a(), "birch_leaves");
        this.a(afi.t, aio.a.d.a(), "jungle_leaves");
        this.a(afi.t, aio.a.a.a(), "oak_leaves");
        this.a(afi.t, aio.a.b.a(), "spruce_leaves");
        this.a(afi.u, aio.a.e.a() - 4, "acacia_leaves");
        this.a(afi.u, aio.a.f.a() - 4, "dark_oak_leaves");
        this.a(afi.r, aio.a.c.a(), "birch_log");
        this.a(afi.r, aio.a.d.a(), "jungle_log");
        this.a(afi.r, aio.a.a.a(), "oak_log");
        this.a(afi.r, aio.a.b.a(), "spruce_log");
        this.a(afi.s, aio.a.e.a() - 4, "acacia_log");
        this.a(afi.s, aio.a.f.a() - 4, "dark_oak_log");
        this.a(afi.be, ahz.a.f.a(), "chiseled_brick_monster_egg");
        this.a(afi.be, ahz.a.b.a(), "cobblestone_monster_egg");
        this.a(afi.be, ahz.a.e.a(), "cracked_brick_monster_egg");
        this.a(afi.be, ahz.a.d.a(), "mossy_brick_monster_egg");
        this.a(afi.be, ahz.a.a.a(), "stone_monster_egg");
        this.a(afi.be, ahz.a.c.a(), "stone_brick_monster_egg");
        this.a(afi.f, aio.a.e.a(), "acacia_planks");
        this.a(afi.f, aio.a.c.a(), "birch_planks");
        this.a(afi.f, aio.a.f.a(), "dark_oak_planks");
        this.a(afi.f, aio.a.d.a(), "jungle_planks");
        this.a(afi.f, aio.a.a.a(), "oak_planks");
        this.a(afi.f, aio.a.b.a(), "spruce_planks");
        this.a(afi.cI, aiu.a.b.a(), "prismarine_bricks");
        this.a(afi.cI, aiu.a.c.a(), "dark_prismarine");
        this.a(afi.cI, aiu.a.a.a(), "prismarine");
        this.a(afi.cq, aiw.a.b.a(), "chiseled_quartz_block");
        this.a(afi.cq, aiw.a.a.a(), "quartz_block");
        this.a(afi.cq, aiw.a.c.a(), "quartz_column");
        this.a(afi.O, agw.a.d.b(), "allium");
        this.a(afi.O, agw.a.c.b(), "blue_orchid");
        this.a(afi.O, agw.a.e.b(), "houstonia");
        this.a(afi.O, agw.a.g.b(), "orange_tulip");
        this.a(afi.O, agw.a.j.b(), "oxeye_daisy");
        this.a(afi.O, agw.a.i.b(), "pink_tulip");
        this.a(afi.O, agw.a.b.b(), "poppy");
        this.a(afi.O, agw.a.f.b(), "red_tulip");
        this.a(afi.O, agw.a.h.b(), "white_tulip");
        this.a(afi.m, ajh.a.b.a(), "red_sand");
        this.a(afi.m, ajh.a.a.a(), "sand");
        this.a(afi.A, aji.a.b.a(), "chiseled_sandstone");
        this.a(afi.A, aji.a.a.a(), "sandstone");
        this.a(afi.A, aji.a.c.a(), "smooth_sandstone");
        this.a(afi.cM, aiz.a.b.a(), "chiseled_red_sandstone");
        this.a(afi.cM, aiz.a.a.a(), "red_sandstone");
        this.a(afi.cM, aiz.a.c.a(), "smooth_red_sandstone");
        this.a(afi.g, aio.a.e.a(), "acacia_sapling");
        this.a(afi.g, aio.a.c.a(), "birch_sapling");
        this.a(afi.g, aio.a.f.a(), "dark_oak_sapling");
        this.a(afi.g, aio.a.d.a(), "jungle_sapling");
        this.a(afi.g, aio.a.a.a(), "oak_sapling");
        this.a(afi.g, aio.a.b.a(), "spruce_sapling");
        this.a(afi.v, 0, "sponge");
        this.a(afi.v, 1, "sponge_wet");
        this.a(afi.cG, zd.p.a(), "black_stained_glass");
        this.a(afi.cG, zd.l.a(), "blue_stained_glass");
        this.a(afi.cG, zd.m.a(), "brown_stained_glass");
        this.a(afi.cG, zd.j.a(), "cyan_stained_glass");
        this.a(afi.cG, zd.h.a(), "gray_stained_glass");
        this.a(afi.cG, zd.n.a(), "green_stained_glass");
        this.a(afi.cG, zd.d.a(), "light_blue_stained_glass");
        this.a(afi.cG, zd.f.a(), "lime_stained_glass");
        this.a(afi.cG, zd.c.a(), "magenta_stained_glass");
        this.a(afi.cG, zd.b.a(), "orange_stained_glass");
        this.a(afi.cG, zd.g.a(), "pink_stained_glass");
        this.a(afi.cG, zd.k.a(), "purple_stained_glass");
        this.a(afi.cG, zd.o.a(), "red_stained_glass");
        this.a(afi.cG, zd.i.a(), "silver_stained_glass");
        this.a(afi.cG, zd.a.a(), "white_stained_glass");
        this.a(afi.cG, zd.e.a(), "yellow_stained_glass");
        this.a(afi.cH, zd.p.a(), "black_stained_glass_pane");
        this.a(afi.cH, zd.l.a(), "blue_stained_glass_pane");
        this.a(afi.cH, zd.m.a(), "brown_stained_glass_pane");
        this.a(afi.cH, zd.j.a(), "cyan_stained_glass_pane");
        this.a(afi.cH, zd.h.a(), "gray_stained_glass_pane");
        this.a(afi.cH, zd.n.a(), "green_stained_glass_pane");
        this.a(afi.cH, zd.d.a(), "light_blue_stained_glass_pane");
        this.a(afi.cH, zd.f.a(), "lime_stained_glass_pane");
        this.a(afi.cH, zd.c.a(), "magenta_stained_glass_pane");
        this.a(afi.cH, zd.b.a(), "orange_stained_glass_pane");
        this.a(afi.cH, zd.g.a(), "pink_stained_glass_pane");
        this.a(afi.cH, zd.k.a(), "purple_stained_glass_pane");
        this.a(afi.cH, zd.o.a(), "red_stained_glass_pane");
        this.a(afi.cH, zd.i.a(), "silver_stained_glass_pane");
        this.a(afi.cH, zd.a.a(), "white_stained_glass_pane");
        this.a(afi.cH, zd.e.a(), "yellow_stained_glass_pane");
        this.a(afi.cu, zd.p.a(), "black_stained_hardened_clay");
        this.a(afi.cu, zd.l.a(), "blue_stained_hardened_clay");
        this.a(afi.cu, zd.m.a(), "brown_stained_hardened_clay");
        this.a(afi.cu, zd.j.a(), "cyan_stained_hardened_clay");
        this.a(afi.cu, zd.h.a(), "gray_stained_hardened_clay");
        this.a(afi.cu, zd.n.a(), "green_stained_hardened_clay");
        this.a(afi.cu, zd.d.a(), "light_blue_stained_hardened_clay");
        this.a(afi.cu, zd.f.a(), "lime_stained_hardened_clay");
        this.a(afi.cu, zd.c.a(), "magenta_stained_hardened_clay");
        this.a(afi.cu, zd.b.a(), "orange_stained_hardened_clay");
        this.a(afi.cu, zd.g.a(), "pink_stained_hardened_clay");
        this.a(afi.cu, zd.k.a(), "purple_stained_hardened_clay");
        this.a(afi.cu, zd.o.a(), "red_stained_hardened_clay");
        this.a(afi.cu, zd.i.a(), "silver_stained_hardened_clay");
        this.a(afi.cu, zd.a.a(), "white_stained_hardened_clay");
        this.a(afi.cu, zd.e.a(), "yellow_stained_hardened_clay");
        this.a(afi.b, ajy.a.f.a(), "andesite");
        this.a(afi.b, ajy.a.g.a(), "andesite_smooth");
        this.a(afi.b, ajy.a.d.a(), "diorite");
        this.a(afi.b, ajy.a.e.a(), "diorite_smooth");
        this.a(afi.b, ajy.a.b.a(), "granite");
        this.a(afi.b, ajy.a.c.a(), "granite_smooth");
        this.a(afi.b, ajy.a.a.a(), "stone");
        this.a(afi.bf, ajz.a.c.a(), "cracked_stonebrick");
        this.a(afi.bf, ajz.a.a.a(), "stonebrick");
        this.a(afi.bf, ajz.a.d.a(), "chiseled_stonebrick");
        this.a(afi.bf, ajz.a.b.a(), "mossy_stonebrick");
        this.a(afi.U, akb.a.e.a(), "brick_slab");
        this.a(afi.U, akb.a.d.a(), "cobblestone_slab");
        this.a(afi.U, akb.a.c.a(), "old_wood_slab");
        this.a(afi.U, akb.a.g.a(), "nether_brick_slab");
        this.a(afi.U, akb.a.h.a(), "quartz_slab");
        this.a(afi.U, akb.a.b.a(), "sandstone_slab");
        this.a(afi.U, akb.a.f.a(), "stone_brick_slab");
        this.a(afi.U, akb.a.a.a(), "stone_slab");
        this.a(afi.cP, aih.a.a.a(), "red_sandstone_slab");
        this.a(afi.H, akc.a.a.a(), "dead_bush");
        this.a(afi.H, akc.a.c.a(), "fern");
        this.a(afi.H, akc.a.b.a(), "tall_grass");
        this.a(afi.bM, aio.a.e.a(), "acacia_slab");
        this.a(afi.bM, aio.a.c.a(), "birch_slab");
        this.a(afi.bM, aio.a.f.a(), "dark_oak_slab");
        this.a(afi.bM, aio.a.d.a(), "jungle_slab");
        this.a(afi.bM, aio.a.a.a(), "oak_slab");
        this.a(afi.bM, aio.a.b.a(), "spruce_slab");
        this.a(afi.L, zd.p.a(), "black_wool");
        this.a(afi.L, zd.l.a(), "blue_wool");
        this.a(afi.L, zd.m.a(), "brown_wool");
        this.a(afi.L, zd.j.a(), "cyan_wool");
        this.a(afi.L, zd.h.a(), "gray_wool");
        this.a(afi.L, zd.n.a(), "green_wool");
        this.a(afi.L, zd.d.a(), "light_blue_wool");
        this.a(afi.L, zd.f.a(), "lime_wool");
        this.a(afi.L, zd.c.a(), "magenta_wool");
        this.a(afi.L, zd.b.a(), "orange_wool");
        this.a(afi.L, zd.g.a(), "pink_wool");
        this.a(afi.L, zd.k.a(), "purple_wool");
        this.a(afi.L, zd.o.a(), "red_wool");
        this.a(afi.L, zd.i.a(), "silver_wool");
        this.a(afi.L, zd.a.a(), "white_wool");
        this.a(afi.L, zd.e.a(), "yellow_wool");
        this.a(afi.cC, "acacia_stairs");
        this.a(afi.cs, "activator_rail");
        this.a(afi.bY, "beacon");
        this.a(afi.h, "bedrock");
        this.a(afi.bV, "birch_stairs");
        this.a(afi.X, "bookshelf");
        this.a(afi.V, "brick_block");
        this.a(afi.V, "brick_block");
        this.a(afi.bu, "brick_stairs");
        this.a(afi.P, "brown_mushroom");
        this.a(afi.aK, "cactus");
        this.a(afi.aL, "clay");
        this.a(afi.cA, "coal_block");
        this.a(afi.q, "coal_ore");
        this.a(afi.e, "cobblestone");
        this.a(afi.ai, "crafting_table");
        this.a(afi.cD, "dark_oak_stairs");
        this.a(afi.cl, "daylight_detector");
        this.a(afi.I, "dead_bush");
        this.a(afi.E, "detector_rail");
        this.a(afi.ah, "diamond_block");
        this.a(afi.ag, "diamond_ore");
        this.a(afi.z, "dispenser");
        this.a(afi.ct, "dropper");
        this.a(afi.bT, "emerald_block");
        this.a(afi.bP, "emerald_ore");
        this.a(afi.bC, "enchanting_table");
        this.a(afi.bG, "end_portal_frame");
        this.a(afi.bH, "end_stone");
        this.a(afi.aO, "oak_fence");
        this.a(afi.aP, "spruce_fence");
        this.a(afi.aQ, "birch_fence");
        this.a(afi.aR, "jungle_fence");
        this.a(afi.aS, "dark_oak_fence");
        this.a(afi.aT, "acacia_fence");
        this.a(afi.bo, "oak_fence_gate");
        this.a(afi.bp, "spruce_fence_gate");
        this.a(afi.bq, "birch_fence_gate");
        this.a(afi.br, "jungle_fence_gate");
        this.a(afi.bs, "dark_oak_fence_gate");
        this.a(afi.bt, "acacia_fence_gate");
        this.a(afi.al, "furnace");
        this.a(afi.w, "glass");
        this.a(afi.bj, "glass_pane");
        this.a(afi.aX, "glowstone");
        this.a(afi.D, "golden_rail");
        this.a(afi.R, "gold_block");
        this.a(afi.o, "gold_ore");
        this.a(afi.c, "grass");
        this.a(afi.n, "gravel");
        this.a(afi.cz, "hardened_clay");
        this.a(afi.cx, "hay_block");
        this.a(afi.ci, "heavy_weighted_pressure_plate");
        this.a(afi.cp, "hopper");
        this.a(afi.aI, "ice");
        this.a(afi.bi, "iron_bars");
        this.a(afi.S, "iron_block");
        this.a(afi.p, "iron_ore");
        this.a(afi.cw, "iron_trapdoor");
        this.a(afi.aN, "jukebox");
        this.a(afi.bW, "jungle_stairs");
        this.a(afi.au, "ladder");
        this.a(afi.y, "lapis_block");
        this.a(afi.x, "lapis_ore");
        this.a(afi.ay, "lever");
        this.a(afi.ch, "light_weighted_pressure_plate");
        this.a(afi.aZ, "lit_pumpkin");
        this.a(afi.bk, "melon_block");
        this.a(afi.Y, "mossy_cobblestone");
        this.a(afi.bw, "mycelium");
        this.a(afi.aV, "netherrack");
        this.a(afi.by, "nether_brick");
        this.a(afi.bz, "nether_brick_fence");
        this.a(afi.bA, "nether_brick_stairs");
        this.a(afi.B, "noteblock");
        this.a(afi.ad, "oak_stairs");
        this.a(afi.Z, "obsidian");
        this.a(afi.cB, "packed_ice");
        this.a(afi.J, "piston");
        this.a(afi.aU, "pumpkin");
        this.a(afi.co, "quartz_ore");
        this.a(afi.cr, "quartz_stairs");
        this.a(afi.av, "rail");
        this.a(afi.cn, "redstone_block");
        this.a(afi.bJ, "redstone_lamp");
        this.a(afi.aC, "redstone_ore");
        this.a(afi.aF, "redstone_torch");
        this.a(afi.Q, "red_mushroom");
        this.a(afi.bO, "sandstone_stairs");
        this.a(afi.cN, "red_sandstone_stairs");
        this.a(afi.cJ, "sea_lantern");
        this.a(afi.cE, "slime");
        this.a(afi.aJ, "snow");
        this.a(afi.aH, "snow_layer");
        this.a(afi.aW, "soul_sand");
        this.a(afi.bU, "spruce_stairs");
        this.a(afi.F, "sticky_piston");
        this.a(afi.bv, "stone_brick_stairs");
        this.a(afi.aG, "stone_button");
        this.a(afi.az, "stone_pressure_plate");
        this.a(afi.aw, "stone_stairs");
        this.a(afi.W, "tnt");
        this.a(afi.aa, "torch");
        this.a(afi.bd, "trapdoor");
        this.a(afi.bR, "tripwire_hook");
        this.a(afi.bn, "vine");
        this.a(afi.bx, "waterlily");
        this.a(afi.G, "web");
        this.a(afi.cd, "wooden_button");
        this.a(afi.aB, "wooden_pressure_plate");
        this.a(afi.N, agw.a.a.b(), "dandelion");
        this.a(afi.ae, "chest");
        this.a(afi.cg, "trapped_chest");
        this.a(afi.bQ, "ender_chest");
        this.a(zy.a, "iron_shovel");
        this.a(zy.b, "iron_pickaxe");
        this.a(zy.c, "iron_axe");
        this.a(zy.d, "flint_and_steel");
        this.a(zy.e, "apple");
        this.a(zy.f, 0, "bow");
        this.a(zy.f, 1, "bow_pulling_0");
        this.a(zy.f, 2, "bow_pulling_1");
        this.a(zy.f, 3, "bow_pulling_2");
        this.a(zy.g, "arrow");
        this.a(zy.h, 0, "coal");
        this.a(zy.h, 1, "charcoal");
        this.a(zy.i, "diamond");
        this.a(zy.j, "iron_ingot");
        this.a(zy.k, "gold_ingot");
        this.a(zy.l, "iron_sword");
        this.a(zy.m, "wooden_sword");
        this.a(zy.n, "wooden_shovel");
        this.a(zy.o, "wooden_pickaxe");
        this.a(zy.p, "wooden_axe");
        this.a(zy.q, "stone_sword");
        this.a(zy.r, "stone_shovel");
        this.a(zy.s, "stone_pickaxe");
        this.a(zy.t, "stone_axe");
        this.a(zy.u, "diamond_sword");
        this.a(zy.v, "diamond_shovel");
        this.a(zy.w, "diamond_pickaxe");
        this.a(zy.x, "diamond_axe");
        this.a(zy.y, "stick");
        this.a(zy.z, "bowl");
        this.a(zy.A, "mushroom_stew");
        this.a(zy.B, "golden_sword");
        this.a(zy.C, "golden_shovel");
        this.a(zy.D, "golden_pickaxe");
        this.a(zy.E, "golden_axe");
        this.a(zy.F, "string");
        this.a(zy.G, "feather");
        this.a(zy.H, "gunpowder");
        this.a(zy.I, "wooden_hoe");
        this.a(zy.J, "stone_hoe");
        this.a(zy.K, "iron_hoe");
        this.a(zy.L, "diamond_hoe");
        this.a(zy.M, "golden_hoe");
        this.a(zy.N, "wheat_seeds");
        this.a(zy.O, "wheat");
        this.a(zy.P, "bread");
        this.a(zy.Q, "leather_helmet");
        this.a(zy.R, "leather_chestplate");
        this.a(zy.S, "leather_leggings");
        this.a(zy.T, "leather_boots");
        this.a(zy.U, "chainmail_helmet");
        this.a(zy.V, "chainmail_chestplate");
        this.a(zy.W, "chainmail_leggings");
        this.a(zy.X, "chainmail_boots");
        this.a(zy.Y, "iron_helmet");
        this.a(zy.Z, "iron_chestplate");
        this.a(zy.aa, "iron_leggings");
        this.a(zy.ab, "iron_boots");
        this.a(zy.ac, "diamond_helmet");
        this.a(zy.ad, "diamond_chestplate");
        this.a(zy.ae, "diamond_leggings");
        this.a(zy.af, "diamond_boots");
        this.a(zy.ag, "golden_helmet");
        this.a(zy.ah, "golden_chestplate");
        this.a(zy.ai, "golden_leggings");
        this.a(zy.aj, "golden_boots");
        this.a(zy.ak, "flint");
        this.a(zy.al, "porkchop");
        this.a(zy.am, "cooked_porkchop");
        this.a(zy.an, "painting");
        this.a(zy.ao, "golden_apple");
        this.a(zy.ao, 1, "golden_apple");
        this.a(zy.ap, "sign");
        this.a(zy.aq, "oak_door");
        this.a(zy.ar, "spruce_door");
        this.a(zy.as, "birch_door");
        this.a(zy.at, "jungle_door");
        this.a(zy.au, "acacia_door");
        this.a(zy.av, "dark_oak_door");
        this.a(zy.aw, "bucket");
        this.a(zy.ax, "water_bucket");
        this.a(zy.ay, "lava_bucket");
        this.a(zy.az, "minecart");
        this.a(zy.aA, "saddle");
        this.a(zy.aB, "iron_door");
        this.a(zy.aC, "redstone");
        this.a(zy.aD, "snowball");
        this.a(zy.aE, "boat");
        this.a(zy.aF, "leather");
        this.a(zy.aG, "milk_bucket");
        this.a(zy.aH, "brick");
        this.a(zy.aI, "clay_ball");
        this.a(zy.aJ, "reeds");
        this.a(zy.aK, "paper");
        this.a(zy.aL, "book");
        this.a(zy.aM, "slime_ball");
        this.a(zy.aN, "chest_minecart");
        this.a(zy.aO, "furnace_minecart");
        this.a(zy.aP, "egg");
        this.a(zy.aQ, "compass");
        this.a(zy.aR, "fishing_rod");
        this.a(zy.aR, 1, "fishing_rod_cast");
        this.a(zy.aS, "clock");
        this.a(zy.aT, "glowstone_dust");
        this.a(zy.aU, zp.a.a.a(), "cod");
        this.a(zy.aU, zp.a.b.a(), "salmon");
        this.a(zy.aU, zp.a.c.a(), "clownfish");
        this.a(zy.aU, zp.a.d.a(), "pufferfish");
        this.a(zy.aV, zp.a.a.a(), "cooked_cod");
        this.a(zy.aV, zp.a.b.a(), "cooked_salmon");
        this.a(zy.aW, zd.p.b(), "dye_black");
        this.a(zy.aW, zd.o.b(), "dye_red");
        this.a(zy.aW, zd.n.b(), "dye_green");
        this.a(zy.aW, zd.m.b(), "dye_brown");
        this.a(zy.aW, zd.l.b(), "dye_blue");
        this.a(zy.aW, zd.k.b(), "dye_purple");
        this.a(zy.aW, zd.j.b(), "dye_cyan");
        this.a(zy.aW, zd.i.b(), "dye_silver");
        this.a(zy.aW, zd.h.b(), "dye_gray");
        this.a(zy.aW, zd.g.b(), "dye_pink");
        this.a(zy.aW, zd.f.b(), "dye_lime");
        this.a(zy.aW, zd.e.b(), "dye_yellow");
        this.a(zy.aW, zd.d.b(), "dye_light_blue");
        this.a(zy.aW, zd.c.b(), "dye_magenta");
        this.a(zy.aW, zd.b.b(), "dye_orange");
        this.a(zy.aW, zd.a.b(), "dye_white");
        this.a(zy.aX, "bone");
        this.a(zy.aY, "sugar");
        this.a(zy.aZ, "cake");
        this.a(zy.ba, "bed");
        this.a(zy.bb, "repeater");
        this.a(zy.bc, "cookie");
        this.a(zy.be, "shears");
        this.a(zy.bf, "melon");
        this.a(zy.bg, "pumpkin_seeds");
        this.a(zy.bh, "melon_seeds");
        this.a(zy.bi, "beef");
        this.a(zy.bj, "cooked_beef");
        this.a(zy.bk, "chicken");
        this.a(zy.bl, "cooked_chicken");
        this.a(zy.bo, "rabbit");
        this.a(zy.bp, "cooked_rabbit");
        this.a(zy.bm, "mutton");
        this.a(zy.bn, "cooked_mutton");
        this.a(zy.br, "rabbit_foot");
        this.a(zy.bs, "rabbit_hide");
        this.a(zy.bq, "rabbit_stew");
        this.a(zy.bt, "rotten_flesh");
        this.a(zy.bu, "ender_pearl");
        this.a(zy.bv, "blaze_rod");
        this.a(zy.bw, "ghast_tear");
        this.a(zy.bx, "gold_nugget");
        this.a(zy.by, "nether_wart");
        this.d.a((zw)zy.bz, new bfp(){

            @Override
            public bov a(zx zx2) {
                if (aai.f(zx2.i())) {
                    return new bov("bottle_splash", "inventory");
                }
                return new bov("bottle_drinkable", "inventory");
            }
        });
        this.a(zy.bA, "glass_bottle");
        this.a(zy.bB, "spider_eye");
        this.a(zy.bC, "fermented_spider_eye");
        this.a(zy.bD, "blaze_powder");
        this.a(zy.bE, "magma_cream");
        this.a(zy.bF, "brewing_stand");
        this.a(zy.bG, "cauldron");
        this.a(zy.bH, "ender_eye");
        this.a(zy.bI, "speckled_melon");
        this.d.a(zy.bJ, new bfp(){

            @Override
            public bov a(zx zx2) {
                return new bov("spawn_egg", "inventory");
            }
        });
        this.a(zy.bK, "experience_bottle");
        this.a(zy.bL, "fire_charge");
        this.a(zy.bM, "writable_book");
        this.a(zy.bO, "emerald");
        this.a(zy.bP, "item_frame");
        this.a(zy.bQ, "flower_pot");
        this.a(zy.bR, "carrot");
        this.a(zy.bS, "potato");
        this.a(zy.bT, "baked_potato");
        this.a(zy.bU, "poisonous_potato");
        this.a(zy.bV, "map");
        this.a(zy.bW, "golden_carrot");
        this.a(zy.bX, 0, "skull_skeleton");
        this.a(zy.bX, 1, "skull_wither");
        this.a(zy.bX, 2, "skull_zombie");
        this.a(zy.bX, 3, "skull_char");
        this.a(zy.bX, 4, "skull_creeper");
        this.a(zy.bY, "carrot_on_a_stick");
        this.a(zy.bZ, "nether_star");
        this.a(zy.ca, "pumpkin_pie");
        this.a(zy.cc, "firework_charge");
        this.a(zy.ce, "comparator");
        this.a(zy.cf, "netherbrick");
        this.a(zy.cg, "quartz");
        this.a(zy.ch, "tnt_minecart");
        this.a(zy.ci, "hopper_minecart");
        this.a(zy.cj, "armor_stand");
        this.a(zy.ck, "iron_horse_armor");
        this.a(zy.cl, "golden_horse_armor");
        this.a(zy.cm, "diamond_horse_armor");
        this.a(zy.cn, "lead");
        this.a(zy.co, "name_tag");
        this.d.a(zy.cE, new bfp(){

            @Override
            public bov a(zx zx2) {
                return new bov("banner", "inventory");
            }
        });
        this.a(zy.cq, "record_13");
        this.a(zy.cr, "record_cat");
        this.a(zy.cs, "record_blocks");
        this.a(zy.ct, "record_chirp");
        this.a(zy.cu, "record_far");
        this.a(zy.cv, "record_mall");
        this.a(zy.cw, "record_mellohi");
        this.a(zy.cx, "record_stal");
        this.a(zy.cy, "record_strad");
        this.a(zy.cz, "record_ward");
        this.a(zy.cA, "record_11");
        this.a(zy.cB, "record_wait");
        this.a(zy.cC, "prismarine_shard");
        this.a(zy.cD, "prismarine_crystals");
        this.d.a((zw)zy.cd, new bfp(){

            @Override
            public bov a(zx zx2) {
                return new bov("enchanted_book", "inventory");
            }
        });
        this.d.a((zw)zy.bd, new bfp(){

            @Override
            public bov a(zx zx2) {
                return new bov("filled_map", "inventory");
            }
        });
        this.a(afi.bX, "command_block");
        this.a(zy.cb, "fireworks");
        this.a(zy.cp, "command_block_minecart");
        this.a(afi.cv, "barrier");
        this.a(afi.ac, "mob_spawner");
        this.a(zy.bN, "written_book");
        this.a(afi.bg, aho.a.k.a(), "brown_mushroom_block");
        this.a(afi.bh, aho.a.k.a(), "red_mushroom_block");
        this.a(afi.bI, "dragon_egg");
    }

    @Override
    public void a(bni bni2) {
        this.d.b();
    }
}

