/*
 * Decompiled with CFR 0.152.
 */
public class alh
extends alk
implements km,
ot {
    private static final int[] a = new int[]{0};
    private static final int[] f = new int[]{2, 1};
    private static final int[] g = new int[]{1};
    private zx[] h = new zx[3];
    private int i;
    private int j;
    private int k;
    private int l;
    private String m;

    @Override
    public int o_() {
        return this.h.length;
    }

    @Override
    public zx a(int n2) {
        return this.h[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.h[n22] != null) {
            int n22;
            if (this.h[n22].b <= n3) {
                zx zx2 = this.h[n22];
                this.h[n22] = null;
                return zx2;
            }
            zx \u26032 = this.h[n22].a(n3);
            if (this.h[n22].b == 0) {
                this.h[n22] = null;
            }
            return \u26032;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (this.h[n2] != null) {
            zx zx2 = this.h[n2];
            this.h[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        boolean bl2 = zx2 != null && zx2.a(this.h[n2]) && zx.a(zx2, this.h[n2]);
        this.h[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
        if (n2 == 0 && !bl2) {
            this.l = this.a(zx2);
            this.k = 0;
            this.p_();
        }
    }

    @Override
    public String e_() {
        return this.l_() ? this.m : "container.furnace";
    }

    @Override
    public boolean l_() {
        return this.m != null && this.m.length() > 0;
    }

    public void a(String string) {
        this.m = string;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.h = new zx[this.o_()];
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            byte \u26032 = dn3.d("Slot");
            if (\u26032 < 0 || \u26032 >= this.h.length) continue;
            this.h[\u26032] = zx.a(dn3);
        }
        this.i = dn2.e("BurnTime");
        this.k = dn2.e("CookTime");
        this.l = dn2.e("CookTimeTotal");
        this.j = alh.b(this.h[1]);
        if (dn2.b("CustomName", 8)) {
            this.m = dn2.j("CustomName");
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        dn22.a("BurnTime", (short)this.i);
        dn22.a("CookTime", (short)this.k);
        dn22.a("CookTimeTotal", (short)this.l);
        du du2 = new du();
        for (int i2 = 0; i2 < this.h.length; ++i2) {
            if (this.h[i2] == null) continue;
            dn dn3 = new dn();
            dn3.a("Slot", (byte)i2);
            this.h[i2].b(dn3);
            du2.a(dn3);
        }
        dn22.a("Items", du2);
        if (this.l_()) {
            dn22.a("CustomName", this.m);
        }
    }

    @Override
    public int q_() {
        return 64;
    }

    public boolean m() {
        return this.i > 0;
    }

    public static boolean a(og og2) {
        return og2.a_(0) > 0;
    }

    @Override
    public void c() {
        boolean bl2;
        boolean bl3 = this.m();
        bl2 = false;
        if (this.m()) {
            --this.i;
        }
        if (!this.b.D) {
            if (this.m() || this.h[1] != null && this.h[0] != null) {
                if (!this.m() && this.o()) {
                    this.j = this.i = alh.b(this.h[1]);
                    if (this.m()) {
                        bl2 = true;
                        if (this.h[1] != null) {
                            --this.h[1].b;
                            if (this.h[1].b == 0) {
                                zw zw2 = this.h[1].b().q();
                                zx zx2 = this.h[1] = zw2 != null ? new zx(zw2) : null;
                            }
                        }
                    }
                }
                if (this.m() && this.o()) {
                    ++this.k;
                    if (this.k == this.l) {
                        this.k = 0;
                        this.l = this.a(this.h[0]);
                        this.n();
                        bl2 = true;
                    }
                } else {
                    this.k = 0;
                }
            } else if (!this.m() && this.k > 0) {
                this.k = ns.a(this.k - 2, 0, this.l);
            }
            if (bl3 != this.m()) {
                bl2 = true;
                ahb.a(this.m(), this.b, this.c);
            }
        }
        if (bl2) {
            this.p_();
        }
    }

    public int a(zx zx2) {
        return 200;
    }

    private boolean o() {
        if (this.h[0] == null) {
            return false;
        }
        zx zx2 = abo.a().a(this.h[0]);
        if (zx2 == null) {
            return false;
        }
        if (this.h[2] == null) {
            return true;
        }
        if (!this.h[2].a(zx2)) {
            return false;
        }
        if (this.h[2].b < this.q_() && this.h[2].b < this.h[2].c()) {
            return true;
        }
        return this.h[2].b < zx2.c();
    }

    public void n() {
        if (!this.o()) {
            return;
        }
        zx zx2 = abo.a().a(this.h[0]);
        if (this.h[2] == null) {
            this.h[2] = zx2.k();
        } else if (this.h[2].b() == zx2.b()) {
            ++this.h[2].b;
        }
        if (this.h[0].b() == zw.a(afi.v) && this.h[0].i() == 1 && this.h[1] != null && this.h[1].b() == zy.aw) {
            this.h[1] = new zx(zy.ax);
        }
        --this.h[0].b;
        if (this.h[0].b <= 0) {
            this.h[0] = null;
        }
    }

    public static int b(zx zx2) {
        if (zx2 == null) {
            return 0;
        }
        zw zw2 = zx2.b();
        if (zw2 instanceof yo && afh.a(zw2) != afi.a) {
            afh afh2 = afh.a(zw2);
            if (afh2 == afi.bM) {
                return 150;
            }
            if (afh2.t() == arm.d) {
                return 300;
            }
            if (afh2 == afi.cA) {
                return 16000;
            }
        }
        if (zw2 instanceof za && ((za)zw2).h().equals("WOOD")) {
            return 200;
        }
        if (zw2 instanceof aay && ((aay)zw2).h().equals("WOOD")) {
            return 200;
        }
        if (zw2 instanceof zv && ((zv)zw2).g().equals("WOOD")) {
            return 200;
        }
        if (zw2 == zy.y) {
            return 100;
        }
        if (zw2 == zy.h) {
            return 1600;
        }
        if (zw2 == zy.ay) {
            return 20000;
        }
        if (zw2 == zw.a(afi.g)) {
            return 100;
        }
        if (zw2 == zy.bv) {
            return 2400;
        }
        return 0;
    }

    public static boolean c(zx zx2) {
        return alh.b(zx2) > 0;
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
        if (n2 == 2) {
            return false;
        }
        if (n2 == 1) {
            return alh.c(zx2) || xt.c_(zx2);
        }
        return true;
    }

    @Override
    public int[] a(cq cq2) {
        if (cq2 == cq.a) {
            return f;
        }
        if (cq2 == cq.b) {
            return a;
        }
        return g;
    }

    @Override
    public boolean a(int n2, zx zx2, cq cq2) {
        return this.b(n2, zx2);
    }

    @Override
    public boolean b(int n2, zx zx2, cq cq2) {
        return cq2 != cq.a || n2 != 1 || (\u2603 = zx2.b()) == zy.ax || \u2603 == zy.aw;
    }

    @Override
    public String k() {
        return "minecraft:furnace";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xu(wm2, this);
    }

    @Override
    public int a_(int n2) {
        switch (n2) {
            case 0: {
                return this.i;
            }
            case 1: {
                return this.j;
            }
            case 2: {
                return this.k;
            }
            case 3: {
                return this.l;
            }
        }
        return 0;
    }

    @Override
    public void b(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.i = n3;
                break;
            }
            case 1: {
                this.j = n3;
                break;
            }
            case 2: {
                this.k = n3;
                break;
            }
            case 3: {
                this.l = n3;
            }
        }
    }

    @Override
    public int g() {
        return 4;
    }

    @Override
    public void l() {
        for (int i2 = 0; i2 < this.h.length; ++i2) {
            this.h[i2] = null;
        }
    }
}

