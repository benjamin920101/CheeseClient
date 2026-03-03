/*
 * Decompiled with CFR 0.152.
 */
public class bjq
extends bjo<ts> {
    private static final jy a = new jy("textures/entity/cat/black.png");
    private static final jy e = new jy("textures/entity/cat/ocelot.png");
    private static final jy j = new jy("textures/entity/cat/red.png");
    private static final jy k = new jy("textures/entity/cat/siamese.png");

    public bjq(biu biu2, bbo bbo2, float f2) {
        super(biu2, bbo2, f2);
    }

    @Override
    protected jy a(ts ts2) {
        switch (ts2.ct()) {
            default: {
                return e;
            }
            case 1: {
                return a;
            }
            case 2: {
                return j;
            }
            case 3: 
        }
        return k;
    }

    @Override
    protected void a(ts ts2, float f2) {
        super.a(ts2, f2);
        if (ts2.cl()) {
            bfl.a(0.8f, 0.8f, 0.8f);
        }
    }
}

