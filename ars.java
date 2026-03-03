/*
 * Decompiled with CFR 0.152.
 */
public class ars
extends ase {
    public ars(long l2, ase ase2) {
        super(l2);
        this.a = ase2;
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        int[] nArray;
        \u2603 = n2 - 1;
        \u2603 = n3 - 1;
        \u2603 = n4 + 2;
        \u2603 = n5 + 2;
        int[] nArray2 = this.a.a(\u2603, \u2603, \u2603, \u2603);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                \u2603 = nArray2[\u2603 + 0 + (i2 + 0) * \u2603];
                \u2603 = nArray2[\u2603 + 2 + (i2 + 0) * \u2603];
                \u2603 = nArray2[\u2603 + 0 + (i2 + 2) * \u2603];
                \u2603 = nArray2[\u2603 + 2 + (i2 + 2) * \u2603];
                \u2603 = nArray2[\u2603 + 1 + (i2 + 1) * \u2603];
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                if (\u2603 == 0 && (\u2603 != 0 || \u2603 != 0 || \u2603 != 0 || \u2603 != 0)) {
                    \u2603 = 1;
                    \u2603 = 1;
                    if (\u2603 != 0 && this.a(\u2603++) == 0) {
                        \u2603 = \u2603;
                    }
                    if (\u2603 != 0 && this.a(\u2603++) == 0) {
                        \u2603 = \u2603;
                    }
                    if (\u2603 != 0 && this.a(\u2603++) == 0) {
                        \u2603 = \u2603;
                    }
                    if (\u2603 != 0 && this.a(\u2603++) == 0) {
                        \u2603 = \u2603;
                    }
                    if (this.a(3) == 0) {
                        nArray[\u2603 + i2 * n4] = \u2603;
                        continue;
                    }
                    if (\u2603 == 4) {
                        nArray[\u2603 + i2 * n4] = 4;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = 0;
                    continue;
                }
                if (\u2603 > 0 && (\u2603 == 0 || \u2603 == 0 || \u2603 == 0 || \u2603 == 0)) {
                    if (this.a(5) == 0) {
                        if (\u2603 == 4) {
                            nArray[\u2603 + i2 * n4] = 4;
                            continue;
                        }
                        nArray[\u2603 + i2 * n4] = 0;
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

