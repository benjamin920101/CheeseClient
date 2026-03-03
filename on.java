/*
 * Decompiled with CFR 0.152.
 */
public class on {
    public static final on a = new on("");
    private final String b;

    public on(String string) {
        this.b = string;
    }

    public boolean a() {
        return this.b == null || this.b.isEmpty();
    }

    public String b() {
        return this.b;
    }

    public void a(dn dn2) {
        dn2.a("Lock", this.b);
    }

    public static on b(dn dn2) {
        if (dn2.b("Lock", 8)) {
            String string = dn2.j("Lock");
            return new on(string);
        }
        return a;
    }
}

