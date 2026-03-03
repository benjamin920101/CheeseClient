/*
 * Decompiled with CFR 0.152.
 */
public class bdu
extends beb {
    private static final jy a = new jy("textures/entity/explosion.png");
    private static final bmu az = new bmu().a(bms.m).a(bms.o).a(bms.n).a(bms.p).a(bms.q).a(bms.r);
    private int aA;
    private int aB;
    private bmj aC;
    private float aD;

    protected bdu(bmj bmj2, adm adm2, double d2, double d3, double d4, double d5, double d6, double d7) {
        super(adm2, d2, d3, d4, 0.0, 0.0, 0.0);
        this.aC = bmj2;
        this.aB = 6 + this.V.nextInt(4);
        this.as = this.at = this.V.nextFloat() * 0.6f + 0.4f;
        this.ar = this.at;
        this.aD = 1.0f - (float)d5 * 0.5f;
    }

    @Override
    public void a(bfd bfd2, pk pk2, float f2, float f3, float f4, float f5, float f6, float f7) {
        int n2 = (int)(((float)this.aA + f2) * 15.0f / (float)this.aB);
        if (n2 > 15) {
            return;
        }
        this.aC.a(a);
        float \u26032 = (float)(n2 % 4) / 4.0f;
        float \u26033 = \u26032 + 0.24975f;
        float \u26034 = (float)(n2 / 4) / 4.0f;
        float \u26035 = \u26034 + 0.24975f;
        float \u26036 = 2.0f * this.aD;
        float \u26037 = (float)(this.p + (this.s - this.p) * (double)f2 - aw);
        float \u26038 = (float)(this.q + (this.t - this.q) * (double)f2 - ax);
        float \u26039 = (float)(this.r + (this.u - this.r) * (double)f2 - ay);
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        bfl.f();
        avc.a();
        bfd2.a(7, az);
        bfd2.b((double)(\u26037 - f3 * \u26036 - f6 * \u26036), (double)(\u26038 - f4 * \u26036), (double)(\u26039 - f5 * \u26036 - f7 * \u26036)).a(\u26033, \u26035).a(this.ar, this.as, this.at, 1.0f).a(0, 240).c(0.0f, 1.0f, 0.0f).d();
        bfd2.b((double)(\u26037 - f3 * \u26036 + f6 * \u26036), (double)(\u26038 + f4 * \u26036), (double)(\u26039 - f5 * \u26036 + f7 * \u26036)).a(\u26033, \u26034).a(this.ar, this.as, this.at, 1.0f).a(0, 240).c(0.0f, 1.0f, 0.0f).d();
        bfd2.b((double)(\u26037 + f3 * \u26036 + f6 * \u26036), (double)(\u26038 + f4 * \u26036), (double)(\u26039 + f5 * \u26036 + f7 * \u26036)).a(\u26032, \u26034).a(this.ar, this.as, this.at, 1.0f).a(0, 240).c(0.0f, 1.0f, 0.0f).d();
        bfd2.b((double)(\u26037 + f3 * \u26036 - f6 * \u26036), (double)(\u26038 - f4 * \u26036), (double)(\u26039 + f5 * \u26036 - f7 * \u26036)).a(\u26032, \u26035).a(this.ar, this.as, this.at, 1.0f).a(0, 240).c(0.0f, 1.0f, 0.0f).d();
        bfx.a().b();
        bfl.e();
    }

    @Override
    public int b(float f2) {
        return 61680;
    }

    @Override
    public void t_() {
        this.p = this.s;
        this.q = this.t;
        this.r = this.u;
        ++this.aA;
        if (this.aA == this.aB) {
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
            return new bdu(ave.A().P(), adm2, d2, d3, d4, d5, d6, d7);
        }
    }
}

