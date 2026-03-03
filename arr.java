/*
 * Decompiled with CFR 0.152.
 */
public class arr
extends ase {
    private final a c;

    public arr(long l2, ase ase2, a a2) {
        super(l2);
        this.a = ase2;
        this.c = a2;
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        switch (this.c) {
            default: {
                return this.c(n2, n3, n4, n5);
            }
            case b: {
                return this.d(n2, n3, n4, n5);
            }
            case c: 
        }
        return this.e(n2, n3, n4, n5);
    }

    private int[] c(int n2, int n3, int n4, int n5) {
        int[] nArray;
        \u2603 = n2 - 1;
        \u2603 = n3 - 1;
        \u2603 = 1 + n4 + 1;
        \u2603 = 1 + n5 + 1;
        int[] nArray2 = this.a.a(\u2603, \u2603, \u2603, \u2603);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                int n6;
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                n6 = nArray2[\u2603 + 1 + (i2 + 1) * \u2603];
                if (n6 == 1) {
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * \u2603];
                    boolean bl2 = \u2603 == 3 || \u2603 == 3 || \u2603 == 3 || \u2603 == 3;
                    boolean bl3 = \u2603 = \u2603 == 4 || \u2603 == 4 || \u2603 == 4 || \u2603 == 4;
                    if (bl2 || \u2603) {
                        n6 = 2;
                    }
                }
                nArray[\u2603 + i2 * n4] = n6;
            }
        }
        return nArray;
    }

    private int[] d(int n2, int n3, int n4, int n5) {
        int[] nArray;
        \u2603 = n2 - 1;
        \u2603 = n3 - 1;
        \u2603 = 1 + n4 + 1;
        \u2603 = 1 + n5 + 1;
        int[] nArray2 = this.a.a(\u2603, \u2603, \u2603, \u2603);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                int n6;
                n6 = nArray2[\u2603 + 1 + (i2 + 1) * \u2603];
                if (n6 == 4) {
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * \u2603];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * \u2603];
                    boolean bl2 = \u2603 == 2 || \u2603 == 2 || \u2603 == 2 || \u2603 == 2;
                    boolean bl3 = \u2603 = \u2603 == 1 || \u2603 == 1 || \u2603 == 1 || \u2603 == 1;
                    if (\u2603 || bl2) {
                        n6 = 3;
                    }
                }
                nArray[\u2603 + i2 * n4] = n6;
            }
        }
        return nArray;
    }

    private int[] e(int n2, int n3, int n4, int n5) {
        int[] nArray;
        int[] nArray2 = this.a.a(n2, n3, n4, n5);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                \u2603 = nArray2[\u2603 + i2 * n4];
                if (\u2603 != 0 && this.a(13) == 0) {
                    \u2603 |= 1 + this.a(15) << 8 & 0xF00;
                }
                nArray[\u2603 + i2 * n4] = \u2603;
            }
        }
        return nArray;
    }

    public static enum a {
        a,
        b,
        c;

    }
}

