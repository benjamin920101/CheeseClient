/*
 * Decompiled with CFR 0.152.
 */
public class arv
extends ase {
    public arv(long l2, ase ase2) {
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
                if (this.a(nArray2, nArray, \u2603, i2, n4, \u2603, ady.s.az, ady.J.az) || this.b(nArray2, nArray, \u2603, i2, n4, \u2603, ady.ab.az, ady.aa.az) || this.b(nArray2, nArray, \u2603, i2, n4, \u2603, ady.ac.az, ady.aa.az) || this.b(nArray2, nArray, \u2603, i2, n4, \u2603, ady.V.az, ady.u.az)) continue;
                if (\u2603 == ady.r.az) {
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (\u2603 == ady.B.az || \u2603 == ady.B.az || \u2603 == ady.B.az || \u2603 == ady.B.az) {
                        nArray[\u2603 + i2 * n4] = ady.X.az;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = \u2603;
                    continue;
                }
                if (\u2603 == ady.v.az) {
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (\u2603 == ady.r.az || \u2603 == ady.r.az || \u2603 == ady.r.az || \u2603 == ady.r.az || \u2603 == ady.T.az || \u2603 == ady.T.az || \u2603 == ady.T.az || \u2603 == ady.T.az || \u2603 == ady.B.az || \u2603 == ady.B.az || \u2603 == ady.B.az || \u2603 == ady.B.az) {
                        nArray[\u2603 + i2 * n4] = ady.q.az;
                        continue;
                    }
                    if (\u2603 == ady.K.az || \u2603 == ady.K.az || \u2603 == ady.K.az || \u2603 == ady.K.az) {
                        nArray[\u2603 + i2 * n4] = ady.M.az;
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

    private boolean a(int[] nArray, int[] nArray2, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (arv.a(n5, n6)) {
            \u2603 = nArray[n2 + 1 + (n3 + 1 - 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 + 1 + (n3 + 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 - 1 + (n3 + 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 + (n3 + 1 + 1) * (n4 + 2)];
            nArray2[n2 + n3 * n4] = !this.b(\u2603, n6) || !this.b(\u2603, n6) || !this.b(\u2603, n6) || !this.b(\u2603, n6) ? n7 : n5;
            return true;
        }
        return false;
    }

    private boolean b(int[] nArray, int[] nArray2, int n2, int n3, int n4, int n5, int n6, int n7) {
        if (n5 == n6) {
            \u2603 = nArray[n2 + 1 + (n3 + 1 - 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 + 1 + (n3 + 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 - 1 + (n3 + 1) * (n4 + 2)];
            \u2603 = nArray[n2 + 1 + (n3 + 1 + 1) * (n4 + 2)];
            nArray2[n2 + n3 * n4] = !arv.a(\u2603, n6) || !arv.a(\u2603, n6) || !arv.a(\u2603, n6) || !arv.a(\u2603, n6) ? n7 : n5;
            return true;
        }
        return false;
    }

    private boolean b(int n2, int n3) {
        if (arv.a(n2, n3)) {
            return true;
        }
        ady ady2 = ady.e(n2);
        \u2603 = ady.e(n3);
        if (ady2 != null && \u2603 != null) {
            ady.b b2 = ady2.m();
            return b2 == (\u2603 = \u2603.m()) || b2 == ady.b.c || \u2603 == ady.b.c;
        }
        return false;
    }
}

