/*
 * Decompiled with CFR 0.152.
 */
public class aam
extends zw {
    public aam() {
        this.h = 1;
        this.a(yz.e);
    }

    @Override
    public boolean a(zx zx2, wn wn2, pr pr2) {
        if (pr2 instanceof tt) {
            tt tt2 = (tt)pr2;
            if (!tt2.cl() && !tt2.j_()) {
                tt2.l(true);
                tt2.o.a(tt2, "mob.horse.leather", 0.5f, 1.0f);
                --zx2.b;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean a(zx zx2, pr pr2, pr pr3) {
        this.a(zx2, null, pr2);
        return true;
    }
}

