/*
 * Decompiled with CFR 0.152.
 */
public class bdj
extends beb {
    protected bdj(adm adm2, double d2, double d3, double d4, zw zw2) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.a(ave.A().ag().a().a(zw2));
        this.at = 1.0f;
        this.as = 1.0f;
        this.ar = 1.0f;
        this.x = 0.0;
        this.w = 0.0;
        this.v = 0.0;
        this.i = 0.0f;
        this.g = 80;
    }

    @Override
    public int a() {
        return 1;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = this.av.e();
        \u2603 = this.av.f();
        \u2603 = this.av.g();
        \u2603 = this.av.h();
        \u2603 = 0.5f;
        \u2603 = (float)(this.p + (this.s - this.p) * (double)f2 - aw);
        \u2603 = (float)(this.q + (this.t - this.q) * (double)f2 - ax);
        \u2603 = (float)(this.r + (this.u - this.r) * (double)f2 - ay);
        int n2 = this.b(f2);
        \u2603 = n2 >> 16 & 0xFFFF;
        \u2603 = n2 & 0xFFFF;
        bfd2.b((double)(\u2603 - f3 * 0.5f - f6 * 0.5f), (double)(\u2603 - f4 * 0.5f), (double)(\u2603 - f5 * 0.5f - f7 * 0.5f)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 - f3 * 0.5f + f6 * 0.5f), (double)(\u2603 + f4 * 0.5f), (double)(\u2603 - f5 * 0.5f + f7 * 0.5f)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * 0.5f + f6 * 0.5f), (double)(\u2603 + f4 * 0.5f), (double)(\u2603 + f5 * 0.5f + f7 * 0.5f)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * 0.5f - f6 * 0.5f), (double)(\u2603 - f4 * 0.5f), (double)(\u2603 + f5 * 0.5f - f7 * 0.5f)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdj(adm2, d2, d3, d4, zw.a(afi.cv));
        }
    }
}

