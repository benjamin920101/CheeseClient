/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahd
extends afh {
    public ahd(arm arm2) {
        super(arm2);
        this.a(yz.b);
    }

    @Override
    public int a(int n2, Random random) {
        return ns.a(this.a(random) + random.nextInt(n2 + 1), 1, 4);
    }

    @Override
    public int a(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.aT;
    }

    @Override
    public arn g(alz alz2) {
        return arn.d;
    }
}

