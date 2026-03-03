/*
 * Decompiled with CFR 0.152.
 */
public class bkt
implements blb<bet> {
    private final bln a;

    public bkt(bln bln2) {
        this.a = bln2;
    }

    @Override
    public void a(bet bet2, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (!bet2.e_().equals("deadmau5") || !bet2.g() || bet2.ax()) {
            return;
        }
        this.a.a(bet2.i());
        for (int i2 = 0; i2 < 2; ++i2) {
            float f9 = bet2.A + (bet2.y - bet2.A) * f4 - (bet2.aJ + (bet2.aI - bet2.aJ) * f4);
            \u2603 = bet2.B + (bet2.z - bet2.B) * f4;
            bfl.E();
            bfl.b(f9, 0.0f, 1.0f, 0.0f);
            bfl.b(\u2603, 1.0f, 0.0f, 0.0f);
            bfl.b(0.375f * (float)(i2 * 2 - 1), 0.0f, 0.0f);
            bfl.b(0.0f, -0.375f, 0.0f);
            bfl.b(-\u2603, 1.0f, 0.0f, 0.0f);
            bfl.b(-f9, 0.0f, 1.0f, 0.0f);
            \u2603 = 1.3333334f;
            bfl.a(\u2603, \u2603, \u2603);
            this.a.g().b(0.0625f);
            bfl.F();
        }
    }

    @Override
    public boolean b() {
        return true;
    }
}

