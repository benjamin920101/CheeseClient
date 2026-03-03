/*
 * Decompiled with CFR 0.152.
 */
public class ays
extends ayl {
    private static final jy u = new jy("textures/gui/container/crafting_table.png");

    public ays(wm wm2, adm adm2) {
        this(wm2, adm2, cj.a);
    }

    public ays(wm wm2, adm adm2, cj cj2) {
        super(new xq(wm2, adm2, cj2));
    }

    @Override
    protected void b(int n2, int n3) {
        this.q.a(bnq.a("container.crafting", new Object[0]), 28, 6, 0x404040);
        this.q.a(bnq.a("container.inventory", new Object[0]), 8, this.g - 96 + 2, 0x404040);
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

