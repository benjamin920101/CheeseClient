/*
 * Decompiled with CFR 0.152.
 */
public class asj
extends ase {
    public asj(long l2, ase ase2) {
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
                \u2603 = this.c(nArray2[\u2603 + 0 + (i2 + 1) * \u2603]);
                \u2603 = this.c(nArray2[\u2603 + 2 + (i2 + 1) * \u2603]);
                \u2603 = this.c(nArray2[\u2603 + 1 + (i2 + 0) * \u2603]);
                \u2603 = this.c(nArray2[\u2603 + 1 + (i2 + 2) * \u2603]);
                \u2603 = this.c(nArray2[\u2603 + 1 + (i2 + 1) * \u2603]);
                nArray[\u2603 + i2 * n4] = \u2603 != \u2603 || \u2603 != \u2603 || \u2603 != \u2603 || \u2603 != \u2603 ? ady.w.az : -1;
            }
        }
        return nArray;
    }

    private int c(int n2) {
        if (n2 >= 2) {
            return 2 + (n2 & 1);
        }
        return n2;
    }
}

