/*
 * Decompiled with CFR 0.152.
 */
public class aaa
extends yo {
    private final ahs b;

    public aaa(ahs ahs2) {
        super(ahs2);
        this.b = ahs2;
        this.d(0);
        this.a(true);
    }

    @Override
    public int a(int n2) {
        return n2 | 4;
    }

    @Override
    public int a(zx zx2, int n2) {
        return this.b.h(this.b.a(zx2.i()));
    }

    @Override
    public String e_(zx zx2) {
        return super.a() + "." + this.b.b(zx2.i()).d();
    }
}

