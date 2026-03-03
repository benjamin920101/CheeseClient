/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ajk
extends afh {
    public ajk(arm arm2) {
        super(arm2);
        this.a(yz.b);
    }

    @Override
    public int a(Random random) {
        return 2 + random.nextInt(2);
    }

    @Override
    public int a(int n2, Random random) {
        return ns.a(this.a(random) + random.nextInt(n2 + 1), 1, 5);
    }

    @Override
    public zw a(alz alz2, Random random, int n2) {
        return zy.cD;
    }

    @Override
    public arn g(alz alz2) {
        return arn.p;
    }

    @Override
    protected boolean I() {
        return true;
    }
}

