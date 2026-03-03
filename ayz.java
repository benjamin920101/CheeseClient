/*
 * Decompiled with CFR 0.152.
 */
public class ayz
extends ayl {
    private static final jy u = new jy("textures/gui/container/furnace.png");
    private final wm v;
    private og w;

    public ayz(wm wm2, og og2) {
        super(new xu(wm2, og2));
        this.v = wm2;
        this.w = og2;
    }

    @Override
    protected void b(int n2, int n3) {
        String string = this.w.f_().c();
        this.q.a(string, this.f / 2 - this.q.a(string) / 2, 6, 0x404040);
        this.q.a(this.v.f_().c(), 8, this.g - 96 + 2, 0x404040);
    }

    @Override
    protected void a(float f2, int n2, int n3) {
        bfl.c(1.0f, 1.0f, 1.0f, 1.0f);
        this.j.P().a(u);
        \u2603 = (this.l - this.f) / 2;
        \u2603 = (this.m - this.g) / 2;
        this.b(\u2603, \u2603, 0, 0, this.f, this.g);
        if (alh.a(this.w)) {
            \u2603 = this.i(13);
            this.b(\u2603 + 56, \u2603 + 36 + 12 - \u2603, 176, 12 - \u2603, 14, \u2603 + 1);
        }
        \u2603 = this.h(24);
        this.b(\u2603 + 79, \u2603 + 34, 176, 14, \u2603 + 1, 16);
    }

    private int h(int n2) {
        \u2603 = this.w.a_(2);
        \u2603 = this.w.a_(3);
        if (\u2603 == 0 || \u2603 == 0) {
            return 0;
        }
        return \u2603 * n2 / \u2603;
    }

    private int i(int n2) {
        \u2603 = this.w.a_(1);
        if (\u2603 == 0) {
            \u2603 = 200;
        }
        return this.w.a_(0) * n2 / \u2603;
    }
}

