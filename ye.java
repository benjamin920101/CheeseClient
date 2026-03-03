/*
 * Decompiled with CFR 0.152.
 */
public class ye
implements og {
    private zx[] a = new zx[1];

    @Override
    public int o_() {
        return 1;
    }

    @Override
    public zx a(int n2) {
        return this.a[0];
    }

    @Override
    public String e_() {
        return "Result";
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
    public zx a(int n2, int n3) {
        if (this.a[0] != null) {
            zx zx2 = this.a[0];
            this.a[0] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (this.a[0] != null) {
            zx zx2 = this.a[0];
            this.a[0] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.a[0] = zx2;
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
}

