/*
 * Decompiled with CFR 0.152.
 */
public class ans {
    private final short[] a = new short[65536];
    private final alz b = afi.a.Q();

    public alz a(int n2, int n3, int n4) {
        \u2603 = n2 << 12 | n4 << 8 | n3;
        return this.a(\u2603);
    }

    public alz a(int n2) {
        if (n2 < 0 || n2 >= this.a.length) {
            throw new IndexOutOfBoundsException("The coordinate is out of range");
        }
        alz alz2 = afh.d.a(this.a[n2]);
        if (alz2 != null) {
            return alz2;
        }
        return this.b;
    }

    public void a(int n2, int n3, int n4, alz alz2) {
        int n5 = n2 << 12 | n4 << 8 | n3;
        this.a(n5, alz2);
    }

    public void a(int n2, alz alz2) {
        if (n2 < 0 || n2 >= this.a.length) {
            throw new IndexOutOfBoundsException("The coordinate is out of range");
        }
        this.a[n2] = (short)afh.d.b(alz2);
    }
}

