/*
 * Decompiled with CFR 0.152.
 */
public class acn
extends aci {
    protected acn(int n2, jy jy2, int n3, acj acj2) {
        super(n2, jy2, n3, acj2);
        this.c("fishingSpeed");
    }

    @Override
    public int a(int n2) {
        return 15 + (n2 - 1) * 9;
    }

    @Override
    public int b(int n2) {
        return super.a(n2) + 50;
    }

    @Override
    public int b() {
        return 3;
    }
}

