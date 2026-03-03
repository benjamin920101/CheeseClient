/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hq
implements ff<fj> {
    private String a;
    private String b;
    private auu.a c;
    private int d;

    public hq() {
    }

    public hq(auk auk2, int n2) {
        this.a = auk2.b();
        this.b = auk2.d();
        this.c = auk2.c().c();
        this.d = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.c(16);
        this.d = em2.readByte();
        if (this.d == 0 || this.d == 2) {
            this.b = em2.c(32);
            this.c = auu.a.a(em2.c(16));
        }
    }

    @Override
    public void b(em em2) throws IOException {
        em2.a(this.a);
        em2.writeByte(this.d);
        if (this.d == 0 || this.d == 2) {
            em2.a(this.b);
            em2.a(this.c.a());
        }
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public auu.a d() {
        return this.c;
    }
}

