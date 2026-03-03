/*
 * Decompiled with CFR 0.152.
 */
public class blj
implements blb<uk> {
    private static final jy a = new jy("textures/entity/wither/wither_armor.png");
    private final bkj b;
    private final bcl c = new bcl(0.5f);

    public blj(bkj bkj2) {
        this.b = bkj2;
    }

    @Override
    public void a(uk uk2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!uk2.cm()) {
            return;
        }
        bfl.a(!uk2.ax());
        this.b.a(a);
        bfl.n(5890);
        bfl.D();
        \u2603 = (float)uk2.W + f4;
        \u2603 = ns.b(\u2603 * 0.02f) * 3.0f;
        \u2603 = \u2603 * 0.01f;
        bfl.b(\u2603, \u2603, 0.0f);
        bfl.n(5888);
        bfl.l();
        \u2603 = 0.5f;
        bfl.c(\u2603, \u2603, \u2603, 1.0f);
        bfl.f();
        bfl.b(1, 1);
        this.c.a(uk2, f2, f3, f4);
        this.c.a(this.b.b());
        this.c.a(uk2, f2, f3, f5, f6, f7, f8);
        bfl.n(5890);
        bfl.D();
        bfl.n(5888);
        bfl.e();
        bfl.k();
    }

    @Override
    public boolean b() {
        return false;
    }
}

