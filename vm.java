/*
 * Decompiled with CFR 0.152.
 */
public class vm
extends wc {
    public vm(adm adm2) {
        super(adm2);
        this.a(0.7f, 0.5f);
    }

    @Override
    protected void aX() {
        super.aX();
        this.a(vy.a).a(12.0);
    }

    @Override
    public boolean r(pk pk2) {
        if (super.r(pk2)) {
            if (pk2 instanceof pr) {
                int n2 = 0;
                if (this.o.aa() == oj.c) {
                    n2 = 7;
                } else if (this.o.aa() == oj.d) {
                    n2 = 15;
                }
                if (n2 > 0) {
                    ((pr)pk2).c(new pf(pe.u.H, n2 * 20, 0));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public pu a(ok ok2, pu pu2) {
        return pu2;
    }

    @Override
    public float aS() {
        return 0.45f;
    }
}

