/*
 * Decompiled with CFR 0.152.
 */
public class ace
extends aci {
    public ace(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.k);
        this.c("arrowKnockback");
    }

    @Override
    public int a(int n2) {
        return 12 + (n2 - 1) * 20;
    }

    @Override
    public int b(int n2) {
        return this.a(n2) + 25;
    }

    @Override
    public int b() {
        return 2;
    }
}

