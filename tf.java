/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;

public class tf {
    private adm a;
    private final List<te> b = Lists.newArrayList();
    private cj c = cj.a;
    private cj d = cj.a;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private TreeMap<String, Integer> j = new TreeMap();
    private List<a> k = Lists.newArrayList();
    private int l;

    public tf() {
    }

    public tf(adm adm2) {
        this.a = adm2;
    }

    public void a(adm adm2) {
        this.a = adm2;
    }

    public void a(int n2) {
        this.g = n2;
        this.m();
        this.l();
        if (n2 % 20 == 0) {
            this.k();
        }
        if (n2 % 30 == 0) {
            this.j();
        }
        if (this.l < (\u2603 = this.h / 10) && this.b.size() > 20 && this.a.s.nextInt(7000) == 0 && (\u2603 = this.a(this.d, 2, 4, 2)) != null) {
            ty ty2 = new ty(this.a);
            ty2.b(\u2603.a, \u2603.b, \u2603.c);
            this.a.d(ty2);
            ++this.l;
        }
    }

    private aui a(cj cj2, int n2, int n3, int n4) {
        for (\u2603 = 0; \u2603 < 10; ++\u2603) {
            cj cj3 = cj2.a(this.a.s.nextInt(16) - 8, this.a.s.nextInt(6) - 3, this.a.s.nextInt(16) - 8);
            if (!this.a(cj3) || !this.a(new cj(n2, n3, n4), cj3)) continue;
            return new aui(cj3.n(), cj3.o(), cj3.p());
        }
        return null;
    }

    private boolean a(cj cj2, cj cj3) {
        if (!adm.a(this.a, cj3.b())) {
            return false;
        }
        int n2 = cj3.n() - cj2.n() / 2;
        \u2603 = cj3.p() - cj2.p() / 2;
        for (\u2603 = n2; \u2603 < n2 + cj2.n(); ++\u2603) {
            for (\u2603 = cj3.o(); \u2603 < cj3.o() + cj2.o(); ++\u2603) {
                for (\u2603 = \u2603; \u2603 < \u2603 + cj2.p(); ++\u2603) {
                    if (!this.a.p(new cj(\u2603, \u2603, \u2603)).c().v()) continue;
                    return false;
                }
            }
        }
        return true;
    }

    private void j() {
        List<ty> list = this.a.a(ty.class, new aug(this.d.n() - this.e, this.d.o() - 4, this.d.p() - this.e, this.d.n() + this.e, this.d.o() + 4, this.d.p() + this.e));
        this.l = list.size();
    }

    private void k() {
        List<wi> list = this.a.a(wi.class, new aug(this.d.n() - this.e, this.d.o() - 4, this.d.p() - this.e, this.d.n() + this.e, this.d.o() + 4, this.d.p() + this.e));
        this.h = list.size();
        if (this.h == 0) {
            this.j.clear();
        }
    }

