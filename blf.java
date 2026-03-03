/*
 * Decompiled with CFR 0.152.
 */
public class blf
implements blb<wc> {
    private static final jy a = new jy("textures/entity/spider_eyes.png");
    private final bka b;

    public blf(bka bka2) {
        this.b = bka2;
    }

    @Override
    public void a(wc wc2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.b.a(a);
        bfl.l();
        bfl.c();
        bfl.b(1, 1);
        if (wc2.ax()) {
            bfl.a(false);
        } else {
            bfl.a(true);
        }
        int n2 = 61680;
        \u2603 = n2 % 65536;
        \u2603 = n2 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b.b().a(wc2, f2, f3, f5, f6, f7, f8);
        n2 = wc2.b(f4);
        \u2603 = n2 % 65536;
        \u2603 = n2 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        this.b.a(wc2, f4);
        bfl.k();
        bfl.d();
    }

    @Override
    public boolean b() {
        return false;
    }
}

