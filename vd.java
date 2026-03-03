/*
 * Decompiled with CFR 0.152.
 */
public abstract class vd
extends va
implements oo {
    private zx[] a = new zx[36];
    private boolean b = true;

    public vd(adm adm2) {
        super(adm2);
    }

    public vd(adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4);
    }

    @Override
    public void a(ow ow2) {
        super.a(ow2);
        if (this.o.Q().b("doEntityDrops")) {
            oi.a(this.o, this, (og)this);
        }
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
    public void p_() {
    }

    @Override
    public boolean a(wn wn2) {
        if (this.I) {
            return false;
        }
        return !(wn2.h(this) > 64.0);
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
    public String e_() {
        return this.l_() ? this.aM() : "container.minecart";
    }

    @Override
    public int q_() {
        return 64;
    }

    @Override
    public void c(int n2) {
        this.b = false;
        super.c(n2);
    }

    @Override
    public void J() {
        if (this.b) {
            oi.a(this.o, this, (og)this);
        }
        super.J();
    }

    @Override
    protected void b(dn dn22) {
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
    }

    @Override
    protected void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.a = new zx[this.o_()];
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            int \u26032 = dn3.d("Slot") & 0xFF;
            if (\u26032 < 0 || \u26032 >= this.a.length) continue;
            this.a[\u26032] = zx.a(dn3);
        }
    }

    @Override
    public boolean e(wn wn2) {
        if (!this.o.D) {
            wn2.a(this);
        }
        return true;
    }

    @Override
    protected void o() {
        int n2 = 15 - xi.b(this);
        float \u26032 = 0.98f + (float)n2 * 0.001f;
        this.v *= (double)\u26032;
        this.w *= 0.0;
        this.x *= (double)\u26032;
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
    public boolean r_() {
        return false;
    }

    @Override
    public void a(on on2) {
    }

    @Override
    public on i() {
        return on.a;
    }

    @Override
    public void l() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = null;
        }
    }
}

