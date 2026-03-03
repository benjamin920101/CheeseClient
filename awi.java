/*
 * Decompiled with CFR 0.152.
 */
import org.lwjgl.input.Mouse;

public abstract class awi {
    protected final ave a;
    protected int b;
    protected int c;
    protected int d;
    protected int e;
    protected int f;
    protected int g;
    protected final int h;
    private int u;
    private int v;
    protected int i;
    protected int j;
    protected boolean k = true;
    protected int l = -2;
    protected float m;
    protected float n;
    protected int o = -1;
    protected long p;
    protected boolean q = true;
    protected boolean r = true;
    protected boolean s;
    protected int t;
    private boolean w = true;

    public awi(ave ave2, int n2, int n3, int n4, int n5, int n6) {
        this.a = ave2;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
        this.h = n6;
        this.g = 0;
        this.f = n2;
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
        this.g = 0;
        this.f = n2;
    }

    public void b(boolean bl2) {
        this.r = bl2;
    }

    protected void a(boolean bl2, int n2) {
        this.s = bl2;
        this.t = n2;
        if (!bl2) {
            this.t = 0;
        }
    }

    protected abstract int b();

    protected abstract void a(int var1, boolean var2, int var3, int var4);

    protected abstract boolean a(int var1);

    protected int k() {
        return this.b() * this.h + this.t;
    }

    protected abstract void a();

    protected void a(int n2, int n3, int n4) {
    }

    protected abstract void a(int var1, int var2, int var3, int var4, int var5, int var6);

    protected void a(int n2, int n3, bfx bfx2) {
    }

    protected void a(int n2, int n3) {
    }

    protected void b(int n2, int n3) {
    }

    public int c(int n2, int n3) {
        \u2603 = this.g + this.b / 2 - this.c() / 2;
        \u2603 = this.g + this.b / 2 + this.c() / 2;
        \u2603 = n3 - this.d - this.t + (int)this.n - 4;
        \u2603 = \u2603 / this.h;
        if (n2 < this.d() && n2 >= \u2603 && n2 <= \u2603 && \u2603 >= 0 && \u2603 >= 0 && \u2603 < this.b()) {
            return \u2603;
        }
        return -1;
    }

    public void d(int n2, int n3) {
        this.u = n2;
        this.v = n3;
    }

    protected void l() {
        this.n = ns.a(this.n, 0.0f, (float)this.m());
    }

    public int m() {
        return Math.max(0, this.k() - (this.e - this.d - 4));
    }

    public int n() {
        return (int)this.n;
    }

    public boolean g(int n2) {
        return n2 >= this.d && n2 <= this.e && this.i >= this.g && this.i <= this.f;
    }

    public void h(int n2) {
        this.n += (float)n2;
        this.l();
        this.l = -2;
    }

    public void a(avs avs2) {
        if (!avs2.l) {
            return;
        }
        if (avs2.k == this.u) {
            this.n -= (float)(this.h * 2 / 3);
            this.l = -2;
            this.l();
        } else if (avs2.k == this.v) {
            this.n += (float)(this.h * 2 / 3);
            this.l = -2;
            this.l();
        }
    }

