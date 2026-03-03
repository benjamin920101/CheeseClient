/*
 * Decompiled with CFR 0.152.
 */
public class acu
extends aci {
    public acu(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.c);
        this.c("waterWalker");
    }

    @Override
    public int a(int n2) {
        return n2 * 10;
    }

    @Override
    public int b(int n2) {
        return this.a(n2) + 15;
    }

    @Override
    public int b() {
        return 3;
    }
}

