/*
 * Decompiled with CFR 0.152.
 */
public class bhh
extends bhd<all> {
    @Override
    public void a(all all2, double d2, double d3, double d4, float f2, int n2) {
        bfl.E();
        bfl.b((float)d2 + 0.5f, (float)d3, (float)d4 + 0.5f);
        bhh.a(all2.b(), d2, d3, d4, f2);
        bfl.F();
    }

    public static void a(add add2, double d2, double d3, double d4, float f2) {
        pk pk2 = add2.a(add2.a());
        if (pk2 != null) {
            float f3 = 0.4375f;
            bfl.b(0.0f, 0.4f, 0.0f);
            bfl.b((float)(add2.e() + (add2.d() - add2.e()) * (double)f2) * 10.0f, 0.0f, 1.0f, 0.0f);
            bfl.b(-30.0f, 1.0f, 0.0f, 0.0f);
            bfl.b(0.0f, -0.4f, 0.0f);
            bfl.a(f3, f3, f3);
            pk2.b(d2, d3, d4, 0.0f, 0.0f);
            ave.A().af().a(pk2, 0.0, 0.0, 0.0, 0.0f, f2);
        }
    }
}

