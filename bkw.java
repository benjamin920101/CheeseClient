/*
 * Decompiled with CFR 0.152.
 */
public class bkw
implements blb<vo> {
    private static final jy a = new jy("textures/entity/enderman/enderman_eyes.png");
    private final bis b;

    public bkw(bis bis2) {
        this.b = bis2;
    }

    @Override
    public void a(vo vo2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.b.a(a);
        bfl.l();
        bfl.c();
        bfl.b(1, 1);
        bfl.f();
        bfl.a(!vo2.ax());
        int n2 = 61680;
        \u2603 = n2 % 65536;
        \u2603 = n2 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        bfl.e();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b.b().a(vo2, f2, f3, f5, f6, f7, f8);
        this.b.a(vo2, f4);
        bfl.a(true);
        bfl.k();
        bfl.d();
    }

    @Override
    public boolean b() {
        return false;
    }
}

