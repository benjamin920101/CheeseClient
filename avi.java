/*
 * Decompiled with CFR 0.152.
 */
public class avi
implements nu {
    private String a = "";
    private ave b;
    private String c = "";
    private long d = ave.J();
    private boolean e;
    private avr f;
    private bfw g;

    public avi(ave ave2) {
        this.b = ave2;
        this.f = new avr(ave2);
        this.g = new bfw(ave2.d, ave2.e, false);
        this.g.a(9728);
    }

    @Override
    public void b(String string) {
        this.e = false;
        this.d(string);
    }

    @Override
    public void a(String string) {
        this.e = true;
        this.d(string);
    }

    private void d(String string) {
        this.c = string;
        if (!this.b.B) {
            if (this.e) {
                return;
            }
            throw new avk();
        }
        bfl.m(256);
        bfl.n(5889);
        bfl.D();
        if (bqs.i()) {
            int n2 = this.f.e();
            bfl.a(0.0, this.f.a() * n2, this.f.b() * n2, 0.0, 100.0, 300.0);
        } else {
            avr avr2 = new avr(this.b);
            bfl.a(0.0, avr2.c(), avr2.d(), 0.0, 100.0, 300.0);
        }
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -200.0f);
    }

    @Override
    public void c(String string) {
        if (!this.b.B) {
            if (this.e) {
                return;
            }
            throw new avk();
        }
        this.d = 0L;
        this.a = string;
        this.a(-1);
        this.d = 0L;
    }

    @Override
    public void a(int n2) {
        if (!this.b.B) {
            if (this.e) {
                return;
            }
            throw new avk();
        }
        long l2 = ave.J();
        if (l2 - this.d < 100L) {
            return;
        }
        this.d = l2;
        avr \u26032 = new avr(this.b);
        int \u26033 = \u26032.e();
        int \u26034 = \u26032.a();
        int \u26035 = \u26032.b();
        if (bqs.i()) {
            this.g.f();
        } else {
            bfl.m(256);
        }
        this.g.a(false);
        bfl.n(5889);
        bfl.D();
        bfl.a(0.0, \u26032.c(), \u26032.d(), 0.0, 100.0, 300.0);
        bfl.n(5888);
        bfl.D();
        bfl.b(0.0f, 0.0f, -200.0f);
        if (!bqs.i()) {
            bfl.m(16640);
        }
        bfx \u26036 = bfx.a();
        bfd \u26037 = \u26036.c();
        this.b.P().a(avp.b);
        float \u26038 = 32.0f;
        \u26037.a(7, bms.i);
        \u26037.b(0.0, (double)\u26035, 0.0).a(0.0, (float)\u26035 / \u26038).b(64, 64, 64, 255).d();
        \u26037.b((double)\u26034, (double)\u26035, 0.0).a((float)\u26034 / \u26038, (float)\u26035 / \u26038).b(64, 64, 64, 255).d();
        \u26037.b((double)\u26034, 0.0, 0.0).a((float)\u26034 / \u26038, 0.0).b(64, 64, 64, 255).d();
        \u26037.b(0.0, 0.0, 0.0).a(0.0, 0.0).b(64, 64, 64, 255).d();
        \u26036.b();
        if (n2 >= 0) {
            int n3 = 100;
            \u2603 = 2;
            \u2603 = \u26034 / 2 - n3 / 2;
            \u2603 = \u26035 / 2 + 16;
            bfl.x();
            \u26037.a(7, bms.f);
            \u26037.b((double)\u2603, (double)\u2603, 0.0).b(128, 128, 128, 255).d();
            \u26037.b((double)\u2603, (double)(\u2603 + \u2603), 0.0).b(128, 128, 128, 255).d();
            \u26037.b((double)(\u2603 + n3), (double)(\u2603 + \u2603), 0.0).b(128, 128, 128, 255).d();
            \u26037.b((double)(\u2603 + n3), (double)\u2603, 0.0).b(128, 128, 128, 255).d();
            \u26037.b((double)\u2603, (double)\u2603, 0.0).b(128, 255, 128, 255).d();
            \u26037.b((double)\u2603, (double)(\u2603 + \u2603), 0.0).b(128, 255, 128, 255).d();
            \u26037.b((double)(\u2603 + n2), (double)(\u2603 + \u2603), 0.0).b(128, 255, 128, 255).d();
            \u26037.b((double)(\u2603 + n2), (double)\u2603, 0.0).b(128, 255, 128, 255).d();
            \u26036.b();
            bfl.w();
        }
        bfl.l();
        bfl.a(770, 771, 1, 0);
        this.b.k.a(this.c, (float)((\u26034 - this.b.k.a(this.c)) / 2), (float)(\u26035 / 2 - 4 - 16), 0xFFFFFF);
        this.b.k.a(this.a, (float)((\u26034 - this.b.k.a(this.a)) / 2), (float)(\u26035 / 2 - 4 + 8), 0xFFFFFF);
        this.g.e();
        if (bqs.i()) {
            this.g.c(\u26034 * \u26033, \u26035 * \u26033);
        }
        this.b.h();
        try {
            Thread.yield();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void a() {
    }
}

