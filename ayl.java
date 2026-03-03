/*
 * Decompiled with CFR 0.152.
 */
import com.google.common.collect.Sets;
import java.util.Set;
import org.lwjgl.input.Keyboard;

public abstract class ayl
extends axu {
    protected static final jy a = new jy("textures/gui/container/inventory.png");
    protected int f = 176;
    protected int g = 166;
    public xi h;
    protected int i;
    protected int r;
    private yg u;
    private yg v;
    private boolean w;
    private zx x;
    private int y;
    private int z;
    private yg A;
    private long B;
    private zx C;
    private yg D;
    private long E;
    protected final Set<yg> s = Sets.newHashSet();
    protected boolean t;
    private int F;
    private int G;
    private boolean H;
    private int I;
    private long J;
    private yg K;
    private int L;
    private boolean M;
    private zx N;

    public ayl(xi xi2) {
        this.h = xi2;
        this.H = true;
    }

    @Override
    public void b() {
        super.b();
        this.j.h.bk = this.h;
        this.i = (this.l - this.f) / 2;
        this.r = (this.m - this.g) / 2;
    }

    @Override
    public void a(int n2, int n3, float f2) {
        int \u26033;
        int \u26032;
        Object object;
        this.c();
        int n4 = this.i;
        \u2603 = this.r;
        this.a(f2, n2, n3);
        bfl.C();
        avc.a();
        bfl.f();
        bfl.i();
        super.a(n2, n3, f2);
        avc.c();
        bfl.E();
        bfl.b(n4, \u2603, 0.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.B();
        this.u = null;
        \u2603 = 240;
        \u2603 = 240;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        for (\u2603 = 0; \u2603 < this.h.c.size(); ++\u2603) {
            object = this.h.c.get(\u2603);
            this.a((yg)object);
            if (!this.a((yg)object, n2, n3) || !((yg)object).b()) continue;
            this.u = object;
            bfl.f();
            bfl.i();
            \u26032 = ((yg)object).f;
            \u26033 = ((yg)object).g;
            bfl.a(true, true, true, false);
            this.a(\u26032, \u26033, \u26032 + 16, \u26033 + 16, -2130706433, -2130706433);
            bfl.a(true, true, true, true);
            bfl.e();
            bfl.j();
        }
        avc.a();
        this.b(n2, n3);
        avc.c();
        wm wm2 = this.j.h.bi;
        Object object2 = object = this.x == null ? wm2.p() : this.x;
        if (object != null) {
            \u26032 = 8;
            \u26033 = this.x == null ? 8 : 16;
            String string = null;
            if (this.x != null && this.w) {
                object = ((zx)object).k();
                ((zx)object).b = ns.f((float)((zx)object).b / 2.0f);
            } else if (this.t && this.s.size() > 1) {
                object = ((zx)object).k();
                ((zx)object).b = this.I;
                if (((zx)object).b == 0) {
                    string = "" + (Object)((Object)a.o) + "0";
                }
            }
            this.a((zx)object, n2 - n4 - \u26032, n3 - \u2603 - \u26033, string);
        }
        if (this.C != null) {
            float f3 = (float)(ave.J() - this.B) / 100.0f;
            if (f3 >= 1.0f) {
                f3 = 1.0f;
                this.C = null;
            }
            \u26033 = this.A.f - this.y;
            int \u26034 = this.A.g - this.z;
            int \u26035 = this.y + (int)((float)\u26033 * f3);
            int \u26036 = this.z + (int)((float)\u26034 * f3);
            this.a(this.C, \u26035, \u26036, null);
        }
        bfl.F();
        if (wm2.p() == null && this.u != null && this.u.e()) {
            zx \u26037 = this.u.d();
            this.a(\u26037, n2, n3);
        }
        bfl.e();
        bfl.j();
        avc.b();
    }

    private void a(zx zx2, int n2, int n3, String string) {
        bfl.b(0.0f, 0.0f, 32.0f);
        this.e = 200.0f;
        this.k.a = 200.0f;
        this.k.b(zx2, n2, n3);
        this.k.a(this.q, zx2, n2, n3 - (this.x == null ? 0 : 8), string);
        this.e = 0.0f;
        this.k.a = 0.0f;
    }

    protected void b(int n2, int n3) {
    }

    protected abstract void a(float var1, int var2, int var3);

    private void a(yg yg2) {
        int n2 = yg2.f;
        \u2603 = yg2.g;
        zx \u26032 = yg2.d();
        boolean \u26033 = false;
        boolean \u26034 = yg2 == this.v && this.x != null && !this.w;
        zx \u26035 = this.j.h.bi.p();
        String \u26036 = null;
        if (yg2 == this.v && this.x != null && this.w && \u26032 != null) {
            \u26032 = \u26032.k();
            \u26032.b /= 2;
        } else if (this.t && this.s.contains(yg2) && \u26035 != null) {
            if (this.s.size() == 1) {
                return;
            }
            if (xi.a(yg2, \u26035, true) && this.h.b(yg2)) {
                \u26032 = \u26035.k();
                \u26033 = true;
                xi.a(this.s, this.F, \u26032, yg2.d() == null ? 0 : yg2.d().b);
                if (\u26032.b > \u26032.c()) {
                    \u26036 = (Object)((Object)a.o) + "" + \u26032.c();
                    \u26032.b = \u26032.c();
                }
                if (\u26032.b > yg2.b(\u26032)) {
                    \u26036 = (Object)((Object)a.o) + "" + yg2.b(\u26032);
                    \u26032.b = yg2.b(\u26032);
                }
            } else {
                this.s.remove(yg2);
                this.a();
            }
        }
        this.e = 100.0f;
        this.k.a = 100.0f;
        if (\u26032 == null && (\u2603 = yg2.c()) != null) {
            bmi bmi2 = this.j.T().a(\u2603);
            bfl.f();
            this.j.P().a(bmh.g);
            this.a(n2, \u2603, bmi2, 16, 16);
            bfl.e();
            \u26034 = true;
        }
        if (!\u26034) {
            if (\u26033) {
                ayl.a(n2, \u2603, n2 + 16, \u2603 + 16, -2130706433);
            }
            bfl.j();
            this.k.b(\u26032, n2, \u2603);
            this.k.a(this.q, \u26032, n2, \u2603, \u26036);
        }
        this.k.a = 0.0f;
        this.e = 0.0f;
    }

    private void a() {
        zx zx2 = this.j.h.bi.p();
        if (zx2 == null || !this.t) {
            return;
        }
        this.I = zx2.b;
        for (yg yg2 : this.s) {
            zx zx3 = zx2.k();
            int \u26032 = yg2.d() == null ? 0 : yg2.d().b;
            xi.a(this.s, this.F, zx3, \u26032);
            if (zx3.b > zx3.c()) {
                zx3.b = zx3.c();
            }
            if (zx3.b > yg2.b(zx3)) {
                zx3.b = yg2.b(zx3);
            }
            this.I -= zx3.b - \u26032;
        }
    }

    private yg c(int n2, int n3) {
        for (\u2603 = 0; \u2603 < this.h.c.size(); ++\u2603) {
            yg yg2 = this.h.c.get(\u2603);
            if (!this.a(yg2, n2, n3)) continue;
            return yg2;
        }
        return null;
    }

    @Override
    protected void a(int n2, int n3, int n4) {
        super.a(n2, n3, n4);
        boolean bl2 = n4 == this.j.t.ai.i() + 100;
        yg \u26032 = this.c(n2, n3);
        long \u26033 = ave.J();
        this.M = this.K == \u26032 && \u26033 - this.J < 250L && this.L == n4;
        this.H = false;
        if (n4 == 0 || n4 == 1 || bl2) {
            int n5 = this.i;
            \u2603 = this.r;
            boolean \u26034 = n2 < n5 || n3 < \u2603 || n2 >= n5 + this.f || n3 >= \u2603 + this.g;
            \u2603 = -1;
            if (\u26032 != null) {
                \u2603 = \u26032.e;
            }
            if (\u26034) {
                \u2603 = -999;
            }
            if (this.j.t.A && \u26034 && this.j.h.bi.p() == null) {
                this.j.a((axu)null);
                return;
            }
            if (\u2603 != -1) {
                if (this.j.t.A) {
                    if (\u26032 != null && \u26032.e()) {
                        this.v = \u26032;
                        this.x = null;
                        this.w = n4 == 1;
                    } else {
                        this.v = null;
                    }
                } else if (!this.t) {
                    if (this.j.h.bi.p() == null) {
                        if (n4 == this.j.t.ai.i() + 100) {
                            this.a(\u26032, \u2603, n4, 3);
                        } else {
                            boolean bl3 = \u2603 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                            int \u26035 = 0;
                            if (bl3) {
                                this.N = \u26032 != null && \u26032.e() ? \u26032.d() : null;
                                \u26035 = 1;
                            } else if (\u2603 == -999) {
                                \u26035 = 4;
                            }
                            this.a(\u26032, \u2603, n4, \u26035);
                        }
                        this.H = true;
                    } else {
                        this.t = true;
                        this.G = n4;
                        this.s.clear();
                        if (n4 == 0) {
                            this.F = 0;
                        } else if (n4 == 1) {
                            this.F = 1;
                        } else if (n4 == this.j.t.ai.i() + 100) {
                            this.F = 2;
                        }
                    }
                }
            }
        }
        this.K = \u26032;
        this.J = \u26033;
        this.L = n4;
    }

    @Override
    protected void a(int n2, int n3, int n4, long l2) {
        yg yg2 = this.c(n2, n3);
        zx \u26032 = this.j.h.bi.p();
        if (this.v != null && this.j.t.A) {
            if (n4 == 0 || n4 == 1) {
                if (this.x == null) {
                    if (yg2 != this.v && this.v.d() != null) {
                        this.x = this.v.d().k();
                    }
                } else if (this.x.b > 1 && yg2 != null && xi.a(yg2, this.x, false)) {
                    long l3 = ave.J();
                    if (this.D == yg2) {
                        if (l3 - this.E > 500L) {
                            this.a(this.v, this.v.e, 0, 0);
                            this.a(yg2, yg2.e, 1, 0);
                            this.a(this.v, this.v.e, 0, 0);
                            this.E = l3 + 750L;
                            --this.x.b;
                        }
                    } else {
                        this.D = yg2;
                        this.E = l3;
                    }
                }
            }
        } else if (this.t && yg2 != null && \u26032 != null && \u26032.b > this.s.size() && xi.a(yg2, \u26032, true) && yg2.a(\u26032) && this.h.b(yg2)) {
            this.s.add(yg2);
            this.a();
        }
    }

    @Override
    protected void b(int n2, int n3, int n42) {
        yg yg2 = this.c(n2, n3);
        int \u26032 = this.i;
        int \u26033 = this.r;
        boolean \u26034 = n2 < \u26032 || n3 < \u26033 || n2 >= \u26032 + this.f || n3 >= \u26033 + this.g;
        int \u26035 = -1;
        if (yg2 != null) {
            \u26035 = yg2.e;
        }
        if (\u26034) {
            \u26035 = -999;
        }
        if (this.M && yg2 != null && n42 == 0 && this.h.a(null, yg2)) {
            if (ayl.r()) {
                if (yg2 != null && yg2.d != null && this.N != null) {
                    for (yg yg3 : this.h.c) {
                        if (yg3 == null || !yg3.a(this.j.h) || !yg3.e() || yg3.d != yg2.d || !xi.a(yg3, this.N, true)) continue;
                        this.a(yg3, yg3.e, n42, 1);
                    }
                }
            } else {
                this.a(yg2, \u26035, n42, 6);
            }
            this.M = false;
            this.J = 0L;
        } else {
            if (this.t && this.G != n42) {
                this.t = false;
                this.s.clear();
                this.H = true;
                return;
            }
            if (this.H) {
                this.H = false;
                return;
            }
            if (this.v != null && this.j.t.A) {
                if (n42 == 0 || n42 == 1) {
                    if (this.x == null && yg2 != this.v) {
                        this.x = this.v.d();
                    }
                    boolean bl2 = xi.a(yg2, this.x, false);
                    if (\u26035 != -1 && this.x != null && bl2) {
                        this.a(this.v, this.v.e, n42, 0);
                        this.a(yg2, \u26035, 0, 0);
                        if (this.j.h.bi.p() != null) {
                            this.a(this.v, this.v.e, n42, 0);
                            this.y = n2 - \u26032;
                            this.z = n3 - \u26033;
                            this.A = this.v;
                            this.C = this.x;
                            this.B = ave.J();
                        } else {
                            this.C = null;
                        }
                    } else if (this.x != null) {
                        this.y = n2 - \u26032;
                        this.z = n3 - \u26033;
                        this.A = this.v;
                        this.C = this.x;
                        this.B = ave.J();
                    }
                    this.x = null;
                    this.v = null;
                }
            } else if (this.t && !this.s.isEmpty()) {
                this.a(null, -999, xi.d(0, this.F), 5);
                for (yg yg4 : this.s) {
                    this.a(yg4, yg4.e, xi.d(1, this.F), 5);
                }
                this.a(null, -999, xi.d(2, this.F), 5);
            } else if (this.j.h.bi.p() != null) {
                int n42;
                if (n42 == this.j.t.ai.i() + 100) {
                    this.a(yg2, \u26035, n42, 3);
                } else {
                    boolean bl3 = \u2603 = \u26035 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                    if (\u2603) {
                        this.N = yg2 != null && yg2.e() ? yg2.d() : null;
                    }
                    this.a(yg2, \u26035, n42, \u2603 ? 1 : 0);
                }
            }
        }
        if (this.j.h.bi.p() == null) {
            this.J = 0L;
        }
        this.t = false;
    }

    private boolean a(yg yg2, int n2, int n3) {
        return this.c(yg2.f, yg2.g, 16, 16, n2, n3);
    }

    protected boolean c(int n2, int n3, int n4, int n5, int n6, int n7) {
        \u2603 = this.i;
        \u2603 = this.r;
        return (n6 -= \u2603) >= n2 - 1 && n6 < n2 + n4 + 1 && (n7 -= \u2603) >= n3 - 1 && n7 < n3 + n5 + 1;
    }

    protected void a(yg yg2, int n2, int n3, int n4) {
        if (yg2 != null) {
            n2 = yg2.e;
        }
        this.j.c.a(this.h.d, n2, n3, n4, this.j.h);
    }

    @Override
    protected void a(char c2, int n2) {
        if (n2 == 1 || n2 == this.j.t.ae.i()) {
            this.j.h.n();
        }
        this.b(n2);
        if (this.u != null && this.u.e()) {
            if (n2 == this.j.t.ai.i()) {
                this.a(this.u, this.u.e, 0, 3);
            } else if (n2 == this.j.t.ag.i()) {
                this.a(this.u, this.u.e, ayl.q() ? 1 : 0, 4);
            }
        }
    }

    protected boolean b(int n2) {
        if (this.j.h.bi.p() == null && this.u != null) {
            for (\u2603 = 0; \u2603 < 9; ++\u2603) {
                if (n2 != this.j.t.av[\u2603].i()) continue;
                this.a(this.u, this.u.e, \u2603, 2);
                return true;
            }
        }
        return false;
    }

    @Override
    public void m() {
        if (this.j.h == null) {
            return;
        }
        this.h.b(this.j.h);
    }

    @Override
    public boolean d() {
        return false;
    }

    @Override
    public void e() {
        super.e();
        if (!this.j.h.ai() || this.j.h.I) {
            this.j.h.n();
        }
    }
}

