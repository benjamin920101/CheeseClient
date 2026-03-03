/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.realms.RealmsClickableScrolledSelectionList;
import net.minecraft.realms.Tezzelator;
import org.lwjgl.input.Mouse;

public class awq
extends awi {
    private final RealmsClickableScrolledSelectionList u;

    public awq(RealmsClickableScrolledSelectionList realmsClickableScrolledSelectionList, int n2, int n3, int n4, int n5, int n6) {
        super(ave.A(), n2, n3, n4, n5, n6);
        this.u = realmsClickableScrolledSelectionList;
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
        if (this.m > 0.0f && Mouse.getEventButtonState()) {
            this.u.customMouseEvent(this.d, this.e, this.t, this.n, this.h);
        }
    }

    public void a(int n2, int n3, int n4, Tezzelator tezzelator) {
        this.u.renderSelected(n2, n3, n4, tezzelator);
    }

    @Override
    protected void b(int n2, int n3, int n4, int n5) {
        \u2603 = this.b();
        for (\u2603 = 0; \u2603 < \u2603; ++\u2603) {
            \u2603 = n3 + \u2603 * this.h + this.t;
            \u2603 = this.h - 4;
            if (\u2603 > this.e || \u2603 + \u2603 < this.d) {
                this.a(\u2603, n2, \u2603);
            }
            if (this.r && this.a(\u2603)) {
                this.a(this.b, \u2603, \u2603, Tezzelator.instance);
            }
            this.a(\u2603, n2, \u2603, \u2603, n4, n5);
        }
    }
}

