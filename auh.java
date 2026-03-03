/*
 * Decompiled with CFR 0.152.
 */
public class auh {
    private cj e;
    public a a;
    public cq b;
    public aui c;
    public pk d;

    public auh(aui aui2, cq cq2, cj cj2) {
        this(auh$a.b, aui2, cq2, cj2);
    }

    public auh(aui aui2, cq cq2) {
        this(auh$a.b, aui2, cq2, cj.a);
    }

    public auh(pk pk2) {
        this(pk2, new aui(pk2.s, pk2.t, pk2.u));
    }

    public auh(a a2, aui aui2, cq cq2, cj cj2) {
        this.a = a2;
        this.e = cj2;
        this.b = cq2;
        this.c = new aui(aui2.a, aui2.b, aui2.c);
    }

    public auh(pk pk2, aui aui2) {
        this.a = auh$a.c;
        this.d = pk2;
        this.c = aui2;
    }

    public cj a() {
        return this.e;
    }

    public String toString() {
        return "HitResult{type=" + (Object)((Object)this.a) + ", blockpos=" + this.e + ", f=" + this.b + ", pos=" + this.c + ", entity=" + this.d + '}';
    }

    public static enum a {
        a,
        b,
        c;

    }
}