    public void a(int n2, int n3, float f2) {
        if (!this.q) {
            return;
        }
        this.i = n2;
        this.j = n3;
        this.a();
        int n4 = this.d();
        \u2603 = n4 + 6;
        this.l();
        bfl.f();
        bfl.n();
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        this.a.P().a(avp.b);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        float \u26034 = 32.0f;
        \u26033.a(7, bms.i);
        \u26033.b((double)this.g, (double)this.e, 0.0).a((float)this.g / \u26034, (float)(this.e + (int)this.n) / \u26034).b(32, 32, 32, 255).d();
        \u26033.b((double)this.f, (double)this.e, 0.0).a((float)this.f / \u26034, (float)(this.e + (int)this.n) / \u26034).b(32, 32, 32, 255).d();
        \u26033.b((double)this.f, (double)this.d, 0.0).a((float)this.f / \u26034, (float)(this.d + (int)this.n) / \u26034).b(32, 32, 32, 255).d();
        \u26033.b((double)this.g, (double)this.d, 0.0).a((float)this.g / \u26034, (float)(this.d + (int)this.n) / \u26034).b(32, 32, 32, 255).d();
        \u26032.b();
        \u2603 = this.g + (this.b / 2 - this.c() / 2 + 2);
        \u2603 = this.d + 4 - (int)this.n;
        if (this.s) {
            this.a(\u2603, \u2603, \u26032);
        }
        this.b(\u2603, \u2603, n2, n3);
        bfl.i();
        \u2603 = 4;
        this.c(0, this.d, 255, 255);
        this.c(this.e, this.c, 255, 255);
        bfl.l();
        bfl.a(770, 771, 0, 1);
        bfl.c();
        bfl.j(7425);
        bfl.x();
        \u26033.a(7, bms.i);
        \u26033.b((double)this.g, (double)(this.d + \u2603), 0.0).a(0.0, 1.0).b(0, 0, 0, 0).d();
        \u26033.b((double)this.f, (double)(this.d + \u2603), 0.0).a(1.0, 1.0).b(0, 0, 0, 0).d();
        \u26033.b((double)this.f, (double)this.d, 0.0).a(1.0, 0.0).b(0, 0, 0, 255).d();
        \u26033.b((double)this.g, (double)this.d, 0.0).a(0.0, 0.0).b(0, 0, 0, 255).d();
        \u26032.b();
        \u26033.a(7, bms.i);
        \u26033.b((double)this.g, (double)this.e, 0.0).a(0.0, 1.0).b(0, 0, 0, 255).d();
        \u26033.b((double)this.f, (double)this.e, 0.0).a(1.0, 1.0).b(0, 0, 0, 255).d();
        \u26033.b((double)this.f, (double)(this.e - \u2603), 0.0).a(1.0, 0.0).b(0, 0, 0, 0).d();
        \u26033.b((double)this.g, (double)(this.e - \u2603), 0.0).a(0.0, 0.0).b(0, 0, 0, 0).d();
        \u26032.b();
        \u2603 = this.m();
        if (\u2603 > 0) {
            \u2603 = (this.e - this.d) * (this.e - this.d) / this.k();
            \u2603 = (int)this.n * (this.e - this.d - (\u2603 = ns.a(\u2603, 32, this.e - this.d - 8))) / \u2603 + this.d;
            if (\u2603 < this.d) {
                \u2603 = this.d;
            }
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)this.e, 0.0).a(0.0, 1.0).b(0, 0, 0, 255).d();
            \u26033.b((double)\u2603, (double)this.e, 0.0).a(1.0, 1.0).b(0, 0, 0, 255).d();
            \u26033.b((double)\u2603, (double)this.d, 0.0).a(1.0, 0.0).b(0, 0, 0, 255).d();
            \u26033.b((double)n4, (double)this.d, 0.0).a(0.0, 0.0).b(0, 0, 0, 255).d();
            \u26032.b();
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)(\u2603 + \u2603), 0.0).a(0.0, 1.0).b(128, 128, 128, 255).d();
            \u26033.b((double)\u2603, (double)(\u2603 + \u2603), 0.0).a(1.0, 1.0).b(128, 128, 128, 255).d();
            \u26033.b((double)\u2603, (double)\u2603, 0.0).a(1.0, 0.0).b(128, 128, 128, 255).d();
            \u26033.b((double)n4, (double)\u2603, 0.0).a(0.0, 0.0).b(128, 128, 128, 255).d();
            \u26032.b();
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)(\u2603 + \u2603 - 1), 0.0).a(0.0, 1.0).b(192, 192, 192, 255).d();
            \u26033.b((double)(\u2603 - 1), (double)(\u2603 + \u2603 - 1), 0.0).a(1.0, 1.0).b(192, 192, 192, 255).d();
            \u26033.b((double)(\u2603 - 1), (double)\u2603, 0.0).a(1.0, 0.0).b(192, 192, 192, 255).d();
            \u26033.b((double)n4, (double)\u2603, 0.0).a(0.0, 0.0).b(192, 192, 192, 255).d();
            \u26032.b();
        }
        this.b(n2, n3);
        bfl.w();
        bfl.j(7424);
        bfl.d();
        bfl.k();
    }

    public void p() {
        int n2;
        if (!this.g(this.j)) {
            return;
        }
        if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.j >= this.d && this.j <= this.e) {
            n2 = (this.b - this.c()) / 2;
            \u2603 = (this.b + this.c()) / 2;
            \u2603 = this.j - this.d - this.t + (int)this.n - 4;
            \u2603 = \u2603 / this.h;
            if (\u2603 < this.b() && this.i >= n2 && this.i <= \u2603 && \u2603 >= 0 && \u2603 >= 0) {
                this.a(\u2603, false, this.i, this.j);
                this.o = \u2603;
            } else if (this.i >= n2 && this.i <= \u2603 && \u2603 < 0) {
                this.a(this.i - n2, this.j - this.d + (int)this.n - 4);
            }
        }
        if (Mouse.isButtonDown(0) && this.q()) {
            if (this.l == -1) {
                n2 = 1;
                if (this.j >= this.d && this.j <= this.e) {
                    \u2603 = (this.b - this.c()) / 2;
                    \u2603 = (this.b + this.c()) / 2;
                    \u2603 = this.j - this.d - this.t + (int)this.n - 4;
                    \u2603 = \u2603 / this.h;
                    if (\u2603 < this.b() && this.i >= \u2603 && this.i <= \u2603 && \u2603 >= 0 && \u2603 >= 0) {
                        \u2603 = \u2603 == this.o && ave.J() - this.p < 250L ? 1 : 0;
                        this.a(\u2603, \u2603 != 0, this.i, this.j);
                        this.o = \u2603;
                        this.p = ave.J();
                    } else if (this.i >= \u2603 && this.i <= \u2603 && \u2603 < 0) {
                        this.a(this.i - \u2603, this.j - this.d + (int)this.n - 4);
                        n2 = 0;
                    }
                    \u2603 = this.d();
                    \u2603 = \u2603 + 6;
                    if (this.i >= \u2603 && this.i <= \u2603) {
                        this.m = -1.0f;
                        \u2603 = this.m();
                        if (\u2603 < 1) {
                            \u2603 = 1;
                        }
                        \u2603 = (int)((float)((this.e - this.d) * (this.e - this.d)) / (float)this.k());
                        \u2603 = ns.a(\u2603, 32, this.e - this.d - 8);
                        this.m /= (float)(this.e - this.d - \u2603) / (float)\u2603;
                    } else {
                        this.m = 1.0f;
                    }
                    this.l = n2 != 0 ? this.j : -2;
                } else {
                    this.l = -2;
                }
            } else if (this.l >= 0) {
                this.n -= (float)(this.j - this.l) * this.m;
                this.l = this.j;
            }
        } else {
            this.l = -1;
        }
        n2 = Mouse.getEventDWheel();
        if (n2 != 0) {
            if (n2 > 0) {
                n2 = -1;
            } else if (n2 < 0) {
                n2 = 1;
            }
            this.n += (float)(n2 * this.h / 2);
        }
    }

    public void d(boolean bl2) {
        this.w = bl2;
    }

    public boolean q() {
        return this.w;
    }

    public int c() {
        return 220;
    }

    protected void b(int n2, int n3, int n4, int n5) {
        \u2603 = this.b();
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        for (int i2 = 0; i2 < \u2603; ++i2) {
            \u2603 = n3 + i2 * this.h + this.t;
            \u2603 = this.h - 4;
            if (\u2603 > this.e || \u2603 + \u2603 < this.d) {
                this.a(i2, n2, \u2603);
            }
            if (this.r && this.a(i2)) {
                \u2603 = this.g + (this.b / 2 - this.c() / 2);
                \u2603 = this.g + (this.b / 2 + this.c() / 2);
                bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
                bfl.x();
                \u26032.a(7, bms.i);
                \u26032.b((double)\u2603, (double)(\u2603 + \u2603 + 2), 0.0).a(0.0, 1.0).b(128, 128, 128, 255).d();
                \u26032.b((double)\u2603, (double)(\u2603 + \u2603 + 2), 0.0).a(1.0, 1.0).b(128, 128, 128, 255).d();
                \u26032.b((double)\u2603, (double)(\u2603 - 2), 0.0).a(1.0, 0.0).b(128, 128, 128, 255).d();
                \u26032.b((double)\u2603, (double)(\u2603 - 2), 0.0).a(0.0, 0.0).b(128, 128, 128, 255).d();
                \u26032.b((double)(\u2603 + 1), (double)(\u2603 + \u2603 + 1), 0.0).a(0.0, 1.0).b(0, 0, 0, 255).d();
                \u26032.b((double)(\u2603 - 1), (double)(\u2603 + \u2603 + 1), 0.0).a(1.0, 1.0).b(0, 0, 0, 255).d();
                \u26032.b((double)(\u2603 - 1), (double)(\u2603 - 1), 0.0).a(1.0, 0.0).b(0, 0, 0, 255).d();
                \u26032.b((double)(\u2603 + 1), (double)(\u2603 - 1), 0.0).a(0.0, 0.0).b(0, 0, 0, 255).d();
                bfx2.b();
                bfl.w();
            }
            this.a(i2, n2, \u2603, \u2603, n4, n5);
        }
    }

    protected int d() {
        return this.b / 2 + 124;
    }

    protected void c(int n2, int n3, int n4, int n5) {
        bfx bfx2 = bfx.a();
        bfd \u26032 = bfx2.c();
        this.a.P().a(avp.b);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        float \u26033 = 32.0f;
        \u26032.a(7, bms.i);
        \u26032.b((double)this.g, (double)n3, 0.0).a(0.0, (float)n3 / 32.0f).b(64, 64, 64, n5).d();
        \u26032.b((double)(this.g + this.b), (double)n3, 0.0).a((float)this.b / 32.0f, (float)n3 / 32.0f).b(64, 64, 64, n5).d();
        \u26032.b((double)(this.g + this.b), (double)n2, 0.0).a((float)this.b / 32.0f, (float)n2 / 32.0f).b(64, 64, 64, n4).d();
        \u26032.b((double)this.g, (double)n2, 0.0).a(0.0, (float)n2 / 32.0f).b(64, 64, 64, n4).d();
        bfx2.b();
    }

    public void i(int n2) {
        this.g = n2;
        this.f = n2 + this.b;
    }

    public int r() {
        return this.h;
    }
}

