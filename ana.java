/*
 * Decompiled with CFR 0.152.
 */
public class ana {
    public final byte[] a;
    private final int b;
    private final int c;

    public ana(byte[] byArray, int n2) {
        this.a = byArray;
        this.b = n2;
        this.c = n2 + 4;
    }

    public int a(int n2, int n3, int n4) {
        \u2603 = n2 << this.c | n4 << this.b | n3;
        \u2603 = \u2603 >> 1;
        \u2603 = \u2603 & 1;
        if (\u2603 == 0) {
            return this.a[\u2603] & 0xF;
        }
        return this.a[\u2603] >> 4 & 0xF;
    }
}

