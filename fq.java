/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class fq
implements ff<fj> {
    private int a;
    private int b;

    public fq() {
    }

    public fq(pk pk2, int n2) {
        this.a = pk2.F();
        this.b = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readUnsignedByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.b(this.a);
        em2.writeByte(this.b);
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
}

