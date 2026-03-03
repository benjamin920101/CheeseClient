/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class zx {
    public static final DecimalFormat a = new DecimalFormat("#.###");
    public int b;
    public int c;
    private zw d;
    private dn e;
    private int f;
    private uo g;
    private afh h = null;
    private boolean i = false;
    private afh j = null;
    private boolean k = false;

    public zx(afh afh2) {
        this(afh2, 1);
    }

    public zx(afh afh2, int n2) {
        this(afh2, n2, 0);
    }

    public zx(afh afh2, int n2, int n3) {
        this(zw.a(afh2), n2, n3);
    }

    public zx(zw zw2) {
        this(zw2, 1);
    }

    public zx(zw zw2, int n2) {
        this(zw2, n2, 0);
    }

    public zx(zw zw2, int n2, int n3) {
        this.d = zw2;
        this.b = n2;
        this.f = n3;
        if (this.f < 0) {
            this.f = 0;
        }
    }

    public static zx a(dn dn2) {
        zx zx2 = new zx();
        zx2.c(dn2);
        return zx2.b() != null ? zx2 : null;
    }

    private zx() {
    }

    public zx a(int n2) {
        zx zx2 = new zx(this.d, n2, this.f);
        if (this.e != null) {
            zx2.e = (dn)this.e.b();
        }
        this.b -= n2;
        return zx2;
    }

    public zw b() {
        return this.d;
    }

    public boolean a(wn wn2, adm adm2, cj cj2, cq cq2, float f2, float f3, float f4) {
        boolean bl2 = this.b().a(this, wn2, adm2, cj2, cq2, f2, f3, f4);
        if (bl2) {
            wn2.b(na.ad[zw.b(this.d)]);
        }
        return bl2;
    }

    public float a(afh afh2) {
        return this.b().a(this, afh2);
    }

    public zx a(adm adm2, wn wn2) {
        return this.b().a(this, adm2, wn2);
    }

    public zx b(adm adm2, wn wn2) {
        return this.b().b(this, adm2, wn2);
    }

    public dn b(dn dn2) {
        jy jy2 = zw.e.c(this.d);
        dn2.a("id", jy2 == null ? "minecraft:air" : jy2.toString());
        dn2.a("Count", (byte)this.b);
        dn2.a("Damage", (short)this.f);
        if (this.e != null) {
            dn2.a("tag", this.e);
        }
        return dn2;
    }

    public void c(dn dn2) {
        this.d = dn2.b("id", 8) ? zw.d(dn2.j("id")) : zw.b(dn2.e("id"));
        this.b = dn2.d("Count");
        this.f = dn2.e("Damage");
        if (this.f < 0) {
            this.f = 0;
        }
        if (dn2.b("tag", 10)) {
            this.e = dn2.m("tag");
            if (this.d != null) {
                this.d.a(this.e);
            }
        }
    }

    public int c() {
        return this.b().j();
    }

    public boolean d() {
        return this.c() > 1 && (!this.e() || !this.g());
    }

    public boolean e() {
        if (this.d == null) {
            return false;
        }
        if (this.d.l() <= 0) {
            return false;
        }
        return !this.n() || !this.o().n("Unbreakable");
    }

    public boolean f() {
        return this.d.k();
    }

    public boolean g() {
        return this.e() && this.f > 0;
    }

    public int h() {
        return this.f;
    }

    public int i() {
        return this.f;
    }

    public void b(int n2) {
        this.f = n2;
        if (this.f < 0) {
            this.f = 0;
        }
    }

    public int j() {
        return this.d.l();
    }

    public boolean a(int n22, Random random) {
        int n22;
        if (!this.e()) {
            return false;
        }
        if (n22 > 0) {
            int n3 = ack.a(aci.t.B, this);
            \u2603 = 0;
            for (\u2603 = 0; n3 > 0 && \u2603 < n22; ++\u2603) {
                if (!acg.a(this, n3, random)) continue;
                ++\u2603;
            }
            if ((n22 -= \u2603) <= 0) {
                return false;
            }
        }
        this.f += n22;
        return this.f > this.j();
    }

    public void a(int n2, pr pr2) {
        if (pr2 instanceof wn && ((wn)pr2).bA.d) {
            return;
        }
        if (!this.e()) {
            return;
        }
        if (this.a(n2, pr2.bc())) {
            pr2.b(this);
            --this.b;
            if (pr2 instanceof wn) {
                wn wn2 = (wn)pr2;
                wn2.b(na.ae[zw.b(this.d)]);
                if (this.b == 0 && this.b() instanceof yt) {
                    wn2.ca();
                }
            }
            if (this.b < 0) {
                this.b = 0;
            }
            this.f = 0;
        }
    }

    public void a(pr pr2, wn wn2) {
        boolean bl2 = this.d.a(this, pr2, (pr)wn2);
        if (bl2) {
            wn2.b(na.ad[zw.b(this.d)]);
        }
    }

    public void a(adm adm2, afh afh2, cj cj2, wn wn2) {
        boolean bl2 = this.d.a(this, adm2, afh2, cj2, wn2);
        if (bl2) {
            wn2.b(na.ad[zw.b(this.d)]);
        }
    }

    public boolean b(afh afh2) {
        return this.d.b(afh2);
    }

    public boolean a(wn wn2, pr pr2) {
        return this.d.a(this, wn2, pr2);
    }

    public zx k() {
        zx zx2 = new zx(this.d, this.b, this.f);
        if (this.e != null) {
            zx2.e = (dn)this.e.b();
        }
        return zx2;
    }

    public static boolean a(zx zx2, zx zx3) {
        if (zx2 == null && zx3 == null) {
            return true;
        }
        if (zx2 == null || zx3 == null) {
            return false;
        }
        if (zx2.e == null && zx3.e != null) {
            return false;
        }
        return zx2.e == null || zx2.e.equals(zx3.e);
    }

    public static boolean b(zx zx2, zx zx3) {
        if (zx2 == null && zx3 == null) {
            return true;
        }
        if (zx2 == null || zx3 == null) {
            return false;
        }
        return zx2.d(zx3);
    }

    private boolean d(zx zx2) {
        if (this.b != zx2.b) {
            return false;
        }
        if (this.d != zx2.d) {
            return false;
        }
        if (this.f != zx2.f) {
            return false;
        }
        if (this.e == null && zx2.e != null) {
            return false;
        }
        return this.e == null || this.e.equals(zx2.e);
    }

    public static boolean c(zx zx2, zx zx3) {
        if (zx2 == null && zx3 == null) {
            return true;
        }
        if (zx2 != null && zx3 != null) {
            return zx2.a(zx3);
        }
        return false;
    }

    public boolean a(zx zx2) {
        return zx2 != null && this.d == zx2.d && this.f == zx2.f;
    }

    public String a() {
        return this.d.e_(this);
    }

    public static zx b(zx zx2) {
        return zx2 == null ? null : zx2.k();
    }

    public String toString() {
        return this.b + "x" + this.d.a() + "@" + this.f;
    }

    public void a(adm adm2, pk pk2, int n2, boolean bl2) {
        if (this.c > 0) {
            --this.c;
        }
        this.d.a(this, adm2, pk2, n2, bl2);
    }

    public void a(adm adm2, wn wn2, int n2) {
        wn2.a(na.ac[zw.b(this.d)], n2);
        this.d.d(this, adm2, wn2);
    }

    public boolean c(zx zx2) {
        return this.d(zx2);
    }

    public int l() {
        return this.b().d(this);
    }

    public aba m() {
        return this.b().e(this);
    }

    public void b(adm adm2, wn wn2, int n2) {
        this.b().a(this, adm2, wn2, n2);
    }

    public boolean n() {
        return this.e != null;
    }

    public dn o() {
        return this.e;
    }

    public dn a(String string2, boolean bl2) {
        String string2;
        if (this.e == null || !this.e.b(string2, 10)) {
            if (bl2) {
                dn dn2 = new dn();
                this.a(string2, dn2);
                return dn2;
            }
            return null;
        }
        return this.e.m(string2);
    }

    public du p() {
        if (this.e == null) {
            return null;
        }
        return this.e.c("ench", 10);
    }

    public void d(dn dn2) {
        this.e = dn2;
    }

    public String q() {
        String string = this.b().a(this);
        if (this.e != null && this.e.b("display", 10) && (\u2603 = this.e.m("display")).b("Name", 8)) {
            string = \u2603.j("Name");
        }
        return string;
    }

    public zx c(String string) {
        if (this.e == null) {
            this.e = new dn();
        }
        if (!this.e.b("display", 10)) {
            this.e.a("display", new dn());
        }
        this.e.m("display").a("Name", string);
        return this;
    }

    public void r() {
        if (this.e == null) {
            return;
        }
        if (!this.e.b("display", 10)) {
            return;
        }
        dn dn2 = this.e.m("display");
        dn2.o("Name");
        if (dn2.c_()) {
            this.e.o("display");
            if (this.e.c_()) {
                this.d((dn)null);
            }
        }
    }

    public boolean s() {
        if (this.e == null) {
            return false;
        }
        if (!this.e.b("display", 10)) {
            return false;
        }
        return this.e.m("display").b("Name", 8);
    }

    public List<String> a(wn wn2, boolean bl22) {
        boolean bl22;
        Multimap<String, qd> multimap;
        ArrayList<String> arrayList = Lists.newArrayList();
        String \u26032 = this.q();
        if (this.s()) {
            \u26032 = (Object)((Object)a.u) + \u26032;
        }
        \u26032 = \u26032 + (Object)((Object)a.v);
        if (bl22) {
            String string = "";
            if (\u26032.length() > 0) {
                \u26032 = \u26032 + " (";
                string = ")";
            }
            int \u26033 = zw.b(this.d);
            \u26032 = this.f() ? \u26032 + String.format("#%04d/%d%s", \u26033, this.f, string) : \u26032 + String.format("#%04d%s", \u26033, string);
        } else if (!this.s() && this.d == zy.bd) {
            \u26032 = \u26032 + " #" + this.f;
        }
        arrayList.add(\u26032);
        int n2 = 0;
        if (this.n() && this.e.b("HideFlags", 99)) {
            n2 = this.e.f("HideFlags");
        }
        if ((n2 & 0x20) == 0) {
            this.d.a(this, wn2, arrayList, bl22);
        }
        if (this.n()) {
            if ((n2 & 1) == 0 && (\u2603 = this.p()) != null) {
                for (\u2603 = 0; \u2603 < \u2603.c(); ++\u2603) {
                    i2 = \u2603.b(\u2603).e("id");
                    short s2 = \u2603.b(\u2603).e("lvl");
                    if (aci.c(i2) == null) continue;
                    arrayList.add(aci.c(i2).d(s2));
                }
            }
            if (this.e.b("display", 10)) {
                dn dn2 = this.e.m("display");
                if (dn2.b("color", 3)) {
                    if (bl22) {
                        arrayList.add("Color: #" + Integer.toHexString(dn2.f("color")).toUpperCase());
                    } else {
                        arrayList.add((Object)((Object)a.u) + di.a("item.dyed"));
                    }
                }
                if (dn2.b("Lore") == 9 && (\u2603 = dn2.c("Lore", 8)).c() > 0) {
                    for (int i2 = 0; i2 < \u2603.c(); ++i2) {
                        arrayList.add((Object)((Object)a.f) + "" + (Object)((Object)a.u) + \u2603.f(i2));
                    }
                }
            }
        }
        if (!(multimap = this.B()).isEmpty() && (n2 & 2) == 0) {
            arrayList.add("");
            for (Map.Entry<String, qd> entry : multimap.entries()) {
                qd qd2 = entry.getValue();
                double \u26034 = qd2.d();
                if (qd2.a() == zw.f) {
                    \u26034 += (double)ack.a(this, pw.a);
                }
                double \u26035 = qd2.c() == 1 || qd2.c() == 2 ? \u26034 * 100.0 : \u26034;
                if (\u26034 > 0.0) {
                    arrayList.add((Object)((Object)a.j) + di.a("attribute.modifier.plus." + qd2.c(), a.format(\u26035), di.a("attribute.name." + entry.getKey())));
                    continue;
                }
                if (!(\u26034 < 0.0)) continue;
                arrayList.add((Object)((Object)a.m) + di.a("attribute.modifier.take." + qd2.c(), a.format(\u26035 *= -1.0), di.a("attribute.name." + entry.getKey())));
            }
        }
        if (this.n() && this.o().n("Unbreakable") && (n2 & 4) == 0) {
            arrayList.add((Object)((Object)a.j) + di.a("item.unbreakable"));
        }
        if (this.n() && this.e.b("CanDestroy", 9) && (n2 & 8) == 0 && (\u2603 = this.e.c("CanDestroy", 8)).c() > 0) {
            arrayList.add("");
            arrayList.add((Object)((Object)a.h) + di.a("item.canBreak"));
            for (int i3 = 0; i3 < \u2603.c(); ++i3) {
                afh afh2 = afh.b(\u2603.f(i3));
                if (afh2 != null) {
                    arrayList.add((Object)((Object)a.i) + afh2.f());
                    continue;
                }
                arrayList.add((Object)((Object)a.i) + "missingno");
            }
        }
        if (this.n() && this.e.b("CanPlaceOn", 9) && (n2 & 0x10) == 0 && (\u2603 = this.e.c("CanPlaceOn", 8)).c() > 0) {
            arrayList.add("");
            arrayList.add((Object)((Object)a.h) + di.a("item.canPlace"));
            for (\u2603 = 0; \u2603 < \u2603.c(); ++\u2603) {
                afh afh3 = afh.b(\u2603.f(\u2603));
                if (afh3 != null) {
                    arrayList.add((Object)((Object)a.i) + afh3.f());
                    continue;
                }
                arrayList.add((Object)((Object)a.i) + "missingno");
            }
        }
        if (bl22) {
            if (this.g()) {
                arrayList.add("Durability: " + (this.j() - this.h()) + " / " + this.j());
            }
            arrayList.add((Object)((Object)a.i) + zw.e.c(this.d).toString());
            if (this.n()) {
                arrayList.add((Object)((Object)a.i) + "NBT: " + this.o().c().size() + " tag(s)");
            }
        }
        return arrayList;
    }

    public boolean t() {
        return this.b().f(this);
    }

    public aaj u() {
        return this.b().g(this);
    }

    public boolean v() {
        if (!this.b().f_(this)) {
            return false;
        }
        return !this.w();
    }

    public void a(aci aci2, int n2) {
        if (this.e == null) {
            this.d(new dn());
        }
        if (!this.e.b("ench", 9)) {
            this.e.a("ench", new du());
        }
        du du2 = this.e.c("ench", 10);
        dn \u26032 = new dn();
        \u26032.a("id", (short)aci2.B);
        \u26032.a("lvl", (short)((byte)n2));
        du2.a(\u26032);
    }

    public boolean w() {
        return this.e != null && this.e.b("ench", 9);
    }

    public void a(String string, eb eb2) {
        if (this.e == null) {
            this.d(new dn());
        }
        this.e.a(string, eb2);
    }

    public boolean x() {
        return this.b().s();
    }

    public boolean y() {
        return this.g != null;
    }

    public void a(uo uo2) {
        this.g = uo2;
    }

    public uo z() {
        return this.g;
    }

    public int A() {
        if (this.n() && this.e.b("RepairCost", 3)) {
            return this.e.f("RepairCost");
        }
        return 0;
    }

    public void c(int n2) {
        if (!this.n()) {
            this.e = new dn();
        }
        this.e.a("RepairCost", n2);
    }

    public Multimap<String, qd> B() {
        Multimap<String, qd> multimap;
        if (this.n() && this.e.b("AttributeModifiers", 9)) {
            multimap = HashMultimap.create();
            du \u26032 = this.e.c("AttributeModifiers", 10);
            for (int i2 = 0; i2 < \u26032.c(); ++i2) {
                dn dn2 = \u26032.b(i2);
                qd \u26033 = vy.a(dn2);
                if (\u26033 == null || \u26033.a().getLeastSignificantBits() == 0L || \u26033.a().getMostSignificantBits() == 0L) continue;
                multimap.put(dn2.j("AttributeName"), \u26033);
            }
        } else {
            multimap = this.b().i();
        }
        return multimap;
    }

    public void a(zw zw2) {
        this.d = zw2;
    }

    public eu C() {
        fa fa2 = new fa(this.q());
        if (this.s()) {
            fa2.b().b(true);
        }
        eu \u26032 = new fa("[").a(fa2).a("]");
        if (this.d != null) {
            dn dn2 = new dn();
            this.b(dn2);
            \u26032.b().a(new ew(ew.a.c, new fa(dn2.toString())));
            \u26032.b().a(this.u().e);
        }
        return \u26032;
    }

    public boolean c(afh afh2) {
        if (afh2 == this.h) {
            return this.i;
        }
        this.h = afh2;
        if (this.n() && this.e.b("CanDestroy", 9)) {
            du du2 = this.e.c("CanDestroy", 8);
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                afh afh3 = afh.b(du2.f(i2));
                if (afh3 != afh2) continue;
                this.i = true;
                return true;
            }
        }
        this.i = false;
        return false;
    }

    public boolean d(afh afh2) {
        if (afh2 == this.j) {
            return this.k;
        }
        this.j = afh2;
        if (this.n() && this.e.b("CanPlaceOn", 9)) {
            du du2 = this.e.c("CanPlaceOn", 8);
            for (int i2 = 0; i2 < du2.c(); ++i2) {
                afh afh3 = afh.b(du2.f(i2));
                if (afh3 != afh2) continue;
                this.k = true;
                return true;
            }
        }
        this.k = false;
        return false;
    }
}

