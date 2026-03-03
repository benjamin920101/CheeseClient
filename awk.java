/*
 * Decompiled with CFR 0.152.
 */
public class awk {
    private static final jy a = new jy("textures/gui/stream_indicator.png");
    private final ave b;
    private float c = 1.0f;
    private int d = 1;

    public awk(ave ave2) {
        this.b = ave2;
    }

    public void a(int n2, int n3) {
        if (this.b.Y().k()) {
            bfl.l();
            \u2603 = this.b.Y().x();
            if (\u2603 > 0) {
                String string = "" + \u2603;
                int \u26032 = this.b.k.a(string);
                int \u26033 = 20;
                int \u26034 = n2 - \u26032 - 1;
                int \u26035 = n3 + 20 - 1;
                int \u26036 = n2;
                int \u26037 = n3 + 20 + this.b.k.a - 1;
                bfl.x();
                bfx \u26038 = bfx.a();
                bfd \u26039 = \u26038.c();
                bfl.c(0.0f, 0.0f, 0.0f, (0.65f + 0.35000002f * this.c) / 2.0f);
                \u26039.a(7, bms.e);
                \u26039.b((double)\u26034, (double)\u26037, 0.0).d();
                \u26039.b((double)\u26036, (double)\u26037, 0.0).d();
                \u26039.b((double)\u26036, (double)\u26035, 0.0).d();
                \u26039.b((double)\u26034, (double)\u26035, 0.0).d();
                \u26038.b();
                bfl.w();
                this.b.k.a(string, n2 - \u26032, n3 + 20, 0xFFFFFF);
            }
            this.a(n2, n3, this.b(), 0);
            this.a(n2, n3, this.c(), 17);
        }
    }

    private void a(int n2, int n3, int n4, int n5) {
        bfl.c(1.0f, 1.0f, 1.0f, 0.65f + 0.35000002f * this.c);
        this.b.P().a(a);
        float f2 = 150.0f;
        \u2603 = 0.0f;
        \u2603 = (float)n4 * 0.015625f;
        \u2603 = 1.0f;
        \u2603 = (float)(n4 + 16) * 0.015625f;
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u26033.a(7, bms.g);
        \u26033.b((double)(n2 - 16 - n5), (double)(n3 + 16), (double)f2).a(\u2603, \u2603).d();
        \u26033.b((double)(n2 - n5), (double)(n3 + 16), (double)f2).a(\u2603, \u2603).d();
        \u26033.b((double)(n2 - n5), (double)(n3 + 0), (double)f2).a(\u2603, \u2603).d();
        \u26033.b((double)(n2 - 16 - n5), (double)(n3 + 0), (double)f2).a(\u2603, \u2603).d();
        \u26032.b();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private int b() {
        return this.b.Y().l() ? 16 : 0;
    }

    private int c() {
        return this.b.Y().D() ? 48 : 32;
    }

    public void a() {
        if (this.b.Y().k()) {
            this.c += 0.025f * (float)this.d;
            if (this.c < 0.0f) {
                this.d *= -1;
                this.c = 0.0f;
            } else if (this.c > 1.0f) {
                this.d *= -1;
                this.c = 1.0f;
            }
        } else {
            this.c = 1.0f;
            this.d = 1;
        }
    }
}

