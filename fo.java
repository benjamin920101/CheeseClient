/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fo
implements ff<fj> {
    private int a;
    private cj b;
    private cq c;
    private String d;

    public fo() {
    }

    public fo(uq uq2) {
        this.a = uq2.F();
        this.b = uq2.n();
        this.c = uq2.b;
        this.d = uq2.c.B;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.d = em2.c(uq.a.A);
        this.b = em2.c();
        this.c = cq.b(em2.readUnsignedByte());
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.a(this.d);
        em2.a(this.b);
        em2.writeByte(this.c.b());
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public cj b() {
        return this.b;
    }

    public cq c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }
}

