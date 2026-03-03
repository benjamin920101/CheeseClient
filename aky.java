/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class aky
extends alk
implements km,
og {
    private zx[] m = new zx[27];
    public boolean a;
    public aky f;
    public aky g;
    public aky h;
    public aky i;
    public float j;
    public float k;
    public int l;
    private int n;
    private int o;
    private String p;

    public aky() {
        this.o = -1;
    }

    public aky(int n2) {
        this.o = n2;
    }

    @Override
    public int o_() {
        return 27;
    }

    @Override
    public zx a(int n2) {
        return this.m[n2];
    }

    @Override
    public zx a(int n22, int n3) {
        if (this.m[n22] != null) {
            int n22;
            if (this.m[n22].b <= n3) {
                zx zx2 = this.m[n22];
                this.m[n22] = null;
                this.p_();
                return zx2;
            }
            zx \u26032 = this.m[n22].a(n3);
            if (this.m[n22].b == 0) {
                this.m[n22] = null;
            }
            this.p_();
            return \u26032;
        }
        return null;
    }

    @Override
    public zx b(int n2) {
        if (this.m[n2] != null) {
            zx zx2 = this.m[n2];
            this.m[n2] = null;
            return zx2;
        }
        return null;
    }

    @Override
    public void a(int n2, zx zx2) {
        this.m[n2] = zx2;
        if (zx2 != null && zx2.b > this.q_()) {
            zx2.b = this.q_();
        }
        this.p_();
    }

    @Override
    public String e_() {
        return this.l_() ? this.p : "container.chest";
    }

    @Override
    public boolean l_() {
        return this.p != null && this.p.length() > 0;
    }

    public void a(String string) {
        this.p = string;
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        du du2 = dn2.c("Items", 10);
        this.m = new zx[this.o_()];
        if (dn2.b("CustomName", 8)) {
            this.p = dn2.j("CustomName");
        }
        for (int i2 = 0; i2 < du2.c(); ++i2) {
            dn dn3 = du2.b(i2);
            int \u26032 = dn3.d("Slot") & 0xFF;
            if (\u26032 < 0 || \u26032 >= this.m.length) continue;
            this.m[\u26032] = zx.a(dn3);
        }
    }

    @Override
    public void b(dn dn22) {
        dn dn22;
        super.b(dn22);
        du du2 = new du();
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            if (this.m[i2] == null) continue;
            dn dn3 = new dn();
            dn3.a("Slot", (byte)i2);
            this.m[i2].b(dn3);
            du2.a(dn3);
        }
        dn22.a("Items", du2);
        if (this.l_()) {
            dn22.a("CustomName", this.p);
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
    public void E() {
        super.E();
        this.a = false;
    }

    private void a(aky aky2, cq cq2) {
        if (aky2.x()) {
            this.a = false;
        } else if (this.a) {
            switch (cq2) {
                case c: {
                    if (this.f == aky2) break;
                    this.a = false;
                    break;
                }
                case d: {
                    if (this.i == aky2) break;
                    this.a = false;
                    break;
                }
                case f: {
                    if (this.g == aky2) break;
                    this.a = false;
                    break;
                }
                case e: {
                    if (this.h == aky2) break;
                    this.a = false;
                }
            }
        }
    }

    public void m() {
        if (this.a) {
            return;
        }
        this.a = true;
        this.h = this.a(cq.e);
        this.g = this.a(cq.f);
        this.f = this.a(cq.c);
        this.i = this.a(cq.d);
    }

    protected aky a(cq cq2) {
        cj cj2 = this.c.a(cq2);
        if (this.b(cj2) && (\u2603 = this.b.s(cj2)) instanceof aky) {
            aky aky2 = (aky)\u2603;
            aky2.a(this, cq2.d());
            return aky2;
        }
        return null;
    }

    private boolean b(cj cj2) {
        if (this.b == null) {
            return false;
        }
        afh afh2 = this.b.p(cj2).c();
        return afh2 instanceof afs && ((afs)afh2).b == this.n();
    }

    @Override
    public void c() {
        float f2;
        this.m();
        int n2 = this.c.n();
        \u2603 = this.c.o();
        \u2603 = this.c.p();
        ++this.n;
        if (!this.b.D && this.l != 0 && (this.n + n2 + \u2603 + \u2603) % 200 == 0) {
            this.l = 0;
            f2 = 5.0f;
            List<wn> \u26032 = this.b.a(wn.class, new aug((float)n2 - f2, (float)\u2603 - f2, (float)\u2603 - f2, (float)(n2 + 1) + f2, (float)(\u2603 + 1) + f2, (float)(\u2603 + 1) + f2));
            for (wn wn2 : \u26032) {
                if (!(wn2.bk instanceof xo) || (\u2603 = ((xo)wn2.bk).e()) != this && (!(\u2603 instanceof of) || !((of)\u2603).a(this))) continue;
                ++this.l;
            }
        }
        this.k = this.j;
        f2 = 0.1f;
        if (this.l > 0 && this.j == 0.0f && this.f == null && this.h == null) {
            double d2 = (double)n2 + 0.5;
            \u2603 = (double)\u2603 + 0.5;
            if (this.i != null) {
                \u2603 += 0.5;
            }
            if (this.g != null) {
                d2 += 0.5;
            }
            this.b.a(d2, (double)\u2603 + 0.5, \u2603, "random.chestopen", 0.5f, this.b.s.nextFloat() * 0.1f + 0.9f);
        }
        if (this.l == 0 && this.j > 0.0f || this.l > 0 && this.j < 1.0f) {
            float f3 = this.j;
            this.j = this.l > 0 ? (this.j += f2) : (this.j -= f2);
            if (this.j > 1.0f) {
                this.j = 1.0f;
            }
            if (this.j < (\u2603 = 0.5f) && f3 >= \u2603 && this.f == null && this.h == null) {
                double d3 = (double)n2 + 0.5;
                \u2603 = (double)\u2603 + 0.5;
                if (this.i != null) {
                    \u2603 += 0.5;
                }
                if (this.g != null) {
                    d3 += 0.5;
                }
                this.b.a(d3, (double)\u2603 + 0.5, \u2603, "random.chestclosed", 0.5f, this.b.s.nextFloat() * 0.1f + 0.9f);
            }
            if (this.j < 0.0f) {
                this.j = 0.0f;
            }
        }
    }

    @Override
    public boolean c(int n2, int n3) {
        if (n2 == 1) {
            this.l = n3;
            return true;
        }
        return super.c(n2, n3);
    }

    @Override
    public void b(wn wn2) {
        if (!wn2.v()) {
            if (this.l < 0) {
                this.l = 0;
            }
            ++this.l;
            this.b.c(this.c, this.w(), 1, this.l);
            this.b.c(this.c, this.w());
            this.b.c(this.c.b(), this.w());
        }
    }

    @Override
    public void c(wn wn2) {
        if (!wn2.v() && this.w() instanceof afs) {
            --this.l;
            this.b.c(this.c, this.w(), 1, this.l);
            this.b.c(this.c, this.w());
            this.b.c(this.c.b(), this.w());
        }
    }

    @Override
    public boolean b(int n2, zx zx2) {
        return true;
    }

    @Override
    public void y() {
        super.y();
        this.E();
        this.m();
    }

    public int n() {
        if (this.o == -1) {
            if (this.b != null && this.w() instanceof afs) {
                this.o = ((afs)this.w()).b;
            } else {
                return 0;
            }
        }
        return this.o;
    }

    @Override
    public String k() {
        return "minecraft:chest";
    }

    @Override
    public xi a(wm wm2, wn wn2) {
        return new xo(wm2, this, wn2);
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
        for (int i2 = 0; i2 < this.m.length; ++i2) {
            this.m[i2] = null;
        }
    }
}

