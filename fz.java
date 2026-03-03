/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fz
implements ff<fj> {
    private adg a;
    private a[] b;

    public fz() {
    }

    public fz(int n2, short[] sArray, amy amy2) {
        this.a = new adg(amy2.a, amy2.b);
        this.b = new a[n2];
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2] = new a(sArray[i2], amy2);
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = new adg(em2.readInt(), em2.readInt());
        this.b = new a[em2.e()];
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            this.b[i2] = new a(em2.readShort(), afh.d.a(em2.e()));
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeInt(this.a.a);
        em2.writeInt(this.a.b);
        em2.b(this.b.length);
        for (a a2 : this.b) {
            em2.writeShort(a2.b());
            em2.b(afh.d.b(a2.c()));
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public a[] a() {
        return this.b;
    }

    public class a {
        private final short b;
        private final alz c;

        public a(short s2, alz alz2) {
            this.b = s2;
            this.c = alz2;
        }

        public a(short s2, amy amy2) {
            this.b = s2;
            this.c = amy2.g(this.a());
        }

        public cj a() {
            return new cj(fz.this.a.a(this.b >> 12 & 0xF, this.b & 0xFF, this.b >> 8 & 0xF));
        }

        public short b() {
            return this.b;
        }

        public alz c() {
            return this.c;
        }
    }
}

