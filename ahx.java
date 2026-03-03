/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahx
extends afh {
    protected ahx() {
        super(arm.C, arn.u);
        this.a(yz.b);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.bf;
    }

    @Override
    public int a(Random random) {
        return 3 + random.nextInt(5);
    }

    @Override
    public int a(int n2, Random random) {
        return Math.min(9, this.a(random) + random.nextInt(1 + n2));
    }
}

