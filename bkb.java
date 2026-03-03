/*
 * Decompiled with CFR 0.152.
 */
public class bkb
extends bjo<tx> {
    private static final jy a = new jy("textures/entity/squid.png");

    public bkb(biu biu2, bbo bbo2, float f2) {
        super(biu2, bbo2, f2);
    }

    @Override
    protected jy a(tx tx2) {
        return a;
    }

    @Override
    protected void a(tx tx2, float f2, float f3, float f4) {
        \u2603 = tx2.b + (tx2.a - tx2.b) * f4;
        \u2603 = tx2.bk + (tx2.c - tx2.bk) * f4;
        bfl.b(0.0f, 0.5f, 0.0f);
        bfl.b(180.0f - f3, 0.0f, 1.0f, 0.0f);
        bfl.b(\u2603, 1.0f, 0.0f, 0.0f);
        bfl.b(\u2603, 0.0f, 1.0f, 0.0f);
        bfl.b(0.0f, -1.2f, 0.0f);
    }

    protected float a(tx tx2, float f2) {
        return tx2.bo + (tx2.bn - tx2.bo) * f2;
    }

    @Override
    protected /* synthetic */ float b(pr pr2, float f2) {
        return this.a((tx)pr2, f2);
    }
}

