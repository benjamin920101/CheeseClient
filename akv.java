/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;

public class akv
extends alk
implements km,
og {
    public static final pe[][] a = new pe[][]{{pe.c, pe.e}, {pe.m, pe.j}, {pe.g}, {pe.l}};
    private final List<a> f = Lists.newArrayList();
    private long g;
    private float h;
    private boolean i;
    private int j = -1;
    private int k;
    private int l;
    private zx m;
    private String n;

    @Override
    public void c() {
        if (this.b.K() % 80L == 0L) {
            this.m();
        }
    }

    public void m() {
        this.B();
        this.A();
    }

    private void A() {
        if (this.i && this.j > 0 && !this.b.D && this.k > 0) {
            double d2 = this.j * 10 + 10;
            int \u26032 = 0;
            if (this.j >= 4 && this.k == this.l) {
                \u26032 = 1;
            }
            int \u26033 = this.c.n();
            int \u26034 = this.c.o();
            int \u26035 = this.c.p();
            aug \u26036 = new aug(\u26033, \u26034, \u26035, \u26033 + 1, \u26034 + 1, \u26035 + 1).b(d2, d2, d2).a(0.0, this.b.U(), 0.0);
            List<wn> \u26037 = this.b.a(wn.class, \u26036);
            for (wn wn2 : \u26037) {
                wn2.c(new pf(this.k, 180, \u26032, true, true));
            }
            if (this.j >= 4 && this.k != this.l && this.l > 0) {
                for (wn wn2 : \u26037) {
                    wn2.c(new pf(this.l, 180, 0, true, true));
                }
            }
        }
    }

    private void B() {
        int n2 = this.j;
        int n3 = this.c.n();
        \u2603 = this.c.o();
        \u2603 = this.c.p();
        this.j = 0;
        this.f.clear();
        this.i = true;
        a \u26032 = new a(tv.a(zd.a));
        this.f.add(\u26032);
        boolean \u26033 = true;
        cj.a \u26034 = new cj.a();
        for (int n32 = \u2603 + 1; n32 < 256; ++n32) {
            float[] fArray;
            alz alz2 = this.b.p(\u26034.c(n3, n32, \u2603));
            if (alz2.c() == afi.cG) {
                float[] fArray2 = tv.a(alz2.b(ajs.a));
            } else if (alz2.c() == afi.cH) {
                fArray = tv.a(alz2.b(ajt.a));
            } else {
                if (alz2.c().p() < 15 || alz2.c() == afi.h) {
                    \u26032.a();
                    continue;
                }
                this.i = false;
                this.f.clear();
                break;
            }
            if (!\u26033) {
                fArray = new float[]{(\u26032.b()[0] + fArray[0]) / 2.0f, (\u26032.b()[1] + fArray[1]) / 2.0f, (\u26032.b()[2] + fArray[2]) / 2.0f};
            }
            if (Arrays.equals(fArray, \u26032.b())) {
                \u26032.a();
            } else {
                \u26032 = new a(fArray);
                this.f.add(\u26032);
            }
            \u26033 = false;
        }
        if (this.i) {
            int n4;
            int n5 = 1;
            while (n5 <= 4 && (n4 = \u2603 - n5) >= 0) {
                boolean \u26035 = true;
                block2: for (int i2 = n3 - n5; i2 <= n3 + n5 && \u26035; ++i2) {
                    for (\u2603 = \u2603 - n5; \u2603 <= \u2603 + n5; ++\u2603) {
                        afh afh2 = this.b.p(new cj(i2, n4, \u2603)).c();
                        if (afh2 == afi.bT || afh2 == afi.R || afh2 == afi.ah || afh2 == afi.S) continue;
                        \u26035 = false;
                        continue block2;
                    }
                }
                if (!\u26035) break;
                this.j = n5++;
            }
            if (this.j == 0) {
                this.i = false;
            }
        }
        if (!this.b.D && this.j == 4 && n2 < this.j) {
            for (wn wn2 : this.b.a(wn.class, new aug(n3, \u2603, \u2603, n3, \u2603 - 4, \u2603).b(10.0, 5.0, 10.0))) {
                wn2.b(mr.K);
            }
        }
    }

    public List<a> n() {
        return this.f;
    }

    public float o() {
        if (!this.i) {
            return 0.0f;
        }
        int n2 = (int)(this.b.K() - this.g);
        this.g = this.b.K();
        if (n2 > 1) {
            this.h -= (float)n2 / 40.0f;
            if (this.h < 0.0f) {
                this.h = 0.0f;
            }
        }
        this.h += 0.025f;
        if (this.h > 1.0f) {
            this.h = 1.0f;
        }
        return this.h;
    }

    @Override
    public ff y_() {
        dn dn2 = new dn();
        this.b(dn2);
        return new ft(this.c, 3, dn2);
    }

    @Override
    public double s() {
        return 65536.0;
    }

    private int h(int n2) {
        if (n2 < 0 || n2 >= pe.a.length || pe.a[n2] == null) {
            return 0;
        }
        pe pe2 = pe.a[n2];
        if (pe2 != pe.c && pe2 != pe.e && pe2 != pe.m && pe2 != pe.j && pe2 != pe.g && pe2 != pe.l) {
            return 0;
        }
        return n2;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.k = this.h(dn2.f("Primary"));
        this.l = this.h(dn2.f("Secondary"));
        this.j = dn2.f("Levels");
    }

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("Primary", this.k);
        dn2.a("Secondary", this.l);
        dn2.a("Levels", this.j);
    }

    @Override
    public int o_() {
        return 1;
    }

    @Override
    public zx a(int n2) {
        if (n2 == 0) {
            return this.m;
        }
        return null;
    }

    @Override
    public zx a(int n2, int n32) {
        if (n2 == 0 && this.m != null) {
            int n32;
            if (n32 >= this.m.b) {
                zx zx2 = this.m;
                this.m = null;
                return zx2;
            }
            this.m.b -= n32;
            return new zx(this.m.b(), n32, this.m.i());
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (n2 == 0 && this.m != null) {
            zx zx2 = this.m;
            this.m = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        if (n2 == 0) {
            this.m = zx2;
        }
    }

    @Override
    public String e_() {
        return this.l_() ? this.n : "container.beacon";
    }

    @Override
    public boolean l_() {
        return this.n != null && this.n.length() > 0;
    }

    public void a(String string) {
        this.n = string;
    }

    @Override
    public int q_() {
        return 1;
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
        return zx2.b() == zy.bO || zx2.b() == zy.i || zx2.b() == zy.k || zx2.b() == zy.j;
    }

    @Override
    public String k() {
        return "minecraft:beacon";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xl(wm2, this);
    }

    @Override
    public int a_(int n2) {
        switch (n2) {
            case 0: {
                return this.j;
            }
            case 1: {
                return this.k;
            }
            case 2: {
                return this.l;
            }
        }
        return 0;
    }

    @Override
    public void b(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.j = n3;
                break;
            }
            case 1: {
                this.k = this.h(n3);
                break;
            }
            case 2: {
                this.l = this.h(n3);
            }
        }
    }

    @Override
    public int g() {
        return 3;
    }

    @Override
    public void l() {
        this.m = null;
    }

    @Override
    public boolean c(int n2, int n3) {
        if (n2 == 1) {
            this.m();
            return true;
        }
        return super.c(n2, n3);
    }

    public static class a {
        private final float[] a;
        private int b;

        public a(float[] fArray) {
            this.a = fArray;
            this.b = 1;
        }

        protected void a() {
            ++this.b;
        }

        public float[] b() {
            return this.a;
        }

        public int c() {
            return this.b;
        }
    }
}

