/*
 * Decompiled with CFR 0.152.
 */
public class oy
extends ox {
    private pk r;

    public oy(String string, pk pk2, pk pk3) {
        super(string, pk2);
        this.r = pk3;
    }

    @Override
    public pk i() {
        return this.q;
    }

    @Override
    public pk j() {
        return this.r;
    }

    @Override
    public eu b(pr pr2) {
        eu eu2 = this.r == null ? this.q.f_() : this.r.f_();
        zx \u26032 = this.r instanceof pr ? ((pr)this.r).bA() : null;
        String \u26033 = "death.attack." + this.p;
        String \u26034 = \u26033 + ".item";
        if (\u26032 != null && \u26032.s() && di.c(\u26034)) {
            return new fb(\u26034, pr2.f_(), eu2, \u26032.C());
        }
        return new fb(\u26033, pr2.f_(), eu2);
    }
}

