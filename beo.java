/*
 * Decompiled with CFR 0.152.
 */
public class beo
extends beb {
    private alz a;
    private cj az;

    protected beo(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, alz alz2) {
        super(adm2, d2, d3, d4, d5, d6, d7);
        this.a = alz2;
        this.a(ave.A().ae().a().a(alz2));
        this.i = alz2.c().I;
        this.at = 0.6f;
        this.as = 0.6f;
        this.ar = 0.6f;
        this.h /= 2.0f;
    }

    public beo a(cj cj2) {
        this.az = cj2;
        if (this.a.c() == afi.c) {
            return this;
        }
        int n2 = this.a.c().d((adq)this.o, cj2);
        this.ar *= (float)(n2 >> 16 & 0xFF) / 255.0f;
        this.as *= (float)(n2 >> 8 & 0xFF) / 255.0f;
        this.at *= (float)(n2 & 0xFF) / 255.0f;
        return this;
    }

    public beo l() {
        this.az = new cj(this.s, this.t, this.u);
        afh afh2 = this.a.c();
        if (afh2 == afi.c) {
            return this;
        }
        int \u26032 = afh2.h(this.a);
        this.ar *= (float)(\u26032 >> 16 & 0xFF) / 255.0f;
        this.as *= (float)(\u26032 >> 8 & 0xFF) / 255.0f;
        this.at *= (float)(\u26032 & 0xFF) / 255.0f;
        return this;
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

    @Override
    public int b(float f2) {
        int n2 = super.b(f2);
        \u2603 = 0;
        if (this.o.e(this.az)) {
            \u2603 = this.o.b(this.az, 0);
        }
        return n2 == 0 ? \u2603 : n2;
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new beo(adm2, d2, d3, d4, d5, d6, d7, afh.d(nArray[0])).l();
        }
    }
}

