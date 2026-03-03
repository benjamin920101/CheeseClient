/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class bej
extends beb {
    private static final Random a = new Random();
    private int az = 128;

    protected bej(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, 0.5 - a.nextDouble(), d6, 0.5 - a.nextDouble());
        this.w *= (double)0.2f;
        if (d5 == 0.0 && d7 == 0.0) {
            this.v *= (double)0.1f;
            this.x *= (double)0.1f;
        }
        this.h *= 0.75f;
        this.g = (int)(8.0 / (Math.random() * 0.8 + 0.2));
        this.T = false;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.f + f2) / (float)this.g * 32.0f;
        \u2603 = ns.a(\u2603, 0.0f, 1.0f);
        super.a(bfd2, pk2, f2, f3, f4, f5, f6, f7);
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        if (this.f++ >= this.g) {
            this.J();
        }
        this.k(this.az + (7 - this.f * 8 / this.g));
        this.w += 0.004;
        this.d(this.v, this.w, this.x);
        if (this.t == this.q) {
            this.v *= 1.1;
            this.x *= 1.1;
        }
        this.v *= (double)0.96f;
        this.w *= (double)0.96f;
        this.x *= (double)0.96f;
        if (this.C) {
            this.v *= (double)0.7f;
            this.x *= (double)0.7f;
        }
    }

    public void a(int n2) {
        this.az = n2;
    }

    public static class b
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bej bej2 = new bej(adm2, d2, d3, d4, d5, d6, d7);
            bej2.a(144);
            return bej2;
        }
    }

    public static class e
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bej bej2 = new bej(adm2, d2, d3, d4, d5, d6, d7);
            bej2.a(144);
            float \u26032 = adm2.s.nextFloat() * 0.5f + 0.35f;
            bej2.b(1.0f * \u26032, 0.0f * \u26032, 1.0f * \u26032);
            return bej2;
        }
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bej bej2 = new bej(adm2, d2, d3, d4, d5, d6, d7);
            bej2.i(0.15f);
            bej2.b((float)d5, (float)d6, (float)d7);
            return bej2;
        }
    }

    public static class c
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            bej bej2 = new bej(adm2, d2, d3, d4, d5, d6, d7);
            bej2.b((float)d5, (float)d6, (float)d7);
            return bej2;
        }
    }

    public static class d
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bej(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