    public cj a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.b.size();
    }

    public int d() {
        return this.g - this.f;
    }

    public int e() {
        return this.h;
    }

    public boolean a(cj cj2) {
        return this.d.i(cj2) < (double)(this.e * this.e);
    }

    public List<te> f() {
        return this.b;
    }

    public te b(cj cj2) {
        te \u26033 = null;
        int \u26032 = Integer.MAX_VALUE;
        for (te te2 : this.b) {
            int n2 = te2.a(cj2);
            if (n2 >= \u26032) continue;
            \u26033 = te2;
            \u26032 = n2;
        }
        return \u26033;
    }

    public te c(cj cj2) {
        te \u26033 = null;
        int \u26032 = Integer.MAX_VALUE;
        for (te te2 : this.b) {
            int n2 = te2.a(cj2);
            n2 = n2 > 256 ? (n2 *= 1000) : te2.c();
            if (n2 >= \u26032) continue;
            \u26033 = te2;
            \u26032 = n2;
        }
        return \u26033;
    }

    public te e(cj cj2) {
        if (this.d.i(cj2) > (double)(this.e * this.e)) {
            return null;
        }
        for (te te2 : this.b) {
            if (te2.d().n() != cj2.n() || te2.d().p() != cj2.p() || Math.abs(te2.d().o() - cj2.o()) > 1) continue;
            return te2;
        }
        return null;
    }

    public void a(te te2) {
        this.b.add(te2);
        this.c = this.c.a(te2.d());
        this.n();
        this.f = te2.h();
    }

    public boolean g() {
        return this.b.isEmpty();
    }

    public void a(pr pr22) {
        pr pr22;
        for (a a2 : this.k) {
            if (a2.a != pr22) continue;
            a2.b = this.g;
            return;
        }
        this.k.add(new a(pr22, this.g));
    }

    public pr b(pr pr2) {
        double \u26034 = Double.MAX_VALUE;
        a \u26032 = null;
        for (int i2 = 0; i2 < this.k.size(); ++i2) {
            a a2 = this.k.get(i2);
            double \u26033 = a2.a.h(pr2);
            if (\u26033 > \u26034) continue;
            \u26032 = a2;
            \u26034 = \u26033;
        }
        return \u26032 != null ? \u26032.a : null;
    }

    public wn c(pr pr2) {
        double d2 = Double.MAX_VALUE;
        wn \u26032 = null;
        for (String string : this.j.keySet()) {
            if (!this.d(string) || (\u2603 = this.a.a(string)) == null || (\u2603 = \u2603.h(pr2)) > d2) continue;
            \u26032 = \u2603;
            d2 = \u2603;
        }
        return \u26032;
    }

    private void l() {
        Iterator<a> iterator = this.k.iterator();
        while (iterator.hasNext()) {
            a a2 = iterator.next();
            if (a2.a.ai() && Math.abs(this.g - a2.b) <= 300) continue;
            iterator.remove();
        }
    }

    private void m() {
        boolean \u26033 = false;
        \u2603 = this.a.s.nextInt(50) == 0;
        Iterator<te> \u26032 = this.b.iterator();
        while (\u26032.hasNext()) {
            te te2 = \u26032.next();
            if (\u2603) {
                te2.a();
            }
            if (this.f(te2.d()) && Math.abs(this.g - te2.h()) <= 1200) continue;
            this.c = this.c.b(te2.d());
            \u26033 = true;
            te2.a(true);
            \u26032.remove();
        }
        if (\u26033) {
            this.n();
        }
    }

    private boolean f(cj cj2) {
        afh afh2 = this.a.p(cj2).c();
        if (afh2 instanceof agh) {
            return afh2.t() == arm.d;
        }
        return false;
    }

    private void n() {
        int n2;
        int n3 = this.b.size();
        if (n3 == 0) {
            this.d = new cj(0, 0, 0);
            this.e = 0;
            return;
        }
        this.d = new cj(this.c.n() / n3, this.c.o() / n3, this.c.p() / n3);
        n2 = 0;
        for (te te2 : this.b) {
            n2 = Math.max(te2.a(this.d), n2);
        }
        this.e = Math.max(32, (int)Math.sqrt(n2) + 1);
    }

    public int a(String string) {
        Integer n2 = this.j.get(string);
        if (n2 != null) {
            return n2;
        }
        return 0;
    }

    public int a(String string, int n2) {
        \u2603 = this.a(string);
        \u2603 = ns.a(\u2603 + n2, -30, 10);
        this.j.put(string, \u2603);
        return \u2603;
    }

    public boolean d(String string) {
        return this.a(string) <= -15;
    }

    public void a(dn dn2) {
        Object \u26032;
        this.h = dn2.f("PopSize");
        this.e = dn2.f("Radius");
        this.l = dn2.f("Golems");
        this.f = dn2.f("Stable");
        this.g = dn2.f("Tick");
        this.i = dn2.f("MTick");
        this.d = new cj(dn2.f("CX"), dn2.f("CY"), dn2.f("CZ"));
        this.c = new cj(dn2.f("ACX"), dn2.f("ACY"), dn2.f("ACZ"));
        du du2 = dn2.c("Doors", 10);
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            \u26032 = new te(new cj(dn3.f("X"), dn3.f("Y"), dn3.f("Z")), dn3.f("IDX"), dn3.f("IDZ"), dn3.f("TS"));
            this.b.add((te)\u26032);
        }
        du du3 = dn2.c("Players", 10);
        for (int i3 = 0; i3 < du3.c(); ++i3) {
            \u26032 = du3.b(i3);
            if (((dn)\u26032).c("UUID")) {
                lt lt2 = MinecraftServer.N().aF();
                GameProfile \u26033 = lt2.a(UUID.fromString(((dn)\u26032).j("UUID")));
                if (\u26033 == null) continue;
                this.j.put(\u26033.getName(), ((dn)\u26032).f("S"));
                continue;
            }
            this.j.put(((dn)\u26032).j("Name"), ((dn)\u26032).f("S"));
        }
    }

    public void b(dn dn22) {
        dn22.a("PopSize", this.h);
        dn22.a("Radius", this.e);
        dn22.a("Golems", this.l);
        dn22.a("Stable", this.f);
        dn22.a("Tick", this.g);
        dn22.a("MTick", this.i);
        dn22.a("CX", this.d.n());
        dn22.a("CY", this.d.o());
        dn22.a("CZ", this.d.p());
        dn22.a("ACX", this.c.n());
        dn22.a("ACY", this.c.o());
        dn22.a("ACZ", this.c.p());
        du du2 = new du();
        for (te te2 : this.b) {
            dn dn2 = new dn();
            dn2.a("X", te2.d().n());
            dn2.a("Y", te2.d().o());
            dn2.a("Z", te2.d().p());
            dn2.a("IDX", te2.f());
            dn2.a("IDZ", te2.g());
            dn2.a("TS", te2.h());
            du2.a(dn2);
        }
        dn22.a("Doors", du2);
        du du3 = new du();
        for (String string : this.j.keySet()) {
            dn dn3 = new dn();
            lt \u26033 = MinecraftServer.N().aF();
            GameProfile \u26034 = \u26033.a(string);
            if (\u26034 == null) continue;
            dn3.a("UUID", \u26034.getId().toString());
            dn3.a("S", this.j.get(string));
            du3.a(dn3);
        }
        dn22.a("Players", du3);
    }

    public void h() {
        this.i = this.g;
    }

    public boolean i() {
        return this.i == 0 || this.g - this.i >= 3600;
    }

    public void b(int n2) {
        for (String string : this.j.keySet()) {
            this.a(string, n2);
        }
    }

    class a {
        public pr a;
        public int b;

        a(pr pr2, int n2) {
            this.a = pr2;
            this.b = n2;
        }
    }
}

