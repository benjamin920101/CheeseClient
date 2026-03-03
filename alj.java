/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.base.Predicate;
import java.util.List;

public class alj
extends alk
implements ali,
km {
    private zx[] a = new zx[5];
    private String f;
    private int g = -1;

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.a = new zx[this.o_()];
        if (dn2.b("CustomName", 8)) {
            this.f = dn2.j("CustomName");
        }
        this.g = dn2.f("TransferCooldown");
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            byte \u26032 = dn3.d("Slot");
            if (\u26032 < 0 || \u26032 >= this.a.length) continue;
            this.a[\u26032] = zx.a(dn3);
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        du du2 = new du();
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null) continue;
            dn dn3 = new dn();
            dn3.a("Slot", (byte)i2);
            this.a[i2].b(dn3);
            du2.a(dn3);
        }
        dn22.a("Items", du2);
        dn22.a("TransferCooldown", this.g);
        if (this.l_()) {
            dn22.a("CustomName", this.f);
        }
    }

    @Override
    public void p_() {
        super.p_();
    }

    @Override
    public int o_() {
        return this.a.length;
    }

    @Override
    public zx a(int n2) {
        return this.a[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.a[n22] != null) {
            int n22;
            if (this.a[n22].b <= n3) {
                zx zx2 = this.a[n22];
                this.a[n22] = null;
                return zx2;
            }
            zx \u26032 = this.a[n22].a(n3);
            if (this.a[n22].b == 0) {
                this.a[n22] = null;
            }
            return \u26032;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (this.a[n2] != null) {
            zx zx2 = this.a[n2];
            this.a[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.a[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
    }

    @Override
    public String e_() {
        return this.l_() ? this.f : "container.hopper";
    }

    @Override
    public boolean l_() {
        return this.f != null && this.f.length() > 0;
    }

    public void a(String string) {
        this.f = string;
    }

    @Override
    public int q_() {
        return 64;
    }

    @Override
    public boolean a(wn wn2) {
        if (this.b.s(this.c) != this) {
            return false;
        }
        return !(wn2.e((double)this.c.n() + 0.5, (double)this.c.o() + 0.5, (double)this.c.p() + 0.5) > 64.0);
    }

    @Override
    public void b(wn wn2) {
    }

    @Override
    public void c(wn wn2) {
    }

    @Override
    public boolean b(int n2, zx zx2) {
        return true;
    }

    @Override
    public void c() {
        if (this.b == null || this.b.D) {
            return;
        }
        --this.g;
        if (!this.n()) {
            this.d(0);
            this.m();
        }
    }

    public boolean m() {
        if (this.b == null || this.b.D) {
            return false;
        }
        if (!this.n() && ahn.f(this.u())) {
            boolean bl2 = false;
            if (!this.p()) {
                bl2 = this.r();
            }
            if (!this.q()) {
                boolean bl3 = bl2 = alj.a(this) || bl2;
            }
            if (bl2) {
                this.d(8);
                this.p_();
                return true;
            }
        }
        return false;
    }

    private boolean p() {
        for (zx zx2 : this.a) {
            if (zx2 == null) continue;
            return false;
        }
        return true;
    }

    private boolean q() {
        for (zx zx2 : this.a) {
            if (zx2 != null && zx2.b == zx2.c()) continue;
            return false;
        }
        return true;
    }

    private boolean r() {
        og og2 = this.H();
        if (og2 == null) {
            return false;
        }
        cq \u26032 = ahn.b(this.u()).d();
        if (this.a(og2, \u26032)) {
            return false;
        }
        for (int i2 = 0; i2 < this.o_(); ++i2) {
            if (this.a(i2) == null) continue;
            zx zx2 = this.a(i2).k();
            \u2603 = alj.a(og2, this.a(i2, 1), \u26032);
            if (\u2603 == null || \u2603.b == 0) {
                og2.p_();
                return true;
            }
            this.a(i2, zx2);
        }
        return false;
    }

    private boolean a(og og22, cq cq2) {
        if (og22 instanceof ot) {
            ot ot2 = (ot)og22;
            int[] \u26032 = ot2.a(cq2);
            for (int i2 = 0; i2 < \u26032.length; ++i2) {
                zx zx2 = ot2.a(\u26032[i2]);
                if (zx2 != null && zx2.b == zx2.c()) continue;
                return false;
            }
        } else {
            og og22;
            int \u26033 = og22.o_();
            for (int i3 = 0; i3 < \u26033; ++i3) {
                zx zx3 = og22.a(i3);
                if (zx3 != null && zx3.b == zx3.c()) continue;
                return false;
            }
        }
        return true;
    }

    private static boolean b(og og22, cq cq2) {
        if (og22 instanceof ot) {
            ot ot2 = (ot)og22;
            int[] \u26032 = ot2.a(cq2);
            for (int i2 = 0; i2 < \u26032.length; ++i2) {
                if (ot2.a(\u26032[i2]) == null) continue;
                return false;
            }
        } else {
            og og22;
            int \u26033 = og22.o_();
            for (int i3 = 0; i3 < \u26033; ++i3) {
                if (og22.a(i3) == null) continue;
                return false;
            }
        }
        return true;
    }

    public static boolean a(ali ali22) {
        og og2 = alj.b(ali22);
        if (og2 != null) {
            cq cq2 = cq.a;
            if (alj.b(og2, cq2)) {
                return false;
            }
            if (og2 instanceof ot) {
                ot ot2 = (ot)og2;
                int[] \u26032 = ot2.a(cq2);
                for (int i2 = 0; i2 < \u26032.length; ++i2) {
                    if (!alj.a(ali22, og2, \u26032[i2], cq2)) continue;
                    return true;
                }
            } else {
                int \u26033 = og2.o_();
                for (int i3 = 0; i3 < \u26033; ++i3) {
                    if (!alj.a(ali22, og2, i3, cq2)) continue;
                    return true;
                }
            }
        } else {
            ali ali22;
            for (uz \u26034 : alj.a(ali22.z(), ali22.A(), ali22.B() + 1.0, ali22.C())) {
                if (!alj.a(ali22, \u26034)) continue;
                return true;
            }
        }
        return false;
    }

    private static boolean a(ali ali2, og og2, int n2, cq cq2) {
        zx zx2 = og2.a(n2);
        if (zx2 != null && alj.b(og2, zx2, n2, cq2)) {
            \u2603 = zx2.k();
            \u2603 = alj.a(ali2, og2.a(n2, 1), null);
            if (\u2603 == null || \u2603.b == 0) {
                og2.p_();
                return true;
            }
            og2.a(n2, \u2603);
        }
        return false;
    }

    public static boolean a(og og2, uz uz2) {
        boolean bl2 = false;
        if (uz2 == null) {
            return false;
        }
        zx \u26032 = uz2.l().k();
        zx \u26033 = alj.a(og2, \u26032, null);
        if (\u26033 == null || \u26033.b == 0) {
            bl2 = true;
            uz2.J();
        } else {
            uz2.a(\u26033);
        }
        return bl2;
    }

    public static zx a(og og22, zx zx22, cq cq2) {
        zx zx22;
        if (og22 instanceof ot && cq2 != null) {
            ot ot2 = (ot)og22;
            int[] \u26032 = ot2.a(cq2);
            for (int i2 = 0; i2 < \u26032.length && zx22 != null && zx22.b > 0; ++i2) {
                zx22 = alj.c(og22, zx22, \u26032[i2], cq2);
            }
        } else {
            og og22;
            int \u26033 = og22.o_();
            for (int i3 = 0; i3 < \u26033 && zx22 != null && zx22.b > 0; ++i3) {
                zx22 = alj.c(og22, zx22, i3, cq2);
            }
        }
        if (zx22 != null && zx22.b == 0) {
            zx22 = null;
        }
        return zx22;
    }

    private static boolean a(og og2, zx zx2, int n2, cq cq2) {
        if (!og2.b(n2, zx2)) {
            return false;
        }
        return !(og2 instanceof ot) || ((ot)og2).a(n2, zx2, cq2);
    }

    private static boolean b(og og2, zx zx2, int n2, cq cq2) {
        return !(og2 instanceof ot) || ((ot)og2).b(n2, zx2, cq2);
    }

    private static zx c(og og22, zx zx22, int n2, cq cq2) {
        zx zx22;
        zx zx3 = og22.a(n2);
        if (alj.a(og22, zx22, n2, cq2)) {
            boolean \u26032 = false;
            if (zx3 == null) {
                og22.a(n2, zx22);
                zx22 = null;
                \u26032 = true;
            } else if (alj.a(zx3, zx22)) {
                int \u26033 = zx22.c() - zx3.b;
                int \u26034 = Math.min(zx22.b, \u26033);
                zx22.b -= \u26034;
                zx3.b += \u26034;
                boolean bl2 = \u26032 = \u26034 > 0;
            }
            if (\u26032) {
                og og22;
                if (og22 instanceof alj) {
                    alj alj2 = (alj)og22;
                    if (alj2.o()) {
                        alj2.d(8);
                    }
                    og22.p_();
                }
                og22.p_();
            }
        }
        return zx22;
    }

    private og H() {
        cq cq2 = ahn.b(this.u());
        return alj.b(this.z(), this.c.n() + cq2.g(), (double)(this.c.o() + cq2.h()), this.c.p() + cq2.i());
    }

    public static og b(ali ali2) {
        return alj.b(ali2.z(), ali2.A(), ali2.B() + 1.0, ali2.C());
    }

    public static List<uz> a(adm adm2, double d2, double d3, double d4) {
        return adm2.a(uz.class, new aug(d2 - 0.5, d3 - 0.5, d4 - 0.5, d2 + 0.5, d3 + 0.5, d4 + 0.5), po.a);
    }

    public static og b(adm adm2, double d2, double d3, double d4) {
        og og2 = null;
        int \u26032 = ns.c(d2);
        cj \u26033 = new cj(\u26032, \u2603 = ns.c(d3), \u2603 = ns.c(d4));
        afh \u26034 = adm2.p(\u26033).c();
        if (\u26034.z() && (\u2603 = adm2.s(\u26033)) instanceof og && (og2 = (og)\u2603) instanceof aky && \u26034 instanceof afs) {
            og2 = ((afs)\u26034).f(adm2, \u26033);
        }
        if (og2 == null && (\u2603 = adm2.a((pk)null, new aug(d2 - 0.5, d3 - 0.5, d4 - 0.5, d2 + 0.5, d3 + 0.5, d4 + 0.5), (Predicate<? super pk>)po.c)).size() > 0) {
            og2 = (og)\u2603.get(adm2.s.nextInt(\u2603.size()));
        }
        return og2;
    }

    private static boolean a(zx zx2, zx zx3) {
        if (zx2.b() != zx3.b()) {
            return false;
        }
        if (zx2.i() != zx3.i()) {
            return false;
        }
        if (zx2.b > zx2.c()) {
            return false;
        }
        return zx.a(zx2, zx3);
    }

    @Override
    public double A() {
        return (double)this.c.n() + 0.5;
    }

    @Override
    public double B() {
        return (double)this.c.o() + 0.5;
    }

    @Override
    public double C() {
        return (double)this.c.p() + 0.5;
    }

    public void d(int n2) {
        this.g = n2;
    }

    public boolean n() {
        return this.g > 0;
    }

    public boolean o() {
        return this.g <= 1;
    }

    @Override
    public String k() {
        return "minecraft:hopper";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xw(wm2, this, wn2);
    }

    @Override
    public int a_(int n2) {
        return 0;
    }

    @Override
    public void b(int n2, int n3) {
    }

    @Override
    public int g() {
        return 0;
    }

    @Override
    public void l() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = null;
        }
    }
}

