/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class pe {
    public static final pe[] a = new pe[32];
    private static final Map<jy, pe> I = Maps.newHashMap();
    public static final pe b = null;
    public static final pe c = new pe(1, new jy("speed"), false, 8171462).c("potion.moveSpeed").b(0, 0).a(vy.d, "91AEAA56-376B-4498-935B-2F7F68070635", 0.2f, 2);
    public static final pe d = new pe(2, new jy("slowness"), true, 5926017).c("potion.moveSlowdown").b(1, 0).a(vy.d, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15f, 2);
    public static final pe e = new pe(3, new jy("haste"), false, 14270531).c("potion.digSpeed").b(2, 0).a(1.5);
    public static final pe f = new pe(4, new jy("mining_fatigue"), true, 4866583).c("potion.digSlowDown").b(3, 0);
    public static final pe g = new pb(5, new jy("strength"), false, 9643043).c("potion.damageBoost").b(4, 0).a(vy.e, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 2.5, 2);
    public static final pe h = new pd(6, new jy("instant_health"), false, 16262179).c("potion.heal");
    public static final pe i = new pd(7, new jy("instant_damage"), true, 4393481).c("potion.harm");
    public static final pe j = new pe(8, new jy("jump_boost"), false, 2293580).c("potion.jump").b(2, 1);
    public static final pe k = new pe(9, new jy("nausea"), true, 5578058).c("potion.confusion").b(3, 1).a(0.25);
    public static final pe l = new pe(10, new jy("regeneration"), false, 13458603).c("potion.regeneration").b(7, 0).a(0.25);
    public static final pe m = new pe(11, new jy("resistance"), false, 10044730).c("potion.resistance").b(6, 1);
    public static final pe n = new pe(12, new jy("fire_resistance"), false, 14981690).c("potion.fireResistance").b(7, 1);
    public static final pe o = new pe(13, new jy("water_breathing"), false, 3035801).c("potion.waterBreathing").b(0, 2);
    public static final pe p = new pe(14, new jy("invisibility"), false, 8356754).c("potion.invisibility").b(0, 1);
    public static final pe q = new pe(15, new jy("blindness"), true, 2039587).c("potion.blindness").b(5, 1).a(0.25);
    public static final pe r = new pe(16, new jy("night_vision"), false, 0x1F1FA1).c("potion.nightVision").b(4, 1);
    public static final pe s = new pe(17, new jy("hunger"), true, 5797459).c("potion.hunger").b(1, 1);
    public static final pe t = new pb(18, new jy("weakness"), true, 0x484D48).c("potion.weakness").b(5, 0).a(vy.e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0, 0);
    public static final pe u = new pe(19, new jy("poison"), true, 5149489).c("potion.poison").b(6, 0).a(0.25);
    public static final pe v = new pe(20, new jy("wither"), true, 3484199).c("potion.wither").b(1, 2).a(0.25);
    public static final pe w = new pc(21, new jy("health_boost"), false, 16284963).c("potion.healthBoost").b(2, 2).a(vy.a, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0, 0);
    public static final pe x = new pa(22, new jy("absorption"), false, 0x2552A5).c("potion.absorption").b(2, 2);
    public static final pe y = new pd(23, new jy("saturation"), false, 16262179).c("potion.saturation");
    public static final pe z = null;
    public static final pe A = null;
    public static final pe B = null;
    public static final pe C = null;
    public static final pe D = null;
    public static final pe E = null;
    public static final pe F = null;
    public static final pe G = null;
    public final int H;
    private final Map<qb, qd> J = Maps.newHashMap();
    private final boolean K;
    private final int L;
    private String M = "";
    private int N = -1;
    private double O;
    private boolean P;

    protected pe(int n2, jy jy2, boolean bl2, int n3) {
        this.H = n2;
        pe.a[n2] = this;
        I.put(jy2, this);
        this.K = bl2;
        this.O = bl2 ? 0.5 : 1.0;
        this.L = n3;
    }

    public static pe b(String string) {
        return I.get(new jy(string));
    }

    public static Set<jy> c() {
        return I.keySet();
    }

    protected pe b(int n2, int n3) {
        this.N = n2 + n3 * 8;
        return this;
    }

    public int d() {
        return this.H;
    }

    public void a(pr pr2, int n2) {
        if (this.H == pe.l.H) {
            if (pr2.bn() < pr2.bu()) {
                pr2.h(1.0f);
            }
        } else if (this.H == pe.u.H) {
            if (pr2.bn() > 1.0f) {
                pr2.a(ow.l, 1.0f);
            }
        } else if (this.H == pe.v.H) {
            pr2.a(ow.m, 1.0f);
        } else if (this.H == pe.s.H && pr2 instanceof wn) {
            ((wn)pr2).a(0.025f * (float)(n2 + 1));
        } else if (this.H == pe.y.H && pr2 instanceof wn) {
            if (!pr2.o.D) {
                ((wn)pr2).cl().a(n2 + 1, 1.0f);
            }
        } else if (this.H == pe.h.H && !pr2.bm() || this.H == pe.i.H && pr2.bm()) {
            pr2.h((float)Math.max(4 << n2, 0));
        } else if (this.H == pe.i.H && !pr2.bm() || this.H == pe.h.H && pr2.bm()) {
            pr2.a(ow.l, (float)(6 << n2));
        }
    }

    public void a(pk pk2, pk pk3, pr pr22, int n2, double d2) {
        pr pr22;
        if (this.H == pe.h.H && !pr22.bm() || this.H == pe.i.H && pr22.bm()) {
            int n3 = (int)(d2 * (double)(4 << n2) + 0.5);
            pr22.h((float)n3);
        } else if (this.H == pe.i.H && !pr22.bm() || this.H == pe.h.H && pr22.bm()) {
            int n4 = (int)(d2 * (double)(6 << n2) + 0.5);
            if (pk2 == null) {
                pr22.a(ow.l, (float)n4);
            } else {
                pr22.a(ow.b(pk2, pk3), (float)n4);
            }
        }
    }

    public boolean b() {
        return false;
    }

    public boolean a(int n2, int n3) {
        if (this.H == pe.l.H) {
            \u2603 = 50 >> n3;
            if (\u2603 > 0) {
                return n2 % \u2603 == 0;
            }
            return true;
        }
        if (this.H == pe.u.H) {
            \u2603 = 25 >> n3;
            if (\u2603 > 0) {
                return n2 % \u2603 == 0;
            }
            return true;
        }
        if (this.H == pe.v.H) {
            \u2603 = 40 >> n3;
            if (\u2603 > 0) {
                return n2 % \u2603 == 0;
            }
            return true;
        }
        return this.H == pe.s.H;
    }

    public pe c(String string) {
        this.M = string;
        return this;
    }

    public String a() {
        return this.M;
    }

    public boolean e() {
        return this.N >= 0;
    }

    public int f() {
        return this.N;
    }

    public boolean g() {
        return this.K;
    }

    public static String a(pf pf2) {
        if (pf2.h()) {
            return "**:**";
        }
        int n2 = pf2.b();
        return nx.a(n2);
    }

    protected pe a(double d2) {
        this.O = d2;
        return this;
    }

    public double h() {
        return this.O;
    }

    public boolean j() {
        return this.P;
    }

    public int k() {
        return this.L;
    }

    public pe a(qb qb2, String string, double d2, int n2) {
        qd qd2 = new qd(UUID.fromString(string), this.a(), d2, n2);
        this.J.put(qb2, qd2);
        return this;
    }

    public Map<qb, qd> l() {
        return this.J;
    }

    public void a(pr pr2, qf qf2, int n2) {
        for (Map.Entry<qb, qd> entry : this.J.entrySet()) {
            qc qc2 = qf2.a(entry.getKey());
            if (qc2 == null) continue;
            qc2.c(entry.getValue());
        }
    }

    public void b(pr pr2, qf qf2, int n2) {
        for (Map.Entry<qb, qd> entry : this.J.entrySet()) {
            qc qc2 = qf2.a(entry.getKey());
            if (qc2 == null) continue;
            qd \u26032 = entry.getValue();
            qc2.c(\u26032);
            qc2.b(new qd(\u26032.a(), this.a() + " " + n2, this.a(n2, \u26032), \u26032.c()));
        }
    }

    public double a(int n2, qd qd2) {
        return qd2.d() * (double)(n2 + 1);
    }
}

