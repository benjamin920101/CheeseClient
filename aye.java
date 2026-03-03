/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;
import org.lwjgl.input.Mouse;

public class aye
extends axu
implements ayg {
    private static final int y = mr.a * 24 - 112;
    private static final int z = mr.b * 24 - 112;
    private static final int A = mr.c * 24 - 77;
    private static final int B = mr.d * 24 - 77;
    private static final jy C = new jy("textures/gui/achievement/achievement_background.png");
    protected axu a;
    protected int f = 256;
    protected int g = 202;
    protected int h;
    protected int i;
    protected float r = 1.0f;
    protected double s;
    protected double t;
    protected double u;
    protected double v;
    protected double w;
    protected double x;
    private int D;
    private nb E;
    private boolean F = true;

    public aye(axu axu2, nb nb2) {
        this.a = axu2;
        this.E = nb2;
        int n2 = 141;
        \u2603 = 141;
        this.u = this.w = (double)(mr.f.a * 24 - n2 / 2 - 12);
        this.s = this.w;
        this.v = this.x = (double)(mr.f.b * 24 - \u2603 / 2);
        this.t = this.x;
    }

    @Override
    public void b() {
        this.j.u().a(new ig(ig.a.b));
        this.n.clear();
        this.n.add(new awe(1, this.l / 2 + 24, this.m / 2 + 74, 80, 20, bnq.a("gui.done", new Object[0])));
    }

    @Override
    protected void a(avs avs2) {
        if (this.F) {
            return;
        }
        if (avs2.k == 1) {
            this.j.a(this.a);
        }
    }

    @Override
    protected void a(char c2, int n2) {
        if (n2 == this.j.t.ae.i()) {
            this.j.a((axu)null);
            this.j.n();
        } else {
            super.a(c2, n2);
        }
    }

    @Override
    public void a(int n22, int n3, float f2) {
        if (this.F) {
            this.c();
            this.a(this.q, bnq.a("multiplayer.downloadingStats", new Object[0]), this.l / 2, this.m / 2, 0xFFFFFF);
            this.a(this.q, c_[(int)(ave.J() / 150L % (long)c_.length)], this.l / 2, this.m / 2 + this.q.a * 2, 0xFFFFFF);
        } else {
            int n22;
            int n4;
            if (Mouse.isButtonDown(0)) {
                n4 = (this.l - this.f) / 2;
                \u2603 = (this.m - this.g) / 2;
                \u2603 = n4 + 8;
                \u2603 = \u2603 + 17;
                if ((this.D == 0 || this.D == 1) && n22 >= \u2603 && n22 < \u2603 + 224 && n3 >= \u2603 && n3 < \u2603 + 155) {
                    if (this.D == 0) {
                        this.D = 1;
                    } else {
                        this.u -= (double)((float)(n22 - this.h) * this.r);
                        this.v -= (double)((float)(n3 - this.i) * this.r);
                        this.w = this.s = this.u;
                        this.x = this.t = this.v;
                    }
                    this.h = n22;
                    this.i = n3;
                }
            } else {
                this.D = 0;
            }
            n4 = Mouse.getDWheel();
            float \u26032 = this.r;
            if (n4 < 0) {
                this.r += 0.25f;
            } else if (n4 > 0) {
                this.r -= 0.25f;
            }
            this.r = ns.a(this.r, 1.0f, 2.0f);
            if (this.r != \u26032) {
                float f3 = \u26032 - this.r;
                \u2603 = \u26032 * (float)this.f;
                \u2603 = \u26032 * (float)this.g;
                \u2603 = this.r * (float)this.f;
                \u2603 = this.r * (float)this.g;
                this.u -= (double)((\u2603 - \u2603) * 0.5f);
                this.v -= (double)((\u2603 - \u2603) * 0.5f);
                this.w = this.s = this.u;
                this.x = this.t = this.v;
            }
            if (this.w < (double)y) {
                this.w = y;
            }
            if (this.x < (double)z) {
                this.x = z;
            }
            if (this.w >= (double)A) {
                this.w = A - 1;
            }
            if (this.x >= (double)B) {
                this.x = B - 1;
            }
            this.c();
            this.b(n22, n3, f2);
            bfl.f();
            bfl.i();
            this.f();
            bfl.e();
            bfl.j();
        }
    }

    @Override
    public void a() {
        if (this.F) {
            this.F = false;
        }
    }

    @Override
    public void e() {
        if (this.F) {
            return;
        }
        this.s = this.u;
        this.t = this.v;
        double d2 = this.w - this.u;
        \u2603 = this.x - this.v;
        if (d2 * d2 + \u2603 * \u2603 < 4.0) {
            this.u += d2;
            this.v += \u2603;
        } else {
            this.u += d2 * 0.85;
            this.v += \u2603 * 0.85;
        }
    }

    protected void f() {
        int n2 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.q.a(bnq.a("gui.achievements", new Object[0]), n2 + 15, \u2603 + 5, 0x404040);
    }

    protected void b(int n2, int n3, float f2) {
        int n4;
        int \u260313;
        int \u260311;
        int \u260310;
        int n5;
        int \u26037;
        int \u26035;
        int n6 = ns.c(this.s + (this.u - this.s) * (double)f2);
        \u2603 = ns.c(this.t + (this.v - this.t) * (double)f2);
        if (n6 < y) {
            n6 = y;
        }
        if (\u2603 < z) {
            \u2603 = z;
        }
        if (n6 >= A) {
            n6 = A - 1;
        }
        if (\u2603 >= B) {
            \u2603 = B - 1;
        }
        n4 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        \u2603 = n4 + 16;
        \u2603 = \u2603 + 17;
        this.e = 0.0f;
        bfl.c(518);
        bfl.E();
        bfl.b(\u2603, \u2603, -200.0f);
        bfl.a(1.0f / this.r, 1.0f / this.r, 0.0f);
        bfl.w();
        bfl.f();
        bfl.B();
        bfl.g();
        \u2603 = n6 + 288 >> 4;
        \u2603 = \u2603 + 288 >> 4;
        \u2603 = (n6 + 288) % 16;
        \u2603 = (\u2603 + 288) % 16;
        \u2603 = 4;
        \u2603 = 8;
        \u2603 = 10;
        \u2603 = 22;
        \u2603 = 37;
        Random \u26032 = new Random();
        float \u26033 = 16.0f / this.r;
        float \u26034 = 16.0f / this.r;
        n5 = 0;
        while ((float)n5 * \u26033 - (float)\u2603 < 155.0f) {
            float f3 = 0.6f - (float)(\u2603 + n5) / 25.0f * 0.3f;
            bfl.c(f3, f3, f3, 1.0f);
            \u26035 = 0;
            while ((float)\u26035 * \u26034 - (float)\u2603 < 224.0f) {
                \u26032.setSeed(this.j.L().b().hashCode() + (\u2603 + \u26035) + (\u2603 + n5) * 16);
                \u26037 = \u26032.nextInt(1 + \u2603 + n5) + (\u2603 + n5) / 2;
                bmi \u26036 = this.a(afi.m);
                if (\u26037 > 37 || \u2603 + n5 == 35) {
                    afh afh2 = afi.h;
                    \u26036 = this.a(afh2);
                } else if (\u26037 == 22) {
                    \u26036 = \u26032.nextInt(2) == 0 ? this.a(afi.ag) : this.a(afi.aC);
                } else if (\u26037 == 10) {
                    \u26036 = this.a(afi.p);
                } else if (\u26037 == 8) {
                    \u26036 = this.a(afi.q);
                } else if (\u26037 > 4) {
                    \u26036 = this.a(afi.b);
                } else if (\u26037 > 0) {
                    \u26036 = this.a(afi.d);
                }
                this.j.P().a(bmh.g);
                this.a(\u26035 * 16 - \u2603, n5 * 16 - \u2603, \u26036, 16, 16);
                ++\u26035;
            }
            ++n5;
        }
        bfl.j();
        bfl.c(515);
        this.j.P().a(C);
        for (n5 = 0; n5 < mr.e.size(); ++n5) {
            mq mq2 = mr.e.get(n5);
            if (mq2.c == null) continue;
            \u26035 = mq2.a * 24 - n6 + 11;
            \u26037 = mq2.b * 24 - \u2603 + 11;
            int \u26038 = mq2.c.a * 24 - n6 + 11;
            int \u26039 = mq2.c.b * 24 - \u2603 + 11;
            \u260310 = this.E.a(mq2) ? 1 : 0;
            \u260311 = this.E.b(mq2);
            int \u260312 = this.E.c(mq2);
            if (\u260312 > 4) continue;
            \u260313 = -16777216;
            if (\u260310 != 0) {
                \u260313 = -6250336;
            } else if (\u260311 != 0) {
                \u260313 = -16711936;
            }
            this.a(\u26035, \u26038, \u26037, \u260313);
            this.b(\u26038, \u26037, \u26039, \u260313);
            if (\u26035 > \u26038) {
                this.b(\u26035 - 11 - 7, \u26037 - 5, 114, 234, 7, 11);
                continue;
            }
            if (\u26035 < \u26038) {
                this.b(\u26035 + 11, \u26037 - 5, 107, 234, 7, 11);
                continue;
            }
            if (\u26037 > \u26039) {
                this.b(\u26035 - 5, \u26037 - 11 - 7, 96, 234, 11, 7);
                continue;
            }
            if (\u26037 >= \u26039) continue;
            this.b(\u26035 - 5, \u26037 + 11, 96, 241, 11, 7);
        }
        \u2603 = null;
        float f4 = (float)(n2 - \u2603) * this.r;
        \u2603 = (float)(n3 - \u2603) * this.r;
        avc.c();
        bfl.f();
        bfl.B();
        bfl.g();
        for (\u26037 = 0; \u26037 < mr.e.size(); ++\u26037) {
            float f5;
            mq mq3 = mr.e.get(\u26037);
            int \u260314 = mq3.a * 24 - n6;
            \u260310 = mq3.b * 24 - \u2603;
            if (\u260314 < -24 || \u260310 < -24 || !((float)\u260314 <= 224.0f * this.r) || !((float)\u260310 <= 155.0f * this.r)) continue;
            \u260311 = this.E.c(mq3);
            if (this.E.a(mq3)) {
                f5 = 0.75f;
                bfl.c(f5, f5, f5, 1.0f);
            } else if (this.E.b(mq3)) {
                f5 = 1.0f;
                bfl.c(f5, f5, f5, 1.0f);
            } else if (\u260311 < 3) {
                f5 = 0.3f;
                bfl.c(f5, f5, f5, 1.0f);
            } else if (\u260311 == 3) {
                f5 = 0.2f;
                bfl.c(f5, f5, f5, 1.0f);
            } else {
                if (\u260311 != 4) continue;
                f5 = 0.1f;
                bfl.c(f5, f5, f5, 1.0f);
            }
            this.j.P().a(C);
            if (mq3.g()) {
                this.b(\u260314 - 2, \u260310 - 2, 26, 202, 26, 26);
            } else {
                this.b(\u260314 - 2, \u260310 - 2, 0, 202, 26, 26);
            }
            if (!this.E.b(mq3)) {
                f5 = 0.1f;
                bfl.c(f5, f5, f5, 1.0f);
                this.k.a(false);
            }
            bfl.e();
            bfl.o();
            this.k.b(mq3.d, \u260314 + 3, \u260310 + 3);
            bfl.b(770, 771);
            bfl.f();
            if (!this.E.b(mq3)) {
                this.k.a(true);
            }
            bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
            if (!(f4 >= (float)\u260314) || !(f4 <= (float)(\u260314 + 22)) || !(\u2603 >= (float)\u260310) || !(\u2603 <= (float)(\u260310 + 22))) continue;
            \u2603 = mq3;
        }
        bfl.i();
        bfl.l();
        bfl.F();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(C);
        this.b(n4, \u2603, 0, 0, this.f, this.g);
        this.e = 0.0f;
        bfl.c(515);
        bfl.i();
        bfl.w();
        super.a(n2, n3, f2);
        if (\u2603 != null) {
            String string = \u2603.e().c();
            \u2603 = \u2603.f();
            int \u260315 = n2 + 12;
            \u260310 = n3 - 4;
            \u260311 = this.E.c(\u2603);
            if (this.E.b(\u2603)) {
                int n7 = Math.max(this.q.a(string), 120);
                \u260313 = this.q.b(\u2603, n7);
                if (this.E.a(\u2603)) {
                    \u260313 += 12;
                }
                this.a(\u260315 - 3, \u260310 - 3, \u260315 + n7 + 3, \u260310 + \u260313 + 3 + 12, -1073741824, -1073741824);
                this.q.a(\u2603, \u260315, \u260310 + 12, n7, -6250336);
                if (this.E.a(\u2603)) {
                    this.q.a(bnq.a("achievement.taken", new Object[0]), (float)\u260315, (float)(\u260310 + \u260313 + 4), -7302913);
                }
            } else if (\u260311 == 3) {
                string = bnq.a("achievement.unknown", new Object[0]);
                int \u260316 = Math.max(this.q.a(string), 120);
                \u2603 = new fb("achievement.requires", \u2603.c.e()).c();
                int \u260317 = this.q.b(\u2603, \u260316);
                this.a(\u260315 - 3, \u260310 - 3, \u260315 + \u260316 + 3, \u260310 + \u260317 + 12 + 3, -1073741824, -1073741824);
                this.q.a(\u2603, \u260315, \u260310 + 12, \u260316, -9416624);
            } else if (\u260311 < 3) {
                int \u260318 = Math.max(this.q.a(string), 120);
                \u2603 = new fb("achievement.requires", \u2603.c.e()).c();
                int \u260319 = this.q.b(\u2603, \u260318);
                this.a(\u260315 - 3, \u260310 - 3, \u260315 + \u260318 + 3, \u260310 + \u260319 + 12 + 3, -1073741824, -1073741824);
                this.q.a(\u2603, \u260315, \u260310 + 12, \u260318, -9416624);
            } else {
                string = null;
            }
            if (string != null) {
                this.q.a(string, (float)\u260315, (float)\u260310, this.E.b(\u2603) ? (\u2603.g() ? -128 : -1) : (\u2603.g() ? -8355776 : -8355712));
            }
        }
        bfl.j();
        bfl.e();
        avc.a();
    }

    private bmi a(afh afh2) {
        return ave.A().ae().a().a(afh2.Q());
    }

    @Override
    public boolean d() {
        return !this.F;
    }
}

