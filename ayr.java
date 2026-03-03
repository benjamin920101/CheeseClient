/*
 * Decompiled with CFR 0.152.
 */
public class ayr
extends ayl {
    private static final jy u = new jy("textures/gui/container/generic_54.png");
    private og v;
    private og w;
    private int x;

    public ayr(og og2, og og3) {
        super(new xo(og2, og3, ave.A().h));
        this.v = og2;
        this.w = og3;
        this.p = false;
        int n2 = 222;
        \u2603 = n2 - 108;
        this.x = og3.o_() / 9;
        this.g = \u2603 + this.x * 18;
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
        this.b(\u2603, \u2603, 0, 0, this.f, this.x * 18 + 17);
        this.b(\u2603, \u2603 + this.x * 18 + 17, 0, 126, this.f, 96);
    }
}

