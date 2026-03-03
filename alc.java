/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class alc
extends alk
implements og {
    private static final Random f = new Random();
    private zx[] g = new zx[9];
    protected String a;

    @Override
    public int o_() {
        return 9;
    }

    @Override
    public zx a(int n2) {
        return this.g[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.g[n22] != null) {
            int n22;
            if (this.g[n22].b <= n3) {
                zx zx2 = this.g[n22];
                this.g[n22] = null;
                this.p_();
                return zx2;
            }
            zx \u26032 = this.g[n22].a(n3);
            if (this.g[n22].b == 0) {
                this.g[n22] = null;
            }
            this.p_();
            return \u26032;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (this.g[n2] != null) {
            zx zx2 = this.g[n2];
            this.g[n2] = null;
            return zx2;
        }
        return null;
    }

    public int m() {
        int n2 = -1;
        \u2603 = 1;
        for (\u2603 = 0; \u2603 < this.g.length; ++\u2603) {
            if (this.g[\u2603] == null || f.nextInt(\u2603++) != 0) continue;
            n2 = \u2603;
        }
        return n2;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.g[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
        this.p_();
    }

    public int a(zx zx2) {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            if (this.g[i2] != null && this.g[i2].b() != null) continue;
            this.a(i2, zx2);
            return i2;
        }
        return -1;
    }

    @Override
    public String e_() {
        return this.l_() ? this.a : "container.dispenser";
    }

    public void a(String string) {
        this.a = string;
    }

    @Override
    public boolean l_() {
        return this.a != null;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.g = new zx[this.o_()];
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            int \u26032 = dn3.d("Slot") & 0xFF;
            if (\u26032 < 0 || \u26032 >= this.g.length) continue;
            this.g[\u26032] = zx.a(dn3);
        }
        if (dn2.b("CustomName", 8)) {
            this.a = dn2.j("CustomName");
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        du du2 = new du();
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            if (this.g[i2] == null) continue;
            dn dn3 = new dn();
            dn3.a("Slot", (byte)i2);
            this.g[i2].b(dn3);
            du2.a(dn3);
        }
        dn22.a("Items", du2);
        if (this.l_()) {
            dn22.a("CustomName", this.a);
        }
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
    public String k() {
        return "minecraft:dispenser";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xr(wm2, this);
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
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            this.g[i2] = null;
        }
    }
}

