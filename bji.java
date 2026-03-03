/*
 * Decompiled with CFR 0.152.
 */
public class bji
extends bjo<vu> {
    private static final jy a = new jy("textures/entity/slime/magmacube.png");

    public bji(biu biu2) {
        super(biu2, new bbl(), 0.25f);
    }

    @Override
    protected jy a(vu vu2) {
        return a;
    }

    @Override
    protected void a(vu vu2, float f2) {
        int n2 = vu2.cm();
        float \u26032 = (vu2.c + (vu2.b - vu2.c) * f2) / ((float)n2 * 0.5f + 1.0f);
        float \u26033 = 1.0f / (\u26032 + 1.0f);
        float \u26034 = n2;
        bfl.a(\u26033 * \u26034, 1.0f / \u26033 * \u26034, \u26033 * \u26034);
    }
}

