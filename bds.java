/*
 * Decompiled with CFR 0.152.
 */
public class bds
extends beb {
    private static final jy a = new jy("textures/particle/footprint.png");
    private int az;
    private int aA;
    private bmj aB;

    protected bds(bmj bmj2, adm adm2, double d2, double d3, double d4) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.aB = bmj2;
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
        this.aA = 200;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.az + f2) / (float)this.aA;
        if ((\u2603 = 2.0f - (\u2603 *= \u2603) * 2.0f) > 1.0f) {
            \u2603 = 1.0f;
        }
        \u2603 *= 0.2f;
        bfl.f();
        \u2603 = 0.125f;
        \u2603 = (float)(this.s - aw);
        \u2603 = (float)(this.t - ax);
        \u2603 = (float)(this.u - ay);
        \u2603 = this.o.o(new cj(this));
        this.aB.a(a);
        bfl.l();
        bfl.b(770, 771);
        bfd2.a(7, bms.i);
        bfd2.b((double)(\u2603 - 0.125f), (double)\u2603, (double)(\u2603 + 0.125f)).a(0.0, 1.0).a(\u2603, \u2603, \u2603, \u2603).d();
        bfd2.b((double)(\u2603 + 0.125f), (double)\u2603, (double)(\u2603 + 0.125f)).a(1.0, 1.0).a(\u2603, \u2603, \u2603, \u2603).d();
        bfd2.b((double)(\u2603 + 0.125f), (double)\u2603, (double)(\u2603 - 0.125f)).a(1.0, 0.0).a(\u2603, \u2603, \u2603, \u2603).d();
        bfd2.b((double)(\u2603 - 0.125f), (double)\u2603, (double)(\u2603 - 0.125f)).a(0.0, 0.0).a(\u2603, \u2603, \u2603, \u2603).d();
        bfx.a().b();
        bfl.k();
        bfl.e();
    }

    @Override
    public void t_() {
        ++this.az;
        if (this.az == this.aA) {
            this.J();
        }
    }

    @Override
    public int a() {
        return 3;
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bds(ave.A().P(), adm2, d2, d3, d4);
        }
    }
}

