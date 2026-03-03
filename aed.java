/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aed
extends ady {
    public aed(int n2) {
        super(n2);
        this.au.clear();
        this.ak = afi.m.Q();
        this.al = afi.m.Q();
        this.as.A = -999;
        this.as.D = 2;
        this.as.F = 50;
        this.as.G = 10;
        this.au.clear();
    }

    @Override
    public void a(adm adm2, Random random, cj cj2) {
        super.a(adm2, random, cj2);
        if (random.nextInt(1000) == 0) {
            int n2 = random.nextInt(16) + 8;
            \u2603 = random.nextInt(16) + 8;
            cj \u26032 = adm2.m(cj2.a(n2, 0, \u2603)).a();
            new aor().b(adm2, random, \u26032);
        }
    }
}

