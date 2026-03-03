/*
 * Decompiled with CFR 0.152.
 */
public class aco
extends aci {
    protected aco(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.g);
        this.c("knockback");
    }

    @Override
    public int a(int n2) {
        return 5 + 20 * (n2 - 1);
    }

    @Override
    public int b(int n2) {
        return super.a(n2) + 50;
    }

    @Override
    public int b() {
        return 2;
    }
}

