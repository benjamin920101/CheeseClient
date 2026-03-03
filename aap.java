/*
 * Decompiled with CFR 0.152.
 */
public class aap
extends zw {
    public aap() {
        this.c(1);
        this.d(238);
        this.a(yz.i);
    }

    @Override
    public boolean a(zx zx2, adm adm2, afh afh2, cj cj2, pr pr2) {
        if (afh2.t() == arm.j || afh2 == afi.G || afh2 == afi.H || afh2 == afi.bn || afh2 == afi.bS || afh2 == afi.L) {
            zx2.a(1, pr2);
            return true;
        }
        return super.a(zx2, adm2, afh2, cj2, pr2);
    }

    @Override
    public boolean b(afh afh2) {
        return afh2 == afi.G || afh2 == afi.af || afh2 == afi.bS;
    }

    @Override
    public float a(zx zx2, afh afh2) {
        if (afh2 == afi.G || afh2.t() == arm.j) {
            return 15.0f;
        }
        if (afh2 == afi.L) {
            return 5.0f;
        }
        return super.a(zx2, afh2);
    }
}

