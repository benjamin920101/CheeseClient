/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;
import java.util.List;

public class akx
extends alk
implements km,
ot {
    private static final int[] a = new int[]{3};
    private static final int[] f = new int[]{0, 1, 2};
    private zx[] g = new zx[4];
    private int h;
    private boolean[] i;
    private zw j;
    private String k;

    @Override
    public String e_() {
        return this.l_() ? this.k : "container.brewing";
    }

    @Override
    public boolean l_() {
        return this.k != null && this.k.length() > 0;
    }

    public void a(String string) {
        this.k = string;
    }

    @Override
    public int o_() {
        return this.g.length;
    }

    @Override
    public void c() {
        boolean[] blArray;
        if (this.h > 0) {
            --this.h;
            if (this.h == 0) {
                this.o();
                this.p_();
            } else if (!this.n()) {
                this.h = 0;
                this.p_();
            } else if (this.j != this.g[3].b()) {
                this.h = 0;
                this.p_();
            }
        } else if (this.n()) {
            this.h = 400;
            this.j = this.g[3].b();
        }
        if (!this.b.D && !Arrays.equals(blArray = this.m(), this.i)) {
            this.i = blArray;
            alz alz2 = this.b.p(this.v());
            if (!(alz2.c() instanceof afl)) {
                return;
            }
            for (int i2 = 0; i2 < afl.a.length; ++i2) {
                alz2 = alz2.a(afl.a[i2], blArray[i2]);
            }
            this.b.a(this.c, alz2, 2);
        }
    }

    private boolean n() {
        if (this.g[3] == null || this.g[3].b <= 0) {
            return false;
        }
        zx zx2 = this.g[3];
        if (!zx2.b().l(zx2)) {
            return false;
        }
        boolean \u26032 = false;
        for (int i2 = 0; i2 < 3; ++i2) {
            if (this.g[i2] == null || this.g[i2].b() != zy.bz) continue;
            \u2603 = this.g[i2].i();
            \u2603 = this.c(\u2603, zx2);
            if (!aai.f(\u2603) && aai.f(\u2603)) {
                \u26032 = true;
                break;
            }
            List<pf> list = zy.bz.e(\u2603);
            \u2603 = zy.bz.e(\u2603);
            if (\u2603 > 0 && list == \u2603 || list != null && (list.equals(\u2603) || \u2603 == null) || \u2603 == \u2603) continue;
            \u26032 = true;
            break;
        }
        return \u26032;
    }

    private void o() {
        if (!this.n()) {
            return;
        }
        zx zx2 = this.g[3];
        for (int i2 = 0; i2 < 3; ++i2) {
            if (this.g[i2] == null || this.g[i2].b() != zy.bz) continue;
            \u2603 = this.g[i2].i();
            \u2603 = this.c(\u2603, zx2);
            List<pf> list = zy.bz.e(\u2603);
            \u2603 = zy.bz.e(\u2603);
            if (\u2603 > 0 && list == \u2603 || list != null && (list.equals(\u2603) || \u2603 == null)) {
                if (aai.f(\u2603) || !aai.f(\u2603)) continue;
                this.g[i2].b(\u2603);
                continue;
            }
            if (\u2603 == \u2603) continue;
            this.g[i2].b(\u2603);
        }
        if (zx2.b().r()) {
            this.g[3] = new zx(zx2.b().q());
        } else {
            --this.g[3].b;
            if (this.g[3].b <= 0) {
                this.g[3] = null;
            }
        }
    }

    private int c(int n2, zx zx2) {
        if (zx2 == null) {
            return n2;
        }
        if (zx2.b().l(zx2)) {
            return abe.a(n2, zx2.b().j(zx2));
        }
        return n2;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.g = new zx[this.o_()];
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            byte \u26032 = dn3.d("Slot");
            if (\u26032 < 0 || \u26032 >= this.g.length) continue;
            this.g[\u26032] = zx.a(dn3);
        }
        this.h = dn2.e("BrewTime");
        if (dn2.b("CustomName", 8)) {
            this.k = dn2.j("CustomName");
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        dn22.a("BrewTime", (short)this.h);
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
            dn22.a("CustomName", this.k);
        }
    }

    @Override
    public zx a(int n2) {
        if (n2 >= 0 && n2 < this.g.length) {
            return this.g[n2];
        }
        return null;
    }

    @Override
    public zx a(int n2, int n3) {
        if (n2 >= 0 && n2 < this.g.length) {
            zx zx2 = this.g[n2];
            this.g[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (n2 >= 0 && n2 < this.g.length) {
            zx zx2 = this.g[n2];
            this.g[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        if (n2 >= 0 && n2 < this.g.length) {
            this.g[n2] = zx2;
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
        if (n2 == 3) {
            return zx2.b().l(zx2);
        }
        return zx2.b() == zy.bz || zx2.b() == zy.bA;
    }

    public boolean[] m() {
        boolean[] blArray = new boolean[3];
        for (int i2 = 0; i2 < 3; ++i2) {
            if (this.g[i2] == null) continue;
            blArray[i2] = true;
        }
        return blArray;
    }

    @Override
    public int[] a(cq cq2) {
        if (cq2 == cq.b) {
            return a;
        }
        return f;
    }

    @Override
    public boolean a(int n2, zx zx2, cq cq2) {
        return this.b(n2, zx2);
    }

    @Override
    public boolean b(int n2, zx zx2, cq cq2) {
        return true;
    }

    @Override
    public String k() {
        return "minecraft:brewing_stand";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xm(wm2, this);
    }

    @Override
    public int a_(int n2) {
        switch (n2) {
            case 0: {
                return this.h;
            }
        }
        return 0;
    }

    @Override
    public void b(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.h = n3;
            }
        }
    }

    @Override
    public int g() {
        return 1;
    }

    @Override
    public void l() {
        for (int i2 = 0; i2 < this.g.length; ++i2) {
            this.g[i2] = null;
        }
    }
}

