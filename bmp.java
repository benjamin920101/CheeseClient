/*
 * Decompiled with CFR 0.152.
 */
public class bmp
extends bmi {
    public double j;
    public double k;
    public static String l;

    public bmp(String string) {
        super(string);
        l = string;
    }

    @Override
    public void j() {
        ave ave2 = ave.A();
        if (ave2.f != null && ave2.h != null) {
            this.a(ave2.f, ave2.h.s, ave2.h.u, ave2.h.y, false, false);
        } else {
            this.a(null, 0.0, 0.0, 0.0, true, false);
        }
    }

    public void a(adm adm2, double d2, double d3, double d4, boolean bl2, boolean bl3) {
        if (this.a.isEmpty()) {
            return;
        }
        double \u26034 = 0.0;
        if (adm2 != null && !bl2) {
            cj cj2 = adm2.M();
            double \u26032 = (double)cj2.n() - d2;
            double \u26033 = (double)cj2.p() - d3;
            \u26034 = -(((d4 %= 360.0) - 90.0) * Math.PI / 180.0 - Math.atan2(\u26033, \u26032));
            if (!adm2.t.d()) {
                \u26034 = Math.random() * 3.1415927410125732 * 2.0;
            }
        }
        if (bl3) {
            this.j = \u26034;
        } else {
            double d5;
            for (d5 = \u26034 - this.j; d5 < -Math.PI; d5 += Math.PI * 2) {
            }
            while (d5 >= Math.PI) {
                d5 -= Math.PI * 2;
            }
            d5 = ns.a(d5, -1.0, 1.0);
            this.k += d5 * 0.1;
            this.k *= 0.8;
            this.j += this.k;
        }
        int n2 = (int)((this.j / (Math.PI * 2) + 1.0) * (double)this.a.size()) % this.a.size();
        while (n2 < 0) {
            n2 = (n2 + this.a.size()) % this.a.size();
        }
        if (n2 != this.h) {
            this.h = n2;
            bml.a((int[][])this.a.get(this.h), this.f, this.g, this.d, this.e, false, false);
        }
    }
}

