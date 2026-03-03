/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aer
extends ady {
    private static final app aD = new app(false);

    protected aer(int n2) {
        super(n2);
        this.au.add(new ady.c(tp.class, 1, 2, 6));
        this.as.A = 1;
        this.as.B = 4;
        this.as.C = 20;
    }

    @Override
    public aoh a(Random random) {
        if (random.nextInt(5) > 0) {
            return aD;
        }
        return this.aA;
    }

    @Override
    protected ady d(int n2) {
        a a2 = new a(n2, this);
        a2.ap = (this.ap + 1.0f) * 0.5f;
        a2.an = this.an * 0.5f + 0.3f;
        a2.ao = this.ao * 0.5f + 1.2f;
        return a2;
    }

    @Override
    public void a(adm adm22, Random random, cj cj2) {
        adm adm22;
        ag.a(agi.b.c);
        for (int i2 = 0; i2 < 7; ++i2) {
            \u2603 = random.nextInt(16) + 8;
            \u2603 = random.nextInt(16) + 8;
            \u2603 = random.nextInt(adm22.m(cj2.a(\u2603, 0, \u2603)).o() + 32);
            ag.b(adm22, random, cj2.a(\u2603, \u2603, \u2603));
        }
        super.a(adm22, random, cj2);
    }

    public static class a
    extends aem {
        public a(int n2, ady ady2) {
            super(n2, ady2);
            this.as.A = 2;
            this.as.B = 2;
            this.as.C = 5;
        }

        @Override
        public void a(adm adm2, Random random, ans ans2, int n2, int n3, double d2) {
            this.ak = afi.c.Q();
            this.al = afi.d.Q();
            if (d2 > 1.75) {
                this.ak = afi.b.Q();
                this.al = afi.b.Q();
            } else if (d2 > -0.5) {
                this.ak = afi.d.Q().a(agf.a, agf.a.b);
            }
            this.b(adm2, random, ans2, n2, n3, d2);
        }

        @Override
        public void a(adm adm2, Random random, cj cj2) {
            this.as.a(adm2, random, this, cj2);
        }
    }
}

