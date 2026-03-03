/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hj
implements ff<fj> {
    private int a;
    private String b;

    public hj() {
    }

    public hj(int n2, auk auk2) {
        this.a = n2;
        this.b = auk2 == null ? "" : auk2.b();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readByte();
        this.b = em2.c(16);
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.a(this.b);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }
}

