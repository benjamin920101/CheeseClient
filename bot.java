/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bot {
    private static final Set<jy> b = Sets.newHashSet(new jy("blocks/water_flow"), new jy("blocks/water_still"), new jy("blocks/lava_flow"), new jy("blocks/lava_still"), new jy("blocks/destroy_stage_0"), new jy("blocks/destroy_stage_1"), new jy("blocks/destroy_stage_2"), new jy("blocks/destroy_stage_3"), new jy("blocks/destroy_stage_4"), new jy("blocks/destroy_stage_5"), new jy("blocks/destroy_stage_6"), new jy("blocks/destroy_stage_7"), new jy("blocks/destroy_stage_8"), new jy("blocks/destroy_stage_9"), new jy("items/empty_armor_slot_helmet"), new jy("items/empty_armor_slot_chestplate"), new jy("items/empty_armor_slot_leggings"), new jy("items/empty_armor_slot_boots"));
    private static final Logger c = LogManager.getLogger();
    protected static final bov a = new bov("builtin/missing", "missing");
    private static final Map<String, String> d = Maps.newHashMap();
    private static final Joiner e;
    private final bni f;
    private final Map<jy, bmi> g = Maps.newHashMap();
    private final Map<jy, bgl> h = Maps.newLinkedHashMap();
    private final Map<bov, bgm.d> i = Maps.newLinkedHashMap();
    private final bmh j;
    private final bgc k;
    private final bgo l = new bgo();
    private final bgp m = new bgp();
    private dd<bov, boq> n = new dd();
    private static final bgl o;
    private static final bgl p;
    private static final bgl q;
    private static final bgl r;
    private Map<String, jy> s = Maps.newLinkedHashMap();
    private final Map<jy, bgm> t = Maps.newHashMap();
    private Map<zw, List<String>> u = Maps.newIdentityHashMap();

    public bot(bni bni2, bmh bmh2, bgc bgc2) {
        this.f = bni2;
        this.j = bmh2;
        this.k = bgc2;
    }

    public db<bov, boq> a() {
        this.b();
        this.h();
        this.j();
        this.l();
        this.f();
        return this.n;
    }

    private void b() {
        this.a(this.k.a().a().values());
        this.i.put(a, new bgm.d(a.c(), Lists.newArrayList(new bgm.c(new jy(a.a()), bor.a, false, 1))));
        jy jy2 = new jy("item_frame");
        bgm \u26032 = this.a(jy2);
        this.a(\u26032, new bov(jy2, "normal"));
        this.a(\u26032, new bov(jy2, "map"));
        this.c();
        this.d();
    }

    private void a(Collection<bov> collection) {
        for (bov bov2 : collection) {
            try {
                bgm bgm2 = this.a(bov2);
                try {
                    this.a(bgm2, bov2);
                }
                catch (Exception \u26032) {
                    c.warn("Unable to load variant: " + bov2.c() + " from " + bov2);
                }
            }
            catch (Exception exception) {
                c.warn("Unable to load definition " + bov2, (Throwable)exception);
            }
        }
    }

    private void a(bgm bgm2, bov bov2) {
        this.i.put(bov2, bgm2.b(bov2.c()));
    }

    private bgm a(jy jy2) {
        \u2603 = this.b(jy2);
        bgm bgm2 = this.t.get(\u2603);
        if (bgm2 == null) {
            ArrayList<bgm> arrayList = Lists.newArrayList();
            try {
                for (bnh bnh2 : this.f.b(\u2603)) {
                    InputStream inputStream = null;
                    try {
                        inputStream = bnh2.b();
                        bgm bgm3 = bgm.a(new InputStreamReader(inputStream, Charsets.UTF_8));
                        arrayList.add(bgm3);
                    }
                    catch (Exception exception) {
                        throw new RuntimeException("Encountered an exception when loading model definition of '" + jy2 + "' from: '" + bnh2.a() + "' in resourcepack: '" + bnh2.d() + "'", exception);
                    }
                    finally {
                        IOUtils.closeQuietly(inputStream);
                    }
                }
            }
            catch (IOException iOException) {
                throw new RuntimeException("Encountered an exception when loading model definition of model " + \u2603.toString(), iOException);
            }
            bgm2 = new bgm((List<bgm>)arrayList);
            this.t.put(\u2603, bgm2);
        }
        return bgm2;
    }

    private jy b(jy jy2) {
        return new jy(jy2.b(), "blockstates/" + jy2.a() + ".json");
    }

    private void c() {
        for (bov bov2 : this.i.keySet()) {
            for (bgm.c c2 : this.i.get(bov2).b()) {
                jy jy2 = c2.a();
                if (this.h.get(jy2) != null) continue;
                try {
                    bgl bgl2 = this.c(jy2);
                    this.h.put(jy2, bgl2);
                }
                catch (Exception exception) {
                    c.warn("Unable to load block model: '" + jy2 + "' for variant: '" + bov2 + "'", (Throwable)exception);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private bgl c(jy jy2) throws IOException {
        Reader \u26033;
        String string = jy2.a();
        if ("builtin/generated".equals(string)) {
            return o;
        }
        if ("builtin/compass".equals(string)) {
            return p;
        }
        if ("builtin/clock".equals(string)) {
            return q;
        }
        if ("builtin/entity".equals(string)) {
            return r;
        }
        if (string.startsWith("builtin/")) {
            Object object = string.substring("builtin/".length());
            String \u26032 = d.get(object);
            if (\u26032 == null) {
                throw new FileNotFoundException(jy2.toString());
            }
            \u26033 = new StringReader(\u26032);
        } else {
            object = this.f.a(this.d(jy2));
            \u26033 = new InputStreamReader(object.b(), Charsets.UTF_8);
        }
        try {
            object = bgl.a(\u26033);
            ((bgl)object).b = jy2.toString();
            Object object = object;
            return object;
        }
        finally {
            \u26033.close();
        }
    }

    private jy d(jy jy2) {
        return new jy(jy2.b(), "models/" + jy2.a() + ".json");
    }

    private void d() {
        this.e();
        for (zw zw2 : zw.e) {
            List<String> list = this.a(zw2);
            for (String string : list) {
                jy jy2 = this.a(string);
                this.s.put(string, jy2);
                if (this.h.get(jy2) != null) continue;
                try {
                    bgl bgl2 = this.c(jy2);
                    this.h.put(jy2, bgl2);
                }
                catch (Exception exception) {
                    c.warn("Unable to load item model: '" + jy2 + "' for item: '" + zw.e.c(zw2) + "'", (Throwable)exception);
                }
            }
        }
    }

    private void e() {
        this.u.put(zw.a(afi.b), Lists.newArrayList("stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth"));
        this.u.put(zw.a(afi.d), Lists.newArrayList("dirt", "coarse_dirt", "podzol"));
        this.u.put(zw.a(afi.f), Lists.newArrayList("oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks"));
        this.u.put(zw.a(afi.g), Lists.newArrayList("oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling"));
        this.u.put(zw.a(afi.m), Lists.newArrayList("sand", "red_sand"));
        this.u.put(zw.a(afi.r), Lists.newArrayList("oak_log", "spruce_log", "birch_log", "jungle_log"));
        this.u.put(zw.a(afi.t), Lists.newArrayList("oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves"));
        this.u.put(zw.a(afi.v), Lists.newArrayList("sponge", "sponge_wet"));
        this.u.put(zw.a(afi.A), Lists.newArrayList("sandstone", "chiseled_sandstone", "smooth_sandstone"));
        this.u.put(zw.a(afi.cM), Lists.newArrayList("red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"));
        this.u.put(zw.a(afi.H), Lists.newArrayList("dead_bush", "tall_grass", "fern"));
        this.u.put(zw.a(afi.I), Lists.newArrayList("dead_bush"));
        this.u.put(zw.a(afi.L), Lists.newArrayList("black_wool", "red_wool", "green_wool", "brown_wool", "blue_wool", "purple_wool", "cyan_wool", "silver_wool", "gray_wool", "pink_wool", "lime_wool", "yellow_wool", "light_blue_wool", "magenta_wool", "orange_wool", "white_wool"));
        this.u.put(zw.a(afi.N), Lists.newArrayList("dandelion"));
        this.u.put(zw.a(afi.O), Lists.newArrayList("poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy"));
        this.u.put(zw.a(afi.U), Lists.newArrayList("stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"));
        this.u.put(zw.a(afi.cP), Lists.newArrayList("red_sandstone_slab"));
        this.u.put(zw.a(afi.cG), Lists.newArrayList("black_stained_glass", "red_stained_glass", "green_stained_glass", "brown_stained_glass", "blue_stained_glass", "purple_stained_glass", "cyan_stained_glass", "silver_stained_glass", "gray_stained_glass", "pink_stained_glass", "lime_stained_glass", "yellow_stained_glass", "light_blue_stained_glass", "magenta_stained_glass", "orange_stained_glass", "white_stained_glass"));
        this.u.put(zw.a(afi.be), Lists.newArrayList("stone_monster_egg", "cobblestone_monster_egg", "stone_brick_monster_egg", "mossy_brick_monster_egg", "cracked_brick_monster_egg", "chiseled_brick_monster_egg"));
        this.u.put(zw.a(afi.bf), Lists.newArrayList("stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick"));
        this.u.put(zw.a(afi.bM), Lists.newArrayList("oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab"));
        this.u.put(zw.a(afi.bZ), Lists.newArrayList("cobblestone_wall", "mossy_cobblestone_wall"));
        this.u.put(zw.a(afi.cf), Lists.newArrayList("anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged"));
        this.u.put(zw.a(afi.cq), Lists.newArrayList("quartz_block", "chiseled_quartz_block", "quartz_column"));
        this.u.put(zw.a(afi.cu), Lists.newArrayList("black_stained_hardened_clay", "red_stained_hardened_clay", "green_stained_hardened_clay", "brown_stained_hardened_clay", "blue_stained_hardened_clay", "purple_stained_hardened_clay", "cyan_stained_hardened_clay", "silver_stained_hardened_clay", "gray_stained_hardened_clay", "pink_stained_hardened_clay", "lime_stained_hardened_clay", "yellow_stained_hardened_clay", "light_blue_stained_hardened_clay", "magenta_stained_hardened_clay", "orange_stained_hardened_clay", "white_stained_hardened_clay"));
        this.u.put(zw.a(afi.cH), Lists.newArrayList("black_stained_glass_pane", "red_stained_glass_pane", "green_stained_glass_pane", "brown_stained_glass_pane", "blue_stained_glass_pane", "purple_stained_glass_pane", "cyan_stained_glass_pane", "silver_stained_glass_pane", "gray_stained_glass_pane", "pink_stained_glass_pane", "lime_stained_glass_pane", "yellow_stained_glass_pane", "light_blue_stained_glass_pane", "magenta_stained_glass_pane", "orange_stained_glass_pane", "white_stained_glass_pane"));
        this.u.put(zw.a(afi.u), Lists.newArrayList("acacia_leaves", "dark_oak_leaves"));
        this.u.put(zw.a(afi.s), Lists.newArrayList("acacia_log", "dark_oak_log"));
        this.u.put(zw.a(afi.cI), Lists.newArrayList("prismarine", "prismarine_bricks", "dark_prismarine"));
        this.u.put(zw.a(afi.cy), Lists.newArrayList("black_carpet", "red_carpet", "green_carpet", "brown_carpet", "blue_carpet", "purple_carpet", "cyan_carpet", "silver_carpet", "gray_carpet", "pink_carpet", "lime_carpet", "yellow_carpet", "light_blue_carpet", "magenta_carpet", "orange_carpet", "white_carpet"));
        this.u.put(zw.a(afi.cF), Lists.newArrayList("sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia"));
        this.u.put(zy.f, Lists.newArrayList("bow", "bow_pulling_0", "bow_pulling_1", "bow_pulling_2"));
        this.u.put(zy.h, Lists.newArrayList("coal", "charcoal"));
        this.u.put(zy.aR, Lists.newArrayList("fishing_rod", "fishing_rod_cast"));
        this.u.put(zy.aU, Lists.newArrayList("cod", "salmon", "clownfish", "pufferfish"));
        this.u.put(zy.aV, Lists.newArrayList("cooked_cod", "cooked_salmon"));
        this.u.put(zy.aW, Lists.newArrayList("dye_black", "dye_red", "dye_green", "dye_brown", "dye_blue", "dye_purple", "dye_cyan", "dye_silver", "dye_gray", "dye_pink", "dye_lime", "dye_yellow", "dye_light_blue", "dye_magenta", "dye_orange", "dye_white"));
        this.u.put(zy.bz, Lists.newArrayList("bottle_drinkable", "bottle_splash"));
        this.u.put(zy.bX, Lists.newArrayList("skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper"));
        this.u.put(zw.a(afi.bo), Lists.newArrayList("oak_fence_gate"));
        this.u.put(zw.a(afi.aO), Lists.newArrayList("oak_fence"));
        this.u.put(zy.aq, Lists.newArrayList("oak_door"));
    }

    private List<String> a(zw zw2) {
        List<String> list = this.u.get(zw2);
        if (list == null) {
            list = Collections.singletonList(zw.e.c(zw2).toString());
        }
        return list;
    }

    private jy a(String string) {
        jy jy2 = new jy(string);
        return new jy(jy2.b(), "item/" + jy2.a());
    }

    private void f() {
        Object object;
        for (bov bov2 : this.i.keySet()) {
            object = new box.a();
            int n2 = 0;
            for (bgm.c c2 : this.i.get(bov2).b()) {
                bgl bgl2 = this.h.get(c2.a());
                if (bgl2 == null || !bgl2.d()) {
                    c.warn("Missing model for: " + bov2);
                    continue;
                }
                ++n2;
                ((box.a)object).a(this.a(bgl2, c2.b(), c2.c()), c2.d());
            }
            if (n2 == 0) {
                c.warn("No weighted models for: " + bov2);
                continue;
            }
            if (n2 == 1) {
                this.n.a(bov2, ((box.a)object).b());
                continue;
            }
            this.n.a(bov2, ((box.a)object).a());
        }
        for (Map.Entry entry : this.s.entrySet()) {
            object = (jy)entry.getValue();
            bov bov2 = new bov((String)entry.getKey(), "inventory");
            bgl \u26033 = this.h.get(object);
            if (\u26033 == null || !\u26033.d()) {
                c.warn("Missing model for: " + object);
                continue;
            }
            if (this.c(\u26033)) {
                this.n.a(bov2, new bos(\u26033.g()));
                continue;
            }
            this.n.a(bov2, this.a(\u26033, bor.a, false));
        }
    }

    private Set<jy> g() {
        HashSet<jy> hashSet = Sets.newHashSet();
        ArrayList<bov> \u26032 = Lists.newArrayList(this.i.keySet());
        Collections.sort(\u26032, new Comparator<bov>(){

            public int a(bov bov2, bov bov3) {
                return bov2.toString().compareTo(bov3.toString());
            }

            @Override
            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((bov)object, (bov)object2);
            }
        });
        for (bov bov2 : \u26032) {
            bgm.d d2 = this.i.get(bov2);
            for (bgm.c c2 : d2.b()) {
                bgl bgl2 = this.h.get(c2.a());
                if (bgl2 == null) {
                    c.warn("Missing model for: " + bov2);
                    continue;
                }
                hashSet.addAll(this.a(bgl2));
            }
        }
        hashSet.addAll(b);
        return hashSet;
    }

    private boq a(bgl bgl2, bor bor2, boolean bl2) {
        bmi bmi2 = this.g.get(new jy(bgl2.c("particle")));
        bow.a \u26032 = new bow.a(bgl2).a(bmi2);
        for (bgh bgh2 : bgl2.a()) {
            for (cq cq2 : bgh2.c.keySet()) {
                bgi bgi2 = bgh2.c.get(cq2);
                bmi \u26033 = this.g.get(new jy(bgl2.c(bgi2.d)));
                if (bgi2.b == null) {
                    \u26032.a(this.a(bgh2, bgi2, \u26033, cq2, bor2, bl2));
                    continue;
                }
                \u26032.a(bor2.a(bgi2.b), this.a(bgh2, bgi2, \u26033, cq2, bor2, bl2));
            }
        }
        return \u26032.b();
    }

    private bgg a(bgh bgh2, bgi bgi2, bmi bmi2, cq cq2, bor bor2, boolean bl2) {
        return this.l.a(bgh2.a, bgh2.b, bgi2, bmi2, cq2, bor2, bgh2.d, bl2, bgh2.e);
    }

    private void h() {
        this.i();
        for (bgl bgl2 : this.h.values()) {
            bgl2.a(this.h);
        }
        bgl.b(this.h);
    }

    private void i() {
        ArrayDeque<jy> arrayDeque = Queues.newArrayDeque();
        HashSet<jy> \u26032 = Sets.newHashSet();
        for (jy jy2 : this.h.keySet()) {
            \u26032.add(jy2);
            jy \u26033 = this.h.get(jy2).e();
            if (\u26033 == null) continue;
            arrayDeque.add(\u26033);
        }
        while (!arrayDeque.isEmpty()) {
            jy jy3 = (jy)arrayDeque.pop();
            try {
                if (this.h.get(jy3) != null) continue;
                bgl bgl2 = this.c(jy3);
                this.h.put(jy3, bgl2);
                \u2603 = bgl2.e();
                if (\u2603 != null && !\u26032.contains(\u2603)) {
                    arrayDeque.add(\u2603);
                }
            }
            catch (Exception exception) {
                c.warn("In parent chain: " + e.join(this.e(jy3)) + "; unable to load model: '" + jy3 + "'", (Throwable)exception);
            }
            \u26032.add(jy3);
        }
    }

    private List<jy> e(jy jy2) {
        ArrayList<jy> arrayList = Lists.newArrayList(jy2);
        jy \u26032 = jy2;
        while ((\u26032 = this.f(\u26032)) != null) {
            arrayList.add(0, \u26032);
        }
        return arrayList;
    }

    private jy f(jy jy2) {
        for (Map.Entry<jy, bgl> entry : this.h.entrySet()) {
            bgl bgl2 = entry.getValue();
            if (bgl2 == null || !jy2.equals(bgl2.e())) continue;
            return entry.getKey();
        }
        return null;
    }

    private Set<jy> a(bgl bgl2) {
        HashSet<jy> hashSet = Sets.newHashSet();
        for (bgh bgh2 : bgl2.a()) {
            for (bgi bgi2 : bgh2.c.values()) {
                jy jy2 = new jy(bgl2.c(bgi2.d));
                hashSet.add(jy2);
            }
        }
        hashSet.add(new jy(bgl2.c("particle")));
        return hashSet;
    }

    private void j() {
        final Set<jy> set = this.g();
        set.addAll(this.k());
        set.remove(bmh.f);
        bmb \u26032 = new bmb(){

            @Override
            public void a(bmh bmh2) {
                for (jy jy2 : set) {
                    bmi bmi2 = bmh2.a(jy2);
                    bot.this.g.put(jy2, bmi2);
                }
            }
        };
        this.j.a(this.f, \u26032);
        this.g.put(new jy("missingno"), this.j.f());
    }

    private Set<jy> k() {
        HashSet<jy> hashSet = Sets.newHashSet();
        for (jy jy2 : this.s.values()) {
            bgl bgl2 = this.h.get(jy2);
            if (bgl2 == null) continue;
            hashSet.add(new jy(bgl2.c("particle")));
            if (this.b(bgl2)) {
                for (String string : bgp.a) {
                    jy jy3 = new jy(bgl2.c(string));
                    if (bgl2.f() == p && !bmh.f.equals(jy3)) {
                        bmi.b(jy3.toString());
                    } else if (bgl2.f() == q && !bmh.f.equals(jy3)) {
                        bmi.a(jy3.toString());
                    }
                    hashSet.add(jy3);
                }
                continue;
            }
            if (this.c(bgl2)) continue;
            for (bgh bgh2 : bgl2.a()) {
                for (bgi bgi2 : bgh2.c.values()) {
                    jy jy4 = new jy(bgl2.c(bgi2.d));
                    hashSet.add(jy4);
                }
            }
        }
        return hashSet;
    }

    private boolean b(bgl bgl2) {
        if (bgl2 == null) {
            return false;
        }
        \u2603 = bgl2.f();
        return \u2603 == o || \u2603 == p || \u2603 == q;
    }

    private boolean c(bgl bgl2) {
        if (bgl2 == null) {
            return false;
        }
        \u2603 = bgl2.f();
        return \u2603 == r;
    }

    private void l() {
        for (jy jy2 : this.s.values()) {
            bgl bgl2 = this.h.get(jy2);
            if (this.b(bgl2)) {
                bgl bgl3 = this.d(bgl2);
                if (bgl3 != null) {
                    bgl3.b = jy2.toString();
                }
                this.h.put(jy2, bgl3);
                continue;
            }
            if (!this.c(bgl2)) continue;
            this.h.put(jy2, bgl2);
        }
        for (bmi bmi2 : this.g.values()) {
            if (bmi2.m()) continue;
            bmi2.l();
        }
    }

    private bgl d(bgl bgl2) {
        return this.m.a(this.j, bgl2);
    }

    static {
        d.put("missing", "{ \"textures\": {   \"particle\": \"missingno\",   \"missingno\": \"missingno\"}, \"elements\": [ {     \"from\": [ 0, 0, 0 ],     \"to\": [ 16, 16, 16 ],     \"faces\": {         \"down\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"down\", \"texture\": \"#missingno\" },         \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"up\", \"texture\": \"#missingno\" },         \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"north\", \"texture\": \"#missingno\" },         \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"south\", \"texture\": \"#missingno\" },         \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"west\", \"texture\": \"#missingno\" },         \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"cullface\": \"east\", \"texture\": \"#missingno\" }    }}]}");
        e = Joiner.on(" -> ");
        o = bgl.a("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
        p = bgl.a("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
        q = bgl.a("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
        r = bgl.a("{\"elements\":[{  \"from\": [0, 0, 0],   \"to\": [16, 16, 16],   \"faces\": {       \"down\": {\"uv\": [0, 0, 16, 16], \"texture\":\"\"}   }}]}");
        bot.o.b = "generation marker";
        bot.p.b = "compass generation marker";
        bot.q.b = "class generation marker";
        bot.r.b = "block entity marker";
    }
}

