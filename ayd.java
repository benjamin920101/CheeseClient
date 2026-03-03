/*
 * Decompiled with CFR 0.152.
 */
public class ayd
extends avp {
    private static final jy a = new jy("textures/gui/achievement/achievement_background.png");
    private ave f;
    private int g;
    private int h;
    private String i;
    private String j;
    private mq k;
    private long l;
    private bjh m;
    private boolean n;

    public ayd(ave ave2) {
        this.f = ave2;
        this.m = ave2.ag();
    }

    public void a(mq mq2) {
        this.i = bnq.a("achievement.get", new Object[0]);
        this.j = mq2.e().c();
        this.l = ave.J();
        this.k = mq2;
        this.n = false;
    }

    public void b(mq mq2) {
        this.i = mq2.e().c();
        this.j = mq2.f();
        this.l = ave.J() + 2500L;
        this.k = mq2;
        this.n = true;
    }

    private void c() {
        bfl.b(0, 0, this.f.d, this.f.e);
        bfl.n(5889);
        bfl.D();
        bfl.n(5888);
        bfl.D();
        this.g = this.f.d;
        this.h = this.f.e;
        avr avr2 = new avr(this.f);
        this.g = avr2.a();
        this.h = avr2.b();
        bfl.m(256);
        bfl.n(5889);
        bfl.D();
        bfl.a(0.0, this.g, this.h, 0.0, 1000.0, 3000.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -2000.0f);
    }

    public void a() {
        if (this.k == null || this.l == 0L || ave.A().h == null) {
            return;
        }
        double d2 = (double)(ave.J() - this.l) / 3000.0;
        if (!this.n) {
            if (d2 < 0.0 || d2 > 1.0) {
                this.l = 0L;
                return;
            }
        } else if (d2 > 0.5) {
            d2 = 0.5;
        }
        this.c();
        bfl.i();
        bfl.a(false);
        \u2603 = d2 * 2.0;
        if (\u2603 > 1.0) {
            \u2603 = 2.0 - \u2603;
        }
        \u2603 *= 4.0;
        if ((\u2603 = 1.0 - \u2603) < 0.0) {
            \u2603 = 0.0;
        }
        \u2603 *= \u2603;
        \u2603 *= \u2603;
        int \u26032 = this.g - 160;
        int \u26033 = 0 - (int)(\u2603 * 36.0);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.w();
        this.f.P().a(a);
        bfl.f();
        this.b(\u26032, \u26033, 96, 202, 160, 32);
        if (this.n) {
            this.f.k.a(this.j, \u26032 + 30, \u26033 + 7, 120, -1);
        } else {
            this.f.k.a(this.i, \u26032 + 30, \u26033 + 7, -256);
            this.f.k.a(this.j, \u26032 + 30, \u26033 + 18, -1);
        }
        avc.c();
        bfl.f();
        bfl.B();
        bfl.g();
        bfl.e();
        this.m.b(this.k.d, \u26032 + 8, \u26033 + 8);
        bfl.f();
        bfl.a(true);
        bfl.j();
    }

    public void b() {
        this.k = null;
        this.l = 0L;
    }
}

