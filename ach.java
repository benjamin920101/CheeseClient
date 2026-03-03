/*
 * Decompiled with CFR 0.152.
 */
public class ach
extends aci {
    protected ach(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.h);
        this.c("digging");
    }

    @Override
    public int a(int n2) {
        return 1 + 10 * (n2 - 1);
    }

    @Override
    public int b(int n2) {
        return super.a(n2) + 50;
    }

    @Override
    public int b() {
        return 5;
    }

    @Override
    public boolean a(zx zx2) {
        if (zx2.b() == zy.be) {
            return true;
        }
        return super.a(zx2);
    }
}

