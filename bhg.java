/*
 * Decompiled with CFR 0.152.
 */
public class bhg
extends bhd<alf> {
    private static final jy c = new jy("textures/entity/chest/ender.png");
    private baz d = new baz();

    @Override
    public void a(alf alf2, double d2, double d3, double d4, float f2, int n2) {
        \u2603 = 0;
        if (alf2.t()) {
            \u2603 = alf2.u();
        }
        if (n2 >= 0) {
            this.a(a[n2]);
            bfl.n(5890);
            bfl.E();
            bfl.a(4.0f, 4.0f, 1.0f);
            bfl.b(0.0625f, 0.0625f, 0.0625f);
            bfl.n(5888);
        } else {
            this.a(c);
        }
        bfl.E();
        bfl.B();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.b((float)d2, (float)d3 + 1.0f, (float)d4 + 1.0f);
        bfl.a(1.0f, -1.0f, -1.0f);
        bfl.b(0.5f, 0.5f, 0.5f);
        \u2603 = 0;
        if (\u2603 == 2) {
            \u2603 = 180;
        }
        if (\u2603 == 3) {
            \u2603 = 0;
        }
        if (\u2603 == 4) {
            \u2603 = 90;
        }
        if (\u2603 == 5) {
            \u2603 = -90;
        }
        bfl.b((float)\u2603, 0.0f, 1.0f, 0.0f);
        bfl.b(-0.5f, -0.5f, -0.5f);
        float f3 = alf2.f + (alf2.a - alf2.f) * f2;
        f3 = 1.0f - f3;
        f3 = 1.0f - f3 * f3 * f3;
        this.d.a.f = -(f3 * (float)Math.PI / 2.0f);
        this.d.a();
        bfl.C();
        bfl.F();
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        if (n2 >= 0) {
            bfl.n(5890);
            bfl.F();
            bfl.n(5888);
        }
    }
}

