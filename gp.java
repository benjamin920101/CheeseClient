/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;
import java.util.List;

public class gp
implements ff<fj> {
    private int[] a;
    private int[] b;
    private go.a[] c;
    private boolean d;

    public gp() {
    }

    public gp(List<amy> list) {
        int n2 = list.size();
        this.a = new int[n2];
        this.b = new int[n2];
        this.c = new go.a[n2];
        this.d = !list.get((int)0).p().t.o();
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            amy amy2 = list.get(\u2603);
            go.a \u26032 = go.a(amy2, true, this.d, 65535);
            this.a[\u2603] = amy2.a;
            this.b[\u2603] = amy2.b;
            this.c[\u2603] = \u26032;
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.d = em2.readBoolean();
        int n2 = em2.e();
        this.a = new int[n2];
        this.b = new int[n2];
        this.c = new go.a[n2];
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            this.a[\u2603] = em2.readInt();
            this.b[\u2603] = em2.readInt();
            this.c[\u2603] = new go.a();
            this.c[\u2603].b = em2.readShort() & 0xFFFF;
            this.c[\u2603].a = new byte[go.a(Integer.bitCount(this.c[\u2603].b), this.d, true)];
        }
        for (\u2603 = 0; \u2603 < n2; ++\u2603) {
            em2.readBytes(this.c[\u2603].a);
        }
    }

    @Override
    public void b(em em2) throws IOException {
        int n2;
        em2.writeBoolean(this.d);
        em2.b(this.c.length);
        for (n2 = 0; n2 < this.a.length; ++n2) {
            em2.writeInt(this.a[n2]);
            em2.writeInt(this.b[n2]);
            em2.writeShort((short)(this.c[n2].b & 0xFFFF));
        }
        for (n2 = 0; n2 < this.a.length; ++n2) {
            em2.writeBytes(this.c[n2].a);
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a(int n2) {
        return this.a[n2];
    }

    public int b(int n2) {
        return this.b[n2];
    }

    public int a() {
        return this.a.length;
    }

    public byte[] c(int n2) {
        return this.c[n2].a;
    }

    public int d(int n2) {
        return this.c[n2].b;
    }
}

