/*
 * Decompiled with CFR 0.152.
 */
public class bkv
implements blb<ug> {
    private static final jy a = new jy("textures/entity/enderdragon/dragon_eyes.png");
    private final bir b;

    public bkv(bir bir2) {
        this.b = bir2;
    }

    @Override
    public void a(ug ug2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        this.b.a(a);
        bfl.l();
        bfl.c();
        bfl.b(1, 1);
        bfl.f();
        bfl.c(514);
        int n2 = 61680;
        \u2603 = n2 % 65536;
        \u2603 = n2 / 65536;
        bqs.a(bqs.r, (float)\u2603 / 1.0f, (float)\u2603 / 1.0f);
        bfl.e();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.b.b().a(ug2, f2, f3, f5, f6, f7, f8);
        this.b.a(ug2, f4);
        bfl.k();
        bfl.d();
        bfl.c(515);
    }

    @Override
    public boolean b() {
        return false;
    }
}

