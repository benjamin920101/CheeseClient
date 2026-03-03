/*
 * Decompiled with CFR 0.152.
 */
public class bkh
extends bjo<wi> {
    private static final jy a = new jy("textures/entity/villager/villager.png");
    private static final jy e = new jy("textures/entity/villager/farmer.png");
    private static final jy j = new jy("textures/entity/villager/librarian.png");
    private static final jy k = new jy("textures/entity/villager/priest.png");
    private static final jy l = new jy("textures/entity/villager/smith.png");
    private static final jy m = new jy("textures/entity/villager/butcher.png");

    public bkh(biu biu2) {
        super(biu2, new bci(0.0f), 0.5f);
        this.a(new bks(this.g().a));
    }

    public bci g() {
        return (bci)super.b();
    }

    @Override
    protected jy a(wi wi2) {
        switch (wi2.cl()) {
            case 0: {
                return e;
            }
            case 1: {
                return j;
            }
            case 2: {
                return k;
            }
            case 3: {
                return l;
            }
            case 4: {
                return m;
            }
        }
        return a;
    }

    @Override
    protected void a(wi wi2, float f2) {
        \u2603 = 0.9375f;
        if (wi2.l() < 0) {
            \u2603 = (float)((double)\u2603 * 0.5);
            this.c = 0.25f;
        } else {
            this.c = 0.5f;
        }
        bfl.a(\u2603, \u2603, \u2603);
    }

    @Override
    public /* synthetic */ bbo b() {
        return this.g();
    }
}

