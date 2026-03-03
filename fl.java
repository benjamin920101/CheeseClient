/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fl
implements ff<fj> {
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;

    public fl() {
    }

    public fl(pp pp2) {
        this.a = pp2.F();
        this.b = ns.c(pp2.s * 32.0);
        this.c = ns.c(pp2.t * 32.0);
        this.d = ns.c(pp2.u * 32.0);
        this.e = pp2.j();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.d = em2.readInt();
        this.e = em2.readShort();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeInt(this.d);
        em2.writeShort(this.e);
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

