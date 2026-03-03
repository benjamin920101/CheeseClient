/*
 * Decompiled with CFR 0.152.
 */
public class act
extends aci {
    protected act(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.h);
        this.c("untouching");
    }

    @Override
    public int a(int n2) {
        return 15;
    }

    @Override
    public int b(int n2) {
        return super.a(n2) + 50;
    }

    @Override
    public int b() {
        return 1;
    }

    @Override
    public boolean a(aci aci2) {
        return super.a(aci2) && aci2.B != act.u.B;
    }

    @Override
    public boolean a(zx zx2) {
        if (zx2.b() == zy.be) {
            return true;
        }
        return super.a(zx2);
    }
}

