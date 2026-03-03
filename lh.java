/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lh {
    private static final Logger p = LogManager.getLogger();
    public pk a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public double j;
    public double k;
    public double l;
    public int m;
    private double q;
    private double r;
    private double s;
    private boolean t;
    private boolean u;
    private int v;
    private pk w;
    private boolean x;
    private boolean y;
    public boolean n;
    public Set<lf> o = Sets.newHashSet();

    public lh(pk pk2, int n2, int n3, boolean bl2) {
        this.a = pk2;
        this.b = n2;
        this.c = n3;
        this.u = bl2;
        this.d = ns.c(pk2.s * 32.0);
        this.e = ns.c(pk2.t * 32.0);
        this.f = ns.c(pk2.u * 32.0);
        this.g = ns.d(pk2.y * 256.0f / 360.0f);
        this.h = ns.d(pk2.z * 256.0f / 360.0f);
        this.i = ns.d(pk2.aC() * 256.0f / 360.0f);
        this.y = pk2.C;
    }

    public boolean equals(Object object) {
        if (object instanceof lh) {
            return ((lh)object).a.F() == this.a.F();
        }
        return false;
    }

    public int hashCode() {
        return this.a.F();
    }

    public void a(List<wn> list) {
        this.n = false;
        if (!this.t || this.a.e(this.q, this.r, this.s) > 16.0) {
            this.q = this.a.s;
            this.r = this.a.t;
            this.s = this.a.u;
            this.t = true;
            this.n = true;
            this.b(list);
        }
        if (this.w != this.a.m || this.a.m != null && this.m % 60 == 0) {
            this.w = this.a.m;
            this.a(new hl(0, this.a, this.a.m));
        }
        if (this.a instanceof uo && this.m % 10 == 0) {
            uo uo2 = (uo)this.a;
            zx \u26032 = uo2.o();
            if (\u26032 != null && \u26032.b() instanceof aab) {
                atg atg2 = zy.bd.a(\u26032, this.a.o);
                for (wn wn2 : list) {
                    lf lf2 = (lf)wn2;
                    atg2.a(lf2, \u26032);
                    ff \u26033 = zy.bd.c(\u26032, this.a.o, lf2);
                    if (\u26033 == null) continue;
                    lf2.a.a(\u26033);
                }
            }
            this.b();
        }
        if (this.m % this.c == 0 || this.a.ai || this.a.H().a()) {
            if (this.a.m == null) {
                ++this.v;
                int n2 = ns.c(this.a.s * 32.0);
                \u2603 = ns.c(this.a.t * 32.0);
                \u2603 = ns.c(this.a.u * 32.0);
                \u2603 = ns.d(this.a.y * 256.0f / 360.0f);
                \u2603 = ns.d(this.a.z * 256.0f / 360.0f);
                \u2603 = n2 - this.d;
                \u2603 = \u2603 - this.e;
                \u2603 = \u2603 - this.f;
                ff<fj> \u26034 = null;
                boolean \u26035 = Math.abs(\u2603) >= 4 || Math.abs(\u2603) >= 4 || Math.abs(\u2603) >= 4 || this.m % 60 == 0;
                boolean bl2 = \u2603 = Math.abs(\u2603 - this.g) >= 4 || Math.abs(\u2603 - this.h) >= 4;
                if (this.m > 0 || this.a instanceof wq) {
                    if (\u2603 < -128 || \u2603 >= 128 || \u2603 < -128 || \u2603 >= 128 || \u2603 < -128 || \u2603 >= 128 || this.v > 400 || this.x || this.y != this.a.C) {
                        this.y = this.a.C;
                        this.v = 0;
                        \u26034 = new hz(this.a.F(), n2, \u2603, \u2603, (byte)\u2603, (byte)\u2603, this.a.C);
                    } else if (\u26035 && \u2603 || this.a instanceof wq) {
                        \u26034 = new gv.b(this.a.F(), (byte)\u2603, (byte)\u2603, (byte)\u2603, (byte)\u2603, (byte)\u2603, this.a.C);
                    } else if (\u26035) {
                        \u26034 = new gv.a(this.a.F(), (byte)\u2603, (byte)\u2603, (byte)\u2603, this.a.C);
                    } else if (\u2603) {
                        \u26034 = new gv.c(this.a.F(), (byte)\u2603, (byte)\u2603, this.a.C);
                    }
                }
                if (this.u && ((\u2603 = (\u2603 = this.a.v - this.j) * \u2603 + (\u2603 = this.a.w - this.k) * \u2603 + (\u2603 = this.a.x - this.l) * \u2603) > (\u2603 = 0.02) * \u2603 || \u2603 > 0.0 && this.a.v == 0.0 && this.a.w == 0.0 && this.a.x == 0.0)) {
                    this.j = this.a.v;
                    this.k = this.a.w;
                    this.l = this.a.x;
                    this.a(new hm(this.a.F(), this.j, this.k, this.l));
                }
                if (\u26034 != null) {
                    this.a(\u26034);
                }
                this.b();
                if (\u26035) {
                    this.d = n2;
                    this.e = \u2603;
                    this.f = \u2603;
                }
                if (\u2603) {
                    this.g = \u2603;
                    this.h = \u2603;
                }
                this.x = false;
            } else {
                n2 = ns.d(this.a.y * 256.0f / 360.0f);
                \u2603 = ns.d(this.a.z * 256.0f / 360.0f);
                boolean bl3 = \u2603 = Math.abs(n2 - this.g) >= 4 || Math.abs(\u2603 - this.h) >= 4;
                if (\u2603) {
                    this.a(new gv.c(this.a.F(), (byte)n2, (byte)\u2603, this.a.C));
                    this.g = n2;
                    this.h = \u2603;
                }
                this.d = ns.c(this.a.s * 32.0);
                this.e = ns.c(this.a.t * 32.0);
                this.f = ns.c(this.a.u * 32.0);
                this.b();
                this.x = true;
            }
            n2 = ns.d(this.a.aC() * 256.0f / 360.0f);
            if (Math.abs(n2 - this.i) >= 4) {
                this.a(new hf(this.a, (byte)n2));
                this.i = n2;
            }
            this.a.ai = false;
        }
        ++this.m;
        if (this.a.G) {
            this.b(new hm(this.a));
            this.a.G = false;
        }
    }

    private void b() {
        pz pz2 = this.a.H();
        if (pz2.a()) {
            this.b(new hk(this.a.F(), pz2, false));
        }
        if (this.a instanceof pr) {
            qi qi2 = (qi)((pr)this.a).by();
            Set<qc> \u26032 = qi2.b();
            if (!\u26032.isEmpty()) {
                this.b(new ia(this.a.F(), \u26032));
            }
            \u26032.clear();
        }
    }

    public void a(ff ff2) {
        for (lf lf2 : this.o) {
            lf2.a.a(ff2);
        }
    }

    public void b(ff ff2) {
        this.a(ff2);
        if (this.a instanceof lf) {
            ((lf)this.a).a.a(ff2);
        }
    }

    public void a() {
        for (lf lf2 : this.o) {
            lf2.d(this.a);
        }
    }

    public void a(lf lf2) {
        if (this.o.contains(lf2)) {
            lf2.d(this.a);
            this.o.remove(lf2);
        }
    }

    public void b(lf lf22) {
        lf lf22;
        if (lf22 == this.a) {
            return;
        }
        if (this.c(lf22)) {
            if (!this.o.contains(lf22) && (this.e(lf22) || this.a.n)) {
                Object object;
                this.o.add(lf22);
                ff ff2 = this.c();
                lf22.a.a(ff2);
                if (!this.a.H().d()) {
                    lf22.a.a(new hk(this.a.F(), this.a.H(), true));
                }
                if ((\u2603 = this.a.aV()) != null) {
                    lf22.a.a(new gj(this.a.F(), \u2603));
                }
                if (this.a instanceof pr && !(object2 = ((qi)(object = (qi)((pr)this.a).by())).c()).isEmpty()) {
                    lf22.a.a(new ia(this.a.F(), (Collection<qc>)object2));
                }
                this.j = this.a.v;
                this.k = this.a.w;
                this.l = this.a.x;
                if (this.u && !(ff2 instanceof fn)) {
                    lf22.a.a(new hm(this.a.F(), this.a.v, this.a.w, this.a.x));
                }
                if (this.a.m != null) {
                    lf22.a.a(new hl(0, this.a, this.a.m));
                }
                if (this.a instanceof ps && ((ps)this.a).cd() != null) {
                    lf22.a.a(new hl(1, this.a, ((ps)this.a).cd()));
                }
                if (this.a instanceof pr) {
                    for (int i2 = 0; i2 < 5; ++i2) {
                        Object object2 = ((pr)this.a).p(i2);
                        if (object2 == null) continue;
                        lf22.a.a(new hn(this.a.F(), i2, (zx)object2));
                    }
                }
                if (this.a instanceof wn && ((wn)(object = (wn)this.a)).bJ()) {
                    lf22.a.a(new ha((wn)object, new cj(this.a)));
                }
                if (this.a instanceof pr) {
                    object = (pr)this.a;
                    for (pf pf2 : ((pr)object).bl()) {
                        lf22.a.a(new ib(this.a.F(), pf2));
                    }
                }
            }
        } else if (this.o.contains(lf22)) {
            this.o.remove(lf22);
            lf22.d(this.a);
        }
    }

    public boolean c(lf lf2) {
        double d2 = lf2.s - (double)(this.d / 32);
        \u2603 = lf2.u - (double)(this.f / 32);
        return d2 >= (double)(-this.b) && d2 <= (double)this.b && \u2603 >= (double)(-this.b) && \u2603 <= (double)this.b && this.a.a(lf2);
    }

    private boolean e(lf lf2) {
        return lf2.u().t().a(lf2, this.a.ae, this.a.ag);
    }

    public void b(List<wn> list) {
        for (int i2 = 0; i2 < list.size(); ++i2) {
            this.b((lf)list.get(i2));
        }
    }

    private ff c() {
        if (this.a.I) {
            p.warn("Fetching addPacket for removed entity");
        }
        if (this.a instanceof uz) {
            return new fk(this.a, 2, 1);
        }
        if (this.a instanceof lf) {
            return new fp((wn)this.a);
        }
        if (this.a instanceof va) {
            va va2 = (va)this.a;
            return new fk(this.a, 10, va2.s().a());
        }
        if (this.a instanceof ux) {
            return new fk(this.a, 1);
        }
        if (this.a instanceof pi) {
            this.i = ns.d(this.a.aC() * 256.0f / 360.0f);
            return new fn((pr)this.a);
        }
        if (this.a instanceof ur) {
            wn wn2 = ((ur)this.a).b;
            return new fk(this.a, 90, wn2 != null ? wn2.F() : this.a.F());
        }
        if (this.a instanceof wq) {
            pk pk2 = ((wq)this.a).c;
            return new fk(this.a, 60, pk2 != null ? pk2.F() : this.a.F());
        }
        if (this.a instanceof wx) {
            return new fk(this.a, 61);
        }
        if (this.a instanceof xc) {
            return new fk(this.a, 73, ((xc)this.a).o());
        }
        if (this.a instanceof xb) {
            return new fk(this.a, 75);
        }
        if (this.a instanceof xa) {
            return new fk(this.a, 65);
        }
        if (this.a instanceof wr) {
            return new fk(this.a, 72);
        }
        if (this.a instanceof wt) {
            return new fk(this.a, 76);
        }
        if (this.a instanceof ws) {
            ws ws2 = (ws)this.a;
            fk \u26032 = null;
            int \u26033 = 63;
            if (this.a instanceof ww) {
                \u26033 = 64;
            } else if (this.a instanceof xd) {
                \u26033 = 66;
            }
            \u26032 = ws2.a != null ? new fk(this.a, \u26033, ((ws)this.a).a.F()) : new fk(this.a, \u26033, 0);
            \u26032.d((int)(ws2.b * 8000.0));
            \u26032.e((int)(ws2.c * 8000.0));
            \u26032.f((int)(ws2.d * 8000.0));
            return \u26032;
        }
        if (this.a instanceof wz) {
            return new fk(this.a, 62);
        }
        if (this.a instanceof vj) {
            return new fk(this.a, 50);
        }
        if (this.a instanceof uf) {
            return new fk(this.a, 51);
        }
        if (this.a instanceof uy) {
            uy \u26034 = (uy)this.a;
            return new fk(this.a, 70, afh.f(\u26034.l()));
        }
        if (this.a instanceof um) {
            return new fk(this.a, 78);
        }
        if (this.a instanceof uq) {
            return new fo((uq)this.a);
        }
        if (this.a instanceof uo) {
            uo \u26035 = (uo)this.a;
            fk \u26036 = new fk(this.a, 71, \u26035.b.b());
            cj \u26037 = \u26035.n();
            \u26036.a(ns.d((float)(\u26037.n() * 32)));
            \u26036.b(ns.d((float)(\u26037.o() * 32)));
            \u26036.c(ns.d((float)(\u26037.p() * 32)));
            return \u26036;
        }
        if (this.a instanceof up) {
            up \u26038 = (up)this.a;
            fk \u26039 = new fk(this.a, 77);
            cj \u260310 = \u26038.n();
            \u26039.a(ns.d((float)(\u260310.n() * 32)));
            \u26039.b(ns.d((float)(\u260310.o() * 32)));
            \u26039.c(ns.d((float)(\u260310.p() * 32)));
            return \u26039;
        }
        if (this.a instanceof pp) {
            return new fl((pp)this.a);
        }
        throw new IllegalArgumentException("Don't know how to add " + this.a.getClass() + "!");
    }

    public void d(lf lf2) {
        if (this.o.contains(lf2)) {
            this.o.remove(lf2);
            lf2.d(this.a);
        }
    }
}

