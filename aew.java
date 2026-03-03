/*
 * Decompiled with CFR 0.152.
 */
public class aew
extends aeb {
    protected aot M = new apq(afi.bH);

    @Override
    protected void a(ady ady2) {
        this.a();
        if (this.b.nextInt(5) == 0) {
            int n2 = this.b.nextInt(16) + 8;
            \u2603 = this.b.nextInt(16) + 8;
            this.M.b(this.a, this.b, this.a.r(this.c.a(n2, 0, \u2603)));
        }
        if (this.c.n() == 0 && this.c.p() == 0) {
            ug ug2 = new ug(this.a);
            ug2.b(0.0, 128.0, 0.0, this.b.nextFloat() * 360.0f, 0.0f);
            this.a.d(ug2);
        }
    }
}

