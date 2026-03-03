/*
 * Decompiled with CFR 0.152.
 */
public class bdv
extends beb {
    private int a;
    private int az = 8;

    protected bdv(adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
    }

    @Override
    public void t_() {
        for (int i2 = 0; i2 < 6; ++i2) {
            double d2 = this.s + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
            \u2603 = this.t + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
            \u2603 = this.u + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
            this.o.a(cy.b, d2, \u2603, \u2603, (double)((float)this.a / (float)this.az), 0.0, 0.0, new int[0]);
        }
        ++this.a;
        if (this.a == this.az) {
            this.J();
        }
    }

    @Override
    public int a() {
        return 1;
    }

    public static class a
    implements bed {
        @Override
        public beb a(int n2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7, int ... nArray) {
            return new bdv(adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

