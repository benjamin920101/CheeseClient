/*
 * Decompiled with CFR 0.152.
 */
import java.util.concurrent.Callable;

public class wm
implements og {
    public zx[] a = new zx[36];
    public zx[] b = new zx[4];
    public int c;
    public wn d;
    private zx f;
    public boolean e;

    public wm(wn wn2) {
        this.d = wn2;
    }

    public zx h() {
        if (this.c < 9 && this.c >= 0) {
            return this.a[this.c];
        }
        return null;
    }

    public static int i() {
        return 9;
    }

    private int c(zw zw2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null || this.a[i2].b() != zw2) continue;
            return i2;
        }
        return -1;
    }

    private int a(zw zw2, int n2) {
        for (\u2603 = 0; \u2603 < this.a.length; ++\u2603) {
            if (this.a[\u2603] == null || this.a[\u2603].b() != zw2 || this.a[\u2603].i() != n2) continue;
            return \u2603;
        }
        return -1;
    }

    private int d(zx zx2) {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null || this.a[i2].b() != zx2.b() || !this.a[i2].d() || this.a[i2].b >= this.a[i2].c() || this.a[i2].b >= this.q_() || this.a[i2].f() && this.a[i2].i() != zx2.i() || !zx.a(this.a[i2], zx2)) continue;
            return i2;
        }
        return -1;
    }

    public int j() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] != null) continue;
            return i2;
        }
        return -1;
    }

    public void a(zw zw2, int n2, boolean bl2, boolean bl3) {
        zx zx2 = this.h();
        int n3 = \u2603 = bl2 ? this.a(zw2, n2) : this.c(zw2);
        if (\u2603 >= 0 && \u2603 < 9) {
            this.c = \u2603;
            return;
        }
        if (!bl3 || zw2 == null) {
            return;
        }
        int \u26032 = this.j();
        if (\u26032 >= 0 && \u26032 < 9) {
            this.c = \u26032;
        }
        if (zx2 == null || !zx2.v() || this.a(zx2.b(), zx2.h()) != this.c) {
            int n4 = this.a(zw2, n2);
            if (n4 >= 0) {
                \u2603 = this.a[n4].b;
                this.a[n4] = this.a[this.c];
            } else {
                \u2603 = 1;
            }
            this.a[this.c] = new zx(zw2, \u2603, n2);
        }
    }

    public void d(int n2) {
        if (n2 > 0) {
            n2 = 1;
        }
        if (n2 < 0) {
            n2 = -1;
        }
        this.c -= n2;
        while (this.c < 0) {
            this.c += 9;
        }
        while (this.c >= 9) {
            this.c -= 9;
        }
    }

    public int a(zw zw22, int n2, int n3, dn dn2) {
        int \u26033;
        int \u26032;
        zx zx2;
        int n4 = 0;
        for (\u26033 = 0; \u26033 < this.a.length; ++\u26033) {
            zx2 = this.a[\u26033];
            if (zx2 == null || zw22 != null && zx2.b() != zw22 || n2 > -1 && zx2.i() != n2 || dn2 != null && !dy.a(dn2, zx2.o(), true)) continue;
            \u26032 = n3 <= 0 ? zx2.b : Math.min(n3 - n4, zx2.b);
            n4 += \u26032;
            if (n3 == 0) continue;
            this.a[\u26033].b -= \u26032;
            if (this.a[\u26033].b == 0) {
                this.a[\u26033] = null;
            }
            if (n3 <= 0 || n4 < n3) continue;
            return n4;
        }
        for (\u26033 = 0; \u26033 < this.b.length; ++\u26033) {
            zx2 = this.b[\u26033];
            if (zx2 == null || zw22 != null && zx2.b() != zw22 || n2 > -1 && zx2.i() != n2 || dn2 != null && !dy.a(dn2, zx2.o(), false)) continue;
            \u26032 = n3 <= 0 ? zx2.b : Math.min(n3 - n4, zx2.b);
            n4 += \u26032;
            if (n3 == 0) continue;
            this.b[\u26033].b -= \u26032;
            if (this.b[\u26033].b == 0) {
                this.b[\u26033] = null;
            }
            if (n3 <= 0 || n4 < n3) continue;
            return n4;
        }
        if (this.f != null) {
            zw zw22;
            if (zw22 != null && this.f.b() != zw22) {
                return n4;
            }
            if (n2 > -1 && this.f.i() != n2) {
                return n4;
            }
            if (dn2 != null && !dy.a(dn2, this.f.o(), false)) {
                return n4;
            }
            \u26033 = n3 <= 0 ? this.f.b : Math.min(n3 - n4, this.f.b);
            n4 += \u26033;
            if (n3 != 0) {
                this.f.b -= \u26033;
                if (this.f.b == 0) {
                    this.f = null;
                }
                if (n3 > 0 && n4 >= n3) {
                    return n4;
                }
            }
        }
        return n4;
    }

    private int e(zx zx2) {
        int n2;
        zw zw2 = zx2.b();
        int \u26032 = zx2.b;
        int \u26033 = this.d(zx2);
        if (\u26033 < 0) {
            \u26033 = this.j();
        }
        if (\u26033 < 0) {
            return \u26032;
        }
        if (this.a[\u26033] == null) {
            this.a[\u26033] = new zx(zw2, 0, zx2.i());
            if (zx2.n()) {
                this.a[\u26033].d((dn)zx2.o().b());
            }
        }
        if ((n2 = \u26032) > this.a[\u26033].c() - this.a[\u26033].b) {
            n2 = this.a[\u26033].c() - this.a[\u26033].b;
        }
        if (n2 > this.q_() - this.a[\u26033].b) {
            n2 = this.q_() - this.a[\u26033].b;
        }
        if (n2 == 0) {
            return \u26032;
        }
        this.a[\u26033].b += n2;
        this.a[\u26033].c = 5;
        return \u26032 -= n2;
    }

    public void k() {
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            if (this.a[i2] == null) continue;
            this.a[i2].a(this.d.o, this.d, i2, this.c == i2);
        }
    }

    public boolean a(zw zw2) {
        int n2 = this.c(zw2);
        if (n2 < 0) {
            return false;
        }
        if (--this.a[n2].b <= 0) {
            this.a[n2] = null;
        }
        return true;
    }

    public boolean b(zw zw2) {
        int n2 = this.c(zw2);
        return n2 >= 0;
    }

    public boolean a(final zx zx22) {
        if (zx22 == null || zx22.b == 0 || zx22.b() == null) {
            return false;
        }
        try {
            if (!zx22.g()) {
                zx zx22;
                do {
                    int n2 = zx22.b;
                    zx22.b = this.e(zx22);
                } while (zx22.b > 0 && zx22.b < n2);
                if (zx22.b == n2 && this.d.bA.d) {
                    zx22.b = 0;
                    return true;
                }
                return zx22.b < n2;
            }
            int n3 = this.j();
            if (n3 >= 0) {
                this.a[n3] = zx.b(zx22);
                this.a[n3].c = 5;
                zx22.b = 0;
                return true;
            }
            if (this.d.bA.d) {
                zx22.b = 0;
                return true;
            }
            return false;
        }
        catch (Throwable throwable) {
            b b2 = b.a(throwable, "Adding item to inventory");
            c \u26032 = b2.a("Item being added");
            \u26032.a("Item ID", zw.b(zx22.b()));
            \u26032.a("Item data", zx22.i());
            \u26032.a("Item name", new Callable<String>(){

                public String a() throws Exception {
                    return zx22.q();
                }

                @Override
                public /* synthetic */ Object call() throws Exception {
                    return this.a();
                }
            });
            throw new e(b2);
        }
    }

    @Override
    public zx a(int n2, int n3) {
        zx[] zxArray = this.a;
        if (n2 >= this.a.length) {
            zxArray = this.b;
            n2 -= this.a.length;
        }
        if (zxArray[n2] != null) {
            if (zxArray[n2].b <= n3) {
                zx zx2 = zxArray[n2];
                zxArray[n2] = null;
                return zx2;
            }
            zx \u26032 = zxArray[n2].a(n3);
            if (zxArray[n2].b == 0) {
                zxArray[n2] = null;
            }
            return \u26032;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        zx[] zxArray = this.a;
        if (n2 >= this.a.length) {
            zxArray = this.b;
            n2 -= this.a.length;
        }
        if (zxArray[n2] != null) {
            zx zx2 = zxArray[n2];
            zxArray[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        zx[] zxArray = this.a;
        if (n2 >= zxArray.length) {
            n2 -= zxArray.length;
            zxArray = this.b;
        }
        zxArray[n2] = zx2;
    }

    public float a(afh afh2) {
        float f2 = 1.0f;
        if (this.a[this.c] != null) {
            f2 *= this.a[this.c].a(afh2);
        }
        return f2;
    }

    public du a(du du22) {
        du du22;
        dn dn2;
        int n2;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] == null) continue;
            dn2 = new dn();
            dn2.a("Slot", (byte)n2);
            this.a[n2].b(dn2);
            du22.a(dn2);
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            if (this.b[n2] == null) continue;
            dn2 = new dn();
            dn2.a("Slot", (byte)(n2 + 100));
            this.b[n2].b(dn2);
            du22.a(dn2);
        }
        return du22;
    }

    public void b(du du2) {
        this.a = new zx[36];
        this.b = new zx[4];
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn2 = du2.b(i2);
            int \u26032 = dn2.d("Slot") & 0xFF;
            zx \u26033 = zx.a(dn2);
            if (\u26033 == null) continue;
            if (\u26032 >= 0 && \u26032 < this.a.length) {
                this.a[\u26032] = \u26033;
            }
            if (\u26032 < 100 || \u26032 >= this.b.length + 100) continue;
            this.b[\u26032 - 100] = \u26033;
        }
    }

    @Override
    public int o_() {
        return this.a.length + 4;
    }

    @Override
    public zx a(int n2) {
        zx[] zxArray = this.a;
        if (n2 >= zxArray.length) {
            n2 -= zxArray.length;
            zxArray = this.b;
        }
        return zxArray[n2];
    }

    @Override
    public String e_() {
        return "container.inventory";
    }

    @Override
    public boolean l_() {
        return false;
    }

    @Override
    public eu f_() {
        if (this.l_()) {
            return new fa(this.e_());
        }
        return new fb(this.e_(), new Object[0]);
    }

    @Override
    public int q_() {
        return 64;
    }

    public boolean b(afh afh2) {
        if (afh2.t().l()) {
            return true;
        }
        zx zx2 = this.a(this.c);
        if (zx2 != null) {
            return zx2.b(afh2);
        }
        return false;
    }

    public zx e(int n2) {
        return this.b[n2];
    }

    public int m() {
        int n2 = 0;
        for (\u2603 = 0; \u2603 < this.b.length; ++\u2603) {
            if (this.b[\u2603] == null || !(this.b[\u2603].b() instanceof yj)) continue;
            \u2603 = ((yj)this.b[\u2603].b()).c;
            n2 += \u2603;
        }
        return n2;
    }

    public void a(float f2) {
        if ((f2 /= 4.0f) < 1.0f) {
            f2 = 1.0f;
        }
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            if (this.b[i2] == null || !(this.b[i2].b() instanceof yj)) continue;
            this.b[i2].a((int)f2, (pr)this.d);
            if (this.b[i2].b != 0) continue;
            this.b[i2] = null;
        }
    }

    public void n() {
        int n2;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] == null) continue;
            this.d.a(this.a[n2], true, false);
            this.a[n2] = null;
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            if (this.b[n2] == null) continue;
            this.d.a(this.b[n2], true, false);
            this.b[n2] = null;
        }
    }

    @Override
    public void p_() {
        this.e = true;
    }

    public void b(zx zx2) {
        this.f = zx2;
    }

    public zx p() {
        return this.f;
    }

    @Override
    public boolean a(wn wn2) {
        if (this.d.I) {
            return false;
        }
        return !(wn2.h(this.d) > 64.0);
    }

    public boolean c(zx zx2) {
        int n2;
        for (n2 = 0; n2 < this.b.length; ++n2) {
            if (this.b[n2] == null || !this.b[n2].a(zx2)) continue;
            return true;
        }
        for (n2 = 0; n2 < this.a.length; ++n2) {
            if (this.a[n2] == null || !this.a[n2].a(zx2)) continue;
            return true;
        }
        return false;
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

    public void b(wm wm22) {
        wm wm22;
        int n2;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            this.a[n2] = zx.b(wm22.a[n2]);
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            this.b[n2] = zx.b(wm22.b[n2]);
        }
        this.c = wm22.c;
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
        int n2;
        for (n2 = 0; n2 < this.a.length; ++n2) {
            this.a[n2] = null;
        }
        for (n2 = 0; n2 < this.b.length; ++n2) {
            this.b[n2] = null;
        }
    }
}

