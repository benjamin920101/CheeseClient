/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aej
extends ady {
    private boolean aD;
    private static final alz aE = afi.r.Q().a(ail.b, aio.a.d);
    private static final alz aF = afi.t.Q().a(aik.Q, aio.a.d).a(ahs.b, false);
    private static final alz aG = afi.t.Q().a(aik.Q, aio.a.a).a(ahs.b, false);

    public aej(int n2, boolean bl2) {
        super(n2);
        this.aD = bl2;
        this.as.A = bl2 ? 2 : 50;
        this.as.C = 25;
        this.as.B = 4;
        if (!bl2) {
            this.at.add(new ady.c(ts.class, 2, 1, 1));
        }
        this.au.add(new ady.c(tn.class, 10, 4, 4));
    }

    @Override
    public aoh a(Random random) {
        if (random.nextInt(10) == 0) {
            return this.aB;
        }
        if (random.nextInt(2) == 0) {
            return new aov(aE, aG);
        }
        if (!this.aD && random.nextInt(3) == 0) {
            return new ape(false, 10, 20, aE, aF);
        }
        return new apv(false, 4 + random.nextInt(7), aE, aF, true);
    }

    @Override
    public aot b(Random random) {
        if (random.nextInt(4) == 0) {
            return new apu(akc.a.c);
        }
        return new apu(akc.a.b);
    }

    @Override
    public void a(adm adm2, Random random, cj cj2) {
        super.a(adm2, random, cj2);
        int n2 = random.nextInt(16) + 8;
        \u2603 = random.nextInt(16) + 8;
        \u2603 = random.nextInt(adm2.m(cj2.a(n2, 0, \u2603)).o() * 2);
        new aph().b(adm2, random, cj2.a(n2, \u2603, \u2603));
        apw \u26032 = new apw();
        for (\u2603 = 0; \u2603 < 50; ++\u2603) {
            \u2603 = random.nextInt(16) + 8;
            \u2603 = 128;
            \u2603 = random.nextInt(16) + 8;
            \u26032.b(adm2, random, cj2.a(\u2603, 128, \u2603));
        }
    }
}

