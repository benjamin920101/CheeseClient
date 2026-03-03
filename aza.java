/*
 * Decompiled with CFR 0.152.
 */
public class aza
extends ayl {
    private static final jy u = new jy("textures/gui/container/hopper.png");
    private og v;
    private og w;

    public aza(wm wm2, og og2) {
        super(new xw(wm2, og2, ave.A().h));
        this.v = wm2;
        this.w = og2;
        this.p = false;
        this.g = 133;
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
    }
}

