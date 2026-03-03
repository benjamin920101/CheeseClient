/*
 * Decompiled with CFR 0.152.
 */
public class bik
extends biv<ux> {
    private static final jy e = new jy("textures/entity/boat.png");
    protected bbo a = new bax();

    public bik(biu biu2) {
        super(biu2);
        this.c = 0.5f;
    }

    @Override
    public void a(ux ux2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.b((float)d2, (float)d3 + 0.25f, (float)d4);
        bfl.b(180.0f - f2, 0.0f, 1.0f, 0.0f);
        \u2603 = (float)ux2.l() - f3;
        \u2603 = ux2.j() - f3;
        if (\u2603 < 0.0f) {
            \u2603 = 0.0f;
        }
        if (\u2603 > 0.0f) {
            bfl.b(ns.a(\u2603) * \u2603 * \u2603 / 10.0f * (float)ux2.m(), 1.0f, 0.0f, 0.0f);
        }
        \u2603 = 0.75f;
        bfl.a(\u2603, \u2603, \u2603);
        bfl.a(1.0f / \u2603, 1.0f / \u2603, 1.0f / \u2603);
        this.c(ux2);
        bfl.a(-1.0f, -1.0f, 1.0f);
        this.a.a(ux2, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        bfl.F();
        super.a(ux2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(ux ux2) {
        return e;
    }
}

