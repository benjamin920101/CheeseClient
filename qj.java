/*
 * Decompiled with CFR 0.152.
 */
public class qj
extends qe {
    private final double a;
    private final double b;
    private String c;

    public qj(qb qb2, String string, double d2, double d3, double d4) {
        super(qb2, string, d2);
        this.a = d3;
        this.b = d4;
        if (d3 > d4) {
            throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
        }
        if (d2 < d3) {
            throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
        }
        if (d2 > d4) {
            throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
        }
    }

    public qj a(String string) {
        this.c = string;
        return this;
    }

    public String g() {
        return this.c;
    }

    @Override
    public double a(double d2) {
        d2 = ns.a(d2, this.a, this.b);
        return d2;
    }
}

