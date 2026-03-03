/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class lx {
    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger f = LogManager.getLogger();
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private final MinecraftServer h;
    private final List<lf> i = Lists.newArrayList();
    private final Map<UUID, lf> j = Maps.newHashMap();
    private final mc k = new mc(a);
    private final lu l = new lu(b);
    private final ly m = new ly(c);
    private final me n = new me(d);
    private final Map<UUID, mv> o = Maps.newHashMap();
    private aty p;
    private boolean q;
    protected int e;
    private int r;
    private adp.a s;
    private boolean t;
    private int u;

    public lx(MinecraftServer minecraftServer) {
        this.h = minecraftServer;
        this.k.a(false);
        this.l.a(false);
        this.e = 8;
    }

    public void a(ek ek2, lf lf22) {
        lf lf22;
        GameProfile gameProfile = lf22.cd();
        lt \u26032 = this.h.aF();
        \u2603 = \u26032.a(gameProfile.getId());
        String \u26033 = \u2603 == null ? gameProfile.getName() : \u2603.getName();
        \u26032.a(gameProfile);
        dn \u26034 = this.a(lf22);
        lf22.a(this.h.a(lf22.am));
        lf22.c.a((le)lf22.o);
        String \u26035 = "local";
        if (ek2.b() != null) {
            \u26035 = ek2.b().toString();
        }
        f.info(lf22.e_() + "[" + \u26035 + "] logged in with entity id " + lf22.F() + " at (" + lf22.s + ", " + lf22.t + ", " + lf22.u + ")");
        le \u26036 = this.h.a(lf22.am);
        ato \u26037 = \u26036.P();
        cj \u26038 = \u26036.M();
        this.a(lf22, null, \u26036);
        lm \u26039 = new lm(this.h, ek2, lf22);
        \u26039.a(new gt(lf22.F(), lf22.c.b(), \u26037.t(), \u26036.t.q(), \u26036.aa(), this.p(), \u26037.u(), \u26036.Q().b("reducedDebugInfo")));
        \u26039.a(new gg("MC|Brand", new em(Unpooled.buffer()).a(this.c().getServerModName())));
        \u26039.a(new fw(\u26037.y(), \u26037.z()));
        \u26039.a(new ht(\u26038));
        \u26039.a(new gx(lf22.bA));
        \u26039.a(new hi(lf22.bi.c));
        lf22.A().d();
        lf22.A().b(lf22);
        this.a((kk)\u26036.Z(), lf22);
        this.h.aH();
        fb \u260310 = !lf22.e_().equalsIgnoreCase(\u26033) ? new fb("multiplayer.player.joined.renamed", lf22.f_(), \u26033) : new fb("multiplayer.player.joined", lf22.f_());
        \u260310.b().a(a.o);
        this.a(\u260310);
        this.c(lf22);
        \u26039.a(lf22.s, lf22.t, lf22.u, lf22.y, lf22.z);
        this.b(lf22, \u26036);
        if (this.h.ab().length() > 0) {
            lf22.a(this.h.ab(), this.h.ac());
        }
        Object \u260311 = lf22.bl().iterator();
        while (\u260311.hasNext()) {
            pf pf2 = \u260311.next();
            \u26039.a(new ib(lf22.F(), pf2));
        }
        lf22.g_();
        if (\u26034 != null && \u26034.b("Riding", 10) && (\u260311 = pm.a(\u26034.m("Riding"), (adm)\u26036)) != null) {
            ((pk)\u260311).n = true;
            \u26036.d((pk)\u260311);
            lf22.a((pk)\u260311);
            ((pk)\u260311).n = false;
        }
    }

    protected void a(kk kk2, lf lf2) {
        HashSet<auk> hashSet = Sets.newHashSet();
        for (aul aul2 : kk2.g()) {
            lf2.a.a(new hr(aul2, 0));
        }
        for (int i2 = 0; i2 < 19; ++i2) {
            auk auk2 = kk2.a(i2);
            if (auk2 == null || hashSet.contains(auk2)) continue;
            List<ff> \u26032 = kk2.d(auk2);
            for (ff ff2 : \u26032) {
                lf2.a.a(ff2);
            }
            hashSet.add(auk2);
        }
    }

    public void a(le[] leArray) {
        this.p = leArray[0].O().e();
        leArray[0].af().a(new amq(){

            @Override
            public void a(ams ams2, double d2) {
                lx.this.a(new hg(ams2, hg.a.a));
            }

            @Override
            public void a(ams ams2, double d2, double d3, long l2) {
                lx.this.a(new hg(ams2, hg.a.b));
            }

            @Override
            public void a(ams ams2, double d2, double d3) {
                lx.this.a(new hg(ams2, hg.a.c));
            }

            @Override
            public void a(ams ams2, int n2) {
                lx.this.a(new hg(ams2, hg.a.e));
            }

            @Override
            public void b(ams ams2, int n2) {
                lx.this.a(new hg(ams2, hg.a.f));
            }

            @Override
            public void b(ams ams2, double d2) {
            }

            @Override
            public void c(ams ams2, double d2) {
            }
        });
    }

    public void a(lf lf2, le le2) {
        \u2603 = lf2.u();
        if (le2 != null) {
            le2.t().c(lf2);
        }
        \u2603.t().a(lf2);
        \u2603.b.c((int)lf2.s >> 4, (int)lf2.u >> 4);
    }

    public int d() {
        return lc.b(this.s());
    }

    public dn a(lf lf2) {
        dn dn2 = this.h.d[0].P().i();
        if (lf2.e_().equals(this.h.S()) && dn2 != null) {
            lf2.f(dn2);
            \u2603 = dn2;
            f.debug("loading single player");
        } else {
            \u2603 = this.p.b(lf2);
        }
        return \u2603;
    }

    protected void b(lf lf2) {
        this.p.a(lf2);
        mv mv2 = this.o.get(lf2.aK());
        if (mv2 != null) {
            mv2.b();
        }
    }

    public void c(lf lf2) {
        this.i.add(lf2);
        this.j.put(lf2.aK(), lf2);
        this.a(new gz(gz.a.a, lf2));
        le le2 = this.h.a(lf2.am);
        le2.d(lf2);
        this.a(lf2, null);
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            lf lf3 = this.i.get(i2);
            lf2.a.a(new gz(gz.a.a, lf3));
        }
    }

    public void d(lf lf2) {
        lf2.u().t().d(lf2);
    }

    public void e(lf lf2) {
        lf2.b(na.f);
        this.b(lf2);
        le le2 = lf2.u();
        if (lf2.m != null) {
            le2.f(lf2.m);
            f.debug("removing player mount");
        }
        le2.e(lf2);
        le2.t().c(lf2);
        this.i.remove(lf2);
        UUID \u26032 = lf2.aK();
        lf \u26033 = this.j.get(\u26032);
        if (\u26033 == lf2) {
            this.j.remove(\u26032);
            this.o.remove(\u26032);
        }
        this.a(new gz(gz.a.e, lf2));
    }

    public String a(SocketAddress socketAddress, GameProfile gameProfile) {
        if (this.k.a(gameProfile)) {
            md md2 = (md)this.k.b(gameProfile);
            String \u26032 = "You are banned from this server!\nReason: " + md2.d();
            if (md2.c() != null) {
                \u26032 = \u26032 + "\nYour ban will be removed on " + g.format(md2.c());
            }
            return \u26032;
        }
        if (!this.e(gameProfile)) {
            return "You are not white-listed on this server!";
        }
        if (this.l.a(socketAddress)) {
            lv \u26033 = this.l.b(socketAddress);
            String \u26034 = "Your IP address is banned from this server!\nReason: " + \u26033.d();
            if (\u26033.c() != null) {
                \u26034 = \u26034 + "\nYour ban will be removed on " + g.format(\u26033.c());
            }
            return \u26034;
        }
        if (this.i.size() >= this.e && !this.f(gameProfile)) {
            return "The server is full!";
        }
        return null;
    }

    public lf g(GameProfile gameProfile2) {
        UUID uUID = wn.a(gameProfile2);
        ArrayList<lf> \u26032 = Lists.newArrayList();
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            lf object = this.i.get(i2);
            if (!object.aK().equals(uUID)) continue;
            \u26032.add(object);
        }
        lf \u26033 = this.j.get(gameProfile2.getId());
        if (\u26033 != null && !\u26032.contains(\u26033)) {
            \u26032.add(\u26033);
        }
        for (lf lf2 : \u26032) {
            lf2.a.c("You logged in from another location");
        }
        lg lg2 = this.h.X() ? new ky(this.h.a(0)) : new lg(this.h.a(0));
        return new lf(this.h, this.h.a(0), gameProfile2, lg2);
    }

    public lf a(lf lf2, int n2, boolean bl2) {
        lf2.u().s().b(lf2);
        lf2.u().s().b((pk)lf2);
        lf2.u().t().c(lf2);
        this.i.remove(lf2);
        this.h.a(lf2.am).f(lf2);
        cj cj2 = lf2.ch();
        boolean \u26032 = lf2.ci();
        lf2.am = n2;
        lg \u26033 = this.h.X() ? new ky(this.h.a(lf2.am)) : new lg(this.h.a(lf2.am));
        lf \u26034 = new lf(this.h, this.h.a(lf2.am), lf2.cd(), \u26033);
        \u26034.a = lf2.a;
        \u26034.a((wn)lf2, bl2);
        \u26034.d(lf2.F());
        \u26034.o(lf2);
        le \u26035 = this.h.a(lf2.am);
        this.a(\u26034, lf2, \u26035);
        if (cj2 != null) {
            \u2603 = wn.a(this.h.a(lf2.am), cj2, \u26032);
            if (\u2603 != null) {
                \u26034.b((float)\u2603.n() + 0.5f, (float)\u2603.o() + 0.1f, (float)\u2603.p() + 0.5f, 0.0f, 0.0f);
                \u26034.a(cj2, \u26032);
            } else {
                \u26034.a.a(new gm(0, 0.0f));
            }
        }
        \u26035.b.c((int)\u26034.s >> 4, (int)\u26034.u >> 4);
        while (!\u26035.a((pk)\u26034, \u26034.aR()).isEmpty() && \u26034.t < 256.0) {
            \u26034.b(\u26034.s, \u26034.t + 1.0, \u26034.u);
        }
        \u26034.a.a(new he(\u26034.am, \u26034.o.aa(), \u26034.o.P().u(), \u26034.c.b()));
        \u2603 = \u26035.M();
        \u26034.a.a(\u26034.s, \u26034.t, \u26034.u, \u26034.y, \u26034.z);
        \u26034.a.a(new ht(\u2603));
        \u26034.a.a(new ho(\u26034.bD, \u26034.bC, \u26034.bB));
        this.b(\u26034, \u26035);
        \u26035.t().a(\u26034);
        \u26035.d(\u26034);
        this.i.add(\u26034);
        this.j.put(\u26034.aK(), \u26034);
        \u26034.g_();
        \u26034.i(\u26034.bn());
        return \u26034;
    }

    public void a(lf lf2, int n2) {
        \u2603 = lf2.am;
        le le2 = this.h.a(lf2.am);
        lf2.am = n2;
        \u2603 = this.h.a(lf2.am);
        lf2.a.a(new he(lf2.am, lf2.o.aa(), lf2.o.P().u(), lf2.c.b()));
        le2.f(lf2);
        lf2.I = false;
        this.a(lf2, \u2603, le2, \u2603);
        this.a(lf2, le2);
        lf2.a.a(lf2.s, lf2.t, lf2.u, lf2.y, lf2.z);
        lf2.c.a(\u2603);
        this.b(lf2, \u2603);
        this.f(lf2);
        for (pf pf2 : lf2.bl()) {
            lf2.a.a(new ib(lf2.F(), pf2));
        }
    }

    public void a(pk pk2, int n2, le le2, le le3) {
        double \u26034;
        double \u26033 = pk2.s;
        \u26034 = pk2.u;
        \u2603 = 8.0;
        float \u26032 = pk2.y;
        le2.B.a("moving");
        if (pk2.am == -1) {
            \u26033 = ns.a(\u26033 / \u2603, le3.af().b() + 16.0, le3.af().d() - 16.0);
            \u26034 = ns.a(\u26034 / \u2603, le3.af().c() + 16.0, le3.af().e() - 16.0);
            pk2.b(\u26033, pk2.t, \u26034, pk2.y, pk2.z);
            if (pk2.ai()) {
                le2.a(pk2, false);
            }
        } else if (pk2.am == 0) {
            \u26033 = ns.a(\u26033 * \u2603, le3.af().b() + 16.0, le3.af().d() - 16.0);
            \u26034 = ns.a(\u26034 * \u2603, le3.af().c() + 16.0, le3.af().e() - 16.0);
            pk2.b(\u26033, pk2.t, \u26034, pk2.y, pk2.z);
            if (pk2.ai()) {
                le2.a(pk2, false);
            }
        } else {
            cj cj2 = n2 == 1 ? le3.M() : le3.m();
            \u26033 = cj2.n();
            pk2.t = cj2.o();
            \u26034 = cj2.p();
            pk2.b(\u26033, pk2.t, \u26034, 90.0f, 0.0f);
            if (pk2.ai()) {
                le2.a(pk2, false);
            }
        }
        le2.B.b();
        if (n2 != 1) {
            le2.B.a("placing");
            \u26033 = ns.a((int)\u26033, -29999872, 29999872);
            \u26034 = ns.a((int)\u26034, -29999872, 29999872);
            if (pk2.ai()) {
                pk2.b(\u26033, pk2.t, \u26034, pk2.y, pk2.z);
                le3.u().a(pk2, \u26032);
                le3.d(pk2);
                le3.a(pk2, false);
            }
            le2.B.b();
        }
        pk2.a(le3);
    }

    public void e() {
        if (++this.u > 600) {
            this.a(new gz(gz.a.c, this.i));
            this.u = 0;
        }
    }

    public void a(ff ff2) {
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            this.i.get((int)i2).a.a(ff2);
        }
    }

    public void a(ff ff2, int n2) {
        for (\u2603 = 0; \u2603 < this.i.size(); ++\u2603) {
            lf lf2 = this.i.get(\u2603);
            if (lf2.am != n2) continue;
            lf2.a.a(ff2);
        }
    }

    public void a(wn wn2, eu eu2) {
        auq auq2 = wn2.bO();
        if (auq2 == null) {
            return;
        }
        Collection<String> \u26032 = auq2.d();
        for (String string : \u26032) {
            lf lf2 = this.a(string);
            if (lf2 == null || lf2 == wn2) continue;
            lf2.a(eu2);
        }
    }

    public void b(wn wn2, eu eu2) {
        auq auq2 = wn2.bO();
        if (auq2 == null) {
            this.a(eu2);
            return;
        }
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            lf lf2 = this.i.get(i2);
            if (lf2.bO() == auq2) continue;
            lf2.a(eu2);
        }
    }

    public String b(boolean bl2) {
        String string = "";
        ArrayList<lf> \u26032 = Lists.newArrayList(this.i);
        for (int i2 = 0; i2 < \u26032.size(); ++i2) {
            if (i2 > 0) {
                string = string + ", ";
            }
            string = string + ((lf)\u26032.get(i2)).e_();
            if (!bl2) continue;
            string = string + " (" + ((lf)\u26032.get(i2)).aK().toString() + ")";
        }
        return string;
    }

    public String[] f() {
        String[] stringArray = new String[this.i.size()];
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            stringArray[i2] = this.i.get(i2).e_();
        }
        return stringArray;
    }

    public GameProfile[] g() {
        GameProfile[] gameProfileArray = new GameProfile[this.i.size()];
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            gameProfileArray[i2] = this.i.get(i2).cd();
        }
        return gameProfileArray;
    }

    public mc h() {
        return this.k;
    }

    public lu i() {
        return this.l;
    }

    public void a(GameProfile gameProfile) {
        this.m.a(new lz(gameProfile, this.h.p(), this.m.b(gameProfile)));
    }

    public void b(GameProfile gameProfile) {
        this.m.c(gameProfile);
    }

    public boolean e(GameProfile gameProfile) {
        return !this.q || this.m.d(gameProfile) || this.n.d(gameProfile);
    }

    public boolean h(GameProfile gameProfile) {
        return this.m.d(gameProfile) || this.h.T() && this.h.d[0].P().v() && this.h.S().equalsIgnoreCase(gameProfile.getName()) || this.t;
    }

    public lf a(String string) {
        for (lf lf2 : this.i) {
            if (!lf2.e_().equalsIgnoreCase(string)) continue;
            return lf2;
        }
        return null;
    }

    public void a(double d2, double d3, double d4, double d5, int n2, ff ff2) {
        this.a(null, d2, d3, d4, d5, n2, ff2);
    }

    public void a(wn wn2, double d2, double d3, double d4, double d5, int n2, ff ff2) {
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            lf lf2 = this.i.get(i2);
            if (lf2 == wn2 || lf2.am != n2 || !((\u2603 = d2 - lf2.s) * \u2603 + (\u2603 = d3 - lf2.t) * \u2603 + (\u2603 = d4 - lf2.u) * \u2603 < d5 * d5)) continue;
            lf2.a.a(ff2);
        }
    }

    public void j() {
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            this.b(this.i.get(i2));
        }
    }

    public void d(GameProfile gameProfile) {
        this.n.a(new mf(gameProfile));
    }

    public void c(GameProfile gameProfile) {
        this.n.c(gameProfile);
    }

    public me k() {
        return this.n;
    }

    public String[] l() {
        return this.n.a();
    }

    public ly m() {
        return this.m;
    }

    public String[] n() {
        return this.m.a();
    }

    public void a() {
    }

    public void b(lf lf2, le le2) {
        ams ams2 = this.h.d[0].af();
        lf2.a.a(new hg(ams2, hg.a.d));
        lf2.a.a(new hu(le2.K(), le2.L(), le2.Q().b("doDaylightCycle")));
        if (le2.S()) {
            lf2.a.a(new gm(1, 0.0f));
            lf2.a.a(new gm(7, le2.j(1.0f)));
            lf2.a.a(new gm(8, le2.h(1.0f)));
        }
    }

    public void f(lf lf2) {
        lf2.a(lf2.bj);
        lf2.r();
        lf2.a.a(new hi(lf2.bi.c));
    }

    public int o() {
        return this.i.size();
    }

    public int p() {
        return this.e;
    }

    public String[] q() {
        return this.h.d[0].O().e().f();
    }

    public void a(boolean bl2) {
        this.q = bl2;
    }

    public List<lf> b(String string) {
        ArrayList<lf> arrayList = Lists.newArrayList();
        for (lf lf2 : this.i) {
            if (!lf2.w().equals(string)) continue;
            arrayList.add(lf2);
        }
        return arrayList;
    }

    public int s() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.h;
    }

    public dn t() {
        return null;
    }

    public void a(adp.a a2) {
        this.s = a2;
    }

    private void a(lf lf2, lf lf3, adm adm2) {
        if (lf3 != null) {
            lf2.c.a(lf3.c.b());
        } else if (this.s != null) {
            lf2.c.a(this.s);
        }
        lf2.c.b(adm2.P().r());
    }

    public void c(boolean bl2) {
        this.t = bl2;
    }

    public void u() {
        for (int i2 = 0; i2 < this.i.size(); ++i2) {
            this.i.get((int)i2).a.c("Server closed");
        }
    }

    public void a(eu eu2, boolean bl2) {
        this.h.a(eu2);
        byte by = bl2 ? (byte)1 : 0;
        this.a(new fy(eu2, by));
    }

    public void a(eu eu2) {
        this.a(eu2, true);
    }

    public mv a(wn wn2) {
        mv \u26032;
        UUID uUID = wn2.aK();
        mv mv2 = \u26032 = uUID == null ? null : this.o.get(uUID);
        if (\u26032 == null) {
            File file = new File(this.h.a(0).O().b(), "stats");
            \u2603 = new File(file, uUID.toString() + ".json");
            if (!\u2603.exists() && (\u2603 = new File(file, wn2.e_() + ".json")).exists() && \u2603.isFile()) {
                \u2603.renameTo(\u2603);
            }
            \u26032 = new mv(this.h, \u2603);
            \u26032.a();
            this.o.put(uUID, \u26032);
        }
        return \u26032;
    }

    public void a(int n2) {
        this.r = n2;
        if (this.h.d == null) {
            return;
        }
        for (le le2 : this.h.d) {
            if (le2 == null) continue;
            le2.t().a(n2);
        }
    }

    public List<lf> v() {
        return this.i;
    }

    public lf a(UUID uUID) {
        return this.j.get(uUID);
    }

    public boolean f(GameProfile gameProfile) {
        return false;
    }
}

