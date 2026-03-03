/*
 * Decompiled with CFR 0.152.
 */
public class ade {
    private cj a;
    private afh b;
    private int c;
    private int d;

    public ade(cj cj2, afh afh2, int n2, int n3) {
        this.a = cj2;
        this.c = n2;
        this.d = n3;
        this.b = afh2;
    }

    public cj a() {
        return this.a;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public afh d() {
        return this.b;
    }

    public boolean equals(Object object) {
        if (object instanceof ade) {
            ade ade2 = (ade)object;
            return this.a.equals(ade2.a) && this.c == ade2.c && this.d == ade2.d && this.b == ade2.b;
        }
        return false;
    }

    public String toString() {
        return "TE(" + this.a + ")," + this.c + "," + this.d + "," + this.b;
    }
}

