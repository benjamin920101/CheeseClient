/*
 * Decompiled with CFR 0.152.
 */
public class bkj
extends bjo<uk> {
    private static final jy a = new jy("textures/entity/wither/wither_invulnerable.png");
    private static final jy e = new jy("textures/entity/wither/wither.png");

    public bkj(biu biu2) {
        super(biu2, new bcl(0.0f), 1.0f);
        this.a(new blj(this));
    }

    @Override
    public void a(uk uk2, double d2, double d3, double d4, float f2, float f3) {
        bfc.a(uk2, true);
        super.a(uk2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(uk uk2) {
        int n2 = uk2.cl();
        if (n2 <= 0 || n2 <= 80 && n2 / 5 % 2 == 1) {
            return e;
        }
        return a;
    }

    @Override
    protected void a(uk uk2, float f2) {
        \u2603 = 2.0f;
        int n2 = uk2.cl();
        if (n2 > 0) {
            \u2603 -= ((float)n2 - f2) / 220.0f * 0.5f;
        }
        bfl.a(\u2603, \u2603, \u2603);
    }
}

