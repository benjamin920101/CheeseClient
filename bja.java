/*
 * Decompiled with CFR 0.152.
 */
public class bja
extends bjo<vr> {
    private static final jy a = new jy("textures/entity/ghast/ghast.png");
    private static final jy e = new jy("textures/entity/ghast/ghast_shooting.png");

    public bja(biu biu2) {
        super(biu2, new bbf(), 0.5f);
    }

    @Override
    protected jy a(vr vr2) {
        if (vr2.n()) {
            return e;
        }
        return a;
    }

    @Override
    protected void a(vr vr2, float f2) {
        \u2603 = 1.0f;
        \u2603 = (8.0f + \u2603) / 2.0f;
        \u2603 = (8.0f + 1.0f / \u2603) / 2.0f;
        bfl.a(\u2603, \u2603, \u2603);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
    }
}

