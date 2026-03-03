/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class na {
    protected static Map<String, mw> a = Maps.newHashMap();
    public static List<mw> b = Lists.newArrayList();
    public static List<mw> c = Lists.newArrayList();
    public static List<mu> d = Lists.newArrayList();
    public static List<mu> e = Lists.newArrayList();
    public static mw f = new mt("stat.leaveGame", new fb("stat.leaveGame", new Object[0])).i().h();
    public static mw g = new mt("stat.playOneMinute", new fb("stat.playOneMinute", new Object[0]), mw.h).i().h();
    public static mw h = new mt("stat.timeSinceDeath", new fb("stat.timeSinceDeath", new Object[0]), mw.h).i().h();
    public static mw i = new mt("stat.walkOneCm", new fb("stat.walkOneCm", new Object[0]), mw.i).i().h();
    public static mw j = new mt("stat.crouchOneCm", new fb("stat.crouchOneCm", new Object[0]), mw.i).i().h();
    public static mw k = new mt("stat.sprintOneCm", new fb("stat.sprintOneCm", new Object[0]), mw.i).i().h();
    public static mw l = new mt("stat.swimOneCm", new fb("stat.swimOneCm", new Object[0]), mw.i).i().h();
    public static mw m = new mt("stat.fallOneCm", new fb("stat.fallOneCm", new Object[0]), mw.i).i().h();
    public static mw n = new mt("stat.climbOneCm", new fb("stat.climbOneCm", new Object[0]), mw.i).i().h();
    public static mw o = new mt("stat.flyOneCm", new fb("stat.flyOneCm", new Object[0]), mw.i).i().h();
    public static mw p = new mt("stat.diveOneCm", new fb("stat.diveOneCm", new Object[0]), mw.i).i().h();
    public static mw q = new mt("stat.minecartOneCm", new fb("stat.minecartOneCm", new Object[0]), mw.i).i().h();
    public static mw r = new mt("stat.boatOneCm", new fb("stat.boatOneCm", new Object[0]), mw.i).i().h();
    public static mw s = new mt("stat.pigOneCm", new fb("stat.pigOneCm", new Object[0]), mw.i).i().h();
    public static mw t = new mt("stat.horseOneCm", new fb("stat.horseOneCm", new Object[0]), mw.i).i().h();
    public static mw u = new mt("stat.jump", new fb("stat.jump", new Object[0])).i().h();
    public static mw v = new mt("stat.drop", new fb("stat.drop", new Object[0])).i().h();
    public static mw w = new mt("stat.damageDealt", new fb("stat.damageDealt", new Object[0]), mw.j).h();
    public static mw x = new mt("stat.damageTaken", new fb("stat.damageTaken", new Object[0]), mw.j).h();
    public static mw y = new mt("stat.deaths", new fb("stat.deaths", new Object[0])).h();
    public static mw z = new mt("stat.mobKills", new fb("stat.mobKills", new Object[0])).h();
    public static mw A = new mt("stat.animalsBred", new fb("stat.animalsBred", new Object[0])).h();
    public static mw B = new mt("stat.playerKills", new fb("stat.playerKills", new Object[0])).h();
    public static mw C = new mt("stat.fishCaught", new fb("stat.fishCaught", new Object[0])).h();
    public static mw D = new mt("stat.junkFished", new fb("stat.junkFished", new Object[0])).h();
    public static mw E = new mt("stat.treasureFished", new fb("stat.treasureFished", new Object[0])).h();
    public static mw F = new mt("stat.talkedToVillager", new fb("stat.talkedToVillager", new Object[0])).h();
    public static mw G = new mt("stat.tradedWithVillager", new fb("stat.tradedWithVillager", new Object[0])).h();
    public static mw H = new mt("stat.cakeSlicesEaten", new fb("stat.cakeSlicesEaten", new Object[0])).h();
    public static mw I = new mt("stat.cauldronFilled", new fb("stat.cauldronFilled", new Object[0])).h();
    public static mw J = new mt("stat.cauldronUsed", new fb("stat.cauldronUsed", new Object[0])).h();
    public static mw K = new mt("stat.armorCleaned", new fb("stat.armorCleaned", new Object[0])).h();
    public static mw L = new mt("stat.bannerCleaned", new fb("stat.bannerCleaned", new Object[0])).h();
    public static mw M = new mt("stat.brewingstandInteraction", new fb("stat.brewingstandInteraction", new Object[0])).h();
    public static mw N = new mt("stat.beaconInteraction", new fb("stat.beaconInteraction", new Object[0])).h();
    public static mw O = new mt("stat.dropperInspected", new fb("stat.dropperInspected", new Object[0])).h();
    public static mw P = new mt("stat.hopperInspected", new fb("stat.hopperInspected", new Object[0])).h();
    public static mw Q = new mt("stat.dispenserInspected", new fb("stat.dispenserInspected", new Object[0])).h();
    public static mw R = new mt("stat.noteblockPlayed", new fb("stat.noteblockPlayed", new Object[0])).h();
    public static mw S = new mt("stat.noteblockTuned", new fb("stat.noteblockTuned", new Object[0])).h();
    public static mw T = new mt("stat.flowerPotted", new fb("stat.flowerPotted", new Object[0])).h();
    public static mw U = new mt("stat.trappedChestTriggered", new fb("stat.trappedChestTriggered", new Object[0])).h();
    public static mw V = new mt("stat.enderchestOpened", new fb("stat.enderchestOpened", new Object[0])).h();
    public static mw W = new mt("stat.itemEnchanted", new fb("stat.itemEnchanted", new Object[0])).h();
    public static mw X = new mt("stat.recordPlayed", new fb("stat.recordPlayed", new Object[0])).h();
    public static mw Y = new mt("stat.furnaceInteraction", new fb("stat.furnaceInteraction", new Object[0])).h();
    public static mw Z = new mt("stat.craftingTableInteraction", new fb("stat.workbenchInteraction", new Object[0])).h();
    public static mw aa = new mt("stat.chestOpened", new fb("stat.chestOpened", new Object[0])).h();
    public static final mw[] ab = new mw[4096];
    public static final mw[] ac = new mw[32000];
    public static final mw[] ad = new mw[32000];
    public static final mw[] ae = new mw[32000];

    public static void a() {
        na.c();
        na.d();
        na.e();
        na.b();
        mr.a();
        pm.a();
    }

    private static void b() {
        HashSet<zw> hashSet = Sets.newHashSet();
        for (abs abs2 : abt.a().b()) {
            if (abs2.b() == null) continue;
            hashSet.add(abs2.b().b());
        }
        for (zx zx2 : abo.a().b().values()) {
            hashSet.add(zx2.b());
        }
        for (zw zw2 : hashSet) {
            if (zw2 == null) continue;
            int n2 = zw.b(zw2);
            String \u26033 = na.a(zw2);
            if (\u26033 == null) continue;
            na.ac[n2] = new mu("stat.craftItem.", \u26033, new fb("stat.craftItem", new zx(zw2).C()), zw2).h();
        }
        na.a(ac);
    }

    private static void c() {
        for (afh afh2 : afh.c) {
            zw zw2 = zw.a(afh2);
            if (zw2 == null) continue;
            int \u26032 = afh.a(afh2);
            String \u26033 = na.a(zw2);
            if (\u26033 == null || !afh2.J()) continue;
            na.ab[\u26032] = new mu("stat.mineBlock.", \u26033, new fb("stat.mineBlock", new zx(afh2).C()), zw2).h();
            e.add((mu)ab[\u26032]);
        }
        na.a(ab);
    }

    private static void d() {
        for (zw zw2 : zw.e) {
            if (zw2 == null) continue;
            int n2 = zw.b(zw2);
            String \u26032 = na.a(zw2);
            if (\u26032 == null) continue;
            na.ad[n2] = new mu("stat.useItem.", \u26032, new fb("stat.useItem", new zx(zw2).C()), zw2).h();
            if (zw2 instanceof yo) continue;
            d.add((mu)ad[n2]);
        }
        na.a(ad);
    }

    private static void e() {
        for (zw zw2 : zw.e) {
            if (zw2 == null) continue;
            int n2 = zw.b(zw2);
            String \u26032 = na.a(zw2);
            if (\u26032 == null || !zw2.m()) continue;
            na.ae[n2] = new mu("stat.breakItem.", \u26032, new fb("stat.breakItem", new zx(zw2).C()), zw2).h();
        }
        na.a(ae);
    }

    private static String a(zw zw2) {
        jy jy2 = zw.e.c(zw2);
        if (jy2 != null) {
            return jy2.toString().replace(':', '.');
        }
        return null;
    }

    private static void a(mw[] mwArray) {
        na.a(mwArray, afi.j, afi.i);
        na.a(mwArray, afi.l, afi.k);
        na.a(mwArray, afi.aZ, afi.aU);
        na.a(mwArray, afi.am, afi.al);
        na.a(mwArray, afi.aD, afi.aC);
        na.a(mwArray, afi.bc, afi.bb);
        na.a(mwArray, afi.ck, afi.cj);
        na.a(mwArray, afi.aF, afi.aE);
        na.a(mwArray, afi.bK, afi.bJ);
        na.a(mwArray, afi.T, afi.U);
        na.a(mwArray, afi.bL, afi.bM);
        na.a(mwArray, afi.cO, afi.cP);
        na.a(mwArray, afi.c, afi.d);
        na.a(mwArray, afi.ak, afi.d);
    }

    private static void a(mw[] mwArray, afh afh2, afh afh3) {
        int n2 = afh.a(afh2);
        \u2603 = afh.a(afh3);
        if (mwArray[n2] != null && mwArray[\u2603] == null) {
            mwArray[\u2603] = mwArray[n2];
            return;
        }
        b.remove(mwArray[n2]);
        e.remove(mwArray[n2]);
        c.remove(mwArray[n2]);
        mwArray[n2] = mwArray[\u2603];
    }

    public static mw a(pm.a a2) {
        String string = pm.b(a2.a);
        if (string == null) {
            return null;
        }
        return new mw("stat.killEntity." + string, new fb("stat.entityKill", new fb("entity." + string + ".name", new Object[0]))).h();
    }

    public static mw b(pm.a a2) {
        String string = pm.b(a2.a);
        if (string == null) {
            return null;
        }
        return new mw("stat.entityKilledBy." + string, new fb("stat.entityKilledBy", new fb("entity." + string + ".name", new Object[0]))).h();
    }

    public static mw a(String string) {
        return a.get(string);
    }
}

