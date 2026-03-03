/*
 * Decompiled with CFR 0.152.
 */
public class asr
extends ase {
    public asr(long l2, ase ase2) {
        super(l2);
        this.a = ase2;
    }

    @Override
    public int[] a(int n2, int n3, int n42, int n5) {
        int n42;
        \u2603 = n2 >> 1;
        \u2603 = n3 >> 1;
        \u2603 = (n42 >> 1) + 2;
        \u2603 = (n5 >> 1) + 2;
        int[] nArray = this.a.a(\u2603, \u2603, \u2603, \u2603);
        int \u26032 = \u2603 - 1 << 1;
        int \u26033 = \u2603 - 1 << 1;
        \u2603 = asc.a(\u26032 * \u26033);
        for (int i2 = 0; i2 < \u2603 - 1; ++i2) {
            \u2603 = (i2 << 1) * \u26032;
            \u2603 = nArray[\u2603 + 0 + (i2 + 0) * \u2603];
            \u2603 = nArray[\u2603 + 0 + (i2 + 1) * \u2603];
            for (\u2603 = 0; \u2603 < \u2603 - 1; ++\u2603) {
                this.a((long)(\u2603 + \u2603 << 1), (long)(i2 + \u2603 << 1));
                \u2603 = nArray[\u2603 + 1 + (i2 + 0) * \u2603];
                \u2603 = nArray[\u2603 + 1 + (i2 + 1) * \u2603];
                \u2603[\u2603] = \u2603;
                \u2603[\u2603++ + \u26032] = this.a(\u2603, \u2603);
                \u2603[\u2603] = this.a(\u2603, \u2603);
                \u2603[\u2603++ + \u26032] = this.b(\u2603, \u2603, \u2603, \u2603);
                \u2603 = \u2603;
                \u2603 = \u2603;
            }
        }
        int[] \u26034 = asc.a(n42 * n5);
        for (\u2603 = 0; \u2603 < n5; ++\u2603) {
            System.arraycopy(\u2603, (\u2603 + (n3 & 1)) * \u26032 + (n2 & 1), \u26034, \u2603 * n42, n42);
        }
        return \u26034;
    }

    public static ase b(long l2, ase ase2, int n2) {
        ase ase3 = ase2;
        for (int i2 = 0; i2 < n2; ++i2) {
            ase3 = new asr(l2 + (long)i2, ase3);
        }
        return ase3;
    }
}

