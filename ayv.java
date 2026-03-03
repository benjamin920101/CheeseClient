/*
 * Decompiled with CFR 0.152.
 */
public class ayv
extends ayl {
    private static final jy v = new jy("textures/gui/container/dispenser.png");
    private final wm w;
    public og u;

    public ayv(wm wm2, og og2) {
        super(new xr(wm2, og2));
        this.w = wm2;
        this.u = og2;
    }

    @Override
    protected void b(int n2, int n3) {
        String string = this.u.f_().c();
        this.q.a(string, this.f / 2 - this.q.a(string) / 2, 6, 0x404040);
        this.q.a(this.w.f_().c(), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(v);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
    }
}

