/*
 * Decompiled with CFR 0.152.
 */
public class axs
extends axu {
    private bcy a;
    private int f;

    public axs(bcy bcy2) {
        this.a = bcy2;
    }

    @Override
    protected void a(char c2, int n2) {
    }

    @Override
    public void b() {
        this.n.clear();
    }

    @Override
    public void e() {
        ++this.f;
        if (this.f % 20 == 0) {
            this.a.a(new io());
        }
    }

    @Override
    public void a(int n2, int n3, float f2) {
        this.c(0);
        this.a(this.q, bnq.a("multiplayer.downloadingTerrain", new Object[0]), this.l / 2, this.m / 2 - 50, 0xFFFFFF);
        super.a(n2, n3, f2);
    }

    @Override
    public boolean d() {
        return false;
    }
}

