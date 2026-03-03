/*
 * Decompiled with CFR 0.152.
 */
public class xp
implements og {
    private final zx[] a;
    private final int b;
    private final int c;
    private final xi d;

    public xp(xi xi2, int n2, int n3) {
        \u2603 = n2 * n3;
        this.a = new zx[\u2603];
        this.d = xi2;
        this.b = n2;
        this.c = n3;
    }

    @Override
    public int o_() {
        return this.a.length;
    }

    @Override
    public zx a(int n2) {
        if (n2 >= this.o_()) {
            return null;
        }
        return this.a[n2];
    }

    public zx c(int n2, int n3) {
        if (n2 < 0 || n2 >= this.b || n3 < 0 || n3 > this.c) {
            return null;
        }
        return this.a(n2 + n3 * this.b);
    }

    @Override
    public String e_() {
        return "container.crafting";
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
    public zx b(int n2) {
        if (this.a[n2] != null) {
            zx zx2 = this.a[n2];
            this.a[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.a[n22] != null) {
            int n22;
            if (this.a[n22].b <= n3) {
                zx zx2 = this.a[n22];
                this.a[n22] = null;
                this.d.a(this);
                return zx2;
            }
            zx \u26032 = this.a[n22].a(n3);
            if (this.a[n22].b == 0) {
                this.a[n22] = null;
            }
            this.d.a(this);
            return \u26032;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.a[n2] = zx2;
        this.d.a(this);
    }

    @Override
    public int q_() {
        return 64;
    }

    @Override
    public void p_() {
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
        for (int i2 = 0; i2 < this.a.length; ++i2) {
            this.a[i2] = null;
        }
    }

    public int h() {
        return this.c;
    }

    public int i() {
        return this.b;
    }
}

