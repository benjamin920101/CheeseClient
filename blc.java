/*
 * Decompiled with CFR 0.152.
 */
public class blc
implements blb<tv> {
    private static final jy a = new jy("textures/entity/sheep/sheep_fur.png");
    private final bjv b;
    private final bbv c = new bbv();

    public blc(bjv bjv2) {
        this.b = bjv2;
    }

    @Override
    public void a(tv tv2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (tv2.cm() || tv2.ax()) {
            return;
        }
        this.b.a(a);
        if (tv2.l_() && "jeb_".equals(tv2.aM())) {
            int n2 = 25;
            \u2603 = tv2.W / 25 + tv2.F();
            \u2603 = zd.values().length;
            \u2603 = \u2603 % \u2603;
            \u2603 = (\u2603 + 1) % \u2603;
            float \u26032 = ((float)(tv2.W % 25) + f4) / 25.0f;
            float[] \u26033 = tv.a(zd.b(\u2603));
            float[] \u26034 = tv.a(zd.b(\u2603));
            bfl.c(\u26033[0] * (1.0f - \u26032) + \u26034[0] * \u26032, \u26033[1] * (1.0f - \u26032) + \u26034[1] * \u26032, \u26033[2] * (1.0f - \u26032) + \u26034[2] * \u26032);
        } else {
            float[] \u26035 = tv.a(tv2.cl());
            bfl.c(\u26035[0], \u26035[1], \u26035[2]);
        }
        this.c.a(this.b.b());
        this.c.a(tv2, f2, f3, f4);
        this.c.a(tv2, f2, f3, f5, f6, f7, f8);
    }

    @Override
    public boolean b() {
        return true;
    }
}

