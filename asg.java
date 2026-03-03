/*
 * Decompiled with CFR 0.152.
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class asg
extends ase {
    private static final Logger c = LogManager.getLogger();
    private ase d;

    public asg(long l2, ase ase2, ase ase3) {
        super(l2);
        this.a = ase2;
        this.d = ase3;
    }

    @Override
    public int[] a(int n2, int n3, int n4, int n5) {
        int[] nArray;
        int[] nArray2 = this.a.a(n2 - 1, n3 - 1, n4 + 2, n5 + 2);
        \u2603 = this.d.a(n2 - 1, n3 - 1, n4 + 2, n5 + 2);
        nArray = asc.a(n4 * n5);
        for (int i2 = 0; i2 < n5; ++i2) {
            for (\u2603 = 0; \u2603 < n4; ++\u2603) {
                this.a((long)(\u2603 + n2), (long)(i2 + n3));
                \u2603 = nArray2[\u2603 + 1 + (i2 + 1) * (n4 + 2)];
                \u2603 = \u2603[\u2603 + 1 + (i2 + 1) * (n4 + 2)];
                boolean bl2 = \u2603 = (\u2603 - 2) % 29 == 0;
                if (\u2603 > 255) {
                    c.debug("old! " + \u2603);
                }
                if (\u2603 != 0 && \u2603 >= 2 && (\u2603 - 2) % 29 == 1 && \u2603 < 128) {
                    if (ady.e(\u2603 + 128) != null) {
                        nArray[\u2603 + i2 * n4] = \u2603 + 128;
                        continue;
                    }
                    nArray[\u2603 + i2 * n4] = \u2603;
                    continue;
                }
                if (this.a(3) == 0 || \u2603) {
                    \u2603 = \u2603;
                    if (\u2603 == ady.r.az) {
                        \u2603 = ady.G.az;
                    } else if (\u2603 == ady.t.az) {
                        \u2603 = ady.H.az;
                    } else if (\u2603 == ady.Q.az) {
                        \u2603 = ady.R.az;
                    } else if (\u2603 == ady.S.az) {
                        \u2603 = ady.q.az;
                    } else if (\u2603 == ady.u.az) {
                        \u2603 = ady.I.az;
                    } else if (\u2603 == ady.V.az) {
                        \u2603 = ady.W.az;
                    } else if (\u2603 == ady.T.az) {
                        \u2603 = ady.U.az;
                    } else if (\u2603 == ady.q.az) {
                        \u2603 = this.a(3) == 0 ? ady.H.az : ady.t.az;
                    } else if (\u2603 == ady.B.az) {
                        \u2603 = ady.C.az;
                    } else if (\u2603 == ady.K.az) {
                        \u2603 = ady.L.az;
                    } else if (\u2603 == ady.p.az) {
                        \u2603 = ady.N.az;
                    } else if (\u2603 == ady.s.az) {
                        \u2603 = ady.X.az;
                    } else if (\u2603 == ady.Y.az) {
                        \u2603 = ady.Z.az;
                    } else if (asg.a(\u2603, ady.ab.az)) {
                        \u2603 = ady.aa.az;
                    } else if (\u2603 == ady.N.az && this.a(3) == 0) {
                        \u2603 = this.a(2);
                        \u2603 = \u2603 == 0 ? ady.q.az : ady.t.az;
                    }
                    if (\u2603 && \u2603 != \u2603) {
                        \u2603 = ady.e(\u2603 + 128) != null ? (\u2603 += 128) : \u2603;
                    }
                    if (\u2603 == \u2603) {
                        nArray[\u2603 + i2 * n4] = \u2603;
                        continue;
                    }
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 - 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 - 1 + (i2 + 1) * (n4 + 2)];
                    \u2603 = nArray2[\u2603 + 1 + (i2 + 1 + 1) * (n4 + 2)];
                    \u2603 = 0;
                    if (asg.a(\u2603, \u2603)) {
                        ++\u2603;
                    }
                    if (asg.a(\u2603, \u2603)) {
                        ++\u2603;
                    }
                    if (asg.a(\u2603, \u2603)) {
                        ++\u2603;
                    }
                    if (asg.a(\u2603, \u2603)) {
                        ++\u2603;
                    }
                    if (\u2603 >= 3) {
                        nArray[\u2603 + i2 * n4] = \u2603;
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

