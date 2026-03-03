/*
 * Decompiled with CFR 0.152.
 */
public class ask
extends ase {
    private ase c;
    private ase d;

    public ask(long l2, ase ase2, ase ase3) {
        super(l2);
        this.c = ase2;
        this.d = ase3;
    }

    @Override
    public void a(long l2) {
        this.c.a(l2);
        this.d.a(l2);
        super.a(l2);
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        int[] nArray;
        int[] nArray2 = this.c.a(n2, n3, n4, n5);
        \u2603 = this.d.a(n2, n3, n4, n5);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n4 * n5; ++i2) {
            if (nArray2[i2] == ady.p.az || nArray2[i2] == ady.N.az) {
                nArray[i2] = nArray2[i2];
                continue;
            }
            if (\u2603[i2] == ady.w.az) {
                if (nArray2[i2] == ady.B.az) {
                    nArray[i2] = ady.A.az;
                    continue;
                }
                if (nArray2[i2] == ady.D.az || nArray2[i2] == ady.E.az) {
                    nArray[i2] = ady.E.az;
                    continue;
                }
                nArray[i2] = \u2603[i2] & 0xFF;
                continue;
            }
            nArray[i2] = nArray2[i2];
        }
        return nArray;
    }
}

