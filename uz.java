/*
 * Decompiled with CFR 0.152.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class uz
extends pk {
    private static final Logger b = LogManager.getLogger();
    private int c;
    private int d;
    private int e = 5;
    private String f;
    private String g;
    public float a = (float)(Math.random() * Math.PI * 2.0);

    public uz(adm adm2, double d2, double d3, double d4) {
        super(adm2);
        this.a(0.25f, 0.25f);
        this.b(d2, d3, d4);
        this.y = (float)(Math.random() * 360.0);
        this.v = (float)(Math.random() * (double)0.2f - (double)0.1f);
        this.w = 0.2f;
        this.x = (float)(Math.random() * (double)0.2f - (double)0.1f);
    }

    public uz(adm adm2, double d2, double d3, double d4, zx zx2) {
        this(adm2, d2, d3, d4);
        this.a(zx2);
    }

    @Override
    protected boolean s_() {
        return false;
    }

    public uz(adm adm2) {
        super(adm2);
        this.a(0.25f, 0.25f);
        this.a(new zx(afi.a, 0));
    }

    @Override
    protected void h() {
        this.H().a(10, 5);
    }

    @Override
    public void t_() {
        boolean bl2;
        if (this.l() == null) {
            this.J();
            return;
        }
        super.t_();
        if (this.d > 0 && this.d != Short.MAX_VALUE) {
            --this.d;
        }
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        this.w -= (double)0.04f;
        this.T = this.j(this.s, (this.aR().b + this.aR().e) / 2.0, this.u);
        this.d(this.v, this.w, this.x);
        boolean bl3 = bl2 = (int)this.p != (int)this.s || (int)this.q != (int)this.t || (int)this.r != (int)this.u;
        if (bl2 || this.W % 25 == 0) {
            if (this.o.p(new cj(this)).c().t() == arm.i) {
                this.w = 0.2f;
                this.v = (this.V.nextFloat() - this.V.nextFloat()) * 0.2f;
                this.x = (this.V.nextFloat() - this.V.nextFloat()) * 0.2f;
                this.a("random.fizz", 0.4f, 2.0f + this.V.nextFloat() * 0.4f);
            }
            if (!this.o.D) {
                this.w();
            }
        }
        float \u26032 = 0.98f;
        if (this.C) {
            \u26032 = this.o.p((cj)new cj((int)ns.c((double)this.s), (int)(ns.c((double)this.aR().b) - 1), (int)ns.c((double)this.u))).c().L * 0.98f;
        }
        this.v *= (double)\u26032;
        this.w *= (double)0.98f;
        this.x *= (double)\u26032;
        if (this.C) {
            this.w *= -0.5;
        }
        if (this.c != Short.MIN_VALUE) {
            ++this.c;
        }
        this.W();
        if (!this.o.D && this.c >= 6000) {
            this.J();
        }
    }

    private void w() {
        for (uz uz2 : this.o.a(uz.class, this.aR().b(0.5, 0.0, 0.5))) {
            this.a(uz2);
        }
    }

    private boolean a(uz uz2) {
        if (uz2 == this) {
            return false;
        }
        if (!uz2.ai() || !this.ai()) {
            return false;
        }
        zx zx2 = this.l();
        \u2603 = uz2.l();
        if (this.d == Short.MAX_VALUE || uz2.d == Short.MAX_VALUE) {
            return false;
        }
        if (this.c == Short.MIN_VALUE || uz2.c == Short.MIN_VALUE) {
            return false;
        }
        if (\u2603.b() != zx2.b()) {
            return false;
        }
        if (\u2603.n() ^ zx2.n()) {
            return false;
        }
        if (\u2603.n() && !\u2603.o().equals(zx2.o())) {
            return false;
        }
        if (\u2603.b() == null) {
            return false;
        }
        if (\u2603.b().k() && \u2603.i() != zx2.i()) {
            return false;
        }
        if (\u2603.b < zx2.b) {
            return uz2.a(this);
        }
        if (\u2603.b + zx2.b > \u2603.c()) {
            return false;
        }
        \u2603.b += zx2.b;
        uz2.d = Math.max(uz2.d, this.d);
        uz2.c = Math.min(uz2.c, this.c);
        uz2.a(\u2603);
        this.J();
        return true;
    }

    public void j() {
        this.c = 4800;
    }

    @Override
    public boolean W() {
        if (this.o.a(this.aR(), arm.h, this)) {
            if (!this.Y && !this.aa) {
                this.X();
            }
            this.Y = true;
        } else {
            this.Y = false;
        }
        return this.Y;
    }

    @Override
    protected void f(int n2) {
        this.a(ow.a, (float)n2);
    }

    @Override
    public boolean a(ow ow2, float f2) {
        if (this.b(ow2)) {
            return false;
        }
        if (this.l() != null && this.l().b() == zy.bZ && ow2.c()) {
            return false;
        }
        this.ac();
        this.e = (int)((float)this.e - f2);
        if (this.e <= 0) {
            this.J();
        }
        return false;
    }

    @Override
    public void b(dn dn2) {
        dn2.a("Health", (short)((byte)this.e));
        dn2.a("Age", (short)this.c);
        dn2.a("PickupDelay", (short)this.d);
        if (this.n() != null) {
            dn2.a("Thrower", this.f);
        }
        if (this.m() != null) {
            dn2.a("Owner", this.g);
        }
        if (this.l() != null) {
            dn2.a("Item", this.l().b(new dn()));
        }
    }

    @Override
    public void a(dn dn2) {
        this.e = dn2.e("Health") & 0xFF;
        this.c = dn2.e("Age");
        if (dn2.c("PickupDelay")) {
            this.d = dn2.e("PickupDelay");
        }
        if (dn2.c("Owner")) {
            this.g = dn2.j("Owner");
        }
        if (dn2.c("Thrower")) {
            this.f = dn2.j("Thrower");
        }
        \u2603 = dn2.m("Item");
        this.a(zx.a(\u2603));
        if (this.l() == null) {
            this.J();
        }
    }

    @Override
    public void d(wn wn2) {
        if (this.o.D) {
            return;
        }
        zx zx2 = this.l();
        int \u26032 = zx2.b;
        if (this.d == 0 && (this.g == null || 6000 - this.c <= 200 || this.g.equals(wn2.e_())) && wn2.bi.a(zx2)) {
            if (zx2.b() == zw.a(afi.r)) {
                wn2.b(mr.g);
            }
            if (zx2.b() == zw.a(afi.s)) {
                wn2.b(mr.g);
            }
            if (zx2.b() == zy.aF) {
                wn2.b(mr.t);
            }
            if (zx2.b() == zy.i) {
                wn2.b(mr.w);
            }
            if (zx2.b() == zy.bv) {
                wn2.b(mr.A);
            }
            if (zx2.b() == zy.i && this.n() != null && (\u2603 = this.o.a(this.n())) != null && \u2603 != wn2) {
                \u2603.b(mr.x);
            }
            if (!this.R()) {
                this.o.a((pk)wn2, "random.pop", 0.2f, ((this.V.nextFloat() - this.V.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            }
            wn2.a(this, \u26032);
            if (zx2.b <= 0) {
                this.J();
            }
        }
    }

    @Override
    public String e_() {
        if (this.l_()) {
            return this.aM();
        }
        return di.a("item." + this.l().a());
    }

    @Override
    public boolean aD() {
        return false;
    }

    @Override
    public void c(int n2) {
        super.c(n2);
        if (!this.o.D) {
            this.w();
        }
    }

    public zx l() {
        zx zx2 = this.H().f(10);
        if (zx2 == null) {
            if (this.o != null) {
                b.error("Item entity " + this.F() + " has no item?!");
            }
            return new zx(afi.b);
        }
        return zx2;
    }

    public void a(zx zx2) {
        this.H().b(10, zx2);
        this.H().i(10);
    }

    public String m() {
        return this.g;
    }

    public void b(String string) {
        this.g = string;
    }

    public String n() {
        return this.f;
    }

    public void c(String string) {
        this.f = string;
    }

    public int o() {
        return this.c;
    }

    public void p() {
        this.d = 10;
    }

    public void q() {
        this.d = 0;
    }

    public void r() {
        this.d = Short.MAX_VALUE;
    }

    public void a(int n2) {
        this.d = n2;
    }

    public boolean s() {
        return this.d > 0;
    }

    public void u() {
        this.c = -6000;
    }

    public void v() {
        this.r();
        this.c = 5999;
    }
}

