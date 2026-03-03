/*
 * Decompiled with CFR 0.152.
 */
public class awm
extends avp
implements bai {
    private static final jy f = new jy("textures/gui/widgets.png");
    public static final jy a = new jy("textures/gui/spectator_widgets.png");
    private final ave g;
    private long h;
    private baf i;

    public awm(ave ave2) {
        this.g = ave2;
    }

    public void a(int n2) {
        this.h = ave.J();
        if (this.i != null) {
            this.i.b(n2);
        } else {
            this.i = new baf(this);
        }
    }

    private float c() {
        long l2 = this.h - ave.J() + 5000L;
        return ns.a((float)l2 / 2000.0f, 0.0f, 1.0f);
    }

    public void a(avr avr2, float f2) {
        if (this.i == null) {
            return;
        }
        \u2603 = this.c();
        if (\u2603 <= 0.0f) {
            this.i.d();
            return;
        }
        int n2 = avr2.a() / 2;
        float \u26032 = this.e;
        this.e = -90.0f;
        float \u26033 = (float)avr2.b() - 22.0f * \u2603;
        baj \u26034 = this.i.f();
        this.a(avr2, \u2603, n2, \u26033, \u26034);
        this.e = \u26032;
    }

    protected void a(avr avr2, float f2, int n2, float f3, baj baj2) {
        bfl.B();
        bfl.l();
        bfl.a(770, 771, 1, 0);
        bfl.c(1.0f, 1.0f, 1.0f, f2);
        this.g.P().a(f);
        this.a((float)(n2 - 91), f3, 0, 0, 182, 22);
        if (baj2.b() >= 0) {
            this.a((float)(n2 - 91 - 1 + baj2.b() * 20), f3 - 1.0f, 0, 22, 24, 22);
        }
        avc.c();
        for (int i2 = 0; i2 < 9; ++i2) {
            this.a(i2, avr2.a() / 2 - 90 + i2 * 20 + 2, f3 + 3.0f, f2, baj2.a(i2));
        }
        avc.a();
        bfl.C();
        bfl.k();
    }

    private void a(int n2, int n3, float f2, float f3, bah bah2) {
        this.g.P().a(a);
        if (bah2 != baf.a) {
            int n4 = (int)(f3 * 255.0f);
            bfl.E();
            bfl.b(n3, f2, 0.0f);
            float \u26032 = bah2.B_() ? 1.0f : 0.25f;
            bfl.c(\u26032, \u26032, \u26032, f3);
            bah2.a(\u26032, n4);
            bfl.F();
            String \u26033 = String.valueOf(avh.c(this.g.t.av[n2].i()));
            if (n4 > 3 && bah2.B_()) {
                this.g.k.a(\u26033, (float)(n3 + 19 - 2 - this.g.k.a(\u26033)), f2 + 6.0f + 3.0f, 0xFFFFFF + (n4 << 24));
            }
        }
    }

    public void a(avr avr2) {
        int n2 = (int)(this.c() * 255.0f);
        if (n2 > 3 && this.i != null) {
            bah bah2 = this.i.b();
            String string = \u2603 = bah2 != baf.a ? bah2.A_().d() : this.i.c().b().d();
            if (\u2603 != null) {
                int n3 = (avr2.a() - this.g.k.a(\u2603)) / 2;
                \u2603 = avr2.b() - 35;
                bfl.E();
                bfl.l();
                bfl.a(770, 771, 1, 0);
                this.g.k.a(\u2603, (float)n3, (float)\u2603, 0xFFFFFF + (n2 << 24));
                bfl.k();
                bfl.F();
            }
        }
    }

    @Override
    public void a(baf baf2) {
        this.i = null;
        this.h = 0L;
    }

    public boolean a() {
        return this.i != null;
    }

    public void b(int n2) {
        for (\u2603 = this.i.e() + n2; !(\u2603 < 0 || \u2603 > 8 || this.i.a(\u2603) != baf.a && this.i.a(\u2603).B_()); \u2603 += n2) {
        }
        if (\u2603 >= 0 && \u2603 <= 8) {
            this.i.b(\u2603);
            this.h = ave.J();
        }
    }

    public void b() {
        this.h = ave.J();
        if (this.a()) {
            int n2 = this.i.e();
            if (n2 != -1) {
                this.i.b(n2);
            }
        } else {
            this.i = new baf(this);
        }
    }
}

