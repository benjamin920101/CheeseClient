/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class uy
extends pk {
    private alz d;
    public int a;
    public boolean b = true;
    private boolean e;
    private boolean f;
    private int g = 40;
    private float h = 2.0f;
    public dn c;

    public uy(adm adm2) {
        super(adm2);
    }

    public uy(adm adm2, double d2, double d3, double d4, alz alz2) {
        super(adm2);
        this.d = alz2;
        this.k = true;
        this.a(0.98f, 0.98f);
        this.b(d2, d3, d4);
        this.v = 0.0;
        this.w = 0.0;
        this.x = 0.0;
        this.p = d2;
        this.q = d3;
        this.r = d4;
    }

    @Override
    protected boolean s_() {
        return false;
    }

    @Override
    protected void h() {
    }

    @Override
    public boolean ad() {
        return !this.I;
    }

    @Override
    public void t_() {
        cj cj2;
        afh afh2 = this.d.c();
        if (afh2.t() == arm.a) {
            this.J();
            return;
        }
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        if (this.a++ == 0) {
            cj2 = new cj(this);
            if (this.o.p(cj2).c() == afh2) {
                this.o.g(cj2);
            } else if (!this.o.D) {
                this.J();
                return;
            }
        }
        this.w -= (double)0.04f;
        this.d(this.v, this.w, this.x);
        this.v *= (double)0.98f;
        this.w *= (double)0.98f;
        this.x *= (double)0.98f;
        if (!this.o.D) {
            cj2 = new cj(this);
            if (this.C) {
                this.v *= (double)0.7f;
                this.x *= (double)0.7f;
                this.w *= -0.5;
                if (this.o.p(cj2).c() != afi.M) {
                    this.J();
                    if (!this.e) {
                        if (this.o.a(afh2, cj2, true, cq.b, null, null) && !agr.e(this.o, cj2.b()) && this.o.a(cj2, this.d, 3)) {
                            if (afh2 instanceof agr) {
                                ((agr)afh2).a_(this.o, cj2);
                            }
                            if (this.c != null && afh2 instanceof agq && (akw2 = this.o.s(cj2)) != null) {
                                akw akw2;
                                dn dn2 = new dn();
                                akw2.b(dn2);
                                for (String string : this.c.c()) {
                                    eb eb2 = this.c.a(string);
                                    if (string.equals("x") || string.equals("y") || string.equals("z")) continue;
                                    dn2.a(string, eb2.b());
                                }
                                akw2.a(dn2);
                                akw2.p_();
                            }
                        } else if (this.b && this.o.Q().b("doEntityDrops")) {
                            this.a(new zx(afh2, 1, afh2.a(this.d)), 0.0f);
                        }
                    }
                }
            } else if (this.a > 100 && !this.o.D && (cj2.o() < 1 || cj2.o() > 256) || this.a > 600) {
                if (this.b && this.o.Q().b("doEntityDrops")) {
                    this.a(new zx(afh2, 1, afh2.a(this.d)), 0.0f);
                }
                this.J();
            }
        }
    }

    @Override
    public void e(float f2, float f3) {
        afh afh2 = this.d.c();
        if (this.f && (n2 = ns.f(f2 - 1.0f)) > 0) {
            int n2;
            ArrayList<pk> arrayList = Lists.newArrayList(this.o.b(this, this.aR()));
            boolean \u26032 = afh2 == afi.cf;
            ow \u26033 = \u26032 ? ow.n : ow.o;
            for (pk pk2 : arrayList) {
                pk2.a(\u26033, (float)Math.min(ns.d((float)n2 * this.h), this.g));
            }
            if (\u26032 && (double)this.V.nextFloat() < (double)0.05f + (double)n2 * 0.05) {
                \u2603 = this.d.b(aez.b);
                if (++\u2603 > 2) {
                    this.e = true;
                } else {
                    this.d = this.d.a(aez.b, \u2603);
                }
            }
        }
    }

    @Override
    protected void b(dn dn2) {
        afh afh2 = this.d != null ? this.d.c() : afi.a;
        jy \u26032 = (jy)afh.c.c(afh2);
        dn2.a("Block", \u26032 == null ? "" : \u26032.toString());
        dn2.a("Data", (byte)afh2.c(this.d));
        dn2.a("Time", (byte)this.a);
        dn2.a("DropItem", this.b);
        dn2.a("HurtEntities", this.f);
        dn2.a("FallHurtAmount", this.h);
        dn2.a("FallHurtMax", this.g);
        if (this.c != null) {
            dn2.a("TileEntityData", this.c);
        }
    }

    @Override
    protected void a(dn dn2) {
        int n2 = dn2.d("Data") & 0xFF;
        this.d = dn2.b("Block", 8) ? afh.b(dn2.j("Block")).a(n2) : (dn2.b("TileID", 99) ? afh.c(dn2.f("TileID")).a(n2) : afh.c(dn2.d("Tile") & 0xFF).a(n2));
        this.a = dn2.d("Time") & 0xFF;
        afh \u26032 = this.d.c();
        if (dn2.b("HurtEntities", 99)) {
            this.f = dn2.n("HurtEntities");
            this.h = dn2.h("FallHurtAmount");
            this.g = dn2.f("FallHurtMax");
        } else if (\u26032 == afi.cf) {
            this.f = true;
        }
        if (dn2.b("DropItem", 99)) {
            this.b = dn2.n("DropItem");
        }
        if (dn2.b("TileEntityData", 10)) {
            this.c = dn2.m("TileEntityData");
        }
        if (\u26032 == null || \u26032.t() == arm.a) {
            this.d = afi.m.Q();
        }
    }

    public adm j() {
        return this.o;
    }

    public void a(boolean bl2) {
        this.f = bl2;
    }

    @Override
    public boolean aJ() {
        return false;
    }

    @Override
    public void a(c c2) {
        super.a(c2);
        if (this.d != null) {
            afh afh2 = this.d.c();
            c2.a("Immitating block ID", afh.a(afh2));
            c2.a("Immitating block data", afh2.c(this.d));
        }
    }

    public alz l() {
        return this.d;
    }
}

