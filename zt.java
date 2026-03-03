/*
 * Decompiled with CFR 0.152.
 */
import java.util.List;

public class zt
extends zs {
    public zt(int n2, float f2, boolean bl2) {
        super(n2, f2, bl2);
        this.a(true);
    }

    @Override
    public boolean f(zx zx2) {
        return zx2.i() > 0;
    }

    @Override
    public aaj g(zx zx2) {
        if (zx2.i() == 0) {
            return aaj.c;
        }
        return aaj.d;
    }

    @Override
    protected void c(zx zx2, adm adm2, wn wn2) {
        if (!adm2.D) {
            wn2.c(new pf(pe.x.H, 2400, 0));
        }
        if (zx2.i() > 0) {
            if (!adm2.D) {
                wn2.c(new pf(pe.l.H, 600, 4));
                wn2.c(new pf(pe.m.H, 6000, 0));
                wn2.c(new pf(pe.n.H, 6000, 0));
            }
        } else {
            super.c(zx2, adm2, wn2);
        }
    }

    @Override
    public void a(zw zw2, yz yz2, List<zx> list) {
        list.add(new zx(zw2, 1, 0));
        list.add(new zx(zw2, 1, 1));
    }
}

