/*
 * Decompiled with CFR 0.152.
 */
public class bkk
extends biv<xd> {
    private static final jy a = new jy("textures/entity/wither/wither_invulnerable.png");
    private static final jy e = new jy("textures/entity/wither/wither.png");
    private final bbz f = new bbz();

    public bkk(biu biu2) {
        super(biu2);
    }

    private float a(float f2, float f3, float f4) {
        for (\u2603 = f3 - f2; \u2603 < -180.0f; \u2603 += 360.0f) {
        }
        while (\u2603 >= 180.0f) {
            \u2603 -= 360.0f;
        }
        return f2 + f4 * \u2603;
    }

    @Override
    public void a(xd xd2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.p();
        \u2603 = this.a(xd2.A, xd2.y, f3);
        \u2603 = xd2.B + (xd2.z - xd2.B) * f3;
        bfl.b((float)d2, (float)d3, (float)d4);
        \u2603 = 0.0625f;
        bfl.B();
        bfl.a(-1.0f, -1.0f, 1.0f);
        bfl.d();
        this.c(xd2);
        this.f.a(xd2, 0.0f, 0.0f, 0.0f, \u2603, \u2603, \u2603);
        bfl.F();
        super.a(xd2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(xd xd2) {
        return xd2.l() ? a : e;
    }
}

