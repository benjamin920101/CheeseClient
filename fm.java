/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fm
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public fm() {
    }

    public fm(pk pk2) {
        this.a = pk2.F();
        this.b = ns.c(pk2.s * 32.0);
        this.c = ns.c(pk2.t * 32.0);
        this.d = ns.c(pk2.u * 32.0);
        if (pk2 instanceof uv) {
            this.e = 1;
        }
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.e = em2.readByte();
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.d = em2.readInt();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeByte(this.e);
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }
}

