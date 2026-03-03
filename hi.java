/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hi
implements ff<fj> {
    private int a;

    public hi() {
    }

    public hi(int n2) {
        this.a = n2;
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
    }

    @Override
    public void a(fj fj2) {
        fj2.a(this);
    }

    public int a() {
        return this.a;
    }
}

