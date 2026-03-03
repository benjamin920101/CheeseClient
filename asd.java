/*
 * Decompiled with CFR 0.152.
 */
public class asd
extends ase {
    public asd(long l2) {
        super(l2);
    }

    @Override
    public int[] a(int n22, int n3, int n4, int n5) {
        int n22;
        int[] nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                this.a((long)(n22 + \u2603), (long)(n3 + i2));
                nArray[\u2603 + i2 * n4] = this.a(10) == 0 ? 1 : 0;
            }
        }
        if (n22 > -n4 && n22 <= 0 && n3 > -n5 && n3 <= 0) {
            nArray[-n22 + -n3 * n4] = 1;
        }
        return nArray;
    }
}

