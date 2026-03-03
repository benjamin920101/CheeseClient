/*
 * Decompiled with CFR 0.152.
 */
public class bkg
extends bjo<ty> {
    private static final jy a = new jy("textures/entity/iron_golem.png");

    public bkg(biu biu2) {
        super(biu2, new bch(), 0.5f);
        this.a(new blh(this));
    }

    @Override
    protected jy a(ty ty2) {
        return a;
    }

    @Override
    protected void a(ty ty2, float f2, float f3, float f4) {
        super.a(ty2, f2, f3, f4);
        if ((double)ty2.aB < 0.01) {
            return;
        }
        \u2603 = 13.0f;
        \u2603 = ty2.aC - ty2.aB * (1.0f - f4) + 6.0f;
        \u2603 = (Math.abs(\u2603 % \u2603 - \u2603 * 0.5f) - \u2603 * 0.25f) / (\u2603 * 0.25f);
        bfl.b(6.5f * \u2603, 0.0f, 0.0f, 1.0f);
    }
}

