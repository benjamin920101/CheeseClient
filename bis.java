/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bis
extends bjo<vo> {
    private static final jy a = new jy("textures/entity/enderman/enderman.png");
    private bbd e;
    private Random j = new Random();

    public bis(biu biu2) {
        super(biu2, new bbd(0.0f), 0.5f);
        this.e = (bbd)this.f;
        this.a(new bkw(this));
        this.a(new bkq(this));
    }

    @Override
    public void a(vo vo22, double d2, double d3, double d4, float f2, float f3) {
        vo vo22;
        this.e.a = vo22.cm().c().t() != arm.a;
        this.e.b = vo22.co();
        if (vo22.co()) {
            double d5 = 0.02;
            d2 += this.j.nextGaussian() * d5;
            d4 += this.j.nextGaussian() * d5;
        }
        super.a(vo22, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(vo vo2) {
        return a;
    }
}

