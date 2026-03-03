/*
 * Decompiled with CFR 0.152.
 */
public class asi
extends ase {
    public asi(long l2, ase ase2) {
        super(l2);
        this.a = ase2;
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        int[] nArray;
        int[] nArray2 = this.a.a(n2, n3, n4, n5);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                nArray[\u2603 + i2 * n4] = nArray2[\u2603 + i2 * n4] > 0 ? this.a(299999) + 2 : 0;
            }
        }
        return nArray;
    }
}

