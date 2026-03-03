/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aeu
extends ady {
    private static final apk aD = new apk();
    private static final aps aE = new aps(false);
    private static final apf aF = new apf(false, false);
    private static final apf aG = new apf(false, true);
    private static final aok aH = new aok(afi.Y, 0);
    private int aI;

    public aeu(int n2, int n3) {
        super(n2);
        this.aI = n3;
        this.au.add(new ady.c(ua.class, 8, 4, 4));
        this.as.A = 10;
        if (n3 == 1 || n3 == 2) {
            this.as.C = 7;
            this.as.D = 1;
            this.as.E = 3;
        } else {
            this.as.C = 1;
            this.as.E = 1;
        }
    }

    @Override
    public aoh a(Random random) {
        if ((this.aI == 1 || this.aI == 2) && random.nextInt(3) == 0) {
            if (this.aI == 2 || random.nextInt(13) == 0) {
                return aG;
            }
            return aF;
        }
        if (random.nextInt(3) == 0) {
            return aD;
        }
        return aE;
    }

    @Override
    public aot b(Random random) {
        if (random.nextInt(5) > 0) {
            return new apu(akc.a.c);
        }
        return new apu(akc.a.b);
    }

    @Override
    public void a(adm adm22, Random random, cj cj2) {
        adm adm22;
        int n2;
        if (this.aI == 1 || this.aI == 2) {
            n2 = random.nextInt(3);
            for (\u2603 = 0; \u2603 < n2; ++\u2603) {
                \u2603 = random.nextInt(16) + 8;
                \u2603 = random.nextInt(16) + 8;
                cj cj3 = adm22.m(cj2.a(\u2603, 0, \u2603));
                aH.b(adm22, random, cj3);
            }
        }
        ag.a(agi.b.d);
        for (n2 = 0; n2 < 7; ++n2) {
            \u2603 = random.nextInt(16) + 8;
            \u2603 = random.nextInt(16) + 8;
            \u2603 = random.nextInt(adm22.m(cj2.a(\u2603, 0, \u2603)).o() + 32);
            ag.b(adm22, random, cj2.a(\u2603, \u2603, \u2603));
        }
        super.a(adm22, random, cj2);
    }

    @Override
    public void a(adm adm2, Random random, ans ans2, int n2, int n3, double d2) {
        if (this.aI == 1 || this.aI == 2) {
            this.ak = afi.c.Q();
            this.al = afi.d.Q();
            if (d2 > 1.75) {
                this.ak = afi.d.Q().a(agf.a, agf.a.b);
            } else if (d2 > -0.95) {
                this.ak = afi.d.Q().a(agf.a, agf.a.c);
            }
        }
        this.b(adm2, random, ans2, n2, n3, d2);
    }

    @Override
    protected ady d(int n2) {
        if (this.az == ady.V.az) {
            return new aeu(n2, 2).a(5858897, true).a("Mega Spruce Taiga").a(5159473).a(0.25f, 0.8f).a(new ady.a(this.an, this.ao));
        }
        return super.d(n2);
    }
}

