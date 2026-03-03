/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hf
implements ff<fj> {
    private int a;
    private byte b;

    public hf() {
    }

    public hf(pk pk2, byte by) {
        this.a = pk2.F();
        this.b = by;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.e();
        this.b = em2.readByte();
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

    public pk a(adm adm2) {
        return adm2.a(this.a);
    }

    public byte a() {
        return this.b;
    }
}

