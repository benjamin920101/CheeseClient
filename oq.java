/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.List;

public class oq
implements og {
    private String a;
    private int b;
    private zx[] c;
    private List<oh> d;
    private boolean e;

    public oq(String string, boolean bl2, int n2) {
        this.a = string;
        this.e = bl2;
        this.b = n2;
        this.c = new zx[n2];
    }

    public oq(eu eu2, int n2) {
        this(eu2.c(), true, n2);
    }

    public void a(oh oh2) {
        if (this.d == null) {
            this.d = Lists.newArrayList();
        }
        this.d.add(oh2);
    }

    public void b(oh oh2) {
        this.d.remove(oh2);
    }

    @Override
    public zx a(int n2) {
        if (n2 < 0 || n2 >= this.c.length) {
            return null;
        }
        return this.c[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.c[n22] != null) {
            int n22;
            if (this.c[n22].b <= n3) {
                zx zx2 = this.c[n22];
                this.c[n22] = null;
                this.p_();
                return zx2;
            }
            zx \u26032 = this.c[n22].a(n3);
            if (this.c[n22].b == 0) {
                this.c[n22] = null;
            }
            this.p_();
            return \u26032;
        }
        return null;
    }

    public zx a(zx zx2) {
        zx zx3;
        zx3 = zx2.k();
        for (int i2 = 0; i2 < this.b; ++i2) {
            zx zx4 = this.a(i2);
            if (zx4 == null) {
                this.a(i2, zx3);
                this.p_();
                return null;
            }
            if (!zx.c(zx4, zx3) || (\u2603 = Math.min(zx3.b, (\u2603 = Math.min(this.q_(), zx4.c())) - zx4.b)) <= 0) continue;
            zx4.b += \u2603;
            zx3.b -= \u2603;
            if (zx3.b > 0) continue;
            this.p_();
            return null;
        }
        if (zx3.b != zx2.b) {
            this.p_();
        }
        return zx3;
    }

    @Override
    public zx b(int n2) {
        if (this.c[n2] != null) {
            zx zx2 = this.c[n2];
            this.c[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.c[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
        this.p_();
    }

    @Override
    public int o_() {
        return this.b;
    }

    @Override
    public String e_() {
        return this.a;
    }

    @Override
    public boolean l_() {
        return this.e;
    }

    public void a(String string) {
        this.e = true;
        this.a = string;
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
    public void p_() {
        if (this.d != null) {
            for (int i2 = 0; i2 < this.d.size(); ++i2) {
                this.d.get(i2).a(this);
            }
        }
    }

    @Override
    public boolean a(wn wn2) {
        return true;
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
        for (int i2 = 0; i2 < this.c.length; ++i2) {
            this.c[i2] = null;
        }
    }
}

