/*
 * Decompiled with CFR 0.152.
 */
public class ra
extends rd {
    private ps a;

    public ra(ps ps2) {
        this.a = ps2;
        this.a(4);
        ((sv)ps2.s()).d(true);
    }

    @Override
    public boolean a() {
        return this.a.V() || this.a.ab();
    }

    @Override
    public void e() {
        if (this.a.bc().nextFloat() < 0.8f) {
            this.a.r().a();
        }
    }
}

