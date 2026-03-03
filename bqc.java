/*
 * Decompiled with CFR 0.152.
 */
public class bqc
implements bqb<bpw> {
    private final bpw a;
    private final int b;

    bqc(bpw bpw2, int n2) {
        this.a = bpw2;
        this.b = n2;
    }

    @Override
    public int a() {
        return this.b;
    }

    public bpw b() {
        return new bpw(this.a);
    }

    @Override
    public /* synthetic */ Object g() {
        return this.b();
    }
}

