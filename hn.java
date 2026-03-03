/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hn
implements ff<fj> {
    private int a;
    private int b;
    private zx c;

    public hn() {
    }

    public hn(int n2, int n3, zx zx2) {
        this.a = n2;
        this.b = n3;
        this.c = zx2 == null ? null : zx2.k();
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readShort();
        this.c = em2.i();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeShort(this.b);
        em2.a(this.c);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public zx a() {
        return this.c;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }
}

