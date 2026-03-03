/*
 * Decompiled with CFR 0.152.
 */
public abstract class qe
implements qb {
    private final qb a;
    private final String b;
    private final double c;
    private boolean d;

    protected qe(qb qb2, String string, double d2) {
        this.a = qb2;
        this.b = string;
        this.c = d2;
        if (string == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
    }

    @Override
    public String a() {
        return this.b;
    }

    @Override
    public double b() {
        return this.c;
    }

    @Override
    public boolean c() {
        return this.d;
    }

    public qe a(boolean bl2) {
        this.d = bl2;
        return this;
    }

    @Override
    public qb d() {
        return this.a;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof qb && this.b.equals(((qb)object).a());
    }
}

