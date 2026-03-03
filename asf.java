/*
 * Decompiled with CFR 0.152.
 */
public class asf
extends ase {
    public asf(long l2, ase ase2) {
        super(l2);
        this.a = ase2;
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        int[] nArray;
        int[] nArray2 = this.a.a(n2 - 1, n3 - 1, n4 + 2, n5 + 2);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                \u2603 = nArray2[\u2603 + 1 + (i2 + 1) * (n4 + 2)];
                if (this.a(57) == 0) {
                    if (\u2603 == ady.q.az) {
                        nArray[\u2603 + i2 * n4] = ady.q.az + 128;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = \u2603;
                    continue;
                }
                nArray[\u2603 + i2 * n4] = \u2603;
            }
        }
        return nArray;
    }
}

