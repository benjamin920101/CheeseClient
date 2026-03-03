/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class pm {
    private static final Logger b = LogManager.getLogger();
    private static final Map<String, Class<? extends pk>> c = Maps.newHashMap();
    private static final Map<Class<? extends pk>, String> d = Maps.newHashMap();
    private static final Map<Integer, Class<? extends pk>> e = Maps.newHashMap();
    private static final Map<Class<? extends pk>, Integer> f = Maps.newHashMap();
    private static final Map<String, Integer> g = Maps.newHashMap();
    public static final Map<Integer, a> a = Maps.newLinkedHashMap();

    private static void a(Class<? extends pk> clazz, String string, int n2) {
        if (c.containsKey(string)) {
            throw new IllegalArgumentException("ID is already registered: " + string);
        }
        if (e.containsKey(n2)) {
            throw new IllegalArgumentException("ID is already registered: " + n2);
        }
        if (n2 == 0) {
            throw new IllegalArgumentException("Cannot register to reserved id: " + n2);
        }
        if (clazz == null) {
            throw new IllegalArgumentException("Cannot register null clazz for id: " + n2);
        }
        c.put(string, clazz);
        d.put(clazz, string);
        e.put(n2, clazz);
        f.put(clazz, n2);
        g.put(string, n2);
    }

    private static void a(Class<? extends pk> clazz, String string, int n2, int n3, int n4) {
        pm.a(clazz, string, n2);
        a.put(n2, new a(n2, n3, n4));
    }

    public static pk a(String string, adm adm2) {
        pk pk2 = null;
        try {
            Class<? extends pk> clazz = c.get(string);
            if (clazz != null) {
                pk2 = clazz.getConstructor(adm.class).newInstance(adm2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return pk2;
    }

    public static pk a(dn dn2, adm adm2) {
        pk pk2 = null;
        if ("Minecart".equals(dn2.j("id"))) {
            dn2.a("id", va.a.a(dn2.f("Type")).b());
            dn2.o("Type");
        }
        try {
            Class<? extends pk> clazz = c.get(dn2.j("id"));
            if (clazz != null) {
                pk2 = clazz.getConstructor(adm.class).newInstance(adm2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (pk2 != null) {
            pk2.f(dn2);
        } else {
            b.warn("Skipping Entity with id " + dn2.j("id"));
        }
        return pk2;
    }

    public static pk a(int n2, adm adm2) {
        pk pk2 = null;
        try {
            Class<? extends pk> clazz = pm.a(n2);
            if (clazz != null) {
                pk2 = clazz.getConstructor(adm.class).newInstance(adm2);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        if (pk2 == null) {
            b.warn("Skipping Entity with id " + n2);
        }
        return pk2;
    }

    public static int a(pk pk2) {
        Integer n2 = f.get(pk2.getClass());
        return n2 == null ? 0 : n2;
    }

    public static Class<? extends pk> a(int n2) {
        return e.get(n2);
    }

    public static String b(pk pk2) {
        return d.get(pk2.getClass());
    }

    public static int a(String string) {
        Integer n2 = g.get(string);
        if (n2 == null) {
            return 90;
        }
        return n2;
    }

    public static String b(int n2) {
        return d.get(pm.a(n2));
    }

    public static void a() {
    }

    public static List<String> b() {
        Set<String> set = c.keySet();
        ArrayList<String> \u26032 = Lists.newArrayList();
        for (String string : set) {
            Class<? extends pk> clazz = c.get(string);
            if ((clazz.getModifiers() & 0x400) == 1024) continue;
            \u26032.add(string);
        }
        \u26032.add("LightningBolt");
        return \u26032;
    }

    public static boolean a(pk pk2, String string) {
        \u2603 = pm.b(pk2);
        if (\u2603 == null && pk2 instanceof wn) {
            \u2603 = "Player";
        } else if (\u2603 == null && pk2 instanceof uv) {
            \u2603 = "LightningBolt";
        }
        return string.equals(\u2603);
    }

    public static boolean b(String string) {
        return "Player".equals(string) || pm.b().contains(string);
    }

    static {
        pm.a(uz.class, "Item", 1);
        pm.a(pp.class, "XPOrb", 2);
        pm.a(wz.class, "ThrownEgg", 7);
        pm.a(up.class, "LeashKnot", 8);
        pm.a(uq.class, "Painting", 9);
        pm.a(wq.class, "Arrow", 10);
        pm.a(wx.class, "Snowball", 11);
        pm.a(wu.class, "Fireball", 12);
        pm.a(ww.class, "SmallFireball", 13);
        pm.a(xa.class, "ThrownEnderpearl", 14);
        pm.a(wr.class, "EyeOfEnderSignal", 15);
        pm.a(xc.class, "ThrownPotion", 16);
        pm.a(xb.class, "ThrownExpBottle", 17);
        pm.a(uo.class, "ItemFrame", 18);
        pm.a(xd.class, "WitherSkull", 19);
        pm.a(vj.class, "PrimedTnt", 20);
        pm.a(uy.class, "FallingSand", 21);
        pm.a(wt.class, "FireworksRocketEntity", 22);
        pm.a(um.class, "ArmorStand", 30);
        pm.a(ux.class, "Boat", 41);
        pm.a(vg.class, va.a.a.b(), 42);
        pm.a(vb.class, va.a.b.b(), 43);
        pm.a(ve.class, va.a.c.b(), 44);
        pm.a(vi.class, va.a.d.b(), 45);
        pm.a(vf.class, va.a.f.b(), 46);
        pm.a(vh.class, va.a.e.b(), 47);
        pm.a(vc.class, va.a.g.b(), 40);
        pm.a(ps.class, "Mob", 48);
        pm.a(vv.class, "Monster", 49);
        pm.a(vn.class, "Creeper", 50, 894731, 0);
        pm.a(wa.class, "Skeleton", 51, 0xC1C1C1, 0x494949);
        pm.a(wc.class, "Spider", 52, 3419431, 11013646);
        pm.a(vs.class, "Giant", 53);
        pm.a(we.class, "Zombie", 54, 44975, 7969893);
        pm.a(wb.class, "Slime", 55, 5349438, 8306542);
        pm.a(vr.class, "Ghast", 56, 0xF9F9F9, 0xBCBCBC);
        pm.a(vw.class, "PigZombie", 57, 15373203, 5009705);
        pm.a(vo.class, "Enderman", 58, 0x161616, 0);
        pm.a(vm.class, "CaveSpider", 59, 803406, 11013646);
        pm.a(vz.class, "Silverfish", 60, 0x6E6E6E, 0x303030);
        pm.a(vl.class, "Blaze", 61, 16167425, 16775294);
        pm.a(vu.class, "LavaSlime", 62, 0x340000, 0xFCFC00);
        pm.a(ug.class, "EnderDragon", 63);
        pm.a(uk.class, "WitherBoss", 64);
        pm.a(tk.class, "Bat", 65, 4996656, 986895);
        pm.a(wd.class, "Witch", 66, 0x340000, 5349438);
        pm.a(vp.class, "Endermite", 67, 0x161616, 0x6E6E6E);
        pm.a(vt.class, "Guardian", 68, 5931634, 15826224);
        pm.a(tt.class, "Pig", 90, 15771042, 14377823);
        pm.a(tv.class, "Sheep", 91, 0xE7E7E7, 0xFFB5B5);
        pm.a(to.class, "Cow", 92, 4470310, 0xA1A1A1);
        pm.a(tn.class, "Chicken", 93, 0xA1A1A1, 0xFF0000);
        pm.a(tx.class, "Squid", 94, 2243405, 7375001);
        pm.a(ua.class, "Wolf", 95, 0xD7D3D3, 13545366);
        pm.a(tr.class, "MushroomCow", 96, 10489616, 0xB7B7B7);
        pm.a(tw.class, "SnowMan", 97);
        pm.a(ts.class, "Ozelot", 98, 15720061, 5653556);
        pm.a(ty.class, "VillagerGolem", 99);
        pm.a(tp.class, "EntityHorse", 100, 12623485, 0xEEE500);
        pm.a(tu.class, "Rabbit", 101, 10051392, 7555121);
        pm.a(wi.class, "Villager", 120, 5651507, 12422002);
        pm.a(uf.class, "EnderCrystal", 200);
    }

    public static class a {
        public final int a;
        public final int b;
        public final int c;
        public final mw d;
        public final mw e;

        public a(int n2, int n3, int n4) {
            this.a = n2;
            this.b = n3;
            this.c = n4;
            this.d = na.a(this);
            this.e = na.b(this);
        }
    }
}

