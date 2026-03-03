/*
 * Decompiled with CFR 0.152.
 */
public class acb
extends aci {
    public acb(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.k);
        this.c("arrowDamage");
    }

    @Override
    public int a(int n2) {
        return 1 + (n2 - 1) * 10;
    }

    @Override
    public int b(int n2) {
        return this.a(n2) + 15;
    }

    @Override
    public int b() {
        return 5;
    }
}

