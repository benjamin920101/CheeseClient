/*
 * Decompiled with CFR 0.152.
 */
public class bdw
extends beb {
    private pk a;
    private pk az;
    private int aA;
    private int aB;
    private float aC;
    private biu aD = ave.A().af();

    public bdw(adm adm2, pk pk2, pk pk3, float f2) {
        super(adm2, pk2.s, pk2.t, pk2.u, pk2.v, pk2.w, pk2.x);
        this.a = pk2;
        this.az = pk3;
        this.aB = 3;
        this.aC = f2;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        \u2603 = ((float)this.aA + f2) / (float)this.aB;
        \u2603 *= \u2603;
        double d2 = this.a.s;
        \u2603 = this.a.t;
        \u2603 = this.a.u;
        \u2603 = this.az.P + (this.az.s - this.az.P) * (double)f2;
        \u2603 = this.az.Q + (this.az.t - this.az.Q) * (double)f2 + (double)this.aC;
        \u2603 = this.az.R + (this.az.u - this.az.R) * (double)f2;
        \u2603 = d2 + (\u2603 - d2) * (double)\u2603;
        \u2603 = \u2603 + (\u2603 - \u2603) * (double)\u2603;
        \u2603 = \u2603 + (\u2603 - \u2603) * (double)\u2603;
        int \u26032 = this.b(f2);
        int \u26033 = \u26032 % 65536;
        int \u26034 = \u26032 / 65536;
        bqs.a(bqs.r, (float)\u26033 / 1.0f, (float)\u26034 / 1.0f);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.aD.a(this.a, (float)(\u2603 -= aw), (float)(\u2603 -= ax), (float)(\u2603 -= ay), this.a.y, f2);
    }

    @Override
    public void t_() {
        ++this.aA;
        if (this.aA == this.aB) {
            this.J();
        }
    }

    @Override
    public int a() {
        return 3;
    }
}

