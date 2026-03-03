/*
 * Decompiled with CFR 0.152.
 */
public class asq
extends ase {
    public asq(long l2, ase ase2) {
        super(l2);
        this.a = ase2;
    }

    @Override
    public int[] a(int n2, int n3, int n42, int n5) {
        int n42;
        \u2603 = (n2 -= 2) >> 2;
        \u2603 = (n3 -= 2) >> 2;
        \u2603 = (n42 >> 2) + 2;
        \u2603 = (n5 >> 2) + 2;
        int[] nArray = this.a.a(\u2603, \u2603, \u2603, \u2603);
        int \u26032 = \u2603 - 1 << 2;
        int \u26033 = \u2603 - 1 << 2;
        \u2603 = asc.a(\u26032 * \u26033);
        for (int i2 = 0; i2 < \u2603 - 1; ++i2) {
            n6 = nArray[\u2603 + 0 + (i2 + 0) * \u2603];
            \u2603 = nArray[\u2603 + 0 + (i2 + 1) * \u2603];
            for (\u2603 = 0; \u2603 < \u2603 - 1; ++\u2603) {
                double d2 = 3.6;
                this.a((long)(\u2603 + \u2603 << 2), (long)(i2 + \u2603 << 2));
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6;
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6;
                this.a((long)(\u2603 + \u2603 + 1 << 2), (long)(i2 + \u2603 << 2));
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6;
                this.a((long)(\u2603 + \u2603 << 2), (long)(i2 + \u2603 + 1 << 2));
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6;
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
                this.a((long)(\u2603 + \u2603 + 1 << 2), (long)(i2 + \u2603 + 1 << 2));
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
                \u2603 = ((double)this.a(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
                int \u26034 = nArray[\u2603 + 1 + (i2 + 0) * \u2603] & 0xFF;
                int \u26035 = nArray[\u2603 + 1 + (i2 + 1) * \u2603] & 0xFF;
                for (int i3 = 0; i3 < 4; ++i3) {
                    \u2603 = ((i2 << 2) + i3) * \u26032 + (\u2603 << 2);
                    for (\u2603 = 0; \u2603 < 4; ++\u2603) {
                        double d3 = ((double)i3 - \u2603) * ((double)i3 - \u2603) + ((double)\u2603 - \u2603) * ((double)\u2603 - \u2603);
                        \u2603 = ((double)i3 - \u2603) * ((double)i3 - \u2603) + ((double)\u2603 - \u2603) * ((double)\u2603 - \u2603);
                        \u2603 = ((double)i3 - \u2603) * ((double)i3 - \u2603) + ((double)\u2603 - \u2603) * ((double)\u2603 - \u2603);
                        \u2603 = ((double)i3 - \u2603) * ((double)i3 - \u2603) + ((double)\u2603 - \u2603) * ((double)\u2603 - \u2603);
                        \u2603[\u2603++] = d3 < \u2603 && d3 < \u2603 && d3 < \u2603 ? n6 : (\u2603 < d3 && \u2603 < \u2603 && \u2603 < \u2603 ? \u26034 : (\u2603 < d3 && \u2603 < \u2603 && \u2603 < \u2603 ? \u2603 : \u26035));
                    }
                }
                int n6 = \u26034;
                \u2603 = \u26035;
            }
        }
        int[] \u26036 = asc.a(n42 * n5);
        for (\u2603 = 0; \u2603 < n5; ++\u2603) {
            System.arraycopy(\u2603, (\u2603 + (n3 & 3)) * \u26032 + (n2 & 3), \u26036, \u2603 * n42, n42);
        }
        return \u26036;
    }
}

