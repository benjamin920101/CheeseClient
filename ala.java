/*
 * Decompiled with CFR 0.152.
 */
public class ala
extends akw {
    private int a;

    @Override
    public void b(dn dn2) {
        super.b(dn2);
        dn2.a("OutputSignal", this.a);
    }

    @Override
    public void a(dn dn2) {
        super.a(dn2);
        this.a = dn2.f("OutputSignal");
    }

    public int b() {
        return this.a;
    }

    public void a(int n2) {
        this.a = n2;
    }
}

