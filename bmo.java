/*
 * Decompiled with CFR 0.152.
 */
public class bmo
extends bmi {
    private double j;
    private double k;

    public bmo(String string) {
        super(string);
    }

    @Override
    public void j() {
        double d2;
        if (this.a.isEmpty()) {
            return;
        }
        ave ave2 = ave.A();
        double \u26032 = 0.0;
        if (ave2.f != null && ave2.h != null) {
            \u26032 = ave2.f.c(1.0f);
            if (!ave2.f.t.d()) {
                \u26032 = Math.random();
            }
        }
        for (d2 = \u26032 - this.j; d2 < -0.5; d2 += 1.0) {
        }
        while (d2 >= 0.5) {
            d2 -= 1.0;
        }
        d2 = ns.a(d2, -1.0, 1.0);
        this.k += d2 * 0.1;
        this.k *= 0.8;
        this.j += this.k;
        int \u26033 = (int)((this.j + 1.0) * (double)this.a.size()) % this.a.size();
        while (\u26033 < 0) {
            \u26033 = (\u26033 + this.a.size()) % this.a.size();
        }
        if (\u26033 != this.h) {
            this.h = \u26033;
            bml.a((int[][])this.a.get(this.h), this.f, this.g, this.d, this.e, false, false);
        }
    }
}

