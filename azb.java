/*
 * Decompiled with CFR 0.152.
 */
public class azb
extends ayl {
    private static final jy u = new jy("textures/gui/container/horse.png");
    private og v;
    private og w;
    private tp x;
    private float y;
    private float z;

    public azb(og og2, og og3, tp tp2) {
        super(new xx(og2, og3, tp2, ave.A().h));
        this.v = og2;
        this.w = og3;
        this.x = tp2;
        this.p = false;
    }

    @Override
    protected void b(int n2, int n3) {
        this.q.a(this.w.f_().c(), 8, 6, 0x404040);
        this.q.a(this.v.f_().c(), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(u);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        if (this.x.cw()) {
            this.b(\u2603 + 79, \u2603 + 17, 0, this.g, 90, 54);
        }
        if (this.x.cO()) {
            this.b(\u2603 + 7, \u2603 + 35, 0, this.g + 54, 18, 18);
        }
        azc.a(\u2603 + 51, \u2603 + 60, 17, (float)(\u2603 + 51) - this.y, (float)(\u2603 + 75 - 50) - this.z, this.x);
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.y = n2;
        this.z = n3;
        super.a(n2, n3, f2);
    }
}

