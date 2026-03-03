/*
 * Decompiled with CFR 0.152.
 */
public class amw {
    private final byte[] a;

    public amw() {
        this.a = new byte[2048];
    }

    public amw(byte[] byArray) {
        this.a = byArray;
        if (byArray.length != 2048) {
            throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + byArray.length);
        }
    }

    public int a(int n2, int n3, int n4) {
        return this.a(this.b(n2, n3, n4));
    }

    public void a(int n2, int n3, int n4, int n5) {
        this.a(this.b(n2, n3, n4), n5);
    }

    private int b(int n2, int n3, int n4) {
        return n3 << 8 | n4 << 4 | n2;
    }

    public int a(int n2) {
        \u2603 = this.c(n2);
        if (this.b(n2)) {
            return this.a[\u2603] & 0xF;
        }
        return this.a[\u2603] >> 4 & 0xF;
    }

    public void a(int n2, int n3) {
        \u2603 = this.c(n2);
        this.a[\u2603] = this.b(n2) ? (byte)(this.a[\u2603] & 0xF0 | n3 & 0xF) : (byte)(this.a[\u2603] & 0xF | (n3 & 0xF) << 4);
    }

    private boolean b(int n2) {
        return (n2 & 1) == 0;
    }

    private int c(int n2) {
        return n2 >> 1;
    }

    public byte[] a() {
        return this.a;
    }
}

