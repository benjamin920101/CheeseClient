/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class gb
implements ff<fj> {
    private int a;

    public gb() {
    }

    public gb(int n2) {
        this.a = n2;
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readUnsignedByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
    }
}

