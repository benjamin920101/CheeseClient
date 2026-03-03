/*
 * Decompiled with CFR 0.152.
 */
public class acq
extends aci {
    public acq(int n2, jy jy2, int n3) {
        super(n2, jy2, n3, acj.f);
        this.c("oxygen");
    }

    @Override
    public int a(int n2) {
        return 10 * n2;
    }

    @Override
    public int b(int n2) {
        return this.a(n2) + 30;
    }

    @Override
    public int b() {
        return 3;
    }
}

