/*
 * Decompiled with CFR 0.152.
 */
public class asl
extends ase {
    public asl(long l2, ase ase2) {
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
                int n6;
                int n7;
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                n6 = nArray2[\u2603 + 1 + (i2 + 1) * (n4 + 2)];
                ady ady2 = ady.e(n6);
                if (n6 == ady.D.az) {
                    n7 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (n7 == ady.p.az || \u2603 == ady.p.az || \u2603 == ady.p.az || \u2603 == ady.p.az) {
                        nArray[\u2603 + i2 * n4] = ady.E.az;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = n6;
                    continue;
                }
                if (ady2 != null && ady2.l() == aej.class) {
                    n7 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (!(this.c(n7) && this.c(\u2603) && this.c(\u2603) && this.c(\u2603))) {
                        nArray[\u2603 + i2 * n4] = ady.M.az;
                        continue;
                    }
                    if (asl.b(n7) || asl.b(\u2603) || asl.b(\u2603) || asl.b(\u2603)) {
                        nArray[\u2603 + i2 * n4] = ady.F.az;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = n6;
                    continue;
                }
                if (n6 == ady.s.az || n6 == ady.X.az || n6 == ady.J.az) {
                    this.a(nArray2, nArray, \u2603, i2, n4, n6, ady.O.az);
                    continue;
                }
                if (ady2 != null && ady2.j()) {
                    this.a(nArray2, nArray, \u2603, i2, n4, n6, ady.P.az);
                    continue;
                }
                if (n6 == ady.aa.az || n6 == ady.ab.az) {
                    n7 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (asl.b(n7) || asl.b(\u2603) || asl.b(\u2603) || asl.b(\u2603)) {
                        nArray[\u2603 + i2 * n4] = n6;
                        continue;
                    }
                    if (!(this.d(n7) && this.d(\u2603) && this.d(\u2603) && this.d(\u2603))) {
                        nArray[\u2603 + i2 * n4] = ady.r.az;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = n6;
                    continue;
                }
                if (n6 != ady.p.az && n6 != ady.N.az && n6 != ady.w.az && n6 != ady.v.az) {
                    n7 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    if (asl.b(n7) || asl.b(\u2603) || asl.b(\u2603) || asl.b(\u2603)) {
                        nArray[\u2603 + i2 * n4] = ady.F.az;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = n6;
                    continue;
                }
                nArray[\u2603 + i2 * n4] = n6;
            }
        }
        return nArray;
    }

    private void a(int[] nArray, int[] nArray2, int n2, int n3, int n4, int n5, int n6) {
        if (asl.b(n5)) {
            nArray2[n2 + n3 * n4] = n5;
            return;
        }
        \u2603 = nArray[n2 + 1 + (n3 + 1 - 1) * (n4 + 2)];
        \u2603 = nArray[n2 + 1 + 1 + (n3 + 1) * (n4 + 2)];
        \u2603 = nArray[n2 + 1 - 1 + (n3 + 1) * (n4 + 2)];
        \u2603 = nArray[n2 + 1 + (n3 + 1 + 1) * (n4 + 2)];
        nArray2[n2 + n3 * n4] = asl.b(\u2603) || asl.b(\u2603) || asl.b(\u2603) || asl.b(\u2603) ? n6 : n5;
    }

    private boolean c(int n2) {
        if (ady.e(n2) != null && ady.e(n2).l() == aej.class) {
            return true;
        }
        return n2 == ady.M.az || n2 == ady.K.az || n2 == ady.L.az || n2 == ady.t.az || n2 == ady.u.az || asl.b(n2);
    }

    private boolean d(int n2) {
        return ady.e(n2) instanceof aek;
    }
}

