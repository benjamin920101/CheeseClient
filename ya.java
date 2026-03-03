/*
 * Decompiled with CFR 0.152.
 */
public class ya
implements og {
    private final acy a;
    private zx[] b = new zx[3];
    private final wn c;
    private acz d;
    private int e;

    public ya(wn wn2, acy acy2) {
        this.c = wn2;
        this.a = acy2;
    }

    @Override
    public int o_() {
        return this.b.length;
    }

    @Override
    public zx a(int n2) {
        return this.b[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.b[n22] != null) {
            int n22;
            if (n22 == 2) {
                zx zx2 = this.b[n22];
                this.b[n22] = null;
                return zx2;
            }
            if (this.b[n22].b <= n3) {
                zx zx3 = this.b[n22];
                this.b[n22] = null;
                if (this.e(n22)) {
                    this.h();
                }
                return zx3;
            }
            zx \u26032 = this.b[n22].a(n3);
            if (this.b[n22].b == 0) {
                this.b[n22] = null;
            }
            if (this.e(n22)) {
                this.h();
            }
            return \u26032;
        }
        return null;
    }

    private boolean e(int n2) {
        return n2 == 0 || n2 == 1;
    }

    @Override
    public zx b(int n2) {
        if (this.b[n2] != null) {
            zx zx2 = this.b[n2];
            this.b[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.b[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
        if (this.e(n2)) {
            this.h();
        }
    }

    @Override
    public String e_() {
        return "mob.villager";
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

    @Override
    public boolean a(wn wn2) {
        return this.a.v_() == wn2;
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
    public void p_() {
        this.h();
    }

    public void h() {
        this.d = null;
        zx zx2 = this.b[0];
        \u2603 = this.b[1];
        if (zx2 == null) {
            zx2 = \u2603;
            \u2603 = null;
        }
        if (zx2 == null) {
            this.a(2, null);
        } else {
            ada ada2 = this.a.b_(this.c);
            if (ada2 != null) {
                acz acz2 = ada2.a(zx2, \u2603, this.e);
                if (acz2 != null && !acz2.h()) {
                    this.d = acz2;
                    this.a(2, acz2.d().k());
                } else if (\u2603 != null) {
                    acz2 = ada2.a(\u2603, zx2, this.e);
                    if (acz2 != null && !acz2.h()) {
                        this.d = acz2;
                        this.a(2, acz2.d().k());
                    } else {
                        this.a(2, null);
                    }
                } else {
                    this.a(2, null);
                }
            }
        }
        this.a.a_(this.a(2));
    }

    public acz i() {
        return this.d;
    }

    public void d(int n2) {
        this.e = n2;
        this.h();
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
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2] = null;
        }
    }
}

