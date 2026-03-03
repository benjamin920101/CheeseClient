/*
 * Decompiled with CFR 0.152.
 */
import java.io.IOException;

public class hl
implements ff<fj> {
    private int a;
    private int b;
    private int c;

    public hl() {
    }

    public hl(int n2, pk pk2, pk pk3) {
        this.a = n2;
        this.b = pk2.F();
        this.c = pk3 != null ? pk3.F() : -1;
    }

    @Override
    public void a(em em2) throws IOException {
        this.b = em2.readInt();
        this.c = em2.readInt();
        this.a = em2.readUnsignedByte();
    }

    @Override
    public void b(em em2) throws IOException {
        em2.writeInt(this.b);
        em2.writeInt(this.c);
        em2.writeByte(this.a);
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
}

