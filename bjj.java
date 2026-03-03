/*
 * Decompiled with CFR 0.152.
 */
public class bjj
extends biv<up> {
    private static final jy a = new jy("textures/entity/lead_knot.png");
    private bbm e = new bbm();

    public bjj(biu biu2) {
        super(biu2);
    }

    @Override
    public void a(up up2, double d2, double d3, double d4, float f2, float f3) {
        bfl.E();
        bfl.p();
        bfl.b((float)d2, (float)d3, (float)d4);
        \u2603 = 0.0625f;
        bfl.B();
        bfl.a(-1.0f, -1.0f, 1.0f);
        bfl.d();
        this.c(up2);
        this.e.a(up2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, \u2603);
        bfl.F();
        super.a(up2, d2, d3, d4, f2, f3);
    }

    @Override
    protected jy a(up up2) {
        return a;
    }
}

