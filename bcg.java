/*
 * Decompiled with CFR 0.152.
 */
public class bcg {
    public aui a;
    public float b;
    public float c;

    public bcg(float f2, float f3, float f4, float f5, float f6) {
        this(new aui(f2, f3, f4), f5, f6);
    }

    public bcg a(float f2, float f3) {
        return new bcg(this, f2, f3);
    }

    public bcg(bcg bcg2, float f2, float f3) {
        this.a = bcg2.a;
        this.b = f2;
        this.c = f3;
    }

    public bcg(aui aui2, float f2, float f3) {
        this.a = aui2;
        this.b = f2;
        this.c = f3;
    }
}

