/*
 * Decompiled with CFR 0.152.
 */
public class biq
extends biv<uf> {
    private static final jy a = new jy("textures/entity/endercrystal/endercrystal.png");
    private bbo e = new bcp(0.0f, true);

    public biq(biu biu2) {
        super(biu2);
        this.c = 0.5f;
    }

    @Override
    public void a(uf uf2, double d2, double d3, double d4, float f2, float f3) {
        \u2603 = (float)uf2.a + f3;
        bfl.E();
        bfl.b((float)d2, (float)d3, (float)d4);
        this.a(a);
        \u2603 = ns.a(\u2603 * 0.2f) / 2.0f + 0.5f;
        \u2603 = \u2603 * \u2603 + \u2603;
        this.e.a(uf2, 0.0f, \u2603 * 3.0f, \u2603 * 0.2f, 0.0f, 0.0f, 0.0625f);
        bfl.F();
        super.a(uf2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(uf uf2) {
        return a;
    }
}

