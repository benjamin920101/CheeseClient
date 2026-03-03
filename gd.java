/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.List;

public class gd
implements ff<fj> {
    private int a;
    private zx[] b;

    public gd() {
    }

    public gd(int n2, List<zx> list) {
        this.a = n2;
        this.b = new zx[list.size()];
        for (int i2 = 0; i2 < this.b.length; ++i2) {
            zx zx2 = list.get(i2);
            this.b[i2] = zx2 == null ? null : zx2.k();
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readUnsignedByte();
        int n2 = em2.readShort();
        this.b = new zx[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            this.b[\u2603] = em2.i();
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.writeShort(this.b.length);
        for (zx zx2 : this.b) {
            em2.a(zx2);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public zx[] b() {
        return this.b;
    }
}

