/*
 * Decompiled with CFR 0.152.
 */
import net.minecraft.realms.RealmsScrolledSelectionList;

public class aws
extends awi {
    private final RealmsScrolledSelectionList u;

    public aws(RealmsScrolledSelectionList realmsScrolledSelectionList, int n2, int n3, int n4, int n5, int n6) {
        super(ave.A(), n2, n3, n4, n5, n6);
        this.u = realmsScrolledSelectionList;
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
}

