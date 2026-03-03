/*
 * Decompiled with CFR 0.152.
 */
public class bic
implements bia {
    private bid a;
    private double b;
    private double c;
    private double d;

    public bic() {
        this(bib.a());
    }

    public bic(bid bid2) {
        this.a = bid2;
    }

    @Override
    public void a(double d2, double d3, double d4) {
        this.b = d2;
        this.c = d3;
        this.d = d4;
    }

    public boolean b(double d2, double d3, double d4, double d5, double d6, double d7) {
        return this.a.b(d2 - this.b, d3 - this.c, d4 - this.d, d5 - this.b, d6 - this.c, d7 - this.d);
    }

    @Override
    public boolean a(aug aug2) {
        return this.b(aug2.a, aug2.b, aug2.c, aug2.d, aug2.e, aug2.f);
    }
}

