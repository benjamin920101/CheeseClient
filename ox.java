/*
 * Decompiled with CFR 0.152.
 */
public class ox
extends ow {
    protected pk q;
    private boolean r = false;

    public ox(String string, pk pk2) {
        super(string);
        this.q = pk2;
    }

    public ox v() {
        this.r = true;
        return this;
    }

    public boolean w() {
        return this.r;
    }

    @Override
    public pk j() {
        return this.q;
    }

    @Override
    public eu b(pr pr2) {
        zx zx2 = this.q instanceof pr ? ((pr)this.q).bA() : null;
        String \u26032 = "death.attack." + this.p;
        String \u26033 = \u26032 + ".item";
        if (zx2 != null && zx2.s() && di.c(\u26033)) {
            return new fb(\u26033, pr2.f_(), this.q.f_(), zx2.C());
        }
        return new fb(\u26032, pr2.f_(), this.q.f_());
    }

    @Override
    public boolean r() {
        return this.q != null && this.q instanceof pr && !(this.q instanceof wn);
    }
}

