/*
 * Decompiled with CFR 0.152.
 */
public class bep
extends beb {
    private pk a;
    private int az;
    private int aA;
    private cy aB;

    public bep(adm adm2, pk pk2, cy cy2) {
        super(adm2, pk2.s, pk2.aR().b + (double)(pk2.K / 2.0f), pk2.u, pk2.v, pk2.w, pk2.x);
        this.a = pk2;
        this.aA = 3;
        this.aB = cy2;
        this.t_();
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
    }

    @Override
    public void t_() {
        for (int i2 = 0; i2 < 16; ++i2) {
            double d2 = this.V.nextFloat() * 2.0f - 1.0f;
            if (d2 * d2 + (\u2603 = (double)(this.V.nextFloat() * 2.0f - 1.0f)) * \u2603 + (\u2603 = (double)(this.V.nextFloat() * 2.0f - 1.0f)) * \u2603 > 1.0) continue;
            \u2603 = this.a.s + d2 * (double)this.a.J / 4.0;
            \u2603 = this.a.aR().b + (double)(this.a.K / 2.0f) + \u2603 * (double)this.a.K / 4.0;
            \u2603 = this.a.u + \u2603 * (double)this.a.J / 4.0;
            this.o.a(this.aB, false, \u2603, \u2603, \u2603, d2, \u2603 + 0.2, \u2603, new int[0]);
        }
        ++this.az;
        if (this.az >= this.aA) {
            this.J();
        }
    }

    @Override
    public int a() {
        return 3;
    }
}

