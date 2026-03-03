/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.realms.RealmsSimpleScrolledSelectionList;

public class awt
extends awi {
    private final RealmsSimpleScrolledSelectionList u;

    public awt(RealmsSimpleScrolledSelectionList realmsSimpleScrolledSelectionList, int n2, int n3, int n4, int n5, int n6) {
        super(ave.A(), n2, n3, n4, n5, n6);
        this.u = realmsSimpleScrolledSelectionList;
    }

    @Override
    protected int b() {
        return this.u.getItemCount();
    }

    @Override
    protected void a(int n2, boolean bl2, int n3, int n4) {
        this.u.selectItem(n2, bl2, n3, n4);
    }

    @Override
    protected boolean a(int n2) {
        return this.u.isSelectedItem(n2);
    }

    @Override
    protected void a() {
        this.u.renderBackground();
    }

    @Override
    protected void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.u.renderItem(n2, n3, n4, n5, n6, n7);
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.j;
    }

    public int g() {
        return this.i;
    }

    @Override
    protected int k() {
        return this.u.getMaxPosition();
    }

    @Override
    protected int d() {
        return this.u.getScrollbarPosition();
    }

    @Override
    public void p() {
        super.p();
    }

    @Override
    public void a(int n2, int n3, float f2) {
        if (!this.q) {
            return;
        }
        this.i = n2;
        this.j = n3;
        this.a();
        int n4 = this.d();
        \u2603 = n4 + 6;
        this.l();
        bfl.f();
        bfl.n();
        bfx \u26032 = bfx.a();
        bfd \u26033 = \u26032.c();
        \u2603 = this.g + (this.b / 2 - this.c() / 2 + 2);
        \u2603 = this.d + 4 - (int)this.n;
        if (this.s) {
            this.a(\u2603, \u2603, \u26032);
        }
        this.b(\u2603, \u2603, n2, n3);
        bfl.i();
        \u2603 = 4;
        this.c(0, this.d, 255, 255);
        this.c(this.e, this.c, 255, 255);
        bfl.l();
        bfl.a(770, 771, 0, 1);
        bfl.c();
        bfl.j(7425);
        bfl.x();
        \u2603 = this.m();
        if (\u2603 > 0) {
            \u2603 = (this.e - this.d) * (this.e - this.d) / this.k();
            \u2603 = (int)this.n * (this.e - this.d - (\u2603 = ns.a(\u2603, 32, this.e - this.d - 8))) / \u2603 + this.d;
            if (\u2603 < this.d) {
                \u2603 = this.d;
            }
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)this.e, 0.0).a(0.0, 1.0).b(0, 0, 0, 255).d();
            \u26033.b((double)\u2603, (double)this.e, 0.0).a(1.0, 1.0).b(0, 0, 0, 255).d();
            \u26033.b((double)\u2603, (double)this.d, 0.0).a(1.0, 0.0).b(0, 0, 0, 255).d();
            \u26033.b((double)n4, (double)this.d, 0.0).a(0.0, 0.0).b(0, 0, 0, 255).d();
            \u26032.b();
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)(\u2603 + \u2603), 0.0).a(0.0, 1.0).b(128, 128, 128, 255).d();
            \u26033.b((double)\u2603, (double)(\u2603 + \u2603), 0.0).a(1.0, 1.0).b(128, 128, 128, 255).d();
            \u26033.b((double)\u2603, (double)\u2603, 0.0).a(1.0, 0.0).b(128, 128, 128, 255).d();
            \u26033.b((double)n4, (double)\u2603, 0.0).a(0.0, 0.0).b(128, 128, 128, 255).d();
            \u26032.b();
            \u26033.a(7, bms.i);
            \u26033.b((double)n4, (double)(\u2603 + \u2603 - 1), 0.0).a(0.0, 1.0).b(192, 192, 192, 255).d();
            \u26033.b((double)(\u2603 - 1), (double)(\u2603 + \u2603 - 1), 0.0).a(1.0, 1.0).b(192, 192, 192, 255).d();
            \u26033.b((double)(\u2603 - 1), (double)\u2603, 0.0).a(1.0, 0.0).b(192, 192, 192, 255).d();
            \u26033.b((double)n4, (double)\u2603, 0.0).a(0.0, 0.0).b(192, 192, 192, 255).d();
            \u26032.b();
        }
        this.b(n2, n3);
        bfl.w();
        bfl.j(7424);
        bfl.d();
        bfl.k();
    }
}

