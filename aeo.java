/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aeo
extends ady {
    protected boolean aD;

    protected aeo(int n2) {
        super(n2);
        this.a(0.8f, 0.4f);
        this.a(e);
        this.au.add(new ady.c(tp.class, 5, 2, 6));
        this.as.A = -999;
        this.as.B = 4;
        this.as.C = 10;
    }

    @Override
    public agw.a a(Random random2, cj cj2) {
        Random random2;
        double d2 = af.a((double)cj2.n() / 200.0, (double)cj2.p() / 200.0);
        if (d2 < -0.8) {
            int n2 = random2.nextInt(4);
            switch (n2) {
                case 0: {
                    return agw.a.g;
                }
                case 1: {
                    return agw.a.f;
                }
                case 2: {
                    return agw.a.i;
                }
            }
            return agw.a.h;
        }
        if (random2.nextInt(3) > 0) {
            int n3 = random2.nextInt(3);
            if (n3 == 0) {
                return agw.a.b;
            }
            if (n3 == 1) {
                return agw.a.e;
            }
            return agw.a.j;
        }
        return agw.a.a;
    }

    @Override
    public void a(adm adm22, Random random, cj cj2) {
        adm adm22;
        int n2;
        double d2 = af.a((double)(cj2.n() + 8) / 200.0, (double)(cj2.p() + 8) / 200.0);
        if (d2 < -0.8) {
            this.as.B = 15;
            this.as.C = 5;
        } else {
            this.as.B = 4;
            this.as.C = 10;
            ag.a(agi.b.c);
            for (n2 = 0; n2 < 7; ++n2) {
                \u2603 = random.nextInt(16) + 8;
                \u2603 = random.nextInt(16) + 8;
                \u2603 = random.nextInt(adm22.m(cj2.a(\u2603, 0, \u2603)).o() + 32);
                ag.b(adm22, random, cj2.a(\u2603, \u2603, \u2603));
            }
        }
        if (this.aD) {
            ag.a(agi.b.a);
            for (n2 = 0; n2 < 10; ++n2) {
                \u2603 = random.nextInt(16) + 8;
                \u2603 = random.nextInt(16) + 8;
                \u2603 = random.nextInt(adm22.m(cj2.a(\u2603, 0, \u2603)).o() + 32);
                ag.b(adm22, random, cj2.a(\u2603, \u2603, \u2603));
            }
        }
        super.a(adm22, random, cj2);
    }

    @Override
    protected ady d(int n2) {
        aeo aeo2 = new aeo(n2);
        aeo2.a("Sunflower Plains");
        aeo2.aD = true;
        aeo2.b(9286496);
        aeo2.aj = 14273354;
        return aeo2;
    }
}

