/*
 * Decompiled with CFR 0.152.
 */
public class bkr
implements blb<vn> {
    private static final jy a = new jy("textures/entity/creeper/creeper_armor.png");
    private final bio b;
    private final bbc c = new bbc(2.0f);

    public bkr(bio bio2) {
        this.b = bio2;
    }

    @Override
    public void a(vn vn2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!vn2.n()) {
            return;
        }
        boolean bl2 = vn2.ax();
        bfl.a(!bl2);
        this.b.a(a);
        bfl.n(5890);
        bfl.D();
        float \u26032 = (float)vn2.W + f4;
        bfl.b(\u26032 * 0.01f, \u26032 * 0.01f, 0.0f);
        bfl.n(5888);
        bfl.l();
        float \u26033 = 0.5f;
        bfl.c(\u26033, \u26033, \u26033, 1.0f);
        bfl.f();
        bfl.b(1, 1);
        this.c.a(this.b.b());
        this.c.a(vn2, f2, f3, f5, f6, f7, f8);
        bfl.n(5890);
        bfl.D();
        bfl.n(5888);
        bfl.e();
        bfl.k();
        bfl.a(bl2);
    }

    @Override
    public boolean b() {
        return false;
    }
}

