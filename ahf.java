/*
 * Decompiled with CFR 0.152.
 */
import java.util.Random;

public class ahf
extends agr {
    @Override
    public zw a(alz alz2, Random random, int n2) {
        if (n2 > 3) {
            n2 = 3;
        }
        if (random.nextInt(10 - n2 * 3) == 0) {
            return zy.ak;
        }
        return zw.a(this);
    }

    @Override
    public arn g(alz alz2) {
        return arn.m;
    }
}

