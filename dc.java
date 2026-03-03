/*
 * Decompiled with CFR 0.152.
 */
public class dc {
    protected final float a;
    protected final float b;
    protected final float c;

    public dc(float f2, float f3, float f4) {
        this.a = f2;
        this.b = f3;
        this.c = f4;
    }

    public dc(du du2) {
        this.a = du2.e(0);
        this.b = du2.e(1);
        this.c = du2.e(2);
    }

    public du a() {
        du du2 = new du();
        du2.a(new dr(this.a));
        du2.a(new dr(this.b));
        du2.a(new dr(this.c));
        return du2;
    }

    public boolean equals(Object object) {
        if (!(object instanceof dc)) {
            return false;
        }
        dc dc2 = (dc)object;
        return this.a == dc2.a && this.b == dc2.b && this.c == dc2.c;
    }

    public float b() {
        return this.a;
    }

    public float c() {
        return this.b;
    }

    public float d() {
        return this.c;
    }
}

