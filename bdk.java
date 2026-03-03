/*
 * Decompiled with CFR 0.152.
 */
public class bdk
extends beb {
    protected bdk(adm adm2, double d2, double d3, double d4, zw zw2) {
        this(adm2, d2, d3, d4, zw2, 0);
    }

    protected bdk(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, zw zw2, int n2) {
        this(adm2, d2, d3, d4, zw2, n2);
        this.v *= (double)0.1f;
        this.w *= (double)0.1f;
        this.x *= (double)0.1f;
        this.v += d5;
        this.w += d6;
        this.x += d7;
    }

    protected bdk(adm adm2, double d2, double d3, double d4, zw zw2, int n2) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.a(ave.A().ag().a().a(zw2, n2));
        this.at = 1.0f;
        this.as = 1.0f;
        this.ar = 1.0f;
        this.i = afi.aJ.I;
        this.h /= 2.0f;
    }

    @Override
    public int a() {
        return 1;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.b + this.d / 4.0f) / 16.0f;
        \u2603 = \u2603 + 0.015609375f;
        \u2603 = ((float)this.c + this.e / 4.0f) / 16.0f;
        \u2603 = \u2603 + 0.015609375f;
        \u2603 = 0.1f * this.h;
        if (this.av != null) {
            \u2603 = this.av.a(this.d / 4.0f * 16.0f);
            \u2603 = this.av.a((this.d + 1.0f) / 4.0f * 16.0f);
            \u2603 = this.av.b(this.e / 4.0f * 16.0f);
            \u2603 = this.av.b((this.e + 1.0f) / 4.0f * 16.0f);
        }
        \u2603 = (float)(this.p + (this.s - this.p) * (double)f2 - aw);
        \u2603 = (float)(this.q + (this.t - this.q) * (double)f2 - ax);
        \u2603 = (float)(this.r + (this.u - this.r) * (double)f2 - ay);
        int n2 = this.b(f2);
        \u2603 = n2 >> 16 & 0xFFFF;
        \u2603 = n2 & 0xFFFF;
        bfd2.b((double)(\u2603 - f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 - f5 * \u2603 - f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 - f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 - f5 * \u2603 + f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * \u2603 + f6 * \u2603), (double)(\u2603 + f4 * \u2603), (double)(\u2603 + f5 * \u2603 + f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
        bfd2.b((double)(\u2603 + f3 * \u2603 - f6 * \u2603), (double)(\u2603 - f4 * \u2603), (double)(\u2603 + f5 * \u2603 - f7 * \u2603)).a(\u2603, \u2603).a(this.ar, this.as, this.at, 1.0f).a(\u2603, \u2603).d();
    }

    public static class c
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdk(adm2, d2, d3, d4, zy.aD);
        }
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdk(adm2, d2, d3, d4, zy.aM);
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            int n3 = nArray.length > 1 ? nArray[1] : 0;
            return new bdk(adm2, d2, d3, d4, d5, d6, d7, zw.b(nArray[0]), n3);
        }
    }
}

