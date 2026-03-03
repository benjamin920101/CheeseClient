/*
 * Decompiled with CFR 0.152.
 */
public class aqs
extends ate {
    private dn b = new dn();

    public aqs(String string) {
        super(string);
    }

    @Override
    public void a(dn dn2) {
        this.b = dn2.m("Features");
    }

    @Override
    public void b(dn dn2) {
        dn2.a("Features", this.b);
    }

    public void a(dn dn2, int n2, int n3) {
        this.b.a(aqs.b(n2, n3), dn2);
    }

    public static String b(int n2, int n3) {
        return "[" + n2 + "," + n3 + "]";
    }

    public dn a() {
        return this.b;
    }
}

