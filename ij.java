/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class ij
implements ff<ic> {
    private int a;
    private int b;

    public ij() {
    }

    public ij(int n2, int n3) {
        this.a = n2;
        this.b = n3;
    }

    @Override
    public void a(ic ic2) {
        ic2.a(this);
    }

    @Override
    public void a(em em2) throws IOException {
        this.a = em2.readByte();
        this.b = em2.readByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeByte(this.a);
        em2.writeByte(this.b);
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }
}

