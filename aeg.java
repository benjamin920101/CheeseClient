/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aeg
extends ady {
    private int aG;
    protected static final aoj aD = new aoj(false, true);
    protected static final aoj aE = new aoj(false, false);
    protected static final apn aF = new apn(false);

    public aeg(int n2, int n3) {
        super(n2);
        this.aG = n3;
        this.as.A = 10;
        this.as.C = 2;
        if (this.aG == 1) {
            this.as.A = 6;
            this.as.B = 100;
            this.as.C = 1;
        }
        this.a(5159473);
        this.a(0.7f, 0.8f);
        if (this.aG == 2) {
            this.aj = 353825;
            this.ai = 3175492;
            this.a(0.6f, 0.6f);
        }
        if (this.aG == 0) {
            this.au.add(new ady.c(ua.class, 5, 4, 4));
        }
        if (this.aG == 3) {
            this.as.A = -999;
        }
    }

    @Override
    protected ady a(int n2, boolean bl2) {
        if (this.aG == 2) {
            this.aj = 353825;
            this.ai = n2;
            if (bl2) {
                this.aj = (this.aj & 0xFEFEFE) >> 1;
            }
            return this;
        }
        return super.a(n2, bl2);
    }

    @Override
    public aoh a(Random random) {
        if (this.aG == 3 && random.nextInt(3) > 0) {
            return aF;
        }
        if (this.aG == 2 || random.nextInt(5) == 0) {
            return aE;
        }
        return this.aA;
    }

    @Override
    public agw.a a(Random random, cj cj2) {
        if (this.aG == 1) {
            double d2 = ns.a((1.0 + af.a((double)cj2.n() / 48.0, (double)cj2.p() / 48.0)) / 2.0, 0.0, 0.9999);
            agw.a \u26032 = agw.a.values()[(int)(d2 * (double)agw.a.values().length)];
            if (\u26032 == agw.a.c) {
                return agw.a.b;
            }
            return \u26032;
        }
        return super.a(random, cj2);
    }

    @Override
    public void a(adm adm2, Random random2, cj cj2) {
        int n2;
        if (this.aG == 3) {
            for (n2 = 0; n2 < 4; ++n2) {
                for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                    Random random2;
                    aot \u26032;
                    \u2603 = n2 * 4 + 1 + 8 + random2.nextInt(3);
                    \u2603 = \u2603 * 4 + 1 + 8 + random2.nextInt(3);
                    cj cj3 = adm2.m(cj2.a(\u2603, 0, \u2603));
                    if (random2.nextInt(20) == 0) {
                        \u26032 = new aoz();
                        ((aoz)\u26032).b(adm2, random2, cj3);
                        continue;
                    }
                    \u26032 = this.a(random2);
                    \u26032.e();
                    if (!\u26032.b(adm2, random2, cj3)) continue;
                    ((aoh)\u26032).a(adm2, random2, cj3);
                }
            }
        }
        n2 = random2.nextInt(5) - 3;
        if (this.aG == 1) {
            n2 += 2;
        }
        block2: for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            \u2603 = random2.nextInt(3);
            if (\u2603 == 0) {
                ag.a(agi.b.b);
            } else if (\u2603 == 1) {
                ag.a(agi.b.e);
            } else if (\u2603 == 2) {
                ag.a(agi.b.f);
            }
            for (\u2603 = 0; \u2603 < 5; ++\u2603) {
                \u2603 = random2.nextInt(16) + 8;
                \u2603 = random2.nextInt(16) + 8;
                \u2603 = random2.nextInt(adm2.m(cj2.a(\u2603, 0, \u2603)).o() + 32);
                if (ag.b(adm2, random2, new cj(cj2.n() + \u2603, \u2603, cj2.p() + \u2603))) continue block2;
            }
        }
        super.a(adm2, random2, cj2);
    }

    @Override
    public int b(cj cj2) {
        int n2 = super.b(cj2);
        if (this.aG == 3) {
            return (n2 & 0xFEFEFE) + 2634762 >> 1;
        }
        return n2;
    }

    @Override
    protected ady d(int n22) {
        int n22;
        if (this.az == ady.t.az) {
            aeg aeg2 = new aeg(n22, 1);
            aeg2.a(new ady.a(this.an, this.ao + 0.2f));
            aeg2.a("Flower Forest");
            aeg2.a(6976549, true);
            aeg2.a(8233509);
            return aeg2;
        }
        if (this.az == ady.Q.az || this.az == ady.R.az) {
            return new aem(n22, this){

                @Override
                public aoh a(Random random) {
                    if (random.nextBoolean()) {
                        return aD;
                    }
                    return aE;
                }
            };
        }
        return new aem(n22, this){

            @Override
            public void a(adm adm2, Random random, cj cj2) {
                this.aE.a(adm2, random, cj2);
            }
        };
    }
}

