/*
 * Decompiled with CFR 0.152.
 */
import java.util.Arrays;

public class bff
extends adv {
    private static final alz f = afi.a.Q();
    private final cj g;
    private int[] h;
    private alz[] i;

    public bff(adm adm2, cj cj2, cj cj3, int n2) {
        super(adm2, cj2, cj3, n2);
        this.g = cj2.b(new df(n2, n2, n2));
        \u2603 = 8000;
        this.h = new int[8000];
        Arrays.fill(this.h, -1);
        this.i = new alz[8000];
    }

    @Override
    public akw s(cj cj2) {
        int n2 = (cj2.n() >> 4) - this.a;
        \u2603 = (cj2.p() >> 4) - this.b;
        return this.c[n2][\u2603].a(cj2, amy.a.b);
    }

    @Override
    public int b(cj cj2, int n2) {
        \u2603 = this.e(cj2);
        \u2603 = this.h[\u2603];
        if (\u2603 == -1) {
            this.h[\u2603] = \u2603 = super.b(cj2, n2);
        }
        return \u2603;
    }

    @Override
    public alz p(cj cj2) {
        int n2 = this.e(cj2);
        alz \u26032 = this.i[n2];
        if (\u26032 == null) {
            this.i[n2] = \u26032 = this.c(cj2);
        }
        return \u26032;
    }

    private alz c(cj cj2) {
        if (cj2.o() >= 0 && cj2.o() < 256) {
            int n2 = (cj2.n() >> 4) - this.a;
            \u2603 = (cj2.p() >> 4) - this.b;
            return this.c[n2][\u2603].g(cj2);
        }
        return f;
    }

    private int e(cj cj2) {
        int n2 = cj2.n() - this.g.n();
        \u2603 = cj2.o() - this.g.o();
        \u2603 = cj2.p() - this.g.p();
        return n2 * 400 + \u2603 * 20 + \u2603;
    }
}

