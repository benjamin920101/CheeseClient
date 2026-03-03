/*
 * Decompiled with CFR 0.152.
 */
public class bkl
extends bjo<ua> {
    private static final jy a = new jy("textures/entity/wolf/wolf.png");
    private static final jy e = new jy("textures/entity/wolf/wolf_tame.png");
    private static final jy j = new jy("textures/entity/wolf/wolf_angry.png");

    public bkl(biu biu2, bbo bbo2, float f2) {
        super(biu2, bbo2, f2);
        this.a(new blk(this));
    }

    protected float a(ua ua2, float f2) {
        return ua2.cu();
    }

    @Override
    public void a(ua ua2, double d2, double d3, double d4, float f2, float f3) {
        if (ua2.ct()) {
            \u2603 = ua2.c(f3) * ua2.p(f3);
            bfl.c(\u2603, \u2603, \u2603);
        }
        super.a(ua2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(ua ua2) {
        if (ua2.cl()) {
            return e;
        }
        if (ua2.cv()) {
            return j;
        }
        return a;
    }

    @Override
    protected /* synthetic */ float b(pr pr2, float f2) {
        return this.a((ua)pr2, f2);
    }
}

