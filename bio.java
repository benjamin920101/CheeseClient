/*
 * Decompiled with CFR 0.152.
 */
public class bio
extends bjo<vn> {
    private static final jy a = new jy("textures/entity/creeper/creeper.png");

    public bio(biu biu2) {
        super(biu2, new bbc(), 0.5f);
        this.a(new bkr(this));
    }

    @Override
    protected void a(vn vn2, float f2) {
        \u2603 = vn2.a(f2);
        \u2603 = 1.0f + ns.a(\u2603 * 100.0f) * \u2603 * 0.01f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        \u2603 *= \u2603;
        \u2603 *= \u2603;
        \u2603 = (1.0f + \u2603 * 0.4f) * \u2603;
        \u2603 = (1.0f + \u2603 * 0.1f) / \u2603;
        bfl.a(\u2603, \u2603, \u2603);
    }

    @Override
    protected int a(vn vn2, float f2, float f3) {
        \u2603 = vn2.a(f3);
        if ((int)(\u2603 * 10.0f) % 2 == 0) {
            return 0;
        }
        int n2 = (int)(\u2603 * 0.2f * 255.0f);
        n2 = ns.a(n2, 0, 255);
        return n2 << 24 | 0xFFFFFF;
    }

    @Override
    protected jy a(vn vn2) {
        return a;
    }
}

