/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class aqh
extends aqu {
    public aqh() {
    }

    public aqh(adm adm2, Random random, int n2, int n3) {
        super(n2, n3);
        aqg.c c2 = new aqg.c(0, random, (n2 << 4) + 2, (n3 << 4) + 2);
        this.a.add(c2);
        c2.a(c2, this.a, random);
        this.c();
        this.a(adm2, random, 10);
    }
}

